import Foundation

/// Keeps editing text separate from money: a trailing comma is a valid intermediate input.
enum AmountInput {
    static func parse(_ text: String) -> Decimal? {
        let value = text.trimmingCharacters(in: .whitespacesAndNewlines)
        guard value.range(of: #"^-?(?:[0-9]+(?:[.,][0-9]{0,4})?|[.,][0-9]{1,4})$"#,
                          options: .regularExpression) != nil else { return nil }
        return Decimal(string: value.replacingOccurrences(of: ",", with: "."), locale: Locale(identifier: "en_US_POSIX"))
    }
    static func editorText(_ value: Decimal, minimumFractionDigits: Int = 0) -> String {
        guard !value.isNaN else { return "" }
        var result = NSDecimalNumber(decimal: value).stringValue
        let fraction = result.split(separator: ".", omittingEmptySubsequences: false)
        if minimumFractionDigits > 0 {
            if fraction.count == 1 { result += "." + String(repeating: "0", count: minimumFractionDigits) }
            else if fraction[1].count < minimumFractionDigits { result += String(repeating: "0", count: minimumFractionDigits - fraction[1].count) }
        }
        return result
    }
    static func text(_ value: Decimal, locale: Locale = .current) -> String {
        guard !value.isNaN else { return "" }
        return NSDecimalNumber(decimal: value).stringValue.replacingOccurrences(of: ".", with: locale.decimalSeparator ?? ".")
    }
}
