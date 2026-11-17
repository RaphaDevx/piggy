import SwiftUI

@main
struct PiggyNativeApp: App {
    @StateObject private var store = PiggyStore()
    @StateObject private var gold = GoldManager()
    @StateObject private var ads = NativeAdsManager()
    var body: some Scene {
        WindowGroup {
            NativeRootView().environmentObject(store).environmentObject(gold).environmentObject(ads)
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
    @Environment(\.scenePhase) private var phase
    var body: some View {
        Group {
            if !completedWalkthrough {
                OnboardingView { completedWalkthrough = true }
            } else if starting {
                VStack { MascotView(size: 180); ProgressView("Piggy wird geöffnet") }.frame(maxWidth: .infinity, maxHeight: .infinity).background(Theme.background)
            } else if store.userID == nil {
                LoginView()
            } else {
                MainTabs()
            }
        }
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
