import XCTest
@testable import PiggyCore

final class CompanyWorkspaceTests: XCTestCase {
    private let uid = UUID(uuidString:"11111111-1111-4111-8111-111111111111")!
    private func submission(status:String="pending") throws -> CompanySubmission {
        let raw = """
        {"id":"aaaaaaaa-aaaa-4aaa-8aaa-aaaaaaaaaaaa","submitter_id":"11111111-1111-4111-8111-111111111111","category":"meal","note":"Kundentermin","status":"\(status)","submitted_at":"2026-11-27T12:00:00Z","snapshot":{"receipt_id":"bbbbbbbb-bbbb-4bbb-8bbb-bbbbbbbbbbbb","store_name":"Restaurant","receipt_date":"2026-11-27","total_amount":24.50,"currency":"CHF","receipt_items":[],"extra_fields":{"Kasse":4}},"snapshot_hash":"hash","image_state":"archived","export_prepared":true,"events":[{"id":"cccccccc-cccc-4ccc-8ccc-cccccccccccc","event_type":"archived","detail":{"sha256":"hash"},"created_at":"2026-11-27T12:01:00Z"}]}
        """
        return try JSONDecoder().decode(CompanySubmission.self,from:Data(raw.utf8))
    }
    func testFrozenAmountAndSystemArchiveEventDecode() throws {
        let value=try submission(); XCTAssertEqual(value.snapshot.total_amount,Decimal(string:"24.50"))
        XCTAssertNil(value.events.first?.actor_id); XCTAssertEqual(value.events.first?.title,"Original archiviert")
        XCTAssertEqual(value.statusTitle,"Zur Prüfung")
    }
    func testSelfApprovalRemainsUnavailableForAdministrators() throws {
        let company=CompanySummary(id:UUID(),name:"Firma",role:"admin",currency:"CHF",meal_limit:nil)
        XCTAssertFalse(try submission().canReview(user:uid,company:company))
        XCTAssertTrue(try submission().canReview(user:UUID(),company:company))
        XCTAssertFalse(try submission(status:"approved").canReview(user:UUID(),company:company))
        XCTAssertFalse(try submission().canReview(user:nil,company:company))
    }
    func testExportPreparationDoesNotBecomePaidStatus() throws {
        let value=try submission(status:"approved")
        XCTAssertTrue(value.export_prepared); XCTAssertEqual(value.statusTitle,"Freigegeben")
        let member=CompanySummary(id:UUID(),name:"Firma",role:"member",currency:"CHF",meal_limit:nil)
        XCTAssertFalse(value.canReview(user:UUID(),company:member))
    }
}
