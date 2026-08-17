package com.scandit.datacapture.frameworks.barcode.spark.ui;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.spark.capture.SparkScan;
import com.scandit.datacapture.barcode.spark.serialization.SparkScanDeserializer;
import com.scandit.datacapture.barcode.spark.serialization.SparkScanViewDeserializer;
import com.scandit.datacapture.barcode.spark.ui.SparkScanView;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.frameworks.barcode.spark.data.SparkScanViewCreationData;
import com.scandit.datacapture.frameworks.barcode.spark.delegates.FrameworksSparkScanFeedbackDelegate;
import com.scandit.datacapture.frameworks.barcode.spark.listeners.FrameworksSparkScanListener;
import com.scandit.datacapture.frameworks.barcode.spark.listeners.FrameworksSparkScanViewUiListener;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView;
import com.scandit.datacapture.frameworks.core.utils.DefaultMainThread;
import com.scandit.datacapture.frameworks.core.utils.MainThread;
import com.tealium.library.DataSources;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksSparkScanView.kt */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u0000 O2\u00020\u0001:\u0001OB=\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020\"J\u0006\u0010$\u001a\u00020\"J \u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0006\u0010,\u001a\u00020\"J\u000e\u0010-\u001a\u00020\"2\u0006\u0010.\u001a\u00020\u0010J\b\u0010/\u001a\u00020\"H\u0016J\u0006\u00100\u001a\u00020\"J\u0006\u00101\u001a\u00020\"J\u0006\u00102\u001a\u000203J\u0006\u00104\u001a\u00020\"J\u0006\u00105\u001a\u00020\"J\u000e\u00106\u001a\u00020\"2\u0006\u00107\u001a\u000203J\u000e\u00108\u001a\u00020\"2\u0006\u00107\u001a\u000203J\u0006\u00109\u001a\u00020\"J\u0010\u0010:\u001a\u00020\"2\u0006\u0010;\u001a\u00020+H\u0002J\u0010\u0010<\u001a\u00020\"2\u0006\u0010;\u001a\u00020+H\u0002J\u0006\u0010=\u001a\u00020\"J\u0006\u0010>\u001a\u00020\"J\u0006\u0010?\u001a\u00020\"J\u000e\u0010@\u001a\u00020\"2\u0006\u00107\u001a\u000203J\u000e\u0010A\u001a\u00020\"2\u0006\u0010B\u001a\u00020CJ\u0006\u0010D\u001a\u00020\"J\u000e\u0010E\u001a\u00020\"2\u0006\u0010F\u001a\u00020GJ\u0006\u0010H\u001a\u00020\"J\u0010\u0010I\u001a\u00020\"2\b\u0010J\u001a\u0004\u0018\u00010GJ\u000e\u0010K\u001a\u00020\"2\u0006\u0010L\u001a\u00020GJ\u000e\u0010M\u001a\u00020\"2\u0006\u0010N\u001a\u00020GR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u00020\u00178\u0006@\u0006X\u0087.¢\u0006\u0014\n\u0000\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006P"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/spark/ui/FrameworksSparkScanView;", "Lcom/scandit/datacapture/frameworks/core/ui/FrameworksBaseView;", "modeListener", "Lcom/scandit/datacapture/frameworks/barcode/spark/listeners/FrameworksSparkScanListener;", "viewListener", "Lcom/scandit/datacapture/frameworks/barcode/spark/listeners/FrameworksSparkScanViewUiListener;", "feedbackDelegate", "Lcom/scandit/datacapture/frameworks/barcode/spark/delegates/FrameworksSparkScanFeedbackDelegate;", "modeDeserializer", "Lcom/scandit/datacapture/barcode/spark/serialization/SparkScanDeserializer;", "viewDeserializer", "Lcom/scandit/datacapture/barcode/spark/serialization/SparkScanViewDeserializer;", "mainThread", "Lcom/scandit/datacapture/frameworks/core/utils/MainThread;", "(Lcom/scandit/datacapture/frameworks/barcode/spark/listeners/FrameworksSparkScanListener;Lcom/scandit/datacapture/frameworks/barcode/spark/listeners/FrameworksSparkScanViewUiListener;Lcom/scandit/datacapture/frameworks/barcode/spark/delegates/FrameworksSparkScanFeedbackDelegate;Lcom/scandit/datacapture/barcode/spark/serialization/SparkScanDeserializer;Lcom/scandit/datacapture/barcode/spark/serialization/SparkScanViewDeserializer;Lcom/scandit/datacapture/frameworks/core/utils/MainThread;)V", "_viewId", "", "mode", "Lcom/scandit/datacapture/barcode/spark/capture/SparkScan;", "parentId", "getParentId", "()Ljava/lang/Integer;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/barcode/spark/ui/SparkScanView;", "getView$annotations", "()V", "getView", "()Lcom/scandit/datacapture/barcode/spark/ui/SparkScanView;", "setView", "(Lcom/scandit/datacapture/barcode/spark/ui/SparkScanView;)V", "viewId", "getViewId", "()I", "addFeedbackDelegate", "", "addSparkScanViewUiListener", "cancelPendingEvents", "deserializeView", "context", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/View;", "viewCreationParams", "Lcom/scandit/datacapture/frameworks/barcode/spark/data/SparkScanViewCreationData;", "disableSparkScanListener", "dispatchWindowVisibilityChanged", "visibility", "dispose", "enableSparkScanListener", "hide", "isModeEnabled", "", "onActivityPause", "onActivityResume", "onFinishBarcodeScannedCallback", ExtentionsKt.ENABLED_KEY, "onFinishSessionUpdatedCallback", "pauseScanning", "postModeCreation", "creationData", "postViewCreation", "removeFeedbackDelegate", "removeSparkScanViewUiListener", "resetLastSession", "setModeEnabled", "setViewLayoutParams", "layoutParams", "Landroid/view/ViewGroup$LayoutParams;", "show", "showToast", "text", "", "startScanning", "submitFeedback", "feedbackJson", "updateMode", "modeJson", "updateView", "viewJson", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksSparkScanView implements FrameworksBaseView {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int _viewId;
    private final FrameworksSparkScanFeedbackDelegate feedbackDelegate;
    private final MainThread mainThread;
    private SparkScan mode;
    private final SparkScanDeserializer modeDeserializer;
    private final FrameworksSparkScanListener modeListener;
    public SparkScanView view;
    private final SparkScanViewDeserializer viewDeserializer;
    private final FrameworksSparkScanViewUiListener viewListener;

    public static /* synthetic */ void getView$annotations() {
    }

    @Override // com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView
    /* renamed from: getParentId */
    public Integer get_parentId() {
        return null;
    }

    private FrameworksSparkScanView(FrameworksSparkScanListener frameworksSparkScanListener, FrameworksSparkScanViewUiListener frameworksSparkScanViewUiListener, FrameworksSparkScanFeedbackDelegate frameworksSparkScanFeedbackDelegate, SparkScanDeserializer sparkScanDeserializer, SparkScanViewDeserializer sparkScanViewDeserializer, MainThread mainThread) {
        this.modeListener = frameworksSparkScanListener;
        this.viewListener = frameworksSparkScanViewUiListener;
        this.feedbackDelegate = frameworksSparkScanFeedbackDelegate;
        this.modeDeserializer = sparkScanDeserializer;
        this.viewDeserializer = sparkScanViewDeserializer;
        this.mainThread = mainThread;
    }

    /* synthetic */ FrameworksSparkScanView(FrameworksSparkScanListener frameworksSparkScanListener, FrameworksSparkScanViewUiListener frameworksSparkScanViewUiListener, FrameworksSparkScanFeedbackDelegate frameworksSparkScanFeedbackDelegate, SparkScanDeserializer sparkScanDeserializer, SparkScanViewDeserializer sparkScanViewDeserializer, DefaultMainThread defaultMainThread, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(frameworksSparkScanListener, frameworksSparkScanViewUiListener, frameworksSparkScanFeedbackDelegate, (i & 8) != 0 ? new SparkScanDeserializer() : sparkScanDeserializer, (i & 16) != 0 ? new SparkScanViewDeserializer() : sparkScanViewDeserializer, (i & 32) != 0 ? DefaultMainThread.INSTANCE.getInstance() : defaultMainThread);
    }

    @Override // com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView
    /* renamed from: getViewId, reason: from getter */
    public int get_viewId() {
        return this._viewId;
    }

    public final SparkScanView getView() {
        SparkScanView sparkScanView = this.view;
        if (sparkScanView != null) {
            return sparkScanView;
        }
        Intrinsics.throwUninitializedPropertyAccessException(DataSources.EventTypeValue.VIEW_EVENT_TYPE);
        return null;
    }

    public final void setView(SparkScanView sparkScanView) {
        Intrinsics.checkNotNullParameter(sparkScanView, "<set-?>");
        this.view = sparkScanView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deserializeView(DataCaptureContext context, View container, SparkScanViewCreationData viewCreationParams) {
        this._viewId = viewCreationParams.getViewId();
        SparkScan modeFromJson = this.modeDeserializer.modeFromJson(viewCreationParams.getModeJson());
        this.mode = modeFromJson;
        SparkScanViewDeserializer sparkScanViewDeserializer = this.viewDeserializer;
        if (modeFromJson == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            modeFromJson = null;
        }
        setView(sparkScanViewDeserializer.viewFrom(container, modeFromJson, context, viewCreationParams.getViewJson()));
        getView().setTag(Integer.valueOf(get_viewId()));
        postModeCreation(viewCreationParams);
        postViewCreation(viewCreationParams);
        getView().onResume();
        getView().bringToFront();
    }

    private final void postViewCreation(SparkScanViewCreationData creationData) {
        if (creationData.getHasUIListener()) {
            getView().setListener(this.viewListener);
        } else {
            getView().setListener(null);
        }
        if (creationData.getHasFeedbackDelegate()) {
            getView().setFeedbackDelegate(this.feedbackDelegate);
        } else {
            getView().setFeedbackDelegate(null);
        }
    }

    private final void postModeCreation(SparkScanViewCreationData creationData) {
        SparkScan sparkScan = null;
        if (creationData.getHasModeListener()) {
            SparkScan sparkScan2 = this.mode;
            if (sparkScan2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mode");
                sparkScan2 = null;
            }
            sparkScan2.removeListener(this.modeListener);
            SparkScan sparkScan3 = this.mode;
            if (sparkScan3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mode");
                sparkScan3 = null;
            }
            sparkScan3.addListener(this.modeListener);
            this.modeListener.enable();
        } else {
            SparkScan sparkScan4 = this.mode;
            if (sparkScan4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mode");
                sparkScan4 = null;
            }
            sparkScan4.removeListener(this.modeListener);
            this.modeListener.disable();
        }
        SparkScan sparkScan5 = this.mode;
        if (sparkScan5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
        } else {
            sparkScan = sparkScan5;
        }
        sparkScan.setEnabled(creationData.getIsModeEnabled());
    }

    public final void updateView(String viewJson) {
        Intrinsics.checkNotNullParameter(viewJson, "viewJson");
        final SparkScanViewCreationData fromJson = SparkScanViewCreationData.INSTANCE.fromJson(viewJson);
        getView().post(new Runnable() { // from class: com.scandit.datacapture.frameworks.barcode.spark.ui.FrameworksSparkScanView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FrameworksSparkScanView.updateView$lambda$0(FrameworksSparkScanView.this, fromJson);
            }
        });
        postViewCreation(fromJson);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateView$lambda$0(FrameworksSparkScanView this$0, SparkScanViewCreationData updateParams) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(updateParams, "$updateParams");
        this$0.viewDeserializer.updateViewFromJson(this$0.getView(), updateParams.getViewJson());
    }

    public final void submitFeedback(String feedbackJson) {
        this.feedbackDelegate.submitFeedback(feedbackJson);
    }

    public final void enableSparkScanListener() {
        this.modeListener.enable();
    }

    public final void disableSparkScanListener() {
        this.modeListener.disable();
    }

    @Override // com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView
    public void dispose() {
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.spark.ui.FrameworksSparkScanView$dispose$1
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
                FrameworksSparkScanListener frameworksSparkScanListener;
                SparkScan sparkScan;
                FrameworksSparkScanListener frameworksSparkScanListener2;
                frameworksSparkScanListener = FrameworksSparkScanView.this.modeListener;
                frameworksSparkScanListener.disable();
                sparkScan = FrameworksSparkScanView.this.mode;
                if (sparkScan == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mode");
                    sparkScan = null;
                }
                frameworksSparkScanListener2 = FrameworksSparkScanView.this.modeListener;
                sparkScan.removeListener(frameworksSparkScanListener2);
                FrameworksSparkScanView.this.getView().setListener(null);
                FrameworksSparkScanView.this.getView().pauseScanning();
                FrameworksSparkScanView.this.getView().onPause();
                FrameworksSparkScanView.this.getView().setFeedbackDelegate(null);
                ViewParent parent = FrameworksSparkScanView.this.getView().getParent();
                ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                if (viewGroup != null) {
                    viewGroup.removeView(FrameworksSparkScanView.this.getView());
                }
            }
        });
    }

    public final void onActivityPause() {
        getView().onPause();
    }

    public final void onActivityResume() {
        getView().onResume();
    }

    public final void startScanning() {
        getView().startScanning();
    }

    public final void pauseScanning() {
        getView().pauseScanning();
    }

    public final void updateMode(String modeJson) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        SparkScanDeserializer sparkScanDeserializer = this.modeDeserializer;
        SparkScan sparkScan = this.mode;
        if (sparkScan == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            sparkScan = null;
        }
        sparkScanDeserializer.updateModeFromJson(sparkScan, modeJson);
        postModeCreation(SparkScanViewCreationData.INSTANCE.fromJson(modeJson));
    }

    public final void onFinishSessionUpdatedCallback(boolean enabled) {
        this.modeListener.onFinishSessionUpdatedCallback(enabled);
    }

    public final void onFinishBarcodeScannedCallback(boolean enabled) {
        this.modeListener.onFinishBarcodeScannedCallback(enabled);
    }

    public final void resetLastSession() {
        this.modeListener.resetLastSession();
    }

    public final void addSparkScanViewUiListener() {
        getView().setListener(this.viewListener);
    }

    public final void removeSparkScanViewUiListener() {
        getView().setListener(null);
    }

    public final void showToast(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        getView().showToast(text);
    }

    public final void setModeEnabled(boolean enabled) {
        SparkScan sparkScan = this.mode;
        if (sparkScan == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            sparkScan = null;
        }
        sparkScan.setEnabled(enabled);
    }

    public final boolean isModeEnabled() {
        SparkScan sparkScan = this.mode;
        if (sparkScan == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            sparkScan = null;
        }
        return sparkScan.isEnabled();
    }

    public final void setViewLayoutParams(final ViewGroup.LayoutParams layoutParams) {
        Intrinsics.checkNotNullParameter(layoutParams, "layoutParams");
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.spark.ui.FrameworksSparkScanView$setViewLayoutParams$1
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
                FrameworksSparkScanView.this.getView().setLayoutParams(layoutParams);
                FrameworksSparkScanView.this.getView().requestLayout();
            }
        });
    }

    public final void dispatchWindowVisibilityChanged(int visibility) {
        getView().dispatchWindowVisibilityChanged(visibility);
    }

    public final void hide() {
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.spark.ui.FrameworksSparkScanView$hide$1
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
                FrameworksSparkScanView.this.getView().setVisibility(8);
                FrameworksSparkScanView.this.getView().onPause();
            }
        });
    }

    public final void show() {
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.spark.ui.FrameworksSparkScanView$show$1
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
                FrameworksSparkScanView.this.getView().setVisibility(0);
                FrameworksSparkScanView.this.getView().bringToFront();
                FrameworksSparkScanView.this.getView().onResume();
            }
        });
    }

    public final void addFeedbackDelegate() {
        getView().setFeedbackDelegate(this.feedbackDelegate);
    }

    public final void removeFeedbackDelegate() {
        getView().setFeedbackDelegate(null);
    }

    public final void cancelPendingEvents() {
        this.modeListener.reset();
    }

    /* compiled from: FrameworksSparkScanView.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/spark/ui/FrameworksSparkScanView$Companion;", "", "()V", "create", "Lcom/scandit/datacapture/frameworks/barcode/spark/ui/FrameworksSparkScanView;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "context", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/View;", "viewCreationParams", "Lcom/scandit/datacapture/frameworks/barcode/spark/data/SparkScanViewCreationData;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FrameworksSparkScanView create(Emitter emitter, DataCaptureContext context, View container, SparkScanViewCreationData viewCreationParams) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(container, "container");
            Intrinsics.checkNotNullParameter(viewCreationParams, "viewCreationParams");
            FrameworksSparkScanListener frameworksSparkScanListener = new FrameworksSparkScanListener(emitter, viewCreationParams.getViewId(), null, null, null, null, 60, null);
            FrameworksSparkScanViewUiListener frameworksSparkScanViewUiListener = new FrameworksSparkScanViewUiListener(emitter, viewCreationParams.getViewId());
            FrameworksSparkScanFeedbackDelegate frameworksSparkScanFeedbackDelegate = new FrameworksSparkScanFeedbackDelegate(emitter, viewCreationParams.getViewId(), null, 4, null);
            SparkScanDeserializer sparkScanDeserializer = null;
            SparkScanViewDeserializer sparkScanViewDeserializer = null;
            FrameworksSparkScanView frameworksSparkScanView = new FrameworksSparkScanView(frameworksSparkScanListener, frameworksSparkScanViewUiListener, frameworksSparkScanFeedbackDelegate, sparkScanDeserializer, sparkScanViewDeserializer, null, 56, null);
            frameworksSparkScanView.deserializeView(context, container, viewCreationParams);
            return frameworksSparkScanView;
        }
    }
}
