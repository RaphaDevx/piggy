package com.scandit.datacapture.barcode.internal.module.count.ui.cluster;

import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class y extends Lambda implements Function1 {
    public final /* synthetic */ B a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y(B b) {
        super(1);
        this.a = b;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        p it = (p) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        p pVar = (p) this.a.f.b();
        List items = CollectionsKt.emptyList();
        pVar.getClass();
        Intrinsics.checkNotNullParameter(items, "items");
        pVar.a = items;
        pVar.invalidate();
        return Unit.INSTANCE;
    }
}
