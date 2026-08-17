package com.samsung.android.sdk.samsungpay.v2.payment;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class SpayResponseInfo implements Parcelable {
    public static final Parcelable.Creator<SpayResponseInfo> CREATOR = new Parcelable.Creator<SpayResponseInfo>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.SpayResponseInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SpayResponseInfo createFromParcel(Parcel parcel) {
            return new SpayResponseInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SpayResponseInfo[] newArray(int i) {
            return new SpayResponseInfo[i];
        }
    };
    static final int ERROR_MST_TRANSACTION_TIMEOUT = 27;
    static final int ERROR_MST_TRANSACTION_TIMEOUT_CAN_RETRY = 28;
    static final int ERROR_USER_CANCEL = 17;
    static final int PAY_MST_TRANSACTION_CANCLE = 22;
    static final int PAY_MST_TRANSACTION_FINISH = 21;
    static final int PAY_MST_TRANSACTION_PROGRESS = 20;
    static final int PAY_MST_TRANSACTION_START = 23;
    static final int PAY_MST_TRANSACTION_UNKNOWN = 0;
    Bundle responseData;
    private final String PAY_MODE_DELAY_TIME = "PAY_MODE_DELAY_TIME";
    private final String PAY_MODE_CYCLE_TIME = "PAY_MODE_CYCLE_TIME";
    private final String PAY_TRANSACTION_ID = "PAY_TRANSACTION_ID";
    private final String PAY_MST_STATUS = "PAY_MST_STATUS";

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SpayResponseInfo(Bundle bundle) {
        this.responseData = bundle;
    }

    public SpayResponseInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.responseData = parcel.readBundle();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.responseData);
    }

    public int getPayModeDelayTime() {
        Bundle bundle = this.responseData;
        if (bundle == null) {
            return 0;
        }
        return bundle.getInt("PAY_MODE_DELAY_TIME", 0);
    }

    public int getPayModeCycleTime() {
        Bundle bundle = this.responseData;
        if (bundle == null) {
            return 0;
        }
        return bundle.getInt("PAY_MODE_CYCLE_TIME", 0);
    }

    public String getPayTransactionId() {
        Bundle bundle = this.responseData;
        return bundle == null ? "" : bundle.getString("PAY_TRANSACTION_ID", "");
    }

    int getPayMstStatus() {
        Bundle bundle = this.responseData;
        if (bundle == null) {
            return 0;
        }
        return bundle.getInt("PAY_MST_STATUS", 0);
    }
}
