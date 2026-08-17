package com.scandit.datacapture.frameworks.barcode.batch.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlay;
import com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlayListener;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.events.Event;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeBatchBasicOverlayListener.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/batch/listeners/FrameworksBarcodeBatchBasicOverlayListener;", "Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlayListener;", "eventEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;)V", "brushForTrackedBarcodeEvent", "Lcom/scandit/datacapture/frameworks/core/events/Event;", "onTrackedBarcodeTappedEvent", "brushForTrackedBarcode", "Lcom/scandit/datacapture/core/ui/style/Brush;", "overlay", "Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchBasicOverlay;", FrameworksBarcodeBatchBasicOverlayListener.FIELD_TRACKED_BARCODE, "Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;", "onTrackedBarcodeTapped", "", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksBarcodeBatchBasicOverlayListener implements BarcodeBatchBasicOverlayListener {
    public static final String EVENT_ON_TRACKED_BARCODE_TAPPED = "BarcodeBatchBasicOverlayListener.didTapTrackedBarcode";
    public static final String EVENT_SET_BRUSH_FOR_TRACKED_BARCODE = "BarcodeBatchBasicOverlayListener.brushForTrackedBarcode";
    private static final String FIELD_TRACKED_BARCODE = "trackedBarcode";
    private final Event brushForTrackedBarcodeEvent;
    private final Emitter eventEmitter;
    private final Event onTrackedBarcodeTappedEvent;

    public FrameworksBarcodeBatchBasicOverlayListener(Emitter eventEmitter) {
        Intrinsics.checkNotNullParameter(eventEmitter, "eventEmitter");
        this.eventEmitter = eventEmitter;
        this.brushForTrackedBarcodeEvent = new Event(EVENT_SET_BRUSH_FOR_TRACKED_BARCODE);
        this.onTrackedBarcodeTappedEvent = new Event(EVENT_ON_TRACKED_BARCODE_TAPPED);
    }

    @Override // com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlayListener
    public Brush brushForTrackedBarcode(BarcodeBatchBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        if (!this.eventEmitter.hasListenersForEvent(EVENT_SET_BRUSH_FOR_TRACKED_BARCODE)) {
            return overlay.getBrush();
        }
        this.brushForTrackedBarcodeEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_TRACKED_BARCODE, trackedBarcode.toJson())));
        return null;
    }

    @Override // com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlayListener
    public void onTrackedBarcodeTapped(BarcodeBatchBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        this.onTrackedBarcodeTappedEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_TRACKED_BARCODE, trackedBarcode.toJson())));
    }
}
