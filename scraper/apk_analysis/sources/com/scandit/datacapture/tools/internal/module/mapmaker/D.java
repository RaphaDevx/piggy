package com.scandit.datacapture.tools.internal.module.mapmaker;

/* loaded from: classes2.dex */
public final class D implements InterfaceC0626n {
    public static final D a = new D();

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final InterfaceC0625m a(r rVar, InterfaceC0625m interfaceC0625m, InterfaceC0625m interfaceC0625m2) {
        F f = (F) rVar;
        E e = (E) interfaceC0625m;
        E e2 = (E) interfaceC0625m2;
        if (e.get() == 0) {
            return null;
        }
        E e3 = new E(f.h, e.get(), e.a, e2);
        e3.c = e.c;
        return e3;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final InterfaceC0625m a(r rVar, Object obj, int i, InterfaceC0625m interfaceC0625m) {
        return new E(((F) rVar).h, obj, i, (E) interfaceC0625m);
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final void a(r rVar, InterfaceC0625m interfaceC0625m, Object obj) {
        ((E) interfaceC0625m).c = obj;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final u a() {
        return u.a;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final r a(N n, int i) {
        return new F(n, i);
    }
}
