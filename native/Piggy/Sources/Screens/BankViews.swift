import SwiftUI
import UniformTypeIdentifiers

/// Schalter aus Info.plist (xcconfig). Für die Store-Einreichung nur nach Rechtsprüfung auf YES lassen.
enum BankFeature {
    static var enabled: Bool { flag("PIGGY_BANK_IMPORT") }
    static var sharing: Bool { enabled && flag("PIGGY_SHARE_BANK_ITEMS") }
    private static func flag(_ key: String) -> Bool { (Bundle.main.object(forInfoDictionaryKey: key) as? String) == "YES" }
}

struct BankHomeView: View {
    @EnvironmentObject private var store: PiggyStore
    @State private var consentShown = false
    @State private var importAfterConsent = false
    @State private var importing = false
    @State private var pickedURL: URL?
    @State private var busy = false
    @State private var error: String?
    @State private var deleting: BankStatement?
    private var running: Bool { store.bank.statements.contains { $0.isRunning } }

    var body: some View {
        let report = store.reconciliation
        List {
            Section {
                Text("Lade einen Kontoauszug hoch. Piggy gleicht die Buchungen mit deinen Quittungen ab. Vorschläge übernimmst du immer selbst.")
                    .font(.footnote).foregroundStyle(.secondary)
                Button {
                    if store.aiConsent && store.statementConsent { importing = true } else { consentShown = true }
                } label: { Label("Kontoauszug hochladen", systemImage: "square.and.arrow.up") }
                .disabled(busy)
            }
            if store.bank.loaded && !store.bank.statements.isEmpty {
                Section("Abgleich") {
                    summaryLink(.confirmed, "Bestätigt", count: report.confirmed.count, icon: "checkmark.seal.fill")
                    summaryLink(.suggestions, "Vorschläge", count: report.suggestions.count, icon: "lightbulb")
                    summaryLink(.bankOnly, "Quittung fehlt", count: report.bankOnly.count, icon: "creditcard")
                    summaryLink(.receiptOnly, "Buchung fehlt", count: report.receiptOnly.count, icon: "receipt")
                    if report.receiptsOutsideStatements > 0 {
                        Text("\(report.receiptsOutsideStatements) Quittungen liegen ausserhalb der hochgeladenen Auszüge.")
                            .font(.footnote).foregroundStyle(.secondary)
                    }
                }
            }
            Section("Kontoauszüge") {
                if store.bank.statements.isEmpty {
                    Text(store.bank.loaded ? "Noch kein Kontoauszug hochgeladen." : "Wird geladen …").foregroundStyle(.secondary)
                }
                ForEach(store.bank.statements) { statement in
                    StatementRow(statement: statement) { run { try await store.retryStatement(statement) } }
                        .swipeActions { Button("Löschen", role: .destructive) { deleting = statement } }
                }
            }
            if let error { Section { ScreenError(message: error) { Task { await refresh() } } } }
        }
        .navigationTitle("Abgleich")
        .toolbar {
            ToolbarItemGroup(placement: .topBarTrailing) {
                if !report.confirmed.isEmpty || !report.bankOnly.isEmpty || !report.receiptOnly.isEmpty {
                    ShareLink(item: report.shareText(currency: store.bank.transactions.first?.currency ?? "CHF")) {
                        Image(systemName: "square.and.arrow.up")
                    }.accessibilityLabel("Abgleich teilen")
                }
                Button { run { try await store.rematchAll() } } label: { Image(systemName: "arrow.triangle.2.circlepath") }
                    .accessibilityLabel("Neu abgleichen").disabled(busy || store.bank.transactions.isEmpty)
            }
        }
        .refreshable { await refresh() }
        .task { await refresh() }
        .task(id: running) {
            while running && !Task.isCancelled {
                try? await Task.sleep(for: .seconds(4))
                await refresh()
            }
        }
        .sheet(isPresented: $consentShown, onDismiss: { if importAfterConsent { importAfterConsent = false; importing = true } }) {
            StatementConsentView { importAfterConsent = true }
        }
        .fileImporter(isPresented: $importing, allowedContentTypes: [.pdf, .commaSeparatedText, .xml], allowsMultipleSelection: false) { result in
            switch result {
            case .success(let urls): pickedURL = urls.first
            case .failure(let failure): error = failure.localizedDescription
            }
        }
        .sheet(item: Binding(get: { pickedURL.map(PickedFile.init) }, set: { if $0 == nil { pickedURL = nil } })) { file in
            StatementNameView(fileURL: file.url) { pickedURL = nil }
        }
        .alert("Kontoauszug löschen?", isPresented: Binding(get: { deleting != nil }, set: { if !$0 { deleting = nil } }), presenting: deleting) { statement in
            Button("Löschen", role: .destructive) { run { try await store.deleteStatement(statement) } }
            Button("Abbrechen", role: .cancel) {}
        } message: { _ in Text("Datei, Buchungen und Zuordnungen werden gelöscht. Deine Quittungen bleiben erhalten.") }
    }

