package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.view.View;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.m, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0363m extends Lambda implements Function0 {
    public final /* synthetic */ C0371q a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0363m(C0371q c0371q) {
        super(0);
        this.a = c0371q;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        C0371q c0371q = this.a;
        z1 z1Var = c0371q.g;
        C0361l action = new C0361l(c0371q);
        z1Var.getClass();
        Intrinsics.checkNotNullParameter(action, "action");
        Context context = z1Var.a.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        return (View) action.invoke(context);
    }
}
