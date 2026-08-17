package com.scandit.datacapture.tools.internal.module.mapmaker;

/* loaded from: classes2.dex */
public final class z extends AbstractC0620h implements J {
    public volatile K d;

    public z(Object obj, int i, z zVar) {
        super(obj, i, zVar);
        this.d = N.j;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.J
    public final K a() {
        return this.d;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0625m
    public final Object getValue() {
        return this.d.get();
    }
}
