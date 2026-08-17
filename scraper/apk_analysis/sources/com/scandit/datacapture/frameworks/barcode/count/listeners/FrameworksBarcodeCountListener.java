package com.scandit.datacapture.frameworks.barcode.count.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.count.capture.BarcodeCount;
import com.scandit.datacapture.barcode.count.capture.BarcodeCountListener;
import com.scandit.datacapture.barcode.count.capture.BarcodeCountSession;
import com.scandit.datacapture.barcode.count.capture.map.BarcodeSpatialGrid;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.events.EventForResult;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.frames.DefaultFrameDataHandler;
import com.scandit.datacapture.frameworks.core.frames.FrameDataHandler;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeCountListener.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 $2\u00020\u0001:\u0001$B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0012J\u000e\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0010J\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005J \u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0006\u0010 \u001a\u00020\u0012J\u0015\u0010!\u001a\u00020\u00122\b\u0010\"\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010#R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/listeners/FrameworksBarcodeCountListener;", "Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountListener;", "eventEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodeCountListener.FIELD_VIEW_ID, "", "frameDataHandler", "Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;ILcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;)V", "eventForResultTimeout", "", "latestSession", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/scandit/datacapture/barcode/count/capture/BarcodeCountSession;", "onBarcodeScanned", "Lcom/scandit/datacapture/frameworks/core/events/EventForResult;", "", "disableInAsyncMode", "", "enableInAsyncMode", "finishOnScan", ExtentionsKt.ENABLED_KEY, "getSpatialMap", "Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeSpatialGrid;", "expectedNumberOfRows", "expectedNumberOfColumns", "onScan", "mode", "Lcom/scandit/datacapture/barcode/count/capture/BarcodeCount;", "session", "data", "Lcom/scandit/datacapture/core/data/FrameData;", "reset", "resetSession", "frameSequenceId", "(Ljava/lang/Long;)V", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksBarcodeCountListener implements BarcodeCountListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_FRAME_ID = "frameId";
    private static final String FIELD_SESSION = "session";
    private static final String FIELD_VIEW_ID = "viewId";
    public static final String ON_BARCODE_SCANNED_EVENT_NAME = "BarcodeCountListener.onScan";
    private final Emitter eventEmitter;
    private long eventForResultTimeout;
    private final FrameDataHandler frameDataHandler;
    private final AtomicReference<BarcodeCountSession> latestSession;
    private final EventForResult<Boolean> onBarcodeScanned;
    private final int viewId;

    @JvmStatic
    public static final FrameworksBarcodeCountListener create(Emitter emitter, int i) {
        return INSTANCE.create(emitter, i);
    }

    public FrameworksBarcodeCountListener(Emitter eventEmitter, int i, FrameDataHandler frameDataHandler) {
        Intrinsics.checkNotNullParameter(eventEmitter, "eventEmitter");
        Intrinsics.checkNotNullParameter(frameDataHandler, "frameDataHandler");
        this.eventEmitter = eventEmitter;
        this.viewId = i;
        this.frameDataHandler = frameDataHandler;
        this.latestSession = new AtomicReference<>();
        this.eventForResultTimeout = EventForResult.DEFAULT_TIMEOUT_MILLIS;
        this.onBarcodeScanned = new EventForResult<>(ON_BARCODE_SCANNED_EVENT_NAME, null, null, 6, null);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountListener
    public void onObservationStarted(BarcodeCount barcodeCount) {
        BarcodeCountListener.DefaultImpls.onObservationStarted(this, barcodeCount);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountListener
    public void onObservationStopped(BarcodeCount barcodeCount) {
        BarcodeCountListener.DefaultImpls.onObservationStopped(this, barcodeCount);
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountListener
    public void onSessionUpdated(BarcodeCount barcodeCount, BarcodeCountSession barcodeCountSession, FrameData frameData) {
        BarcodeCountListener.DefaultImpls.onSessionUpdated(this, barcodeCount, barcodeCountSession, frameData);
    }

    public /* synthetic */ FrameworksBarcodeCountListener(Emitter emitter, int i, DefaultFrameDataHandler defaultFrameDataHandler, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(emitter, i, (i2 & 4) != 0 ? DefaultFrameDataHandler.INSTANCE.getInstance() : defaultFrameDataHandler);
    }

    public final void enableInAsyncMode() {
        this.eventForResultTimeout = -1L;
    }

    public final void disableInAsyncMode() {
        this.eventForResultTimeout = EventForResult.DEFAULT_TIMEOUT_MILLIS;
    }

    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountListener
    public void onScan(BarcodeCount mode, BarcodeCountSession session, FrameData data) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        if (this.eventEmitter.hasViewSpecificListenersForEvent(this.viewId, ON_BARCODE_SCANNED_EVENT_NAME)) {
            String addToCache = this.frameDataHandler.addToCache(data);
            this.latestSession.set(session);
            this.onBarcodeScanned.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to("session", session.toJson()), TuplesKt.to(FIELD_FRAME_ID, addToCache), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))), Boolean.valueOf(mode.isEnabled()), this.eventForResultTimeout);
            this.frameDataHandler.removeFromCache(addToCache);
        }
    }

    public final void finishOnScan(boolean enabled) {
        this.onBarcodeScanned.submitResult(Boolean.valueOf(enabled));
    }

    public final void resetSession(Long frameSequenceId) {
        BarcodeCountSession barcodeCountSession = this.latestSession.get();
        if (barcodeCountSession == null) {
            return;
        }
        if (frameSequenceId == null || barcodeCountSession.getFrameSequenceId() == frameSequenceId.longValue()) {
            barcodeCountSession.reset();
        }
    }

    public final BarcodeSpatialGrid getSpatialMap() {
        BarcodeCountSession barcodeCountSession = this.latestSession.get();
        if (barcodeCountSession == null) {
            return null;
        }
        return barcodeCountSession.getSpatialMap();
    }

    public final BarcodeSpatialGrid getSpatialMap(int expectedNumberOfRows, int expectedNumberOfColumns) {
        BarcodeCountSession barcodeCountSession = this.latestSession.get();
        if (barcodeCountSession == null) {
            return null;
        }
        return barcodeCountSession.getSpatialMap(expectedNumberOfRows, expectedNumberOfColumns);
    }

    public final void reset() {
        this.onBarcodeScanned.cancel();
        this.latestSession.set(null);
    }

    /* compiled from: FrameworksBarcodeCountListener.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/listeners/FrameworksBarcodeCountListener$Companion;", "", "()V", "FIELD_FRAME_ID", "", "FIELD_SESSION", "FIELD_VIEW_ID", "ON_BARCODE_SCANNED_EVENT_NAME", "create", "Lcom/scandit/datacapture/frameworks/barcode/count/listeners/FrameworksBarcodeCountListener;", "eventEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodeCountListener.FIELD_VIEW_ID, "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final FrameworksBarcodeCountListener create(Emitter eventEmitter, int viewId) {
            Intrinsics.checkNotNullParameter(eventEmitter, "eventEmitter");
            return new FrameworksBarcodeCountListener(eventEmitter, viewId, null, 4, null);
        }
    }
}
