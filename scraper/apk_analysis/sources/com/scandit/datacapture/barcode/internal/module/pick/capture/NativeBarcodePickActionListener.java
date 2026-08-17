package com.scandit.datacapture.barcode.internal.module.pick.capture;

/* loaded from: classes2.dex */
public abstract class NativeBarcodePickActionListener {
    public abstract void onObservationStarted(NativeBarcodePick nativeBarcodePick);

    public abstract void onObservationStopped(NativeBarcodePick nativeBarcodePick);

    public abstract void onPick(String str, NativeBarcodePickActionCallback nativeBarcodePickActionCallback);

    public abstract void onUnpick(String str, NativeBarcodePickActionCallback nativeBarcodePickActionCallback);
}
