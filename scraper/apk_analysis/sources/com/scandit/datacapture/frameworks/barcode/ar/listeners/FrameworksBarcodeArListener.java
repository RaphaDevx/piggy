package com.scandit.datacapture.frameworks.barcode.ar.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.ar.capture.BarcodeAr;
import com.scandit.datacapture.barcode.ar.capture.BarcodeArListener;
import com.scandit.datacapture.barcode.ar.capture.BarcodeArSession;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.frameworks.barcode.ar.caches.BarcodeArAugmentationsCache;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.events.EventForResult;
import com.scandit.datacapture.frameworks.core.frames.DefaultFrameDataHandler;
import com.scandit.datacapture.frameworks.core.frames.FrameDataHandler;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeArListener.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u0015\u001a\u00020\u0016J \u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0006\u0010\u001d\u001a\u00020\u0016J\u0006\u0010\u001e\u001a\u00020\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArListener;", "Lcom/scandit/datacapture/barcode/ar/capture/BarcodeArListener;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodeArListener.FIELD_VIEW_ID, "", "cache", "Lcom/scandit/datacapture/frameworks/barcode/ar/caches/BarcodeArAugmentationsCache;", "frameDataHandler", "Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;ILcom/scandit/datacapture/frameworks/barcode/ar/caches/BarcodeArAugmentationsCache;Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;)V", "getCache", "()Lcom/scandit/datacapture/frameworks/barcode/ar/caches/BarcodeArAugmentationsCache;", "eventForResultTimeout", "", "lastTrackedSession", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/scandit/datacapture/barcode/ar/capture/BarcodeArSession;", "onSessionUpdatedEvent", "Lcom/scandit/datacapture/frameworks/core/events/EventForResult;", "", "finishDidUpdateSession", "", "onSessionUpdated", "barcodeAr", "Lcom/scandit/datacapture/barcode/ar/capture/BarcodeAr;", "session", "frameData", "Lcom/scandit/datacapture/core/data/FrameData;", "reset", "resetSession", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksBarcodeArListener implements BarcodeArListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_FRAME_ID = "frameId";
    private static final String FIELD_SESSION = "session";
    private static final String FIELD_VIEW_ID = "viewId";
    public static final String ON_SESSION_UPDATED_EVENT_NAME = "BarcodeArListener.didUpdateSession";
    private final BarcodeArAugmentationsCache cache;
    private final Emitter emitter;
    private long eventForResultTimeout;
    private final FrameDataHandler frameDataHandler;
    private final AtomicReference<BarcodeArSession> lastTrackedSession;
    private final EventForResult<Boolean> onSessionUpdatedEvent;
    private final int viewId;

    @JvmStatic
    public static final FrameworksBarcodeArListener create(Emitter emitter, int i, BarcodeArAugmentationsCache barcodeArAugmentationsCache) {
        return INSTANCE.create(emitter, i, barcodeArAugmentationsCache);
    }

    public FrameworksBarcodeArListener(Emitter emitter, int i, BarcodeArAugmentationsCache cache, FrameDataHandler frameDataHandler) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        Intrinsics.checkNotNullParameter(cache, "cache");
        Intrinsics.checkNotNullParameter(frameDataHandler, "frameDataHandler");
        this.emitter = emitter;
        this.viewId = i;
        this.cache = cache;
        this.frameDataHandler = frameDataHandler;
        this.lastTrackedSession = new AtomicReference<>();
        this.eventForResultTimeout = EventForResult.DEFAULT_TIMEOUT_MILLIS;
        this.onSessionUpdatedEvent = new EventForResult<>(ON_SESSION_UPDATED_EVENT_NAME, null, null, 6, null);
    }

    @Override // com.scandit.datacapture.barcode.ar.capture.BarcodeArListener
    @ProxyFunction
    public void onObservationStarted(BarcodeAr barcodeAr) {
        BarcodeArListener.DefaultImpls.onObservationStarted(this, barcodeAr);
    }

    @Override // com.scandit.datacapture.barcode.ar.capture.BarcodeArListener
    @ProxyFunction
    public void onObservationStopped(BarcodeAr barcodeAr) {
        BarcodeArListener.DefaultImpls.onObservationStopped(this, barcodeAr);
    }

    public final BarcodeArAugmentationsCache getCache() {
        return this.cache;
    }

    public /* synthetic */ FrameworksBarcodeArListener(Emitter emitter, int i, BarcodeArAugmentationsCache barcodeArAugmentationsCache, DefaultFrameDataHandler defaultFrameDataHandler, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(emitter, i, barcodeArAugmentationsCache, (i2 & 8) != 0 ? DefaultFrameDataHandler.INSTANCE.getInstance() : defaultFrameDataHandler);
    }

    @Override // com.scandit.datacapture.barcode.ar.capture.BarcodeArListener
    public void onSessionUpdated(BarcodeAr barcodeAr, BarcodeArSession session, FrameData frameData) {
        Intrinsics.checkNotNullParameter(barcodeAr, "barcodeAr");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(frameData, "frameData");
        this.lastTrackedSession.set(session);
        this.cache.updateFromSession(session);
        String addToCache = this.frameDataHandler.addToCache(frameData);
        this.onSessionUpdatedEvent.emit(this.emitter, MapsKt.mutableMapOf(TuplesKt.to("session", session.toJson()), TuplesKt.to(FIELD_FRAME_ID, addToCache), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))), true, this.eventForResultTimeout);
        this.frameDataHandler.removeFromCache(addToCache);
    }

    public final void resetSession() {
        this.lastTrackedSession.get().reset();
    }

    public final void finishDidUpdateSession() {
        this.onSessionUpdatedEvent.submitResult(true);
    }

    public final void reset() {
        this.onSessionUpdatedEvent.cancel();
        this.lastTrackedSession.set(null);
    }

    /* compiled from: FrameworksBarcodeArListener.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArListener$Companion;", "", "()V", "FIELD_FRAME_ID", "", "FIELD_SESSION", "FIELD_VIEW_ID", "ON_SESSION_UPDATED_EVENT_NAME", "create", "Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArListener;", "eventEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodeArListener.FIELD_VIEW_ID, "", "cache", "Lcom/scandit/datacapture/frameworks/barcode/ar/caches/BarcodeArAugmentationsCache;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final FrameworksBarcodeArListener create(Emitter eventEmitter, int viewId, BarcodeArAugmentationsCache cache) {
            Intrinsics.checkNotNullParameter(eventEmitter, "eventEmitter");
            Intrinsics.checkNotNullParameter(cache, "cache");
            return new FrameworksBarcodeArListener(eventEmitter, viewId, cache, null, 8, null);
        }
    }
}
