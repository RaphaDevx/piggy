import Foundation

// Kontoauszug-Abgleich: Modelle und reine Logik (ohne Netzwerk, testbar in PiggyCore).

struct BankStatement: Identifiable, Codable, Hashable {
    var id: UUID
    var account_label: String
    var account_type: String
    var file_name: String?
    var file_path: String?
    var period_start: String?
    var period_end: String?
    var status: String
    var stage: String?
    var page_count: Int?
    var pages_done: Int?
    var pages_failed: Int?
    var transactions_imported: Int?
    var duplicates_skipped: Int?
    var error_message: String?
    var created_at: String

    var isRunning: Bool { status == "processing" && stage != "done" && stage != "error" }
    var failed: Bool { status == "error" || stage == "error" || (pages_failed ?? 0) > 0 }
    /// Fortschritt 0...1 für die Anzeige; nil, wenn die Seitenzahl noch nicht bekannt ist.
    var progress: Double? {
        guard let total = page_count, total > 0 else { return nil }
        return min(1, Double(pages_done ?? 0) / Double(total))
    }
}

struct BankTransaction: Identifiable, Codable, Hashable {
    var id: UUID
    var statement_id: UUID
    var booking_date: String
    var amount: Decimal
    var currency: String
    var description: String
    var match_status: String
}

struct ReceiptMatch: Identifiable, Codable, Hashable {
    var id: UUID
    var transaction_id: UUID
    var receipt_id: UUID
    var score: Decimal?
    var match_type: String
}

struct MatchSuggestion: Identifiable, Codable, Hashable {
    var id: UUID
    var transaction_id: UUID
    var receipt_id: UUID
    var kind: String
    var diff_amount: Decimal
    var score: Decimal?
    var status: String
    var note: String?

    var kindTitle: String {
        switch kind {
        case "tip": return "Trinkgeld"
        case "fx": return "Fremdwährung / Gebühr"
        case "discount": return "Rabatt"
        case "partial": return "Teilzahlung"
        default: return "Differenz"
        }
    }
}

/// Bestätigung von beiden Seiten: Buchung und Quittung gehören zusammen.
struct ConfirmedPair: Identifiable, Hashable {
    var id: UUID { match.id }
    var match: ReceiptMatch
    var transaction: BankTransaction
    var receipt: Receipt
    /// Betrag der Buchung minus Quittungstotal (beide positiv gerechnet). 0 = stimmt exakt.
    var difference: Decimal {
        abs(transaction.amount) - (receipt.total_amount ?? 0)
    }
}

struct OpenSuggestion: Identifiable, Hashable {
    var id: UUID { suggestion.id }
    var suggestion: MatchSuggestion
    var transaction: BankTransaction
    var receipt: Receipt
}

struct ReconciliationReport: Hashable {
    /// Quittung und Buchung sind zugeordnet.
    var confirmed: [ConfirmedPair] = []
    /// Ausgaben auf dem Konto ohne Quittung.
    var bankOnly: [BankTransaction] = []
    /// Quittungen im Zeitraum der Auszüge ohne Buchung.
    var receiptOnly: [Receipt] = []
    /// Von Piggy vorgeschlagen, noch nicht bestätigt.
    var suggestions: [OpenSuggestion] = []
    /// Quittungen ausserhalb des Zeitraums aller Auszüge dieser Währung (kein Vergleich möglich).
    var receiptsOutsideStatements = 0
    var ignoredCount = 0
}

