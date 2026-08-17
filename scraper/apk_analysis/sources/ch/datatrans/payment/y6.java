package ch.datatrans.payment;

import android.content.Context;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class y6 implements InterfaceC0235ud {
    public final C9 a;

    public y6(C9 lambda) {
        Intrinsics.checkNotNullParameter(lambda, "lambda");
        this.a = lambda;
    }

    @Override // ch.datatrans.payment.InterfaceC0235ud
    public final String a(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (String) this.a.invoke(context);
    }
}
