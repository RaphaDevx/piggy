package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import com.scandit.datacapture.barcode.spark.ui.SparkScanMiniPreviewSize;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.n, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract /* synthetic */ class AbstractC0465n {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[SparkScanMiniPreviewSize.values().length];
        try {
            iArr[SparkScanMiniPreviewSize.EXPANDED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[SparkScanMiniPreviewSize.REGULAR.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        a = iArr;
    }
}
