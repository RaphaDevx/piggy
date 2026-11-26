import SwiftUI

struct DuplicatesView: View {
    @EnvironmentObject private var store: PiggyStore
    @State private var loading = false
    @State private var error: String?
    private var pairs: [DuplicatePair] { ReceiptRules.duplicatePairs(store.receipts, dismissed: store.dismissedDuplicates) }
    var body: some View {
        List {
            Section {
                Text("Datum, Betrag, Währung und normalisierter Ladenname müssen übereinstimmen. Abweichende Angaben kannst du zuerst am Beleg korrigieren. Piggy führt nie automatisch zusammen.")
                    .font(.footnote).foregroundStyle(.secondary)
            }
            if loading { ProgressView("Belege werden geprüft …") }
            if let error { ScreenError(message: error, retry: { Task { await load() } }) }
            if !loading && error == nil && pairs.isEmpty {
                ContentUnavailableView("Keine Duplikate gefunden", systemImage: "checkmark.circle", description: Text("Bereits als getrennt bestätigte Einkäufe werden nicht erneut vorgeschlagen."))
            }
            ForEach(pairs) { pair in
                NavigationLink { DuplicatePairReview(pair: pair) } label: {
                    VStack(alignment: .leading, spacing: 8) {
                        ReceiptRow(receipt: pair.target)
                        Text("Zweiter Beleg: \(pair.source.store_name) · \(money(pair.source.total_amount, pair.source.currency))")
                            .font(.subheadline).foregroundStyle(.secondary)
                    }
                }
            }
        }
        .navigationTitle("Duplikate prüfen")
        .task { await load() }
        .refreshable { await load() }
    }
    private func load() async {
        loading = true
        defer { loading = false }
        do { try await store.loadDuplicateReview(); error = nil } catch { self.error = error.localizedDescription }
    }
}

