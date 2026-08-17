package com.scandit.datacapture.core.source.serialization;

import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.source.FrameSource;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements FrameSourceDeserializerListener {
    public final WeakReference a;

    public a(FrameSourceDeserializer owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerListener
    public final void onCameraSettingsDeserializationFinished(FrameSourceDeserializer deserializer, CameraSettings settings, JsonValue json) {
        FrameSourceDeserializerListener frameSourceDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        FrameSourceDeserializer frameSourceDeserializer = (FrameSourceDeserializer) this.a.get();
        if (frameSourceDeserializer == null || (frameSourceDeserializerListener = frameSourceDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        frameSourceDeserializerListener.onCameraSettingsDeserializationFinished(deserializer, settings, json);
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerListener
    public final void onCameraSettingsDeserializationStarted(FrameSourceDeserializer deserializer, CameraSettings settings, JsonValue json) {
        FrameSourceDeserializerListener frameSourceDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        FrameSourceDeserializer frameSourceDeserializer = (FrameSourceDeserializer) this.a.get();
        if (frameSourceDeserializer == null || (frameSourceDeserializerListener = frameSourceDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        frameSourceDeserializerListener.onCameraSettingsDeserializationStarted(deserializer, settings, json);
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerListener
    public final void onFrameSourceDeserializationFinished(FrameSourceDeserializer deserializer, FrameSource frameSource, JsonValue json) {
        FrameSourceDeserializerListener frameSourceDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
        Intrinsics.checkNotNullParameter(json, "json");
        FrameSourceDeserializer frameSourceDeserializer = (FrameSourceDeserializer) this.a.get();
        if (frameSourceDeserializer == null || (frameSourceDeserializerListener = frameSourceDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        frameSourceDeserializerListener.onFrameSourceDeserializationFinished(deserializer, frameSource, json);
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerListener
    public final void onFrameSourceDeserializationStarted(FrameSourceDeserializer deserializer, FrameSource frameSource, JsonValue json) {
        FrameSourceDeserializerListener frameSourceDeserializerListener;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
        Intrinsics.checkNotNullParameter(json, "json");
        FrameSourceDeserializer frameSourceDeserializer = (FrameSourceDeserializer) this.a.get();
        if (frameSourceDeserializer == null || (frameSourceDeserializerListener = frameSourceDeserializer.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        frameSourceDeserializerListener.onFrameSourceDeserializationStarted(deserializer, frameSource, json);
    }
}
