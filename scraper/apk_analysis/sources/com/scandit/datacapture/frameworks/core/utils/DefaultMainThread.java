package com.scandit.datacapture.frameworks.core.utils;

import android.os.Handler;
import android.os.Looper;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefaultMainThread.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\tH\u0016J\u001a\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0002J\u0018\u0010\u0010\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0016\u0010\u0011\u001a\u00020\t2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u0013H\u0016J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/utils/DefaultMainThread;", "Lcom/scandit/datacapture/frameworks/core/utils/MainThread;", "()V", "isOnMainThread", "", "()Z", "mainHandler", "Landroid/os/Handler;", "cancelDelayedRun", "", "runnable", "Ljava/lang/Runnable;", "cleanup", "postOnMainThread", "delay", "", "runDelayedOnMainThread", "runOnMainThread", "lambda", "Lkotlin/Function0;", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DefaultMainThread implements MainThread {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final DefaultMainThread instance = new DefaultMainThread();
    private Handler mainHandler;

    @JvmStatic
    public static final DefaultMainThread getInstance() {
        return INSTANCE.getInstance();
    }

    private DefaultMainThread() {
    }

    private final boolean isOnMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    @Override // com.scandit.datacapture.frameworks.core.utils.MainThread
    public void runOnMainThread(final Function0<Unit> lambda) {
        Intrinsics.checkNotNullParameter(lambda, "lambda");
        if (isOnMainThread()) {
            lambda.invoke();
        } else {
            postOnMainThread$default(this, new Runnable() { // from class: com.scandit.datacapture.frameworks.core.utils.DefaultMainThread$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultMainThread.runOnMainThread$lambda$0(Function0.this);
                }
            }, 0L, 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void runOnMainThread$lambda$0(Function0 tmp0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke();
    }

    @Override // com.scandit.datacapture.frameworks.core.utils.MainThread
    public void runOnMainThread(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        if (isOnMainThread()) {
            runnable.run();
        } else {
            postOnMainThread$default(this, runnable, 0L, 2, null);
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.utils.MainThread
    public void runDelayedOnMainThread(Runnable runnable, long delay) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        postOnMainThread(runnable, delay);
    }

    @Override // com.scandit.datacapture.frameworks.core.utils.MainThread
    public void cancelDelayedRun(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        Handler handler = this.mainHandler;
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }

    static /* synthetic */ void postOnMainThread$default(DefaultMainThread defaultMainThread, Runnable runnable, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = -1;
        }
        defaultMainThread.postOnMainThread(runnable, j);
    }

    private final void postOnMainThread(Runnable runnable, long delay) {
        synchronized (this) {
            if (this.mainHandler == null) {
                this.mainHandler = new Handler(Looper.getMainLooper());
            }
            Unit unit = Unit.INSTANCE;
        }
        if (delay == -1) {
            Handler handler = this.mainHandler;
            if (handler != null) {
                handler.post(runnable);
                return;
            }
            return;
        }
        Handler handler2 = this.mainHandler;
        if (handler2 != null) {
            handler2.postDelayed(runnable, delay);
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.utils.MainThread
    public void cleanup() {
        synchronized (this) {
            Handler handler = this.mainHandler;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            this.mainHandler = null;
            Unit unit = Unit.INSTANCE;
        }
    }

    /* compiled from: DefaultMainThread.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/utils/DefaultMainThread$Companion;", "", "()V", "instance", "Lcom/scandit/datacapture/frameworks/core/utils/DefaultMainThread;", "getInstance", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final DefaultMainThread getInstance() {
            return DefaultMainThread.instance;
        }
    }
}
