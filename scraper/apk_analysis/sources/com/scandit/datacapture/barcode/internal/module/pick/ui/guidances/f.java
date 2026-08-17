package com.scandit.datacapture.barcode.internal.module.pick.ui.guidances;

import android.view.View;
import android.widget.FrameLayout;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class f extends Lambda implements Function1 {
    public final /* synthetic */ g a;
    public final /* synthetic */ boolean b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(g gVar, boolean z) {
        super(1);
        this.a = gVar;
        this.b = z;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        g it = (g) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        View view = (View) this.a.b.getValue();
        com.scandit.datacapture.barcode.internal.module.pick.ui.e eVar = view instanceof com.scandit.datacapture.barcode.internal.module.pick.ui.e ? (com.scandit.datacapture.barcode.internal.module.pick.ui.e) view : null;
        if (eVar != null) {
            eVar.c.setText(this.b ? eVar.a : eVar.b);
        }
        g gVar = this.a;
        if (!gVar.c) {
            gVar.addView((View) gVar.b.getValue(), new FrameLayout.LayoutParams(-1, -1));
            ((View) this.a.b.getValue()).bringToFront();
            this.a.c = true;
        }
        return Unit.INSTANCE;
    }
}
