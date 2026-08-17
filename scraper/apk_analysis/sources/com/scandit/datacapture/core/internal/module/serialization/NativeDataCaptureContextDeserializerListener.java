package com.scandit.datacapture.core.internal.module.serialization;

import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;

/* loaded from: classes2.dex */
public abstract class NativeDataCaptureContextDeserializerListener {
    public abstract void onContextDeserializationFinished(NativeDataCaptureContextDeserializer nativeDataCaptureContextDeserializer, NativeDataCaptureContext nativeDataCaptureContext, NativeJsonValue nativeJsonValue);

    public abstract void onContextDeserializationStarted(NativeDataCaptureContextDeserializer nativeDataCaptureContextDeserializer, NativeDataCaptureContext nativeDataCaptureContext, NativeJsonValue nativeJsonValue);
}
