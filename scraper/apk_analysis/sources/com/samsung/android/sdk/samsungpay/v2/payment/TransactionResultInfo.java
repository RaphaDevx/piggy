package com.samsung.android.sdk.samsungpay.v2.payment;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class TransactionResultInfo implements Parcelable {
    public static final Parcelable.Creator<TransactionResultInfo> CREATOR = new Parcelable.Creator<TransactionResultInfo>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.TransactionResultInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TransactionResultInfo createFromParcel(Parcel parcel) {
            return new TransactionResultInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TransactionResultInfo[] newArray(int i) {
            return new TransactionResultInfo[i];
        }
    };
    private Double amount;
    private String approvalType;
    private String currency;
    private String dateTime;
    private Bundle extraData;
    private String issuer;
    private String merchantName;
    private String userId;

    public enum ApprovalType {
        UNKNOWN,
        APPROVED,
        CANCELED,
        REJECTED
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TransactionResultInfo(Builder builder) {
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.dateTime = builder.dateTime;
        this.issuer = builder.issuer;
        this.approvalType = builder.approvalType;
        this.merchantName = builder.merchantName;
        this.userId = builder.userId;
        this.extraData = builder.extraData;
    }

    public TransactionResultInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.amount = Double.valueOf(parcel.readDouble());
        this.currency = parcel.readString();
        this.dateTime = parcel.readString();
        this.issuer = parcel.readString();
        this.approvalType = parcel.readString();
        this.merchantName = parcel.readString();
        this.userId = parcel.readString();
        this.extraData = parcel.readBundle();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.amount.doubleValue());
        parcel.writeString(this.currency);
        parcel.writeString(this.dateTime);
        parcel.writeString(this.issuer);
        parcel.writeString(this.approvalType);
        parcel.writeString(this.merchantName);
        parcel.writeString(this.userId);
        parcel.writeBundle(this.extraData);
    }

    public static class Builder {
        private Double amount;
        private String approvalType;
        private String currency;
        private String dateTime;
        private Bundle extraData;
        private String issuer;
        private String merchantName;
        private String userId;

        public Builder setTransactionAmount(Double d) {
            this.amount = d;
            return this;
        }

        public Builder setTransactionCurrency(String str) {
            this.currency = str;
            return this;
        }

        public Builder setTransactionDateTime(String str) {
            this.dateTime = str;
            return this;
        }

        public Builder setTransactionIssuer(String str) {
            this.issuer = str;
            return this;
        }

        public Builder setTransactionApprovalType(ApprovalType approvalType) {
            this.approvalType = approvalType.name();
            return this;
        }

        public Builder setTransactionMerchantName(String str) {
            this.merchantName = str;
            return this;
        }

        public Builder setUserProfileId(String str) {
            this.userId = str;
            return this;
        }

        public Builder setExtraData(Bundle bundle) {
            this.extraData = bundle;
            return this;
        }

        public TransactionResultInfo build() {
            return new TransactionResultInfo(this);
        }
    }

    public Double getTransactionAmount() {
        return this.amount;
    }

    public String getTransactionCurrency() {
        return this.currency;
    }

    public String getTransactionDateTime() {
        return this.dateTime;
    }

    public String getTransactionIssuer() {
        return this.issuer;
    }

    public String getTransactionApprovalType() {
        return this.approvalType;
    }

    public String getTransactionMerchantName() {
        return this.merchantName;
    }

    public String getUserProfileId() {
        return this.userId;
    }

    public Bundle getExtraData() {
        if (this.extraData == null) {
            this.extraData = new Bundle();
        }
        return this.extraData;
    }
}
