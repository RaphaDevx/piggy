package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer;

import com.scandit.datacapture.barcode.internal.module.pick.capture.v;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function0 {
    public final /* synthetic */ f a;
    public final /* synthetic */ v b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(f fVar, v vVar) {
        super(0);
        this.a = fVar;
        this.b = vVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        this.a.a(this.b);
        return Unit.INSTANCE;
    }
}
