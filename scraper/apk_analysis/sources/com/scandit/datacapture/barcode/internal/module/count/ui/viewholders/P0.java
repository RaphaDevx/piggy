package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class P0 {
    public static void a(Z0 z0, boolean z) {
        Q0 onFinished = Q0.a;
        Intrinsics.checkNotNullParameter(onFinished, "onFinished");
        com.scandit.datacapture.barcode.internal.module.count.ui.p pVar = z0.d;
        if (pVar == null || pVar.b != z) {
            z0.e.a(new X0(z0, z, onFinished));
        } else {
            onFinished.getClass();
            Unit unit = Unit.INSTANCE;
        }
    }

    public static void b(Z0 z0, boolean z) {
        R0 onFinished = R0.a;
        Intrinsics.checkNotNullParameter(onFinished, "onFinished");
        com.scandit.datacapture.barcode.internal.module.count.ui.p pVar = z0.d;
        if (pVar == null || pVar.isEnabled() != z) {
            z0.e.a(new Y0(z0, z, onFinished));
        } else {
            onFinished.getClass();
            Unit unit = Unit.INSTANCE;
        }
    }
}
