package com.scandit.datacapture.tools.internal.module.mapmaker;

import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* loaded from: classes2.dex */
public final class N extends AbstractMap implements ConcurrentMap, Serializable {
    public static final C0619g j = new C0619g();
    public final transient int a;
    public final transient int b;
    public final transient r[] c;
    public final int d = Math.min(4, 65536);
    public final AbstractC0617e e;
    public final transient InterfaceC0626n f;
    public transient C0628p g;
    public transient C h;
    public transient C0623k i;

    public N(C0618f c0618f, InterfaceC0626n interfaceC0626n) {
        this.e = (AbstractC0617e) P.a(null, ((u) P.a(c0618f.a, u.a)).a());
        this.f = interfaceC0626n;
        int min = Math.min(16, BasicMeasure.EXACTLY);
        int i = 0;
        int i2 = 1;
        int i3 = 0;
        int i4 = 1;
        while (i4 < this.d) {
            i3++;
            i4 <<= 1;
        }
        this.b = 32 - i3;
        this.a = i4 - 1;
        this.c = new r[i4];
        int i5 = min / i4;
        while (i2 < (i4 * i5 < min ? i5 + 1 : i5)) {
            i2 <<= 1;
        }
        while (true) {
            r[] rVarArr = this.c;
            if (i >= rVarArr.length) {
                return;
            }
            rVarArr[i] = this.f.a(this, i2);
            i++;
        }
    }

