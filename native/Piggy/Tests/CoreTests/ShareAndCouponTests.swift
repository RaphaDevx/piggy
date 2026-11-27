import XCTest
@testable import PiggyCore

final class ShareAndCouponTests: XCTestCase {
    func testInboxPublicationAndAccountIsolation() throws {
        let root = FileManager.default.temporaryDirectory.appendingPathComponent(UUID().uuidString)
        defer { try? FileManager.default.removeItem(at: root) }
        let alice = UUID(), bob = UUID()
        let record = try ShareInbox.enqueue(Data("%PDF-1.4\nOriginal".utf8), kind: .pdf, name: "../Quittung.pdf", owner: alice, at: root)
        XCTAssertEqual(try ShareInbox.list(owner: alice, at: root).map(\.id), [record.id])
        XCTAssertTrue(try ShareInbox.list(owner: bob, at: root).isEmpty)
        XCTAssertThrowsError(try ShareInbox.data(record, owner: bob, at: root))
        let forged = SharedImport(id: record.id, owner: nil, createdAt: record.createdAt, kind: .pdf, name: "forged")
        XCTAssertThrowsError(try ShareInbox.data(forged, owner: bob, at: root))
        XCTAssertThrowsError(try ShareInbox.remove(forged, owner: bob, at: root))
        XCTAssertThrowsError(try ShareInbox.remove(record, owner: bob, at: root))
        XCTAssertEqual(try ShareInbox.data(record, owner: alice, at: root), Data("%PDF-1.4\nOriginal".utf8))
        XCTAssertEqual(try FileManager.default.contentsOfDirectory(at: root, includingPropertiesForKeys: nil).count, 1)
        try ShareInbox.remove(record, owner: alice, at: root)
        XCTAssertTrue(try ShareInbox.list(owner: alice, at: root).isEmpty)
    }
    func testRejectsFakePDFOversizeAndUnsafeWebSchemes() throws {
        let root = FileManager.default.temporaryDirectory.appendingPathComponent(UUID().uuidString)
        defer { try? FileManager.default.removeItem(at: root) }
        XCTAssertThrowsError(try ShareInbox.enqueue(Data("not a pdf".utf8), kind: .pdf, name: "x.pdf", owner: nil, at: root))
        XCTAssertThrowsError(try ShareInbox.enqueue(Data(repeating: 0, count: ShareInbox.maxBytes + 1), kind: .image, name: "huge", owner: nil, at: root))
        for raw in ["javascript:alert(1)", "http://example.com", "https://user:secret@example.com/"] {
            XCTAssertNil(MerchantBrand.catalogURL(raw))
            XCTAssertThrowsError(try ShareInbox.enqueue(Data(raw.utf8), kind: .web, name: "link", owner: nil, at: root))
        }
    }
    func testCouponValidThroughEndOfPrintedDateAndIndependentUsedState() {
        var calendar = Calendar(identifier: .gregorian); calendar.timeZone = TimeZone(secondsFromGMT: 0)!
        var coupon = SavedCoupon(title: "20 %", merchant: "Laden", code: "00123", symbology: "", expiry: "2026-11-27", note: "")
        let before = calendar.date(from: DateComponents(year: 2026, month: 11, day: 27, hour: 23, minute: 59))!
        let after = calendar.date(from: DateComponents(year: 2026, month: 11, day: 28))!
        XCTAssertFalse(coupon.expired(on: before, calendar: calendar))
        XCTAssertTrue(coupon.expired(on: after, calendar: calendar))
        coupon.used = true
        XCTAssertEqual(coupon.code, "00123"); XCTAssertFalse(coupon.expired(on: before, calendar: calendar))
    }
    func testInternalOriginalPDFReferencesDoNotAppearAsPrintedMerchantInfo() {
        let extra: [String: String?] = ["_piggy_original_pdf_test":"uid/original.pdf", "cashier_name":"Anna"]
        let details = ReceiptMetadata.details(extra, raw: nil)
        XCTAssertEqual(details.map(\.key), ["cashier_name"])
    }
}
