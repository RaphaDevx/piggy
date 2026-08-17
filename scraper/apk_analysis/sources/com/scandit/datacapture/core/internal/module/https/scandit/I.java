package com.scandit.datacapture.core.internal.module.https.scandit;

import com.scandit.datacapture.core.internal.module.https.NativeHttpsError;
import com.scandit.datacapture.core.internal.module.https.NativeHttpsMethod;
import com.scandit.datacapture.core.internal.module.https.NativeHttpsRequest;
import com.scandit.datacapture.core.internal.module.https.NativeHttpsResponse;
import com.scandit.datacapture.core.internal.module.https.NativeHttpsTaskState;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.net.ssl.HttpsURLConnection;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class I extends AbstractC0539k {
    public final int a;
    public final NativeHttpsRequest b;
    public final S c;
    public final InterfaceC0532d d;
    public final Y e;
    public final ReentrantReadWriteLock f;
    public l0 g;

    public I(int i, NativeHttpsRequest nativeRequest, O sslSocketHandler, Y y) {
        Intrinsics.checkNotNullParameter(nativeRequest, "nativeRequest");
        Intrinsics.checkNotNullParameter(sslSocketHandler, "sslSocketHandler");
        String url = nativeRequest.getUrl();
        Intrinsics.checkNotNullExpressionValue(url, "getUrl(...)");
        NativeHttpsMethod method = nativeRequest.getMethod();
        Intrinsics.checkNotNullExpressionValue(method, "getMethod(...)");
        HashMap<String, String> headers = nativeRequest.getHeaders();
        Intrinsics.checkNotNullExpressionValue(headers, "getHeaders(...)");
        C0537i taskBackend = new C0537i(new C0531c(url, method, headers, sslSocketHandler));
        C0533e headerParser = new C0533e();
        Intrinsics.checkNotNullParameter(nativeRequest, "nativeRequest");
        Intrinsics.checkNotNullParameter(taskBackend, "taskBackend");
        Intrinsics.checkNotNullParameter(headerParser, "headerParser");
        this.a = i;
        this.b = nativeRequest;
        this.c = taskBackend;
        this.d = headerParser;
        this.e = y;
        this.f = new ReentrantReadWriteLock(true);
        this.g = k0.a;
        b();
    }

    @Override // com.scandit.datacapture.core.internal.module.https.scandit.AbstractC0539k
    public final void a(C0549v exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        ReentrantReadWriteLock reentrantReadWriteLock = this.f;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            a((Throwable) exception);
            Unit unit = Unit.INSTANCE;
        } finally {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    public final void b() {
        Y y = this.e;
        if (y != null) {
            y.a(new V(this, this.g));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0049 A[DONT_GENERATE, LOOP:1: B:17:0x0047->B:18:0x0049, LOOP_END] */
    @Override // com.scandit.datacapture.core.internal.module.https.NativeHttpsTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void cancel() {
        /*
            r8 = this;
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r8.f
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r0.readLock()
            int r2 = r0.getWriteHoldCount()
            r3 = 0
            if (r2 != 0) goto L12
            int r2 = r0.getReadHoldCount()
            goto L13
        L12:
            r2 = r3
        L13:
            r4 = r3
        L14:
            if (r4 >= r2) goto L1c
            r1.unlock()
            int r4 = r4 + 1
            goto L14
        L1c:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r0 = r0.writeLock()
            r0.lock()
            com.scandit.datacapture.core.internal.module.https.scandit.l0 r4 = r8.g     // Catch: java.lang.Throwable -> L53
            com.scandit.datacapture.core.internal.module.https.scandit.g0 r5 = com.scandit.datacapture.core.internal.module.https.scandit.g0.a     // Catch: java.lang.Throwable -> L53
            boolean r6 = r4 instanceof com.scandit.datacapture.core.internal.module.https.scandit.g0     // Catch: java.lang.Throwable -> L53
            if (r6 != 0) goto L39
            boolean r6 = r4 instanceof com.scandit.datacapture.core.internal.module.https.scandit.j0     // Catch: java.lang.Throwable -> L53
            if (r6 == 0) goto L30
            goto L39
        L30:
            r8.a()     // Catch: java.lang.Throwable -> L53
            r8.g = r5     // Catch: java.lang.Throwable -> L53
            r8.b()     // Catch: java.lang.Throwable -> L53
            goto L45
        L39:
            com.scandit.datacapture.core.internal.module.https.scandit.Y r6 = r8.e     // Catch: java.lang.Throwable -> L53
            if (r6 == 0) goto L45
            com.scandit.datacapture.core.internal.module.https.scandit.W r7 = new com.scandit.datacapture.core.internal.module.https.scandit.W     // Catch: java.lang.Throwable -> L53
            r7.<init>(r8, r4, r5)     // Catch: java.lang.Throwable -> L53
            r6.a(r7)     // Catch: java.lang.Throwable -> L53
        L45:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L53
        L47:
            if (r3 >= r2) goto L4f
            r1.lock()
            int r3 = r3 + 1
            goto L47
        L4f:
            r0.unlock()
            return
        L53:
            r8 = move-exception
        L54:
            if (r3 >= r2) goto L5c
            r1.lock()
            int r3 = r3 + 1
            goto L54
        L5c:
            r0.unlock()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.module.https.scandit.I.cancel():void");
    }

    @Override // com.scandit.datacapture.core.internal.module.https.NativeHttpsTask
    public final NativeHttpsError getError() {
        ReentrantReadWriteLock.ReadLock readLock = this.f.readLock();
        readLock.lock();
        try {
            l0 l0Var = this.g;
            return l0Var instanceof h0 ? new NativeHttpsError() : l0Var instanceof g0 ? new NativeHttpsError() : null;
        } finally {
            readLock.unlock();
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.https.NativeHttpsTask
    public final int getId() {
        return this.a;
    }

    @Override // com.scandit.datacapture.core.internal.module.https.NativeHttpsTask
    public final float getProgress() {
        float f;
        ReentrantReadWriteLock.ReadLock readLock = this.f.readLock();
        readLock.lock();
        try {
            l0 l0Var = this.g;
            if (l0Var instanceof k0) {
                f = 0.0f;
            } else if (l0Var instanceof f0) {
                f = ((f0) l0Var).a;
            } else {
                if (!(l0Var instanceof j0 ? true : l0Var instanceof g0)) {
                    throw new NoWhenBranchMatchedException();
                }
                f = 1.0f;
            }
            return f;
        } finally {
            readLock.unlock();
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.https.NativeHttpsTask
    public final NativeHttpsRequest getRequest() {
        return this.b;
    }

    @Override // com.scandit.datacapture.core.internal.module.https.NativeHttpsTask
    public final NativeHttpsResponse getResponse() {
        ReentrantReadWriteLock.ReadLock readLock = this.f.readLock();
        readLock.lock();
        try {
            l0 l0Var = this.g;
            return l0Var instanceof i0 ? ((i0) l0Var).a : null;
        } finally {
            readLock.unlock();
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.https.NativeHttpsTask
    public final NativeHttpsTaskState getState() {
        NativeHttpsTaskState nativeHttpsTaskState;
        ReentrantReadWriteLock.ReadLock readLock = this.f.readLock();
        readLock.lock();
        try {
            l0 l0Var = this.g;
            if (l0Var instanceof k0 ? true : l0Var instanceof f0) {
                nativeHttpsTaskState = NativeHttpsTaskState.ACTIVE;
            } else if (l0Var instanceof j0) {
                nativeHttpsTaskState = NativeHttpsTaskState.COMPLETED;
            } else {
                if (!(l0Var instanceof g0)) {
                    throw new NoWhenBranchMatchedException();
                }
                nativeHttpsTaskState = NativeHttpsTaskState.CANCELLED;
            }
            return nativeHttpsTaskState;
        } finally {
            readLock.unlock();
        }
    }

    /* JADX WARN: Finally extract failed */
    @Override // com.scandit.datacapture.core.internal.module.https.NativeHttpsTask
    public final void start() {
        ReentrantReadWriteLock.ReadLock readLock;
        l0 l0Var;
        try {
            readLock = this.f.readLock();
            readLock.lock();
            try {
                l0Var = this.g;
            } finally {
                readLock.unlock();
            }
        } catch (C0540l unused) {
        } catch (Throwable th) {
            try {
                ReentrantReadWriteLock reentrantReadWriteLock = this.f;
                ReentrantReadWriteLock.ReadLock readLock2 = reentrantReadWriteLock.readLock();
                int i = 0;
                int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
                for (int i2 = 0; i2 < readHoldCount; i2++) {
                    readLock2.unlock();
                }
                ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
                writeLock.lock();
                try {
                    a(th);
                    Unit unit = Unit.INSTANCE;
                    while (i < readHoldCount) {
                        readLock2.lock();
                        i++;
                    }
                    writeLock.unlock();
                } catch (Throwable th2) {
                    while (i < readHoldCount) {
                        readLock2.lock();
                        i++;
                    }
                    writeLock.unlock();
                    throw th2;
                }
            } finally {
                a();
            }
        }
        if (!(l0Var instanceof k0)) {
            Z z = Z.b;
            Y y = this.e;
            if (y != null) {
                y.a(new W(this, l0Var, z));
            }
            return;
        }
        Unit unit2 = Unit.INSTANCE;
        readLock.unlock();
        a(this.f, new E(this));
        ((C0537i) this.c).a();
        OutputStream c = ((C0537i) this.c).c();
        if (c != null) {
            a(this.f, new F(this));
            try {
                c.write(this.b.getBody());
                c.flush();
                CloseableKt.closeFinally(c, null);
            } finally {
            }
        }
        a(this.f, new G(this));
        a(this.f, new H(this, ((C0533e) this.d).a(((C0537i) this.c).b())));
    }

    public final void a(Throwable th) {
        D c0548u;
        D d;
        l0 l0Var = this.g;
        if (th instanceof D) {
            d = (D) th;
        } else {
            if (th instanceof ProtocolException) {
                c0548u = new C(th);
            } else if (th instanceof SocketTimeoutException) {
                c0548u = new C0547t(th);
            } else {
                c0548u = th instanceof IOException ? new C0548u(th) : new C0553z(th);
            }
            d = c0548u;
        }
        h0 h0Var = new h0(d);
        if (!(l0Var instanceof g0)) {
            this.g = h0Var;
            b();
        } else {
            Y y = this.e;
            if (y != null) {
                y.a(new W(this, l0Var, h0Var));
            }
        }
    }

    public final void a(ReentrantReadWriteLock reentrantReadWriteLock, Function0 function0) {
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            ReentrantReadWriteLock.ReadLock readLock2 = this.f.readLock();
            readLock2.lock();
            try {
                if (!(this.g instanceof g0)) {
                    Unit unit = Unit.INSTANCE;
                    readLock2.unlock();
                    function0.invoke();
                    return;
                }
                throw C0540l.a;
            } catch (Throwable th) {
                readLock2.unlock();
                throw th;
            }
        } finally {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    public final void a() {
        try {
            C0537i c0537i = (C0537i) this.c;
            c0537i.getClass();
            try {
                ((HttpsURLConnection) c0537i.b.getValue()).disconnect();
            } catch (IOException e) {
                throw new C0541m(e);
            }
        } catch (Throwable unused) {
        }
    }
}
