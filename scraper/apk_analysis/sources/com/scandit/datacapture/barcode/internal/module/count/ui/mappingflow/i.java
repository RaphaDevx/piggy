package com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow;

import android.content.Context;
import android.view.View;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.z1;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class i extends Lambda implements Function0 {
    public final /* synthetic */ j a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(j jVar) {
        super(0);
        this.a = jVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        j jVar = this.a;
        z1 z1Var = jVar.c;
        h action = new h(jVar);
        z1Var.getClass();
        Intrinsics.checkNotNullParameter(action, "action");
        Context context = z1Var.a.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        return (View) action.invoke(context);
    }
}
