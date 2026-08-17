package ch.datatrans.payment;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class aa extends t4 {
    public final Function0 c;
    public final Function0 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public aa(int i, int i2, Function0 retryAction, Function0 cancelAction) {
        super(i, i2);
        Intrinsics.checkNotNullParameter(retryAction, "retryAction");
        Intrinsics.checkNotNullParameter(cancelAction, "cancelAction");
        this.c = retryAction;
        this.d = cancelAction;
    }
}
