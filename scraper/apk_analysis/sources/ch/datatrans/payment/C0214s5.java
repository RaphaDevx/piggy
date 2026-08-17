package ch.datatrans.payment;

import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* renamed from: ch.datatrans.payment.s5, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0214s5 implements Y9 {
    public final String a;
    public final Be b;

    public C0214s5(String url, Be listener) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.a = url;
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
        this.b.a(null, MapsKt.toMutableMap(vd.a(url)));
    }
}
