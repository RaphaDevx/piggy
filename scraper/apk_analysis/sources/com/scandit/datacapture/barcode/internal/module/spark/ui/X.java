package com.scandit.datacapture.barcode.internal.module.spark.ui;

import com.scandit.datacapture.barcode.spark.ui.SparkScanViewState;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class X extends Lambda implements Function0 {
    public final /* synthetic */ SparkScanViewPresenter a;
    public final /* synthetic */ SparkScanViewState b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public X(SparkScanViewPresenter sparkScanViewPresenter, SparkScanViewState sparkScanViewState) {
        super(0);
        this.a = sparkScanViewPresenter;
        this.b = sparkScanViewState;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        SparkScanViewPresenter.c(this.a, this.b);
        SparkScanViewPresenter.b(this.a, this.b);
        SparkScanViewPresenter.a(this.a, this.b);
        this.a.d(this.b);
        return Unit.INSTANCE;
    }
}
