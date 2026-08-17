package com.scandit.datacapture.frameworks.barcode.capture;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.capture.BarcodeCaptureDeserializer;
import com.scandit.datacapture.barcode.capture.BarcodeCaptureSession;
import com.scandit.datacapture.barcode.internal.sdk.feedback.BarcodeCaptureFeedbackDeserializer;
import com.scandit.datacapture.barcode.ui.overlay.BarcodeCaptureOverlay;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import com.scandit.datacapture.frameworks.barcode.capture.data.BarcodeCaptureModeCreationData;
import com.scandit.datacapture.frameworks.barcode.capture.data.BarcodeCaptureOverlayCreationData;
import com.scandit.datacapture.frameworks.barcode.capture.data.FrameworksBarcodeCaptureSession;
import com.scandit.datacapture.frameworks.barcode.capture.data.defaults.BarcodeCaptureDefaults;
import com.scandit.datacapture.frameworks.barcode.capture.mode.FrameworksBarcodeCaptureMode;
import com.scandit.datacapture.frameworks.core.common.BasicFrameworkModule;
import com.scandit.datacapture.frameworks.core.context.DefaultFrameworksCaptureContext;
import com.scandit.datacapture.frameworks.core.context.FrameworksCaptureContext;
import com.scandit.datacapture.frameworks.core.deserialization.DefaultDeserializationLifecycleObserver;
import com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver;
import com.scandit.datacapture.frameworks.core.errors.FrameDataNullError;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.frames.DefaultFrameDataHandler;
import com.scandit.datacapture.frameworks.core.frames.FrameDataHandler;
import com.scandit.datacapture.frameworks.core.handlers.DataCaptureViewHandler;
import com.scandit.datacapture.frameworks.core.handlers.DefaultDataCaptureViewHandler;
import com.scandit.datacapture.frameworks.core.method.FrameworksMethodCall;
import com.scandit.datacapture.frameworks.core.mode.FrameworksBaseMode;
import com.scandit.datacapture.frameworks.core.result.FrameworksResult;
import com.scandit.datacapture.frameworks.core.ui.FrameworksDataCaptureView;
import com.scandit.datacapture.frameworks.core.utils.DefaultFrameworksLog;
import com.scandit.datacapture.frameworks.core.utils.FrameworksLog;
import com.tealium.library.DataSources;
import io.sentry.SentryEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;
import org.json.JSONObject;

