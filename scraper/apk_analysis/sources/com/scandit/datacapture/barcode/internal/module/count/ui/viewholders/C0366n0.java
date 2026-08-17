package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.n0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0366n0 extends Lambda implements Function1 {
    public final /* synthetic */ C0377t0 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0366n0(C0377t0 c0377t0) {
        super(1);
        this.a = c0377t0;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Context it = (Context) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        return this.a.a(it);
    }
}
