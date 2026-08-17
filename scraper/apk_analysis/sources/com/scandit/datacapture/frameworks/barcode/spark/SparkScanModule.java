package com.scandit.datacapture.frameworks.barcode.spark;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.frameworks.barcode.spark.data.SparkScanViewCreationData;
import com.scandit.datacapture.frameworks.barcode.spark.data.defaults.SparkScanDefaults;
import com.scandit.datacapture.frameworks.barcode.spark.ui.FrameworksSparkScanView;
import com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule;
import com.scandit.datacapture.frameworks.core.context.DefaultFrameworksCaptureContext;
import com.scandit.datacapture.frameworks.core.context.FrameworksCaptureContext;
import com.scandit.datacapture.frameworks.core.deserialization.DefaultDeserializationLifecycleObserver;
import com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver;
import com.scandit.datacapture.frameworks.core.errors.DataCaptureContextNotInitialized;
import com.scandit.datacapture.frameworks.core.errors.FrameDataNullError;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.frames.DefaultFrameDataHandler;
import com.scandit.datacapture.frameworks.core.frames.FrameDataHandler;
import com.scandit.datacapture.frameworks.core.method.FrameworksMethodCall;
import com.scandit.datacapture.frameworks.core.result.FrameworksResult;
import com.scandit.datacapture.frameworks.core.ui.FrameworksViewsCache;
import com.scandit.datacapture.frameworks.core.utils.DefaultFrameworksLog;
import com.scandit.datacapture.frameworks.core.utils.FrameworksLog;
import io.sentry.SentryEvent;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: SparkScanModule.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\f\b\u0016\u0018\u0000 N2\u00020\u0001:\u0001NB5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010!\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u0017J\u000e\u0010#\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010(\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010)\u001a\u00020%J\u0016\u0010*\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010)\u001a\u00020%J\u0014\u0010+\u001a\u0010\u0012\u0004\u0012\u00020 \u0012\u0006\u0012\u0004\u0018\u00010-0,J\u0016\u0010.\u001a\u00020\u00152\u0006\u0010/\u001a\u00020 2\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u00100\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u00101\u001a\u00020%J\b\u00102\u001a\u00020\u0015H\u0016J\b\u00103\u001a\u00020\u0015H\u0016J\b\u00104\u001a\u00020\u0015H\u0016J\b\u00105\u001a\u00020\u0015H\u0016J\u0010\u00106\u001a\u00020\u00152\u0006\u00107\u001a\u000208H\u0016J\b\u00109\u001a\u00020\u0015H\u0016J\u0016\u0010:\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010;\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010<\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010=\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010>\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010?\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010)\u001a\u00020%J\u000e\u0010@\u001a\u00020\u00152\u0006\u0010)\u001a\u00020%J\u0016\u0010A\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010B\u001a\u00020CJ\u001e\u0010D\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010E\u001a\u00020 2\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010F\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010G\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J \u0010H\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010I\u001a\u0004\u0018\u00010 2\u0006\u0010\u0018\u001a\u00020\u0019J\u001e\u0010J\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010K\u001a\u00020 2\u0006\u0010\u0018\u001a\u00020\u0019J\u001e\u0010L\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010M\u001a\u00020 2\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006O"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/spark/SparkScanModule;", "Lcom/scandit/datacapture/frameworks/core/common/BaseFrameworkModule;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", SentryEvent.JsonKeys.LOGGER, "Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;", "frameDataHandler", "Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;", "captureContext", "Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;", "deserializationLifecycleObserver", "Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;Lcom/scandit/datacapture/frameworks/core/utils/FrameworksLog;Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;)V", "viewCache", "Lcom/scandit/datacapture/frameworks/core/ui/FrameworksViewsCache;", "Lcom/scandit/datacapture/frameworks/barcode/spark/ui/FrameworksSparkScanView;", "getViewCache$annotations", "()V", "getViewCache", "()Lcom/scandit/datacapture/frameworks/core/ui/FrameworksViewsCache;", "addFeedbackDelegate", "", "viewId", "", "result", "Lcom/scandit/datacapture/frameworks/core/result/FrameworksResult;", "addSparkScanListener", "addSparkScanViewUiListener", "addViewToContainer", "containerView", "Landroid/view/View;", "jsonString", "", "dispatchWindowVisibilityChanged", "visibility", "disposeView", "execute", "", "method", "Lcom/scandit/datacapture/frameworks/core/method/FrameworksMethodCall;", "finishDidScanCallback", ExtentionsKt.ENABLED_KEY, "finishDidUpdateSessionCallback", "getDefaults", "", "", "getFrameDataBytes", "frameId", "hideView", "isModeEnabled", "onActivityPause", "onActivityResume", "onActivityStop", "onAllModesRemovedFromContext", "onCreate", "context", "Landroid/content/Context;", "onDestroy", "pauseScanning", "removeFeedbackDelegate", "removeSparkScanListener", "removeSparkScanViewUiListener", "resetSession", "setModeEnabled", "setTopMostViewModeEnabled", "setViewLayoutParams", "layoutParams", "Landroid/view/ViewGroup$LayoutParams;", "showToast", "text", "showView", "startScanning", "submitFeedbackForBarcode", "feedbackJson", "updateMode", "modeJson", "updateView", "viewJson", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class SparkScanModule extends BaseFrameworkModule {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FrameworksCaptureContext captureContext;
    private final Emitter emitter;
    private final FrameDataHandler frameDataHandler;
    private final FrameworksLog logger;
    private final FrameworksViewsCache<FrameworksSparkScanView> viewCache;

    @JvmStatic
    public static final SparkScanModule create(Emitter emitter) {
        return INSTANCE.create(emitter);
    }

    public static /* synthetic */ void getViewCache$annotations() {
    }

    public /* synthetic */ SparkScanModule(Emitter emitter, DefaultFrameworksLog defaultFrameworksLog, DefaultFrameDataHandler defaultFrameDataHandler, DefaultFrameworksCaptureContext defaultFrameworksCaptureContext, DefaultDeserializationLifecycleObserver defaultDeserializationLifecycleObserver, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(emitter, (i & 2) != 0 ? DefaultFrameworksLog.INSTANCE.getInstance() : defaultFrameworksLog, (i & 4) != 0 ? DefaultFrameDataHandler.INSTANCE.getInstance() : defaultFrameDataHandler, (i & 8) != 0 ? DefaultFrameworksCaptureContext.INSTANCE.getInstance() : defaultFrameworksCaptureContext, (i & 16) != 0 ? DefaultDeserializationLifecycleObserver.INSTANCE.getInstance() : defaultDeserializationLifecycleObserver);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SparkScanModule(Emitter emitter, FrameworksLog logger, FrameDataHandler frameDataHandler, FrameworksCaptureContext captureContext, DeserializationLifecycleObserver deserializationLifecycleObserver) {
        super(deserializationLifecycleObserver, null, 2, null);
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(frameDataHandler, "frameDataHandler");
        Intrinsics.checkNotNullParameter(captureContext, "captureContext");
        Intrinsics.checkNotNullParameter(deserializationLifecycleObserver, "deserializationLifecycleObserver");
        this.emitter = emitter;
        this.logger = logger;
        this.frameDataHandler = frameDataHandler;
        this.captureContext = captureContext;
        this.viewCache = new FrameworksViewsCache<>();
    }

    public final FrameworksViewsCache<FrameworksSparkScanView> getViewCache() {
        return this.viewCache;
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
        this.viewCache.disposeAll();
        super.onDestroy();
    }

    public final boolean execute(FrameworksMethodCall method, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(result, "result");
        String method2 = method.getMethod();
        if (method2 == null) {
            return false;
        }
        switch (method2.hashCode()) {
            case -2135035710:
                if (!method2.equals("showSparkScanView")) {
                    return false;
                }
                showView(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case -1988557644:
                if (!method2.equals("sparkScanViewStartScanning")) {
                    return false;
                }
                startScanning(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case -1968981228:
                if (!method2.equals("submitFeedbackForBarcode")) {
                    return false;
                }
                submitFeedbackForBarcode(((Number) method.argument("viewId")).intValue(), (String) method.argument("feedbackJson"), result);
                return true;
            case -1913642710:
                if (!method2.equals("showToast")) {
                    return false;
                }
                showToast(((Number) method.argument("viewId")).intValue(), (String) method.argument("text"), result);
                return true;
            case -1205367366:
                if (!method2.equals("unregisterSparkScanFeedbackDelegateForEvents")) {
                    return false;
                }
                removeFeedbackDelegate(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case -1194851539:
                if (!method2.equals("addSparkScanListener")) {
                    return false;
                }
                addSparkScanListener(((Number) method.argument("viewId")).intValue());
                result.success(null);
                return true;
            case -950014042:
                if (!method2.equals("addSparkScanViewUiListener")) {
                    return false;
                }
                addSparkScanViewUiListener(((Number) method.argument("viewId")).intValue());
                result.success(null);
                return true;
            case -257247351:
                if (!method2.equals("finishDidScan")) {
                    return false;
                }
                finishDidScanCallback(((Number) method.argument("viewId")).intValue(), ((Boolean) method.argument(ExtentionsKt.ENABLED_KEY)).booleanValue());
                result.success(null);
                return true;
            case -130465693:
                if (!method2.equals("removeSparkScanViewUiListener")) {
                    return false;
                }
                removeSparkScanViewUiListener(((Number) method.argument("viewId")).intValue());
                result.success(null);
                return true;
            case 102865409:
                if (!method2.equals("finishDidUpdateSession")) {
                    return false;
                }
                finishDidUpdateSessionCallback(((Number) method.argument("viewId")).intValue(), ((Boolean) method.argument(ExtentionsKt.ENABLED_KEY)).booleanValue());
                result.success(null);
                return true;
            case 300257864:
                if (!method2.equals("sparkScanViewPauseScanning")) {
                    return false;
                }
                pauseScanning(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case 327531190:
                if (!method2.equals("getSparkScanDefaults")) {
                    return false;
                }
                result.success(new JSONObject(getDefaults()).toString());
                return true;
            case 405267882:
                if (!method2.equals("removeSparkScanListener")) {
                    return false;
                }
                removeSparkScanListener(((Number) method.argument("viewId")).intValue());
                result.success(null);
                return true;
            case 536727137:
                if (!method2.equals("registerSparkScanFeedbackDelegateForEvents")) {
                    return false;
                }
                addFeedbackDelegate(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case 754812573:
                if (!method2.equals("hideSparkScanView")) {
                    return false;
                }
                hideView(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case 1522047403:
                if (!method2.equals("resetSparkScanSession")) {
                    return false;
                }
                resetSession(((Number) method.argument("viewId")).intValue());
                result.success(null);
                return true;
            case 1552642664:
                if (!method2.equals("sparkScanViewUpdate")) {
                    return false;
                }
                updateView(((Number) method.argument("viewId")).intValue(), (String) method.argument("updateJson"), result);
                return true;
            case 1641371541:
                if (!method2.equals("setModeEnabledState")) {
                    return false;
                }
                setModeEnabled(((Number) method.argument("viewId")).intValue(), ((Boolean) method.argument(ExtentionsKt.ENABLED_KEY)).booleanValue());
                result.success(null);
                return true;
            case 1927438603:
                if (!method2.equals("getLastFrameData")) {
                    return false;
                }
                getFrameDataBytes((String) method.arguments(), result);
                return true;
            case 2049662324:
                if (!method2.equals("updateSparkScanMode")) {
                    return false;
                }
                updateMode(((Number) method.argument("viewId")).intValue(), (String) method.argument("updateJson"), result);
                return true;
            default:
                return false;
        }
    }

    public final Map<String, Object> getDefaults() {
        return SparkScanDefaults.INSTANCE.get();
    }

    public final void addSparkScanListener(int viewId) {
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.enableSparkScanListener();
    }

    public final void removeSparkScanListener(int viewId) {
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.disableSparkScanListener();
    }

    public final void finishDidUpdateSessionCallback(int viewId, boolean enabled) {
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.onFinishSessionUpdatedCallback(enabled);
    }

    public final void finishDidScanCallback(int viewId, boolean enabled) {
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.onFinishBarcodeScannedCallback(enabled);
    }

    public final void resetSession(int viewId) {
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.resetLastSession();
    }

    public final void addSparkScanViewUiListener(final int viewId) {
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.spark.SparkScanModule$addSparkScanViewUiListener$viewInstance$1
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
                    SparkScanModule.this.addSparkScanViewUiListener(viewId);
                }
            });
        } else {
            view.addSparkScanViewUiListener();
        }
    }

    public final void removeSparkScanViewUiListener(int viewId) {
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.removeSparkScanViewUiListener();
    }

    public final int addViewToContainer(View containerView, String jsonString, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(containerView, "containerView");
        Intrinsics.checkNotNullParameter(jsonString, "jsonString");
        Intrinsics.checkNotNullParameter(result, "result");
        try {
            DataCaptureContext context = this.captureContext.getContext();
            if (context == null) {
                SparkScanModule sparkScanModule = this;
                ExtentionsKt.reject(result, new DataCaptureContextNotInitialized());
                return -1;
            }
            SparkScanViewCreationData fromJson = SparkScanViewCreationData.INSTANCE.fromJson(jsonString);
            FrameworksSparkScanView view = this.viewCache.getView(fromJson.getViewId());
            if (view != null) {
                view.dispose();
                this.viewCache.remove(view.get_viewId());
            }
            FrameworksSparkScanView topMost = this.viewCache.getTopMost();
            if (topMost != null) {
                topMost.hide();
            }
            FrameworksSparkScanView create = FrameworksSparkScanView.INSTANCE.create(this.emitter, context, containerView, fromJson);
            this.viewCache.addView(create);
            Iterator<Function0<Unit>> it = getPostSpecificViewCreationActions(create.get_viewId()).iterator();
            while (it.hasNext()) {
                it.next().invoke();
            }
            result.success(null);
            return create.get_viewId();
        } catch (Exception e) {
            this.logger.error(e);
            ExtentionsKt.reject(result, e);
            return -1;
        }
    }

    public final void updateView(int viewId, String viewJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(viewJson, "viewJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view == null) {
            ExtentionsKt.reject(result, new Error("View with id " + viewId + " was not yet created or already disposed."));
            return;
        }
        view.updateView(viewJson);
        result.success(null);
    }

    public final void updateMode(final int viewId, final String modeJson, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.spark.SparkScanModule$updateMode$viewInstance$1
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
                    SparkScanModule.this.updateMode(viewId, modeJson, result);
                }
            });
        } else {
            view.updateMode(modeJson);
            result.success(null);
        }
    }

    public final void pauseScanning(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.pauseScanning();
        }
    }

    public final void startScanning(final int viewId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.spark.SparkScanModule$startScanning$viewInstance$1
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
                    SparkScanModule.this.startScanning(viewId, result);
                }
            });
        } else {
            view.startScanning();
            result.success(null);
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle.Observer
    public void onActivityResume() {
        FrameworksSparkScanView topMost = this.viewCache.getTopMost();
        if (topMost == null) {
            return;
        }
        topMost.onActivityResume();
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle.Observer
    public void onActivityPause() {
        FrameworksSparkScanView topMost = this.viewCache.getTopMost();
        if (topMost == null) {
            return;
        }
        topMost.onActivityPause();
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle.Observer
    public void onActivityStop() {
        FrameworksSparkScanView topMost = this.viewCache.getTopMost();
        if (topMost != null) {
            topMost.cancelPendingEvents();
        }
        super.onActivityStop();
    }

    public final void disposeView(int viewId) {
        FrameworksSparkScanView remove = this.viewCache.remove(viewId);
        if (remove != null) {
            remove.dispose();
        }
        clearPostSpecificViewCreationActions(viewId);
        FrameworksSparkScanView topMost = this.viewCache.getTopMost();
        if (topMost != null) {
            topMost.show();
        }
    }

    public final void showToast(final int viewId, final String text, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.spark.SparkScanModule$showToast$viewInstance$1
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
                    SparkScanModule.this.showToast(viewId, text, result);
                }
            });
        } else {
            view.showToast(text);
            result.success(null);
        }
    }

    public final void setModeEnabled(final int viewId, final boolean enabled) {
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.spark.SparkScanModule$setModeEnabled$viewInstance$1
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
                    SparkScanModule.this.setModeEnabled(viewId, enabled);
                }
            });
        } else {
            view.setModeEnabled(enabled);
        }
    }

    public final void setTopMostViewModeEnabled(boolean enabled) {
        FrameworksSparkScanView topMost = this.viewCache.getTopMost();
        if (topMost == null) {
            return;
        }
        topMost.setModeEnabled(enabled);
    }

    public final boolean isModeEnabled() {
        FrameworksSparkScanView topMost = this.viewCache.getTopMost();
        return topMost != null && topMost.isModeEnabled();
    }

    public final void submitFeedbackForBarcode(int viewId, String feedbackJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.submitFeedback(feedbackJson);
            result.success(null);
        }
    }

    public final void addFeedbackDelegate(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.addFeedbackDelegate();
            result.success(null);
        }
    }

    public final void removeFeedbackDelegate(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.removeFeedbackDelegate();
            result.success(null);
        }
    }

    public final void getFrameDataBytes(String frameId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(frameId, "frameId");
        Intrinsics.checkNotNullParameter(result, "result");
        this.frameDataHandler.retrieveDataAsMap(frameId, new Function1<Map<String, ? extends Object>, Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.spark.SparkScanModule$getFrameDataBytes$1
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

    public final void setViewLayoutParams(int viewId, ViewGroup.LayoutParams layoutParams) {
        Intrinsics.checkNotNullParameter(layoutParams, "layoutParams");
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view != null) {
            view.setViewLayoutParams(layoutParams);
        }
    }

    public final void dispatchWindowVisibilityChanged(int viewId, int visibility) {
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view != null) {
            view.dispatchWindowVisibilityChanged(visibility);
        }
    }

    public final void hideView(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.hide();
        }
    }

    public final void showView(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksSparkScanView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.show();
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver.Observer
    public void onAllModesRemovedFromContext() {
        this.viewCache.disposeAll();
    }

    /* compiled from: SparkScanModule.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/spark/SparkScanModule$Companion;", "", "()V", "create", "Lcom/scandit/datacapture/frameworks/barcode/spark/SparkScanModule;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final SparkScanModule create(Emitter emitter) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            return new SparkScanModule(emitter, null, null, null, null, 30, null);
        }
    }
}
