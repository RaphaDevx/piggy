package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.view.ViewGroup;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.p, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0369p extends Lambda implements Function1 {
    public final /* synthetic */ C0371q a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ Function0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0369p(C0371q c0371q, boolean z, Function0 function0) {
        super(1);
        this.a = c0371q;
        this.b = z;
        this.c = function0;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ViewGroup it = (ViewGroup) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        com.scandit.datacapture.barcode.internal.module.count.ui.h hVar = this.a.e;
        if (hVar != null) {
            hVar.setEnabled(this.b);
        }
        this.c.invoke();
        return Unit.INSTANCE;
    }
}
