package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayColorScheme;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayStyle;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.b, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract /* synthetic */ class AbstractC0299b {
    public static final /* synthetic */ int[] a;
    public static final /* synthetic */ int[] b;

    static {
        int[] iArr = new int[NativeBarcodeCountBasicOverlayStyle.values().length];
        try {
            iArr[NativeBarcodeCountBasicOverlayStyle.DOT.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[NativeBarcodeCountBasicOverlayStyle.ICON.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        a = iArr;
        int[] iArr2 = new int[W.values().length];
        try {
            iArr2[1] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            W w = W.b;
            iArr2[2] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            W w2 = W.b;
            iArr2[0] = 3;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            W w3 = W.b;
            iArr2[3] = 4;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            W w4 = W.b;
            iArr2[4] = 5;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            W w5 = W.b;
            iArr2[5] = 6;
        } catch (NoSuchFieldError unused8) {
        }
        int[] iArr3 = new int[EnumC0298a.values().length];
        try {
            iArr3[0] = 1;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            EnumC0298a enumC0298a = EnumC0298a.a;
            iArr3[1] = 2;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            EnumC0298a enumC0298a2 = EnumC0298a.a;
            iArr3[2] = 3;
        } catch (NoSuchFieldError unused11) {
        }
        int[] iArr4 = new int[NativeBarcodeCountBasicOverlayColorScheme.values().length];
        try {
            iArr4[NativeBarcodeCountBasicOverlayColorScheme.DEFAULT.ordinal()] = 1;
        } catch (NoSuchFieldError unused12) {
        }
        try {
            iArr4[NativeBarcodeCountBasicOverlayColorScheme.ACCESSIBLE.ordinal()] = 2;
        } catch (NoSuchFieldError unused13) {
        }
        b = iArr4;
    }
}
