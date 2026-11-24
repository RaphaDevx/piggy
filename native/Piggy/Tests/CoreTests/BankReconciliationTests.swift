import XCTest
@testable import PiggyCore

final class BankReconciliationTests: XCTestCase {
    private func transaction(_ date: String, _ amount: Decimal, status: String = "unmatched", currency: String = "CHF") -> BankTransaction {
        BankTransaction(id: UUID(), statement_id: UUID(), booking_date: date, amount: amount, currency: currency, description: "Kauf", match_status: status)
    }
    private func receipt(_ date: String, _ total: Decimal, currency: String = "CHF") -> Receipt {
        var value = Receipt(); value.store_name = "Migros"; value.receipt_date = date; value.total_amount = total; value.currency = currency
        return value
    }

    func testMatchedPairIsConfirmedWithDifference() {
        let tx = transaction("2026-11-10", -25, status: "matched"), r = receipt("2026-11-10", Decimal(string: "22.50")!)
        let match = ReceiptMatch(id: UUID(), transaction_id: tx.id, receipt_id: r.id, score: 1, match_type: "manual")
        let report = Reconciliation.build(transactions: [tx], matches: [match], receipts: [r], suggestions: [])
        XCTAssertEqual(report.confirmed.count, 1)
        XCTAssertEqual(report.confirmed[0].difference, Decimal(string: "2.5")!)
        XCTAssertTrue(report.bankOnly.isEmpty); XCTAssertTrue(report.receiptOnly.isEmpty)
    }
    func testUnmatchedExpenseWithoutReceiptIsBankOnlyButIncomeIsNot() {
        let expense = transaction("2026-11-10", -10), income = transaction("2026-11-11", 3000), ignored = transaction("2026-11-12", -5, status: "ignored")
        let report = Reconciliation.build(transactions: [expense, income, ignored], matches: [], receipts: [], suggestions: [])
        XCTAssertEqual(report.bankOnly.map(\.id), [expense.id])
        XCTAssertEqual(report.ignoredCount, 1)
    }
    func testReceiptInsideStatementWindowIsReceiptOnlyOutsideIsNotCounted() {
        let tx1 = transaction("2026-11-01", -1), tx2 = transaction("2026-11-30", -1)
        let inside = receipt("2026-11-15", 9), outside = receipt("2026-09-01", 9), otherCurrency = receipt("2026-11-15", 9, currency: "EUR")
        let report = Reconciliation.build(transactions: [tx1, tx2], matches: [], receipts: [inside, outside, otherCurrency], suggestions: [])
        XCTAssertEqual(report.receiptOnly.map(\.id), [inside.id])
        XCTAssertEqual(report.receiptsOutsideStatements, 2)
    }
    func testSuggestionRemovesBothSidesFromOpenListsAndIsNeverConfirmed() {
        let tx = transaction("2026-11-10", -25), r = receipt("2026-11-10", 22)
        let suggestion = MatchSuggestion(id: UUID(), transaction_id: tx.id, receipt_id: r.id, kind: "tip", diff_amount: 3, score: Decimal(string: "0.9"), status: "open", note: nil)
        let report = Reconciliation.build(transactions: [tx], matches: [], receipts: [r], suggestions: [suggestion])
        XCTAssertEqual(report.suggestions.count, 1)
        XCTAssertTrue(report.confirmed.isEmpty); XCTAssertTrue(report.bankOnly.isEmpty); XCTAssertTrue(report.receiptOnly.isEmpty)
        XCTAssertEqual(report.suggestions[0].suggestion.kindTitle, "Trinkgeld")
    }
    func testRejectedSuggestionIsIgnored() {
        let tx = transaction("2026-11-10", -25), r = receipt("2026-11-10", 22)
        let suggestion = MatchSuggestion(id: UUID(), transaction_id: tx.id, receipt_id: r.id, kind: "tip", diff_amount: 3, score: 1, status: "rejected", note: nil)
        let report = Reconciliation.build(transactions: [tx], matches: [], receipts: [r], suggestions: [suggestion])
        XCTAssertTrue(report.suggestions.isEmpty)
        XCTAssertEqual(report.bankOnly.count, 1); XCTAssertEqual(report.receiptOnly.count, 1)
    }
    func testMaskingRemovesIbanAndCardNumbers() {
        XCTAssertFalse(Masking.sanitize("Zahlung an CH93 0076 2011 6238 5295 7 Miete").contains("0076"))
        XCTAssertFalse(Masking.sanitize("Karte 4111 1111 1111 1111 Coop").contains("4111"))
        XCTAssertEqual(Masking.sanitize("Migros Bern"), "Migros Bern")
    }
    func testShareTextHasNoAccountNumbers() {
        var tx = transaction("2026-11-10", -25); tx.description = "Miete CH93 0076 2011 6238 5295 7"
        let report = Reconciliation.build(transactions: [tx], matches: [], receipts: [], suggestions: [])
        let text = report.shareText(currency: "CHF")
        XCTAssertTrue(text.contains("Quittung fehlt")); XCTAssertFalse(text.contains("0076"))
    }
    func testStatementFileValidationAndSafeName() throws {
        XCTAssertEqual(try StatementFile.validate(fileName: "Auszug März.PDF", byteCount: 10), "application/pdf")
        XCTAssertThrowsError(try StatementFile.validate(fileName: "foto.png", byteCount: 10))
        XCTAssertThrowsError(try StatementFile.validate(fileName: "a.pdf", byteCount: 0))
        XCTAssertThrowsError(try StatementFile.validate(fileName: "a.pdf", byteCount: StatementFile.maxBytes + 1))
        XCTAssertEqual(StatementFile.safeName("Auszug März 2026.pdf"), "Auszug_Marz_2026.pdf")
        XCTAssertEqual(StatementFile.safeName("___"), "auszug")
    }
    func testStatementProgressAndRunningState() {
        var s = BankStatement(id: UUID(), account_label: "Konto", account_type: "debit", status: "processing", stage: "extracting", page_count: 4, pages_done: 1, created_at: "")
        XCTAssertTrue(s.isRunning); XCTAssertEqual(s.progress, 0.25)
        s.stage = "done"; s.status = "done"; XCTAssertFalse(s.isRunning)
    }
}
