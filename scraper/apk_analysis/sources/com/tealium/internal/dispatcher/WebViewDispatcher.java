package com.tealium.internal.dispatcher;

import android.net.Uri;
import android.net.http.SslError;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tealium.internal.NetworkRequestBuilder;
import com.tealium.internal.QueryParameterProvider;
import com.tealium.internal.data.Dispatch;
import com.tealium.internal.data.PublishSettings;
import com.tealium.internal.j.m;
import com.tealium.internal.j.w;
import com.tealium.internal.j.x;
import com.tealium.internal.j.y;
import com.tealium.internal.listeners.BulkDispatchSendListener;
import com.tealium.internal.listeners.DispatchReadyListener;
import com.tealium.internal.listeners.DispatchSendListener;
import com.tealium.internal.listeners.EvalJavaScriptListener;
import com.tealium.internal.listeners.PublishSettingsUpdateListener;
import com.tealium.internal.listeners.WebViewCrashedListener;
import com.tealium.internal.listeners.WebViewCreatedListener;
import com.tealium.internal.listeners.WebViewLoadedListener;
import com.tealium.library.BuildConfig;
import com.tealium.library.DataSources;
import com.tealium.library.R;
import com.tealium.library.Tealium;
import com.tealium.remotecommands.RemoteCommandRequest;
import io.sentry.protocol.SentryThread;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.core.HttpHeaders;
import org.apache.commons.lang3.time.TimeZones;

/* loaded from: classes2.dex */
public final class WebViewDispatcher implements BulkDispatchSendListener, DispatchReadyListener, DispatchSendListener, PublishSettingsUpdateListener, EvalJavaScriptListener, WebViewLoadedListener, WebViewCreatedListener, WebViewCrashedListener {
    private static final long MINUTE_OF_MS = 60000;
    private static final int MPS_TIMEOUT_DELAY = 4000;
    private static final int PAGE_STATUS_LOADED_ERROR = 3;
    private static final int PAGE_STATUS_LOADED_SUCCESS = 2;
    private static final int PAGE_STATUS_LOADING = 1;
    private static final int PAGE_STATUS_UNLOADED = 0;
    private static final long PARAMS_PROVIDER_TIMEOUT = 5000;
    private static final String UTAG_TAGBRIDGE = "remote_api";
    private final Tealium.Config mConfig;
    private final com.tealium.internal.a mConnectivityHelper;
    private final SimpleDateFormat mIfModifiedFormat;
    private boolean mIsWifiOnlySending;
    private volatile long mLastPublishURLLoad;
    private final com.tealium.internal.c mLogger;
    private final com.tealium.internal.d mMessageRouter;
    private final AtomicInteger mPageStatus;
    private final com.tealium.internal.k.c mTagBridge;
    private long mTimeout;
    private String mTraceId;
    private final String mUrl;
    private volatile WebView mWebView;
    private final Map<String, String[]> mQueryParams = new ConcurrentHashMap();
    private volatile CountDownLatch mCountDownLatch = null;

