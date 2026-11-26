import SwiftUI
import PhotosUI
import VisionKit

struct CaptureFlow: View {
    @EnvironmentObject private var store: PiggyStore
    @Environment(\.dismiss) private var dismiss
    @State private var cameraPresented = false
    @State private var photo: PhotosPickerItem?
    @State private var pages: [UIImage] = []
    @State private var draft: ReceiptDraft?
    @State private var processing = false
    @State private var message: String?
    @State private var requestAI = false
    @State private var pendingAIImage: UIImage?
    @State private var showingDiscard = false
    @State private var draftID = UUID()
    var body: some View {
        NavigationStack {
            Group {
                if processing {
                    VStack(spacing: 20) {
                        MascotView(pose: "catch-surprised", size: 190).accessibilityHidden(true)
                        ProgressView("Piggy liest deinen Beleg …")
                        Text("Danach kannst du alle Angaben prüfen und korrigieren.")
                            .foregroundStyle(Theme.secondary).multilineTextAlignment(.center)
                    }.padding(24).frame(maxWidth: .infinity, maxHeight: .infinity)
                } else if let draft {
                    DraftReviewView(draft: draft, image: pages.first, warning: message, onSaved: continueQueue)
                        .id(draftID)
                } else {
                    ScrollView {
                        VStack(spacing: 20) {
                            MascotView(pose: "catch-surprised", size: 210).accessibilityHidden(true)
                            Text("Ein Beleg. Alle Details.").font(.title.bold()).multilineTextAlignment(.center)
                            Text("Halte den Beleg gut beleuchtet in die Kamera. Jede Aufnahme wird einzeln geprüft, bevor du sie speicherst.")
                                .foregroundStyle(Theme.secondary).multilineTextAlignment(.center)
                            if let message { ScreenError(message: message) }
                            if VNDocumentCameraViewController.isSupported {
                                BusyButton(title: "Mit Kamera scannen", busy: false) { cameraPresented = true }
                            } else {
                                Text("Auf diesem Gerät ist der Dokumentenscanner nicht verfügbar. Wähle ein Foto oder erfasse den Beleg selbst.")
                                    .font(.footnote).foregroundStyle(Theme.secondary)
                            }
                            PhotosPicker(selection: $photo, matching: .images) {
                                Label("Foto auswählen", systemImage: "photo")
                                    .frame(maxWidth: .infinity, minHeight: 44)
                            }.buttonStyle(.bordered)
                            Button("Beleg selbst erfassen") { draft = blankDraft(); draftID = UUID() }
                                .frame(minHeight: 44)
                        }.padding(24)
                    }
                }
            }
            .background(Theme.background)
            .navigationTitle(draft == nil ? "Beleg erfassen" : "Beleg prüfen")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .cancellationAction) {
                    Button("Abbrechen") {
                        if draft != nil || processing { showingDiscard = true } else { dismiss() }
                    }.disabled(processing)
                }
                if pages.count > 1 {
                    ToolbarItem(placement: .principal) { Text("Noch \(pages.count) Aufnahmen").font(.subheadline) }
                }
            }
            .confirmationDialog("Erfassung beenden?", isPresented: $showingDiscard, titleVisibility: .visible) {
                Button("Ungespeicherte Aufnahmen verwerfen", role: .destructive) { dismiss() }
                Button("Weiter prüfen", role: .cancel) {}
            } message: { Text("Bereits gespeicherte Belege bleiben erhalten.") }
            .sheet(isPresented: $cameraPresented) {
                DocumentCamera { result in
                    cameraPresented = false
                    switch result {
                    case .success(let images):
                        pages = images
                        if let image = images.first { beginRecognition(image) }
                    case .failure(let error): if !(error is CancellationError) { message = error.localizedDescription }
                    }
                }
                .ignoresSafeArea()
            }
            .onChange(of: photo) { _, item in
                guard let item else { return }
                Task {
                    do {
                        guard let data = try await item.loadTransferable(type: Data.self), let image = UIImage(data: data) else {
                            message = "Das Foto konnte nicht geöffnet werden. Bitte wähle ein anderes Bild."
                            return
                        }
                        pages = [image]
                        beginRecognition(image)
                    } catch { message = error.localizedDescription }
                }
            }
            .alert("KI-Erkennung erlauben?", isPresented: $requestAI) {
                Button("Lokal erkennen", role: .cancel) { if let image = pendingAIImage { Task { await recognise(image, allowAI: false) } } }
                Button("KI erlauben") {
                    store.aiConsent = true
                    if let image = pendingAIImage { Task { await recognise(image, allowAI: true) } }
                }
            } message: {
                Text("Für eine genauere Aufschlüsselung wird der erkannte Quittungstext an Anthropic (USA) oder, falls du früher einen eigenen Gemini-Schlüssel hinterlegt hast, an Google (USA) übermittelt. Ohne Zustimmung bleibt die Erkennung auf dem Gerät. Du kannst die Zustimmung später im Profil ändern.")
            }
        }
    }
    private func beginRecognition(_ image: UIImage) {
        message = nil
        if store.aiConsent { Task { await recognise(image, allowAI: true) } }
        else { pendingAIImage = image; requestAI = true }
    }
    @MainActor private func recognise(_ image: UIImage, allowAI: Bool) async {
        processing = true
        do {
            draft = try await store.processImage(image: image, allowAI: allowAI)
            draftID = UUID()
        } catch {
            // Keep the captured photo so a temporary network failure doesn't force another scan.
            message = "Die Erkennung konnte nicht abgeschlossen werden. Du kannst den Beleg selbst erfassen oder die Erfassung erneut starten. " + error.localizedDescription
            draft = blankDraft()
            draftID = UUID()
        }
        processing = false
    }
    private func continueQueue() {
        draft = nil
        if !pages.isEmpty { pages.removeFirst() }
        if let next = pages.first { beginRecognition(next) } else { dismiss() }
    }
    private func blankDraft() -> ReceiptDraft {
        let formatter = DateFormatter(); formatter.dateFormat = "yyyy-MM-dd"; formatter.locale = Locale(identifier: "en_US_POSIX")
        return ReceiptDraft(store_name: "", receipt_date: formatter.string(from: Date()), total_amount: 0, currency: "CHF", payment_method: "", notes: "", raw_ocr_text: "", items: [])
    }
}

