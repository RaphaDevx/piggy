package com.scandit.datacapture.frameworks.barcode.batch.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.batch.capture.BarcodeBatch;
import com.scandit.datacapture.barcode.batch.capture.BarcodeBatchListener;
import com.scandit.datacapture.barcode.batch.capture.BarcodeBatchSession;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.frameworks.barcode.batch.data.FrameworksBarcodeBatchSession;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.events.EventForResult;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.frames.DefaultFrameDataHandler;
import com.scandit.datacapture.frameworks.core.frames.FrameDataHandler;
import com.scandit.datacapture.frameworks.core.lifecycle.AppState;
import com.scandit.datacapture.frameworks.core.lifecycle.DefaultActivityLifecycle;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeBatchListener.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 !2\u00020\u0001:\u0001!B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0014J \u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0006\u0010\u001f\u001a\u00020\u0016J\u000e\u0010 \u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0014R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/batch/listeners/FrameworksBarcodeBatchListener;", "Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatchListener;", "eventEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "modeId", "", "cachedBatchSession", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/scandit/datacapture/frameworks/barcode/batch/data/FrameworksBarcodeBatchSession;", "frameDataHandler", "Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;", "appState", "Lcom/scandit/datacapture/frameworks/core/lifecycle/AppState;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;ILjava/util/concurrent/atomic/AtomicReference;Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;Lcom/scandit/datacapture/frameworks/core/lifecycle/AppState;)V", "eventForResultTimeout", "", "isEnabled", "Ljava/util/concurrent/atomic/AtomicBoolean;", "onSessionUpdatedEvent", "Lcom/scandit/datacapture/frameworks/core/events/EventForResult;", "", "finishDidUpdateSession", "", ExtentionsKt.ENABLED_KEY, "onSessionUpdated", "mode", "Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatch;", "session", "Lcom/scandit/datacapture/barcode/batch/capture/BarcodeBatchSession;", "data", "Lcom/scandit/datacapture/core/data/FrameData;", "reset", "setEnabled", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksBarcodeBatchListener implements BarcodeBatchListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_FRAME_ID = "frameId";
    private static final String FIELD_MODE_ID = "modeId";
    private static final String FIELD_SESSION = "session";
    public static final String ON_SESSION_UPDATED_EVENT_NAME = "BarcodeBatchListener.didUpdateSession";
    private final AppState appState;
    private final AtomicReference<FrameworksBarcodeBatchSession> cachedBatchSession;
    private final Emitter eventEmitter;
    private long eventForResultTimeout;
    private final FrameDataHandler frameDataHandler;
    private final AtomicBoolean isEnabled;
    private final int modeId;
    private final EventForResult<Boolean> onSessionUpdatedEvent;

    @JvmStatic
    public static final FrameworksBarcodeBatchListener create(Emitter emitter, int i, AtomicReference<FrameworksBarcodeBatchSession> atomicReference) {
        return INSTANCE.create(emitter, i, atomicReference);
    }

    public FrameworksBarcodeBatchListener(Emitter eventEmitter, int i, AtomicReference<FrameworksBarcodeBatchSession> cachedBatchSession, FrameDataHandler frameDataHandler, AppState appState) {
        Intrinsics.checkNotNullParameter(eventEmitter, "eventEmitter");
        Intrinsics.checkNotNullParameter(cachedBatchSession, "cachedBatchSession");
        Intrinsics.checkNotNullParameter(frameDataHandler, "frameDataHandler");
        Intrinsics.checkNotNullParameter(appState, "appState");
        this.eventEmitter = eventEmitter;
        this.modeId = i;
        this.cachedBatchSession = cachedBatchSession;
        this.frameDataHandler = frameDataHandler;
        this.appState = appState;
        this.eventForResultTimeout = EventForResult.DEFAULT_TIMEOUT_MILLIS;
        this.onSessionUpdatedEvent = new EventForResult<>(ON_SESSION_UPDATED_EVENT_NAME, null, null, 6, null);
        this.isEnabled = new AtomicBoolean(false);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchListener
    @ProxyFunction
    public void onObservationStarted(BarcodeBatch barcodeBatch) {
        BarcodeBatchListener.DefaultImpls.onObservationStarted(this, barcodeBatch);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchListener
    @ProxyFunction
    public void onObservationStopped(BarcodeBatch barcodeBatch) {
        BarcodeBatchListener.DefaultImpls.onObservationStopped(this, barcodeBatch);
    }

    public /* synthetic */ FrameworksBarcodeBatchListener(Emitter emitter, int i, AtomicReference atomicReference, DefaultFrameDataHandler defaultFrameDataHandler, DefaultActivityLifecycle defaultActivityLifecycle, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(emitter, i, atomicReference, (i2 & 8) != 0 ? DefaultFrameDataHandler.INSTANCE.getInstance() : defaultFrameDataHandler, (i2 & 16) != 0 ? DefaultActivityLifecycle.INSTANCE.getInstance() : defaultActivityLifecycle);
    }

    @Override // com.scandit.datacapture.barcode.batch.capture.BarcodeBatchListener
    public void onSessionUpdated(BarcodeBatch mode, BarcodeBatchSession session, FrameData data) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        if (this.appState.isResumed()) {
            this.cachedBatchSession.set(FrameworksBarcodeBatchSession.INSTANCE.fromBatchSession(session));
            if (this.isEnabled.get()) {
                String addToCache = this.frameDataHandler.addToCache(data);
                this.onSessionUpdatedEvent.emit(this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to("session", session.toJson()), TuplesKt.to(FIELD_FRAME_ID, addToCache), TuplesKt.to("modeId", Integer.valueOf(this.modeId))), Boolean.valueOf(mode.isEnabled()), this.eventForResultTimeout);
                this.frameDataHandler.removeFromCache(addToCache);
            }
        }
    }

    public final void setEnabled(boolean enabled) {
        this.isEnabled.set(enabled);
    }

    public final void finishDidUpdateSession(boolean enabled) {
        this.onSessionUpdatedEvent.submitResult(Boolean.valueOf(enabled));
    }

    public final void reset() {
        this.cachedBatchSession.set(null);
        this.onSessionUpdatedEvent.cancel();
    }

    /* compiled from: FrameworksBarcodeBatchListener.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/batch/listeners/FrameworksBarcodeBatchListener$Companion;", "", "()V", "FIELD_FRAME_ID", "", "FIELD_MODE_ID", "FIELD_SESSION", "ON_SESSION_UPDATED_EVENT_NAME", "create", "Lcom/scandit/datacapture/frameworks/barcode/batch/listeners/FrameworksBarcodeBatchListener;", "eventEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "modeId", "", "cachedBatchSession", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/scandit/datacapture/frameworks/barcode/batch/data/FrameworksBarcodeBatchSession;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final FrameworksBarcodeBatchListener create(Emitter eventEmitter, int modeId, AtomicReference<FrameworksBarcodeBatchSession> cachedBatchSession) {
            Intrinsics.checkNotNullParameter(eventEmitter, "eventEmitter");
            Intrinsics.checkNotNullParameter(cachedBatchSession, "cachedBatchSession");
            return new FrameworksBarcodeBatchListener(eventEmitter, modeId, cachedBatchSession, null, null, 24, null);
        }
    }
}
