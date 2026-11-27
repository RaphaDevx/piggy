import SwiftUI
import UIKit

/// A real private original, with a fresh signed URL when opening the zoomable viewer.
struct ReceiptOriginalThumbnail: View {
    @EnvironmentObject private var store: PiggyStore
    let receipt: Receipt
    var height: CGFloat = 170
    @State private var imageURL: URL?
    @State private var photo: UIImage?
    @State private var showingPhoto = false
    @State private var loading = false
    @State private var opening = false
    @State private var error: String?
    private var path: String? {
        [receipt.image_url, receipt.original_image_url].compactMap { $0 }.first { !$0.isEmpty }
    }
    var body: some View {
        VStack(spacing: 6) {
            Button { Task { await open() } } label: {
                ZStack {
                    RoundedRectangle(cornerRadius: 12).fill(Color(uiColor: .tertiarySystemGroupedBackground))
                    if let imageURL {
                        AsyncImage(url: imageURL) { phase in
                            switch phase {
                            case .success(let image): image.resizable().scaledToFit().padding(8)
                            case .failure: placeholder("Bild erneut öffnen", symbol: "arrow.clockwise")
                            default: ProgressView()
                            }
                        }
                    } else if loading { ProgressView() }
                    else { placeholder(path == nil ? "Kein Originalbild" : "Original laden", symbol: "doc.text.image") }
                    if opening { ProgressView().padding(10).background(.regularMaterial, in: Capsule()) }
                }.frame(height: height).clipped()
            }
            .buttonStyle(.plain).disabled(path == nil || opening)
            .accessibilityLabel("Originalbeleg von \(receipt.store_name) vergrössern")
            if path != nil {
                Label("Vergrössern", systemImage: "plus.magnifyingglass").font(.caption).foregroundStyle(.secondary)
                    .accessibilityHidden(true)
            }
            if let error {
                Text(error).font(.caption).foregroundStyle(.secondary)
                Button("Erneut laden") { Task { await load() } }.font(.caption).buttonStyle(.borderless)
            }
        }
        .task(id: path) { await load() }
        .sheet(isPresented: $showingPhoto) { if let photo { PhotoPreview(image: photo) } }
    }
    private func placeholder(_ text: String, symbol: String) -> some View {
        VStack(spacing: 8) { Image(systemName: symbol).font(.title2); Text(text).font(.caption).multilineTextAlignment(.center) }
            .foregroundStyle(.secondary).padding(8)
    }
    private func load() async {
        imageURL = nil; photo = nil; error = nil
        guard let path else { return }
        let uid = store.userID
        loading = true; defer { loading = false }
        do {
            let url = try await store.signedImageURL(path: path)
            guard !Task.isCancelled, store.userID == uid else { return }
            imageURL = url
        } catch { if !Task.isCancelled, store.userID == uid { self.error = "Original konnte nicht geladen werden." } }
    }
    private func open() async {
        guard let path else { return }
        let uid = store.userID
        opening = true; defer { opening = false }
        do {
            let url = try await store.signedImageURL(path: path)
            let (data, response) = try await URLSession.shared.data(from: url)
            guard !Task.isCancelled, store.userID == uid else { return }
            guard (response as? HTTPURLResponse)?.statusCode == 200, let image = UIImage(data: data) else {
                throw PiggyFailure.service("Das Originalbild konnte nicht geöffnet werden.")
            }
            imageURL = url; photo = image; showingPhoto = true; error = nil
        } catch { if store.userID == uid { self.error = "Original konnte nicht geöffnet werden. Bitte erneut laden." } }
    }
}

struct ReceiptOverview: View {
    @EnvironmentObject private var store: PiggyStore
    @Environment(\.dynamicTypeSize) private var typeSize
    let receipt: Receipt
    let edit: () -> Void
    var body: some View {
        let layout = typeSize.isAccessibilitySize ? AnyLayout(VStackLayout(alignment: .leading, spacing: 16)) : AnyLayout(HStackLayout(alignment: .top, spacing: 16))
        layout {
            ReceiptOriginalThumbnail(receipt: receipt, height: 170)
                .frame(width: typeSize.isAccessibilitySize ? nil : 104)
            VStack(alignment: .leading, spacing: 10) {
                Label("Dein Einkauf", systemImage: "bag").font(.caption.weight(.medium)).foregroundStyle(.secondary)
                Text(receipt.store_name).font(.title3.bold()).fixedSize(horizontal: false, vertical: true)
                Text(receiptDate(receipt.receipt_date)).font(.subheadline).foregroundStyle(.secondary)
                Button(action: edit) {
                    HStack(alignment: .firstTextBaseline, spacing: 6) {
                        Text(money(receipt.total_amount, receipt.currency)).font(.title2.bold()).monospacedDigit()
                            .fixedSize(horizontal: false, vertical: true)
                        Image(systemName: "pencil").font(.caption)
                    }
                }.buttonStyle(.plain).accessibilityLabel("Gesamtbetrag bearbeiten: \(money(receipt.total_amount, receipt.currency))")
                if let payment = store.cardDisplay(receipt.payment_card, method: receipt.payment_method) ?? receipt.payment_method, !payment.isEmpty {
                    VStack(alignment: .leading, spacing: 3) {
                        Text("Bezahlt mit").font(.caption).foregroundStyle(.secondary)
                        Text(payment).font(.subheadline).fixedSize(horizontal: false, vertical: true)
                    }
                }
                Text("\(receipt.receipt_items.count) Artikel").font(.caption).foregroundStyle(.secondary)
            }.frame(maxWidth: .infinity, alignment: .leading)
        }.padding(.vertical, 8)
    }
}

struct ReceiptComparisonOverview: View {
    @Environment(\.dynamicTypeSize) private var typeSize
    let target: Receipt
    let source: Receipt
    var body: some View {
        let layout = typeSize.isAccessibilitySize ? AnyLayout(VStackLayout(alignment: .leading, spacing: 16)) : AnyLayout(HStackLayout(alignment: .top, spacing: 12))
        layout {
            card(target, title: "A · Behalten")
            card(source, title: "B · Zusammenführen")
        }
    }
    private func card(_ receipt: Receipt, title: String) -> some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(title).font(.caption.weight(.semibold)).foregroundStyle(.secondary)
            ReceiptOriginalThumbnail(receipt: receipt, height: 180)
            Text(receipt.store_name).font(.headline).fixedSize(horizontal: false, vertical: true)
            Text(money(receipt.total_amount, receipt.currency)).font(.subheadline.bold()).monospacedDigit()
                .fixedSize(horizontal: false, vertical: true)
            Text(receiptDate(receipt.receipt_date)).font(.caption).foregroundStyle(.secondary)
            Text("\(receipt.receipt_items.count) Artikel").font(.caption).foregroundStyle(.secondary)
        }.frame(maxWidth: .infinity, alignment: .leading)
    }
}
