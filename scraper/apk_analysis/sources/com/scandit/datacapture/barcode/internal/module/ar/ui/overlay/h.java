package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class h extends Lambda implements Function0 {
    public final /* synthetic */ i a;
    public final /* synthetic */ TrackedBarcode b;
    public final /* synthetic */ BarcodeArAugmentation$Annotation c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(i iVar, TrackedBarcode trackedBarcode, BarcodeArAugmentation$Annotation barcodeArAugmentation$Annotation) {
        super(0);
        this.a = iVar;
        this.b = trackedBarcode;
        this.c = barcodeArAugmentation$Annotation;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        this.a.b.invoke(this.b, this.c);
        return Unit.INSTANCE;
    }
}
