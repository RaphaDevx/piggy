package com.scandit.datacapture.barcode.internal.module.spark.ui;

import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewHandlerDelegate;
import com.scandit.datacapture.barcode.spark.ui.SparkScanViewState;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class G extends NativeSparkScanViewHandlerDelegate {
    public final F a;

    public G(L _SparkScanViewHandlerDelegate) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_SparkScanViewHandlerDelegate, "_SparkScanViewHandlerDelegate");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _SparkScanViewHandlerDelegate;
    }

    @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewHandlerDelegate
    public final void enableHapticWhenOverlapping() {
        SparkScanViewPresenter sparkScanViewPresenter = (SparkScanViewPresenter) ((L) this.a).a.get();
        if (sparkScanViewPresenter != null) {
            SparkScanViewPresenter.a(sparkScanViewPresenter);
        }
    }

    @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewHandlerDelegate
    public final void onStateTransition(SparkScanViewState currentState) {
        Intrinsics.checkNotNullParameter(currentState, "state");
        L l = (L) this.a;
        l.getClass();
        Intrinsics.checkNotNullParameter(currentState, "currentState");
        SparkScanViewPresenter sparkScanViewPresenter = (SparkScanViewPresenter) l.a.get();
        if (sparkScanViewPresenter != null) {
            sparkScanViewPresenter.c(currentState);
        }
    }

    @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewHandlerDelegate
    public final void onSwitchedAwayFromSmartScanSelection() {
        SparkScanViewPresenter sparkScanViewPresenter = (SparkScanViewPresenter) ((L) this.a).a.get();
        if (sparkScanViewPresenter != null) {
            sparkScanViewPresenter.n(false);
        }
    }

    @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewHandlerDelegate
    public final void onSwitchedToSmartScanSelection() {
        SparkScanViewPresenter sparkScanViewPresenter = (SparkScanViewPresenter) ((L) this.a).a.get();
        if (sparkScanViewPresenter != null) {
            sparkScanViewPresenter.n(true);
        }
    }

    @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewHandlerDelegate
    public final void resetAllTimers() {
        SparkScanViewPresenter sparkScanViewPresenter = (SparkScanViewPresenter) ((L) this.a).a.get();
        if (sparkScanViewPresenter != null) {
            SparkScanViewPresenter.l(sparkScanViewPresenter);
        }
    }
}
