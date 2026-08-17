package com.samsung.android.sdk.samsungpay.v2;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class Status implements Parcelable {
    public static final Parcelable.Creator<Status> CREATOR = new Parcelable.Creator<Status>() { // from class: com.samsung.android.sdk.samsungpay.v2.Status.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Status createFromParcel(Parcel parcel) {
            return new Status(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Status[] newArray(int i) {
            return new Status[i];
        }
    };
    private Bundle mData;
    private int mStatus;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Status(int i) {
        this.mStatus = i;
    }

    public Status(int i, Bundle bundle) {
        this.mStatus = i;
        this.mData = bundle;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setStatus(int i) {
        this.mStatus = i;
    }

    public Bundle getData() {
        return this.mData;
    }

    public void setData(Bundle bundle) {
        this.mData = bundle;
    }

    protected Status(Parcel parcel) {
        readFromParacel(parcel);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mStatus);
        parcel.writeBundle(this.mData);
    }

    private void readFromParacel(Parcel parcel) {
        this.mStatus = parcel.readInt();
        this.mData = parcel.readBundle();
    }

    public String toString() {
        return "Status{mStatus=" + this.mStatus + ", mData=" + this.mData + '}';
    }
}
