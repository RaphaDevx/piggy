package com.scandit.datacapture.tools.internal.module.mapmaker;

import java.lang.ref.ReferenceQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes2.dex */
public abstract class r extends ReentrantLock {
    public static final /* synthetic */ int g = 0;
    public final N a;
    public volatile int b;
    public int c;
    public int d;
    public volatile AtomicReferenceArray e;
    public final AtomicInteger f = new AtomicInteger();

    public r(N n, int i) {
        this.a = n;
        AtomicReferenceArray atomicReferenceArray = new AtomicReferenceArray(i);
        int length = (atomicReferenceArray.length() * 3) / 4;
        this.d = length;
        if (length == -1) {
            this.d = length + 1;
        }
        this.e = atomicReferenceArray;
    }

    public final void a(ReferenceQueue referenceQueue) {
        int i = 0;
        do {
            Object poll = referenceQueue.poll();
            if (poll == null) {
                return;
            }
            InterfaceC0625m interfaceC0625m = (InterfaceC0625m) poll;
            N n = this.a;
            n.getClass();
            int c = interfaceC0625m.c();
            r a = n.a(c);
            a.lock();
            try {
                AtomicReferenceArray atomicReferenceArray = a.e;
                int length = c & (atomicReferenceArray.length() - 1);
                InterfaceC0625m interfaceC0625m2 = (InterfaceC0625m) atomicReferenceArray.get(length);
                InterfaceC0625m interfaceC0625m3 = interfaceC0625m2;
                while (true) {
                    if (interfaceC0625m3 == null) {
                        break;
                    }
                    if (interfaceC0625m3 == interfaceC0625m) {
                        a.c++;
                        InterfaceC0625m a2 = a.a(interfaceC0625m2, interfaceC0625m3);
                        int i2 = a.b - 1;
                        atomicReferenceArray.set(length, a2);
                        a.b = i2;
                        break;
                    }
                    interfaceC0625m3 = interfaceC0625m3.b();
                }
                i++;
            } finally {
                a.unlock();
            }
        } while (i != 16);
    }

    public void b() {
    }

    public void c() {
    }

    public final void d() {
        if ((this.f.incrementAndGet() & 63) == 0) {
            e();
        }
    }

    public final void e() {
        if (tryLock()) {
            try {
                c();
                this.f.set(0);
            } finally {
                unlock();
            }
        }
    }

    public abstract r f();

    public final void b(ReferenceQueue referenceQueue) {
        int i = 0;
        do {
            Object poll = referenceQueue.poll();
            if (poll == null) {
                return;
            }
            K k = (K) poll;
            N n = this.a;
            n.getClass();
            InterfaceC0625m a = k.a();
            int c = a.c();
            r a2 = n.a(c);
            Object key = a.getKey();
            a2.lock();
            try {
                AtomicReferenceArray atomicReferenceArray = a2.e;
                int length = (atomicReferenceArray.length() - 1) & c;
                InterfaceC0625m interfaceC0625m = (InterfaceC0625m) atomicReferenceArray.get(length);
                InterfaceC0625m interfaceC0625m2 = interfaceC0625m;
                while (true) {
                    if (interfaceC0625m2 == null) {
                        break;
                    }
                    Object key2 = interfaceC0625m2.getKey();
                    if (interfaceC0625m2.c() != c || key2 == null || !a2.a.e.b(key, key2)) {
                        interfaceC0625m2 = interfaceC0625m2.b();
                    } else if (((J) interfaceC0625m2).a() == k) {
                        a2.c++;
                        InterfaceC0625m a3 = a2.a(interfaceC0625m, interfaceC0625m2);
                        int i2 = a2.b - 1;
                        atomicReferenceArray.set(length, a3);
                        a2.b = i2;
                    }
                }
                a2.unlock();
                i++;
            } catch (Throwable th) {
                a2.unlock();
                throw th;
            }
        } while (i != 16);
    }

