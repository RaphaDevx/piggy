package com.scandit.capacitor.datacapture.core.handlers;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.capacitor.datacapture.core.data.ResizeAndMoveInfo;
import com.scandit.capacitor.datacapture.core.utils.ExtensionsKt;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.tealium.library.DataSources;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DataCaptureViewHandler.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\fJ\u0006\u0010\u0015\u001a\u00020\u0014J\u0006\u0010\u0016\u001a\u00020\u0014J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0005J\u0006\u0010\u0019\u001a\u00020\u0014J\u0016\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\nJ\u0010\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\fH\u0002J\b\u0010 \u001a\u00020\u0014H\u0002J\u0010\u0010!\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\u0004\u0018\u00010\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\n8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\""}, d2 = {"Lcom/scandit/capacitor/datacapture/core/handlers/DataCaptureViewHandler;", "", "<init>", "()V", "latestInfo", "Lcom/scandit/capacitor/datacapture/core/data/ResizeAndMoveInfo;", "isVisible", "", "dataCaptureViewReference", "Ljava/lang/ref/WeakReference;", "Lcom/scandit/datacapture/core/ui/DataCaptureView;", "webViewReference", "Landroid/view/View;", "webView", "getWebView", "()Landroid/view/View;", "dataCaptureView", "getDataCaptureView", "()Lcom/scandit/datacapture/core/ui/DataCaptureView;", "initialize", "", "setVisible", "setInvisible", "setResizeAndMoveInfo", "info", "disposeCurrentWebView", "addDataCaptureView", DataSources.EventTypeValue.ACTIVITY_EVENT_TYPE, "Landroid/app/Activity;", "removeDataCaptureView", "removeView", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "render", "renderNoAnimate", "scandit-capacitor-datacapture-core_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DataCaptureViewHandler {
    private boolean isVisible;
    private ResizeAndMoveInfo latestInfo = new ResizeAndMoveInfo(0, 0, 0, 0, false);
    private WeakReference<DataCaptureView> dataCaptureViewReference = new WeakReference<>(null);
    private WeakReference<View> webViewReference = new WeakReference<>(null);

    private final View getWebView() {
        return this.webViewReference.get();
    }

    public final DataCaptureView getDataCaptureView() {
        return this.dataCaptureViewReference.get();
    }

    public final void initialize(final View webView) {
        Intrinsics.checkNotNullParameter(webView, "webView");
        if (Intrinsics.areEqual(getWebView(), webView)) {
            return;
        }
        this.webViewReference = new WeakReference<>(webView);
        webView.post(new Runnable() { // from class: com.scandit.capacitor.datacapture.core.handlers.DataCaptureViewHandler$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                DataCaptureViewHandler.initialize$lambda$0(webView);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initialize$lambda$0(View view) {
        view.bringToFront();
        view.setBackgroundColor(0);
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

    public final void disposeCurrentWebView() {
        this.webViewReference = new WeakReference<>(null);
    }

    public final void addDataCaptureView(final DataCaptureView dataCaptureView, final Activity activity) {
        Intrinsics.checkNotNullParameter(dataCaptureView, "dataCaptureView");
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.dataCaptureViewReference = new WeakReference<>(dataCaptureView);
        activity.runOnUiThread(new Runnable() { // from class: com.scandit.capacitor.datacapture.core.handlers.DataCaptureViewHandler$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DataCaptureViewHandler.addDataCaptureView$lambda$1(activity, dataCaptureView, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addDataCaptureView$lambda$1(Activity activity, DataCaptureView dataCaptureView, DataCaptureViewHandler dataCaptureViewHandler) {
        activity.addContentView(dataCaptureView, new ViewGroup.LayoutParams((int) ExtensionsKt.pxFromDp(dataCaptureViewHandler.latestInfo.getWidth()), (int) ExtensionsKt.pxFromDp(dataCaptureViewHandler.latestInfo.getHeight())));
        dataCaptureViewHandler.render();
    }

    public final void removeDataCaptureView(DataCaptureView dataCaptureView) {
        Intrinsics.checkNotNullParameter(dataCaptureView, "dataCaptureView");
        if (Intrinsics.areEqual(getDataCaptureView(), dataCaptureView)) {
            this.dataCaptureViewReference = new WeakReference<>(null);
        }
        removeView(dataCaptureView);
    }

    private final void removeView(final View view) {
        view.post(new Runnable() { // from class: com.scandit.capacitor.datacapture.core.handlers.DataCaptureViewHandler$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ExtensionsKt.removeFromParent(view);
            }
        });
    }

    private final void render() {
        DataCaptureView dataCaptureView = getDataCaptureView();
        if (dataCaptureView == null) {
            return;
        }
        renderNoAnimate(dataCaptureView);
    }

    private final void renderNoAnimate(final DataCaptureView dataCaptureView) {
        dataCaptureView.post(new Runnable() { // from class: com.scandit.capacitor.datacapture.core.handlers.DataCaptureViewHandler$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                DataCaptureViewHandler.renderNoAnimate$lambda$4(DataCaptureView.this, this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void renderNoAnimate$lambda$4(DataCaptureView dataCaptureView, DataCaptureViewHandler dataCaptureViewHandler) {
        View view;
        dataCaptureView.setVisibility(dataCaptureViewHandler.isVisible ? 0 : 8);
        dataCaptureView.setX(ExtensionsKt.pxFromDp(dataCaptureViewHandler.latestInfo.getLeft()));
        dataCaptureView.setY(ExtensionsKt.pxFromDp(dataCaptureViewHandler.latestInfo.getTop()));
        ViewGroup.LayoutParams layoutParams = dataCaptureView.getLayoutParams();
        layoutParams.width = (int) ExtensionsKt.pxFromDp(dataCaptureViewHandler.latestInfo.getWidth());
        layoutParams.height = (int) ExtensionsKt.pxFromDp(dataCaptureViewHandler.latestInfo.getHeight());
        if (dataCaptureViewHandler.latestInfo.getShouldBeUnderWebView()) {
            View webView = dataCaptureViewHandler.getWebView();
            if (webView != null) {
                webView.bringToFront();
            }
            View webView2 = dataCaptureViewHandler.getWebView();
            ViewParent parent = webView2 != null ? webView2.getParent() : null;
            view = parent instanceof View ? (View) parent : null;
            if (view != null) {
                view.setTranslationZ(1.0f);
            }
        } else {
            dataCaptureView.bringToFront();
            View webView3 = dataCaptureViewHandler.getWebView();
            ViewParent parent2 = webView3 != null ? webView3.getParent() : null;
            view = parent2 instanceof View ? (View) parent2 : null;
            if (view != null) {
                view.setTranslationZ(-1.0f);
            }
        }
        dataCaptureView.requestLayout();
    }
}
