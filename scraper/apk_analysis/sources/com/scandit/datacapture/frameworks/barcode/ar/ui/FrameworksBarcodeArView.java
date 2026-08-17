package com.scandit.datacapture.frameworks.barcode.ar.ui;

import android.content.Context;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.analytics.stats.b;
import com.scandit.datacapture.barcode.ar.capture.BarcodeAr;
import com.scandit.datacapture.barcode.ar.capture.BarcodeArSettings;
import com.scandit.datacapture.barcode.ar.feedback.BarcodeArFeedback;
import com.scandit.datacapture.barcode.ar.serialization.BarcodeArDeserializer;
import com.scandit.datacapture.barcode.ar.serialization.BarcodeArViewDeserializer;
import com.scandit.datacapture.barcode.ar.ui.BarcodeArView;
import com.scandit.datacapture.barcode.internal.sdk.feedback.BarcodeArFeedbackDeserializer;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.frameworks.barcode.ar.caches.BarcodeArAugmentationsCache;
import com.scandit.datacapture.frameworks.barcode.ar.data.BarcodeArViewCreationData;
import com.scandit.datacapture.frameworks.barcode.ar.listeners.FrameworksBarcodeArAnnotationProvider;
import com.scandit.datacapture.frameworks.barcode.ar.listeners.FrameworksBarcodeArHighlightProvider;
import com.scandit.datacapture.frameworks.barcode.ar.listeners.FrameworksBarcodeArInfoAnnotationListener;
import com.scandit.datacapture.frameworks.barcode.ar.listeners.FrameworksBarcodeArListener;
import com.scandit.datacapture.frameworks.barcode.ar.listeners.FrameworksBarcodeArPopoverAnnotationListener;
import com.scandit.datacapture.frameworks.barcode.ar.listeners.FrameworksBarcodeArViewUiListener;
import com.scandit.datacapture.frameworks.barcode.ar.parsers.BarcodeArAnnotationParser;
import com.scandit.datacapture.frameworks.barcode.ar.parsers.BarcodeArHighlightParser;
import com.scandit.datacapture.frameworks.core.events.Emitter;
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

