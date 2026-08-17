package com.scandit.datacapture.barcode.internal.module.capture;

import com.scandit.datacapture.core.internal.sdk.data.NativeFrameData;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeCaptureListener {
    public abstract void onObservationStarted(NativeBarcodeCapture nativeBarcodeCapture);

    public abstract void onObservationStopped(NativeBarcodeCapture nativeBarcodeCapture);

    public abstract void onScan(NativeBarcodeCapture nativeBarcodeCapture, NativeBarcodeCaptureSession nativeBarcodeCaptureSession, NativeFrameData nativeFrameData);

    public abstract void onSessionUpdated(NativeBarcodeCapture nativeBarcodeCapture, NativeBarcodeCaptureSession nativeBarcodeCaptureSession, NativeFrameData nativeFrameData);
}
