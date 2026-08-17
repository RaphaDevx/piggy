package com.scandit.datacapture.frameworks.barcode.count;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.PluginCall;
import com.scandit.datacapture.barcode.count.capture.list.TargetBarcode;
import com.scandit.datacapture.barcode.count.capture.map.BarcodeSpatialGrid;
import com.scandit.datacapture.barcode.count.serialization.BarcodeCountDeserializer;
import com.scandit.datacapture.barcode.count.serialization.BarcodeCountViewDeserializer;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.frameworks.barcode.count.data.BarcodeCountViewCreationData;
import com.scandit.datacapture.frameworks.barcode.count.data.defaults.BarcodeCountDefaults;
import com.scandit.datacapture.frameworks.barcode.count.ui.FrameworksBarcodeCountView;
import com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule;
import com.scandit.datacapture.frameworks.core.context.DefaultFrameworksCaptureContext;
import com.scandit.datacapture.frameworks.core.context.FrameworksCaptureContext;
import com.scandit.datacapture.frameworks.core.deserialization.DefaultDeserializationLifecycleObserver;
import com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver;
import com.scandit.datacapture.frameworks.core.errors.DataCaptureContextNotInitialized;
import com.scandit.datacapture.frameworks.core.errors.FrameDataNullError;
import com.scandit.datacapture.frameworks.core.errors.ModuleNotStartedError;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.frames.DefaultFrameDataHandler;
import com.scandit.datacapture.frameworks.core.frames.FrameDataHandler;
import com.scandit.datacapture.frameworks.core.result.FrameworksResult;
import com.scandit.datacapture.frameworks.core.ui.FrameworksViewsCache;
import com.scandit.datacapture.frameworks.core.utils.DefaultFrameworksLog;
import com.scandit.datacapture.frameworks.core.utils.DefaultMainThread;
import com.scandit.datacapture.frameworks.core.utils.FrameworksLog;
import io.sentry.SentryEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BarcodeCountModule.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0016\u0018\u0000 T2\u00020\u0001:\u0001TB5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J%\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010 \u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\u001b¢\u0006\u0002\u0010!J\u000e\u0010\"\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J \u0010#\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020\u0017J \u0010'\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020\u0017J \u0010(\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020\u0017J \u0010)\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020\u0017J\u0016\u0010*\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010+\u001a\u00020,J\u0014\u0010-\u001a\u0010\u0012\u0004\u0012\u00020/\u0012\u0006\u0012\u0004\u0018\u0001000.J\u0016\u00101\u001a\u00020\u00152\u0006\u00102\u001a\u00020/2\u0006\u0010\u001a\u001a\u00020\u001bJ\u0010\u00103\u001a\u0004\u0018\u0001042\u0006\u00105\u001a\u00020/J\u000e\u00106\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u00107\u001a\u00020,J\b\u00108\u001a\u00020\u0015H\u0016J\u000e\u00109\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010:\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010;\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010<\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010=\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u001d\u0010>\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010?\u001a\u0004\u0018\u00010@¢\u0006\u0002\u0010AJ\u0016\u0010B\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010C\u001a\u00020DJ\u0016\u0010E\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010+\u001a\u00020,J\u000e\u0010F\u001a\u00020\u00152\u0006\u0010+\u001a\u00020,J\u000e\u0010G\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010H\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010I\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010J\u001a\u00020/2\u0006\u0010\u001a\u001a\u00020\u001bJ\u0016\u0010K\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001bJ&\u0010K\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010L\u001a\u00020\u00172\u0006\u0010M\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001bJ\u0016\u0010N\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010O\u001a\u00020/J\u0016\u0010P\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u00105\u001a\u00020/J\u001e\u0010Q\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010R\u001a\u00020/2\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010S\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006U"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/BarcodeCountModule;", "Lcom/scandit/datacapture/frameworks/core/common/BaseFrameworkModule;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "captureContext", "Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;", SentryEvent.JsonKeys.LOGGER, "Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;", "frameDataHandler", "Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;", "deserializationLifecycleObserver", "Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;)V", "viewCache", "Lcom/scandit/datacapture/frameworks/core/ui/FrameworksViewsCache;", "Lcom/scandit/datacapture/frameworks/barcode/count/ui/FrameworksBarcodeCountView;", "getViewCache$annotations", "()V", "getViewCache", "()Lcom/scandit/datacapture/frameworks/core/ui/FrameworksViewsCache;", "addAsyncBarcodeCountListener", "", "viewId", "", "addBarcodeCountListener", "addBarcodeCountStatusProvider", "result", "Lcom/scandit/datacapture/frameworks/core/result/FrameworksResult;", "addBarcodeCountViewListener", "addBarcodeCountViewUiListener", "clearHighlights", "enableHardwareTrigger", BarcodeCountViewDeserializer.KEY_HW_TRIGGER_KEY_CODE, "(ILjava/lang/Integer;Lcom/scandit/datacapture/frameworks/core/result/FrameworksResult;)V", "endScanningPhase", "finishBrushForAcceptedBarcodeEvent", "brush", "Lcom/scandit/datacapture/core/ui/style/Brush;", "trackedBarcodeId", "finishBrushForRecognizedBarcodeEvent", "finishBrushForRecognizedBarcodeNotInListEvent", "finishBrushForRejectedBarcodeEvent", "finishOnScan", ExtentionsKt.ENABLED_KEY, "", "getDefaults", "", "", "", "getFrameDataBytes", "frameId", "getViewFromJson", "Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountView;", "viewJson", "hideView", "isModeEnabled", "onDestroy", "removeAsyncBarcodeCountListener", "removeBarcodeCountListener", "removeBarcodeCountViewListener", "removeBarcodeCountViewUiListener", "resetBarcodeCount", "resetBarcodeCountSession", "frameSequenceId", "", "(ILjava/lang/Long;)V", "setBarcodeCountCaptureList", "barcodes", "Lorg/json/JSONArray;", "setModeEnabled", "setTopMostModeEnabled", "showView", "startScanningPhase", "submitBarcodeCountStatusProviderCallbackResult", "statusJson", "submitSpatialMap", "expectedNumberOfRows", "expectedNumberOfColumns", "updateBarcodeCount", "modeJson", "updateBarcodeCountView", "updateFeedback", "feedbackJson", "viewDisposed", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class BarcodeCountModule extends BaseFrameworkModule {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FrameworksCaptureContext captureContext;
    private final Emitter emitter;
    private final FrameDataHandler frameDataHandler;
    private final FrameworksLog logger;
    private final FrameworksViewsCache<FrameworksBarcodeCountView> viewCache;

    @JvmStatic
    public static final BarcodeCountModule create(Emitter emitter) {
        return INSTANCE.create(emitter);
    }

    public static /* synthetic */ void getViewCache$annotations() {
    }

    public /* synthetic */ BarcodeCountModule(Emitter emitter, DefaultFrameworksCaptureContext defaultFrameworksCaptureContext, DefaultFrameworksLog defaultFrameworksLog, DefaultFrameDataHandler defaultFrameDataHandler, DefaultDeserializationLifecycleObserver defaultDeserializationLifecycleObserver, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(emitter, (i & 2) != 0 ? DefaultFrameworksCaptureContext.INSTANCE.getInstance() : defaultFrameworksCaptureContext, (i & 4) != 0 ? DefaultFrameworksLog.INSTANCE.getInstance() : defaultFrameworksLog, (i & 8) != 0 ? DefaultFrameDataHandler.INSTANCE.getInstance() : defaultFrameDataHandler, (i & 16) != 0 ? DefaultDeserializationLifecycleObserver.INSTANCE.getInstance() : defaultDeserializationLifecycleObserver);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BarcodeCountModule(Emitter emitter, FrameworksCaptureContext captureContext, FrameworksLog logger, FrameDataHandler frameDataHandler, DeserializationLifecycleObserver deserializationLifecycleObserver) {
        super(deserializationLifecycleObserver, null, 2, null);
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        Intrinsics.checkNotNullParameter(captureContext, "captureContext");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(frameDataHandler, "frameDataHandler");
        Intrinsics.checkNotNullParameter(deserializationLifecycleObserver, "deserializationLifecycleObserver");
        this.emitter = emitter;
        this.captureContext = captureContext;
        this.logger = logger;
        this.frameDataHandler = frameDataHandler;
        this.viewCache = new FrameworksViewsCache<>();
    }

    public final FrameworksViewsCache<FrameworksBarcodeCountView> getViewCache() {
        return this.viewCache;
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.FrameworkModule
    public void onDestroy() {
        this.viewCache.disposeAll();
        super.onDestroy();
    }

    public final Map<String, Object> getDefaults() {
        Context context = getContext();
        if (context == null) {
            Intrinsics.checkNotNullExpressionValue("BarcodeCountModule", "getSimpleName(...)");
            throw new ModuleNotStartedError("BarcodeCountModule");
        }
        return BarcodeCountDefaults.INSTANCE.get(context);
    }

    public final BarcodeCountView getViewFromJson(String viewJson) {
        FrameworksBarcodeCountView create;
        Intrinsics.checkNotNullParameter(viewJson, "viewJson");
        Context context = getContext();
        if (context == null) {
            this.logger.error("At this stage the context is not expected to be null.");
            return null;
        }
        DataCaptureContext context2 = this.captureContext.getContext();
        if (context2 == null) {
            this.logger.error(new DataCaptureContextNotInitialized());
            return null;
        }
        try {
            BarcodeCountViewCreationData fromJson = BarcodeCountViewCreationData.INSTANCE.fromJson(viewJson);
            FrameworksBarcodeCountView view = this.viewCache.getView(fromJson.getViewId());
            if (view != null) {
                view.dispose();
                this.viewCache.remove(view.get_viewId());
            }
            FrameworksBarcodeCountView topMost = this.viewCache.getTopMost();
            if (topMost != null) {
                topMost.hide();
            }
            create = FrameworksBarcodeCountView.INSTANCE.create(this.emitter, context, context2, fromJson, (r17 & 16) != 0 ? new BarcodeCountDeserializer() : null, (r17 & 32) != 0 ? new BarcodeCountViewDeserializer() : null, (r17 & 64) != 0 ? DefaultMainThread.INSTANCE.getInstance() : null);
            this.viewCache.addView(create);
            Iterator<Function0<Unit>> it = getPostSpecificViewCreationActions(create.get_viewId()).iterator();
            while (it.hasNext()) {
                it.next().invoke();
            }
            return create.getView();
        } catch (Exception e) {
            FrameworksLog frameworksLog = this.logger;
            StringBuilder sb = new StringBuilder("Error during the barcode count view deserialization. Error: ");
            String message = e.getMessage();
            if (message == null) {
                message = "unknown";
            }
            sb.append(message);
            frameworksLog.error(sb.toString());
            return null;
        }
    }

    public final void updateBarcodeCountView(int viewId, String viewJson) {
        Intrinsics.checkNotNullParameter(viewJson, "viewJson");
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.updateView(viewJson);
    }

    public final void addBarcodeCountStatusProvider(final int viewId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.BarcodeCountModule$addBarcodeCountStatusProvider$viewInstance$1
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
                    BarcodeCountModule.this.addBarcodeCountStatusProvider(viewId, result);
                }
            });
        } else {
            view.addBarcodeCountStatusProvider();
            result.success(null);
        }
    }

    public final void updateBarcodeCount(final int viewId, final String modeJson) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.BarcodeCountModule$updateBarcodeCount$viewInstance$1
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
                    BarcodeCountModule.this.updateBarcodeCount(viewId, modeJson);
                }
            });
        } else {
            view.updateMode(modeJson);
        }
    }

    public final void addBarcodeCountViewListener(final int viewId) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.BarcodeCountModule$addBarcodeCountViewListener$viewInstance$1
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
                    BarcodeCountModule.this.addBarcodeCountViewListener(viewId);
                }
            });
        } else {
            view.addBarcodeCountViewListener();
        }
    }

    public final void removeBarcodeCountViewListener(int viewId) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.removeBarcodeCountViewListener();
    }

    public final void addBarcodeCountViewUiListener(final int viewId) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.BarcodeCountModule$addBarcodeCountViewUiListener$viewInstance$1
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
                    BarcodeCountModule.this.addBarcodeCountViewUiListener(viewId);
                }
            });
        } else {
            view.addBarcodeCountViewUiListener();
        }
    }

    public final void removeBarcodeCountViewUiListener(int viewId) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.removeBarcodeCountViewUiListener();
    }

    public final void clearHighlights(final int viewId) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.BarcodeCountModule$clearHighlights$viewInstance$1
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
                    BarcodeCountModule.this.clearHighlights(viewId);
                }
            });
        } else {
            view.barcodeViewClearHighlights();
        }
    }

    public final void finishBrushForRecognizedBarcodeEvent(final int viewId, final Brush brush, final int trackedBarcodeId) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.BarcodeCountModule$finishBrushForRecognizedBarcodeEvent$viewInstance$1
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
                    BarcodeCountModule.this.finishBrushForRecognizedBarcodeEvent(viewId, brush, trackedBarcodeId);
                }
            });
        } else {
            view.finishBrushForRecognizedBarcodeEvent(brush, trackedBarcodeId);
        }
    }

    public final void finishBrushForRecognizedBarcodeNotInListEvent(final int viewId, final Brush brush, final int trackedBarcodeId) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.BarcodeCountModule$finishBrushForRecognizedBarcodeNotInListEvent$viewInstance$1
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
                    BarcodeCountModule.this.finishBrushForRecognizedBarcodeEvent(viewId, brush, trackedBarcodeId);
                }
            });
        } else {
            view.finishBrushForRecognizedBarcodeNotInListEvent(brush, trackedBarcodeId);
        }
    }

    public final void finishBrushForAcceptedBarcodeEvent(final int viewId, final Brush brush, final int trackedBarcodeId) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.BarcodeCountModule$finishBrushForAcceptedBarcodeEvent$viewInstance$1
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
                    BarcodeCountModule.this.finishBrushForAcceptedBarcodeEvent(viewId, brush, trackedBarcodeId);
                }
            });
        } else {
            view.finishBrushForAcceptedBarcodeEvent(brush, trackedBarcodeId);
        }
    }

    public final void finishBrushForRejectedBarcodeEvent(final int viewId, final Brush brush, final int trackedBarcodeId) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.BarcodeCountModule$finishBrushForRejectedBarcodeEvent$viewInstance$1
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
                    BarcodeCountModule.this.finishBrushForRejectedBarcodeEvent(viewId, brush, trackedBarcodeId);
                }
            });
        } else {
            view.finishBrushForRejectedBarcodeEvent(brush, trackedBarcodeId);
        }
    }

    public final void setBarcodeCountCaptureList(final int viewId, final JSONArray barcodes) {
        Intrinsics.checkNotNullParameter(barcodes, "barcodes");
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.BarcodeCountModule$setBarcodeCountCaptureList$1
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
                BarcodeCountModule.this.setBarcodeCountCaptureList(viewId, barcodes);
            }
        });
        ArrayList arrayList = new ArrayList();
        int length = barcodes.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = new JSONObject(barcodes.get(i).toString());
            arrayList.add(TargetBarcode.INSTANCE.create(jSONObject.get("data").toString(), Integer.parseInt(jSONObject.get("quantity").toString())));
        }
        view.setBarcodeCountCaptureList(arrayList);
    }

    public final void resetBarcodeCountSession(int viewId, Long frameSequenceId) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.resetBarcodeCountSession(frameSequenceId);
    }

    public final void finishOnScan(int viewId, boolean enabled) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.finishOnScan(enabled);
    }

    public final void addBarcodeCountListener(final int viewId) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.BarcodeCountModule$addBarcodeCountListener$viewInstance$1
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
                    BarcodeCountModule.this.addBarcodeCountListener(viewId);
                }
            });
        } else {
            view.addBarcodeCountListener();
        }
    }

    public final void removeBarcodeCountListener(int viewId) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.removeBarcodeCountListener();
    }

    public final void addAsyncBarcodeCountListener(final int viewId) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.BarcodeCountModule$addAsyncBarcodeCountListener$viewInstance$1
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
                    BarcodeCountModule.this.addAsyncBarcodeCountListener(viewId);
                }
            });
        } else {
            view.addAsyncBarcodeCountListener();
        }
    }

    public final void removeAsyncBarcodeCountListener(int viewId) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.removeAsyncBarcodeCountListener();
    }

    public final void resetBarcodeCount(int viewId) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.resetBarcodeCount();
    }

    public final void startScanningPhase(final int viewId) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.BarcodeCountModule$startScanningPhase$viewInstance$1
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
                    BarcodeCountModule.this.startScanningPhase(viewId);
                }
            });
        } else {
            view.startScanningPhase();
        }
    }

    public final void endScanningPhase(final int viewId) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.BarcodeCountModule$endScanningPhase$viewInstance$1
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
                    BarcodeCountModule.this.endScanningPhase(viewId);
                }
            });
        } else {
            view.endScanningPhase();
        }
    }

    public final void submitSpatialMap(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            result.success(view.getSpatialMap());
        }
    }

    public final void submitSpatialMap(int viewId, int expectedNumberOfRows, int expectedNumberOfColumns, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            BarcodeSpatialGrid spatialMap = view.getSpatialMap(expectedNumberOfRows, expectedNumberOfColumns);
            result.success(spatialMap != null ? spatialMap.toJson() : null);
        }
    }

    public final void setModeEnabled(final int viewId, final boolean enabled) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.BarcodeCountModule$setModeEnabled$viewInstance$1
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
                    BarcodeCountModule.this.setModeEnabled(viewId, enabled);
                }
            });
        } else {
            view.setModeEnabled(enabled);
        }
    }

    public final boolean isModeEnabled() {
        FrameworksBarcodeCountView topMost = this.viewCache.getTopMost();
        return topMost != null && topMost.isModeEnabled();
    }

    public final void setTopMostModeEnabled(boolean enabled) {
        FrameworksBarcodeCountView topMost = this.viewCache.getTopMost();
        if (topMost != null) {
            topMost.setModeEnabled(enabled);
        }
    }

    public final void updateFeedback(final int viewId, final String feedbackJson, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(feedbackJson, "feedbackJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.BarcodeCountModule$updateFeedback$viewInstance$1
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
                    BarcodeCountModule.this.updateFeedback(viewId, feedbackJson, result);
                }
            });
            return;
        }
        try {
            view.updateFeedback(feedbackJson);
            result.success(null);
        } catch (JSONException e) {
            result.error(PluginCall.CALLBACK_ID_DANGLING, e.getMessage(), e.getCause());
        }
    }

    public final void submitBarcodeCountStatusProviderCallbackResult(int viewId, String statusJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(statusJson, "statusJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.submitBarcodeCountStatusProviderCallbackResult(statusJson);
            result.success(null);
        }
    }

    public final void getFrameDataBytes(String frameId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(frameId, "frameId");
        Intrinsics.checkNotNullParameter(result, "result");
        this.frameDataHandler.retrieveDataAsMap(frameId, new Function1<Map<String, ? extends Object>, Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.BarcodeCountModule$getFrameDataBytes$1
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

    public final void enableHardwareTrigger(final int viewId, final Integer hardwareTriggerKeyCode, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.BarcodeCountModule$enableHardwareTrigger$viewInstance$1
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
                    BarcodeCountModule.this.enableHardwareTrigger(viewId, hardwareTriggerKeyCode, result);
                }
            });
        } else {
            view.enableHardwareTrigger(hardwareTriggerKeyCode);
            result.success(null);
        }
    }

    public final void viewDisposed(int viewId) {
        FrameworksBarcodeCountView remove = this.viewCache.remove(viewId);
        if (remove != null) {
            remove.dispose();
        }
        clearPostSpecificViewCreationActions(viewId);
        FrameworksBarcodeCountView topMost = this.viewCache.getTopMost();
        if (topMost != null) {
            topMost.show();
        }
    }

    public final void showView(int viewId) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view != null) {
            view.show();
        }
    }

    public final void hideView(int viewId) {
        FrameworksBarcodeCountView view = this.viewCache.getView(viewId);
        if (view != null) {
            view.hide();
        }
    }

    /* compiled from: BarcodeCountModule.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/BarcodeCountModule$Companion;", "", "()V", "create", "Lcom/scandit/datacapture/frameworks/barcode/count/BarcodeCountModule;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final BarcodeCountModule create(Emitter emitter) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            return new BarcodeCountModule(emitter, null, null, null, null, 30, null);
        }
    }
}
