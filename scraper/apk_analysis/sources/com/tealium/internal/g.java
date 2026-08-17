package com.tealium.internal;

import android.app.ActivityManager;
import android.app.UiModeManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Debug;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.os.StatFs;
import android.util.Log;
import com.tealium.library.BuildConfig;
import com.tealium.library.Tealium;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: TealiumUtils.java */
/* loaded from: classes2.dex */
public final class g {

    /* compiled from: TealiumUtils.java */
    public static final class b {
        public static SharedPreferences a(Tealium.Config config) {
            return config.getApplication().getSharedPreferences("tealium.datasources." + Integer.toHexString(b(config)), 0);
        }

        private static int b(Tealium.Config config) {
            return (config.getAccountName() + ClassUtils.PACKAGE_SEPARATOR_CHAR + config.getProfileName() + ClassUtils.PACKAGE_SEPARATOR_CHAR + config.getEnvironmentName()).hashCode();
        }
    }

    /* compiled from: TealiumUtils.java */
    public static final class c {
        public static String a(String str) {
            if (str == null || str.length() == 0) {
                return str;
            }
            char charAt = str.charAt(0);
            if (Character.isUpperCase(charAt)) {
                return str;
            }
            return Character.toUpperCase(charAt) + str.substring(1);
        }
    }

    public static String a(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static String b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static boolean c(Context context) {
        return ((UiModeManager) context.getSystemService("uimode")).getCurrentModeType() == 4;
    }

    public static boolean c() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static long a(ActivityManager activityManager) {
        try {
            Debug.MemoryInfo[] processMemoryInfo = activityManager.getProcessMemoryInfo(new int[]{Process.myPid()});
            if (processMemoryInfo.length > 0) {
                int totalPss = processMemoryInfo[0].getTotalPss();
                if (totalPss >= 0) {
                    return (long) (totalPss / 1024.0d);
                }
            }
        } catch (Exception e) {
            Log.e(BuildConfig.DEBUG_TAG, "Error retrieving process memory.", e);
        }
        return 0L;
    }

    public static long b() {
        StatFs statFs = new StatFs(Environment.getRootDirectory().getPath());
        return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
    }

    /* compiled from: TealiumUtils.java */
    public static final class a {
        public static String a(File file) {
            if (!file.exists()) {
                return null;
            }
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String str = "";
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            bufferedReader.close();
                            return str;
                        }
                        str = str + readLine;
                    } finally {
                    }
                }
            } catch (IOException unused) {
                return null;
            }
        }

        public static boolean a(File file, String str) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    fileOutputStream.write(str.getBytes());
                    fileOutputStream.close();
                    return true;
                } finally {
                }
            } catch (IOException unused) {
                return false;
            }
        }
    }

    public static long a() {
        return new StatFs(Environment.getExternalStorageDirectory().getPath()).getAvailableBlocksLong() * new StatFs(Environment.getRootDirectory().getPath()).getBlockSizeLong();
    }

    public static Set<String> a(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            hashSet.add(str);
        }
        return hashSet;
    }

    public static String[] a(Set<String> set) {
        if (set == null) {
            return new String[0];
        }
        return (String[]) set.toArray(new String[0]);
    }
}
