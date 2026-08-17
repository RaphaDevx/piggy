package com.salesforce.marketingcloud.sfmcsdk.components.behaviors;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorManagerImpl;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import com.tealium.library.DataSources;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: LifecycleListener.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u001d2\u00020\u00012\u00020\u0002:\u0001\u001dB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0015\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0011H\u0016J\u0010\u0010\u0017\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u001e"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/LifecycleListener;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "inForeground", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getInForeground", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "setInForeground", "(Ljava/util/concurrent/atomic/AtomicBoolean;)V", "onActivityCreated", "", DataSources.EventTypeValue.ACTIVITY_EVENT_TYPE, "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "onStart", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onStop", "Companion", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class LifecycleListener implements DefaultLifecycleObserver, Application.ActivityLifecycleCallbacks {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "~$LifecycleListener";
    private static LifecycleListener instance;
    private Context context;
    private AtomicBoolean inForeground;

    public /* synthetic */ LifecycleListener(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(outState, "outState");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    private LifecycleListener(Context context) {
        this.context = context;
        this.inForeground = new AtomicBoolean(false);
    }

    public final AtomicBoolean getInForeground() {
        return this.inForeground;
    }

    public final void setInForeground(AtomicBoolean atomicBoolean) {
        Intrinsics.checkNotNullParameter(atomicBoolean, "<set-?>");
        this.inForeground = atomicBoolean;
    }

    /* compiled from: LifecycleListener.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R \u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0000@\u0000X\u0081\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/LifecycleListener$Companion;", "", "()V", "TAG", "", "instance", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/LifecycleListener;", "getInstance$sfmcsdk_release", "()Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/LifecycleListener;", "setInstance$sfmcsdk_release", "(Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/LifecycleListener;)V", "getInstance", "context", "Landroid/content/Context;", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LifecycleListener getInstance$sfmcsdk_release() {
            return LifecycleListener.instance;
        }

        public final void setInstance$sfmcsdk_release(LifecycleListener lifecycleListener) {
            LifecycleListener.instance = lifecycleListener;
        }

        public final LifecycleListener getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (getInstance$sfmcsdk_release() == null) {
                setInstance$sfmcsdk_release(new LifecycleListener(context, null));
            }
            LifecycleListener instance$sfmcsdk_release = getInstance$sfmcsdk_release();
            Intrinsics.checkNotNull(instance$sfmcsdk_release, "null cannot be cast to non-null type com.salesforce.marketingcloud.sfmcsdk.components.behaviors.LifecycleListener");
            return instance$sfmcsdk_release;
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStart(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        super.onStart(owner);
        if (this.inForeground.getAndSet(true)) {
            return;
        }
        SFMCSdkLogger.INSTANCE.d(TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.components.behaviors.LifecycleListener$onStart$1
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "Application came into the foreground.";
            }
        });
        BehaviorManagerImpl.INSTANCE.notifyBehavior$sfmcsdk_release(this.context, BehaviorType.APPLICATION_FOREGROUNDED, new Bundle());
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStop(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        if (this.inForeground.getAndSet(false)) {
            SFMCSdkLogger.INSTANCE.d(TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.components.behaviors.LifecycleListener$onStop$1
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "Application went into the background.";
                }
            });
            BehaviorManagerImpl.INSTANCE.notifyBehavior$sfmcsdk_release(this.context, BehaviorType.APPLICATION_BACKGROUNDED, new Bundle());
        }
        super.onStop(owner);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        BehaviorManagerImpl.Companion companion = BehaviorManagerImpl.INSTANCE;
        Context context = this.context;
        BehaviorType behaviorType = BehaviorType.SCREEN_ENTRY;
        Bundle bundle = new Bundle();
        bundle.putString("screen_name", Reflection.getOrCreateKotlinClass(activity.getClass()).getSimpleName());
        Unit unit = Unit.INSTANCE;
        companion.notifyBehavior$sfmcsdk_release(context, behaviorType, bundle);
    }
}
