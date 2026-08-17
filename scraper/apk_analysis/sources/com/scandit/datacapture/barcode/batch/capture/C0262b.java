package com.scandit.datacapture.barcode.batch.capture;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.batch.capture.b, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0262b extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeBatch a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0262b(BarcodeBatch barcodeBatch) {
        super(0);
        this.a = barcodeBatch;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a.getA();
    }
}
