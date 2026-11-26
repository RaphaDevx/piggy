import XCTest
@testable import PiggyCore

final class PaymentEditingTests: XCTestCase {
    func testAmountEditingPreservesDecimalSeparatorsAndIntermediateValues() {
        for input in ["12.50", "12,50", " 12,50 "] {
            XCTAssertEqual(AmountInput.parse(input), Decimal(string: "12.5"))
        }
        XCTAssertEqual(AmountInput.parse("12,"), 12)
        XCTAssertEqual(AmountInput.parse("0,"), 0)
        XCTAssertEqual(AmountInput.parse(",50"), Decimal(string: "0.5"))
        XCTAssertEqual(AmountInput.parse("-2.50"), Decimal(string: "-2.5"))
        XCTAssertEqual(AmountInput.text(Decimal(string: "12.5")!, locale: Locale(identifier: "de_DE")), "12,5")
    }
    func testInvalidAmountsNeverBecomeZeroOrTruncatedValues() {
        for input in ["", "-", ",", "12abc", "1,234.56", "1.2.3", "NaN", "12.12345"] {
            XCTAssertNil(AmountInput.parse(input), input)
        }
        var draft = ReceiptDraft(); draft.store_name = "Coop"; draft.total_amount = .nan
        XCTAssertThrowsError(try ReceiptRules.validate(draft))
        draft.total_amount = 10
        draft.items = [DraftItem(name: "Brot", total_price: .nan)]
        XCTAssertThrowsError(try ReceiptRules.validate(draft))
    }
    private func card(_ suffix: String = "5327", _ label: String? = "Mastercard · Privat") -> ProfileCard {
        ProfileCard(id: UUID(), user_id: UUID(), last_four: suffix, label: label)
    }
    func testMaskedAndBareCardFormatsNormalizeToSameStoredCard() {
        let saved = card()
        for text in ["5327", "****5327", "XXXX 5327", "XXXX-5327", "MasterCard **** 5327", "Mastercard ···· 5327"] {
            XCTAssertEqual(PaymentCard.normalized(text, method: nil, cards: [saved]), "Mastercard ···· 5327", text)
            XCTAssertEqual(PaymentCard.display(text, method: nil, cards: [saved]), saved.displayName, text)
        }
    }
    func testCardMatchingDoesNotGuessNetworkFromSuffixOrFullPAN() {
        XCTAssertEqual(PaymentCard.normalized("5327", method: nil, cards: []), "Karte ···· 5327")
        for text in ["1234567890125327", "CHF 15.5327", "26.11.2026", "Terminal 1234 5327", "5327 Visa"] {
            XCTAssertNil(PaymentCard.lastFour(in: text), text)
        }
        XCTAssertThrowsError(try PaymentCard.validate("1234567890125327"))
    }
    func testAmbiguousSuffixRemainsUnassignedAndExplicitNetworkResolvesIt() {
        let cards = [card(), card("5327", "Visa · Reise")]
        XCTAssertEqual(PaymentCard.matches("5327", method: nil, cards: cards).count, 2)
        XCTAssertEqual(PaymentCard.display("5327", method: nil, cards: cards), "Karte ···· 5327")
        XCTAssertEqual(PaymentCard.matches("5327", method: "Visa", cards: cards).map(\.id), [cards[1].id])
        XCTAssertEqual(PaymentCard.display("5327", method: "Visa", cards: cards), cards[1].displayName)
        XCTAssertTrue(PaymentCard.matches("5327", method: "Maestro", cards: cards).isEmpty)
    }
    func testOfflineOCRUsesCardEvidenceAndDoesNotInventPANFromOtherNumbers() {
        XCTAssertEqual(PaymentCard.fromOCR("Coop\nMastercard\nXXXX 5327\nTotal CHF 15.50"), "Mastercard ···· 5327")
        XCTAssertNil(PaymentCard.fromOCR("Coop\nTerminal 5327\nDatum 26.11.2026\nTotal 5327"))
        XCTAssertNil(PaymentCard.fromOCR("Visa **** 5327\nMastercard **** 1234"))
        XCTAssertEqual(LocalReceiptParser.parse("Coop\nVisa **** 1234\nTotal 12.50").payment_card, "Visa ···· 1234")
    }
    func testOldCardLabelsAndUnknownNetworksRemainUsable() {
        let old = card("5327", "Privat")
        XCTAssertEqual(PaymentCard.display("****5327", method: "Mastercard", cards: [old]), "Privat ···· 5327")
        XCTAssertEqual(PaymentCard.normalized("****5327", method: "Mastercard", cards: [old]), "Mastercard ···· 5327")
        XCTAssertNil(PaymentCard.display(nil, method: "Bar", cards: [old]))
    }
}
