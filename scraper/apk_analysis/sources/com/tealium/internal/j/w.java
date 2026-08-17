package com.tealium.internal.j;

import android.webkit.WebView;
import com.tealium.internal.listeners.WebViewCrashedListener;

/* compiled from: WebViewCrashedMessenger.java */
/* loaded from: classes2.dex */
public final class w extends n<WebViewCrashedListener> {
    private final WebView b;

    public w(WebView webView) {
        super(WebViewCrashedListener.class);
        this.b = webView;
    }

    @Override // com.tealium.internal.j.n
    public void a(WebViewCrashedListener webViewCrashedListener) {
        webViewCrashedListener.onWebViewCrashed(this.b);
    }
}
