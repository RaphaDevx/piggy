package androidx.webkit;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.emoji2.text.ConcurrencyHelpers$$ExternalSyntheticLambda0;
import androidx.webkit.WebViewCompat;
import androidx.webkit.internal.ApiFeature;
import androidx.webkit.internal.ApiHelperForO;
import androidx.webkit.internal.ApiHelperForOMR1;
import androidx.webkit.internal.ApiHelperForP;
import androidx.webkit.internal.ApiHelperForQ;
import androidx.webkit.internal.WebMessageAdapter;
import androidx.webkit.internal.WebMessagePortImpl;
import androidx.webkit.internal.WebViewFeatureInternal;
import androidx.webkit.internal.WebViewGlueCommunicator;
import androidx.webkit.internal.WebViewProviderAdapter;
import androidx.webkit.internal.WebViewProviderFactory;
import androidx.webkit.internal.WebViewRenderProcessClientFrameworkAdapter;
import androidx.webkit.internal.WebViewRenderProcessImpl;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface;

/* loaded from: classes3.dex */
public class WebViewCompat {
    private static final Uri WILDCARD_URI = Uri.parse("*");
    private static final Uri EMPTY_URI = Uri.parse("");
    private static final WeakHashMap<WebView, WebViewProviderAdapter> sProviderAdapterCache = new WeakHashMap<>();

    @Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
    @Retention(RetentionPolicy.CLASS)
    public @interface ExperimentalAsyncStartUp {
    }

    @Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
    @Retention(RetentionPolicy.CLASS)
    public @interface ExperimentalSaveState {
    }

    public interface VisualStateCallback {
        void onComplete(long j);
    }

    public interface WebMessageListener {
        void onPostMessage(WebView webView, WebMessageCompat webMessageCompat, Uri uri, boolean z, JavaScriptReplyProxy javaScriptReplyProxy);
    }

    public interface WebViewStartUpCallback {
        void onSuccess(WebViewStartUpResult webViewStartUpResult);
    }

    private WebViewCompat() {
    }

    public static void postVisualStateCallback(WebView webView, long j, final VisualStateCallback visualStateCallback) {
        webView.postVisualStateCallback(j, new WebView.VisualStateCallback() { // from class: androidx.webkit.WebViewCompat.1
            @Override // android.webkit.WebView.VisualStateCallback
            public void onComplete(long j2) {
                VisualStateCallback.this.onComplete(j2);
            }
        });
    }

