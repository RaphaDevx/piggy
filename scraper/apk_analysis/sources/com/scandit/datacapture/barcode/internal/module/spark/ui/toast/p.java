package com.scandit.datacapture.barcode.internal.module.spark.ui.toast;

import com.scandit.datacapture.barcode.internal.module.spark.ui.K;
import com.scandit.datacapture.barcode.internal.module.spark.ui.SparkScanViewPresenter;
import com.scandit.datacapture.barcode.internal.module.spark.ui.e0;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanToastPresenterDelegate;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanToastType;
import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.source.TorchState;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class p extends NativeSparkScanToastPresenterDelegate {
    public final o a;

    public p(K _SparkScanToastPresenterDelegate) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_SparkScanToastPresenterDelegate, "_SparkScanToastPresenterDelegate");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _SparkScanToastPresenterDelegate;
    }

    @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanToastPresenterDelegate
    public final CameraPosition getCameraPosition() {
        CameraPosition c;
        SparkScanViewPresenter sparkScanViewPresenter = (SparkScanViewPresenter) ((K) this.a).a.get();
        return (sparkScanViewPresenter == null || (c = SparkScanViewPresenter.c(sparkScanViewPresenter)) == null) ? e0.a : c;
    }

    @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanToastPresenterDelegate
    public final TorchState getTorchState() {
        TorchState h;
        SparkScanViewPresenter sparkScanViewPresenter = (SparkScanViewPresenter) ((K) this.a).a.get();
        return (sparkScanViewPresenter == null || (h = SparkScanViewPresenter.h(sparkScanViewPresenter)) == null) ? e0.b : h;
    }

    @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanToastPresenterDelegate
    public final boolean isCameraZoomedIn() {
        SparkScanViewPresenter sparkScanViewPresenter = (SparkScanViewPresenter) ((K) this.a).a.get();
        if (sparkScanViewPresenter != null) {
            return SparkScanViewPresenter.i(sparkScanViewPresenter);
        }
        return false;
    }

    @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanToastPresenterDelegate
    public final boolean isLongPressing() {
        SparkScanViewPresenter sparkScanViewPresenter = (SparkScanViewPresenter) ((K) this.a).a.get();
        if (sparkScanViewPresenter != null) {
            return SparkScanViewPresenter.j(sparkScanViewPresenter);
        }
        return false;
    }

    @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanToastPresenterDelegate
    public final boolean isPersistentPreview() {
        SparkScanViewPresenter sparkScanViewPresenter = (SparkScanViewPresenter) ((K) this.a).a.get();
        if (sparkScanViewPresenter != null) {
            return SparkScanViewPresenter.k(sparkScanViewPresenter);
        }
        return false;
    }

    @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanToastPresenterDelegate
    public final void showToast(NativeSparkScanToastType toast) {
        Intrinsics.checkNotNullParameter(toast, "toast");
        K k = (K) this.a;
        k.getClass();
        Intrinsics.checkNotNullParameter(toast, "toast");
        SparkScanViewPresenter sparkScanViewPresenter = (SparkScanViewPresenter) k.a.get();
        if (sparkScanViewPresenter != null) {
            SparkScanViewPresenter.a(sparkScanViewPresenter, toast);
        }
    }
}
