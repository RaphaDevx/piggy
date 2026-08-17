package com.scandit.datacapture.frameworks.barcode.selection.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.selection.capture.BarcodeSelection;
import com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionListener;
import com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSession;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.frameworks.barcode.extensions.ExtensionsKt;
import com.scandit.datacapture.frameworks.barcode.selection.data.FrameworksBarcodeSelectionSession;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.events.EventForResult;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.frames.DefaultFrameDataHandler;
import com.scandit.datacapture.frameworks.core.frames.FrameDataHandler;
import com.scandit.datacapture.frameworks.core.lifecycle.AppState;
import com.scandit.datacapture.frameworks.core.lifecycle.DefaultActivityLifecycle;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeSelectionListener.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\b\u0016\u0018\u0000 (2\u00020\u0001:\u0001(B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0012J\u000e\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u000bJ\u000e\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u000bJ\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\"\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\"\u0010\"\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0006\u0010#\u001a\u00020\u0012J\u0015\u0010$\u001a\u00020\u00122\b\u0010%\u001a\u0004\u0018\u00010&¢\u0006\u0002\u0010'R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/selection/listeners/FrameworksBarcodeSelectionListener;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionListener;", "eventEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "frameDataHandler", "Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;", "appState", "Lcom/scandit/datacapture/frameworks/core/lifecycle/AppState;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;Lcom/scandit/datacapture/frameworks/core/lifecycle/AppState;)V", "isEnabled", "Ljava/util/concurrent/atomic/AtomicReference;", "", "latestSession", "Lcom/scandit/datacapture/frameworks/barcode/selection/data/FrameworksBarcodeSelectionSession;", "onSelectionUpdatedEvent", "Lcom/scandit/datacapture/frameworks/core/events/EventForResult;", "onSessionUpdatedEvent", "disable", "", "enable", "finishDidSelect", ExtentionsKt.ENABLED_KEY, "finishDidUpdateSession", "getBarcodeCount", "", "selectionIdentifier", "", "onSelectionUpdated", "barcodeSelection", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelection;", "session", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionSession;", "frameData", "Lcom/scandit/datacapture/core/data/FrameData;", "onSessionUpdated", "reset", "resetSession", "frameSequenceId", "", "(Ljava/lang/Long;)V", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksBarcodeSelectionListener implements BarcodeSelectionListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_FRAME_ID = "frameId";
    private static final String FIELD_SESSION = "session";
    public static final String ON_SELECTION_UPDATE_EVENT_NAME = "BarcodeSelectionListener.didUpdateSelection";
    public static final String ON_SESSION_UPDATE_EVENT_NAME = "BarcodeSelectionListener.didUpdateSession";
    private final AppState appState;
    private final Emitter eventEmitter;
    private final FrameDataHandler frameDataHandler;
    private final AtomicReference<Boolean> isEnabled;
    private final AtomicReference<FrameworksBarcodeSelectionSession> latestSession;
    private final EventForResult<Boolean> onSelectionUpdatedEvent;
    private final EventForResult<Boolean> onSessionUpdatedEvent;

    @JvmStatic
    public static final FrameworksBarcodeSelectionListener create(Emitter emitter) {
        return INSTANCE.create(emitter);
    }

    public FrameworksBarcodeSelectionListener(Emitter eventEmitter, FrameDataHandler frameDataHandler, AppState appState) {
        Intrinsics.checkNotNullParameter(eventEmitter, "eventEmitter");
        Intrinsics.checkNotNullParameter(frameDataHandler, "frameDataHandler");
        Intrinsics.checkNotNullParameter(appState, "appState");
        this.eventEmitter = eventEmitter;
        this.frameDataHandler = frameDataHandler;
        this.appState = appState;
        this.latestSession = new AtomicReference<>();
        this.isEnabled = new AtomicReference<>(false);
        this.onSelectionUpdatedEvent = new EventForResult<>("BarcodeSelectionListener.didUpdateSelection", null, null, 6, null);
        this.onSessionUpdatedEvent = new EventForResult<>("BarcodeSelectionListener.didUpdateSession", null, null, 6, null);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionListener
    public void onObservationStarted(BarcodeSelection barcodeSelection) {
        BarcodeSelectionListener.DefaultImpls.onObservationStarted(this, barcodeSelection);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionListener
    public void onObservationStopped(BarcodeSelection barcodeSelection) {
        BarcodeSelectionListener.DefaultImpls.onObservationStopped(this, barcodeSelection);
    }

    public /* synthetic */ FrameworksBarcodeSelectionListener(Emitter emitter, DefaultFrameDataHandler defaultFrameDataHandler, DefaultActivityLifecycle defaultActivityLifecycle, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(emitter, (i & 2) != 0 ? DefaultFrameDataHandler.INSTANCE.getInstance() : defaultFrameDataHandler, (i & 4) != 0 ? DefaultActivityLifecycle.INSTANCE.getInstance() : defaultActivityLifecycle);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionListener
    public void onSelectionUpdated(BarcodeSelection barcodeSelection, BarcodeSelectionSession session, FrameData frameData) {
        Intrinsics.checkNotNullParameter(barcodeSelection, "barcodeSelection");
        Intrinsics.checkNotNullParameter(session, "session");
        if (this.appState.isResumed() && this.isEnabled.get().booleanValue() && this.eventEmitter.hasListenersForEvent("BarcodeSelectionListener.didUpdateSelection")) {
            this.latestSession.set(FrameworksBarcodeSelectionSession.INSTANCE.fromSelectionSession(session));
            String addToCache = frameData != null ? this.frameDataHandler.addToCache(frameData) : null;
            EventForResult.emit$default(this.onSelectionUpdatedEvent, this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to("session", session.toJson()), TuplesKt.to(FIELD_FRAME_ID, addToCache)), Boolean.valueOf(barcodeSelection.isEnabled()), 0L, 8, null);
            if (addToCache != null) {
                this.frameDataHandler.removeFromCache(addToCache);
            }
        }
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionListener
    public void onSessionUpdated(BarcodeSelection barcodeSelection, BarcodeSelectionSession session, FrameData frameData) {
        Intrinsics.checkNotNullParameter(barcodeSelection, "barcodeSelection");
        Intrinsics.checkNotNullParameter(session, "session");
        if (this.isEnabled.get().booleanValue() && this.eventEmitter.hasListenersForEvent("BarcodeSelectionListener.didUpdateSession")) {
            String addToCache = frameData != null ? this.frameDataHandler.addToCache(frameData) : null;
            EventForResult.emit$default(this.onSessionUpdatedEvent, this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to("session", session.toJson()), TuplesKt.to(FIELD_FRAME_ID, addToCache)), Boolean.valueOf(barcodeSelection.isEnabled()), 0L, 8, null);
            if (addToCache != null) {
                this.frameDataHandler.removeFromCache(addToCache);
            }
        }
    }

    public final void enable() {
        this.isEnabled.set(true);
    }

    public final void disable() {
        this.isEnabled.set(false);
        this.onSelectionUpdatedEvent.cancel();
        this.onSessionUpdatedEvent.cancel();
        this.latestSession.set(null);
    }

    public final void finishDidSelect(boolean enabled) {
        this.onSelectionUpdatedEvent.submitResult(Boolean.valueOf(enabled));
    }

    public final void finishDidUpdateSession(boolean enabled) {
        this.onSessionUpdatedEvent.submitResult(Boolean.valueOf(enabled));
    }

    public final int getBarcodeCount(String selectionIdentifier) {
        BarcodeSelectionSession barcodeSelectionSession;
        List<Barcode> selectedBarcodes;
        Object obj;
        Intrinsics.checkNotNullParameter(selectionIdentifier, "selectionIdentifier");
        FrameworksBarcodeSelectionSession frameworksBarcodeSelectionSession = this.latestSession.get();
        if (frameworksBarcodeSelectionSession == null || (barcodeSelectionSession = frameworksBarcodeSelectionSession.getSelectionSession().get()) == null || (selectedBarcodes = barcodeSelectionSession.getSelectedBarcodes()) == null) {
            return 0;
        }
        Iterator<T> it = selectedBarcodes.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(ExtensionsKt.selectionIdentifier((Barcode) obj), selectionIdentifier)) {
                break;
            }
        }
        Barcode barcode = (Barcode) obj;
        if (barcode == null) {
            return 0;
        }
        BarcodeSelectionSession barcodeSelectionSession2 = frameworksBarcodeSelectionSession.getSelectionSession().get();
        Integer valueOf = barcodeSelectionSession2 != null ? Integer.valueOf(barcodeSelectionSession2.getCount(barcode)) : null;
        if (valueOf != null) {
            return valueOf.intValue();
        }
        return 0;
    }

    public final void resetSession(Long frameSequenceId) {
        BarcodeSelectionSession barcodeSelectionSession;
        FrameworksBarcodeSelectionSession frameworksBarcodeSelectionSession = this.latestSession.get();
        if (frameworksBarcodeSelectionSession == null) {
            return;
        }
        if ((frameSequenceId == null || frameworksBarcodeSelectionSession.getFrameSequenceId() == frameSequenceId.longValue()) && (barcodeSelectionSession = frameworksBarcodeSelectionSession.getSelectionSession().get()) != null) {
            barcodeSelectionSession.reset();
        }
    }

    public final void reset() {
        resetSession(null);
        this.onSelectionUpdatedEvent.cancel();
        this.onSessionUpdatedEvent.cancel();
    }

    /* compiled from: FrameworksBarcodeSelectionListener.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/selection/listeners/FrameworksBarcodeSelectionListener$Companion;", "", "()V", "FIELD_FRAME_ID", "", "FIELD_SESSION", "ON_SELECTION_UPDATE_EVENT_NAME", "ON_SESSION_UPDATE_EVENT_NAME", "create", "Lcom/scandit/datacapture/frameworks/barcode/selection/listeners/FrameworksBarcodeSelectionListener;", "eventEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final FrameworksBarcodeSelectionListener create(Emitter eventEmitter) {
            Intrinsics.checkNotNullParameter(eventEmitter, "eventEmitter");
            return new FrameworksBarcodeSelectionListener(eventEmitter, null, null, 6, null);
        }
    }
}
