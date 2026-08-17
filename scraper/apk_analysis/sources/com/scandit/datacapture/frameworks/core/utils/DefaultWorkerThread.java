package com.scandit.datacapture.frameworks.core.utils;

import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.SentryStackFrame;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefaultWorkerThread.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\t\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH\u0002J\u0016\u0010\f\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/utils/DefaultWorkerThread;", "Lcom/scandit/datacapture/frameworks/core/utils/WorkerThread;", "()V", "executorService", "Ljava/util/concurrent/ExecutorService;", SentryStackFrame.JsonKeys.LOCK, "Ljava/lang/Object;", "cleanup", "", "postOnBackgroundThread", "lambda", "Lkotlin/Function0;", "runOnBackgroundThread", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DefaultWorkerThread implements WorkerThread {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final DefaultWorkerThread instance = new DefaultWorkerThread();
    private ExecutorService executorService;
    private final Object lock = new Object();

    @JvmStatic
    public static final DefaultWorkerThread getInstance() {
        return INSTANCE.getInstance();
    }

    private DefaultWorkerThread() {
    }

    @Override // com.scandit.datacapture.frameworks.core.utils.WorkerThread
    public void runOnBackgroundThread(Function0<Unit> lambda) {
        Intrinsics.checkNotNullParameter(lambda, "lambda");
        postOnBackgroundThread(lambda);
    }

    private final void postOnBackgroundThread(final Function0<Unit> lambda) {
        synchronized (this.lock) {
            if (this.executorService == null) {
                this.executorService = Executors.newSingleThreadExecutor();
            }
            ExecutorService executorService = this.executorService;
            if (executorService != null) {
                executorService.execute(new Runnable() { // from class: com.scandit.datacapture.frameworks.core.utils.DefaultWorkerThread$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        DefaultWorkerThread.postOnBackgroundThread$lambda$1$lambda$0(Function0.this);
                    }
                });
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void postOnBackgroundThread$lambda$1$lambda$0(Function0 tmp0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke();
    }

    @Override // com.scandit.datacapture.frameworks.core.utils.WorkerThread
    public void cleanup() {
        synchronized (this.lock) {
            ExecutorService executorService = this.executorService;
            if (executorService != null) {
                executorService.shutdown();
            }
            this.executorService = null;
            Unit unit = Unit.INSTANCE;
        }
    }

    /* compiled from: DefaultWorkerThread.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/utils/DefaultWorkerThread$Companion;", "", "()V", "instance", "Lcom/scandit/datacapture/frameworks/core/utils/DefaultWorkerThread;", "getInstance", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final DefaultWorkerThread getInstance() {
            return DefaultWorkerThread.instance;
        }
    }
}
