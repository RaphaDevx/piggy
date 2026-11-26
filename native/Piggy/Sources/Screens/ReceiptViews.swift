import SwiftUI

struct ReceiptListView: View {
    @EnvironmentObject private var store: PiggyStore
    let openScanner: () -> Void
    @State private var search = ""
    @State private var selectedCurrency = "all"
    @State private var selectedCategory = "all"
    @State private var selectedMethod = "all"
    @State private var selectedPeriod: FinancePeriod?
    @State private var filterDate = Date()
    private var filtered: [Receipt] {
        store.receipts.filter { receipt in
            (selectedCurrency == "all" || receipt.currency == selectedCurrency) &&
            (selectedCategory == "all" || receipt.receipt_items.contains { $0.category == selectedCategory }) &&
            (selectedMethod == "all" || receipt.payment_method == selectedMethod) &&
            (selectedPeriod.map { period in
                let range = period.range(containing: filterDate)
                return receipt.displayDate >= range.start && receipt.displayDate < range.endExclusive
            } ?? true) &&
            (search.isEmpty || receipt.searchText.localizedCaseInsensitiveContains(search))
        }
    }
    var body: some View {
        List {
            if store.busy && store.receipts.isEmpty { ProgressView("Belege werden geladen …") }
            Section { NavigationLink { DuplicatesView() } label: { Label("Duplikate prüfen", systemImage: "doc.on.doc") } }
            if !store.receipts.isEmpty {
                DisclosureGroup("Filter") {
                    Picker("Zeitraum", selection: $selectedPeriod) {
                        Text("Alle Zeiträume").tag(FinancePeriod?.none)
                        ForEach(FinancePeriod.allCases) { Text($0.rawValue).tag(Optional($0)) }
                    }
                    if selectedPeriod != nil { DatePicker("Datum im Zeitraum", selection: $filterDate, displayedComponents: .date) }
                    Picker("Kategorie", selection: $selectedCategory) {
                        Text("Alle Kategorien").tag("all")
                        ForEach(Array(Set(store.receipts.flatMap { $0.receipt_items.map(\.category) })).sorted(), id: \.self) { Text($0).tag($0) }
                    }
                    Picker("Zahlungsart", selection: $selectedMethod) {
                        Text("Alle Zahlungsarten").tag("all")
                        ForEach(Array(Set(store.receipts.compactMap(\.payment_method))).sorted(), id: \.self) { Text($0).tag($0) }
                    }
                    Button("Filter zurücksetzen") { selectedCategory = "all"; selectedMethod = "all"; selectedPeriod = nil; selectedCurrency = "all" }
                }
                Picker("Währung", selection: $selectedCurrency) {
                    Text("Alle Währungen").tag("all")
                    ForEach(Array(Set(store.receipts.map(\.currency))).sorted(), id: \.self) { Text($0).tag($0) }
                }
            }
            if filtered.isEmpty && !store.busy {
                EmptyPiggy(title: store.receipts.isEmpty ? "Noch keine Belege" : "Nichts gefunden",
                           message: store.receipts.isEmpty ? "Dein nächster Einkauf ist ein guter Anfang." : "Prüfe deine Filter oder versuche einen anderen Suchbegriff.")
                    .listRowBackground(Color.clear)
                if store.receipts.isEmpty { Button("Erste Quittung scannen", action: openScanner) }
            }
            ForEach(filtered) { receipt in
                NavigationLink { ReceiptDetailView(receiptID: receipt.id) } label: { ReceiptRow(receipt: receipt) }
            }
        }
        .searchable(text: $search, prompt: "Geschäft, Artikel oder Belegdetails")
        .navigationTitle("Deine Belege")
        .toolbar {
            ToolbarItem(placement: .topBarTrailing) {
                Button(action: openScanner) { Image(systemName: "camera") }.accessibilityLabel("Beleg scannen")
            }
        }
        .refreshable { await store.reload() }
    }
}

