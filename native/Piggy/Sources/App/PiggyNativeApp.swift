import SwiftUI

@main
struct PiggyNativeApp: App {
    @StateObject private var store = PiggyStore()
    @StateObject private var gold = GoldManager()
    @StateObject private var ads = NativeAdsManager()
    @StateObject private var library = PersonalLibrary()
    var body: some Scene {
        WindowGroup {
            NativeRootView().environmentObject(store).environmentObject(gold).environmentObject(ads).environmentObject(library)
                .onChange(of: store.userID, initial: true) { _, uid in library.activate(uid); ShareInbox.setActiveOwner(uid) }
                .task { ads.attach(gold: gold) }
        }
    }
}

private struct NativeRootView: View {
    @EnvironmentObject private var store: PiggyStore
    @EnvironmentObject private var gold: GoldManager
    @EnvironmentObject private var ads: NativeAdsManager
    @AppStorage("piggy.onboarding.completed.v1") private var completedWalkthrough = false
    @State private var starting = true
    @State private var replayWalkthrough = false
    @State private var recoveryError: String?
    @Environment(\.scenePhase) private var phase
    var body: some View {
        Group {
            if !completedWalkthrough {
                OnboardingView { completedWalkthrough = true }
            } else if starting {
                VStack { MascotView(size: 180); ProgressView("Piggy wird geöffnet") }.frame(maxWidth: .infinity, maxHeight: .infinity).background(Theme.background)
            } else if store.passwordRecovery && store.userID != nil {
                RecoveredPasswordView()
            } else if store.userID == nil {
                LoginView()
            } else {
                MainTabs()
            }
        }
        .onOpenURL { url in
            if url.isFileURL {
                do { try SharedImportService.enqueue(file: url, owner: store.userID ?? ShareInbox.activeOwner()) }
                catch { recoveryError = error.localizedDescription }
                return
            }
            guard NativeAuthPolicy.isRecoveryCallback(url) else { return }
            Task {
                do { try await store.receivePasswordRecovery(url) }
                catch { recoveryError = "Der Link ist abgelaufen oder wurde auf einem anderen Gerät angefordert. Fordere auf diesem iPhone einen neuen Link an." }
            }
        }
        .alert("Piggy", isPresented: Binding(get: { recoveryError != nil }, set: { if !$0 { recoveryError = nil } })) {
            Button("OK") { recoveryError = nil }
        } message: { Text(recoveryError ?? "") }
        .task { await store.start(); starting = false; await gold.configure(userID: store.userID) }
        .onChange(of: store.userID) { _, userID in Task { await gold.configure(userID: userID) } }
        .onChange(of: phase) { _, state in
            if state == .active { Task { await gold.refresh(); await ads.refreshConsentIfAllowed() } }
            else { ads.suspend() }
        }
        .onChange(of: gold.isGold) { _, isGold in if isGold { ads.suspend() } }
        .onReceive(NotificationCenter.default.publisher(for: .init("piggy.showOnboarding"))) { _ in replayWalkthrough = true }
        .sheet(isPresented: $replayWalkthrough) { OnboardingView { replayWalkthrough = false } }
        .onReceive(NotificationCenter.default.publisher(for: .init("piggy.receiptSaved"))) { _ in
            Task { await ads.receiptSaved() }
        }
        .tint(Theme.text)
    }
}
