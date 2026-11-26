import Foundation
import AuthenticationServices
import CryptoKit
import Security
import UIKit

/// Native public-client OAuth: PKCE, state, hashed nonce; no Google client secret.
@MainActor
enum NativeGoogleSignIn {
    struct Result { let idToken: String; let nonce: String }
    static func signIn() async throws -> Result {
        let verifier = try randomValue()
        let nonce = try randomValue()
        let state = try randomValue()
        let challenge = Data(SHA256.hash(data: Data(verifier.utf8))).base64EncodedString()
            .replacingOccurrences(of: "+", with: "-").replacingOccurrences(of: "/", with: "_").replacingOccurrences(of: "=", with: "")
        let nonceHash = SHA256.hash(data: Data(nonce.utf8)).map { String(format: "%02x", $0) }.joined()
        let url = NativeAuthPolicy.googleURL(state: state, nonceHash: nonceHash, challenge: challenge)
        guard let window = UIApplication.shared.connectedScenes.compactMap({ $0 as? UIWindowScene })
            .filter({ $0.activationState == .foregroundActive }).flatMap(\.windows).first(where: \.isKeyWindow) else {
            throw PiggyFailure.service("Die Anmeldung ist gerade nicht verfügbar. Bitte öffne Piggy erneut.")
        }
        let browser = NativeOAuthBrowser(window: window)
        let callback = try await browser.open(url)
        let code = try NativeAuthPolicy.googleCode(from: callback, expectedState: state)
        var request = URLRequest(url: URL(string: "https://oauth2.googleapis.com/token")!)
        request.httpMethod = "POST"; request.timeoutInterval = 30
        request.setValue("application/x-www-form-urlencoded", forHTTPHeaderField: "Content-Type")
        request.httpBody = NativeAuthPolicy.form([
            "client_id": NativeAuthPolicy.googleClientID, "redirect_uri": NativeAuthPolicy.googleRedirect,
            "grant_type": "authorization_code", "code": code, "code_verifier": verifier
        ])
        struct Tokens: Decodable { let id_token: String? }
        do {
            let (data, response) = try await URLSession.shared.data(for: request)
            guard let http = response as? HTTPURLResponse, (200...299).contains(http.statusCode),
                  let token = try JSONDecoder().decode(Tokens.self, from: data).id_token, !token.isEmpty else {
                throw PiggyFailure.service("Google konnte die Anmeldung nicht bestätigen. Bitte versuche es erneut.")
            }
            return Result(idToken: token, nonce: nonce)
        } catch is CancellationError { throw CancellationError() }
        catch { throw PiggyFailure.service("Google konnte die Anmeldung nicht bestätigen. Bitte prüfe deine Internetverbindung und versuche es erneut.") }
    }
    private static func randomValue() throws -> String {
        var bytes = [UInt8](repeating: 0, count: 32)
        guard SecRandomCopyBytes(kSecRandomDefault, bytes.count, &bytes) == errSecSuccess else {
            throw PiggyFailure.service("Die sichere Anmeldung konnte nicht gestartet werden.")
        }
        return Data(bytes).base64EncodedString().replacingOccurrences(of: "+", with: "-")
            .replacingOccurrences(of: "/", with: "_").replacingOccurrences(of: "=", with: "")
    }
}

@MainActor
private final class NativeOAuthBrowser: NSObject, ASWebAuthenticationPresentationContextProviding {
    private let window: UIWindow
    private var session: ASWebAuthenticationSession?
    init(window: UIWindow) { self.window = window }
    func presentationAnchor(for session: ASWebAuthenticationSession) -> ASPresentationAnchor { window }
    func open(_ url: URL) async throws -> URL {
        try await withCheckedThrowingContinuation { continuation in
            let session = ASWebAuthenticationSession(url: url, callbackURLScheme: NativeAuthPolicy.googleScheme) { [weak self] callback, error in
                Task { @MainActor in
                    self?.session = nil
                    if let failure = error as? ASWebAuthenticationSessionError, failure.code == .canceledLogin {
                        continuation.resume(throwing: CancellationError())
                    } else if error != nil {
                        continuation.resume(throwing: PiggyFailure.service("Das Google-Anmeldefenster konnte nicht geöffnet werden."))
                    } else if let callback { continuation.resume(returning: callback) }
                    else { continuation.resume(throwing: PiggyFailure.service("Google hat keine Anmeldebestätigung zurückgegeben.")) }
                }
            }
            session.presentationContextProvider = self
            self.session = session
            if !session.start() {
                self.session = nil
                continuation.resume(throwing: PiggyFailure.service("Das Google-Anmeldefenster konnte nicht geöffnet werden."))
            }
        }
    }
}
