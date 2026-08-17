package com.scandit.datacapture.core.internal.module.source.api2;

import com.scandit.datacapture.core.source.FrameSourceState;

/* renamed from: com.scandit.datacapture.core.internal.module.source.api2.e, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract /* synthetic */ class AbstractC0562e {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[FrameSourceState.values().length];
        try {
            iArr[FrameSourceState.ON.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[FrameSourceState.STANDBY.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        a = iArr;
    }
}
