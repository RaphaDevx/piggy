package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class C extends Lambda implements Function0 {
    public final /* synthetic */ F a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C(F f) {
        super(0);
        this.a = f;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        this.a.setVisibility(4);
        F f = this.a;
        f.l.removeView(f.q);
        this.a.n.a.a.hideMiniPreview();
        this.a.b.D();
        return Unit.INSTANCE;
    }
}
