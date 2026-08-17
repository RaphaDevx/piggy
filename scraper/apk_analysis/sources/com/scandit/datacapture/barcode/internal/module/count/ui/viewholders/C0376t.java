package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.view.View;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.t, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0376t extends Lambda implements Function0 {
    public final /* synthetic */ C0384x a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0376t(C0384x c0384x) {
        super(0);
        this.a = c0384x;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        C0384x c0384x = this.a;
        z1 z1Var = c0384x.g;
        C0374s action = new C0374s(c0384x);
        z1Var.getClass();
        Intrinsics.checkNotNullParameter(action, "action");
        Context context = z1Var.a.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        return (View) action.invoke(context);
    }
}
