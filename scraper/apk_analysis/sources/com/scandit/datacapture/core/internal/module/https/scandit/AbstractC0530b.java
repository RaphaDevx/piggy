package com.scandit.datacapture.core.internal.module.https.scandit;

import com.scandit.datacapture.core.internal.module.https.NativeHttpsMethod;

/* renamed from: com.scandit.datacapture.core.internal.module.https.scandit.b, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract /* synthetic */ class AbstractC0530b {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[NativeHttpsMethod.values().length];
        try {
            iArr[NativeHttpsMethod.GET.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[NativeHttpsMethod.POST.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        a = iArr;
    }
}
