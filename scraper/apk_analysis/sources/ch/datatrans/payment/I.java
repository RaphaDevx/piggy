package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class I {
    public final String a;
    public final String b;

    public I(String alias, String maskedCardNo) {
        Intrinsics.checkNotNullParameter(alias, "alias");
        Intrinsics.checkNotNullParameter(maskedCardNo, "maskedCardNo");
        this.a = alias;
        this.b = maskedCardNo;
    }
}
