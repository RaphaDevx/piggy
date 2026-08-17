package com.getcapacitor;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.pm.PackageInfoCompat;
import androidx.fragment.app.Fragment;
import com.getcapacitor.android.R;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.cordova.MockCordovaInterfaceImpl;
import com.getcapacitor.cordova.MockCordovaWebViewImpl;
import com.getcapacitor.plugin.CapacitorCookies;
import com.getcapacitor.plugin.CapacitorHttp;
import com.getcapacitor.util.HostMask;
import com.getcapacitor.util.InternalUtils;
import com.getcapacitor.util.PermissionHelper;
import com.getcapacitor.util.WebColor;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginEntry;
import org.json.JSONException;

/* loaded from: classes3.dex */
public class Bridge {
    private static final String BUNDLE_LAST_PLUGIN_CALL_METHOD_NAME_KEY = "capacitorLastActivityPluginMethod";
    private static final String BUNDLE_LAST_PLUGIN_ID_KEY = "capacitorLastActivityPluginId";
    private static final String BUNDLE_PLUGIN_CALL_BUNDLE_KEY = "capacitorLastPluginCallBundle";
    private static final String BUNDLE_PLUGIN_CALL_OPTIONS_SAVED_KEY = "capacitorLastPluginCallOptions";
    public static final String CAPACITOR_CONTENT_START = "/_capacitor_content_";
    public static final String CAPACITOR_FILE_START = "/_capacitor_file_";

    @Deprecated
    public static final String CAPACITOR_HTTPS_INTERCEPTOR_START = "/_capacitor_https_interceptor_";
    public static final String CAPACITOR_HTTPS_SCHEME = "https";
    public static final String CAPACITOR_HTTP_INTERCEPTOR_START = "/_capacitor_http_interceptor_";
    public static final String CAPACITOR_HTTP_INTERCEPTOR_URL_PARAM = "u";
    public static final String CAPACITOR_HTTP_SCHEME = "http";
    public static final int DEFAULT_ANDROID_WEBVIEW_VERSION = 60;
    public static final int DEFAULT_HUAWEI_WEBVIEW_VERSION = 10;
    public static final String DEFAULT_WEB_ASSET_DIR = "public";
    private static final String LAST_BINARY_VERSION_CODE = "lastBinaryVersionCode";
    private static final String LAST_BINARY_VERSION_NAME = "lastBinaryVersionName";
    private static final String MINIMUM_ANDROID_WEBVIEW_ERROR = "System WebView is not supported";
    public static final int MINIMUM_ANDROID_WEBVIEW_VERSION = 55;
    public static final int MINIMUM_HUAWEI_WEBVIEW_VERSION = 10;
    private static final String PERMISSION_PREFS_NAME = "PluginPermStates";
    private Set<String> allowedOriginRules;
    private App app;
    private HostMask appAllowNavigationMask;
    private String appUrl;
    private String appUrlConfig;
    private ArrayList<String> authorities;
    private Boolean canInjectJS;
    private CapConfig config;
    private final AppCompatActivity context;
    public final MockCordovaInterfaceImpl cordovaInterface;
    private CordovaWebView cordovaWebView;
    private final Fragment fragment;
    private final HandlerThread handlerThread;
    private final List<Class<? extends Plugin>> initialPlugins;
    private Uri intentUri;
    private WebViewLocalServer localServer;
    private String localUrl;
    private ArrayList<String> miscJSFileInjections;
    private final MessageHandler msgHandler;
    private PluginCall pluginCallForLastActivity;
    private final List<Plugin> pluginInstances;
    private Map<String, PluginHandle> plugins;
    private CordovaPreferences preferences;
    private RouteProcessor routeProcessor;
    private Map<String, PluginCall> savedCalls;
    private Map<String, LinkedList<String>> savedPermissionCallIds;
    private ServerPath serverPath;
    private Handler taskHandler;
    private final WebView webView;
    private BridgeWebViewClient webViewClient;
    private List<WebViewListener> webViewListeners;

    static /* synthetic */ void lambda$triggerJSEvent$2(String str) {
    }

    static /* synthetic */ void lambda$triggerJSEvent$3(String str) {
    }

    @Deprecated
    public Bridge(AppCompatActivity appCompatActivity, WebView webView, List<Class<? extends Plugin>> list, MockCordovaInterfaceImpl mockCordovaInterfaceImpl, org.apache.cordova.PluginManager pluginManager, CordovaPreferences cordovaPreferences, CapConfig capConfig) {
        this(appCompatActivity, null, null, webView, list, new ArrayList(), mockCordovaInterfaceImpl, pluginManager, cordovaPreferences, capConfig);
    }

    private Bridge(AppCompatActivity appCompatActivity, ServerPath serverPath, Fragment fragment, WebView webView, List<Class<? extends Plugin>> list, List<Plugin> list2, MockCordovaInterfaceImpl mockCordovaInterfaceImpl, org.apache.cordova.PluginManager pluginManager, CordovaPreferences cordovaPreferences, CapConfig capConfig) {
        this.allowedOriginRules = new HashSet();
        this.authorities = new ArrayList<>();
        this.miscJSFileInjections = new ArrayList<>();
        this.canInjectJS = true;
        HandlerThread handlerThread = new HandlerThread("CapacitorPlugins");
        this.handlerThread = handlerThread;
        this.taskHandler = null;
        this.plugins = new HashMap();
        this.savedCalls = new HashMap();
        this.savedPermissionCallIds = new HashMap();
        this.webViewListeners = new ArrayList();
        this.app = new App();
        this.serverPath = serverPath;
        this.context = appCompatActivity;
        this.fragment = fragment;
        this.webView = webView;
        this.webViewClient = new BridgeWebViewClient(this);
        this.initialPlugins = list;
        this.pluginInstances = list2;
        this.cordovaInterface = mockCordovaInterfaceImpl;
        this.preferences = cordovaPreferences;
        handlerThread.start();
        this.taskHandler = new Handler(handlerThread.getLooper());
        capConfig = capConfig == null ? CapConfig.loadDefault(getActivity()) : capConfig;
        this.config = capConfig;
        Logger.init(capConfig);
        initWebView();
        setAllowedOriginRules();
        this.msgHandler = new MessageHandler(this, webView, pluginManager);
        this.intentUri = appCompatActivity.getIntent().getData();
        registerAllPlugins();
        loadWebView();
    }

