package com.scandit.datacapture.barcode.capture;

import com.scandit.datacapture.core.capture.DataCaptureContext;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.capture.a, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0269a extends Lambda implements Function1 {
    public final /* synthetic */ BarcodeCapture a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0269a(BarcodeCapture barcodeCapture) {
        super(1);
        this.a = barcodeCapture;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        DataCaptureContext dataCaptureContext;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        BarcodeCapture barcodeCapture = this.a;
        dataCaptureContext = barcodeCapture.c;
        BarcodeCapture.access$applyBatterySavingMode(barcodeCapture, dataCaptureContext, booleanValue);
        return Unit.INSTANCE;
    }
}
