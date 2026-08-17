package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations;

import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationAnchor;
import com.scandit.datacapture.barcode.ar.ui.annotations.statusicon.BarcodeArStatusIconAnnotationAnchor;
import com.scandit.datacapture.core.common.geometry.Anchor;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class b {
    public static final /* synthetic */ Anchor a(BarcodeArInfoAnnotationAnchor barcodeArInfoAnnotationAnchor) {
        Intrinsics.checkNotNullParameter(barcodeArInfoAnnotationAnchor, "<this>");
        int i = a.a[barcodeArInfoAnnotationAnchor.ordinal()];
        if (i == 1) {
            return Anchor.TOP_CENTER;
        }
        if (i == 2) {
            return Anchor.BOTTOM_CENTER;
        }
        if (i == 3) {
            return Anchor.CENTER_LEFT;
        }
        if (i == 4) {
            return Anchor.CENTER_RIGHT;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final /* synthetic */ Anchor a(BarcodeArStatusIconAnnotationAnchor barcodeArStatusIconAnnotationAnchor) {
        Intrinsics.checkNotNullParameter(barcodeArStatusIconAnnotationAnchor, "<this>");
        int i = a.b[barcodeArStatusIconAnnotationAnchor.ordinal()];
        if (i == 1) {
            return Anchor.TOP_CENTER;
        }
        if (i == 2) {
            return Anchor.BOTTOM_CENTER;
        }
        if (i == 3) {
            return Anchor.CENTER_LEFT;
        }
        if (i == 4) {
            return Anchor.CENTER_RIGHT;
        }
        throw new NoWhenBranchMatchedException();
    }
}
