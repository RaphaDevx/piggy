package com.scandit.datacapture.core.internal.module.utils;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class f {
    public final Function1 a;
    public final c b;
    public final c c;
    public final d d;
    public long e;

    public f() {
        b onDelayUpdated = b.a;
        Intrinsics.checkNotNullParameter(onDelayUpdated, "onDelayUpdated");
        this.a = onDelayUpdated;
        this.b = new c(0.95d);
        this.c = new c(0.95d);
        this.d = new d(0.95d);
    }

    public final void a() {
        synchronized (this) {
            c cVar = this.b;
            if (cVar.d) {
                c cVar2 = this.c;
                if (cVar2.d) {
                    d dVar = this.d;
                    long j = ((long) cVar.c) - ((long) cVar2.c);
                    if (dVar.c) {
                        double d = dVar.b;
                        double d2 = dVar.a;
                        dVar.b = ((1.0d - d2) * j) + (d * d2);
                    } else {
                        dVar.b = j;
                        dVar.c = true;
                    }
                    long j2 = (long) dVar.b;
                    if (this.e != j2) {
                        this.e = j2;
                        this.a.invoke(Long.valueOf(j2));
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
    }
}
