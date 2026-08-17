package com.scandit.datacapture.core.internal.module.source.api2;

import android.os.SystemClock;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class q0 extends Lambda implements Function1 {
    public final /* synthetic */ long a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q0(long j) {
        super(1);
        this.a = j;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Unit unit;
        com.scandit.datacapture.core.internal.module.gl.l use = (com.scandit.datacapture.core.internal.module.gl.l) obj;
        Intrinsics.checkNotNullParameter(use, "$this$use");
        long j = this.a;
        com.scandit.datacapture.core.internal.module.utils.f fVar = use.v;
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (fVar) {
            com.scandit.datacapture.core.internal.module.utils.c cVar = fVar.b;
            cVar.getClass();
            cVar.b.put(new com.scandit.datacapture.core.internal.module.utils.e(j), Long.valueOf(uptimeMillis));
            com.scandit.datacapture.core.internal.module.utils.c cVar2 = fVar.c;
            cVar2.getClass();
            cVar2.b.put(new com.scandit.datacapture.core.internal.module.utils.e(j), Long.valueOf(uptimeMillis));
            unit = Unit.INSTANCE;
        }
        return unit;
    }
}