private struct DocumentCamera: UIViewControllerRepresentable {
    let completed: (Result<[UIImage], Error>) -> Void
    func makeCoordinator() -> Coordinator { Coordinator(completed) }
    func makeUIViewController(context: Context) -> VNDocumentCameraViewController {
        let controller = VNDocumentCameraViewController()
        controller.delegate = context.coordinator
        return controller
    }
    func updateUIViewController(_ controller: VNDocumentCameraViewController, context: Context) {}
    final class Coordinator: NSObject, VNDocumentCameraViewControllerDelegate {
        let completed: (Result<[UIImage], Error>) -> Void
        init(_ completed: @escaping (Result<[UIImage], Error>) -> Void) { self.completed = completed }
        func documentCameraViewController(_ controller: VNDocumentCameraViewController, didFinishWith scan: VNDocumentCameraScan) {
            guard scan.pageCount > 0 else { completed(.failure(CancellationError())); return }
            completed(.success((0..<scan.pageCount).map { scan.imageOfPage(at: $0) }))
        }
        func documentCameraViewControllerDidCancel(_ controller: VNDocumentCameraViewController) { completed(.failure(CancellationError())) }
        func documentCameraViewController(_ controller: VNDocumentCameraViewController, didFailWithError error: Error) { completed(.failure(error)) }
    }
}