/* compiled from: BarcodeCaptureModule.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u000b\b\u0016\u0018\u0000 F2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001FBI\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u001e\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010\u001e\u001a\u00020\u0016H\u0002J\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0016\u0010#\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010$\u001a\u00020 J\u0016\u0010%\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010$\u001a\u00020 J\u0014\u0010&\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0006\u0012\u0004\u0018\u00010(0'J\u0016\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010+\u001a\u00020 J\b\u0010,\u001a\u00020\u0016H\u0016J\u0010\u0010-\u001a\u00020\u00162\u0006\u0010.\u001a\u00020\u001bH\u0016J\u0018\u0010/\u001a\u00020\u00162\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u001bH\u0016J\b\u00103\u001a\u00020\u0016H\u0016J\u0010\u00104\u001a\u00020\u00162\u0006\u00105\u001a\u000206H\u0016J\b\u00107\u001a\u00020\u0016H\u0016J\u0010\u00108\u001a\u00020\u00162\u0006\u0010.\u001a\u00020\u001bH\u0016J\u000e\u00109\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u0017\u0010:\u001a\u00020\u00162\b\u0010;\u001a\u0004\u0018\u00010<H\u0002¢\u0006\u0002\u0010=J\u0015\u0010>\u001a\u00020\u00162\b\u0010;\u001a\u0004\u0018\u00010<¢\u0006\u0002\u0010=J\u0016\u0010?\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010$\u001a\u00020 J\u000e\u0010@\u001a\u00020\u00162\u0006\u0010$\u001a\u00020 J\u001e\u0010A\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010B\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0016\u0010C\u001a\u00020\u00162\u0006\u0010.\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u001e\u0010D\u001a\u00020\u00162\u0006\u0010E\u001a\u00020\u00182\u0006\u00102\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/capture/BarcodeCaptureModule;", "Lcom/scandit/datacapture/frameworks/core/common/BasicFrameworkModule;", "Lcom/scandit/datacapture/frameworks/barcode/capture/mode/FrameworksBarcodeCaptureMode;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "barcodeCaptureDeserializer", "Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializer;", SentryEvent.JsonKeys.LOGGER, "Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;", "dataCaptureViewHandler", "Lcom/scandit/datacapture/frameworks/core/handlers/DataCaptureViewHandler;", "frameDataHandler", "Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;", "captureContext", "Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;", "deserializationLifecycleObserver", "Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;Lcom/scandit/datacapture/barcode/capture/BarcodeCaptureDeserializer;Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;Lcom/scandit/datacapture/frameworks/core/handlers/DataCaptureViewHandler;Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;)V", "cachedCaptureSession", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/scandit/datacapture/frameworks/barcode/capture/data/FrameworksBarcodeCaptureSession;", "addListener", "", ExtentionsKt.MODE_ID_KEY, "", "applyModeSettings", "modeSettingsJson", "", "result", "Lcom/scandit/datacapture/frameworks/core/result/FrameworksResult;", "cancelAllListenerPendingEvents", "execute", "", "method", "Lcom/scandit/datacapture/frameworks/core/method/FrameworksMethodCall;", "finishDidScan", ExtentionsKt.ENABLED_KEY, "finishDidUpdateSession", "getDefaults", "", "", "getFrameDataBytes", "frameId", "isModeEnabled", "onActivityStop", "onAddModeToContext", "modeJson", "onAddOverlayToView", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/frameworks/core/ui/FrameworksDataCaptureView;", "overlayJson", "onAllModesRemovedFromContext", "onCreate", "context", "Landroid/content/Context;", "onDestroy", "onRemoveModeFromContext", "removeListener", "resetLatestSession", "frameSequenceId", "", "(Ljava/lang/Long;)V", "resetSession", "setModeEnabled", "setTopMostModeEnabled", "updateFeedback", "feedbackJson", "updateModeFromJson", "updateOverlay", "viewId", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class BarcodeCaptureModule extends BasicFrameworkModule<FrameworksBarcodeCaptureMode> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final BarcodeCaptureDeserializer barcodeCaptureDeserializer;
    private final AtomicReference<FrameworksBarcodeCaptureSession> cachedCaptureSession;
    private final FrameworksCaptureContext captureContext;
    private final DataCaptureViewHandler dataCaptureViewHandler;
    private final Emitter emitter;
    private final FrameDataHandler frameDataHandler;
    private final FrameworksLog logger;

    @JvmStatic
    public static final BarcodeCaptureModule create(Emitter emitter) {
        return INSTANCE.create(emitter);
    }

    public /* synthetic */ BarcodeCaptureModule(Emitter emitter, BarcodeCaptureDeserializer barcodeCaptureDeserializer, FrameworksLog frameworksLog, DataCaptureViewHandler dataCaptureViewHandler, FrameDataHandler frameDataHandler, FrameworksCaptureContext frameworksCaptureContext, DeserializationLifecycleObserver deserializationLifecycleObserver, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(emitter, (i & 2) != 0 ? new BarcodeCaptureDeserializer() : barcodeCaptureDeserializer, (i & 4) != 0 ? DefaultFrameworksLog.INSTANCE.getInstance() : frameworksLog, (i & 8) != 0 ? DefaultDataCaptureViewHandler.INSTANCE.getInstance() : dataCaptureViewHandler, (i & 16) != 0 ? DefaultFrameDataHandler.INSTANCE.getInstance() : frameDataHandler, (i & 32) != 0 ? DefaultFrameworksCaptureContext.INSTANCE.getInstance() : frameworksCaptureContext, (i & 64) != 0 ? DefaultDeserializationLifecycleObserver.INSTANCE.getInstance() : deserializationLifecycleObserver);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BarcodeCaptureModule(Emitter emitter, BarcodeCaptureDeserializer barcodeCaptureDeserializer, FrameworksLog logger, DataCaptureViewHandler dataCaptureViewHandler, FrameDataHandler frameDataHandler, FrameworksCaptureContext captureContext, DeserializationLifecycleObserver deserializationLifecycleObserver) {
        super(deserializationLifecycleObserver);
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        Intrinsics.checkNotNullParameter(barcodeCaptureDeserializer, "barcodeCaptureDeserializer");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(dataCaptureViewHandler, "dataCaptureViewHandler");
        Intrinsics.checkNotNullParameter(frameDataHandler, "frameDataHandler");
        Intrinsics.checkNotNullParameter(captureContext, "captureContext");
        Intrinsics.checkNotNullParameter(deserializationLifecycleObserver, "deserializationLifecycleObserver");
        this.emitter = emitter;
        this.barcodeCaptureDeserializer = barcodeCaptureDeserializer;
        this.logger = logger;
        this.dataCaptureViewHandler = dataCaptureViewHandler;
        this.frameDataHandler = frameDataHandler;
        this.captureContext = captureContext;
        this.cachedCaptureSession = new AtomicReference<>();
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.FrameworkModule
    public void onCreate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onCreate(context);
        attachForActivityLifecycleEvents();
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.FrameworkModule
    public void onDestroy() {
        detachForActivityLifecycleEvents();
        super.onDestroy();
    }

    public final Map<String, Object> getDefaults() {
        return BarcodeCaptureDefaults.INSTANCE.get();
    }

    public final void addListener(final int modeId) {
        FrameworksBarcodeCaptureMode modeFromCache = getModeFromCache(modeId);
        if (modeFromCache == null) {
            addPostModeCreationAction(modeId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.capture.BarcodeCaptureModule$addListener$mode$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    BarcodeCaptureModule.this.addListener(modeId);
                }
            });
        } else {
            modeFromCache.addListener();
        }
    }

    public final void removeListener(final int modeId) {
        FrameworksBarcodeCaptureMode modeFromCache = getModeFromCache(modeId);
        if (modeFromCache == null) {
            addPostModeCreationAction(modeId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.capture.BarcodeCaptureModule$removeListener$mode$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    BarcodeCaptureModule.this.removeListener(modeId);
                }
            });
        } else {
            modeFromCache.removeListener();
        }
    }

    public final void finishDidScan(final int modeId, final boolean enabled) {
        FrameworksBarcodeCaptureMode modeFromCache = getModeFromCache(modeId);
        if (modeFromCache == null) {
            addPostModeCreationAction(modeId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.capture.BarcodeCaptureModule$finishDidScan$mode$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    BarcodeCaptureModule.this.finishDidScan(modeId, enabled);
                }
            });
        } else {
            modeFromCache.finishDidScan(enabled);
        }
    }

    public final void finishDidUpdateSession(final int modeId, final boolean enabled) {
        FrameworksBarcodeCaptureMode modeFromCache = getModeFromCache(modeId);
        if (modeFromCache == null) {
            addPostModeCreationAction(modeId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.capture.BarcodeCaptureModule$finishDidUpdateSession$mode$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    BarcodeCaptureModule.this.finishDidUpdateSession(modeId, enabled);
                }
            });
        } else {
            modeFromCache.finishDidUpdateSession(enabled);
        }
    }

    public final void resetSession(Long frameSequenceId) {
        resetLatestSession(frameSequenceId);
    }

    public final void setModeEnabled(final int modeId, final boolean enabled) {
        FrameworksBarcodeCaptureMode modeFromCache = getModeFromCache(modeId);
        if (modeFromCache == null) {
            addPostModeCreationAction(modeId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.capture.BarcodeCaptureModule$setModeEnabled$mode$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    BarcodeCaptureModule.this.setModeEnabled(modeId, enabled);
                }
            });
        } else {
            modeFromCache.setEnabled(enabled);
        }
    }

    public final void setTopMostModeEnabled(boolean enabled) {
        FrameworksBarcodeCaptureMode topmostMode = getTopmostMode();
        if (topmostMode == null) {
            return;
        }
        topmostMode.setEnabled(enabled);
    }

    public final boolean isModeEnabled() {
        FrameworksBarcodeCaptureMode topmostMode = getTopmostMode();
        return topmostMode != null && topmostMode.isEnabled();
    }

    public final void updateModeFromJson(String modeJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeCaptureMode modeFromCache = getModeFromCache(ExtentionsKt.getModeId(modeJson));
        if (modeFromCache == null) {
            result.success(null);
        } else {
            modeFromCache.updateModeFromJson(modeJson);
            result.success(null);
        }
    }

    public final void applyModeSettings(int modeId, String modeSettingsJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(modeSettingsJson, "modeSettingsJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeCaptureMode modeFromCache = getModeFromCache(modeId);
        if (modeFromCache == null) {
            result.success(null);
        } else {
            modeFromCache.applySettings(modeSettingsJson);
            result.success(null);
        }
    }

    public final void updateOverlay(int viewId, String overlayJson, FrameworksResult result) {
        Object obj;
        Intrinsics.checkNotNullParameter(overlayJson, "overlayJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksDataCaptureView view = this.dataCaptureViewHandler.getView(viewId);
        if (view == null) {
            result.success(null);
            return;
        }
        Iterator<T> it = view.getOverlays().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (((DataCaptureOverlay) obj) instanceof BarcodeCaptureOverlay) {
                    break;
                }
            }
        }
        if (!(obj instanceof BarcodeCaptureOverlay)) {
            obj = null;
        }
        BarcodeCaptureOverlay barcodeCaptureOverlay = (BarcodeCaptureOverlay) obj;
        if (barcodeCaptureOverlay == null) {
            result.success(null);
        } else {
            this.barcodeCaptureDeserializer.updateOverlayFromJson(barcodeCaptureOverlay, overlayJson);
            result.success(true);
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver.Observer
    public void onAddModeToContext(String modeJson) {
        DataCaptureContext contextForModeCreation;
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        BarcodeCaptureModeCreationData fromJson = BarcodeCaptureModeCreationData.INSTANCE.fromJson(modeJson);
        if (Intrinsics.areEqual(fromJson.getModeType(), BarcodeCaptureModeCreationData.MODE_TYPE) && (contextForModeCreation = ExtentionsKt.getContextForModeCreation(this.captureContext, BarcodeCaptureModeCreationData.MODE_TYPE)) != null) {
            FrameworksBarcodeCaptureMode create = FrameworksBarcodeCaptureMode.INSTANCE.create(this.emitter, this.captureContext, fromJson, contextForModeCreation, this.barcodeCaptureDeserializer);
            addModeToCache(fromJson.getModeId(), create);
            Iterator<Function0<Unit>> it = getPostModeCreationActions(fromJson.getModeId()).iterator();
            while (it.hasNext()) {
                it.next().invoke();
            }
            Integer num = create.get_parentId();
            Iterator<Function0<Unit>> it2 = getPostModeCreationActionsByParent(num != null ? num.intValue() : -1).iterator();
            while (it2.hasNext()) {
                it2.next().invoke();
            }
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver.Observer
    public void onRemoveModeFromContext(String modeJson) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        if (Intrinsics.areEqual(ExtentionsKt.getJsonValueTypeAttribute(modeJson), BarcodeCaptureModeCreationData.MODE_TYPE)) {
            int modeId = ExtentionsKt.getModeId(modeJson);
            FrameworksBarcodeCaptureMode modeFromCache = getModeFromCache(modeId);
            if (modeFromCache == null) {
                this.logger.error("Unable to remove the BarcodeCaptureMode from the DataCaptureContext, the mode is null.");
                return;
            }
            modeFromCache.dispose();
            removeModeFromCache(modeId);
            clearPostModeCreationActions(Integer.valueOf(modeId));
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver.Observer
    public void onAllModesRemovedFromContext() {
        Iterator<FrameworksBarcodeCaptureMode> it = getAllModesInCache().iterator();
        while (it.hasNext()) {
            it.next().dispose();
        }
        removeAllModesFromCache();
        clearPostModeCreationActions(null);
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver.Observer
    public void onAddOverlayToView(final FrameworksDataCaptureView view, final String overlayJson) {
        FrameworksBarcodeCaptureMode modeFromCache;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(overlayJson, "overlayJson");
        BarcodeCaptureOverlayCreationData fromJson = BarcodeCaptureOverlayCreationData.INSTANCE.fromJson(overlayJson);
        if (fromJson.getIsBasic()) {
            Integer num = view.get_parentId();
            int intValue = num != null ? num.intValue() : -1;
            if (intValue != -1) {
                FrameworksBaseMode modeFromCacheByParent = getModeFromCacheByParent(intValue);
                modeFromCache = modeFromCacheByParent instanceof FrameworksBarcodeCaptureMode ? (FrameworksBarcodeCaptureMode) modeFromCacheByParent : null;
            } else {
                modeFromCache = getModeFromCache(fromJson.getModeId());
            }
            if (modeFromCache == null) {
                if (intValue != -1) {
                    addPostModeCreationActionByParent(intValue, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.capture.BarcodeCaptureModule$onAddOverlayToView$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            BarcodeCaptureModule.this.onAddOverlayToView(view, overlayJson);
                        }
                    });
                    return;
                } else {
                    addPostModeCreationAction(fromJson.getModeId(), new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.capture.BarcodeCaptureModule$onAddOverlayToView$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            BarcodeCaptureModule.this.onAddOverlayToView(view, overlayJson);
                        }
                    });
                    return;
                }
            }
            try {
                view.addOverlay(this.barcodeCaptureDeserializer.overlayFromJson(modeFromCache.getMode(), overlayJson));
            } catch (Exception e) {
                this.logger.error(e);
            }
        }
    }

    public final void updateFeedback(final int modeId, final String feedbackJson, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(feedbackJson, "feedbackJson");
        Intrinsics.checkNotNullParameter(result, "result");
        try {
            FrameworksBarcodeCaptureMode modeFromCache = getModeFromCache(modeId);
            if (modeFromCache == null) {
                addPostModeCreationAction(modeId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.capture.BarcodeCaptureModule$updateFeedback$mode$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        BarcodeCaptureModule.this.updateFeedback(modeId, feedbackJson, result);
                    }
                });
            } else {
                modeFromCache.updateFeedback(BarcodeCaptureFeedbackDeserializer.fromJson(new JsonValue(feedbackJson)));
                result.success(null);
            }
        } catch (Exception e) {
            ExtentionsKt.reject(result, e);
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle.Observer
    public void onActivityStop() {
        cancelAllListenerPendingEvents();
        super.onActivityStop();
    }

    public final void getFrameDataBytes(String frameId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(frameId, "frameId");
        Intrinsics.checkNotNullParameter(result, "result");
        this.frameDataHandler.retrieveDataAsMap(frameId, new Function1<Map<String, ? extends Object>, Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.capture.BarcodeCaptureModule$getFrameDataBytes$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Map<String, ? extends Object> map) {
                invoke2(map);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Map<String, ? extends Object> map) {
                if (map == null) {
                    ExtentionsKt.reject(FrameworksResult.this, new FrameDataNullError());
                } else {
                    FrameworksResult.this.success(map);
                }
            }
        });
    }

    public final boolean execute(FrameworksMethodCall method, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(result, "result");
        String method2 = method.getMethod();
        if (method2 == null) {
            return false;
        }
        switch (method2.hashCode()) {
            case -2016039410:
                if (!method2.equals("getBarcodeCaptureDefaults")) {
                    return false;
                }
                result.success(new JSONObject(getDefaults()).toString());
                return true;
            case -895022406:
                if (!method2.equals("applyBarcodeCaptureModeSettings")) {
                    return false;
                }
                applyModeSettings(((Number) method.argument(ExtentionsKt.MODE_ID_KEY)).intValue(), (String) method.argument("modeSettingsJson"), result);
                return true;
            case -637947106:
                if (!method2.equals("removeBarcodeCaptureListener")) {
                    return false;
                }
                removeListener(((Number) method.argument(ExtentionsKt.MODE_ID_KEY)).intValue());
                result.success(null);
                return true;
            case -257247351:
                if (!method2.equals("finishDidScan")) {
                    return false;
                }
                finishDidScan(((Number) method.argument(ExtentionsKt.MODE_ID_KEY)).intValue(), ((Boolean) method.argument(ExtentionsKt.ENABLED_KEY)).booleanValue());
                result.success(null);
                return true;
            case -110194821:
                if (!method2.equals("addBarcodeCaptureListener")) {
                    return false;
                }
                addListener(((Number) method.argument(ExtentionsKt.MODE_ID_KEY)).intValue());
                result.success(null);
                return true;
            case 102865409:
                if (!method2.equals("finishDidUpdateSession")) {
                    return false;
                }
                finishDidUpdateSession(((Number) method.argument(ExtentionsKt.MODE_ID_KEY)).intValue(), ((Boolean) method.argument(ExtentionsKt.ENABLED_KEY)).booleanValue());
                result.success(null);
                return true;
            case 259884562:
                if (!method2.equals("updateBarcodeCaptureMode")) {
                    return false;
                }
                updateModeFromJson((String) method.arguments(), result);
                return true;
            case 371780993:
                if (!method2.equals("updateBarcodeCaptureOverlay")) {
                    return false;
                }
                updateOverlay(((Number) method.argument("viewId")).intValue(), (String) method.argument("overlayJson"), result);
                return true;
            case 678670190:
                if (!method2.equals("updateFeedback")) {
                    return false;
                }
                updateFeedback(((Number) method.argument(ExtentionsKt.MODE_ID_KEY)).intValue(), (String) method.argument("feedbackJson"), result);
                return true;
            case 1641371541:
                if (!method2.equals("setModeEnabledState")) {
                    return false;
                }
                setModeEnabled(((Number) method.argument(ExtentionsKt.MODE_ID_KEY)).intValue(), ((Boolean) method.argument(ExtentionsKt.ENABLED_KEY)).booleanValue());
                result.success(null);
                return true;
            case 1684371201:
                if (!method2.equals("resetBarcodeCaptureSession")) {
                    return false;
                }
                resetSession((Long) method.argument("frameSequenceId"));
                result.success(null);
                return true;
            case 1927438603:
                if (!method2.equals("getLastFrameData")) {
                    return false;
                }
                getFrameDataBytes((String) method.arguments(), result);
                return true;
            default:
                return false;
        }
    }

    private final void resetLatestSession(Long frameSequenceId) {
        BarcodeCaptureSession barcodeCaptureSession;
        FrameworksBarcodeCaptureSession frameworksBarcodeCaptureSession = this.cachedCaptureSession.get();
        if (frameworksBarcodeCaptureSession == null) {
            return;
        }
        if ((frameSequenceId == null || frameworksBarcodeCaptureSession.getFrameSequenceId() == frameSequenceId.longValue()) && (barcodeCaptureSession = frameworksBarcodeCaptureSession.getCaptureSession().get()) != null) {
            barcodeCaptureSession.reset();
        }
    }

    private final void cancelAllListenerPendingEvents() {
        Iterator<T> it = getAllModesInCache().iterator();
        while (it.hasNext()) {
            ((FrameworksBarcodeCaptureMode) it.next()).cancelPendingEvents();
        }
    }

    /* compiled from: BarcodeCaptureModule.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/capture/BarcodeCaptureModule$Companion;", "", "()V", "create", "Lcom/scandit/datacapture/frameworks/barcode/capture/BarcodeCaptureModule;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final BarcodeCaptureModule create(Emitter emitter) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            return new BarcodeCaptureModule(emitter, null, null, null, null, null, null, WebSocketProtocol.PAYLOAD_SHORT, null);
        }
    }
}
