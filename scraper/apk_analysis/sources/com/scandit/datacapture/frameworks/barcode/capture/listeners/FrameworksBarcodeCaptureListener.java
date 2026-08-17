package com.scandit.datacapture.frameworks.barcode.capture.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.capture.BarcodeCapture;
import com.scandit.datacapture.barcode.capture.BarcodeCaptureListener;
import com.scandit.datacapture.barcode.capture.BarcodeCaptureSession;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.frameworks.barcode.capture.data.BarcodeCaptureModeCreationData;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.events.EventForResult;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.frames.DefaultFrameDataHandler;
import com.scandit.datacapture.frameworks.core.frames.FrameDataHandler;
import com.scandit.datacapture.frameworks.core.lifecycle.AppState;
import com.scandit.datacapture.frameworks.core.lifecycle.DefaultActivityLifecycle;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeCaptureListener.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 #2\u00020\u0001:\u0001#BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\bJ\u000e\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\bJ \u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J \u0010\u001e\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0006\u0010\u001f\u001a\u00020\u0015J\u0015\u0010 \u001a\u00020\u00152\b\u0010!\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\"R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/capture/listeners/FrameworksBarcodeCaptureListener;", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureListener;", "eventEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "modeId", "", "barcodeScannedEvent", "Lcom/scandit/datacapture/frameworks/core/events/EventForResult;", "", "sessionUpdatedEvent", "frameDataHandler", "Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;", "appState", "Lcom/scandit/datacapture/frameworks/core/lifecycle/AppState;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;ILcom/scandit/datacapture/frameworks/core/events/EventForResult;Lcom/scandit/datacapture/frameworks/core/events/EventForResult;Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;Lcom/scandit/datacapture/frameworks/core/lifecycle/AppState;)V", "eventForResultTimeout", "", "latestSession", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureSession;", "finishDidScan", "", ExtentionsKt.ENABLED_KEY, "finishDidUpdateSession", "onBarcodeScanned", BarcodeCaptureModeCreationData.MODE_TYPE, "Lcom/scandit/datacapture/barcode/capture/BarcodeCapture;", "session", "data", "Lcom/scandit/datacapture/core/data/FrameData;", "onSessionUpdated", "reset", "resetSession", "frameSequenceId", "(Ljava/lang/Long;)V", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksBarcodeCaptureListener implements BarcodeCaptureListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_FRAME_ID = "frameId";
    private static final String FIELD_MODE_ID = "modeId";
    private static final String FIELD_SESSION = "session";
    public static final String ON_BARCODE_SCANNED_EVENT_NAME = "BarcodeCaptureListener.didScan";
    public static final String ON_SESSION_UPDATED_EVENT_NAME = "BarcodeCaptureListener.didUpdateSession";
    private final AppState appState;
    private final EventForResult<Boolean> barcodeScannedEvent;
    private final Emitter eventEmitter;
    private long eventForResultTimeout;
    private final FrameDataHandler frameDataHandler;
    private final AtomicReference<BarcodeCaptureSession> latestSession;
    private final int modeId;
    private final EventForResult<Boolean> sessionUpdatedEvent;

    @JvmStatic
    public static final FrameworksBarcodeCaptureListener create(Emitter emitter, int i) {
        return INSTANCE.create(emitter, i);
    }

    public FrameworksBarcodeCaptureListener(Emitter eventEmitter, int i, EventForResult<Boolean> barcodeScannedEvent, EventForResult<Boolean> sessionUpdatedEvent, FrameDataHandler frameDataHandler, AppState appState) {
        Intrinsics.checkNotNullParameter(eventEmitter, "eventEmitter");
        Intrinsics.checkNotNullParameter(barcodeScannedEvent, "barcodeScannedEvent");
        Intrinsics.checkNotNullParameter(sessionUpdatedEvent, "sessionUpdatedEvent");
        Intrinsics.checkNotNullParameter(frameDataHandler, "frameDataHandler");
        Intrinsics.checkNotNullParameter(appState, "appState");
        this.eventEmitter = eventEmitter;
        this.modeId = i;
        this.barcodeScannedEvent = barcodeScannedEvent;
        this.sessionUpdatedEvent = sessionUpdatedEvent;
        this.frameDataHandler = frameDataHandler;
        this.appState = appState;
        this.latestSession = new AtomicReference<>();
        this.eventForResultTimeout = EventForResult.DEFAULT_TIMEOUT_MILLIS;
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureListener
    @ProxyFunction
    public void onObservationStarted(BarcodeCapture barcodeCapture) {
        BarcodeCaptureListener.DefaultImpls.onObservationStarted(this, barcodeCapture);
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureListener
    @ProxyFunction
    public void onObservationStopped(BarcodeCapture barcodeCapture) {
        BarcodeCaptureListener.DefaultImpls.onObservationStopped(this, barcodeCapture);
    }

    public /* synthetic */ FrameworksBarcodeCaptureListener(Emitter emitter, int i, EventForResult eventForResult, EventForResult eventForResult2, FrameDataHandler frameDataHandler, AppState appState, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(emitter, i, (i2 & 4) != 0 ? new EventForResult(ON_BARCODE_SCANNED_EVENT_NAME, null, null, 6, null) : eventForResult, (i2 & 8) != 0 ? new EventForResult(ON_SESSION_UPDATED_EVENT_NAME, null, null, 6, null) : eventForResult2, (i2 & 16) != 0 ? DefaultFrameDataHandler.INSTANCE.getInstance() : frameDataHandler, (i2 & 32) != 0 ? DefaultActivityLifecycle.INSTANCE.getInstance() : appState);
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureListener
    public void onBarcodeScanned(BarcodeCapture barcodeCapture, BarcodeCaptureSession session, FrameData data) {
        Intrinsics.checkNotNullParameter(barcodeCapture, "barcodeCapture");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        this.latestSession.set(session);
        String addToCache = this.frameDataHandler.addToCache(data);
        this.barcodeScannedEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to("session", session.toJson()), TuplesKt.to(FIELD_FRAME_ID, addToCache), TuplesKt.to("modeId", Integer.valueOf(this.modeId))), Boolean.valueOf(barcodeCapture.isEnabled()), this.eventForResultTimeout);
        this.frameDataHandler.removeFromCache(addToCache);
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureListener
    public void onSessionUpdated(BarcodeCapture barcodeCapture, BarcodeCaptureSession session, FrameData data) {
        Intrinsics.checkNotNullParameter(barcodeCapture, "barcodeCapture");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        if (this.appState.isResumed()) {
            this.latestSession.set(session);
            String addToCache = this.frameDataHandler.addToCache(data);
            this.sessionUpdatedEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to("session", session.toJson()), TuplesKt.to(FIELD_FRAME_ID, addToCache), TuplesKt.to("modeId", Integer.valueOf(this.modeId))), Boolean.valueOf(barcodeCapture.isEnabled()), this.eventForResultTimeout);
            this.frameDataHandler.removeFromCache(addToCache);
        }
    }

    public final void finishDidScan(boolean enabled) {
        this.barcodeScannedEvent.submitResult(Boolean.valueOf(enabled));
    }

    public final void finishDidUpdateSession(boolean enabled) {
        this.sessionUpdatedEvent.submitResult(Boolean.valueOf(enabled));
    }

    public final void resetSession(Long frameSequenceId) {
        BarcodeCaptureSession barcodeCaptureSession = this.latestSession.get();
        if (barcodeCaptureSession == null) {
            return;
        }
        if (frameSequenceId == null || barcodeCaptureSession.getFrameSequenceId() == frameSequenceId.longValue()) {
            barcodeCaptureSession.reset();
        }
    }

    public final void reset() {
        this.barcodeScannedEvent.cancel();
        this.sessionUpdatedEvent.cancel();
        this.latestSession.set(null);
    }

    /* compiled from: FrameworksBarcodeCaptureListener.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/capture/listeners/FrameworksBarcodeCaptureListener$Companion;", "", "()V", "FIELD_FRAME_ID", "", "FIELD_MODE_ID", "FIELD_SESSION", "ON_BARCODE_SCANNED_EVENT_NAME", "ON_SESSION_UPDATED_EVENT_NAME", "create", "Lcom/scandit/datacapture/frameworks/barcode/capture/listeners/FrameworksBarcodeCaptureListener;", "eventEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "modeId", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final FrameworksBarcodeCaptureListener create(Emitter eventEmitter, int modeId) {
            Intrinsics.checkNotNullParameter(eventEmitter, "eventEmitter");
            return new FrameworksBarcodeCaptureListener(eventEmitter, modeId, null, null, null, null, 60, null);
        }
    }
}
