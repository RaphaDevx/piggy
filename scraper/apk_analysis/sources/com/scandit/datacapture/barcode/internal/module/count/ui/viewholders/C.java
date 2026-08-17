package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.view.View;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class C extends Lambda implements Function0 {
    public final /* synthetic */ F a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C(F f) {
        super(0);
        this.a = f;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        F f = this.a;
        z1 z1Var = f.e;
        B action = new B(f);
        z1Var.getClass();
        Intrinsics.checkNotNullParameter(action, "action");
        Context context = z1Var.a.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        return (View) action.invoke(context);
    }
}
