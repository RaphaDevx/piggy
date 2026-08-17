package com.scandit.datacapture.core.internal.module.source.dynamicresolution;

import kotlin.Unit;

/* loaded from: classes2.dex */
public final class d implements c {
    public long b;
    public long c;
    public float e;
    public long f;
    public final Object a = new Object();
    public final Object d = new Object();

    public final void a(float f) {
        synchronized (this.d) {
            this.e += f;
            this.f++;
            Unit unit = Unit.INSTANCE;
        }
    }

    public final float a() {
        synchronized (this.d) {
            long j = this.f;
            if (j == 0) {
                return 0.0f;
            }
            return this.e / j;
        }
    }
}
