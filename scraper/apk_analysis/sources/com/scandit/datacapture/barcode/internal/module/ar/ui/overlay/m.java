package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class m extends Lambda implements Function0 {
    public final /* synthetic */ v a;
    public final /* synthetic */ BarcodeArAugmentation$Highlight b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(v vVar, BarcodeArAugmentation$Highlight barcodeArAugmentation$Highlight) {
        super(0);
        this.a = vVar;
        this.b = barcodeArAugmentation$Highlight;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        this.a.d.invoke(this.b);
        return Unit.INSTANCE;
    }
}
