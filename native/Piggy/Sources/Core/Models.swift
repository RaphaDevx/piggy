import Foundation

struct Receipt: Identifiable, Codable, Hashable {
    var id: UUID = UUID()
    var user_id: UUID = UUID()
    var store_name: String = ""
    var receipt_date: String?
    var store_category: String?
    var total_amount: Decimal?
    var currency: String = "CHF"
    var payment_method: String?
    var payment_card: String?
    var image_url: String?
    var original_image_url: String?
    var raw_ocr_text: String?
    var notes: String?
    var extra_fields: [String: String?]?
    var markdown_content: String?
    var created_at: String = ""
    var project_id: UUID?
    var receipt_items: [ReceiptItem] = []
    var displayDate: String { receipt_date ?? String(created_at.prefix(10)) }
}
struct ReceiptItem: Identifiable, Codable, Hashable {
    var id: UUID = UUID()
    var receipt_id: UUID?
    var name: String = ""
    var quantity: Decimal?
    var unit: String?
    var unit_price: Decimal?
    var total_price: Decimal?
    var tags: [String] = []
    var subcategory: String?
    var is_adjustment: Bool?
    var category: String { tags.first ?? "Diverses" }
}
struct ReceiptDraft: Identifiable, Hashable {
    var id = UUID()
    var store_name = ""
    var receipt_date = ISO8601DateFormatter().string(from: Date()).prefix(10).description
    var total_amount: Decimal = 0
    var currency = "CHF"
    var payment_method = "Unbekannt"
    var payment_card: String?
    var store_category: String?
    var notes = ""
    var raw_ocr_text = ""
    var items: [DraftItem] = []
    var extra_fields: [String: String?]?
    var recognition_warning: String?
}
struct DraftItem: Identifiable, Hashable {
    var id = UUID()
    var name = ""
    var quantity: Decimal = 1
    var unit = "Stk"
    var unit_price: Decimal = 0
    var total_price: Decimal = 0
    var tags: [String] = ["Diverses"]
    var subcategory: String?
}
struct Profile: Identifiable, Codable, Hashable {
    var id: UUID = UUID()
    var display_name: String?
    var piggy_code: String = ""
    var name: String { display_name?.isEmpty == false ? display_name! : piggy_code }
}
struct Friend: Identifiable, Hashable {
    var id: UUID
    var profile: Profile
    var status: String
    var outgoing = false
}
struct Project: Identifiable, Codable, Hashable {
    var id = UUID()
    var name = ""
    var emoji = "📁"
    var description: String?
    var currency = "CHF"
    var created_by: UUID?
    var project_members: [ProjectMember]?
}
struct ProjectMember: Identifiable, Codable, Hashable {
    var id: UUID
    var project_id: UUID
    var user_id: UUID
    var profile: Profile?
}
struct ExpenseSplit: Identifiable, Codable, Hashable {
    var id: UUID
    var receipt_id: UUID
    var payer_id: UUID
    var total_amount: Decimal
    var currency: String
    var note: String?
    var split_participants: [SplitParticipant]
    var split_exclusions: [SplitExclusion]?
    var payer_profile: Profile?
    var receipt: SplitReceipt?
}
struct SplitReceipt: Codable, Hashable { var store_name: String; var receipt_date: String? }
struct SplitParticipant: Identifiable, Codable, Hashable {
    var id: UUID
    var split_id: UUID
    var user_id: UUID
    var amount: Decimal
    var settled: Bool
    var settled_at: String?
    var profile: Profile?
}
struct SplitExclusion: Identifiable, Codable, Hashable {
    var id: UUID
    var split_id: UUID
    var user_id: UUID
    var receipt_item_id: UUID
}
struct OpenBalance: Identifiable, Hashable {
    var id: UUID
    var otherName: String
    var amount: Decimal
    var currency: String
    var owedToMe: Bool
    var receiptName: String
}
struct NativeCategoryTotal: Codable, Hashable, Identifiable {
    var category: String
    var amount: Decimal
    var share: Double
    var fromBank: Decimal
    var fromReceipts: Decimal
    var id: String { category }
}
struct NativeFinanceSummary: Codable, Hashable {
    var month: String
    var currency: String
    var income: Decimal
    var expenses: Decimal
    var net: Decimal
    var byCategory: [NativeCategoryTotal]
    var receiptCoverage: Double?
    var bankExpenses: Decimal
    var receiptOnlyExpenses: Decimal
    var transfers: Decimal
    var uncategorizedCount: Int
    var unmatchedExpenseCount: Int
}

