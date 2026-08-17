package com.samsung.android.sdk.samsungpay.v2;

import android.util.Log;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;

/* loaded from: classes2.dex */
class VersionChecker {
    static final int COUNTRY_COLUMN = 0;
    static final int LAST_UNSUPPORTED_VERSION_CODE_FOR_SPAY_WATCH_PLUGIN = 173000006;
    private static final String TAG = "SPAYSDK:VersionChecker";
    static final int VERSION_CODE_COLUMN = 1;
    String[][] countryAndMinimumVer = {new String[]{"US", "2300"}, new String[]{"PR", "2300"}, new String[]{"KR", "2250"}, new String[]{"BR", "2400"}, new String[]{"ES", "2400"}, new String[]{"AU", "2400"}, new String[]{"SG", "2400"}, new String[]{"CN", "2800"}};

    protected boolean wasReleasedSpayAppExcludeVersionCheck(String str) {
        int length = this.countryAndMinimumVer.length;
        for (int i = 0; i < length; i++) {
            if (this.countryAndMinimumVer[i][0].equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    protected boolean isIncludeVersionCheckingRule(String str, int i) {
        int length = this.countryAndMinimumVer.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (this.countryAndMinimumVer[i2][0].equalsIgnoreCase(str)) {
                return Integer.parseInt(this.countryAndMinimumVer[i2][1]) <= i;
            }
        }
        return false;
    }

    protected boolean isValidSdkApiLevel(SpaySdk.SdkApiLevel sdkApiLevel, SpaySdk.SdkApiLevel sdkApiLevel2) {
        if (sdkApiLevel == null || sdkApiLevel == SpaySdk.SdkApiLevel.LEVEL_UNKNOWN || sdkApiLevel2 == null || sdkApiLevel2 == SpaySdk.SdkApiLevel.LEVEL_UNKNOWN) {
            Log.e(TAG, "SDK API Level was not defined.");
            return false;
        }
        if (sdkApiLevel.compareTo(sdkApiLevel2) <= 0) {
            return true;
        }
        Log.e(TAG, "API level defined in manifest(" + sdkApiLevel + ") must lower than(or same with) SDK api level(" + sdkApiLevel2 + "). You should change your api level in the AndroidManifest");
        return false;
    }
}
