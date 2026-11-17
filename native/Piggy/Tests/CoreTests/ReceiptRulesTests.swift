import XCTest
@testable import PiggyCore

final class ReceiptRulesTests: XCTestCase {
    func testDuplicateUsesMerchantCurrencyDateAndRoundedMoney() {
        var draft = ReceiptDraft(); draft.store_name = "MIGRÓS"; draft.total_amount = Decimal(string: "12.345")!; draft.receipt_date = "2026-11-17"
        var receipt = Receipt(); receipt.store_name = "Migros"; receipt.total_amount = Decimal(string: "12.35")!; receipt.receipt_date = draft.receipt_date
        XCTAssertEqual(ReceiptRules.duplicates(draft, in: [receipt]).count, 1)
        receipt.currency = "EUR"
        XCTAssertTrue(ReceiptRules.duplicates(draft, in: [receipt]).isEmpty)
    }
    func testSplitPreservesCentsWithDiscountAndExclusions() throws {
        let a = UUID(), b = UUID(), c = UUID()
        var first = ReceiptItem(); first.name = "A"; first.total_price = 10
        var discount = ReceiptItem(); discount.name = "Rabatt"; discount.total_price = -1
        let result = try ReceiptRules.shares(items: [first, discount], total: Decimal(string: "9.01")!, participants: [a,b,c], exclusions: [b:[first.id]])
        XCTAssertEqual(result.values.reduce(0,+), Decimal(string: "9.01")!)
        XCTAssertEqual(result.count, 3)
    }
    func testAllPeopleExcludedRejectsLossOfMoney() {
        let a = UUID(); var item = ReceiptItem(); item.total_price = 3
        XCTAssertThrowsError(try ReceiptRules.shares(items: [item], total: 3, participants: [a], exclusions: [a:[item.id]]))
    }
    func testOfflineParserDoesNotInventArticles() {
        let result = LocalReceiptParser.parse("Migros\n17.11.2026\nTOTAL CHF 12.35")
        XCTAssertEqual(result.store_name, "Migros")
        XCTAssertEqual(result.receipt_date, "2026-11-17")
        XCTAssertEqual(result.total_amount, Decimal(string: "12.35"))
        XCTAssertTrue(result.items.isEmpty)
    }
    func testDraftRejectsImpossibleCalendarDate() {
        var draft = ReceiptDraft(); draft.store_name = "Migros"; draft.receipt_date = "2026-02-31"
        XCTAssertThrowsError(try ReceiptRules.validate(draft))
    }
    func testLegacyNullTagsDecodeWithoutLosingReceipt() throws {
        let id = UUID()
        let data = Data("{\"id\":\"\(id)\",\"name\":\"Brot\",\"tags\":null}".utf8)
        let item = try JSONDecoder().decode(ReceiptItem.self, from: data)
        XCTAssertEqual(item.id, id)
        XCTAssertTrue(item.tags.isEmpty)
    }
    func testQuarterAndWeekCrossYearUseExclusiveBounds() throws {
        let format = ISO8601DateFormatter()
        let date = try XCTUnwrap(format.date(from: "2026-01-01T12:00:00Z"))
        let quarter = FinancePeriod.quarter.range(containing: date)
        XCTAssertEqual(quarter.start, "2026-01-01")
        XCTAssertEqual(quarter.endExclusive, "2026-04-01")
        let week = FinancePeriod.week.range(containing: date)
        XCTAssertEqual(week.start, "2025-12-29")
        XCTAssertEqual(week.endExclusive, "2026-01-05")
    }
}
