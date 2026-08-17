package com.scandit.datacapture.barcode.internal.module.spark.ui;

import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanScanningMode;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewUiListener;
import com.scandit.datacapture.barcode.spark.ui.SparkScanViewState;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class J extends NativeSparkScanViewUiListener {
    public final WeakReference a;

    public J(SparkScanViewPresenter owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewUiListener
    public final void onScanningModeChanged(NativeSparkScanScanningMode previousMode, NativeSparkScanScanningMode newMode) {
        Intrinsics.checkNotNullParameter(previousMode, "previousMode");
        Intrinsics.checkNotNullParameter(newMode, "newMode");
        SparkScanViewPresenter sparkScanViewPresenter = (SparkScanViewPresenter) this.a.get();
        if (sparkScanViewPresenter != null) {
            sparkScanViewPresenter.a(new H(sparkScanViewPresenter, newMode));
        }
    }

    @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewUiListener
    public final void onViewStateChanged(SparkScanViewState newState) {
        Intrinsics.checkNotNullParameter(newState, "newState");
        SparkScanViewPresenter sparkScanViewPresenter = (SparkScanViewPresenter) this.a.get();
        if (sparkScanViewPresenter != null) {
            sparkScanViewPresenter.a(new I(sparkScanViewPresenter, newState));
        }
    }
}
