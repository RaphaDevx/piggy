package ch.datatrans.payment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import ch.datatrans.payment.web.WebLoadingIndicator;
import java.util.Arrays;
import java.util.Iterator;
import javax.net.ssl.SSLException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* loaded from: classes3.dex */
public final class K9 extends WebViewClient {
    public final Ee a;
    public final WebLoadingIndicator b;
    public final boolean c;
    public String d;
    public int e;

    public K9(Ee viewModel, WebLoadingIndicator loadIndicator, boolean z) {
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(loadIndicator, "loadIndicator");
        this.a = viewModel;
        this.b = loadIndicator;
        this.c = z;
    }

    public final void a(WebView webView) {
        String str = this.d;
        if (str != null) {
            float f = this.e;
            if (f > 0.0f) {
                float f2 = f / f;
                float coerceAtLeast = RangesKt.coerceAtLeast(f2, 1.5f);
                String str2 = "width=" + f + ", minimum-scale=" + RangesKt.coerceAtMost(f2, 0.5f) + ", initial-scale=" + f2 + ", maximum-scale=" + coerceAtLeast;
                if (str != null) {
                    str = str2 + ", " + str;
                } else {
                    str = str2;
                }
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("\n\t\tvp = document.getElementsByName('viewport');\n\t\tif (vp.length == 0) {\n\t\t\te = document.createElement('meta');\n\t\t\te.name = 'viewport';\n\t\t\te.content = '%s';\n\t\t\tdocument.getElementsByTagName('head')[0].appendChild(e);\n\t\t} else {\n\t\t\te = vp.item(0);\n\t\t\te.content = '%s';\n\t\t}", Arrays.copyOf(new Object[]{str, str}, 2));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            webView.evaluateJavascript(format, null);
        }
    }

    @Override // android.webkit.WebViewClient
    public final void onLoadResource(WebView view, String url) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(url, "url");
        synchronized (this) {
            if (!this.a.b) {
                if (a(url)) {
                    view.stopLoading();
                } else {
                    super.onLoadResource(view, url);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView view, String url) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(url, "url");
        synchronized (this) {
            a(view);
            Intrinsics.checkNotNullParameter(url, "<this>");
            Uri parse = Uri.parse(url);
            Intrinsics.checkNotNullParameter(parse, "<this>");
            String scheme = parse.getScheme();
            if (scheme != null && ((StringsKt.equals(scheme, "http", true) || StringsKt.equals(scheme, "https", true)) && !this.a.b)) {
                this.b.c();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // android.webkit.WebViewClient
    public final void onPageStarted(WebView view, String url, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(url, "url");
        synchronized (this) {
            if (!this.a.b) {
                if (!a(url)) {
                    Intrinsics.checkNotNullParameter(url, "<this>");
                    Uri parse = Uri.parse(url);
                    Intrinsics.checkNotNullParameter(parse, "<this>");
                    String scheme = parse.getScheme();
                    if (scheme != null && (StringsKt.equals(scheme, "http", true) || StringsKt.equals(scheme, "https", true))) {
                        this.b.d();
                    }
                }
                view.stopLoading();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedError(WebView view, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        String str;
        Uri url;
        String uri;
        CharSequence description;
        Intrinsics.checkNotNullParameter(view, "view");
        int errorCode = webResourceError != null ? webResourceError.getErrorCode() : -1;
        String str2 = "";
        if (webResourceError == null || (description = webResourceError.getDescription()) == null || (str = description.toString()) == null) {
            str = "";
        }
        if (webResourceRequest != null && (url = webResourceRequest.getUrl()) != null && (uri = url.toString()) != null) {
            str2 = uri;
        }
        onReceivedError(view, errorCode, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(handler, "handler");
        Intrinsics.checkNotNullParameter(error, "error");
        this.b.a();
        handler.cancel();
        SSLException e = new SSLException("Not trusted server certificate." + error);
        Ee ee = this.a;
        ee.getClass();
        Intrinsics.checkNotNullParameter(e, "e");
        ee.e.postValue(Unit.INSTANCE);
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        Uri url = webResourceRequest != null ? webResourceRequest.getUrl() : null;
        if (this.c) {
            return a(url, webView);
        }
        if (url != null) {
            String uri = url.toString();
            Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
            if (a(uri)) {
                return true;
            }
        }
        return super.shouldOverrideUrlLoading(webView, webResourceRequest);
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedError(WebView view, int i, String description, String failingUrl) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(failingUrl, "failingUrl");
        synchronized (this) {
            if (!this.a.b && Intrinsics.areEqual(failingUrl, view.getUrl())) {
                this.b.a();
                view.loadUrl("about:blank");
                Ee ee = this.a;
                Object e = i == -11 ? new SSLException(description) : new D7(description);
                ee.getClass();
                Intrinsics.checkNotNullParameter(e, "e");
                if (e instanceof SSLException) {
                    ee.e.postValue(Unit.INSTANCE);
                } else {
                    ee.d.postValue(Unit.INSTANCE);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.c) {
            if (str == null) {
                return false;
            }
            return a(Uri.parse(str), webView);
        }
        if (str == null || !a(str)) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        return true;
    }

    public final boolean a(String url) {
        boolean z;
        Ee ee = this.a;
        ee.getClass();
        Intrinsics.checkNotNullParameter(url, "url");
        Ae ae = ee.a;
        if (ae != null) {
            Iterator it = ae.f().iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                Y9 y9 = (Y9) it.next();
                if (y9.a(url)) {
                    y9.c(url);
                    boolean b = y9.b(url);
                    ee.b = b;
                    if (b) {
                        ee.c.postValue(Unit.INSTANCE);
                    }
                    z = true;
                }
            }
            if (z) {
                this.b.a();
            }
            return z;
        }
        throw new IllegalArgumentException("Required value was null.");
    }

    public static boolean a(Uri uri, WebView webView) {
        String host;
        String scheme;
        if (uri != null && webView != null && (((host = uri.getHost()) != null && StringsKt.contains$default((CharSequence) host, (CharSequence) "ideal", false, 2, (Object) null)) || ((scheme = uri.getScheme()) != null && StringsKt.contains$default((CharSequence) scheme, (CharSequence) "ideal", false, 2, (Object) null)))) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(uri);
                Context context = webView.getContext();
                Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
                ((Activity) context).startActivity(intent);
                return true;
            } catch (ActivityNotFoundException unused) {
                webView.loadUrl(uri.toString());
            }
        }
        return false;
    }
}
