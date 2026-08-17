package com.scandit.datacapture.frameworks.core.common;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.UrlHandler;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import com.scandit.datacapture.frameworks.core.FrameworkModule;
import com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver;
import com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle;
import com.scandit.datacapture.frameworks.core.lifecycle.DefaultActivityLifecycle;
import com.scandit.datacapture.frameworks.core.ui.FrameworksDataCaptureView;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* compiled from: BaseFrameworkModule.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001e\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u00142\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0004J\u0016\u0010\u001c\u001a\u00020\u00172\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0004J\b\u0010\u001d\u001a\u00020\u0017H\u0004J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0014H\u0004J\b\u0010\u001f\u001a\u00020\u0017H\u0004J\b\u0010 \u001a\u00020\u0017H\u0004J\n\u0010!\u001a\u0004\u0018\u00010\u0011H\u0004J\u001c\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160#2\u0006\u0010\u001a\u001a\u00020\u0014H\u0004J\u0010\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u0011H\u0016J\b\u0010&\u001a\u00020\u0017H\u0016R\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0012\u001a\u001a\u0012\u0004\u0012\u00020\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u00150\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/common/BaseFrameworkModule;", "Lcom/scandit/datacapture/frameworks/core/FrameworkModule;", "Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver$Observer;", "Lcom/scandit/datacapture/frameworks/core/lifecycle/ActivityLifecycle$Observer;", "deserializationLifecycleObserver", "Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;", "lifecycle", "Lcom/scandit/datacapture/frameworks/core/lifecycle/ActivityLifecycle;", "(Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;Lcom/scandit/datacapture/frameworks/core/lifecycle/ActivityLifecycle;)V", "automaticAttachToDeserializationObserver", "", "getAutomaticAttachToDeserializationObserver", "()Z", "setAutomaticAttachToDeserializationObserver", "(Z)V", "contextRef", "Ljava/lang/ref/WeakReference;", "Landroid/content/Context;", "postSpecificViewCreationActions", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lkotlin/Function0;", "", "postViewCreationActions", "addPostSpecificViewCreationAction", "viewId", UrlHandler.ACTION, "addPostViewCreationAction", "attachForActivityLifecycleEvents", "clearPostSpecificViewCreationActions", "clearPostViewCreationActions", "detachForActivityLifecycleEvents", "getContext", "getPostSpecificViewCreationActions", "Lkotlin/sequences/Sequence;", "onCreate", "context", "onDestroy", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public abstract class BaseFrameworkModule implements FrameworkModule, DeserializationLifecycleObserver.Observer, ActivityLifecycle.Observer {
    private boolean automaticAttachToDeserializationObserver;
    private WeakReference<Context> contextRef;
    private final ActivityLifecycle lifecycle;
    private final ConcurrentHashMap<Integer, ConcurrentLinkedQueue<Function0<Unit>>> postSpecificViewCreationActions;
    private final ConcurrentLinkedQueue<Function0<Unit>> postViewCreationActions;

    public BaseFrameworkModule(DeserializationLifecycleObserver deserializationLifecycleObserver, ActivityLifecycle lifecycle) {
        Intrinsics.checkNotNullParameter(deserializationLifecycleObserver, "deserializationLifecycleObserver");
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        this.lifecycle = lifecycle;
        this.postViewCreationActions = new ConcurrentLinkedQueue<>();
        this.postSpecificViewCreationActions = new ConcurrentHashMap<>();
        this.contextRef = new WeakReference<>(null);
        this.automaticAttachToDeserializationObserver = true;
        if (getAutomaticAttachToDeserializationObserver()) {
            deserializationLifecycleObserver.attach(this);
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle.Observer
    public void onActivityCreate() {
        ActivityLifecycle.Observer.DefaultImpls.onActivityCreate(this);
    }

    public void onActivityDestroy() {
        ActivityLifecycle.Observer.DefaultImpls.onActivityDestroy(this);
    }

    public void onActivityPause() {
        ActivityLifecycle.Observer.DefaultImpls.onActivityPause(this);
    }

    public void onActivityResume() {
        ActivityLifecycle.Observer.DefaultImpls.onActivityResume(this);
    }

    @Override // com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle.Observer
    public void onActivityStart() {
        ActivityLifecycle.Observer.DefaultImpls.onActivityStart(this);
    }

    public void onActivityStop() {
        ActivityLifecycle.Observer.DefaultImpls.onActivityStop(this);
    }

    public void onAddModeToContext(String str) {
        DeserializationLifecycleObserver.Observer.DefaultImpls.onAddModeToContext(this, str);
    }

    public void onAddOverlayToView(FrameworksDataCaptureView frameworksDataCaptureView, String str) {
        DeserializationLifecycleObserver.Observer.DefaultImpls.onAddOverlayToView(this, frameworksDataCaptureView, str);
    }

    public void onAllModesRemovedFromContext() {
        DeserializationLifecycleObserver.Observer.DefaultImpls.onAllModesRemovedFromContext(this);
    }

    @Override // com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver.Observer
    public void onDataCaptureViewDeserialized(DataCaptureView dataCaptureView) {
        DeserializationLifecycleObserver.Observer.DefaultImpls.onDataCaptureViewDeserialized(this, dataCaptureView);
    }

    public void onOverlayRemovedFromView(DataCaptureOverlay dataCaptureOverlay) {
        DeserializationLifecycleObserver.Observer.DefaultImpls.onOverlayRemovedFromView(this, dataCaptureOverlay);
    }

    public void onRemoveModeFromContext(String str) {
        DeserializationLifecycleObserver.Observer.DefaultImpls.onRemoveModeFromContext(this, str);
    }

    public /* synthetic */ BaseFrameworkModule(DeserializationLifecycleObserver deserializationLifecycleObserver, DefaultActivityLifecycle defaultActivityLifecycle, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(deserializationLifecycleObserver, (i & 2) != 0 ? DefaultActivityLifecycle.INSTANCE.getInstance() : defaultActivityLifecycle);
    }

    public boolean getAutomaticAttachToDeserializationObserver() {
        return this.automaticAttachToDeserializationObserver;
    }

    public void setAutomaticAttachToDeserializationObserver(boolean z) {
        this.automaticAttachToDeserializationObserver = z;
    }

    @Override // com.scandit.datacapture.frameworks.core.FrameworkModule
    public void onCreate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.contextRef = new WeakReference<>(context);
    }

    protected final Context getContext() {
        return this.contextRef.get();
    }

    @Override // com.scandit.datacapture.frameworks.core.FrameworkModule
    public void onDestroy() {
        clearPostViewCreationActions();
        this.postSpecificViewCreationActions.clear();
    }

    protected final void addPostViewCreationAction(Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        this.postViewCreationActions.add(action);
    }

    protected final void addPostSpecificViewCreationAction(int viewId, Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        if (this.postSpecificViewCreationActions.get(Integer.valueOf(viewId)) == null) {
            this.postSpecificViewCreationActions.put(Integer.valueOf(viewId), new ConcurrentLinkedQueue<>());
        }
        ConcurrentLinkedQueue<Function0<Unit>> concurrentLinkedQueue = this.postSpecificViewCreationActions.get(Integer.valueOf(viewId));
        if (concurrentLinkedQueue != null) {
            concurrentLinkedQueue.add(action);
        }
    }

    protected final Sequence<Function0<Unit>> getPostSpecificViewCreationActions(int viewId) {
        return SequencesKt.sequence(new BaseFrameworkModule$getPostSpecificViewCreationActions$1(this.postSpecificViewCreationActions.get(Integer.valueOf(viewId)), null));
    }

    protected final void attachForActivityLifecycleEvents() {
        this.lifecycle.attach(this);
    }

    protected final void detachForActivityLifecycleEvents() {
        this.lifecycle.detach(this);
    }

    protected final void clearPostViewCreationActions() {
        this.postViewCreationActions.clear();
    }

    protected final void clearPostSpecificViewCreationActions(int viewId) {
        Iterator<Map.Entry<Integer, ConcurrentLinkedQueue<Function0<Unit>>>> it = this.postSpecificViewCreationActions.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, ConcurrentLinkedQueue<Function0<Unit>>> next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            Integer key = next.getKey();
            if (key != null && key.intValue() == viewId) {
                it.remove();
            }
        }
    }
}
