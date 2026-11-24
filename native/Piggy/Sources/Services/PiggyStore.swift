import Foundation
import Combine
import UIKit
import Supabase

@MainActor
final class PiggyStore: ObservableObject {
    @Published var userID: UUID?
    @Published var receipts: [Receipt] = []
    @Published var friends: [Friend] = []
    @Published var requests: [Friend] = []
    @Published var projects: [Project] = []
    @Published var splits: [ExpenseSplit] = []
    @Published var profile: Profile?
    @Published var finance: NativeFinanceSummary?
    @Published var bank = BankSnapshot()
    @Published var busy = false
    @Published var errorMessage: String?
    @Published private(set) var hasAppleIdentity = false
    @Published var aiConsent = false {
        didSet {
            guard !settingConsent, oldValue != aiConsent, let uid = userID else { return }
            UserDefaults.standard.set(aiConsent, forKey: "piggy.aiConsent.\(uid)")
            let enabled = aiConsent
            let previous = consentTask
            consentTask = Task {
                await previous?.value
                guard self.userID == uid else { return }
                do { try await persistConsent(enabled, user: uid) }
                catch { if self.userID == uid { errorMessage = error.localizedDescription } }
            }
        }
    }
    let client: SupabaseClient?
    var configured: Bool { client != nil }
    private var authTask: Task<Void, Never>?
    private var consentTask: Task<Void, Never>?
    private var settingConsent = false
    private var reloadGeneration = UUID()

