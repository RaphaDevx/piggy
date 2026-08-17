package com.scandit.datacapture.barcode.internal.module.spark.ui;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class Q extends Lambda implements Function1 {
    public final /* synthetic */ SparkScanViewPresenter a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Q(SparkScanViewPresenter sparkScanViewPresenter) {
        super(1);
        this.a = sparkScanViewPresenter;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ((Boolean) obj).booleanValue();
        SparkScanViewPresenter sparkScanViewPresenter = this.a;
        sparkScanViewPresenter.a(new P(sparkScanViewPresenter));
        return Unit.INSTANCE;
    }
}
