package com.scandit.datacapture.barcode.internal.module.spark.ui;

import com.scandit.datacapture.barcode.spark.capture.SparkScanViewUiListener;
import com.scandit.datacapture.barcode.spark.ui.SparkScanView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class V extends Lambda implements Function0 {
    public final /* synthetic */ SparkScanViewPresenter a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public V(SparkScanViewPresenter sparkScanViewPresenter) {
        super(0);
        this.a = sparkScanViewPresenter;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        SparkScanViewUiListener sparkScanViewUiListener;
        SparkScanView sparkScanView;
        sparkScanViewUiListener = this.a.o;
        if (sparkScanViewUiListener != null) {
            sparkScanView = this.a.a;
            sparkScanViewUiListener.onLabelCaptureButtonTap(sparkScanView);
        }
        return Unit.INSTANCE;
    }
}
