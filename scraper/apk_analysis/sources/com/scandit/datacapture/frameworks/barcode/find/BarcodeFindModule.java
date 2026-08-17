package com.scandit.datacapture.frameworks.barcode.find;

import android.content.Context;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.find.ui.BarcodeFindView;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.frameworks.barcode.find.data.BarcodeFindViewCreationData;
import com.scandit.datacapture.frameworks.barcode.find.data.defaults.BarcodeFindDefaults;
import com.scandit.datacapture.frameworks.barcode.find.ui.FrameworksBarcodeFindView;
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
import com.scandit.datacapture.frameworks.core.ui.FrameworksViewsCache;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: BarcodeFindModule.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0019\b\u0016\u0018\u0000 I2\u00020\u0001:\u0001IB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u001e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0014\u001a\u00020\u0015J\u0014\u0010 \u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0006\u0012\u0004\u0018\u00010\"0!J)\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010'J\u000e\u0010(\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010)\u001a\u00020\u001dJ\u0016\u0010*\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010+\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010,\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010-\u001a\u00020\u0011H\u0016J\b\u0010.\u001a\u00020\u0011H\u0016J\u0010\u0010/\u001a\u00020\u00112\u0006\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u00020\u0011H\u0016J\u0016\u00103\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u00104\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u00105\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u001e\u00106\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u00107\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u00108\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u00109\u001a\u00020\u001dJ\u000e\u0010:\u001a\u00020\u00112\u0006\u00109\u001a\u00020\u001dJ\u000e\u0010;\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J \u0010<\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010=\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010>\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u001e\u0010?\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010@\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u0015J\u001e\u0010A\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010B\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u0015J\u001e\u0010C\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010D\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010E\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010F\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010G\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010H\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006J"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/find/BarcodeFindModule;", "Lcom/scandit/datacapture/frameworks/core/common/BaseFrameworkModule;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "captureContext", "Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;", "deserializationLifecycleObserver", "Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;Lcom/scandit/datacapture/frameworks/core/context/FrameworksCaptureContext;Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;)V", "viewCache", "Lcom/scandit/datacapture/frameworks/core/ui/FrameworksViewsCache;", "Lcom/scandit/datacapture/frameworks/barcode/find/ui/FrameworksBarcodeFindView;", "getViewCache$annotations", "()V", "getViewCache", "()Lcom/scandit/datacapture/frameworks/core/ui/FrameworksViewsCache;", "addBarcodeFindListener", "", "viewId", "", "result", "Lcom/scandit/datacapture/frameworks/core/result/FrameworksResult;", "addBarcodeFindViewListener", "addViewToContainer", "containerView", "Landroid/view/View;", "jsonString", "", "execute", "", "method", "Lcom/scandit/datacapture/frameworks/core/method/FrameworksMethodCall;", "getDefaults", "", "", "getView", "Lkotlin/Result;", "Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindView;", "getView-gIAlu-s", "(Landroid/view/View;Ljava/lang/String;)Ljava/lang/Object;", "hideView", "isModeEnabled", "modePause", "modeStart", "modeStop", "onActivityPause", "onActivityResume", "onCreate", "context", "Landroid/content/Context;", "onDestroy", "removeBarcodeFindListener", "removeBarcodeFindViewListener", "setBarcodeFindTransformer", "setItemList", "barcodeFindItemsJson", "setModeEnabled", ExtentionsKt.ENABLED_KEY, "setTopMostModeEnabled", "showView", "submitBarcodeFindTransformerResult", "transformedData", "unsetBarcodeFindTransformer", "updateBarcodeFindMode", "modeJson", "updateBarcodeFindView", "viewJson", "updateFeedback", "feedbackJson", "viewDisposed", "viewPauseSearching", "viewStartSearching", "viewStopSearching", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class BarcodeFindModule extends BaseFrameworkModule {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FrameworksCaptureContext captureContext;
    private final Emitter emitter;
    private final FrameworksViewsCache<FrameworksBarcodeFindView> viewCache;

    @JvmStatic
    public static final BarcodeFindModule create(Emitter emitter) {
        return INSTANCE.create(emitter);
    }

    public static /* synthetic */ void getViewCache$annotations() {
    }

    public /* synthetic */ BarcodeFindModule(Emitter emitter, DefaultFrameworksCaptureContext defaultFrameworksCaptureContext, DefaultDeserializationLifecycleObserver defaultDeserializationLifecycleObserver, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(emitter, (i & 2) != 0 ? DefaultFrameworksCaptureContext.INSTANCE.getInstance() : defaultFrameworksCaptureContext, (i & 4) != 0 ? DefaultDeserializationLifecycleObserver.INSTANCE.getInstance() : defaultDeserializationLifecycleObserver);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BarcodeFindModule(Emitter emitter, FrameworksCaptureContext captureContext, DeserializationLifecycleObserver deserializationLifecycleObserver) {
        super(deserializationLifecycleObserver, null, 2, null);
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        Intrinsics.checkNotNullParameter(captureContext, "captureContext");
        Intrinsics.checkNotNullParameter(deserializationLifecycleObserver, "deserializationLifecycleObserver");
        this.emitter = emitter;
        this.captureContext = captureContext;
        this.viewCache = new FrameworksViewsCache<>();
    }

    public final FrameworksViewsCache<FrameworksBarcodeFindView> getViewCache() {
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
        super.onDestroy();
        detachForActivityLifecycleEvents();
        this.viewCache.disposeAll();
    }

    public final Map<String, Object> getDefaults() {
        return BarcodeFindDefaults.INSTANCE.get();
    }

    /* renamed from: getView-gIAlu-s, reason: not valid java name */
    public final Object m3787getViewgIAlus(View containerView, String jsonString) {
        Intrinsics.checkNotNullParameter(containerView, "containerView");
        Intrinsics.checkNotNullParameter(jsonString, "jsonString");
        DataCaptureContext context = this.captureContext.getContext();
        if (context == null) {
            Result.Companion companion = Result.INSTANCE;
            return Result.m3945constructorimpl(ResultKt.createFailure(new DataCaptureContextNotInitialized()));
        }
        try {
            BarcodeFindViewCreationData fromJson = BarcodeFindViewCreationData.INSTANCE.fromJson(jsonString);
            FrameworksBarcodeFindView view = this.viewCache.getView(fromJson.getViewId());
            if (view != null) {
                view.dispose();
                this.viewCache.remove(view.get_viewId());
            }
            FrameworksBarcodeFindView topMost = this.viewCache.getTopMost();
            if (topMost != null) {
                topMost.hide();
            }
            FrameworksBarcodeFindView create = FrameworksBarcodeFindView.INSTANCE.create(this.emitter, context, containerView, fromJson);
            this.viewCache.addView(create);
            Iterator<Function0<Unit>> it = getPostSpecificViewCreationActions(create.get_viewId()).iterator();
            while (it.hasNext()) {
                it.next().invoke();
            }
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m3945constructorimpl(create.getView());
        } catch (Exception e) {
            Result.Companion companion3 = Result.INSTANCE;
            StringBuilder sb = new StringBuilder("Error during the barcode find view deserialization. Error: ");
            String message = e.getMessage();
            if (message == null) {
                message = "unknown";
            }
            sb.append(message);
            return Result.m3945constructorimpl(ResultKt.createFailure(new Throwable(sb.toString())));
        }
    }

    public final int addViewToContainer(View containerView, String jsonString, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(containerView, "containerView");
        Intrinsics.checkNotNullParameter(jsonString, "jsonString");
        Intrinsics.checkNotNullParameter(result, "result");
        Object m3787getViewgIAlus = m3787getViewgIAlus(containerView, jsonString);
        if (Result.m3951isFailureimpl(m3787getViewgIAlus)) {
            Throwable m3948exceptionOrNullimpl = Result.m3948exceptionOrNullimpl(m3787getViewgIAlus);
            if (m3948exceptionOrNullimpl == null) {
                m3948exceptionOrNullimpl = new Throwable("Unable to create the view from the json " + jsonString + ' ');
            }
            ExtentionsKt.reject(result, m3948exceptionOrNullimpl);
            return -1;
        }
        result.success(null);
        ResultKt.throwOnFailure(m3787getViewgIAlus);
        Object tag = ((BarcodeFindView) m3787getViewgIAlus).getTag();
        Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type kotlin.Int");
        return ((Integer) tag).intValue();
    }

    public final void updateBarcodeFindView(final int viewId, final String viewJson, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(viewJson, "viewJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.find.BarcodeFindModule$updateBarcodeFindView$viewInstance$1
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
                    BarcodeFindModule.this.updateBarcodeFindView(viewId, viewJson, result);
                }
            });
        } else {
            view.updateBarcodeFindView(viewJson);
            result.success(null);
        }
    }

    public final void updateBarcodeFindMode(final int viewId, final String modeJson, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.find.BarcodeFindModule$updateBarcodeFindMode$viewInstance$1
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
                    BarcodeFindModule.this.updateBarcodeFindMode(viewId, modeJson, result);
                }
            });
        } else {
            view.updateBarcodeFindMode(modeJson);
            result.success(null);
        }
    }

    public final void addBarcodeFindListener(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.addBarcodeFindListener();
            result.success(null);
        }
    }

    public final void removeBarcodeFindListener(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.removeBarcodeFindListener();
            result.success(null);
        }
    }

    public final void addBarcodeFindViewListener(final int viewId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.find.BarcodeFindModule$addBarcodeFindViewListener$viewInstance$1
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
                    BarcodeFindModule.this.addBarcodeFindViewListener(viewId, result);
                }
            });
        } else {
            view.addBarcodeFindViewListener();
            result.success(null);
        }
    }

    public final void removeBarcodeFindViewListener(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.removeBarcodeFindViewListener();
        result.success(null);
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle.Observer
    public void onActivityResume() {
        FrameworksBarcodeFindView topMost = this.viewCache.getTopMost();
        if (topMost != null) {
            topMost.viewOnResume();
        }
    }

    @Override // com.scandit.datacapture.frameworks.core.common.BaseFrameworkModule, com.scandit.datacapture.frameworks.core.lifecycle.ActivityLifecycle.Observer
    public void onActivityPause() {
        FrameworksBarcodeFindView topMost = this.viewCache.getTopMost();
        if (topMost != null) {
            topMost.viewOnPause();
        }
    }

    public final void setItemList(final int viewId, final String barcodeFindItemsJson, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(barcodeFindItemsJson, "barcodeFindItemsJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.find.BarcodeFindModule$setItemList$viewInstance$1
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
                    BarcodeFindModule.this.setItemList(viewId, barcodeFindItemsJson, result);
                }
            });
            return;
        }
        try {
            view.setItemList(barcodeFindItemsJson);
            result.success(null);
        } catch (Exception e) {
            ExtentionsKt.reject(result, e);
        }
    }

    public final void viewStopSearching(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.viewStopSearching();
            result.success(null);
        }
    }

    public final void viewStartSearching(final int viewId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.find.BarcodeFindModule$viewStartSearching$viewInstance$1
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
                    BarcodeFindModule.this.viewStartSearching(viewId, result);
                }
            });
        } else {
            view.viewStartSearching();
            result.success(null);
        }
    }

    public final void viewPauseSearching(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.viewPauseSearching();
            result.success(null);
        }
    }

    public final void modeStart(final int viewId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.find.BarcodeFindModule$modeStart$viewInstance$1
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
                    BarcodeFindModule.this.modeStart(viewId, result);
                }
            });
        } else {
            view.modeStart();
            result.success(null);
        }
    }

    public final void modeStop(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.modeStop();
            result.success(null);
        }
    }

    public final void modePause(int viewId, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.modePause();
            result.success(null);
        }
    }

    public final void setModeEnabled(int viewId, boolean enabled) {
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.setModeEnabled(enabled);
    }

    public final void setTopMostModeEnabled(boolean enabled) {
        FrameworksBarcodeFindView topMost = this.viewCache.getTopMost();
        if (topMost != null) {
            topMost.setModeEnabled(enabled);
        }
    }

    public final boolean isModeEnabled() {
        FrameworksBarcodeFindView topMost = this.viewCache.getTopMost();
        return topMost != null && topMost.isModeEnabled();
    }

    public final void viewDisposed(int viewId) {
        FrameworksBarcodeFindView remove = this.viewCache.remove(viewId);
        if (remove != null) {
            remove.dispose();
        }
        clearPostSpecificViewCreationActions(viewId);
        FrameworksBarcodeFindView topMost = this.viewCache.getTopMost();
        if (topMost != null) {
            topMost.show();
        }
    }

    public final void setBarcodeFindTransformer(final int viewId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.find.BarcodeFindModule$setBarcodeFindTransformer$viewInstance$1
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
                    BarcodeFindModule.this.setBarcodeFindTransformer(viewId, result);
                }
            });
        } else {
            view.setBarcodeFindTransformer();
            result.success(null);
        }
    }

    public final void unsetBarcodeFindTransformer(final int viewId, final FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            addPostSpecificViewCreationAction(viewId, new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.find.BarcodeFindModule$unsetBarcodeFindTransformer$viewInstance$1
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
                    BarcodeFindModule.this.setBarcodeFindTransformer(viewId, result);
                }
            });
        } else {
            view.unsetBarcodeFindTransformer();
            result.success(null);
        }
    }

    public final void submitBarcodeFindTransformerResult(int viewId, String transformedData, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
        } else {
            view.submitBarcodeFindTransformerResult(transformedData);
            result.success(null);
        }
    }

    public final void updateFeedback(int viewId, String feedbackJson, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(feedbackJson, "feedbackJson");
        Intrinsics.checkNotNullParameter(result, "result");
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            result.success(null);
            return;
        }
        try {
            view.updateFeedback(feedbackJson);
            result.success(null);
        } catch (Exception e) {
            ExtentionsKt.reject(result, e);
        }
    }

    public final void showView(int viewId) {
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.show();
    }

    public final void hideView(int viewId) {
        FrameworksBarcodeFindView view = this.viewCache.getView(viewId);
        if (view == null) {
            return;
        }
        view.hide();
    }

    public final boolean execute(FrameworksMethodCall method, FrameworksResult result) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(result, "result");
        String method2 = method.getMethod();
        if (method2 == null) {
            return false;
        }
        switch (method2.hashCode()) {
            case -2079043398:
                if (!method2.equals("barcodeFindSetItemList")) {
                    return false;
                }
                setItemList(((Number) method.argument("viewId")).intValue(), (String) method.argument("itemsJson"), result);
                return true;
            case -1985009014:
                if (!method2.equals("registerBarcodeFindListener")) {
                    return false;
                }
                addBarcodeFindListener(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case -1785120322:
                if (!method2.equals("barcodeFindModeStop")) {
                    return false;
                }
                modeStop(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case -1510601694:
                if (!method2.equals("barcodeFindViewPauseSearching")) {
                    return false;
                }
                viewPauseSearching(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case -1120493445:
                if (!method2.equals("setBarcodeTransformer")) {
                    return false;
                }
                setBarcodeFindTransformer(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case -452737967:
                if (!method2.equals("unregisterBarcodeFindListener")) {
                    return false;
                }
                removeBarcodeFindListener(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case -123101350:
                if (!method2.equals("barcodeFindViewStopSearching")) {
                    return false;
                }
                viewStopSearching(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case 168786255:
                if (!method2.equals("registerBarcodeFindViewListener")) {
                    return false;
                }
                addBarcodeFindViewListener(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case 190767845:
                if (!method2.equals("updateFindMode")) {
                    return false;
                }
                updateBarcodeFindMode(((Number) method.argument("viewId")).intValue(), (String) method.argument("barcodeFindJson"), result);
                return true;
            case 191030247:
                if (!method2.equals("updateFindView")) {
                    return false;
                }
                updateBarcodeFindView(((Number) method.argument("viewId")).intValue(), (String) method.argument("barcodeFindViewJson"), result);
                return true;
            case 308533142:
                if (!method2.equals("unregisterBarcodeFindViewListener")) {
                    return false;
                }
                removeBarcodeFindViewListener(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case 492514234:
                if (!method2.equals("barcodeFindModePause")) {
                    return false;
                }
                modePause(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case 495831590:
                if (!method2.equals("barcodeFindModeStart")) {
                    return false;
                }
                modeStart(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case 550561590:
                if (!method2.equals("barcodeFindViewStartSearching")) {
                    return false;
                }
                viewStartSearching(((Number) method.argument("viewId")).intValue(), result);
                return true;
            case 678670190:
                if (!method2.equals("updateFeedback")) {
                    return false;
                }
                updateFeedback(((Number) method.argument("viewId")).intValue(), (String) method.argument("feedbackJson"), result);
                return true;
            case 958454933:
                if (!method2.equals("getBarcodeFindDefaults")) {
                    return false;
                }
                result.success(new JSONObject(getDefaults()).toString());
                return true;
            case 1641371541:
                if (!method2.equals("setModeEnabledState")) {
                    return false;
                }
                setModeEnabled(((Number) method.argument("viewId")).intValue(), ((Boolean) method.argument(ExtentionsKt.ENABLED_KEY)).booleanValue());
                result.success(null);
                return true;
            case 1822953006:
                if (!method2.equals("submitBarcodeTransformerResult")) {
                    return false;
                }
                submitBarcodeFindTransformerResult(((Number) method.argument("viewId")).intValue(), (String) method.argument("transformedBarcode"), result);
                return true;
            default:
                return false;
        }
    }

    /* compiled from: BarcodeFindModule.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/find/BarcodeFindModule$Companion;", "", "()V", "create", "Lcom/scandit/datacapture/frameworks/barcode/find/BarcodeFindModule;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final BarcodeFindModule create(Emitter emitter) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            return new BarcodeFindModule(emitter, null, null, 6, null);
        }
    }
}
