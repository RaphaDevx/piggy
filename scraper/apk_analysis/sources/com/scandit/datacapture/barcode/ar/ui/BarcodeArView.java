package com.scandit.datacapture.barcode.ar.ui;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.ar.capture.BarcodeAr;
import com.scandit.datacapture.barcode.ar.capture.BarcodeArListener;
import com.scandit.datacapture.barcode.ar.capture.BarcodeArSession;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotationProvider;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotationTrigger;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArPopoverAnnotation;
import com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArHighlight;
import com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArHighlightProvider;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.internal.module.ar.feedback.BarcodeArFeedbackEmitter;
import com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArView;
import com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.BarcodeArAugmentation$Annotation;
import com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.BarcodeArAugmentation$Highlight;
import com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.v;
import com.scandit.datacapture.barcode.internal.sdk.ui.InternalPropertyManager;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.core.internal.sdk.common.async.MainThreadHelperImpl;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.notification.NotificationPresenter;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B5\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0011\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0012\u0010\u0010J\u000f\u0010\u0013\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0013\u0010\u0010J\u000f\u0010\u0014\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0014\u0010\u0010J\u000f\u0010\u0015\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0015\u0010\u0010J\u0017\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0016H\u0000¢\u0006\u0004\b\u0018\u0010\u0019R$\u0010\"\u001a\u0004\u0018\u00010\u001b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R$\u0010*\u001a\u0004\u0018\u00010#8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R$\u00102\u001a\u0004\u0018\u00010+8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u0017\u00108\u001a\u0002038\u0006¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107R\u0014\u0010<\u001a\u0002098@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;R+\u0010E\u001a\u00020=2\u0006\u0010>\u001a\u00020=8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\b?\u0010@\"\u0004\bA\u0010B*\u0004\bC\u0010DR+\u0010L\u001a\u00020F2\u0006\u0010>\u001a\u00020F8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bG\u0010H\"\u0004\bI\u0010J*\u0004\bK\u0010DR+\u0010P\u001a\u00020=2\u0006\u0010>\u001a\u00020=8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bM\u0010@\"\u0004\bN\u0010B*\u0004\bO\u0010DR+\u0010T\u001a\u00020F2\u0006\u0010>\u001a\u00020F8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bQ\u0010H\"\u0004\bR\u0010J*\u0004\bS\u0010DR+\u0010X\u001a\u00020=2\u0006\u0010>\u001a\u00020=8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bU\u0010@\"\u0004\bV\u0010B*\u0004\bW\u0010DR+\u0010\\\u001a\u00020F2\u0006\u0010>\u001a\u00020F8F@FX\u0086\u008e\u0002¢\u0006\u0012\u001a\u0004\bY\u0010H\"\u0004\bZ\u0010J*\u0004\b[\u0010DR\u0011\u0010`\u001a\u00020]8F¢\u0006\u0006\u001a\u0004\b^\u0010_¨\u0006a"}, d2 = {"Lcom/scandit/datacapture/barcode/ar/ui/BarcodeArView;", "Landroid/widget/FrameLayout;", "Landroid/view/ViewGroup;", "parentView", "Lcom/scandit/datacapture/barcode/ar/capture/BarcodeAr;", "barcodeAr", "Lcom/scandit/datacapture/core/capture/DataCaptureContext;", "dataCaptureContext", "Lcom/scandit/datacapture/barcode/ar/ui/BarcodeArViewSettings;", "settings", "Lcom/scandit/datacapture/core/source/CameraSettings;", "cameraSettings", "<init>", "(Landroid/view/ViewGroup;Lcom/scandit/datacapture/barcode/ar/capture/BarcodeAr;Lcom/scandit/datacapture/core/capture/DataCaptureContext;Lcom/scandit/datacapture/barcode/ar/ui/BarcodeArViewSettings;Lcom/scandit/datacapture/core/source/CameraSettings;)V", "", "onResume", "()V", "onPause", "onDestroy", "start", "stop", "reset", "Lcom/scandit/datacapture/core/json/JsonValue;", "jsonValue", "_updateFromJson$scandit_barcode_capture", "(Lcom/scandit/datacapture/core/json/JsonValue;)V", "_updateFromJson", "Lcom/scandit/datacapture/barcode/ar/ui/BarcodeArViewUiListener;", "k", "Lcom/scandit/datacapture/barcode/ar/ui/BarcodeArViewUiListener;", "getUiListener", "()Lcom/scandit/datacapture/barcode/ar/ui/BarcodeArViewUiListener;", "setUiListener", "(Lcom/scandit/datacapture/barcode/ar/ui/BarcodeArViewUiListener;)V", "uiListener", "Lcom/scandit/datacapture/barcode/ar/ui/highlight/BarcodeArHighlightProvider;", "l", "Lcom/scandit/datacapture/barcode/ar/ui/highlight/BarcodeArHighlightProvider;", "getHighlightProvider", "()Lcom/scandit/datacapture/barcode/ar/ui/highlight/BarcodeArHighlightProvider;", "setHighlightProvider", "(Lcom/scandit/datacapture/barcode/ar/ui/highlight/BarcodeArHighlightProvider;)V", "highlightProvider", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationProvider;", "m", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationProvider;", "getAnnotationProvider", "()Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationProvider;", "setAnnotationProvider", "(Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationProvider;)V", "annotationProvider", "Lcom/scandit/datacapture/barcode/internal/sdk/ui/InternalPropertyManager;", "n", "Lcom/scandit/datacapture/barcode/internal/sdk/ui/InternalPropertyManager;", "get_internalPropertyManager", "()Lcom/scandit/datacapture/barcode/internal/sdk/ui/InternalPropertyManager;", "_internalPropertyManager", "Lcom/scandit/datacapture/core/ui/DataCaptureView;", "get_dataCaptureView$scandit_barcode_capture", "()Lcom/scandit/datacapture/core/ui/DataCaptureView;", "_dataCaptureView", "", "<set-?>", "getShouldShowTorchControl", "()Z", "setShouldShowTorchControl", "(Z)V", "getShouldShowTorchControl$delegate", "(Lcom/scandit/datacapture/barcode/ar/ui/BarcodeArView;)Ljava/lang/Object;", "shouldShowTorchControl", "Lcom/scandit/datacapture/core/common/geometry/Anchor;", "getTorchControlPosition", "()Lcom/scandit/datacapture/core/common/geometry/Anchor;", "setTorchControlPosition", "(Lcom/scandit/datacapture/core/common/geometry/Anchor;)V", "getTorchControlPosition$delegate", "torchControlPosition", "getShouldShowZoomControl", "setShouldShowZoomControl", "getShouldShowZoomControl$delegate", "shouldShowZoomControl", "getZoomControlPosition", "setZoomControlPosition", "getZoomControlPosition$delegate", "zoomControlPosition", "getShouldShowCameraSwitchControl", "setShouldShowCameraSwitchControl", "getShouldShowCameraSwitchControl$delegate", "shouldShowCameraSwitchControl", "getCameraSwitchControlPosition", "setCameraSwitchControlPosition", "getCameraSwitchControlPosition$delegate", "cameraSwitchControlPosition", "Lcom/scandit/datacapture/core/ui/notification/NotificationPresenter;", "getNotificationPresenter", "()Lcom/scandit/datacapture/core/ui/notification/NotificationPresenter;", "notificationPresenter", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeArView extends FrameLayout {
    private final BarcodeAr a;
    private final BarcodeArViewSettings b;
    private final Camera c;
    private final DataCaptureContext d;
    private final DataCaptureView e;
    private final NativeBarcodeArView f;
    private final com.scandit.datacapture.barcode.internal.module.shared.c g;
    private final v h;
    private final BarcodeArView$arListener$1 i;
    private final com.scandit.datacapture.barcode.internal.module.ar.ui.d j;

    /* renamed from: k, reason: from kotlin metadata */
    private BarcodeArViewUiListener uiListener;

    /* renamed from: l, reason: from kotlin metadata */
    private BarcodeArHighlightProvider highlightProvider;

    /* renamed from: m, reason: from kotlin metadata */
    private BarcodeArAnnotationProvider annotationProvider;
    private final BarcodeArView$_internalPropertyManager$1 n;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BarcodeArView(ViewGroup parentView, BarcodeAr barcodeAr, DataCaptureContext dataCaptureContext, BarcodeArViewSettings settings) {
        this(parentView, barcodeAr, dataCaptureContext, settings, null, 16, null);
        Intrinsics.checkNotNullParameter(parentView, "parentView");
        Intrinsics.checkNotNullParameter(barcodeAr, "barcodeAr");
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(settings, "settings");
    }

    public static final void access$_releaseScanning(BarcodeArView barcodeArView) {
        synchronized (barcodeArView) {
            BarcodeArFeedbackEmitter feedbackEmitter$scandit_barcode_capture = barcodeArView.a.getFeedbackEmitter$scandit_barcode_capture();
            if (feedbackEmitter$scandit_barcode_capture != null) {
                feedbackEmitter$scandit_barcode_capture.a();
            }
            barcodeArView.a.removeListener(barcodeArView.i);
            barcodeArView.a.getBarcodeArInternal().h();
            barcodeArView.d.removeMode(barcodeArView.a.getBarcodeArInternal());
            barcodeArView.h.n.set(false);
            barcodeArView.h.a();
        }
    }

    public static final void access$_resetScanning(BarcodeArView barcodeArView) {
        synchronized (barcodeArView) {
            barcodeArView.h.a();
        }
    }

    public static final void access$_startScanning(BarcodeArView barcodeArView) {
        synchronized (barcodeArView) {
            BarcodeArFeedbackEmitter feedbackEmitter$scandit_barcode_capture = barcodeArView.a.getFeedbackEmitter$scandit_barcode_capture();
            if (feedbackEmitter$scandit_barcode_capture != null) {
                feedbackEmitter$scandit_barcode_capture.c();
            }
            barcodeArView.a.addListener(barcodeArView.i);
            barcodeArView.d.setMode(barcodeArView.a.getBarcodeArInternal());
            barcodeArView.a.getBarcodeArInternal().g();
            barcodeArView.h.n.set(true);
        }
    }

    public static final void access$_stopScanning(BarcodeArView barcodeArView) {
        synchronized (barcodeArView) {
            BarcodeArFeedbackEmitter feedbackEmitter$scandit_barcode_capture = barcodeArView.a.getFeedbackEmitter$scandit_barcode_capture();
            if (feedbackEmitter$scandit_barcode_capture != null) {
                feedbackEmitter$scandit_barcode_capture.a();
            }
            barcodeArView.a.getBarcodeArInternal().f();
            barcodeArView.h.n.set(false);
        }
    }

    public static final void access$onAnnotationOrElementTap(BarcodeArView barcodeArView, TrackedBarcode trackedBarcode, BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation) {
        BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation2;
        v vVar = barcodeArView.h;
        int identifier = trackedBarcode.getIdentifier();
        ReentrantReadWriteLock.ReadLock readLock = vVar.k.readLock();
        readLock.lock();
        try {
            com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.a aVar = (com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.a) vVar.m.get(Integer.valueOf(identifier));
            if (aVar != null && (barcodeArAugmentation$Annotation2 = aVar.e) != null) {
                BarcodeArAnnotation a = barcodeArAugmentation$Annotation2.a();
                if ((a instanceof BarcodeArPopoverAnnotation) && a.getAnnotationTrigger() != BarcodeArAnnotationTrigger.BARCODE_SCAN) {
                    vVar.j.runOnMainThread(new com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.l(vVar, barcodeArAugmentation$Annotation2, aVar.d));
                }
            }
            BarcodeArFeedbackEmitter feedbackEmitter$scandit_barcode_capture = barcodeArView.a.getFeedbackEmitter$scandit_barcode_capture();
            if (feedbackEmitter$scandit_barcode_capture != null) {
                feedbackEmitter$scandit_barcode_capture.b();
            }
        } finally {
            readLock.unlock();
        }
    }

    public static final void access$onHighlightTap(BarcodeArView barcodeArView, TrackedBarcode trackedBarcode, BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight) {
        BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation;
        v vVar = barcodeArView.h;
        int identifier = trackedBarcode.getIdentifier();
        ReentrantReadWriteLock.ReadLock readLock = vVar.k.readLock();
        readLock.lock();
        try {
            com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.a aVar = (com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.a) vVar.m.get(Integer.valueOf(identifier));
            if (aVar != null && (barcodeArAugmentation$Annotation = aVar.e) != null) {
                BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight2 = aVar.d;
                BarcodeArAnnotationTrigger annotationTrigger = barcodeArAugmentation$Annotation.a().getAnnotationTrigger();
                Intrinsics.checkNotNullParameter(annotationTrigger, "<this>");
                int i = com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.c.a[annotationTrigger.ordinal()];
                if (i == 1 || i == 2) {
                    vVar.j.runOnMainThread(new com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.s(barcodeArAugmentation$Annotation, vVar, barcodeArAugmentation$Highlight2, aVar));
                } else if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
            }
            BarcodeArFeedbackEmitter feedbackEmitter$scandit_barcode_capture = barcodeArView.a.getFeedbackEmitter$scandit_barcode_capture();
            if (feedbackEmitter$scandit_barcode_capture != null) {
                feedbackEmitter$scandit_barcode_capture.b();
            }
            BarcodeArViewUiListener barcodeArViewUiListener = barcodeArView.uiListener;
            if (barcodeArViewUiListener != null) {
                barcodeArViewUiListener.onHighlightForBarcodeTapped(barcodeArView.a, trackedBarcode.getBarcode(), barcodeArAugmentation$Highlight.a(), barcodeArAugmentation$Highlight.b());
            }
        } finally {
            readLock.unlock();
        }
    }

    public static final boolean access$onOverlayTap(BarcodeArView barcodeArView) {
        v vVar = barcodeArView.h;
        boolean z = vVar.o > 0;
        Iterator it = vVar.b().iterator();
        while (it.hasNext()) {
            com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.a aVar = (com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.a) it.next();
            BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation = aVar.e;
            if (barcodeArAugmentation$Annotation != null) {
                vVar.a(barcodeArAugmentation$Annotation, aVar.d);
            }
        }
        return z;
    }

    public static final void access$requestAnnotation(BarcodeArView barcodeArView, Barcode barcode, final Function1 function1) {
        BarcodeArAnnotationProvider barcodeArAnnotationProvider = barcodeArView.annotationProvider;
        if (barcodeArAnnotationProvider != null) {
            Context context = barcodeArView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            barcodeArAnnotationProvider.annotationForBarcode(context, barcode, new BarcodeArAnnotationProvider.Callback() { // from class: com.scandit.datacapture.barcode.ar.ui.BarcodeArView$requestAnnotation$1
                @Override // com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotationProvider.Callback
                public void onData(BarcodeArAnnotation annotation) {
                    Function1.this.invoke(annotation);
                }
            });
        }
    }

    public static final void access$requestHighlight(BarcodeArView barcodeArView, Barcode barcode, final Function1 function1) {
        BarcodeArHighlightProvider barcodeArHighlightProvider = barcodeArView.highlightProvider;
        if (barcodeArHighlightProvider == null) {
            BarcodeArHighlightProvider.Companion companion = BarcodeArHighlightProvider.INSTANCE;
            Lazy lazy = com.scandit.datacapture.barcode.internal.module.ar.ui.highlight.c.a;
            Intrinsics.checkNotNullParameter(companion, "<this>");
            barcodeArHighlightProvider = (BarcodeArHighlightProvider) com.scandit.datacapture.barcode.internal.module.ar.ui.highlight.c.a.getValue();
        }
        Context context = barcodeArView.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        barcodeArHighlightProvider.highlightForBarcode(context, barcode, new BarcodeArHighlightProvider.Callback() { // from class: com.scandit.datacapture.barcode.ar.ui.BarcodeArView$requestHighlight$1
            @Override // com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArHighlightProvider.Callback
            public void onData(BarcodeArHighlight highlight) {
                Function1.this.invoke(highlight);
            }
        });
    }

    public final void _updateFromJson$scandit_barcode_capture(JsonValue jsonValue) {
        Intrinsics.checkNotNullParameter(jsonValue, "jsonValue");
        this.f.updateFromJson(jsonValue.getA());
        com.scandit.datacapture.barcode.internal.module.ar.ui.d dVar = this.j;
        dVar.b();
        dVar.c();
        dVar.a();
    }

    public final BarcodeArAnnotationProvider getAnnotationProvider() {
        return this.annotationProvider;
    }

    public final Anchor getCameraSwitchControlPosition() {
        Anchor cameraSwitchControlPosition = this.j.a.getCameraSwitchControlPosition();
        Intrinsics.checkNotNullExpressionValue(cameraSwitchControlPosition, "getCameraSwitchControlPosition(...)");
        return cameraSwitchControlPosition;
    }

    public final BarcodeArHighlightProvider getHighlightProvider() {
        return this.highlightProvider;
    }

    public final NotificationPresenter getNotificationPresenter() {
        return this.e.get_notificationPresenter();
    }

    public final boolean getShouldShowCameraSwitchControl() {
        return this.j.a.getShouldShowCameraSwitchControl();
    }

    public final boolean getShouldShowTorchControl() {
        return this.j.a.getShouldShowTorchControl();
    }

    public final boolean getShouldShowZoomControl() {
        return this.j.a.getShouldShowZoomControl();
    }

    public final Anchor getTorchControlPosition() {
        Anchor torchControlPosition = this.j.a.getTorchControlPosition();
        Intrinsics.checkNotNullExpressionValue(torchControlPosition, "getTorchControlPosition(...)");
        return torchControlPosition;
    }

    public final BarcodeArViewUiListener getUiListener() {
        return this.uiListener;
    }

    public final Anchor getZoomControlPosition() {
        Anchor zoomControlPosition = this.j.a.getZoomControlPosition();
        Intrinsics.checkNotNullExpressionValue(zoomControlPosition, "getZoomControlPosition(...)");
        return zoomControlPosition;
    }

    /* renamed from: get_dataCaptureView$scandit_barcode_capture, reason: from getter */
    public final /* synthetic */ DataCaptureView getE() {
        return this.e;
    }

    public final InternalPropertyManager get_internalPropertyManager() {
        return this.n;
    }

    public final void onDestroy() {
        com.scandit.datacapture.barcode.internal.module.shared.c cVar = this.g;
        synchronized (cVar) {
            com.scandit.datacapture.barcode.internal.module.shared.a b = cVar.b();
            com.scandit.datacapture.barcode.internal.module.shared.a aVar = com.scandit.datacapture.barcode.internal.module.shared.a.d;
            if (b == aVar) {
                return;
            }
            cVar.f = aVar;
            com.scandit.datacapture.barcode.internal.module.shared.b c = cVar.c();
            c.getClass();
            if (c == com.scandit.datacapture.barcode.internal.module.shared.b.b) {
                cVar.e();
            }
            cVar.d.invoke();
        }
    }

    public final void onPause() {
        com.scandit.datacapture.barcode.internal.module.shared.c cVar = this.g;
        synchronized (cVar) {
            com.scandit.datacapture.barcode.internal.module.shared.a b = cVar.b();
            com.scandit.datacapture.barcode.internal.module.shared.a aVar = com.scandit.datacapture.barcode.internal.module.shared.a.c;
            if (b == aVar) {
                return;
            }
            cVar.f = aVar;
            com.scandit.datacapture.barcode.internal.module.shared.b c = cVar.c();
            c.getClass();
            if (c == com.scandit.datacapture.barcode.internal.module.shared.b.b) {
                cVar.b.invoke();
            }
        }
    }

    public final void onResume() {
        com.scandit.datacapture.barcode.internal.module.shared.c cVar = this.g;
        synchronized (cVar) {
            com.scandit.datacapture.barcode.internal.module.shared.a b = cVar.b();
            com.scandit.datacapture.barcode.internal.module.shared.a aVar = com.scandit.datacapture.barcode.internal.module.shared.a.b;
            if (b == aVar) {
                return;
            }
            cVar.f = aVar;
            com.scandit.datacapture.barcode.internal.module.shared.b c = cVar.c();
            c.getClass();
            if (c == com.scandit.datacapture.barcode.internal.module.shared.b.b) {
                cVar.a.invoke();
            }
        }
    }

    public final void reset() {
        com.scandit.datacapture.barcode.internal.module.shared.c cVar = this.g;
        synchronized (cVar) {
            cVar.e.invoke();
        }
    }

    public final void setAnnotationProvider(BarcodeArAnnotationProvider barcodeArAnnotationProvider) {
        this.annotationProvider = barcodeArAnnotationProvider;
    }

    public final void setCameraSwitchControlPosition(Anchor value) {
        Intrinsics.checkNotNullParameter(value, "<set-?>");
        com.scandit.datacapture.barcode.internal.module.ar.ui.d dVar = this.j;
        dVar.getClass();
        Intrinsics.checkNotNullParameter(value, "value");
        dVar.a.setCameraSwitchControlPosition(value);
        dVar.a();
    }

    public final void setHighlightProvider(BarcodeArHighlightProvider barcodeArHighlightProvider) {
        this.highlightProvider = barcodeArHighlightProvider;
    }

    public final void setShouldShowCameraSwitchControl(boolean z) {
        com.scandit.datacapture.barcode.internal.module.ar.ui.d dVar = this.j;
        dVar.a.setShouldShowCameraSwitchControl(z);
        dVar.a();
    }

    public final void setShouldShowTorchControl(boolean z) {
        com.scandit.datacapture.barcode.internal.module.ar.ui.d dVar = this.j;
        dVar.a.setShouldShowTorchControl(z);
        dVar.b();
    }

    public final void setShouldShowZoomControl(boolean z) {
        com.scandit.datacapture.barcode.internal.module.ar.ui.d dVar = this.j;
        dVar.a.setShouldShowZoomControl(z);
        dVar.c();
    }

    public final void setTorchControlPosition(Anchor value) {
        Intrinsics.checkNotNullParameter(value, "<set-?>");
        com.scandit.datacapture.barcode.internal.module.ar.ui.d dVar = this.j;
        dVar.getClass();
        Intrinsics.checkNotNullParameter(value, "value");
        dVar.a.setTorchControlPosition(value);
        dVar.b();
    }

    public final void setUiListener(BarcodeArViewUiListener barcodeArViewUiListener) {
        this.uiListener = barcodeArViewUiListener;
    }

    public final void setZoomControlPosition(Anchor value) {
        Intrinsics.checkNotNullParameter(value, "<set-?>");
        com.scandit.datacapture.barcode.internal.module.ar.ui.d dVar = this.j;
        dVar.getClass();
        Intrinsics.checkNotNullParameter(value, "value");
        dVar.a.setZoomControlPosition(value);
        dVar.c();
    }

    public final void start() {
        this.g.d();
    }

    public final void stop() {
        this.g.e();
    }

    public /* synthetic */ BarcodeArView(ViewGroup viewGroup, BarcodeAr barcodeAr, DataCaptureContext dataCaptureContext, BarcodeArViewSettings barcodeArViewSettings, CameraSettings cameraSettings, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(viewGroup, barcodeAr, dataCaptureContext, barcodeArViewSettings, (i & 16) != 0 ? null : cameraSettings);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r1v11, types: [com.scandit.datacapture.barcode.ar.ui.BarcodeArView$arListener$1] */
    /* JADX WARN: Type inference failed for: r1v13, types: [com.scandit.datacapture.barcode.ar.ui.BarcodeArView$_internalPropertyManager$1] */
    public BarcodeArView(ViewGroup parentView, BarcodeAr barcodeAr, DataCaptureContext dataCaptureContext, BarcodeArViewSettings settings, CameraSettings cameraSettings) {
        super(parentView.getContext());
        Intrinsics.checkNotNullParameter(parentView, "parentView");
        Intrinsics.checkNotNullParameter(barcodeAr, "barcodeAr");
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(settings, "settings");
        BarcodeArViewSettings clone$scandit_barcode_capture = settings.clone$scandit_barcode_capture();
        Camera camera = Camera.INSTANCE.getCamera(settings.getDefaultCameraPosition(), cameraSettings == null ? BarcodeAr.INSTANCE.createRecommendedCameraSettings() : cameraSettings);
        DataCaptureView.Companion companion = DataCaptureView.INSTANCE;
        Context context = parentView.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        DataCaptureView dataCaptureView = companion.newInstance(context, dataCaptureContext);
        NativeBarcodeArView impl = NativeBarcodeArView.create();
        Intrinsics.checkNotNullExpressionValue(impl, "create(...)");
        this.a = barcodeAr;
        this.b = clone$scandit_barcode_capture;
        this.c = camera;
        this.d = dataCaptureContext;
        this.e = dataCaptureView;
        this.f = impl;
        MainThreadHelperImpl mainThreadHelper = new MainThreadHelperImpl();
        e onStartScanning = new e(this);
        f onStopScanning = new f(this);
        g onFreezeScanning = g.a;
        h onReleaseScanning = new h(this);
        i onResetScanning = new i(this);
        com.scandit.datacapture.barcode.internal.module.shared.b initialScanState = com.scandit.datacapture.barcode.internal.module.shared.b.b;
        Intrinsics.checkNotNullParameter(onStartScanning, "onStartScanning");
        Intrinsics.checkNotNullParameter(onStopScanning, "onStopScanning");
        Intrinsics.checkNotNullParameter(onFreezeScanning, "onFreezeScanning");
        Intrinsics.checkNotNullParameter(onReleaseScanning, "onReleaseScanning");
        Intrinsics.checkNotNullParameter(onResetScanning, "onResetScanning");
        Intrinsics.checkNotNullParameter(initialScanState, "initialScanState");
        this.g = new com.scandit.datacapture.barcode.internal.module.shared.c(onStartScanning, onStopScanning, onFreezeScanning, onReleaseScanning, onResetScanning, initialScanState);
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        b onHighlightTap = new b(this);
        c onAnnotationOrElementTap = new c(this);
        d onOverlayTap = new d(this);
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(onHighlightTap, "onHighlightTap");
        Intrinsics.checkNotNullParameter(onAnnotationOrElementTap, "onAnnotationOrElementTap");
        Intrinsics.checkNotNullParameter(onOverlayTap, "onOverlayTap");
        com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.i iVar = new com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.i(context2, onHighlightTap, onAnnotationOrElementTap, onOverlayTap);
        l highlightRequester = new l(this);
        m annotationRequestor = new m(this);
        n quadrilateralMapper = new n(dataCaptureView);
        Intrinsics.checkNotNullParameter(highlightRequester, "highlightRequester");
        Intrinsics.checkNotNullParameter(annotationRequestor, "annotationRequestor");
        Intrinsics.checkNotNullParameter(quadrilateralMapper, "quadrilateralMapper");
        com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.e augmentationDataFactory = new com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.e(highlightRequester, annotationRequestor, quadrilateralMapper);
        o addHighlight = new o(iVar);
        p updateHighlight = new p(iVar);
        q removeHighlight = new q(iVar);
        r addAnnotation = new r(iVar);
        s updateAnnotation = new s(iVar);
        t removeAnnotation = new t(iVar);
        j showScrim = new j(iVar);
        k hideScrim = new k(iVar);
        Intrinsics.checkNotNullParameter(augmentationDataFactory, "augmentationDataFactory");
        Intrinsics.checkNotNullParameter(addHighlight, "addHighlight");
        Intrinsics.checkNotNullParameter(updateHighlight, "updateHighlight");
        Intrinsics.checkNotNullParameter(removeHighlight, "removeHighlight");
        Intrinsics.checkNotNullParameter(addAnnotation, "addAnnotation");
        Intrinsics.checkNotNullParameter(updateAnnotation, "updateAnnotation");
        Intrinsics.checkNotNullParameter(removeAnnotation, "removeAnnotation");
        Intrinsics.checkNotNullParameter(showScrim, "showScrim");
        Intrinsics.checkNotNullParameter(hideScrim, "hideScrim");
        Intrinsics.checkNotNullParameter(mainThreadHelper, "mainThreadHelper");
        this.h = new v(augmentationDataFactory, addHighlight, updateHighlight, removeHighlight, addAnnotation, updateAnnotation, removeAnnotation, showScrim, hideScrim, mainThreadHelper);
        this.i = new BarcodeArListener() { // from class: com.scandit.datacapture.barcode.ar.ui.BarcodeArView$arListener$1
            @Override // com.scandit.datacapture.barcode.ar.capture.BarcodeArListener
            @ProxyFunction
            public void onObservationStarted(BarcodeAr barcodeAr2) {
                BarcodeArListener.DefaultImpls.onObservationStarted(this, barcodeAr2);
            }

            @Override // com.scandit.datacapture.barcode.ar.capture.BarcodeArListener
            @ProxyFunction
            public void onObservationStopped(BarcodeAr barcodeAr2) {
                BarcodeArListener.DefaultImpls.onObservationStopped(this, barcodeAr2);
            }

            @Override // com.scandit.datacapture.barcode.ar.capture.BarcodeArListener
            public void onSessionUpdated(BarcodeAr barcodeAr2, BarcodeArSession session, FrameData frameData) {
                Intrinsics.checkNotNullParameter(barcodeAr2, "barcodeAr");
                Intrinsics.checkNotNullParameter(session, "session");
                Intrinsics.checkNotNullParameter(frameData, "frameData");
                BarcodeArView.this.h.a(session);
            }
        };
        a cameraSwitchControlBuilder = new a(this);
        Intrinsics.checkNotNullParameter(impl, "impl");
        Intrinsics.checkNotNullParameter(dataCaptureView, "dataCaptureView");
        Intrinsics.checkNotNullParameter(cameraSwitchControlBuilder, "cameraSwitchControlBuilder");
        this.j = new com.scandit.datacapture.barcode.internal.module.ar.ui.d(impl, dataCaptureView, cameraSwitchControlBuilder);
        this.n = new InternalPropertyManager() { // from class: com.scandit.datacapture.barcode.ar.ui.BarcodeArView$_internalPropertyManager$1
            @Override // com.scandit.datacapture.barcode.internal.sdk.ui.InternalPropertyManager
            public void _setProperty(String key, Object value) {
                DataCaptureView dataCaptureView2;
                DataCaptureView dataCaptureView3;
                Intrinsics.checkNotNullParameter(key, "key");
                Intrinsics.checkNotNullParameter(value, "value");
                if (Intrinsics.areEqual(key, "add_overlay") && (value instanceof DataCaptureOverlay)) {
                    dataCaptureView3 = BarcodeArView.this.e;
                    dataCaptureView3.addOverlay((DataCaptureOverlay) value);
                } else if (Intrinsics.areEqual(key, "remove_overlay") && (value instanceof DataCaptureOverlay)) {
                    dataCaptureView2 = BarcodeArView.this.e;
                    dataCaptureView2.removeOverlay((DataCaptureOverlay) value);
                }
            }
        };
        if (camera == null) {
            Log.w("ScanditDataCapture", "Barcode ar view initialized with \"null\" camera");
        }
        DataCaptureContext.setFrameSource$default(dataCaptureContext, camera, null, 2, null);
        barcodeAr.setFeedbackEmitter$scandit_barcode_capture(new BarcodeArFeedbackEmitter(barcodeAr, clone$scandit_barcode_capture.getSoundEnabled(), clone$scandit_barcode_capture.getHapticEnabled()));
        ViewParent parent = dataCaptureView.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup != null) {
            viewGroup.removeView(dataCaptureView);
        }
        parentView.addView(this, -1, -1);
        addView(dataCaptureView, -1, -1);
        dataCaptureView.addOverlay(iVar);
    }
}
