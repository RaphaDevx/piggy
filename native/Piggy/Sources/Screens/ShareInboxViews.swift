import SwiftUI
import UniformTypeIdentifiers
import PDFKit

struct ShareInboxView: View {
    @EnvironmentObject private var store: PiggyStore
    @Environment(\.scenePhase) private var phase
    @State private var records: [SharedImport] = []
    @State private var importer = false
    @State private var error: String?
    var body: some View {
        List {
            Section {
                Text("Aus Fotos, Dateien oder Safari: Teilen → Piggy. Danach hier als Beleg oder Coupon prüfen. Es wird nichts automatisch an eine Firma gesendet.").font(.footnote).foregroundStyle(.secondary)
                Button("PDF oder Bild aus Dateien wählen") { importer = true }
            }
            ForEach(records) { record in
                NavigationLink { SharedImportDetail(record: record) } label: {
                    Label { VStack(alignment: .leading) {
                        Text(record.name).lineLimit(2)
                        Text(record.kind == .web ? "Weblink – noch keine Datei" : record.kind == .pdf ? "PDF" : "Bild").font(.caption).foregroundStyle(.secondary)
                        if record.owner == nil { Text("Noch keinem Konto zugeordnet").font(.caption).foregroundStyle(.secondary) }
                    } } icon: { Image(systemName: record.kind == .pdf ? "doc" : record.kind == .web ? "link" : "photo") }
                }
            }
            if records.isEmpty && error == nil { ContentUnavailableView("Eingang ist leer", systemImage: "tray", description: Text("Geteilte Dateien bleiben hier, bis du sie geprüft oder entfernt hast.")) }
            if let error { ScreenError(message: error, retry: { load() }) }
        }.navigationTitle("Eingang")
            .task(id: store.userID) { load() }
            .onChange(of: phase) { _, value in if value == .active { load() } }
            .onAppear { load() }
            .refreshable { load() }
            .fileImporter(isPresented: $importer, allowedContentTypes: [.pdf, .image], allowsMultipleSelection: true) { result in
                do {
                    let files = try result.get()
                    guard files.count <= 10 else { throw PiggyFailure.validation("Bitte höchstens 10 Dateien wählen.") }
                    for file in files { try SharedImportService.enqueue(file: file, owner: store.requireUser()) }
                    load()
                } catch { self.error = error.localizedDescription; loadKeepingError() }
            }
    }
    private func loadKeepingError() { if let uid = store.userID { records = (try? ShareInbox.list(owner: uid, at: ShareInbox.root())) ?? records } }
    private func load() {
        records = []; error = nil
        do { records = try ShareInbox.list(owner: store.requireUser(), at: ShareInbox.root()) }
        catch { self.error = error.localizedDescription }
    }
}
private struct SharedImportDetail: View {
    @EnvironmentObject private var store: PiggyStore
    @Environment(\.dismiss) private var dismiss
    let record: SharedImport
    @State private var bytes: Data?
    @State private var preview: UIImage?
    @State private var draft: ReceiptDraft?
    @State private var busy = false
    @State private var coupon = false
    @State private var couponSaved = false
    @State private var pdf = false
    @State private var consent = false
    @State private var page = 0
    @State private var pageCount = 1
    @State private var error: String?
    var body: some View {
        Group {
            if let draft, let preview {
                DraftReviewView(draft: draft, image: preview, sourcePDF: record.kind == .pdf ? bytes : nil, onSaved: finish)
            } else {
                Form {
                    Section(record.name) {
                        if let preview { Image(uiImage: preview).resizable().scaledToFit().frame(maxHeight: 300) }
                        if record.kind == .pdf, bytes != nil {
                            Button("Vollständiges Original-PDF ansehen") { pdf = true }
                            if pageCount > 1 { Picker("Seite für Couponcode", selection: $page) { ForEach(0..<pageCount, id: \.self) { Text("Seite \($0+1)").tag($0) } } }
                        }
                        if record.kind == .web, let bytes, let raw = String(data: bytes, encoding: .utf8), let url = MerchantBrand.catalogURL(raw) {
                            Link("Weblink im Browser öffnen", destination: url)
                            Text("Es wurde nur ein Link geteilt. Öffne dort die Quittung als PDF und teile die Datei mit Piggy oder speichere sie zuerst in Dateien. Piggy lädt diese Adresse nicht automatisch herunter.").font(.footnote)
                        }
                    }
                    if record.kind != .web {
                        Section {
                            Toggle("Diese Datei mit meinem aktuellen Konto übernehmen", isOn: $consent)
                            Text(store.aiConsent ? "Beim Prüfen als Beleg gilt deine aktivierte KI-Einstellung. Coupons werden lokal nach Codes durchsucht." : "Die Erkennung bleibt auf diesem Gerät. Prüfe die Angaben vor dem Speichern.").font(.footnote).foregroundStyle(.secondary)
                            Button("Als Beleg prüfen") { recognise() }.disabled(!consent || busy || bytes == nil)
                            Button("Als Coupon erfassen") { coupon = true }.disabled(!consent || busy || preview == nil)
                        }
                    }
                    if busy { ProgressView("Datei wird gelesen …") }
                    if let error { ScreenError(message: error) }
                    Section { Button("Aus dem Eingang entfernen", role: .destructive) { finish() }.disabled(busy) }
                }
            }
        }.navigationTitle("Geteilte Datei")
            .task { load() }
            .onChange(of: page) { _, value in if let bytes, let document = try? SharedImportService.document(bytes), let pdfPage = document.page(at: value) { preview = SharedImportService.image(pdfPage) } }
            .sheet(isPresented: $pdf) { if let bytes { NavigationStack { PDFOriginalView(data: bytes).navigationTitle("Original-PDF").toolbar { ToolbarItem(placement: .cancellationAction) { Button("Schliessen") { pdf = false } } } } } }
            .sheet(isPresented: $coupon, onDismiss: { if couponSaved { finish() } }) {
                NavigationStack { CouponEditor(seedImage: preview, sourcePDF: record.kind == .pdf ? bytes : nil, onSaved: { couponSaved = true }) }
            }
    }
    private func load() {
        do {
            bytes = try ShareInbox.data(record, owner: store.requireUser(), at: ShareInbox.root())
            if record.kind == .image, let bytes { preview = UIImage(data: bytes) }
            if record.kind == .pdf, let bytes {
                let document = try SharedImportService.document(bytes); pageCount = document.pageCount
                if let first = document.page(at: 0) { preview = SharedImportService.image(first) }
            }
        } catch { self.error = error.localizedDescription }
    }
    private func recognise() {
        guard let bytes else { return }; busy = true; error = nil
        Task {
            do {
                let result = try await SharedImportService.draft(data: bytes, kind: record.kind, store: store)
                draft = result.0; preview = result.1
            } catch { self.error = error.localizedDescription }
            busy = false
        }
    }
    private func finish() {
        do { try ShareInbox.remove(record, owner: store.requireUser(), at: ShareInbox.root()); dismiss() }
        catch { self.error = "Gespeichert oder geprüft; der Eingang konnte noch nicht bereinigt werden. \(error.localizedDescription)" }
    }
}
