package com.scandit.datacapture.barcode.internal.module.find.ui.wrappers;

import com.scandit.datacapture.barcode.find.ui.ScreenSizeCategory;

/* loaded from: classes2.dex */
public abstract /* synthetic */ class C {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[ScreenSizeCategory.values().length];
        try {
            iArr[ScreenSizeCategory.SMALL.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[ScreenSizeCategory.MID.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[ScreenSizeCategory.LARGE.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        a = iArr;
    }
}
