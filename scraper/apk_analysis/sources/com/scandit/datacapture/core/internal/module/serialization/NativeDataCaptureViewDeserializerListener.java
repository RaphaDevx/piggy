package com.scandit.datacapture.core.internal.module.serialization;

import com.scandit.datacapture.core.internal.module.ui.NativeDataCaptureView;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;

/* loaded from: classes2.dex */
public abstract class NativeDataCaptureViewDeserializerListener {
    public abstract void onViewDeserializationFinished(NativeDataCaptureViewDeserializer nativeDataCaptureViewDeserializer, NativeDataCaptureView nativeDataCaptureView, NativeJsonValue nativeJsonValue);

    public abstract void onViewDeserializationStarted(NativeDataCaptureViewDeserializer nativeDataCaptureViewDeserializer, NativeDataCaptureView nativeDataCaptureView, NativeJsonValue nativeJsonValue);
}
