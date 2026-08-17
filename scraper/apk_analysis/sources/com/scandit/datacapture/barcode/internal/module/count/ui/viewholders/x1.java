package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.view.ViewGroup;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class x1 extends Lambda implements Function1 {
    public final /* synthetic */ boolean a;
    public final /* synthetic */ y1 b;
    public final /* synthetic */ String c;
    public final /* synthetic */ Function0 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x1(boolean z, y1 y1Var, String str, Function0 function0) {
        super(1);
        this.a = z;
        this.b = y1Var;
        this.c = str;
        this.d = function0;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ViewGroup it = (ViewGroup) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        if (this.a) {
            com.scandit.datacapture.barcode.internal.module.ui.n nVar = this.b.b;
            if (nVar != null) {
                com.scandit.datacapture.barcode.internal.module.ui.l lVar = com.scandit.datacapture.barcode.internal.module.ui.l.c;
                String str = this.c;
                if (str == null) {
                    str = "";
                }
                nVar.a(lVar, str);
            }
        } else {
            com.scandit.datacapture.barcode.internal.module.ui.n nVar2 = this.b.b;
            if (nVar2 != null) {
                com.scandit.datacapture.barcode.internal.module.ui.l guidanceState = com.scandit.datacapture.barcode.internal.module.ui.l.c;
                Intrinsics.checkNotNullParameter(guidanceState, "guidanceState");
                if (nVar2.d == guidanceState) {
                    nVar2.a();
                }
            }
        }
        this.d.invoke();
        return Unit.INSTANCE;
    }
}
