package com.scandit.datacapture.barcode.internal.module.ar.capture;

import com.scandit.datacapture.core.internal.sdk.data.NativeFrameData;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeArListener {
    public abstract void onObservationStarted(NativeBarcodeAr nativeBarcodeAr);

    public abstract void onObservationStopped(NativeBarcodeAr nativeBarcodeAr);

    public abstract void onSessionUpdated(NativeBarcodeAr nativeBarcodeAr, NativeBarcodeArSession nativeBarcodeArSession, NativeFrameData nativeFrameData);
}
