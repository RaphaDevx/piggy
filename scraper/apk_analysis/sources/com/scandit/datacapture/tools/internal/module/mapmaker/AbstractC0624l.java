package com.scandit.datacapture.tools.internal.module.mapmaker;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* renamed from: com.scandit.datacapture.tools.internal.module.mapmaker.l, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract class AbstractC0624l implements Iterator {
    public int a;
    public int b = -1;
    public r c;
    public AtomicReferenceArray d;
    public InterfaceC0625m e;
    public M f;
    public M g;
    public final /* synthetic */ N h;

    public AbstractC0624l(N n) {
        this.h = n;
        this.a = n.c.length - 1;
        a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0021, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x007c, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x005a, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a() {
        /*
            r3 = this;
            r0 = 0
            r3.f = r0
            com.scandit.datacapture.tools.internal.module.mapmaker.m r0 = r3.e
            if (r0 == 0) goto L21
            com.scandit.datacapture.tools.internal.module.mapmaker.m r0 = r0.b()
            r3.e = r0
        Ld:
            com.scandit.datacapture.tools.internal.module.mapmaker.m r0 = r3.e
            if (r0 == 0) goto L21
            boolean r0 = r3.a(r0)
            if (r0 == 0) goto L18
            return
        L18:
            com.scandit.datacapture.tools.internal.module.mapmaker.m r0 = r3.e
            com.scandit.datacapture.tools.internal.module.mapmaker.m r0 = r0.b()
            r3.e = r0
            goto Ld
        L21:
            int r0 = r3.b
            if (r0 < 0) goto L5a
            java.util.concurrent.atomic.AtomicReferenceArray r1 = r3.d
            int r2 = r0 + (-1)
            r3.b = r2
            java.lang.Object r0 = r1.get(r0)
            com.scandit.datacapture.tools.internal.module.mapmaker.m r0 = (com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0625m) r0
            r3.e = r0
            if (r0 == 0) goto L21
            boolean r0 = r3.a(r0)
            if (r0 != 0) goto L59
            com.scandit.datacapture.tools.internal.module.mapmaker.m r0 = r3.e
            if (r0 == 0) goto L21
            com.scandit.datacapture.tools.internal.module.mapmaker.m r0 = r0.b()
            r3.e = r0
        L45:
            com.scandit.datacapture.tools.internal.module.mapmaker.m r0 = r3.e
            if (r0 == 0) goto L21
            boolean r0 = r3.a(r0)
            if (r0 == 0) goto L50
            goto L59
        L50:
            com.scandit.datacapture.tools.internal.module.mapmaker.m r0 = r3.e
            com.scandit.datacapture.tools.internal.module.mapmaker.m r0 = r0.b()
            r3.e = r0
            goto L45
        L59:
            return
        L5a:
            int r0 = r3.a
            if (r0 < 0) goto Lb4
            com.scandit.datacapture.tools.internal.module.mapmaker.N r1 = r3.h
            com.scandit.datacapture.tools.internal.module.mapmaker.r[] r1 = r1.c
            int r2 = r0 + (-1)
            r3.a = r2
            r0 = r1[r0]
            r3.c = r0
            int r0 = r0.b
            if (r0 == 0) goto L5a
            com.scandit.datacapture.tools.internal.module.mapmaker.r r0 = r3.c
            java.util.concurrent.atomic.AtomicReferenceArray r0 = r0.e
            r3.d = r0
            int r0 = r0.length()
            int r0 = r0 + (-1)
            r3.b = r0
        L7c:
            int r0 = r3.b
            if (r0 < 0) goto L5a
            java.util.concurrent.atomic.AtomicReferenceArray r1 = r3.d
            int r2 = r0 + (-1)
            r3.b = r2
            java.lang.Object r0 = r1.get(r0)
            com.scandit.datacapture.tools.internal.module.mapmaker.m r0 = (com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0625m) r0
            r3.e = r0
            if (r0 == 0) goto L7c
            boolean r0 = r3.a(r0)
            if (r0 != 0) goto Lb4
            com.scandit.datacapture.tools.internal.module.mapmaker.m r0 = r3.e
            if (r0 == 0) goto L7c
            com.scandit.datacapture.tools.internal.module.mapmaker.m r0 = r0.b()
            r3.e = r0
        La0:
            com.scandit.datacapture.tools.internal.module.mapmaker.m r0 = r3.e
            if (r0 == 0) goto L7c
            boolean r0 = r3.a(r0)
            if (r0 == 0) goto Lab
            goto Lb4
        Lab:
            com.scandit.datacapture.tools.internal.module.mapmaker.m r0 = r3.e
            com.scandit.datacapture.tools.internal.module.mapmaker.m r0 = r0.b()
            r3.e = r0
            goto La0
        Lb4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.tools.internal.module.mapmaker.AbstractC0624l.a():void");
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f != null;
    }

    @Override // java.util.Iterator
    public final void remove() {
        M m = this.g;
        if (!(m != null)) {
            throw new IllegalStateException("no calls to next() since the last call to remove()");
        }
        this.h.remove(m.a);
        this.g = null;
    }

    public final boolean a(InterfaceC0625m interfaceC0625m) {
        try {
            Object key = interfaceC0625m.getKey();
            this.h.getClass();
            Object value = interfaceC0625m.getKey() == null ? null : interfaceC0625m.getValue();
            if (value != null) {
                this.f = new M(this.h, key, value);
                this.c.d();
                return true;
            }
            this.c.d();
            return false;
        } catch (Throwable th) {
            this.c.d();
            throw th;
        }
    }
}
