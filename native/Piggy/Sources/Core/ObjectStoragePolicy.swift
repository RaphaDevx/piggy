import Foundation

/// The fallback is read-only. New files always use R2 unless explicitly rolled back.
enum ObjectStorageBackend: String {
    case supabase, r2
    case r2WithFallback = "r2-with-fallback"
    init(configuration: String) { self = Self(rawValue: configuration) ?? .supabase }
    var usesR2: Bool { self != .supabase }
    var allowsLegacyRead: Bool { self == .r2WithFallback }
}

enum ObjectStoragePolicy {
    static let buckets: Set<String> = ["receipts", "receipt-images", "bank-statements"]
    static func validate(bucket: String, path: String, userID: UUID) throws {
        let segments = path.split(separator: "/", omittingEmptySubsequences: false)
        guard buckets.contains(bucket), path.count <= 512,
              segments.count > 1, segments.first == Substring(userID.uuidString.lowercased()),
              !segments.contains(where: { $0.isEmpty || $0 == "." || $0 == ".." }),
              !path.unicodeScalars.contains(where: { CharacterSet.controlCharacters.contains($0) || $0 == "\\" }) else {
            throw PiggyFailure.validation("Ungültiger Dateipfad.")
        }
    }
    static func signedURL(_ raw: String) throws -> URL {
        guard let url = URL(string: raw), url.scheme == "https", url.user == nil, url.password == nil,
              url.port == nil, let host = url.host, host.hasSuffix(".r2.cloudflarestorage.com") else {
            throw PiggyFailure.service("Der Dateispeicher hat keine gültige Adresse geliefert.")
        }
        return url
    }
}
