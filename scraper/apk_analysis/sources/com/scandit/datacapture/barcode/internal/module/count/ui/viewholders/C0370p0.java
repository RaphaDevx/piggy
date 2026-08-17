package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.p0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0370p0 extends Lambda implements Function1 {
    public final /* synthetic */ boolean a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0370p0(boolean z) {
        super(1);
        this.a = z;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        com.scandit.datacapture.barcode.internal.module.ui.b it = (com.scandit.datacapture.barcode.internal.module.ui.b) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        it.setEnabled(this.a);
        return Unit.INSTANCE;
    }
}
