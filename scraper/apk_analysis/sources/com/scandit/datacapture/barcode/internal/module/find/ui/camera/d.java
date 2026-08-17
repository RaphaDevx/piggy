package com.scandit.datacapture.barcode.internal.module.find.ui.camera;

import android.view.View;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class d extends Lambda implements Function1 {
    public final /* synthetic */ e a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(e eVar) {
        super(1);
        this.a = eVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        View view = (View) obj;
        Intrinsics.checkNotNullParameter(view, "view");
        i iVar = (i) view;
        e eVar = this.a;
        int childCount = eVar.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = eVar.getChildAt(i);
            Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type com.scandit.datacapture.barcode.internal.module.find.ui.camera.ZoomButton");
            i iVar2 = (i) childAt;
            iVar2.setSelected(Intrinsics.areEqual(iVar2, iVar));
        }
        float f = iVar.b;
        if (eVar.b != f) {
            eVar.b = f;
            eVar.a();
        }
        e eVar2 = this.a;
        Function1 function1 = eVar2.c;
        if (function1 != null) {
            function1.invoke(Float.valueOf(eVar2.b));
        }
        return Unit.INSTANCE;
    }
}
