package com.scandit.datacapture.barcode.batch.internal.module.capture;

import com.scandit.datacapture.core.internal.sdk.data.NativeFrameData;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeTrackingListener {
    public abstract void onObservationStarted(NativeBarcodeTracking nativeBarcodeTracking);

    public abstract void onObservationStopped(NativeBarcodeTracking nativeBarcodeTracking);

    public abstract void onSessionUpdated(NativeBarcodeTracking nativeBarcodeTracking, NativeBarcodeTrackingSession nativeBarcodeTrackingSession, NativeFrameData nativeFrameData);
}