    private void setAllowedOriginRules() {
        String[] allowNavigation = this.config.getAllowNavigation();
        String host = getHost();
        this.allowedOriginRules.add(getScheme() + "://" + host);
        if (getServerUrl() != null) {
            this.allowedOriginRules.add(getServerUrl());
        }
        if (allowNavigation != null) {
            for (String str : allowNavigation) {
                if (!str.startsWith("http")) {
                    this.allowedOriginRules.add("https://" + str);
                } else {
                    this.allowedOriginRules.add(str);
                }
            }
            this.authorities.addAll(Arrays.asList(allowNavigation));
        }
        this.appAllowNavigationMask = HostMask.Parser.parse(allowNavigation);
    }

    public App getApp() {
        return this.app;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void loadWebView() {
        /*
            r8 = this;
            com.getcapacitor.CapConfig r0 = r8.config
            boolean r6 = r0.isHTML5Mode()
            com.getcapacitor.JSInjector r0 = r8.getJSInjector()
            java.lang.String r1 = "DOCUMENT_START_SCRIPT"
            boolean r1 = androidx.webkit.WebViewFeature.isFeatureSupported(r1)
            r7 = 0
            if (r1 == 0) goto L45
            java.lang.String r1 = r8.appUrl
            android.net.Uri r1 = android.net.Uri.parse(r1)
            android.net.Uri$Builder r1 = r1.buildUpon()
            android.net.Uri$Builder r1 = r1.path(r7)
            android.net.Uri$Builder r1 = r1.fragment(r7)
            android.net.Uri$Builder r1 = r1.clearQuery()
            android.net.Uri r1 = r1.build()
            java.lang.String r1 = r1.toString()
            android.webkit.WebView r2 = r8.webView     // Catch: java.lang.IllegalArgumentException -> L40
            java.lang.String r3 = r0.getScriptString()     // Catch: java.lang.IllegalArgumentException -> L40
            java.util.Set r1 = java.util.Collections.singleton(r1)     // Catch: java.lang.IllegalArgumentException -> L40
            androidx.webkit.WebViewCompat.addDocumentStartJavaScript(r2, r3, r1)     // Catch: java.lang.IllegalArgumentException -> L40
            r4 = r7
            goto L46
        L40:
            java.lang.String r1 = "Invalid url, using fallback"
            com.getcapacitor.Logger.warn(r1)
        L45:
            r4 = r0
        L46:
            com.getcapacitor.WebViewLocalServer r0 = new com.getcapacitor.WebViewLocalServer
            androidx.appcompat.app.AppCompatActivity r2 = r8.context
            java.util.ArrayList<java.lang.String> r5 = r8.authorities
            r1 = r0
            r3 = r8
            r1.<init>(r2, r3, r4, r5, r6)
            r8.localServer = r0
            java.lang.String r1 = "public"
            r0.hostAssets(r1)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Loading app at "
            r0.<init>(r1)
            java.lang.String r1 = r8.appUrl
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.getcapacitor.Logger.debug(r0)
            android.webkit.WebView r0 = r8.webView
            com.getcapacitor.BridgeWebChromeClient r1 = new com.getcapacitor.BridgeWebChromeClient
            r1.<init>(r8)
            r0.setWebChromeClient(r1)
            android.webkit.WebView r0 = r8.webView
            com.getcapacitor.BridgeWebViewClient r1 = r8.webViewClient
            r0.setWebViewClient(r1)
            com.getcapacitor.CapConfig r0 = r8.config
            boolean r0 = r0.isResolveServiceWorkerRequests()
            if (r0 == 0) goto L90
            android.webkit.ServiceWorkerController r0 = android.webkit.ServiceWorkerController.getInstance()
            com.getcapacitor.Bridge$1 r1 = new com.getcapacitor.Bridge$1
            r1.<init>()
            r0.setServiceWorkerClient(r1)
        L90:
            boolean r0 = r8.isDeployDisabled()
            if (r0 != 0) goto Lc3
            boolean r0 = r8.isNewBinary()
            if (r0 != 0) goto Lc3
            android.content.Context r0 = r8.getContext()
            java.lang.String r1 = "CapWebViewSettings"
            r2 = 0
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r1, r2)
            java.lang.String r1 = "serverBasePath"
            java.lang.String r0 = r0.getString(r1, r7)
            if (r0 == 0) goto Lc3
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto Lc3
            java.io.File r1 = new java.io.File
            r1.<init>(r0)
            boolean r1 = r1.exists()
            if (r1 == 0) goto Lc3
            r8.setServerBasePath(r0)
        Lc3:
            boolean r0 = r8.isMinimumWebViewInstalled()
            if (r0 != 0) goto Lda
            java.lang.String r0 = r8.getErrorUrl()
            if (r0 == 0) goto Ld5
            android.webkit.WebView r8 = r8.webView
            r8.loadUrl(r0)
            return
        Ld5:
            java.lang.String r0 = "System WebView is not supported"
            com.getcapacitor.Logger.error(r0)
        Lda:
            com.getcapacitor.ServerPath r0 = r8.serverPath
            if (r0 == 0) goto Lfa
            com.getcapacitor.ServerPath$PathType r0 = r0.getType()
            com.getcapacitor.ServerPath$PathType r1 = com.getcapacitor.ServerPath.PathType.ASSET_PATH
            if (r0 != r1) goto Lf0
            com.getcapacitor.ServerPath r0 = r8.serverPath
            java.lang.String r0 = r0.getPath()
            r8.setServerAssetPath(r0)
            goto L101
        Lf0:
            com.getcapacitor.ServerPath r0 = r8.serverPath
            java.lang.String r0 = r0.getPath()
            r8.setServerBasePath(r0)
            goto L101
        Lfa:
            android.webkit.WebView r0 = r8.webView
            java.lang.String r8 = r8.appUrl
            r0.loadUrl(r8)
        L101:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getcapacitor.Bridge.loadWebView():void");
    }

    public boolean isMinimumWebViewInstalled() {
        getContext().getPackageManager();
        PackageInfo currentWebViewPackage = WebView.getCurrentWebViewPackage();
        Matcher matcher = Pattern.compile("(\\d+)").matcher(currentWebViewPackage.versionName);
        if (!matcher.find()) {
            return false;
        }
        int parseInt = Integer.parseInt(matcher.group(0));
        return currentWebViewPackage.packageName.equals("com.huawei.webview") ? parseInt >= this.config.getMinHuaweiWebViewVersion() : parseInt >= this.config.getMinWebViewVersion();
    }

    private int extractWebViewMajorVersion(PackageManager packageManager, String str) {
        try {
            return Integer.parseInt(InternalUtils.getPackageInfo(packageManager, str).versionName.split("\\.")[0]);
        } catch (Exception e) {
            Logger.warn(String.format("Unable to get package info for '%s' with err '%s'", str, e));
            return 0;
        }
    }

    public boolean launchIntent(Uri uri) {
        Boolean shouldOverrideLoad;
        Iterator<Map.Entry<String, PluginHandle>> it = this.plugins.entrySet().iterator();
        while (it.hasNext()) {
            Plugin pluginHandle = it.next().getValue().getInstance();
            if (pluginHandle != null && (shouldOverrideLoad = pluginHandle.shouldOverrideLoad(uri)) != null) {
                return shouldOverrideLoad.booleanValue();
            }
        }
        if (!uri.getScheme().equals("data") && !uri.getScheme().equals("blob")) {
            Uri parse = Uri.parse(this.appUrl);
            if ((!parse.getHost().equals(uri.getHost()) || !uri.getScheme().equals(parse.getScheme())) && !this.appAllowNavigationMask.matches(uri.getHost())) {
                try {
                    getContext().startActivity(new Intent("android.intent.action.VIEW", uri));
                    return true;
                } catch (ActivityNotFoundException unused) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isNewBinary() {
        String str;
        String str2;
        PackageInfo packageInfo;
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(com.getcapacitor.plugin.WebView.WEBVIEW_PREFS_NAME, 0);
        String string = sharedPreferences.getString(LAST_BINARY_VERSION_CODE, null);
        String string2 = sharedPreferences.getString(LAST_BINARY_VERSION_NAME, null);
        try {
            packageInfo = InternalUtils.getPackageInfo(getContext().getPackageManager(), getContext().getPackageName());
            str = Integer.toString((int) PackageInfoCompat.getLongVersionCode(packageInfo));
        } catch (Exception e) {
            e = e;
            str = "";
        }
        try {
            str2 = packageInfo.versionName;
        } catch (Exception e2) {
            e = e2;
            Logger.error("Unable to get package info", e);
            str2 = "";
            if (!str.equals(string)) {
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(LAST_BINARY_VERSION_CODE, str);
            edit.putString(LAST_BINARY_VERSION_NAME, str2);
            edit.putString(com.getcapacitor.plugin.WebView.CAP_SERVER_PATH, "");
            edit.apply();
            return true;
        }
        if (!str.equals(string) && str2.equals(string2)) {
            return false;
        }
        SharedPreferences.Editor edit2 = sharedPreferences.edit();
        edit2.putString(LAST_BINARY_VERSION_CODE, str);
        edit2.putString(LAST_BINARY_VERSION_NAME, str2);
        edit2.putString(com.getcapacitor.plugin.WebView.CAP_SERVER_PATH, "");
        edit2.apply();
        return true;
    }

    public boolean isDeployDisabled() {
        return this.preferences.getBoolean("DisableDeploy", false);
    }

    public boolean shouldKeepRunning() {
        return this.preferences.getBoolean("KeepRunning", true);
    }

    public void handleAppUrlLoadError(Exception exc) {
        if (exc instanceof SocketTimeoutException) {
            Logger.error("Unable to load app. Ensure the server is running at " + this.appUrl + ", or modify the appUrl setting in capacitor.config.json (make sure to npx cap copy after to commit changes).", exc);
        }
    }

    public boolean isDevMode() {
        return (getActivity().getApplicationInfo().flags & 2) != 0;
    }

    protected void setCordovaWebView(CordovaWebView cordovaWebView) {
        this.cordovaWebView = cordovaWebView;
    }

    public Context getContext() {
        return this.context;
    }

    public AppCompatActivity getActivity() {
        return this.context;
    }

    public Fragment getFragment() {
        return this.fragment;
    }

    public WebView getWebView() {
        return this.webView;
    }

    public Uri getIntentUri() {
        return this.intentUri;
    }

    public String getScheme() {
        return this.config.getAndroidScheme();
    }

    public String getHost() {
        return this.config.getHostname();
    }

    public String getServerUrl() {
        return this.config.getServerUrl();
    }

    public String getErrorUrl() {
        String errorPath = this.config.getErrorPath();
        if (errorPath == null || errorPath.trim().isEmpty()) {
            return null;
        }
        String host = getHost();
        return (getScheme() + "://" + host) + "/" + errorPath;
    }

    public String getAppUrl() {
        return this.appUrl;
    }

    public CapConfig getConfig() {
        return this.config;
    }

    public void reset() {
        this.savedCalls = new HashMap();
        Iterator<PluginHandle> it = this.plugins.values().iterator();
        while (it.hasNext()) {
            it.next().getInstance().removeAllListeners();
        }
    }

    private void initWebView() {
        WebSettings settings = this.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (this.config.isMixedContentAllowed()) {
            settings.setMixedContentMode(0);
        }
        String appendedUserAgentString = this.config.getAppendedUserAgentString();
        if (appendedUserAgentString != null) {
            settings.setUserAgentString(settings.getUserAgentString() + StringUtils.SPACE + appendedUserAgentString);
        }
        String overriddenUserAgentString = this.config.getOverriddenUserAgentString();
        if (overriddenUserAgentString != null) {
            settings.setUserAgentString(overriddenUserAgentString);
        }
        String backgroundColor = this.config.getBackgroundColor();
        if (backgroundColor != null) {
            try {
                this.webView.setBackgroundColor(WebColor.parseColor(backgroundColor));
            } catch (IllegalArgumentException unused) {
                Logger.debug("WebView background color not applied");
            }
        }
        settings.setDisplayZoomControls(false);
        settings.setBuiltInZoomControls(this.config.isZoomableWebView());
        if (this.config.isInitialFocus()) {
            this.webView.requestFocusFromTouch();
        }
        WebView.setWebContentsDebuggingEnabled(this.config.isWebContentsDebuggingEnabled());
        this.appUrlConfig = getServerUrl();
        String host = getHost();
        this.authorities.add(host);
        String scheme = getScheme();
        String str = scheme + "://" + host;
        this.localUrl = str;
        if (this.appUrlConfig != null) {
            try {
                this.authorities.add(new URL(this.appUrlConfig).getAuthority());
                String str2 = this.appUrlConfig;
                this.localUrl = str2;
                this.appUrl = str2;
            } catch (Exception e) {
                Logger.error("Provided server url is invalid: " + e.getMessage());
                return;
            }
        } else {
            this.appUrl = str;
            if (!scheme.equals("http") && !scheme.equals("https")) {
                this.appUrl += "/";
            }
        }
        String startPath = this.config.getStartPath();
        if (startPath == null || startPath.trim().isEmpty()) {
            return;
        }
        this.appUrl += startPath;
    }

    private void registerAllPlugins() {
        registerPlugin(CapacitorCookies.class);
        registerPlugin(com.getcapacitor.plugin.WebView.class);
        registerPlugin(CapacitorHttp.class);
        Iterator<Class<? extends Plugin>> it = this.initialPlugins.iterator();
        while (it.hasNext()) {
            registerPlugin(it.next());
        }
        Iterator<Plugin> it2 = this.pluginInstances.iterator();
        while (it2.hasNext()) {
            registerPluginInstance(it2.next());
        }
    }

    public void registerPlugins(Class<? extends Plugin>[] clsArr) {
        for (Class<? extends Plugin> cls : clsArr) {
            registerPlugin(cls);
        }
    }

    public void registerPluginInstances(Plugin[] pluginArr) {
        for (Plugin plugin : pluginArr) {
            registerPluginInstance(plugin);
        }
    }

    private String getLegacyPluginName(Class<? extends Plugin> cls) {
        NativePlugin nativePlugin = (NativePlugin) cls.getAnnotation(NativePlugin.class);
        if (nativePlugin == null) {
            Logger.error("Plugin doesn't have the @CapacitorPlugin annotation. Please add it");
            return null;
        }
        return nativePlugin.name();
    }

    public void registerPlugin(Class<? extends Plugin> cls) {
        String pluginId = pluginId(cls);
        if (pluginId == null) {
            return;
        }
        try {
            this.plugins.put(pluginId, new PluginHandle(this, cls));
        } catch (InvalidPluginException unused) {
            logInvalidPluginException(cls);
        } catch (PluginLoadException e) {
            logPluginLoadException(cls, e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void registerPluginInstance(Plugin plugin) {
        Class<?> cls = plugin.getClass();
        String pluginId = pluginId(cls);
        if (pluginId == null) {
            return;
        }
        try {
            this.plugins.put(pluginId, new PluginHandle(this, plugin));
        } catch (InvalidPluginException unused) {
            logInvalidPluginException(cls);
        }
    }

    private String pluginId(Class<? extends Plugin> cls) {
        String pluginName = pluginName(cls);
        String simpleName = cls.getSimpleName();
        if (pluginName == null) {
            return null;
        }
        if (pluginName.equals("")) {
            pluginName = simpleName;
        }
        Logger.debug("Registering plugin instance: " + pluginName);
        return pluginName;
    }

    private String pluginName(Class<? extends Plugin> cls) {
        CapacitorPlugin capacitorPlugin = (CapacitorPlugin) cls.getAnnotation(CapacitorPlugin.class);
        if (capacitorPlugin == null) {
            return getLegacyPluginName(cls);
        }
        return capacitorPlugin.name();
    }

    private void logInvalidPluginException(Class<? extends Plugin> cls) {
        Logger.error("NativePlugin " + cls.getName() + " is invalid. Ensure the @CapacitorPlugin annotation exists on the plugin class and the class extends Plugin");
    }

    private void logPluginLoadException(Class<? extends Plugin> cls, Exception exc) {
        Logger.error("NativePlugin " + cls.getName() + " failed to load", exc);
    }

    public PluginHandle getPlugin(String str) {
        return this.plugins.get(str);
    }

    @Deprecated
    public PluginHandle getPluginWithRequestCode(int i) {
        for (PluginHandle pluginHandle : this.plugins.values()) {
            CapacitorPlugin pluginAnnotation = pluginHandle.getPluginAnnotation();
            int i2 = 0;
            if (pluginAnnotation == null) {
                NativePlugin legacyPluginAnnotation = pluginHandle.getLegacyPluginAnnotation();
                if (legacyPluginAnnotation == null) {
                    continue;
                } else {
                    if (legacyPluginAnnotation.permissionRequestCode() == i) {
                        return pluginHandle;
                    }
                    int[] requestCodes = legacyPluginAnnotation.requestCodes();
                    int length = requestCodes.length;
                    while (i2 < length) {
                        if (requestCodes[i2] == i) {
                            return pluginHandle;
                        }
                        i2++;
                    }
                }
            } else {
                int[] requestCodes2 = pluginAnnotation.requestCodes();
                int length2 = requestCodes2.length;
                while (i2 < length2) {
                    if (requestCodes2[i2] == i) {
                        return pluginHandle;
                    }
                    i2++;
                }
            }
        }
        return null;
    }

    public void callPluginMethod(String str, final String str2, final PluginCall pluginCall) {
        try {
            final PluginHandle plugin = getPlugin(str);
            if (plugin == null) {
                Logger.error("unable to find plugin : " + str);
                pluginCall.errorCallback("unable to find plugin : " + str);
                return;
            }
            if (Logger.shouldLog()) {
                Logger.verbose("callback: " + pluginCall.getCallbackId() + ", pluginId: " + plugin.getId() + ", methodName: " + str2 + ", methodData: " + pluginCall.getData().toString());
            }
            this.taskHandler.post(new Runnable() { // from class: com.getcapacitor.Bridge$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Bridge.this.lambda$callPluginMethod$0(plugin, str2, pluginCall);
                }
            });
        } catch (Exception e) {
            Logger.error(Logger.tags("callPluginMethod"), "error : " + e, null);
            pluginCall.errorCallback(e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$callPluginMethod$0(PluginHandle pluginHandle, String str, PluginCall pluginCall) {
        try {
            pluginHandle.invoke(str, pluginCall);
            if (pluginCall.isKeptAlive()) {
                saveCall(pluginCall);
            }
        } catch (InvalidPluginMethodException | PluginLoadException e) {
            Logger.error("Unable to execute plugin method", e);
        } catch (Exception e2) {
            Logger.error("Serious error executing plugin", e2);
            throw new RuntimeException(e2);
        }
    }

    public void eval(final String str, final ValueCallback<String> valueCallback) {
        new Handler(this.context.getMainLooper()).post(new Runnable() { // from class: com.getcapacitor.Bridge$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                Bridge.this.lambda$eval$1(str, valueCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$eval$1(String str, ValueCallback valueCallback) {
        this.webView.evaluateJavascript(str, valueCallback);
    }

    public void logToJs(String str, String str2) {
        eval("window.Capacitor.logJs(\"" + str + "\", \"" + str2 + "\")", null);
    }

    public void logToJs(String str) {
        logToJs(str, "log");
    }

    public void triggerJSEvent(String str, String str2) {
        eval("window.Capacitor.triggerEvent(\"" + str + "\", \"" + str2 + "\")", new ValueCallback() { // from class: com.getcapacitor.Bridge$$ExternalSyntheticLambda2
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                Bridge.lambda$triggerJSEvent$2((String) obj);
            }
        });
    }

    public void triggerJSEvent(String str, String str2, String str3) {
        eval("window.Capacitor.triggerEvent(\"" + str + "\", \"" + str2 + "\", " + str3 + ")", new ValueCallback() { // from class: com.getcapacitor.Bridge$$ExternalSyntheticLambda4
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                Bridge.lambda$triggerJSEvent$3((String) obj);
            }
        });
    }

    public void triggerWindowJSEvent(String str) {
        triggerJSEvent(str, "window");
    }

    public void triggerWindowJSEvent(String str, String str2) {
        triggerJSEvent(str, "window", str2);
    }

    public void triggerDocumentJSEvent(String str) {
        triggerJSEvent(str, "document");
    }

    public void triggerDocumentJSEvent(String str, String str2) {
        triggerJSEvent(str, "document", str2);
    }

    public void execute(Runnable runnable) {
        this.taskHandler.post(runnable);
    }

    public void executeOnMainThread(Runnable runnable) {
        new Handler(this.context.getMainLooper()).post(runnable);
    }

    public void saveCall(PluginCall pluginCall) {
        this.savedCalls.put(pluginCall.getCallbackId(), pluginCall);
    }

    public PluginCall getSavedCall(String str) {
        if (str == null) {
            return null;
        }
        return this.savedCalls.get(str);
    }

    PluginCall getPluginCallForLastActivity() {
        PluginCall pluginCall = this.pluginCallForLastActivity;
        this.pluginCallForLastActivity = null;
        return pluginCall;
    }

    void setPluginCallForLastActivity(PluginCall pluginCall) {
        this.pluginCallForLastActivity = pluginCall;
    }

    public void releaseCall(PluginCall pluginCall) {
        releaseCall(pluginCall.getCallbackId());
    }

    public void releaseCall(String str) {
        this.savedCalls.remove(str);
    }

    protected PluginCall getPermissionCall(String str) {
        LinkedList<String> linkedList = this.savedPermissionCallIds.get(str);
        return getSavedCall(linkedList != null ? linkedList.poll() : null);
    }

    protected void savePermissionCall(PluginCall pluginCall) {
        if (pluginCall != null) {
            if (!this.savedPermissionCallIds.containsKey(pluginCall.getPluginId())) {
                this.savedPermissionCallIds.put(pluginCall.getPluginId(), new LinkedList<>());
            }
            this.savedPermissionCallIds.get(pluginCall.getPluginId()).add(pluginCall.getCallbackId());
            saveCall(pluginCall);
        }
    }

    public <I, O> ActivityResultLauncher<I> registerForActivityResult(ActivityResultContract<I, O> activityResultContract, ActivityResultCallback<O> activityResultCallback) {
        Fragment fragment = this.fragment;
        if (fragment != null) {
            return fragment.registerForActivityResult(activityResultContract, activityResultCallback);
        }
        return this.context.registerForActivityResult(activityResultContract, activityResultCallback);
    }

    private JSInjector getJSInjector() {
        try {
            String globalJS = JSExport.getGlobalJS(this.context, this.config.isLoggingEnabled(), isDevMode());
            String bridgeJS = JSExport.getBridgeJS(this.context);
            String pluginJS = JSExport.getPluginJS(this.plugins.values());
            String cordovaJS = JSExport.getCordovaJS(this.context);
            String cordovaPluginJS = JSExport.getCordovaPluginJS(this.context);
            String cordovaPluginsFileJS = JSExport.getCordovaPluginsFileJS(this.context);
            String str = "window.WEBVIEW_SERVER_URL = '" + this.localUrl + "';";
            String miscFileJS = JSExport.getMiscFileJS(this.miscJSFileInjections, this.context);
            this.miscJSFileInjections = new ArrayList<>();
            this.canInjectJS = false;
            return new JSInjector(globalJS, bridgeJS, pluginJS, cordovaJS, cordovaPluginJS, cordovaPluginsFileJS, str, miscFileJS);
        } catch (Exception e) {
            Logger.error("Unable to export Capacitor JS. App will not function!", e);
            return null;
        }
    }

    public void injectScriptBeforeLoad(String str) {
        if (this.canInjectJS.booleanValue()) {
            this.miscJSFileInjections.add(str);
        }
    }

    public void restoreInstanceState(Bundle bundle) {
        String string = bundle.getString(BUNDLE_LAST_PLUGIN_ID_KEY);
        String string2 = bundle.getString(BUNDLE_LAST_PLUGIN_CALL_METHOD_NAME_KEY);
        String string3 = bundle.getString(BUNDLE_PLUGIN_CALL_OPTIONS_SAVED_KEY);
        if (string != null) {
            if (string3 != null) {
                try {
                    this.pluginCallForLastActivity = new PluginCall(this.msgHandler, string, PluginCall.CALLBACK_ID_DANGLING, string2, new JSObject(string3));
                } catch (JSONException e) {
                    Logger.error("Unable to restore plugin call, unable to parse persisted JSON object", e);
                }
            }
            Bundle bundle2 = bundle.getBundle(BUNDLE_PLUGIN_CALL_BUNDLE_KEY);
            PluginHandle plugin = getPlugin(string);
            if (bundle2 != null && plugin != null) {
                plugin.getInstance().restoreState(bundle2);
            } else {
                Logger.error("Unable to restore last plugin call");
            }
        }
    }

    public void saveInstanceState(Bundle bundle) {
        PluginHandle plugin;
        Logger.debug("Saving instance state!");
        PluginCall pluginCall = this.pluginCallForLastActivity;
        if (pluginCall == null || (plugin = getPlugin(pluginCall.getPluginId())) == null) {
            return;
        }
        Bundle saveInstanceState = plugin.getInstance().saveInstanceState();
        if (saveInstanceState != null) {
            bundle.putString(BUNDLE_LAST_PLUGIN_ID_KEY, pluginCall.getPluginId());
            bundle.putString(BUNDLE_LAST_PLUGIN_CALL_METHOD_NAME_KEY, pluginCall.getMethodName());
            bundle.putString(BUNDLE_PLUGIN_CALL_OPTIONS_SAVED_KEY, pluginCall.getData().toString());
            bundle.putBundle(BUNDLE_PLUGIN_CALL_BUNDLE_KEY, saveInstanceState);
            return;
        }
        Logger.error("Couldn't save last " + pluginCall.getPluginId() + "'s Plugin " + pluginCall.getMethodName() + " call");
    }

    @Deprecated
    public void startActivityForPluginWithResult(PluginCall pluginCall, Intent intent, int i) {
        Logger.debug("Starting activity for result");
        this.pluginCallForLastActivity = pluginCall;
        getActivity().startActivityForResult(intent, i);
    }

    boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        PluginHandle pluginWithRequestCode = getPluginWithRequestCode(i);
        if (pluginWithRequestCode == null) {
            Logger.debug("Unable to find a Capacitor plugin to handle permission requestCode, trying Cordova plugins " + i);
            try {
                return this.cordovaInterface.handlePermissionResult(i, strArr, iArr);
            } catch (JSONException e) {
                Logger.debug("Error on Cordova plugin permissions request " + e.getMessage());
                return false;
            }
        }
        if (pluginWithRequestCode.getPluginAnnotation() != null) {
            return false;
        }
        pluginWithRequestCode.getInstance().handleRequestPermissionsResult(i, strArr, iArr);
        return true;
    }

    protected boolean validatePermissions(Plugin plugin, PluginCall pluginCall, Map<String, Boolean> map) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(PERMISSION_PREFS_NAME, 0);
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            String key = entry.getKey();
            if (entry.getValue().booleanValue()) {
                if (sharedPreferences.getString(key, null) != null) {
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.remove(key);
                    edit.apply();
                }
            } else {
                SharedPreferences.Editor edit2 = sharedPreferences.edit();
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), key)) {
                    edit2.putString(key, PermissionState.PROMPT_WITH_RATIONALE.toString());
                } else {
                    edit2.putString(key, PermissionState.DENIED.toString());
                }
                edit2.apply();
            }
        }
        String[] strArr = (String[]) map.keySet().toArray(new String[0]);
        if (PermissionHelper.hasDefinedPermissions(getContext(), strArr)) {
            return true;
        }
        StringBuilder sb = new StringBuilder("Missing the following permissions in AndroidManifest.xml:\n");
        for (String str : PermissionHelper.getUndefinedPermissions(getContext(), strArr)) {
            sb.append(str + StringUtils.LF);
        }
        pluginCall.reject(sb.toString());
        return false;
    }

    protected Map<String, PermissionState> getPermissionStates(Plugin plugin) {
        PermissionState byState;
        HashMap hashMap = new HashMap();
        for (Permission permission : plugin.getPluginHandle().getPluginAnnotation().permissions()) {
            if (permission.strings().length == 0 || (permission.strings().length == 1 && permission.strings()[0].isEmpty())) {
                String alias = permission.alias();
                if (!alias.isEmpty() && ((PermissionState) hashMap.get(alias)) == null) {
                    hashMap.put(alias, PermissionState.GRANTED);
                }
            } else {
                for (String str : permission.strings()) {
                    String alias2 = permission.alias().isEmpty() ? str : permission.alias();
                    if (ActivityCompat.checkSelfPermission(getContext(), str) == 0) {
                        byState = PermissionState.GRANTED;
                    } else {
                        PermissionState permissionState = PermissionState.PROMPT;
                        String string = getContext().getSharedPreferences(PERMISSION_PREFS_NAME, 0).getString(str, null);
                        byState = string != null ? PermissionState.byState(string) : permissionState;
                    }
                    PermissionState permissionState2 = (PermissionState) hashMap.get(alias2);
                    if (permissionState2 == null || permissionState2 == PermissionState.GRANTED) {
                        hashMap.put(alias2, byState);
                    }
                }
            }
        }
        return hashMap;
    }

    boolean onActivityResult(int i, int i2, Intent intent) {
        PluginHandle pluginWithRequestCode = getPluginWithRequestCode(i);
        if (pluginWithRequestCode == null || pluginWithRequestCode.getInstance() == null) {
            Logger.debug("Unable to find a Capacitor plugin to handle requestCode, trying Cordova plugins " + i);
            return this.cordovaInterface.onActivityResult(i, i2, intent);
        }
        if (pluginWithRequestCode.getInstance().getSavedCall() == null && this.pluginCallForLastActivity != null) {
            pluginWithRequestCode.getInstance().saveCall(this.pluginCallForLastActivity);
        }
        pluginWithRequestCode.getInstance().handleOnActivityResult(i, i2, intent);
        this.pluginCallForLastActivity = null;
        return true;
    }

    public void onNewIntent(Intent intent) {
        Iterator<PluginHandle> it = this.plugins.values().iterator();
        while (it.hasNext()) {
            it.next().getInstance().handleOnNewIntent(intent);
        }
        CordovaWebView cordovaWebView = this.cordovaWebView;
        if (cordovaWebView != null) {
            cordovaWebView.onNewIntent(intent);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        Iterator<PluginHandle> it = this.plugins.values().iterator();
        while (it.hasNext()) {
            it.next().getInstance().handleOnConfigurationChanged(configuration);
        }
    }

    public void onRestart() {
        Iterator<PluginHandle> it = this.plugins.values().iterator();
        while (it.hasNext()) {
            it.next().getInstance().handleOnRestart();
        }
    }

    public void onStart() {
        Iterator<PluginHandle> it = this.plugins.values().iterator();
        while (it.hasNext()) {
            it.next().getInstance().handleOnStart();
        }
        CordovaWebView cordovaWebView = this.cordovaWebView;
        if (cordovaWebView != null) {
            cordovaWebView.handleStart();
        }
    }

    public void onResume() {
        Iterator<PluginHandle> it = this.plugins.values().iterator();
        while (it.hasNext()) {
            it.next().getInstance().handleOnResume();
        }
        CordovaWebView cordovaWebView = this.cordovaWebView;
        if (cordovaWebView != null) {
            cordovaWebView.handleResume(shouldKeepRunning());
        }
    }

    public void onPause() {
        Iterator<PluginHandle> it = this.plugins.values().iterator();
        while (it.hasNext()) {
            it.next().getInstance().handleOnPause();
        }
        if (this.cordovaWebView != null) {
            this.cordovaWebView.handlePause(shouldKeepRunning() || this.cordovaInterface.getActivityResultCallback() != null);
        }
    }

    public void onStop() {
        Iterator<PluginHandle> it = this.plugins.values().iterator();
        while (it.hasNext()) {
            it.next().getInstance().handleOnStop();
        }
        CordovaWebView cordovaWebView = this.cordovaWebView;
        if (cordovaWebView != null) {
            cordovaWebView.handleStop();
        }
    }

    public void onDestroy() {
        Iterator<PluginHandle> it = this.plugins.values().iterator();
        while (it.hasNext()) {
            it.next().getInstance().handleOnDestroy();
        }
        this.handlerThread.quitSafely();
        CordovaWebView cordovaWebView = this.cordovaWebView;
        if (cordovaWebView != null) {
            cordovaWebView.handleDestroy();
        }
    }

    public void onDetachedFromWindow() {
        this.webView.removeAllViews();
        this.webView.destroy();
    }

    public String getServerBasePath() {
        return this.localServer.getBasePath();
    }

    public void setServerBasePath(String str) {
        this.localServer.hostFiles(str);
        this.webView.post(new Runnable() { // from class: com.getcapacitor.Bridge$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                Bridge.this.lambda$setServerBasePath$4();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setServerBasePath$4() {
        this.webView.loadUrl(this.appUrl);
    }

    public void setServerAssetPath(String str) {
        this.localServer.hostAssets(str);
        this.webView.post(new Runnable() { // from class: com.getcapacitor.Bridge$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                Bridge.this.lambda$setServerAssetPath$5();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setServerAssetPath$5() {
        this.webView.loadUrl(this.appUrl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$reload$6() {
        this.webView.loadUrl(this.appUrl);
    }

    public void reload() {
        this.webView.post(new Runnable() { // from class: com.getcapacitor.Bridge$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                Bridge.this.lambda$reload$6();
            }
        });
    }

    public String getLocalUrl() {
        return this.localUrl;
    }

    public WebViewLocalServer getLocalServer() {
        return this.localServer;
    }

    public HostMask getAppAllowNavigationMask() {
        return this.appAllowNavigationMask;
    }

    public Set<String> getAllowedOriginRules() {
        return this.allowedOriginRules;
    }

    public BridgeWebViewClient getWebViewClient() {
        return this.webViewClient;
    }

    public void setWebViewClient(BridgeWebViewClient bridgeWebViewClient) {
        this.webViewClient = bridgeWebViewClient;
        this.webView.setWebViewClient(bridgeWebViewClient);
    }

    List<WebViewListener> getWebViewListeners() {
        return this.webViewListeners;
    }

    void setWebViewListeners(List<WebViewListener> list) {
        this.webViewListeners = list;
    }

    RouteProcessor getRouteProcessor() {
        return this.routeProcessor;
    }

    void setRouteProcessor(RouteProcessor routeProcessor) {
        this.routeProcessor = routeProcessor;
    }

    ServerPath getServerPath() {
        return this.serverPath;
    }

    public void addWebViewListener(WebViewListener webViewListener) {
        this.webViewListeners.add(webViewListener);
    }

    public void removeWebViewListener(WebViewListener webViewListener) {
        this.webViewListeners.remove(webViewListener);
    }

    public static class Builder {
        private AppCompatActivity activity;
        private Fragment fragment;
        private RouteProcessor routeProcessor;
        private ServerPath serverPath;
        private Bundle instanceState = null;
        private CapConfig config = null;
        private List<Class<? extends Plugin>> plugins = new ArrayList();
        private List<Plugin> pluginInstances = new ArrayList();
        private final List<WebViewListener> webViewListeners = new ArrayList();

        public Builder(AppCompatActivity appCompatActivity) {
            this.activity = appCompatActivity;
        }

        public Builder(Fragment fragment) {
            this.activity = (AppCompatActivity) fragment.getActivity();
            this.fragment = fragment;
        }

        public Builder setInstanceState(Bundle bundle) {
            this.instanceState = bundle;
            return this;
        }

        public Builder setConfig(CapConfig capConfig) {
            this.config = capConfig;
            return this;
        }

        public Builder setPlugins(List<Class<? extends Plugin>> list) {
            this.plugins = list;
            return this;
        }

        public Builder addPlugin(Class<? extends Plugin> cls) {
            this.plugins.add(cls);
            return this;
        }

        public Builder addPlugins(List<Class<? extends Plugin>> list) {
            Iterator<Class<? extends Plugin>> it = list.iterator();
            while (it.hasNext()) {
                addPlugin(it.next());
            }
            return this;
        }

        public Builder addPluginInstance(Plugin plugin) {
            this.pluginInstances.add(plugin);
            return this;
        }

        public Builder addPluginInstances(List<Plugin> list) {
            this.pluginInstances.addAll(list);
            return this;
        }

        public Builder addWebViewListener(WebViewListener webViewListener) {
            this.webViewListeners.add(webViewListener);
            return this;
        }

        public Builder addWebViewListeners(List<WebViewListener> list) {
            Iterator<WebViewListener> it = list.iterator();
            while (it.hasNext()) {
                addWebViewListener(it.next());
            }
            return this;
        }

        public Builder setRouteProcessor(RouteProcessor routeProcessor) {
            this.routeProcessor = routeProcessor;
            return this;
        }

        public Builder setServerPath(ServerPath serverPath) {
            this.serverPath = serverPath;
            return this;
        }

        public Bridge create() {
            ConfigXmlParser configXmlParser = new ConfigXmlParser();
            configXmlParser.parse(this.activity.getApplicationContext());
            CordovaPreferences preferences = configXmlParser.getPreferences();
            preferences.setPreferencesBundle(this.activity.getIntent().getExtras());
            ArrayList<PluginEntry> pluginEntries = configXmlParser.getPluginEntries();
            MockCordovaInterfaceImpl mockCordovaInterfaceImpl = new MockCordovaInterfaceImpl(this.activity);
            Bundle bundle = this.instanceState;
            if (bundle != null) {
                mockCordovaInterfaceImpl.restoreInstanceState(bundle);
            }
            Fragment fragment = this.fragment;
            WebView webView = (WebView) (fragment != null ? fragment.getView().findViewById(R.id.webview) : this.activity.findViewById(R.id.webview));
            MockCordovaWebViewImpl mockCordovaWebViewImpl = new MockCordovaWebViewImpl(this.activity.getApplicationContext());
            mockCordovaWebViewImpl.init(mockCordovaInterfaceImpl, pluginEntries, preferences, webView);
            org.apache.cordova.PluginManager pluginManager = mockCordovaWebViewImpl.getPluginManager();
            mockCordovaInterfaceImpl.onCordovaInit(pluginManager);
            Bridge bridge = new Bridge(this.activity, this.serverPath, this.fragment, webView, this.plugins, this.pluginInstances, mockCordovaInterfaceImpl, pluginManager, preferences, this.config);
            if (webView instanceof CapacitorWebView) {
                CapacitorWebView capacitorWebView = (CapacitorWebView) webView;
                capacitorWebView.setBridge(bridge);
                capacitorWebView.edgeToEdgeHandler(bridge);
            }
            bridge.setCordovaWebView(mockCordovaWebViewImpl);
            bridge.setWebViewListeners(this.webViewListeners);
            bridge.setRouteProcessor(this.routeProcessor);
            Bundle bundle2 = this.instanceState;
            if (bundle2 != null) {
                bridge.restoreInstanceState(bundle2);
            }
            return bridge;
        }
    }
}
