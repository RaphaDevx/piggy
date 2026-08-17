package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.u0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract class AbstractC0379u0 {
    public static void a(InterfaceC0385x0 interfaceC0385x0, boolean z) {
        C0381v0 onFinished = C0381v0.a;
        J0 j0 = (J0) interfaceC0385x0;
        j0.getClass();
        Intrinsics.checkNotNullParameter(onFinished, "onFinished");
        synchronized (j0) {
            j0.j = Boolean.valueOf(z);
            if (j0.d) {
                j0.g.a(new F0(j0, z, onFinished));
            } else {
                j0.f.a(new G0(j0, z, onFinished));
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public static void b(InterfaceC0385x0 interfaceC0385x0, boolean z) {
        C0383w0 onFinished = C0383w0.a;
        J0 j0 = (J0) interfaceC0385x0;
        j0.getClass();
        Intrinsics.checkNotNullParameter(onFinished, "onFinished");
        I0 onFinished2 = new I0(j0, z, onFinished);
        Intrinsics.checkNotNullParameter(onFinished2, "onFinished");
        j0.f.a(new H0(j0, z, onFinished2));
    }
}
