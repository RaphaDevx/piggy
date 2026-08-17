package com.scandit.datacapture.barcode.ar.ui.annotations.responsive;

import android.content.Context;
import android.view.View;
import com.scandit.datacapture.barcode.ar.ui.BarcodeArViewDefaults;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotationTrigger;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArAnnotationVariation;
import com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.responsive.b;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.tealium.library.DataSources;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 22\u00020\u0001:\u00012B)\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ)\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\"\u0010 \u001a\u00020\u00198\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR:\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010!2\u000e\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010!8\u0000@@X\u0080\u000e¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\"\u00101\u001a\u00020*8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100¨\u00063"}, d2 = {"Lcom/scandit/datacapture/barcode/ar/ui/annotations/responsive/BarcodeArResponsiveAnnotation;", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotation;", "Landroid/content/Context;", "context", "Lcom/scandit/datacapture/barcode/data/Barcode;", "barcode", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArInfoAnnotation;", "closeUpAnnotation", "farAwayAnnotation", "<init>", "(Landroid/content/Context;Lcom/scandit/datacapture/barcode/data/Barcode;Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArInfoAnnotation;Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArInfoAnnotation;)V", "Landroid/view/View;", "createView", "()Landroid/view/View;", "Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "barcodeLocation", "highlightViewLocation", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "", "update", "(Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;Landroid/view/View;)V", "a", "Lcom/scandit/datacapture/barcode/data/Barcode;", "getBarcode", "()Lcom/scandit/datacapture/barcode/data/Barcode;", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationTrigger;", "e", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationTrigger;", "getAnnotationTrigger", "()Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationTrigger;", "setAnnotationTrigger", "(Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationTrigger;)V", "annotationTrigger", "Lkotlin/Function0;", "value", "f", "Lkotlin/jvm/functions/Function0;", "getOnAnnotationOrElementTap$scandit_barcode_capture", "()Lkotlin/jvm/functions/Function0;", "setOnAnnotationOrElementTap$scandit_barcode_capture", "(Lkotlin/jvm/functions/Function0;)V", "onAnnotationOrElementTap", "Lcom/scandit/datacapture/barcode/internal/module/ar/ui/NativeBarcodeArAnnotationVariation;", "g", "Lcom/scandit/datacapture/barcode/internal/module/ar/ui/NativeBarcodeArAnnotationVariation;", "getVariation$scandit_barcode_capture", "()Lcom/scandit/datacapture/barcode/internal/module/ar/ui/NativeBarcodeArAnnotationVariation;", "setVariation$scandit_barcode_capture", "(Lcom/scandit/datacapture/barcode/internal/module/ar/ui/NativeBarcodeArAnnotationVariation;)V", "variation", "Companion", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeArResponsiveAnnotation implements BarcodeArAnnotation {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static float h = BarcodeArViewDefaults.getDefaultResponsiveAnnotationThreshold();

    /* renamed from: a, reason: from kotlin metadata */
    private final Barcode barcode;
    private final Context b;
    private final BarcodeArAnnotation c;
    private final BarcodeArAnnotation d;

    /* renamed from: e, reason: from kotlin metadata */
    private BarcodeArAnnotationTrigger annotationTrigger;

    /* renamed from: f, reason: from kotlin metadata */
    private /* synthetic */ Function0 onAnnotationOrElementTap;

    /* renamed from: g, reason: from kotlin metadata */
    private /* synthetic */ NativeBarcodeArAnnotationVariation variation;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001R(\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b\u0003\u0010\u0004\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/scandit/datacapture/barcode/ar/ui/annotations/responsive/BarcodeArResponsiveAnnotation$Companion;", "", "", "threshold", "F", "getThreshold", "()F", "setThreshold", "(F)V", "getThreshold$annotations", "()V", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public static /* synthetic */ void getThreshold$annotations() {
        }

        public final float getThreshold() {
            return BarcodeArResponsiveAnnotation.h;
        }

        public final void setThreshold(float f) {
            BarcodeArResponsiveAnnotation.h = f;
        }
    }

    public BarcodeArResponsiveAnnotation(Context context, Barcode barcode, BarcodeArInfoAnnotation closeUpAnnotation, BarcodeArInfoAnnotation farAwayAnnotation) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        Intrinsics.checkNotNullParameter(closeUpAnnotation, "closeUpAnnotation");
        Intrinsics.checkNotNullParameter(farAwayAnnotation, "farAwayAnnotation");
        this.barcode = barcode;
        this.b = context;
        this.c = closeUpAnnotation;
        this.d = farAwayAnnotation;
        this.annotationTrigger = BarcodeArViewDefaults.getDefaultResponsiveAnnotationTrigger();
        this.variation = NativeBarcodeArAnnotationVariation.UNKNOWN;
    }

    public static final float getThreshold() {
        return INSTANCE.getThreshold();
    }

    public static final void setThreshold(float f) {
        INSTANCE.setThreshold(f);
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation
    public View createView() {
        return new b(this.b, this.c, this.d);
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation
    public BarcodeArAnnotationTrigger getAnnotationTrigger() {
        return this.annotationTrigger;
    }

    public final Barcode getBarcode() {
        return this.barcode;
    }

    public final Function0<Unit> getOnAnnotationOrElementTap$scandit_barcode_capture() {
        return this.onAnnotationOrElementTap;
    }

    /* renamed from: getVariation$scandit_barcode_capture, reason: from getter */
    public final NativeBarcodeArAnnotationVariation getVariation() {
        return this.variation;
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation
    public void setAnnotationTrigger(BarcodeArAnnotationTrigger barcodeArAnnotationTrigger) {
        Intrinsics.checkNotNullParameter(barcodeArAnnotationTrigger, "<set-?>");
        this.annotationTrigger = barcodeArAnnotationTrigger;
    }

    public final void setOnAnnotationOrElementTap$scandit_barcode_capture(Function0<Unit> function0) {
        BarcodeArAnnotation barcodeArAnnotation = this.c;
        if (barcodeArAnnotation instanceof BarcodeArInfoAnnotation) {
            ((BarcodeArInfoAnnotation) barcodeArAnnotation).setOnAnnotationOrElementTap$scandit_barcode_capture(function0);
        }
        BarcodeArAnnotation barcodeArAnnotation2 = this.d;
        if (barcodeArAnnotation2 instanceof BarcodeArInfoAnnotation) {
            ((BarcodeArInfoAnnotation) barcodeArAnnotation2).setOnAnnotationOrElementTap$scandit_barcode_capture(function0);
        }
        this.onAnnotationOrElementTap = function0;
    }

    public final void setVariation$scandit_barcode_capture(NativeBarcodeArAnnotationVariation nativeBarcodeArAnnotationVariation) {
        Intrinsics.checkNotNullParameter(nativeBarcodeArAnnotationVariation, "<set-?>");
        this.variation = nativeBarcodeArAnnotationVariation;
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation
    public void update(Quadrilateral barcodeLocation, Quadrilateral highlightViewLocation, View view) {
        Intrinsics.checkNotNullParameter(barcodeLocation, "barcodeLocation");
        Intrinsics.checkNotNullParameter(view, "view");
        b bVar = view instanceof b ? (b) view : null;
        if (bVar != null) {
            bVar.a(barcodeLocation, highlightViewLocation, this.variation);
        }
    }
}
