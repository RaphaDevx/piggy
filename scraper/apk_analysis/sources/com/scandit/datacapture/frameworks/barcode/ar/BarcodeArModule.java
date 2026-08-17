package com.scandit.datacapture.frameworks.barcode.ar;

import android.content.Context;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.PluginCall;
import com.scandit.datacapture.barcode.ar.ui.BarcodeArView;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.frameworks.barcode.ar.caches.BarcodeArAugmentationsCache;
import com.scandit.datacapture.frameworks.barcode.ar.data.BarcodeArViewCreationData;
import com.scandit.datacapture.frameworks.barcode.ar.data.defaults.BarcodeArDefaults;
import com.scandit.datacapture.frameworks.barcode.ar.ui.FrameworksBarcodeArView;
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
import io.sentry.protocol.Feedback;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BarcodeArModule.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001c\u0018\u0000 R2\u00020\u0001:\u0001RB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010#\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010%\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010&\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010'\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u0017J\u0014\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\u001c\u0012\u0006\u0012\u0004\u0018\u00010*0)J\u0016\u0010+\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u0017J)\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00101J\u000e\u00102\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\b\u00103\u001a\u00020\u0013H\u0016J\b\u00104\u001a\u00020\u0013H\u0016J\u0010\u00105\u001a\u00020\u00132\u0006\u00106\u001a\u000207H\u0016J\b\u00108\u001a\u00020\u0013H\u0016J\u0016\u00109\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010:\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010;\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010<\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010=\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010>\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010?\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010@\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010A\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010B\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010C\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010D\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010E\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010F\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010G\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010H\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010I\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010J\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010K\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010L\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010M\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010N\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010O\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010P\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010Q\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006S"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/BarcodeArModule;", "Lcom/scandit/datacapture/frameworks/core/common/BaseFrameworkModule;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "frameDataHandler", "Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;", "captureContext", "Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;", "deserializationLifecycleObserver", "Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;Lcom/scandit/datacapture/frameworks/core/frames/FrameDataHandler;Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;)V", "viewCache", "Lcom/scandit/datacapture/frameworks/core/ui/FrameworksViewsCache;", "Lcom/scandit/datacapture/frameworks/barcode/ar/ui/FrameworksBarcodeArView;", "getViewCache$annotations", "()V", "getViewCache", "()Lcom/scandit/datacapture/frameworks/core/ui/FrameworksViewsCache;", "addModeListener", "", "viewId", "", "result", "Lcom/scandit/datacapture/frameworks/core/result/FrameworksResult;", "addViewToContainer", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "jsonString", "", "applyModeSettings", "modeSettingsJson", "execute", "", "method", "Lcom/scandit/datacapture/frameworks/core/method/FrameworksMethodCall;", "finishAnnotationForBarcode", "barcodeResponse", "finishDidUpdateSession", "finishHighlightForBarcode", "highlightResponse", "getDefaults", "", "", "getFrameDataBytes", "frameId", "getView", "Lkotlin/Result;", "Lcom/scandit/datacapture/barcode/ar/ui/BarcodeArView;", "getView-gIAlu-s", "(Landroid/view/ViewGroup;Ljava/lang/String;)Ljava/lang/Object;", "hideView", "onActivityPause", "onActivityResume", "onCreate", "context", "Landroid/content/Context;", "onDestroy", "registerBarcodeArAnnotationProvider", "registerBarcodeArHighlightProvider", "registerBarcodeArViewUiListener", "removeModeListener", "resetLatestBarcodeArSession", "showView", "unregisterBarcodeArAnnotationProvider", "unregisterBarcodeArHighlightProvider", "unregisterBarcodeArViewUiListener", "updateAnnotation", "annotationJson", "updateBarcodeArPopoverButtonAtIndex", "updateJson", "updateFeedback", "feedbackJson", "updateHighlight", "highlightJson", "updateMode", "modeJson", "updateView", "viewJson", "viewDisposed", "viewReset", "viewStart", "viewStop", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeArModule extends BaseFrameworkModule {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FrameworksCaptureContext captureContext;
    private final Emitter emitter;
    private final FrameDataHandler frameDataHandler;
    private final FrameworksViewsCache<FrameworksBarcodeArView> viewCache;

    @JvmStatic
    public static final BarcodeArModule create(Emitter emitter) {
        return INSTANCE.create(emitter);
    }

    public static /* synthetic */ void getViewCache$annotations() {
    }

    public /* synthetic */ BarcodeArModule(Emitter emitter, DefaultFrameDataHandler defaultFrameDataHandler, DefaultFrameworksCaptureContext defaultFrameworksCaptureContext, DefaultDeserializationLifecycleObserver defaultDeserializationLifecycleObserver, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(emitter, (i & 2) != 0 ? DefaultFrameDataHandler.INSTANCE.getInstance() : defaultFrameDataHandler, (i & 4) != 0 ? DefaultFrameworksCaptureContext.INSTANCE.getInstance() : defaultFrameworksCaptureContext, (i & 8) != 0 ? DefaultDeserializationLifecycleObserver.INSTANCE.getInstance() : defaultDeserializationLifecycleObserver);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BarcodeArModule(Emitter emitter, FrameDataHandler frameDataHandler, FrameworksCaptureContext captureContext, DeserializationLifecycleObserver deserializationLifecycleObserver) {
        super(deserializationLifecycleObserver, null, 2, null);
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        Intrinsics.checkNotNullParameter(frameDataHandler, "frameDataHandler");
        Intrinsics.checkNotNullParameter(captureContext, "captureContext");
        Intrinsics.checkNotNullParameter(deserializationLifecycleObserver, "deserializationLifecycleObserver");
        this.emitter = emitter;
        this.frameDataHandler = frameDataHandler;
        this.captureContext = captureContext;
        this.viewCache = new FrameworksViewsCache<>();
    }

    public final FrameworksViewsCache<FrameworksBarcodeArView> getViewCache() {
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
        this.viewCache.disposeAll();
        detachForActivityLifecycleEvents();
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
            case -2036675774:
                if (!method2.equals("unregisterBarcodeArViewUiListener")) {
                    return false;
                }
                unregisterBarcodeArViewUiListener(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case -2015930487:
                if (!method2.equals("applyBarcodeArModeSettings")) {
                    return false;
                }
                applyModeSettings(((Number) method.argument("viewId")).intValue(), (String) method.argument("settings"), result);
                return true;
            case -1696751851:
                if (!method2.equals("unregisterBarcodeArAnnotationProvider")) {
                    return false;
                }
                unregisterBarcodeArAnnotationProvider(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case -1587422639:
                if (!method2.equals("viewPause")) {
                    return false;
                }
                result.success(null);
                return true;
            case -1585458774:
                if (!method2.equals("viewReset")) {
                    return false;
                }
                viewReset(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case -1584105283:
                if (!method2.equals("viewStart")) {
                    return false;
                }
                viewStart(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case -1409303474:
                if (!method2.equals("registerBarcodeArAnnotationProvider")) {
                    return false;
                }
                registerBarcodeArAnnotationProvider(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case -1401289993:
                if (!method2.equals("registerBarcodeArHighlightProvider")) {
                    return false;
                }
                registerBarcodeArHighlightProvider(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case -1091423182:
                if (!method2.equals("barcodeArFinishDidUpdateSession")) {
                    return false;
                }
                finishDidUpdateSession(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case -295871730:
                if (!method2.equals("updateView")) {
                    return false;
                }
                updateView(((Number) method.argument("viewId")).intValue(), (String) method.argument("viewJson"), result);
                return true;
            case -32823717:
                if (!method2.equals("resetLatestBarcodeArSession")) {
                    return false;
                }
                resetLatestBarcodeArSession(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case -25089200:
                if (!method2.equals("unregisterBarcodeArHighlightProvider")) {
                    return false;
                }
                unregisterBarcodeArHighlightProvider(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case 128653392:
                if (!method2.equals("updateBarcodeArPopoverButtonAtIndex")) {
                    return false;
                }
                updateBarcodeArPopoverButtonAtIndex(((Number) method.argument("viewId")).intValue(), (String) method.argument("updateJson"), result);
                return true;
            case 269260292:
                if (!method2.equals("addBarcodeArListener")) {
                    return false;
                }
                addModeListener(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case 524305899:
                if (!method2.equals("updateHighlight")) {
                    return false;
                }
                updateHighlight(((Number) method.argument("viewId")).intValue(), (String) method.argument("highlightJson"), result);
                return true;
            case 535035905:
                if (!method2.equals("getFrameData")) {
                    return false;
                }
                getFrameDataBytes((String) method.arguments(), result);
                return true;
            case 678670190:
                if (!method2.equals("updateFeedback")) {
                    return false;
                }
                updateFeedback(((Number) method.argument("viewId")).intValue(), (String) method.argument(Feedback.TYPE), result);
                return true;
            case 709270456:
                if (!method2.equals("finishHighlightForBarcode")) {
                    return false;
                }
                finishHighlightForBarcode(((Number) method.argument("viewId")).intValue(), (String) method.argument("result"), result);
                return true;
            case 761328587:
                if (!method2.equals("updateBarcodeArMode")) {
                    return false;
                }
                updateMode(((Number) method.argument("viewId")).intValue(), (String) method.argument("modeJson"), result);
                return true;
            case 1030111545:
                if (!method2.equals("finishAnnotationForBarcode")) {
                    return false;
                }
                finishAnnotationForBarcode(((Number) method.argument("viewId")).intValue(), (String) method.argument("result"), result);
                return true;
            case 1195826247:
                if (!method2.equals("viewStop")) {
                    return false;
                }
                viewStop(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case 1456232664:
                if (!method2.equals("updateAnnotation")) {
                    return false;
                }
                updateAnnotation(((Number) method.argument("viewId")).intValue(), (String) method.argument("annotationJson"), result);
                return true;
            case 1791643021:
                if (!method2.equals("getBarcodeArDefaults")) {
                    return false;
                }
                result.success(new JSONObject(getDefaults()).toString());
                return true;
            case 1869379713:
                if (!method2.equals("removeBarcodeArListener")) {
                    return false;
                }
                removeModeListener(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case 2118544635:
                if (!method2.equals("registerBarcodeArViewUiListener")) {
                    return false;
                }
                registerBarcodeArViewUiListener(((Number) method.argument("viewId")).intValue(), result);
                return true;
            default:
                return false;
        }
    }

    public final void updateBarcodeArPopoverButtonAtIndex(int viewId, String updateJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(updateJson, "updateJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.updateBarcodeArPopoverButtonAtIndex(updateJson);
            result.success(null);
        }
    }

    public final void updateHighlight(int viewId, String highlightJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(highlightJson, "highlightJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.updateHighlight(highlightJson);
            result.success(null);
        }
    }

    public final void updateAnnotation(int viewId, String annotationJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(annotationJson, "annotationJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.updateAnnotation(annotationJson);
            result.success(null);
        }
    }

    public final void finishHighlightForBarcode(int viewId, String highlightResponse, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(highlightResponse, "highlightResponse");
        Intrinsics.checkNotNullParameter(result, "result");
        Context context = getContext();
        if (context == null) {
            result.success(null);
            return;
        }
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.finishHighlightForBarcode(context, highlightResponse);
            result.success(null);
        }
    }

    public final void finishAnnotationForBarcode(int viewId, String barcodeResponse, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(barcodeResponse, "barcodeResponse");
        Intrinsics.checkNotNullParameter(result, "result");
        Context context = getContext();
        if (context == null) {
            result.success(null);
            return;
        }
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.finishAnnotationForBarcode(context, barcodeResponse);
            result.success(null);
        }
    }

    public final void unregisterBarcodeArAnnotationProvider(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.removeBarcodeArAnnotationProvider();
        result.success(null);
    }

    public final void registerBarcodeArAnnotationProvider(final int viewId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.ar.BarcodeArModule$registerBarcodeArAnnotationProvider$viewInstance$1
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
                    BarcodeArModule.this.registerBarcodeArAnnotationProvider(viewId, result);
                }
            });
        } else {
            view.addBarcodeArAnnotationProvider();
            result.success(null);
        }
    }

    public final void unregisterBarcodeArHighlightProvider(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.removeBarcodeArHighlightProvider();
            result.success(null);
        }
    }

    public final void registerBarcodeArHighlightProvider(final int viewId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.ar.BarcodeArModule$registerBarcodeArHighlightProvider$viewInstance$1
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
                    BarcodeArModule.this.registerBarcodeArHighlightProvider(viewId, result);
                }
            });
        } else {
            view.addBarcodeArHighlightProvider();
            result.success(null);
        }
    }

    public final void unregisterBarcodeArViewUiListener(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.removeBarcodeArViewUiListener();
            result.success(null);
        }
    }

    public final void registerBarcodeArViewUiListener(final int viewId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.ar.BarcodeArModule$registerBarcodeArViewUiListener$viewInstance$1
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
                    BarcodeArModule.this.registerBarcodeArViewUiListener(viewId, result);
                }
            });
        } else {
            view.addBarcodeArViewUiListener();
            result.success(null);
        }
    }

    public final void updateView(final int viewId, final String viewJson, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(viewJson, "viewJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.ar.BarcodeArModule$updateView$viewInstance$1
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
                    BarcodeArModule.this.updateView(viewId, viewJson, result);
                }
            });
        } else {
            view.updateView(viewJson);
            result.success(null);
        }
    }

    public final Map<String, Object> getDefaults() {
        return BarcodeArDefaults.INSTANCE.get();
    }

    public final void updateFeedback(final int viewId, final String feedbackJson, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(feedbackJson, "feedbackJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.ar.BarcodeArModule$updateFeedback$viewInstance$1
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
                    BarcodeArModule.this.updateFeedback(viewId, feedbackJson, result);
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

    public final void resetLatestBarcodeArSession(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.resetSession();
            result.success(null);
        }
    }

    public final void getFrameDataBytes(String frameId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(frameId, "frameId");
        Intrinsics.checkNotNullParameter(result, "result");
        this.frameDataHandler.retrieveDataAsMap(frameId, new Function1<Map<String, ? extends Object>, Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.ar.BarcodeArModule$getFrameDataBytes$1
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

    public final void applyModeSettings(final int viewId, final String modeSettingsJson, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(modeSettingsJson, "modeSettingsJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.ar.BarcodeArModule$applyModeSettings$viewInstance$1
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
                    BarcodeArModule.this.applyModeSettings(viewId, modeSettingsJson, result);
                }
            });
        } else {
            view.applySettings(modeSettingsJson);
            result.success(null);
        }
    }

    public final void updateMode(final int viewId, final String modeJson, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.ar.BarcodeArModule$updateMode$viewInstance$1
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
                    BarcodeArModule.this.updateMode(viewId, modeJson, result);
                }
            });
        } else {
            view.updateMode(modeJson);
            result.success(null);
        }
    }

    public final void finishDidUpdateSession(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.finishDidUpdateSession();
            result.success(null);
        }
    }

    public final void removeModeListener(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.removeBarcodeArListener();
            result.success(null);
        }
    }

    public final void addModeListener(final int viewId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.ar.BarcodeArModule$addModeListener$viewInstance$1
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
                    BarcodeArModule.this.addModeListener(viewId, result);
                }
            });
        } else {
            view.addBarcodeArListener();
            result.success(null);
        }
    }

    public final void addViewToContainer(ViewGroup container, String jsonString, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(jsonString, "jsonString");
        Intrinsics.checkNotNullParameter(result, "result");
        Object m3783getViewgIAlus = m3783getViewgIAlus(container, jsonString);
        if (Result.m3951isFailureimpl(m3783getViewgIAlus)) {
            Throwable m3948exceptionOrNullimpl = Result.m3948exceptionOrNullimpl(m3783getViewgIAlus);
            if (m3948exceptionOrNullimpl == null) {
                m3948exceptionOrNullimpl = new Throwable("Unable to create the view from the json " + jsonString + ' ');
            }
            ExtentionsKt.reject(result, m3948exceptionOrNullimpl);
            return;
        }
        result.success(null);
    }

    /* renamed from: getView-gIAlu-s, reason: not valid java name */
    public final Object m3783getViewgIAlus(ViewGroup container, String jsonString) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(jsonString, "jsonString");
        DataCaptureContext context = this.captureContext.getContext();
        if (context == null) {
            Result.Companion companion = Result.INSTANCE;
            return Result.m3945constructorimpl(ResultKt.createFailure(new DataCaptureContextNotInitialized()));
        }
        try {
            BarcodeArViewCreationData fromJson = BarcodeArViewCreationData.INSTANCE.fromJson(jsonString);
            FrameworksBarcodeArView view = this.viewCache.getView(fromJson.getViewId());
            if (view != null) {
                view.dispose();
                this.viewCache.remove(view.get_viewId());
            }
            FrameworksBarcodeArView topMost = this.viewCache.getTopMost();
            if (topMost != null) {
                topMost.hide();
            }
            FrameworksBarcodeArView create = FrameworksBarcodeArView.INSTANCE.create(this.emitter, container, context, fromJson);
            this.viewCache.addView(create);
            if (fromJson.getIsStarted()) {
                create.start();
            }
            Iterator<Function0<Unit>> it = getPostSpecificViewCreationActions(create.get_viewId()).iterator();
            while (it.hasNext()) {
                it.next().invoke();
            }
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m3945constructorimpl(create.getView());
        } catch (Exception e) {
            Result.Companion companion3 = Result.INSTANCE;
            StringBuilder sb = new StringBuilder("Error during the barcode ar view deserialization. Error: ");
            String message = e.getMessage();
            if (message == null) {
                message = "unknown";
            }
            sb.append(message);
            return Result.m3945constructorimpl(ResultKt.createFailure(new Throwable(sb.toString())));
        }
    }

    public final void viewStart(final int viewId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.ar.BarcodeArModule$viewStart$viewInstance$1
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
                    BarcodeArModule.this.viewStart(viewId, result);
                }
            });
        } else {
            view.start();
            result.success(null);
        }
    }

    public final void viewStop(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.stop();
            result.success(null);
        }
    }

    public final void viewDisposed(int viewId) {
        FrameworksBarcodeArView remove = this.viewCache.remove(viewId);
        if (remove != null) {
            remove.dispose();
        }
        clearPostSpecificViewCreationActions(viewId);
        FrameworksBarcodeArView topMost = this.viewCache.getTopMost();
        if (topMost != null) {
            topMost.show();
        }
    }

    public final void viewReset(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.reset();
            result.success(null);
        }
    }

    public final void showView(int viewId) {
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view != null) {
            view.show();
        }
    }

    public final void hideView(int viewId) {
        FrameworksBarcodeArView view = this.viewCache.getView(viewId);
        if (view != null) {
            view.hide();
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle.Observer
    public void onActivityPause() {
        BarcodeArAugmentationsCache cache;
        BarcodeArView view;
        FrameworksBarcodeArView topMost = this.viewCache.getTopMost();
        if (topMost != null && (view = topMost.getView()) != null) {
            view.onPause();
        }
        if (topMost == null || (cache = topMost.getCache()) == null) {
            return;
        }
        cache.clear();
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle.Observer
    public void onActivityResume() {
        BarcodeArView view;
        FrameworksBarcodeArView topMost = this.viewCache.getTopMost();
        if (topMost == null || (view = topMost.getView()) == null) {
            return;
        }
        view.onResume();
    }

    /* compiled from: BarcodeArModule.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/BarcodeArModule$Companion;", "", "()V", "create", "Lcom/scandit/datacapture/frameworks/barcode/ar/BarcodeArModule;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final BarcodeArModule create(Emitter emitter) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            return new BarcodeArModule(emitter, null, null, null, 14, null);
        }
    }
}
