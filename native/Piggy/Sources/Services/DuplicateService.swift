import Foundation
import Supabase

extension PiggyStore {
    func refreshReceipts() async throws {
        let uid = try requireUser()
        var loaded: [Receipt] = []
        for offset in stride(from: 0, to: Int.max - 500, by: 500) {
            let page: [Receipt] = try await requireClient().from("receipts").select("*,receipt_items(*)")
                .eq("user_id", value: uid.uuidString).order("receipt_date", ascending: false).order("id")
                .range(from: offset, to: offset + 499).execute().value
            guard userID == uid else { throw PiggyFailure.signedOut }
            loaded += page
            if page.count < 500 { break }
        }
        receipts = loaded
    }
    func loadDuplicateReview() async throws {
        let uid = try requireUser()
        try await refreshReceipts()
        var ignored: [DismissedDuplicate] = []
        for offset in stride(from: 0, to: Int.max - 500, by: 500) {
            let page: [DismissedDuplicate] = try await requireClient().from("receipt_duplicate_decisions")
                .select("receipt_a,receipt_b").eq("user_id", value: uid.uuidString)
                .order("receipt_a").order("receipt_b").range(from: offset, to: offset + 499).execute().value
            guard userID == uid else { throw PiggyFailure.signedOut }
            ignored += page
            if page.count < 500 { break }
        }
        dismissedDuplicates = ignored
    }
    func keepSeparate(_ pair: DuplicatePair) async throws {
        let uid = try requireUser()
        try await requireClient().rpc("keep_receipts_separate", params: ["p_receipt_a": pair.target.id, "p_receipt_b": pair.source.id]).execute()
        guard userID == uid else { throw PiggyFailure.signedOut }
        dismissedDuplicates.append(DismissedDuplicate(receipt_a: pair.target.id, receipt_b: pair.source.id))
    }
    func mergeDuplicates(_ pair: DuplicatePair, choices: [String: JSONValue]) async throws {
        let uid = try requireUser()
        do {
            let _: UUID = try await requireClient().rpc("merge_receipt_duplicates", params: [
                "p_target_id": JSONValue.uuid(pair.target.id), "p_source_id": .uuid(pair.source.id), "p_choices": .object(choices)
            ]).execute().value
            guard userID == uid else { throw PiggyFailure.signedOut }
            try await loadDuplicateReview()
        } catch {
            await reload()
            throw error
        }
    }
}
