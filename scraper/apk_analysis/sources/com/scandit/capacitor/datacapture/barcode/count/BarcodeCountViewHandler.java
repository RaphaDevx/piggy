package com.scandit.capacitor.datacapture.barcode.count;

import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.capacitor.datacapture.core.data.ResizeAndMoveInfo;
import com.scandit.capacitor.datacapture.core.utils.ExtensionsKt;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView;
import com.scandit.datacapture.frameworks.core.utils.DefaultMainThread;
import com.scandit.datacapture.frameworks.core.utils.MainThread;
import com.tealium.library.DataSources;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarcodeCountViewHandler.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001b\u001a\u00020\u0016J\u0006\u0010\u001c\u001a\u00020\u0016J\u000e\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u0007J\u0006\u0010\u001f\u001a\u00020\u0016J\u0006\u0010 \u001a\u00020\u0016J\b\u0010!\u001a\u00020\u0016H\u0002J\u0018\u0010\"\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010#\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\fH\u0002J\"\u0010$\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u000e2\u0010\b\u0002\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010'H\u0002J\u0006\u0010(\u001a\u00020\u0016J\u0010\u0010)\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006*"}, d2 = {"Lcom/scandit/capacitor/datacapture/barcode/count/BarcodeCountViewHandler;", "", "mainThread", "Lcom/scandit/datacapture/frameworks/core/utils/MainThread;", "<init>", "(Lcom/scandit/datacapture/frameworks/core/utils/MainThread;)V", "latestInfo", "Lcom/scandit/capacitor/datacapture/core/data/ResizeAndMoveInfo;", "isVisible", "", "barcodeCountViewReference", "Ljava/lang/ref/WeakReference;", "Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountView;", "webViewReference", "Landroid/view/View;", "currentBarcodeCountView", "getCurrentBarcodeCountView", "()Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountView;", "webView", "getWebView", "()Landroid/view/View;", "attachBarcodeCountView", "", "barcodeCountView", DataSources.EventTypeValue.ACTIVITY_EVENT_TYPE, "Landroidx/appcompat/app/AppCompatActivity;", "attachWebView", "setVisible", "setInvisible", "setResizeAndMoveInfo", "info", "disposeCurrent", "disposeCurrentView", "disposeCurrentWebView", "addBarcodeCountView", "removeBarcodeCountView", "removeView", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "uiBlock", "Lkotlin/Function0;", "render", "renderNoAnimate", "scandit-capacitor-datacapture-barcode_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeCountViewHandler {
    private WeakReference<BarcodeCountView> barcodeCountViewReference;
    private boolean isVisible;
    private ResizeAndMoveInfo latestInfo;
    private final MainThread mainThread;
    private WeakReference<View> webViewReference;

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodeCountViewHandler() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public BarcodeCountViewHandler(MainThread mainThread) {
        Intrinsics.checkNotNullParameter(mainThread, "mainThread");
        this.mainThread = mainThread;
        this.latestInfo = new ResizeAndMoveInfo(0, 0, 600, 600, false);
        this.isVisible = true;
    }

    public /* synthetic */ BarcodeCountViewHandler(DefaultMainThread defaultMainThread, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? DefaultMainThread.INSTANCE.getInstance() : defaultMainThread);
    }

    public final BarcodeCountView getCurrentBarcodeCountView() {
        WeakReference<BarcodeCountView> weakReference = this.barcodeCountViewReference;
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

    public final void attachBarcodeCountView(BarcodeCountView barcodeCountView, AppCompatActivity activity) {
        Intrinsics.checkNotNullParameter(barcodeCountView, "barcodeCountView");
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (Intrinsics.areEqual(getCurrentBarcodeCountView(), barcodeCountView)) {
            return;
        }
        disposeCurrentView();
        addBarcodeCountView(barcodeCountView, activity);
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
        BarcodeCountView currentBarcodeCountView = getCurrentBarcodeCountView();
        if (currentBarcodeCountView == null) {
            return;
        }
        removeBarcodeCountView(currentBarcodeCountView);
    }

    private final void disposeCurrentWebView() {
        this.webViewReference = null;
    }

    private final void addBarcodeCountView(final BarcodeCountView barcodeCountView, final AppCompatActivity activity) {
        this.barcodeCountViewReference = new WeakReference<>(barcodeCountView);
        this.mainThread.runOnMainThread(new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.count.BarcodeCountViewHandler$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit addBarcodeCountView$lambda$0;
                addBarcodeCountView$lambda$0 = BarcodeCountViewHandler.addBarcodeCountView$lambda$0(AppCompatActivity.this, barcodeCountView, this);
                return addBarcodeCountView$lambda$0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addBarcodeCountView$lambda$0(AppCompatActivity appCompatActivity, BarcodeCountView barcodeCountView, BarcodeCountViewHandler barcodeCountViewHandler) {
        appCompatActivity.addContentView(barcodeCountView, new ViewGroup.LayoutParams(-1, -1));
        barcodeCountViewHandler.render();
        return Unit.INSTANCE;
    }

    private final void removeBarcodeCountView(final BarcodeCountView barcodeCountView) {
        this.barcodeCountViewReference = null;
        removeView(barcodeCountView, new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.count.BarcodeCountViewHandler$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit removeBarcodeCountView$lambda$1;
                removeBarcodeCountView$lambda$1 = BarcodeCountViewHandler.removeBarcodeCountView$lambda$1(BarcodeCountView.this);
                return removeBarcodeCountView$lambda$1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit removeBarcodeCountView$lambda$1(BarcodeCountView barcodeCountView) {
        barcodeCountView.setListener(null);
        barcodeCountView.setUiListener(null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void removeView$default(BarcodeCountViewHandler barcodeCountViewHandler, View view, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        barcodeCountViewHandler.removeView(view, function0);
    }

    private final void removeView(final View view, final Function0<Unit> uiBlock) {
        this.mainThread.runOnMainThread(new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.count.BarcodeCountViewHandler$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit removeView$lambda$2;
                removeView$lambda$2 = BarcodeCountViewHandler.removeView$lambda$2(view, uiBlock);
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
        BarcodeCountView currentBarcodeCountView = getCurrentBarcodeCountView();
        if (currentBarcodeCountView == null) {
            return;
        }
        renderNoAnimate(currentBarcodeCountView);
    }

    private final void renderNoAnimate(final BarcodeCountView barcodeCountView) {
        barcodeCountView.post(new Runnable() { // from class: com.scandit.capacitor.datacapture.barcode.count.BarcodeCountViewHandler$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BarcodeCountViewHandler.renderNoAnimate$lambda$4(BarcodeCountView.this, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void renderNoAnimate$lambda$4(BarcodeCountView barcodeCountView, BarcodeCountViewHandler barcodeCountViewHandler) {
        Object parent;
        barcodeCountView.setVisibility(barcodeCountViewHandler.isVisible ? 0 : 8);
        barcodeCountView.setX(ExtensionsKt.pxFromDp(barcodeCountViewHandler.latestInfo.getLeft()));
        barcodeCountView.setY(ExtensionsKt.pxFromDp(barcodeCountViewHandler.latestInfo.getTop()));
        ViewGroup.LayoutParams layoutParams = barcodeCountView.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        if (barcodeCountViewHandler.latestInfo.getShouldBeUnderWebView()) {
            View webView = barcodeCountViewHandler.getWebView();
            if (webView != null) {
                webView.bringToFront();
            }
            View webView2 = barcodeCountViewHandler.getWebView();
            parent = webView2 != null ? webView2.getParent() : null;
            Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
            ((View) parent).setTranslationZ(1.0f);
        } else {
            barcodeCountView.bringToFront();
            View webView3 = barcodeCountViewHandler.getWebView();
            parent = webView3 != null ? webView3.getParent() : null;
            Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
            ((View) parent).setTranslationZ(-1.0f);
        }
        barcodeCountView.requestLayout();
    }
}
