package com.scandit.datacapture.barcode.internal.module.serialization;

import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.source.serialization.FrameSourceDeserializer;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class BarcodeCameraSettingsDeserializer {
    private final CameraSettings a;

    public BarcodeCameraSettingsDeserializer(CameraSettings recommendedCameraSettings) {
        Intrinsics.checkNotNullParameter(recommendedCameraSettings, "recommendedCameraSettings");
        this.a = recommendedCameraSettings;
    }

    public final CameraSettings a(JsonValue json) {
        Intrinsics.checkNotNullParameter(json, "json");
        CameraSettings cameraSettings = json.getByKeyAsBoolean("usesRecommendedSettings", true) ? this.a : new CameraSettings();
        json._removeKeys(CollectionsKt.arrayListOf("usesRecommendedSettings"));
        new FrameSourceDeserializer(CollectionsKt.emptyList()).updateCameraSettingsFromJson(cameraSettings, json.jsonString());
        return cameraSettings;
    }
}