struct ReceiptDetailView: View {
    @EnvironmentObject private var store: PiggyStore
    @Environment(\.dismiss) private var dismiss
    let receiptID: UUID
    @State private var editing = false
    @State private var deleteConfirmation = false
    @State private var busy = false
    @State private var message: String?
    @State private var imageURL: URL?
    @State private var originalImage: UIImage?
    @State private var showingPhoto = false
    private var receipt: Receipt? { store.receipts.first { $0.id == receiptID } }
    var body: some View {
        Group {
            if let receipt {
                List {
                    Section {
                        VStack(alignment: .leading, spacing: 8) {
                            Text(receipt.store_name).font(.title.bold())
                            Button { editing = true } label: {
                                HStack {
                                    Text(money(receipt.total_amount, receipt.currency)).font(.largeTitle.bold()).monospacedDigit()
                                    Image(systemName: "pencil").font(.body)
                                }
                            }.buttonStyle(.plain).disabled(busy)
                                .accessibilityLabel("Gesamtbetrag bearbeiten: \(money(receipt.total_amount, receipt.currency))")
                            Text(receiptDate(receipt.receipt_date)).foregroundStyle(Theme.secondary)
                        }.padding(.vertical, 8)
                        if let payment = receipt.payment_method, !payment.isEmpty { LabeledContent("Zahlungsart", value: payment) }
                        if let card = store.cardDisplay(receipt.payment_card, method: receipt.payment_method) { LabeledContent("Karte", value: card) }
                    }
                    if receipt.image_url != nil {
                        Section("Originalbeleg") {
                            if let imageURL {
                                AsyncImage(url: imageURL) { phase in
                                    switch phase {
                                    case .success(let image): image.resizable().scaledToFit().frame(maxHeight: 220)
                                    case .failure: Text("Das Belegbild konnte nicht geladen werden.")
                                    default: ProgressView("Belegbild wird geladen …")
                                    }
                                }
                                Button("Bild vergrössern") { Task { await openPhoto() } }.disabled(busy)
                            } else {
                                Button("Belegbild laden") { Task { await loadImage() } }
                            }
                        }
                    }
                    Section("Artikel") {
                        if receipt.receipt_items.isEmpty { Text("Keine einzelnen Artikel erfasst.").foregroundStyle(Theme.secondary) }
                        ForEach(receipt.receipt_items) { item in
                            VStack(alignment: .leading, spacing: 6) {
                                HStack(alignment: .firstTextBaseline) {
                                    Text(item.name)
                                    Spacer()
                                    Button { editing = true } label: {
                                        Label(money(item.total_price, receipt.currency), systemImage: "pencil").monospacedDigit()
                                    }.buttonStyle(.plain).disabled(busy)
                                        .accessibilityLabel("Betrag für \(item.name) bearbeiten")
                                }
                                HStack {
                                    if let category = item.tags.first { Text(category) }
                                    if let quantity = item.quantity { Text("· \(NSDecimalNumber(decimal: quantity).stringValue) Stück") }
                                }.font(.footnote).foregroundStyle(Theme.secondary)
                            }.padding(.vertical, 4)
                        }
                    }
                    let parking = ParkingDetails.read(extras: receipt.extra_fields, raw: receipt.raw_ocr_text, receiptDate: receipt.receipt_date)
                    if parking.hasDetails { ParkingSummary(details: parking, total: receipt.total_amount, currency: receipt.currency) }
                    if let fields = receipt.extra_fields, !fields.isEmpty {
                        Section("Zusatzangaben") {
                            ForEach(ReceiptExtras.labels, id: \.0) { key, title in
                                if let wrapped = fields[key], let value = wrapped, !value.isEmpty { LabeledContent(title, value: value) }
                            }
                        }
                    }
                    if let text = receipt.raw_ocr_text, !text.isEmpty {
                        Section { DisclosureGroup("Erkannten Originaltext anzeigen") { Text(text).textSelection(.enabled) } }
                    }
                    if let text = receipt.markdown_content, !text.isEmpty {
                        Section { DisclosureGroup("Belegtext anzeigen") { Text(text).textSelection(.enabled) } }
                    }
                    if let notes = receipt.notes, !notes.isEmpty { Section("Notiz") { Text(notes) } }
                    if BankFeature.enabled { ReceiptBankSection(receiptID: receipt.id) }
                    Section("Gemeinsam") {
                        NavigationLink { SplitReceiptView(receipt: receipt) } label: { Label("Ausgabe aufteilen", systemImage: "person.2") }
                        Picker("Projekt", selection: Binding(get: { receipt.project_id }, set: { id in Task { await assignProject(id, receipt: receipt) } })) {
                            Text("Kein Projekt").tag(UUID?.none)
                            ForEach(store.projects) { Text("\($0.emoji) \($0.name)").tag(Optional($0.id)) }
                        }.disabled(busy)
                    }
                    Section {
                        ShareLink(item: receipt.shareText) { Label("Belegübersicht teilen", systemImage: "square.and.arrow.up") }
                        Button("Beleg löschen", role: .destructive) { deleteConfirmation = true }.disabled(busy)
                    }
                    if let message { Section { ScreenError(message: message) } }
                }
                .navigationTitle("Beleg")
                .navigationBarTitleDisplayMode(.inline)
                .toolbar { ToolbarItem(placement: .topBarTrailing) { Button("Bearbeiten") { editing = true }.disabled(busy) } }
                .sheet(isPresented: $editing) { ReceiptEditView(receipt: receipt) }
                .sheet(isPresented: $showingPhoto) { if let originalImage { PhotoPreview(image: originalImage) } }
                .task(id: receipt.image_url) { await loadImage() }
                .task { do { try await store.loadCards() } catch { message = error.localizedDescription } }
                .confirmationDialog("Beleg endgültig löschen?", isPresented: $deleteConfirmation, titleVisibility: .visible) {
                    Button("Beleg löschen", role: .destructive) { Task { await delete(receipt) } }
                    Button("Abbrechen", role: .cancel) {}
                } message: { Text("Der Beleg wird entfernt. Verknüpfungen können das Löschen verhindern; Piggy zeigt dir dann den Grund.") }
            } else {
                ContentUnavailableView("Beleg nicht verfügbar", systemImage: "receipt", description: Text("Er wurde gelöscht oder ist für dieses Konto nicht zugänglich."))
            }
        }
    }
    @MainActor private func loadImage() async {
        guard let path = receipt?.image_url else { return }
        do { imageURL = try await store.signedImageURL(path: path) }
        catch { message = error.localizedDescription }
    }
    @MainActor private func openPhoto() async {
        guard let imageURL else { return }
        busy = true
        defer { busy = false }
        do {
            let (data, response) = try await URLSession.shared.data(from: imageURL)
            guard (response as? HTTPURLResponse)?.statusCode == 200, let image = UIImage(data: data) else {
                message = "Das Belegbild konnte nicht geöffnet werden. Lade es bitte erneut."
                return
            }
            originalImage = image
            showingPhoto = true
        } catch { message = error.localizedDescription }
    }
    @MainActor private func assignProject(_ id: UUID?, receipt: Receipt) async {
        busy = true
        do { try await store.assign(receiptID: receipt.id, projectID: id); message = nil }
        catch { message = error.localizedDescription }
        busy = false
    }
    @MainActor private func delete(_ receipt: Receipt) async {
        busy = true
        do { try await store.delete(receipt: receipt); dismiss() }
        catch { message = error.localizedDescription }
        busy = false
    }
}

