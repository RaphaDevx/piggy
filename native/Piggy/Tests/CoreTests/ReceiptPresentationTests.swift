import XCTest
@testable import PiggyCore

final class ReceiptPresentationTests: XCTestCase {
    func testEditorUsesPointWithoutChangingDecimalValue() {
        XCTAssertEqual(AmountInput.editorText(Decimal(string: "12.5")!, minimumFractionDigits: 2), "12.50")
        XCTAssertEqual(AmountInput.editorText(Decimal(string: "1.2345")!, minimumFractionDigits: 2), "1.2345")
        XCTAssertEqual(AmountInput.editorText(2), "2")
        XCTAssertEqual(AmountInput.editorText(-2, minimumFractionDigits: 2), "-2.00")
        XCTAssertEqual(AmountInput.parse("0,10"), AmountInput.parse("0.10"))
        XCTAssertEqual(AmountInput.parse("0.10")! + AmountInput.parse("0.20")!, Decimal(string: "0.30"))
    }
    func testQuantityKeepsActualUnitAndDoesNotInventPieces() {
        XCTAssertEqual(ReceiptItemPresentation.quantity(2, unit: "Stk"), "2 Stk")
        XCTAssertEqual(ReceiptItemPresentation.quantity(Decimal(string: "0.5"), unit: "kg"), "0.5 kg")
        XCTAssertEqual(ReceiptItemPresentation.quantity(1, unit: "L"), "1 L")
        XCTAssertEqual(ReceiptItemPresentation.quantity(nil, unit: nil), "–")
        XCTAssertEqual(ReceiptItemPresentation.quantity(2, unit: nil), "2")
    }
    func testCashierNumberNameRegisterAndTerminalAreDistinct() {
        let raw = "Coop\nFiliale: St. Gallen Marktgasse\nKasse:4\nKassierer: 17 Anna Meier\nTID: 876543\nUID: CHE-123.456.789 MWST"
        let fields = ReceiptMetadata.enriched(nil, raw: raw)
        XCTAssertEqual(fields["cashier_number"] ?? nil, "17")
        XCTAssertEqual(fields["cashier_name"] ?? nil, "Anna Meier")
        XCTAssertEqual(fields["register_number"] ?? nil, "4")
        XCTAssertEqual(fields["payment_terminal"] ?? nil, "876543")
        XCTAssertEqual(fields["store_branch"] ?? nil, "St. Gallen Marktgasse")
        XCTAssertEqual(fields["vat_number"] ?? nil, "CHE-123.456.789 MWST")
        XCTAssertNil(fields["printed:Kassierer"])
    }
    func testCashierFourStaysNumberAndNoNameIsInvented() {
        let fields = ReceiptMetadata.enriched(nil, raw: "Kassierer 4\nMwSt: 2.6% 0.10\nTotal 4.00")
        XCTAssertEqual(fields["cashier_number"] ?? nil, "4")
        XCTAssertNil(fields["cashier_name"])
        XCTAssertNil(fields["vat_number"])
        XCTAssertTrue(ReceiptMetadata.details(nil, raw: "Total 4.00").isEmpty)
    }
    func testUnknownAndRepeatedPrintedFieldsSurviveWithoutRepeatedEnrichment() {
        let raw = "Spezialcode: ABC-999\nSteuer: 2.6%\nSteuer: 8.1%\nBeliebige unbeschriftete Zeile"
        let fields = ReceiptMetadata.enriched(nil, raw: raw)
        XCTAssertEqual(fields["printed:Spezialcode"] ?? nil, "ABC-999")
        XCTAssertEqual(fields["printed:Steuer"] ?? nil, "2.6%")
        XCTAssertEqual(fields["printed:Steuer (2)"] ?? nil, "8.1%")
        XCTAssertEqual(ReceiptMetadata.enriched(fields, raw: raw), fields)
        XCTAssertEqual(LocalReceiptParser.parse(raw).raw_ocr_text, raw)
    }
    func testReviewedAndExplicitlyClearedValuesArePreserved() {
        let existing: [String: String?] = ["cashier_name": "Geprüfter Name", "cashier_number": nil]
        let fields = ReceiptMetadata.enriched(existing, raw: "Kassierer: 4 Anders\nKasse: 2")
        XCTAssertEqual(fields["cashier_name"] ?? nil, "Geprüfter Name")
        XCTAssertTrue(fields.keys.contains("cashier_number"))
        XCTAssertNil(fields["cashier_number"] ?? nil)
    }
    func testNumericAndUnknownReceiptExtrasDoNotDropReceiptOrFullText() throws {
        let id = UUID().uuidString
        let raw = "Erste Zeile\nKassierer 4\nLetzte Zeile ohne eigenes Feld"
        let payload: [String: Any] = ["id": id, "user_id": id, "store_name": "Rivella Shop", "currency": "CHF",
                                     "raw_ocr_text": raw, "extra_fields": ["cashier_number": 4, "custom": ["data": "XYZ"]]]
        let receipt = try JSONDecoder().decode(Receipt.self, from: JSONSerialization.data(withJSONObject: payload))
        XCTAssertEqual(receipt.extra_fields?["cashier_number"] ?? nil, "4")
        XCTAssertEqual(receipt.extra_fields?["custom"] ?? nil, "{\"data\":\"XYZ\"}")
        XCTAssertEqual(receipt.raw_ocr_text, raw)
        XCTAssertEqual(try JSONDecoder().decode(Receipt.self, from: JSONEncoder().encode(receipt)).raw_ocr_text, raw)
    }
}
