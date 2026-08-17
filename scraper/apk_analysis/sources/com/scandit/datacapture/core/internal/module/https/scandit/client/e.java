package com.scandit.datacapture.core.internal.module.https.scandit.client;

import com.scandit.datacapture.core.internal.module.https.NativeHttpsSessionConfiguration;
import com.scandit.datacapture.core.internal.module.https.scandit.AbstractC0539k;
import com.scandit.datacapture.core.internal.module.https.scandit.I;
import com.scandit.datacapture.core.internal.module.https.scandit.N;
import com.scandit.datacapture.core.internal.module.https.scandit.O;
import com.scandit.datacapture.core.internal.module.https.scandit.T;
import com.scandit.datacapture.core.internal.module.https.scandit.U;
import com.scandit.datacapture.core.time.TimeInterval;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class e implements b {
    public final TimeInterval a;
    public final Executor b;
    public final f c;
    public final Timer d;
    public final N e;
    public final Set f;
    public final Set g;
    public final c h;

    public e(NativeHttpsSessionConfiguration config) {
        Intrinsics.checkNotNullParameter(config, "config");
        TimeInterval timeout = TimeInterval.INSTANCE.seconds(config.getTimeoutInterval());
        ExecutorService taskExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Intrinsics.checkNotNullExpressionValue(taskExecutor, "newFixedThreadPool(...)");
        a taskIdGenerator = new a();
        Timer timeoutTimer = new Timer();
        O sslSocketHandler = new O();
        Intrinsics.checkNotNullParameter(timeout, "timeout");
        Intrinsics.checkNotNullParameter(taskExecutor, "taskExecutor");
        Intrinsics.checkNotNullParameter(taskIdGenerator, "taskIdGenerator");
        Intrinsics.checkNotNullParameter(timeoutTimer, "timeoutTimer");
        Intrinsics.checkNotNullParameter(sslSocketHandler, "sslSocketHandler");
        this.a = timeout;
        this.b = taskExecutor;
        this.c = taskIdGenerator;
        this.d = timeoutTimer;
        this.e = sslSocketHandler;
        Set synchronizedSet = Collections.synchronizedSet(new LinkedHashSet());
        Intrinsics.checkNotNullExpressionValue(synchronizedSet, "synchronizedSet(...)");
        this.f = synchronizedSet;
        Set synchronizedSet2 = Collections.synchronizedSet(new LinkedHashSet());
        Intrinsics.checkNotNullExpressionValue(synchronizedSet2, "synchronizedSet(...)");
        this.g = synchronizedSet2;
        this.h = new c(this);
    }

    public final void a(final I task) {
        Intrinsics.checkNotNullParameter(task, "task");
        this.b.execute(new Runnable() { // from class: com.scandit.datacapture.core.internal.module.https.scandit.client.e$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                e.a(e.this, task);
            }
        });
    }

    public static final void a(e this$0, AbstractC0539k task) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(task, "$task");
        for (l lVar : this$0.g) {
            this$0.h.a(new U(task, lVar));
            com.scandit.datacapture.core.internal.module.https.scandit.interceptors.a aVar = (com.scandit.datacapture.core.internal.module.https.scandit.interceptors.a) lVar;
            aVar.getClass();
            Intrinsics.checkNotNullParameter(task, "task");
            com.scandit.datacapture.core.internal.module.https.a a = ((com.scandit.datacapture.core.internal.module.https.d) aVar.a).a();
            k jVar = a == com.scandit.datacapture.core.internal.module.https.a.a ? new j("No connection") : (a != com.scandit.datacapture.core.internal.module.https.a.b || aVar.b) ? i.a : new j("Cellular not allowed");
            this$0.h.a(new T(task, lVar, jVar));
            if (jVar instanceof j) {
                task.a(((j) jVar).a);
                return;
            }
        }
        Timer timer = this$0.d;
        long asMillis = this$0.a.asMillis();
        d dVar = new d(task);
        timer.schedule(dVar, asMillis);
        task.start();
        dVar.cancel();
    }
}
