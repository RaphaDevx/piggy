package com.samsung.android.sdk.samsungpay.v2.payment;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class MstPaymentInfo implements Parcelable {
    public static final Parcelable.Creator<MstPaymentInfo> CREATOR = new Parcelable.Creator<MstPaymentInfo>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.MstPaymentInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MstPaymentInfo createFromParcel(Parcel parcel) {
            return new MstPaymentInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MstPaymentInfo[] newArray(int i) {
            return new MstPaymentInfo[i];
        }
    };
    private int durationTime;
    private String encryptedToken;
    private String endEntityCA;
    private Bundle extraData;
    private String intermediateCA;
    private boolean isEncrypted;
    private boolean isRetry;
    private String signedToken;
    private String transactionReference;
    private String transactionSignature;
    private Long transactionTimestamp;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MstPaymentInfo(Builder builder) {
        this.transactionReference = "";
        this.transactionTimestamp = 0L;
        this.transactionSignature = "";
        this.encryptedToken = "";
        this.signedToken = "";
        this.intermediateCA = "";
        this.endEntityCA = "";
        this.durationTime = 0;
        this.isRetry = false;
        this.isEncrypted = false;
        this.extraData = new Bundle();
        this.transactionReference = builder.transactionReference;
        this.transactionTimestamp = builder.transactionTimestamp;
        this.transactionSignature = builder.transactionSignature;
        this.encryptedToken = builder.encryptedToken;
        this.signedToken = builder.signedToken;
        this.intermediateCA = builder.intermediateCA;
        this.endEntityCA = builder.endEntityCA;
        this.durationTime = builder.durationTime;
        this.isRetry = builder.isRetry;
        this.isEncrypted = builder.isEncrypted;
        this.extraData = builder.extraData;
    }

    public MstPaymentInfo(Parcel parcel) {
        this.transactionReference = "";
        this.transactionTimestamp = 0L;
        this.transactionSignature = "";
        this.encryptedToken = "";
        this.signedToken = "";
        this.intermediateCA = "";
        this.endEntityCA = "";
        this.durationTime = 0;
        this.isRetry = false;
        this.isEncrypted = false;
        this.extraData = new Bundle();
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.transactionReference = parcel.readString();
        this.transactionTimestamp = Long.valueOf(parcel.readLong());
        this.transactionSignature = parcel.readString();
        this.encryptedToken = parcel.readString();
        this.signedToken = parcel.readString();
        this.intermediateCA = parcel.readString();
        this.endEntityCA = parcel.readString();
        this.durationTime = parcel.readInt();
        this.isRetry = ((Boolean) parcel.readValue(Boolean.class.getClassLoader())).booleanValue();
        this.isEncrypted = ((Boolean) parcel.readValue(Boolean.class.getClassLoader())).booleanValue();
        this.extraData = parcel.readBundle();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.transactionReference);
        parcel.writeLong(this.transactionTimestamp.longValue());
        parcel.writeString(this.transactionSignature);
        parcel.writeString(this.encryptedToken);
        parcel.writeString(this.signedToken);
        parcel.writeString(this.intermediateCA);
        parcel.writeString(this.endEntityCA);
        parcel.writeInt(this.durationTime);
        parcel.writeValue(Boolean.valueOf(this.isRetry));
        parcel.writeValue(Boolean.valueOf(this.isEncrypted));
        parcel.writeBundle(this.extraData);
    }

    public static class Builder {
        private String transactionReference = "";
        private Long transactionTimestamp = 0L;
        private String transactionSignature = "";
        private String encryptedToken = "";
        private String signedToken = "";
        private String intermediateCA = "";
        private String endEntityCA = "";
        private int durationTime = 0;
        private boolean isRetry = false;
        private boolean isEncrypted = false;
        private Bundle extraData = new Bundle();

        public Builder setTransactionReference(String str) {
            this.transactionReference = str;
            return this;
        }

        public Builder setTransactionTimestamp(Long l) {
            this.transactionTimestamp = l;
            return this;
        }

        public Builder setTransactionSignature(String str) {
            this.transactionSignature = str;
            return this;
        }

        public Builder setEncryptedToken(String str) {
            this.encryptedToken = str;
            return this;
        }

        public Builder setSignedToken(String str) {
            this.signedToken = str;
            return this;
        }

        public Builder setIntermediateCA(String str) {
            this.intermediateCA = str;
            return this;
        }

        public Builder setEndEntityCA(String str) {
            this.endEntityCA = str;
            return this;
        }

        public Builder setDurationTime(int i) {
            this.durationTime = i;
            return this;
        }

        public Builder setIsRetry(boolean z) {
            this.isRetry = z;
            return this;
        }

        public Builder setIsEncrypted(boolean z) {
            this.isEncrypted = z;
            return this;
        }

        public Builder setExtraMSTPaymentData(Bundle bundle) {
            this.extraData = bundle;
            return this;
        }

        public MstPaymentInfo build() {
            return new MstPaymentInfo(this);
        }
    }

    public String getTransactionReference() {
        return this.transactionReference;
    }

    public Long getTransactionTimeStamp() {
        return this.transactionTimestamp;
    }

    public String getTransactionSignature() {
        return this.transactionSignature;
    }

    public String getEncryptedToken() {
        return this.encryptedToken;
    }

    public String getSignedToken() {
        return this.signedToken;
    }

    public String getIntermediateCA() {
        return this.intermediateCA;
    }

    public String getEndEntityCA() {
        return this.endEntityCA;
    }

    public int getDurationTime() {
        return this.durationTime;
    }

    public boolean getIsRetry() {
        return this.isRetry;
    }

    public boolean getIsEncrypted() {
        return this.isEncrypted;
    }

    public Bundle getExtraMstPaymentData() {
        if (this.extraData == null) {
            this.extraData = new Bundle();
        }
        return this.extraData;
    }
}
