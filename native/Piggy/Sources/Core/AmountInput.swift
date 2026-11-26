import Foundation

/// Keeps editing text separate from money: a trailing comma is a valid intermediate input.
enum AmountInput {
    static func parse(_ text: String) -> Decimal? {
        let value = text.trimmingCharacters(in: .whitespacesAndNewlines)
        guard value.range(of: #"^-?(?:[0-9]+(?:[.,][0-9]{0,4})?|[.,][0-9]{1,4})$"#,
                          options: .regularExpression) != nil else { return nil }
        return Decimal(string: value.replacingOccurrences(of: ",", with: "."), locale: Locale(identifier: "en_US_POSIX"))
    }
    static func text(_ value: Decimal, locale: Locale = .current) -> String {
        guard !value.isNaN else { return "" }
        return NSDecimalNumber(decimal: value).stringValue.replacingOccurrences(of: ".", with: locale.decimalSeparator ?? ".")
    }
}
