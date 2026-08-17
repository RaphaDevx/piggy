package com.getcapacitor.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

/* loaded from: classes3.dex */
public class InternalUtils {
    public static PackageInfo getPackageInfo(PackageManager packageManager, String str) throws PackageManager.NameNotFoundException {
        return getPackageInfo(packageManager, str, 0L);
    }

    public static PackageInfo getPackageInfo(PackageManager packageManager, String str, long j) throws PackageManager.NameNotFoundException {
        PackageManager.PackageInfoFlags of;
        PackageInfo packageInfo;
        if (Build.VERSION.SDK_INT >= 33) {
            of = PackageManager.PackageInfoFlags.of(j);
            packageInfo = packageManager.getPackageInfo(str, of);
            return packageInfo;
        }
        return getPackageInfoLegacy(packageManager, str, (int) j);
    }

    private static PackageInfo getPackageInfoLegacy(PackageManager packageManager, String str, long j) throws PackageManager.NameNotFoundException {
        return packageManager.getPackageInfo(str, (int) j);
    }
}