    public final InterfaceC0625m a(Object obj, int i) {
        if (this.b != 0) {
            for (InterfaceC0625m interfaceC0625m = (InterfaceC0625m) this.e.get((r0.length() - 1) & i); interfaceC0625m != null; interfaceC0625m = interfaceC0625m.b()) {
                if (interfaceC0625m.c() == i) {
                    Object key = interfaceC0625m.getKey();
                    if (key == null) {
                        if (tryLock()) {
                            try {
                                c();
                            } finally {
                                unlock();
                            }
                        } else {
                            continue;
                        }
                    } else if (this.a.e.b(obj, key)) {
                        return interfaceC0625m;
                    }
                }
            }
        }
        return null;
    }

    public final Object a(Object obj, int i, Object obj2, boolean z) {
        lock();
        try {
            e();
            int i2 = this.b + 1;
            if (i2 > this.d) {
                a();
                i2 = this.b + 1;
            }
            AtomicReferenceArray atomicReferenceArray = this.e;
            int length = (atomicReferenceArray.length() - 1) & i;
            InterfaceC0625m interfaceC0625m = (InterfaceC0625m) atomicReferenceArray.get(length);
            for (InterfaceC0625m interfaceC0625m2 = interfaceC0625m; interfaceC0625m2 != null; interfaceC0625m2 = interfaceC0625m2.b()) {
                Object key = interfaceC0625m2.getKey();
                if (interfaceC0625m2.c() == i && key != null && this.a.e.b(obj, key)) {
                    Object value = interfaceC0625m2.getValue();
                    if (value == null) {
                        this.c++;
                        this.a.f.a(f(), interfaceC0625m2, obj2);
                        this.b = this.b;
                        return null;
                    }
                    if (z) {
                        return value;
                    }
                    this.c++;
                    this.a.f.a(f(), interfaceC0625m2, obj2);
                    return value;
                }
            }
            this.c++;
            InterfaceC0625m a = this.a.f.a(f(), obj, i, interfaceC0625m);
            this.a.f.a(f(), a, obj2);
            atomicReferenceArray.set(length, a);
            this.b = i2;
            return null;
        } finally {
            unlock();
        }
    }

    public final void a() {
        AtomicReferenceArray atomicReferenceArray = this.e;
        int length = atomicReferenceArray.length();
        if (length >= 1073741824) {
            return;
        }
        int i = this.b;
        AtomicReferenceArray atomicReferenceArray2 = new AtomicReferenceArray(length << 1);
        this.d = (atomicReferenceArray2.length() * 3) / 4;
        int length2 = atomicReferenceArray2.length() - 1;
        for (int i2 = 0; i2 < length; i2++) {
            InterfaceC0625m interfaceC0625m = (InterfaceC0625m) atomicReferenceArray.get(i2);
            if (interfaceC0625m != null) {
                InterfaceC0625m b = interfaceC0625m.b();
                int c = interfaceC0625m.c() & length2;
                if (b == null) {
                    atomicReferenceArray2.set(c, interfaceC0625m);
                } else {
                    InterfaceC0625m interfaceC0625m2 = interfaceC0625m;
                    while (b != null) {
                        int c2 = b.c() & length2;
                        if (c2 != c) {
                            interfaceC0625m2 = b;
                            c = c2;
                        }
                        b = b.b();
                    }
                    atomicReferenceArray2.set(c, interfaceC0625m2);
                    while (interfaceC0625m != interfaceC0625m2) {
                        int c3 = interfaceC0625m.c() & length2;
                        InterfaceC0625m a = this.a.f.a(f(), interfaceC0625m, (InterfaceC0625m) atomicReferenceArray2.get(c3));
                        if (a != null) {
                            atomicReferenceArray2.set(c3, a);
                        } else {
                            i--;
                        }
                        interfaceC0625m = interfaceC0625m.b();
                    }
                }
            }
        }
        this.e = atomicReferenceArray2;
        this.b = i;
    }

    public final InterfaceC0625m a(InterfaceC0625m interfaceC0625m, InterfaceC0625m interfaceC0625m2) {
        int i = this.b;
        InterfaceC0625m b = interfaceC0625m2.b();
        while (interfaceC0625m != interfaceC0625m2) {
            InterfaceC0625m a = this.a.f.a(f(), interfaceC0625m, b);
            if (a != null) {
                b = a;
            } else {
                i--;
            }
            interfaceC0625m = interfaceC0625m.b();
        }
        this.b = i;
        return b;
    }
}
