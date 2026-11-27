import Foundation
import Supabase

// Kept off until staging deployment, retention review and company access checks pass.
enum CompanyFeature {
    static var enabled: Bool { Bundle.main.object(forInfoDictionaryKey: "PIGGY_COMPANIES") as? String == "YES" }
}
@MainActor
extension PiggyStore {
    func companyAction<T: Decodable>(_ action: String, input: [String: JSONValue] = [:]) async throws -> T {
        guard CompanyFeature.enabled else { throw PiggyFailure.configuration }
        let uid = try requireUser()
        let value: T = try await requireClient().rpc("company_workspace", params: ["p_action": JSONValue.string(action), "p_input": .object(input)]).execute().value
        guard userID == uid else { throw PiggyFailure.signedOut }
        return value
    }
    func archiveCompanySubmission(_ id: UUID) async throws {
        guard CompanyFeature.enabled else { throw PiggyFailure.configuration }
        let uid = try requireUser()
        let reply: CompanyArchiveReply = try await requireClient().functions.invoke("company-expenses", options: .init(body: ["op":"archive", "submission_id":id.uuidString]))
        guard userID == uid else { throw PiggyFailure.signedOut }
        guard reply.archived else { throw PiggyFailure.configuration }
    }
    func companyImage(_ id: UUID) async throws -> URL {
        guard CompanyFeature.enabled else { throw PiggyFailure.configuration }
        let uid = try requireUser()
        let reply: CompanyImageReply = try await requireClient().functions.invoke("company-expenses", options: .init(body: ["op":"image", "submission_id":id.uuidString]))
        guard userID == uid else { throw PiggyFailure.signedOut }
        return reply.url
    }
}
