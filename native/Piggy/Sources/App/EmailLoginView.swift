import SwiftUI

struct EmailLoginView: View {
    @EnvironmentObject private var store: PiggyStore
    @Environment(\.dismiss) private var dismiss
    @State private var email = ""
    @State private var password = ""
    @State private var busy = false
    @State private var message: String?
    @FocusState private var focused: Field?
    private enum Field: Hashable { case email, password }
    var body: some View {
        NavigationStack {
            Form {
                Section {
                    Text("Melde dich mit der E-Mail-Adresse und dem Passwort deines bestehenden Piggy-Kontos an.")
                    TextField("E-Mail-Adresse", text: $email)
                        .keyboardType(.emailAddress).textContentType(.username)
                        .textInputAutocapitalization(.never).autocorrectionDisabled()
                        .focused($focused, equals: .email).submitLabel(.next)
                        .onSubmit { focused = .password }.accessibilityIdentifier("login.email")
                    SecureField("Passwort", text: $password).textContentType(.password)
                        .focused($focused, equals: .password).submitLabel(.go)
                        .onSubmit(signIn).accessibilityIdentifier("login.password")
                }
                if let message { Section { ScreenError(message: message) } }
                Section {
                    BusyButton(title: "Anmelden", busy: busy, disabled: email.isEmpty || password.isEmpty || !store.configured, action: signIn)
                        .accessibilityIdentifier("login.submit")
                    NavigationLink("Passwort vergessen?") { PasswordResetRequestView(initialEmail: email) }.disabled(busy)
                }
            }
            .navigationTitle("Mit E-Mail anmelden")
            .toolbar { ToolbarItem(placement: .cancellationAction) { Button("Schliessen") { dismiss() }.disabled(busy) } }
            .interactiveDismissDisabled(busy)
        }.onDisappear { password = "" }
    }
    private func signIn() {
        guard !busy, !password.isEmpty else { return }
        do { _ = try NativeAuthPolicy.email(email) }
        catch { message = error.localizedDescription; return }
        busy = true; message = nil; focused = nil
        Task {
            defer { busy = false; password = "" }
            do { try await store.signInEmail(email: email, password: password); dismiss() }
            catch { message = "Die Anmeldung ist fehlgeschlagen. Prüfe E-Mail und Passwort oder setze dein Passwort zurück." }
        }
    }
}

private struct PasswordResetRequestView: View {
    @EnvironmentObject private var store: PiggyStore
    @State private var email: String
    @State private var busy = false
    @State private var sent = false
    @State private var message: String?
    init(initialEmail: String) { _email = State(initialValue: initialEmail) }
    var body: some View {
        Form {
            Section {
                Text("Du erhältst einen Link, mit dem du ein neues Passwort festlegen kannst. Öffne ihn auf diesem iPhone mit der aktuellen Piggy-App.")
                TextField("E-Mail-Adresse", text: $email).keyboardType(.emailAddress)
                    .textContentType(.emailAddress).textInputAutocapitalization(.never).autocorrectionDisabled()
            }
            if sent {
                Section { Label("Anfrage gesendet", systemImage: "envelope"); Text("Falls für diese Adresse ein Konto besteht, erhältst du eine E-Mail. Prüfe auch den Spam-Ordner.") }
            } else {
                Section { BusyButton(title: "Link anfordern", busy: busy, disabled: email.isEmpty || !store.configured) { request() } }
            }
            if let message { Section { ScreenError(message: message) } }
        }.navigationTitle("Passwort zurücksetzen")
    }
    private func request() {
        guard !busy else { return }
        do { _ = try NativeAuthPolicy.email(email) }
        catch { message = error.localizedDescription; return }
        busy = true; message = nil
        Task {
            defer { busy = false }
            do { try await store.requestPasswordReset(email: email); sent = true }
            catch { message = "Die Anfrage konnte nicht gesendet werden. Bitte warte kurz und versuche es erneut." }
        }
    }
}

struct RecoveredPasswordView: View {
    @EnvironmentObject private var store: PiggyStore
    @State private var password = ""
    @State private var confirmation = ""
    @State private var busy = false
    @State private var message: String?
    var body: some View {
        NavigationStack {
            Form {
                Section {
                    Text("Wähle ein neues Passwort mit mindestens 8 Zeichen.")
                    SecureField("Neues Passwort", text: $password).textContentType(.newPassword)
                    SecureField("Passwort wiederholen", text: $confirmation).textContentType(.newPassword)
                }
                if let message { Section { ScreenError(message: message) } }
                Section {
                    BusyButton(title: "Passwort speichern", busy: busy, disabled: password.count < 8 || password != confirmation) {
                        guard !busy else { return }; busy = true; message = nil
                        Task {
                            defer { busy = false; password = ""; confirmation = "" }
                            do { try await store.setRecoveredPassword(password) }
                            catch { message = "Das Passwort konnte nicht gespeichert werden. Bitte prüfe die Angaben und versuche es erneut." }
                        }
                    }
                    Button("Abbrechen und abmelden", role: .cancel) {
                        guard !busy else { return }; busy = true
                        Task {
                            defer { busy = false; password = ""; confirmation = "" }
                            do { try await store.cancelPasswordRecovery() }
                            catch { message = "Die Abmeldung konnte nicht abgeschlossen werden. Bitte versuche es erneut." }
                        }
                    }.disabled(busy)
                }
            }.navigationTitle("Neues Passwort")
        }
    }
}
