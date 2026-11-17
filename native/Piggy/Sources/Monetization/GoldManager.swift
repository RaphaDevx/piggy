import Foundation
import Combine
import RevenueCat

@MainActor
final class GoldManager: NSObject, ObservableObject, PurchasesDelegate {
    @Published private(set) var isGold = false
    @Published private(set) var resolved = false
    @Published private(set) var loading = false
    @Published private(set) var offers: [Package] = []
    @Published var errorMessage: String?
    private var userID: UUID?
    private var generation = 0
    var available: Bool { !LaunchConfiguration.value("REVENUECAT_IOS_KEY").isEmpty && userID != nil }

    func configure(userID: UUID?) async {
        generation += 1
        let epoch = generation
        self.userID = userID
        isGold = false; resolved = false; offers = []
        guard let userID, available else {
            if Purchases.isConfigured, !Purchases.shared.isAnonymous { _ = try? await Purchases.shared.logOut() }
            return
        }
        loading = true
        defer { if epoch == generation { loading = false } }
        do {
            if !Purchases.isConfigured {
                Purchases.configure(withAPIKey: LaunchConfiguration.value("REVENUECAT_IOS_KEY"), appUserID: userID.uuidString.lowercased())
            } else if Purchases.shared.appUserID != userID.uuidString.lowercased() {
                _ = try await Purchases.shared.logIn(userID.uuidString.lowercased())
            }
            guard epoch == generation else { return }
            Purchases.shared.delegate = self
            await refresh()
            let offering = try await Purchases.shared.offerings().all["default"]
            guard epoch == generation else { return }
            offers = offering?.availablePackages.filter { $0.packageType == .monthly || ($0.packageType == .annual && LaunchConfiguration.flag("PIGGY_ANNUAL_ENABLED")) } ?? []
            offers.sort { $0.packageType == .monthly && $1.packageType != .monthly }
        } catch { if epoch == generation { errorMessage = "Dein Abo konnte gerade nicht geprüft werden."; resolved = false } }
    }
    func refresh() async {
        guard available, Purchases.isConfigured else { resolved = false; return }
        let epoch = generation
        do {
            let info = try await Purchases.shared.customerInfo()
            guard epoch == generation else { return }
            apply(info)
        } catch { if epoch == generation { resolved = false } }
    }
    func purchase(_ package: Package) async throws {
        guard available, Purchases.isConfigured else { return }
        let epoch = generation
        let result = try await Purchases.shared.purchase(package: package)
        guard epoch == generation else { return }
        if !result.userCancelled { apply(result.customerInfo) }
    }
    func restore() async throws {
        guard available, Purchases.isConfigured else { return }
        let epoch = generation
        let info = try await Purchases.shared.restorePurchases()
        if epoch == generation { apply(info) }
    }
    nonisolated func purchases(_ purchases: Purchases, receivedUpdated customerInfo: CustomerInfo) {
        Task { @MainActor [weak self] in
            guard let self, self.userID?.uuidString.lowercased() == purchases.appUserID else { return }
            self.apply(customerInfo)
        }
    }
    private func apply(_ info: CustomerInfo) {
        isGold = info.entitlements.active["plus"]?.isActive == true
        resolved = true
    }
}

enum LaunchConfiguration {
    static func value(_ key: String) -> String {
        let value = (Bundle.main.object(forInfoDictionaryKey: key) as? String ?? "").trimmingCharacters(in: .whitespacesAndNewlines)
        return value.hasPrefix("$(") ? "" : value
    }
    static func flag(_ key: String) -> Bool {
        (Bundle.main.object(forInfoDictionaryKey: key) as? Bool) == true || ["YES", "true", "1"].contains(value(key))
    }
    static func httpsURL(_ key: String) -> URL? {
        guard let url = URL(string: value(key)), url.scheme == "https", url.host != nil else { return nil }
        return url
    }
}
