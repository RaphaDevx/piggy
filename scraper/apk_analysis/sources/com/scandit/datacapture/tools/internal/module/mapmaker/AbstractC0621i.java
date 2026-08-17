package com.scandit.datacapture.tools.internal.module.mapmaker;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* renamed from: com.scandit.datacapture.tools.internal.module.mapmaker.i, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract class AbstractC0621i extends WeakReference implements InterfaceC0625m {
    public final int a;
    public final InterfaceC0625m b;

    public AbstractC0621i(ReferenceQueue referenceQueue, Object obj, int i, InterfaceC0625m interfaceC0625m) {
        super(obj, referenceQueue);
        this.a = i;
        this.b = interfaceC0625m;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0625m
    public final InterfaceC0625m b() {
        return this.b;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0625m
    public final int c() {
        return this.a;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0625m
    public final Object getKey() {
        return get();
    }
}
