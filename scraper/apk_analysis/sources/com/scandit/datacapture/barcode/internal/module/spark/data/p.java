package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.core.source.CameraPosition;

/* loaded from: classes2.dex */
public abstract /* synthetic */ class p {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[CameraPosition.values().length];
        try {
            iArr[CameraPosition.USER_FACING.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        a = iArr;
    }
}
