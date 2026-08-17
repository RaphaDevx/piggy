package io.card.payment;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.UUID;
import kotlin.text.Typography;

/* loaded from: classes2.dex */
public class CreditCard implements Parcelable {
    public static final Parcelable.Creator<CreditCard> CREATOR = new Parcelable.Creator<CreditCard>() { // from class: io.card.payment.CreditCard.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CreditCard createFromParcel(Parcel parcel) {
            return new CreditCard(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CreditCard[] newArray(int i) {
            return new CreditCard[i];
        }
    };
    public static final int EXPIRY_MAX_FUTURE_YEARS = 15;
    private static final String TAG = "CreditCard";
    public String cardNumber;
    public String cardholderName;
    public String cvv;
    public int expiryMonth;
    public int expiryYear;
    boolean flipped;
    public String postalCode;
    String scanId;
    int[] xoff;
    int yoff;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CreditCard() {
        this.expiryMonth = 0;
        this.expiryYear = 0;
        this.flipped = false;
        this.xoff = new int[16];
        this.scanId = UUID.randomUUID().toString();
    }

    public CreditCard(String str, int i, int i2, String str2, String str3, String str4) {
        this.flipped = false;
        this.cardNumber = str;
        this.expiryMonth = i;
        this.expiryYear = i2;
        this.cvv = str2;
        this.postalCode = str3;
        this.cardholderName = str4;
    }

    private CreditCard(Parcel parcel) {
        this.expiryMonth = 0;
        this.expiryYear = 0;
        this.flipped = false;
        this.cardNumber = parcel.readString();
        this.expiryMonth = parcel.readInt();
        this.expiryYear = parcel.readInt();
        this.cvv = parcel.readString();
        this.postalCode = parcel.readString();
        this.cardholderName = parcel.readString();
        this.scanId = parcel.readString();
        this.yoff = parcel.readInt();
        this.xoff = parcel.createIntArray();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.cardNumber);
        parcel.writeInt(this.expiryMonth);
        parcel.writeInt(this.expiryYear);
        parcel.writeString(this.cvv);
        parcel.writeString(this.postalCode);
        parcel.writeString(this.cardholderName);
        parcel.writeString(this.scanId);
        parcel.writeInt(this.yoff);
        parcel.writeIntArray(this.xoff);
    }

    public String getLastFourDigitsOfCardNumber() {
        String str = this.cardNumber;
        if (str != null) {
            int min = Math.min(4, str.length());
            String str2 = this.cardNumber;
            return str2.substring(str2.length() - min);
        }
        return "";
    }

    public String getRedactedCardNumber() {
        String str = this.cardNumber;
        String str2 = "";
        if (str == null) {
            return "";
        }
        if (str.length() > 4) {
            StringBuilder sb = new StringBuilder("");
            sb.append(String.format("%" + (this.cardNumber.length() - 4) + "s", "").replace(' ', Typography.bullet));
            str2 = sb.toString();
        }
        return CreditCardNumber.formatString(str2 + getLastFourDigitsOfCardNumber(), false, CardType.fromCardNumber(this.cardNumber));
    }

    public CardType getCardType() {
        return CardType.fromCardNumber(this.cardNumber);
    }

    public String getFormattedCardNumber() {
        return CreditCardNumber.formatString(this.cardNumber);
    }

    public boolean isExpiryValid() {
        return CreditCardNumber.isDateValid(this.expiryMonth, this.expiryYear);
    }

    public String toString() {
        String str = "{" + getCardType() + ": " + getRedactedCardNumber();
        if (this.expiryMonth > 0 || this.expiryYear > 0) {
            str = str + "  expiry:" + this.expiryMonth + "/" + this.expiryYear;
        }
        if (this.postalCode != null) {
            str = str + "  postalCode:" + this.postalCode;
        }
        if (this.cardholderName != null) {
            str = str + "  cardholderName:" + this.cardholderName;
        }
        if (this.cvv != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("  cvvLength:");
            String str2 = this.cvv;
            sb.append(str2 != null ? str2.length() : 0);
            str = sb.toString();
        }
        return str + "}";
    }
}
