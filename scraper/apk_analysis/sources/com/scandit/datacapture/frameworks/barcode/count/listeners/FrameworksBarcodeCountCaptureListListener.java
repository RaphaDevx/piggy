package com.scandit.datacapture.frameworks.barcode.count.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureList;
import com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureListListener;
import com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureListSession;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.events.Event;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeCountCaptureListListener.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/listeners/FrameworksBarcodeCountCaptureListListener;", "Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureListListener;", "eventEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodeCountCaptureListListener.FIELD_VIEW_ID, "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;I)V", "event", "Lcom/scandit/datacapture/frameworks/core/events/Event;", "onCaptureListSessionUpdated", "", "list", "Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureList;", "session", "Lcom/scandit/datacapture/barcode/count/capture/list/BarcodeCountCaptureListSession;", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksBarcodeCountCaptureListListener implements BarcodeCountCaptureListListener {
    private static final String FIELD_SESSION = "session";
    private static final String FIELD_VIEW_ID = "viewId";
    public static final String ON_CAPTURE_LIST_SESSION_UPDATED_EVENT_NAME = "BarcodeCountCaptureListListener.didUpdateSession";
    private final Event event;
    private final Emitter eventEmitter;
    private final int viewId;

    public FrameworksBarcodeCountCaptureListListener(Emitter eventEmitter, int i) {
        Intrinsics.checkNotNullParameter(eventEmitter, "eventEmitter");
        this.eventEmitter = eventEmitter;
        this.viewId = i;
        this.event = new Event(ON_CAPTURE_LIST_SESSION_UPDATED_EVENT_NAME);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureListListener
    @ProxyFunction
    public void onCaptureListCompleted(BarcodeCountCaptureList barcodeCountCaptureList, BarcodeCountCaptureListSession barcodeCountCaptureListSession) {
        BarcodeCountCaptureListListener.DefaultImpls.onCaptureListCompleted(this, barcodeCountCaptureList, barcodeCountCaptureListSession);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureListListener
    @ProxyFunction
    public void onObservationStarted(BarcodeCountCaptureList barcodeCountCaptureList) {
        BarcodeCountCaptureListListener.DefaultImpls.onObservationStarted(this, barcodeCountCaptureList);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureListListener
    @ProxyFunction
    public void onObservationStopped(BarcodeCountCaptureList barcodeCountCaptureList) {
        BarcodeCountCaptureListListener.DefaultImpls.onObservationStopped(this, barcodeCountCaptureList);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureListListener
    public void onCaptureListSessionUpdated(BarcodeCountCaptureList list, BarcodeCountCaptureListSession session) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(session, "session");
        if (this.eventEmitter.hasViewSpecificListenersForEvent(this.viewId, ON_CAPTURE_LIST_SESSION_UPDATED_EVENT_NAME)) {
            this.event.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to("session", session.toJson()), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }
}
