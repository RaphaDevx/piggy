package com.scandit.datacapture.tools.internal.module.mapmaker;

import java.lang.ref.ReferenceQueue;

/* loaded from: classes2.dex */
public final class G implements InterfaceC0626n {
    public static final G a = new G();

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final InterfaceC0625m a(r rVar, InterfaceC0625m interfaceC0625m, InterfaceC0625m interfaceC0625m2) {
        I i = (I) rVar;
        H h = (H) interfaceC0625m;
        H h2 = (H) interfaceC0625m2;
        if (h.get() != 0) {
            int i2 = r.g;
            if (h.c.get() != null) {
                ReferenceQueue referenceQueue = i.h;
                ReferenceQueue referenceQueue2 = i.i;
                H h3 = new H(referenceQueue, h.get(), h.a, h2);
                h3.c = h.c.a(referenceQueue2, h3);
                return h3;
            }
        }
        return null;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final InterfaceC0625m a(r rVar, Object obj, int i, InterfaceC0625m interfaceC0625m) {
        return new H(((I) rVar).h, obj, i, (H) interfaceC0625m);
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final void a(r rVar, InterfaceC0625m interfaceC0625m, Object obj) {
        H h = (H) interfaceC0625m;
        ReferenceQueue referenceQueue = ((I) rVar).i;
        K k = h.c;
        h.c = new L(referenceQueue, obj, h);
        k.clear();
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final u a() {
        return u.b;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0626n
    public final r a(N n, int i) {
        return new I(n, i);
    }
}
