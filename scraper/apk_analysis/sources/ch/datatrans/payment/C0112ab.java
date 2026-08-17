package ch.datatrans.payment;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* renamed from: ch.datatrans.payment.ab, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0112ab implements Y9 {
    public final String a;
    public final Be b;

    public C0112ab(String successUrl, Be listener) {
        Intrinsics.checkNotNullParameter(successUrl, "successUrl");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.a = successUrl;
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
        Map a = vd.a(url);
        this.b.a((String) a.get("uppTransactionId"), a);
    }
}
