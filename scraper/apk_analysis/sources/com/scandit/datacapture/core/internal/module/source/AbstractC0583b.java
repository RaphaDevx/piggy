package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.source.TorchState;

/* renamed from: com.scandit.datacapture.core.internal.module.source.b, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract /* synthetic */ class AbstractC0583b {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[TorchState.values().length];
        try {
            iArr[TorchState.OFF.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[TorchState.ON.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        a = iArr;
    }
}
