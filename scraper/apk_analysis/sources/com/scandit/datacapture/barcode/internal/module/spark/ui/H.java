package com.scandit.datacapture.barcode.internal.module.spark.ui;

import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import com.scandit.datacapture.barcode.spark.capture.SparkScanViewUiListener;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanScanningMode;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class H extends Lambda implements Function0 {
    public final /* synthetic */ SparkScanViewPresenter a;
    public final /* synthetic */ NativeSparkScanScanningMode b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H(SparkScanViewPresenter sparkScanViewPresenter, NativeSparkScanScanningMode nativeSparkScanScanningMode) {
        super(0);
        this.a = sparkScanViewPresenter;
        this.b = nativeSparkScanScanningMode;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        SparkScanViewUiListener sparkScanViewUiListener;
        SparkScanViewPresenter it = this.a;
        Intrinsics.checkNotNullExpressionValue(it, "$it");
        sparkScanViewUiListener = it.o;
        if (sparkScanViewUiListener != null) {
            sparkScanViewUiListener.onScanningModeChange(BarcodeNativeTypeFactory.INSTANCE.convert(this.b));
        }
        return Unit.INSTANCE;
    }
}
