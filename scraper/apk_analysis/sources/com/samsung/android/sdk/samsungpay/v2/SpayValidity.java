package com.samsung.android.sdk.samsungpay.v2;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat;
import androidx.room.RoomDatabase;
import com.samsung.android.sdk.samsungpay.BuildConfig;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import com.samsung.android.sdk.samsungpay.v2.card.GetCardListener;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.AmountBoxControl;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.CustomSheet;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetControl;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.SheetItem;
import io.sentry.ProfileChunk;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes2.dex */
public final class SpayValidity {
    public static final int ERROR_NONE_INTERNAL = -999;
    private static final String TAG = "SPAYSDK:SpayValidity";
    protected static SpaySdk.SdkApiLevel sPartnerManifestApiLevel = SpaySdk.SdkApiLevel.LEVEL_UNKNOWN;
    private final int MIN_SUPPORTED_CA_VERSION;
    private Context context;
    private String mBrand;
    private boolean mIsWatch;
    private String mManufacture;
    protected SpaySdk.SdkApiLevel mPartnerDefinedSdkApiLevel;
    private String mSpayPackageName;
    private String mSpayServiceName;
    protected PackageInfo spayPackageInfo;

    protected void setSpayPackageInfo(PackageInfo packageInfo) {
        this.spayPackageInfo = packageInfo;
    }

    protected PackageInfo getSpayPackageInfo() {
        return this.spayPackageInfo;
    }

    public SpayValidity(Context context) {
        this.MIN_SUPPORTED_CA_VERSION = 203710006;
        this.mIsWatch = false;
        this.mPartnerDefinedSdkApiLevel = SpaySdk.SdkApiLevel.LEVEL_UNKNOWN;
        this.spayPackageInfo = new PackageInfo();
        init(context);
    }

    public SpayValidity(Context context, boolean z) {
        this.MIN_SUPPORTED_CA_VERSION = 203710006;
        this.mIsWatch = false;
        this.mPartnerDefinedSdkApiLevel = SpaySdk.SdkApiLevel.LEVEL_UNKNOWN;
        this.spayPackageInfo = new PackageInfo();
        this.mIsWatch = z;
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        this.mBrand = Build.BRAND;
        this.mManufacture = Build.MANUFACTURER;
        loadDefinedSdkApiLevelInManifest();
        boolean z = this.mIsWatch;
        this.mSpayPackageName = z ? "com.samsung.android.samsungpay.gear" : InternalConst.SERVICE_PACKAGE;
        this.mSpayServiceName = z ? "com.samsung.android.samsungpay.gear.sdk.v2.service.SPaySDKV2Service" : InternalConst.SERVICE_NAME;
    }

    protected String getSpayPackageName() {
        return this.mSpayPackageName;
    }

    protected void setSpayPackageName(String str) {
        this.mSpayPackageName = str;
    }

    protected String getSpayServiceName() {
        return this.mSpayServiceName;
    }

    protected void setSpayServiceName(String str) {
        this.mSpayServiceName = str;
    }

