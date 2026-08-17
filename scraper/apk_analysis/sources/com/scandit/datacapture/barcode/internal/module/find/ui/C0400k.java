package com.scandit.datacapture.barcode.internal.module.find.ui;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.find.ui.k, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0400k extends Lambda implements Function1 {
    public final /* synthetic */ C0402m a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0400k(C0402m c0402m) {
        super(1);
        this.a = c0402m;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        C0402m it = (C0402m) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        this.a.invalidate();
        return Unit.INSTANCE;
    }
}
