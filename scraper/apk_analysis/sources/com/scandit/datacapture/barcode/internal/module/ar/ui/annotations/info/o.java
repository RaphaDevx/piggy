package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info;

import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation;
import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent;
import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationListener;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class o implements BarcodeArInfoAnnotationListener {
    public final Function0 a;
    public final Function0 b;

    public o(com.scandit.datacapture.barcode.ar.ui.annotations.n getListener, com.scandit.datacapture.barcode.ar.ui.annotations.o onAnnotationOrElementTap) {
        Intrinsics.checkNotNullParameter(getListener, "getListener");
        Intrinsics.checkNotNullParameter(onAnnotationOrElementTap, "onAnnotationOrElementTap");
        this.a = getListener;
        this.b = onAnnotationOrElementTap;
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationListener
    public final void onInfoAnnotationFooterTapped(BarcodeArInfoAnnotation annotation) {
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        BarcodeArInfoAnnotationListener barcodeArInfoAnnotationListener = (BarcodeArInfoAnnotationListener) this.a.invoke();
        if (barcodeArInfoAnnotationListener != null) {
            barcodeArInfoAnnotationListener.onInfoAnnotationFooterTapped(annotation);
        }
        this.b.invoke();
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationListener
    public final void onInfoAnnotationHeaderTapped(BarcodeArInfoAnnotation annotation) {
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        BarcodeArInfoAnnotationListener barcodeArInfoAnnotationListener = (BarcodeArInfoAnnotationListener) this.a.invoke();
        if (barcodeArInfoAnnotationListener != null) {
            barcodeArInfoAnnotationListener.onInfoAnnotationHeaderTapped(annotation);
        }
        this.b.invoke();
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationListener
    public final void onInfoAnnotationLeftIconTapped(BarcodeArInfoAnnotation annotation, BarcodeArInfoAnnotationBodyComponent component, int i) {
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        Intrinsics.checkNotNullParameter(component, "component");
        BarcodeArInfoAnnotationListener barcodeArInfoAnnotationListener = (BarcodeArInfoAnnotationListener) this.a.invoke();
        if (barcodeArInfoAnnotationListener != null) {
            barcodeArInfoAnnotationListener.onInfoAnnotationLeftIconTapped(annotation, component, i);
        }
        this.b.invoke();
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationListener
    public final void onInfoAnnotationRightIconTapped(BarcodeArInfoAnnotation annotation, BarcodeArInfoAnnotationBodyComponent component, int i) {
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        Intrinsics.checkNotNullParameter(component, "component");
        BarcodeArInfoAnnotationListener barcodeArInfoAnnotationListener = (BarcodeArInfoAnnotationListener) this.a.invoke();
        if (barcodeArInfoAnnotationListener != null) {
            barcodeArInfoAnnotationListener.onInfoAnnotationRightIconTapped(annotation, component, i);
        }
        this.b.invoke();
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationListener
    public final void onInfoAnnotationTapped(BarcodeArInfoAnnotation annotation) {
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        BarcodeArInfoAnnotationListener barcodeArInfoAnnotationListener = (BarcodeArInfoAnnotationListener) this.a.invoke();
        if (barcodeArInfoAnnotationListener != null) {
            barcodeArInfoAnnotationListener.onInfoAnnotationTapped(annotation);
        }
        this.b.invoke();
    }
}
