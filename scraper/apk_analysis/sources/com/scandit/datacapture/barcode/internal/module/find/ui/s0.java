package com.scandit.datacapture.barcode.internal.module.find.ui;

import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class s0 extends Lambda implements Function1 {
    public final /* synthetic */ u0 a;
    public final /* synthetic */ List b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s0(u0 u0Var, ArrayList arrayList) {
        super(1);
        this.a = u0Var;
        this.b = arrayList;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        C0390a it = (C0390a) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        this.a.d.a((ArrayList) this.b);
        return Unit.INSTANCE;
    }
}