    private func summaryLink(_ filter: ReconFilter, _ title: String, count: Int, icon: String) -> some View {
        NavigationLink { ReconciliationView(initial: filter) } label: {
            HStack { Label(title, systemImage: icon); Spacer(); Text("\(count)").foregroundStyle(.secondary).monospacedDigit() }
        }
        .accessibilityElement(children: .combine)
    }
    private func refresh() async {
        do { try await store.loadBank(); error = nil } catch { self.error = error.localizedDescription }
    }
    private func run(_ work: @escaping @MainActor () async throws -> Void) {
        busy = true; error = nil
        Task { do { try await work() } catch { self.error = error.localizedDescription }; busy = false }
    }
}

private struct PickedFile: Identifiable, Equatable {
    let url: URL
    var id: URL { url }
}

struct StatementRow: View {
    let statement: BankStatement
    let retry: () -> Void
    var body: some View {
        VStack(alignment: .leading, spacing: 6) {
            HStack {
                Image(systemName: statement.account_type == "credit" ? "creditcard" : "building.columns").accessibilityHidden(true)
                Text(statement.account_label).font(.headline)
            }
            if let name = statement.file_name { Text(name).font(.footnote).foregroundStyle(.secondary).lineLimit(1) }
            if statement.isRunning {
                if let progress = statement.progress { ProgressView(value: progress) } else { ProgressView() }
            }
            Text(statusText).font(.subheadline).foregroundStyle(statement.failed ? Color.orange : Theme.secondary)
            if statement.failed && !statement.isRunning { Button("Erneut versuchen", action: retry).buttonStyle(.bordered) }
        }
        .padding(.vertical, 4)
        .accessibilityElement(children: .contain)
    }
    private var statusText: String {
        switch statement.stage ?? statement.status {
        case "queued": return "In der Warteschlange"
        case "extracting": return "Wird gelesen (\(statement.pages_done ?? 0) von \(statement.page_count ?? 0) Seiten)"
        case "reconciling": return "Wird mit Quittungen abgeglichen"
        case "done", "ok":
            let base = "\(statement.transactions_imported ?? 0) Buchungen gelesen"
            if let failed = statement.pages_failed, failed > 0 { return base + ", \(failed) Seiten nicht lesbar" }
            return base
        case "error": return statement.error_message ?? "Der Auszug konnte nicht gelesen werden."
        default: return statement.status == "processing" ? "Wird verarbeitet" : statement.status
        }
    }
}

