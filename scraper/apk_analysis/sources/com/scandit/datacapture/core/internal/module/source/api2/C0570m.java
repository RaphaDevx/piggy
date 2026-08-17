package com.scandit.datacapture.core.internal.module.source.api2;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.source.api2.m, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0570m implements InterfaceC0579w {
    public final /* synthetic */ C0573p a;
    public final /* synthetic */ Function1 b;
    public final /* synthetic */ p0 c;

    public C0570m(C0573p c0573p, Function1 function1, p0 p0Var) {
        this.a = c0573p;
        this.b = function1;
        this.c = p0Var;
    }

    public final void a(K result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (Intrinsics.areEqual(result, I.a)) {
            this.a.b();
            this.b.invoke(Boolean.FALSE);
        } else if (result instanceof J) {
            C0573p c0573p = this.a;
            if (c0573p.k == null) {
                this.b.invoke(Boolean.FALSE);
            } else {
                c0573p.l = ((J) result).a;
                this.b.invoke(Boolean.TRUE);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x022f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(android.media.Image r34) {
        /*
            Method dump skipped, instructions count: 754
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.module.source.api2.C0570m.a(android.media.Image):void");
    }
}
