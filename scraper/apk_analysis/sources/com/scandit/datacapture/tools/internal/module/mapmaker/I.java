package com.scandit.datacapture.tools.internal.module.mapmaker;

import java.lang.ref.ReferenceQueue;

/* loaded from: classes2.dex */
public final class I extends r {
    public final ReferenceQueue h;
    public final ReferenceQueue i;

    public I(N n, int i) {
        super(n, i);
        this.h = new ReferenceQueue();
        this.i = new ReferenceQueue();
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.r
    public final void b() {
        while (this.h.poll() != null) {
        }
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.r
    public final void c() {
        a(this.h);
        b(this.i);
    }

    @Override // com.scandit.datacapture.tools.internal.module.mapmaker.r
    public final r f() {
        return this;
    }
}
