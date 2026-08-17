package ch.coop.supercardapp.containers.cards;

import java.util.Objects;

/* loaded from: classes3.dex */
public class CardIdentifier {
    private final String cardNr;
    private final CardType cardType;

    public enum CardType {
        GIFTCARD,
        EMPLOYEE_DISCOUNT_CARD
    }

    public CardIdentifier(CardType cardType, String str) {
        this.cardType = cardType;
        this.cardNr = str;
    }

    public CardType getCardType() {
        return this.cardType;
    }

    public String getCardNr() {
        return this.cardNr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CardIdentifier cardIdentifier = (CardIdentifier) obj;
        return this.cardType == cardIdentifier.cardType && Objects.equals(this.cardNr, cardIdentifier.cardNr);
    }

    public int hashCode() {
        return Objects.hash(this.cardType, this.cardNr);
    }
}
