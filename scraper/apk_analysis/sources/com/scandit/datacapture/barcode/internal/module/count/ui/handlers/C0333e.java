package com.scandit.datacapture.barcode.internal.module.count.ui.handlers;

import com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.W;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.handlers.e, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0333e extends Lambda implements Function1 {
    public final /* synthetic */ W a;
    public final /* synthetic */ r b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0333e(W w, r rVar) {
        super(1);
        this.a = w;
        this.b = rVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Unit it = (Unit) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        int ordinal = this.a.ordinal();
        if (ordinal == 1) {
            return this.b.a.getAcceptedNotInListBrush();
        }
        if (ordinal == 2) {
            return this.b.a.getRejectedNotInListBrush();
        }
        if (ordinal == 3) {
            return this.b.a.getNotInListBrush();
        }
        throw new IllegalStateException(("Unreachable " + this.a).toString());
    }
}