struct DraftReviewView: View {
    @EnvironmentObject private var store: PiggyStore
    @State private var draft: ReceiptDraft
    let image: UIImage?
    let onSaved: () -> Void
    @State private var saving = false
    @State private var message: String?
    @State private var candidates: [Receipt] = []
    @State private var duplicateSheet = false
    @State private var previewPhoto = false
    init(draft: ReceiptDraft, image: UIImage?, warning: String? = nil, onSaved: @escaping () -> Void) {
        _draft = State(initialValue: draft)
        _message = State(initialValue: warning)
        self.image = image
        self.onSaved = onSaved
    }
    var body: some View {
        Form {
            if let message { Section { ScreenError(message: message) } }
            if let image {
                Section {
                    Button { previewPhoto = true } label: {
                        HStack {
                            Image(uiImage: image).resizable().scaledToFill().frame(width: 52, height: 70).clipped().clipShape(RoundedRectangle(cornerRadius: 8))
                            Label("Originalbeleg ansehen", systemImage: "arrow.up.left.and.arrow.down.right")
                        }
                    }.buttonStyle(.plain)
                }
            }
            Section("Beleg") {
                TextField("Geschäft", text: $draft.store_name)
                TextField("Datum (JJJJ-MM-TT)", text: $draft.receipt_date).keyboardType(.numbersAndPunctuation)
                DecimalField(title: "Gesamtbetrag", value: $draft.total_amount)
                TextField("Währung", text: $draft.currency).textInputAutocapitalization(.characters).autocorrectionDisabled()
                ReceiptPaymentFields(method: $draft.payment_method, card: $draft.payment_card)
            }
            Section {
                ForEach($draft.items) { $item in
                    VStack(alignment: .leading, spacing: 10) {
                        TextField("Artikelname", text: $item.name)
                        HStack {
                            DecimalField(title: "Menge", value: $item.quantity)
                            DecimalField(title: "Artikelbetrag", value: $item.total_price)
                        }
                        CategoryPicker(category: Binding(get: { item.tags.first ?? "Diverses" }, set: { item.tags = [$0]; item.subcategory = nil }))
                    }.padding(.vertical, 5)
                }
                .onDelete { draft.items.remove(atOffsets: $0) }
                Button { draft.items.append(DraftItem(name: "", quantity: 1, unit_price: 0, total_price: 0, tags: ["Diverses"], subcategory: nil)) } label: {
                    Label("Artikel hinzufügen", systemImage: "plus")
                }
            } header: { Text("Artikel prüfen") } footer: {
                Text("Die Angaben der Erkennung können falsch sein. Vergleiche Artikel und Gesamtbetrag mit dem Original.")
            }
            Section("Notiz") { TextField("Optional", text: $draft.notes, axis: .vertical).lineLimit(3...6) }
            Section {
                BusyButton(title: "Beleg speichern", busy: saving, disabled: !valid) { Task { await prepareSave() } }
            }
        }
        .disabled(saving)
        .interactiveDismissDisabled(saving)
        .sheet(isPresented: $previewPhoto) { if let image { PhotoPreview(image: image) } }
        .sheet(isPresented: $duplicateSheet) {
            NavigationStack {
                List {
                    Section {
                        Text("Dieser Beleg ähnelt einem bereits gespeicherten Einkauf. Du entscheidest, ob es derselbe Einkauf ist.")
                    }
                    ForEach(candidates) { candidate in
                        Section {
                            ReceiptRow(receipt: candidate)
                            Button("Fehlende Angaben im vorhandenen Beleg ergänzen") { Task { await merge(candidate) } }
                        }
                    }
                    Section {
                        Button("Als eigenen Einkauf speichern") { Task { await save(separateFrom: candidates.map(\.id)) } }
                    } footer: { Text("Ein neuer Einkauf zählt zusätzlich zu deinen Ausgaben. Beim Ergänzen bleiben vorhandene Angaben, Artikel und Bilder erhalten; fehlende Angaben werden aus dem Scan übernommen.") }
                    if let message { ScreenError(message: message) }
                    if saving { ProgressView("Beleg wird gespeichert …") }
                }
                .navigationTitle("Möglicher Doppelbeleg")
                .navigationBarTitleDisplayMode(.inline)
                .toolbar { ToolbarItem(placement: .cancellationAction) { Button("Zurück") { duplicateSheet = false }.disabled(saving) } }
                .disabled(saving)
                .interactiveDismissDisabled(saving)
            }
        }
    }
    private var valid: Bool { (try? ReceiptRules.validate(draft)) != nil }
    @MainActor private func prepareSave() async {
        guard !saving, valid else { return }
        candidates = store.duplicateCandidates(for: draft)
        if !candidates.isEmpty { duplicateSheet = true }
        else { await save(separateFrom: []) }
    }
    @MainActor private func save(separateFrom: [UUID]) async {
        guard !saving else { return }
        saving = true
        message = nil
        do {
            _ = try await store.save(draft: draft, image: image, separateFrom: separateFrom)
            NotificationCenter.default.post(name: .init("piggy.receiptSaved"), object: nil)
            duplicateSheet = false
            onSaved()
        } catch { message = error.localizedDescription }
        saving = false
    }
    @MainActor private func merge(_ receipt: Receipt) async {
        guard !saving else { return }
        saving = true
        message = nil
        do {
            _ = try await store.merge(draft: draft, image: image, target: receipt)
            duplicateSheet = false
            onSaved()
        } catch { message = error.localizedDescription }
        saving = false
    }
}

struct PhotoPreview: View {
    @Environment(\.dismiss) private var dismiss
    let image: UIImage
    @State private var zoom: CGFloat = 1
    var body: some View {
        NavigationStack {
            ScrollView([.horizontal, .vertical]) {
                Image(uiImage: image).resizable().scaledToFit()
                    .frame(width: min(image.size.width, UIScreen.main.bounds.width) * zoom)
                    .accessibilityLabel("Originalbeleg")
            }
            .background(Theme.background)
            .navigationTitle("Originalbeleg")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .cancellationAction) { Button("Schliessen") { dismiss() } }
                ToolbarItemGroup(placement: .bottomBar) {
                    Button { zoom = max(1, zoom - 0.5) } label: { Label("Verkleinern", systemImage: "minus.magnifyingglass") }.disabled(zoom <= 1)
                    Spacer()
                    Button { zoom = min(4, zoom + 0.5) } label: { Label("Vergrössern", systemImage: "plus.magnifyingglass") }.disabled(zoom >= 4)
                }
            }
        }
    }
}
