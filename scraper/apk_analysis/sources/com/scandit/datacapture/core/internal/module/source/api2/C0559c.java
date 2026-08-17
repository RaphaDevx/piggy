package com.scandit.datacapture.core.internal.module.source.api2;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.source.api2.c, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0559c implements InterfaceC0556a0 {
    public final Function1 a;
    public final /* synthetic */ C0573p b;

    public C0559c(C0573p c0573p, Function1 completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        this.b = c0573p;
        this.a = completion;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.api2.InterfaceC0556a0
    public final void a(long j) {
        p0 p0Var = this.b.j;
        if (p0Var != null) {
            ((u0) p0Var).a.use(new q0(j));
        }
        this.a.invoke(Boolean.TRUE);
        this.b.s = true;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.api2.InterfaceC0556a0
    public final void a(G captureResult) {
        Intrinsics.checkNotNullParameter(captureResult, "captureResult");
        C0573p c0573p = this.b;
        Integer a = captureResult.a();
        c0573p.o = a != null ? a.intValue() : 0;
        h0 h0Var = (h0) this.b.h;
        h0Var.getClass();
        Intrinsics.checkNotNullParameter(captureResult, "captureResult");
        h0Var.b.set(captureResult);
        C0573p c0573p2 = this.b;
        if (c0573p2.s) {
            c0573p2.q.a(captureResult);
            m0 m0Var = this.b.q;
            if (m0Var.l.hashCode() != m0Var.k) {
                C0573p.b(this.b);
            }
        }
    }
}
