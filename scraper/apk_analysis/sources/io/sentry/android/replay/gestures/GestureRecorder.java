package io.sentry.android.replay.gestures;

import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.ISentryLifecycleToken;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.android.replay.OnRootViewsChangedListener;
import io.sentry.android.replay.WindowsKt;
import io.sentry.android.replay.util.FixedWindowCallback;
import io.sentry.rrweb.RRWebOptionsEvent;
import io.sentry.util.AutoClosableReentrantLock;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GestureRecorder.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0016B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0006\u0010\u0013\u001a\u00020\u000fJ\f\u0010\u0014\u001a\u00020\u000f*\u00020\nH\u0002J\f\u0010\u0015\u001a\u00020\u000f*\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0007\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bj\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t`\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lio/sentry/android/replay/gestures/GestureRecorder;", "Lio/sentry/android/replay/OnRootViewsChangedListener;", RRWebOptionsEvent.EVENT_TAG, "Lio/sentry/SentryOptions;", "touchRecorderCallback", "Lio/sentry/android/replay/gestures/TouchRecorderCallback;", "(Lio/sentry/SentryOptions;Lio/sentry/android/replay/gestures/TouchRecorderCallback;)V", "rootViews", "Ljava/util/ArrayList;", "Ljava/lang/ref/WeakReference;", "Landroid/view/View;", "Lkotlin/collections/ArrayList;", "rootViewsLock", "Lio/sentry/util/AutoClosableReentrantLock;", "onRootViewsChanged", "", "root", "added", "", "stop", "startGestureTracking", "stopGestureTracking", "SentryReplayGestureRecorder", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class GestureRecorder implements OnRootViewsChangedListener {
    public static final int $stable = 8;
    private final SentryOptions options;
    private final ArrayList<WeakReference<View>> rootViews;
    private final AutoClosableReentrantLock rootViewsLock;
    private final TouchRecorderCallback touchRecorderCallback;

    public GestureRecorder(SentryOptions options, TouchRecorderCallback touchRecorderCallback) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(touchRecorderCallback, "touchRecorderCallback");
        this.options = options;
        this.touchRecorderCallback = touchRecorderCallback;
        this.rootViews = new ArrayList<>();
        this.rootViewsLock = new AutoClosableReentrantLock();
    }

    @Override // io.sentry.android.replay.OnRootViewsChangedListener
    public void onRootViewsChanged(final View root, boolean added) {
        Intrinsics.checkNotNullParameter(root, "root");
        ISentryLifecycleToken acquire = this.rootViewsLock.acquire();
        try {
            ISentryLifecycleToken iSentryLifecycleToken = acquire;
            if (added) {
                this.rootViews.add(new WeakReference<>(root));
                startGestureTracking(root);
                Unit unit = Unit.INSTANCE;
            } else {
                stopGestureTracking(root);
                Boolean.valueOf(CollectionsKt.removeAll((List) this.rootViews, (Function1) new Function1<WeakReference<View>, Boolean>() { // from class: io.sentry.android.replay.gestures.GestureRecorder$onRootViewsChanged$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(WeakReference<View> it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return Boolean.valueOf(Intrinsics.areEqual(it.get(), root));
                    }
                }));
            }
            AutoCloseableKt.closeFinally(acquire, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(acquire, th);
                throw th2;
            }
        }
    }

    public final void stop() {
        ISentryLifecycleToken acquire = this.rootViewsLock.acquire();
        try {
            ISentryLifecycleToken iSentryLifecycleToken = acquire;
            Iterator<T> it = this.rootViews.iterator();
            while (it.hasNext()) {
                View view = (View) ((WeakReference) it.next()).get();
                if (view != null) {
                    Intrinsics.checkNotNull(view);
                    stopGestureTracking(view);
                }
            }
            this.rootViews.clear();
            Unit unit = Unit.INSTANCE;
            AutoCloseableKt.closeFinally(acquire, null);
        } finally {
        }
    }

    private final void startGestureTracking(View view) {
        Window phoneWindow = WindowsKt.getPhoneWindow(view);
        if (phoneWindow == null) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Window is invalid, not tracking gestures", new Object[0]);
            return;
        }
        Window.Callback callback = phoneWindow.getCallback();
        if (callback instanceof SentryReplayGestureRecorder) {
            return;
        }
        phoneWindow.setCallback(new SentryReplayGestureRecorder(this.options, this.touchRecorderCallback, callback));
    }

    private final void stopGestureTracking(View view) {
        Window phoneWindow = WindowsKt.getPhoneWindow(view);
        if (phoneWindow == null) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Window was null in stopGestureTracking", new Object[0]);
            return;
        }
        Window.Callback callback = phoneWindow.getCallback();
        if (callback instanceof SentryReplayGestureRecorder) {
            phoneWindow.setCallback(((SentryReplayGestureRecorder) callback).delegate);
        }
    }

    /* compiled from: GestureRecorder.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lio/sentry/android/replay/gestures/GestureRecorder$SentryReplayGestureRecorder;", "Lio/sentry/android/replay/util/FixedWindowCallback;", RRWebOptionsEvent.EVENT_TAG, "Lio/sentry/SentryOptions;", "touchRecorderCallback", "Lio/sentry/android/replay/gestures/TouchRecorderCallback;", "delegate", "Landroid/view/Window$Callback;", "(Lio/sentry/SentryOptions;Lio/sentry/android/replay/gestures/TouchRecorderCallback;Landroid/view/Window$Callback;)V", "dispatchTouchEvent", "", "event", "Landroid/view/MotionEvent;", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class SentryReplayGestureRecorder extends FixedWindowCallback {
        public static final int $stable = 8;
        private final SentryOptions options;
        private final TouchRecorderCallback touchRecorderCallback;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SentryReplayGestureRecorder(SentryOptions options, TouchRecorderCallback touchRecorderCallback, Window.Callback callback) {
            super(callback);
            Intrinsics.checkNotNullParameter(options, "options");
            this.options = options;
            this.touchRecorderCallback = touchRecorderCallback;
        }

        @Override // io.sentry.android.replay.util.FixedWindowCallback, android.view.Window.Callback
        public boolean dispatchTouchEvent(MotionEvent event) {
            if (event != null) {
                MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(event);
                Intrinsics.checkNotNullExpressionValue(obtainNoHistory, "obtainNoHistory(...)");
                try {
                    TouchRecorderCallback touchRecorderCallback = this.touchRecorderCallback;
                    if (touchRecorderCallback != null) {
                        touchRecorderCallback.onTouchEvent(obtainNoHistory);
                    }
                } finally {
                    try {
                    } finally {
                    }
                }
            }
            return super.dispatchTouchEvent(event);
        }
    }
}
