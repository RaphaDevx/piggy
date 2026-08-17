package com.scandit.datacapture.barcode.internal.module.count.ui.handlers;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.handlers.c, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0331c extends Lambda implements Function1 {
    public final /* synthetic */ r a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0331c(r rVar) {
        super(1);
        this.a = rVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Unit it = (Unit) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        return this.a.a.getUnrecognizedBrush();
    }
}
