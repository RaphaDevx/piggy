package androidx.webkit;

import android.content.Context;
import androidx.compose.animation.core.MutatorMutex$$ExternalSyntheticBackportWithForwarding0;
import androidx.webkit.internal.ApiHelperForP;
import androidx.webkit.internal.StartupApiFeature;
import androidx.webkit.internal.WebViewFeatureInternal;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.support_lib_boundary.ProcessGlobalConfigConstants;

/* loaded from: classes3.dex */
public class ProcessGlobalConfig {
    public static final int UI_THREAD_STARTUP_MODE_ASYNC = 3;

    @Deprecated
    public static final int UI_THREAD_STARTUP_MODE_ASYNC_LONG_TASKS = 3;

    @Deprecated
    public static final int UI_THREAD_STARTUP_MODE_ASYNC_SHORT_TASKS = 3;

    @Deprecated
    public static final int UI_THREAD_STARTUP_MODE_ASYNC_VERY_SHORT_TASKS = 3;
    public static final int UI_THREAD_STARTUP_MODE_ASYNC_WITHOUT_MULTI_PROCESS_STARTUP = 4;
    public static final int UI_THREAD_STARTUP_MODE_DEFAULT = -1;
    public static final int UI_THREAD_STARTUP_MODE_SYNC = 0;
    String mCacheDirectoryBasePath;
    String mDataDirectoryBasePath;
    String mDataDirectorySuffix;
    Boolean mPartitionedCookiesEnabled;
    int mUiThreadStartupMode = -1;
    private static final AtomicReference<HashMap<String, Object>> sProcessGlobalConfig = new AtomicReference<>();
    private static final Object sLock = new Object();
    private static boolean sApplyCalled = false;

    @Retention(RetentionPolicy.SOURCE)
    public @interface UiThreadStartupMode {
    }

    public ProcessGlobalConfig setDataDirectorySuffix(Context context, String str) {
        if (!WebViewFeatureInternal.STARTUP_FEATURE_SET_DATA_DIRECTORY_SUFFIX.isSupported(context)) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        if (str.equals("")) {
            throw new IllegalArgumentException("Suffix cannot be an empty string");
        }
        if (str.indexOf(File.separatorChar) >= 0) {
            throw new IllegalArgumentException("Suffix " + str + " contains a path separator");
        }
        this.mDataDirectorySuffix = str;
        return this;
    }

    public ProcessGlobalConfig setDirectoryBasePaths(Context context, File file, File file2) {
        if (!WebViewFeatureInternal.STARTUP_FEATURE_SET_DIRECTORY_BASE_PATH.isSupported(context)) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        if (!file.isAbsolute()) {
            throw new IllegalArgumentException("dataDirectoryBasePath must be a non-empty absolute path");
        }
        if (!file2.isAbsolute()) {
            throw new IllegalArgumentException("cacheDirectoryBasePath must be a non-empty absolute path");
        }
        this.mDataDirectoryBasePath = file.getAbsolutePath();
        this.mCacheDirectoryBasePath = file2.getAbsolutePath();
        return this;
    }

    public ProcessGlobalConfig setPartitionedCookiesEnabled(Context context, boolean z) {
        if (!WebViewFeatureInternal.STARTUP_FEATURE_CONFIGURE_PARTITIONED_COOKIES.isSupported(context)) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        this.mPartitionedCookiesEnabled = Boolean.valueOf(z);
        return this;
    }

    @Deprecated
    public ProcessGlobalConfig setUiThreadStartupMode(Context context, int i) {
        StartupApiFeature.NoFramework noFramework = WebViewFeatureInternal.STARTUP_FEATURE_SET_UI_THREAD_STARTUP_MODE;
        if (i == 4 || !noFramework.isSupported(context)) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        this.mUiThreadStartupMode = i;
        return this;
    }

    public ProcessGlobalConfig setUiThreadStartupModeV2(Context context, int i) {
        if (!WebViewFeatureInternal.STARTUP_FEATURE_SET_UI_THREAD_STARTUP_MODE_V2.isSupported(context)) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
        this.mUiThreadStartupMode = i;
        return this;
    }

    public static void apply(ProcessGlobalConfig processGlobalConfig) {
        synchronized (sLock) {
            if (sApplyCalled) {
                throw new IllegalStateException("ProcessGlobalConfig#apply was called more than once, which is an illegal operation. The configuration settings provided by ProcessGlobalConfig take effect only once, when WebView is first loaded into the current process. Every process should only ever create a single instance of ProcessGlobalConfig and apply it once, before any calls to android.webkit APIs, such as during early app startup.");
            }
            sApplyCalled = true;
        }
        HashMap hashMap = new HashMap();
        if (webViewCurrentlyLoaded()) {
            throw new IllegalStateException("WebView has already been loaded in the current process, so any attempt to apply the settings in ProcessGlobalConfig will have no effect. ProcessGlobalConfig#apply needs to be called before any calls to android.webkit APIs, such as during early app startup.");
        }
        if (processGlobalConfig.mDataDirectorySuffix != null) {
            if (WebViewFeatureInternal.STARTUP_FEATURE_SET_DATA_DIRECTORY_SUFFIX.isSupportedByFramework()) {
                ApiHelperForP.setDataDirectorySuffix(processGlobalConfig.mDataDirectorySuffix);
            } else {
                hashMap.put(ProcessGlobalConfigConstants.DATA_DIRECTORY_SUFFIX, processGlobalConfig.mDataDirectorySuffix);
            }
        }
        String str = processGlobalConfig.mDataDirectoryBasePath;
        if (str != null) {
            hashMap.put(ProcessGlobalConfigConstants.DATA_DIRECTORY_BASE_PATH, str);
        }
        String str2 = processGlobalConfig.mCacheDirectoryBasePath;
        if (str2 != null) {
            hashMap.put(ProcessGlobalConfigConstants.CACHE_DIRECTORY_BASE_PATH, str2);
        }
        Boolean bool = processGlobalConfig.mPartitionedCookiesEnabled;
        if (bool != null) {
            hashMap.put(ProcessGlobalConfigConstants.CONFIGURE_PARTITIONED_COOKIES, bool);
        }
        int i = processGlobalConfig.mUiThreadStartupMode;
        if (i != -1) {
            hashMap.put(ProcessGlobalConfigConstants.UI_THREAD_STARTUP_MODE, Integer.valueOf(i));
        }
        if (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(sProcessGlobalConfig, null, hashMap)) {
            throw new RuntimeException("Attempting to set ProcessGlobalConfig#sProcessGlobalConfig when it was already set");
        }
    }

    private static boolean webViewCurrentlyLoaded() {
        try {
            Field declaredField = Class.forName("android.webkit.WebViewFactory").getDeclaredField("sProviderInstance");
            declaredField.setAccessible(true);
            return declaredField.get(null) != null;
        } catch (Exception unused) {
            return false;
        }
    }
}
