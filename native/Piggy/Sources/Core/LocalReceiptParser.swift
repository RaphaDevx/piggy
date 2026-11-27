import Foundation

/// Conservative offline draft. The user reviews every field before persistence.
enum LocalReceiptParser {
    static func parse(_ text: String) -> ReceiptDraft {
        var draft = ReceiptDraft(); draft.raw_ocr_text = text; draft.receipt_date = ""
        let lines = text.components(separatedBy: .newlines).map { $0.trimmingCharacters(in: .whitespaces) }.filter { !$0.isEmpty }
        draft.store_name = lines.first ?? ""
        if text.range(of: "\\bEUR\\b|€", options: .regularExpression) != nil { draft.currency = "EUR" }
        let amount = try? NSRegularExpression(pattern: "(-?\\d+[.,]\\d{2})\\s*$")
        for line in lines {
            let lower = line.lowercased()
            if lower.range(of: "total|summe|betrag|à payer|gesamt", options: .regularExpression) != nil,
               let match = amount?.firstMatch(in: line, range: NSRange(line.startIndex..., in: line)),
               let range = Range(match.range(at: 1), in: line),
               let number = Decimal(string: line[range].replacingOccurrences(of: ",", with: "."), locale: Locale(identifier: "en_US_POSIX")) {
                draft.total_amount = number
            }
        }
        let regex = try? NSRegularExpression(pattern: "\\b(\\d{2})[./](\\d{2})[./](\\d{4})\\b")
        if let match = regex?.firstMatch(in: text, range: NSRange(text.startIndex..., in: text)) {
            let parts = (1...3).compactMap { Range(match.range(at: $0), in: text).map { String(text[$0]) } }
            if parts.count == 3 { draft.receipt_date = "\(parts[2])-\(parts[1])-\(parts[0])" }
        }
        draft.extra_fields = ReceiptMetadata.enriched(nil, raw: text)
        draft.payment_card = PaymentCard.fromOCR(text)
        if let card = draft.payment_card { draft.payment_method = PaymentCard.network(in: card) ?? "Karte" }
        // Do not invent article details from layout-ambiguous OCR lines.
        return draft
    }
}
