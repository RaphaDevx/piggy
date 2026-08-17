package com.samsung.android.sdk.samsungpay.v2;

import android.app.Activity;
import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.samsung.android.sdk.samsungpay.v2.card.AddCardInfo;
import com.samsung.android.sdk.samsungpay.v2.service.RequestType;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* loaded from: classes2.dex */
public abstract class SpaySdk {
    public static final String COMMON_STATUS_TABLE = "common_error_table";

    @ParamInfo(since = SdkApiLevel.LEVEL_2_4)
    public static final String CRYPTOGRAM_TYPE_ICC = "ICC";

    @ParamInfo(since = SdkApiLevel.LEVEL_2_4)
    public static final String CRYPTOGRAM_TYPE_UCAF = "UCAF";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_2)
    public static final String DEVICE_ID = "deviceId";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_1)
    public static final String DEVICE_TYPE_GEAR = "gear";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_1)
    public static final String DEVICE_TYPE_PHONE = "phone";
    public static final int ERROR_ALREADY_DONE = -5;
    public static final int ERROR_ANDROID_PLATFORM_CHECK_FAIL = -361;
    public static final int ERROR_DEVICE_INTEGRITY_CHECK_FAIL = -353;
    public static final int ERROR_DEVICE_NOT_SAMSUNG = -350;
    public static final int ERROR_DUPLICATED_SDK_API_CALLED = -105;
    public static final int ERROR_EXPIRED_OR_INVALID_DEBUG_KEY = -310;
    public static final int ERROR_INITIATION_FAIL = -103;
    public static final int ERROR_INVALID_INPUT = -2;
    public static final int ERROR_INVALID_PARAMETER = -12;
    public static final int ERROR_MISSING_INFORMATION = -354;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_NOT_ALLOWED = -6;
    public static final int ERROR_NOT_FOUND = -4;
    public static final int ERROR_NOT_SUPPORTED = -3;
    public static final int ERROR_NO_NETWORK = -21;
    public static final int ERROR_PARTNER_APP_BLOCKED = -305;

    @Deprecated
    public static final int ERROR_PARTNER_APP_NEED_TO_UPDATE = -358;
    public static final int ERROR_PARTNER_APP_SIGNATURE_MISMATCH = -303;
    public static final int ERROR_PARTNER_APP_VERSION_NOT_SUPPORTED = -304;
    public static final int ERROR_PARTNER_INFO_INVALID = -99;
    public static final int ERROR_PARTNER_NOT_APPROVED = -308;
    public static final int ERROR_PARTNER_SDK_API_LEVEL = -10;
    public static final int ERROR_PARTNER_SDK_VERSION_NOT_ALLOWED = -358;
    public static final int ERROR_PARTNER_SERVICE_TYPE = -11;

    @Deprecated
    public static final int ERROR_PRODUCT_BLOCKED = -305;

    @Deprecated
    public static final int ERROR_PRODUCT_ID_INVALID = -301;

    @Deprecated
    public static final int ERROR_PRODUCT_NOT_APPROVED_FOR_RELEASE = -307;

    @Deprecated
    public static final int ERROR_PRODUCT_NOT_VERIFIED_WITH_PARTNER = -303;

    @Deprecated
    public static final int ERROR_PRODUCT_UNAVAILABLE_FOR_THIS_REGION = -302;

    @Deprecated
    public static final int ERROR_PRODUCT_VERSION_NOT_SUPPORTED = -304;
    public static final int ERROR_REGISTRATION_FAIL = -104;
    public static final int ERROR_SDK_NOT_SUPPORTED_FOR_THIS_REGION = -300;
    public static final int ERROR_SERVER_INTERNAL = -311;
    public static final int ERROR_SERVER_NO_RESPONSE = -22;

    @Deprecated
    public static final int ERROR_SERVICE_BLOCKED = -305;
    public static final int ERROR_SERVICE_ID_INVALID = -301;
    public static final int ERROR_SERVICE_NOT_APPROVED_FOR_RELEASE = -307;

    @Deprecated
    public static final int ERROR_SERVICE_NOT_VERIFIED_WITH_PARTNER = -303;
    public static final int ERROR_SERVICE_UNAVAILABLE_FOR_THIS_REGION = -302;

    @Deprecated
    public static final int ERROR_SERVICE_VERSION_NOT_SUPPORTED = -304;

    @Deprecated
    public static final int ERROR_SHORT_INFORMATION = -354;
    public static final int ERROR_SPAY_APP_INTEGRITY_CHECK_FAIL = -360;
    public static final int ERROR_SPAY_APP_NEED_TO_UPDATE = -357;
    public static final int ERROR_SPAY_CONNECTED_WITH_EXTERNAL_DISPLAY = -605;
    public static final int ERROR_SPAY_FMM_LOCK = -604;
    public static final int ERROR_SPAY_INTERNAL = -1;
    public static final int ERROR_SPAY_PKG_NOT_FOUND = -351;
    public static final int ERROR_SPAY_SDK_SERVICE_NOT_AVAILABLE = -352;
    public static final int ERROR_SPAY_SETUP_NOT_COMPLETED = -356;
    public static final int ERROR_UNABLE_TO_VERIFY_CALLER = -359;
    public static final int ERROR_UNAUTHORIZED_REQUEST_TYPE = -309;
    public static final int ERROR_USER_CANCELED = -7;
    public static final int ERROR_USER_NOT_REGISTERED_FOR_DEBUG = -306;

    @ParamInfo(since = SdkApiLevel.LEVEL_2_9)
    public static final String EXTRA_ACCEPT_COMBO_CARD = "acceptComboCard";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_7)
    public static final String EXTRA_CARD_BRAND = "cardBrand";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_1)
    public static final String EXTRA_CARD_TYPE = "cardType";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_1)
    public static final String EXTRA_COUNTRY_CODE = "countryCode";

    @ParamInfo(since = SdkApiLevel.LEVEL_2_11)
    public static final String EXTRA_CPF_HOLDER_NAME = "cpfHolderName";

    @ParamInfo(since = SdkApiLevel.LEVEL_2_11)
    public static final String EXTRA_CPF_NUMBER = "cpfNumber";

    @ParamInfo(checkValue = true, since = SdkApiLevel.LEVEL_2_4)
    public static final String EXTRA_CRYPTOGRAM_TYPE = "cryptogramType";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_1)
    public static final String EXTRA_DEVICE_CARD_LIMIT_REACHED = "deviceCardLimitReached";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_1)
    public static final String EXTRA_DEVICE_TYPE = "deviceType";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_1)
    public static final String EXTRA_ERROR_REASON = "errorReason";

    @ParamInfo(since = SdkApiLevel.LEVEL_2_9)
    public static final String EXTRA_ERROR_REASON_MESSAGE = "errorReasonMessage";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_1)
    public static final String EXTRA_ISSUER_NAME = "issuerName";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_1)
    public static final String EXTRA_ISSUER_NAMES = "issuerNames";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_1)
    public static final String EXTRA_ISSUER_PKGNAME = "issuerPkgName";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_1)
    public static final String EXTRA_IS_DEFAULT_CARD = "defaultCard";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_1)
    public static final String EXTRA_LAST4_DPAN = "last4Dpan";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_1)
    public static final String EXTRA_LAST4_FPAN = "last4Fpan";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_1)
    public static final String EXTRA_MEMBER_ID = "memberID";

    @ParamInfo(since = SdkApiLevel.LEVEL_2_11)
    public static final String EXTRA_MERCHANT_REF_ID = "merchantRefId";
    public static final String EXTRA_PARTNER_BINDER = "binder";

    @ParamInfo(since = SdkApiLevel.LEVEL_2_6)
    public static final String EXTRA_PARTNER_NAME = "partnerName";

    @ParamInfo(since = SdkApiLevel.LEVEL_2_5)
    public static final String EXTRA_REQUEST_ID = "extra_request_id";

    @ParamInfo(since = SdkApiLevel.LEVEL_2_11)
    public static final String EXTRA_REQUIRE_CPF = "requireCpf";

    @ParamInfo(since = SdkApiLevel.LEVEL_2_4)
    public static final String EXTRA_RESOLVED_1 = "extra_resolved_1";

    @ParamInfo(since = SdkApiLevel.LEVEL_2_4)
    public static final String EXTRA_RESOLVED_2 = "extra_resolved_2";

    @ParamInfo(since = SdkApiLevel.LEVEL_2_4)
    public static final String EXTRA_RESOLVED_3 = "extra_resolved_3";

    @ParamInfo(since = SdkApiLevel.LEVEL_2_4)
    public static final String EXTRA_RESOLVED_4 = "extra_resolved_4";

    @ParamInfo(since = SdkApiLevel.LEVEL_2_4)
    public static final String EXTRA_RESOLVED_5 = "extra_resolved_5";

    @ParamInfo(since = SdkApiLevel.LEVEL_2_4)
    public static final String EXTRA_RESOLVED_6 = "extra_resolved_6";

    @ParamInfo(since = SdkApiLevel.LEVEL_2_4)
    public static final String EXTRA_RESOLVED_7 = "extra_resolved_7";

    @ParamInfo(since = SdkApiLevel.LEVEL_2_8)
    @Deprecated
    public static final String EXTRA_SUPPORT_COMBO_CARD = "supportComboCard";
    protected static final String KEY_FOR_MESSAGE = "KEY_FOR_MESSAGE";
    protected static final SdkApiLevel MANDATORY_SDK_API_LEVEL = SdkApiLevel.LEVEL_1_4;

    @ParamInfo(since = SdkApiLevel.LEVEL_1_4)
    public static final String PARTNER_SDK_API_LEVEL = "PartnerSdkApiLevel";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_4)
    public static final String PARTNER_SERVICE_TYPE = "PartnerServiceType";
    protected static final int SDK_REVISION = 2;
    private static final int SDK_VERSION = 12800;
    private static final String SDK_VERSION_NAME = "2.18.00";
    public static final int SPAY_HAS_NO_TRANSIT_CARD = 11;
    public static final int SPAY_HAS_TRANSIT_CARD = 10;
    public static final int SPAY_NOT_ALLOWED_TEMPORALLY = 3;
    public static final int SPAY_NOT_READY = 1;
    public static final int SPAY_NOT_SUPPORTED = 0;
    public static final int SPAY_READY = 2;
    private static final String TAG = "SPAYSDK:SpaySdk";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_2)
    public static final String WALLET_DM_ID = "walletDMId";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_2)
    public static final String WALLET_PROVIDER_ID = "walletProviderId";

    @ParamInfo(since = SdkApiLevel.LEVEL_1_2)
    public static final String WALLET_USER_ID = "walletUserId";
    public static final String WEB_CHECKOUT_API_LEVEL = "WEB_CHECKOUT_API_LEVEL";
    protected WeakReference<Context> contextRef;
    protected Binder mBinderToSdk;
    protected Handler mMainThreadHandler = new Handler(Looper.getMainLooper());
    protected PartnerInfo partnerInfo;

    public enum ServiceType {
        INAPP_PAYMENT,
        APP2APP,
        WEB_PAYMENT,
        W3C,
        MOBILEWEB_PAYMENT,
        INTERNAL_APK
    }

    public static int getVersionCode() {
        return 12800;
    }

    public enum SdkApiLevel {
        LEVEL_UNKNOWN("0.0"),
        LEVEL_1_1("1.1"),
        LEVEL_1_2("1.2"),
        LEVEL_1_3("1.3"),
        LEVEL_1_4("1.4"),
        LEVEL_1_5("1.5"),
        LEVEL_1_6("1.6"),
        LEVEL_1_7("1.7"),
        LEVEL_1_8("1.8"),
        LEVEL_1_9("1.9"),
        LEVEL_2_0("2.0"),
        LEVEL_2_1("2.1"),
        LEVEL_2_2("2.2"),
        LEVEL_2_3("2.3"),
        LEVEL_2_4("2.4"),
        LEVEL_2_5("2.5"),
        LEVEL_2_6("2.6"),
        LEVEL_2_7("2.7"),
        LEVEL_2_8("2.8"),
        LEVEL_2_9("2.9"),
        LEVEL_2_11("2.11"),
        LEVEL_2_13("2.13"),
        LEVEL_2_14("2.14"),
        LEVEL_2_15("2.15"),
        LEVEL_2_16("2.16"),
        LEVEL_2_17("2.17"),
        LEVEL_2_18("2.18");

        private String apiLevel;

        SdkApiLevel(String str) {
            this.apiLevel = str;
        }

        public String getLevel() {
            return this.apiLevel;
        }

        public static SdkApiLevel findSdkApiLevel(String str) {
            for (SdkApiLevel sdkApiLevel : values()) {
                if (TextUtils.equals(sdkApiLevel.apiLevel, str)) {
                    return sdkApiLevel;
                }
            }
            Log.e(SpaySdk.TAG, "Can not find a api level : " + str);
            return LEVEL_UNKNOWN;
        }
    }

    public static String getVersionName() {
        return "2.18.00";
    }

    protected SpaySdk(Context context, PartnerInfo partnerInfo) {
        this.partnerInfo = null;
        Log.d(TAG, "Partner SDK version : " + getVersionName());
        if (!(context instanceof Activity)) {
            Log.d(TAG, "Context is not activity. Get application context");
            context = context.getApplicationContext();
        }
        this.contextRef = new WeakReference<>(context);
        this.partnerInfo = partnerInfo;
        if (!isValidContextAndServiceId(partnerInfo)) {
            throw new NullPointerException("Context and PartnerInfo.serviceId have to be set.");
        }
        synchronized (partnerInfo) {
            Bundle data = partnerInfo.getData();
            if (data == null) {
                data = new Bundle();
                partnerInfo.setData(data);
            }
            data.putString(InternalConst.EXTRA_SDK_VERSION, getVersionName());
            Binder binder = new Binder();
            this.mBinderToSdk = binder;
            data.putBinder(EXTRA_PARTNER_BINDER, binder);
        }
    }

    public enum Brand implements Parcelable {
        AMERICANEXPRESS,
        MASTERCARD,
        VISA,
        DISCOVER,
        CHINAUNIONPAY,
        UNKNOWN_CARD,
        OCTOPUS,
        ECI,
        PAGOBANCOMAT;

        public static final Parcelable.Creator<Brand> CREATOR = new Parcelable.Creator<Brand>() { // from class: com.samsung.android.sdk.samsungpay.v2.SpaySdk.Brand.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Brand createFromParcel(Parcel parcel) {
                int readInt = parcel.readInt();
                if (readInt >= Brand.values().length) {
                    Log.e(SpaySdk.TAG, "Brand name in Parcel is not included in current SDK");
                    return Brand.UNKNOWN_CARD;
                }
                return Brand.values()[readInt];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Brand[] newArray(int i) {
                return new Brand[i];
            }
        };

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public static Brand convert(String str) {
            String upperCase = str.toUpperCase(Locale.US);
            if ("VISA".equals(upperCase) || AddCardInfo.PROVIDER_VISA.equals(upperCase)) {
                return VISA;
            }
            if ("MASTERCARD".equals(upperCase) || AddCardInfo.PROVIDER_MASTERCARD.equals(upperCase) || upperCase.contains("MASTER")) {
                return MASTERCARD;
            }
            if ("AMEX".equals(upperCase) || AddCardInfo.PROVIDER_AMEX.equals(upperCase) || upperCase.contains("AMERICANEXPRESS")) {
                return AMERICANEXPRESS;
            }
            if ("DISCOVER".equals(upperCase) || AddCardInfo.PROVIDER_DISCOVER.equals(upperCase)) {
                return DISCOVER;
            }
            if ("CUP".equals(upperCase) || upperCase.contains("CHINA")) {
                return CHINAUNIONPAY;
            }
            if ("OCL".equals(upperCase) || upperCase.contains("OC")) {
                return OCTOPUS;
            }
            if ("ECI".equals(upperCase)) {
                return ECI;
            }
            if (AddCardInfo.PROVIDER_PAGOBANCOMAT.equals(upperCase)) {
                return PAGOBANCOMAT;
            }
            return UNKNOWN_CARD;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    protected boolean isValidContextAndServiceId(PartnerInfo partnerInfo) {
        return isValidContext() && isValidServiceId(partnerInfo);
    }

    protected boolean isValidServiceId(PartnerInfo partnerInfo) {
        return (partnerInfo == null || partnerInfo.getServiceId() == null || "".equals(partnerInfo.getServiceId())) ? false : true;
    }

    protected boolean isValidContext() {
        return this.contextRef.get() != null;
    }

    protected PartnerInfo getPartnerInfo() {
        return this.partnerInfo;
    }

    protected void setPartnerInfo(PartnerInfo partnerInfo) {
        this.partnerInfo = partnerInfo;
    }

    protected void assertNotNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Input parameter must be set");
        }
    }

    protected void runOnMainThread(Runnable runnable) {
        this.mMainThreadHandler.post(runnable);
    }

    private void assertNotEmpty(Object[] objArr) {
        if (objArr == null) {
            throw new NullPointerException("Input array is null");
        }
        if (objArr.length == 0) {
            throw new IllegalArgumentException("Input array is empty");
        }
    }

    private void verifyRequestTypes(RequestType[] requestTypeArr) {
        assertNotEmpty(requestTypeArr);
        List asList = Arrays.asList(requestTypeArr);
        if (asList.contains(RequestType.NAME) && (asList.contains(RequestType.FIRST_NAME) || asList.contains(RequestType.LAST_NAME))) {
            throw new IllegalArgumentException("Name can't be requested together with First name or Last name");
        }
        if (asList.contains(RequestType.LAST_NAME) ^ asList.contains(RequestType.FIRST_NAME)) {
            throw new IllegalArgumentException("First name and Last name must be requested together");
        }
    }

    protected RequestType[] convertRequestAllType(RequestType[] requestTypeArr) {
        verifyRequestTypes(requestTypeArr);
        if (!Arrays.asList(requestTypeArr).contains(RequestType.ALL)) {
            return requestTypeArr;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(RequestType.NAME);
        arrayList.add(RequestType.TEL);
        arrayList.add(RequestType.SHIPPING_ADDRESS);
        arrayList.add(RequestType.BILLING_ADDRESS);
        arrayList.add(RequestType.DATE_OF_BIRTH);
        arrayList.add(RequestType.PAYMENT_CARD);
        arrayList.add(RequestType.MODEL_NAME);
        arrayList.add(RequestType.IMEI);
        arrayList.add(RequestType.EMAIL);
        arrayList.add(RequestType.NO_SHEET_UPDATE_CALLBACK);
        return (RequestType[]) arrayList.toArray(new RequestType[0]);
    }
}
