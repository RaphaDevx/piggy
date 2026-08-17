package ch.coop.auth;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import ch.coop.auth.AuthBrowserView;
import ch.coop.supercardapp.widget.WidgetHelper$$ExternalSyntheticBackport0;
import com.getcapacitor.android.R;
import io.sentry.IScope;
import io.sentry.ScopeCallback;
import io.sentry.Sentry;
import io.sentry.protocol.SentryThread;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lib.android.paypal.com.magnessdk.c;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.EndSessionRequest;
import net.openid.appauth.EndSessionResponse;

/* loaded from: classes3.dex */
public class AuthBrowserView {
    public static final int TOOLBAR_HEIGHT_DP = 56;
    private final Activity activity;
    private AuthorizationRequest authRequest;
    private ResultCallback callback;
    private TextView closeButton;
    private Dialog dialog;
    private EndSessionRequest endSessionRequest;
    private Uri expectedRedirectUri;
    private View headerLine;
    private TextView headerTitleLabel;
    private Uri loadUri;
    private ProgressBar loadingSpinner;
    private Handler mainHandler;
    private Scenario scenario;
    private LinearLayout toolbar;
    private AuthConfigurationUIOptions uiOptions;
    private WebView webView;

    private enum BrowserRunnableMessage {
        LOAD_URL,
        OPEN,
        CLOSE,
        CLOSE_BY_USER
    }

    @FunctionalInterface
    public interface ResultCallback {
        void onResult(Intent intent);
    }

    public enum Scenario {
        Authorize,
        Reauthorize,
        EndSession
    }

    public AuthBrowserView(Activity activity) {
        this.activity = activity;
    }

    public WebView getWebView() {
        return this.webView;
    }

    public TextView getCloseButton() {
        return this.closeButton;
    }

    public ProgressBar getLoadingSpinner() {
        return this.loadingSpinner;
    }

    public Dialog getDialog() {
        return this.dialog;
    }

    public void show(EndSessionRequest endSessionRequest, AuthConfigurationUIOptions authConfigurationUIOptions, ResultCallback resultCallback) {
        this.scenario = Scenario.EndSession;
        this.authRequest = null;
        this.endSessionRequest = endSessionRequest;
        this.loadUri = endSessionRequest.toUri();
        this.expectedRedirectUri = this.endSessionRequest.postLogoutRedirectUri;
        this.uiOptions = authConfigurationUIOptions;
        this.callback = resultCallback;
        openAndLoadUrl();
    }

    public void show(Scenario scenario, AuthorizationRequest authorizationRequest, AuthConfigurationUIOptions authConfigurationUIOptions, ResultCallback resultCallback) {
        this.scenario = scenario;
        this.authRequest = authorizationRequest;
        this.endSessionRequest = null;
        this.loadUri = authorizationRequest.toUri();
        this.expectedRedirectUri = this.authRequest.redirectUri;
        this.uiOptions = authConfigurationUIOptions;
        this.callback = resultCallback;
        openAndLoadUrl();
    }

    private void openAndLoadUrl() {
        this.activity.runOnUiThread(new BrowserViewUpdaterRunnable(BrowserRunnableMessage.OPEN));
        this.activity.runOnUiThread(new BrowserViewUpdaterRunnable(BrowserRunnableMessage.LOAD_URL));
    }

    /* JADX INFO: Access modifiers changed from: private */
    class BrowserViewUpdaterRunnable implements Runnable {
        private final BrowserRunnableMessage message;

        static /* synthetic */ void lambda$close$3(DialogInterface dialogInterface, int i) {
        }

        BrowserViewUpdaterRunnable(BrowserRunnableMessage browserRunnableMessage) {
            this.message = browserRunnableMessage;
        }