    public int getSpayValidity(PartnerInfo partnerInfo, SpaySdk.SdkApiLevel sdkApiLevel, PartnerRequest partnerRequest) {
        String str;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str = (String) cls.getMethod("get", String.class).invoke(cls, "ro.csc.countryiso_code");
        } catch (Exception e) {
            e.printStackTrace();
            str = "";
        }
        String upperCase = str.toUpperCase(Locale.US);
        if (!isSupportedSpay()) {
            Log.i(TAG, "[FAIL] Spay Local validity check");
            return getSpayLocalValidity();
        }
        Log.i(TAG, "[PASS] Spay Local validity check");
        if (this.mIsWatch && !isSupportedCA()) {
            Log.i(TAG, "[FAIL] CA version validity check");
            return SpaySdk.ERROR_SPAY_APP_NEED_TO_UPDATE;
        }
        Log.i(TAG, "[PASS] CA version validity check");
        if (!isIntactSpay()) {
            Log.i(TAG, "[FAIL] Spay integrity check");
            return SpaySdk.ERROR_SPAY_APP_INTEGRITY_CHECK_FAIL;
        }
        Log.i(TAG, "[PASS] Spay integrity check");
        if (isNeedUpgradeSpay(upperCase, getSpayPackageInfo().versionCode / AndroidComposeViewAccessibilityDelegateCompat.ParcelSafeTextLength)) {
            Log.i(TAG, "[FAIL] Minimum Spay app version check");
            return SpaySdk.ERROR_SPAY_APP_NEED_TO_UPDATE;
        }
        Log.i(TAG, "[PASS] Minimum Spay app version check");
        if (!canFindSdkService(getSpayPackageName(), getSpayServiceName())) {
            Log.i(TAG, "[FAIL] Spay SDK service check");
            return SpaySdk.ERROR_SPAY_SETUP_NOT_COMPLETED;
        }
        Log.i(TAG, "[PASS] Spay SDK service check");
        if (!isValidPartnerServiceType(partnerInfo.getData())) {
            Log.i(TAG, "[FAIL] Defined Service Type check");
            return -11;
        }
        Log.i(TAG, "[PASS] Defined Service Type check");
        if (!isSupportedAndroidPlatform(upperCase, partnerInfo.getData())) {
            Log.i(TAG, "[FAIL] Minimum Android Platform version check");
            return SpaySdk.ERROR_ANDROID_PLATFORM_CHECK_FAIL;
        }
        Log.i(TAG, "[PASS] Minimum Android Platform version check");
        if (!isValidPartnerSdkApiLevel()) {
            Log.i(TAG, "[FAIL] Defined SDK API Level check");
            return -10;
        }
        Log.i(TAG, "[PASS] Defined SDK API Level check");
        if (!isValidUsingSdkApiLevel(sdkApiLevel)) {
            Log.i(TAG, "[FAIL] Using SDK API Level check");
            return -10;
        }
        Log.i(TAG, "[PASS] Using SDK API Level check");
        if (containsNotDefinedField(partnerRequest)) {
            Log.i(TAG, "[FAIL] Using parameters Level check");
            return -10;
        }
        Log.i(TAG, "[PASS] Using parameters Level check");
        return ERROR_NONE_INTERNAL;
    }

    protected int errorCodeToReadyStatus(int i) {
        if (i == -999) {
            return 2;
        }
        if (i == -361 || i == -360) {
            return 0;
        }
        if (i != -11 && i != -10) {
            switch (i) {
                case -358:
                    break;
                case SpaySdk.ERROR_SPAY_APP_NEED_TO_UPDATE /* -357 */:
                case SpaySdk.ERROR_SPAY_SETUP_NOT_COMPLETED /* -356 */:
                    break;
                default:
                    switch (i) {
                        case SpaySdk.ERROR_SPAY_SDK_SERVICE_NOT_AVAILABLE /* -352 */:
                        case SpaySdk.ERROR_SPAY_PKG_NOT_FOUND /* -351 */:
                        case SpaySdk.ERROR_DEVICE_NOT_SAMSUNG /* -350 */:
                            break;
                        default:
                            Log.e(TAG, "sdk can not catch spay status. " + getSpayLocalValidity());
                            break;
                    }
            }
            return 0;
        }
        return -99;
    }

    protected void callbackResult(Object obj, int i) {
        int errorCodeToReadyStatus = errorCodeToReadyStatus(i);
        Bundle bundle = new Bundle();
        bundle.putInt(SpaySdk.EXTRA_ERROR_REASON, i);
        if (obj instanceof StatusListener) {
            ((StatusListener) obj).onSuccess(errorCodeToReadyStatus, bundle);
        } else if (obj instanceof GetCardListener) {
            ((GetCardListener) obj).onFail(errorCodeToReadyStatus, bundle);
        } else {
            Log.e(TAG, "sdk can not catch result callback.");
        }
    }

    protected void setBuildBrand(String str) {
        this.mBrand = str;
    }

    protected String getBuildBrand() {
        return this.mBrand;
    }

    protected void setBuildManufacture(String str) {
        this.mManufacture = str;
    }

    protected String getBuildManufacture() {
        return this.mManufacture;
    }

    protected boolean isSamsungDevice() {
        return "Samsung".compareToIgnoreCase(getBuildBrand()) == 0 || "Samsung".compareToIgnoreCase(getBuildManufacture()) == 0;
    }

    protected int getSpayLocalValidity() {
        if (!this.mIsWatch && !isSamsungDevice()) {
            Log.i(TAG, "This is not Samsung device.");
            return SpaySdk.ERROR_DEVICE_NOT_SAMSUNG;
        }
        if (canFindSpayPackage(getSpayPackageName())) {
            return RoomDatabase.MAX_BIND_PARAMETER_CNT;
        }
        Log.i(TAG, "SamsungPay package is not exist.");
        return SpaySdk.ERROR_SPAY_PKG_NOT_FOUND;
    }

    protected boolean canFindSpayPackage(String str) {
        try {
            setSpayPackageInfo(this.context.getPackageManager().getPackageInfo(str, 1));
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.i(TAG, "Unable to find Samsung Pay application on the device");
            return false;
        }
    }

    protected boolean isSupportedSpay() {
        return getSpayLocalValidity() == 999;
    }

    protected boolean canFindSdkService(String str, String str2) {
        try {
            this.context.getPackageManager().getServiceInfo(new ComponentName(str, str2), 4);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e(TAG, "can not find spay app or service");
            return false;
        } catch (NullPointerException unused2) {
            return false;
        }
    }

    protected boolean isNeedUpgradeSpay(String str, int i) {
        if (this.mIsWatch) {
            if (173000006 < getSpayPackageInfo().versionCode) {
                return false;
            }
        } else {
            VersionChecker versionChecker = new VersionChecker();
            if (!versionChecker.wasReleasedSpayAppExcludeVersionCheck(str) || versionChecker.isIncludeVersionCheckingRule(str, i)) {
                return false;
            }
        }
        Log.w(TAG, "SamsungPay App is old version. SamsungPay App needs to be updated.");
        return true;
    }

    private boolean isIntactSpay() {
        if (this.mIsWatch) {
            return isValidWatchPlugInSignature();
        }
        boolean z = this.context.getPackageManager().checkSignatures(ProfileChunk.PLATFORM_ANDROID, InternalConst.SERVICE_PACKAGE) == 0;
        if (!z) {
            Log.e(TAG, "Spay has no integrity.");
        }
        return z;
    }

    private boolean isSupportedCA() {
        boolean z = false;
        if (this.spayPackageInfo != null) {
            Log.d(TAG, "CA versionCode = " + this.spayPackageInfo.versionCode);
            if (this.spayPackageInfo.versionCode >= 203710006) {
                z = true;
            }
        }
        Log.d(TAG, "isSupportedCA = " + z);
        return z;
    }

    protected boolean isValidPartnerServiceType(Bundle bundle) {
        try {
            String string = bundle.getString(SpaySdk.PARTNER_SERVICE_TYPE, "");
            Log.i(TAG, "Partner defined Service Type : " + string);
            for (SpaySdk.ServiceType serviceType : SpaySdk.ServiceType.values()) {
                if (string.equals(serviceType.toString())) {
                    return true;
                }
            }
            Log.e(TAG, "Partner service type is not valid. Refer SpaySdk.ServiceType enum.");
            return false;
        } catch (NullPointerException e) {
            e.printStackTrace();
            Log.e(TAG, "Fail to validate Partner service type.");
            return false;
        }
    }

    protected boolean isSupportedAndroidPlatform(String str, Bundle bundle) {
        int i = Build.VERSION.SDK_INT;
        String string = bundle.getString(SpaySdk.PARTNER_SERVICE_TYPE, "");
        Log.d(TAG, "[isAndroidPlaformAvailable] Device country code : " + str);
        Log.d(TAG, "[isAndroidPlaformAvailable] Android Platform API Level : " + i);
        Log.d(TAG, "[isAndroidPlaformAvailable] Partner Service Type : " + string);
        if ("KR".equalsIgnoreCase(str)) {
            return true;
        }
        "CN".equalsIgnoreCase(str);
        return true;
    }

    protected boolean isValidPartnerSdkApiLevel() {
        try {
            return new VersionChecker().isValidSdkApiLevel(this.mPartnerDefinedSdkApiLevel, SpaySdk.SdkApiLevel.findSdkApiLevel("2.18"));
        } catch (NullPointerException e) {
            e.printStackTrace();
            Log.e(TAG, "Fail to validate SDK API Level.");
            return false;
        }
    }

    protected boolean isValidUsingSdkApiLevel(SpaySdk.SdkApiLevel sdkApiLevel) {
        try {
            return new VersionChecker().isValidSdkApiLevel(sdkApiLevel, this.mPartnerDefinedSdkApiLevel);
        } catch (NullPointerException e) {
            e.printStackTrace();
            Log.e(TAG, "Fail to validate SDK API Level.");
            return false;
        }
    }

    protected boolean containsNotDefinedField(PartnerRequest partnerRequest) {
        if (partnerRequest.checkApiLevelParams == null) {
            return false;
        }
        for (Object obj : partnerRequest.checkApiLevelParams) {
            if (obj != null && notDefinedInPartnerApiLevel(obj)) {
                return true;
            }
        }
        return false;
    }

    private boolean notDefinedInPartnerApiLevel(Object obj) {
        if ((obj instanceof String) || (obj instanceof Bundle) || obj.getClass().isEnum()) {
            if (ApiLevelTable.getInstance().valueNotDefinedInPartnerApiLevel(obj)) {
                return true;
            }
        } else if (obj instanceof CustomSheet) {
            for (SheetControl sheetControl : ((CustomSheet) obj).getSheetControls()) {
                if (sheetControl instanceof AmountBoxControl) {
                    Iterator<SheetItem> it = ((AmountBoxControl) sheetControl).getItems().iterator();
                    while (it.hasNext()) {
                        if (ApiLevelTable.getInstance().valueNotDefinedInPartnerApiLevel(it.next().getSValue())) {
                            return true;
                        }
                    }
                }
            }
        } else if (obj.getClass().isArray()) {
            for (Object obj2 : (Object[]) obj) {
                if (notDefinedInPartnerApiLevel(obj2)) {
                    return true;
                }
            }
        } else if (ApiLevelTable.getInstance().findFieldContainsNotDefinedValue(obj)) {
            return true;
        }
        return false;
    }

    public SpaySdk.SdkApiLevel getSdkApiLevelInManifest() {
        return this.mPartnerDefinedSdkApiLevel;
    }

    private void loadDefinedSdkApiLevelInManifest() {
        this.mPartnerDefinedSdkApiLevel = readPartnerApiLevelInManifest(this.context);
        ApiLevelTable.getInstance().setPartnerDefinedApiLevel(this.mPartnerDefinedSdkApiLevel);
        Log.i(TAG, "Partner defined SDK API Level : " + this.mPartnerDefinedSdkApiLevel);
    }

    public static SpaySdk.SdkApiLevel readPartnerApiLevelInManifest(Context context) {
        if (sPartnerManifestApiLevel != SpaySdk.SdkApiLevel.LEVEL_UNKNOWN) {
            return sPartnerManifestApiLevel;
        }
        try {
            SpaySdk.SdkApiLevel findSdkApiLevel = SpaySdk.SdkApiLevel.findSdkApiLevel(String.valueOf(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getFloat(InternalConst.SPAY_SDK_API_LEVEL)));
            sPartnerManifestApiLevel = findSdkApiLevel;
            return findSdkApiLevel;
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            throw new IllegalStateException("Failed to load SDK API Level in Application Manifest" + e.toString());
        }
    }

    private boolean isValidWatchPlugInSignature() {
        try {
            if (TextUtils.equals(BuildConfig.STORED_WATCH_SIGNATURE, getWatchPlugInSignature())) {
                return true;
            }
            Log.e(TAG, "Signature of Samsung Pay(Watch Plugin) is not matched");
            return false;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return false;
        }
    }

    private String getWatchPlugInSignature() {
        try {
            return getSHA256Encode(this.context.getPackageManager().getPackageInfo("com.samsung.android.samsungpay.gear", 64).signatures[0].toByteArray());
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }

    private static String getSHA256Encode(byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(bArr, 0, bArr.length);
        return convertToHex(messageDigest.digest());
    }

    private static String convertToHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            int i = (b >>> 4) & 15;
            int i2 = 0;
            while (true) {
                sb.append((char) ((i < 0 || i > 9) ? i + 87 : i + 48));
                i = b & 15;
                int i3 = i2 + 1;
                if (i2 >= 1) {
                    break;
                }
                i2 = i3;
            }
        }
        return sb.toString();
    }
}
