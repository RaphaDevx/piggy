package com.scandit.datacapture.frameworks.barcode.pick.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.pick.capture.BarcodePick;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningListener;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningSession;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.events.Event;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodePickScanningListener.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\u0018\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickScanningListener;", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickScanningListener;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodePickScanningListener.FIELD_VIEW_ID, "", "didUpdateScanningSession", "Lcom/scandit/datacapture/frameworks/core/events/Event;", "didCompleteScanningSession", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;ILcom/scandit/datacapture/frameworks/core/events/Event;Lcom/scandit/datacapture/frameworks/core/events/Event;)V", "isEnabled", "Ljava/util/concurrent/atomic/AtomicReference;", "", "disable", "", "enable", "onScanningSessionCompleted", "barcodePick", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;", "session", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickScanningSession;", "onScanningSessionUpdated", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksBarcodePickScanningListener implements BarcodePickScanningListener {
    private static final String FIELD_SESSION = "session";
    private static final String FIELD_VIEW_ID = "viewId";
    public static final String ON_COMPLETE_SCANNING = "BarcodePickScanningListener.didCompleteScanningSession";
    public static final String ON_UPDATE_SCANNING = "BarcodePickScanningListener.didUpdateScanningSession";
    private final Event didCompleteScanningSession;
    private final Event didUpdateScanningSession;
    private final Emitter emitter;
    private final AtomicReference<Boolean> isEnabled;
    private final int viewId;

    public FrameworksBarcodePickScanningListener(Emitter emitter, int i, Event didUpdateScanningSession, Event didCompleteScanningSession) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        Intrinsics.checkNotNullParameter(didUpdateScanningSession, "didUpdateScanningSession");
        Intrinsics.checkNotNullParameter(didCompleteScanningSession, "didCompleteScanningSession");
        this.emitter = emitter;
        this.viewId = i;
        this.didUpdateScanningSession = didUpdateScanningSession;
        this.didCompleteScanningSession = didCompleteScanningSession;
        this.isEnabled = new AtomicReference<>(false);
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningListener
    @ProxyFunction
    public void onObservationStarted(BarcodePick barcodePick) {
        BarcodePickScanningListener.DefaultImpls.onObservationStarted(this, barcodePick);
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningListener
    @ProxyFunction
    public void onObservationStopped(BarcodePick barcodePick) {
        BarcodePickScanningListener.DefaultImpls.onObservationStopped(this, barcodePick);
    }

    public /* synthetic */ FrameworksBarcodePickScanningListener(Emitter emitter, int i, Event event, Event event2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(emitter, i, (i2 & 4) != 0 ? new Event(ON_UPDATE_SCANNING) : event, (i2 & 8) != 0 ? new Event(ON_COMPLETE_SCANNING) : event2);
    }

    public final void enable() {
        this.isEnabled.set(true);
    }

    public final void disable() {
        this.isEnabled.set(false);
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningListener
    public void onScanningSessionCompleted(BarcodePick barcodePick, BarcodePickScanningSession session) {
        Intrinsics.checkNotNullParameter(barcodePick, "barcodePick");
        Intrinsics.checkNotNullParameter(session, "session");
        if (this.isEnabled.get().booleanValue() && this.emitter.hasViewSpecificListenersForEvent(this.viewId, ON_COMPLETE_SCANNING)) {
            this.didCompleteScanningSession.emit(this.emitter, MapsKt.mutableMapOf(TuplesKt.to("session", session.toJson()), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningListener
    public void onScanningSessionUpdated(BarcodePick barcodePick, BarcodePickScanningSession session) {
        Intrinsics.checkNotNullParameter(barcodePick, "barcodePick");
        Intrinsics.checkNotNullParameter(session, "session");
        if (this.isEnabled.get().booleanValue() && this.emitter.hasViewSpecificListenersForEvent(this.viewId, ON_UPDATE_SCANNING)) {
            this.didUpdateScanningSession.emit(this.emitter, MapsKt.mutableMapOf(TuplesKt.to("session", session.toJson()), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }
}
