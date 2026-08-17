package com.scandit.datacapture.barcode.internal.module.spark.ui.button;

import com.scandit.datacapture.core.ui.orientation.DeviceOrientation;

/* loaded from: classes2.dex */
public abstract /* synthetic */ class j {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[DeviceOrientation.values().length];
        try {
            iArr[DeviceOrientation.LANDSCAPE_LEFT.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        a = iArr;
    }
}
