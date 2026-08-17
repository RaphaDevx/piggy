package com.scandit.datacapture.tools.internal.module.mapmaker;

import java.lang.ref.ReferenceQueue;

/* loaded from: classes2.dex */
public final class y implements InterfaceC0626n {
    public static final y a = new y();

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final InterfaceC0625m a(r rVar, InterfaceC0625m interfaceC0625m, InterfaceC0625m interfaceC0625m2) {
        A a2 = (A) rVar;
        z zVar = (z) interfaceC0625m;
        z zVar2 = (z) interfaceC0625m2;
        int i = r.g;
        if (zVar.getValue() == null) {
            return null;
        }
        ReferenceQueue referenceQueue = a2.h;
        z zVar3 = new z(zVar.a, zVar.b, zVar2);
        zVar3.d = zVar.d.a(referenceQueue, zVar3);
        return zVar3;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final InterfaceC0625m a(r rVar, Object obj, int i, InterfaceC0625m interfaceC0625m) {
        return new z(obj, i, (z) interfaceC0625m);
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final void a(r rVar, InterfaceC0625m interfaceC0625m, Object obj) {
        z zVar = (z) interfaceC0625m;
        ReferenceQueue referenceQueue = ((A) rVar).h;
        K k = zVar.d;
        zVar.d = new L(referenceQueue, obj, zVar);
        k.clear();
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final u a() {
        return u.b;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final r a(N n, int i) {
        return new A(n, i);
    }
}
