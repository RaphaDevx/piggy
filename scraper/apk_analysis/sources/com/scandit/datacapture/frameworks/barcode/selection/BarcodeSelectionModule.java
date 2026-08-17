package com.scandit.datacapture.frameworks.barcode.selection;

import android.content.Context;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.PluginCall;
import com.scandit.datacapture.barcode.internal.sdk.feedback.BarcodeSelectionFeedbackDeserializer;
import com.scandit.datacapture.barcode.selection.capture.BarcodeSelection;
import com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializer;
import com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerListener;
import com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSettings;
import com.scandit.datacapture.barcode.selection.feedback.BarcodeSelectionFeedback;
import com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlay;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import com.scandit.datacapture.frameworks.barcode.selection.data.defaults.BarcodeSelectionDefaults;
import com.scandit.datacapture.frameworks.barcode.selection.listeners.FrameworksBarcodeSelectionAimedBrushProvider;
import com.scandit.datacapture.frameworks.barcode.selection.listeners.FrameworksBarcodeSelectionListener;
import com.scandit.datacapture.frameworks.barcode.selection.listeners.FrameworksBarcodeSelectionTrackedBrushProvider;
import com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule;
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
import com.scandit.datacapture.frameworks.core.result.FrameworksResult;
import com.scandit.datacapture.frameworks.core.ui.FrameworksDataCaptureView;
import com.scandit.datacapture.frameworks.core.utils.DefaultFrameworksLog;
import com.scandit.datacapture.frameworks.core.utils.FrameworksLog;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import com.tealium.library.DataSources;
import io.sentry.SentryEvent;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarcodeSelectionModule.kt */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0012\b\u0016\u0018\u0000 a2\u00020\u00012\u00020\u0002:\u0001aBY\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\u0006\u0010!\u001a\u00020\"J\u0016\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'J\u001a\u0010(\u001a\u00020\"2\b\u0010)\u001a\u0004\u0018\u00010%2\b\u0010*\u001a\u0004\u0018\u00010%J\u001a\u0010+\u001a\u00020\"2\b\u0010)\u001a\u0004\u0018\u00010%2\b\u0010*\u001a\u0004\u0018\u00010%J\u000e\u0010,\u001a\u00020\"2\u0006\u0010-\u001a\u00020 J\u000e\u0010.\u001a\u00020\"2\u0006\u0010-\u001a\u00020 J\u0014\u0010/\u001a\u0010\u0012\u0004\u0012\u00020%\u0012\u0006\u0012\u0004\u0018\u00010100J\u0016\u00102\u001a\u00020\"2\u0006\u00103\u001a\u00020%2\u0006\u0010&\u001a\u00020'J\u0016\u00104\u001a\u00020\"2\u0006\u00105\u001a\u00020%2\u0006\u0010&\u001a\u00020'J\u0006\u0010\u001f\u001a\u00020 J\b\u00106\u001a\u00020\"H\u0016J\u0010\u00107\u001a\u00020\"2\u0006\u00108\u001a\u00020%H\u0016J\u0018\u00109\u001a\u00020\"2\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020%H\u0016J\b\u0010=\u001a\u00020\"H\u0016J \u0010>\u001a\u00020\"2\u0006\u0010?\u001a\u00020\n2\u0006\u0010@\u001a\u00020\u001c2\u0006\u0010A\u001a\u00020BH\u0016J\u0010\u0010C\u001a\u00020\"2\u0006\u0010D\u001a\u00020EH\u0016J\b\u0010F\u001a\u00020\"H\u0016J \u0010G\u001a\u00020\"2\u0006\u0010?\u001a\u00020\n2\u0006\u0010H\u001a\u00020\u00172\u0006\u0010A\u001a\u00020BH\u0016J\b\u0010I\u001a\u00020\"H\u0002J\u0010\u0010J\u001a\u00020\"2\u0006\u00108\u001a\u00020%H\u0016J\u0006\u0010K\u001a\u00020\"J\u0006\u0010L\u001a\u00020\"J\u0006\u0010M\u001a\u00020\"J\u0015\u0010N\u001a\u00020\"2\b\u0010O\u001a\u0004\u0018\u00010P¢\u0006\u0002\u0010QJ\u0006\u0010R\u001a\u00020\"J\u0006\u0010S\u001a\u00020\"J\u000e\u0010T\u001a\u00020\"2\u0006\u0010&\u001a\u00020'J\u000e\u0010U\u001a\u00020\"2\u0006\u0010-\u001a\u00020 J\u001e\u0010V\u001a\u00020\"2\u0006\u00105\u001a\u00020%2\u0006\u0010-\u001a\u00020 2\u0006\u0010&\u001a\u00020'J\u0016\u0010W\u001a\u00020\"2\u0006\u0010X\u001a\u00020%2\u0006\u0010&\u001a\u00020'J\u000e\u0010Y\u001a\u00020\"2\u0006\u0010&\u001a\u00020'J\u0016\u0010Z\u001a\u00020\"2\u0006\u0010*\u001a\u00020%2\u0006\u0010&\u001a\u00020'J\u0006\u0010[\u001a\u00020\"J\u0016\u0010\\\u001a\u00020\"2\u0006\u00105\u001a\u00020%2\u0006\u0010&\u001a\u00020'J\u0016\u0010]\u001a\u00020\"2\u0006\u0010<\u001a\u00020%2\u0006\u0010&\u001a\u00020'J\u0016\u0010^\u001a\u00020\"2\u0006\u0010_\u001a\u00020%2\u0006\u0010&\u001a\u00020'J\u0016\u0010`\u001a\u00020\"2\u0006\u00108\u001a\u00020%2\u0006\u0010&\u001a\u00020'R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u001c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006b"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/selection/BarcodeSelectionModule;", "Lcom/scandit/datacapture/frameworks/core/common/BaseFrameworkModule;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionDeserializerListener;", "barcodeSelectionListener", "Lcom/scandit/datacapture/frameworks/barcode/selection/listeners/FrameworksBarcodeSelectionListener;", "aimedBrushProvider", "Lcom/scandit/datacapture/frameworks/barcode/selection/listeners/FrameworksBarcodeSelectionAimedBrushProvider;", "trackedBrushProvider", "Lcom/scandit/datacapture/frameworks/barcode/selection/listeners/FrameworksBarcodeSelectionTrackedBrushProvider;", "barcodeSelectionDeserializer", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionDeserializer;", SentryEvent.JsonKeys.LOGGER, "Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;", "dataCaptureViewHandler", "Lcom/scandit/datacapture/frameworks/core/handlers/DataCaptureViewHandler;", "frameDataHandler", "Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;", "captureContext", "Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;", "deserializationLifecycleObserver", "Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;", "(Lcom/scandit/datacapture/frameworks/barcode/selection/listeners/FrameworksBarcodeSelectionListener;Lcom/scandit/datacapture/frameworks/barcode/selection/listeners/FrameworksBarcodeSelectionAimedBrushProvider;Lcom/scandit/datacapture/frameworks/barcode/selection/listeners/FrameworksBarcodeSelectionTrackedBrushProvider;Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionDeserializer;Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;Lcom/scandit/datacapture/frameworks/core/handlers/DataCaptureViewHandler;Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;)V", "value", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelection;", BarcodeSelectionModule.MODE_TYPE, "setBarcodeSelection", "(Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelection;)V", "barcodeSelectionBasicOverlay", "Lcom/scandit/datacapture/barcode/selection/ui/overlay/BarcodeSelectionBasicOverlay;", "getBarcodeSelectionBasicOverlay", "()Lcom/scandit/datacapture/barcode/selection/ui/overlay/BarcodeSelectionBasicOverlay;", "isModeEnabled", "", "addListener", "", "applyModeSettings", "modeSettingsJson", "", "result", "Lcom/scandit/datacapture/frameworks/core/result/FrameworksResult;", "finishBrushForAimedBarcode", "brushJson", "selectionIdentifier", "finishBrushForTrackedBarcode", "finishDidSelect", ExtentionsKt.ENABLED_KEY, "finishDidUpdateSession", "getDefaults", "", "", "getFrameDataBytes", "frameId", "increaseCountForBarcodes", "barcodesJson", "onActivityStop", "onAddModeToContext", "modeJson", "onAddOverlayToView", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/frameworks/core/ui/FrameworksDataCaptureView;", "overlayJson", "onAllModesRemovedFromContext", "onBasicOverlayDeserializationFinished", "deserializer", "overlay", "json", "Lcom/scandit/datacapture/core/json/JsonValue;", "onCreate", "context", "Landroid/content/Context;", "onDestroy", "onModeDeserializationFinished", "mode", "onModeRemovedFromContext", "onRemoveModeFromContext", "removeAimedBarcodeBrushProvider", "removeListener", "removeTrackedBarcodeBrushProvider", "resetLatestSession", "frameSequenceId", "", "(Ljava/lang/Long;)V", "resetSelection", "selectAimedBarcode", "setAimedBarcodeBrushProvider", "setModeEnabled", "setSelectBarcodeEnabled", "setTextForAimToSelectAutoHint", "text", "setTrackedBarcodeBrushProvider", "submitBarcodeCountForIdentifier", "unfreezeCamera", "unselectBarcodes", "updateBasicOverlay", "updateFeedback", "feedbackJson", "updateModeFromJson", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class BarcodeSelectionModule extends BaseFrameworkModule implements BarcodeSelectionDeserializerListener {
    private static final String BASIC_OVERLAY_TYPE = "barcodeSelectionBasic";
    private static final String MODE_TYPE = "barcodeSelection";
    private final FrameworksBarcodeSelectionAimedBrushProvider aimedBrushProvider;
    private BarcodeSelection barcodeSelection;
    private final BarcodeSelectionDeserializer barcodeSelectionDeserializer;
    private final FrameworksBarcodeSelectionListener barcodeSelectionListener;
    private final FrameworksCaptureContext captureContext;
    private final DataCaptureViewHandler dataCaptureViewHandler;
    private final FrameDataHandler frameDataHandler;
    private boolean isModeEnabled;
    private final FrameworksLog logger;
    private final FrameworksBarcodeSelectionTrackedBrushProvider trackedBrushProvider;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Error MODE_DOES_NOT_EXIST = new Error("The BarcodeSelection mode instance does not exist.");
    private static final Error ERROR_NULL_OVERLAY = new Error("Overlay is null.");

    @JvmStatic
    public static final BarcodeSelectionModule create(FrameworksBarcodeSelectionListener frameworksBarcodeSelectionListener, FrameworksBarcodeSelectionAimedBrushProvider frameworksBarcodeSelectionAimedBrushProvider, FrameworksBarcodeSelectionTrackedBrushProvider frameworksBarcodeSelectionTrackedBrushProvider) {
        return INSTANCE.create(frameworksBarcodeSelectionListener, frameworksBarcodeSelectionAimedBrushProvider, frameworksBarcodeSelectionTrackedBrushProvider);
    }

    @JvmStatic
    public static final BarcodeSelectionModule create(Emitter emitter) {
        return INSTANCE.create(emitter);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerListener
    @ProxyFunction
    public void onBasicOverlayDeserializationStarted(BarcodeSelectionDeserializer barcodeSelectionDeserializer, BarcodeSelectionBasicOverlay barcodeSelectionBasicOverlay, JsonValue jsonValue) {
        BarcodeSelectionDeserializerListener.DefaultImpls.onBasicOverlayDeserializationStarted(this, barcodeSelectionDeserializer, barcodeSelectionBasicOverlay, jsonValue);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerListener
    @ProxyFunction
    public void onModeDeserializationStarted(BarcodeSelectionDeserializer barcodeSelectionDeserializer, BarcodeSelection barcodeSelection, JsonValue jsonValue) {
        BarcodeSelectionDeserializerListener.DefaultImpls.onModeDeserializationStarted(this, barcodeSelectionDeserializer, barcodeSelection, jsonValue);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerListener
    @ProxyFunction
    public void onSettingsDeserializationFinished(BarcodeSelectionDeserializer barcodeSelectionDeserializer, BarcodeSelectionSettings barcodeSelectionSettings, JsonValue jsonValue) {
        BarcodeSelectionDeserializerListener.DefaultImpls.onSettingsDeserializationFinished(this, barcodeSelectionDeserializer, barcodeSelectionSettings, jsonValue);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerListener
    @ProxyFunction
    public void onSettingsDeserializationStarted(BarcodeSelectionDeserializer barcodeSelectionDeserializer, BarcodeSelectionSettings barcodeSelectionSettings, JsonValue jsonValue) {
        BarcodeSelectionDeserializerListener.DefaultImpls.onSettingsDeserializationStarted(this, barcodeSelectionDeserializer, barcodeSelectionSettings, jsonValue);
    }

    public /* synthetic */ BarcodeSelectionModule(FrameworksBarcodeSelectionListener frameworksBarcodeSelectionListener, FrameworksBarcodeSelectionAimedBrushProvider frameworksBarcodeSelectionAimedBrushProvider, FrameworksBarcodeSelectionTrackedBrushProvider frameworksBarcodeSelectionTrackedBrushProvider, BarcodeSelectionDeserializer barcodeSelectionDeserializer, FrameworksLog frameworksLog, DataCaptureViewHandler dataCaptureViewHandler, FrameDataHandler frameDataHandler, FrameworksCaptureContext frameworksCaptureContext, DeserializationLifecycleObserver deserializationLifecycleObserver, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(frameworksBarcodeSelectionListener, frameworksBarcodeSelectionAimedBrushProvider, frameworksBarcodeSelectionTrackedBrushProvider, (i & 8) != 0 ? new BarcodeSelectionDeserializer() : barcodeSelectionDeserializer, (i & 16) != 0 ? DefaultFrameworksLog.INSTANCE.getInstance() : frameworksLog, (i & 32) != 0 ? DefaultDataCaptureViewHandler.INSTANCE.getInstance() : dataCaptureViewHandler, (i & 64) != 0 ? DefaultFrameDataHandler.INSTANCE.getInstance() : frameDataHandler, (i & 128) != 0 ? DefaultFrameworksCaptureContext.INSTANCE.getInstance() : frameworksCaptureContext, (i & 256) != 0 ? DefaultDeserializationLifecycleObserver.INSTANCE.getInstance() : deserializationLifecycleObserver);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BarcodeSelectionModule(FrameworksBarcodeSelectionListener barcodeSelectionListener, FrameworksBarcodeSelectionAimedBrushProvider aimedBrushProvider, FrameworksBarcodeSelectionTrackedBrushProvider trackedBrushProvider, BarcodeSelectionDeserializer barcodeSelectionDeserializer, FrameworksLog logger, DataCaptureViewHandler dataCaptureViewHandler, FrameDataHandler frameDataHandler, FrameworksCaptureContext captureContext, DeserializationLifecycleObserver deserializationLifecycleObserver) {
        super(deserializationLifecycleObserver, null, 2, null);
        Intrinsics.checkNotNullParameter(barcodeSelectionListener, "barcodeSelectionListener");
        Intrinsics.checkNotNullParameter(aimedBrushProvider, "aimedBrushProvider");
        Intrinsics.checkNotNullParameter(trackedBrushProvider, "trackedBrushProvider");
        Intrinsics.checkNotNullParameter(barcodeSelectionDeserializer, "barcodeSelectionDeserializer");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(dataCaptureViewHandler, "dataCaptureViewHandler");
        Intrinsics.checkNotNullParameter(frameDataHandler, "frameDataHandler");
        Intrinsics.checkNotNullParameter(captureContext, "captureContext");
        Intrinsics.checkNotNullParameter(deserializationLifecycleObserver, "deserializationLifecycleObserver");
        this.barcodeSelectionListener = barcodeSelectionListener;
        this.aimedBrushProvider = aimedBrushProvider;
        this.trackedBrushProvider = trackedBrushProvider;
        this.barcodeSelectionDeserializer = barcodeSelectionDeserializer;
        this.logger = logger;
        this.dataCaptureViewHandler = dataCaptureViewHandler;
        this.frameDataHandler = frameDataHandler;
        this.captureContext = captureContext;
        this.isModeEnabled = true;
    }

    private final void setBarcodeSelection(BarcodeSelection barcodeSelection) {
        BarcodeSelection barcodeSelection2 = this.barcodeSelection;
        if (barcodeSelection2 != null) {
            barcodeSelection2.removeListener(this.barcodeSelectionListener);
        }
        if (barcodeSelection != null) {
            barcodeSelection.addListener(this.barcodeSelectionListener);
        } else {
            barcodeSelection = null;
        }
        this.barcodeSelection = barcodeSelection;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Object] */
    private final BarcodeSelectionBasicOverlay getBarcodeSelectionBasicOverlay() {
        ?? r1;
        FrameworksDataCaptureView topmostDataCaptureView = this.dataCaptureViewHandler.getTopmostDataCaptureView();
        if (topmostDataCaptureView != null) {
            Iterator it = topmostDataCaptureView.getOverlays().iterator();
            while (true) {
                if (!it.hasNext()) {
                    r1 = 0;
                    break;
                }
                r1 = it.next();
                if (((DataCaptureOverlay) r1) instanceof BarcodeSelectionBasicOverlay) {
                    break;
                }
            }
            r0 = r1 instanceof BarcodeSelectionBasicOverlay ? r1 : null;
        }
        return r0;
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.FrameworkModule
    public void onCreate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onCreate(context);
        this.barcodeSelectionDeserializer.setListener(this);
        attachForActivityLifecycleEvents();
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.FrameworkModule
    public void onDestroy() {
        this.barcodeSelectionDeserializer.setListener(null);
        this.aimedBrushProvider.clearCache();
        this.trackedBrushProvider.clearCache();
        detachForActivityLifecycleEvents();
        super.onDestroy();
    }

    public final Map<String, Object> getDefaults() {
        return BarcodeSelectionDefaults.INSTANCE.get();
    }

    public final void addListener() {
        this.barcodeSelectionListener.enable();
    }

    public final void removeListener() {
        this.barcodeSelectionListener.disable();
    }

    public final void unfreezeCamera() {
        BarcodeSelection barcodeSelection = this.barcodeSelection;
        if (barcodeSelection != null) {
            barcodeSelection.unfreezeCamera();
        }
    }

    public final void resetSelection() {
        BarcodeSelection barcodeSelection = this.barcodeSelection;
        if (barcodeSelection != null) {
            barcodeSelection.reset();
        }
    }

    public final void submitBarcodeCountForIdentifier(String selectionIdentifier, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(selectionIdentifier, "selectionIdentifier");
        Intrinsics.checkNotNullParameter(result, "result");
        result.success(Integer.valueOf(this.barcodeSelectionListener.getBarcodeCount(selectionIdentifier)));
    }

    public final void resetLatestSession(Long frameSequenceId) {
        this.barcodeSelectionListener.resetSession(frameSequenceId);
    }

    public final void finishDidSelect(boolean enabled) {
        this.barcodeSelectionListener.finishDidSelect(enabled);
    }

    public final void finishDidUpdateSession(boolean enabled) {
        this.barcodeSelectionListener.finishDidUpdateSession(enabled);
    }

    public final void increaseCountForBarcodes(String barcodesJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(barcodesJson, "barcodesJson");
        Intrinsics.checkNotNullParameter(result, "result");
        BarcodeSelection barcodeSelection = this.barcodeSelection;
        Unit unit = null;
        if (barcodeSelection != null) {
            try {
                barcodeSelection.increaseCountForBarcodesFromJsonString(barcodesJson);
                result.success(null);
            } catch (Exception e) {
                result.error(PluginCall.CALLBACK_ID_DANGLING, "Unable to increment count for barcodes from the provided json.", e);
            }
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            ExtentionsKt.reject(result, MODE_DOES_NOT_EXIST);
        }
    }

    public final void setAimedBarcodeBrushProvider(FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        BarcodeSelectionBasicOverlay barcodeSelectionBasicOverlay = getBarcodeSelectionBasicOverlay();
        if (barcodeSelectionBasicOverlay == null) {
            ExtentionsKt.reject(result, ERROR_NULL_OVERLAY);
        } else {
            barcodeSelectionBasicOverlay.setAimedBarcodeBrushProvider(this.aimedBrushProvider);
            result.success(null);
        }
    }

    public final void setTextForAimToSelectAutoHint(String text, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(result, "result");
        BarcodeSelectionBasicOverlay barcodeSelectionBasicOverlay = getBarcodeSelectionBasicOverlay();
        if (barcodeSelectionBasicOverlay == null) {
            ExtentionsKt.reject(result, ERROR_NULL_OVERLAY);
        } else {
            barcodeSelectionBasicOverlay.setTextForAimToSelectAutoHint(text);
            result.success(null);
        }
    }

    public final void removeAimedBarcodeBrushProvider() {
        this.aimedBrushProvider.clearCache();
        BarcodeSelectionBasicOverlay barcodeSelectionBasicOverlay = getBarcodeSelectionBasicOverlay();
        if (barcodeSelectionBasicOverlay == null) {
            return;
        }
        barcodeSelectionBasicOverlay.setAimedBarcodeBrushProvider(null);
    }

    public final void finishBrushForAimedBarcode(String brushJson, String selectionIdentifier) {
        this.aimedBrushProvider.onFinishCallback(brushJson, selectionIdentifier);
    }

    public final void finishBrushForTrackedBarcode(String brushJson, String selectionIdentifier) {
        this.trackedBrushProvider.onFinishCallback(brushJson, selectionIdentifier);
    }

    public final void setTrackedBarcodeBrushProvider(FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        BarcodeSelectionBasicOverlay barcodeSelectionBasicOverlay = getBarcodeSelectionBasicOverlay();
        if (barcodeSelectionBasicOverlay == null) {
            ExtentionsKt.reject(result, ERROR_NULL_OVERLAY);
        } else {
            barcodeSelectionBasicOverlay.setTrackedBarcodeBrushProvider(this.trackedBrushProvider);
            result.success(null);
        }
    }

    public final void removeTrackedBarcodeBrushProvider() {
        this.trackedBrushProvider.clearCache();
        BarcodeSelectionBasicOverlay barcodeSelectionBasicOverlay = getBarcodeSelectionBasicOverlay();
        if (barcodeSelectionBasicOverlay == null) {
            return;
        }
        barcodeSelectionBasicOverlay.setTrackedBarcodeBrushProvider(null);
    }

    public final void selectAimedBarcode() {
        BarcodeSelection barcodeSelection = this.barcodeSelection;
        if (barcodeSelection != null) {
            barcodeSelection.selectAimedBarcode();
        }
    }

    public final void unselectBarcodes(String barcodesJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(barcodesJson, "barcodesJson");
        Intrinsics.checkNotNullParameter(result, "result");
        BarcodeSelection barcodeSelection = this.barcodeSelection;
        Unit unit = null;
        if (barcodeSelection != null) {
            try {
                barcodeSelection.unselectBarcodesFromJsonString(barcodesJson);
                result.success(null);
            } catch (Exception e) {
                result.error(PluginCall.CALLBACK_ID_DANGLING, "Unable to unselect barcodes from the provided json.", e);
            }
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            ExtentionsKt.reject(result, MODE_DOES_NOT_EXIST);
        }
    }

    public final void setSelectBarcodeEnabled(String barcodesJson, boolean enabled, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(barcodesJson, "barcodesJson");
        Intrinsics.checkNotNullParameter(result, "result");
        BarcodeSelection barcodeSelection = this.barcodeSelection;
        Unit unit = null;
        if (barcodeSelection != null) {
            try {
                barcodeSelection.setSelectBarcodeEnabledFromJsonString(barcodesJson, enabled);
                result.success(null);
            } catch (Exception e) {
                result.error(PluginCall.CALLBACK_ID_DANGLING, "Unable to enable/disable the selection of the barcode from the provided json.", e);
            }
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            ExtentionsKt.reject(result, MODE_DOES_NOT_EXIST);
        }
    }

    public final boolean isModeEnabled() {
        BarcodeSelection barcodeSelection = this.barcodeSelection;
        return barcodeSelection != null && barcodeSelection.isEnabled();
    }

    public final void setModeEnabled(boolean enabled) {
        this.isModeEnabled = enabled;
        BarcodeSelection barcodeSelection = this.barcodeSelection;
        if (barcodeSelection == null) {
            return;
        }
        barcodeSelection.setEnabled(enabled);
    }

    public final void updateModeFromJson(String modeJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        Intrinsics.checkNotNullParameter(result, "result");
        BarcodeSelection barcodeSelection = this.barcodeSelection;
        if (barcodeSelection == null) {
            result.success(null);
        } else {
            this.barcodeSelectionDeserializer.updateModeFromJson(barcodeSelection, modeJson);
            result.success(null);
        }
    }

    public final void applyModeSettings(String modeSettingsJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(modeSettingsJson, "modeSettingsJson");
        Intrinsics.checkNotNullParameter(result, "result");
        BarcodeSelection barcodeSelection = this.barcodeSelection;
        if (barcodeSelection == null) {
            result.success(null);
        } else {
            BarcodeSelection.applySettings$default(barcodeSelection, this.barcodeSelectionDeserializer.settingsFromJson(modeSettingsJson), null, 2, null);
            result.success(null);
        }
    }

    public final void updateBasicOverlay(String overlayJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(overlayJson, "overlayJson");
        Intrinsics.checkNotNullParameter(result, "result");
        BarcodeSelectionBasicOverlay barcodeSelectionBasicOverlay = getBarcodeSelectionBasicOverlay();
        if (barcodeSelectionBasicOverlay == null) {
            result.success(null);
        } else {
            this.barcodeSelectionDeserializer.updateBasicOverlayFromJson(barcodeSelectionBasicOverlay, overlayJson);
            result.success(true);
        }
    }

    public final void updateFeedback(String feedbackJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(feedbackJson, "feedbackJson");
        Intrinsics.checkNotNullParameter(result, "result");
        try {
            BarcodeSelectionFeedback fromJson = BarcodeSelectionFeedbackDeserializer.fromJson(new JsonValue(feedbackJson));
            BarcodeSelection barcodeSelection = this.barcodeSelection;
            if (barcodeSelection != null) {
                barcodeSelection.setFeedback(fromJson);
            }
            result.success(null);
        } catch (Exception e) {
            ExtentionsKt.reject(result, e);
        }
    }

    public final void getFrameDataBytes(String frameId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(frameId, "frameId");
        Intrinsics.checkNotNullParameter(result, "result");
        this.frameDataHandler.retrieveDataAsMap(frameId, new Function1<Map<String, ? extends Object>, Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.selection.BarcodeSelectionModule$getFrameDataBytes$1
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

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle.Observer
    public void onActivityStop() {
        this.barcodeSelectionListener.reset();
        super.onActivityStop();
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerListener
    public void onModeDeserializationFinished(BarcodeSelectionDeserializer deserializer, BarcodeSelection mode, JsonValue json) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        mode.setEnabled(this.isModeEnabled);
        setBarcodeSelection(mode);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionDeserializerListener
    public void onBasicOverlayDeserializationFinished(BarcodeSelectionDeserializer deserializer, BarcodeSelectionBasicOverlay overlay, JsonValue json) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(json, "json");
        if (json.contains("textForSelectOrDoubleTapToFreezeHint")) {
            overlay.setTextForSelectOrDoubleTapToFreezeHint(json.getByKeyAsString("textForSelectOrDoubleTapToFreezeHint", ""));
        }
        if (json.contains("textForTapToSelectHint")) {
            overlay.setTextForTapToSelectHint(json.getByKeyAsString("textForTapToSelectHint", ""));
        }
        if (json.contains("textForDoubleTapToUnfreezeHint")) {
            overlay.setTextForDoubleTapToUnfreezeHint(json.getByKeyAsString("textForDoubleTapToUnfreezeHint", ""));
        }
        if (json.contains("textForTapAnywhereToSelectHint")) {
            overlay.setTextForTapAnywhereToSelectHint(json.getByKeyAsString("textForTapAnywhereToSelectHint", ""));
        }
        if (json.contains("textForAimToSelectAutoHint")) {
            overlay.setTextForAimToSelectAutoHint(json.getByKeyAsString("textForAimToSelectAutoHint", ""));
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver.Observer
    public void onAddModeToContext(String modeJson) {
        DataCaptureContext contextForModeCreation;
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        if (Intrinsics.areEqual(ExtentionsKt.getJsonValueTypeAttribute(modeJson), MODE_TYPE) && (contextForModeCreation = ExtentionsKt.getContextForModeCreation(this.captureContext, MODE_TYPE)) != null) {
            this.captureContext.addMode(this.barcodeSelectionDeserializer.modeFromJson(contextForModeCreation, modeJson));
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver.Observer
    public void onRemoveModeFromContext(String modeJson) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        if (Intrinsics.areEqual(ExtentionsKt.getJsonValueTypeAttribute(modeJson), MODE_TYPE)) {
            BarcodeSelection barcodeSelection = this.barcodeSelection;
            if (barcodeSelection == null) {
                this.logger.error("Unable to add the BarcodeSelectionMode from the DataCaptureContext, the mode is null.");
            } else {
                this.captureContext.removeMode(barcodeSelection);
                onModeRemovedFromContext();
            }
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver.Observer
    public void onAllModesRemovedFromContext() {
        onModeRemovedFromContext();
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver.Observer
    public void onAddOverlayToView(FrameworksDataCaptureView view, String overlayJson) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(overlayJson, "overlayJson");
        if (Intrinsics.areEqual(ExtentionsKt.getJsonValueTypeAttribute(overlayJson), BASIC_OVERLAY_TYPE)) {
            BarcodeSelection barcodeSelection = this.barcodeSelection;
            if (barcodeSelection == null) {
                this.logger.error("Unable to add the BarcodeSelectionBasicOverlay to the DataCaptureView, the mode is null.");
            } else {
                view.addOverlay(this.barcodeSelectionDeserializer.basicOverlayFromJson(barcodeSelection, overlayJson));
            }
        }
    }

    private final void onModeRemovedFromContext() {
        BarcodeSelection barcodeSelection = this.barcodeSelection;
        if (barcodeSelection != null) {
            barcodeSelection.removeListener(this.barcodeSelectionListener);
        }
        setBarcodeSelection(null);
    }

    /* compiled from: BarcodeSelectionModule.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00060\u0006j\u0002`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/selection/BarcodeSelectionModule$Companion;", "", "()V", "BASIC_OVERLAY_TYPE", "", "ERROR_NULL_OVERLAY", "Ljava/lang/Error;", "Lkotlin/Error;", "MODE_DOES_NOT_EXIST", "MODE_TYPE", "create", "Lcom/scandit/datacapture/frameworks/barcode/selection/BarcodeSelectionModule;", "barcodeSelectionListener", "Lcom/scandit/datacapture/frameworks/barcode/selection/listeners/FrameworksBarcodeSelectionListener;", "aimedBrushProvider", "Lcom/scandit/datacapture/frameworks/barcode/selection/listeners/FrameworksBarcodeSelectionAimedBrushProvider;", "trackedBrushProvider", "Lcom/scandit/datacapture/frameworks/barcode/selection/listeners/FrameworksBarcodeSelectionTrackedBrushProvider;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final BarcodeSelectionModule create(FrameworksBarcodeSelectionListener barcodeSelectionListener, FrameworksBarcodeSelectionAimedBrushProvider aimedBrushProvider, FrameworksBarcodeSelectionTrackedBrushProvider trackedBrushProvider) {
            Intrinsics.checkNotNullParameter(barcodeSelectionListener, "barcodeSelectionListener");
            Intrinsics.checkNotNullParameter(aimedBrushProvider, "aimedBrushProvider");
            Intrinsics.checkNotNullParameter(trackedBrushProvider, "trackedBrushProvider");
            return new BarcodeSelectionModule(barcodeSelectionListener, aimedBrushProvider, trackedBrushProvider, null, null, null, null, null, null, TypedValues.PositionType.TYPE_PERCENT_HEIGHT, null);
        }

        @JvmStatic
        public final BarcodeSelectionModule create(Emitter emitter) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            return new BarcodeSelectionModule(new FrameworksBarcodeSelectionListener(emitter, null, null, 6, null), new FrameworksBarcodeSelectionAimedBrushProvider(emitter), new FrameworksBarcodeSelectionTrackedBrushProvider(emitter), null, null, null, null, null, null, TypedValues.PositionType.TYPE_PERCENT_HEIGHT, null);
        }
    }
}
