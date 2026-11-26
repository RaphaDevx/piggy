import Foundation

/// Printed wall-clock times, never inferred from payment time or device location.
struct ParkingDetails: Hashable {
    var arrival = ""
    var departure = ""
    var location = ""
    var plate = ""
    var printedMinutes = ""
    static let labels = [("parking_arrival", "Ankunft"), ("parking_departure", "Abfahrt"),
                         ("parking_location", "Parkplatz"), ("parking_plate", "Kennzeichen"),
                         ("parking_duration_minutes", "Gedruckte Dauer (Minuten)")]
    var hasDetails: Bool { !arrival.isEmpty || !departure.isEmpty || !location.isEmpty || !plate.isEmpty || !printedMinutes.isEmpty }
    static func formatter(_ pattern: String) -> DateFormatter {
        let formatter = DateFormatter()
        formatter.locale = Locale(identifier: "en_US_POSIX")
        formatter.calendar = Calendar(identifier: .gregorian)
        formatter.timeZone = TimeZone(secondsFromGMT: 0)
        formatter.dateFormat = pattern; formatter.isLenient = false
        return formatter
    }
    static func date(_ text: String) -> Date? {
        let formatter = formatter("yyyy-MM-dd HH:mm")
        guard let date = formatter.date(from: text), formatter.string(from: date) == text else { return nil }
        return date
    }
    var minutes: Decimal? {
        if let printed = AmountInput.parse(printedMinutes), printed > 0, printed <= 525_600 { return printed }
        guard let start = Self.date(arrival), let end = Self.date(departure) else { return nil }
        let duration = end.timeIntervalSince(start) / 60
        guard duration > 0, duration <= 525_600 else { return nil }
        return Decimal(Int(duration))
    }
    func perMinute(total: Decimal?) -> Decimal? {
        guard let total, !total.isNaN, total >= 0, let minutes, minutes > 0 else { return nil }
        return total / minutes
    }
    func applying(to extras: [String: String?]?) -> [String: String?] {
        var values = extras ?? [:]
        for (key, value) in [("parking_arrival", arrival), ("parking_departure", departure),
                             ("parking_location", location), ("parking_plate", plate), ("parking_duration_minutes", printedMinutes)] {
            values[key] = .some(value.isEmpty ? nil : value)
        }
        return values
    }
    static func read(extras: [String: String?]?, raw: String?, receiptDate: String?) -> ParkingDetails {
        var parsed = parse(raw ?? "", receiptDate: receiptDate)
        let fields = extras ?? [:]
        // Explicit null means a user cleared the detected value; do not resurrect it from OCR.
        if let value = fields["parking_arrival"] { parsed.arrival = value ?? "" }
        if let value = fields["parking_departure"] { parsed.departure = value ?? "" }
        if let value = fields["parking_location"] { parsed.location = value ?? "" }
        if let value = fields["parking_plate"] { parsed.plate = value ?? "" }
        if let value = fields["parking_duration_minutes"] { parsed.printedMinutes = value ?? "" }
        return parsed
    }
    static func parse(_ raw: String, receiptDate: String?) -> ParkingDetails {
        guard raw.range(of: #"park(?:ing|haus|platz|zeit|schein|ticket|geb.hr)|stationnement|parcheggio|einfahrt|ausfahrt"#,
                        options: [.regularExpression, .caseInsensitive]) != nil else { return ParkingDetails() }
        var result = ParkingDetails()
        func match(_ pattern: String, in text: String, group: Int = 1) -> String? {
            guard let regex = try? NSRegularExpression(pattern: pattern, options: .caseInsensitive),
                  let found = regex.firstMatch(in: text, range: NSRange(text.startIndex..., in: text)),
                  let range = Range(found.range(at: group), in: text) else { return nil }
            return String(text[range])
        }
        func timestamp(_ line: String) -> String? {
            guard let time = match(#"\b([0-2][0-9]:[0-5][0-9])\b"#, in: line),
                  let hour = Int(time.prefix(2)), hour < 24 else { return nil }
            var day = receiptDate
            if let iso = match(#"\b(\d{4}-\d{2}-\d{2})\b"#, in: line) { day = iso }
            else if let printed = match(#"\b(\d{2}\.\d{2}\.\d{4})\b"#, in: line), let date = formatter("dd.MM.yyyy").date(from: printed) {
                day = formatter("yyyy-MM-dd").string(from: date)
            }
            guard let day, Self.date(day + " " + time) != nil else { return nil }
            return day + " " + time
        }
        for line in raw.components(separatedBy: .newlines) {
            if line.range(of: #"ankunft|einfahrt|einfahrtszeit|parkbeginn|beginn|entr.e|arrival|entry"#, options: [.regularExpression, .caseInsensitive]) != nil,
               let value = timestamp(line) { result.arrival = value }
            if line.range(of: #"abfahrt|ausfahrt|ausfahrtszeit|parkende|ende|sortie|departure|exit"#, options: [.regularExpression, .caseInsensitive]) != nil,
               let value = timestamp(line) { result.departure = value }
            if let value = match(#"(?:kennzeichen|kontrollschild|number plate|plaque)\s*[:=]\s*(.+)$"#, in: line) { result.plate = value }
            if let value = match(#"(?:parkhaus|parkplatz|parking)\s*[:=]\s*(.+)$"#, in: line) { result.location = value }
            if let value = match(#"(?:parkdauer|dauer|duration)\s*[:=]?\s*([0-9]+)\s*(?:min(?:uten)?|minutes)\b"#, in: line) { result.printedMinutes = value }
        }
        // If dates are absent, end-before-start is ambiguous; never invent an overnight stay.
        return result
    }
}

enum ReceiptExtras {
    static let labels = [("cashier_number", "Kassen-/Kassierernummer"), ("receipt_number", "Bonnummer"),
                         ("vat_number", "MwSt-/UID-Nummer"), ("store_address", "Adresse"),
                         ("store_email", "E-Mail"), ("store_phone", "Telefon"), ("store_website", "Website")]
}
