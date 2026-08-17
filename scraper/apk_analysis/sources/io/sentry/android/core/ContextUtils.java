package io.sentry.android.core;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import com.tealium.library.DataSources;
import io.sentry.ILogger;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.android.core.util.AndroidLazyEvaluator;
import io.sentry.protocol.App;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public final class ContextUtils {
    private static final AndroidLazyEvaluator<PackageInfo> staticPackageInfo33 = new AndroidLazyEvaluator<>(new AndroidLazyEvaluator.AndroidEvaluator() { // from class: io.sentry.android.core.ContextUtils$$ExternalSyntheticLambda2
        @Override // io.sentry.android.core.util.AndroidLazyEvaluator.AndroidEvaluator
        public final Object evaluate(Context context) {
            return ContextUtils.lambda$static$0(context);
        }
    });
    private static final AndroidLazyEvaluator<PackageInfo> staticPackageInfo = new AndroidLazyEvaluator<>(new AndroidLazyEvaluator.AndroidEvaluator() { // from class: io.sentry.android.core.ContextUtils$$ExternalSyntheticLambda3
        @Override // io.sentry.android.core.util.AndroidLazyEvaluator.AndroidEvaluator
        public final Object evaluate(Context context) {
            return ContextUtils.lambda$static$1(context);
        }
    });
    private static final AndroidLazyEvaluator<String> applicationName = new AndroidLazyEvaluator<>(new AndroidLazyEvaluator.AndroidEvaluator() { // from class: io.sentry.android.core.ContextUtils$$ExternalSyntheticLambda4
        @Override // io.sentry.android.core.util.AndroidLazyEvaluator.AndroidEvaluator
        public final Object evaluate(Context context) {
            return ContextUtils.lambda$static$2(context);
        }
    });
    private static final AndroidLazyEvaluator<ApplicationInfo> staticAppInfo33 = new AndroidLazyEvaluator<>(new AndroidLazyEvaluator.AndroidEvaluator() { // from class: io.sentry.android.core.ContextUtils$$ExternalSyntheticLambda5
        @Override // io.sentry.android.core.util.AndroidLazyEvaluator.AndroidEvaluator
        public final Object evaluate(Context context) {
            return ContextUtils.lambda$static$3(context);
        }
    });
    private static final AndroidLazyEvaluator<ApplicationInfo> staticAppInfo = new AndroidLazyEvaluator<>(new AndroidLazyEvaluator.AndroidEvaluator() { // from class: io.sentry.android.core.ContextUtils$$ExternalSyntheticLambda6
        @Override // io.sentry.android.core.util.AndroidLazyEvaluator.AndroidEvaluator
        public final Object evaluate(Context context) {
            return ContextUtils.lambda$static$4(context);
        }
    });

    static class SideLoadedInfo {
        private final String installerStore;
        private final boolean isSideLoaded;

        public SideLoadedInfo(boolean z, String str) {
            this.isSideLoaded = z;
            this.installerStore = str;
        }

        public boolean isSideLoaded() {
            return this.isSideLoaded;
        }

        public String getInstallerStore() {
            return this.installerStore;
        }

        public Map<String, String> asTags() {
            HashMap hashMap = new HashMap();
            hashMap.put("isSideLoaded", String.valueOf(this.isSideLoaded));
            String str = this.installerStore;
            if (str != null) {
                hashMap.put("installerStore", str);
            }
            return hashMap;
        }
    }

    static class SplitApksInfo {
        static final String SPLITS_REQUIRED = "com.android.vending.splits.required";
        private final boolean isSplitApks;
        private final String[] splitNames;

        public SplitApksInfo(boolean z, String[] strArr) {
            this.isSplitApks = z;
            this.splitNames = strArr;
        }

        public boolean isSplitApks() {
            return this.isSplitApks;
        }

        public String[] getSplitNames() {
            return this.splitNames;
        }
    }

    private ContextUtils() {
    }

    static /* synthetic */ PackageInfo lambda$static$0(Context context) {
        PackageManager.PackageInfoFlags of;
        PackageInfo packageInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            of = PackageManager.PackageInfoFlags.of(0L);
            packageInfo = packageManager.getPackageInfo(packageName, of);
            return packageInfo;
        } catch (Throwable unused) {
            return null;
        }
    }

    static /* synthetic */ PackageInfo lambda$static$1(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (Throwable unused) {
            return null;
        }
    }

    static /* synthetic */ String lambda$static$2(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            int i = applicationInfo.labelRes;
            if (i == 0) {
                if (applicationInfo.nonLocalizedLabel != null) {
                    return applicationInfo.nonLocalizedLabel.toString();
                }
                return context.getPackageManager().getApplicationLabel(applicationInfo).toString();
            }
            return context.getString(i);
        } catch (Throwable unused) {
            return null;
        }
    }

    static /* synthetic */ ApplicationInfo lambda$static$3(Context context) {
        PackageManager.ApplicationInfoFlags of;
        ApplicationInfo applicationInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            of = PackageManager.ApplicationInfoFlags.of(128L);
            applicationInfo = packageManager.getApplicationInfo(packageName, of);
            return applicationInfo;
        } catch (Throwable unused) {
            return null;
        }
    }

    static /* synthetic */ ApplicationInfo lambda$static$4(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (Throwable unused) {
            return null;
        }
    }

    static PackageInfo getPackageInfo(Context context, BuildInfoProvider buildInfoProvider) {
        if (buildInfoProvider.getSdkInfoVersion() >= 33) {
            return staticPackageInfo33.getValue(context);
        }
        return staticPackageInfo.getValue(context);
    }

    static PackageInfo getPackageInfo(Context context, int i, ILogger iLogger, BuildInfoProvider buildInfoProvider) {
        PackageManager.PackageInfoFlags of;
        PackageInfo packageInfo;
        try {
            if (buildInfoProvider.getSdkInfoVersion() >= 33) {
                PackageManager packageManager = context.getPackageManager();
                String packageName = context.getPackageName();
                of = PackageManager.PackageInfoFlags.of(i);
                packageInfo = packageManager.getPackageInfo(packageName, of);
                return packageInfo;
            }
            return context.getPackageManager().getPackageInfo(context.getPackageName(), i);
        } catch (Throwable th) {
            iLogger.log(SentryLevel.ERROR, "Error getting package info.", th);
            return null;
        }
    }

    static ApplicationInfo getApplicationInfo(Context context, BuildInfoProvider buildInfoProvider) {
        if (buildInfoProvider.getSdkInfoVersion() >= 33) {
            return staticAppInfo33.getValue(context);
        }
        return staticAppInfo.getValue(context);
    }

    static String getVersionCode(PackageInfo packageInfo, BuildInfoProvider buildInfoProvider) {
        if (buildInfoProvider.getSdkInfoVersion() >= 28) {
            return Long.toString(packageInfo.getLongVersionCode());
        }
        return getVersionCodeDep(packageInfo);
    }

    static String getVersionName(PackageInfo packageInfo) {
        return packageInfo.versionName;
    }

    private static String getVersionCodeDep(PackageInfo packageInfo) {
        return Integer.toString(packageInfo.versionCode);
    }

    public static boolean isForegroundImportance() {
        try {
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            return runningAppProcessInfo.importance == 100;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean appIsLibraryForComposePreview(Context context) {
        if (!context.getPackageName().endsWith(".test")) {
            return false;
        }
        try {
            Iterator<ActivityManager.AppTask> it = ((ActivityManager) context.getSystemService(DataSources.EventTypeValue.ACTIVITY_EVENT_TYPE)).getAppTasks().iterator();
            while (it.hasNext()) {
                ComponentName component = it.next().getTaskInfo().baseIntent.getComponent();
                if (component != null && component.getClassName().equals("androidx.compose.ui.tooling.PreviewActivity")) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    static String getKernelVersion(ILogger iLogger) {
        String property = System.getProperty("os.version");
        File file = new File("/proc/version");
        if (!file.canRead()) {
            return property;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            try {
                String readLine = bufferedReader.readLine();
                bufferedReader.close();
                return readLine;
            } finally {
            }
        } catch (IOException e) {
            iLogger.log(SentryLevel.ERROR, "Exception while attempting to read kernel information", e);
            return property;
        }
    }

    static SideLoadedInfo retrieveSideLoadedInfo(Context context, ILogger iLogger, BuildInfoProvider buildInfoProvider) {
        String str;
        try {
            PackageInfo packageInfo = getPackageInfo(context, buildInfoProvider);
            PackageManager packageManager = context.getPackageManager();
            if (packageInfo != null && packageManager != null) {
                str = packageInfo.packageName;
                try {
                    String installerPackageName = packageManager.getInstallerPackageName(str);
                    return new SideLoadedInfo(installerPackageName == null, installerPackageName);
                } catch (IllegalArgumentException unused) {
                    iLogger.log(SentryLevel.DEBUG, "%s package isn't installed.", str);
                    return null;
                }
            }
        } catch (IllegalArgumentException unused2) {
            str = null;
        }
        return null;
    }

    static SplitApksInfo retrieveSplitApksInfo(Context context, BuildInfoProvider buildInfoProvider) {
        ApplicationInfo applicationInfo = getApplicationInfo(context, buildInfoProvider);
        PackageInfo packageInfo = getPackageInfo(context, buildInfoProvider);
        if (packageInfo == null) {
            return null;
        }
        return new SplitApksInfo((applicationInfo == null || applicationInfo.metaData == null) ? false : applicationInfo.metaData.getBoolean("com.android.vending.splits.required"), packageInfo.splitNames);
    }

    static String getApplicationName(Context context) {
        return applicationName.getValue(context);
    }

    static DisplayMetrics getDisplayMetrics(Context context, ILogger iLogger) {
        try {
            return context.getResources().getDisplayMetrics();
        } catch (Throwable th) {
            iLogger.log(SentryLevel.ERROR, "Error getting DisplayMetrics.", th);
            return null;
        }
    }

    static String getFamily(ILogger iLogger) {
        try {
            return Build.MODEL.split(StringUtils.SPACE, -1)[0];
        } catch (Throwable th) {
            iLogger.log(SentryLevel.ERROR, "Error getting device family.", th);
            return null;
        }
    }

    static String[] getArchitectures() {
        return Build.SUPPORTED_ABIS;
    }

    static ActivityManager.MemoryInfo getMemInfo(Context context, ILogger iLogger) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(DataSources.EventTypeValue.ACTIVITY_EVENT_TYPE);
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            if (activityManager == null) {
                iLogger.log(SentryLevel.INFO, "Error getting MemoryInfo.", new Object[0]);
                return null;
            }
            activityManager.getMemoryInfo(memoryInfo);
            return memoryInfo;
        } catch (Throwable th) {
            iLogger.log(SentryLevel.ERROR, "Error getting MemoryInfo.", th);
            return null;
        }
    }

    static Intent registerReceiver(Context context, SentryOptions sentryOptions, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, Handler handler) {
        return registerReceiver(context, new BuildInfoProvider(sentryOptions.getLogger()), broadcastReceiver, intentFilter, handler);
    }

    static Intent registerReceiver(Context context, BuildInfoProvider buildInfoProvider, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, Handler handler) {
        if (buildInfoProvider.getSdkInfoVersion() >= 33) {
            return context.registerReceiver(broadcastReceiver, intentFilter, null, handler, 4);
        }
        return context.registerReceiver(broadcastReceiver, intentFilter, null, handler);
    }

    static void setAppPackageInfo(PackageInfo packageInfo, BuildInfoProvider buildInfoProvider, DeviceInfoUtil deviceInfoUtil, App app) {
        app.setAppIdentifier(packageInfo.packageName);
        app.setAppVersion(packageInfo.versionName);
        app.setAppBuild(getVersionCode(packageInfo, buildInfoProvider));
        HashMap hashMap = new HashMap();
        String[] strArr = packageInfo.requestedPermissions;
        int[] iArr = packageInfo.requestedPermissionsFlags;
        if (strArr != null && strArr.length > 0 && iArr != null && iArr.length > 0) {
            for (int i = 0; i < strArr.length; i++) {
                String str = strArr[i];
                hashMap.put(str.substring(str.lastIndexOf(46) + 1), (iArr[i] & 2) == 2 ? "granted" : "not_granted");
            }
        }
        app.setPermissions(hashMap);
        if (deviceInfoUtil != null) {
            try {
                SplitApksInfo splitApksInfo = deviceInfoUtil.getSplitApksInfo();
                if (splitApksInfo != null) {
                    app.setSplitApks(Boolean.valueOf(splitApksInfo.isSplitApks()));
                    if (splitApksInfo.getSplitNames() != null) {
                        app.setSplitNames(Arrays.asList(splitApksInfo.getSplitNames()));
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static Context getApplicationContext(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext != null ? applicationContext : context;
    }

    static void resetInstance() {
        staticPackageInfo33.resetValue();
        staticPackageInfo.resetValue();
        applicationName.resetValue();
        staticAppInfo33.resetValue();
        staticAppInfo.resetValue();
    }
}
