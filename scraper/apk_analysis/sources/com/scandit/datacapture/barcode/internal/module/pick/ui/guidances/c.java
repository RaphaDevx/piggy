package com.scandit.datacapture.barcode.internal.module.pick.ui.guidances;

import android.view.View;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function1 {
    public final /* synthetic */ g a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(g gVar) {
        super(1);
        this.a = gVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        g it = (g) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        g gVar = this.a;
        if (gVar.c) {
            gVar.removeView((View) gVar.b.getValue());
            this.a.c = false;
        }
        return Unit.INSTANCE;
    }
}
