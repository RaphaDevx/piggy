package com.scandit.datacapture.barcode.internal.module.count.ui.popover;

import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayColorScheme;

/* loaded from: classes2.dex */
public abstract /* synthetic */ class b {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[NativeBarcodeCountBasicOverlayColorScheme.values().length];
        try {
            iArr[NativeBarcodeCountBasicOverlayColorScheme.DEFAULT.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[NativeBarcodeCountBasicOverlayColorScheme.ACCESSIBLE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        a = iArr;
    }
}
