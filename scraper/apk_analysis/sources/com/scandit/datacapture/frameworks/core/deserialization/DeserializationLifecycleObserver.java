package com.scandit.datacapture.frameworks.core.deserialization;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import com.scandit.datacapture.frameworks.core.ui.FrameworksDataCaptureView;
import com.tealium.library.DataSources;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeserializationLifecycleObserver.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\u0007J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;", "", "attach", "", "observer", "Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver$Observer;", "detach", "Observer", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface DeserializationLifecycleObserver {

    /* compiled from: DeserializationLifecycleObserver.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0016J\b\u0010\n\u001a\u00020\u0003H\u0016J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0012"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver$Observer;", "", "onAddModeToContext", "", "modeJson", "", "onAddOverlayToView", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/frameworks/core/ui/FrameworksDataCaptureView;", "overlayJson", "onAllModesRemovedFromContext", "onDataCaptureViewDeserialized", "dataCaptureView", "Lcom/scandit/datacapture/core/ui/DataCaptureView;", "onOverlayRemovedFromView", "overlay", "Lcom/scandit/datacapture/core/ui/overlay/DataCaptureOverlay;", "onRemoveModeFromContext", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public interface Observer {

        /* compiled from: DeserializationLifecycleObserver.kt */
        @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class DefaultImpls {
            public static void onAddModeToContext(Observer observer, String modeJson) {
                Intrinsics.checkNotNullParameter(modeJson, "modeJson");
            }

            public static void onAddOverlayToView(Observer observer, FrameworksDataCaptureView view, String overlayJson) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(overlayJson, "overlayJson");
            }

            public static void onAllModesRemovedFromContext(Observer observer) {
            }

            public static void onDataCaptureViewDeserialized(Observer observer, DataCaptureView dataCaptureView) {
            }

            public static void onOverlayRemovedFromView(Observer observer, DataCaptureOverlay overlay) {
                Intrinsics.checkNotNullParameter(overlay, "overlay");
            }

            public static void onRemoveModeFromContext(Observer observer, String modeJson) {
                Intrinsics.checkNotNullParameter(modeJson, "modeJson");
            }
        }

        void onAddModeToContext(String modeJson);

        void onAddOverlayToView(FrameworksDataCaptureView view, String overlayJson);

        void onAllModesRemovedFromContext();

        void onDataCaptureViewDeserialized(DataCaptureView dataCaptureView);

        void onOverlayRemovedFromView(DataCaptureOverlay overlay);

        void onRemoveModeFromContext(String modeJson);
    }

    void attach(Observer observer);

    void detach(Observer observer);
}
