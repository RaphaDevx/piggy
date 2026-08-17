package com.scandit.datacapture.barcode.internal.module.pick.capture;

/* loaded from: classes2.dex */
public abstract class NativeBarcodePickStatusListener {
    public abstract void onFreezed();

    public abstract void onObservationStarted(NativeBarcodePick nativeBarcodePick);

    public abstract void onObservationStopped(NativeBarcodePick nativeBarcodePick);

    public abstract void onPaused();

    public abstract void onStarted();

    public abstract void onStopped();
}
