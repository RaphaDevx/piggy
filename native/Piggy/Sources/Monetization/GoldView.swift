import SwiftUI
import RevenueCat

struct GoldView: View {
    @EnvironmentObject private var gold: GoldManager
    @Environment(\.dismiss) private var dismiss
    @State private var selected: String?
    @State private var busy = false
    @State private var message: String?
    private var selectedPackage: Package? { gold.offers.first(where: { $0.identifier == selected }) ?? gold.offers.first }
    var body: some View {
        NavigationStack {
            ScrollView {
                VStack(spacing: 24) {
                    MascotView(pose: "cheer-happy", size: 200)
                    Text("Piggy Gold").font(.largeTitle.bold())
                    Text("Deine Quittungen. Ohne Werbung.").font(.title3).multilineTextAlignment(.center)
                    VStack(alignment: .leading, spacing: 16) {
                        Label("Werbefrei in Piggy", systemImage: "checkmark.shield")
                        Label("Höheres Tageslimit für KI-Scans", systemImage: "viewfinder")
                        Label("Gold-Logo in deiner App", systemImage: "sparkles")
                    }.font(.body).frame(maxWidth: .infinity, alignment: .leading).padding(20).background(Theme.surface, in: RoundedRectangle(cornerRadius: 20))
                    if gold.isGold {
                        Label("Piggy Gold ist aktiv", systemImage: "checkmark.seal.fill").font(.headline)
                        Link("Abo verwalten", destination: URL(string: "https://apps.apple.com/account/subscriptions")!)
                    } else if gold.loading { ProgressView("Angebote werden geladen") }
                    else if gold.offers.isEmpty {
                        Text("Das Angebot ist gerade nicht verfügbar. Du kannst Piggy kostenlos weiterverwenden.").foregroundStyle(.secondary)
                    } else {
                        ForEach(gold.offers, id: \.identifier) { package in
                            Button { selected = package.identifier } label: {
                                HStack {
                                    VStack(alignment: .leading, spacing: 4) {
                                        Text(package.packageType == .annual ? "Jährlich" : "Monatlich").font(.headline)
                                        Text("\(package.storeProduct.localizedPriceString) pro \(package.packageType == .annual ? "Jahr" : "Monat")")
                                    }
                                    Spacer()
                                    Image(systemName: selectedPackage?.identifier == package.identifier ? "checkmark.circle.fill" : "circle")
                                }.foregroundStyle(Theme.text).padding(18)
                                    .background(selectedPackage?.identifier == package.identifier ? Theme.accent.opacity(0.2) : Theme.surface, in: RoundedRectangle(cornerRadius: 16))
                            }.buttonStyle(.plain).accessibilityAddTraits(selectedPackage?.identifier == package.identifier ? [.isSelected] : [])
                        }
                        if let package = selectedPackage {
                            Button {
                                busy = true
                                Task {
                                    do { try await gold.purchase(package) }
                                    catch { message = "Der Kauf konnte nicht abgeschlossen werden. Bitte versuche es erneut." }
                                    busy = false
                                }
                            } label: { Text(busy ? "Bitte warten…" : "Piggy Gold abonnieren").bold().frame(maxWidth: .infinity).padding(18).foregroundStyle(.black).background(Theme.accent, in: RoundedRectangle(cornerRadius: 16)) }
                                .disabled(busy || LaunchConfiguration.httpsURL("TERMS_URL") == nil || LaunchConfiguration.httpsURL("PRIVACY_URL") == nil)
                            Text("Automatisch verlängerndes Abo: \(package.storeProduct.localizedPriceString) pro \(package.packageType == .annual ? "Jahr" : "Monat"). Die Zahlung erfolgt über dein Apple-Konto. Du kannst die Verlängerung in deinen Apple-Abos kündigen.")
                                .font(.footnote).foregroundStyle(.secondary)
                        }
                    }
                    Button("Käufe wiederherstellen") {
                        busy = true
                        Task { do { try await gold.restore(); message = gold.isGold ? "Piggy Gold ist wieder aktiv." : "Kein aktives Abo gefunden." }
                            catch { message = "Käufe konnten gerade nicht wiederhergestellt werden." }; busy = false }
                    }.disabled(busy || !gold.available).frame(minHeight: 44)
                    HStack {
                        if let url = LaunchConfiguration.httpsURL("TERMS_URL") { Link("Nutzungsbedingungen", destination: url) }
                        if let url = LaunchConfiguration.httpsURL("PRIVACY_URL") { Link("Datenschutz", destination: url) }
                    }.font(.footnote)
                    Text("Die KI-Auswertung über Anthropic (USA), bei einem früher hinterlegten eigenen Gemini-Schlüssel über Google (USA), benötigt deine separate Zustimmung. Ohne Zustimmung bleibt die Erkennung lokal.").font(.footnote).foregroundStyle(.secondary)
                }.padding(24).frame(maxWidth: 560).frame(maxWidth: .infinity)
            }.background(Theme.background).toolbar { ToolbarItem(placement: .cancellationAction) { Button("Schliessen") { dismiss() } } }
                .alert("Piggy Gold", isPresented: Binding(get: { message != nil }, set: { if !$0 { message = nil } })) { Button("OK") { message = nil } } message: { Text(message ?? "") }
        }.tint(Theme.text)
    }
}
