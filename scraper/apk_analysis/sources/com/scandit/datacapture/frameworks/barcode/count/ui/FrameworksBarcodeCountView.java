package com.scandit.datacapture.frameworks.barcode.count.ui;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.count.capture.BarcodeCount;
import com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureList;
import com.scandit.datacapture.barcode.count.capture.list.TargetBarcode;
import com.scandit.datacapture.barcode.count.capture.map.BarcodeSpatialGrid;
import com.scandit.datacapture.barcode.count.feedback.BarcodeCountFeedback;
import com.scandit.datacapture.barcode.count.serialization.BarcodeCountDeserializer;
import com.scandit.datacapture.barcode.count.serialization.BarcodeCountViewDeserializer;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView;
import com.scandit.datacapture.barcode.internal.sdk.feedback.BarcodeCountFeedbackDeserializer;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.frameworks.barcode.count.data.BarcodeCountViewCreationData;
import com.scandit.datacapture.frameworks.barcode.count.listeners.FrameworksBarcodeCountCaptureListListener;
import com.scandit.datacapture.frameworks.barcode.count.listeners.FrameworksBarcodeCountListener;
import com.scandit.datacapture.frameworks.barcode.count.listeners.FrameworksBarcodeCountStatusProvider;
import com.scandit.datacapture.frameworks.barcode.count.listeners.FrameworksBarcodeCountViewListener;
import com.scandit.datacapture.frameworks.barcode.count.listeners.FrameworksBarcodeCountViewUiListener;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView;
import com.scandit.datacapture.frameworks.core.utils.MainThread;
import com.tealium.library.DataSources;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeCountView.kt */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u0000 b2\u00020\u0001:\u0001bBM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\u0006\u0010'\u001a\u00020(J\u0006\u0010)\u001a\u00020(J\u0006\u0010*\u001a\u00020(J\u0006\u0010+\u001a\u00020(J\u0006\u0010,\u001a\u00020(J\u0006\u0010-\u001a\u00020(J\u0018\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0002J\b\u00103\u001a\u00020(H\u0016J\u0015\u00104\u001a\u00020(2\b\u00105\u001a\u0004\u0018\u00010\u0016¢\u0006\u0002\u00106J\u0006\u00107\u001a\u00020(J\u0018\u00108\u001a\u00020(2\b\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010;\u001a\u00020\u0016J\u0018\u0010<\u001a\u00020(2\b\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010;\u001a\u00020\u0016J\u0018\u0010=\u001a\u00020(2\b\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010;\u001a\u00020\u0016J\u0018\u0010>\u001a\u00020(2\b\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010;\u001a\u00020\u0016J\u000e\u0010?\u001a\u00020(2\u0006\u0010@\u001a\u00020\u0018J\b\u0010A\u001a\u0004\u0018\u00010BJ\u0018\u0010A\u001a\u0004\u0018\u00010B2\u0006\u0010C\u001a\u00020\u00162\u0006\u0010D\u001a\u00020\u0016J\u0006\u0010E\u001a\u00020(J\u0010\u0010F\u001a\u00020(2\u0006\u0010G\u001a\u000202H\u0002J\u0010\u0010H\u001a\u00020(2\u0006\u0010G\u001a\u000202H\u0002J\u0006\u0010I\u001a\u00020(J\u0006\u0010J\u001a\u00020(J\u0006\u0010K\u001a\u00020(J\u0006\u0010L\u001a\u00020(J\u0006\u0010M\u001a\u00020(J\u0015\u0010N\u001a\u00020(2\b\u0010O\u001a\u0004\u0018\u00010P¢\u0006\u0002\u0010QJ\u0014\u0010R\u001a\u00020(2\f\u0010S\u001a\b\u0012\u0004\u0012\u00020U0TJ\u000e\u0010V\u001a\u00020(2\u0006\u0010@\u001a\u00020\u0018J\u0006\u0010W\u001a\u00020(J\u0006\u0010X\u001a\u00020(J\u000e\u0010Y\u001a\u00020(2\u0006\u0010Z\u001a\u00020[J\u000e\u0010\\\u001a\u00020(2\u0006\u0010]\u001a\u00020[J\u000e\u0010^\u001a\u00020(2\u0006\u0010_\u001a\u00020[J\u000e\u0010`\u001a\u00020(2\u0006\u0010a\u001a\u00020[R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0017\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0019R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\u0004\u0018\u00010\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010!\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020 @BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010$\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006c"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/ui/FrameworksBarcodeCountView;", "Lcom/scandit/datacapture/frameworks/core/ui/FrameworksBaseView;", "dataCaptureContext", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "barcodeCountListener", "Lcom/scandit/datacapture/frameworks/barcode/count/listeners/FrameworksBarcodeCountListener;", "captureListListener", "Lcom/scandit/datacapture/frameworks/barcode/count/listeners/FrameworksBarcodeCountCaptureListListener;", "viewListener", "Lcom/scandit/datacapture/frameworks/barcode/count/listeners/FrameworksBarcodeCountViewListener;", "viewUiListener", "Lcom/scandit/datacapture/frameworks/barcode/count/listeners/FrameworksBarcodeCountViewUiListener;", "statusProvider", "Lcom/scandit/datacapture/frameworks/barcode/count/listeners/FrameworksBarcodeCountStatusProvider;", "modeDeserializer", "Lcom/scandit/datacapture/barcode/count/serialization/BarcodeCountDeserializer;", "viewDeserializer", "Lcom/scandit/datacapture/barcode/count/serialization/BarcodeCountViewDeserializer;", "mainThread", "Lcom/scandit/datacapture/frameworks/core/utils/MainThread;", "(Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/frameworks/barcode/count/listeners/FrameworksBarcodeCountListener;Lcom/scandit/datacapture/frameworks/barcode/count/listeners/FrameworksBarcodeCountCaptureListListener;Lcom/scandit/datacapture/frameworks/barcode/count/listeners/FrameworksBarcodeCountViewListener;Lcom/scandit/datacapture/frameworks/barcode/count/listeners/FrameworksBarcodeCountViewUiListener;Lcom/scandit/datacapture/frameworks/barcode/count/listeners/FrameworksBarcodeCountStatusProvider;Lcom/scandit/datacapture/barcode/count/serialization/BarcodeCountDeserializer;Lcom/scandit/datacapture/barcode/count/serialization/BarcodeCountViewDeserializer;Lcom/scandit/datacapture/frameworks/core/utils/MainThread;)V", "_viewId", "", "isModeEnabled", "", "()Z", "mode", "Lcom/scandit/datacapture/barcode/count/capture/BarcodeCount;", "parentId", "getParentId", "()Ljava/lang/Integer;", "<set-?>", "Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountView;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "getView", "()Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountView;", "viewId", "getViewId", "()I", "addAsyncBarcodeCountListener", "", "addBarcodeCountListener", "addBarcodeCountStatusProvider", "addBarcodeCountViewListener", "addBarcodeCountViewUiListener", "barcodeViewClearHighlights", "deserializeView", "context", "Landroid/content/Context;", "viewCreationParams", "Lcom/scandit/datacapture/frameworks/barcode/count/data/BarcodeCountViewCreationData;", "dispose", "enableHardwareTrigger", BarcodeCountViewDeserializer.KEY_HW_TRIGGER_KEY_CODE, "(Ljava/lang/Integer;)V", "endScanningPhase", "finishBrushForAcceptedBarcodeEvent", "brush", "Lcom/scandit/datacapture/core/ui/style/Brush;", "trackedBarcodeId", "finishBrushForRecognizedBarcodeEvent", "finishBrushForRecognizedBarcodeNotInListEvent", "finishBrushForRejectedBarcodeEvent", "finishOnScan", ExtentionsKt.ENABLED_KEY, "getSpatialMap", "Lcom/scandit/datacapture/barcode/count/capture/map/BarcodeSpatialGrid;", "expectedNumberOfRows", "expectedNumberOfColumns", "hide", "postModeChanges", "changeParams", "postViewChanges", "removeAsyncBarcodeCountListener", "removeBarcodeCountListener", "removeBarcodeCountViewListener", "removeBarcodeCountViewUiListener", "resetBarcodeCount", "resetBarcodeCountSession", "frameSequenceId", "", "(Ljava/lang/Long;)V", "setBarcodeCountCaptureList", "targetBarcodes", "", "Lcom/scandit/datacapture/barcode/count/capture/list/TargetBarcode;", "setModeEnabled", "show", "startScanningPhase", "submitBarcodeCountStatusProviderCallbackResult", "statusJson", "", "updateFeedback", "feedbackJson", "updateMode", "modeJson", "updateView", "viewJson", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksBarcodeCountView implements FrameworksBaseView {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int _viewId;
    private final FrameworksBarcodeCountListener barcodeCountListener;
    private final FrameworksBarcodeCountCaptureListListener captureListListener;
    private final DataCaptureContext dataCaptureContext;
    private final MainThread mainThread;
    private BarcodeCount mode;
    private final BarcodeCountDeserializer modeDeserializer;
    private final FrameworksBarcodeCountStatusProvider statusProvider;
    private BarcodeCountView view;
    private final BarcodeCountViewDeserializer viewDeserializer;
    private final FrameworksBarcodeCountViewListener viewListener;
    private final FrameworksBarcodeCountViewUiListener viewUiListener;

    @Override // com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView
    /* renamed from: getParentId */
    public Integer get_parentId() {
        return null;
    }

    public FrameworksBarcodeCountView(DataCaptureContext dataCaptureContext, FrameworksBarcodeCountListener barcodeCountListener, FrameworksBarcodeCountCaptureListListener captureListListener, FrameworksBarcodeCountViewListener viewListener, FrameworksBarcodeCountViewUiListener viewUiListener, FrameworksBarcodeCountStatusProvider statusProvider, BarcodeCountDeserializer modeDeserializer, BarcodeCountViewDeserializer viewDeserializer, MainThread mainThread) {
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(barcodeCountListener, "barcodeCountListener");
        Intrinsics.checkNotNullParameter(captureListListener, "captureListListener");
        Intrinsics.checkNotNullParameter(viewListener, "viewListener");
        Intrinsics.checkNotNullParameter(viewUiListener, "viewUiListener");
        Intrinsics.checkNotNullParameter(statusProvider, "statusProvider");
        Intrinsics.checkNotNullParameter(modeDeserializer, "modeDeserializer");
        Intrinsics.checkNotNullParameter(viewDeserializer, "viewDeserializer");
        Intrinsics.checkNotNullParameter(mainThread, "mainThread");
        this.dataCaptureContext = dataCaptureContext;
        this.barcodeCountListener = barcodeCountListener;
        this.captureListListener = captureListListener;
        this.viewListener = viewListener;
        this.viewUiListener = viewUiListener;
        this.statusProvider = statusProvider;
        this.modeDeserializer = modeDeserializer;
        this.viewDeserializer = viewDeserializer;
        this.mainThread = mainThread;
    }

    @Override // com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView
    /* renamed from: getViewId, reason: from getter */
    public int get_viewId() {
        return this._viewId;
    }

    public final BarcodeCountView getView() {
        BarcodeCountView barcodeCountView = this.view;
        if (barcodeCountView != null) {
            return barcodeCountView;
        }
        Intrinsics.throwUninitializedPropertyAccessException(DataSources.EventTypeValue.VIEW_EVENT_TYPE);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deserializeView(Context context, BarcodeCountViewCreationData viewCreationParams) {
        this._viewId = viewCreationParams.getViewId();
        this.mode = this.modeDeserializer.modeFromJson(this.dataCaptureContext, viewCreationParams.getModeJson());
        postModeChanges(viewCreationParams);
        BarcodeCountViewDeserializer barcodeCountViewDeserializer = this.viewDeserializer;
        DataCaptureContext dataCaptureContext = this.dataCaptureContext;
        BarcodeCount barcodeCount = this.mode;
        if (barcodeCount == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeCount = null;
        }
        this.view = barcodeCountViewDeserializer.viewFromJson(context, dataCaptureContext, barcodeCount, viewCreationParams.getViewJson());
        postViewChanges(viewCreationParams);
        getView().setTag(Integer.valueOf(get_viewId()));
    }

    private final void postModeChanges(BarcodeCountViewCreationData changeParams) {
        BarcodeCount barcodeCount = this.mode;
        BarcodeCount barcodeCount2 = null;
        if (barcodeCount == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeCount = null;
        }
        barcodeCount.setEnabled(changeParams.getIsModeEnabled());
        if (changeParams.getHasModeListener()) {
            BarcodeCount barcodeCount3 = this.mode;
            if (barcodeCount3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mode");
            } else {
                barcodeCount2 = barcodeCount3;
            }
            barcodeCount2.addListener(this.barcodeCountListener);
            return;
        }
        BarcodeCount barcodeCount4 = this.mode;
        if (barcodeCount4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
        } else {
            barcodeCount2 = barcodeCount4;
        }
        barcodeCount2.removeListener(this.barcodeCountListener);
    }

    private final void postViewChanges(BarcodeCountViewCreationData changeParams) {
        if (changeParams.getHasUIListener()) {
            addBarcodeCountViewUiListener();
        } else {
            removeBarcodeCountViewUiListener();
        }
        if (changeParams.getHasViewListener()) {
            addBarcodeCountViewListener();
        } else {
            removeBarcodeCountViewListener();
        }
        if (changeParams.getHasStatusProvider()) {
            addBarcodeCountStatusProvider();
        }
    }

    public final void updateView(String viewJson) {
        Intrinsics.checkNotNullParameter(viewJson, "viewJson");
        final BarcodeCountViewCreationData fromViewJsonOnly = BarcodeCountViewCreationData.INSTANCE.fromViewJsonOnly(viewJson);
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.ui.FrameworksBarcodeCountView$updateView$1
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
                BarcodeCountViewDeserializer barcodeCountViewDeserializer;
                barcodeCountViewDeserializer = FrameworksBarcodeCountView.this.viewDeserializer;
                barcodeCountViewDeserializer.updateViewFromJson(FrameworksBarcodeCountView.this.getView(), fromViewJsonOnly.getViewJson());
            }
        });
        postViewChanges(fromViewJsonOnly);
    }

    public final void updateMode(String modeJson) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        BarcodeCountViewCreationData fromModeJsonOnly = BarcodeCountViewCreationData.INSTANCE.fromModeJsonOnly(modeJson);
        BarcodeCountDeserializer barcodeCountDeserializer = this.modeDeserializer;
        BarcodeCount barcodeCount = this.mode;
        if (barcodeCount == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeCount = null;
        }
        barcodeCountDeserializer.updateModeFromJson(barcodeCount, fromModeJsonOnly.getModeJson());
        postModeChanges(fromModeJsonOnly);
    }

    public final void setBarcodeCountCaptureList(List<TargetBarcode> targetBarcodes) {
        Intrinsics.checkNotNullParameter(targetBarcodes, "targetBarcodes");
        BarcodeCountCaptureList create = BarcodeCountCaptureList.INSTANCE.create(this.captureListListener, targetBarcodes);
        BarcodeCount barcodeCount = this.mode;
        if (barcodeCount == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeCount = null;
        }
        barcodeCount.setBarcodeCountCaptureList(create);
    }

    public final void addBarcodeCountStatusProvider() {
        getView().setStatusProvider(this.statusProvider);
    }

    public final void addBarcodeCountViewListener() {
        getView().setListener(this.viewListener);
    }

    public final void removeBarcodeCountViewListener() {
        getView().setListener(null);
    }

    public final void addBarcodeCountViewUiListener() {
        getView().setUiListener(this.viewUiListener);
    }

    public final void removeBarcodeCountViewUiListener() {
        getView().setUiListener(null);
    }

    public final void barcodeViewClearHighlights() {
        getView().clearHighlights();
    }

    public final void finishBrushForRecognizedBarcodeEvent(Brush brush, int trackedBarcodeId) {
        TrackedBarcode trackedBarcodeForBrushForRecognizedEvent$scandit_datacapture_frameworks_barcode_release = this.viewListener.getTrackedBarcodeForBrushForRecognizedEvent$scandit_datacapture_frameworks_barcode_release(trackedBarcodeId);
        if (trackedBarcodeForBrushForRecognizedEvent$scandit_datacapture_frameworks_barcode_release == null || brush == null) {
            return;
        }
        getView().setBrushForRecognizedBarcode(trackedBarcodeForBrushForRecognizedEvent$scandit_datacapture_frameworks_barcode_release, brush);
    }

    public final void finishBrushForRecognizedBarcodeNotInListEvent(Brush brush, int trackedBarcodeId) {
        TrackedBarcode trackedBarcodeForBrushForRecognizedNotInListEvent$scandit_datacapture_frameworks_barcode_release = this.viewListener.getTrackedBarcodeForBrushForRecognizedNotInListEvent$scandit_datacapture_frameworks_barcode_release(trackedBarcodeId);
        if (trackedBarcodeForBrushForRecognizedNotInListEvent$scandit_datacapture_frameworks_barcode_release == null || brush == null) {
            return;
        }
        getView().setBrushForRecognizedBarcodeNotInList(trackedBarcodeForBrushForRecognizedNotInListEvent$scandit_datacapture_frameworks_barcode_release, brush);
    }

    public final void finishBrushForAcceptedBarcodeEvent(Brush brush, int trackedBarcodeId) {
        TrackedBarcode trackedBarcodeForBrushForAcceptedEvent$scandit_datacapture_frameworks_barcode_release = this.viewListener.getTrackedBarcodeForBrushForAcceptedEvent$scandit_datacapture_frameworks_barcode_release(trackedBarcodeId);
        if (trackedBarcodeForBrushForAcceptedEvent$scandit_datacapture_frameworks_barcode_release == null || brush == null) {
            return;
        }
        getView().setBrushForAcceptedBarcode(trackedBarcodeForBrushForAcceptedEvent$scandit_datacapture_frameworks_barcode_release, brush);
    }

    public final void finishBrushForRejectedBarcodeEvent(Brush brush, int trackedBarcodeId) {
        TrackedBarcode trackedBarcodeForBrushForRejectedEvent$scandit_datacapture_frameworks_barcode_release = this.viewListener.getTrackedBarcodeForBrushForRejectedEvent$scandit_datacapture_frameworks_barcode_release(trackedBarcodeId);
        if (trackedBarcodeForBrushForRejectedEvent$scandit_datacapture_frameworks_barcode_release == null || brush == null) {
            return;
        }
        getView().setBrushForRejectedBarcode(trackedBarcodeForBrushForRejectedEvent$scandit_datacapture_frameworks_barcode_release, brush);
    }

    public final void resetBarcodeCountSession(Long frameSequenceId) {
        this.barcodeCountListener.resetSession(frameSequenceId);
    }

    public final void finishOnScan(boolean enabled) {
        this.barcodeCountListener.finishOnScan(enabled);
    }

    public final void addBarcodeCountListener() {
        BarcodeCount barcodeCount = this.mode;
        if (barcodeCount == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeCount = null;
        }
        barcodeCount.addListener(this.barcodeCountListener);
    }

    public final void removeBarcodeCountListener() {
        BarcodeCount barcodeCount = this.mode;
        if (barcodeCount == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeCount = null;
        }
        barcodeCount.removeListener(this.barcodeCountListener);
    }

    public final void addAsyncBarcodeCountListener() {
        BarcodeCount barcodeCount = this.mode;
        if (barcodeCount == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeCount = null;
        }
        barcodeCount.addListener(this.barcodeCountListener);
        this.barcodeCountListener.enableInAsyncMode();
    }

    public final void removeAsyncBarcodeCountListener() {
        this.barcodeCountListener.disableInAsyncMode();
        BarcodeCount barcodeCount = this.mode;
        if (barcodeCount == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeCount = null;
        }
        barcodeCount.removeListener(this.barcodeCountListener);
    }

    public final void resetBarcodeCount() {
        BarcodeCount barcodeCount = this.mode;
        if (barcodeCount == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeCount = null;
        }
        barcodeCount.reset();
    }

    public final void startScanningPhase() {
        BarcodeCount barcodeCount = this.mode;
        if (barcodeCount == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeCount = null;
        }
        barcodeCount.startScanningPhase();
    }

    public final void endScanningPhase() {
        BarcodeCount barcodeCount = this.mode;
        if (barcodeCount == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeCount = null;
        }
        barcodeCount.endScanningPhase();
    }

    public final void hide() {
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.ui.FrameworksBarcodeCountView$hide$1
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
                FrameworksBarcodeCountView.this.getView().setVisibility(8);
            }
        });
    }

    public final void show() {
        DataCaptureContext dataCaptureContext = this.dataCaptureContext;
        BarcodeCount barcodeCount = this.mode;
        BarcodeCount barcodeCount2 = null;
        if (barcodeCount == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeCount = null;
        }
        dataCaptureContext.setMode(barcodeCount);
        BarcodeCount barcodeCount3 = this.mode;
        if (barcodeCount3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
        } else {
            barcodeCount2 = barcodeCount3;
        }
        barcodeCount2.reset();
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.ui.FrameworksBarcodeCountView$show$1
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
                FrameworksBarcodeCountView.this.getView().setVisibility(0);
                FrameworksBarcodeCountView.this.getView().bringToFront();
            }
        });
    }

    public final BarcodeSpatialGrid getSpatialMap() {
        return this.barcodeCountListener.getSpatialMap();
    }

    public final BarcodeSpatialGrid getSpatialMap(int expectedNumberOfRows, int expectedNumberOfColumns) {
        return this.barcodeCountListener.getSpatialMap(expectedNumberOfRows, expectedNumberOfColumns);
    }

    public final void setModeEnabled(boolean enabled) {
        BarcodeCount barcodeCount = this.mode;
        if (barcodeCount == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeCount = null;
        }
        barcodeCount.setEnabled(enabled);
    }

    public final boolean isModeEnabled() {
        BarcodeCount barcodeCount = this.mode;
        if (barcodeCount == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeCount = null;
        }
        return barcodeCount.isEnabled();
    }

    public final void updateFeedback(String feedbackJson) {
        Intrinsics.checkNotNullParameter(feedbackJson, "feedbackJson");
        final BarcodeCountFeedback fromJson = BarcodeCountFeedbackDeserializer.fromJson(new JsonValue(feedbackJson));
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.count.ui.FrameworksBarcodeCountView$updateFeedback$1
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
                BarcodeCount barcodeCount;
                barcodeCount = FrameworksBarcodeCountView.this.mode;
                if (barcodeCount == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mode");
                    barcodeCount = null;
                }
                barcodeCount.setFeedback(fromJson);
            }
        });
    }

    public final void submitBarcodeCountStatusProviderCallbackResult(String statusJson) {
        Intrinsics.checkNotNullParameter(statusJson, "statusJson");
        this.statusProvider.submitCallbackResult(statusJson);
    }

    public final void enableHardwareTrigger(Integer hardwareTriggerKeyCode) {
        getView().enableHardwareTrigger(hardwareTriggerKeyCode);
    }

    @Override // com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView
    public void dispose() {
        this.barcodeCountListener.reset();
        this.viewListener.clearCache();
        BarcodeCount barcodeCount = null;
        getView().setListener(null);
        getView().setUiListener(null);
        BarcodeCount barcodeCount2 = this.mode;
        if (barcodeCount2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeCount2 = null;
        }
        barcodeCount2.removeListener(this.barcodeCountListener);
        BarcodeCount barcodeCount3 = this.mode;
        if (barcodeCount3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodeCount3 = null;
        }
        barcodeCount3.reset();
        DataCaptureContext dataCaptureContext = this.dataCaptureContext;
        BarcodeCount barcodeCount4 = this.mode;
        if (barcodeCount4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
        } else {
            barcodeCount = barcodeCount4;
        }
        dataCaptureContext.removeMode(barcodeCount);
    }

    /* compiled from: FrameworksBarcodeCountView.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JD\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012¨\u0006\u0013"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/ui/FrameworksBarcodeCountView$Companion;", "", "()V", "create", "Lcom/scandit/datacapture/frameworks/barcode/count/ui/FrameworksBarcodeCountView;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "context", "Landroid/content/Context;", "dataCaptureContext", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "viewCreationParams", "Lcom/scandit/datacapture/frameworks/barcode/count/data/BarcodeCountViewCreationData;", "modeDeserializer", "Lcom/scandit/datacapture/barcode/count/serialization/BarcodeCountDeserializer;", "viewDeserializer", "Lcom/scandit/datacapture/barcode/count/serialization/BarcodeCountViewDeserializer;", "mainThread", "Lcom/scandit/datacapture/frameworks/core/utils/MainThread;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FrameworksBarcodeCountView create(Emitter emitter, Context context, DataCaptureContext dataCaptureContext, BarcodeCountViewCreationData viewCreationParams, BarcodeCountDeserializer modeDeserializer, BarcodeCountViewDeserializer viewDeserializer, MainThread mainThread) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            Intrinsics.checkNotNullParameter(viewCreationParams, "viewCreationParams");
            Intrinsics.checkNotNullParameter(modeDeserializer, "modeDeserializer");
            Intrinsics.checkNotNullParameter(viewDeserializer, "viewDeserializer");
            Intrinsics.checkNotNullParameter(mainThread, "mainThread");
            FrameworksBarcodeCountView frameworksBarcodeCountView = new FrameworksBarcodeCountView(dataCaptureContext, new FrameworksBarcodeCountListener(emitter, viewCreationParams.getViewId(), null, 4, null), new FrameworksBarcodeCountCaptureListListener(emitter, viewCreationParams.getViewId()), new FrameworksBarcodeCountViewListener(emitter, viewCreationParams.getViewId()), new FrameworksBarcodeCountViewUiListener(emitter, viewCreationParams.getViewId()), new FrameworksBarcodeCountStatusProvider(emitter, viewCreationParams.getViewId()), modeDeserializer, viewDeserializer, mainThread);
            frameworksBarcodeCountView.deserializeView(context, viewCreationParams);
            return frameworksBarcodeCountView;
        }
    }
}
