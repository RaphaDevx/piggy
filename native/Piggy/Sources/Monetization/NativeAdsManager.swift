import Foundation
import SwiftUI
import UIKit
import GoogleMobileAds
import UserMessagingPlatform

@MainActor
final class NativeAdsManager: NSObject, ObservableObject, FullScreenContentDelegate {
    @Published private(set) var allowed = UserDefaults.standard.bool(forKey: "piggy.native.ads.allowed")
    @Published private(set) var ready = false
    @Published private(set) var busy = false
    @Published var message: String?
    private weak var gold: GoldManager?
    private var ad: InterstitialAd?
    private var generation = 0
    private var presenting = false
    var configured: Bool {
        LaunchConfiguration.flag("PIGGY_ADS_ENABLED") && LaunchConfiguration.flag("PIGGY_ADS_REVIEW_APPROVED") &&
        !LaunchConfiguration.value("ADMOB_INTERSTITIAL_ID").isEmpty
    }
    private var eligible: Bool { configured && allowed && gold?.available == true && gold?.resolved == true && gold?.isGold == false }
    func attach(gold: GoldManager) { self.gold = gold }
    func suspend() { generation += 1; ready = false; ad = nil }
    func setAllowed(_ value: Bool) async {
        allowed = value
        UserDefaults.standard.set(value, forKey: "piggy.native.ads.allowed")
        suspend()
        if value { await refreshConsentIfAllowed() }
    }
    func refreshConsentIfAllowed() async {
        guard eligible, !busy, !presenting, UIApplication.shared.applicationState == .active else { return }
        busy = true
        defer { busy = false }
        let epoch = generation
        do {
            try await ConsentInformation.shared.requestConsentInfoUpdate(with: RequestParameters())
            guard epoch == generation, eligible else { return }
            try await ConsentForm.loadAndPresentIfRequired(from: presenter())
            guard epoch == generation, eligible, ConsentInformation.shared.canRequestAds else { return }
            MobileAds.shared.requestConfiguration.maxAdContentRating = .general
            await MobileAds.shared.start()
            guard epoch == generation, eligible else { return }
            let request = Request()
            let extras = Extras(); extras.additionalParameters = ["npa": "1"]; request.register(extras)
            #if DEBUG
            let unit = "ca-app-pub-3940256099942544/4411468910"
            #else
            let unit = LaunchConfiguration.value("ADMOB_INTERSTITIAL_ID")
            #endif
            let loaded = try await InterstitialAd.load(with: unit, request: request)
            guard epoch == generation, eligible else { return }
            loaded.fullScreenContentDelegate = self
            ad = loaded; ready = true
        } catch { ready = false; ad = nil }
    }
    func privacyOptions() async {
        guard configured, !busy else { return }
        suspend(); busy = true
        do { try await ConsentForm.presentPrivacyOptionsForm(from: presenter()) }
        catch { message = "Deine Datenschutzwahl ist gerade nicht verfügbar. Du kannst Werbung hier auch vollständig deaktivieren." }
        busy = false
        await refreshConsentIfAllowed()
    }
    func receiptSaved() async {
        guard eligible, !presenting else { return }
        let defaults = UserDefaults.standard
        let day = String(ISO8601DateFormatter().string(from: Date()).prefix(10))
        if defaults.string(forKey: "piggy.native.ads.day") != day {
            defaults.set(day, forKey: "piggy.native.ads.day"); defaults.set(0, forKey: "piggy.native.ads.count"); defaults.set(0, forKey: "piggy.native.ads.scans")
        }
        let scans = defaults.integer(forKey: "piggy.native.ads.scans") + 1
        defaults.set(scans, forKey: "piggy.native.ads.scans")
        guard scans >= 3, defaults.integer(forKey: "piggy.native.ads.count") < 3,
              Date().timeIntervalSince1970 - defaults.double(forKey: "piggy.native.ads.last") >= 180,
              let ad, ready else { return }
        let epoch = generation
        await gold?.refresh()
        // Wait only for the known receipt-save sheet transition, never for ad loading.
        try? await Task.sleep(for: .milliseconds(650))
        guard epoch == generation, eligible, UIApplication.shared.applicationState == .active,
              let controller = presenter(), controller.presentedViewController == nil else { return }
        defaults.set(scans - 3, forKey: "piggy.native.ads.scans")
        defaults.set(defaults.integer(forKey: "piggy.native.ads.count") + 1, forKey: "piggy.native.ads.count")
        defaults.set(Date().timeIntervalSince1970, forKey: "piggy.native.ads.last")
        ready = false; presenting = true
        ad.present(from: controller)
    }
    func adDidDismissFullScreenContent(_ ad: FullScreenPresentingAd) {
        presenting = false; self.ad = nil
        Task { await refreshConsentIfAllowed() }
    }
    func ad(_ ad: FullScreenPresentingAd, didFailToPresentFullScreenContentWithError error: Error) { presenting = false; self.ad = nil; ready = false }
    private func presenter() -> UIViewController? {
        let root = UIApplication.shared.connectedScenes.compactMap { $0 as? UIWindowScene }.flatMap(\.windows).first(where: \.isKeyWindow)?.rootViewController
        var top = root
        while let presented = top?.presentedViewController, !presented.isBeingDismissed { top = presented }
        return top
    }
}

struct NativeAdPreferences: View {
    @EnvironmentObject private var ads: NativeAdsManager
    @EnvironmentObject private var gold: GoldManager
    var body: some View {
        if ads.configured && !gold.isGold {
            Section("Werbung und Datenschutz") {
                Text("Werbung unterstützt die kostenlose Version. Google erhält technische Werbedaten, aber keine Inhalte deiner Belege.")
                Toggle("Werbung erlauben", isOn: Binding(get: { ads.allowed }, set: { value in Task { await ads.setAllowed(value) } })).disabled(ads.busy)
                if ads.allowed { Button("Datenschutzwahl verwalten") { Task { await ads.privacyOptions() } }.disabled(ads.busy) }
            }
        }
    }
}
