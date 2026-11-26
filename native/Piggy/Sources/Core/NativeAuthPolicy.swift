import Foundation

enum NativeAuthPolicy {
    // Public iOS OAuth client already used by the previous Piggy iPhone app.
    static let googleClientID = "844663909104-0gbgnks89b3ggosos53qfu4f4e7tp4mr.apps.googleusercontent.com"
    static let googleScheme = "com.googleusercontent.apps.844663909104-0gbgnks89b3ggosos53qfu4f4e7tp4mr"
    static let googleRedirect = googleScheme + ":/oauthredirect"
    static let recoveryRedirect = URL(string: "piggy://auth/reset-password")!

    static func email(_ raw: String) throws -> String {
        let value = raw.trimmingCharacters(in: .whitespacesAndNewlines)
        let parts = value.split(separator: "@", omittingEmptySubsequences: false)
        guard value.count <= 254, parts.count == 2, !parts[0].isEmpty,
              parts[1].contains("."), !parts[1].hasPrefix("."), !parts[1].hasSuffix("."),
              !value.contains(where: { $0.isWhitespace || $0.isNewline }) else {
            throw PiggyFailure.validation("Bitte gib eine gültige E-Mail-Adresse ein.")
        }
        return value
    }
    static func googleURL(state: String, nonceHash: String, challenge: String) -> URL {
        var url = URLComponents(string: "https://accounts.google.com/o/oauth2/v2/auth")!
        url.queryItems = [
            .init(name: "client_id", value: googleClientID), .init(name: "redirect_uri", value: googleRedirect),
            .init(name: "response_type", value: "code"), .init(name: "scope", value: "openid email profile"),
            .init(name: "state", value: state), .init(name: "nonce", value: nonceHash),
            .init(name: "code_challenge", value: challenge), .init(name: "code_challenge_method", value: "S256")
        ]
        return url.url!
    }
    static func googleCode(from callback: URL, expectedState: String) throws -> String {
        guard callback.scheme == googleScheme, callback.host == nil, callback.path == "/oauthredirect",
              callback.user == nil, callback.password == nil,
              let components = URLComponents(url: callback, resolvingAgainstBaseURL: false) else {
            throw PiggyFailure.validation("Die Google-Antwort konnte nicht bestätigt werden.")
        }
        let query = components.queryItems ?? []
        guard query.filter({ $0.name == "state" }).count == 1,
              query.first(where: { $0.name == "state" })?.value == expectedState else {
            throw PiggyFailure.validation("Die Google-Anmeldung ist abgelaufen. Bitte starte sie erneut.")
        }
        if query.first(where: { $0.name == "error" })?.value == "access_denied" { throw CancellationError() }
        guard !query.contains(where: { $0.name == "error" }), query.filter({ $0.name == "code" }).count == 1,
              let code = query.first(where: { $0.name == "code" })?.value, !code.isEmpty else {
            throw PiggyFailure.validation("Google konnte die Anmeldung nicht abschliessen.")
        }
        return code
    }
    static func isRecoveryCallback(_ url: URL) -> Bool {
        url.scheme == "piggy" && url.host == "auth" && url.path == "/reset-password" && url.user == nil && url.password == nil && url.port == nil
    }
    static func form(_ values: [String: String]) -> Data {
        let allowed = CharacterSet(charactersIn: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._~")
        let body = values.sorted { $0.key < $1.key }.map {
            "\($0.key.addingPercentEncoding(withAllowedCharacters: allowed)!)=\($0.value.addingPercentEncoding(withAllowedCharacters: allowed)!)"
        }.joined(separator: "&")
        return Data(body.utf8)
    }
}
