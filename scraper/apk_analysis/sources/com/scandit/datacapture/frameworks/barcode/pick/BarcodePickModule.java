package com.scandit.datacapture.frameworks.barcode.pick;

import android.content.Context;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickStatusIconStyle;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickView;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.frameworks.barcode.pick.data.BarcodePickViewCreationData;
import com.scandit.datacapture.frameworks.barcode.pick.data.defaults.BarcodePickDefaults;
import com.scandit.datacapture.frameworks.barcode.pick.ui.FrameworksBarcodePickView;
import com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule;
import com.scandit.datacapture.frameworks.core.context.DefaultFrameworksCaptureContext;
import com.scandit.datacapture.frameworks.core.context.FrameworksCaptureContext;
import com.scandit.datacapture.frameworks.core.deserialization.DefaultDeserializationLifecycleObserver;
import com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver;
import com.scandit.datacapture.frameworks.core.errors.DataCaptureContextNotInitialized;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.method.FrameworksMethodCall;
import com.scandit.datacapture.frameworks.core.result.FrameworksResult;
import com.scandit.datacapture.frameworks.core.result.NoopFrameworksResult;
import com.scandit.datacapture.frameworks.core.ui.FrameworksViewsCache;
import com.tealium.library.DataSources;
import io.sentry.protocol.Response;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: BarcodePickModule.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0016\u0018\u0000 E2\u00020\u0001:\u0001EB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J \u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0014\u001a\u00020\u0015J6\u0010$\u001a\u00020\u00112&\u0010%\u001a\"\u0012\u0004\u0012\u00020\u001e\u0012\u0006\u0012\u0004\u0018\u00010'0&j\u0010\u0012\u0004\u0012\u00020\u001e\u0012\u0006\u0012\u0004\u0018\u00010'`(2\u0006\u0010\u0014\u001a\u00020\u0015J6\u0010)\u001a\u00020\u00112&\u0010%\u001a\"\u0012\u0004\u0012\u00020\u001e\u0012\u0006\u0012\u0004\u0018\u00010'0&j\u0010\u0012\u0004\u0012\u00020\u001e\u0012\u0006\u0012\u0004\u0018\u00010'`(2\u0006\u0010\u0014\u001a\u00020\u0015J6\u0010*\u001a\u00020\u00112&\u0010%\u001a\"\u0012\u0004\u0012\u00020\u001e\u0012\u0006\u0012\u0004\u0018\u00010'0&j\u0010\u0012\u0004\u0012\u00020\u001e\u0012\u0006\u0012\u0004\u0018\u00010'`(2\u0006\u0010\u0014\u001a\u00020\u0015J\u001e\u0010+\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010,\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020!J6\u0010+\u001a\u00020\u00112&\u0010%\u001a\"\u0012\u0004\u0012\u00020\u001e\u0012\u0006\u0012\u0004\u0018\u00010'0&j\u0010\u0012\u0004\u0012\u00020\u001e\u0012\u0006\u0012\u0004\u0018\u00010'`(2\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010.\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0014\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u001e\u0012\u0006\u0012\u0004\u0018\u00010'00J\b\u00101\u001a\u00020\u0011H\u0016J\b\u00102\u001a\u00020\u0011H\u0016J\b\u00103\u001a\u00020\u0011H\u0016J\u0010\u00104\u001a\u00020\u00112\u0006\u00105\u001a\u000206H\u0016J\b\u00107\u001a\u00020\u0011H\u0016J\u0016\u00108\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u00109\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010:\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010;\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010<\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010=\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010>\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010?\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010@\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010A\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u001e\u0010B\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010C\u001a\u00020\u001e2\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010D\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006F"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/BarcodePickModule;", "Lcom/scandit/datacapture/frameworks/core/common/BaseFrameworkModule;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "captureContext", "Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;", "deserializationLifecycleObserver", "Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;)V", "viewCache", "Lcom/scandit/datacapture/frameworks/core/ui/FrameworksViewsCache;", "Lcom/scandit/datacapture/frameworks/barcode/pick/ui/FrameworksBarcodePickView;", "getViewCache$annotations", "()V", "getViewCache", "()Lcom/scandit/datacapture/frameworks/core/ui/FrameworksViewsCache;", "addActionListener", "", "viewId", "", "result", "Lcom/scandit/datacapture/frameworks/core/result/FrameworksResult;", "addBarcodePickListener", "addScanningListener", "addViewListener", "addViewToContainer", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickView;", "containerView", "Landroid/view/ViewGroup;", "jsonString", "", "addViewUiListener", "execute", "", NotificationCompat.CATEGORY_CALL, "Lcom/scandit/datacapture/frameworks/core/method/FrameworksMethodCall;", "finishBarcodePickViewHighlightStyleAsyncProviderStyleForRequest", Response.TYPE, "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "finishBarcodePickViewHighlightStyleCustomViewProviderViewForRequest", "finishOnProductIdentifierForItems", "finishPickAction", "itemData", "actionResult", "freezePickView", "getDefaults", "", "onActivityDestroy", "onActivityPause", "onActivityResume", "onCreate", "context", "Landroid/content/Context;", "onDestroy", "pausePickView", "releasePickView", "removeActionListener", "removeBarcodePickListener", "removeScanningListener", "removeViewListener", "removeViewUiListener", "resumePickView", "startPickView", "stopPickView", "updateView", "viewJson", "viewReset", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class BarcodePickModule extends BaseFrameworkModule {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FrameworksCaptureContext captureContext;
    private final Emitter emitter;
    private final FrameworksViewsCache<FrameworksBarcodePickView> viewCache;

    @JvmStatic
    public static final BarcodePickModule create(Emitter emitter) {
        return INSTANCE.create(emitter);
    }

    public static /* synthetic */ void getViewCache$annotations() {
    }

    public /* synthetic */ BarcodePickModule(Emitter emitter, DefaultFrameworksCaptureContext defaultFrameworksCaptureContext, DefaultDeserializationLifecycleObserver defaultDeserializationLifecycleObserver, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(emitter, (i & 2) != 0 ? DefaultFrameworksCaptureContext.INSTANCE.getInstance() : defaultFrameworksCaptureContext, (i & 4) != 0 ? DefaultDeserializationLifecycleObserver.INSTANCE.getInstance() : defaultDeserializationLifecycleObserver);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BarcodePickModule(Emitter emitter, FrameworksCaptureContext captureContext, DeserializationLifecycleObserver deserializationLifecycleObserver) {
        super(deserializationLifecycleObserver, null, 2, null);
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        Intrinsics.checkNotNullParameter(captureContext, "captureContext");
        Intrinsics.checkNotNullParameter(deserializationLifecycleObserver, "deserializationLifecycleObserver");
        this.emitter = emitter;
        this.captureContext = captureContext;
        this.viewCache = new FrameworksViewsCache<>();
    }

    public final FrameworksViewsCache<FrameworksBarcodePickView> getViewCache() {
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

    public final boolean execute(FrameworksMethodCall call, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(result, "result");
        String method = call.getMethod();
        if (method == null) {
            return false;
        }
        switch (method.hashCode()) {
            case -1982470733:
                if (!method.equals("resumePickView")) {
                    return false;
                }
                resumePickView(((Number) call.argument("viewId")).intValue(), result);
                return true;
            case -1912635256:
                if (!method.equals("stopPickView")) {
                    return false;
                }
                stopPickView(((Number) call.argument("viewId")).intValue(), result);
                return true;
            case -1866099148:
                if (!method.equals("addBarcodePickListener")) {
                    return false;
                }
                addBarcodePickListener(((Number) call.argument("viewId")).intValue(), result);
                return true;
            case -1760838255:
                if (!method.equals("removeViewUiListener")) {
                    return false;
                }
                removeViewUiListener(((Number) call.argument("viewId")).intValue(), result);
                return true;
            case -1749627535:
                if (!method.equals("removeBarcodePickListener")) {
                    return false;
                }
                removeBarcodePickListener(((Number) call.argument("viewId")).intValue(), result);
                return true;
            case -1572786232:
                if (!method.equals("getDefaults")) {
                    return false;
                }
                result.success(new JSONObject(getDefaults()).toString());
                return true;
            case -1169174086:
                if (!method.equals("addViewListener")) {
                    return false;
                }
                addViewListener(((Number) call.argument("viewId")).intValue(), result);
                return true;
            case -1110421987:
                if (!method.equals("freezePickView")) {
                    return false;
                }
                freezePickView(((Number) call.argument("viewId")).intValue(), result);
                return true;
            case -612264675:
                if (!method.equals("removeViewListener")) {
                    return false;
                }
                removeViewListener(((Number) call.argument("viewId")).intValue(), result);
                return true;
            case -612246296:
                if (!method.equals("startPickView")) {
                    return false;
                }
                startPickView(((Number) call.argument("viewId")).intValue(), result);
                return true;
            case -525588469:
                if (!method.equals("addActionListener")) {
                    return false;
                }
                addActionListener(((Number) call.argument("viewId")).intValue(), result);
                return true;
            case -415394339:
                if (!method.equals("finishOnProductIdentifierForItems")) {
                    return false;
                }
                finishOnProductIdentifierForItems((HashMap) call.arguments(), result);
                return true;
            case -79870226:
                if (!method.equals("addViewUiListener")) {
                    return false;
                }
                addViewUiListener(((Number) call.argument("viewId")).intValue(), result);
                return true;
            case 1191234445:
                if (!method.equals("releasePickView")) {
                    return false;
                }
                releasePickView(((Number) call.argument("viewId")).intValue(), result);
                return true;
            case 1584734234:
                if (!method.equals("finishBarcodePickViewHighlightStyleAsyncProviderStyleForRequest")) {
                    return false;
                }
                finishBarcodePickViewHighlightStyleAsyncProviderStyleForRequest((HashMap) call.arguments(), result);
                return true;
            case 1639806249:
                if (!method.equals("removeScanningListener")) {
                    return false;
                }
                removeScanningListener(((Number) call.argument("viewId")).intValue(), result);
                return true;
            case 1676569212:
                if (!method.equals("pausePickView")) {
                    return false;
                }
                pausePickView(((Number) call.argument("viewId")).intValue(), result);
                return true;
            case 1736985090:
                if (!method.equals("finishBarcodePickViewHighlightStyleCustomViewProviderViewForRequest")) {
                    return false;
                }
                finishBarcodePickViewHighlightStyleCustomViewProviderViewForRequest((HashMap) call.arguments(), result);
                return true;
            case 1908775594:
                if (!method.equals("finishPickAction")) {
                    return false;
                }
                finishPickAction((HashMap) call.arguments(), result);
                return true;
            case 2088410798:
                if (!method.equals("removeActionListener")) {
                    return false;
                }
                removeActionListener(((Number) call.argument("viewId")).intValue(), result);
                return true;
            case 2142378822:
                if (!method.equals("addScanningListener")) {
                    return false;
                }
                addScanningListener(((Number) call.argument("viewId")).intValue(), result);
                return true;
            default:
                return false;
        }
    }

    public final Map<String, Object> getDefaults() {
        return BarcodePickDefaults.INSTANCE.get();
    }

    public final BarcodePickView addViewToContainer(ViewGroup containerView, String jsonString, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(containerView, "containerView");
        Intrinsics.checkNotNullParameter(jsonString, "jsonString");
        Intrinsics.checkNotNullParameter(result, "result");
        try {
            DataCaptureContext context = this.captureContext.getContext();
            if (context == null) {
                BarcodePickModule barcodePickModule = this;
                ExtentionsKt.reject(result, new DataCaptureContextNotInitialized());
                return null;
            }
            BarcodePickViewCreationData fromJson = BarcodePickViewCreationData.INSTANCE.fromJson(jsonString);
            FrameworksBarcodePickView view = this.viewCache.getView(fromJson.getViewId());
            if (view != null) {
                view.dispose();
                this.viewCache.remove(view.get_viewId());
            }
            FrameworksBarcodePickView topMost = this.viewCache.getTopMost();
            if (topMost != null) {
                topMost.hide();
            }
            FrameworksBarcodePickView create$default = FrameworksBarcodePickView.Companion.create$default(FrameworksBarcodePickView.INSTANCE, this.emitter, containerView, context, fromJson, null, null, 48, null);
            this.viewCache.addView(create$default);
            Iterator<Function0<Unit>> it = getPostSpecificViewCreationActions(create$default.get_viewId()).iterator();
            while (it.hasNext()) {
                it.next().invoke();
            }
            result.success(Integer.valueOf(create$default.get_viewId()));
            return create$default.getView();
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Error during the barcode pick view deserialization. Error: ");
            String message = e.getMessage();
            if (message == null) {
                message = "unknown";
            }
            sb.append(message);
            ExtentionsKt.reject(result, new Throwable(sb.toString()));
            return null;
        }
    }

    public final void addViewListener(final int viewId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodePickView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.pick.BarcodePickModule$addViewListener$viewInstance$1
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
                    BarcodePickModule.this.addViewListener(viewId, result);
                }
            });
        } else {
            view.addViewListener();
            result.success(null);
        }
    }

    public final void removeViewListener(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodePickView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.removeViewListener();
            result.success(null);
        }
    }

    public final void viewReset(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodePickView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.getView().reset();
            result.success(null);
        }
    }

    public final void addViewUiListener(final int viewId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodePickView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.pick.BarcodePickModule$addViewUiListener$viewInstance$1
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
                    BarcodePickModule.this.addViewUiListener(viewId, result);
                }
            });
        } else {
            view.addViewUiListener();
            result.success(null);
        }
    }

    public final void removeViewUiListener(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodePickView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.removeViewUiListener();
            result.success(null);
        }
    }

    public final void updateView(final int viewId, final String viewJson, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(viewJson, "viewJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodePickView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.pick.BarcodePickModule$updateView$viewInstance$1
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
                    BarcodePickModule.this.updateView(viewId, viewJson, result);
                }
            });
        } else {
            view.updateView(viewJson);
            result.success(null);
        }
    }

    public final void addActionListener(final int viewId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodePickView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.pick.BarcodePickModule$addActionListener$viewInstance$1
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
                    BarcodePickModule.this.addActionListener(viewId, result);
                }
            });
        } else {
            view.addActionListener();
            result.success(null);
        }
    }

    public final void removeActionListener(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodePickView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.removeActionListener();
            result.success(null);
        }
    }

    public final void finishOnProductIdentifierForItems(HashMap<String, Object> response, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(result, "result");
        Object obj = response.get("viewId");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
        int intValue = ((Integer) obj).intValue();
        Object obj2 = response.get("data");
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.String");
        String str = (String) obj2;
        FrameworksBarcodePickView view = this.viewCache.getView(intValue);
        if (view == null) {
            result.success(null);
        } else {
            view.finishOnProductIdentifierForItems(str);
            result.success(null);
        }
    }

    public final void startPickView(final int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodePickView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.pick.BarcodePickModule$startPickView$viewInstance$1
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
                    BarcodePickModule.this.startPickView(viewId, new NoopFrameworksResult());
                }
            });
        } else {
            view.start();
            result.success(null);
        }
    }

    public final void stopPickView(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodePickView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.stop();
            result.success(null);
        }
    }

    public final void freezePickView(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodePickView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.freeze();
            result.success(null);
        }
    }

    public final void pausePickView(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodePickView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.getView().onPause();
            result.success(null);
        }
    }

    public final void resumePickView(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodePickView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.getView().onResume();
            result.success(null);
        }
    }

    public final void releasePickView(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodePickView remove = this.viewCache.remove(viewId);
        if (remove != null) {
            remove.dispose();
        }
        clearPostSpecificViewCreationActions(viewId);
        FrameworksBarcodePickView topMost = this.viewCache.getTopMost();
        if (topMost != null) {
            topMost.show();
        }
        if (topMost != null) {
            topMost.start();
        }
        result.success(null);
    }

    public final void finishPickAction(HashMap<String, Object> response, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(result, "result");
        try {
            Object obj = response.get("viewId");
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
            int intValue = ((Integer) obj).intValue();
            Object obj2 = response.get("itemData");
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.String");
            Object obj3 = response.get("result");
            Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Boolean");
            finishPickAction(intValue, (String) obj2, ((Boolean) obj3).booleanValue());
            result.success(null);
        } catch (Exception e) {
            ExtentionsKt.reject(result, e);
        }
    }

    public final void finishPickAction(int viewId, String itemData, boolean actionResult) {
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        FrameworksBarcodePickView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.finishPickAction(itemData, actionResult);
    }

    public final void addScanningListener(final int viewId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodePickView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.pick.BarcodePickModule$addScanningListener$viewInstance$1
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
                    BarcodePickModule.this.addScanningListener(viewId, result);
                }
            });
        } else {
            view.addScanningListener();
            result.success(null);
        }
    }

    public final void removeScanningListener(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodePickView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.removeScanningListener();
            result.success(null);
        }
    }

    public final void addBarcodePickListener(final int viewId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodePickView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.pick.BarcodePickModule$addBarcodePickListener$viewInstance$1
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
                    BarcodePickModule.this.addBarcodePickListener(viewId, result);
                }
            });
        } else {
            view.addBarcodePickListener();
            result.success(null);
        }
    }

    public final void removeBarcodePickListener(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodePickView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.removeBarcodePickListener();
            result.success(null);
        }
    }

    public final void finishBarcodePickViewHighlightStyleCustomViewProviderViewForRequest(HashMap<String, Object> response, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(result, "result");
        Context context = getContext();
        if (context == null) {
            result.success(null);
            return;
        }
        Object obj = response.get("viewId");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
        FrameworksBarcodePickView view = this.viewCache.getView(((Integer) obj).intValue());
        if (view == null) {
            result.success(null);
            return;
        }
        Object obj2 = response.get("requestId");
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Int");
        int intValue = ((Integer) obj2).intValue();
        Object obj3 = response.get(Response.TYPE);
        Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type org.json.JSONObject");
        JSONObject jSONObject = (JSONObject) obj3;
        Object obj4 = jSONObject.get(DataSources.EventTypeValue.VIEW_EVENT_TYPE);
        byte[] bArr = obj4 instanceof byte[] ? (byte[]) obj4 : null;
        Object obj5 = jSONObject.get("statusIconStyle");
        String str = obj5 instanceof String ? (String) obj5 : null;
        view.finishBarcodePickViewHighlightStyleCustomViewProviderViewForRequest(context, intValue, bArr, str != null ? BarcodePickStatusIconStyle.INSTANCE.fromJson(str) : null);
        result.success(null);
    }

    public final void finishBarcodePickViewHighlightStyleAsyncProviderStyleForRequest(HashMap<String, Object> response, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(result, "result");
        Object obj = response.get("viewId");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
        FrameworksBarcodePickView view = this.viewCache.getView(((Integer) obj).intValue());
        if (view == null) {
            result.success(null);
            return;
        }
        Object obj2 = response.get("requestId");
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Int");
        view.finishBarcodePickViewHighlightStyleAsyncProviderStyleForRequest(((Integer) obj2).intValue(), (String) response.get(Response.TYPE));
        result.success(null);
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle.Observer
    public void onActivityPause() {
        FrameworksBarcodePickView topMost = this.viewCache.getTopMost();
        if (topMost != null) {
            topMost.onPause();
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle.Observer
    public void onActivityResume() {
        FrameworksBarcodePickView topMost = this.viewCache.getTopMost();
        if (topMost != null) {
            topMost.onResume();
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle.Observer
    public void onActivityDestroy() {
        this.viewCache.disposeAll();
    }

    /* compiled from: BarcodePickModule.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/BarcodePickModule$Companion;", "", "()V", "create", "Lcom/scandit/datacapture/frameworks/barcode/pick/BarcodePickModule;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final BarcodePickModule create(Emitter emitter) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            return new BarcodePickModule(emitter, null, null, 6, null);
        }
    }
}
