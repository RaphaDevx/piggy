import SwiftUI
import AuthenticationServices
import CryptoKit
import Security

struct LoginView: View {
    @EnvironmentObject private var store: PiggyStore
    @State private var nonce = ""
    @State private var busy = false
    @State private var error: String?
    #if DEBUG && targetEnvironment(simulator)
    @State private var testEmail = ""
    @State private var testPassword = ""
    @State private var showTestLogin = false
    #endif
    var body: some View {
        ScrollView {
            VStack(spacing: 24) {
                MascotView(pose: "idle-happy", size: 220)
                Text("Willkommen bei Piggy").font(.largeTitle.bold()).multilineTextAlignment(.center)
                Text("Deine Belege und gemeinsamen Ausgaben an einem Ort.").font(.body).foregroundStyle(.secondary).multilineTextAlignment(.center)
                SignInWithAppleButton(.continue) { request in
                    nonce = AppleNonce.make()
                    request.requestedScopes = [.fullName, .email]
                    request.nonce = AppleNonce.sha256(nonce)
                } onCompletion: { result in
                    guard !busy else { return }
                    switch result {
                    case .success(let authorization):
                        guard let credential = authorization.credential as? ASAuthorizationAppleIDCredential,
                              let data = credential.identityToken, let token = String(data: data, encoding: .utf8) else {
                            error = "Apple konnte die Anmeldung nicht bestätigen."; return
                        }
                        busy = true
                        Task {
                            do { try await store.signInApple(idToken: token, nonce: nonce) }
                            catch { self.error = "Die Anmeldung ist fehlgeschlagen. Bitte versuche es erneut." }
                            busy = false
                        }
                    case .failure(let failure):
                        if (failure as? ASAuthorizationError)?.code != .canceled { error = "Die Anmeldung ist gerade nicht möglich." }
                    }
                }.signInWithAppleButtonStyle(.black).frame(height: 52).clipShape(RoundedRectangle(cornerRadius: 12))
                    .disabled(busy || !store.configured)
                Button {
                    busy = true
                    Task { do { try await store.signInGoogle() } catch { self.error = "Die Google-Anmeldung konnte nicht abgeschlossen werden." }; busy = false }
                } label: { Text("Mit Google fortfahren").fontWeight(.semibold).frame(maxWidth: .infinity).padding(16).background(Theme.surface, in: RoundedRectangle(cornerRadius: 12)) }
                    .disabled(busy || !store.configured)
                #if DEBUG && targetEnvironment(simulator)
                DisclosureGroup("Simulator-Testkonto", isExpanded: $showTestLogin) {
                    TextField("Test-E-Mail", text: $testEmail)
                        .textInputAutocapitalization(.never).autocorrectionDisabled()
                        .keyboardType(.emailAddress).accessibilityIdentifier("capture.email")
                    SecureField("Test-Passwort", text: $testPassword)
                        .accessibilityIdentifier("capture.password")
                    Button("Testkonto anmelden") {
                        busy = true
                        Task {
                            defer { busy = false; testPassword = "" }
                            do { try await store.signInSimulatorTest(email: testEmail, password: testPassword) }
                            catch { self.error = "Testanmeldung fehlgeschlagen." }
                        }
                    }.disabled(busy || testEmail.isEmpty || testPassword.isEmpty || !store.configured)
                }.font(.footnote)
                #endif
                if busy { ProgressView("Anmeldung wird geprüft") }
                if !store.configured { Text("Piggy ist hier noch nicht verfügbar. Bitte versuche es mit einer freigegebenen App-Version.").font(.footnote).foregroundStyle(.secondary) }
                if let error { Text(error).foregroundStyle(.red).accessibilityAddTraits(.updatesFrequently) }
                HStack {
                    if let url = LaunchConfiguration.httpsURL("PRIVACY_URL") { Link("Datenschutz", destination: url) }
                    if let url = LaunchConfiguration.httpsURL("TERMS_URL") { Link("Nutzungsbedingungen", destination: url) }
                }.font(.footnote)
            }.padding(28).frame(maxWidth: 500).frame(maxWidth: .infinity)
        }.background(Theme.background)
    }
}

enum AppleNonce {
    static func make() -> String {
        var bytes = [UInt8](repeating: 0, count: 32)
        guard SecRandomCopyBytes(kSecRandomDefault, bytes.count, &bytes) == errSecSuccess else { return UUID().uuidString + UUID().uuidString }
        return bytes.map { String(format: "%02x", $0) }.joined()
    }
    static func sha256(_ string: String) -> String { SHA256.hash(data: Data(string.utf8)).map { String(format: "%02x", $0) }.joined() }
}
