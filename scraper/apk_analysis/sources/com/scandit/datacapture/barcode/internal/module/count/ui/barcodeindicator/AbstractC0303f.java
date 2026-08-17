package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListStatus;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.f, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract /* synthetic */ class AbstractC0303f {
    public static final /* synthetic */ int[] a;
    public static final /* synthetic */ int[] b;

    static {
        int[] iArr = new int[W.values().length];
        try {
            iArr[5] = 1;
        } catch (NoSuchFieldError unused) {
        }
        a = iArr;
        int[] iArr2 = new int[N.values().length];
        try {
            iArr2[0] = 1;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            N n = N.a;
            iArr2[1] = 2;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            N n2 = N.a;
            iArr2[2] = 3;
        } catch (NoSuchFieldError unused4) {
        }
        int[] iArr3 = new int[NativeBarcodeCountNotInListStatus.values().length];
        try {
            iArr3[NativeBarcodeCountNotInListStatus.NONE.ordinal()] = 1;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr3[NativeBarcodeCountNotInListStatus.ACCEPTED.ordinal()] = 2;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            iArr3[NativeBarcodeCountNotInListStatus.REJECTED.ordinal()] = 3;
        } catch (NoSuchFieldError unused7) {
        }
        b = iArr3;
        int[] iArr4 = new int[M.values().length];
        try {
            iArr4[2] = 1;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            M m = M.a;
            iArr4[1] = 2;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            M m2 = M.a;
            iArr4[0] = 3;
        } catch (NoSuchFieldError unused10) {
        }
    }
}
