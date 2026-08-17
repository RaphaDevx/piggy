package com.scandit.datacapture.core.internal.module.source;

/* loaded from: classes2.dex */
public abstract /* synthetic */ class P {
    public static final /* synthetic */ int[] a;
    public static final /* synthetic */ int[] b;

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
        int[] iArr2 = new int[NativePreferredFrameRateRange.values().length];
        try {
            iArr2[NativePreferredFrameRateRange.HIGHEST_WIDEST.ordinal()] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[NativePreferredFrameRateRange.HIGHEST_NARROWEST.ordinal()] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        b = iArr2;
    }
}