struct DuplicatePairReview: View {
    let pair: DuplicatePair
    @EnvironmentObject private var store: PiggyStore
    @Environment(\.dismiss) private var dismiss
    @State private var fields: [String: String] = [:]
    @State private var extras: [String: String] = [:]
    @State private var image = "target"
    @State private var items = "target"
    @State private var busy = false
    @State private var error: String?
    @State private var confirming = false
    private var choices: [String: JSONValue] {
        ["fields": .object(fields.mapValues(JSONValue.string)), "extras": .object(extras.mapValues(JSONValue.string)), "image": .string(image), "items": .string(items)]
    }
    var body: some View {
        Form {
            Section("Beide Originale vergleichen") {
                NavigationLink("Beleg A ansehen") { ReceiptDetailView(receiptID: pair.target.id) }
                NavigationLink("Beleg B ansehen") { ReceiptDetailView(receiptID: pair.source.id) }
                Text("A bleibt als Beleg erhalten. Beim Zusammenführen wird B aus der aktiven Liste entfernt. Verknüpfungen werden erhalten oder der Vorgang wird bei Konflikten abgelehnt.")
                    .font(.footnote).foregroundStyle(.secondary)
            }
            Section("Angaben auswählen") {
                ForEach(DuplicateFields.all, id: \.key) { field in
                    let a = field.value(pair.target)
                    let b = field.value(pair.source)
                    if a != b {
                        Picker(field.label, selection: Binding(get: { fields[field.key] ?? (a.isEmpty ? "source" : "target") }, set: { fields[field.key] = $0 })) {
                            Text("A: \(a.isEmpty ? "Leer" : a)").tag("target")
                            Text("B: \(b.isEmpty ? "Leer" : b)").tag("source")
                        }
                    }
                }
            }
            Section("Zusatzangaben auswählen") {
                ForEach(ReceiptExtras.labels, id: \.0) { key, title in
                    let a = (pair.target.extra_fields?[key] ?? nil) ?? ""
                    let b = (pair.source.extra_fields?[key] ?? nil) ?? ""
                    if a != b {
                        Picker(title, selection: Binding(get: { extras[key] ?? (a.isEmpty ? "source" : "target") }, set: { extras[key] = $0 })) {
                            Text("A: \(a.isEmpty ? "Leer" : a)").tag("target")
                            Text("B: \(b.isEmpty ? "Leer" : b)").tag("source")
                        }
                    }
                }
            }
            Section("Bild und Artikel") {
                Picker("Bild behalten", selection: $image) {
                    Text("A\(pair.target.image_url == nil ? " (kein Bild)" : "")").tag("target")
                    Text("B\(pair.source.image_url == nil ? " (kein Bild)" : "")").tag("source")
                }
                Picker("Artikelliste behalten", selection: $items) {
                    Text("A: \(pair.target.receipt_items.count) Artikel").tag("target")
                    Text("B: \(pair.source.receipt_items.count) Artikel").tag("source")
                }
                Text("Es wird genau eine normale Artikelliste übernommen. Geprüfte Artikel, Aufteilungen und Bankzuordnungen bleiben geschützt; Konflikte müssen zuerst geklärt werden.")
                    .font(.footnote).foregroundStyle(.secondary)
            }
            Section("Vorschau") {
                ForEach(DuplicateFields.all, id: \.key) { field in
                    let side = fields[field.key] ?? (field.value(pair.target).isEmpty ? "source" : "target")
                    let value = field.value(side == "source" ? pair.source : pair.target)
                    if !value.isEmpty { LabeledContent(field.label, value: value) }
                }
                ForEach(ReceiptExtras.labels, id: \.0) { key, title in
                    let a = (pair.target.extra_fields?[key] ?? nil) ?? ""
                    let b = (pair.source.extra_fields?[key] ?? nil) ?? ""
                    let side = extras[key] ?? (a.isEmpty ? "source" : "target")
                    let value = side == "source" ? b : a
                    if !value.isEmpty { LabeledContent(title, value: value) }
                }
                Text("Bild \(image == "source" ? "B" : "A"), Artikel \(items == "source" ? "B" : "A")")
            }
            if let error { ScreenError(message: error) }
            Section {
                BusyButton(title: "Zusammenführen …", busy: busy) { confirming = true }
                Button("Zwei verschiedene Einkäufe – getrennt halten") { Task { await separate() } }
            }
        }
        .navigationTitle("Duplikat vergleichen")
        .disabled(busy)
        .onAppear {
            if pair.target.image_url == nil && pair.source.image_url != nil { image = "source" }
            if pair.target.receipt_items.isEmpty { items = "source" }
        }
        .confirmationDialog("Diese Auswahl zusammenführen?", isPresented: $confirming, titleVisibility: .visible) {
            Button("Auswahl bestätigen und zusammenführen") { Task { await merge() } }
            Button("Abbrechen", role: .cancel) {}
        }
    }
    private func merge() async {
        busy = true; defer { busy = false }
        do { try await store.mergeDuplicates(pair, choices: choices); dismiss() } catch { self.error = error.localizedDescription }
    }
    private func separate() async {
        busy = true; defer { busy = false }
        do { try await store.keepSeparate(pair); dismiss() } catch { self.error = error.localizedDescription }
    }
}

enum DuplicateFields {
    struct Field { let key: String; let label: String; let value: (Receipt) -> String }
    static let all: [Field] = [
        Field(key: "store_name", label: "Geschäft", value: { $0.store_name }),
        Field(key: "receipt_date", label: "Datum", value: { $0.receipt_date ?? "" }),
        Field(key: "total_amount", label: "Betrag", value: { $0.total_amount.map { NSDecimalNumber(decimal: $0).stringValue } ?? "" }),
        Field(key: "currency", label: "Währung", value: { $0.currency }),
        Field(key: "store_category", label: "Ladenkategorie", value: { $0.store_category ?? "" }),
        Field(key: "payment_method", label: "Zahlungsart", value: { $0.payment_method ?? "" }),
        Field(key: "payment_card", label: "Karte", value: { $0.payment_card ?? "" }),
        Field(key: "notes", label: "Notiz", value: { $0.notes ?? "" }),
        Field(key: "raw_ocr_text", label: "Erkannter Text", value: { $0.raw_ocr_text ?? "" }),
        Field(key: "markdown_content", label: "Belegtext", value: { $0.markdown_content ?? "" })
    ]
}
