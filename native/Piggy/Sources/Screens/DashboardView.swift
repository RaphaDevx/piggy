import SwiftUI
import Charts

struct DashboardView: View {
    @EnvironmentObject private var store: PiggyStore
    let openScanner: () -> Void
    @State private var period = Date()
    @State private var currency = "CHF"
    @State private var finance: NativeFinanceSummary?
    @State private var financeError: String?
    @State private var loadingFinance = false
    private var currencies: [String] { Array(Set(store.receipts.map(\.currency)).union(["CHF"])).sorted() }
    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 24) {
                BrandHeader()
                HStack(alignment: .center, spacing: 16) {
                    VStack(alignment: .leading, spacing: 8) {
                        Text("Deine Belege.\nDein Überblick.")
                            .font(.largeTitle.bold())
                            .fixedSize(horizontal: false, vertical: true)
                        Text("Ein Einkauf, alle Details an einem Ort.")
                            .foregroundStyle(Theme.secondary)
                    }
                    Spacer(minLength: 0)
                    MascotView(pose: "idle-happy", size: 110).accessibilityHidden(true)
                }
                BusyButton(title: "Quittung scannen", busy: false, action: openScanner)
                if store.receipts.isEmpty && !store.busy {
                    EmptyPiggy(title: "Dein erster Beleg wartet", message: "Scanne eine Quittung oder wähle ein Foto. Du kannst jedes Ergebnis vor dem Speichern prüfen.")
                } else {
                    financeCard
                    if !store.balances.isEmpty {
                        PiggyCard {
                            Label("Offene Beträge", systemImage: "person.2").font(.headline)
                            ForEach(store.balances.prefix(4)) { balance in
                                HStack(alignment: .firstTextBaseline) {
                                    VStack(alignment: .leading) {
                                        Text(balance.otherName)
                                        Text(balance.owedToMe ? "Du bekommst" : "Du schuldest").font(.caption).foregroundStyle(Theme.secondary)
                                    }
                                    Spacer()
                                    Text(money(balance.amount, balance.currency)).monospacedDigit()
                                }
                            }
                            NavigationLink("Alle gemeinsamen Ausgaben") { SocialHomeView() }
                        }
                    }
                    HStack {
                        Text("Zuletzt erfasst").font(.title2.bold())
                        Spacer()
                        NavigationLink("Alle") { ReceiptListView(openScanner: openScanner) }
                    }
                    VStack(spacing: 0) {
                        ForEach(store.receipts.prefix(5)) { receipt in
                            NavigationLink { ReceiptDetailView(receiptID: receipt.id) } label: { ReceiptRow(receipt: receipt) }
                                .buttonStyle(.plain)
                            if receipt.id != store.receipts.prefix(5).last?.id { Divider() }
                        }
                    }.padding(16).background(Theme.surface, in: RoundedRectangle(cornerRadius: 24))
                }
            }
            .padding(20)
        }
        .background(Theme.background)
        .navigationTitle("Piggy")
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .topBarTrailing) {
                Button(action: openScanner) { Image(systemName: "camera") }
                    .accessibilityLabel("Neue Quittung scannen")
            }
        }
        .refreshable { await store.reload(); await loadFinance() }
        .task(id: store.receipts) { await loadFinance() }
        .onChange(of: period) { _, _ in Task { await loadFinance() } }
        .onChange(of: currency) { _, _ in Task { await loadFinance() } }
    }
    private var financeCard: some View {
        PiggyCard {
            HStack {
                Button { changeMonth(-1) } label: { Image(systemName: "chevron.left").frame(minWidth: 44, minHeight: 44) }
                    .accessibilityLabel("Vorheriger Monat")
                Spacer()
                Text(period.formatted(.dateTime.month(.wide).year())).font(.headline).multilineTextAlignment(.center)
                Spacer()
                Button { changeMonth(1) } label: { Image(systemName: "chevron.right").frame(minWidth: 44, minHeight: 44) }
                    .accessibilityLabel("Nächster Monat")
                    .disabled(monthKey(period) >= monthKey(Date()))
            }
            if currencies.count > 1 { Picker("Währung", selection: $currency) { ForEach(currencies, id: \.self) { Text($0).tag($0) } }.pickerStyle(.segmented) }
            if loadingFinance { ProgressView("Auswertung wird geladen …") }
            else if let financeError { ScreenError(message: financeError, retry: { Task { await loadFinance() } }) }
            else if let finance, finance.month == monthKey(period), finance.currency == currency {
                Text("Erfasste Ausgaben").foregroundStyle(Theme.secondary)
                Text(money(finance.expenses, currency)).font(.largeTitle.bold()).monospacedDigit().minimumScaleFactor(0.7)
                Text("Die Übersicht enthält deine erfassten Belege. Sie ersetzt keinen vollständigen Kontoauszug.")
                    .font(.footnote).foregroundStyle(Theme.secondary)
                NavigationLink { FinanceView() } label: { Label("Kategorien ansehen", systemImage: "chart.bar.xaxis") }
            }
        }
    }
    private func changeMonth(_ amount: Int) {
        if let next = Calendar.current.date(byAdding: .month, value: amount, to: period) { period = next }
    }
    @MainActor private func loadFinance() async {
        loadingFinance = true
        financeError = nil
        do { finance = try await store.loadFinance(month: monthKey(period), currency: currency) }
        catch { financeError = error.localizedDescription }
        loadingFinance = false
    }
}