struct ReceiptEditView: View {
    @EnvironmentObject private var store: PiggyStore
    @Environment(\.dismiss) private var dismiss
    @State private var receipt: Receipt
    @State private var busy = false
    @State private var message: String?
    init(receipt: Receipt) { _receipt = State(initialValue: receipt) }
    var body: some View {
        NavigationStack {
            Form {
                if let message { Section { ScreenError(message: message) } }
                Section("Beleg") {
                    TextField("Geschäft", text: $receipt.store_name)
                    TextField("Datum (JJJJ-MM-TT)", text: Binding(get: { receipt.receipt_date ?? "" }, set: { receipt.receipt_date = $0 }))
                    DecimalField(title: "Gesamtbetrag", value: Binding(get: { receipt.total_amount ?? 0 }, set: { receipt.total_amount = $0 }))
                    TextField("Ladenkategorie", text: Binding(get: { receipt.store_category ?? "" }, set: { receipt.store_category = $0 }))
                    TextField("Währung", text: $receipt.currency).textInputAutocapitalization(.characters).autocorrectionDisabled()
                    ReceiptPaymentFields(method: Binding(get: { receipt.payment_method ?? "" }, set: { receipt.payment_method = $0 }), card: $receipt.payment_card)
                }
                Section("Artikel") {
                    ForEach($receipt.receipt_items) { $item in
                        VStack(alignment: .leading, spacing: 8) {
                            TextField("Artikelname", text: $item.name)
                            DecimalField(title: "Menge", value: Binding(get: { item.quantity ?? 1 }, set: { item.quantity = $0 }))
                            TextField("Einheit (Stk, kg, L …)", text: Binding(get: { item.unit ?? "" }, set: { item.unit = $0 }))
                            DecimalField(title: "Einzelpreis", value: Binding(get: { item.unit_price ?? 0 }, set: { item.unit_price = $0 }))
                            DecimalField(title: "Artikelbetrag", value: Binding(get: { item.total_price ?? 0 }, set: { item.total_price = $0 }))
                            CategoryPicker(category: Binding(get: { item.tags.first ?? "Diverses" }, set: { item.tags = [$0]; item.subcategory = nil }))
                        }.padding(.vertical, 4)
                            .disabled(item.is_adjustment == true)
                            .deleteDisabled(item.is_adjustment == true)
                    }
                    .onDelete { offsets in
                        for index in offsets.sorted(by: >) where receipt.receipt_items[index].is_adjustment != true {
                            receipt.receipt_items.remove(at: index)
                        }
                    }
                    Button { receipt.receipt_items.append(ReceiptItem(name: "Neuer Artikel", quantity: 1, unit: "Stk", unit_price: 0, total_price: 0, tags: ["Diverses"])) } label: {
                        Label("Artikel hinzufügen", systemImage: "plus")
                    }
                }
                ReceiptExtrasEditor(extras: $receipt.extra_fields, rawText: receipt.raw_ocr_text, receiptDate: receipt.receipt_date)
                Section("Notiz") {
                    TextField("Optional", text: Binding(get: { receipt.notes ?? "" }, set: { receipt.notes = $0 }), axis: .vertical).lineLimit(3...6)
                }
            }
            .navigationTitle("Beleg bearbeiten")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .cancellationAction) { Button("Abbrechen") { dismiss() }.disabled(busy) }
                ToolbarItem(placement: .confirmationAction) {
                    Button { Task { await save() } } label: { if busy { ProgressView() } else { Text("Speichern").bold() } }
                        .disabled(busy || receipt.store_name.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty || receipt.currency.count != 3 ||
                                  receipt.total_amount?.isNaN == true || receipt.receipt_items.contains {
                                      $0.total_price?.isNaN == true || $0.quantity?.isNaN == true || $0.unit_price?.isNaN == true
                                  })
                }
            }
            .disabled(busy)
            .interactiveDismissDisabled(busy)
        }
    }
    @MainActor private func save() async {
        busy = true
        do { try await store.update(receipt: receipt); dismiss() }
        catch { message = error.localizedDescription }
        busy = false
    }
}

struct CategoryPicker: View {
    @EnvironmentObject private var store: PiggyStore
    @Binding var category: String
    @State private var adding = false
    @State private var newCategory = ""
    private var categories: [String] {
        Array(Set(store.receipts.flatMap { $0.receipt_items.flatMap(\.tags) }).union(PiggyTaxonomy.categories).union([category, "Diverses"])).sorted()
    }
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Picker("Kategorie", selection: $category) { ForEach(categories, id: \.self) { Text($0).tag($0) } }
            Button("Eigene Kategorie …") { newCategory = ""; adding = true }
                .alert("Eigene Kategorie", isPresented: $adding) {
                    TextField("Name", text: $newCategory)
                    Button("Übernehmen") {
                        let trimmed = newCategory.trimmingCharacters(in: .whitespacesAndNewlines)
                        if !trimmed.isEmpty { category = String(trimmed.prefix(60)) }
                    }
                    Button("Abbrechen", role: .cancel) {}
                }
        }
    }
}
