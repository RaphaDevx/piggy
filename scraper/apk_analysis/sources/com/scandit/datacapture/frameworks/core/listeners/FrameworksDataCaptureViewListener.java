package com.scandit.datacapture.frameworks.core.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.ui.DataCaptureViewListener;
import com.scandit.datacapture.core.ui.orientation.DeviceOrientationMapper;
import com.scandit.datacapture.core.ui.orientation.DeviceOrientationUtilsKt;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksDataCaptureViewListener.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u000bJ \u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/listeners/FrameworksDataCaptureViewListener;", "Lcom/scandit/datacapture/core/ui/DataCaptureViewListener;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksDataCaptureViewListener.FIELD_VIEW_ID, "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;I)V", "isEnabled", "Ljava/util/concurrent/atomic/AtomicReference;", "", "disable", "", "enable", "onSizeChanged", "width", "height", "screenRotation", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksDataCaptureViewListener implements DataCaptureViewListener {
    private static final String FIELD_HEIGHT = "height";
    private static final String FIELD_ORIENTATION = "orientation";
    private static final String FIELD_SIZE = "size";
    private static final String FIELD_VIEW_ID = "viewId";
    private static final String FIELD_WIDTH = "width";
    public static final String ON_SIZE_CHANGED_EVENT_NAME = "DataCaptureViewListener.onSizeChanged";
    private final Emitter emitter;
    private final AtomicReference<Boolean> isEnabled;
    private final int viewId;

    public FrameworksDataCaptureViewListener(Emitter emitter, int i) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        this.emitter = emitter;
        this.viewId = i;
        this.isEnabled = new AtomicReference<>(false);
    }

    public final void enable() {
        this.isEnabled.set(true);
    }

    public final void disable() {
        this.isEnabled.set(false);
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewListener
    public void onSizeChanged(int width, int height, int screenRotation) {
        if (this.isEnabled.get().booleanValue() && this.emitter.hasListenersForEvent(ON_SIZE_CHANGED_EVENT_NAME)) {
            this.emitter.emit(ON_SIZE_CHANGED_EVENT_NAME, MapsKt.mutableMapOf(TuplesKt.to("size", MapsKt.mapOf(TuplesKt.to("width", Integer.valueOf(width)), TuplesKt.to("height", Integer.valueOf(height)))), TuplesKt.to("orientation", DeviceOrientationUtilsKt.toJson(new DeviceOrientationMapper().mapRotationToOrientation(screenRotation))), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }
}
