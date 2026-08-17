package ch.coop.capacitor.browserpro;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;
import ch.coop.capacitor.browserpro.BrowserProPlugin;
import com.getcapacitor.Bridge;
import com.getcapacitor.BridgeWebChromeClient;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.UrlHandler;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.json.JSONException;
import org.json.JSONObject;

@CapacitorPlugin(name = "BrowserPro")
/* loaded from: classes3.dex */
public class BrowserProPlugin extends Plugin {
    static final boolean resetBrowserAfterCloseDefaultValue = true;
    Handler _mainHandler;
    ImageButton backButton;
    BridgeWebChromeClient bridgeWebChromeClient;
    TextView closeButton;
    FrameLayout customViewContainer;
    Dialog dialog;
    ImageButton forwardButton;
    LinearLayout header;
    View headerLine;
    TextView headerTitleLabel;
    List<HttpAuthCredential> httpAuthCredentials;
    List<String> interceptorList;
    BrowserProJavascriptInterface javascriptInterface;
    ProgressBar loadingSpinner;
    List<String> messageInterceptorList;
    ImageButton reloadButton;
    String sessionTag;
    ImageButton shareButton;
    ConstraintLayout toolbar;
    WebView webView;
    CloseDialog closeDialog = null;
    boolean resetBrowserAfterClose = true;

    enum BrowserRunnableMessage {
        LOAD_URL,
        OPEN,
        CLOSE,
        RESET,
        EVALUATE_JAVA_SCRIPT
    }

    public class BrowserProWebChromeClient extends BridgeWebChromeClient {
        public BrowserProWebChromeClient(Bridge bridge) {
            super(bridge);
        }

        @Override // com.getcapacitor.BridgeWebChromeClient, android.webkit.WebChromeClient
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            BrowserProPlugin.this.webView.setVisibility(8);
            BrowserProPlugin.this.header.setVisibility(8);
            BrowserProPlugin.this.toolbar.setVisibility(8);
            BrowserProPlugin.this.customViewContainer.addView(view);
            BrowserProPlugin.this.customViewContainer.setVisibility(0);
        }

