package com.scandit.datacapture.barcode.internal.module.count.ui;

import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListStatus;
import com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.M;
import com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.N;

/* loaded from: classes2.dex */
public abstract /* synthetic */ class d {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[NativeBarcodeCountNotInListStatus.values().length];
        try {
            iArr[NativeBarcodeCountNotInListStatus.ACCEPTED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[NativeBarcodeCountNotInListStatus.REJECTED.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        a = iArr;
        int[] iArr2 = new int[M.values().length];
        try {
            iArr2[0] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            M m = M.a;
            iArr2[1] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            M m2 = M.a;
            iArr2[2] = 3;
        } catch (NoSuchFieldError unused5) {
        }
        int[] iArr3 = new int[N.values().length];
        try {
            iArr3[0] = 1;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            N n = N.a;
            iArr3[1] = 2;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            N n2 = N.a;
            iArr3[2] = 3;
        } catch (NoSuchFieldError unused8) {
        }
    }
}
