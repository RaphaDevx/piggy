package com.scandit.capacitor.datacapture.barcode.find;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.capacitor.datacapture.core.utils.ExtensionsKt;
import com.scandit.datacapture.frameworks.core.utils.DefaultMainThread;
import com.scandit.datacapture.frameworks.core.utils.MainThread;
import com.tealium.library.DataSources;
import io.sentry.rrweb.RRWebVideoEvent;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarcodeFindViewHandler.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0014J\u001e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u000eJ\u000e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bJ\u000e\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bJ\u000e\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bJ\u0006\u0010\u001f\u001a\u00020\u0016J\b\u0010 \u001a\u00020\u0016H\u0002J \u0010!\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\u000eH\u0002J\u0018\u0010$\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010%\u001a\u00020\fH\u0002J\b\u0010&\u001a\u00020\u0016H\u0002J\b\u0010'\u001a\u00020\u0016H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006("}, d2 = {"Lcom/scandit/capacitor/datacapture/barcode/find/BarcodeFindViewHandler;", "", "mainThread", "Lcom/scandit/datacapture/frameworks/core/utils/MainThread;", "<init>", "(Lcom/scandit/datacapture/frameworks/core/utils/MainThread;)V", "containers", "", "", "Ljava/lang/ref/WeakReference;", "Landroid/widget/FrameLayout;", "containerVisibility", "", "webViewReference", "Landroid/view/View;", "webView", "getWebView", "()Landroid/view/View;", "prepareContainer", "context", "Landroid/content/Context;", "addBarcodeFindViewContainer", "", "viewId", RRWebVideoEvent.JsonKeys.CONTAINER, DataSources.EventTypeValue.ACTIVITY_EVENT_TYPE, "Landroidx/appcompat/app/AppCompatActivity;", "attachWebView", "setVisible", "setInvisible", "disposeContainer", "disposeAll", "disposeCurrentWebView", "addContainer", "removeView", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "renderNoAnimate", "isVisible", "setWebViewVisible", "setWebViewInvisible", "scandit-capacitor-datacapture-barcode_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeFindViewHandler {
    private final Map<Integer, Boolean> containerVisibility;
    private final Map<Integer, WeakReference<FrameLayout>> containers;
    private final MainThread mainThread;
    private WeakReference<View> webViewReference;

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodeFindViewHandler() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public BarcodeFindViewHandler(MainThread mainThread) {
        Intrinsics.checkNotNullParameter(mainThread, "mainThread");
        this.mainThread = mainThread;
        this.containers = new ConcurrentHashMap();
        this.containerVisibility = new ConcurrentHashMap();
    }

    public /* synthetic */ BarcodeFindViewHandler(DefaultMainThread defaultMainThread, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? DefaultMainThread.INSTANCE.getInstance() : defaultMainThread);
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

    public final void addBarcodeFindViewContainer(int viewId, FrameLayout container, AppCompatActivity activity) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (this.containers.containsKey(Integer.valueOf(viewId))) {
            WeakReference<FrameLayout> remove = this.containers.remove(Integer.valueOf(viewId));
            FrameLayout frameLayout = remove != null ? remove.get() : null;
            if (frameLayout != null) {
                removeView(frameLayout);
            }
        }
        this.containers.put(Integer.valueOf(viewId), new WeakReference<>(container));
        this.containerVisibility.put(Integer.valueOf(viewId), true);
        addContainer(viewId, container, activity);
    }

    public final void attachWebView(final View webView) {
        Intrinsics.checkNotNullParameter(webView, "webView");
        if (Intrinsics.areEqual(getWebView(), webView)) {
            return;
        }
        this.webViewReference = new WeakReference<>(webView);
        this.mainThread.runOnMainThread(new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.find.BarcodeFindViewHandler$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit attachWebView$lambda$0;
                attachWebView$lambda$0 = BarcodeFindViewHandler.attachWebView$lambda$0(webView);
                return attachWebView$lambda$0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit attachWebView$lambda$0(View view) {
        view.bringToFront();
        view.setBackgroundColor(0);
        return Unit.INSTANCE;
    }

    public final void setVisible(final int viewId) {
        this.mainThread.runOnMainThread(new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.find.BarcodeFindViewHandler$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit visible$lambda$1;
                visible$lambda$1 = BarcodeFindViewHandler.setVisible$lambda$1(BarcodeFindViewHandler.this, viewId);
                return visible$lambda$1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setVisible$lambda$1(BarcodeFindViewHandler barcodeFindViewHandler, int i) {
        FrameLayout frameLayout;
        barcodeFindViewHandler.containerVisibility.put(Integer.valueOf(i), true);
        WeakReference<FrameLayout> weakReference = barcodeFindViewHandler.containers.get(Integer.valueOf(i));
        if (weakReference != null && (frameLayout = weakReference.get()) != null) {
            barcodeFindViewHandler.renderNoAnimate(frameLayout, Intrinsics.areEqual((Object) barcodeFindViewHandler.containerVisibility.get(Integer.valueOf(i)), (Object) true));
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    public final void setInvisible(final int viewId) {
        this.mainThread.runOnMainThread(new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.find.BarcodeFindViewHandler$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit invisible$lambda$2;
                invisible$lambda$2 = BarcodeFindViewHandler.setInvisible$lambda$2(BarcodeFindViewHandler.this, viewId);
                return invisible$lambda$2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setInvisible$lambda$2(BarcodeFindViewHandler barcodeFindViewHandler, int i) {
        FrameLayout frameLayout;
        barcodeFindViewHandler.containerVisibility.put(Integer.valueOf(i), false);
        WeakReference<FrameLayout> weakReference = barcodeFindViewHandler.containers.get(Integer.valueOf(i));
        if (weakReference != null && (frameLayout = weakReference.get()) != null) {
            barcodeFindViewHandler.renderNoAnimate(frameLayout, Intrinsics.areEqual((Object) barcodeFindViewHandler.containerVisibility.get(Integer.valueOf(i)), (Object) true));
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    public final void disposeContainer(final int viewId) {
        this.mainThread.runOnMainThread(new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.find.BarcodeFindViewHandler$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit disposeContainer$lambda$4;
                disposeContainer$lambda$4 = BarcodeFindViewHandler.disposeContainer$lambda$4(BarcodeFindViewHandler.this, viewId);
                return disposeContainer$lambda$4;
            }
        });
        this.containerVisibility.remove(Integer.valueOf(viewId));
        if (this.containers.isEmpty()) {
            this.mainThread.runOnMainThread(new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.find.BarcodeFindViewHandler$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Unit disposeContainer$lambda$5;
                    disposeContainer$lambda$5 = BarcodeFindViewHandler.disposeContainer$lambda$5(BarcodeFindViewHandler.this);
                    return disposeContainer$lambda$5;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit disposeContainer$lambda$4(BarcodeFindViewHandler barcodeFindViewHandler, int i) {
        FrameLayout frameLayout;
        WeakReference<FrameLayout> remove = barcodeFindViewHandler.containers.remove(Integer.valueOf(i));
        if (remove != null && (frameLayout = remove.get()) != null) {
            barcodeFindViewHandler.removeView(frameLayout);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit disposeContainer$lambda$5(BarcodeFindViewHandler barcodeFindViewHandler) {
        barcodeFindViewHandler.setWebViewVisible();
        return Unit.INSTANCE;
    }

    public final void disposeAll() {
        this.mainThread.runOnMainThread(new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.find.BarcodeFindViewHandler$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit disposeAll$lambda$6;
                disposeAll$lambda$6 = BarcodeFindViewHandler.disposeAll$lambda$6(BarcodeFindViewHandler.this);
                return disposeAll$lambda$6;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit disposeAll$lambda$6(BarcodeFindViewHandler barcodeFindViewHandler) {
        Iterator<Integer> it = barcodeFindViewHandler.containers.keySet().iterator();
        while (it.hasNext()) {
            barcodeFindViewHandler.disposeContainer(it.next().intValue());
        }
        barcodeFindViewHandler.disposeCurrentWebView();
        return Unit.INSTANCE;
    }

    private final void disposeCurrentWebView() {
        this.webViewReference = null;
    }

    private final void addContainer(final int viewId, final FrameLayout container, final AppCompatActivity activity) {
        this.mainThread.runOnMainThread(new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.find.BarcodeFindViewHandler$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit addContainer$lambda$7;
                addContainer$lambda$7 = BarcodeFindViewHandler.addContainer$lambda$7(AppCompatActivity.this, container, this, viewId);
                return addContainer$lambda$7;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addContainer$lambda$7(AppCompatActivity appCompatActivity, FrameLayout frameLayout, BarcodeFindViewHandler barcodeFindViewHandler, int i) {
        appCompatActivity.addContentView(frameLayout, new ViewGroup.LayoutParams(-1, -1));
        barcodeFindViewHandler.renderNoAnimate(frameLayout, Intrinsics.areEqual((Object) barcodeFindViewHandler.containerVisibility.get(Integer.valueOf(i)), (Object) true));
        return Unit.INSTANCE;
    }

    private final void removeView(final View view) {
        this.mainThread.runOnMainThread(new Function0() { // from class: com.scandit.capacitor.datacapture.barcode.find.BarcodeFindViewHandler$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Unit removeView$lambda$8;
                removeView$lambda$8 = BarcodeFindViewHandler.removeView$lambda$8(view);
                return removeView$lambda$8;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit removeView$lambda$8(View view) {
        ExtensionsKt.removeFromParent(view);
        return Unit.INSTANCE;
    }

    private final void renderNoAnimate(FrameLayout container, boolean isVisible) {
        container.setVisibility(isVisible ? 0 : 8);
        ViewGroup.LayoutParams layoutParams = container.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        if (isVisible) {
            container.bringToFront();
            setWebViewInvisible();
        } else {
            setWebViewVisible();
        }
        container.requestLayout();
    }

    private final void setWebViewVisible() {
        View webView = getWebView();
        if (webView != null) {
            webView.bringToFront();
        }
        View webView2 = getWebView();
        Object parent = webView2 != null ? webView2.getParent() : null;
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
        ((View) parent).setTranslationZ(1.0f);
    }

    private final void setWebViewInvisible() {
        View webView = getWebView();
        Object parent = webView != null ? webView.getParent() : null;
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
        ((View) parent).setTranslationZ(-1.0f);
    }
}
