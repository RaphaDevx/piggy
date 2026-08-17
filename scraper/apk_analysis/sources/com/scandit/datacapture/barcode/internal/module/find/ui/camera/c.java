package com.scandit.datacapture.barcode.internal.module.find.ui.camera;

import android.view.View;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function1 {
    public final /* synthetic */ e a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(e eVar) {
        super(1);
        this.a = eVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        View view = (View) obj;
        Intrinsics.checkNotNullParameter(view, "view");
        e eVar = this.a;
        eVar.getClass();
        float f = ((i) view).b;
        Iterator it = eVar.a.iterator();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                i2 = -1;
                break;
            }
            if (Math.abs(((Number) it.next()).floatValue() - f) < 0.001f) {
                break;
            }
            i2++;
        }
        if (i2 != -1 && i2 != CollectionsKt.getLastIndex(eVar.a)) {
            i = i2 + 1;
        }
        eVar.a(((Number) eVar.a.get(i)).floatValue());
        e eVar2 = this.a;
        Function1 function1 = eVar2.c;
        if (function1 != null) {
            function1.invoke(Float.valueOf(eVar2.b));
        }
        return Unit.INSTANCE;
    }
}
