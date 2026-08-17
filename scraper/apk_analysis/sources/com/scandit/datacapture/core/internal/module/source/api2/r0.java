package com.scandit.datacapture.core.internal.module.source.api2;

import android.os.SystemClock;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

/* loaded from: classes2.dex */
public final class r0 extends Lambda implements Function1 {
    public final /* synthetic */ Ref.LongRef a;
    public final /* synthetic */ long b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r0(Ref.LongRef longRef, long j) {
        super(1);
        this.a = longRef;
        this.b = j;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Unit unit;
        com.scandit.datacapture.core.internal.module.gl.l use = (com.scandit.datacapture.core.internal.module.gl.l) obj;
        Intrinsics.checkNotNullParameter(use, "$this$use");
        Ref.LongRef longRef = this.a;
        long j = this.b;
        com.scandit.datacapture.core.internal.module.utils.f fVar = use.v;
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (fVar) {
            fVar.b.a(j, uptimeMillis);
            unit = Unit.INSTANCE;
        }
        fVar.a();
        longRef.element = use.v.e;
        return unit;
    }
}
