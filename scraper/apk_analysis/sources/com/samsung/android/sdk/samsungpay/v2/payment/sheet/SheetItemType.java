package com.samsung.android.sdk.samsungpay.v2.payment.sheet;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public enum SheetItemType implements Parcelable {
    BILLING_ADDRESS,
    SHIPPING_ADDRESS,
    AMOUNT_TOTAL,
    AMOUNT_ITEM,
    SHIPPING_METHOD_SPINNER,
    INSTALLMENT_SPINNER;

    public static final Parcelable.Creator<SheetItemType> CREATOR = new Parcelable.Creator<SheetItemType>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetItemType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SheetItemType createFromParcel(Parcel parcel) {
            return SheetItemType.values()[parcel.readInt()];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SheetItemType[] newArray(int i) {
            return new SheetItemType[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