/* compiled from: FrameworksBarcodeArView.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001f\u0018\u0000 T2\u00020\u0001:\u0001TBC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u0006\u0010$\u001a\u00020%J\u0006\u0010&\u001a\u00020%J\u0006\u0010'\u001a\u00020%J\u0006\u0010(\u001a\u00020%J\u000e\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020+J \u0010,\u001a\u00020%2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0002J\b\u00103\u001a\u00020%H\u0016J\u0016\u00104\u001a\u00020%2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020+J\u0006\u00108\u001a\u00020%J\u0016\u00109\u001a\u00020%2\u0006\u00105\u001a\u0002062\u0006\u0010:\u001a\u00020+J\u0006\u0010;\u001a\u00020%J\u0010\u0010<\u001a\u00020%2\u0006\u0010=\u001a\u000202H\u0002J\u0010\u0010>\u001a\u00020%2\u0006\u0010=\u001a\u000202H\u0002J\u0006\u0010?\u001a\u00020%J\u0006\u0010@\u001a\u00020%J\u0006\u0010A\u001a\u00020%J\u0006\u0010B\u001a\u00020%J\u0006\u0010C\u001a\u00020%J\u0006\u0010D\u001a\u00020%J\u0006\u0010E\u001a\u00020%J\u0006\u0010F\u001a\u00020%J\u0006\u0010G\u001a\u00020%J\u000e\u0010H\u001a\u00020%2\u0006\u0010I\u001a\u00020+J\u000e\u0010J\u001a\u00020%2\u0006\u0010K\u001a\u00020+J\u000e\u0010L\u001a\u00020%2\u0006\u0010M\u001a\u00020+J\u000e\u0010N\u001a\u00020%2\u0006\u0010O\u001a\u00020+J\u000e\u0010P\u001a\u00020%2\u0006\u0010Q\u001a\u00020+J\u000e\u0010R\u001a\u00020%2\u0006\u0010S\u001a\u00020+R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\u0004\u0018\u00010\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006U"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/ui/FrameworksBarcodeArView;", "Lcom/scandit/datacapture/frameworks/core/ui/FrameworksBaseView;", "barcodeArListener", "Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArListener;", "viewUiListener", "Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArViewUiListener;", "highlightProvider", "Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArHighlightProvider;", "annotationProvider", "Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArAnnotationProvider;", "modeDeserializer", "Lcom/scandit/datacapture/barcode/ar/serialization/BarcodeArDeserializer;", "viewDeserializer", "Lcom/scandit/datacapture/barcode/ar/serialization/BarcodeArViewDeserializer;", "mainThread", "Lcom/scandit/datacapture/frameworks/core/utils/MainThread;", "(Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArListener;Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArViewUiListener;Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArHighlightProvider;Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArAnnotationProvider;Lcom/scandit/datacapture/barcode/ar/serialization/BarcodeArDeserializer;Lcom/scandit/datacapture/barcode/ar/serialization/BarcodeArViewDeserializer;Lcom/scandit/datacapture/frameworks/core/utils/MainThread;)V", "_viewId", "", "cache", "Lcom/scandit/datacapture/frameworks/barcode/ar/caches/BarcodeArAugmentationsCache;", "getCache", "()Lcom/scandit/datacapture/frameworks/barcode/ar/caches/BarcodeArAugmentationsCache;", "mode", "Lcom/scandit/datacapture/barcode/ar/capture/BarcodeAr;", "parentId", "getParentId", "()Ljava/lang/Integer;", "<set-?>", "Lcom/scandit/datacapture/barcode/ar/ui/BarcodeArView;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "getView", "()Lcom/scandit/datacapture/barcode/ar/ui/BarcodeArView;", "viewId", "getViewId", "()I", "addBarcodeArAnnotationProvider", "", "addBarcodeArHighlightProvider", "addBarcodeArListener", "addBarcodeArViewUiListener", "applySettings", "settingsJson", "", "deserializeView", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "dataCaptureContext", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "viewCreationParams", "Lcom/scandit/datacapture/frameworks/barcode/ar/data/BarcodeArViewCreationData;", "dispose", "finishAnnotationForBarcode", "context", "Landroid/content/Context;", "barcodeResponse", "finishDidUpdateSession", "finishHighlightForBarcode", "highlightResponse", "hide", "postModeChanges", "changeParams", "postViewChanges", "removeBarcodeArAnnotationProvider", "removeBarcodeArHighlightProvider", "removeBarcodeArListener", "removeBarcodeArViewUiListener", "reset", "resetSession", "show", "start", "stop", "updateAnnotation", "annotationJson", "updateBarcodeArPopoverButtonAtIndex", "updateJson", "updateFeedback", "feedbackJson", "updateHighlight", "highlightJson", "updateMode", "modeJson", "updateView", "viewJson", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksBarcodeArView implements FrameworksBaseView {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int _viewId;
    private final FrameworksBarcodeArAnnotationProvider annotationProvider;
    private final FrameworksBarcodeArListener barcodeArListener;
    private final FrameworksBarcodeArHighlightProvider highlightProvider;
    private final MainThread mainThread;
    private BarcodeAr mode;
    private final BarcodeArDeserializer modeDeserializer;
    private BarcodeArView view;
    private final BarcodeArViewDeserializer viewDeserializer;
    private final FrameworksBarcodeArViewUiListener viewUiListener;

    @Override // com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView
    /* renamed from: getParentId */
    public Integer get_parentId() {
        return null;
    }

    public FrameworksBarcodeArView(FrameworksBarcodeArListener barcodeArListener, FrameworksBarcodeArViewUiListener viewUiListener, FrameworksBarcodeArHighlightProvider highlightProvider, FrameworksBarcodeArAnnotationProvider annotationProvider, BarcodeArDeserializer modeDeserializer, BarcodeArViewDeserializer viewDeserializer, MainThread mainThread) {
        Intrinsics.checkNotNullParameter(barcodeArListener, "barcodeArListener");
        Intrinsics.checkNotNullParameter(viewUiListener, "viewUiListener");
        Intrinsics.checkNotNullParameter(highlightProvider, "highlightProvider");
        Intrinsics.checkNotNullParameter(annotationProvider, "annotationProvider");
        Intrinsics.checkNotNullParameter(modeDeserializer, "modeDeserializer");
        Intrinsics.checkNotNullParameter(viewDeserializer, "viewDeserializer");
        Intrinsics.checkNotNullParameter(mainThread, "mainThread");
        this.barcodeArListener = barcodeArListener;
        this.viewUiListener = viewUiListener;
        this.highlightProvider = highlightProvider;
        this.annotationProvider = annotationProvider;
        this.modeDeserializer = modeDeserializer;
        this.viewDeserializer = viewDeserializer;
        this.mainThread = mainThread;
    }

    public /* synthetic */ FrameworksBarcodeArView(FrameworksBarcodeArListener frameworksBarcodeArListener, FrameworksBarcodeArViewUiListener frameworksBarcodeArViewUiListener, FrameworksBarcodeArHighlightProvider frameworksBarcodeArHighlightProvider, FrameworksBarcodeArAnnotationProvider frameworksBarcodeArAnnotationProvider, BarcodeArDeserializer barcodeArDeserializer, BarcodeArViewDeserializer barcodeArViewDeserializer, MainThread mainThread, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(frameworksBarcodeArListener, frameworksBarcodeArViewUiListener, frameworksBarcodeArHighlightProvider, frameworksBarcodeArAnnotationProvider, (i & 16) != 0 ? new BarcodeArDeserializer() : barcodeArDeserializer, (i & 32) != 0 ? new BarcodeArViewDeserializer() : barcodeArViewDeserializer, (i & 64) != 0 ? DefaultMainThread.INSTANCE.getInstance() : mainThread);
    }

    @Override // com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView
    /* renamed from: getViewId, reason: from getter */
    public int get_viewId() {
        return this._viewId;
    }

    public final BarcodeArView getView() {
        BarcodeArView barcodeArView = this.view;
        if (barcodeArView != null) {
            return barcodeArView;
        }
        Intrinsics.throwUninitializedPropertyAccessException(DataSources.EventTypeValue.VIEW_EVENT_TYPE);
        return null;
    }

    public final BarcodeArAugmentationsCache getCache() {
        return this.barcodeArListener.getCache();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deserializeView(ViewGroup container, DataCaptureContext dataCaptureContext, BarcodeArViewCreationData viewCreationParams) {
        this._viewId = viewCreationParams.getViewId();
        this.mode = this.modeDeserializer.modeFromJson(dataCaptureContext, viewCreationParams.getModeJson());
        postModeChanges(viewCreationParams);
        BarcodeArViewDeserializer barcodeArViewDeserializer = this.viewDeserializer;
        BarcodeAr barcodeAr = this.mode;
        if (barcodeAr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeAr = null;
        }
        this.view = barcodeArViewDeserializer.viewFromJson(container, barcodeAr, dataCaptureContext, viewCreationParams.getViewJson());
        postViewChanges(viewCreationParams);
        container.setTag(Integer.valueOf(get_viewId()));
        getView().setTag(Integer.valueOf(get_viewId()));
    }

    private final void postModeChanges(BarcodeArViewCreationData changeParams) {
        BarcodeAr barcodeAr = null;
        if (changeParams.getHasModeListener()) {
            BarcodeAr barcodeAr2 = this.mode;
            if (barcodeAr2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mode");
            } else {
                barcodeAr = barcodeAr2;
            }
            barcodeAr.addListener(this.barcodeArListener);
            return;
        }
        BarcodeAr barcodeAr3 = this.mode;
        if (barcodeAr3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
        } else {
            barcodeAr = barcodeAr3;
        }
        barcodeAr.removeListener(this.barcodeArListener);
    }

    private final void postViewChanges(BarcodeArViewCreationData changeParams) {
        if (changeParams.getHasUiListener()) {
            addBarcodeArViewUiListener();
        } else {
            removeBarcodeArViewUiListener();
        }
        if (changeParams.getHasHighlightProvider()) {
            addBarcodeArHighlightProvider();
        } else {
            removeBarcodeArHighlightProvider();
        }
        if (changeParams.getHasAnnotationProvider()) {
            addBarcodeArAnnotationProvider();
        } else {
            removeBarcodeArAnnotationProvider();
        }
        if (changeParams.getHasModeListener()) {
            addBarcodeArListener();
        } else {
            removeBarcodeArListener();
        }
    }

    public final void updateView(String viewJson) {
        Intrinsics.checkNotNullParameter(viewJson, "viewJson");
        final BarcodeArViewCreationData fromViewJsonOnly = BarcodeArViewCreationData.INSTANCE.fromViewJsonOnly(viewJson);
        getView().post(new Runnable() { // from class: com.scandit.datacapture.frameworks.barcode.ar.ui.FrameworksBarcodeArView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FrameworksBarcodeArView.updateView$lambda$0(FrameworksBarcodeArView.this, fromViewJsonOnly);
            }
        });
        postViewChanges(fromViewJsonOnly);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateView$lambda$0(FrameworksBarcodeArView this$0, BarcodeArViewCreationData updateParams) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(updateParams, "$updateParams");
        this$0.viewDeserializer.updateViewFromJson(this$0.getView(), updateParams.getViewJson());
    }

    public final void updateMode(String modeJson) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        BarcodeArViewCreationData fromModeJsonOnly = BarcodeArViewCreationData.INSTANCE.fromModeJsonOnly(modeJson);
        BarcodeArDeserializer barcodeArDeserializer = this.modeDeserializer;
        BarcodeAr barcodeAr = this.mode;
        if (barcodeAr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeAr = null;
        }
        barcodeArDeserializer.updateModeFromJson(barcodeAr, fromModeJsonOnly.getModeJson());
        postModeChanges(fromModeJsonOnly);
    }

    public final void applySettings(String settingsJson) {
        Intrinsics.checkNotNullParameter(settingsJson, "settingsJson");
        BarcodeArSettings barcodeArSettings = this.modeDeserializer.settingsFromJson(settingsJson);
        BarcodeAr barcodeAr = this.mode;
        if (barcodeAr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeAr = null;
        }
        BarcodeAr.applySettings$default(barcodeAr, barcodeArSettings, null, 2, null);
    }

    public final void addBarcodeArListener() {
        BarcodeAr barcodeAr = this.mode;
        if (barcodeAr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeAr = null;
        }
        barcodeAr.addListener(this.barcodeArListener);
    }

    public final void removeBarcodeArListener() {
        BarcodeAr barcodeAr = this.mode;
        if (barcodeAr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeAr = null;
        }
        barcodeAr.removeListener(this.barcodeArListener);
    }

    public final void addBarcodeArViewUiListener() {
        getView().setUiListener(this.viewUiListener);
    }

    public final void removeBarcodeArViewUiListener() {
        getView().setUiListener(null);
    }

    public final void addBarcodeArHighlightProvider() {
        getView().setHighlightProvider(this.highlightProvider);
    }

    public final void removeBarcodeArHighlightProvider() {
        getView().setHighlightProvider(null);
    }

    public final void addBarcodeArAnnotationProvider() {
        getView().setAnnotationProvider(this.annotationProvider);
    }

    public final void removeBarcodeArAnnotationProvider() {
        getView().setAnnotationProvider(null);
    }

    public final void start() {
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.ar.ui.FrameworksBarcodeArView$start$1
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
                FrameworksBarcodeArView.this.getView().onResume();
                FrameworksBarcodeArView.this.getView().start();
            }
        });
    }

    public final void stop() {
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.ar.ui.FrameworksBarcodeArView$stop$1
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
                FrameworksBarcodeArView.this.getView().stop();
            }
        });
    }

    public final void reset() {
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.ar.ui.FrameworksBarcodeArView$reset$1
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
                FrameworksBarcodeArView.this.getView().reset();
            }
        });
    }

    public final void hide() {
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.ar.ui.FrameworksBarcodeArView$hide$1
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
                FrameworksBarcodeArView.this.getView().setVisibility(8);
                FrameworksBarcodeArView.this.getView().onPause();
            }
        });
    }

    public final void show() {
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.ar.ui.FrameworksBarcodeArView$show$1
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
                FrameworksBarcodeArView.this.getView().setVisibility(0);
                FrameworksBarcodeArView.this.getView().bringToFront();
                FrameworksBarcodeArView.this.getView().onResume();
            }
        });
    }

    public final void updateFeedback(String feedbackJson) {
        Intrinsics.checkNotNullParameter(feedbackJson, "feedbackJson");
        final BarcodeArFeedback fromJson = BarcodeArFeedbackDeserializer.fromJson(new JsonValue(feedbackJson));
        getView().post(new Runnable() { // from class: com.scandit.datacapture.frameworks.barcode.ar.ui.FrameworksBarcodeArView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FrameworksBarcodeArView.updateFeedback$lambda$1(FrameworksBarcodeArView.this, fromJson);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateFeedback$lambda$1(FrameworksBarcodeArView this$0, BarcodeArFeedback feedback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(feedback, "$feedback");
        BarcodeAr barcodeAr = this$0.mode;
        if (barcodeAr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeAr = null;
        }
        barcodeAr.setFeedback(feedback);
    }

    public final void resetSession() {
        this.barcodeArListener.resetSession();
    }

    public final void finishDidUpdateSession() {
        this.barcodeArListener.finishDidUpdateSession();
    }

    @Override // com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView
    public void dispose() {
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.ar.ui.FrameworksBarcodeArView$dispose$1
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
                FrameworksBarcodeArListener frameworksBarcodeArListener;
                BarcodeAr barcodeAr;
                FrameworksBarcodeArListener frameworksBarcodeArListener2;
                frameworksBarcodeArListener = FrameworksBarcodeArView.this.barcodeArListener;
                frameworksBarcodeArListener.reset();
                BarcodeAr barcodeAr2 = null;
                FrameworksBarcodeArView.this.getView().setUiListener(null);
                FrameworksBarcodeArView.this.getView().setHighlightProvider(null);
                FrameworksBarcodeArView.this.getView().setAnnotationProvider(null);
                barcodeAr = FrameworksBarcodeArView.this.mode;
                if (barcodeAr == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mode");
                } else {
                    barcodeAr2 = barcodeAr;
                }
                frameworksBarcodeArListener2 = FrameworksBarcodeArView.this.barcodeArListener;
                barcodeAr2.removeListener(frameworksBarcodeArListener2);
                FrameworksBarcodeArView.this.getView().onPause();
                FrameworksBarcodeArView.this.getView().onDestroy();
            }
        });
    }

    public final void updateBarcodeArPopoverButtonAtIndex(String updateJson) {
        Intrinsics.checkNotNullParameter(updateJson, "updateJson");
        this.annotationProvider.updateBarcodeArPopoverButtonAtIndex(updateJson);
    }

    public final void updateHighlight(String highlightJson) {
        Intrinsics.checkNotNullParameter(highlightJson, "highlightJson");
        this.highlightProvider.updateHighlight(highlightJson);
    }

    public final void updateAnnotation(String annotationJson) {
        Intrinsics.checkNotNullParameter(annotationJson, "annotationJson");
        this.annotationProvider.updateAnnotation(annotationJson);
    }

    public final void finishHighlightForBarcode(Context context, String highlightResponse) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(highlightResponse, "highlightResponse");
        this.highlightProvider.finishHighlightForBarcode(context, highlightResponse);
    }

    public final void finishAnnotationForBarcode(Context context, String barcodeResponse) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(barcodeResponse, "barcodeResponse");
        this.annotationProvider.finishAnnotationForBarcode(context, barcodeResponse);
    }

    /* compiled from: FrameworksBarcodeArView.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/ui/FrameworksBarcodeArView$Companion;", "", "()V", "create", "Lcom/scandit/datacapture/frameworks/barcode/ar/ui/FrameworksBarcodeArView;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "dataCaptureContext", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "viewCreationParams", "Lcom/scandit/datacapture/frameworks/barcode/ar/data/BarcodeArViewCreationData;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FrameworksBarcodeArView create(Emitter emitter, ViewGroup container, DataCaptureContext dataCaptureContext, BarcodeArViewCreationData viewCreationParams) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            Intrinsics.checkNotNullParameter(container, "container");
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            Intrinsics.checkNotNullParameter(viewCreationParams, "viewCreationParams");
            BarcodeArAugmentationsCache barcodeArAugmentationsCache = new BarcodeArAugmentationsCache(null, 1, null);
            BarcodeArDeserializer barcodeArDeserializer = null;
            BarcodeArViewDeserializer barcodeArViewDeserializer = null;
            MainThread mainThread = null;
            FrameworksBarcodeArView frameworksBarcodeArView = new FrameworksBarcodeArView(new FrameworksBarcodeArListener(emitter, viewCreationParams.getViewId(), barcodeArAugmentationsCache, null, 8, null), new FrameworksBarcodeArViewUiListener(emitter, viewCreationParams.getViewId()), new FrameworksBarcodeArHighlightProvider(emitter, viewCreationParams.getViewId(), new BarcodeArHighlightParser(), barcodeArAugmentationsCache), new FrameworksBarcodeArAnnotationProvider(emitter, viewCreationParams.getViewId(), new BarcodeArAnnotationParser(new FrameworksBarcodeArInfoAnnotationListener(emitter, viewCreationParams.getViewId()), new FrameworksBarcodeArPopoverAnnotationListener(emitter, viewCreationParams.getViewId()), null, 4, null), barcodeArAugmentationsCache), barcodeArDeserializer, barcodeArViewDeserializer, mainThread, b.p, null);
            frameworksBarcodeArView.deserializeView(container, dataCaptureContext, viewCreationParams);
            return frameworksBarcodeArView;
        }
    }
}
