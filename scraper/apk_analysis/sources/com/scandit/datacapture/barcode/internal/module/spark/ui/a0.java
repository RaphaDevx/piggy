package com.scandit.datacapture.barcode.internal.module.spark.ui;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class a0 extends Lambda implements Function0 {
    public final /* synthetic */ SparkScanViewPresenter a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a0(SparkScanViewPresenter sparkScanViewPresenter) {
        super(0);
        this.a = sparkScanViewPresenter;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        SparkScanViewPresenter sparkScanViewPresenter = this.a;
        sparkScanViewPresenter.a(new Z(sparkScanViewPresenter));
        return Unit.INSTANCE;
    }
}
