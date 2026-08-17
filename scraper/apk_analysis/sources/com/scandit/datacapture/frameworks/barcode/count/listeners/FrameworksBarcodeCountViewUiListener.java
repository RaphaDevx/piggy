package com.scandit.datacapture.frameworks.barcode.count.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewUiListener;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.events.Event;
import com.tealium.library.DataSources;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeCountViewUiListener.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/listeners/FrameworksBarcodeCountViewUiListener;", "Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountViewUiListener;", "eventEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodeCountViewUiListener.FIELD_VIEW_ID, "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;I)V", "onExitButtonTappedEvent", "Lcom/scandit/datacapture/frameworks/core/events/Event;", "onListButtonTappedEvent", "onSingleScanButtonTappedEvent", "onExitButtonTapped", "", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountView;", "onListButtonTapped", "onSingleScanButtonTapped", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksBarcodeCountViewUiListener implements BarcodeCountViewUiListener {
    private static final String FIELD_VIEW_ID = "viewId";
    public static final String ON_EXIT_BUTTON_TAPPED_EVENT_NAME = "BarcodeCountViewUiListener.onExitButtonTapped";
    public static final String ON_LIST_BUTTON_TAPPED_EVENT_NAME = "BarcodeCountViewUiListener.onListButtonTapped";
    public static final String ON_SINGLE_SCAN_BUTTON_TAPPED_EVENT_NAME = "BarcodeCountViewUiListener.onSingleScanButtonTapped";
    private final Emitter eventEmitter;
    private final Event onExitButtonTappedEvent;
    private final Event onListButtonTappedEvent;
    private final Event onSingleScanButtonTappedEvent;
    private final int viewId;

    public FrameworksBarcodeCountViewUiListener(Emitter eventEmitter, int i) {
        Intrinsics.checkNotNullParameter(eventEmitter, "eventEmitter");
        this.eventEmitter = eventEmitter;
        this.viewId = i;
        this.onExitButtonTappedEvent = new Event(ON_EXIT_BUTTON_TAPPED_EVENT_NAME);
        this.onListButtonTappedEvent = new Event(ON_LIST_BUTTON_TAPPED_EVENT_NAME);
        this.onSingleScanButtonTappedEvent = new Event(ON_SINGLE_SCAN_BUTTON_TAPPED_EVENT_NAME);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewUiListener
    public void onExitButtonTapped(BarcodeCountView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (this.eventEmitter.hasViewSpecificListenersForEvent(this.viewId, ON_EXIT_BUTTON_TAPPED_EVENT_NAME)) {
            this.onExitButtonTappedEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }

    @Override // com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewUiListener
    public void onListButtonTapped(BarcodeCountView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (this.eventEmitter.hasViewSpecificListenersForEvent(this.viewId, ON_LIST_BUTTON_TAPPED_EVENT_NAME)) {
            this.onListButtonTappedEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }

    @Override // com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewUiListener
    public void onSingleScanButtonTapped(BarcodeCountView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (this.eventEmitter.hasViewSpecificListenersForEvent(this.viewId, ON_SINGLE_SCAN_BUTTON_TAPPED_EVENT_NAME)) {
            this.onSingleScanButtonTappedEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }
}
