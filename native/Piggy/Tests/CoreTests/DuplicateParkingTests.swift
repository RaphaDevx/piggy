import XCTest
@testable import PiggyCore

final class DuplicateParkingTests: XCTestCase {
    func testSavedDuplicatesRespectOwnerCurrencyAndSeparateDecision() {
        var a = Receipt(store_name: "Mïgros", receipt_date: "2026-11-27", total_amount: 12, currency: "CHF")
        var b = a; b.id = UUID(); b.store_name = "MIGROS!"
        var other = b; other.id = UUID(); other.user_id = UUID()
        var euro = b; euro.id = UUID(); euro.currency = "EUR"
        XCTAssertEqual(ReceiptRules.duplicatePairs([a,b,other,euro], dismissed: []).count, 1)
        XCTAssertTrue(ReceiptRules.duplicatePairs([a,b], dismissed: [DismissedDuplicate(receipt_a: b.id, receipt_b: a.id)]).isEmpty)
        a.total_amount = nil
        XCTAssertTrue(ReceiptRules.duplicatePairs([a,b], dismissed: []).isEmpty)
    }
    func testNewScanDuplicateRequiresRealDateAndAmount() {
        var a = Receipt(store_name: "Coop", receipt_date: "2026-11-27", total_amount: 5, currency: "CHF")
        var draft = ReceiptDraft(store_name: "COOP", receipt_date: "2026-11-27", total_amount: 5)
        XCTAssertEqual(ReceiptRules.duplicates(draft, in: [a]).count, 1)
        draft.receipt_date = "2026-11-26"
        XCTAssertTrue(ReceiptRules.duplicates(draft, in: [a]).isEmpty)
        a.receipt_date = nil
        XCTAssertTrue(ReceiptRules.duplicates(draft, in: [a]).isEmpty)
        XCTAssertEqual(LocalReceiptParser.parse("Coop\nTOTAL 5.00").receipt_date, "")
    }
    func testParkingSameDayAndDecimalRates() throws {
        let p = ParkingDetails.parse("Parkhaus\nEinfahrt: 27.11.2026 09:10\nAusfahrt: 27.11.2026 10:40\nKennzeichen: SG 12345", receiptDate: "2026-11-27")
        XCTAssertEqual(p.minutes, 90)
        XCTAssertEqual(p.plate, "SG 12345")
        XCTAssertEqual(p.perMinute(total: 9), Decimal(string: "0.1"))
        XCTAssertEqual(try XCTUnwrap(p.perMinute(total: 9)) * 60, 6)
    }
    func testParkingOvernightRequiresExplicitDates() {
        let dates = ParkingDetails.parse("Parking\nAnkunft: 26.11.2026 23:30\nAbfahrt: 27.11.2026 00:30", receiptDate: "2026-11-27")
        XCTAssertEqual(dates.minutes, 60)
        let ambiguous = ParkingDetails.parse("Parkhaus\nAnkunft: 23:30\nAbfahrt: 00:30", receiptDate: "2026-11-27")
        XCTAssertNil(ambiguous.minutes)
        XCTAssertNil(ambiguous.perMinute(total: 5))
    }
    func testParkingDoesNotUsePaymentTimeOrInferNonParkingReceipt() {
        XCTAssertFalse(ParkingDetails.parse("Restaurant\nAnkunft: 12:30\nAbfahrt: 13:30", receiptDate: "2026-11-27").hasDetails)
        let p = ParkingDetails.parse("Parkhaus\nBezahlt um 12:30\nTOTAL 5.00", receiptDate: "2026-11-27")
        XCTAssertTrue(p.arrival.isEmpty)
        XCTAssertTrue(p.departure.isEmpty)
        XCTAssertNil(p.minutes)
        XCTAssertNil(ParkingDetails(arrival: "2026-02-30 12:00", departure: "2026-03-01 13:00").minutes)
    }
    func testPrintedDurationWinsAndClearedMetadataDoesNotReturn() {
        let raw = "Parkhaus\nEinfahrt: 08:00\nAusfahrt: 10:00\nParkdauer: 90 Minuten"
        var p = ParkingDetails.read(extras: ["receipt_number": "A123"], raw: raw, receiptDate: "2026-11-27")
        XCTAssertEqual(p.minutes, 90)
        p.arrival = ""
        let persisted = p.applying(to: ["receipt_number": "A123"])
        XCTAssertEqual((persisted["receipt_number"] ?? nil), "A123")
        XCTAssertEqual(ParkingDetails.read(extras: persisted, raw: raw, receiptDate: "2026-11-27").arrival, "")
    }
    func testOldReceiptExtrasDecodeAndAreSearchableWithoutParking() throws {
        let id = UUID().uuidString
        let data = Data("""
        {"id":"\(id)","user_id":"\(id)","store_name":"Coop","currency":"CHF","created_at":"2026-11-27","receipt_items":[],"extra_fields":{"receipt_number":"BON-123","store_address":"Marktgasse"}}
        """.utf8)
        let receipt = try JSONDecoder().decode(Receipt.self, from: data)
        XCTAssertTrue(receipt.searchText.contains("BON-123"))
        XCTAssertTrue(receipt.searchText.contains("Marktgasse"))
        XCTAssertFalse(ParkingDetails.read(extras: receipt.extra_fields, raw: nil, receiptDate: receipt.receipt_date).hasDetails)
    }
}
