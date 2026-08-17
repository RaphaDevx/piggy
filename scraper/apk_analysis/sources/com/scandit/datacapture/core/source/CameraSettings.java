package com.scandit.datacapture.core.source;

import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.common.geometry.Size2Deserializer;
import com.scandit.datacapture.core.common.geometry.Size2UtilsKt;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile;
import com.scandit.datacapture.core.internal.module.serialization.NativeEnumDeserializer;
import com.scandit.datacapture.core.internal.module.serialization.NativeEnumSerializer;
import com.scandit.datacapture.core.internal.module.source.L;
import com.scandit.datacapture.core.internal.module.source.NativeCameraSettings;
import com.scandit.datacapture.core.internal.module.source.NativeCameraSettingsDefaults;
import com.scandit.datacapture.core.internal.module.source.NativeEdgeEnhancement;
import com.scandit.datacapture.core.internal.module.source.NativeFocusSettings;
import com.scandit.datacapture.core.internal.module.source.NativeFocusStrategy;
import com.scandit.datacapture.core.internal.module.source.NativeNoiseReduction;
import com.scandit.datacapture.core.internal.module.source.NativePreferredFrameRateRange;
import com.scandit.datacapture.core.internal.module.source.NativeRegionStrategy;
import com.scandit.datacapture.core.internal.module.source.NativeShadingMode;
import com.scandit.datacapture.core.internal.module.source.NativeTonemapCurve;
import com.scandit.datacapture.core.internal.module.source.NativeVideoAspectRatio;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.internal.sdk.source.RegionStrategyDeserializer;
import com.scandit.datacapture.core.internal.sdk.source.RegionStrategySerializer;
import com.scandit.datacapture.core.internal.sdk.utils.JsonUtils;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0010\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0002\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\b\u0010\u0006J\u0017\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0019\u001a\u00020\u0016H\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001c\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u001a\u0010\u001bR\"\u0010$\u001a\u00020\u001d8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\"\u0010,\u001a\u00020%8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\"\u00100\u001a\u00020%8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b-\u0010'\u001a\u0004\b.\u0010)\"\u0004\b/\u0010+R\"\u00108\u001a\u0002018\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b2\u00103\u001a\u0004\b4\u00105\"\u0004\b6\u00107R*\u0010>\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00168\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b9\u0010:\u001a\u0004\b;\u0010\u0018\"\u0004\b<\u0010=¨\u0006?"}, d2 = {"Lcom/scandit/datacapture/core/source/CameraSettings;", "", "<init>", "()V", "Lcom/scandit/datacapture/core/internal/module/source/NativeCameraSettings;", "impl", "(Lcom/scandit/datacapture/core/internal/module/source/NativeCameraSettings;)V", "", "updateWithNativeObject$scandit_capture_core", "updateWithNativeObject", "Lcom/scandit/datacapture/core/internal/module/device/profiles/camera/CameraProfile;", "profile", "addDefaultPropertiesFromProfile$scandit_capture_core", "(Lcom/scandit/datacapture/core/internal/module/device/profiles/camera/CameraProfile;)V", "addDefaultPropertiesFromProfile", "", "name", "value", "setProperty", "(Ljava/lang/String;Ljava/lang/Object;)V", "getProperty", "(Ljava/lang/String;)Ljava/lang/Object;", "", "isUsingApi2Features$scandit_capture_core", "()Z", "isUsingApi2Features", "_impl$scandit_capture_core", "()Lcom/scandit/datacapture/core/internal/module/source/NativeCameraSettings;", "_impl", "Lcom/scandit/datacapture/core/source/VideoResolution;", "a", "Lcom/scandit/datacapture/core/source/VideoResolution;", "getPreferredResolution", "()Lcom/scandit/datacapture/core/source/VideoResolution;", "setPreferredResolution", "(Lcom/scandit/datacapture/core/source/VideoResolution;)V", "preferredResolution", "", "b", "F", "getZoomFactor", "()F", "setZoomFactor", "(F)V", "zoomFactor", "c", "getZoomGestureZoomFactor", "setZoomGestureZoomFactor", "zoomGestureZoomFactor", "Lcom/scandit/datacapture/core/source/FocusGestureStrategy;", "d", "Lcom/scandit/datacapture/core/source/FocusGestureStrategy;", "getFocusGestureStrategy", "()Lcom/scandit/datacapture/core/source/FocusGestureStrategy;", "setFocusGestureStrategy", "(Lcom/scandit/datacapture/core/source/FocusGestureStrategy;)V", "focusGestureStrategy", "f", "Z", "getShouldPreferSmoothAutoFocus", "setShouldPreferSmoothAutoFocus", "(Z)V", "shouldPreferSmoothAutoFocus", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class CameraSettings {

    @Deprecated
    public static final String FOCUS_STRATEGY_AUTO = "auto";

    /* renamed from: a, reason: from kotlin metadata */
    private VideoResolution preferredResolution;

    /* renamed from: b, reason: from kotlin metadata */
    private float zoomFactor;

    /* renamed from: c, reason: from kotlin metadata */
    private float zoomGestureZoomFactor;

    /* renamed from: d, reason: from kotlin metadata */
    private FocusGestureStrategy focusGestureStrategy;
    private final HashMap e;

    /* renamed from: f, reason: from kotlin metadata */
    private boolean shouldPreferSmoothAutoFocus;

    public CameraSettings() {
        VideoResolution preferredResolution = NativeCameraSettingsDefaults.preferredResolution();
        Intrinsics.checkNotNullExpressionValue(preferredResolution, "preferredResolution(...)");
        float zoomFactor = NativeCameraSettingsDefaults.zoomFactor();
        float zoomGestureZoomFactor = NativeCameraSettingsDefaults.zoomGestureZoomFactor();
        boolean shouldPreferSmoothAutoFocus = NativeCameraSettingsDefaults.shouldPreferSmoothAutoFocus();
        FocusGestureStrategy focusGestureStrategy = NativeCameraSettingsDefaults.focusGestureStrategy();
        Intrinsics.checkNotNullExpressionValue(focusGestureStrategy, "focusGestureStrategy(...)");
        this.preferredResolution = preferredResolution;
        this.zoomFactor = zoomFactor;
        this.zoomGestureZoomFactor = zoomGestureZoomFactor;
        this.focusGestureStrategy = focusGestureStrategy;
        HashMap hashMap = new HashMap();
        this.e = hashMap;
        this.shouldPreferSmoothAutoFocus = shouldPreferSmoothAutoFocus;
        if (shouldPreferSmoothAutoFocus) {
            hashMap.put("macroAutofocusMode", "off");
        }
    }

    private final void a(NativeJsonValue nativeJsonValue) {
        if (nativeJsonValue != null) {
            String nativeJsonValue2 = nativeJsonValue.toString();
            Intrinsics.checkNotNullExpressionValue(nativeJsonValue2, "toString(...)");
            Object objectFromJson = JsonUtils.objectFromJson(nativeJsonValue2);
            Map map = objectFromJson instanceof Map ? (Map) objectFromJson : null;
            if (map != null) {
                for (Map.Entry entry : map.entrySet()) {
                    setProperty((String) entry.getKey(), entry.getValue());
                }
            }
        }
    }

    public final NativeCameraSettings _impl$scandit_capture_core() {
        HashMap hashMap = this.e;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : hashMap.entrySet()) {
            if (!L.a.contains(entry.getKey())) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        NativeJsonValue fromString = NativeJsonValue.fromString(JsonUtils.jsonFromObject(linkedHashMap));
        VideoResolution videoResolution = this.preferredResolution;
        Float a = a("minFrameRate");
        float floatValue = a != null ? a.floatValue() : NativeCameraSettingsDefaults.minFrameRate();
        Float a2 = a("maxFrameRate");
        float floatValue2 = a2 != null ? a2.floatValue() : NativeCameraSettingsDefaults.maxFrameRate();
        float f = this.zoomFactor;
        float f2 = this.zoomGestureZoomFactor;
        Float a3 = a("manualLensPosition");
        float floatValue3 = a3 != null ? a3.floatValue() : NativeCameraSettingsDefaults.lensPosition();
        Object property = getProperty("focusStrategy");
        String json = property instanceof String ? (String) property : null;
        if (json == null) {
            NativeFocusStrategy focusStrategy = NativeFocusStrategy.AUTO;
            Intrinsics.checkNotNullParameter(focusStrategy, "focusStrategy");
            Intrinsics.checkNotNullParameter(focusStrategy, "<this>");
            json = NativeEnumSerializer.focusStrategyToString(focusStrategy);
            Intrinsics.checkNotNullExpressionValue(json, "focusStrategyToString(...)");
        }
        Intrinsics.checkNotNullParameter(json, "json");
        NativeFocusStrategy focusStrategyFromJsonString = NativeEnumDeserializer.focusStrategyFromJsonString(json);
        Intrinsics.checkNotNullExpressionValue(focusStrategyFromJsonString, "focusStrategyFromJsonString(...)");
        NativeFocusSettings nativeFocusSettings = new NativeFocusSettings(floatValue3, focusStrategyFromJsonString, this.shouldPreferSmoothAutoFocus, fromString, null, this.focusGestureStrategy);
        Object property2 = getProperty("api");
        Integer num = property2 instanceof Integer ? (Integer) property2 : null;
        int intValue = num != null ? num.intValue() : NativeCameraSettingsDefaults.api();
        Object property3 = getProperty("usesApi2Features");
        Boolean bool = property3 instanceof Boolean ? (Boolean) property3 : null;
        boolean booleanValue = bool != null ? bool.booleanValue() : NativeCameraSettingsDefaults.usesCameraApi2Features();
        Object property4 = getProperty("overwriteWithHighestResolution");
        Boolean bool2 = property4 instanceof Boolean ? (Boolean) property4 : null;
        boolean booleanValue2 = bool2 != null ? bool2.booleanValue() : NativeCameraSettingsDefaults.overwriteWithHighestResolution();
        Float a4 = a("exposureTargetBias");
        float floatValue4 = a4 != null ? a4.floatValue() : NativeCameraSettingsDefaults.exposureTargetBias();
        Object property5 = getProperty("colorCorrection");
        Boolean bool3 = property5 instanceof Boolean ? (Boolean) property5 : null;
        boolean booleanValue3 = bool3 != null ? bool3.booleanValue() : NativeCameraSettingsDefaults.colorCorrection();
        Object property6 = getProperty("toneMappingCurve");
        NativeTonemapCurve nativeTonemapCurve = property6 instanceof NativeTonemapCurve ? (NativeTonemapCurve) property6 : null;
        if (nativeTonemapCurve == null) {
            nativeTonemapCurve = NativeCameraSettingsDefaults.tonemapCurve();
        }
        NativeTonemapCurve nativeTonemapCurve2 = nativeTonemapCurve;
        Object property7 = getProperty("noiseReductionMode");
        NativeNoiseReduction nativeNoiseReduction = property7 instanceof NativeNoiseReduction ? (NativeNoiseReduction) property7 : null;
        if (nativeNoiseReduction == null) {
            nativeNoiseReduction = NativeCameraSettingsDefaults.noiseReductionMode();
        }
        NativeNoiseReduction nativeNoiseReduction2 = nativeNoiseReduction;
        Object property8 = getProperty("shadingMode");
        NativeShadingMode nativeShadingMode = property8 instanceof NativeShadingMode ? (NativeShadingMode) property8 : null;
        if (nativeShadingMode == null) {
            nativeShadingMode = NativeCameraSettingsDefaults.shadingMode();
        }
        NativeShadingMode nativeShadingMode2 = nativeShadingMode;
        Object property9 = getProperty("edgeEnhancementMode");
        NativeEdgeEnhancement nativeEdgeEnhancement = property9 instanceof NativeEdgeEnhancement ? (NativeEdgeEnhancement) property9 : null;
        if (nativeEdgeEnhancement == null) {
            nativeEdgeEnhancement = NativeCameraSettingsDefaults.edgeEnhancementMode();
        }
        NativeEdgeEnhancement nativeEdgeEnhancement2 = nativeEdgeEnhancement;
        Object property10 = getProperty("regionStrategy");
        String str = property10 instanceof String ? (String) property10 : null;
        if (str == null) {
            str = RegionStrategySerializer.INSTANCE.toJson(NativeRegionStrategy.DEFAULT);
        }
        NativeRegionStrategy fromJson = RegionStrategyDeserializer.INSTANCE.fromJson(str);
        float f3 = NativeCameraSettingsDefaults.torchLevel();
        Object property11 = getProperty("preferredAspectRatio");
        String json2 = property11 instanceof String ? (String) property11 : null;
        if (json2 == null) {
            NativeVideoAspectRatio aspectRatio = NativeVideoAspectRatio.AUTO;
            Intrinsics.checkNotNullParameter(aspectRatio, "aspectRatio");
            Intrinsics.checkNotNullParameter(aspectRatio, "<this>");
            json2 = NativeEnumSerializer.videoAspectRatioToString(aspectRatio);
            Intrinsics.checkNotNullExpressionValue(json2, "videoAspectRatioToString(...)");
        }
        Intrinsics.checkNotNullParameter(json2, "json");
        NativeVideoAspectRatio videoAspectRatioFromJsonString = NativeEnumDeserializer.videoAspectRatioFromJsonString(json2);
        Intrinsics.checkNotNullExpressionValue(videoAspectRatioFromJsonString, "videoAspectRatioFromJsonString(...)");
        Object property12 = getProperty("arbitraryVideoResolution");
        String str2 = property12 instanceof String ? (String) property12 : null;
        Size2 fromJson2 = str2 == null ? null : Size2Deserializer.INSTANCE.fromJson(str2);
        Object property13 = getProperty("closestResolutionTo12MPForFourToThreeAspectRatio");
        Boolean bool4 = property13 instanceof Boolean ? (Boolean) property13 : null;
        boolean booleanValue4 = bool4 != null ? bool4.booleanValue() : NativeCameraSettingsDefaults.chooseClosestResolutionTo12MPForFourThreeAspectRatio();
        Object property14 = getProperty("quadHd");
        Boolean bool5 = property14 instanceof Boolean ? (Boolean) property14 : null;
        boolean booleanValue5 = bool5 != null ? bool5.booleanValue() : NativeCameraSettingsDefaults.quadHd();
        Object property15 = getProperty("dynamicResolution");
        Boolean bool6 = property15 instanceof Boolean ? (Boolean) property15 : null;
        boolean booleanValue6 = bool6 != null ? bool6.booleanValue() : NativeCameraSettingsDefaults.dynamicResolution();
        Object property16 = getProperty("dynamicResolutionLowFps");
        Integer num2 = property16 instanceof Integer ? (Integer) property16 : null;
        int intValue2 = num2 != null ? num2.intValue() : NativeCameraSettingsDefaults.dynamicResolutionLowFps();
        Object property17 = getProperty("dynamicResolutionMeasuringIntervalMillis");
        Integer num3 = property17 instanceof Integer ? (Integer) property17 : null;
        int intValue3 = num3 != null ? num3.intValue() : NativeCameraSettingsDefaults.dynamicResolutionMeasuringIntervalMillis();
        Object property18 = getProperty("dynamicResolutionLogging");
        Boolean bool7 = property18 instanceof Boolean ? (Boolean) property18 : null;
        boolean booleanValue7 = bool7 != null ? bool7.booleanValue() : NativeCameraSettingsDefaults.dynamicResolutionLogging();
        Object property19 = getProperty("enableSensorPixelModeMaximumResolution");
        Boolean bool8 = property19 instanceof Boolean ? (Boolean) property19 : null;
        boolean booleanValue8 = bool8 != null ? bool8.booleanValue() : NativeCameraSettingsDefaults.enableSensorPixelModeMaximumResolution();
        Object property20 = getProperty("preferredFrameRateRange");
        NativePreferredFrameRateRange nativePreferredFrameRateRange = property20 instanceof NativePreferredFrameRateRange ? (NativePreferredFrameRateRange) property20 : null;
        Object property21 = getProperty("manualUntilCaptureTimeout");
        Integer num4 = property21 instanceof Integer ? (Integer) property21 : null;
        return new NativeCameraSettings(videoResolution, floatValue, floatValue2, f, f2, nativeFocusSettings, intValue, booleanValue, booleanValue2, floatValue4, booleanValue3, nativeTonemapCurve2, nativeNoiseReduction2, nativeShadingMode2, nativeEdgeEnhancement2, fromJson, f3, videoAspectRatioFromJsonString, fromJson2, booleanValue4, booleanValue5, booleanValue6, intValue2, intValue3, booleanValue7, booleanValue8, fromString, nativePreferredFrameRateRange, num4 != null ? num4.intValue() : NativeCameraSettingsDefaults.manualUntilCaptureTimeout(), NativeCameraSettingsDefaults.useDepth());
    }

    public final void addDefaultPropertiesFromProfile$scandit_capture_core(CameraProfile profile) {
        Intrinsics.checkNotNullParameter(profile, "profile");
        NativeFocusStrategy e = profile.e();
        if (e != null) {
            Object property = getProperty("focusStrategy");
            if (property == null || Intrinsics.areEqual(property, "auto")) {
                setProperty("focusStrategy", e);
            }
        }
    }

    public final FocusGestureStrategy getFocusGestureStrategy() {
        return this.focusGestureStrategy;
    }

    public final VideoResolution getPreferredResolution() {
        return this.preferredResolution;
    }

    public final Object getProperty(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.e.get(name);
    }

    public final boolean getShouldPreferSmoothAutoFocus() {
        return this.shouldPreferSmoothAutoFocus;
    }

    public final float getZoomFactor() {
        return this.zoomFactor;
    }

    public final float getZoomGestureZoomFactor() {
        return this.zoomGestureZoomFactor;
    }

    public final boolean isUsingApi2Features$scandit_capture_core() {
        Object property = getProperty("usesApi2Features");
        if (Intrinsics.areEqual(property instanceof Boolean ? (Boolean) property : null, Boolean.TRUE)) {
            return true;
        }
        Float a = a("exposureTargetBias");
        boolean z = (a != null ? a.floatValue() : 0.0f) == 0.0f;
        Float a2 = a("manualLensPosition");
        return (a2 != null ? a2.floatValue() : -1.0f) >= 0.0f || this.preferredResolution == VideoResolution.UHD4K || !z;
    }

    public final void setFocusGestureStrategy(FocusGestureStrategy focusGestureStrategy) {
        Intrinsics.checkNotNullParameter(focusGestureStrategy, "<set-?>");
        this.focusGestureStrategy = focusGestureStrategy;
    }

    public final void setPreferredResolution(VideoResolution videoResolution) {
        Intrinsics.checkNotNullParameter(videoResolution, "<set-?>");
        this.preferredResolution = videoResolution;
    }

    public final void setProperty(String name, Object value) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (Intrinsics.areEqual(name, "focusStrategy") && (value instanceof NativeFocusStrategy)) {
            NativeFocusStrategy focusStrategy = (NativeFocusStrategy) value;
            Intrinsics.checkNotNullParameter(focusStrategy, "focusStrategy");
            Intrinsics.checkNotNullParameter(focusStrategy, "<this>");
            value = NativeEnumSerializer.focusStrategyToString(focusStrategy);
            Intrinsics.checkNotNullExpressionValue(value, "focusStrategyToString(...)");
        }
        this.e.put(name, value);
        NativeFocusStrategy focusStrategy2 = NativeFocusStrategy.FORCE_CONTINUOUS;
        Intrinsics.checkNotNullParameter(focusStrategy2, "focusStrategy");
        Intrinsics.checkNotNullParameter(focusStrategy2, "<this>");
        String focusStrategyToString = NativeEnumSerializer.focusStrategyToString(focusStrategy2);
        Intrinsics.checkNotNullExpressionValue(focusStrategyToString, "focusStrategyToString(...)");
        if (Intrinsics.areEqual(name, "focusStrategy") && Intrinsics.areEqual(value, focusStrategyToString)) {
            this.e.put("macroAutofocusMode", "off");
        }
    }

    public final void setShouldPreferSmoothAutoFocus(boolean z) {
        this.shouldPreferSmoothAutoFocus = z;
        if (z) {
            this.e.put("macroAutofocusMode", "off");
        }
    }

    public final void setZoomFactor(float f) {
        this.zoomFactor = f;
    }

    public final void setZoomGestureZoomFactor(float f) {
        this.zoomGestureZoomFactor = f;
    }

    public final void updateWithNativeObject$scandit_capture_core(NativeCameraSettings impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        VideoResolution preferredResolution = impl.getPreferredResolution();
        Intrinsics.checkNotNullExpressionValue(preferredResolution, "getPreferredResolution(...)");
        this.preferredResolution = preferredResolution;
        this.zoomFactor = impl.getZoomFactor();
        this.zoomGestureZoomFactor = impl.getZoomGestureZoomFactor();
        setShouldPreferSmoothAutoFocus(impl.getFocus().getShouldPreferSmoothAutoFocus());
        FocusGestureStrategy focusGestureStrategy = impl.getFocus().getFocusGestureStrategy();
        Intrinsics.checkNotNullExpressionValue(focusGestureStrategy, "getFocusGestureStrategy(...)");
        this.focusGestureStrategy = focusGestureStrategy;
        setProperty("api", Integer.valueOf(impl.getApi()));
        setProperty("usesApi2Features", Boolean.valueOf(impl.getUsesCameraApi2Features()));
        setProperty("manualLensPosition", Float.valueOf(impl.getFocus().getManualLensPosition()));
        setProperty("overwriteWithHighestResolution", Boolean.valueOf(impl.getOverwriteWithHighestResolution()));
        setProperty("exposureTargetBias", Float.valueOf(impl.getExposureTargetBias()));
        setProperty("maxFrameRate", Float.valueOf(impl.getMaxFrameRate()));
        setProperty("minFrameRate", Float.valueOf(impl.getMinFrameRate()));
        setProperty("colorCorrection", Boolean.valueOf(impl.getColorCorrection()));
        setProperty("toneMappingCurve", impl.getToneMappingCurve());
        setProperty("edgeEnhancementMode", impl.getEdgeEnhancementMode());
        setProperty("noiseReductionMode", impl.getNoiseReductionMode());
        setProperty("shadingMode", impl.getShadingMode());
        NativeFocusStrategy focusStrategy = impl.getFocus().getSelectedFocusStrategy();
        Intrinsics.checkNotNullExpressionValue(focusStrategy, "getSelectedFocusStrategy(...)");
        Intrinsics.checkNotNullParameter(focusStrategy, "focusStrategy");
        Intrinsics.checkNotNullParameter(focusStrategy, "<this>");
        String focusStrategyToString = NativeEnumSerializer.focusStrategyToString(focusStrategy);
        Intrinsics.checkNotNullExpressionValue(focusStrategyToString, "focusStrategyToString(...)");
        setProperty("focusStrategy", focusStrategyToString);
        RegionStrategySerializer regionStrategySerializer = RegionStrategySerializer.INSTANCE;
        NativeRegionStrategy regionControlStrategy = impl.getRegionControlStrategy();
        Intrinsics.checkNotNullExpressionValue(regionControlStrategy, "getRegionControlStrategy(...)");
        setProperty("regionStrategy", regionStrategySerializer.toJson(regionControlStrategy));
        NativeVideoAspectRatio aspectRatio = impl.getPreferredAspectRatio();
        Intrinsics.checkNotNullExpressionValue(aspectRatio, "getPreferredAspectRatio(...)");
        Intrinsics.checkNotNullParameter(aspectRatio, "aspectRatio");
        Intrinsics.checkNotNullParameter(aspectRatio, "<this>");
        String videoAspectRatioToString = NativeEnumSerializer.videoAspectRatioToString(aspectRatio);
        Intrinsics.checkNotNullExpressionValue(videoAspectRatioToString, "videoAspectRatioToString(...)");
        setProperty("preferredAspectRatio", videoAspectRatioToString);
        setProperty("enableSensorPixelModeMaximumResolution", Boolean.valueOf(impl.getEnableSensorPixelModeMaximumResolution()));
        setProperty("closestResolutionTo12MPForFourToThreeAspectRatio", Boolean.valueOf(impl.getChooseClosestResolutionTo12MPForFourThreeAspectRatio()));
        setProperty("quadHd", Boolean.valueOf(impl.getQuadHd()));
        if (impl.getArbitraryVideoResolution() != null) {
            Size2 arbitraryVideoResolution = impl.getArbitraryVideoResolution();
            Intrinsics.checkNotNullExpressionValue(arbitraryVideoResolution, "getArbitraryVideoResolution(...)");
            setProperty("arbitraryVideoResolution", Size2UtilsKt.toJson(arbitraryVideoResolution));
        }
        setProperty("dynamicResolution", Boolean.valueOf(impl.getDynamicResolution()));
        setProperty("dynamicResolutionLowFps", Integer.valueOf(impl.getDynamicResolutionLowFps()));
        setProperty("dynamicResolutionMeasuringIntervalMillis", Integer.valueOf(impl.getDynamicResolutionMeasuringIntervalMillis()));
        setProperty("dynamicResolutionLogging", Boolean.valueOf(impl.getDynamicResolutionLogging()));
        a(impl.getProperties());
        a(impl.getFocus().getProperties());
        setProperty("preferredFrameRateRange", impl.getPreferredFrameRateRange());
        setProperty("manualUntilCaptureTimeout", Integer.valueOf(impl.getManualUntilCaptureTimeout()));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CameraSettings(NativeCameraSettings impl) {
        this();
        Intrinsics.checkNotNullParameter(impl, "impl");
        updateWithNativeObject$scandit_capture_core(impl);
    }

    private final Float a(String str) {
        Object obj = this.e.get(str);
        Float f = obj instanceof Float ? (Float) obj : null;
        if (f == null) {
            Double d = obj instanceof Double ? (Double) obj : null;
            f = d != null ? Float.valueOf((float) d.doubleValue()) : null;
            if (f == null) {
                if ((obj instanceof Integer ? (Integer) obj : null) != null) {
                    return Float.valueOf(r3.intValue());
                }
                return null;
            }
        }
        return f;
    }
}
