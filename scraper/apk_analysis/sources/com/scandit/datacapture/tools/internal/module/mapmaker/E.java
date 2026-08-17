package com.scandit.datacapture.tools.internal.module.mapmaker;

import java.lang.ref.ReferenceQueue;

/* loaded from: classes2.dex */
public final class E extends AbstractC0621i {
    public volatile Object c;

    public E(ReferenceQueue referenceQueue, Object obj, int i, E e) {
        super(referenceQueue, obj, i, e);
        this.c = null;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0625m
    public final Object getValue() {
        return this.c;
    }
}
