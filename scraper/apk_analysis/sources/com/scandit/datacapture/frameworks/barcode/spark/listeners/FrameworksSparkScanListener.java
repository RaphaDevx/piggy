package com.scandit.datacapture.frameworks.barcode.spark.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.spark.capture.SparkScan;
import com.scandit.datacapture.barcode.spark.capture.SparkScanListener;
import com.scandit.datacapture.barcode.spark.capture.SparkScanSession;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.events.EventForResult;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.frames.DefaultFrameDataHandler;
import com.scandit.datacapture.frameworks.core.frames.FrameDataHandler;
import com.scandit.datacapture.frameworks.core.lifecycle.AppState;
import com.scandit.datacapture.frameworks.core.lifecycle.DefaultActivityLifecycle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksSparkScanListener.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 !2\u00020\u0001:\u0001!BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0015J\"\u0010\t\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00132\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u000e\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\bJ\u000e\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\bJ\"\u0010\u0006\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00132\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0006\u0010\u001f\u001a\u00020\u0015J\u0006\u0010 \u001a\u00020\u0015R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/spark/listeners/FrameworksSparkScanListener;", "Lcom/scandit/datacapture/barcode/spark/capture/SparkScanListener;", "eventEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksSparkScanListener.FIELD_VIEW_ID, "", "onSessionUpdated", "Lcom/scandit/datacapture/frameworks/core/events/EventForResult;", "", "onBarcodeScanned", "frameDataHandler", "Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;", "appState", "Lcom/scandit/datacapture/frameworks/core/lifecycle/AppState;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;ILcom/scandit/datacapture/frameworks/core/events/EventForResult;Lcom/scandit/datacapture/frameworks/core/events/EventForResult;Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;Lcom/scandit/datacapture/frameworks/core/lifecycle/AppState;)V", "isEnabled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "latestSession", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/scandit/datacapture/barcode/spark/capture/SparkScanSession;", "disable", "", "enable", "sparkScan", "Lcom/scandit/datacapture/barcode/spark/capture/SparkScan;", "session", "data", "Lcom/scandit/datacapture/core/data/FrameData;", "onFinishBarcodeScannedCallback", ExtentionsKt.ENABLED_KEY, "onFinishSessionUpdatedCallback", "reset", "resetLastSession", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksSparkScanListener implements SparkScanListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_FRAME_ID = "frameId";
    private static final String FIELD_SESSION = "session";
    private static final String FIELD_VIEW_ID = "viewId";
    public static final String ON_BARCODE_SCANNED_EVENT_NAME = "SparkScanListener.didScan";
    public static final String ON_SESSION_UPDATED_EVENT_NAME = "SparkScanListener.didUpdateSession";
    private final AppState appState;
    private final Emitter eventEmitter;
    private final FrameDataHandler frameDataHandler;
    private AtomicBoolean isEnabled;
    private final AtomicReference<SparkScanSession> latestSession;
    private final EventForResult<Boolean> onBarcodeScanned;
    private final EventForResult<Boolean> onSessionUpdated;
    private final int viewId;

    @JvmStatic
    public static final FrameworksSparkScanListener create(Emitter emitter, int i) {
        return INSTANCE.create(emitter, i);
    }

    public FrameworksSparkScanListener(Emitter eventEmitter, int i, EventForResult<Boolean> onSessionUpdated, EventForResult<Boolean> onBarcodeScanned, FrameDataHandler frameDataHandler, AppState appState) {
        Intrinsics.checkNotNullParameter(eventEmitter, "eventEmitter");
        Intrinsics.checkNotNullParameter(onSessionUpdated, "onSessionUpdated");
        Intrinsics.checkNotNullParameter(onBarcodeScanned, "onBarcodeScanned");
        Intrinsics.checkNotNullParameter(frameDataHandler, "frameDataHandler");
        Intrinsics.checkNotNullParameter(appState, "appState");
        this.eventEmitter = eventEmitter;
        this.viewId = i;
        this.onSessionUpdated = onSessionUpdated;
        this.onBarcodeScanned = onBarcodeScanned;
        this.frameDataHandler = frameDataHandler;
        this.appState = appState;
        this.latestSession = new AtomicReference<>();
        this.isEnabled = new AtomicBoolean(false);
    }

    public /* synthetic */ FrameworksSparkScanListener(Emitter emitter, int i, EventForResult eventForResult, EventForResult eventForResult2, FrameDataHandler frameDataHandler, AppState appState, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(emitter, i, (i2 & 4) != 0 ? new EventForResult(ON_SESSION_UPDATED_EVENT_NAME, null, null, 6, null) : eventForResult, (i2 & 8) != 0 ? new EventForResult(ON_BARCODE_SCANNED_EVENT_NAME, null, null, 6, null) : eventForResult2, (i2 & 16) != 0 ? DefaultFrameDataHandler.INSTANCE.getInstance() : frameDataHandler, (i2 & 32) != 0 ? DefaultActivityLifecycle.INSTANCE.getInstance() : appState);
    }

    public final void enable() {
        this.isEnabled.set(true);
    }

    public final void disable() {
        this.isEnabled.set(false);
        this.onSessionUpdated.cancel();
        this.onBarcodeScanned.cancel();
        this.latestSession.set(null);
    }

    @Override // com.scandit.datacapture.barcode.spark.capture.SparkScanListener
    public void onSessionUpdated(SparkScan sparkScan, SparkScanSession session, FrameData data) {
        Intrinsics.checkNotNullParameter(sparkScan, "sparkScan");
        Intrinsics.checkNotNullParameter(session, "session");
        if (this.appState.isResumed() && this.isEnabled.get() && this.eventEmitter.hasViewSpecificListenersForEvent(this.viewId, ON_SESSION_UPDATED_EVENT_NAME)) {
            String addToCache = data != null ? this.frameDataHandler.addToCache(data) : null;
            this.latestSession.set(session);
            EventForResult.emit$default(this.onSessionUpdated, this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to("session", session.toJson()), TuplesKt.to(FIELD_FRAME_ID, addToCache), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))), true, 0L, 8, null);
            if (addToCache != null) {
                this.frameDataHandler.removeFromCache(addToCache);
            }
        }
    }

    @Override // com.scandit.datacapture.barcode.spark.capture.SparkScanListener
    public void onBarcodeScanned(SparkScan sparkScan, SparkScanSession session, FrameData data) {
        Intrinsics.checkNotNullParameter(sparkScan, "sparkScan");
        Intrinsics.checkNotNullParameter(session, "session");
        if (this.isEnabled.get() && this.eventEmitter.hasViewSpecificListenersForEvent(this.viewId, ON_BARCODE_SCANNED_EVENT_NAME)) {
            String addToCache = data != null ? this.frameDataHandler.addToCache(data) : null;
            this.latestSession.set(session);
            EventForResult.emit$default(this.onBarcodeScanned, this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to("session", session.toJson()), TuplesKt.to(FIELD_FRAME_ID, addToCache), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))), true, 0L, 8, null);
            if (addToCache != null) {
                this.frameDataHandler.removeFromCache(addToCache);
            }
        }
    }

    public final void onFinishBarcodeScannedCallback(boolean enabled) {
        this.onBarcodeScanned.submitResult(Boolean.valueOf(enabled));
    }

    public final void onFinishSessionUpdatedCallback(boolean enabled) {
        this.onSessionUpdated.submitResult(Boolean.valueOf(enabled));
    }

    public final void resetLastSession() {
        SparkScanSession sparkScanSession = this.latestSession.get();
        if (sparkScanSession == null) {
            return;
        }
        sparkScanSession.reset();
    }

    public final void reset() {
        this.onBarcodeScanned.cancel();
        this.onSessionUpdated.cancel();
    }

    /* compiled from: FrameworksSparkScanListener.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/spark/listeners/FrameworksSparkScanListener$Companion;", "", "()V", "FIELD_FRAME_ID", "", "FIELD_SESSION", "FIELD_VIEW_ID", "ON_BARCODE_SCANNED_EVENT_NAME", "ON_SESSION_UPDATED_EVENT_NAME", "create", "Lcom/scandit/datacapture/frameworks/barcode/spark/listeners/FrameworksSparkScanListener;", "eventEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksSparkScanListener.FIELD_VIEW_ID, "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final FrameworksSparkScanListener create(Emitter eventEmitter, int viewId) {
            Intrinsics.checkNotNullParameter(eventEmitter, "eventEmitter");
            return new FrameworksSparkScanListener(eventEmitter, viewId, null, null, null, null, 60, null);
        }
    }
}
