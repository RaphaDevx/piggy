package com.scandit.datacapture.barcode.spark.internal.module.capture;

import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.source.TorchState;

/* loaded from: classes2.dex */
public abstract class NativeSparkScanToastPresenterDelegate {
    public abstract CameraPosition getCameraPosition();

    public abstract TorchState getTorchState();

    public abstract boolean isCameraZoomedIn();

    public abstract boolean isLongPressing();

    public abstract boolean isPersistentPreview();

    public abstract void showToast(NativeSparkScanToastType nativeSparkScanToastType);
}
