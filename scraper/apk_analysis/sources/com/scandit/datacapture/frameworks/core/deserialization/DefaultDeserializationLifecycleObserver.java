package com.scandit.datacapture.frameworks.core.deserialization;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver;
import com.scandit.datacapture.frameworks.core.handlers.DefaultDataCaptureViewHandler;
import com.scandit.datacapture.frameworks.core.ui.FrameworksDataCaptureView;
import com.scandit.datacapture.frameworks.core.utils.DefaultFrameworksLog;
import com.tealium.library.DataSources;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefaultDeserializationLifecycleObserver.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0006\u0010\u000b\u001a\u00020\tJ\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u000e\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000fJ\u0006\u0010\u0014\u001a\u00020\tJ\u0010\u0010\u0015\u001a\u00020\t2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u000e\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fR2\u0010\u0003\u001a&\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005 \u0006*\u0012\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005\u0018\u00010\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/deserialization/DefaultDeserializationLifecycleObserver;", "Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;", "()V", "observers", "", "Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver$Observer;", "kotlin.jvm.PlatformType", "", "attach", "", "observer", "clearObservers", "detach", "dispatchAddModeToContext", "modeJson", "", "dispatchAddOverlayToView", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/frameworks/core/ui/FrameworksDataCaptureView;", "overlayJson", "dispatchAllModesRemoved", "dispatchDataCaptureViewDeserialized", "dataCaptureView", "Lcom/scandit/datacapture/core/ui/DataCaptureView;", "dispatchOverlayRemovedFromView", "overlay", "Lcom/scandit/datacapture/core/ui/overlay/DataCaptureOverlay;", "dispatchRemoveModeFromContext", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DefaultDeserializationLifecycleObserver implements DeserializationLifecycleObserver {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final DefaultDeserializationLifecycleObserver instance = new DefaultDeserializationLifecycleObserver();
    private final List<DeserializationLifecycleObserver.Observer> observers = Collections.synchronizedList(new ArrayList());

    private DefaultDeserializationLifecycleObserver() {
    }

    @Override // com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver
    public void attach(DeserializationLifecycleObserver.Observer observer) {
        Intrinsics.checkNotNullParameter(observer, "observer");
        this.observers.add(observer);
        FrameworksDataCaptureView topmostDataCaptureView = DefaultDataCaptureViewHandler.INSTANCE.getInstance().getTopmostDataCaptureView();
        if (topmostDataCaptureView != null) {
            observer.onDataCaptureViewDeserialized(topmostDataCaptureView.getView());
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver
    public void detach(DeserializationLifecycleObserver.Observer observer) {
        Intrinsics.checkNotNullParameter(observer, "observer");
        this.observers.remove(observer);
    }

    public final void clearObservers() {
        List<DeserializationLifecycleObserver.Observer> observers = this.observers;
        Intrinsics.checkNotNullExpressionValue(observers, "observers");
        synchronized (observers) {
            this.observers.clear();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void dispatchAddModeToContext(String modeJson) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        List<DeserializationLifecycleObserver.Observer> observers = this.observers;
        Intrinsics.checkNotNullExpressionValue(observers, "observers");
        synchronized (observers) {
            Iterator<DeserializationLifecycleObserver.Observer> it = this.observers.iterator();
            while (it.hasNext()) {
                try {
                    it.next().onAddModeToContext(modeJson);
                } catch (Exception e) {
                    DefaultFrameworksLog.INSTANCE.getInstance().error(e);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void dispatchRemoveModeFromContext(String modeJson) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        List<DeserializationLifecycleObserver.Observer> observers = this.observers;
        Intrinsics.checkNotNullExpressionValue(observers, "observers");
        synchronized (observers) {
            Iterator<DeserializationLifecycleObserver.Observer> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().onRemoveModeFromContext(modeJson);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void dispatchDataCaptureViewDeserialized(DataCaptureView dataCaptureView) {
        List<DeserializationLifecycleObserver.Observer> observers = this.observers;
        Intrinsics.checkNotNullExpressionValue(observers, "observers");
        synchronized (observers) {
            Iterator<DeserializationLifecycleObserver.Observer> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().onDataCaptureViewDeserialized(dataCaptureView);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void dispatchAllModesRemoved() {
        List<DeserializationLifecycleObserver.Observer> observers = this.observers;
        Intrinsics.checkNotNullExpressionValue(observers, "observers");
        synchronized (observers) {
            Iterator<DeserializationLifecycleObserver.Observer> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().onAllModesRemovedFromContext();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void dispatchAddOverlayToView(FrameworksDataCaptureView view, String overlayJson) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(overlayJson, "overlayJson");
        List<DeserializationLifecycleObserver.Observer> observers = this.observers;
        Intrinsics.checkNotNullExpressionValue(observers, "observers");
        synchronized (observers) {
            Iterator<DeserializationLifecycleObserver.Observer> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().onAddOverlayToView(view, overlayJson);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void dispatchOverlayRemovedFromView(DataCaptureOverlay overlay) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        List<DeserializationLifecycleObserver.Observer> observers = this.observers;
        Intrinsics.checkNotNullExpressionValue(observers, "observers");
        synchronized (observers) {
            Iterator<DeserializationLifecycleObserver.Observer> it = this.observers.iterator();
            while (it.hasNext()) {
                it.next().onOverlayRemovedFromView(overlay);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* compiled from: DefaultDeserializationLifecycleObserver.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/deserialization/DefaultDeserializationLifecycleObserver$Companion;", "", "()V", "instance", "Lcom/scandit/datacapture/frameworks/core/deserialization/DefaultDeserializationLifecycleObserver;", "getInstance", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DefaultDeserializationLifecycleObserver getInstance() {
            return DefaultDeserializationLifecycleObserver.instance;
        }
    }
}
