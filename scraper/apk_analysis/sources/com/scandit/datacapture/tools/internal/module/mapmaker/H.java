package com.scandit.datacapture.tools.internal.module.mapmaker;

import java.lang.ref.ReferenceQueue;

/* loaded from: classes2.dex */
public final class H extends AbstractC0621i implements J {
    public volatile K c;

    public H(ReferenceQueue referenceQueue, Object obj, int i, H h) {
        super(referenceQueue, obj, i, h);
        this.c = N.j;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.J
    public final K a() {
        return this.c;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0625m
    public final Object getValue() {
        return this.c.get();
    }
}
