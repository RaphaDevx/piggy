package com.scandit.datacapture.barcode.internal.module.find.ui;

import android.view.View;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class U extends Lambda implements Function1 {
    public final /* synthetic */ u0 a;
    public final /* synthetic */ boolean b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public U(u0 u0Var, boolean z) {
        super(1);
        this.a = u0Var;
        this.b = z;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        C0390a it = (C0390a) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        C0390a c0390a = this.a.d;
        boolean z = this.b;
        c0390a.g = z;
        int childCount = c0390a.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = c0390a.getChildAt(i);
            C0402m c0402m = childAt instanceof C0402m ? (C0402m) childAt : null;
            if (c0402m != null) {
                if (!z || c0402m.e) {
                    c0402m.d.stop();
                } else {
                    c0402m.d.start();
                }
            }
        }
        return Unit.INSTANCE;
    }
}
