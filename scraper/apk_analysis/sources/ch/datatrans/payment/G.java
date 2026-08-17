package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* loaded from: classes3.dex */
public final class G implements Y9 {
    public final String a;
    public final Be b;

    public G(String cancelUrl, Be listener) {
        Intrinsics.checkNotNullParameter(cancelUrl, "cancelUrl");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.a = cancelUrl;
        this.b = listener;
    }

    @Override // ch.datatrans.payment.Y9
    public final boolean a(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return StringsKt.startsWith(url, this.a, true);
    }

    @Override // ch.datatrans.payment.Y9
    public final boolean b(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return true;
    }

    @Override // ch.datatrans.payment.Y9
    public final void c(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.b.c();
    }
}
