package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class L {
    public static void a(X x, boolean z, String str) {
        M onFinished = M.a;
        Intrinsics.checkNotNullParameter(onFinished, "onFinished");
        if (x.a) {
            x.d.a(new U(x, z, str, onFinished));
        }
    }

    public static void b(X x, boolean z, String str) {
        N onFinished = N.a;
        Intrinsics.checkNotNullParameter(onFinished, "onFinished");
        if (x.a) {
            x.d.a(new W(x, z, str, onFinished));
        }
    }
}
