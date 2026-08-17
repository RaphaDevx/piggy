package com.scandit.datacapture.barcode.internal.module.count.ui.mode;

import com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.g;
import com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.j;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function0 {
    public final /* synthetic */ d a;
    public final /* synthetic */ b b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(d dVar, b bVar) {
        super(0);
        this.a = dVar;
        this.b = bVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        j jVar = (j) this.a.b;
        jVar.c.a(jVar.b, g.a);
        this.a.a(new b(this.b.a, com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.a.b));
        return Unit.INSTANCE;
    }
}
