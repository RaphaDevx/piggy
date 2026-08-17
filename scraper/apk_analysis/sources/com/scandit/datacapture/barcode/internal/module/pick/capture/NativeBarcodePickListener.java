package com.scandit.datacapture.barcode.internal.module.pick.capture;

import com.scandit.datacapture.core.internal.sdk.data.NativeFrameData;
import java.util.HashMap;

/* loaded from: classes2.dex */
public abstract class NativeBarcodePickListener {
    public abstract void onItemResponseReceived(NativeBarcodePick nativeBarcodePick, String str, boolean z);

    public abstract void onObservationStarted(NativeBarcodePick nativeBarcodePick);

    public abstract void onObservationStopped(NativeBarcodePick nativeBarcodePick);

    public abstract void onPickUpdated(NativeBarcodePick nativeBarcodePick, NativeBarcodePickSession nativeBarcodePickSession, NativeFrameData nativeFrameData);

    public abstract void onSessionUpdated(NativeBarcodePick nativeBarcodePick, NativeBarcodePickSession nativeBarcodePickSession, NativeFrameData nativeFrameData);

    public abstract void onStyleShouldBeRequested(NativeBarcodePick nativeBarcodePick, HashMap<String, String> hashMap);
}
