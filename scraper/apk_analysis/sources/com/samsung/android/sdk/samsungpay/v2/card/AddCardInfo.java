package com.samsung.android.sdk.samsungpay.v2.card;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.samsung.android.sdk.samsungpay.v2.CheckApiLevel;
import com.samsung.android.sdk.samsungpay.v2.ParamInfo;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class AddCardInfo implements Parcelable {

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_2)
    public static final String EXTRA_ISSUER_ID = "issuerId";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_2_17)
    public static final String EXTRA_KEY_MOSCOW_TRANSIT = "extra_transit_card_data";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_2)
    public static final String EXTRA_PROVISION_PAYLOAD = "provisionPayload";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_2_14)
    public static final String EXTRA_SAMSUNG_PAY_CARD = "extra_samsung_pay_card";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_2)
    public static final String PROVIDER_AMEX = "AX";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_2)
    public static final String PROVIDER_DISCOVER = "DS";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_2_3)
    public static final String PROVIDER_GEMALTO = "GT";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_2)
    public static final String PROVIDER_GIFT = "GI";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_2)
    @Deprecated
    public static final String PROVIDER_GTO = "GTO";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_2)
    public static final String PROVIDER_LOYALTY = "LO";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_2)
    public static final String PROVIDER_MASTERCARD = "MC";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_2_3)
    public static final String PROVIDER_MIR = "MI";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_2_3)
    public static final String PROVIDER_NAPAS = "NP";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_2_7)
    public static final String PROVIDER_PAGOBANCOMAT = "PB";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_2_0)
    public static final String PROVIDER_PAYPAL = "PP";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_2)
    public static final String PROVIDER_PLCC = "PL";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_2_18)
    public static final String PROVIDER_VACCINE_PASS = "VaccinePass";

    @ParamInfo(since = SpaySdk.SdkApiLevel.LEVEL_1_2)
    public static final String PROVIDER_VISA = "VI";
    private static final String TAG = "AddCardInfo";

    @CheckApiLevel
    private Bundle cardDetail;
    private String cardType;

    @CheckApiLevel
    private String tokenizationProvider;
    private static final ArrayList<String> allowedCardTypes = new ArrayList<>(Arrays.asList(Card.CARD_TYPE_CREDIT_DEBIT, Card.CARD_TYPE_CREDIT, Card.CARD_TYPE_DEBIT, Card.CARD_TYPE_VACCINE_PASS));
    public static final Parcelable.Creator<AddCardInfo> CREATOR = new Parcelable.Creator<AddCardInfo>() { // from class: com.samsung.android.sdk.samsungpay.v2.card.AddCardInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddCardInfo createFromParcel(Parcel parcel) {
            return new AddCardInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AddCardInfo[] newArray(int i) {
            return new AddCardInfo[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setCardDetail(Bundle bundle) {
        assertNotNull(bundle);
        verifyProvisionPayload(this.tokenizationProvider, bundle);
        this.cardDetail = bundle;
    }

    public void setTokenizationProvider(String str) {
        assertNotNull(str);
        this.tokenizationProvider = str;
    }

    public void setCardType(String str) {
        verifyCardTypeAllowed(str);
        this.cardType = str;
    }

    public Bundle getCardDetail() {
        return this.cardDetail;
    }

    public String getTokenizationProvider() {
        return this.tokenizationProvider;
    }

    public String getCardType() {
        return this.cardType;
    }

    protected AddCardInfo(Parcel parcel) {
        this.cardType = parcel.readString();
        this.tokenizationProvider = parcel.readString();
        this.cardDetail = parcel.readBundle();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.cardType);
        parcel.writeString(this.tokenizationProvider);
        parcel.writeBundle(this.cardDetail);
    }

    public AddCardInfo(String str, String str2, Bundle bundle) {
        verifyCardTypeAllowed(str);
        assertNotNull(str2);
        assertNotNull(bundle);
        verifyProvisionPayload(str2, bundle);
        this.cardType = str;
        this.tokenizationProvider = str2;
        this.cardDetail = bundle;
    }

    private void verifyCardTypeAllowed(String str) {
        if (allowedCardTypes.contains(str)) {
            return;
        }
        throw new IllegalArgumentException("Not allowed card type is used : " + str);
    }

    private void verifyProvisionPayload(String str, Bundle bundle) {
        String string = bundle.getString(EXTRA_PROVISION_PAYLOAD);
        if (TextUtils.isEmpty(string)) {
            throw new IllegalArgumentException("Provision payload must be provided");
        }
        if (TextUtils.equals(str, PROVIDER_MASTERCARD)) {
            try {
                Base64.decode(string.getBytes(), 0);
            } catch (Exception e) {
                Log.e(TAG, "verify provision payload failed : " + e.getMessage());
                throwException("In case of Mastercard, payload should be Base64 encoded. Please double check it");
            }
        }
    }

    private void assertNotNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Input parameter must be set");
        }
    }

    private void throwException(String str) {
        Log.e(TAG, str);
    }
}
