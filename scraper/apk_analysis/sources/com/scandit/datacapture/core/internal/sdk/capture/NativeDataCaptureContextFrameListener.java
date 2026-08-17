package com.scandit.datacapture.core.internal.sdk.capture;

import com.scandit.datacapture.core.internal.sdk.data.NativeFrameData;

/* loaded from: classes2.dex */
public abstract class NativeDataCaptureContextFrameListener {
    public abstract void onFrameProcessingFinished(NativeDataCaptureContext nativeDataCaptureContext, NativeFrameData nativeFrameData);

    public abstract void onFrameProcessingStarted(NativeDataCaptureContext nativeDataCaptureContext, NativeFrameData nativeFrameData);

    public abstract void onObservationStarted(NativeDataCaptureContext nativeDataCaptureContext);

    public abstract void onObservationStopped(NativeDataCaptureContext nativeDataCaptureContext);
}
