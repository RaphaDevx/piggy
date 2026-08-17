package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info;

import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationWidthPreset;

/* loaded from: classes2.dex */
public abstract /* synthetic */ class t {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[BarcodeArInfoAnnotationWidthPreset.values().length];
        try {
            iArr[BarcodeArInfoAnnotationWidthPreset.SMALL.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[BarcodeArInfoAnnotationWidthPreset.MEDIUM.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[BarcodeArInfoAnnotationWidthPreset.LARGE.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        a = iArr;
    }
}
