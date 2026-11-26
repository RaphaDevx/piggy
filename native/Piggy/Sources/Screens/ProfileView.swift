import SwiftUI
import AuthenticationServices

struct ProfileView: View {
    @EnvironmentObject private var store: PiggyStore
    @EnvironmentObject private var gold: GoldManager
    @AppStorage("piggy.variant") private var variant = "automatic"
    @State private var showingGold = false
    @State private var deleting = false
    @State private var confirmDelete = false
    @State private var confirmSignOut = false
    @State private var busy = false
    @State private var name = ""
    @State private var error: String?
    var body: some View {
        Form {
            Section {
                HStack { Spacer(); MascotView(size: 140); Spacer() }
                BrandHeader()
                Text(store.profile?.name ?? "Dein Profil").font(.headline)
            }
            Section("Konto") {
                TextField("Anzeigename", text: $name).textContentType(.name)
                Button("Name speichern") { run { try await store.updateName(name) } }.disabled(busy || name.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty)
                if let profile = store.profile { LabeledContent("Piggy-Code", value: profile.piggy_code) }
            }
            Section("Zahlungskarten") {
                NavigationLink { PaymentCardsView() } label: { Label("Meine Karten", systemImage: "creditcard") }
            }
            Section("Piggy Gold") {
                Text(gold.isGold ? "Dein Gold-Abo ist aktiv." : "Piggy ohne Werbung und mit den Vorteilen von Gold.")
                Button(gold.isGold ? "Gold verwalten" : "Gold entdecken") { showingGold = true }
                Link("Abonnements bei Apple verwalten", destination: URL(string: "https://apps.apple.com/account/subscriptions")!)
            }
            Section("Dein Piggy") {
                Picker("Darstellung", selection: $variant) {
                    Text("Automatisch nach Jahreszeit").tag("automatic")
                    ForEach(BrandResources.variants) { item in Text(item.name).tag(item.id) }
                }
                Text("Die Darstellung gilt innerhalb der App.").font(.footnote).foregroundStyle(.secondary)
                Button("Einführung erneut ansehen") { NotificationCenter.default.post(name: .init("piggy.showOnboarding"), object: nil) }
            }
            Section("Belegerkennung") {
                Toggle("KI-Erkennung erlauben", isOn: $store.aiConsent)
                Text("Mit deiner Einwilligung wird erkannter Belegtext zur Analyse an den Piggy-Server und Anthropic (USA), bei einem früher hinterlegten eigenen Gemini-Schlüssel an Google (USA), übertragen. Ohne KI bleibt die Texterkennung auf deinem Gerät; die Angaben prüfst und ergänzt du selbst.").font(.footnote).foregroundStyle(.secondary)
            }
            NativeAdPreferences()
            Section("Informationen") {
                if let url = LaunchConfiguration.httpsURL("PRIVACY_URL") { Link("Datenschutz", destination: url) }
                if let url = LaunchConfiguration.httpsURL("TERMS_URL") { Link("Nutzungsbedingungen", destination: url) }
                if let url = LaunchConfiguration.httpsURL("SUPPORT_URL") { Link("Hilfe und Kontakt", destination: url) }
            }
            Section {
                Button("Abmelden", role: .destructive) { confirmSignOut = true }.disabled(busy)
                Button("Konto und Daten löschen", role: .destructive) { deleting = true }.disabled(busy)
            }
            if let error { Section { ScreenError(message: error) } }
        }
        .navigationTitle("Profil")
        .task { name = store.profile?.display_name ?? "" }
        .sheet(isPresented: $showingGold) { GoldView() }
        .sheet(isPresented: $deleting) { DeleteAccountView() }
        .confirmationDialog("Von Piggy abmelden?", isPresented: $confirmSignOut, titleVisibility: .visible) {
            Button("Abmelden", role: .destructive) { Task { await store.signOut() } }
            Button("Abbrechen", role: .cancel) {}
        }
    }
    private func run(_ work: @escaping @MainActor () async throws -> Void) {
        busy = true; error = nil
        Task { do { try await work() } catch { self.error = error.localizedDescription }; busy = false }
    }
}

private struct DeleteAccountView: View {
    @EnvironmentObject private var store: PiggyStore
    @Environment(\.dismiss) private var dismiss
    @State private var confirmed = false
    @State private var busy = false
    @State private var error: String?
    var body: some View {
        NavigationStack {
            Form {
                Section {
                    Text("Konto endgültig löschen").font(.title2.bold())
                    Text("Dein Konto und die damit verbundenen persönlichen Daten werden gelöscht. Diese Aktion kann nicht rückgängig gemacht werden.")
                    Text("Ein Apple-Abonnement verwaltest und kündigst du separat in deinen Apple-Abonnements.")
                    Link("Apple-Abonnements öffnen", destination: URL(string: "https://apps.apple.com/account/subscriptions")!)
                    Toggle("Ich möchte mein Konto endgültig löschen", isOn: $confirmed)
                }
                if store.hasAppleIdentity {
                    Section("Mit Apple bestätigen") {
                        Text("Bestätige deine Apple-Anmeldung, damit Piggy auch die Verknüpfung widerrufen kann.")
                        SignInWithAppleButton(.continue) { request in request.requestedScopes = [] } onCompletion: { result in
                            switch result {
                            case .success(let auth):
                                guard let credential = auth.credential as? ASAuthorizationAppleIDCredential,
                                      let data = credential.authorizationCode, let code = String(data: data, encoding: .utf8) else { error = "Apple hat keine Bestätigung übermittelt."; return }
                                remove(authorizationCode: code)
                            case .failure(let failure): if (failure as? ASAuthorizationError)?.code != .canceled { error = "Die Apple-Bestätigung ist fehlgeschlagen." }
                            }
                        }.frame(height: 48).disabled(!confirmed || busy)
                    }
                } else {
                    Button("Konto endgültig löschen", role: .destructive) { remove(authorizationCode: nil) }.disabled(!confirmed || busy)
                }
                if busy { ProgressView("Konto wird gelöscht …") }
                if let error { ScreenError(message: error) }
            }.navigationTitle("Konto löschen").toolbar { ToolbarItem(placement: .cancellationAction) { Button("Abbrechen") { dismiss() }.disabled(busy) } }
        }.interactiveDismissDisabled(busy)
    }
    private func remove(authorizationCode: String?) {
        busy = true; error = nil
        Task { do { try await store.deleteAccount(authorizationCode: authorizationCode); dismiss() } catch { self.error = error.localizedDescription }; busy = false }
    }
}
