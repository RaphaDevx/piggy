package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.statusicon;

import com.scandit.datacapture.barcode.ar.ui.annotations.statusicon.BarcodeArStatusIconAnnotationAnchor;

/* loaded from: classes2.dex */
public abstract /* synthetic */ class o {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[BarcodeArStatusIconAnnotationAnchor.values().length];
        try {
            iArr[BarcodeArStatusIconAnnotationAnchor.TOP.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[BarcodeArStatusIconAnnotationAnchor.BOTTOM.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[BarcodeArStatusIconAnnotationAnchor.LEFT.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[BarcodeArStatusIconAnnotationAnchor.RIGHT.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        a = iArr;
    }
}