/// Eigene Einwilligung, bevor ein Kontoauszug hochgeladen wird.
struct StatementConsentView: View {
    @EnvironmentObject private var store: PiggyStore
    @Environment(\.dismiss) private var dismiss
    var accepted: () -> Void
    var body: some View {
        NavigationStack {
            Form {
                Section("Was passiert mit dem Kontoauszug?") {
                    Label("Die Datei wird verschlüsselt in deinem privaten Bereich gespeichert. Nur du hast Zugriff.", systemImage: "lock")
                    Label("PDF- und CSV-Dateien werden zum Auslesen der Buchungen an Anthropic (Claude) übermittelt. CAMT-XML-Dateien werden ohne KI gelesen.", systemImage: "sparkles")
                    Label("Piggy bewegt kein Geld und greift nicht auf dein Bankkonto zu.", systemImage: "banknote")
                    Label("Du kannst jeden Auszug jederzeit löschen. Datei, Buchungen und Zuordnungen werden dann entfernt.", systemImage: "trash")
                }
                Section {
                    Text("Tipp: Lade nur Auszüge hoch, die du auswerten lassen willst. Konto- und Kartennummern verlassen die App nie über Teilen.")
                        .font(.footnote).foregroundStyle(.secondary)
                }
                Section {
                    Button("Zustimmen und Auszug wählen") {
                        store.aiConsent = true; store.statementConsent = true
                        accepted(); dismiss()
                    }.bold()
                    Button("Nicht jetzt", role: .cancel) { dismiss() }
                }
            }
            .navigationTitle("Kontoauszug auswerten")
            .navigationBarTitleDisplayMode(.inline)
        }
    }
}

struct StatementNameView: View {
    let fileURL: URL
    var finished: () -> Void
    @EnvironmentObject private var store: PiggyStore
    @State private var label = ""
    @State private var accountType = "debit"
    @State private var busy = false
    @State private var error: String?
    var body: some View {
        NavigationStack {
            Form {
                Section("Datei") { Text(fileURL.lastPathComponent) }
                Section("Konto") {
                    TextField("Bezeichnung (z. B. PostFinance)", text: $label)
                    Picker("Art", selection: $accountType) {
                        Text("Konto").tag("debit"); Text("Kreditkarte").tag("credit")
                    }.pickerStyle(.segmented)
                }
                if let error { Section { ScreenError(message: error) } }
                BusyButton(title: "Auswerten", busy: busy) {
                    busy = true; error = nil
                    Task {
                        do { try await store.uploadStatement(fileURL: fileURL, label: label, accountType: accountType); finished() }
                        catch { self.error = error.localizedDescription }
                        busy = false
                    }
                }
            }
            .navigationTitle("Kontoauszug")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar { ToolbarItem(placement: .cancellationAction) { Button("Abbrechen") { finished() }.disabled(busy) } }
        }
        .interactiveDismissDisabled(busy)
    }
}

enum ReconFilter: String, CaseIterable, Identifiable {
    case confirmed, suggestions, bankOnly, receiptOnly
    var id: String { rawValue }
    var title: String {
        switch self {
        case .confirmed: return "Bestätigt"
        case .suggestions: return "Vorschläge"
        case .bankOnly: return "Quittung fehlt"
        case .receiptOnly: return "Buchung fehlt"
        }
    }
}

struct ReconciliationView: View {
    @EnvironmentObject private var store: PiggyStore
    @State private var filter: ReconFilter
    @State private var busy = false
    @State private var error: String?
    @State private var pickReceiptFor: BankTransaction?
    @State private var pickTransactionFor: Receipt?
    @State private var sharing: ConfirmedPair?

    init(initial: ReconFilter) { _filter = State(initialValue: initial) }

