package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.view.ViewGroup;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class J extends Lambda implements Function1 {
    public final /* synthetic */ K a;
    public final /* synthetic */ int b;
    public final /* synthetic */ int c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public J(K k, int i, int i2) {
        super(1);
        this.a = k;
        this.b = i;
        this.c = i2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ViewGroup it = (ViewGroup) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        com.scandit.datacapture.barcode.internal.module.ui.e eVar = this.a.c;
        if (eVar != null) {
            eVar.b = this.b;
            eVar.b();
        }
        com.scandit.datacapture.barcode.internal.module.ui.e eVar2 = this.a.c;
        if (eVar2 != null) {
            eVar2.a = this.c;
            eVar2.b();
        }
        return Unit.INSTANCE;
    }
}
