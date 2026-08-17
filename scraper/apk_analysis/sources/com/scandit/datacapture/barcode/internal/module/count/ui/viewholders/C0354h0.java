package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.view.View;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.h0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0354h0 extends Lambda implements Function0 {
    public final /* synthetic */ C0362l0 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0354h0(C0362l0 c0362l0) {
        super(0);
        this.a = c0362l0;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        C0362l0 c0362l0 = this.a;
        z1 z1Var = c0362l0.f;
        C0352g0 action = new C0352g0(c0362l0);
        z1Var.getClass();
        Intrinsics.checkNotNullParameter(action, "action");
        Context context = z1Var.a.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        return (View) action.invoke(context);
    }
}
