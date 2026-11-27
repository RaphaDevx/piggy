import Foundation

struct CompanySummary: Decodable, Identifiable {
    let id: UUID; let name: String; let role: String; let currency: String; let meal_limit: Decimal?
    var canReview: Bool { role == "admin" || role == "accountant" }
}
struct CompanyList: Decodable { let companies: [CompanySummary] }
struct CompanyInvitation: Decodable { let company_id: UUID; let invite_code: String? }
struct CompanyMember: Decodable, Identifiable {
    let id: UUID; let user_id: UUID; let role: String; let name: String
}
struct CompanyWorkspace: Decodable {
    let company: CompanySummary; let members: [CompanyMember]; let submissions: [CompanySubmission]
}
struct CompanySnapshot: Decodable {
    let receipt_id: UUID; let store_name: String?; let receipt_date: String?
    let total_amount: Decimal; let currency: String
    let payment_method: String?; let payment_card: String?; let raw_ocr_text: String?
    let extra_fields: JSONValue?; let receipt_items: [JSONValue]
}
struct CompanyEvent: Decodable, Identifiable {
    let id: UUID; let event_type: String; let actor_id: UUID?; let detail: JSONValue; let created_at: String
    var title: String {
        switch event_type {
        case "submitted": return "Eingereicht"
        case "archived": return "Original archiviert"
        case "approved": return "Freigegeben"
        case "rejected": return "Abgelehnt"
        case "withdrawn": return "Zurückgezogen"
        case "export_prepared": return "Übergabepaket vorbereitet"
        default: return event_type
        }
    }
}
struct CompanySubmission: Decodable, Identifiable {
    let id: UUID; let submitter_id: UUID; let category: String; let note: String?; let status: String
    let submitted_at: String; let reviewed_by: UUID?; let reviewed_at: String?
    let snapshot: CompanySnapshot; let snapshot_hash: String; let image_state: String
    let image_sha256: String?; let export_prepared: Bool; let events: [CompanyEvent]
    var statusTitle: String {
        switch status {
        case "pending": return "Zur Prüfung"
        case "approved": return "Freigegeben"
        case "rejected": return "Abgelehnt"
        case "withdrawn": return "Zurückgezogen"
        case "reimbursed": return "Erstattet (Altbestand)"
        default: return status
        }
    }
    func canReview(user: UUID?, company: CompanySummary) -> Bool {
        company.canReview && user != nil && user != submitter_id && status == "pending"
    }
}
struct CompanySubmitReply: Decodable { let submission_id: UUID; let existing: Bool }
struct CompanyExportReply: Decodable { let export_id: UUID; let payload: JSONValue }
struct CompanyOK: Decodable { let ok: Bool }
struct CompanyArchiveReply: Decodable { let archived: Bool }
struct CompanyImageReply: Decodable { let url: URL }
