package com.samsung.android.sdk.samsungpay.v2.payment;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class PaymentResultInfo implements Parcelable {
    public static final Parcelable.Creator<PaymentResultInfo> CREATOR = new Parcelable.Creator<PaymentResultInfo>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.PaymentResultInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PaymentResultInfo createFromParcel(Parcel parcel) {
            return new PaymentResultInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PaymentResultInfo[] newArray(int i) {
            return new PaymentResultInfo[i];
        }
    };
    private static final String PAYMENT_RESULT_ORDER_NUMBER = "paymentResultOrderNumber";
    private static final String PAYMENT_RESULT_STATUS = "paymentResultStatus";
    private Bundle paymentResultData;

    public enum ResultStatus {
        UNKNOWN,
        CHARGED,
        REJECTED
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PaymentResultInfo(Builder builder) {
        this.paymentResultData = builder.paymentResultData;
    }

    public PaymentResultInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.paymentResultData = parcel.readBundle();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.paymentResultData);
    }

    public static class Builder {
        private Bundle paymentResultData;

        public Builder setPaymentResultStatus(ResultStatus resultStatus) {
            if (this.paymentResultData == null) {
                this.paymentResultData = new Bundle();
            }
            this.paymentResultData.putString(PaymentResultInfo.PAYMENT_RESULT_STATUS, resultStatus.name());
            return this;
        }

        public Builder setPaymentResultOrderNumber(String str) {
            this.paymentResultData.putString(PaymentResultInfo.PAYMENT_RESULT_ORDER_NUMBER, str);
            return this;
        }

        public PaymentResultInfo build() {
            return new PaymentResultInfo(this);
        }
    }

    public String getPaymentResultStatus() {
        return this.paymentResultData.getString(PAYMENT_RESULT_STATUS, ResultStatus.UNKNOWN.name());
    }

    public String getPaymentResultOrderNumber() {
        return this.paymentResultData.getString(PAYMENT_RESULT_ORDER_NUMBER, "");
    }
}
