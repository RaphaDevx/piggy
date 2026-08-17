package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.view.View;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class u1 extends Lambda implements Function0 {
    public final /* synthetic */ y1 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u1(y1 y1Var) {
        super(0);
        this.a = y1Var;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        y1 y1Var = this.a;
        z1 z1Var = y1Var.c;
        t1 action = new t1(y1Var);
        z1Var.getClass();
        Intrinsics.checkNotNullParameter(action, "action");
        Context context = z1Var.a.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        return (View) action.invoke(context);
    }
}
