package com.scandit.datacapture.barcode.internal.module.spark.ui;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class Y extends Lambda implements Function0 {
    public final /* synthetic */ SparkScanViewPresenter a;
    public final /* synthetic */ boolean b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Y(SparkScanViewPresenter sparkScanViewPresenter, boolean z) {
        super(0);
        this.a = sparkScanViewPresenter;
        this.b = z;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        SparkScanViewPresenter.a(this.a, this.b);
        return Unit.INSTANCE;
    }
}
