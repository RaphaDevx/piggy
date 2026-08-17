package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.popover;

import com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationAnchor;

/* loaded from: classes2.dex */
public abstract /* synthetic */ class a {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[BarcodeArPopoverAnnotationAnchor.values().length];
        try {
            iArr[BarcodeArPopoverAnnotationAnchor.TOP.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[BarcodeArPopoverAnnotationAnchor.BOTTOM.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[BarcodeArPopoverAnnotationAnchor.LEFT.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[BarcodeArPopoverAnnotationAnchor.RIGHT.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        a = iArr;
    }
}
