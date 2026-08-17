package com.scandit.datacapture.core.internal.module.source.dynamicresolution;

import com.scandit.datacapture.core.source.FrameSource;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class z {
    public final Function3 a;
    public final Function3 b;
    public boolean c;
    public u d;
    public y e;
    public q f;

    public z(h start, i stop) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(stop, "stop");
        this.a = start;
        this.b = stop;
        this.d = t.a;
        this.e = x.a;
        this.f = n.a;
    }

    public final synchronized void a(m dynamicResolutionSettings) {
        Intrinsics.checkNotNullParameter(dynamicResolutionSettings, "dynamicResolutionSettings");
        w wVar = new w(dynamicResolutionSettings);
        if (!Intrinsics.areEqual(wVar, this.e)) {
            this.e = wVar;
            if (!this.c) {
                a();
            }
        }
    }

    public final synchronized void b() {
        v vVar = v.a;
        if (!Intrinsics.areEqual(vVar, this.e)) {
            this.e = vVar;
            if (!this.c) {
                a();
            }
        }
    }

    public final synchronized void c() {
        if (this.c) {
            return;
        }
        this.b.invoke(this.e.a(), this.f.a(), this.d.a());
        this.c = true;
        this.f = n.a;
    }

    public final synchronized void a(FrameSource frameSource) {
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
        p pVar = new p(frameSource);
        if (!Intrinsics.areEqual(pVar, this.f)) {
            this.f = pVar;
            if (!this.c) {
                a();
            }
        }
    }

    public final void a() {
        u uVar = this.d;
        r rVar = uVar instanceof r ? (r) uVar : null;
        if (rVar == null) {
            a(this);
            return;
        }
        y yVar = this.e;
        w wVar = yVar instanceof w ? (w) yVar : null;
        if (wVar == null) {
            a(this);
            return;
        }
        q qVar = this.f;
        o oVar = qVar instanceof o ? (o) qVar : null;
        if (oVar == null) {
            a(this);
        } else {
            this.a.invoke(wVar.a, oVar.a, rVar.a);
        }
    }

    public static void a(z zVar) {
        u uVar = zVar.d;
        zVar.b.invoke(zVar.e.a(), zVar.f.a(), uVar.a());
    }
}
