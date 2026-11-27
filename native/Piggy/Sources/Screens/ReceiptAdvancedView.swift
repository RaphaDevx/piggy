import SwiftUI
import UIKit

struct ReceiptAdvancedView: View {
    @EnvironmentObject private var store: PiggyStore
    let receiptID: UUID
    @State private var imageURL: URL?
    @State private var originalImage: UIImage?
    @State private var showingPhoto = false
    @State private var busy = false
    @State private var error: String?
    private var receipt: Receipt? { store.receipts.first { $0.id == receiptID } }
    var body: some View {
        Group {
            if let receipt {
                List {
                    Section("Geschäft und Zahlung") {
                        LabeledContent("Geschäft", value: receipt.store_name)
                        if let category = receipt.store_category, !category.isEmpty { LabeledContent("Ladenkategorie", value: category) }
                        if let date = receipt.receipt_date { LabeledContent("Belegdatum", value: receiptDate(date)) }
                        LabeledContent("Gesamtbetrag", value: money(receipt.total_amount, receipt.currency))
                        if let method = receipt.payment_method, !method.isEmpty { LabeledContent("Zahlungsart", value: method) }
                        if let card = store.cardDisplay(receipt.payment_card, method: receipt.payment_method) { LabeledContent("Karte", value: card) }
                        if let rawCard = receipt.payment_card, !rawCard.isEmpty { LabeledContent("Kartenangabe des Belegs", value: rawCard) }
                    }
                    let details = ReceiptMetadata.details(receipt.extra_fields, raw: receipt.raw_ocr_text)
                    if !details.isEmpty {
                        Section("Angaben auf der Quittung") {
                            ForEach(details) { field in
                                VStack(alignment: .leading, spacing: 4) {
                                    Text(field.title).font(.subheadline).foregroundStyle(.secondary)
                                    Text(field.value).textSelection(.enabled)
                                }
                            }
                        }
                    }
                    if receipt.image_url != nil || receipt.original_image_url != nil {
                        Section("Originalbeleg") {
                            if let imageURL {
                                AsyncImage(url: imageURL) { phase in
                                    switch phase {
                                    case .success(let image): image.resizable().scaledToFit().frame(maxHeight: 320)
                                    case .failure: Text("Das Belegbild konnte nicht geladen werden.")
                                    default: ProgressView("Belegbild wird geladen …")
                                    }
                                }
                            }
                            Button("Originalbild öffnen") { Task { await openPhoto() } }.disabled(busy)
                            Button("Bild neu laden") { Task { await loadImage() } }.disabled(busy)
                        }
                    }
                    if let raw = receipt.raw_ocr_text, !raw.isEmpty {
                        Section {
                            Text(raw).textSelection(.enabled)
                            ShareLink(item: raw) { Label("Erkannten Text teilen", systemImage: "square.and.arrow.up") }
                        } header: { Text("Vollständiger erkannter Originaltext") } footer: {
                            Text("Hier bleibt der gesamte erkannte Text erhalten, auch Angaben ohne eigenes Feld. Vergleiche unklare Stellen mit dem Originalbild.")
                        }
                    }
                    if let markdown = receipt.markdown_content, !markdown.isEmpty {
                        Section("Weiterer Belegtext") { Text(markdown).textSelection(.enabled) }
                    }
                    if let error { ScreenError(message: error, retry: { Task { await loadImage() } }) }
                }
                .task(id: receipt.image_url ?? receipt.original_image_url) { await loadImage() }
            } else { ContentUnavailableView("Beleg nicht verfügbar", systemImage: "receipt") }
        }
        .navigationTitle("Weitere Details")
        .navigationBarTitleDisplayMode(.inline)
        .sheet(isPresented: $showingPhoto) { if let originalImage { PhotoPreview(image: originalImage) } }
    }
    private func loadImage() async {
        guard let path = receipt?.image_url ?? receipt?.original_image_url else { return }
        do { imageURL = try await store.signedImageURL(path: path); error = nil } catch { self.error = error.localizedDescription }
    }
    private func openPhoto() async {
        busy = true; defer { busy = false }
        do {
            guard let path = receipt?.image_url ?? receipt?.original_image_url else { return }
            // Obtain a fresh URL even when this screen has stayed open beyond the old TTL.
            let url = try await store.signedImageURL(path: path)
            imageURL = url
            let (data, response) = try await URLSession.shared.data(from: url)
            guard (response as? HTTPURLResponse)?.statusCode == 200, let image = UIImage(data: data) else {
                throw PiggyFailure.service("Das Belegbild konnte nicht geöffnet werden. Lade es bitte erneut.")
            }
            originalImage = image; showingPhoto = true; error = nil
        } catch { self.error = error.localizedDescription }
    }
}
