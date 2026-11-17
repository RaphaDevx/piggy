import Foundation

enum FinancePeriod: String, CaseIterable, Identifiable {
    case week = "Woche", month = "Monat", quarter = "Quartal", year = "Jahr"
    var id: String { rawValue }
    func range(containing date: Date) -> FinanceDateRange {
        var calendar = Calendar(identifier: .iso8601)
        calendar.timeZone = TimeZone(identifier: "Europe/Zurich")!
        let component: Calendar.Component = self == .week ? .weekOfYear : self == .year ? .year : .month
        let interval = calendar.dateInterval(of: component, for: date)!
        var start = interval.start
        var end = interval.end
        if self == .quarter {
            let month = calendar.component(.month, from: date)
            start = calendar.date(byAdding: .month, value: -((month - 1) % 3), to: start)!
            end = calendar.date(byAdding: .month, value: 3, to: start)!
        }
        let format = DateFormatter()
        format.locale = Locale(identifier: "en_US_POSIX"); format.timeZone = calendar.timeZone; format.dateFormat = "yyyy-MM-dd"
        return FinanceDateRange(start: format.string(from: start), endExclusive: format.string(from: end))
    }
}
struct FinanceDateRange: Hashable {
    let start: String
    let endExclusive: String
    var id: String { "\(start)/\(endExclusive)" }
}
