package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArHighlight;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class d extends Lambda implements Function1 {
    public final /* synthetic */ Function1 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(n nVar) {
        super(1);
        this.a = nVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        BarcodeArHighlight barcodeArHighlight = (BarcodeArHighlight) obj;
        this.a.invoke(barcodeArHighlight != null ? new BarcodeArAugmentation$Highlight(barcodeArHighlight, barcodeArHighlight.createView()) : null);
        return Unit.INSTANCE;
    }
}
