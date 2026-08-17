package com.tealium.internal.j;

import android.webkit.WebView;
import com.tealium.internal.listeners.WebViewLoadedListener;

/* compiled from: WebViewLoadMessenger.java */
/* loaded from: classes2.dex */
public final class y extends n<WebViewLoadedListener> {
    private final WebView b;
    private final boolean c;

    public y(WebView webView, boolean z) {
        super(WebViewLoadedListener.class);
        this.b = webView;
        this.c = z;
    }

    @Override // com.tealium.internal.j.n
    public void a(WebViewLoadedListener webViewLoadedListener) {
        webViewLoadedListener.onWebViewLoad(this.b, this.c);
    }
}
