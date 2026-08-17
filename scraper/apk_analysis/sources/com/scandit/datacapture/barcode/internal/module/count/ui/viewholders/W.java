package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.view.ViewGroup;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class W extends Lambda implements Function1 {
    public final /* synthetic */ X a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ String c;
    public final /* synthetic */ Function0 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public W(X x, boolean z, String str, Function0 function0) {
        super(1);
        this.a = x;
        this.b = z;
        this.c = str;
        this.d = function0;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ViewGroup it = (ViewGroup) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        X x = this.a;
        boolean z = this.b;
        String str = this.c;
        com.scandit.datacapture.barcode.internal.module.count.ui.k kVar = x.b;
        if (kVar != null) {
            kVar.setVisibility(z ? 0 : 4);
            kVar.b.setText(str);
        }
        this.d.invoke();
        return Unit.INSTANCE;
    }
}
