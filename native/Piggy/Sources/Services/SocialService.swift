import Foundation
import Supabase

extension PiggyStore {
    func sendFriendRequest(code: String) async throws {
        let uid = try requireUser(); let client = try requireClient()
        let target: Profile = try await client.from("profiles").select().eq("piggy_code", value: code.trimmingCharacters(in: .whitespacesAndNewlines).uppercased()).single().execute().value
        guard target.id != uid else { throw PiggyFailure.validation("Du kannst dich nicht selbst hinzufügen.") }
        try await client.from("friendships").insert(["requester_id": uid, "recipient_id": target.id]).execute()
        await reload()
    }
    func respondToFriend(id: UUID, accept: Bool) async throws {
        if accept {
            try await requireClient().from("friendships").update(["status": "accepted"]).eq("id", value: id.uuidString).eq("recipient_id", value: try requireUser().uuidString).execute()
        } else { try await requireClient().from("friendships").delete().eq("id", value: id.uuidString).execute() }
        await reload()
    }
    func removeFriend(id: UUID) async throws {
        try await requireClient().from("friendships").delete().eq("id", value: id.uuidString).execute()
        await reload()
    }
    func createProject(name: String, description: String, friendIDs: [UUID]) async throws {
        let name = name.trimmingCharacters(in: .whitespacesAndNewlines)
        guard !name.isEmpty else { throw PiggyFailure.validation("Bitte gib einen Projektnamen an.") }
        let uid = try requireUser(); let client = try requireClient()
        let project: Project = try await client.from("projects").insert([
            "name": JSONValue.string(name), "description": .string(description), "emoji": .string("📁"),
            "currency": .string("CHF"), "created_by": .uuid(uid)
        ]).select().single().execute().value
        do {
            if !friendIDs.isEmpty {
                try await client.from("project_members").insert(Array(Set(friendIDs)).map { [
                    "project_id": project.id, "user_id": $0, "invited_by": uid
                ] }).execute()
            }
        } catch {
            do { try await client.from("projects").delete().eq("id", value: project.id.uuidString).execute() }
            catch { await reload(); throw PiggyFailure.service("Das Projekt wurde angelegt, aber nicht alle Einladungen gespeichert. Bitte prüfe es in deiner Projektliste.") }
            throw error
        }
        await reload()
    }
    func split(receipt: Receipt, participantIDs: [UUID], exclusions: [UUID: Set<UUID>]) async throws {
        let uid = try requireUser(); let client = try requireClient()
        guard receipt.user_id == uid else { throw PiggyFailure.validation("Nur eigene Belege können aufgeteilt werden.") }
        // Preserve existing settlements. Never delete a live split just to retry a save.
        let existing: [SplitID] = try await client.from("splits").select("id").eq("receipt_id", value: receipt.id.uuidString).execute().value
        guard existing.isEmpty else { throw PiggyFailure.validation("Dieser Beleg ist bereits aufgeteilt. Bestehende Zahlungen bleiben erhalten.") }
        let people = Array(Set(participantIDs + [uid]))
        let shares = try ReceiptRules.shares(items: receipt.receipt_items, total: receipt.total_amount ?? 0, participants: people, exclusions: exclusions)
        let split: SplitID = try await client.from("splits").insert([
            "receipt_id": JSONValue.uuid(receipt.id), "payer_id": .uuid(uid),
            "total_amount": .number(receipt.total_amount ?? 0), "currency": .string(receipt.currency)
        ]).select("id").single().execute().value
        do {
            try await client.from("split_participants").insert(people.map { person in [
                "split_id": JSONValue.uuid(split.id), "user_id": .uuid(person),
                "amount": .number(shares[person] ?? 0), "settled": .bool(person == uid)
            ] }).execute()
            let rows: [[String: JSONValue]] = exclusions.flatMap { person, items in
                items.map { ["split_id": .uuid(split.id), "user_id": .uuid(person), "receipt_item_id": .uuid($0)] }
            }
            if !rows.isEmpty { try await client.from("split_exclusions").insert(rows).execute() }
        } catch {
            do { try await client.from("splits").delete().eq("id", value: split.id.uuidString).execute() }
            catch { await reload(); throw PiggyFailure.service("Die Aufteilung konnte nicht vollständig gespeichert oder zurückgenommen werden. Bitte prüfe die offenen Beträge, bevor du erneut speicherst.") }
            throw error
        }
        await reload()
    }
    func settle(participantID: UUID) async throws {
        try await requireClient().from("split_participants").update([
            "settled": JSONValue.bool(true), "settled_at": .string(ISO8601DateFormatter().string(from: Date()))
        ]).eq("id", value: participantID.uuidString).execute()
        await reload()
    }
    func updateName(_ name: String) async throws {
        try await requireClient().from("profiles").update(["display_name": name.trimmingCharacters(in: .whitespacesAndNewlines)]).eq("id", value: try requireUser().uuidString).execute()
        await reload()
    }
}
private struct SplitID: Decodable { let id: UUID }

/// Option A: beglichene Beträge, für beide Seiten sichtbar.
struct SettledEntry: Identifiable, Hashable {
    var id: UUID
    var otherName: String
    var amount: Decimal
    var currency: String
    var owedToMe: Bool
    var receiptName: String
    var settledAt: String?
}

extension PiggyStore {
    var settledEntries: [SettledEntry] {
        guard let uid = userID else { return [] }
        let rows: [SettledEntry] = splits.flatMap { split in
            split.split_participants.compactMap { participant -> SettledEntry? in
                guard participant.settled, participant.user_id != split.payer_id,
                      split.payer_id == uid || participant.user_id == uid else { return nil }
                return SettledEntry(id: participant.id,
                                    otherName: split.payer_id == uid ? (participant.profile?.name ?? "Kontakt") : (split.payer_profile?.name ?? "Kontakt"),
                                    amount: participant.amount, currency: split.currency, owedToMe: split.payer_id == uid,
                                    receiptName: split.receipt?.store_name ?? "Beleg", settledAt: participant.settled_at)
            }
        }
        return rows.sorted { ($0.settledAt ?? "") > ($1.settledAt ?? "") }
    }
}
