package com.scandit.datacapture.tools.internal.module.mapmaker;

/* loaded from: classes2.dex */
public final class v implements InterfaceC0626n {
    public static final v a = new v();

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final InterfaceC0625m a(r rVar, InterfaceC0625m interfaceC0625m, InterfaceC0625m interfaceC0625m2) {
        w wVar = (w) interfaceC0625m;
        w wVar2 = new w(wVar.a, wVar.b, (w) interfaceC0625m2);
        wVar2.d = wVar.d;
        return wVar2;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final InterfaceC0625m a(r rVar, Object obj, int i, InterfaceC0625m interfaceC0625m) {
        return new w(obj, i, (w) interfaceC0625m);
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final void a(r rVar, InterfaceC0625m interfaceC0625m, Object obj) {
        ((w) interfaceC0625m).d = obj;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final u a() {
        return u.a;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final r a(N n, int i) {
        return new x(n, i);
    }
}
