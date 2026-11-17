import SwiftUI

struct MainTabs: View {
    @EnvironmentObject private var store: PiggyStore
    @State private var scannerPresented = false
    var body: some View {
        TabView {
            NavigationStack { DashboardView(openScanner: { scannerPresented = true }) }
                .tabItem { Label("Übersicht", systemImage: "house.fill") }
            NavigationStack { ReceiptListView(openScanner: { scannerPresented = true }) }
                .tabItem { Label("Belege", systemImage: "receipt.fill") }
            NavigationStack { SocialHomeView() }
                .tabItem { Label("Gemeinsam", systemImage: "person.2.fill") }
            NavigationStack { ProfileView() }
                .tabItem { Label("Profil", systemImage: "person.crop.circle.fill") }
        }
        .tint(Theme.text)
        .sheet(isPresented: $scannerPresented) { CaptureFlow() }
        .alert("Bitte erneut versuchen", isPresented: Binding(
            get: { store.errorMessage != nil },
            set: { if !$0 { store.errorMessage = nil } }
        )) {
            Button("Schliessen", role: .cancel) { store.errorMessage = nil }
        } message: { Text(store.errorMessage ?? "") }
    }
}
