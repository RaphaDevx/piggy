package com.scandit.datacapture.frameworks.barcode.count.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewListener;
import com.scandit.datacapture.barcode.data.Cluster;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.events.Event;
import com.tealium.library.DataSources;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeCountViewListener.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0014\b\u0016\u0018\u0000 22\u00020\u0001:\u00012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fH\u0016J\u001a\u0010\u001b\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fH\u0016J\u001a\u0010\u001c\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fH\u0016J\u001a\u0010\u001d\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fH\u0016J\u0006\u0010\u001e\u001a\u00020\u001fJ\u0017\u0010 \u001a\u0004\u0018\u00010\u000f2\u0006\u0010!\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\"J\u0017\u0010#\u001a\u0004\u0018\u00010\u000f2\u0006\u0010!\u001a\u00020\u0005H\u0000¢\u0006\u0002\b$J\u0017\u0010%\u001a\u0004\u0018\u00010\u000f2\u0006\u0010!\u001a\u00020\u0005H\u0000¢\u0006\u0002\b&J\u0017\u0010'\u001a\u0004\u0018\u00010\u000f2\u0006\u0010!\u001a\u00020\u0005H\u0000¢\u0006\u0002\b(J\u0018\u0010)\u001a\u00020\u001f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fH\u0016J\u0010\u0010*\u001a\u00020\u001f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010+\u001a\u00020\u001f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010,\u001a\u00020\u000fH\u0016J\u0018\u0010-\u001a\u00020\u001f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fH\u0016J\u0018\u0010.\u001a\u00020\u001f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fH\u0016J\u0018\u0010/\u001a\u00020\u001f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000fH\u0016J\u0014\u00100\u001a\u00020\u000e*\u00020\u00052\u0006\u00101\u001a\u00020\u000eH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/listeners/FrameworksBarcodeCountViewListener;", "Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountViewListener;", "eventEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodeCountViewListener.FIELD_VIEW_ID, "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;I)V", "brushForAcceptedBarcodeEvent", "Lcom/scandit/datacapture/frameworks/core/events/Event;", "brushForRecognizedBarcodeEvent", "brushForRecognizedBarcodeNotInListEvent", "brushForRejectedBarcodeEvent", "brushRequests", "", "", "Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;", "onAcceptedBarcodeTappedEvent", "onCaptureListCompletedEvent", "onFilteredBarcodeTappedEvent", "onRecognizedBarcodeNotInListTappedEvent", "onRecognizedBarcodeTappedEvent", "onRejectedBarcodeTappedEvent", "brushForAcceptedBarcode", "Lcom/scandit/datacapture/core/ui/style/Brush;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountView;", FrameworksBarcodeCountViewListener.FIELD_TRACKED_BARCODE, "brushForRecognizedBarcode", "brushForRecognizedBarcodeNotInList", "brushForRejectedBarcode", "clearCache", "", "getTrackedBarcodeForBrushForAcceptedEvent", "trackedBarcodeId", "getTrackedBarcodeForBrushForAcceptedEvent$scandit_datacapture_frameworks_barcode_release", "getTrackedBarcodeForBrushForRecognizedEvent", "getTrackedBarcodeForBrushForRecognizedEvent$scandit_datacapture_frameworks_barcode_release", "getTrackedBarcodeForBrushForRecognizedNotInListEvent", "getTrackedBarcodeForBrushForRecognizedNotInListEvent$scandit_datacapture_frameworks_barcode_release", "getTrackedBarcodeForBrushForRejectedEvent", "getTrackedBarcodeForBrushForRejectedEvent$scandit_datacapture_frameworks_barcode_release", "onAcceptedBarcodeTapped", "onCaptureListCompleted", "onFilteredBarcodeTapped", "filteredBarcode", "onRecognizedBarcodeNotInListTapped", "onRecognizedBarcodeTapped", "onRejectedBarcodeTapped", "keyFor", "prefix", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksBarcodeCountViewListener implements BarcodeCountViewListener {
    public static final String BRUSH_FOR_ACCEPTED_BARCODE = "BarcodeCountViewListener.brushForAcceptedBarcode";
    public static final String BRUSH_FOR_RECOGNIZED_BARCODE_EVENT = "BarcodeCountViewListener.brushForRecognizedBarcode";
    public static final String BRUSH_FOR_RECOGNIZED_BARCODE_NOT_IN_LIST_EVENT = "BarcodeCountViewListener.brushForRecognizedBarcodeNotInList";
    public static final String BRUSH_FOR_REJECTED_BARCODE = "BarcodeCountViewListener.brushForRejectedBarcode";
    public static final String DID_COMPLETE_CAPTURE_LIST = "BarcodeCountViewListener.didCompleteCaptureList";
    public static final String DID_TAP_ACCEPTED_BARCODE = "BarcodeCountViewListener.didTapAcceptedBarcode";
    public static final String DID_TAP_FILTERED_BARCODE = "BarcodeCountViewListener.didTapFilteredBarcode";
    public static final String DID_TAP_RECOGNIZED_BARCODE = "BarcodeCountViewListener.didTapRecognizedBarcode";
    public static final String DID_TAP_RECOGNIZED_BARCODE_NOT_IN_LIST = "BarcodeCountViewListener.didTapRecognizedBarcodeNotInList";
    public static final String DID_TAP_REJECTED_BARCODE = "BarcodeCountViewListener.didTapRejectedBarcode";
    private static final String FIELD_TRACKED_BARCODE = "trackedBarcode";
    private static final String FIELD_VIEW_ID = "viewId";
    private final Event brushForAcceptedBarcodeEvent;
    private final Event brushForRecognizedBarcodeEvent;
    private final Event brushForRecognizedBarcodeNotInListEvent;
    private final Event brushForRejectedBarcodeEvent;
    private final Map<String, TrackedBarcode> brushRequests;
    private final Emitter eventEmitter;
    private final Event onAcceptedBarcodeTappedEvent;
    private final Event onCaptureListCompletedEvent;
    private final Event onFilteredBarcodeTappedEvent;
    private final Event onRecognizedBarcodeNotInListTappedEvent;
    private final Event onRecognizedBarcodeTappedEvent;
    private final Event onRejectedBarcodeTappedEvent;
    private final int viewId;

    public FrameworksBarcodeCountViewListener(Emitter eventEmitter, int i) {
        Intrinsics.checkNotNullParameter(eventEmitter, "eventEmitter");
        this.eventEmitter = eventEmitter;
        this.viewId = i;
        this.brushRequests = new LinkedHashMap();
        this.brushForRecognizedBarcodeEvent = new Event(BRUSH_FOR_RECOGNIZED_BARCODE_EVENT);
        this.brushForRecognizedBarcodeNotInListEvent = new Event(BRUSH_FOR_RECOGNIZED_BARCODE_NOT_IN_LIST_EVENT);
        this.brushForAcceptedBarcodeEvent = new Event(BRUSH_FOR_ACCEPTED_BARCODE);
        this.brushForRejectedBarcodeEvent = new Event(BRUSH_FOR_REJECTED_BARCODE);
        this.onFilteredBarcodeTappedEvent = new Event(DID_TAP_FILTERED_BARCODE);
        this.onRecognizedBarcodeNotInListTappedEvent = new Event(DID_TAP_RECOGNIZED_BARCODE_NOT_IN_LIST);
        this.onRecognizedBarcodeTappedEvent = new Event(DID_TAP_RECOGNIZED_BARCODE);
        this.onAcceptedBarcodeTappedEvent = new Event(DID_TAP_ACCEPTED_BARCODE);
        this.onRejectedBarcodeTappedEvent = new Event(DID_TAP_REJECTED_BARCODE);
        this.onCaptureListCompletedEvent = new Event(DID_COMPLETE_CAPTURE_LIST);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewListener
    public void onClusterTapped(BarcodeCountView barcodeCountView, Cluster cluster) {
        BarcodeCountViewListener.DefaultImpls.onClusterTapped(this, barcodeCountView, cluster);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewListener
    public Brush brushForRecognizedBarcode(BarcodeCountView view, TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        if (!this.eventEmitter.hasViewSpecificListenersForEvent(this.viewId, BRUSH_FOR_RECOGNIZED_BARCODE_EVENT)) {
            return null;
        }
        this.brushForRecognizedBarcodeEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_TRACKED_BARCODE, trackedBarcode.toJson()), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        this.brushRequests.put(keyFor(trackedBarcode.getIdentifier(), BRUSH_FOR_RECOGNIZED_BARCODE_EVENT), trackedBarcode);
        return null;
    }

    public final TrackedBarcode getTrackedBarcodeForBrushForRecognizedEvent$scandit_datacapture_frameworks_barcode_release(int trackedBarcodeId) {
        String keyFor = keyFor(trackedBarcodeId, BRUSH_FOR_RECOGNIZED_BARCODE_EVENT);
        TrackedBarcode trackedBarcode = this.brushRequests.get(keyFor);
        if (trackedBarcode != null) {
            this.brushRequests.remove(keyFor);
        }
        return trackedBarcode;
    }

    @Override // com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewListener
    public Brush brushForRecognizedBarcodeNotInList(BarcodeCountView view, TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        if (!this.eventEmitter.hasViewSpecificListenersForEvent(this.viewId, BRUSH_FOR_RECOGNIZED_BARCODE_NOT_IN_LIST_EVENT)) {
            return null;
        }
        this.brushForRecognizedBarcodeNotInListEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_TRACKED_BARCODE, trackedBarcode.toJson()), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        this.brushRequests.put(keyFor(trackedBarcode.getIdentifier(), BRUSH_FOR_RECOGNIZED_BARCODE_NOT_IN_LIST_EVENT), trackedBarcode);
        return null;
    }

    public final TrackedBarcode getTrackedBarcodeForBrushForRecognizedNotInListEvent$scandit_datacapture_frameworks_barcode_release(int trackedBarcodeId) {
        String keyFor = keyFor(trackedBarcodeId, BRUSH_FOR_RECOGNIZED_BARCODE_NOT_IN_LIST_EVENT);
        TrackedBarcode trackedBarcode = this.brushRequests.get(keyFor);
        if (trackedBarcode != null) {
            this.brushRequests.remove(keyFor);
        }
        return trackedBarcode;
    }

    @Override // com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewListener
    public Brush brushForAcceptedBarcode(BarcodeCountView view, TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        if (!this.eventEmitter.hasViewSpecificListenersForEvent(this.viewId, BRUSH_FOR_ACCEPTED_BARCODE)) {
            return null;
        }
        this.brushForAcceptedBarcodeEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_TRACKED_BARCODE, trackedBarcode.toJson()), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        this.brushRequests.put(keyFor(trackedBarcode.getIdentifier(), BRUSH_FOR_ACCEPTED_BARCODE), trackedBarcode);
        return null;
    }

    public final TrackedBarcode getTrackedBarcodeForBrushForAcceptedEvent$scandit_datacapture_frameworks_barcode_release(int trackedBarcodeId) {
        String keyFor = keyFor(trackedBarcodeId, BRUSH_FOR_ACCEPTED_BARCODE);
        TrackedBarcode trackedBarcode = this.brushRequests.get(keyFor);
        if (trackedBarcode != null) {
            this.brushRequests.remove(keyFor);
        }
        return trackedBarcode;
    }

    @Override // com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewListener
    public Brush brushForRejectedBarcode(BarcodeCountView view, TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        if (!this.eventEmitter.hasViewSpecificListenersForEvent(this.viewId, BRUSH_FOR_REJECTED_BARCODE)) {
            return null;
        }
        this.brushForRejectedBarcodeEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_TRACKED_BARCODE, trackedBarcode.toJson()), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        this.brushRequests.put(keyFor(trackedBarcode.getIdentifier(), BRUSH_FOR_REJECTED_BARCODE), trackedBarcode);
        return null;
    }

    public final TrackedBarcode getTrackedBarcodeForBrushForRejectedEvent$scandit_datacapture_frameworks_barcode_release(int trackedBarcodeId) {
        String keyFor = keyFor(trackedBarcodeId, BRUSH_FOR_REJECTED_BARCODE);
        TrackedBarcode trackedBarcode = this.brushRequests.get(keyFor);
        if (trackedBarcode != null) {
            this.brushRequests.remove(keyFor);
        }
        return trackedBarcode;
    }

    @Override // com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewListener
    public void onFilteredBarcodeTapped(BarcodeCountView view, TrackedBarcode filteredBarcode) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(filteredBarcode, "filteredBarcode");
        if (this.eventEmitter.hasViewSpecificListenersForEvent(this.viewId, DID_TAP_FILTERED_BARCODE)) {
            this.onFilteredBarcodeTappedEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_TRACKED_BARCODE, filteredBarcode.toJson()), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }

    @Override // com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewListener
    public void onRecognizedBarcodeNotInListTapped(BarcodeCountView view, TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        if (this.eventEmitter.hasViewSpecificListenersForEvent(this.viewId, DID_TAP_RECOGNIZED_BARCODE_NOT_IN_LIST)) {
            this.onRecognizedBarcodeNotInListTappedEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_TRACKED_BARCODE, trackedBarcode.toJson()), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }

    @Override // com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewListener
    public void onRecognizedBarcodeTapped(BarcodeCountView view, TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        if (this.eventEmitter.hasViewSpecificListenersForEvent(this.viewId, DID_TAP_RECOGNIZED_BARCODE)) {
            this.onRecognizedBarcodeTappedEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_TRACKED_BARCODE, trackedBarcode.toJson()), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }

    @Override // com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewListener
    public void onAcceptedBarcodeTapped(BarcodeCountView view, TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        if (this.eventEmitter.hasViewSpecificListenersForEvent(this.viewId, DID_TAP_ACCEPTED_BARCODE)) {
            this.onAcceptedBarcodeTappedEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_TRACKED_BARCODE, trackedBarcode.toJson()), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }

    @Override // com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewListener
    public void onRejectedBarcodeTapped(BarcodeCountView view, TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        if (this.eventEmitter.hasViewSpecificListenersForEvent(this.viewId, DID_TAP_REJECTED_BARCODE)) {
            this.onRejectedBarcodeTappedEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_TRACKED_BARCODE, trackedBarcode.toJson()), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }

    @Override // com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewListener
    public void onCaptureListCompleted(BarcodeCountView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (this.eventEmitter.hasViewSpecificListenersForEvent(this.viewId, DID_COMPLETE_CAPTURE_LIST)) {
            this.onCaptureListCompletedEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }

    public final void clearCache() {
        this.brushRequests.clear();
    }

    private final String keyFor(int i, String str) {
        return str + '-' + i;
    }
}
