package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class C0 extends Lambda implements Function0 {
    public final /* synthetic */ J0 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0(J0 j0) {
        super(0);
        this.a = j0;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Context context = this.a.b.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        return new com.scandit.datacapture.barcode.internal.module.count.ui.a(context, J0.a(this.a), J0.b(this.a), J0.m);
    }
}
