package com.scandit.datacapture.barcode.internal.module.spark.ui;

import com.scandit.datacapture.barcode.spark.ui.SparkScanViewState;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class S extends Lambda implements Function0 {
    public final /* synthetic */ SparkScanViewPresenter a;
    public final /* synthetic */ Function0 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S(SparkScanViewPresenter sparkScanViewPresenter, U u) {
        super(0);
        this.a = sparkScanViewPresenter;
        this.b = u;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        SparkScanViewPresenter sparkScanViewPresenter = this.a;
        SparkScanViewState b = sparkScanViewPresenter.b();
        U u = (U) this.b;
        SparkScanViewPresenter.a(sparkScanViewPresenter, b, u, u);
        return Unit.INSTANCE;
    }
}
