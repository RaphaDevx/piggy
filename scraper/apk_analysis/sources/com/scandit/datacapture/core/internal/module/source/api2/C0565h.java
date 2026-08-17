package com.scandit.datacapture.core.internal.module.source.api2;

import kotlin.jvm.functions.Function1;

/* renamed from: com.scandit.datacapture.core.internal.module.source.api2.h, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0565h implements Q {
    public boolean a = true;
    public final /* synthetic */ C0573p b;
    public final /* synthetic */ i0 c;
    public final /* synthetic */ Function1 d;

    public C0565h(C0573p c0573p, i0 i0Var, Function1 function1) {
        this.b = c0573p;
        this.c = i0Var;
        this.d = function1;
    }

    public final void a() {
        if (this.a) {
            C0573p c0573p = this.b;
            c0573p.a(false);
            InterfaceC0558b0 interfaceC0558b0 = c0573p.l;
            if (interfaceC0558b0 != null) {
                ((d0) interfaceC0558b0).a();
            }
            c0573p.s = false;
            this.b.b();
            this.d.invoke(Boolean.FALSE);
        }
    }
}
