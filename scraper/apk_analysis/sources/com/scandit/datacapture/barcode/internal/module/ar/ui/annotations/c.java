package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations;

import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotationTrigger;

/* loaded from: classes2.dex */
public abstract /* synthetic */ class c {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[BarcodeArAnnotationTrigger.values().length];
        try {
            iArr[BarcodeArAnnotationTrigger.HIGHLIGHT_TAP.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[BarcodeArAnnotationTrigger.HIGHLIGHT_TAP_AND_BARCODE_SCAN.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[BarcodeArAnnotationTrigger.BARCODE_SCAN.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        a = iArr;
    }
}