    @Deprecated
    public static void startSafeBrowsing(Context context, ValueCallback<Boolean> valueCallback) {
        ApiFeature.O_MR1 o_mr1 = WebViewFeatureInternal.START_SAFE_BROWSING;
        if (o_mr1.isSupportedByFramework()) {
            ApiHelperForOMR1.startSafeBrowsing(context, valueCallback);
        } else {
            if (o_mr1.isSupportedByWebView()) {
                getFactory().getStatics().initSafeBrowsing(context, valueCallback);
                return;
            }
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    public static void setSafeBrowsingAllowlist(Set<String> set, ValueCallback<Boolean> valueCallback) {
        ApiFeature.O_MR1 o_mr1 = WebViewFeatureInternal.SAFE_BROWSING_ALLOWLIST_PREFERRED_TO_PREFERRED;
        ApiFeature.O_MR1 o_mr12 = WebViewFeatureInternal.SAFE_BROWSING_ALLOWLIST_PREFERRED_TO_DEPRECATED;
        if (o_mr1.isSupportedByWebView()) {
            getFactory().getStatics().setSafeBrowsingAllowlist(set, valueCallback);
            return;
        }
        ArrayList arrayList = new ArrayList(set);
        if (o_mr12.isSupportedByFramework()) {
            ApiHelperForOMR1.setSafeBrowsingWhitelist(arrayList, valueCallback);
        } else {
            if (o_mr12.isSupportedByWebView()) {
                getFactory().getStatics().setSafeBrowsingWhitelist(arrayList, valueCallback);
                return;
            }
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    @Deprecated
    public static void setSafeBrowsingWhitelist(List<String> list, ValueCallback<Boolean> valueCallback) {
        setSafeBrowsingAllowlist(new HashSet(list), valueCallback);
    }

    public static Uri getSafeBrowsingPrivacyPolicyUrl() {
        ApiFeature.O_MR1 o_mr1 = WebViewFeatureInternal.SAFE_BROWSING_PRIVACY_POLICY_URL;
        if (o_mr1.isSupportedByFramework()) {
            return ApiHelperForOMR1.getSafeBrowsingPrivacyPolicyUrl();
        }
        if (o_mr1.isSupportedByWebView()) {
            return getFactory().getStatics().getSafeBrowsingPrivacyPolicyUrl();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static PackageInfo getCurrentWebViewPackage(Context context) {
        PackageInfo currentLoadedWebViewPackage = getCurrentLoadedWebViewPackage();
        return currentLoadedWebViewPackage != null ? currentLoadedWebViewPackage : getNotYetLoadedWebViewPackageInfo(context);
    }

    public static PackageInfo getCurrentLoadedWebViewPackage() {
        return ApiHelperForO.getCurrentWebViewPackage();
    }

    private static PackageInfo getLoadedWebViewPackageInfo() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return (PackageInfo) Class.forName("android.webkit.WebViewFactory").getMethod("getLoadedPackageInfo", null).invoke(null, null);
    }

    private static PackageInfo getNotYetLoadedWebViewPackageInfo(Context context) {
        try {
            String str = (String) Class.forName("android.webkit.WebViewUpdateService").getMethod("getCurrentWebViewPackageName", null).invoke(null, null);
            if (str == null) {
                return null;
            }
            return context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException | ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }

    private static WebViewProviderAdapter getProvider(WebView webView) {
        if (WebViewFeatureInternal.PROVIDER_WEAKLY_REF_WEBVIEW.isSupportedByWebView()) {
            WeakHashMap<WebView, WebViewProviderAdapter> weakHashMap = sProviderAdapterCache;
            WebViewProviderAdapter webViewProviderAdapter = weakHashMap.get(webView);
            if (webViewProviderAdapter != null) {
                return webViewProviderAdapter;
            }
            WebViewProviderAdapter webViewProviderAdapter2 = new WebViewProviderAdapter(createProvider(webView));
            weakHashMap.put(webView, webViewProviderAdapter2);
            return webViewProviderAdapter2;
        }
        return new WebViewProviderAdapter(createProvider(webView));
    }

    public static WebMessagePortCompat[] createWebMessageChannel(WebView webView) {
        return WebMessagePortImpl.portsToCompat(webView.createWebMessageChannel());
    }

    public static void postWebMessage(WebView webView, WebMessageCompat webMessageCompat, Uri uri) {
        if (WILDCARD_URI.equals(uri)) {
            uri = EMPTY_URI;
        }
        ApiFeature.M m = WebViewFeatureInternal.POST_WEB_MESSAGE;
        if (webMessageCompat.getType() == 0) {
            webView.postWebMessage(WebMessagePortImpl.compatToFrameworkMessage(webMessageCompat), uri);
        } else {
            if (m.isSupportedByWebView() && WebMessageAdapter.isMessagePayloadTypeSupportedByWebView(webMessageCompat.getType())) {
                checkThread(webView);
                getProvider(webView).postWebMessage(webMessageCompat, uri);
                return;
            }
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    public static void addWebMessageListener(WebView webView, String str, Set<String> set, WebMessageListener webMessageListener) {
        if (WebViewFeatureInternal.WEB_MESSAGE_LISTENER.isSupportedByWebView()) {
            getProvider(webView).addWebMessageListener(str, (String[]) set.toArray(new String[0]), webMessageListener);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static void removeWebMessageListener(WebView webView, String str) {
        if (WebViewFeatureInternal.WEB_MESSAGE_LISTENER.isSupportedByWebView()) {
            getProvider(webView).removeWebMessageListener(str);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static ScriptHandler addDocumentStartJavaScript(WebView webView, String str, Set<String> set) {
        if (WebViewFeatureInternal.DOCUMENT_START_SCRIPT.isSupportedByWebView()) {
            return getProvider(webView).addDocumentStartJavaScript(str, (String[]) set.toArray(new String[0]));
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static WebViewClient getWebViewClient(WebView webView) {
        ApiFeature.O o = WebViewFeatureInternal.GET_WEB_VIEW_CLIENT;
        if (o.isSupportedByFramework()) {
            return ApiHelperForO.getWebViewClient(webView);
        }
        if (o.isSupportedByWebView()) {
            checkThread(webView);
            return getProvider(webView).getWebViewClient();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static WebChromeClient getWebChromeClient(WebView webView) {
        ApiFeature.O o = WebViewFeatureInternal.GET_WEB_CHROME_CLIENT;
        if (o.isSupportedByFramework()) {
            return ApiHelperForO.getWebChromeClient(webView);
        }
        if (o.isSupportedByWebView()) {
            checkThread(webView);
            return getProvider(webView).getWebChromeClient();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static WebViewRenderProcess getWebViewRenderProcess(WebView webView) {
        ApiFeature.Q q = WebViewFeatureInternal.GET_WEB_VIEW_RENDERER;
        if (q.isSupportedByFramework()) {
            android.webkit.WebViewRenderProcess webViewRenderProcess = ApiHelperForQ.getWebViewRenderProcess(webView);
            if (webViewRenderProcess != null) {
                return WebViewRenderProcessImpl.forFrameworkObject(webViewRenderProcess);
            }
            return null;
        }
        if (q.isSupportedByWebView()) {
            checkThread(webView);
            return getProvider(webView).getWebViewRenderProcess();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static void setWebViewRenderProcessClient(WebView webView, Executor executor, WebViewRenderProcessClient webViewRenderProcessClient) {
        ApiFeature.Q q = WebViewFeatureInternal.WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE;
        if (q.isSupportedByFramework()) {
            ApiHelperForQ.setWebViewRenderProcessClient(webView, executor, webViewRenderProcessClient);
        } else {
            if (q.isSupportedByWebView()) {
                checkThread(webView);
                getProvider(webView).setWebViewRenderProcessClient(executor, webViewRenderProcessClient);
                return;
            }
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    public static void setWebViewRenderProcessClient(WebView webView, WebViewRenderProcessClient webViewRenderProcessClient) {
        ApiFeature.Q q = WebViewFeatureInternal.WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE;
        if (q.isSupportedByFramework()) {
            ApiHelperForQ.setWebViewRenderProcessClient(webView, webViewRenderProcessClient);
        } else {
            if (q.isSupportedByWebView()) {
                checkThread(webView);
                getProvider(webView).setWebViewRenderProcessClient(null, webViewRenderProcessClient);
                return;
            }
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    public static WebViewRenderProcessClient getWebViewRenderProcessClient(WebView webView) {
        ApiFeature.Q q = WebViewFeatureInternal.WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE;
        if (q.isSupportedByFramework()) {
            android.webkit.WebViewRenderProcessClient webViewRenderProcessClient = ApiHelperForQ.getWebViewRenderProcessClient(webView);
            if (webViewRenderProcessClient == null || !(webViewRenderProcessClient instanceof WebViewRenderProcessClientFrameworkAdapter)) {
                return null;
            }
            return ((WebViewRenderProcessClientFrameworkAdapter) webViewRenderProcessClient).getFrameworkRenderProcessClient();
        }
        if (q.isSupportedByWebView()) {
            checkThread(webView);
            return getProvider(webView).getWebViewRenderProcessClient();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static boolean isMultiProcessEnabled() {
        if (WebViewFeatureInternal.MULTI_PROCESS.isSupportedByWebView()) {
            return getFactory().getStatics().isMultiProcessEnabled();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static String getVariationsHeader() {
        if (WebViewFeatureInternal.GET_VARIATIONS_HEADER.isSupportedByWebView()) {
            return getFactory().getStatics().getVariationsHeader();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static void setProfile(WebView webView, String str) {
        if (WebViewFeatureInternal.MULTI_PROFILE.isSupportedByWebView()) {
            getProvider(webView).setProfileWithName(str);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static Profile getProfile(WebView webView) {
        if (WebViewFeatureInternal.MULTI_PROFILE.isSupportedByWebView()) {
            return getProvider(webView).getProfile();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static boolean isAudioMuted(WebView webView) {
        if (WebViewFeatureInternal.MUTE_AUDIO.isSupportedByWebView()) {
            return getProvider(webView).isAudioMuted();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static void setAudioMuted(WebView webView, boolean z) {
        if (WebViewFeatureInternal.MUTE_AUDIO.isSupportedByWebView()) {
            getProvider(webView).setAudioMuted(z);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static void startUpWebView(final Context context, final WebViewStartUpConfig webViewStartUpConfig, final WebViewStartUpCallback webViewStartUpCallback) {
        webViewStartUpConfig.getBackgroundExecutor().execute(new Runnable() { // from class: androidx.webkit.WebViewCompat$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                WebViewCompat.lambda$startUpWebView$3(WebViewStartUpConfig.this, webViewStartUpCallback, context);
            }
        });
    }

    static /* synthetic */ void lambda$startUpWebView$3(WebViewStartUpConfig webViewStartUpConfig, final WebViewStartUpCallback webViewStartUpCallback, Context context) {
        WebViewGlueCommunicator.getWebViewClassLoader();
        if (WebViewFeatureInternal.ASYNC_WEBVIEW_STARTUP.isSupportedByWebView()) {
            getFactory().startUpWebView(webViewStartUpConfig, new WebViewStartUpCallback() { // from class: androidx.webkit.WebViewCompat$$ExternalSyntheticLambda2
                @Override // androidx.webkit.WebViewCompat.WebViewStartUpCallback
                public final void onSuccess(WebViewStartUpResult webViewStartUpResult) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: androidx.webkit.WebViewCompat$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            WebViewCompat.WebViewStartUpCallback.this.onSuccess(webViewStartUpResult);
                        }
                    });
                }
            });
            return;
        }
        if (webViewStartUpConfig.shouldRunUiThreadStartUpTasks()) {
            WebSettings.getDefaultUserAgent(context.getApplicationContext());
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: androidx.webkit.WebViewCompat$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                WebViewCompat.WebViewStartUpCallback.this.onSuccess(new WebViewCompat.NullReturningWebViewStartUpResult());
            }
        });
    }

    public static void setDefaultTrafficStatsTag(int i) {
        if (WebViewFeatureInternal.DEFAULT_TRAFFICSTATS_TAGGING.isSupportedByWebView()) {
            getFactory().getStatics().setDefaultTrafficStatsTag(i);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class NullReturningWebViewStartUpResult implements WebViewStartUpResult {
        @Override // androidx.webkit.WebViewStartUpResult
        public Long getMaxTimePerTaskInUiThreadMillis() {
            return null;
        }

        @Override // androidx.webkit.WebViewStartUpResult
        public List<StartUpLocation> getNonUiThreadBlockingStartUpLocations() {
            return null;
        }

        @Override // androidx.webkit.WebViewStartUpResult
        public Long getTotalTimeInUiThreadMillis() {
            return null;
        }

        @Override // androidx.webkit.WebViewStartUpResult
        public List<StartUpLocation> getUiThreadBlockingStartUpLocations() {
            return null;
        }

        private NullReturningWebViewStartUpResult() {
        }
    }

    public static void prerenderUrlAsync(WebView webView, String str, CancellationSignal cancellationSignal, Executor executor, PrerenderOperationCallback prerenderOperationCallback) {
        if (WebViewFeatureInternal.PRERENDER_WITH_URL.isSupportedByWebView()) {
            getProvider(webView).prerenderUrlAsync(str, cancellationSignal, executor, prerenderOperationCallback);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static void prerenderUrlAsync(WebView webView, String str, CancellationSignal cancellationSignal, Executor executor, SpeculativeLoadingParameters speculativeLoadingParameters, PrerenderOperationCallback prerenderOperationCallback) {
        if (WebViewFeatureInternal.PRERENDER_WITH_URL.isSupportedByWebView()) {
            getProvider(webView).prerenderUrlAsync(str, cancellationSignal, executor, speculativeLoadingParameters, prerenderOperationCallback);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static void saveState(WebView webView, Bundle bundle, int i, boolean z) {
        if (WebViewFeatureInternal.SAVE_STATE.isSupportedByWebView()) {
            getProvider(webView).saveState(bundle, i, z);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    @Deprecated
    public static void setWebNavigationClient(WebView webView, WebNavigationClient webNavigationClient) {
        if (WebViewFeatureInternal.NAVIGATION_CALLBACK_BASIC.isSupportedByWebView()) {
            getProvider(webView).setWebNavigationClient(webNavigationClient);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    @Deprecated
    public static WebNavigationClient getWebNavigationClient(WebView webView) {
        if (WebViewFeatureInternal.NAVIGATION_CALLBACK_BASIC.isSupportedByWebView()) {
            return getProvider(webView).getWebNavigationClient();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static void addNavigationListener(WebView webView, Executor executor, NavigationListener navigationListener) {
        if (WebViewFeatureInternal.NAVIGATION_LISTENER_V1.isSupportedByWebView()) {
            getProvider(webView).addNavigationListener(executor, navigationListener);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static void addNavigationListener(WebView webView, NavigationListener navigationListener) {
        addNavigationListener(webView, new ConcurrencyHelpers$$ExternalSyntheticLambda0(new Handler(Looper.getMainLooper())), navigationListener);
    }

    public static void removeNavigationListener(WebView webView, NavigationListener navigationListener) {
        if (WebViewFeatureInternal.NAVIGATION_LISTENER_V1.isSupportedByWebView()) {
            getProvider(webView).removeNavigationListener(navigationListener);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    private static WebViewProviderFactory getFactory() {
        return WebViewGlueCommunicator.getFactory();
    }

    private static WebViewProviderBoundaryInterface createProvider(WebView webView) {
        return getFactory().createWebView(webView);
    }

    private static void checkThread(WebView webView) {
        Looper webViewLooper = ApiHelperForP.getWebViewLooper(webView);
        if (webViewLooper == Looper.myLooper()) {
            return;
        }
        throw new RuntimeException("A WebView method was called on thread '" + Thread.currentThread().getName() + "'. All WebView methods must be called on the same thread. (Expected Looper " + webViewLooper + " called on " + Looper.myLooper() + ", FYI main Looper is " + Looper.getMainLooper() + ")");
    }

    static WeakHashMap<WebView, WebViewProviderAdapter> getProviderAdapterCacheForTesting() {
        return sProviderAdapterCache;
    }
}
