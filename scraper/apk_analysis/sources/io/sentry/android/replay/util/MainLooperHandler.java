package io.sentry.android.replay.util;

import android.os.Handler;
import android.os.Looper;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MainLooperHandler.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010\r\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\b\u0010\u000b\u001a\u0004\u0018\u00010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lio/sentry/android/replay/util/MainLooperHandler;", "", "looper", "Landroid/os/Looper;", "(Landroid/os/Looper;)V", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "post", "", "runnable", "Ljava/lang/Runnable;", "postDelayed", "delay", "", "removeCallbacks", "", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class MainLooperHandler {
    public static final int $stable = 8;
    private final Handler handler;

    /* JADX WARN: Multi-variable type inference failed */
    public MainLooperHandler() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public MainLooperHandler(Looper looper) {
        Intrinsics.checkNotNullParameter(looper, "looper");
        this.handler = new Handler(looper);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ MainLooperHandler(android.os.Looper r1, int r2, kotlin.jvm.internal.DefaultConstructorMarker r3) {
        /*
            r0 = this;
            r2 = r2 & 1
            if (r2 == 0) goto Ld
            android.os.Looper r1 = android.os.Looper.getMainLooper()
            java.lang.String r2 = "getMainLooper(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
        Ld:
            r0.<init>(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.replay.util.MainLooperHandler.<init>(android.os.Looper, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final Handler getHandler() {
        return this.handler;
    }

    public final boolean post(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        return this.handler.post(runnable);
    }

    public final boolean postDelayed(Runnable runnable, long delay) {
        Handler handler = this.handler;
        if (runnable == null) {
            return false;
        }
        return handler.postDelayed(runnable, delay);
    }

    public final void removeCallbacks(Runnable runnable) {
        Handler handler = this.handler;
        if (runnable == null) {
            return;
        }
        handler.removeCallbacks(runnable);
    }
}
