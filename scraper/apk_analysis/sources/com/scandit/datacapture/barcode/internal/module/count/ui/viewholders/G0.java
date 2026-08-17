package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.view.ViewGroup;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class G0 extends Lambda implements Function1 {
    public final /* synthetic */ J0 a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ Function0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public G0(J0 j0, boolean z, Function0 function0) {
        super(1);
        this.a = j0;
        this.b = z;
        this.c = function0;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ViewGroup it = (ViewGroup) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        com.scandit.datacapture.barcode.internal.module.count.ui.n nVar = this.a.h;
        if (nVar != null) {
            if (this.b) {
                nVar.d.start();
            } else {
                nVar.d.stop();
            }
        }
        com.scandit.datacapture.barcode.internal.module.count.ui.n nVar2 = this.a.i;
        if (nVar2 != null) {
            nVar2.d.stop();
        }
        this.c.invoke();
        return Unit.INSTANCE;
    }
}
