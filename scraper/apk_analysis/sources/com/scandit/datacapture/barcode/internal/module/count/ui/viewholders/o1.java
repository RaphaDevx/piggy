package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class o1 {
    public static void a(y1 y1Var, boolean z, String str) {
        q1 onFinished = q1.a;
        Intrinsics.checkNotNullParameter(onFinished, "onFinished");
        if (y1Var.a) {
            y1Var.c.a(new w1(z, y1Var, str, onFinished));
        }
    }

    public static void b(y1 y1Var, boolean z, String str) {
        r1 onFinished = r1.a;
        Intrinsics.checkNotNullParameter(onFinished, "onFinished");
        if (y1Var.a) {
            y1Var.c.a(new x1(z, y1Var, str, onFinished));
        }
    }
}
