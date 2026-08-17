package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations;

import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationAnchor;
import com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationAnchor;
import com.scandit.datacapture.barcode.ar.ui.annotations.statusicon.BarcodeArStatusIconAnnotationAnchor;

/* loaded from: classes2.dex */
public abstract /* synthetic */ class a {
    public static final /* synthetic */ int[] a;
    public static final /* synthetic */ int[] b;
    public static final /* synthetic */ int[] c;

    static {
        int[] iArr = new int[BarcodeArInfoAnnotationAnchor.values().length];
        try {
            iArr[BarcodeArInfoAnnotationAnchor.TOP.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[BarcodeArInfoAnnotationAnchor.BOTTOM.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[BarcodeArInfoAnnotationAnchor.LEFT.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[BarcodeArInfoAnnotationAnchor.RIGHT.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        a = iArr;
        int[] iArr2 = new int[BarcodeArStatusIconAnnotationAnchor.values().length];
        try {
            iArr2[BarcodeArStatusIconAnnotationAnchor.TOP.ordinal()] = 1;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr2[BarcodeArStatusIconAnnotationAnchor.BOTTOM.ordinal()] = 2;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            iArr2[BarcodeArStatusIconAnnotationAnchor.LEFT.ordinal()] = 3;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            iArr2[BarcodeArStatusIconAnnotationAnchor.RIGHT.ordinal()] = 4;
        } catch (NoSuchFieldError unused8) {
        }
        b = iArr2;
        int[] iArr3 = new int[BarcodeArPopoverAnnotationAnchor.values().length];
        try {
            iArr3[BarcodeArPopoverAnnotationAnchor.TOP.ordinal()] = 1;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            iArr3[BarcodeArPopoverAnnotationAnchor.BOTTOM.ordinal()] = 2;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            iArr3[BarcodeArPopoverAnnotationAnchor.LEFT.ordinal()] = 3;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            iArr3[BarcodeArPopoverAnnotationAnchor.RIGHT.ordinal()] = 4;
        } catch (NoSuchFieldError unused12) {
        }
        c = iArr3;
    }
}
