package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* loaded from: classes3.dex */
public final class O9 implements Y9 {
    public final lg a;

    public O9(lg listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.a = listener;
    }

    @Override // ch.datatrans.payment.Y9
    public final boolean a(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return StringsKt.startsWith(url, "https://universal.postfinance.ch/epayment/", true) || StringsKt.startsWith(url, "https://universal-test.postfinance.ch/epayment/", true) || StringsKt.startsWith(url, "https://universal-t2.postfinance.ch/epayment/", true);
    }

    @Override // ch.datatrans.payment.Y9
    public final boolean b(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return false;
    }

    @Override // ch.datatrans.payment.Y9
    public final void c(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        Jc jc = (Jc) this.a;
        jc.getClass();
        Intrinsics.checkNotNullParameter(url, "url");
        jc.j.postValue(url);
    }
}