    var body: some View {
        let report = store.reconciliation
        List {
            Section {
                Picker("Ansicht", selection: $filter) { ForEach(ReconFilter.allCases) { Text($0.title).tag($0) } }
                    .pickerStyle(.menu)
            }
            switch filter {
            case .confirmed:
                if report.confirmed.isEmpty { empty("Noch nichts bestätigt", "Sobald Quittung und Buchung zusammenpassen, erscheinen sie hier.") }
                ForEach(report.confirmed) { pair in
                    VStack(alignment: .leading, spacing: 4) {
                        HStack { Text(pair.receipt.store_name).font(.headline); Spacer(); Text(money(abs(pair.transaction.amount), pair.transaction.currency)).monospacedDigit() }
                        Text(receiptDate(pair.transaction.booking_date)).font(.subheadline).foregroundStyle(.secondary)
                        if pair.difference != 0 {
                            Text("Differenz zur Quittung: \(money(pair.difference, pair.transaction.currency))").font(.footnote).foregroundStyle(.orange)
                        }
                    }
                    .accessibilityElement(children: .combine)
                    .swipeActions {
                        Button("Lösen", role: .destructive) { run { try await store.removeMatch(transactionID: pair.transaction.id) } }
                        if BankFeature.sharing { Button("Senden") { sharing = pair }.tint(Theme.accent) }
                    }
                }
            case .suggestions:
                if report.suggestions.isEmpty { empty("Keine Vorschläge", "Piggy schlägt etwas vor, wenn Beträge fast passen, etwa mit Trinkgeld.") }
                ForEach(report.suggestions) { item in suggestionRow(item) }
            case .bankOnly:
                if report.bankOnly.isEmpty { empty("Alles belegt", "Zu jeder Ausgabe auf dem Konto gibt es eine Quittung.") }
                ForEach(report.bankOnly) { tx in
                    VStack(alignment: .leading, spacing: 6) {
                        HStack { Text(tx.description).font(.headline).lineLimit(2); Spacer(); Text(money(abs(tx.amount), tx.currency)).monospacedDigit() }
                        Text(receiptDate(tx.booking_date)).font(.subheadline).foregroundStyle(.secondary)
                        HStack {
                            Button("Quittung zuordnen") { pickReceiptFor = tx }.buttonStyle(.bordered)
                            Button("Ignorieren") { run { try await store.ignoreTransaction(tx.id) } }.buttonStyle(.bordered)
                        }.disabled(busy)
                    }
                }
            case .receiptOnly:
                if report.receiptOnly.isEmpty { empty("Keine offenen Quittungen", "Alle Quittungen im Zeitraum der Auszüge sind auf dem Konto gefunden.") }
                ForEach(report.receiptOnly) { receipt in
                    VStack(alignment: .leading, spacing: 6) {
                        ReceiptRow(receipt: receipt)
                        Button("Buchung zuordnen") { pickTransactionFor = receipt }.buttonStyle(.bordered).disabled(busy)
                    }
                }
            }
            if let error { Section { ScreenError(message: error) } }
        }
        .navigationTitle(filter.title)
        .navigationBarTitleDisplayMode(.inline)
        .refreshable { try? await store.loadBank() }
        .sheet(item: $pickReceiptFor) { tx in
            PickReceiptSheet(transaction: tx) { receipt in run { try await store.manualMatch(transactionID: tx.id, receiptID: receipt.id) } }
        }
        .sheet(item: $pickTransactionFor) { receipt in
            PickTransactionSheet(receipt: receipt) { tx in run { try await store.manualMatch(transactionID: tx.id, receiptID: receipt.id) } }
        }
        .sheet(item: $sharing) { pair in ShareBankItemView(pair: pair) }
    }

    private func suggestionRow(_ item: OpenSuggestion) -> some View {
        VStack(alignment: .leading, spacing: 6) {
            Text(item.receipt.store_name).font(.headline)
            Text("Buchung \(money(abs(item.transaction.amount), item.transaction.currency)) am \(receiptDate(item.transaction.booking_date)), Quittung \(money(item.receipt.total_amount, item.receipt.currency))")
                .font(.subheadline).foregroundStyle(.secondary)
            Text("\(item.suggestion.kindTitle): \(money(item.suggestion.diff_amount, item.transaction.currency))").font(.footnote)
            HStack {
                Button("Übernehmen") { run { try await store.acceptSuggestion(item) } }.buttonStyle(.borderedProminent).tint(Theme.accent).foregroundStyle(Theme.text)
                Button("Ablehnen") { run { try await store.rejectSuggestion(item) } }.buttonStyle(.bordered)
            }.disabled(busy)
        }
        .padding(.vertical, 4)
    }
    private func empty(_ title: String, _ message: String) -> some View {
        EmptyPiggy(title: title, message: message).listRowBackground(Color.clear)
    }
    private func run(_ work: @escaping @MainActor () async throws -> Void) {
        busy = true; error = nil
        Task { do { try await work(); pickReceiptFor = nil; pickTransactionFor = nil } catch { self.error = error.localizedDescription }; busy = false }
    }
}

