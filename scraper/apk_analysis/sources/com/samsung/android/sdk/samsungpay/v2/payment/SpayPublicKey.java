package com.samsung.android.sdk.samsungpay.v2.payment;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class SpayPublicKey implements Parcelable {
    public static final Parcelable.Creator<SpayPublicKey> CREATOR = new Parcelable.Creator<SpayPublicKey>() { // from class: com.samsung.android.sdk.samsungpay.v2.payment.SpayPublicKey.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SpayPublicKey createFromParcel(Parcel parcel) {
            return new SpayPublicKey(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SpayPublicKey[] newArray(int i) {
            return new SpayPublicKey[i];
        }
    };
    private String algorithm;
    private byte[] encoded;
    private String format;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SpayPublicKey(String str, String str2, byte[] bArr) {
        this.algorithm = str;
        this.format = str2;
        this.encoded = bArr;
    }

    public SpayPublicKey(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.algorithm = parcel.readString();
        this.format = parcel.readString();
        byte[] bArr = new byte[parcel.readInt()];
        this.encoded = bArr;
        parcel.readByteArray(bArr);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.algorithm);
        parcel.writeString(this.format);
        parcel.writeInt(this.encoded.length);
        parcel.writeByteArray(this.encoded);
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public String getFormat() {
        return this.format;
    }

    public byte[] getEncoded() {
        return this.encoded;
    }
}
