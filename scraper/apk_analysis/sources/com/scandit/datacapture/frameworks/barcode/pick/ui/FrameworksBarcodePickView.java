package com.scandit.datacapture.frameworks.barcode.pick.ui;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.pick.capture.BarcodePick;
import com.scandit.datacapture.barcode.pick.serialization.BarcodePickDeserializer;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickStatusIconStyle;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickView;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.frameworks.barcode.pick.data.BarcodePickViewCreationData;
import com.scandit.datacapture.frameworks.barcode.pick.listeners.FrameworksBarcodePickActionListener;
import com.scandit.datacapture.frameworks.barcode.pick.listeners.FrameworksBarcodePickAsyncMapperProductProviderCallback;
import com.scandit.datacapture.frameworks.barcode.pick.listeners.FrameworksBarcodePickListener;
import com.scandit.datacapture.frameworks.barcode.pick.listeners.FrameworksBarcodePickScanningListener;
import com.scandit.datacapture.frameworks.barcode.pick.listeners.FrameworksBarcodePickViewHighlightStyleAsyncProvider;
import com.scandit.datacapture.frameworks.barcode.pick.listeners.FrameworksBarcodePickViewHighlightStyleCustomViewProvider;
import com.scandit.datacapture.frameworks.barcode.pick.listeners.FrameworksBarcodePickViewListener;
import com.scandit.datacapture.frameworks.barcode.pick.listeners.FrameworksBarcodePickViewUiListener;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView;
import com.scandit.datacapture.frameworks.core.utils.DefaultAdvancedOverlayViewCache;
import com.scandit.datacapture.frameworks.core.utils.DefaultMainThread;
import com.scandit.datacapture.frameworks.core.utils.MainThread;
import com.tealium.library.DataSources;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodePickView.kt */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0013\u0018\u0000 V2\u00020\u0001:\u0001VB[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015¢\u0006\u0002\u0010\u0016J\u0006\u0010&\u001a\u00020'J\u0006\u0010(\u001a\u00020'J\u0006\u0010)\u001a\u00020'J\u0006\u0010*\u001a\u00020'J\u0006\u0010+\u001a\u00020'J \u0010,\u001a\u00020'2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0002J\b\u00103\u001a\u00020'H\u0016J\u0018\u00104\u001a\u00020'2\u0006\u00105\u001a\u00020\u00182\b\u00106\u001a\u0004\u0018\u000107J*\u00108\u001a\u00020'2\u0006\u00109\u001a\u00020:2\u0006\u00105\u001a\u00020\u00182\b\u0010;\u001a\u0004\u0018\u00010<2\b\u0010=\u001a\u0004\u0018\u00010>J\u000e\u0010?\u001a\u00020'2\u0006\u0010@\u001a\u000207J\u0016\u0010A\u001a\u00020'2\u0006\u0010B\u001a\u0002072\u0006\u0010C\u001a\u00020DJ\u0006\u0010E\u001a\u00020'J\u0006\u0010F\u001a\u00020'J\u0006\u0010G\u001a\u00020'J\u0006\u0010H\u001a\u00020'J\u0010\u0010I\u001a\u00020'2\u0006\u0010J\u001a\u000202H\u0002J\u0010\u0010K\u001a\u00020'2\u0006\u0010J\u001a\u000202H\u0002J\u0006\u0010L\u001a\u00020'J\u0006\u0010M\u001a\u00020'J\u0006\u0010N\u001a\u00020'J\u0006\u0010O\u001a\u00020'J\u0006\u0010P\u001a\u00020'J\u0006\u0010Q\u001a\u00020'J\u0006\u0010R\u001a\u00020'J\u0006\u0010S\u001a\u00020'J\u000e\u0010T\u001a\u00020'2\u0006\u0010U\u001a\u000207R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010 \u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001f@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006W"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/ui/FrameworksBarcodePickView;", "Lcom/scandit/datacapture/frameworks/core/ui/FrameworksBaseView;", "actionListener", "Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickActionListener;", "scanningListener", "Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickScanningListener;", "viewListener", "Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickViewListener;", "viewUiListener", "Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickViewUiListener;", "pickListener", "Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickListener;", "productProviderCallback", "Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickAsyncMapperProductProviderCallback;", "customViewProvider", "Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickViewHighlightStyleCustomViewProvider;", "customStyleProvider", "Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickViewHighlightStyleAsyncProvider;", "deserializer", "Lcom/scandit/datacapture/barcode/pick/serialization/BarcodePickDeserializer;", "mainThread", "Lcom/scandit/datacapture/frameworks/core/utils/MainThread;", "(Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickActionListener;Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickScanningListener;Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickViewListener;Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickViewUiListener;Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickListener;Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickAsyncMapperProductProviderCallback;Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickViewHighlightStyleCustomViewProvider;Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickViewHighlightStyleAsyncProvider;Lcom/scandit/datacapture/barcode/pick/serialization/BarcodePickDeserializer;Lcom/scandit/datacapture/frameworks/core/utils/MainThread;)V", "_viewId", "", "mode", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePick;", "parentId", "getParentId", "()Ljava/lang/Integer;", "<set-?>", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickView;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "getView", "()Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickView;", "viewId", "getViewId", "()I", "addActionListener", "", "addBarcodePickListener", "addScanningListener", "addViewListener", "addViewUiListener", "deserializeView", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "dataCaptureContext", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "viewCreationParams", "Lcom/scandit/datacapture/frameworks/barcode/pick/data/BarcodePickViewCreationData;", "dispose", "finishBarcodePickViewHighlightStyleAsyncProviderStyleForRequest", "requestId", "responseJson", "", "finishBarcodePickViewHighlightStyleCustomViewProviderViewForRequest", "context", "Landroid/content/Context;", "viewBytes", "", "statusIconStyle", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;", "finishOnProductIdentifierForItems", "itemsJson", "finishPickAction", "itemData", "result", "", "freeze", "hide", "onPause", "onResume", "postModeChanges", "changeParams", "postViewChanges", "removeActionListener", "removeBarcodePickListener", "removeScanningListener", "removeViewListener", "removeViewUiListener", "show", "start", "stop", "updateView", "viewJson", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksBarcodePickView implements FrameworksBaseView {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int _viewId;
    private final FrameworksBarcodePickActionListener actionListener;
    private final FrameworksBarcodePickViewHighlightStyleAsyncProvider customStyleProvider;
    private final FrameworksBarcodePickViewHighlightStyleCustomViewProvider customViewProvider;
    private final BarcodePickDeserializer deserializer;
    private final MainThread mainThread;
    private BarcodePick mode;
    private final FrameworksBarcodePickListener pickListener;
    private final FrameworksBarcodePickAsyncMapperProductProviderCallback productProviderCallback;
    private final FrameworksBarcodePickScanningListener scanningListener;
    private BarcodePickView view;
    private final FrameworksBarcodePickViewListener viewListener;
    private final FrameworksBarcodePickViewUiListener viewUiListener;

    @Override // com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView
    /* renamed from: getParentId */
    public Integer get_parentId() {
        return null;
    }

    public FrameworksBarcodePickView(FrameworksBarcodePickActionListener actionListener, FrameworksBarcodePickScanningListener scanningListener, FrameworksBarcodePickViewListener viewListener, FrameworksBarcodePickViewUiListener viewUiListener, FrameworksBarcodePickListener pickListener, FrameworksBarcodePickAsyncMapperProductProviderCallback productProviderCallback, FrameworksBarcodePickViewHighlightStyleCustomViewProvider frameworksBarcodePickViewHighlightStyleCustomViewProvider, FrameworksBarcodePickViewHighlightStyleAsyncProvider frameworksBarcodePickViewHighlightStyleAsyncProvider, BarcodePickDeserializer deserializer, MainThread mainThread) {
        Intrinsics.checkNotNullParameter(actionListener, "actionListener");
        Intrinsics.checkNotNullParameter(scanningListener, "scanningListener");
        Intrinsics.checkNotNullParameter(viewListener, "viewListener");
        Intrinsics.checkNotNullParameter(viewUiListener, "viewUiListener");
        Intrinsics.checkNotNullParameter(pickListener, "pickListener");
        Intrinsics.checkNotNullParameter(productProviderCallback, "productProviderCallback");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(mainThread, "mainThread");
        this.actionListener = actionListener;
        this.scanningListener = scanningListener;
        this.viewListener = viewListener;
        this.viewUiListener = viewUiListener;
        this.pickListener = pickListener;
        this.productProviderCallback = productProviderCallback;
        this.customViewProvider = frameworksBarcodePickViewHighlightStyleCustomViewProvider;
        this.customStyleProvider = frameworksBarcodePickViewHighlightStyleAsyncProvider;
        this.deserializer = deserializer;
        this.mainThread = mainThread;
    }

    public /* synthetic */ FrameworksBarcodePickView(FrameworksBarcodePickActionListener frameworksBarcodePickActionListener, FrameworksBarcodePickScanningListener frameworksBarcodePickScanningListener, FrameworksBarcodePickViewListener frameworksBarcodePickViewListener, FrameworksBarcodePickViewUiListener frameworksBarcodePickViewUiListener, FrameworksBarcodePickListener frameworksBarcodePickListener, FrameworksBarcodePickAsyncMapperProductProviderCallback frameworksBarcodePickAsyncMapperProductProviderCallback, FrameworksBarcodePickViewHighlightStyleCustomViewProvider frameworksBarcodePickViewHighlightStyleCustomViewProvider, FrameworksBarcodePickViewHighlightStyleAsyncProvider frameworksBarcodePickViewHighlightStyleAsyncProvider, BarcodePickDeserializer barcodePickDeserializer, MainThread mainThread, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(frameworksBarcodePickActionListener, frameworksBarcodePickScanningListener, frameworksBarcodePickViewListener, frameworksBarcodePickViewUiListener, frameworksBarcodePickListener, frameworksBarcodePickAsyncMapperProductProviderCallback, frameworksBarcodePickViewHighlightStyleCustomViewProvider, frameworksBarcodePickViewHighlightStyleAsyncProvider, barcodePickDeserializer, (i & 512) != 0 ? DefaultMainThread.INSTANCE.getInstance() : mainThread);
    }

    @Override // com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView
    /* renamed from: getViewId, reason: from getter */
    public int get_viewId() {
        return this._viewId;
    }

    public final BarcodePickView getView() {
        BarcodePickView barcodePickView = this.view;
        if (barcodePickView != null) {
            return barcodePickView;
        }
        Intrinsics.throwUninitializedPropertyAccessException(DataSources.EventTypeValue.VIEW_EVENT_TYPE);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deserializeView(ViewGroup container, DataCaptureContext dataCaptureContext, BarcodePickViewCreationData viewCreationParams) {
        BarcodePickView viewFromJson;
        BarcodePick barcodePick;
        BarcodePick barcodePick2;
        this._viewId = viewCreationParams.getViewId();
        this.mode = this.deserializer.modeFromJson(dataCaptureContext, this.deserializer.asyncMapperProductProviderFromJson(viewCreationParams.getBarcodeProviderJson(), this.productProviderCallback), viewCreationParams.getModeJson());
        postModeChanges(viewCreationParams);
        BarcodePick barcodePick3 = null;
        if (this.customViewProvider != null) {
            BarcodePickDeserializer barcodePickDeserializer = this.deserializer;
            BarcodePick barcodePick4 = this.mode;
            if (barcodePick4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mode");
                barcodePick2 = null;
            } else {
                barcodePick2 = barcodePick4;
            }
            viewFromJson = barcodePickDeserializer.viewFromJson(container, dataCaptureContext, barcodePick2, viewCreationParams.getViewJson(), this.customViewProvider);
        } else if (this.customStyleProvider != null) {
            BarcodePickDeserializer barcodePickDeserializer2 = this.deserializer;
            BarcodePick barcodePick5 = this.mode;
            if (barcodePick5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mode");
                barcodePick = null;
            } else {
                barcodePick = barcodePick5;
            }
            viewFromJson = barcodePickDeserializer2.viewFromJson(container, dataCaptureContext, barcodePick, viewCreationParams.getViewJson(), this.customStyleProvider);
        } else {
            BarcodePickDeserializer barcodePickDeserializer3 = this.deserializer;
            BarcodePick barcodePick6 = this.mode;
            if (barcodePick6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mode");
            } else {
                barcodePick3 = barcodePick6;
            }
            viewFromJson = barcodePickDeserializer3.viewFromJson(container, dataCaptureContext, barcodePick3, viewCreationParams.getViewJson());
        }
        this.view = viewFromJson;
        postViewChanges(viewCreationParams);
        container.setTag(Integer.valueOf(get_viewId()));
        getView().setTag(Integer.valueOf(get_viewId()));
    }

    private final void postModeChanges(BarcodePickViewCreationData changeParams) {
        if (changeParams.getHasModeListener()) {
            addBarcodePickListener();
        } else {
            removeBarcodePickListener();
        }
        if (changeParams.getHasScanningListener()) {
            addScanningListener();
        } else {
            removeScanningListener();
        }
    }

    private final void postViewChanges(BarcodePickViewCreationData changeParams) {
        if (changeParams.getHasViewListeners()) {
            addViewListener();
        } else {
            removeViewListener();
        }
        if (changeParams.getHasViewUiListener()) {
            addViewUiListener();
        } else {
            removeViewUiListener();
        }
        if (changeParams.getHasViewActionListener()) {
            addActionListener();
        } else {
            removeActionListener();
        }
        if (changeParams.getViewIsStarted()) {
            start();
        }
    }

    public final void updateView(String viewJson) {
        Intrinsics.checkNotNullParameter(viewJson, "viewJson");
        this.deserializer.updateViewFromJson(getView(), viewJson);
    }

    public final void addViewListener() {
        Log.d("Konrad", "adding view listener to viewId: " + get_viewId());
        getView().setListener(this.viewListener);
    }

    public final void removeViewListener() {
        Log.d("Konrad", "removing view listener from viewId: " + get_viewId());
        getView().setListener(null);
    }

    public final void addViewUiListener() {
        getView().setUiListener(this.viewUiListener);
    }

    public final void removeViewUiListener() {
        getView().setUiListener(null);
    }

    public final void addActionListener() {
        getView().addActionListener(this.actionListener);
    }

    public final void removeActionListener() {
        getView().removeActionListener(this.actionListener);
    }

    public final void addScanningListener() {
        this.scanningListener.enable();
        BarcodePick barcodePick = this.mode;
        if (barcodePick == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodePick = null;
        }
        barcodePick.addScanningListener(this.scanningListener);
    }

    public final void removeScanningListener() {
        this.scanningListener.disable();
        BarcodePick barcodePick = this.mode;
        if (barcodePick == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodePick = null;
        }
        barcodePick.removeScanningListener(this.scanningListener);
    }

    public final void addBarcodePickListener() {
        this.pickListener.enable();
        BarcodePick barcodePick = this.mode;
        if (barcodePick == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodePick = null;
        }
        barcodePick.addListener(this.pickListener);
    }

    public final void removeBarcodePickListener() {
        this.pickListener.disable();
        BarcodePick barcodePick = this.mode;
        if (barcodePick == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodePick = null;
        }
        barcodePick.removeListener(this.pickListener);
    }

    public final void finishOnProductIdentifierForItems(String itemsJson) {
        Intrinsics.checkNotNullParameter(itemsJson, "itemsJson");
        this.productProviderCallback.finishOnProductIdentifierForItems(itemsJson);
    }

    public final void finishPickAction(String itemData, boolean result) {
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        this.actionListener.finishPickAction(itemData, result);
    }

    public final void onResume() {
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.pick.ui.FrameworksBarcodePickView$onResume$1
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
                FrameworksBarcodePickView.this.getView().onResume();
            }
        });
    }

    public final void onPause() {
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.pick.ui.FrameworksBarcodePickView$onPause$1
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
                FrameworksBarcodePickView.this.getView().onPause();
            }
        });
    }

    public final void start() {
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.pick.ui.FrameworksBarcodePickView$start$1
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
                FrameworksBarcodePickView.this.getView().onResume();
                FrameworksBarcodePickView.this.getView().start();
            }
        });
    }

    public final void stop() {
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.pick.ui.FrameworksBarcodePickView$stop$1
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
                FrameworksBarcodePickView.this.getView().stop();
            }
        });
    }

    public final void freeze() {
        getView().freeze();
    }

    public final void finishBarcodePickViewHighlightStyleCustomViewProviderViewForRequest(Context context, int requestId, byte[] viewBytes, BarcodePickStatusIconStyle statusIconStyle) {
        ImageView imageView;
        Intrinsics.checkNotNullParameter(context, "context");
        if (viewBytes != null) {
            imageView = new ImageView(context);
            imageView.setImageBitmap(DefaultAdvancedOverlayViewCache.INSTANCE.parse(viewBytes));
        } else {
            imageView = null;
        }
        FrameworksBarcodePickViewHighlightStyleCustomViewProvider frameworksBarcodePickViewHighlightStyleCustomViewProvider = this.customViewProvider;
        if (frameworksBarcodePickViewHighlightStyleCustomViewProvider != null) {
            frameworksBarcodePickViewHighlightStyleCustomViewProvider.finishViewForRequest(requestId, imageView, statusIconStyle);
        }
    }

    public final void finishBarcodePickViewHighlightStyleAsyncProviderStyleForRequest(int requestId, String responseJson) {
        FrameworksBarcodePickViewHighlightStyleAsyncProvider frameworksBarcodePickViewHighlightStyleAsyncProvider = this.customStyleProvider;
        if (frameworksBarcodePickViewHighlightStyleAsyncProvider != null) {
            frameworksBarcodePickViewHighlightStyleAsyncProvider.finishStyleForRequest(requestId, responseJson);
        }
    }

    public final void hide() {
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.pick.ui.FrameworksBarcodePickView$hide$1
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
                FrameworksBarcodePickView.this.onPause();
                FrameworksBarcodePickView.this.getView().setVisibility(8);
            }
        });
    }

    public final void show() {
        this.mainThread.runOnMainThread(new Function0<Unit>() { // from class: com.scandit.datacapture.frameworks.barcode.pick.ui.FrameworksBarcodePickView$show$1
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
                FrameworksBarcodePickView.this.getView().setVisibility(0);
                FrameworksBarcodePickView.this.getView().bringToFront();
                FrameworksBarcodePickView.this.onResume();
            }
        });
    }

    @Override // com.scandit.datacapture.frameworks.core.ui.FrameworksBaseView
    public void dispose() {
        BarcodePick barcodePick = null;
        getView().setUiListener(null);
        getView().setListener(null);
        getView().removeActionListener(this.actionListener);
        getView().onPause();
        getView().onDestroy();
        BarcodePick barcodePick2 = this.mode;
        if (barcodePick2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
            barcodePick2 = null;
        }
        barcodePick2.removeScanningListener(this.scanningListener);
        BarcodePick barcodePick3 = this.mode;
        if (barcodePick3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mode");
        } else {
            barcodePick = barcodePick3;
        }
        barcodePick.removeListener(this.pickListener);
        this.productProviderCallback.cancel();
        FrameworksBarcodePickViewHighlightStyleCustomViewProvider frameworksBarcodePickViewHighlightStyleCustomViewProvider = this.customViewProvider;
        if (frameworksBarcodePickViewHighlightStyleCustomViewProvider != null) {
            frameworksBarcodePickViewHighlightStyleCustomViewProvider.dispose();
        }
    }

    /* compiled from: FrameworksBarcodePickView.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J:\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010¨\u0006\u0011"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/ui/FrameworksBarcodePickView$Companion;", "", "()V", "create", "Lcom/scandit/datacapture/frameworks/barcode/pick/ui/FrameworksBarcodePickView;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "dataCaptureContext", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "viewCreationParams", "Lcom/scandit/datacapture/frameworks/barcode/pick/data/BarcodePickViewCreationData;", "deserializer", "Lcom/scandit/datacapture/barcode/pick/serialization/BarcodePickDeserializer;", "mainThread", "Lcom/scandit/datacapture/frameworks/core/utils/MainThread;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ FrameworksBarcodePickView create$default(Companion companion, Emitter emitter, ViewGroup viewGroup, DataCaptureContext dataCaptureContext, BarcodePickViewCreationData barcodePickViewCreationData, BarcodePickDeserializer barcodePickDeserializer, MainThread mainThread, int i, Object obj) {
            if ((i & 16) != 0) {
                barcodePickDeserializer = new BarcodePickDeserializer();
            }
            BarcodePickDeserializer barcodePickDeserializer2 = barcodePickDeserializer;
            if ((i & 32) != 0) {
                mainThread = DefaultMainThread.INSTANCE.getInstance();
            }
            return companion.create(emitter, viewGroup, dataCaptureContext, barcodePickViewCreationData, barcodePickDeserializer2, mainThread);
        }

        public final FrameworksBarcodePickView create(Emitter emitter, ViewGroup container, DataCaptureContext dataCaptureContext, BarcodePickViewCreationData viewCreationParams, BarcodePickDeserializer deserializer, MainThread mainThread) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            Intrinsics.checkNotNullParameter(container, "container");
            Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
            Intrinsics.checkNotNullParameter(viewCreationParams, "viewCreationParams");
            Intrinsics.checkNotNullParameter(deserializer, "deserializer");
            Intrinsics.checkNotNullParameter(mainThread, "mainThread");
            FrameworksBarcodePickView frameworksBarcodePickView = new FrameworksBarcodePickView(new FrameworksBarcodePickActionListener(emitter, viewCreationParams.getViewId()), new FrameworksBarcodePickScanningListener(emitter, viewCreationParams.getViewId(), null, null, 12, null), new FrameworksBarcodePickViewListener(emitter, viewCreationParams.getViewId()), new FrameworksBarcodePickViewUiListener(emitter, viewCreationParams.getViewId()), new FrameworksBarcodePickListener(emitter, viewCreationParams.getViewId()), new FrameworksBarcodePickAsyncMapperProductProviderCallback(emitter, viewCreationParams.getViewId()), viewCreationParams.getHighlightType() == BarcodePickViewCreationData.HighlightType.CUSTOM_VIEW ? new FrameworksBarcodePickViewHighlightStyleCustomViewProvider(emitter, viewCreationParams.getViewId()) : null, (viewCreationParams.getHasAsyncProvider() && (viewCreationParams.getHighlightType() == BarcodePickViewCreationData.HighlightType.DOT_WITH_ICONS || viewCreationParams.getHighlightType() == BarcodePickViewCreationData.HighlightType.RECTANGULAR_WITH_ICONS)) ? new FrameworksBarcodePickViewHighlightStyleAsyncProvider(emitter, viewCreationParams.getViewId()) : null, deserializer, mainThread);
            frameworksBarcodePickView.deserializeView(container, dataCaptureContext, viewCreationParams);
            return frameworksBarcodePickView;
        }
    }
}
