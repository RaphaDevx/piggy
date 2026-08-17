package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.view.View;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class U0 extends Lambda implements Function0 {
    public final /* synthetic */ Z0 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public U0(Z0 z0) {
        super(0);
        this.a = z0;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Z0 z0 = this.a;
        z1 z1Var = z0.e;
        T0 action = new T0(z0);
        z1Var.getClass();
        Intrinsics.checkNotNullParameter(action, "action");
        Context context = z1Var.a.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        return (View) action.invoke(context);
    }
}
