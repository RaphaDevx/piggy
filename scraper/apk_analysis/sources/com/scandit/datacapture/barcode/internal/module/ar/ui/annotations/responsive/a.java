package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.responsive;

import com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArAnnotationVariation;

/* loaded from: classes2.dex */
public abstract /* synthetic */ class a {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[NativeBarcodeArAnnotationVariation.values().length];
        try {
            iArr[NativeBarcodeArAnnotationVariation.UNKNOWN.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[NativeBarcodeArAnnotationVariation.CLOSE_UP.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[NativeBarcodeArAnnotationVariation.FAR_AWAY.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        a = iArr;
    }
}
