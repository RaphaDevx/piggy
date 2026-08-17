package com.scandit.datacapture.frameworks.barcode.spark.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.config.a;
import com.scandit.datacapture.barcode.spark.capture.SparkScanViewUiListener;
import com.scandit.datacapture.barcode.spark.ui.SparkScanScanningMode;
import com.scandit.datacapture.barcode.spark.ui.SparkScanView;
import com.scandit.datacapture.barcode.spark.ui.SparkScanViewState;
import com.scandit.datacapture.barcode.spark.ui.SparkScanViewStateSerializer;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.events.Event;
import com.tealium.library.DataSources;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksSparkScanViewUiListener.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\n\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/spark/listeners/FrameworksSparkScanViewUiListener;", "Lcom/scandit/datacapture/barcode/spark/capture/SparkScanViewUiListener;", "eventEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksSparkScanViewUiListener.FIELD_VIEW_ID, "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;I)V", "onBarcodeCountButtonTapEvent", "Lcom/scandit/datacapture/frameworks/core/events/Event;", "onBarcodeFindButtonTapEvent", "onLabelCaptureButtonTap", "onViewStateChangedEvent", "hasListener", "", a.h, "", "onBarcodeCountButtonTap", "", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/barcode/spark/ui/SparkScanView;", "onBarcodeFindButtonTap", "onViewStateChanged", "newState", "Lcom/scandit/datacapture/barcode/spark/ui/SparkScanViewState;", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksSparkScanViewUiListener implements SparkScanViewUiListener {
    public static final String EVENT_BARCODE_COUNT_BUTTON_TAP = "SparkScanViewUiListener.barcodeCountButtonTapped";
    public static final String EVENT_BARCODE_FIND_BUTTON_TAP = "SparkScanViewUiListener.barcodeFindButtonTapped";
    public static final String EVENT_LABEL_CAPTURE_BUTTON_TAP = "SparkScanViewUiListener.labelCaptureButtonTapped";
    public static final String EVENT_VIEW_STATE_CHANGED = "SparkScanViewUiListener.didChangeViewState";
    private static final String FIELD_VIEW_ID = "viewId";
    private final Emitter eventEmitter;
    private final Event onBarcodeCountButtonTapEvent;
    private final Event onBarcodeFindButtonTapEvent;
    private final Event onLabelCaptureButtonTap;
    private final Event onViewStateChangedEvent;
    private final int viewId;

    public FrameworksSparkScanViewUiListener(Emitter eventEmitter, int i) {
        Intrinsics.checkNotNullParameter(eventEmitter, "eventEmitter");
        this.eventEmitter = eventEmitter;
        this.viewId = i;
        this.onBarcodeFindButtonTapEvent = new Event(EVENT_BARCODE_FIND_BUTTON_TAP);
        this.onBarcodeCountButtonTapEvent = new Event(EVENT_BARCODE_COUNT_BUTTON_TAP);
        this.onLabelCaptureButtonTap = new Event(EVENT_LABEL_CAPTURE_BUTTON_TAP);
        this.onViewStateChangedEvent = new Event(EVENT_VIEW_STATE_CHANGED);
    }

    @Override // com.scandit.datacapture.barcode.spark.capture.SparkScanViewUiListener
    public void onScanningModeChange(SparkScanScanningMode sparkScanScanningMode) {
        SparkScanViewUiListener.DefaultImpls.onScanningModeChange(this, sparkScanScanningMode);
    }

    @Override // com.scandit.datacapture.barcode.spark.capture.SparkScanViewUiListener
    public void onBarcodeFindButtonTap(SparkScanView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (hasListener(EVENT_BARCODE_FIND_BUTTON_TAP)) {
            this.onBarcodeFindButtonTapEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }

    @Override // com.scandit.datacapture.barcode.spark.capture.SparkScanViewUiListener
    public void onBarcodeCountButtonTap(SparkScanView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (hasListener(EVENT_BARCODE_COUNT_BUTTON_TAP)) {
            this.onBarcodeCountButtonTapEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }

    @Override // com.scandit.datacapture.barcode.spark.capture.SparkScanViewUiListener
    public void onLabelCaptureButtonTap(SparkScanView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (hasListener(EVENT_LABEL_CAPTURE_BUTTON_TAP)) {
            this.onLabelCaptureButtonTap.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }

    @Override // com.scandit.datacapture.barcode.spark.capture.SparkScanViewUiListener
    public void onViewStateChanged(SparkScanViewState newState) {
        Intrinsics.checkNotNullParameter(newState, "newState");
        if (hasListener(EVENT_VIEW_STATE_CHANGED)) {
            this.onViewStateChangedEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(SentryThread.JsonKeys.STATE, SparkScanViewStateSerializer.toString(newState)), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }

    private final boolean hasListener(String eventName) {
        return this.eventEmitter.hasViewSpecificListenersForEvent(this.viewId, eventName);
    }
}
