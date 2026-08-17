package com.scandit.datacapture.frameworks.core.lifecycle;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefaultActivityLifecycle.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\u0018\u0000 \u001a2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001\u001aB\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\fH\u0016J\b\u0010\u0012\u001a\u00020\u0010H\u0016J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\fH\u0016J\b\u0010\u0014\u001a\u00020\u0010H\u0016J\b\u0010\u0015\u001a\u00020\u0010H\u0016J\b\u0010\u0016\u001a\u00020\u0010H\u0016J\b\u0010\u0017\u001a\u00020\u0010H\u0016J\b\u0010\u0018\u001a\u00020\u0010H\u0016J\b\u0010\u0019\u001a\u00020\u0010H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR2\u0010\n\u001a&\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f \r*\u0012\u0012\f\u0012\n \r*\u0004\u0018\u00010\f0\f\u0018\u00010\u000e0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/lifecycle/DefaultActivityLifecycle;", "Lcom/scandit/datacapture/frameworks/core/lifecycle/ActivityLifecycle;", "Lcom/scandit/datacapture/frameworks/core/lifecycle/ActivityLifecycleDispatcher;", "Lcom/scandit/datacapture/frameworks/core/lifecycle/AppState;", "()V", "isAppResumed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isResumed", "", "()Z", "observers", "", "Lcom/scandit/datacapture/frameworks/core/lifecycle/ActivityLifecycle$Observer;", "kotlin.jvm.PlatformType", "", "attach", "", "observer", "clearObservers", "detach", "dispatchOnCreate", "dispatchOnDestroy", "dispatchOnPause", "dispatchOnResume", "dispatchOnStart", "dispatchOnStop", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DefaultActivityLifecycle implements ActivityLifecycle, ActivityLifecycleDispatcher, AppState {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final DefaultActivityLifecycle instance = new DefaultActivityLifecycle();
    private final AtomicBoolean isAppResumed = new AtomicBoolean(false);
    private final List<ActivityLifecycle.Observer> observers = Collections.synchronizedList(new ArrayList());

    private DefaultActivityLifecycle() {
    }

    @Override // com.scandit.datacapture.frameworks.core.lifecycle.AppState
    public boolean isResumed() {
        return this.isAppResumed.get();
    }

    @Override // com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle
    public void attach(ActivityLifecycle.Observer observer) {
        Intrinsics.checkNotNullParameter(observer, "observer");
        List<ActivityLifecycle.Observer> observers = this.observers;
        Intrinsics.checkNotNullExpressionValue(observers, "observers");
        synchronized (observers) {
            this.observers.add(observer);
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle
    public void detach(ActivityLifecycle.Observer observer) {
        Intrinsics.checkNotNullParameter(observer, "observer");
        List<ActivityLifecycle.Observer> observers = this.observers;
        Intrinsics.checkNotNullExpressionValue(observers, "observers");
        synchronized (observers) {
            this.observers.remove(observer);
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle
    public void clearObservers() {
        List<ActivityLifecycle.Observer> observers = this.observers;
        Intrinsics.checkNotNullExpressionValue(observers, "observers");
        synchronized (observers) {
            this.observers.clear();
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycleDispatcher
    public void dispatchOnCreate() {
        List<ActivityLifecycle.Observer> observers = this.observers;
        Intrinsics.checkNotNullExpressionValue(observers, "observers");
        synchronized (observers) {
            Iterator<ActivityLifecycle.Observer> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().onActivityCreate();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycleDispatcher
    public void dispatchOnStart() {
        List<ActivityLifecycle.Observer> observers = this.observers;
        Intrinsics.checkNotNullExpressionValue(observers, "observers");
        synchronized (observers) {
            Iterator<ActivityLifecycle.Observer> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().onActivityStart();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycleDispatcher
    public void dispatchOnResume() {
        this.isAppResumed.set(true);
        List<ActivityLifecycle.Observer> observers = this.observers;
        Intrinsics.checkNotNullExpressionValue(observers, "observers");
        synchronized (observers) {
            Iterator<ActivityLifecycle.Observer> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().onActivityResume();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycleDispatcher
    public void dispatchOnPause() {
        this.isAppResumed.set(false);
        List<ActivityLifecycle.Observer> observers = this.observers;
        Intrinsics.checkNotNullExpressionValue(observers, "observers");
        synchronized (observers) {
            Iterator<ActivityLifecycle.Observer> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().onActivityPause();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycleDispatcher
    public void dispatchOnStop() {
        List<ActivityLifecycle.Observer> observers = this.observers;
        Intrinsics.checkNotNullExpressionValue(observers, "observers");
        synchronized (observers) {
            Iterator<ActivityLifecycle.Observer> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().onActivityStop();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycleDispatcher
    public void dispatchOnDestroy() {
        List<ActivityLifecycle.Observer> observers = this.observers;
        Intrinsics.checkNotNullExpressionValue(observers, "observers");
        synchronized (observers) {
            Iterator<ActivityLifecycle.Observer> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().onActivityDestroy();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* compiled from: DefaultActivityLifecycle.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/lifecycle/DefaultActivityLifecycle$Companion;", "", "()V", "instance", "Lcom/scandit/datacapture/frameworks/core/lifecycle/DefaultActivityLifecycle;", "getInstance", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DefaultActivityLifecycle getInstance() {
            return DefaultActivityLifecycle.instance;
        }
    }
}
