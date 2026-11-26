import SwiftUI

struct PaymentCardsView: View {
    @EnvironmentObject private var store: PiggyStore
    @State private var adding = false
    @State private var editing: ProfileCard?
    @State private var removing: ProfileCard?
    @State private var busy = false
    @State private var error: String?
    var body: some View {
        List {
            Section {
                ForEach(store.cards) { card in
                    Button { editing = card } label: {
                        Label(card.displayName, systemImage: "creditcard").foregroundStyle(.primary)
                    }
                    .swipeActions {
                        Button("Entfernen", role: .destructive) { removing = card }
                    }
                }
                Button { adding = true } label: { Label("Karte hinzufügen", systemImage: "plus") }
            } footer: {
                Text("Piggy speichert nur Name, Kartentyp und die letzten vier Ziffern. Hinterlegte Karten sind wie bisher für deine Piggy-Freunde sichtbar. Apple Pay kann andere Endziffern verwenden; hinterlege diese bei Bedarf als eigene Karte.")
            }
            if let error { ScreenError(message: error) }
        }
        .navigationTitle("Meine Karten")
        .disabled(busy)
        .task { await load() }
        .refreshable { await load() }
        .sheet(isPresented: $adding) { PaymentCardEditView() }
        .sheet(item: $editing) { PaymentCardEditView(card: $0) }
        .confirmationDialog("Karte aus deinem Profil entfernen?", isPresented: Binding(get: { removing != nil }, set: { if !$0 { removing = nil } }), titleVisibility: .visible) {
            Button("Karte entfernen", role: .destructive) { if let card = removing { Task { await remove(card) } } }
            Button("Abbrechen", role: .cancel) { removing = nil }
        } message: { Text("Die Zahlungsangaben auf deinen Belegen bleiben erhalten.") }
    }
    private func load() async {
        do { try await store.loadCards(); error = nil } catch { self.error = error.localizedDescription }
    }
    private func remove(_ card: ProfileCard) async {
        busy = true
        defer { busy = false; removing = nil }
        do { try await store.removeCard(card); error = nil } catch { self.error = error.localizedDescription }
    }
}

struct PaymentCardEditView: View {
    @EnvironmentObject private var store: PiggyStore
    @Environment(\.dismiss) private var dismiss
    private let cardID: UUID?
    @State private var lastFour: String
    @State private var network: String
    @State private var name: String
    @State private var busy = false
    @State private var error: String?
    init(card: ProfileCard? = nil, suggested: String? = nil) {
        cardID = card?.id
        let brand = card?.network ?? PaymentCard.network(in: suggested ?? "") ?? ""
        _lastFour = State(initialValue: card?.last_four ?? PaymentCard.lastFour(in: suggested) ?? "")
        _network = State(initialValue: brand)
        let label = card?.label ?? ""
        _name = State(initialValue: label == brand ? "" : label.hasPrefix(brand + " · ") ? String(label.dropFirst(brand.count + 3)) : label)
    }
    var body: some View {
        NavigationStack {
            Form {
                Section {
                    TextField("Name, z. B. Privat oder Reisekarte", text: $name).accessibilityLabel("Kartenname")
                    Picker("Kartentyp", selection: $network) {
                        Text("Unbekannt / Andere").tag("")
                        ForEach(PaymentCard.networks, id: \.self) { Text($0).tag($0) }
                    }
                    LabeledContent("Letzte vier Ziffern") {
                        TextField("5327", text: $lastFour).keyboardType(.numberPad).multilineTextAlignment(.trailing)
                            .accessibilityLabel("Letzte vier Kartenziffern")
                    }
                } footer: { Text("Nur die letzten vier Ziffern eingeben. Keine vollständige Kartennummer und keinen Sicherheitscode.") }
                if let error { ScreenError(message: error) }
            }
            .navigationTitle(cardID == nil ? "Karte hinterlegen" : "Karte bearbeiten")
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .cancellationAction) { Button("Abbrechen") { dismiss() }.disabled(busy) }
                ToolbarItem(placement: .confirmationAction) {
                    Button { Task { await save() } } label: { if busy { ProgressView() } else { Text("Speichern").bold() } }
                        .disabled(busy || lastFour.range(of: #"^[0-9]{4}$"#, options: .regularExpression) == nil)
                }
            }
            .disabled(busy).interactiveDismissDisabled(busy)
        }
    }
    private func save() async {
        busy = true
        defer { busy = false }
        do { try await store.saveCard(id: cardID, lastFour: lastFour, network: network, name: name); dismiss() }
        catch { self.error = error.localizedDescription }
    }
}

/// Shared by scan review and editing existing receipts.
struct ReceiptPaymentFields: View {
    @EnvironmentObject private var store: PiggyStore
    @Binding var method: String
    @Binding var card: String?
    @State private var adding = false
    @State private var error: String?
    private var candidates: [ProfileCard] { PaymentCard.matches(card, method: method, cards: store.cards) }
    var body: some View {
        TextField("Zahlungsart", text: $method)
        LabeledContent("Kartenangabe") {
            TextField("z. B. Mastercard ···· 5327", text: Binding(get: { card ?? "" }, set: { card = $0.isEmpty ? nil : $0 }))
                .multilineTextAlignment(.trailing).autocorrectionDisabled().accessibilityLabel("Kartenangabe")
        }
        Menu {
            Button("Keine Karte") { card = nil; method = "Unbekannt" }
            ForEach(store.cards) { saved in
                Button(saved.displayName) { card = saved.canonical; method = saved.network ?? "Karte" }
            }
        } label: { Label("Hinterlegte Karte auswählen", systemImage: "creditcard") }
        if let display = store.cardDisplay(card, method: method) {
            Label(display, systemImage: candidates.count == 1 ? "checkmark.circle" : "creditcard")
                .font(.subheadline).foregroundStyle(.secondary)
        }
        if let card, !card.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty, PaymentCard.lastFour(in: card) == nil {
            Text("Bitte Kartentyp und die letzten vier Ziffern prüfen, z. B. Mastercard ···· 5327.")
                .font(.footnote).foregroundStyle(.red)
        }
        if candidates.count > 1 {
            Text("Mehrere Karten passen zu diesen Endziffern. Wähle den passenden Kartentyp; Piggy ordnet keine Karte automatisch zu.")
                .font(.footnote).foregroundStyle(.secondary)
        }
        Button("Karte im Profil hinterlegen") { adding = true }
            .disabled(candidates.count == 1)
            .task { do { try await store.loadCards() } catch { self.error = error.localizedDescription } }
            .sheet(isPresented: $adding) { PaymentCardEditView(suggested: PaymentCard.normalized(card, method: method, cards: [])) }
        if let error { Text(error).font(.footnote).foregroundStyle(.red) }
    }
}