extension BankTransaction {
    /// Hilfsfunktion für die Auswahl: wie nah liegt die Buchung an einer Quittung (Betrag zuerst, dann Datum).
    func distance(to receipt: Receipt) -> (Decimal, String) {
        (abs(abs(amount) - (receipt.total_amount ?? 0)), booking_date == receipt.displayDate ? "0" : "1")
    }
}

struct PickReceiptSheet: View {
    let transaction: BankTransaction
    var pick: (Receipt) -> Void
    @EnvironmentObject private var store: PiggyStore
    @Environment(\.dismiss) private var dismiss
    private var candidates: [Receipt] {
        let matched = Set(store.bank.matches.map(\.receipt_id))
        return store.receipts.filter { $0.currency == transaction.currency && !matched.contains($0.id) }
            .sorted { transaction.distance(to: $0) < transaction.distance(to: $1) }
    }
    var body: some View {
        NavigationStack {
            List {
                Section("Buchung") { Text("\(transaction.description)\n\(money(abs(transaction.amount), transaction.currency)) am \(receiptDate(transaction.booking_date))") }
                Section("Quittung wählen") {
                    if candidates.isEmpty { Text("Keine freie Quittung in dieser Währung.").foregroundStyle(.secondary) }
                    ForEach(candidates.prefix(40)) { receipt in
                        Button { pick(receipt); dismiss() } label: { ReceiptRow(receipt: receipt) }.foregroundStyle(Theme.text)
                    }
                }
            }
            .navigationTitle("Zuordnen").navigationBarTitleDisplayMode(.inline)
            .toolbar { ToolbarItem(placement: .cancellationAction) { Button("Abbrechen") { dismiss() } } }
        }
    }
}

struct PickTransactionSheet: View {
    let receipt: Receipt
    var pick: (BankTransaction) -> Void
    @EnvironmentObject private var store: PiggyStore
    @Environment(\.dismiss) private var dismiss
    private var candidates: [BankTransaction] {
        store.reconciliation.bankOnly.filter { $0.currency == receipt.currency }
            .sorted { $0.distance(to: receipt) < $1.distance(to: receipt) }
    }
    var body: some View {
        NavigationStack {
            List {
                Section("Quittung") { ReceiptRow(receipt: receipt) }
                Section("Buchung wählen") {
                    if candidates.isEmpty { Text("Keine freie Buchung in dieser Währung.").foregroundStyle(.secondary) }
                    ForEach(candidates.prefix(40)) { tx in
                        Button { pick(tx); dismiss() } label: {
                            HStack { VStack(alignment: .leading) { Text(tx.description).lineLimit(2); Text(receiptDate(tx.booking_date)).font(.footnote).foregroundStyle(.secondary) }; Spacer(); Text(money(abs(tx.amount), tx.currency)).monospacedDigit() }
                        }.foregroundStyle(Theme.text)
                    }
                }
            }
            .navigationTitle("Zuordnen").navigationBarTitleDisplayMode(.inline)
            .toolbar { ToolbarItem(placement: .cancellationAction) { Button("Abbrechen") { dismiss() } } }
        }
    }
}

