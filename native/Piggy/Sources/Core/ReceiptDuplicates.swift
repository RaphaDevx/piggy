import Foundation

struct DismissedDuplicate: Decodable, Hashable {
    var receipt_a: UUID
    var receipt_b: UUID
    var key: String { DuplicatePair.key(receipt_a, receipt_b) }
}
struct DuplicatePair: Identifiable, Hashable {
    var target: Receipt
    var source: Receipt
    var id: String { Self.key(target.id, source.id) }
    static func key(_ a: UUID, _ b: UUID) -> String { [a.uuidString, b.uuidString].sorted().joined(separator: ":") }
}
extension ReceiptRules {
    /// Manual review is independent of automatic duplicate detection and dismissed suggestions.
    static func manualMergeCandidates(_ receipts: [Receipt], owner: UUID, targetID: UUID?, search: String) -> [Receipt] {
        let query = search.trimmingCharacters(in: .whitespacesAndNewlines)
        return receipts.filter {
            $0.user_id == owner && $0.id != targetID &&
            (query.isEmpty || $0.searchText.localizedCaseInsensitiveContains(query))
        }
    }

    static func duplicatePairs(_ receipts: [Receipt], dismissed: [DismissedDuplicate]) -> [DuplicatePair] {
        var buckets: [String: [Receipt]] = [:]
        var pairs: [DuplicatePair] = []
        let ignored = Set(dismissed.map(\.key))
        for receipt in receipts {
            guard let date = receipt.receipt_date, !date.isEmpty,
                  let amount = receipt.total_amount, !amount.isNaN,
                  !merchantKey(receipt.store_name).isEmpty else { continue }
            let key = [receipt.user_id.uuidString, String(date.prefix(10)), String(cents(amount)), receipt.currency.uppercased(), merchantKey(receipt.store_name)].joined(separator: "|")
            for target in buckets[key, default: []] where target.id != receipt.id {
                let pair = DuplicatePair(target: target, source: receipt)
                if !ignored.contains(pair.id) { pairs.append(pair) }
            }
            buckets[key, default: []].append(receipt)
        }
        return pairs
    }
}
