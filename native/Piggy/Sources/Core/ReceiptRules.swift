import Foundation

enum ReceiptRules {
    static func cents(_ value: Decimal) -> Int {
        var source = value * 100
        var rounded = Decimal()
        NSDecimalRound(&rounded, &source, 0, .plain)
        return NSDecimalNumber(decimal: rounded).intValue
    }
    static func money(_ cents: Int) -> Decimal { Decimal(cents) / 100 }
    static func merchantKey(_ value: String) -> String {
        value.folding(options: [.diacriticInsensitive, .caseInsensitive], locale: Locale(identifier: "de"))
            .unicodeScalars.filter { CharacterSet.alphanumerics.contains($0) }.map(String.init).joined()
    }
    static func duplicates(_ draft: ReceiptDraft, in receipts: [Receipt]) -> [Receipt] {
        let key = merchantKey(draft.store_name)
        guard !key.isEmpty, !draft.receipt_date.isEmpty else { return [] }
        return receipts.filter { receipt in
            guard let date = receipt.receipt_date, let amount = receipt.total_amount else { return false }
            return date.prefix(10) == draft.receipt_date.prefix(10) && cents(amount) == cents(draft.total_amount)
                && receipt.currency.uppercased() == draft.currency.uppercased() && merchantKey(receipt.store_name) == key
        }
    }
    static func validate(_ draft: ReceiptDraft, allowMissingDate: Bool = false) throws {
        try PaymentCard.validate(draft.payment_card)
        guard !draft.store_name.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty else {
            throw PiggyFailure.validation("Bitte gib das Geschäft an.")
        }
        guard !draft.total_amount.isNaN, draft.total_amount >= 0, draft.total_amount <= 10_000_000 else { throw PiggyFailure.validation("Bitte prüfe den Gesamtbetrag.") }
        guard draft.currency.range(of: "^[A-Z]{3}$", options: .regularExpression) != nil else { throw PiggyFailure.validation("Bitte wähle eine gültige Währung.") }
        if !allowMissingDate || !draft.receipt_date.isEmpty {
            guard draft.receipt_date.range(of: "^\\d{4}-\\d{2}-\\d{2}$", options: .regularExpression) != nil else { throw PiggyFailure.validation("Bitte prüfe das Belegdatum.") }
            let calendar = DateFormatter()
            calendar.locale = Locale(identifier: "en_US_POSIX")
            calendar.dateFormat = "yyyy-MM-dd"
            calendar.isLenient = false
            guard let date = calendar.date(from: draft.receipt_date), calendar.string(from: date) == draft.receipt_date else {
                throw PiggyFailure.validation("Bitte prüfe das Belegdatum.")
            }
        }
        guard draft.items.allSatisfy({ !$0.name.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty && !$0.quantity.isNaN && $0.quantity > 0 && !$0.total_price.isNaN && !$0.unit_price.isNaN }) else {
            throw PiggyFailure.validation("Bitte prüfe Namen und Mengen deiner Artikel.")
        }
    }
    /// Distribute integer cents, preserving the exact sum across participants.
    static func shares(items: [ReceiptItem], total: Decimal, participants: [UUID], exclusions: [UUID: Set<UUID>]) throws -> [UUID: Decimal] {
        let people = Array(Set(participants)).sorted { $0.uuidString < $1.uuidString }
        guard total >= 0 else { throw PiggyFailure.validation("Ein negativer Gesamtbetrag kann nicht aufgeteilt werden.") }
        guard !people.isEmpty else { throw PiggyFailure.validation("Wähle mindestens eine Person.") }
        var amounts = Dictionary(uniqueKeysWithValues: people.map { ($0, 0) })
        func distribute(_ cents: Int, among included: [UUID]) {
            guard !included.isEmpty else { return }
            let each = cents / included.count
            let remainder = cents % included.count
            for (index, id) in included.enumerated() {
                amounts[id, default: 0] += each + (index < abs(remainder) ? (remainder < 0 ? -1 : 1) : 0)
            }
        }
        if items.isEmpty { distribute(cents(total), among: people) }
        else {
            for item in items {
                let included = people.filter { !(exclusions[$0]?.contains(item.id) ?? false) }
                guard !included.isEmpty || cents(item.total_price ?? 0) == 0 else {
                    throw PiggyFailure.validation("Jeder Artikel muss mindestens einer Person zugeordnet sein.")
                }
                distribute(cents(item.total_price ?? 0), among: included)
            }
            // The receipt's true total is authoritative; account for tip/discount/rounding exactly once.
            let difference = cents(total) - amounts.values.reduce(0, +)
            distribute(difference, among: people)
        }
        return amounts.mapValues(money)
    }
}
