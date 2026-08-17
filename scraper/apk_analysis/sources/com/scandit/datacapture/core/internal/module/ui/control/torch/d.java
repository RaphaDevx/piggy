package com.scandit.datacapture.core.internal.module.ui.control.torch;

import com.scandit.datacapture.core.source.TorchState;

/* loaded from: classes2.dex */
public abstract /* synthetic */ class d {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[TorchState.values().length];
        try {
            iArr[TorchState.ON.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[TorchState.OFF.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[TorchState.AUTO.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        a = iArr;
    }
}
