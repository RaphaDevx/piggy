package com.scandit.capacitor.datacapture.barcode.pick;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewGroupKt;
import com.scandit.capacitor.datacapture.core.data.ResizeAndMoveInfo;
import com.scandit.capacitor.datacapture.core.utils.ExtensionsKt;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickView;
import com.scandit.datacapture.frameworks.core.utils.DefaultMainThread;
import com.scandit.datacapture.frameworks.core.utils.MainThread;
import com.tealium.library.DataSources;
import io.sentry.rrweb.RRWebVideoEvent;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;

/* compiled from: BarcodePickViewHandler.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0016\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001e\u001a\u00020\u0019J\u0006\u0010\u001f\u001a\u00020\u0019J\u000e\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u0007J\u0006\u0010\"\u001a\u00020\u0019J\u0006\u0010#\u001a\u00020\u0019J\b\u0010$\u001a\u00020\u0019H\u0002J\u0018\u0010%\u001a\u00020\u00192\u0006\u0010&\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010'\u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00020\fH\u0002J\"\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u000e2\u0010\b\u0002\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010+H\u0002J\u0006\u0010,\u001a\u00020\u0019J\u0010\u0010-\u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006."}, d2 = {"Lcom/scandit/capacitor/datacapture/barcode/pick/BarcodePickViewHandler;", "", "mainThread", "Lcom/scandit/datacapture/frameworks/core/utils/MainThread;", "<init>", "(Lcom/scandit/datacapture/frameworks/core/utils/MainThread;)V", "latestInfo", "Lcom/scandit/capacitor/datacapture/core/data/ResizeAndMoveInfo;", "isVisible", "", "barcodePickViewContainerReference", "Ljava/lang/ref/WeakReference;", "Landroid/widget/FrameLayout;", "webViewReference", "Landroid/view/View;", "barcodePickViewContainer", "getBarcodePickViewContainer", "()Landroid/widget/FrameLayout;", "webView", "getWebView", "()Landroid/view/View;", "prepareContainer", "context", "Landroid/content/Context;", "addBarcodePickViewContainer", "", RRWebVideoEvent.JsonKeys.CONTAINER, DataSources.EventTypeValue.ACTIVITY_EVENT_TYPE, "Landroidx/appcompat/app/AppCompatActivity;", "attachWebView", "setVisible", "setInvisible", "setResizeAndMoveInfo", "info", "disposeCurrent", "disposeCurrentView", "disposeCurrentWebView", "addContainer", "barcodeFindViewContainer", "removeBarcodePickViewContainer", "removeView", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "uiBlock", "Lkotlin/Function0;", "render", "renderNoAnimate", "scandit-capacitor-datacapture-barcode_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodePickViewHandler {
    private WeakReference<FrameLayout> barcodePickViewContainerReference;
    private boolean isVisible;
    private ResizeAndMoveInfo latestInfo;
    private final MainThread mainThread;
    private WeakReference<View> webViewReference;

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodePickViewHandler() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public BarcodePickViewHandler(MainThread mainThread) {
        Intrinsics.checkNotNullParameter(mainThread, "mainThread");
        this.mainThread = mainThread;
        this.latestInfo = new ResizeAndMoveInfo(0, 0, 600, 600, false);
        this.isVisible = true;
    }

    public /* synthetic */ BarcodePickViewHandler(DefaultMainThread defaultMainThread, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? DefaultMainThread.INSTANCE.getInstance() : defaultMainThread);
    }

    public final FrameLayout getBarcodePickViewContainer() {
        WeakReference<FrameLayout> weakReference = this.barcodePickViewContainerReference;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private final View getWebView() {
        WeakReference<View> weakReference = this.webViewReference;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public final FrameLayout prepareContainer(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new FrameLayout(context);
    }

    public final void addBarcodePickViewContainer(FrameLayout container, AppCompatActivity activity) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (Intrinsics.areEqual(getBarcodePickViewContainer(), container)) {
            return;
        }
        disposeCurrentView();
        addContainer(container, activity);
    }

    public final void attachWebView(View webView, AppCompatActivity activity) {
        Intrinsics.checkNotNullParameter(webView, "webView");
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (Intrinsics.areEqual(getWebView(), webView)) {
            return;
        }
        this.webViewReference = new WeakReference<>(webView);
        webView.bringToFront();
        webView.setBackgroundColor(0);
    }

    public final void setVisible() {
        this.isVisible = true;
        render();
    }

    public final void setInvisible() {
        this.isVisible = false;
        render();
    }

    public final void setResizeAndMoveInfo(ResizeAndMoveInfo info) {
        Intrinsics.checkNotNullParameter(info, "info");
        this.latestInfo = info;
        render();
    }

    public final void disposeCurrent() {
        disposeCurrentView();
        disposeCurrentWebView();
    }

    public final void disposeCurrentView() {
        FrameLayout barcodePickViewContainer = getBarcodePickViewContainer();
        if (barcodePickViewContainer == null) {
            return;
        }
        removeBarcodePickViewContainer(barcodePickViewContainer);
    }

    private final void disposeCurrentWebView() {
        this.webViewReference = null;
    }

    private final void addContainer(FrameLayout barcodeFindViewContainer, final AppCompatActivity activity) {
        this.barcodePickViewContainerReference = new WeakReference<>(barcodeFindViewContainer);
        this.mainThread.runOnMainThread(new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.pick.BarcodePickViewHandler$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit addContainer$lambda$0;
                addContainer$lambda$0 = BarcodePickViewHandler.addContainer$lambda$0(AppCompatActivity.this, this);
                return addContainer$lambda$0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addContainer$lambda$0(AppCompatActivity appCompatActivity, BarcodePickViewHandler barcodePickViewHandler) {
        appCompatActivity.addContentView(barcodePickViewHandler.getBarcodePickViewContainer(), new ViewGroup.LayoutParams(-1, -1));
        barcodePickViewHandler.render();
        return Unit.INSTANCE;
    }

    private final void removeBarcodePickViewContainer(final FrameLayout barcodePickViewContainer) {
        this.barcodePickViewContainerReference = null;
        removeView(barcodePickViewContainer, new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.pick.BarcodePickViewHandler$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit removeBarcodePickViewContainer$lambda$1;
                removeBarcodePickViewContainer$lambda$1 = BarcodePickViewHandler.removeBarcodePickViewContainer$lambda$1(barcodePickViewContainer);
                return removeBarcodePickViewContainer$lambda$1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit removeBarcodePickViewContainer$lambda$1(FrameLayout frameLayout) {
        FrameLayout frameLayout2 = frameLayout;
        Object firstOrNull = SequencesKt.firstOrNull(ViewGroupKt.getChildren(frameLayout2));
        BarcodePickView barcodePickView = firstOrNull instanceof BarcodePickView ? (BarcodePickView) firstOrNull : null;
        if (barcodePickView != null) {
            barcodePickView.setListener(null);
        }
        Object firstOrNull2 = SequencesKt.firstOrNull(ViewGroupKt.getChildren(frameLayout2));
        BarcodePickView barcodePickView2 = firstOrNull2 instanceof BarcodePickView ? (BarcodePickView) firstOrNull2 : null;
        if (barcodePickView2 != null) {
            barcodePickView2.setUiListener(null);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void removeView$default(BarcodePickViewHandler barcodePickViewHandler, View view, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        barcodePickViewHandler.removeView(view, function0);
    }

    private final void removeView(final View view, final Function0<Unit> uiBlock) {
        this.mainThread.runOnMainThread(new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.pick.BarcodePickViewHandler$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit removeView$lambda$2;
                removeView$lambda$2 = BarcodePickViewHandler.removeView$lambda$2(view, uiBlock);
                return removeView$lambda$2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit removeView$lambda$2(View view, Function0 function0) {
        ExtensionsKt.removeFromParent(view);
        if (function0 != null) {
            function0.invoke();
        }
        return Unit.INSTANCE;
    }

    public final void render() {
        FrameLayout barcodePickViewContainer = getBarcodePickViewContainer();
        if (barcodePickViewContainer == null) {
            return;
        }
        renderNoAnimate(barcodePickViewContainer);
    }

    private final void renderNoAnimate(final FrameLayout barcodePickViewContainer) {
        barcodePickViewContainer.post(new Runnable() { // from class: com.scandit.capacitor.datacapture.barcode.pick.BarcodePickViewHandler$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                BarcodePickViewHandler.renderNoAnimate$lambda$4(barcodePickViewContainer, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void renderNoAnimate$lambda$4(FrameLayout frameLayout, BarcodePickViewHandler barcodePickViewHandler) {
        Object parent;
        frameLayout.setVisibility(barcodePickViewHandler.isVisible ? 0 : 8);
        frameLayout.setX(ExtensionsKt.pxFromDp(barcodePickViewHandler.latestInfo.getLeft()));
        frameLayout.setY(ExtensionsKt.pxFromDp(barcodePickViewHandler.latestInfo.getTop()));
        ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        if (barcodePickViewHandler.latestInfo.getShouldBeUnderWebView()) {
            View webView = barcodePickViewHandler.getWebView();
            if (webView != null) {
                webView.bringToFront();
            }
            View webView2 = barcodePickViewHandler.getWebView();
            parent = webView2 != null ? webView2.getParent() : null;
            Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
            ((View) parent).setTranslationZ(1.0f);
        } else {
            frameLayout.bringToFront();
            View webView3 = barcodePickViewHandler.getWebView();
            parent = webView3 != null ? webView3.getParent() : null;
            Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
            ((View) parent).setTranslationZ(-1.0f);
        }
        frameLayout.requestLayout();
    }
}
