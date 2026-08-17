package com.scandit.datacapture.barcode.internal.module.find.ui;

import android.view.View;
import com.scandit.datacapture.barcode.find.capture.BarcodeFindItem;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class S extends Lambda implements Function1 {
    public final /* synthetic */ u0 a;
    public final /* synthetic */ BarcodeFindItem b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S(u0 u0Var, BarcodeFindItem barcodeFindItem) {
        super(1);
        this.a = u0Var;
        this.b = barcodeFindItem;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Object obj2;
        C0390a it = (C0390a) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        List list = this.a.d.b;
        BarcodeFindItem barcodeFindItem = this.b;
        Iterator it2 = list.iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = it2.next();
            if (Intrinsics.areEqual(((y0) obj2).b, barcodeFindItem)) {
                break;
            }
        }
        y0 dotInfo = (y0) obj2;
        if (dotInfo != null) {
            C0390a c0390a = this.a.d;
            c0390a.getClass();
            Intrinsics.checkNotNullParameter(dotInfo, "dotInfo");
            int childCount = c0390a.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = c0390a.getChildAt(i);
                C0402m c0402m = childAt instanceof C0402m ? (C0402m) childAt : null;
                if (c0402m != null) {
                    if (Intrinsics.areEqual(c0402m.getTag(), dotInfo)) {
                        c0402m.d.start();
                    } else {
                        c0402m.d.stop();
                    }
                }
            }
        }
        return Unit.INSTANCE;
    }
}
