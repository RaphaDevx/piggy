package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a {
    public final Quadrilateral a;
    public final Quadrilateral b;
    public final TrackedBarcode c;
    public final BarcodeArAugmentation$Highlight d;
    public final BarcodeArAugmentation$Annotation e;

    public a(Quadrilateral locationIgnoringLicense, Quadrilateral locationConsideringLicense, TrackedBarcode track, BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight, BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation) {
        Intrinsics.checkNotNullParameter(locationIgnoringLicense, "locationIgnoringLicense");
        Intrinsics.checkNotNullParameter(locationConsideringLicense, "locationConsideringLicense");
        Intrinsics.checkNotNullParameter(track, "track");
        this.a = locationIgnoringLicense;
        this.b = locationConsideringLicense;
        this.c = track;
        this.d = barcodeArAugmentation$Highlight;
        this.e = barcodeArAugmentation$Annotation;
    }

    public final Quadrilateral a() {
        BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation = this.e;
        return Intrinsics.areEqual(barcodeArAugmentation$Annotation != null ? Boolean.valueOf(barcodeArAugmentation$Annotation.d()) : null, Boolean.TRUE) ? this.a : this.b;
    }

    public final Quadrilateral b() {
        BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight = this.d;
        return Intrinsics.areEqual(barcodeArAugmentation$Highlight != null ? Boolean.valueOf(barcodeArAugmentation$Highlight.d()) : null, Boolean.TRUE) ? this.a : this.b;
    }
}
