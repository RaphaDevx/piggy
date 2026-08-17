package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.view.View;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class I extends Lambda implements Function0 {
    public final /* synthetic */ K a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public I(K k) {
        super(0);
        this.a = k;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        K k = this.a;
        z1 z1Var = k.d;
        H action = new H(k);
        z1Var.getClass();
        Intrinsics.checkNotNullParameter(action, "action");
        Context context = z1Var.a.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        return (View) action.invoke(context);
    }
}
