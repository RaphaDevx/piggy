import Foundation

/// Supplement structured fields only from explicitly labelled text; preserve the entire
/// original OCR separately. Existing values (including deliberately cleared nulls) win.
enum ReceiptMetadata {
    struct Values: Decodable {
        var fields: [String: String?]
        init(from decoder: Decoder) throws {
            let values = try [String: JSONValue](from: decoder)
            fields = try values.mapValues { value in
                switch value {
                case .null: return nil
                case .string(let text): return text
                case .number(let number): return NSDecimalNumber(decimal: number).stringValue
                case .bool(let flag): return flag ? "true" : "false"
                case .array, .object:
                    let encoder = JSONEncoder(); encoder.outputFormatting = [.sortedKeys]
                    return String(data: try encoder.encode(value), encoding: .utf8)
                }
            }
        }
    }
    struct Detail: Identifiable, Hashable {
        var key: String
        var title: String
        var value: String
        var id: String { key }
    }
    static func enriched(_ existing: [String: String?]?, raw: String?) -> [String: String?] {
        var fields = existing ?? [:]
        func put(_ key: String, _ value: String?) {
            guard !fields.keys.contains(key), let value else { return }
            let clean = value.trimmingCharacters(in: .whitespacesAndNewlines)
            if !clean.isEmpty { fields[key] = clean }
        }
        func match(_ pattern: String, _ line: String) -> String? {
            guard let regex = try? NSRegularExpression(pattern: pattern, options: .caseInsensitive),
                  let result = regex.firstMatch(in: line, range: NSRange(line.startIndex..., in: line)),
                  let range = Range(result.range(at: 1), in: line) else { return nil }
            return String(line[range])
        }
        let patterns = [
            ("register_number", #"^\s*(?:kasse|caisse|register)(?:\s*[- ]?(?:nr|no|nummer)\.?)?\s*(?:[:#=]\s*|\s+)(.+)$"#),
            ("receipt_number", #"^\s*(?:bon|beleg|quittung|receipt)(?:\s*[- ]?(?:nr|no|nummer)\.?)\s*[:#=]?\s*(.+)$"#),
            ("store_branch", #"^\s*(?:filiale|zweigstelle|branch|standort)\s*(?:[:#=]\s*|\s+)(.+)$"#),
            ("store_address", #"^\s*(?:adresse|address|anschrift)\s*[:=]\s*(.+)$"#),
            ("vat_number", #"^\s*(?:uid|mwst[- ]?(?:nr|nummer|id)\.?|ust[- ]?id\.?|tva[- ]?(?:nr|no)|vat[- ]?(?:no|id))\s*(?:[:#=]\s*|\s+)(\S.*)$"#),
            ("vat_number", #"\b(CHE[- ]?[0-9]{3}\.[0-9]{3}\.[0-9]{3}(?:\s+MWST)?)\b"#),
            ("store_phone", #"^\s*(?:tel(?:efon)?\.?|phone)\s*[:=]?\s+(\+?[0-9][0-9 ()/.-]+)$"#),
            ("store_email", #"^\s*(?:e-?mail)\s*[:=]?\s+(\S+@\S+)$"#),
            ("store_website", #"^\s*(?:website|web|internet)\s*[:=]?\s+(\S+)$"#),
            ("payment_terminal", #"^\s*(?:terminal(?:[- ]?id)?|tid)\s*(?:[:#=]\s*|\s+)(.+)$"#),
            ("payment_reference", #"^\s*(?:transaktions?[- ]?(?:nr|id)|transaction[- ]?(?:id|no)|referenz|reference)\s*(?:[:#=]\s*|\s+)(.+)$"#),
            ("authorization_code", #"^\s*(?:autorisierung|authorization|auth(?:\.?[- ]?code)?)\s*(?:[:#=]\s*|\s+)(.+)$"#)
        ]
        for line in (raw ?? "").components(separatedBy: .newlines) {
            var recognized = false
            if let cashier = match(#"^\s*(?:kassierer(?:in)?|bediener(?:in)?|bedienung|cashier|bedient von)(?:\s*[- ]?(?:nr|no|nummer)\.?)?\s*(?:[:#=]\s*|\s+)(.+)$"#, line) {
                recognized = true
                if cashier.range(of: #"^[0-9]+$"#, options: .regularExpression) != nil { put("cashier_number", cashier) }
                else if let number = match(#"^([0-9]+)\s+\p{L}"#, cashier),
                        let name = match(#"^[0-9]+\s+(\p{L}.*)$"#, cashier) {
                    put("cashier_number", number); put("cashier_name", name)
                } else { put("cashier_name", cashier) }
            }
            for (key, pattern) in patterns {
                if let value = match(pattern, line) { recognized = true; put(key, value) }
            }
            // Preserve other printed key/value lines as supplementary details. The complete
            // text (including lines with no label) stays in raw_ocr_text without truncation.
            if !recognized, let colon = line.firstIndex(of: ":") {
                let key = line[..<colon].trimmingCharacters(in: .whitespaces)
                let value = line[line.index(after: colon)...].trimmingCharacters(in: .whitespaces)
                if key.count >= 2 && key.count <= 60 && key.contains(where: { $0.isLetter }) && !value.isEmpty {
                    let prefix = "printed:" + key
                    let alreadyPresent = fields.contains { entry in
                        (entry.key == prefix || entry.key.hasPrefix(prefix + " (")) && entry.value == value
                    }
                    if !alreadyPresent {
                        var unique = prefix; var suffix = 2
                        while fields.keys.contains(unique) { unique = prefix + " (\(suffix))"; suffix += 1 }
                        put(unique, value)
                    }
                }
            }
        }
        return fields
    }
    static func details(_ existing: [String: String?]?, raw: String?) -> [Detail] {
        let fields = enriched(existing, raw: raw)
        let labels = Dictionary(uniqueKeysWithValues: ReceiptExtras.labels)
        return fields.compactMap { key, wrapped in
            guard let value = wrapped, !value.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty,
                  !key.hasPrefix("parking_") else { return nil }
            let title = labels[key] ?? (key.hasPrefix("printed:") ? String(key.dropFirst(8)) : key.replacingOccurrences(of: "_", with: " "))
            return Detail(key: key, title: title, value: value)
        }.sorted { a, b in a.title.localizedStandardCompare(b.title) == .orderedAscending }
    }
}

enum ReceiptItemPresentation {
    static func quantity(_ amount: Decimal?, unit: String?) -> String {
        let unit = unit?.trimmingCharacters(in: .whitespacesAndNewlines) ?? ""
        guard let amount, !amount.isNaN else { return unit.isEmpty ? "–" : "– \(unit)" }
        return AmountInput.editorText(amount) + (unit.isEmpty ? "" : " " + unit)
    }
}
