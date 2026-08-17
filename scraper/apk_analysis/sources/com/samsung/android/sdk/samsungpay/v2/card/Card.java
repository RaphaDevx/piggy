package com.samsung.android.sdk.samsungpay.v2.card;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.samsung.android.sdk.samsungpay.v2.ParamInfo;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;

/* loaded from: classes2.dex */
public class Card implements Parcelable {
    public static final String ACTIVE = "ACTIVE";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_8)
    public static final String CARD_TYPE = "CARD_TYPE";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_2)
    public static final String CARD_TYPE_CREDIT = "CREDIT";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_1)
    public static final String CARD_TYPE_CREDIT_DEBIT = "PAYMENT";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_2)
    public static final String CARD_TYPE_DEBIT = "DEBIT";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_1)
    public static final String CARD_TYPE_GIFT = "GIFT";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_1)
    public static final String CARD_TYPE_LOYALTY = "LOYALTY";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_8)
    public static final String CARD_TYPE_TRANSIT = "TRANSIT";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_2_16)
    public static final String CARD_TYPE_VACCINE_PASS = "VACCINE_PASS";
    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() { // from class: com.samsung.android.sdk.samsungpay.v2.card.Card.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Card createFromParcel(Parcel parcel) {
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            SpaySdk.Brand brand = (SpaySdk.Brand) parcel.readValue(SpaySdk.Brand.class.getClassLoader());
            Bundle readBundle = parcel.readBundle();
            if (readBundle == null) {
                readBundle = new Bundle();
            }
            if (TextUtils.equals(Card.CARD_TYPE_TRANSIT, readBundle.getString(Card.CARD_TYPE))) {
                return new TransitCard(readString, readString2, brand, readBundle);
            }
            return new Card(readString, readString2, brand, readBundle);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Card[] newArray(int i) {
            return new Card[i];
        }
    };
    public static final String DISPOSED = "DISPOSED";
    public static final String EXPIRED = "EXPIRED";
    public static final String PENDING_ACTIVATION = "PENDING_ACTIVATION";
    public static final String PENDING_ENROLLED = "ENROLLED";
    public static final String PENDING_PROVISION = "PENDING_PROVISION";
    public static final String SUSPENDED = "SUSPENDED";
    private SpaySdk.Brand cardBrand;
    private String cardId;
    private Bundle cardInfo;
    private String cardStatus;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private Card(Parcel parcel) {
        this();
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.cardId = parcel.readString();
        this.cardStatus = parcel.readString();
        this.cardBrand = (SpaySdk.Brand) parcel.readValue(SpaySdk.Brand.class.getClassLoader());
        this.cardInfo = parcel.readBundle();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.cardId);
        parcel.writeString(this.cardStatus);
        parcel.writeValue(this.cardBrand);
        parcel.writeBundle(this.cardInfo);
    }

    public Card() {
    }

    public Card(String str, String str2) {
        this.cardId = str;
        this.cardStatus = str2;
        this.cardInfo = new Bundle();
        this.cardBrand = SpaySdk.Brand.UNKNOWN_CARD;
    }

    public Card(String str, Bundle bundle) {
        this.cardId = str;
        this.cardStatus = "";
        this.cardInfo = bundle;
        this.cardBrand = SpaySdk.Brand.UNKNOWN_CARD;
    }

    public Card(String str, String str2, Bundle bundle) {
        this.cardId = str;
        this.cardStatus = str2;
        this.cardInfo = bundle;
        this.cardBrand = SpaySdk.Brand.UNKNOWN_CARD;
    }

    public Card(String str, String str2, SpaySdk.Brand brand, Bundle bundle) {
        this.cardId = str;
        this.cardStatus = str2;
        this.cardBrand = brand;
        this.cardInfo = bundle;
    }

    public String getCardId() {
        return this.cardId;
    }

    public SpaySdk.Brand getCardBrand() {
        return this.cardBrand;
    }

    public Bundle getCardInfo() {
        return this.cardInfo;
    }

    public String getCardStatus() {
        return this.cardStatus;
    }

    public void setCardId(String str) {
        this.cardId = str;
    }

    public void setCardBrand(SpaySdk.Brand brand) {
        this.cardBrand = brand;
    }

    public void setCardInfo(Bundle bundle) {
        this.cardInfo = bundle;
    }

    public void setCardStatus(String str) {
        this.cardStatus = str;
    }

    public String toString() {
        return "Card{cardId='" + this.cardId + "', cardStatus='" + this.cardStatus + "', cardBrand='" + this.cardBrand + "', cardInfo=" + this.cardInfo + '}';
    }
}