enum Reconciliation {
    static func build(transactions: [BankTransaction], matches: [ReceiptMatch], receipts: [Receipt],
                      suggestions: [MatchSuggestion]) -> ReconciliationReport {
        var report = ReconciliationReport()
        let receiptByID = Dictionary(receipts.map { ($0.id, $0) }, uniquingKeysWith: { first, _ in first })
        let transactionByID = Dictionary(transactions.map { ($0.id, $0) }, uniquingKeysWith: { first, _ in first })

        var matchedTransactions = Set<UUID>(), matchedReceipts = Set<UUID>()
        for match in matches {
            guard let transaction = transactionByID[match.transaction_id], let receipt = receiptByID[match.receipt_id] else { continue }
            report.confirmed.append(ConfirmedPair(match: match, transaction: transaction, receipt: receipt))
            matchedTransactions.insert(transaction.id); matchedReceipts.insert(receipt.id)
        }
        report.confirmed.sort { $0.transaction.booking_date > $1.transaction.booking_date }

        var suggestedTransactions = Set<UUID>(), suggestedReceipts = Set<UUID>()
        for suggestion in suggestions where suggestion.status == "open" {
            guard let transaction = transactionByID[suggestion.transaction_id], let receipt = receiptByID[suggestion.receipt_id],
                  !matchedTransactions.contains(transaction.id), !matchedReceipts.contains(receipt.id) else { continue }
            report.suggestions.append(OpenSuggestion(suggestion: suggestion, transaction: transaction, receipt: receipt))
            suggestedTransactions.insert(transaction.id); suggestedReceipts.insert(receipt.id)
        }
        report.suggestions.sort { ($0.suggestion.score ?? 0) > ($1.suggestion.score ?? 0) }

        report.ignoredCount = transactions.filter { $0.match_status == "ignored" }.count
        report.bankOnly = transactions
            .filter { $0.amount < 0 && $0.match_status == "unmatched" && !matchedTransactions.contains($0.id) && !suggestedTransactions.contains($0.id) }
            .sorted { $0.booking_date > $1.booking_date }

        // Zeitraum je Währung aus den tatsächlichen Buchungen. Ohne Auszug lässt sich nichts bestätigen.
        var window: [String: (first: String, last: String)] = [:]
        for transaction in transactions {
            let current = window[transaction.currency]
            window[transaction.currency] = (
                min(current?.first ?? transaction.booking_date, transaction.booking_date),
                max(current?.last ?? transaction.booking_date, transaction.booking_date))
        }
        for receipt in receipts where !matchedReceipts.contains(receipt.id) && !suggestedReceipts.contains(receipt.id) {
            guard let range = window[receipt.currency] else { report.receiptsOutsideStatements += 1; continue }
            let date = String(receipt.displayDate.prefix(10))
            if date >= range.first && date <= range.last { report.receiptOnly.append(receipt) }
            else { report.receiptsOutsideStatements += 1 }
        }
        report.receiptOnly.sort { $0.displayDate > $1.displayDate }
        return report
    }
}

