package com.samsung.android.sdk.samsungpay.v2.service;

import android.os.Parcel;
import android.os.Parcelable;

@Deprecated
/* loaded from: classes2.dex */
public enum RequestType implements Parcelable {
    ALL(0),
    NAME(1),
    EMAIL(2),
    TEL(3),
    BILLING_ADDRESS(4),
    SHIPPING_ADDRESS(5),
    DATE_OF_BIRTH(6),
    PAYMENT_CARD(7),
    IMEI(8),
    MODEL_NAME(9),
    FIRST_NAME(10),
    LAST_NAME(11),
    NO_SHEET_UPDATE_CALLBACK(100);

    public static final Parcelable.Creator<RequestType> CREATOR = new Parcelable.Creator<RequestType>() { // from class: com.samsung.android.sdk.samsungpay.v2.service.RequestType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RequestType createFromParcel(Parcel parcel) {
            int readInt = parcel.readInt();
            for (RequestType requestType : RequestType.values()) {
                if (requestType.mType == readInt) {
                    return requestType;
                }
            }
            return null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RequestType[] newArray(int i) {
            return new RequestType[i];
        }
    };
    private int mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    RequestType(int i) {
        this.mType = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mType);
    }
}
