package com.scandit.datacapture.frameworks.barcode.find.ui;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.find.capture.BarcodeFind;
import com.scandit.datacapture.barcode.find.serialization.BarcodeFindDeserializer;
import com.scandit.datacapture.barcode.find.serialization.BarcodeFindViewDeserializer;
import com.scandit.datacapture.barcode.find.ui.BarcodeFindView;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.frameworks.barcode.find.data.BarcodeFindItemsData;
import com.scandit.datacapture.frameworks.barcode.find.data.BarcodeFindViewCreationData;
import com.scandit.datacapture.frameworks.barcode.find.listeners.FrameworksBarcodeFindListener;
import com.scandit.datacapture.frameworks.barcode.find.listeners.FrameworksBarcodeFindViewUiListener;
import com.scandit.datacapture.frameworks.barcode.find.transformer.FrameworksBarcodeFindTransformer;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView;
import com.scandit.datacapture.frameworks.core.utils.DefaultMainThread;
import com.scandit.datacapture.frameworks.core.utils.MainThread;
import com.tealium.library.DataSources;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeFindView.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0015\u0018\u0000 I2\u00020\u0001:\u0001IB;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020 J \u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0002J\b\u0010)\u001a\u00020 H\u0016J\u0006\u0010*\u001a\u00020 J\u0006\u0010+\u001a\u00020,J\u0006\u0010-\u001a\u00020 J\u0006\u0010.\u001a\u00020 J\u0006\u0010/\u001a\u00020 J\u0006\u00100\u001a\u00020 J\u0006\u00101\u001a\u00020 J\u0006\u00102\u001a\u00020 J\u000e\u00103\u001a\u00020 2\u0006\u00104\u001a\u000205J\u000e\u00106\u001a\u00020 2\u0006\u00107\u001a\u00020,J\u0006\u00108\u001a\u00020 J\u0010\u00109\u001a\u00020 2\b\u0010:\u001a\u0004\u0018\u000105J\u0006\u0010;\u001a\u00020 J\u000e\u0010<\u001a\u00020 2\u0006\u0010=\u001a\u000205J\u000e\u0010>\u001a\u00020 2\u0006\u0010?\u001a\u000205J\u000e\u0010@\u001a\u00020 2\u0006\u0010A\u001a\u000205J\u0010\u0010B\u001a\u00020 2\u0006\u0010'\u001a\u00020(H\u0002J\u0010\u0010C\u001a\u00020 2\u0006\u0010'\u001a\u00020(H\u0002J\u0006\u0010D\u001a\u00020 J\u0006\u0010E\u001a\u00020 J\u0006\u0010F\u001a\u00020 J\u0006\u0010G\u001a\u00020 J\u0006\u0010H\u001a\u00020 R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/find/ui/FrameworksBarcodeFindView;", "Lcom/scandit/datacapture/frameworks/core/ui/FrameworksBaseView;", "modeListener", "Lcom/scandit/datacapture/frameworks/barcode/find/listeners/FrameworksBarcodeFindListener;", "viewListener", "Lcom/scandit/datacapture/frameworks/barcode/find/listeners/FrameworksBarcodeFindViewUiListener;", "findTransformer", "Lcom/scandit/datacapture/frameworks/barcode/find/transformer/FrameworksBarcodeFindTransformer;", "deserializer", "Lcom/scandit/datacapture/barcode/find/serialization/BarcodeFindDeserializer;", "viewDeserializer", "Lcom/scandit/datacapture/barcode/find/serialization/BarcodeFindViewDeserializer;", "mainThread", "Lcom/scandit/datacapture/frameworks/core/utils/MainThread;", "(Lcom/scandit/datacapture/frameworks/barcode/find/listeners/FrameworksBarcodeFindListener;Lcom/scandit/datacapture/frameworks/barcode/find/listeners/FrameworksBarcodeFindViewUiListener;Lcom/scandit/datacapture/frameworks/barcode/find/transformer/FrameworksBarcodeFindTransformer;Lcom/scandit/datacapture/barcode/find/serialization/BarcodeFindDeserializer;Lcom/scandit/datacapture/barcode/find/serialization/BarcodeFindViewDeserializer;Lcom/scandit/datacapture/frameworks/core/utils/MainThread;)V", "_viewId", "", "mode", "Lcom/scandit/datacapture/barcode/find/capture/BarcodeFind;", "parentId", "getParentId", "()Ljava/lang/Integer;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindView;", "getView", "()Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindView;", "setView", "(Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindView;)V", "viewId", "getViewId", "()I", "addBarcodeFindListener", "", "addBarcodeFindViewListener", "deserializeView", "context", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/View;", "creationData", "Lcom/scandit/datacapture/frameworks/barcode/find/data/BarcodeFindViewCreationData;", "dispose", "hide", "isModeEnabled", "", "modePause", "modeStart", "modeStop", "removeBarcodeFindListener", "removeBarcodeFindViewListener", "setBarcodeFindTransformer", "setItemList", "barcodeFindItemsJson", "", "setModeEnabled", ExtentionsKt.ENABLED_KEY, "show", "submitBarcodeFindTransformerResult", "transformedData", "unsetBarcodeFindTransformer", "updateBarcodeFindMode", "modeJson", "updateBarcodeFindView", "viewJson", "updateFeedback", "feedbackJson", "updateModeProps", "updateViewProps", "viewOnPause", "viewOnResume", "viewPauseSearching", "viewStartSearching", "viewStopSearching", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksBarcodeFindView implements FrameworksBaseView {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int _viewId;
    private final BarcodeFindDeserializer deserializer;
    private final FrameworksBarcodeFindTransformer findTransformer;
    private final MainThread mainThread;
    private BarcodeFind mode;
    private final FrameworksBarcodeFindListener modeListener;
    public BarcodeFindView view;
    private final BarcodeFindViewDeserializer viewDeserializer;
    private final FrameworksBarcodeFindViewUiListener viewListener;

    @Override // com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView
    /* renamed from: getParentId */
    public Integer get_parentId() {
        return null;
    }

    public FrameworksBarcodeFindView(FrameworksBarcodeFindListener modeListener, FrameworksBarcodeFindViewUiListener viewListener, FrameworksBarcodeFindTransformer findTransformer, BarcodeFindDeserializer deserializer, BarcodeFindViewDeserializer viewDeserializer, MainThread mainThread) {
        Intrinsics.checkNotNullParameter(modeListener, "modeListener");
        Intrinsics.checkNotNullParameter(viewListener, "viewListener");
        Intrinsics.checkNotNullParameter(findTransformer, "findTransformer");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(viewDeserializer, "viewDeserializer");
        Intrinsics.checkNotNullParameter(mainThread, "mainThread");
        this.modeListener = modeListener;
        this.viewListener = viewListener;
        this.findTransformer = findTransformer;
        this.deserializer = deserializer;
        this.viewDeserializer = viewDeserializer;
        this.mainThread = mainThread;
    }

    public /* synthetic */ FrameworksBarcodeFindView(FrameworksBarcodeFindListener frameworksBarcodeFindListener, FrameworksBarcodeFindViewUiListener frameworksBarcodeFindViewUiListener, FrameworksBarcodeFindTransformer frameworksBarcodeFindTransformer, BarcodeFindDeserializer barcodeFindDeserializer, BarcodeFindViewDeserializer barcodeFindViewDeserializer, DefaultMainThread defaultMainThread, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(frameworksBarcodeFindListener, frameworksBarcodeFindViewUiListener, frameworksBarcodeFindTransformer, (i & 8) != 0 ? new BarcodeFindDeserializer() : barcodeFindDeserializer, (i & 16) != 0 ? new BarcodeFindViewDeserializer() : barcodeFindViewDeserializer, (i & 32) != 0 ? DefaultMainThread.INSTANCE.getInstance() : defaultMainThread);
    }

    @Override // com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView
    /* renamed from: getViewId, reason: from getter */
    public int get_viewId() {
        return this._viewId;
    }

    public final BarcodeFindView getView() {
        BarcodeFindView barcodeFindView = this.view;
        if (barcodeFindView != null) {
            return barcodeFindView;
        }
        Intrinsics.throwUninitializedPropertyAccessException(DataSources.EventTypeValue.VIEW_EVENT_TYPE);
        return null;
    }

    public final void setView(BarcodeFindView barcodeFindView) {
        Intrinsics.checkNotNullParameter(barcodeFindView, "<set-?>");
        this.view = barcodeFindView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deserializeView(DataCaptureContext context, View container, BarcodeFindViewCreationData creationData) {
        this._viewId = creationData.getViewId();
        BarcodeFind modeFromJson = this.deserializer.modeFromJson(creationData.getModeJson());
        this.mode = modeFromJson;
        BarcodeFind barcodeFind = null;
        if (modeFromJson == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            modeFromJson = null;
        }
        modeFromJson.setEnabled(creationData.getIsModeEnabled());
        updateModeProps(creationData);
        BarcodeFindViewDeserializer barcodeFindViewDeserializer = this.viewDeserializer;
        BarcodeFind barcodeFind2 = this.mode;
        if (barcodeFind2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
        } else {
            barcodeFind = barcodeFind2;
        }
        BarcodeFindView viewFromJson = barcodeFindViewDeserializer.viewFromJson(container, context, barcodeFind, creationData.getViewJson());
        viewFromJson.onResume();
        setView(viewFromJson);
        updateViewProps(creationData);
        getView().setTag(Integer.valueOf(get_viewId()));
    }

    public final void updateBarcodeFindView(String viewJson) {
        Intrinsics.checkNotNullParameter(viewJson, "viewJson");
        final BarcodeFindViewCreationData fromViewJsonOnly = BarcodeFindViewCreationData.INSTANCE.fromViewJsonOnly(viewJson);
        getView().post(new Runnable() { // from class: com.scandit.datacapture.frameworks.barcode.find.ui.FrameworksBarcodeFindView$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                FrameworksBarcodeFindView.updateBarcodeFindView$lambda$1(FrameworksBarcodeFindView.this, fromViewJsonOnly);
            }
        });
        updateViewProps(fromViewJsonOnly);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateBarcodeFindView$lambda$1(FrameworksBarcodeFindView this$0, BarcodeFindViewCreationData updateParams) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(updateParams, "$updateParams");
        this$0.viewDeserializer.updateViewFromJson(this$0.getView(), updateParams.getViewJson());
    }

    public final void updateBarcodeFindMode(String modeJson) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        updateModeProps(BarcodeFindViewCreationData.INSTANCE.fromModeJsonOnly(modeJson));
    }

    public final void addBarcodeFindListener() {
        BarcodeFind barcodeFind = this.mode;
        if (barcodeFind == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeFind = null;
        }
        barcodeFind.addListener(this.modeListener);
    }

    public final void removeBarcodeFindListener() {
        BarcodeFind barcodeFind = this.mode;
        if (barcodeFind == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeFind = null;
        }
        barcodeFind.removeListener(this.modeListener);
    }

    public final void addBarcodeFindViewListener() {
        getView().setListener(this.viewListener);
    }

    public final void removeBarcodeFindViewListener() {
        getView().setListener(null);
    }

    public final void viewOnPause() {
        getView().post(new Runnable() { // from class: com.scandit.datacapture.frameworks.barcode.find.ui.FrameworksBarcodeFindView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FrameworksBarcodeFindView.viewOnPause$lambda$2(FrameworksBarcodeFindView.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void viewOnPause$lambda$2(FrameworksBarcodeFindView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getView().onPause();
    }

    public final void viewOnResume() {
        getView().post(new Runnable() { // from class: com.scandit.datacapture.frameworks.barcode.find.ui.FrameworksBarcodeFindView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FrameworksBarcodeFindView.viewOnResume$lambda$3(FrameworksBarcodeFindView.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void viewOnResume$lambda$3(FrameworksBarcodeFindView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getView().onResume();
    }

    public final void setItemList(String barcodeFindItemsJson) {
        Intrinsics.checkNotNullParameter(barcodeFindItemsJson, "barcodeFindItemsJson");
        BarcodeFindItemsData barcodeFindItemsData = new BarcodeFindItemsData(barcodeFindItemsJson);
        BarcodeFind barcodeFind = this.mode;
        if (barcodeFind == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeFind = null;
        }
        barcodeFind.setItemList(barcodeFindItemsData.getItems());
    }

    public final void viewStopSearching() {
        getView().post(new Runnable() { // from class: com.scandit.datacapture.frameworks.barcode.find.ui.FrameworksBarcodeFindView$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                FrameworksBarcodeFindView.viewStopSearching$lambda$4(FrameworksBarcodeFindView.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void viewStopSearching$lambda$4(FrameworksBarcodeFindView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getView().stopSearching();
    }

    public final void viewStartSearching() {
        getView().post(new Runnable() { // from class: com.scandit.datacapture.frameworks.barcode.find.ui.FrameworksBarcodeFindView$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                FrameworksBarcodeFindView.viewStartSearching$lambda$5(FrameworksBarcodeFindView.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void viewStartSearching$lambda$5(FrameworksBarcodeFindView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getView().startSearching();
    }

    public final void viewPauseSearching() {
        getView().post(new Runnable() { // from class: com.scandit.datacapture.frameworks.barcode.find.ui.FrameworksBarcodeFindView$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                FrameworksBarcodeFindView.viewPauseSearching$lambda$6(FrameworksBarcodeFindView.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void viewPauseSearching$lambda$6(FrameworksBarcodeFindView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getView().pauseSearching();
    }

    public final void modeStart() {
        BarcodeFind barcodeFind = this.mode;
        if (barcodeFind == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeFind = null;
        }
        barcodeFind.start();
    }

    public final void modeStop() {
        BarcodeFind barcodeFind = this.mode;
        if (barcodeFind == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeFind = null;
        }
        barcodeFind.stop();
    }

    public final void modePause() {
        BarcodeFind barcodeFind = this.mode;
        if (barcodeFind == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeFind = null;
        }
        barcodeFind.pause();
    }

    public final void setModeEnabled(boolean enabled) {
        BarcodeFind barcodeFind = this.mode;
        if (barcodeFind == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeFind = null;
        }
        barcodeFind.setEnabled(enabled);
    }

    public final void setBarcodeFindTransformer() {
        BarcodeFind barcodeFind = this.mode;
        if (barcodeFind == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeFind = null;
        }
        barcodeFind.setTransformer(this.findTransformer);
    }

    public final void unsetBarcodeFindTransformer() {
        this.findTransformer.disable();
    }

    public final void submitBarcodeFindTransformerResult(String transformedData) {
        this.findTransformer.submitResult(transformedData);
    }

    public final void updateFeedback(String feedbackJson) {
        Intrinsics.checkNotNullParameter(feedbackJson, "feedbackJson");
        BarcodeFind barcodeFind = this.mode;
        if (barcodeFind == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeFind = null;
        }
        barcodeFind.setFeedback(this.deserializer.feedbackFromJson(feedbackJson));
    }

    public final boolean isModeEnabled() {
        BarcodeFind barcodeFind = this.mode;
        if (barcodeFind == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeFind = null;
        }
        return barcodeFind.isEnabled();
    }

    private final void updateViewProps(BarcodeFindViewCreationData creationData) {
        if (creationData.getHasViewListener()) {
            getView().setListener(this.viewListener);
        }
        if (creationData.getStartSearching()) {
            getView().startSearching();
        }
    }

    private final void updateModeProps(BarcodeFindViewCreationData creationData) {
        BarcodeFind barcodeFind = null;
        if (creationData.getHasModeListeners()) {
            BarcodeFind barcodeFind2 = this.mode;
            if (barcodeFind2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mode");
                barcodeFind2 = null;
            }
            barcodeFind2.addListener(this.modeListener);
        }
        if (creationData.getHasBarcodeTransformer()) {
            BarcodeFind barcodeFind3 = this.mode;
            if (barcodeFind3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mode");
                barcodeFind3 = null;
            }
            barcodeFind3.setTransformer(this.findTransformer);
        }
        if (creationData.getItemsToFind() != null) {
            BarcodeFind barcodeFind4 = this.mode;
            if (barcodeFind4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mode");
            } else {
                barcodeFind = barcodeFind4;
            }
            barcodeFind.setItemList(creationData.getItemsToFind().getItems());
            return;
        }
        BarcodeFind barcodeFind5 = this.mode;
        if (barcodeFind5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
        } else {
            barcodeFind = barcodeFind5;
        }
        barcodeFind.setItemList(SetsKt.emptySet());
    }

    @Override // com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView
    public void dispose() {
        BarcodeFind barcodeFind = this.mode;
        BarcodeFind barcodeFind2 = null;
        if (barcodeFind == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeFind = null;
        }
        barcodeFind.removeListener(this.modeListener);
        BarcodeFind barcodeFind3 = this.mode;
        if (barcodeFind3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
        } else {
            barcodeFind2 = barcodeFind3;
        }
        barcodeFind2.stop();
        this.findTransformer.disable();
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.find.ui.FrameworksBarcodeFindView$dispose$1
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
                FrameworksBarcodeFindView.this.getView().onPause();
                FrameworksBarcodeFindView.this.getView().setListener(null);
            }
        });
    }

    public final void hide() {
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.find.ui.FrameworksBarcodeFindView$hide$1
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
                FrameworksBarcodeFindTransformer frameworksBarcodeFindTransformer;
                frameworksBarcodeFindTransformer = FrameworksBarcodeFindView.this.findTransformer;
                frameworksBarcodeFindTransformer.disable();
                FrameworksBarcodeFindView.this.getView().setVisibility(8);
                FrameworksBarcodeFindView.this.getView().onPause();
            }
        });
    }

    public final void show() {
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.find.ui.FrameworksBarcodeFindView$show$1
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
                FrameworksBarcodeFindTransformer frameworksBarcodeFindTransformer;
                FrameworksBarcodeFindView.this.getView().setVisibility(0);
                FrameworksBarcodeFindView.this.getView().bringToFront();
                FrameworksBarcodeFindView.this.getView().onResume();
                frameworksBarcodeFindTransformer = FrameworksBarcodeFindView.this.findTransformer;
                frameworksBarcodeFindTransformer.enable();
            }
        });
    }

    /* compiled from: FrameworksBarcodeFindView.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/find/ui/FrameworksBarcodeFindView$Companion;", "", "()V", "create", "Lcom/scandit/datacapture/frameworks/barcode/find/ui/FrameworksBarcodeFindView;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "context", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/View;", "viewCreationParams", "Lcom/scandit/datacapture/frameworks/barcode/find/data/BarcodeFindViewCreationData;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FrameworksBarcodeFindView create(Emitter emitter, DataCaptureContext context, View container, BarcodeFindViewCreationData viewCreationParams) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(container, "container");
            Intrinsics.checkNotNullParameter(viewCreationParams, "viewCreationParams");
            FrameworksBarcodeFindView frameworksBarcodeFindView = new FrameworksBarcodeFindView(new FrameworksBarcodeFindListener(emitter, viewCreationParams.getViewId()), new FrameworksBarcodeFindViewUiListener(emitter, viewCreationParams.getViewId()), new FrameworksBarcodeFindTransformer(emitter, viewCreationParams.getViewId()), null, null, null, 56, null);
            frameworksBarcodeFindView.deserializeView(context, container, viewCreationParams);
            return frameworksBarcodeFindView;
        }
    }
}