/// JSON body values preserve decimal money and can explicitly clear nullable database fields.
enum JSONValue: Codable, Sendable {
    case string(String), number(Decimal), bool(Bool), object([String: JSONValue]), array([JSONValue]), null
    init(from decoder: Decoder) throws {
        let box = try decoder.singleValueContainer()
        if box.decodeNil() { self = .null }
        else if let value = try? box.decode(Bool.self) { self = .bool(value) }
        else if let value = try? box.decode(Decimal.self) { self = .number(value) }
        else if let value = try? box.decode(String.self) { self = .string(value) }
        else if let value = try? box.decode([String: JSONValue].self) { self = .object(value) }
        else { self = .array(try box.decode([JSONValue].self)) }
    }
    func encode(to encoder: Encoder) throws {
        var box = encoder.singleValueContainer()
        switch self {
        case .string(let value): try box.encode(value)
        case .number(let value): try box.encode(value)
        case .bool(let value): try box.encode(value)
        case .object(let value): try box.encode(value)
        case .array(let value): try box.encode(value)
        case .null: try box.encodeNil()
        }
    }
    static func optional(_ value: String?) -> JSONValue { value.map(JSONValue.string) ?? .null }
    static func uuid(_ value: UUID) -> JSONValue { .string(value.uuidString.lowercased()) }
}

enum PiggyFailure: LocalizedError {
    case configuration, signedOut, validation(String), service(String)
    var errorDescription: String? {
        switch self {
        case .configuration: return "Piggy ist noch nicht vollständig konfiguriert. Bitte wende dich an den Support."
        case .signedOut: return "Bitte melde dich erneut an."
        case .validation(let message), .service(let message): return message
        }
    }
}

extension ReceiptItem {
    private enum CodingKeys: String, CodingKey {
        case id, receipt_id, name, quantity, unit, unit_price, total_price, tags, subcategory, is_adjustment
    }
    init(from decoder: Decoder) throws {
        let box = try decoder.container(keyedBy: CodingKeys.self)
        id = try box.decode(UUID.self, forKey: .id)
        receipt_id = try box.decodeIfPresent(UUID.self, forKey: .receipt_id)
        name = try box.decodeIfPresent(String.self, forKey: .name) ?? "Artikel"
        quantity = try box.decodeIfPresent(Decimal.self, forKey: .quantity)
        unit = try box.decodeIfPresent(String.self, forKey: .unit)
        unit_price = try box.decodeIfPresent(Decimal.self, forKey: .unit_price)
        total_price = try box.decodeIfPresent(Decimal.self, forKey: .total_price)
        tags = try box.decodeIfPresent([String].self, forKey: .tags) ?? []
        subcategory = try box.decodeIfPresent(String.self, forKey: .subcategory)
        is_adjustment = try box.decodeIfPresent(Bool.self, forKey: .is_adjustment)
    }
}
extension Project {
    private enum CodingKeys: String, CodingKey { case id, name, emoji, description, currency, created_by, project_members }
    init(from decoder: Decoder) throws {
        let box = try decoder.container(keyedBy: CodingKeys.self)
        id = try box.decode(UUID.self, forKey: .id)
        name = try box.decode(String.self, forKey: .name)
        emoji = try box.decodeIfPresent(String.self, forKey: .emoji) ?? "📁"
        description = try box.decodeIfPresent(String.self, forKey: .description)
        currency = try box.decodeIfPresent(String.self, forKey: .currency) ?? "CHF"
        created_by = try box.decodeIfPresent(UUID.self, forKey: .created_by)
        project_members = try box.decodeIfPresent([ProjectMember].self, forKey: .project_members)
    }
}


extension Receipt {
    var searchText: String {
        ([store_name, payment_method ?? "", payment_card ?? "", notes ?? "", raw_ocr_text ?? ""] +
         receipt_items.map(\.name) + (extra_fields ?? [:]).values.compactMap { $0 }).joined(separator: " ")
    }
}
