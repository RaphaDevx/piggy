package com.scandit.datacapture.core.internal.module.source;

/* loaded from: classes2.dex */
public abstract /* synthetic */ class E {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[NativeCameraApi.values().length];
        try {
            iArr[NativeCameraApi.CAMERA1.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[NativeCameraApi.CAMERA2.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        a = iArr;
    }
}
