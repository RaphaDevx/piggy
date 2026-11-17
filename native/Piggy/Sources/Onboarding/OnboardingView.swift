import SwiftUI

struct OnboardingView: View {
    let onComplete: () -> Void
    @State private var page = 0
    @Environment(\.accessibilityReduceMotion) private var reduceMotion
    private let pages: [WalkthroughPage] = [
        .init(title: "Deine Belege. Dein Überblick.", text: "Piggy hilft dir, Einkaufsbelege zu ordnen und gemeinsame Ausgaben verständlich aufzuteilen.", pose: "idle-happy", symbol: "sparkles", hint: "Starte mit deinem nächsten Einkauf."),
        .init(title: "Scannen und kurz prüfen", text: "Fotografiere einen Beleg oder wähle ein Bild. Kontrolliere danach Geschäft, Betrag und die erkannten Artikel, bevor du speicherst.", pose: "catch-surprised", symbol: "viewfinder", hint: "Du entscheidest, was gespeichert wird."),
        .init(title: "Verstehe deine Ausgaben", text: "Deine Belege werden nach Kategorien übersichtlich. So findest du Einkäufe wieder und erkennst, wofür dein Geld ausgegeben wurde.", pose: "idle-happy", symbol: "chart.bar.xaxis", hint: "Deine Übersicht wächst mit deinen Belegen."),
        .init(title: "Gemeinsam fair aufteilen", text: "Verbinde dich mit Freunden und teile gemeinsame Einkäufe. Einzelne Artikel lassen sich den passenden Personen zuordnen.", pose: "cheer-happy", symbol: "person.2.fill", hint: "Bereit für deinen ersten Beleg?")
    ]
    var body: some View {
        VStack(spacing: 0) {
            HStack {
                Text("Piggy").font(.title2.bold())
                Spacer()
                Button("Überspringen", action: onComplete).font(.body.weight(.semibold)).frame(minHeight: 44)
            }.padding(.horizontal, 24)
            TabView(selection: $page) {
                ForEach(Array(pages.enumerated()), id: \.offset) { index, item in
                    ScrollView {
                        VStack(spacing: 24) {
                            MascotView(pose: item.pose, size: 240).padding(.top, 12)
                            Label(item.hint, systemImage: item.symbol)
                                .font(.subheadline.weight(.medium)).multilineTextAlignment(.center)
                                .padding(12).background(Theme.pink.opacity(0.18), in: Capsule())
                            Text(item.title).font(.largeTitle.bold()).multilineTextAlignment(.center)
                                .accessibilityAddTraits(.isHeader)
                            Text(item.text).font(.body).foregroundStyle(.secondary).multilineTextAlignment(.center)
                                .lineSpacing(5)
                        }.padding(.horizontal, 28).padding(.bottom, 24).frame(maxWidth: 520)
                            .frame(maxWidth: .infinity)
                    }.tag(index)
                }
            }.tabViewStyle(.page(indexDisplayMode: .never))
            VStack(spacing: 18) {
                HStack(spacing: 8) {
                    ForEach(pages.indices, id: \.self) { index in
                        Capsule().fill(index == page ? Theme.text : Color.secondary.opacity(0.25))
                            .frame(width: index == page ? 24 : 8, height: 8)
                    }
                }.accessibilityElement(children: .ignore).accessibilityLabel("Schritt \(page + 1) von \(pages.count)")
                Button {
                    if page == pages.count - 1 { onComplete() }
                    else { withAnimation(reduceMotion ? nil : .easeInOut(duration: 0.25)) { page += 1 } }
                } label: {
                    HStack { Spacer(); Text(page == pages.count - 1 ? "Los geht’s" : "Weiter").fontWeight(.bold); Image(systemName: "arrow.right"); Spacer() }
                        .padding(18).foregroundStyle(.black).background(Theme.accent, in: RoundedRectangle(cornerRadius: 18))
                }.buttonStyle(.plain)
                if page > 0 { Button("Zurück") { withAnimation(reduceMotion ? nil : .easeInOut(duration: 0.25)) { page -= 1 } }.frame(minHeight: 44) }
            }.padding(.horizontal, 24).padding(.top, 12).padding(.bottom, 16)
        }.background(Theme.background).tint(Theme.text)
    }
}
private struct WalkthroughPage { let title: String; let text: String; let pose: String; let symbol: String; let hint: String }