        @Override // java.lang.Runnable
        public void run() {
            Map<String, String> emptyMap;
            if (this.message == BrowserRunnableMessage.CLOSE) {
                executeClose(false);
                return;
            }
            if (this.message == BrowserRunnableMessage.CLOSE_BY_USER) {
                close();
                return;
            }
            if (this.message == BrowserRunnableMessage.OPEN) {
                if (AuthBrowserView.this.dialog == null) {
                    AuthBrowserView.this.dialog = createDialog();
                }
                styleUIElements();
                if (AuthBrowserView.this.dialog.isShowing()) {
                    return;
                }
                AuthBrowserView.this.dialog.show();
                return;
            }
            if (this.message == BrowserRunnableMessage.LOAD_URL) {
                if (AuthBrowserView.this.dialog == null) {
                    AuthBrowserView.this.dialog = createDialog();
                }
                String string = Settings.Secure.getString(AuthBrowserView.this.activity.getContentResolver(), c.f);
                if (string != null) {
                    emptyMap = WidgetHelper$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("X-Device-ID", string)});
                } else {
                    emptyMap = Collections.emptyMap();
                }
                AuthBrowserView.this.webView.loadUrl(AuthBrowserView.this.loadUri.toString(), emptyMap);
                if (AuthBrowserView.this.dialog.isShowing()) {
                    return;
                }
                AuthBrowserView.this.dialog.show();
                AuthBrowserView.this.dialog.dismiss();
            }
        }

        private Dialog createDialog() {
            Activity activity = AuthBrowserView.this.activity;
            Dialog dialog = new Dialog(activity, R.style.AppTheme_NoActionBar);
            dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation.Dialog;
            dialog.requestWindowFeature(1);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: ch.coop.auth.AuthBrowserView$BrowserViewUpdaterRunnable$$ExternalSyntheticLambda4
                @Override // android.content.DialogInterface.OnKeyListener
                public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    boolean lambda$createDialog$0;
                    lambda$createDialog$0 = AuthBrowserView.BrowserViewUpdaterRunnable.this.lambda$createDialog$0(dialogInterface, i, keyEvent);
                    return lambda$createDialog$0;
                }
            });
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(1);
            AuthBrowserView.this.toolbar = (LinearLayout) LayoutInflater.from(activity).inflate(ch.coop.capacitor.auth.R.layout.toolbar, (ViewGroup) null);
            AuthBrowserView.this.toolbar.setBackgroundColor(-1);
            AuthBrowserView.this.toolbar.setLayoutParams(new LinearLayout.LayoutParams(-1, dpToPixels(56)));
            linearLayout.addView(AuthBrowserView.this.toolbar);
            AuthBrowserView authBrowserView = AuthBrowserView.this;
            authBrowserView.closeButton = (TextView) authBrowserView.toolbar.findViewById(ch.coop.capacitor.auth.R.id.textViewRight);
            AuthBrowserView.this.closeButton.setContentDescription("Close Button");
            AuthBrowserView.this.closeButton.setOnClickListener(new View.OnClickListener() { // from class: ch.coop.auth.AuthBrowserView$BrowserViewUpdaterRunnable$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AuthBrowserView.BrowserViewUpdaterRunnable.this.lambda$createDialog$1(view);
                }
            });
            AuthBrowserView authBrowserView2 = AuthBrowserView.this;
            authBrowserView2.headerTitleLabel = (TextView) authBrowserView2.toolbar.findViewById(ch.coop.capacitor.auth.R.id.textViewCenter);
            AuthBrowserView.this.headerTitleLabel.setContentDescription("Header title");
            AuthBrowserView authBrowserView3 = AuthBrowserView.this;
            authBrowserView3.loadingSpinner = (ProgressBar) authBrowserView3.toolbar.findViewById(ch.coop.capacitor.auth.R.id.progressBar);
            AuthBrowserView.this.loadingSpinner.setVisibility(4);
            AuthBrowserView.this.headerLine = new View(activity);
            AuthBrowserView.this.headerLine.setLayoutParams(new LinearLayout.LayoutParams(-1, dpToPixels(1)));
            linearLayout.addView(AuthBrowserView.this.headerLine);
            AuthBrowserView.this.webView = new WebView(activity);
            AuthBrowserView.this.webView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            AuthBrowserView.this.webView.setId(6);
            WebSettings settings = AuthBrowserView.this.webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setSupportMultipleWindows(true);
            AuthBrowserView.this.webView.setWebChromeClient(new BrowserWebChromeClient());
            AuthBrowserView.this.webView.setWebViewClient(AuthBrowserView.this.new BrowserClient());
            linearLayout.addView(AuthBrowserView.this.webView);
            dialog.setContentView(linearLayout);
            return dialog;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ boolean lambda$createDialog$0(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            Boolean bool = false;
            if (i == 4 && keyEvent.getAction() == 1) {
                close();
                bool = true;
            }
            return bool.booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$createDialog$1(View view) {
            close();
        }

        private void styleUIElements() {
            String headerTitleEndSession;
            Activity activity = AuthBrowserView.this.activity;
            if (AuthBrowserView.this.uiOptions != null) {
                if (AuthBrowserView.this.uiOptions.getCloseButtonTitle() != null) {
                    AuthBrowserView.this.closeButton.setText(AuthBrowserView.this.uiOptions.getCloseButtonTitle());
                }
                if (AuthBrowserView.this.uiOptions.getCloseButtonFont() != null) {
                    AuthBrowserView.this.uiOptions.getCloseButtonFont().apply(activity, AuthBrowserView.this.closeButton);
                }
                if (AuthBrowserView.this.uiOptions.getTintColor() != null) {
                    int parseColor = Color.parseColor(AuthBrowserView.this.uiOptions.getTintColor());
                    AuthBrowserView.this.closeButton.setTextColor(parseColor);
                    AuthBrowserView.this.headerTitleLabel.setTextColor(parseColor);
                    AuthBrowserView.this.loadingSpinner.setIndeterminateTintList(ColorStateList.valueOf(parseColor));
                } else {
                    AuthBrowserView.this.loadingSpinner.setIndeterminateTintList(ColorStateList.valueOf(-3355444));
                }
                if (AuthBrowserView.this.uiOptions.getHeaderColor() != null) {
                    int parseColor2 = Color.parseColor(AuthBrowserView.this.uiOptions.getHeaderColor());
                    AuthBrowserView.this.dialog.getWindow().setStatusBarColor(parseColor2);
                    AuthBrowserView.this.toolbar.setBackgroundColor(parseColor2);
                }
                if (AuthBrowserView.this.uiOptions.getHeaderLineColor() != null) {
                    AuthBrowserView.this.headerLine.setBackgroundColor(Color.parseColor(AuthBrowserView.this.uiOptions.getHeaderLineColor()));
                    AuthBrowserView.this.headerLine.setVisibility(0);
                } else {
                    AuthBrowserView.this.headerLine.setVisibility(8);
                }
                if (AuthBrowserView.this.authRequest != null) {
                    headerTitleEndSession = AuthBrowserView.this.uiOptions.getHeaderTitleAuthorize();
                } else {
                    headerTitleEndSession = AuthBrowserView.this.endSessionRequest != null ? AuthBrowserView.this.uiOptions.getHeaderTitleEndSession() : null;
                }
                if (headerTitleEndSession != null) {
                    AuthBrowserView.this.headerTitleLabel.setText(headerTitleEndSession);
                    if (AuthBrowserView.this.uiOptions.getHeaderTitleFont() != null) {
                        AuthBrowserView.this.uiOptions.getHeaderTitleFont().apply(activity, AuthBrowserView.this.headerTitleLabel);
                    }
                    AuthBrowserView.this.headerTitleLabel.setVisibility(0);
                    return;
                }
                AuthBrowserView.this.headerTitleLabel.setVisibility(8);
                return;
            }
            AuthBrowserView.this.headerLine.setVisibility(8);
            AuthBrowserView.this.headerTitleLabel.setVisibility(8);
            AuthBrowserView.this.loadingSpinner.setIndeterminateTintList(ColorStateList.valueOf(-3355444));
        }

        private int dpToPixels(int i) {
            return new Double(TypedValue.applyDimension(1, i, AuthBrowserView.this.activity.getResources().getDisplayMetrics())).intValue();
        }

        private void close() {
            AuthConfigurationUIOptionsAlert closeAlertReauthorize;
            if (AuthBrowserView.this.uiOptions.getCloseAlertRegistrationTriggerUrlPathSuffixes().contains(Uri.parse(AuthBrowserView.this.webView.getUrl()).getLastPathSegment())) {
                closeAlertReauthorize = AuthBrowserView.this.uiOptions.getCloseAlertRegistration();
            } else {
                closeAlertReauthorize = AuthBrowserView.this.scenario == Scenario.Reauthorize ? AuthBrowserView.this.uiOptions.getCloseAlertReauthorize() : null;
            }
            if (closeAlertReauthorize != null) {
                new AlertDialog.Builder(AuthBrowserView.this.activity).setTitle(closeAlertReauthorize.getTitle()).setMessage(closeAlertReauthorize.getMessage()).setPositiveButton(closeAlertReauthorize.getTextButtonAction(), new DialogInterface.OnClickListener() { // from class: ch.coop.auth.AuthBrowserView$BrowserViewUpdaterRunnable$$ExternalSyntheticLambda2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        AuthBrowserView.BrowserViewUpdaterRunnable.this.lambda$close$2(dialogInterface, i);
                    }
                }).setNegativeButton(closeAlertReauthorize.getTextButtonCancel(), new DialogInterface.OnClickListener() { // from class: ch.coop.auth.AuthBrowserView$BrowserViewUpdaterRunnable$$ExternalSyntheticLambda3
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        AuthBrowserView.BrowserViewUpdaterRunnable.lambda$close$3(dialogInterface, i);
                    }
                }).create().show();
            } else {
                executeClose(true);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$close$2(DialogInterface dialogInterface, int i) {
            executeClose(true);
        }

        private void executeClose(Boolean bool) {
            if (AuthBrowserView.this.dialog != null && AuthBrowserView.this.dialog.isShowing()) {
                AuthBrowserView.this.dialog.dismiss();
            }
            if (bool.booleanValue()) {
                handleCancelByUser();
            }
        }

        private void handleCancelByUser() {
            AuthBrowserView.this.callback.onResult(AuthorizationException.fromTemplate(AuthorizationException.GeneralErrors.USER_CANCELED_AUTH_FLOW, null).toIntent());
        }
    }

    public class BrowserClient extends WebViewClient {
        public BrowserClient() {
        }

        class WebResourceError extends Error {
            private static final long serialVersionUID = 2523165911192850013L;

            WebResourceError() {
            }
        }

        class HttpError extends Error {
            private static final long serialVersionUID = 6937046000687920919L;

            HttpError() {
            }
        }

        class SslError extends Error {
            private static final long serialVersionUID = -3990500040055329533L;

            SslError() {
            }
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            Uri url = webResourceRequest.getUrl();
            if (url.getScheme().equals(AuthBrowserView.this.expectedRedirectUri.getScheme()) && url.getHost().equals(AuthBrowserView.this.expectedRedirectUri.getHost()) && url.getPath().equals(AuthBrowserView.this.expectedRedirectUri.getPath())) {
                if (AuthBrowserView.this.authRequest != null) {
                    AuthBrowserView.this.callback.onResult(extractAuthorizationResponseData(AuthBrowserView.this.authRequest, url));
                } else if (AuthBrowserView.this.endSessionRequest != null) {
                    AuthBrowserView.this.callback.onResult(extractEndSessionResponseData(AuthBrowserView.this.endSessionRequest, url));
                }
                AuthBrowserView.this.activity.runOnUiThread(AuthBrowserView.this.new BrowserViewUpdaterRunnable(BrowserRunnableMessage.CLOSE));
                return true;
            }
            if (url.equals(Uri.parse("app://app/close-iab"))) {
                AuthBrowserView.this.activity.runOnUiThread(AuthBrowserView.this.new BrowserViewUpdaterRunnable(BrowserRunnableMessage.CLOSE_BY_USER));
                return true;
            }
            if (url.equals(Uri.parse("app://app/reload-initial-url"))) {
                AuthBrowserView.this.activity.runOnUiThread(AuthBrowserView.this.new BrowserViewUpdaterRunnable(BrowserRunnableMessage.LOAD_URL));
                return true;
            }
            if (!"tel".equals(url.getScheme())) {
                return false;
            }
            webView.getContext().startActivity(new Intent("android.intent.action.VIEW", url));
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            updateLoadingState(true);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            updateLoadingState(false);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, final WebResourceRequest webResourceRequest, final android.webkit.WebResourceError webResourceError) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
            updateLoadingState(false);
            Sentry.withScope(new ScopeCallback() { // from class: ch.coop.auth.AuthBrowserView$BrowserClient$$ExternalSyntheticLambda1
                @Override // io.sentry.ScopeCallback
                public final void run(IScope iScope) {
                    AuthBrowserView.BrowserClient.this.lambda$onReceivedError$0(webResourceError, webResourceRequest, iScope);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onReceivedError$0(android.webkit.WebResourceError webResourceError, WebResourceRequest webResourceRequest, IScope iScope) {
            iScope.setFingerprint(Arrays.asList("AuthBrowserView", "WebResourceError"));
            iScope.setTag("error.type", "WebResourceError");
            iScope.setTag("error.code", String.valueOf(webResourceError.getErrorCode()));
            iScope.setTag("error.description", webResourceError.getDescription().toString());
            iScope.setTag("error.request.url", webResourceRequest.getUrl().toString());
            iScope.setTag("error.request.method", webResourceRequest.getMethod());
            Sentry.captureException(new WebResourceError());
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedHttpError(WebView webView, final WebResourceRequest webResourceRequest, final WebResourceResponse webResourceResponse) {
            super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            Sentry.withScope(new ScopeCallback() { // from class: ch.coop.auth.AuthBrowserView$BrowserClient$$ExternalSyntheticLambda2
                @Override // io.sentry.ScopeCallback
                public final void run(IScope iScope) {
                    AuthBrowserView.BrowserClient.this.lambda$onReceivedHttpError$1(webResourceResponse, webResourceRequest, iScope);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onReceivedHttpError$1(WebResourceResponse webResourceResponse, WebResourceRequest webResourceRequest, IScope iScope) {
            iScope.setFingerprint(Arrays.asList("AuthBrowserView", "HttpError"));
            iScope.setTag("error.type", "HttpError");
            iScope.setTag("error.code", String.valueOf(webResourceResponse.getStatusCode()));
            iScope.setTag("error.request.url", webResourceRequest.getUrl().toString());
            iScope.setTag("error.request.method", webResourceRequest.getMethod());
            Sentry.captureException(new HttpError());
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, final android.net.http.SslError sslError) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
            Sentry.withScope(new ScopeCallback() { // from class: ch.coop.auth.AuthBrowserView$BrowserClient$$ExternalSyntheticLambda0
                @Override // io.sentry.ScopeCallback
                public final void run(IScope iScope) {
                    AuthBrowserView.BrowserClient.this.lambda$onReceivedSslError$2(sslError, iScope);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onReceivedSslError$2(android.net.http.SslError sslError, IScope iScope) {
            iScope.setFingerprint(Arrays.asList("AuthBrowserView", "SslError"));
            iScope.setTag("error.type", "SslError");
            iScope.setTag("error.code", String.valueOf(sslError.getPrimaryError()));
            iScope.setTag("error.request.url", sslError.getUrl().toString());
            Sentry.captureException(new SslError());
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
            Boolean bool = false;
            List<AuthBrowserHttpAuthCredential> httpAuthCredentials = AuthBrowserView.this.uiOptions.getHttpAuthCredentials();
            if (httpAuthCredentials != null) {
                for (AuthBrowserHttpAuthCredential authBrowserHttpAuthCredential : httpAuthCredentials) {
                    if ("*".equals(authBrowserHttpAuthCredential.getHost()) || authBrowserHttpAuthCredential.getHost().equals(str)) {
                        if ("*".equals(authBrowserHttpAuthCredential.getRealm()) || authBrowserHttpAuthCredential.getRealm().equals(str2)) {
                            bool = true;
                            httpAuthHandler.proceed(authBrowserHttpAuthCredential.getUsername(), authBrowserHttpAuthCredential.getPassword());
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

        private void updateLoadingState(boolean z) {
            if (AuthBrowserView.this.loadingSpinner != null) {
                if (z) {
                    AuthBrowserView.this.loadingSpinner.setVisibility(0);
                } else {
                    AuthBrowserView.this.loadingSpinner.setVisibility(4);
                }
            }
        }

        private Intent extractAuthorizationResponseData(AuthorizationRequest authorizationRequest, Uri uri) {
            if (uri.getQueryParameterNames().contains("error")) {
                return AuthorizationException.fromOAuthRedirect(uri).toIntent();
            }
            AuthorizationResponse build = new AuthorizationResponse.Builder(authorizationRequest).fromUri(uri).build();
            if ((authorizationRequest.getState() == null && build.getState() != null) || (authorizationRequest.getState() != null && !authorizationRequest.getState().equals(build.getState()))) {
                return AuthorizationException.AuthorizationRequestErrors.STATE_MISMATCH.toIntent();
            }
            return build.toIntent();
        }

        private Intent extractEndSessionResponseData(EndSessionRequest endSessionRequest, Uri uri) {
            return new EndSessionResponse.Builder(endSessionRequest).setState(uri.getQueryParameter(SentryThread.JsonKeys.STATE)).build().toIntent();
        }
    }

    public static class BrowserWebChromeClient extends WebChromeClient {
        @Override // android.webkit.WebChromeClient
        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            WebView.HitTestResult hitTestResult = webView.getHitTestResult();
            String extra = hitTestResult.getExtra();
            int type = hitTestResult.getType();
            if (extra == null) {
                return false;
            }
            if (type != 7 && type != 8) {
                return false;
            }
            webView.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(extra)));
            return false;
        }
    }
}