/// Entfernt Konto- und Kartennummern aus Text, der die App verlässt (Teilen, Export).
enum Masking {
    static func sanitize(_ text: String) -> String {
        var result = text
        // IBAN: Länderkürzel, zwei Prüfziffern, dann 10 bis 30 Zeichen mit optionalen Leerzeichen.
        result = replace(#"\b[A-Z]{2}\d{2}(?:\s?[A-Z0-9]){10,30}\b"#, in: result, with: "IBAN ••••")
        // Kartennummer: 13 bis 19 Ziffern, mit Leerzeichen oder Strichen gruppiert.
        result = replace(#"\b(?:\d[ -]?){13,19}\b"#, in: result, with: "•••• ")
        return result.trimmingCharacters(in: .whitespacesAndNewlines)
    }
    private static func replace(_ pattern: String, in text: String, with template: String) -> String {
        guard let regex = try? NSRegularExpression(pattern: pattern) else { return text }
        return regex.stringByReplacingMatches(in: text, range: NSRange(text.startIndex..., in: text),
                                              withTemplate: NSRegularExpression.escapedTemplate(for: template))
    }
}

/// Prüfung vor dem Hochladen eines Kontoauszugs.
enum StatementFile {
    static let maxBytes = 50 * 1024 * 1024
    static let allowedExtensions: Set<String> = ["pdf", "csv", "xml"]

    static func mimeType(forExtension ext: String) -> String? {
        switch ext.lowercased() {
        case "pdf": return "application/pdf"
        case "csv": return "text/csv"
        case "xml": return "application/xml"
        default: return nil
        }
    }

    /// Storage-Schlüssel erlauben keine Umlaute oder Sonderzeichen.
    static func safeName(_ fileName: String) -> String {
        let folded = fileName.folding(options: .diacriticInsensitive, locale: Locale(identifier: "en_US_POSIX"))
        let cleaned = folded.unicodeScalars.map { scalar -> Character in
            let c = Character(scalar)
            return (c.isASCII && (c.isLetter || c.isNumber)) || c == "." || c == "_" || c == "-" ? c : "_"
        }
        var name = String(cleaned)
        while name.contains("__") { name = name.replacingOccurrences(of: "__", with: "_") }
        name = name.trimmingCharacters(in: CharacterSet(charactersIn: "_"))
        return name.isEmpty ? "auszug" : name
    }

    static func validate(fileName: String, byteCount: Int) throws -> String {
        let ext = (fileName as NSString).pathExtension.lowercased()
        guard allowedExtensions.contains(ext), let mime = mimeType(forExtension: ext) else {
            throw PiggyFailure.validation("Bitte wähle eine PDF-, CSV- oder CAMT-XML-Datei.")
        }
        guard byteCount > 0 else { throw PiggyFailure.validation("Die Datei ist leer oder konnte nicht gelesen werden.") }
        guard byteCount <= maxBytes else { throw PiggyFailure.validation("Die Datei ist grösser als 50 MB.") }
        return mime
    }
}

extension ReconciliationReport {
    /// Text zum Teilen. Keine Konto- oder Kartennummern, keine Kontobezeichnung.
    func shareText(currency: String) -> String {
        func money(_ value: Decimal) -> String {
            let number = NSDecimalNumber(decimal: value)
            let formatter = NumberFormatter()
            formatter.numberStyle = .decimal; formatter.minimumFractionDigits = 2; formatter.maximumFractionDigits = 2
            formatter.locale = Locale(identifier: "de_CH")
            return "\(currency) \(formatter.string(from: number) ?? number.stringValue)"
        }
        var lines = ["Piggy Abgleich"]
        lines.append("Bestätigt (Quittung und Buchung): \(confirmed.count)")
        for pair in confirmed where pair.transaction.currency == currency {
            lines.append("✓ \(pair.transaction.booking_date) \(pair.receipt.store_name) \(money(abs(pair.transaction.amount)))")
        }
        if !bankOnly.isEmpty {
            lines.append("Nur auf dem Konto, Quittung fehlt: \(bankOnly.count)")
            for item in bankOnly where item.currency == currency {
                lines.append("• \(item.booking_date) \(Masking.sanitize(item.description)) \(money(abs(item.amount)))")
            }
        }
        if !receiptOnly.isEmpty {
            lines.append("Quittung ohne Buchung: \(receiptOnly.count)")
            for receipt in receiptOnly where receipt.currency == currency {
                lines.append("• \(receipt.displayDate) \(receipt.store_name) \(money(receipt.total_amount ?? 0))")
            }
        }
        return lines.joined(separator: "\n")
    }
}

/// Option C: eine bestätigte Ausgabe an einen Freund senden. Nur Händler, Datum, Betrag und Notiz,
/// nie Buchungstext, Konto oder Kartennummer.
struct SharedBankItem: Identifiable, Codable, Hashable {
    var id: UUID
    var sender_id: UUID
    var recipient_id: UUID
    var title: String
    var booking_date: String
    var amount: Decimal
    var currency: String
    var note: String?
    var status: String
    var created_at: String
    var sender: Profile?
    var recipient: Profile?

    var statusText: String {
        switch status {
        case "confirmed": return "Bestätigt"
        case "declined": return "Abgelehnt"
        default: return "Wartet auf Antwort"
        }
    }
}

extension SharedBankItem {
    /// Notiz auf eine vernünftige Länge kürzen und von Konto- und Kartennummern befreien.
    static func cleanNote(_ text: String) -> String? {
        let cleaned = Masking.sanitize(String(text.trimmingCharacters(in: .whitespacesAndNewlines).prefix(200)))
        return cleaned.isEmpty ? nil : cleaned
    }
}
