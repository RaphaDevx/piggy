package com.scandit.datacapture.frameworks.barcode.batch.listeners;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlay;
import com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlayListener;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.common.geometry.MeasureUnit;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.events.Event;
import com.tealium.library.DataSources;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeBatchAdvancedOverlayListener.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J \u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000fJ\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00132\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/batch/listeners/FrameworksBarcodeBatchAdvancedOverlayListener;", "Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchAdvancedOverlayListener;", "evenEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;)V", "anchorForTrackedBarcodeEvent", "Lcom/scandit/datacapture/frameworks/core/events/Event;", "offsetForTrackedBarcodeEvent", "viewForBarcodeTappedEvent", "viewForTrackedBarcodeEvent", "anchorForTrackedBarcode", "Lcom/scandit/datacapture/core/common/geometry/Anchor;", "overlay", "Lcom/scandit/datacapture/barcode/batch/ui/overlay/BarcodeBatchAdvancedOverlay;", FrameworksBarcodeBatchAdvancedOverlayListener.FIELD_TRACKED_BARCODE, "Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;", "offsetForTrackedBarcode", "Lcom/scandit/datacapture/core/common/geometry/PointWithUnit;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Landroid/view/View;", "viewForBarcodeTapped", "", "viewForTrackedBarcode", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksBarcodeBatchAdvancedOverlayListener implements BarcodeBatchAdvancedOverlayListener {
    public static final String EVENT_ANCHOR_FOR_TRACKED_BARCODE = "BarcodeBatchAdvancedOverlayListener.anchorForTrackedBarcode";
    public static final String EVENT_DID_TAP_VIEW_FOR_TRACKED_BARCODE = "BarcodeBatchAdvancedOverlayListener.didTapViewForTrackedBarcode";
    public static final String EVENT_OFFSET_FOR_TRACKED_BARCODE = "BarcodeBatchAdvancedOverlayListener.offsetForTrackedBarcode";
    public static final String EVENT_WIDGET_FOR_TRACKED_BARCODE = "BarcodeBatchAdvancedOverlayListener.viewForTrackedBarcode";
    private static final String FIELD_TRACKED_BARCODE = "trackedBarcode";
    private final Event anchorForTrackedBarcodeEvent;
    private final Emitter evenEmitter;
    private final Event offsetForTrackedBarcodeEvent;
    private final Event viewForBarcodeTappedEvent;
    private final Event viewForTrackedBarcodeEvent;

    public FrameworksBarcodeBatchAdvancedOverlayListener(Emitter evenEmitter) {
        Intrinsics.checkNotNullParameter(evenEmitter, "evenEmitter");
        this.evenEmitter = evenEmitter;
        this.anchorForTrackedBarcodeEvent = new Event(EVENT_ANCHOR_FOR_TRACKED_BARCODE);
        this.offsetForTrackedBarcodeEvent = new Event(EVENT_OFFSET_FOR_TRACKED_BARCODE);
        this.viewForTrackedBarcodeEvent = new Event(EVENT_WIDGET_FOR_TRACKED_BARCODE);
        this.viewForBarcodeTappedEvent = new Event(EVENT_DID_TAP_VIEW_FOR_TRACKED_BARCODE);
    }

    @Override // com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlayListener
    public Anchor anchorForTrackedBarcode(BarcodeBatchAdvancedOverlay overlay, TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        this.anchorForTrackedBarcodeEvent.emit(this.evenEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_TRACKED_BARCODE, trackedBarcode.toJson())));
        return Anchor.CENTER;
    }

    @Override // com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlayListener
    public PointWithUnit offsetForTrackedBarcode(BarcodeBatchAdvancedOverlay overlay, TrackedBarcode trackedBarcode, View view) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        Intrinsics.checkNotNullParameter(view, "view");
        this.offsetForTrackedBarcodeEvent.emit(this.evenEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_TRACKED_BARCODE, trackedBarcode.toJson())));
        return new PointWithUnit(new FloatWithUnit(0.0f, MeasureUnit.PIXEL), new FloatWithUnit(0.0f, MeasureUnit.PIXEL));
    }

    @Override // com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlayListener
    public View viewForTrackedBarcode(BarcodeBatchAdvancedOverlay overlay, TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        this.viewForTrackedBarcodeEvent.emit(this.evenEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_TRACKED_BARCODE, trackedBarcode.toJson())));
        return null;
    }

    public final void viewForBarcodeTapped(TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        this.viewForBarcodeTappedEvent.emit(this.evenEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_TRACKED_BARCODE, trackedBarcode.toJson())));
    }
}
