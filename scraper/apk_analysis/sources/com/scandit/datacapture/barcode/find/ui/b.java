package com.scandit.datacapture.barcode.find.ui;

import com.scandit.datacapture.barcode.internal.module.find.ui.u0;
import com.scandit.datacapture.core.ui.DataCaptureView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function1 {
    public final /* synthetic */ u0 a;
    public final /* synthetic */ int b;
    public final /* synthetic */ int c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(u0 u0Var, int i, int i2) {
        super(1);
        this.a = u0Var;
        this.b = i;
        this.c = i2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        DataCaptureView it = (DataCaptureView) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        u0 u0Var = this.a;
        u0Var.c.onResolutionChanged(this.b, this.c);
        u0Var.k.b();
        return Unit.INSTANCE;
    }
}
