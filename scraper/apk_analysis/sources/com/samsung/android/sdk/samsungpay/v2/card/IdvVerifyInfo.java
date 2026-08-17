package com.samsung.android.sdk.samsungpay.v2.card;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.samsungpay.v2.ParamInfo;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;

/* loaded from: classes2.dex */
public class IdvVerifyInfo implements Parcelable {
    public static final Parcelable.Creator<IdvVerifyInfo> CREATOR = new Parcelable.Creator<IdvVerifyInfo>() { // from class: com.samsung.android.sdk.samsungpay.v2.card.IdvVerifyInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IdvVerifyInfo createFromParcel(Parcel parcel) {
            return new IdvVerifyInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IdvVerifyInfo[] newArray(int i) {
            return new IdvVerifyInfo[i];
        }
    };

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_2_7)
    public static final String IDV_TYPE_APP2APP = "APP2APP";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_2_7)
    public static final String IDV_TYPE_EMAIL = "EMAIL";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_2_7)
    public static final String IDV_TYPE_SMS = "SMS";

    @ParamInfo(checkValue = true, since = SpaySdk.SdkApiLevel.LEVEL_2_7)
    public static final String KEY_IDV_AUTH_CODE_TYPE = "IDV_AUTH_CODE_TYPE";
    private String mCardId;
    private Bundle mCardInfoData;
    private String mCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IdvVerifyInfo() {
    }

    public IdvVerifyInfo(String str, String str2, Bundle bundle) {
        this.mCardId = str;
        this.mCode = str2;
        this.mCardInfoData = bundle;
    }

    public String getCardId() {
        return this.mCardId;
    }

    public void setCardId(String str) {
        this.mCardId = str;
    }

    public String getCode() {
        return this.mCode;
    }

    public void setCode(String str) {
        this.mCode = str;
    }

    public Bundle getCardInfoData() {
        return this.mCardInfoData;
    }

    public void setCardInfoData(Bundle bundle) {
        this.mCardInfoData = bundle;
    }

    public String getIDnVType() {
        Bundle bundle = this.mCardInfoData;
        return bundle == null ? IDV_TYPE_APP2APP : bundle.getString(KEY_IDV_AUTH_CODE_TYPE, IDV_TYPE_APP2APP);
    }

    public void setIDnVType(String str) {
        if (this.mCardInfoData == null) {
            this.mCardInfoData = new Bundle();
        }
        this.mCardInfoData.putString(KEY_IDV_AUTH_CODE_TYPE, str);
    }

    private IdvVerifyInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void readFromParcel(Parcel parcel) {
        this.mCardId = parcel.readString();
        this.mCode = parcel.readString();
        this.mCardInfoData = parcel.readBundle();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCardId);
        parcel.writeString(this.mCode);
        parcel.writeBundle(this.mCardInfoData);
    }

    public String toString() {
        return "IdvVerifyInfo{mCardId='" + this.mCardId + "', mCode='" + this.mCode + "', mCardInfoData=" + this.mCardInfoData + '}';
    }
}
