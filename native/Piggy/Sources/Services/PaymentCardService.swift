import Foundation
import Supabase

extension PiggyStore {
    func loadCards() async throws {
        let uid = try requireUser()
        let loaded: [ProfileCard] = try await requireClient().from("profile_cards")
            .select("id,user_id,last_four,label").eq("user_id", value: uid.uuidString).order("created_at").execute().value
        guard userID == uid else { throw PiggyFailure.signedOut }
        cards = loaded
    }
    func saveCard(id: UUID?, lastFour: String, network: String, name: String) async throws {
        let uid = try requireUser()
        guard lastFour.range(of: #"^[0-9]{4}$"#, options: .regularExpression) != nil else {
            throw PiggyFailure.validation("Bitte gib genau die letzten vier Ziffern an.")
        }
        guard network.isEmpty || PaymentCard.networks.contains(network) else {
            throw PiggyFailure.validation("Bitte wähle den Kartentyp.")
        }
        let name = name.trimmingCharacters(in: .whitespacesAndNewlines)
        guard name.count <= 60, !name.contains(where: { $0.isNewline }),
              name.range(of: #"[0-9](?:[ -]?[0-9]){7,}"#, options: .regularExpression) == nil else {
            throw PiggyFailure.validation("Bitte verwende einen kurzen Namen ohne vollständige Kartennummer.")
        }
        try await loadCards()
        guard !cards.contains(where: { $0.id != id && $0.last_four == lastFour && $0.network == (network.isEmpty ? nil : network) }) else {
            throw PiggyFailure.validation("Diese Karte ist bereits hinterlegt. Bearbeite den vorhandenen Eintrag.")
        }
        let label = [network, name].filter { !$0.isEmpty }.joined(separator: " · ")
        let payload: [String: JSONValue] = ["user_id": .uuid(uid), "last_four": .string(lastFour), "label": label.isEmpty ? .null : .string(label)]
        let saved: ProfileCard
        if let id {
            saved = try await requireClient().from("profile_cards").update(payload)
                .eq("id", value: id.uuidString).eq("user_id", value: uid.uuidString)
                .select("id,user_id,last_four,label").single().execute().value
        } else {
            saved = try await requireClient().from("profile_cards").insert(payload)
                .select("id,user_id,last_four,label").single().execute().value
        }
        guard userID == uid else { throw PiggyFailure.signedOut }
        cards.removeAll { $0.id == saved.id }; cards.append(saved)
    }
    func removeCard(_ card: ProfileCard) async throws {
        let uid = try requireUser()
        let removed: [ProfileCard] = try await requireClient().from("profile_cards").delete()
            .eq("id", value: card.id.uuidString).eq("user_id", value: uid.uuidString)
            .select("id,user_id,last_four,label").execute().value
        guard userID == uid else { throw PiggyFailure.signedOut }
        guard removed.count == 1 else { throw PiggyFailure.service("Die Karte konnte nicht entfernt werden. Bitte lade die Liste erneut.") }
        cards.removeAll { $0.id == card.id }
    }
    func cardDisplay(_ raw: String?, method: String?) -> String? {
        PaymentCard.display(raw, method: method, cards: cards)
    }
}
