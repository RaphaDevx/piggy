package com.scandit.datacapture.core.internal.module.ui.hint;

import com.scandit.datacapture.core.internal.module.ui.NativeHintIcon;

/* loaded from: classes2.dex */
public abstract /* synthetic */ class r {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[NativeHintIcon.values().length];
        try {
            iArr[NativeHintIcon.NONE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[NativeHintIcon.CHECK.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[NativeHintIcon.EXCLAMATION_MARK.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[NativeHintIcon.RESOURCE.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        a = iArr;
    }
}
