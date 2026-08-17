package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArPopoverAnnotation;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArStatusIconAnnotation;
import com.scandit.datacapture.barcode.ar.ui.annotations.responsive.BarcodeArResponsiveAnnotation;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArAnnotationVariation;
import com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArOverlay;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.ViewBasedDataCaptureOverlay;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* loaded from: classes2.dex */
public final class i extends ViewBasedDataCaptureOverlay implements g, DataCaptureOverlay {
    public static final int f = com.scandit.datacapture.barcode.internal.module.extensions.a.a(ViewCompat.MEASURED_STATE_MASK, (int) (RangesKt.coerceIn(0.3f, 0.0f, 1.0f) * 255));
    public final Function2 a;
    public final Function2 b;
    public final Function0 c;
    public final /* synthetic */ j d;
    public final View e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(Context context, com.scandit.datacapture.barcode.ar.ui.b onHighlightTap, com.scandit.datacapture.barcode.ar.ui.c onAnnotationOrElementTap, com.scandit.datacapture.barcode.ar.ui.d onOverlayTap) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onHighlightTap, "onHighlightTap");
        Intrinsics.checkNotNullParameter(onAnnotationOrElementTap, "onAnnotationOrElementTap");
        Intrinsics.checkNotNullParameter(onOverlayTap, "onOverlayTap");
        this.a = onHighlightTap;
        this.b = onAnnotationOrElementTap;
        this.c = onOverlayTap;
        NativeBarcodeArOverlay create = NativeBarcodeArOverlay.create();
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        this.d = new j(create);
        View view = new View(context);
        view.setBackgroundColor(f);
        view.setVisibility(8);
        view.setZ(3.0f);
        this.e = view;
        setImportantForAccessibility(2);
        addView(view, -1, -1);
        setTouchDelegate(new com.scandit.datacapture.barcode.internal.module.ui.g(this));
        a();
        setClipChildren(false);
    }

    @Override // com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay, com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlayProxy
    /* renamed from: _dataCaptureOverlayImpl */
    public final NativeDataCaptureOverlay getC() {
        return this.d.b;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.overlay.ViewBasedDataCaptureOverlay
    public final void _setDataCaptureView(DataCaptureView view) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    public final void a() {
        this.e.setOnTouchListener(new View.OnTouchListener() { // from class: com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.i$$ExternalSyntheticLambda1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return i.a(i.this, view, motionEvent);
            }
        });
    }

    public static final boolean a(i this$0, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return ((Boolean) this$0.c.invoke()).booleanValue();
    }

    public final void a(final TrackedBarcode track, final BarcodeArAugmentation$Highlight highlightData) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(highlightData, "highlightData");
        View b = highlightData.b();
        b.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.i$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                i.a(i.this, track, highlightData, view);
            }
        });
        b.setZ(1.0f);
        addView(b);
    }

    public static final void a(i this$0, TrackedBarcode track, BarcodeArAugmentation$Highlight highlightData, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(track, "$track");
        Intrinsics.checkNotNullParameter(highlightData, "$highlightData");
        this$0.a.invoke(track, highlightData);
    }

    public final void a(TrackedBarcode track, BarcodeArAugmentation$Annotation annotationData, BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(annotationData, "annotationData");
        BarcodeArAnnotation a = annotationData.a();
        View b = annotationData.b();
        h hVar = new h(this, track, annotationData);
        Intrinsics.checkNotNullParameter(a, "<this>");
        if (a instanceof BarcodeArInfoAnnotation) {
            ((BarcodeArInfoAnnotation) a).setOnAnnotationOrElementTap$scandit_barcode_capture(hVar);
        } else if (a instanceof BarcodeArPopoverAnnotation) {
            ((BarcodeArPopoverAnnotation) a).setOnAnnotationOrElementTap$scandit_barcode_capture(hVar);
        } else if (a instanceof BarcodeArStatusIconAnnotation) {
            ((BarcodeArStatusIconAnnotation) a).setOnAnnotationOrElementTap$scandit_barcode_capture(hVar);
        } else if (a instanceof BarcodeArResponsiveAnnotation) {
            ((BarcodeArResponsiveAnnotation) a).setOnAnnotationOrElementTap$scandit_barcode_capture(hVar);
        }
        if (b.getVisibility() == 0) {
            b.setVisibility(4);
            annotationData.a(true);
        }
        if (a instanceof BarcodeArPopoverAnnotation) {
            b.setZ(12.0f);
            View b2 = barcodeArAugmentation$Highlight != null ? barcodeArAugmentation$Highlight.b() : null;
            if (b2 != null) {
                b2.setZ(4.0f);
            }
        } else {
            b.setZ(2.0f);
        }
        addView(b);
    }

    public final void a(Quadrilateral quad, final BarcodeArAugmentation$Annotation annotationData, BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight) {
        Intrinsics.checkNotNullParameter(quad, "location");
        Intrinsics.checkNotNullParameter(annotationData, "annotationData");
        if (annotationData.a() instanceof BarcodeArResponsiveAnnotation) {
            float threshold = BarcodeArResponsiveAnnotation.INSTANCE.getThreshold();
            BarcodeArResponsiveAnnotation barcodeArResponsiveAnnotation = (BarcodeArResponsiveAnnotation) annotationData.a();
            Intrinsics.checkNotNullParameter(quad, "quad");
            j jVar = this.d;
            jVar.getClass();
            Intrinsics.checkNotNullParameter(quad, "quad");
            NativeBarcodeArAnnotationVariation annotationVariationForQuadPxSize = jVar.a.getAnnotationVariationForQuadPxSize(quad, threshold);
            Intrinsics.checkNotNull(annotationVariationForQuadPxSize);
            barcodeArResponsiveAnnotation.setVariation$scandit_barcode_capture(annotationVariationForQuadPxSize);
        }
        annotationData.a().update(quad, barcodeArAugmentation$Highlight != null ? barcodeArAugmentation$Highlight.c() : null, annotationData.b());
        final View b = annotationData.b();
        if (annotationData.c() && f.a(b)) {
            b.post(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.ar.ui.overlay.i$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    i.a(b, annotationData);
                }
            });
        }
    }

    public final void a(BarcodeArAugmentation$Annotation annotationData, BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight) {
        Intrinsics.checkNotNullParameter(annotationData, "annotationData");
        BarcodeArAnnotation a = annotationData.a();
        Intrinsics.checkNotNullParameter(a, "<this>");
        if (a instanceof BarcodeArInfoAnnotation) {
            ((BarcodeArInfoAnnotation) a).setOnAnnotationOrElementTap$scandit_barcode_capture(null);
        } else if (a instanceof BarcodeArPopoverAnnotation) {
            ((BarcodeArPopoverAnnotation) a).setOnAnnotationOrElementTap$scandit_barcode_capture(null);
        } else if (a instanceof BarcodeArStatusIconAnnotation) {
            ((BarcodeArStatusIconAnnotation) a).setOnAnnotationOrElementTap$scandit_barcode_capture(null);
        } else if (a instanceof BarcodeArResponsiveAnnotation) {
            ((BarcodeArResponsiveAnnotation) a).setOnAnnotationOrElementTap$scandit_barcode_capture(null);
        }
        annotationData.a(false);
        removeView(annotationData.b());
        View b = barcodeArAugmentation$Highlight != null ? barcodeArAugmentation$Highlight.b() : null;
        if (b == null) {
            return;
        }
        b.setZ(1.0f);
    }

    public static final void a(View view, BarcodeArAugmentation$Annotation annotationData) {
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(annotationData, "$annotationData");
        view.setVisibility(0);
        annotationData.a(false);
    }
}
