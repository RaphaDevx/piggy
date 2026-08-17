package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListStatus;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.q, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final /* synthetic */ class C0314q extends FunctionReferenceImpl implements Function1 {
    public C0314q(Object obj) {
        super(1, obj, C0316t.class, "onTouchConfirmed", "onTouchConfirmed(Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        TrackedBarcode p0 = (TrackedBarcode) obj;
        Intrinsics.checkNotNullParameter(p0, "p0");
        C0316t c0316t = (C0316t) this.receiver;
        Function4 function4 = c0316t.f;
        N d = c0316t.d(p0);
        M m = !c0316t.j ? M.a : c0316t.k.contains(Integer.valueOf(p0.getGlobalId$scandit_barcode_capture())) ? M.b : M.c;
        int globalId$scandit_barcode_capture = p0.getGlobalId$scandit_barcode_capture();
        function4.invoke(p0, d, m, (!c0316t.j || c0316t.k.contains(Integer.valueOf(globalId$scandit_barcode_capture))) ? NativeBarcodeCountNotInListStatus.NONE : c0316t.l.contains(Integer.valueOf(globalId$scandit_barcode_capture)) ? NativeBarcodeCountNotInListStatus.ACCEPTED : c0316t.m.contains(Integer.valueOf(globalId$scandit_barcode_capture)) ? NativeBarcodeCountNotInListStatus.REJECTED : NativeBarcodeCountNotInListStatus.NONE);
        return Unit.INSTANCE;
    }
}
