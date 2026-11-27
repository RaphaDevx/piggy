import SwiftUI
import PhotosUI
import Vision
import VisionKit
import CoreImage.CIFilterBuiltins

struct CouponsView: View {
    @EnvironmentObject private var library: PersonalLibrary
    @EnvironmentObject private var store: PiggyStore
    @State private var creating = false
    var body: some View {
        List {
            Section {
                Text("Deine Coupons, getrennt von deinen Ausgaben. Auf diesem Gerät für dein Konto gespeichert; kein Cloud-Abgleich.").font(.footnote).foregroundStyle(.secondary)
                Button { creating = true } label: { Label("Coupon scannen oder erfassen", systemImage: "plus") }
            }
            if let error = library.error { ScreenError(message: error) }
            if library.owner == store.userID {
                ForEach(library.coupons) { coupon in
                    NavigationLink { CouponDetailView(coupon: coupon) } label: {
                        HStack {
                            Image(systemName: "ticket").foregroundStyle(Theme.accent)
                            VStack(alignment: .leading) {
                                Text(coupon.title).font(.headline)
                                Text(coupon.merchant).font(.subheadline).foregroundStyle(.secondary)
                                Text(coupon.used ? "Als verwendet markiert" : coupon.expired() ? "Abgelaufen" : coupon.expiry.map { "Gültig bis \(receiptDate($0))" } ?? "Kein Ablaufdatum hinterlegt").font(.caption).foregroundStyle(.secondary)
                            }
                        }
                    }
                }
                if library.coupons.isEmpty { ContentUnavailableView("Noch keine Coupons", systemImage: "ticket", description: Text("Fotografiere einen Coupon oder wähle ein Bild. Prüfe die Angaben vor dem Speichern.")) }
            }
        }.navigationTitle("Coupons")
            .sheet(isPresented: $creating) { NavigationStack { CouponEditor() } }
    }
}
struct CouponEditor: View {
    @EnvironmentObject private var library: PersonalLibrary
    @EnvironmentObject private var store: PiggyStore
    @Environment(\.dismiss) private var dismiss
    var seedImage: UIImage? = nil
    var sourcePDF: Data? = nil
    var onSaved: (() -> Void)? = nil
    @State private var title = ""
    @State private var merchant = ""
    @State private var code = ""
    @State private var symbology = ""
    @State private var note = ""
    @State private var hasExpiry = false
    @State private var expiry = Date()
    @State private var photo: PhotosPickerItem?
    @State private var image: UIImage?
    @State private var camera = false
    @State private var error: String?
    @State private var busy = false
    @State private var detected: [(String, String)] = []
    var body: some View {
        Form {
            Section("Originalcoupon") {
                if let image { Image(uiImage: image).resizable().scaledToFit().frame(maxHeight: 240) }
                if VNDocumentCameraViewController.isSupported { Button("Coupon fotografieren") { camera = true } }
                PhotosPicker("Couponbild auswählen", selection: $photo, matching: .images)
                if busy { ProgressView("Code wird lokal gesucht …") }
                ForEach(Array(detected.enumerated()), id: \.offset) { _, value in
                    Button("Code übernehmen: \(value.0)") { code = value.0; symbology = value.1 }
                }
            }
            Section("Angaben prüfen") {
                TextField("Titel, z. B. 20 % auf Getränke", text: $title)
                TextField("Anbieter", text: $merchant)
                TextField("Couponcode", text: $code).textInputAutocapitalization(.never).autocorrectionDisabled()
                    .onChange(of: code) { _, value in if !detected.contains(where: { $0.0 == value }) { symbology = "" } }
                Toggle("Ablaufdatum bekannt", isOn: $hasExpiry)
                if hasExpiry { DatePicker("Gültig bis", selection: $expiry, displayedComponents: .date) }
                TextField("Bedingungen / Notizen", text: $note, axis: .vertical)
                Text("Codes werden lokal aus dem Bild gelesen. Titel, Bedingungen und Datum bitte selbst prüfen. Ein Barcode allein enthält diese Angaben oft nicht.").font(.footnote).foregroundStyle(.secondary)
            }
            Section {
                Button("Coupon speichern") {
                    do {
                        let uid = try store.requireUser()
                        let formatter = DateFormatter(); formatter.locale = Locale(identifier: "en_US_POSIX"); formatter.dateFormat = "yyyy-MM-dd"
                        let coupon = SavedCoupon(title: title.trimmingCharacters(in: .whitespacesAndNewlines), merchant: merchant, code: code, symbology: symbology, expiry: hasExpiry ? formatter.string(from: expiry) : nil, note: note, image: image.flatMap(PersonalLibrary.couponImage), document: sourcePDF)
                        try library.saveCoupon(coupon, owner: uid); onSaved?(); dismiss()
                    } catch { self.error = error.localizedDescription }
                }.disabled(busy || title.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty || (image == nil && code.isEmpty))
            }
            if let error { ScreenError(message: error) }
        }.navigationTitle("Coupon erfassen")
            .toolbar { ToolbarItem(placement: .cancellationAction) { Button("Abbrechen") { dismiss() } } }
            .task { if let seedImage { image = seedImage; await detect(seedImage) } }
            .sheet(isPresented: $camera) { DocumentCamera { result in
                camera = false
                switch result {
                case .success(let pages): if let first = pages.first { image = first; Task { await detect(first) } }
                case .failure(let failure): if !(failure is CancellationError) { error = failure.localizedDescription }
                }
            } }
            .onChange(of: photo) { _, item in Task {
                do { if let bytes = try await item?.loadTransferable(type: Data.self), let value = UIImage(data: bytes) { image = value; await detect(value) } }
                catch { self.error = error.localizedDescription }
            } }
    }
    private func detect(_ image: UIImage) async {
        busy = true; error = nil; detected = []; defer { busy = false }
        guard let cgImage = image.cgImage else { return }
        do {
            let values = try await Task.detached(priority: .userInitiated) {
                let request = VNDetectBarcodesRequest()
                try VNImageRequestHandler(cgImage: cgImage).perform([request])
                return (request.results ?? []).compactMap { result -> [String]? in
                    guard let text = result.payloadStringValue else { return nil }
                    return [text, result.symbology.rawValue]
                }
            }.value
            detected = values.map { ($0[0], $0[1]) }
            if detected.count == 1, let first = detected.first { code = first.0; symbology = first.1 }
        } catch { self.error = "Kein Code lesbar. Du kannst ihn von Hand erfassen und das Original speichern." }
    }
}
private struct CouponDetailView: View {
    @EnvironmentObject private var library: PersonalLibrary
    @EnvironmentObject private var store: PiggyStore
    let coupon: SavedCoupon
    @State private var showOriginal = false
    @State private var showPDF = false
    @State private var error: String?
    private var current: SavedCoupon { library.coupons.first { $0.id == coupon.id } ?? coupon }
    var body: some View {
        List {
            Section {
                Text(current.title).font(.title2.bold())
                Text(current.merchant).foregroundStyle(.secondary)
                if let image = barcode { Image(uiImage: image).interpolation(.none).resizable().scaledToFit().frame(height: 190).padding().background(.white).accessibilityLabel("Couponcode") }
                if !current.code.isEmpty { Text(current.code).font(.title3.monospaced()).textSelection(.enabled); Button("Code kopieren") { UIPasteboard.general.string = current.code } }
                if let data = current.image, let image = UIImage(data: data) {
                    Button { showOriginal = true } label: { Image(uiImage: image).resizable().scaledToFit().frame(maxHeight: 280) }.buttonStyle(.plain).accessibilityLabel("Originalcoupon vergrössern")
                }
            }
            if current.document != nil { Section { Button("Original-PDF öffnen") { showPDF = true } } }
            Section("Bedingungen") {
                Text(current.expiry.map { "Gültig bis \(receiptDate($0))" } ?? "Ablaufdatum nicht hinterlegt")
                if current.expired() { Label("Abgelaufen", systemImage: "clock.badge.exclamationmark") }
                if !current.note.isEmpty { Text(current.note) }
                Text("Ob der Coupon akzeptiert wird, entscheidet der Anbieter. Die Markierung unten ist nur deine persönliche Merkhilfe.").font(.footnote).foregroundStyle(.secondary)
                Button(current.used ? "Wieder als unbenutzt markieren" : "Als verwendet markieren") {
                    do { var value = current; value.used.toggle(); try library.saveCoupon(value, owner: store.requireUser()) } catch { self.error = error.localizedDescription }
                }
            }
            if let error { ScreenError(message: error) }
        }.navigationTitle("Coupon")
            .sheet(isPresented: $showPDF) { if let data = current.document { NavigationStack { PDFOriginalView(data: data).navigationTitle("Originalcoupon").toolbar { ToolbarItem(placement: .cancellationAction) { Button("Schliessen") { showPDF = false } } } } }
            .sheet(isPresented: $showOriginal) { if let data = current.image, let image = UIImage(data: data) { PhotoPreview(image: image) } }
    }
    private var barcode: UIImage? {
        guard current.symbology == VNBarcodeSymbology.qr.rawValue, !current.code.isEmpty else { return nil }
        let filter = CIFilter.qrCodeGenerator(); filter.message = Data(current.code.utf8)
        guard let output = filter.outputImage?.transformed(by: CGAffineTransform(scaleX: 8, y: 8)), let cg = CIContext().createCGImage(output, from: output.extent) else { return nil }
        return UIImage(cgImage: cg)
    }
}