        @Override // com.getcapacitor.BridgeWebChromeClient, android.webkit.WebChromeClient
        public void onHideCustomView() {
            BrowserProPlugin.this.webView.setVisibility(0);
            BrowserProPlugin.this.header.setVisibility(0);
            BrowserProPlugin.this.toolbar.setVisibility(0);
            BrowserProPlugin.this.customViewContainer.setVisibility(8);
            BrowserProPlugin.this.customViewContainer.removeAllViews();
        }
    }

    public class BrowserProJavascriptInterface {
        public BrowserProJavascriptInterface() {
        }

        @JavascriptInterface
        public void postMessageFromJS(String str) {
            try {
                BrowserProPlugin.this.notifyEventListeners("messageReceived", new JSObject(str));
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.getcapacitor.Plugin
    public void load() {
        super.load();
        this.bridgeWebChromeClient = new BrowserProWebChromeClient(getBridge());
        this.javascriptInterface = new BrowserProJavascriptInterface();
    }

    @PluginMethod
    public void setLimitsNavigationsToAppBoundDomains(PluginCall pluginCall) {
        pluginCall.resolve();
    }

    @PluginMethod
    public void evaluateJavaScript(PluginCall pluginCall) {
        String string = pluginCall.getString("script");
        if (string != null) {
            BrowserViewUpdaterRunnable browserViewUpdaterRunnable = new BrowserViewUpdaterRunnable(BrowserRunnableMessage.EVALUATE_JAVA_SCRIPT, pluginCall);
            browserViewUpdaterRunnable.script = string;
            mainHandler().post(browserViewUpdaterRunnable);
            return;
        }
        pluginCall.reject("Missing JS to evaluate");
    }

    @PluginMethod
    public void setHttpAuthCredentials(PluginCall pluginCall) {
        try {
            List<JSONObject> list = pluginCall.getArray("httpAuthCredentials").toList();
            ArrayList arrayList = new ArrayList();
            for (JSONObject jSONObject : list) {
                arrayList.add(new HttpAuthCredential(jSONObject.getString("host"), jSONObject.getString("realm"), jSONObject.getString("username"), jSONObject.getString("password")));
            }
            this.httpAuthCredentials = arrayList;
            pluginCall.resolve();
        } catch (JSONException e) {
            pluginCall.reject("Invalid arguments: " + e.getMessage());
        }
    }

    @PluginMethod
    public void setInterceptorList(PluginCall pluginCall) {
        try {
            this.interceptorList = pluginCall.getArray("interceptorList").toList();
            pluginCall.resolve();
        } catch (JSONException unused) {
            pluginCall.reject("JSON exception");
        }
    }

    @PluginMethod
    public void setMessageInterceptorList(PluginCall pluginCall) {
        try {
            this.messageInterceptorList = pluginCall.getArray("messageInterceptorList").toList();
            pluginCall.resolve();
        } catch (JSONException unused) {
            pluginCall.reject("JSON exception");
        }
    }

    @PluginMethod
    public void loadUrl(PluginCall pluginCall) {
        String string = pluginCall.getString("url");
        if (string == null) {
            pluginCall.reject("Must provide a URL");
            return;
        }
        if (string.isEmpty()) {
            pluginCall.reject("URL must not be empty");
            return;
        }
        final HashMap hashMap = new HashMap();
        final JSObject object = pluginCall.getObject("headers");
        if (object != null) {
            object.keys().forEachRemaining(new Consumer() { // from class: ch.coop.capacitor.browserpro.BrowserProPlugin$$ExternalSyntheticLambda2
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    BrowserProPlugin.lambda$loadUrl$0(JSObject.this, hashMap, (String) obj);
                }
            });
        }
        this.sessionTag = pluginCall.getString("sessionTag");
        BrowserViewUpdaterRunnable browserViewUpdaterRunnable = new BrowserViewUpdaterRunnable(BrowserRunnableMessage.LOAD_URL, pluginCall);
        browserViewUpdaterRunnable.url = string;
        browserViewUpdaterRunnable.headers = hashMap;
        mainHandler().post(browserViewUpdaterRunnable);
    }

    static /* synthetic */ void lambda$loadUrl$0(JSObject jSObject, HashMap hashMap, String str) {
        String string = jSObject.getString(str);
        if (string != null) {
            hashMap.put(str, string);
        }
    }

    @PluginMethod
    public void open(PluginCall pluginCall) {
        String str;
        String str2;
        Boolean bool;
        Boolean bool2;
        String str3;
        String string = pluginCall.getString("tintColor");
        String string2 = pluginCall.getString("headerColor");
        String string3 = pluginCall.getString("headerLineColor");
        String string4 = pluginCall.getString("headerTitle");
        FontDefinition from = FontDefinition.from(pluginCall.getObject("headerTitleFont"));
        String string5 = pluginCall.getString("closeButtonTitle");
        FontDefinition from2 = FontDefinition.from(pluginCall.getObject("closeButtonFont"));
        String string6 = pluginCall.getString("closeButtonStyle");
        String string7 = pluginCall.getString("closeButtonBackgroundColor");
        Boolean bool3 = pluginCall.getBoolean("showToolbar", false);
        String string8 = pluginCall.getString("toolbarTintColor");
        Boolean bool4 = pluginCall.getBoolean("sharingEnabled", false);
        JSObject object = pluginCall.getObject("closeDialog", null);
        if (object != null) {
            bool = bool4;
            str3 = string8;
            bool2 = bool3;
            str2 = string7;
            str = string6;
            this.closeDialog = new CloseDialog(object.getString("title", ""), object.getString("message", ""), object.getString("cancelButton", ""), object.getString("okButton", ""));
        } else {
            str = string6;
            str2 = string7;
            bool = bool4;
            bool2 = bool3;
            str3 = string8;
            this.closeDialog = null;
        }
        this.resetBrowserAfterClose = pluginCall.getBoolean("resetBrowserAfterClose", true).booleanValue();
        if (string5 == null) {
            pluginCall.reject("Missing 'closeButtonTitle'");
            return;
        }
        BrowserViewUpdaterRunnable browserViewUpdaterRunnable = new BrowserViewUpdaterRunnable(BrowserRunnableMessage.OPEN, pluginCall);
        browserViewUpdaterRunnable.tintColor = string;
        browserViewUpdaterRunnable.headerColor = string2;
        browserViewUpdaterRunnable.headerLineColor = string3;
        browserViewUpdaterRunnable.headerTitle = string4;
        browserViewUpdaterRunnable.headerTitleFont = from;
        browserViewUpdaterRunnable.closeButtonTitle = string5;
        browserViewUpdaterRunnable.closeButtonFont = from2;
        browserViewUpdaterRunnable.closeButtonStyle = str;
        browserViewUpdaterRunnable.closeButtonBackgroundColor = str2;
        browserViewUpdaterRunnable.showToolbar = bool2;
        browserViewUpdaterRunnable.toolbarTintColor = str3;
        browserViewUpdaterRunnable.sharingEnabled = bool;
        mainHandler().post(browserViewUpdaterRunnable);
    }

    @PluginMethod
    public void close(PluginCall pluginCall) {
        mainHandler().post(new BrowserViewUpdaterRunnable(BrowserRunnableMessage.CLOSE, pluginCall));
    }

    @PluginMethod
    public void clearCache(final PluginCall pluginCall) {
        getActivity().runOnUiThread(new Runnable() { // from class: ch.coop.capacitor.browserpro.BrowserProPlugin$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BrowserProPlugin.this.lambda$clearCache$1(pluginCall);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$clearCache$1(PluginCall pluginCall) {
        new WebView(getActivity()).clearCache(true);
        pluginCall.resolve();
    }

    @PluginMethod
    public void clearCookies(final PluginCall pluginCall) {
        CookieManager.getInstance().removeAllCookies(new ValueCallback() { // from class: ch.coop.capacitor.browserpro.BrowserProPlugin$$ExternalSyntheticLambda1
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                PluginCall.this.resolve();
            }
        });
    }

    @PluginMethod
    public void reset(PluginCall pluginCall) {
        mainHandler().post(new BrowserViewUpdaterRunnable(BrowserRunnableMessage.RESET, pluginCall));
    }

    private Handler mainHandler() {
        if (this._mainHandler == null) {
            this._mainHandler = new Handler(getContext().getMainLooper());
        }
        return this._mainHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyEventListeners(String str) {
        notifyEventListeners(str, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyEventListeners(String str, JSObject jSObject) {
        if (jSObject == null) {
            jSObject = new JSObject();
        }
        notifyListeners(str, jSObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLoadingState(boolean z) {
        ProgressBar progressBar = this.loadingSpinner;
        if (progressBar != null) {
            if (z) {
                progressBar.setVisibility(0);
            } else {
                progressBar.setVisibility(4);
            }
        }
        ImageButton imageButton = this.reloadButton;
        if (imageButton != null) {
            imageButton.setEnabled(!z);
        }
        WebView webView = this.webView;
        if (webView != null) {
            ImageButton imageButton2 = this.backButton;
            if (imageButton2 != null) {
                imageButton2.setEnabled(webView.canGoBack());
            }
            ImageButton imageButton3 = this.forwardButton;
            if (imageButton3 != null) {
                imageButton3.setEnabled(this.webView.canGoForward());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<String> getMatchingEntries(String str, List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && str != null) {
            for (String str2 : list) {
                if (str2.startsWith("_REGEX:")) {
                    try {
                        if (Pattern.compile(str2.substring(7)).matcher(str).matches()) {
                            arrayList.add(str2);
                        }
                    } catch (PatternSyntaxException unused) {
                    }
                }
                if (str.startsWith(str2)) {
                    arrayList.add(str2);
                }
            }
        }
        return arrayList;
    }

    class BrowserViewUpdaterRunnable implements Runnable {
        PluginCall call;
        String closeButtonBackgroundColor;
        FontDefinition closeButtonFont;
        String closeButtonStyle;
        String closeButtonTitle;
        String headerColor;
        String headerLineColor;
        String headerTitle;
        FontDefinition headerTitleFont;
        Map<String, String> headers;
        BrowserRunnableMessage message;
        String script;
        Boolean sharingEnabled;
        Boolean showToolbar;
        String tintColor;
        String toolbarTintColor;
        String url;

        static /* synthetic */ void lambda$close$1(DialogInterface dialogInterface, int i) {
        }

        BrowserViewUpdaterRunnable(BrowserRunnableMessage browserRunnableMessage, PluginCall pluginCall) {
            this.message = browserRunnableMessage;
            this.call = pluginCall;
        }

        private void close() {
            if (BrowserProPlugin.this.closeDialog != null) {
                new AlertDialog.Builder(BrowserProPlugin.this.getContext()).setTitle(BrowserProPlugin.this.closeDialog.title).setMessage(BrowserProPlugin.this.closeDialog.message).setPositiveButton(BrowserProPlugin.this.closeDialog.okButton, new DialogInterface.OnClickListener() { // from class: ch.coop.capacitor.browserpro.BrowserProPlugin$BrowserViewUpdaterRunnable$$ExternalSyntheticLambda0
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        BrowserProPlugin.BrowserViewUpdaterRunnable.this.lambda$close$0(dialogInterface, i);
                    }
                }).setNegativeButton(BrowserProPlugin.this.closeDialog.cancelButton, new DialogInterface.OnClickListener() { // from class: ch.coop.capacitor.browserpro.BrowserProPlugin$BrowserViewUpdaterRunnable$$ExternalSyntheticLambda1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        BrowserProPlugin.BrowserViewUpdaterRunnable.lambda$close$1(dialogInterface, i);
                    }
                }).create().show();
            } else {
                executeClose();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$close$0(DialogInterface dialogInterface, int i) {
            executeClose();
        }

        private void executeClose() {
            if (BrowserProPlugin.this.dialog != null && BrowserProPlugin.this.dialog.isShowing()) {
                BrowserProPlugin.this.dialog.dismiss();
            }
            onClose();
        }

        private void onClose() {
            if (BrowserProPlugin.this.resetBrowserAfterClose) {
                reset();
            } else {
                BrowserProPlugin.this.resetBrowserAfterClose = true;
            }
            BrowserProPlugin.this.closeDialog = null;
            if (BrowserProPlugin.this.sessionTag != null) {
                JSObject jSObject = new JSObject();
                jSObject.put("sessionTag", BrowserProPlugin.this.sessionTag);
                BrowserProPlugin.this.notifyEventListeners("close", jSObject);
                BrowserProPlugin.this.sessionTag = null;
                return;
            }
            BrowserProPlugin.this.notifyEventListeners("close");
        }

        private void reset() {
            if (BrowserProPlugin.this.dialog != null) {
                if (BrowserProPlugin.this.dialog.isShowing()) {
                    BrowserProPlugin.this.dialog.dismiss();
                }
                BrowserProPlugin.this.dialog = null;
                if (BrowserProPlugin.this.webView != null) {
                    BrowserProPlugin.this.webView.destroy();
                    BrowserProPlugin.this.webView = null;
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.message == BrowserRunnableMessage.CLOSE) {
                executeClose();
                this.call.resolve();
                return;
            }
            if (this.message == BrowserRunnableMessage.RESET) {
                reset();
                this.call.resolve();
                return;
            }
            if (this.message == BrowserRunnableMessage.EVALUATE_JAVA_SCRIPT) {
                BrowserProPlugin.this.webView.evaluateJavascript(this.script, new ValueCallback() { // from class: ch.coop.capacitor.browserpro.BrowserProPlugin$BrowserViewUpdaterRunnable$$ExternalSyntheticLambda2
                    @Override // android.webkit.ValueCallback
                    public final void onReceiveValue(Object obj) {
                        BrowserProPlugin.BrowserViewUpdaterRunnable.this.lambda$run$2((String) obj);
                    }
                });
                return;
            }
            if (BrowserProPlugin.this.dialog == null) {
                BrowserProPlugin.this.dialog = new Dialog(BrowserProPlugin.this.getContext(), com.getcapacitor.android.R.style.AppTheme_NoActionBar);
                BrowserProPlugin.this.dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation.Dialog;
                BrowserProPlugin.this.dialog.requestWindowFeature(1);
                BrowserProPlugin.this.dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: ch.coop.capacitor.browserpro.BrowserProPlugin$BrowserViewUpdaterRunnable$$ExternalSyntheticLambda3
                    @Override // android.content.DialogInterface.OnKeyListener
                    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        boolean lambda$run$3;
                        lambda$run$3 = BrowserProPlugin.BrowserViewUpdaterRunnable.this.lambda$run$3(dialogInterface, i, keyEvent);
                        return lambda$run$3;
                    }
                });
                ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(BrowserProPlugin.this.getContext()).inflate(R.layout.browser, (ViewGroup) null);
                BrowserProPlugin.this.webView = (WebView) constraintLayout.findViewById(R.id.webview);
                BrowserProPlugin.this.header = (LinearLayout) constraintLayout.findViewById(R.id.header);
                BrowserProPlugin.this.closeButton = (TextView) constraintLayout.findViewById(R.id.closeButton);
                BrowserProPlugin.this.headerTitleLabel = (TextView) constraintLayout.findViewById(R.id.headerTitleLabel);
                BrowserProPlugin.this.loadingSpinner = (ProgressBar) constraintLayout.findViewById(R.id.loadingSpinner);
                BrowserProPlugin.this.headerLine = constraintLayout.findViewById(R.id.headerLine);
                BrowserProPlugin.this.toolbar = (ConstraintLayout) constraintLayout.findViewById(R.id.toolbar);
                BrowserProPlugin.this.backButton = (ImageButton) constraintLayout.findViewById(R.id.backButton);
                BrowserProPlugin.this.forwardButton = (ImageButton) constraintLayout.findViewById(R.id.forwardButton);
                BrowserProPlugin.this.reloadButton = (ImageButton) constraintLayout.findViewById(R.id.reloadButton);
                BrowserProPlugin.this.shareButton = (ImageButton) constraintLayout.findViewById(R.id.shareButton);
                BrowserProPlugin.this.customViewContainer = (FrameLayout) constraintLayout.findViewById(R.id.customViewContainer);
                BrowserProPlugin.this.closeButton.setOnClickListener(new View.OnClickListener() { // from class: ch.coop.capacitor.browserpro.BrowserProPlugin$BrowserViewUpdaterRunnable$$ExternalSyntheticLambda4
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BrowserProPlugin.BrowserViewUpdaterRunnable.this.lambda$run$4(view);
                    }
                });
                BrowserProPlugin.this.loadingSpinner.setVisibility(4);
                WebSettings settings = BrowserProPlugin.this.webView.getSettings();
                settings.setJavaScriptEnabled(true);
                settings.setDomStorageEnabled(true);
                BrowserProPlugin.this.webView.setWebChromeClient(BrowserProPlugin.this.bridgeWebChromeClient);
                BrowserProPlugin.this.webView.setWebViewClient(BrowserProPlugin.this.new BrowserClient());
                BrowserProPlugin.this.webView.setDownloadListener(new OpenExternalAppDownloadListener(BrowserProPlugin.this.getContext()));
                BrowserProPlugin.this.webView.addJavascriptInterface(BrowserProPlugin.this.javascriptInterface, "AndroidBridge");
                if (WebViewFeature.isFeatureSupported("PAYMENT_REQUEST")) {
                    WebSettingsCompat.setPaymentRequestEnabled(settings, true);
                }
                BrowserProPlugin.this.backButton.setOnClickListener(new View.OnClickListener() { // from class: ch.coop.capacitor.browserpro.BrowserProPlugin$BrowserViewUpdaterRunnable$$ExternalSyntheticLambda5
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BrowserProPlugin.BrowserViewUpdaterRunnable.this.lambda$run$5(view);
                    }
                });
                BrowserProPlugin.this.forwardButton.setOnClickListener(new View.OnClickListener() { // from class: ch.coop.capacitor.browserpro.BrowserProPlugin$BrowserViewUpdaterRunnable$$ExternalSyntheticLambda6
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BrowserProPlugin.BrowserViewUpdaterRunnable.this.lambda$run$6(view);
                    }
                });
                BrowserProPlugin.this.reloadButton.setOnClickListener(new View.OnClickListener() { // from class: ch.coop.capacitor.browserpro.BrowserProPlugin$BrowserViewUpdaterRunnable$$ExternalSyntheticLambda7
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BrowserProPlugin.BrowserViewUpdaterRunnable.this.lambda$run$7(view);
                    }
                });
                BrowserProPlugin.this.shareButton.setOnClickListener(new View.OnClickListener() { // from class: ch.coop.capacitor.browserpro.BrowserProPlugin$BrowserViewUpdaterRunnable$$ExternalSyntheticLambda8
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BrowserProPlugin.BrowserViewUpdaterRunnable.this.lambda$run$8(view);
                    }
                });
                BrowserProPlugin.this.dialog.setContentView(constraintLayout);
            }
            if (this.message == BrowserRunnableMessage.OPEN) {
                if (this.closeButtonTitle != null) {
                    BrowserProPlugin.this.closeButton.setText(this.closeButtonTitle);
                }
                FontDefinition fontDefinition = this.closeButtonFont;
                if (fontDefinition != null) {
                    fontDefinition.apply(BrowserProPlugin.this.getContext(), BrowserProPlugin.this.closeButton);
                }
                if ("capsule".equals(this.closeButtonStyle)) {
                    GradientDrawable gradientDrawable = new GradientDrawable();
                    gradientDrawable.setShape(0);
                    gradientDrawable.setCornerRadius(9999.0f);
                    String str = this.closeButtonBackgroundColor;
                    if (str != null) {
                        gradientDrawable.setColor(Color.parseColor(str));
                    }
                    BrowserProPlugin.this.closeButton.setBackground(gradientDrawable);
                }
                String str2 = this.tintColor;
                if (str2 != null) {
                    int parseColor = parseColor(str2);
                    BrowserProPlugin.this.closeButton.setTextColor(parseColor);
                    BrowserProPlugin.this.headerTitleLabel.setTextColor(parseColor);
                    BrowserProPlugin.this.loadingSpinner.setIndeterminateTintList(ColorStateList.valueOf(parseColor));
                } else {
                    BrowserProPlugin.this.loadingSpinner.setIndeterminateTintList(ColorStateList.valueOf(-3355444));
                }
                String str3 = this.headerColor;
                if (str3 != null) {
                    int parseColor2 = parseColor(str3);
                    BrowserProPlugin.this.dialog.getWindow().setStatusBarColor(parseColor2);
                    BrowserProPlugin.this.header.setBackgroundColor(parseColor2);
                }
                if (this.headerLineColor != null) {
                    BrowserProPlugin.this.headerLine.setBackgroundColor(parseColor(this.headerLineColor));
                    BrowserProPlugin.this.headerLine.setVisibility(0);
                } else {
                    BrowserProPlugin.this.headerLine.setVisibility(8);
                }
                if (this.headerTitle != null) {
                    BrowserProPlugin.this.headerTitleLabel.setText(this.headerTitle);
                    FontDefinition fontDefinition2 = this.headerTitleFont;
                    if (fontDefinition2 != null) {
                        fontDefinition2.apply(BrowserProPlugin.this.getContext(), BrowserProPlugin.this.headerTitleLabel);
                    }
                    BrowserProPlugin.this.headerTitleLabel.setVisibility(0);
                } else {
                    BrowserProPlugin.this.headerTitleLabel.setVisibility(8);
                }
                if (this.showToolbar.booleanValue()) {
                    BrowserProPlugin.this.toolbar.setVisibility(0);
                    String str4 = this.toolbarTintColor;
                    if (str4 != null) {
                        ColorStateList colorStateList = new ColorStateList(new int[][]{new int[]{-16842910}, new int[0]}, new int[]{-3355444, parseColor(str4)});
                        BrowserProPlugin.this.backButton.setImageTintList(colorStateList);
                        BrowserProPlugin.this.forwardButton.setImageTintList(colorStateList);
                        BrowserProPlugin.this.reloadButton.setImageTintList(colorStateList);
                        BrowserProPlugin.this.shareButton.setImageTintList(colorStateList);
                    }
                    if (!this.sharingEnabled.booleanValue()) {
                        BrowserProPlugin.this.shareButton.setVisibility(8);
                    }
                } else {
                    BrowserProPlugin.this.toolbar.setVisibility(8);
                }
                if (!BrowserProPlugin.this.dialog.isShowing()) {
                    BrowserProPlugin.this.dialog.show();
                }
            } else if (this.message == BrowserRunnableMessage.LOAD_URL) {
                BrowserProPlugin.this.updateLoadingState(true);
                BrowserProPlugin.this.webView.loadUrl(this.url, this.headers);
                if (!BrowserProPlugin.this.dialog.isShowing()) {
                    BrowserProPlugin.this.dialog.show();
                    BrowserProPlugin.this.dialog.dismiss();
                }
            }
            this.call.resolve();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$run$2(String str) {
            JSObject jSObject = new JSObject();
            jSObject.put("result", str);
            this.call.resolve(jSObject);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ boolean lambda$run$3(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            Boolean bool = false;
            if (i == 4 && keyEvent.getAction() == 1) {
                close();
                bool = true;
            }
            return bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$run$4(View view) {
            close();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$run$5(View view) {
            backButtonTapped();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$run$6(View view) {
            forwardButtonTapped();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$run$7(View view) {
            reloadButtonTapped();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$run$8(View view) {
            shareButtonTapped();
        }

        private void backButtonTapped() {
            if (BrowserProPlugin.this.webView != null) {
                BrowserProPlugin.this.webView.goBack();
                emitBrowserNavigationChangeEvent("Back", BrowserProPlugin.this.webView.getUrl());
            }
        }

        private void forwardButtonTapped() {
            if (BrowserProPlugin.this.webView != null) {
                BrowserProPlugin.this.webView.goForward();
                emitBrowserNavigationChangeEvent("Forward", BrowserProPlugin.this.webView.getUrl());
            }
        }

        private void reloadButtonTapped() {
            if (BrowserProPlugin.this.webView != null) {
                BrowserProPlugin.this.updateLoadingState(true);
                BrowserProPlugin.this.webView.reload();
                emitBrowserNavigationChangeEvent("Reload", BrowserProPlugin.this.webView.getUrl());
            }
        }

        private void shareButtonTapped() {
            String url = BrowserProPlugin.this.webView.getUrl();
            if (url != null) {
                JSObject jSObject = new JSObject();
                jSObject.put("url", url);
                jSObject.put("title", BrowserProPlugin.this.webView.getTitle());
                BrowserProPlugin.this.notifyEventListeners(FirebaseAnalytics.Event.SHARE, jSObject);
            }
        }

        private int parseColor(String str) {
            if (str != null) {
                try {
                    return Color.parseColor(str.trim());
                } catch (Exception unused) {
                }
            }
            return ViewCompat.MEASURED_STATE_MASK;
        }

        private void emitBrowserNavigationChangeEvent(String str, String str2) {
            JSObject jSObject = new JSObject();
            jSObject.put(UrlHandler.ACTION, str);
            jSObject.put("url", str2);
            BrowserProPlugin.this.notifyEventListeners("browserNavigationChange", jSObject);
        }
    }

    public class BrowserClient extends WebViewClient {
        private static final String REGEX_PREFIX = "_REGEX:";

        public BrowserClient() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            String uri = webResourceRequest.getUrl().toString();
            List matchingEntries = BrowserProPlugin.getMatchingEntries(uri, BrowserProPlugin.this.interceptorList);
            if (!matchingEntries.isEmpty()) {
                JSObject jSObject = new JSObject();
                jSObject.put("url", uri);
                jSObject.put("match", (String) matchingEntries.get(0));
                BrowserProPlugin.this.notifyEventListeners("inAppBrowserInterceptor", jSObject);
                return true;
            }
            Uri url = webResourceRequest.getUrl();
            String scheme = url.getScheme();
            if ("mailto".equals(scheme) || "tel".equals(scheme) || "whatsapp".equals(scheme)) {
                BrowserProPlugin.this.getContext().startActivity(new Intent("android.intent.action.VIEW", url));
                return true;
            }
            if ("intent".equals(scheme)) {
                try {
                    Intent parseUri = Intent.parseUri(uri, 1);
                    if (parseUri != null && "ch.twint.action.TWINT_PAYMENT".equals(parseUri.getAction())) {
                        Context context = BrowserProPlugin.this.getContext();
                        if (context.getPackageManager().resolveActivity(parseUri, 65536) != null) {
                            context.startActivity(parseUri);
                            return true;
                        }
                        String stringExtra = parseUri.getStringExtra("browser_fallback_url");
                        if (stringExtra == null || stringExtra.isEmpty()) {
                            stringExtra = "https://play.google.com/store/search?q=twint&c=apps";
                        }
                        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringExtra)));
                        return true;
                    }
                } catch (URISyntaxException unused) {
                }
            }
            return false;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            try {
                if (Uri.parse(str).getHost().equals("pay.datatrans.com")) {
                    webView.loadUrl("javascript:window.twintHookActivated=true;");
                }
            } catch (Exception unused) {
            }
            BrowserProPlugin.this.updateLoadingState(true);
            JSObject jSObject = new JSObject();
            jSObject.put("url", str);
            BrowserProPlugin.this.notifyEventListeners("loadstart", jSObject);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            try {
                if (Uri.parse(str).getHost().equals("pay.datatrans.com")) {
                    webView.loadUrl("javascript:if(typeof window.refreshUI !== 'undefined') window.refreshUI()");
                }
                if (!BrowserProPlugin.getMatchingEntries(str, BrowserProPlugin.this.messageInterceptorList).isEmpty()) {
                    webView.evaluateJavascript("(function() {\n    if (window.__androidPostMessageHooked) return;\n    window.__androidPostMessageHooked = true;\n    const originalPostMessage = window.parent.postMessage;\n    window.parent.postMessage = function(message, targetOrigin) {\n        try {\n            AndroidBridge.postMessageFromJS(JSON.stringify(message));\n        } catch (e) {}\n        return originalPostMessage.apply(this, arguments);\n    };\n})();", null);
                }
            } catch (Exception unused) {
            }
            BrowserProPlugin.this.updateLoadingState(false);
            JSObject jSObject = new JSObject();
            jSObject.put("url", str);
            BrowserProPlugin.this.notifyEventListeners("loadstop", jSObject);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            BrowserProPlugin.this.updateLoadingState(false);
            JSObject jSObject = new JSObject();
            jSObject.put("url", webResourceRequest.getUrl().toString());
            jSObject.put("code", webResourceError.getErrorCode());
            jSObject.put("message", (Object) webResourceError.getDescription());
            BrowserProPlugin.this.notifyEventListeners("loaderror", jSObject);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
            Boolean bool = false;
            if (BrowserProPlugin.this.httpAuthCredentials != null) {
                for (HttpAuthCredential httpAuthCredential : BrowserProPlugin.this.httpAuthCredentials) {
                    if ("*".equals(httpAuthCredential.host) || httpAuthCredential.host.equals(str)) {
                        if ("*".equals(httpAuthCredential.realm) || httpAuthCredential.realm.equals(str2)) {
                            bool = true;
                            httpAuthHandler.proceed(httpAuthCredential.username, httpAuthCredential.password);
                            break;
                        }
                    }
                }
            }
            if (bool.booleanValue()) {
                return;
            }
            super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
        }
    }

    public static class OpenExternalAppDownloadListener implements DownloadListener {
        private final Context context;

        OpenExternalAppDownloadListener(Context context) {
            this.context = context;
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        }
    }

    static class HttpAuthCredential {
        private String host;
        private String password;
        private String realm;
        private String username;

        HttpAuthCredential(String str, String str2, String str3, String str4) {
            this.host = str;
            this.realm = str2;
            this.username = str3;
            this.password = str4;
        }
    }

    static class CloseDialog {
        private String cancelButton;
        private String message;
        private String okButton;
        private String title;

        CloseDialog(String str, String str2, String str3, String str4) {
            this.title = str;
            this.message = str2;
            this.cancelButton = str3;
            this.okButton = str4;
        }
    }
}
