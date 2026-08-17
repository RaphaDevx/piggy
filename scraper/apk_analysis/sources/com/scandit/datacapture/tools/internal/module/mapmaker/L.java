package com.scandit.datacapture.tools.internal.module.mapmaker;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* loaded from: classes2.dex */
public final class L extends WeakReference implements K {
    public final InterfaceC0625m a;

    public L(ReferenceQueue referenceQueue, Object obj, InterfaceC0625m interfaceC0625m) {
        super(obj, referenceQueue);
        this.a = interfaceC0625m;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.K
    public final InterfaceC0625m a() {
        return this.a;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.K
    public final K a(ReferenceQueue referenceQueue, J j) {
        return new L(referenceQueue, get(), j);
    }
}