    public final int a(Object obj) {
        int a;
        AbstractC0617e abstractC0617e = this.e;
        if (obj == null) {
            abstractC0617e.getClass();
            a = 0;
        } else {
            a = abstractC0617e.a(obj);
        }
        int i = a + ((a << 15) ^ (-12931));
        int i2 = i ^ (i >>> 10);
        int i3 = i2 + (i2 << 3);
        int i4 = i3 ^ (i3 >>> 6);
        int i5 = (i4 << 2) + (i4 << 14) + i4;
        return (i5 >>> 16) ^ i5;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        r[] rVarArr = this.c;
        int length = rVarArr.length;
        for (int i = 0; i < length; i++) {
            r rVar = rVarArr[i];
            if (rVar.b != 0) {
                rVar.lock();
                try {
                    AtomicReferenceArray atomicReferenceArray = rVar.e;
                    for (int i2 = 0; i2 < atomicReferenceArray.length(); i2++) {
                        atomicReferenceArray.set(i2, null);
                    }
                    rVar.b();
                    rVar.f.set(0);
                    rVar.c++;
                    rVar.b = 0;
                } finally {
                    rVar.unlock();
                }
            }
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        InterfaceC0625m a;
        boolean z = false;
        if (obj == null) {
            return false;
        }
        int a2 = a(obj);
        r a3 = a(a2);
        a3.getClass();
        try {
            if (a3.b != 0 && (a = a3.a(obj, a2)) != null) {
                if (a.getValue() != null) {
                    z = true;
                }
            }
            return z;
        } finally {
            a3.d();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [int] */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r12v1, types: [java.util.concurrent.atomic.AtomicReferenceArray] */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v1, types: [int] */
    /* JADX WARN: Type inference failed for: r13v3 */
    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object obj) {
        Object value;
        boolean z = false;
        if (obj == null) {
            return false;
        }
        r[] rVarArr = this.c;
        long j2 = -1;
        int i = 0;
        while (i < 3) {
            int length = rVarArr.length;
            long j3 = 0;
            for (?? r10 = z; r10 < length; r10++) {
                r rVar = rVarArr[r10];
                int i2 = rVar.b;
                ?? r12 = rVar.e;
                for (?? r13 = z; r13 < r12.length(); r13++) {
                    for (InterfaceC0625m interfaceC0625m = (InterfaceC0625m) r12.get(r13); interfaceC0625m != null; interfaceC0625m = interfaceC0625m.b()) {
                        if (interfaceC0625m.getKey() != null) {
                            value = interfaceC0625m.getValue();
                            if (value == null) {
                                if (rVar.tryLock()) {
                                    try {
                                        rVar.c();
                                    } finally {
                                    }
                                }
                            }
                            if (value == null && this.f.a().a().b(obj, value)) {
                                return true;
                            }
                        } else if (rVar.tryLock()) {
                            try {
                                rVar.c();
                            } finally {
                            }
                        }
                        value = null;
                        if (value == null) {
                        }
                    }
                }
                j3 += rVar.c;
                z = false;
            }
            if (j3 == j2) {
                return false;
            }
            i++;
            j2 = j3;
            z = false;
        }
        return z;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        C0623k c0623k = this.i;
        if (c0623k != null) {
            return c0623k;
        }
        C0623k c0623k2 = new C0623k(this);
        this.i = c0623k2;
        return c0623k2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Object obj2 = null;
        if (obj == null) {
            return null;
        }
        int a = a(obj);
        r a2 = a(a);
        a2.getClass();
        try {
            InterfaceC0625m a3 = a2.a(obj, a);
            if (a3 != null) {
                obj2 = a3.getValue();
                if (obj2 == null && a2.tryLock()) {
                    try {
                        a2.c();
                        a2.unlock();
                    } catch (Throwable th) {
                        a2.unlock();
                        throw th;
                    }
                }
            }
            return obj2;
        } finally {
            a2.d();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean isEmpty() {
        r[] rVarArr = this.c;
        long j2 = 0;
        for (int i = 0; i < rVarArr.length; i++) {
            if (rVarArr[i].b != 0) {
                return false;
            }
            j2 += rVarArr[i].c;
        }
        if (j2 == 0) {
            return true;
        }
        for (int i2 = 0; i2 < rVarArr.length; i2++) {
            if (rVarArr[i2].b != 0) {
                return false;
            }
            j2 -= rVarArr[i2].c;
        }
        return j2 == 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        C0628p c0628p = this.g;
        if (c0628p != null) {
            return c0628p;
        }
        C0628p c0628p2 = new C0628p(this);
        this.g = c0628p2;
        return c0628p2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        obj.getClass();
        obj2.getClass();
        int a = a(obj);
        return a(a).a(obj, a, obj2, false);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void putAll(Map map) {
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public final Object putIfAbsent(Object obj, Object obj2) {
        obj.getClass();
        obj2.getClass();
        int a = a(obj);
        return a(a).a(obj, a, obj2, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003a, code lost:
    
        r9 = r5.getValue();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003e, code lost:
    
        if (r9 == null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0047, code lost:
    
        r8.c++;
        r0 = r8.a(r4, r5);
        r1 = r8.b - 1;
        r2.set(r3, r0);
        r8.b = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:?, code lost:
    
        return r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0045, code lost:
    
        if (r5.getValue() != null) goto L22;
     */
    @Override // java.util.AbstractMap, java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object remove(java.lang.Object r9) {
        /*
            r8 = this;
            r0 = 0
            if (r9 != 0) goto L4
            return r0
        L4:
            int r1 = r8.a(r9)
            com.scandit.datacapture.tools.internal.module.mapmaker.r r8 = r8.a(r1)
            r8.lock()
            r8.e()     // Catch: java.lang.Throwable -> L68
            java.util.concurrent.atomic.AtomicReferenceArray r2 = r8.e     // Catch: java.lang.Throwable -> L68
            int r3 = r2.length()     // Catch: java.lang.Throwable -> L68
            int r3 = r3 + (-1)
            r3 = r3 & r1
            java.lang.Object r4 = r2.get(r3)     // Catch: java.lang.Throwable -> L68
            com.scandit.datacapture.tools.internal.module.mapmaker.m r4 = (com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0625m) r4     // Catch: java.lang.Throwable -> L68
            r5 = r4
        L22:
            if (r5 == 0) goto L64
            java.lang.Object r6 = r5.getKey()     // Catch: java.lang.Throwable -> L68
            int r7 = r5.c()     // Catch: java.lang.Throwable -> L68
            if (r7 != r1) goto L5f
            if (r6 == 0) goto L5f
            com.scandit.datacapture.tools.internal.module.mapmaker.N r7 = r8.a     // Catch: java.lang.Throwable -> L68
            com.scandit.datacapture.tools.internal.module.mapmaker.e r7 = r7.e     // Catch: java.lang.Throwable -> L68
            boolean r6 = r7.b(r9, r6)     // Catch: java.lang.Throwable -> L68
            if (r6 == 0) goto L5f
            java.lang.Object r9 = r5.getValue()     // Catch: java.lang.Throwable -> L68
            if (r9 == 0) goto L41
            goto L47
        L41:
            java.lang.Object r1 = r5.getValue()     // Catch: java.lang.Throwable -> L68
            if (r1 != 0) goto L64
        L47:
            int r0 = r8.c     // Catch: java.lang.Throwable -> L68
            int r0 = r0 + 1
            r8.c = r0     // Catch: java.lang.Throwable -> L68
            com.scandit.datacapture.tools.internal.module.mapmaker.m r0 = r8.a(r4, r5)     // Catch: java.lang.Throwable -> L68
            int r1 = r8.b     // Catch: java.lang.Throwable -> L68
            int r1 = r1 + (-1)
            r2.set(r3, r0)     // Catch: java.lang.Throwable -> L68
            r8.b = r1     // Catch: java.lang.Throwable -> L68
            r8.unlock()
            r0 = r9
            goto L67
        L5f:
            com.scandit.datacapture.tools.internal.module.mapmaker.m r5 = r5.b()     // Catch: java.lang.Throwable -> L68
            goto L22
        L64:
            r8.unlock()
        L67:
            return r0
        L68:
            r9 = move-exception
            r8.unlock()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.tools.internal.module.mapmaker.N.remove(java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0076, code lost:
    
        r7.unlock();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x007a, code lost:
    
        return null;
     */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object replace(java.lang.Object r8, java.lang.Object r9) {
        /*
            r7 = this;
            r8.getClass()
            r9.getClass()
            int r0 = r7.a(r8)
            com.scandit.datacapture.tools.internal.module.mapmaker.r r7 = r7.a(r0)
            r7.lock()
            r7.e()     // Catch: java.lang.Throwable -> L7b
            java.util.concurrent.atomic.AtomicReferenceArray r1 = r7.e     // Catch: java.lang.Throwable -> L7b
            int r2 = r1.length()     // Catch: java.lang.Throwable -> L7b
            int r2 = r2 + (-1)
            r2 = r2 & r0
            java.lang.Object r3 = r1.get(r2)     // Catch: java.lang.Throwable -> L7b
            com.scandit.datacapture.tools.internal.module.mapmaker.m r3 = (com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0625m) r3     // Catch: java.lang.Throwable -> L7b
            r4 = r3
        L24:
            if (r4 == 0) goto L76
            java.lang.Object r5 = r4.getKey()     // Catch: java.lang.Throwable -> L7b
            int r6 = r4.c()     // Catch: java.lang.Throwable -> L7b
            if (r6 != r0) goto L71
            if (r5 == 0) goto L71
            com.scandit.datacapture.tools.internal.module.mapmaker.N r6 = r7.a     // Catch: java.lang.Throwable -> L7b
            com.scandit.datacapture.tools.internal.module.mapmaker.e r6 = r6.e     // Catch: java.lang.Throwable -> L7b
            boolean r5 = r6.b(r8, r5)     // Catch: java.lang.Throwable -> L7b
            if (r5 == 0) goto L71
            java.lang.Object r8 = r4.getValue()     // Catch: java.lang.Throwable -> L7b
            if (r8 != 0) goto L5c
            java.lang.Object r8 = r4.getValue()     // Catch: java.lang.Throwable -> L7b
            if (r8 != 0) goto L76
            int r8 = r7.c     // Catch: java.lang.Throwable -> L7b
            int r8 = r8 + 1
            r7.c = r8     // Catch: java.lang.Throwable -> L7b
            com.scandit.datacapture.tools.internal.module.mapmaker.m r8 = r7.a(r3, r4)     // Catch: java.lang.Throwable -> L7b
            int r9 = r7.b     // Catch: java.lang.Throwable -> L7b
            int r9 = r9 + (-1)
            r1.set(r2, r8)     // Catch: java.lang.Throwable -> L7b
            r7.b = r9     // Catch: java.lang.Throwable -> L7b
            goto L76
        L5c:
            int r0 = r7.c     // Catch: java.lang.Throwable -> L7b
            int r0 = r0 + 1
            r7.c = r0     // Catch: java.lang.Throwable -> L7b
            com.scandit.datacapture.tools.internal.module.mapmaker.N r0 = r7.a     // Catch: java.lang.Throwable -> L7b
            com.scandit.datacapture.tools.internal.module.mapmaker.n r0 = r0.f     // Catch: java.lang.Throwable -> L7b
            com.scandit.datacapture.tools.internal.module.mapmaker.r r1 = r7.f()     // Catch: java.lang.Throwable -> L7b
            r0.a(r1, r4, r9)     // Catch: java.lang.Throwable -> L7b
            r7.unlock()
            goto L7a
        L71:
            com.scandit.datacapture.tools.internal.module.mapmaker.m r4 = r4.b()     // Catch: java.lang.Throwable -> L7b
            goto L24
        L76:
            r7.unlock()
            r8 = 0
        L7a:
            return r8
        L7b:
            r8 = move-exception
            r7.unlock()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.tools.internal.module.mapmaker.N.replace(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        long j2 = 0;
        for (int i = 0; i < this.c.length; i++) {
            j2 += r5[i].b;
        }
        if (j2 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j2 < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        C c = this.h;
        if (c != null) {
            return c;
        }
        C c2 = new C(this);
        this.h = c2;
        return c2;
    }

    public final r a(int i) {
        return this.c[this.a & (i >>> this.b)];
    }

    public static ArrayList a(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        it.getClass();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0051, code lost:
    
        if (r9.a.f.a().a().b(r11, r6.getValue()) == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0053, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005b, code lost:
    
        r9.c++;
        r10 = r9.a(r5, r6);
        r11 = r9.b - 1;
        r2.set(r3, r10);
        r9.b = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0059, code lost:
    
        if (r6.getValue() != null) goto L22;
     */
    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean remove(java.lang.Object r10, java.lang.Object r11) {
        /*
            r9 = this;
            r0 = 0
            if (r10 == 0) goto L7b
            if (r11 != 0) goto L7
            goto L7b
        L7:
            int r1 = r9.a(r10)
            com.scandit.datacapture.tools.internal.module.mapmaker.r r9 = r9.a(r1)
            r9.lock()
            r9.e()     // Catch: java.lang.Throwable -> L76
            java.util.concurrent.atomic.AtomicReferenceArray r2 = r9.e     // Catch: java.lang.Throwable -> L76
            int r3 = r2.length()     // Catch: java.lang.Throwable -> L76
            r4 = 1
            int r3 = r3 - r4
            r3 = r3 & r1
            java.lang.Object r5 = r2.get(r3)     // Catch: java.lang.Throwable -> L76
            com.scandit.datacapture.tools.internal.module.mapmaker.m r5 = (com.scandit.datacapture.tools.internal.module.mapmaker.InterfaceC0625m) r5     // Catch: java.lang.Throwable -> L76
            r6 = r5
        L25:
            if (r6 == 0) goto L72
            java.lang.Object r7 = r6.getKey()     // Catch: java.lang.Throwable -> L76
            int r8 = r6.c()     // Catch: java.lang.Throwable -> L76
            if (r8 != r1) goto L6d
            if (r7 == 0) goto L6d
            com.scandit.datacapture.tools.internal.module.mapmaker.N r8 = r9.a     // Catch: java.lang.Throwable -> L76
            com.scandit.datacapture.tools.internal.module.mapmaker.e r8 = r8.e     // Catch: java.lang.Throwable -> L76
            boolean r7 = r8.b(r10, r7)     // Catch: java.lang.Throwable -> L76
            if (r7 == 0) goto L6d
            java.lang.Object r10 = r6.getValue()     // Catch: java.lang.Throwable -> L76
            com.scandit.datacapture.tools.internal.module.mapmaker.N r1 = r9.a     // Catch: java.lang.Throwable -> L76
            com.scandit.datacapture.tools.internal.module.mapmaker.n r1 = r1.f     // Catch: java.lang.Throwable -> L76
            com.scandit.datacapture.tools.internal.module.mapmaker.u r1 = r1.a()     // Catch: java.lang.Throwable -> L76
            com.scandit.datacapture.tools.internal.module.mapmaker.e r1 = r1.a()     // Catch: java.lang.Throwable -> L76
            boolean r10 = r1.b(r11, r10)     // Catch: java.lang.Throwable -> L76
            if (r10 == 0) goto L55
            r0 = r4
            goto L5b
        L55:
            java.lang.Object r10 = r6.getValue()     // Catch: java.lang.Throwable -> L76
            if (r10 != 0) goto L72
        L5b:
            int r10 = r9.c     // Catch: java.lang.Throwable -> L76
            int r10 = r10 + r4
            r9.c = r10     // Catch: java.lang.Throwable -> L76
            com.scandit.datacapture.tools.internal.module.mapmaker.m r10 = r9.a(r5, r6)     // Catch: java.lang.Throwable -> L76
            int r11 = r9.b     // Catch: java.lang.Throwable -> L76
            int r11 = r11 - r4
            r2.set(r3, r10)     // Catch: java.lang.Throwable -> L76
            r9.b = r11     // Catch: java.lang.Throwable -> L76
            goto L72
        L6d:
            com.scandit.datacapture.tools.internal.module.mapmaker.m r6 = r6.b()     // Catch: java.lang.Throwable -> L76
            goto L25
        L72:
            r9.unlock()
            return r0
        L76:
            r10 = move-exception
            r9.unlock()
            throw r10
        L7b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.tools.internal.module.mapmaker.N.remove(java.lang.Object, java.lang.Object):boolean");
    }

    @Override // java.util.Map, java.util.concurrent.ConcurrentMap
    public final boolean replace(Object obj, Object obj2, Object obj3) {
        obj.getClass();
        obj3.getClass();
        if (obj2 == null) {
            return false;
        }
        int a = a(obj);
        r a2 = a(a);
        a2.lock();
        try {
            a2.e();
            AtomicReferenceArray atomicReferenceArray = a2.e;
            int length = (atomicReferenceArray.length() - 1) & a;
            InterfaceC0625m interfaceC0625m = (InterfaceC0625m) atomicReferenceArray.get(length);
            InterfaceC0625m interfaceC0625m2 = interfaceC0625m;
            while (true) {
                if (interfaceC0625m2 == null) {
                    break;
                }
                Object key = interfaceC0625m2.getKey();
                if (interfaceC0625m2.c() == a && key != null && a2.a.e.b(obj, key)) {
                    Object value = interfaceC0625m2.getValue();
                    if (value == null) {
                        if (interfaceC0625m2.getValue() == null) {
                            a2.c++;
                            InterfaceC0625m a3 = a2.a(interfaceC0625m, interfaceC0625m2);
                            int i = a2.b - 1;
                            atomicReferenceArray.set(length, a3);
                            a2.b = i;
                        }
                    } else if (a2.a.f.a().a().b(obj2, value)) {
                        a2.c++;
                        a2.a.f.a(a2.f(), interfaceC0625m2, obj3);
                        return true;
                    }
                } else {
                    interfaceC0625m2 = interfaceC0625m2.b();
                }
            }
            return false;
        } finally {
            a2.unlock();
        }
    }
}
