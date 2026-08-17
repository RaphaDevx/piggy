package com.tealium.internal.j;

import android.webkit.WebView;
import com.tealium.internal.listeners.WebViewCreatedListener;

/* compiled from: WebViewCreatedMessenger.java */
/* loaded from: classes2.dex */
public class x extends n<WebViewCreatedListener> {
    private final WebView b;

    public x(WebView webView) {
        super(WebViewCreatedListener.class);
        this.b = webView;
        if (webView == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override // com.tealium.internal.j.n
    public void a(WebViewCreatedListener webViewCreatedListener) {
        webViewCreatedListener.onWebViewCreated(this.b);
    }
}