struct FinanceView: View {
    @EnvironmentObject private var store: PiggyStore
    @State private var finance: NativeFinanceSummary?
    @State private var month = Date()
    @State private var period = FinancePeriod.month
    private var range: FinanceDateRange { period.range(containing: month) }
    @State private var currency = "CHF"
    @State private var loading = false
    @State private var message: String?
    private var currencies: [String] { Array(Set(store.receipts.map(\.currency)).union(["CHF"])).sorted() }
    var body: some View {
        List {
            Section {
                Picker("Zeitraum", selection: $period) { ForEach(FinancePeriod.allCases) { Text($0.rawValue).tag($0) } }
                DatePicker("Datum im Zeitraum", selection: $month, in: ...Date(), displayedComponents: .date)
                Picker("Währung", selection: $currency) { ForEach(currencies, id: \.self) { Text($0).tag($0) } }
            }
            if loading { ProgressView("Auswertung wird geladen …") }
            if let message { Section { ScreenError(message: message, retry: { Task { await load() } }) } }
            if let finance, finance.month == range.id, finance.currency == currency {
                Section("Erfasste Ausgaben") {
                    Text(money(finance.expenses, currency)).font(.largeTitle.bold()).monospacedDigit()
                    Text("Nur erfasste Belege. Verschiedene Währungen werden getrennt ausgewertet.").font(.footnote).foregroundStyle(Theme.secondary)
                }
                if !finance.byCategory.isEmpty {
                    Section("Nach Kategorie") {
                        Chart(finance.byCategory, id: \.category) { category in
                            BarMark(x: .value("Betrag", NSDecimalNumber(decimal: category.amount).doubleValue), y: .value("Kategorie", category.category))
                                .foregroundStyle(Theme.accent)
                                .accessibilityLabel(category.category)
                                .accessibilityValue(money(category.amount, currency))
                        }
                        .frame(height: CGFloat(max(180, finance.byCategory.count * 42)))
                        .accessibilityLabel("Ausgaben nach Kategorie")
                        ForEach(finance.byCategory, id: \.category) { category in
                            NavigationLink { CategoryReceiptsView(category: category.category, currency: currency, month: range.start, endDate: range.endExclusive) } label: {
                                HStack { Text(category.category); Spacer(); Text(money(category.amount, currency)).monospacedDigit() }
                            }
                        }
                    }
                } else if !loading {
                    EmptyPiggy(title: "Noch keine Ausgaben", message: "Für diesen Zeitraum und diese Währung sind keine Belege erfasst.")
                }
            }
        }
        .navigationTitle("Auswertung")
        .task(id: store.receipts) { await load() }
        .refreshable { await load() }
        .onChange(of: period) { _, _ in Task { await load() } }
        .onChange(of: month) { _, _ in Task { await load() } }
        .onChange(of: currency) { _, _ in Task { await load() } }
    }
    @MainActor private func load() async {
        loading = true
        message = nil
        do { finance = try await store.loadFinance(range: range, currency: currency) }
        catch { message = error.localizedDescription }
        loading = false
    }
}

struct CategoryReceiptsView: View {
    @EnvironmentObject private var store: PiggyStore
    let category: String
    let currency: String
    let month: String
    var endDate: String? = nil
    private var receipts: [Receipt] {
        store.receipts.filter { receipt in receipt.currency == currency && (endDate.map { end in (receipt.receipt_date ?? "") >= month && (receipt.receipt_date ?? "") < end } ?? (receipt.receipt_date ?? "").hasPrefix(month)) && receipt.receipt_items.contains(where: { $0.tags.first == category }) }
    }
    var body: some View {
        List {
            if receipts.isEmpty { Text("Keine passenden Belege.").foregroundStyle(Theme.secondary) }
            ForEach(receipts) { receipt in
                NavigationLink { ReceiptDetailView(receiptID: receipt.id) } label: {
                    VStack(alignment: .leading, spacing: 8) {
                        ReceiptRow(receipt: receipt)
                        ForEach(receipt.receipt_items.filter { $0.tags.first == category }) { item in
                            HStack { Text(item.name).font(.subheadline); Spacer(); Text(money(item.total_price, currency)).font(.subheadline.monospacedDigit()) }
                        }
                    }
                }
            }
        }.navigationTitle(category)
    }
}