    init() {
        if let raw = Bundle.main.object(forInfoDictionaryKey: "SUPABASE_URL") as? String,
           let url = URL(string: raw), url.scheme == "https", !raw.contains("$("),
           let key = Bundle.main.object(forInfoDictionaryKey: "SUPABASE_ANON_KEY") as? String,
           !key.isEmpty, !key.contains("$(") {
            // Supabase's Apple default auth storage is Keychain; tokens never enter UserDefaults.
            client = SupabaseClient(supabaseURL: url, supabaseKey: key)
        } else { client = nil }
    }
    deinit { authTask?.cancel() }
    func requireClient() throws -> SupabaseClient { guard let client else { throw PiggyFailure.configuration }; return client }
    func requireUser() throws -> UUID { guard let userID else { throw PiggyFailure.signedOut }; return userID }
    func start() async {
        guard authTask == nil, let client else { return }
        authTask = Task { [weak self] in
            for await (_, session) in client.auth.authStateChanges {
                guard !Task.isCancelled, let self else { return }
                let changed = self.userID != session?.user.id
                if changed { self.clearAccount() }
                self.userID = session?.user.id
                self.hasAppleIdentity = session?.user.identities?.contains(where: { $0.provider == "apple" }) == true
                if changed, self.userID != nil { await self.reload() }
            }
        }
        do {
            let session = try await client.auth.session
            if userID != session.user.id {
                clearAccount(); userID = session.user.id
                hasAppleIdentity = session.user.identities?.contains(where: { $0.provider == "apple" }) == true
                await reload()
            }
        } catch { /* Absence of a saved session is the normal first-launch state. */ }
    }
    private func clearAccount() {
        reloadGeneration = UUID(); userID = nil; receipts = []; friends = []; requests = []
        projects = []; splits = []; profile = nil; finance = nil; bank = BankSnapshot(); hasAppleIdentity = false; busy = false
        settingConsent = true; aiConsent = false; settingConsent = false
    }
    #if DEBUG && targetEnvironment(simulator)
    /// Internal capture/testing only. Uses normal Supabase authentication and RLS.
    /// Credentials are entered interactively and never bundled or logged.
    func signInSimulatorTest(email: String, password: String) async throws {
        let session = try await requireClient().auth.signIn(email: email, password: password)
        await accept(session)
    }
    #endif
    func signInGoogle() async throws {
        let session = try await requireClient().auth.signInWithOAuth(provider: .google, redirectTo: URL(string: "piggy://auth/callback"))
        await accept(session)
    }
    func signInApple(idToken: String, nonce: String) async throws {
        let session = try await requireClient().auth.signInWithIdToken(credentials: .init(provider: .apple, idToken: idToken, nonce: nonce))
        await accept(session)
    }
    private func accept(_ session: Session) async {
        if userID != session.user.id { clearAccount() }
        userID = session.user.id
        hasAppleIdentity = session.user.identities?.contains(where: { $0.provider == "apple" }) == true
        await reload()
    }
    func signOut() async {
        do { try await requireClient().auth.signOut(); clearAccount() }
        catch { errorMessage = error.localizedDescription }
    }
    func deleteAccount(authorizationCode: String?) async throws {
        struct Deletion: Decodable { var deleted: Bool }
        var body: [String: JSONValue] = ["confirm": .string("DELETE")]
        if let authorizationCode { body["apple_authorization_code"] = .string(authorizationCode) }
        let result: Deletion = try await requireClient().functions.invoke("delete-account", options: .init(body: body))
        guard result.deleted else { throw PiggyFailure.service("Das Konto konnte nicht gelöscht werden.") }
        try? await client?.auth.signOut(scope: .local)
        clearAccount()
    }
    func reload() async {
        guard let uid = userID, let client else { return }
        let generation = UUID(); reloadGeneration = generation; busy = true
        defer { if reloadGeneration == generation { busy = false } }
        do {
            var loaded: [Receipt] = []; var offset = 0
            while true {
                let page: [Receipt] = try await client.from("receipts").select("*,receipt_items(*)")
                    .eq("user_id", value: uid.uuidString).order("receipt_date", ascending: false).order("id")
                    .range(from: offset, to: offset + 499).execute().value
                loaded += page; if page.count < 500 { break }; offset += 500
            }
            let account: Profile = try await client.from("profiles").select().eq("id", value: uid.uuidString).single().execute().value
            let social: [FriendshipRow] = try await client.from("friendships")
                .select("*,requester:profiles!requester_id(*),recipient:profiles!recipient_id(*)")
                .or("requester_id.eq.\(uid),recipient_id.eq.\(uid)").execute().value
            let groups: [Project] = try await client.from("projects").select("*,project_members(*,profile:profiles(*))").order("created_at", ascending: false).execute().value
            var debts: [ExpenseSplit] = []; offset = 0
            while true {
                let page: [ExpenseSplit] = try await client.from("splits")
                    .select("*,split_participants(*,profile:profiles(*)),split_exclusions(*),receipt:receipts(store_name,receipt_date),payer_profile:profiles!splits_payer_id_fkey(*)")
                    .range(from: offset, to: offset + 499).execute().value
                debts += page; if page.count < 500 { break }; offset += 500
            }
            struct Settings: Decodable { let ai_consent_at: String? }
            let settings: [Settings] = try await client.from("user_settings").select("ai_consent_at").eq("user_id", value: uid.uuidString).execute().value
            guard userID == uid, reloadGeneration == generation else { return }
            receipts = loaded; profile = account; projects = groups; splits = debts
            let people = social.map { Friend(id: $0.id, profile: $0.requester_id == uid ? $0.recipient : $0.requester, status: $0.status, outgoing: $0.requester_id == uid) }
            friends = people.filter { $0.status == "accepted" }; requests = people.filter { $0.status == "pending" }
            settingConsent = true; aiConsent = settings.first?.ai_consent_at != nil; settingConsent = false
            errorMessage = nil
        } catch { if userID == uid, reloadGeneration == generation { errorMessage = error.localizedDescription } }
    }
    private func persistConsent(_ enabled: Bool, user: UUID) async throws {
        try await requireClient().from("user_settings").upsert([
            "user_id": JSONValue.uuid(user), "ai_consent_at": enabled ? .string(ISO8601DateFormatter().string(from: Date())) : .null,
            "ai_consent_version": .string("2026-10-31")
        ], onConflict: "user_id").execute()
    }
    func loadFinance(month: String, currency: String = "CHF") async throws -> NativeFinanceSummary {
        let uid = try requireUser()
        let value: NativeFinanceSummary = try await requireClient().functions.invoke("native-finance", options: .init(body: ["month": month, "currency": currency]))
        guard userID == uid else { throw PiggyFailure.signedOut }; finance = value; return value
    }
    func loadFinance(range: FinanceDateRange, currency: String) async throws -> NativeFinanceSummary {
        let uid = try requireUser()
        let value: NativeFinanceSummary = try await requireClient().functions.invoke("native-finance", options: .init(body: ["start": range.start, "end": range.endExclusive, "currency": currency]))
        guard userID == uid else { throw PiggyFailure.signedOut }
        return value
    }
    var balances: [OpenBalance] {
        guard let uid = userID else { return [] }
        return splits.flatMap { split in
            split.split_participants.compactMap { participant in
                guard !participant.settled, participant.user_id != split.payer_id,
                      split.payer_id == uid || participant.user_id == uid else { return nil }
                return OpenBalance(id: participant.id, otherName: split.payer_id == uid ? (participant.profile?.name ?? "Kontakt") : (split.payer_profile?.name ?? "Kontakt"), amount: participant.amount, currency: split.currency, owedToMe: split.payer_id == uid, receiptName: split.receipt?.store_name ?? "Beleg")
            }
        }
    }
}
private struct FriendshipRow: Decodable {
    let id: UUID; let requester_id: UUID; let recipient_id: UUID; let status: String
    let requester: Profile; let recipient: Profile
}
