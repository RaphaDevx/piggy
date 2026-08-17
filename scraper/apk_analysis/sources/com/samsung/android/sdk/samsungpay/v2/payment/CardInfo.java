package com.samsung.android.sdk.samsungpay.v2.payment;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;

/* loaded from: classes2.dex */
public class CardInfo implements Parcelable {
    public static final Parcelable.Creator<CardInfo> CREATOR = new Parcelable.Creator<CardInfo>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.CardInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CardInfo createFromParcel(Parcel parcel) {
            return new CardInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CardInfo[] newArray(int i) {
            return new CardInfo[i];
        }
    };
    private SpaySdk.Brand brand;
    private String cardId;
    private Bundle cardMetaData;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private CardInfo() {
    }

    private CardInfo(Builder builder) {
        this.brand = builder.brand;
        this.cardId = builder.cardId;
        this.cardMetaData = builder.cardMetaData;
    }

    public CardInfo(Parcel parcel) {
        this.brand = (SpaySdk.Brand) parcel.readValue(SpaySdk.Brand.class.getClassLoader());
        this.cardId = parcel.readString();
        this.cardMetaData = parcel.readBundle();
    }

    public SpaySdk.Brand getBrand() {
        return this.brand;
    }

    public String getCardId() {
        return this.cardId;
    }

    public Bundle getCardMetaData() {
        return this.cardMetaData;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.brand);
        parcel.writeString(this.cardId);
        parcel.writeBundle(this.cardMetaData);
    }

    public static class Builder {
        private SpaySdk.Brand brand;
        private String cardId;
        private Bundle cardMetaData;

        public Builder setBrand(SpaySdk.Brand brand) {
            this.brand = brand;
            return this;
        }

        public Builder setCardId(String str) {
            this.cardId = str;
            return this;
        }

        public Builder setCardMetaData(Bundle bundle) {
            this.cardMetaData = bundle;
            return this;
        }

        public CardInfo build() {
            return new CardInfo(this);
        }
    }
}
