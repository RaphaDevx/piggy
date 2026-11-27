import Foundation

struct MerchantBrand: Codable, Identifiable {
    var id: String
    var name: String
    var information: String
    var logo: Data?
    var catalog: String
    static func catalogURL(_ raw: String) -> URL? {
        guard let url = URL(string: raw.trimmingCharacters(in: .whitespacesAndNewlines)),
              url.scheme?.lowercased() == "https", let host = url.host, !host.isEmpty,
              url.user == nil, url.password == nil else { return nil }
        return url
    }
}
struct SavedCoupon: Codable, Identifiable {
    var id = UUID()
    var title: String
    var merchant: String
    var code: String
    var symbology: String
    var expiry: String?
    var note: String
    var image: Data?
    var document: Data? = nil
    var used = false
    var createdAt = Date()
    func expired(on date: Date = Date(), calendar: Calendar = .current) -> Bool {
        guard let expiry else { return false }
        let parts = expiry.split(separator: "-").compactMap { Int($0) }
        guard parts.count == 3, let day = calendar.date(from: DateComponents(year: parts[0], month: parts[1], day: parts[2])),
              let end = calendar.date(byAdding: .day, value: 1, to: day) else { return false }
        return date >= end
    }
}
