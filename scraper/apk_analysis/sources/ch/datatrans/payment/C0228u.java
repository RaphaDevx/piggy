package ch.datatrans.payment;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.u, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0228u extends t4 {
    public final Function0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0228u(int i, int i2, Function0 retryAction) {
        super(i, i2);
        Intrinsics.checkNotNullParameter(retryAction, "retryAction");
        this.c = retryAction;
    }
}
