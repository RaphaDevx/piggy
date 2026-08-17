package com.scandit.datacapture.core.internal.module.https.scandit;

import com.scandit.datacapture.core.internal.module.https.NativeHttpsRequest;
import com.scandit.datacapture.core.internal.module.https.NativeHttpsSession;
import com.scandit.datacapture.core.internal.module.https.NativeHttpsSessionConfiguration;
import com.scandit.datacapture.core.internal.module.https.NativeHttpsSessionDelegate;
import com.scandit.datacapture.core.internal.module.https.NativeHttpsTask;
import com.scandit.datacapture.core.internal.module.https.trusts.AllowExpiredCertificatesTrustManager;
import com.scandit.datacapture.core.internal.module.https.trusts.ExtendedX509TrustManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.https.scandit.j, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0538j extends NativeHttpsSession {
    public final NativeHttpsSessionConfiguration a;
    public final com.scandit.datacapture.core.internal.module.https.scandit.client.b b;
    public final ReentrantReadWriteLock c;
    public NativeHttpsSessionDelegate d;

    public C0538j(NativeHttpsSessionConfiguration config) {
        Intrinsics.checkNotNullParameter(config, "config");
        com.scandit.datacapture.core.internal.module.https.scandit.client.e client = new com.scandit.datacapture.core.internal.module.https.scandit.client.e(config);
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(client, "client");
        this.a = config;
        this.b = client;
        this.c = new ReentrantReadWriteLock(true);
        com.scandit.datacapture.core.internal.module.https.scandit.interceptors.a interceptor = new com.scandit.datacapture.core.internal.module.https.scandit.interceptors.a(config.getAllowsCellularAccess());
        Intrinsics.checkNotNullParameter(interceptor, "interceptor");
        client.g.add(interceptor);
        com.scandit.datacapture.core.internal.module.https.scandit.listeners.a listener = new com.scandit.datacapture.core.internal.module.https.scandit.listeners.a(this);
        Intrinsics.checkNotNullParameter(listener, "listener");
        client.f.add(listener);
    }

    @Override // com.scandit.datacapture.core.internal.module.https.NativeHttpsSession
    public final NativeHttpsSessionConfiguration getConfiguration() {
        return this.a;
    }

    @Override // com.scandit.datacapture.core.internal.module.https.NativeHttpsSession
    public final NativeHttpsSessionDelegate getDelegate() {
        ReentrantReadWriteLock.ReadLock readLock = this.c.readLock();
        readLock.lock();
        try {
            return this.d;
        } finally {
            readLock.unlock();
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.https.NativeHttpsSession
    public final void setDelegate(NativeHttpsSessionDelegate nativeHttpsSessionDelegate) {
        ReentrantReadWriteLock reentrantReadWriteLock = this.c;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            this.d = nativeHttpsSessionDelegate;
            Unit unit = Unit.INSTANCE;
        } finally {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.https.NativeHttpsSession
    public final void shouldAllowExpiredCertificates(boolean z) {
        ExtendedX509TrustManager[] extendedX509TrustManagerArr = (ExtendedX509TrustManager[]) ((M) ((O) ((com.scandit.datacapture.core.internal.module.https.scandit.client.e) this.b).e).a).a.getValue();
        ArrayList arrayList = new ArrayList();
        for (ExtendedX509TrustManager extendedX509TrustManager : extendedX509TrustManagerArr) {
            if (extendedX509TrustManager instanceof AllowExpiredCertificatesTrustManager) {
                arrayList.add(extendedX509TrustManager);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((AllowExpiredCertificatesTrustManager) it.next()).a(z);
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.https.NativeHttpsSession
    public final NativeHttpsTask startRequest(NativeHttpsRequest nativeHttpsRequest) {
        Intrinsics.checkNotNullParameter(nativeHttpsRequest, "request");
        com.scandit.datacapture.core.internal.module.https.scandit.client.e eVar = (com.scandit.datacapture.core.internal.module.https.scandit.client.e) this.b;
        eVar.getClass();
        Intrinsics.checkNotNullParameter(nativeHttpsRequest, "nativeHttpsRequest");
        I i = new I(((com.scandit.datacapture.core.internal.module.https.scandit.client.a) eVar.c).a.getAndIncrement(), nativeHttpsRequest, (O) eVar.e, eVar.h);
        ((com.scandit.datacapture.core.internal.module.https.scandit.client.e) this.b).a(i);
        return i;
    }
}
