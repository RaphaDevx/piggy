package com.scandit.datacapture.core.internal.sdk.data;

import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* loaded from: classes2.dex */
public final class a implements Subscription {
    public final AtomicBoolean a = new AtomicBoolean(false);
    public final /* synthetic */ DisposableResource b;

    public a(DisposableResource disposableResource) {
        this.b = disposableResource;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.data.Subscription
    public final void dispose() {
        Object obj;
        Object obj2;
        Function1 function1;
        if (this.a.compareAndSet(false, true)) {
            obj = this.b.e;
            DisposableResource disposableResource = this.b;
            synchronized (obj) {
                disposableResource.counter = disposableResource.getCounter() - 1;
                if (disposableResource.getCounter() == 0) {
                    obj2 = disposableResource.d;
                    if (obj2 != null) {
                        function1 = disposableResource.b;
                        function1.invoke(obj2);
                    }
                    disposableResource.d = null;
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001f, code lost:
    
        r2 = r1.d;
     */
    @Override // com.scandit.datacapture.core.internal.sdk.data.Subscription
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void use(kotlin.jvm.functions.Function1 r3) {
        /*
            r2 = this;
            java.lang.String r0 = "action"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.util.concurrent.atomic.AtomicBoolean r0 = r2.a
            boolean r0 = r0.get()
            if (r0 == 0) goto Le
            return
        Le:
            com.scandit.datacapture.core.internal.sdk.data.DisposableResource r0 = r2.b
            java.lang.Object r0 = com.scandit.datacapture.core.internal.sdk.data.DisposableResource.access$getLock$p(r0)
            com.scandit.datacapture.core.internal.sdk.data.DisposableResource r1 = r2.b
            monitor-enter(r0)
            java.util.concurrent.atomic.AtomicBoolean r2 = r2.a     // Catch: java.lang.Throwable -> L2c
            boolean r2 = r2.get()     // Catch: java.lang.Throwable -> L2c
            if (r2 != 0) goto L28
            java.lang.Object r2 = com.scandit.datacapture.core.internal.sdk.data.DisposableResource.access$getSource$p(r1)     // Catch: java.lang.Throwable -> L2c
            if (r2 == 0) goto L28
            r3.invoke(r2)     // Catch: java.lang.Throwable -> L2c
        L28:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L2c
            monitor-exit(r0)
            return
        L2c:
            r2 = move-exception
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.sdk.data.a.use(kotlin.jvm.functions.Function1):void");
    }
}
