package com.scandit.datacapture.barcode.internal.module.count.ui.cluster;

import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class z extends Lambda implements Function1 {
    public final /* synthetic */ B a;
    public final /* synthetic */ List b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z(B b, List list) {
        super(1);
        this.a = b;
        this.b = list;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        p it = (p) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        p pVar = (p) this.a.f.b();
        List items = this.b;
        pVar.getClass();
        Intrinsics.checkNotNullParameter(items, "items");
        pVar.a = items;
        pVar.invalidate();
        j jVar = (j) this.a.h.b();
        List items2 = this.b;
        jVar.getClass();
        Intrinsics.checkNotNullParameter(items2, "items");
        jVar.a = items2;
        jVar.invalidate();
        return Unit.INSTANCE;
    }
}