/// Option C: bestätigte Ausgabe an einen Freund senden. Es gehen nur Händler, Datum, Betrag und Notiz raus.
struct ShareBankItemView: View {
    let pair: ConfirmedPair
    @EnvironmentObject private var store: PiggyStore
    @Environment(\.dismiss) private var dismiss
    @State private var friendID: UUID?
    @State private var note = ""
    @State private var busy = false
    @State private var error: String?
    var body: some View {
        NavigationStack {
            Form {
                Section("Ausgabe") {
                    Text(pair.receipt.store_name).font(.headline)
                    Text("\(money(abs(pair.transaction.amount), pair.transaction.currency)) am \(receiptDate(pair.transaction.booking_date))")
                    Text("Es werden nur Händler, Datum, Betrag und deine Notiz gesendet. Kein Buchungstext, keine Konto- oder Kartennummer.")
                        .font(.footnote).foregroundStyle(.secondary)
                }
                Section("An") {
                    if store.friends.isEmpty { Text("Füge zuerst Freunde unter «Gemeinsam» hinzu.").foregroundStyle(.secondary) }
                    ForEach(store.friends) { friend in
                        Button { friendID = friend.profile.id } label: {
                            HStack { Text(friend.profile.name); Spacer(); if friendID == friend.profile.id { Image(systemName: "checkmark") } }
                        }.foregroundStyle(Theme.text)
                    }
                }
                Section("Notiz (optional)") { TextField("Zum Beispiel: Mein Anteil für das Znacht", text: $note, axis: .vertical) }
                if let error { Section { ScreenError(message: error) } }
                BusyButton(title: "Senden", busy: busy, disabled: friendID == nil) {
                    guard let friend = store.friends.first(where: { $0.profile.id == friendID })?.profile else { return }
                    busy = true; error = nil
                    Task {
                        do { try await store.sendBankItem(pair, to: friend, note: note); dismiss() }
                        catch { self.error = error.localizedDescription }
                        busy = false
                    }
                }
            }
            .navigationTitle("An Freund senden").navigationBarTitleDisplayMode(.inline)
            .toolbar { ToolbarItem(placement: .cancellationAction) { Button("Abbrechen") { dismiss() }.disabled(busy) } }
        }
        .interactiveDismissDisabled(busy)
    }
}

/// Abschnitt für «Gemeinsam»: erhaltene und gesendete Ausgaben mit Status von beiden Seiten.
struct SharedItemsSection: View {
    @EnvironmentObject private var store: PiggyStore
    @State private var busy = false
    @State private var error: String?
    var body: some View {
        if BankFeature.sharing, let uid = store.userID {
            let incoming = store.bank.shared.filter { $0.recipient_id == uid }
            let outgoing = store.bank.shared.filter { $0.sender_id == uid }
            if !incoming.isEmpty {
                Section("Von Freunden erhalten") {
                    ForEach(incoming) { item in
                        VStack(alignment: .leading, spacing: 6) {
                            Text("\(item.sender?.name ?? "Freund"): \(item.title)").font(.headline)
                            Text("\(money(item.amount, item.currency)) am \(receiptDate(item.booking_date))").font(.subheadline)
                            if let note = item.note { Text(note).font(.footnote).foregroundStyle(.secondary) }
                            if item.status == "sent" {
                                HStack {
                                    Button("Bestätigen") { run { try await store.respond(to: item, accept: true) } }.buttonStyle(.borderedProminent).tint(Theme.accent).foregroundStyle(Theme.text)
                                    Button("Ablehnen") { run { try await store.respond(to: item, accept: false) } }.buttonStyle(.bordered)
                                }.disabled(busy)
                            } else { Text(item.statusText).font(.footnote).foregroundStyle(.secondary) }
                        }
                    }
                }
            }
            if !outgoing.isEmpty {
                Section("An Freunde gesendet") {
                    ForEach(outgoing) { item in
                        VStack(alignment: .leading, spacing: 4) {
                            Text("\(item.recipient?.name ?? "Freund"): \(item.title)").font(.headline)
                            Text("\(money(item.amount, item.currency)) am \(receiptDate(item.booking_date))").font(.subheadline)
                            Text(item.statusText).font(.footnote).foregroundStyle(item.status == "confirmed" ? Color.green : Theme.secondary)
                        }
                        .swipeActions { if item.status == "sent" { Button("Zurückziehen", role: .destructive) { run { try await store.withdraw(item) } } } }
                    }
                }
            }
            if let error { Section { ScreenError(message: error) } }
        }
    }
    private func run(_ work: @escaping @MainActor () async throws -> Void) {
        busy = true; error = nil
        Task { do { try await work() } catch { self.error = error.localizedDescription }; busy = false }
    }
}
