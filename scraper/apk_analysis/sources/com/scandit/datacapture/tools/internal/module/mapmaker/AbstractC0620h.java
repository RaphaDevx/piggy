package com.scandit.datacapture.tools.internal.module.mapmaker;

/* renamed from: com.scandit.datacapture.tools.internal.module.mapmaker.h, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract class AbstractC0620h implements InterfaceC0625m {
    public final Object a;
    public final int b;
    public final InterfaceC0625m c;

    public AbstractC0620h(Object obj, int i, InterfaceC0625m interfaceC0625m) {
        this.a = obj;
        this.b = i;
        this.c = interfaceC0625m;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0625m
    public final InterfaceC0625m b() {
        return this.c;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0625m
    public final int c() {
        return this.b;
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0625m
    public final Object getKey() {
        return this.a;
    }
}
