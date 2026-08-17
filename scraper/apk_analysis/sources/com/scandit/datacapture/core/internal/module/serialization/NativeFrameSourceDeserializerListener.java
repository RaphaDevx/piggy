package com.scandit.datacapture.core.internal.module.serialization;

import com.scandit.datacapture.core.internal.module.source.NativeCameraSettings;
import com.scandit.datacapture.core.internal.module.source.NativeFrameSource;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;

/* loaded from: classes2.dex */
public abstract class NativeFrameSourceDeserializerListener {
    public abstract void onCameraSettingsDeserializationFinished(NativeFrameSourceDeserializer nativeFrameSourceDeserializer, NativeCameraSettings nativeCameraSettings, NativeJsonValue nativeJsonValue);

    public abstract void onCameraSettingsDeserializationStarted(NativeFrameSourceDeserializer nativeFrameSourceDeserializer, NativeCameraSettings nativeCameraSettings, NativeJsonValue nativeJsonValue);

    public abstract void onFrameSourceDeserializationFinished(NativeFrameSourceDeserializer nativeFrameSourceDeserializer, NativeFrameSource nativeFrameSource, NativeJsonValue nativeJsonValue);

    public abstract void onFrameSourceDeserializationStarted(NativeFrameSourceDeserializer nativeFrameSourceDeserializer, NativeFrameSource nativeFrameSource, NativeJsonValue nativeJsonValue);
}