    class a implements Runnable {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            int andSet = WebViewDispatcher.this.mPageStatus.getAndSet(1);
            if (andSet != 2) {
                WebViewDispatcher.this.mPageStatus.set(andSet);
                return;
            }
            try {
                String str = this.a;
                if (str == null) {
                    str = "";
                }
                WebViewDispatcher.this.mWebView.loadUrl(String.format(Locale.ROOT, "javascript:document.cookie = 'trace_id=%s; expires=0; path=/';", str));
                WebViewDispatcher.this.mWebView.reload();
            } catch (Throwable th) {
                WebViewDispatcher.this.mLogger.a(th);
            }
        }
    }

    class b implements Runnable {

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                WebViewDispatcher.this.fetchUrlParameters();
                WebViewDispatcher.this.attemptLoadPublishURL();
            }
        }

        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (WebViewDispatcher.this.mWebView != null) {
                    return;
                }
                WebViewDispatcher.this.mWebView = new WebView(WebViewDispatcher.this.mConfig.getApplication().getApplicationContext());
                String absolutePath = WebViewDispatcher.this.mConfig.getTealiumDir().getAbsolutePath();
                WebSettings settings = WebViewDispatcher.this.mWebView.getSettings();
                settings.setAppCacheEnabled(true);
                settings.setDatabaseEnabled(true);
                settings.setJavaScriptEnabled(true);
                settings.setDomStorageEnabled(true);
                settings.setAppCachePath(absolutePath);
                WebViewDispatcher.this.mWebView.setLayerType(1, null);
                WebViewDispatcher.this.mWebView.setWebChromeClient(WebViewDispatcher.createWebChromeClient(WebViewDispatcher.this.mLogger));
                WebViewDispatcher.this.mWebView.setWebViewClient(WebViewDispatcher.this.createWebViewClient());
                WebViewDispatcher.this.mMessageRouter.a(new x(WebViewDispatcher.this.mWebView));
                WebViewDispatcher.this.mMessageRouter.c(new a());
            } catch (Throwable th) {
                WebViewDispatcher.this.mLogger.a(R.string.webview_dispatcher_error_creating_webview, th, new Object[0]);
            }
        }
    }

    class c extends TimerTask {
        c() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            WebViewDispatcher.this.mLogger.a(R.string.webview_dispatcher_debug_mps_update, new Object[0]);
            WebViewDispatcher.this.attemptCheckForUpdatedPublish();
        }
    }

    class d implements Runnable {
        final /* synthetic */ String a;

        d(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                WebViewDispatcher.this.mWebView.loadUrl(this.a);
            } catch (Throwable unused) {
                WebViewDispatcher.this.mLogger.b(R.string.webview_dispatcher_error_loading_url, this.a, WebViewDispatcher.this.mWebView);
            }
        }
    }

    class e implements QueryParameterProvider.QueryParameterUpdatedNotifier {
        e() {
        }

        @Override // com.tealium.internal.QueryParameterProvider.QueryParameterUpdatedNotifier
        public void onNotifyUpdatedParameters(Map<String, String[]> map) {
            if (map != null) {
                WebViewDispatcher.this.mQueryParams.putAll(map);
            }
            WebViewDispatcher.this.mCountDownLatch.countDown();
        }
    }

    class f extends WebViewClient {

        class a implements Runnable {
            final /* synthetic */ WebView a;

            a(f fVar, WebView webView) {
                this.a = webView;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.a.loadUrl("javascript:(function(){\n    var payload = {};\n    try {\n        var ts = new RegExp(\"ut[0-9]+\\.[0-9]+\\.[0-9]{12}\").exec(document.childNodes[0].textContent)[0];\n        ts = ts.substring(ts.length - 12, ts.length);\n        var y = ts.substring(0, 4);\n        var mo = ts.substring(4, 6);\n        var d = ts.substring(6, 8);\n        var h = ts.substring(8, 10);\n        var mi = ts.substring(10, 12);\n        var t = Date.from(y+'/'+mo+'/'+d+' '+h+':'+mi+' UTC');\n        if(!isNaN(t)){\n            payload.published = t;\n        }\n    } catch(e) {    }\n    var f=document.cookie.indexOf('trace_id=');\n    if(f>=0){\n        payload.trace_id = document.cookie.substring(f+9).split(';')[0];\n    }\n    window.open('tealium://_config?request=' + encodeURIComponent(JSON.stringify({\n        payload : payload\n    })), '_self');\n})()");
            }
        }

        f() {
        }

        @Override // android.webkit.WebViewClient
        public void onLoadResource(WebView webView, String str) {
            super.onLoadResource(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            WebViewDispatcher.this.mLastPublishURLLoad = SystemClock.elapsedRealtime();
            if (3 == WebViewDispatcher.this.mPageStatus.getAndSet(2)) {
                WebViewDispatcher.this.mPageStatus.set(3);
                WebViewDispatcher.this.mLogger.b(R.string.webview_dispatcher_error_loading_url, str, webView);
            } else {
                WebViewDispatcher.this.mMessageRouter.b(new a(this, webView));
                WebViewDispatcher.this.mMessageRouter.a(new y(WebViewDispatcher.this.mWebView, true));
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            onReceivedError(webView, webResourceError.getErrorCode(), webResourceError.getDescription().toString(), webResourceRequest.getUrl().toString());
            super.onReceivedError(webView, webResourceRequest, webResourceError);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            WebViewDispatcher.this.mLogger.b(R.string.webview_dispatcher_rcvd_http_error, webResourceRequest.getUrl(), Integer.valueOf(webResourceResponse.getStatusCode()), webResourceResponse.getReasonPhrase());
            super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            WebViewDispatcher.this.mPageStatus.set(3);
            WebViewDispatcher.this.mLogger.b(R.string.webview_dispatcher_rcvd_ssl_error, webView, webView.getUrl(), sslError);
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
        }

        @Override // android.webkit.WebViewClient
        public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
            WebViewDispatcher.this.mLogger.b(R.string.webview_dispatcher_error_render_process_gone, renderProcessGoneDetail.didCrash() ? SentryThread.JsonKeys.CRASHED : "killed by system");
            WebViewDispatcher.this.mMessageRouter.a(new w(webView));
            WebViewDispatcher.this.mMessageRouter.b(WebViewDispatcher.this.createWebViewRunnable());
            return true;
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            if (str.toLowerCase().contains("/favicon.ico")) {
                return new WebResourceResponse("image/png", null, null);
            }
            return null;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            return shouldOverrideUrlLoading(webView, webResourceRequest.getUrl().toString());
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!RemoteCommandRequest.isRequest(str)) {
                WebViewDispatcher.this.mLogger.e(R.string.webview_dispatcher_warn_override_url_loading, str);
                return true;
            }
            try {
                if (!WebViewDispatcher.this.mConfig.isRemoteCommandEnabled() && !com.tealium.internal.k.c.b(str)) {
                    WebViewDispatcher.this.mLogger.a(R.string.webview_dispatcher_error_remote_command_not_allowed, null, str);
                    return true;
                }
                WebViewDispatcher.this.mTagBridge.a(str);
                return true;
            } catch (Throwable th) {
                WebViewDispatcher.this.mLogger.a(th);
                return true;
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (str2.toLowerCase().contains("favicon.ico")) {
                WebViewDispatcher.this.mLogger.a(R.string.webview_dispatcher_rcvd_favicon_error, new Object[0]);
                return;
            }
            super.onReceivedError(webView, i, str, str2);
            if (3 == WebViewDispatcher.this.mPageStatus.getAndSet(3)) {
                return;
            }
            WebViewDispatcher.this.mLastPublishURLLoad = SystemClock.uptimeMillis();
            WebViewDispatcher.this.mMessageRouter.a(new y(WebViewDispatcher.this.mWebView, false));
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            if (webResourceRequest.isForMainFrame()) {
                return null;
            }
            return shouldInterceptRequest(webView, webResourceRequest.getUrl().toString());
        }
    }

    class g implements NetworkRequestBuilder.HttpResponseListener {
        final /* synthetic */ int a;

        g(int i) {
            this.a = i;
        }

        @Override // com.tealium.internal.NetworkRequestBuilder.HttpResponseListener
        public void onHttpError(String str, Throwable th) {
            WebViewDispatcher.this.mPageStatus.set(this.a);
        }

        @Override // com.tealium.internal.NetworkRequestBuilder.HttpResponseListener
        public void onHttpResponse(String str, String str2, int i, Map<String, List<String>> map, byte[] bArr) {
            WebViewDispatcher.this.mPageStatus.set(this.a);
            if (i != 200) {
                return;
            }
            WebViewDispatcher.this.attemptLoadPublishURL();
        }
    }

    class h extends WebChromeClient {
        final /* synthetic */ com.tealium.internal.c a;

        h(com.tealium.internal.c cVar) {
            this.a = cVar;
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            String format = String.format(Locale.ROOT, "Src: %s; Line: %d; %s", consoleMessage.sourceId(), Integer.valueOf(consoleMessage.lineNumber()), consoleMessage.message());
            int i = i.a[consoleMessage.messageLevel().ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                if (this.a.d()) {
                                    Log.v(BuildConfig.WEBVIEW_TAG, format);
                                }
                            } else if (this.a.e()) {
                                Log.w(BuildConfig.WEBVIEW_TAG, format);
                            }
                        } else if (this.a.d()) {
                            Log.v(BuildConfig.WEBVIEW_TAG, format);
                        }
                    } else if (this.a.c()) {
                        Log.i(BuildConfig.WEBVIEW_TAG, format);
                    }
                } else if (this.a.b()) {
                    Log.e(BuildConfig.WEBVIEW_TAG, format);
                }
            } else if (this.a.a()) {
                Log.d(BuildConfig.WEBVIEW_TAG, format);
            }
            return true;
        }
    }

    static /* synthetic */ class i {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ConsoleMessage.MessageLevel.values().length];
            a = iArr;
            try {
                iArr[ConsoleMessage.MessageLevel.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ConsoleMessage.MessageLevel.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ConsoleMessage.MessageLevel.LOG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ConsoleMessage.MessageLevel.TIP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[ConsoleMessage.MessageLevel.WARNING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public WebViewDispatcher(Tealium.Config config, com.tealium.internal.d dVar, com.tealium.internal.k.c cVar) {
        this.mConfig = config;
        this.mUrl = config.getOverrideTagManagementUrl() == null ? config.getDefaultTagManagementUrl() : config.getOverrideTagManagementUrl();
        this.mConnectivityHelper = com.tealium.internal.a.a(config.getApplication());
        this.mPageStatus = new AtomicInteger(0);
        this.mLogger = config.getLogger();
        this.mMessageRouter = dVar;
        this.mTagBridge = cVar;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.ROOT);
        this.mIfModifiedFormat = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TimeZones.GMT_ID));
        this.mLastPublishURLLoad = Long.MIN_VALUE;
        onPublishSettingsUpdate(config.getPublishSettings());
        dVar.b(createWebViewRunnable());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void attemptCheckForUpdatedPublish() {
        int andSet;
        if ((!this.mIsWifiOnlySending || this.mConnectivityHelper.b()) && this.mConnectivityHelper.a() && 1 != (andSet = this.mPageStatus.getAndSet(1))) {
            this.mMessageRouter.a(NetworkRequestBuilder.createHeadRequest(this.mUrl).addHeader(HttpHeaders.ACCEPT_ENCODING, "*").addHeader(HttpHeaders.IF_MODIFIED_SINCE, this.mIfModifiedFormat.format(new Date(this.mLastPublishURLLoad))).setListener(createHeadRequestResponseListener(andSet)).createRunnable());
        }
    }

    private void attemptLoadPublishAfterDelay() {
        if (isTimedOut()) {
            new Timer().schedule(new c(), 4000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean attemptLoadPublishURL() {
        boolean z = this.mIsWifiOnlySending && !this.mConnectivityHelper.b();
        if (this.mWebView == null || z || !this.mConnectivityHelper.a() || 1 == this.mPageStatus.getAndSet(1)) {
            return false;
        }
        if (this.mCountDownLatch != null) {
            try {
                this.mCountDownLatch.await(5000L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException unused) {
                this.mCountDownLatch.countDown();
            }
        }
        String decorateUrlParams = decorateUrlParams(this.mUrl, this.mQueryParams);
        String str = this.mUrl.contains("?") ? "&" : "?";
        String str2 = decorateUrlParams + str + ("timestamp_unix=" + (System.currentTimeMillis() / 1000));
        if (com.tealium.internal.g.c()) {
            try {
                this.mWebView.loadUrl(str2);
            } catch (Throwable unused2) {
                this.mLogger.b(R.string.webview_dispatcher_error_loading_url, str2, this.mWebView);
            }
        } else {
            this.mMessageRouter.b(new d(str2));
        }
        return true;
    }

    private void callTagBridgeTags(Dispatch dispatch) {
        this.mMessageRouter.a(new m(String.format(Locale.ROOT, "utag.track(\"%s\", %s)", UTAG_TAGBRIDGE, dispatch.toJsonString())));
    }

    private NetworkRequestBuilder.HttpResponseListener createHeadRequestResponseListener(int i2) {
        return new g(i2);
    }

    private Runnable createTraceUpdateRunnable(String str) {
        return new a(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static WebChromeClient createWebChromeClient(com.tealium.internal.c cVar) {
        return new h(cVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WebViewClient createWebViewClient() {
        return new f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Runnable createWebViewRunnable() {
        return new b();
    }

    private String decorateUrlParams(String str, Map<String, String[]> map) {
        if (map == null || map.isEmpty()) {
            return str;
        }
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            for (String str2 : entry.getValue()) {
                buildUpon.appendQueryParameter(entry.getKey(), str2);
            }
        }
        return buildUpon.build().toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fetchUrlParameters() {
        List<QueryParameterProvider> queryParameterProviders = this.mConfig.getQueryParameterProviders();
        if (queryParameterProviders.isEmpty()) {
            return;
        }
        this.mCountDownLatch = new CountDownLatch(queryParameterProviders.size());
        Iterator<QueryParameterProvider> it = queryParameterProviders.iterator();
        while (it.hasNext()) {
            it.next().provideParameters(new e());
        }
    }

    private boolean isTimedOut() {
        return SystemClock.elapsedRealtime() - this.mLastPublishURLLoad >= this.mTimeout;
    }

    public com.tealium.internal.k.c getTagBridge() {
        return this.mTagBridge;
    }

    @Override // com.tealium.internal.listeners.BulkDispatchSendListener
    public void onBulkDispatchSend(List<Dispatch> list) {
        Iterator<Dispatch> it = list.iterator();
        while (it.hasNext()) {
            onDispatchSend(it.next());
        }
    }

    @Override // com.tealium.internal.listeners.DispatchReadyListener
    public void onDispatchReady(Dispatch dispatch) {
        int i2 = this.mPageStatus.get();
        if (i2 == 0) {
            this.mMessageRouter.b(createWebViewRunnable());
        } else {
            if (i2 == 1) {
                return;
            }
            if (i2 == 2) {
                callTagBridgeTags(dispatch);
                return;
            } else if (i2 == 3 && !isTimedOut()) {
                return;
            }
        }
        attemptLoadPublishURL();
    }

    @Override // com.tealium.internal.listeners.DispatchSendListener
    public void onDispatchSend(Dispatch dispatch) {
        if (this.mPageStatus.get() != 2) {
            return;
        }
        String string = dispatch.getString(DataSources.Key.CALL_TYPE);
        Locale locale = Locale.ROOT;
        if (string == null) {
            string = "link";
        }
        this.mMessageRouter.a(new m(String.format(locale, "utag.track(\"%s\", %s)", string, dispatch.toJsonString())));
        String string2 = dispatch.getString("event");
        if (string2 != null && string2.equals("kill_visitor_session")) {
            this.mMessageRouter.b(createTraceUpdateRunnable(null), 100L);
        }
        CookieManager cookieManager = CookieManager.getInstance();
        if (cookieManager != null) {
            cookieManager.flush();
        }
        attemptLoadPublishAfterDelay();
    }

    @Override // com.tealium.internal.listeners.EvalJavaScriptListener
    public void onEvalJavaScript(String str) {
        if (this.mPageStatus.get() != 2) {
            return;
        }
        try {
            if (str.startsWith("javascript:")) {
                str = str.substring(11);
            }
            this.mWebView.evaluateJavascript(str, null);
        } catch (Throwable th) {
            this.mLogger.b(th);
        }
    }

    @Override // com.tealium.internal.listeners.PublishSettingsUpdateListener
    public void onPublishSettingsUpdate(PublishSettings publishSettings) {
        this.mIsWifiOnlySending = publishSettings.isWifiOnlySending();
        this.mTimeout = ((long) publishSettings.getMinutesBetweenRefresh()) * 60000;
    }

    @Override // com.tealium.internal.listeners.WebViewCrashedListener
    public void onWebViewCrashed(WebView webView) {
        this.mPageStatus.set(0);
        this.mWebView.destroy();
        this.mWebView = null;
    }

    @Override // com.tealium.internal.listeners.WebViewCreatedListener
    public void onWebViewCreated(WebView webView) {
        if (this.mConfig.isCookieManagerEnabled()) {
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.setAcceptThirdPartyCookies(webView, true);
            CookieManager.setAcceptFileSchemeCookies(true);
            Log.d(BuildConfig.TAG, "WebView " + webView + " created and cookies enabled");
        }
    }

    @Override // com.tealium.internal.listeners.WebViewLoadedListener
    public void onWebViewLoad(WebView webView, boolean z) {
        String str;
        StringBuilder sb = new StringBuilder("Webview ");
        sb.append(webView);
        if (z) {
            str = " successfully loaded url: " + webView.getUrl();
        } else {
            str = "failed to load";
        }
        sb.append(str);
        Log.d(BuildConfig.TAG, sb.toString());
    }

    public void setTraceId(String str, boolean z) {
        String str2 = this.mTraceId;
        this.mTraceId = str;
        if (z && !TextUtils.equals(str2, str)) {
            this.mMessageRouter.b(createTraceUpdateRunnable(str));
        }
    }
}
