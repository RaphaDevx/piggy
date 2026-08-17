package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.view.ViewGroup;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.k0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0360k0 extends Lambda implements Function1 {
    public final /* synthetic */ C0362l0 a;
    public final /* synthetic */ String b;
    public final /* synthetic */ Function0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0360k0(C0362l0 c0362l0, String str, Function0 function0) {
        super(1);
        this.a = c0362l0;
        this.b = str;
        this.c = function0;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ViewGroup it = (ViewGroup) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        com.scandit.datacapture.barcode.internal.module.ui.b bVar = this.a.d;
        if (bVar != null) {
            bVar.a(this.b);
        }
        this.c.invoke();
        return Unit.INSTANCE;
    }
}
