package com.scandit.datacapture.barcode.internal.module.spark.ui;

import com.scandit.datacapture.barcode.spark.capture.SparkScanViewUiListener;
import com.scandit.datacapture.barcode.spark.ui.SparkScanViewState;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class I extends Lambda implements Function0 {
    public final /* synthetic */ SparkScanViewPresenter a;
    public final /* synthetic */ SparkScanViewState b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public I(SparkScanViewPresenter sparkScanViewPresenter, SparkScanViewState sparkScanViewState) {
        super(0);
        this.a = sparkScanViewPresenter;
        this.b = sparkScanViewState;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        SparkScanViewUiListener sparkScanViewUiListener;
        SparkScanViewPresenter it = this.a;
        Intrinsics.checkNotNullExpressionValue(it, "$it");
        sparkScanViewUiListener = it.o;
        if (sparkScanViewUiListener != null) {
            sparkScanViewUiListener.onViewStateChanged(this.b);
        }
        return Unit.INSTANCE;
    }
}
