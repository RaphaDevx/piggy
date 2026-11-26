import Foundation

struct ProfileCard: Identifiable, Codable, Hashable {
    var id: UUID
    var user_id: UUID
    var last_four: String
    var label: String?
    var network: String? { PaymentCard.network(in: label ?? "") }
    var displayName: String {
        let name = label?.trimmingCharacters(in: .whitespacesAndNewlines) ?? ""
        return "\(name.isEmpty ? "Karte" : name) ···· \(last_four)"
    }
    var canonical: String { PaymentCard.format(lastFour: last_four, network: network) }
}

/// Last four digits are a hint, never a globally unique card identifier.
enum PaymentCard {
    static let networks = ["Mastercard", "Visa", "American Express", "Maestro", "PostFinance", "Diners Club", "Discover", "UnionPay"]
    static func network(in text: String) -> String? {
        let patterns = [#"master\s*card"#, #"\bvisa\b"#, #"american\s*express|\bamex\b"#,
                        #"\bmaestro\b"#, #"post\s*finance"#, #"diners\s*club"#, #"\bdiscover\b"#, #"union\s*pay"#]
        let found = zip(networks, patterns).compactMap { name, pattern in
            text.range(of: pattern, options: [.regularExpression, .caseInsensitive]) == nil ? nil : name
        }
        return found.count == 1 ? found.first : nil
    }
    static func lastFour(in text: String?) -> String? {
        guard let text else { return nil }
        let trimmed = text.trimmingCharacters(in: .whitespacesAndNewlines)
        // Only a standalone suffix or a masked PAN. Never take four digits out of an amount/date.
        guard let range = trimmed.range(of: #"(?<![0-9.,/])[0-9]{4}$"#, options: .regularExpression) else { return nil }
        let prefix = String(trimmed[..<range.lowerBound])
        guard !prefix.contains(where: { $0.isNumber }),
              prefix.isEmpty || prefix.last?.isWhitespace == true || "*xX•·-".contains(prefix.last!) else { return nil }
        return String(trimmed[range])
    }
    static func validate(_ raw: String?) throws {
        guard let raw, !raw.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty else { return }
        guard lastFour(in: raw) != nil else {
            throw PiggyFailure.validation("Bitte prüfe die Kartenangabe: Kartentyp und nur die letzten vier Ziffern, z. B. Mastercard ···· 5327.")
        }
    }
    static func format(lastFour: String, network: String?) -> String {
        "\(network ?? "Karte") ···· \(lastFour)"
    }
    static func matches(_ raw: String?, method: String?, cards: [ProfileCard]) -> [ProfileCard] {
        guard let suffix = lastFour(in: raw) else { return [] }
        let brand = network(in: raw ?? "") ?? network(in: method ?? "")
        return cards.filter { $0.last_four == suffix && (brand == nil || $0.network == nil || $0.network == brand) }
    }
    static func normalized(_ raw: String?, method: String?, cards: [ProfileCard]) -> String? {
        guard let suffix = lastFour(in: raw) else { return nil }
        let candidates = matches(raw, method: method, cards: cards)
        let brand = network(in: raw ?? "") ?? network(in: method ?? "")
            ?? (candidates.count == 1 ? candidates.first?.network : nil)
        return format(lastFour: suffix, network: brand)
    }
    static func display(_ raw: String?, method: String?, cards: [ProfileCard]) -> String? {
        let candidates = matches(raw, method: method, cards: cards)
        if candidates.count == 1 { return candidates[0].displayName }
        return normalized(raw, method: method, cards: [])
    }
    /// Offline OCR only considers explicitly masked or card-labelled lines.
    static func fromOCR(_ text: String) -> String? {
        var found = Set<String>()
        let brand = network(in: text)
        for line in text.components(separatedBy: .newlines) {
            guard line.range(of: #"\b(?:terminal|tid|aid|auth|trx|transaction|referenz|ref|beleg|betrag|total)\b"#,
                             options: [.regularExpression, .caseInsensitive]) == nil else { continue }
            guard network(in: line) != nil || line.range(of: #"[*xX•·]{2,}|\b(?:karte|card|pan)\b"#,
                  options: [.regularExpression, .caseInsensitive]) != nil else { continue }
            if let suffix = lastFour(in: line) { found.insert(format(lastFour: suffix, network: network(in: line) ?? brand)) }
        }
        return found.count == 1 ? found.first : nil
    }
}
