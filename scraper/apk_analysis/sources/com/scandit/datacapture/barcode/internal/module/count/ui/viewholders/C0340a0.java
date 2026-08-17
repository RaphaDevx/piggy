package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.view.View;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.a0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0340a0 extends Lambda implements Function0 {
    public final /* synthetic */ C0348e0 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0340a0(C0348e0 c0348e0) {
        super(0);
        this.a = c0348e0;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        C0348e0 c0348e0 = this.a;
        z1 z1Var = c0348e0.f;
        Z action = new Z(c0348e0);
        z1Var.getClass();
        Intrinsics.checkNotNullParameter(action, "action");
        Context context = z1Var.a.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        return (View) action.invoke(context);
    }
}
