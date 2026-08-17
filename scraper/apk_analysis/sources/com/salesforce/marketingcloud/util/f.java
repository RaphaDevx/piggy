package com.salesforce.marketingcloud.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;

/* loaded from: classes2.dex */
public final class f {
    public static final String[] a = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION"};
    private static String b;

    private f() {
    }

    public static boolean a(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }

    public static boolean b(PackageManager packageManager, Intent intent) {
        return packageManager.queryIntentServices(intent, 65536).size() > 0;
    }

    public static boolean c(Context context) {
        if (context == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 31) {
            return a(context, "android.permission.BLUETOOTH_SCAN") && a(context, "android.permission.BLUETOOTH_CONNECT");
        }
        return true;
    }

    public static boolean a(PackageManager packageManager, Intent intent) {
        return packageManager.queryBroadcastReceivers(intent, 0).size() > 0;
    }

    public static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        for (String str : a) {
            if (!a(context, str)) {
                return false;
            }
        }
        return true;
    }

    public static String a(Context context) {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        b = "";
        if (context == null) {
            return "";
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            b = String.format("%s : %s", packageInfo.versionName, Integer.valueOf(packageInfo.versionCode));
        } catch (PackageManager.NameNotFoundException e) {
            com.salesforce.marketingcloud.g.b(j.c, e, "Failed to get Application Version from the PackageManager.", new Object[0]);
        }
        return b;
    }
}
