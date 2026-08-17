package com.scandit.datacapture.barcode.internal.module.pick.capture;

/* loaded from: classes2.dex */
public abstract class NativeBarcodePickPublicListener {
    public abstract void onObservationStarted(NativeBarcodePick nativeBarcodePick);

    public abstract void onObservationStopped(NativeBarcodePick nativeBarcodePick);

    public abstract void onSessionUpdated(NativeBarcodePick nativeBarcodePick, NativeBarcodePickPublicSession nativeBarcodePickPublicSession);
}
