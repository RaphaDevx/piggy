package com.scandit.datacapture.barcode.internal.module.find.ui;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class V extends Lambda implements Function1 {
    public final /* synthetic */ u0 a;
    public final /* synthetic */ int b;
    public final /* synthetic */ int c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public V(u0 u0Var, int i, int i2) {
        super(1);
        this.a = u0Var;
        this.b = i;
        this.c = i2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        com.scandit.datacapture.barcode.internal.module.ui.e it = (com.scandit.datacapture.barcode.internal.module.ui.e) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        com.scandit.datacapture.barcode.internal.module.ui.e eVar = this.a.h;
        eVar.a = this.b;
        eVar.b();
        com.scandit.datacapture.barcode.internal.module.ui.e eVar2 = this.a.h;
        eVar2.b = this.c;
        eVar2.b();
        return Unit.INSTANCE;
    }
}
