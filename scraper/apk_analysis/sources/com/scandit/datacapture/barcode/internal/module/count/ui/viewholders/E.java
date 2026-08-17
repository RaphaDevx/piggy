package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.view.ViewGroup;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class E extends Lambda implements Function1 {
    public final /* synthetic */ F a;
    public final /* synthetic */ int b;
    public final /* synthetic */ Function0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public E(F f, int i, Function0 function0) {
        super(1);
        this.a = f;
        this.b = i;
        this.c = function0;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ViewGroup it = (ViewGroup) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        com.scandit.datacapture.barcode.internal.module.ui.b bVar = this.a.d;
        if (bVar != null) {
            bVar.a(this.b);
        }
        this.c.invoke();
        return Unit.INSTANCE;
    }
}
