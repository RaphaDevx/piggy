package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.view.View;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.o0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0368o0 extends Lambda implements Function0 {
    public final /* synthetic */ C0377t0 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0368o0(C0377t0 c0377t0) {
        super(0);
        this.a = c0377t0;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        C0377t0 c0377t0 = this.a;
        z1 z1Var = c0377t0.g;
        C0366n0 action = new C0366n0(c0377t0);
        z1Var.getClass();
        Intrinsics.checkNotNullParameter(action, "action");
        Context context = z1Var.a.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        return (View) action.invoke(context);
    }
}
