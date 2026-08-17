package ch.datatrans.payment;

import android.app.Dialog;
import android.content.Context;
import android.webkit.WebView;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class me extends Dialog {
    public final /* synthetic */ ue a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public me(ue ueVar, Context context, int i) {
        super(context, i);
        this.a = ueVar;
    }

    @Override // android.app.Dialog
    public final void onBackPressed() {
        ue ueVar = this.a;
        WebView webView = ueVar.e;
        WebView webView2 = null;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView = null;
        }
        if (webView.canGoBack()) {
            WebView webView3 = ueVar.e;
            if (webView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
            } else {
                webView2 = webView3;
            }
            webView2.goBack();
            return;
        }
        if (ueVar.c()) {
            ueVar.a();
            return;
        }
        WebView webView4 = ueVar.e;
        if (webView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        } else {
            webView2 = webView4;
        }
        webView2.loadUrl("javascript:history.go(-1)");
    }
}
