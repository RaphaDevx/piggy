package com.scandit.datacapture.frameworks.core.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraSettingsPropertiesDefaults.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/data/defaults/CameraSettingsPropertiesDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "settings", "Lcom/scandit/datacapture/core/source/CameraSettings;", "(Lcom/scandit/datacapture/core/source/CameraSettings;)V", "toMap", "", "", "", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class CameraSettingsPropertiesDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_API = "api";
    private static final String FIELD_CAMERA_DEVICE_TYPE = "cameraDeviceType";
    private static final String FIELD_CLOSEST_RESOLUTION_TO_12_MP_FOR_FOUR_TO_THREE_ASPECT_RATION = "closestResolutionTo12MPForFourToThreeAspectRatio";
    private static final String FIELD_COLOR_CORRECTION = "colorCorrection";
    private static final String FIELD_DISABLE_MANUAL_LENS_POSITION_SUPPORT_CHECK = "disableManualLensPositionSupportCheck";
    private static final String FIELD_DISABLE_RE_TRIGGER_AND_CONTINUOUS = "disableRetriggerAndContinuous";
    private static final String FIELD_EDGE_ENHANCEMENT_MODE = "edgeEnhancementMode";
    private static final String FIELD_EXPOSURE_DURATION = "exposureDuration";
    private static final String FIELD_EXPOSURE_TARGET_BIAS = "exposureTargetBias";
    private static final String FIELD_FOCUS_STRATEGY = "focusStrategy";
    private static final String FIELD_FORCE_AGGRESSIVE_AUTO_FOCUS = "forceAggressiveAutoFocus";
    private static final String FIELD_INITIAL_SINGLE_SHOT_FOCUS_DURATION = "initialSingleShotFocusDuration";
    private static final String FIELD_MACRO_AUTO_FOCUS_MODE = "macroAutofocusMode";
    private static final String FIELD_MANUAL_LENS_POSITION = "manualLensPosition";
    private static final String FIELD_MAX_EXPOSURE_DURATION = "maxExposureDuration";
    private static final String FIELD_MIN_FRAME_RATE = "minFrameRate";
    private static final String FIELD_NOISE_REDUCTION_MODE = "noiseReductionMode";
    private static final String FIELD_NUM_OF_SCAN_PHASE_TRIGGER_CYCLES = "numOfScanPhaseTriggerCycles";
    private static final String FIELD_OVERWRITE_WITH_HIGHEST_RESOLUTION = "overwriteWithHighestResolution";
    private static final String FIELD_PREFERRED_ASPECT_RATION = "preferredAspectRatio";
    private static final String FIELD_PREFERRED_FRAME_RATE_RANGE = "preferredFrameRateRange";
    private static final String FIELD_PREFER_BINNED_FORMAT = "prefer_binned_format";
    private static final String FIELD_REGION_STRATEGY = "regionStrategy";
    private static final String FIELD_REPEATED_TRIGGER_INTERVAL = "repeatedTriggerInterval";
    private static final String FIELD_SCAN_PHASE_NO_SRE_TIMEOUT = "scanPhaseNoSreTimeout";
    private static final String FIELD_SCENARIO_A_SMART_AF = "scenario_a_smart_af";
    private static final String FIELD_SEARCH_PHASE_RE_TRIGGER_INTERVAL = "searchPhaseRetriggerInterval";
    private static final String FIELD_SENSOR_SENSITIVITY = "sensorSensitivity";
    private static final String FIELD_SHARPNESS_STRENGTH = "sharpnessStrength";
    private static final String FIELD_TONE_MAPPING_CURVE = "toneMappingCurve";
    private static final String FIELD_TRIGGER_AF = "triggerAf";
    private static final String FIELD_VIDEO_HDR_MODE = "videoHDRMode";
    private static final String FIELD_XCOVER_INITIAL_LENS_POSITION = "xcoverInitialLensPosition";
    private final CameraSettings settings;

    public /* synthetic */ CameraSettingsPropertiesDefaults(CameraSettings cameraSettings, DefaultConstructorMarker defaultConstructorMarker) {
        this(cameraSettings);
    }

    private CameraSettingsPropertiesDefaults(CameraSettings cameraSettings) {
        this.settings = cameraSettings;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        Object json;
        Object json2;
        Object json3;
        Object json4;
        Object json5;
        Object json6;
        Object json7;
        Object json8;
        Object json9;
        Object json10;
        Object json11;
        Object json12;
        Object json13;
        Object json14;
        Object json15;
        Object json16;
        Object json17;
        Object json18;
        Object json19;
        Object json20;
        Object json21;
        Object json22;
        Object json23;
        Object json24;
        Object json25;
        Object json26;
        Object json27;
        Object json28;
        Object json29;
        Object json30;
        Object json31;
        Object json32;
        Object json33;
        json = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_MIN_FRAME_RATE));
        json2 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_COLOR_CORRECTION));
        json3 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_EDGE_ENHANCEMENT_MODE));
        json4 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_TONE_MAPPING_CURVE));
        json5 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_NOISE_REDUCTION_MODE));
        json6 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_MACRO_AUTO_FOCUS_MODE));
        json7 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_PREFERRED_ASPECT_RATION));
        json8 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_OVERWRITE_WITH_HIGHEST_RESOLUTION));
        json9 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_EXPOSURE_TARGET_BIAS));
        json10 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_TRIGGER_AF));
        json11 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_API));
        json12 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_DISABLE_MANUAL_LENS_POSITION_SUPPORT_CHECK));
        json13 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_XCOVER_INITIAL_LENS_POSITION));
        json14 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_REGION_STRATEGY));
        json15 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_SCAN_PHASE_NO_SRE_TIMEOUT));
        json16 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_CLOSEST_RESOLUTION_TO_12_MP_FOR_FOUR_TO_THREE_ASPECT_RATION));
        json17 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_CAMERA_DEVICE_TYPE));
        json18 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_REPEATED_TRIGGER_INTERVAL));
        json19 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_DISABLE_RE_TRIGGER_AND_CONTINUOUS));
        json20 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_FORCE_AGGRESSIVE_AUTO_FOCUS));
        json21 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_SEARCH_PHASE_RE_TRIGGER_INTERVAL));
        json22 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_NUM_OF_SCAN_PHASE_TRIGGER_CYCLES));
        json23 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_MAX_EXPOSURE_DURATION));
        json24 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_INITIAL_SINGLE_SHOT_FOCUS_DURATION));
        json25 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_SHARPNESS_STRENGTH));
        json26 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_EXPOSURE_DURATION));
        json27 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_SENSOR_SENSITIVITY));
        json28 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_PREFER_BINNED_FORMAT));
        json29 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_VIDEO_HDR_MODE));
        json30 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_SCENARIO_A_SMART_AF));
        json31 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_MANUAL_LENS_POSITION));
        json32 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_FOCUS_STRATEGY));
        json33 = CameraSettingsPropertiesDefaultsKt.toJson(this.settings.getProperty(FIELD_PREFERRED_FRAME_RATE_RANGE));
        Map mapOf = MapsKt.mapOf(TuplesKt.to(FIELD_MIN_FRAME_RATE, json), TuplesKt.to(FIELD_COLOR_CORRECTION, json2), TuplesKt.to(FIELD_EDGE_ENHANCEMENT_MODE, json3), TuplesKt.to(FIELD_TONE_MAPPING_CURVE, json4), TuplesKt.to(FIELD_NOISE_REDUCTION_MODE, json5), TuplesKt.to(FIELD_MACRO_AUTO_FOCUS_MODE, json6), TuplesKt.to(FIELD_PREFERRED_ASPECT_RATION, json7), TuplesKt.to(FIELD_OVERWRITE_WITH_HIGHEST_RESOLUTION, json8), TuplesKt.to(FIELD_EXPOSURE_TARGET_BIAS, json9), TuplesKt.to(FIELD_TRIGGER_AF, json10), TuplesKt.to(FIELD_API, json11), TuplesKt.to(FIELD_DISABLE_MANUAL_LENS_POSITION_SUPPORT_CHECK, json12), TuplesKt.to(FIELD_XCOVER_INITIAL_LENS_POSITION, json13), TuplesKt.to(FIELD_REGION_STRATEGY, json14), TuplesKt.to(FIELD_SCAN_PHASE_NO_SRE_TIMEOUT, json15), TuplesKt.to(FIELD_CLOSEST_RESOLUTION_TO_12_MP_FOR_FOUR_TO_THREE_ASPECT_RATION, json16), TuplesKt.to(FIELD_CAMERA_DEVICE_TYPE, json17), TuplesKt.to(FIELD_REPEATED_TRIGGER_INTERVAL, json18), TuplesKt.to(FIELD_DISABLE_RE_TRIGGER_AND_CONTINUOUS, json19), TuplesKt.to(FIELD_FORCE_AGGRESSIVE_AUTO_FOCUS, json20), TuplesKt.to(FIELD_SEARCH_PHASE_RE_TRIGGER_INTERVAL, json21), TuplesKt.to(FIELD_NUM_OF_SCAN_PHASE_TRIGGER_CYCLES, json22), TuplesKt.to(FIELD_MAX_EXPOSURE_DURATION, json23), TuplesKt.to(FIELD_INITIAL_SINGLE_SHOT_FOCUS_DURATION, json24), TuplesKt.to(FIELD_SHARPNESS_STRENGTH, json25), TuplesKt.to(FIELD_EXPOSURE_DURATION, json26), TuplesKt.to(FIELD_SENSOR_SENSITIVITY, json27), TuplesKt.to(FIELD_PREFER_BINNED_FORMAT, json28), TuplesKt.to(FIELD_VIDEO_HDR_MODE, json29), TuplesKt.to(FIELD_SCENARIO_A_SMART_AF, json30), TuplesKt.to(FIELD_MANUAL_LENS_POSITION, json31), TuplesKt.to(FIELD_FOCUS_STRATEGY, json32), TuplesKt.to(FIELD_PREFERRED_FRAME_RATE_RANGE, json33));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : mapOf.entrySet()) {
            if (entry.getValue() != null) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    /* compiled from: CameraSettingsPropertiesDefaults.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b!\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010&2\u0006\u0010'\u001a\u00020(R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/data/defaults/CameraSettingsPropertiesDefaults$Companion;", "", "()V", "FIELD_API", "", "FIELD_CAMERA_DEVICE_TYPE", "FIELD_CLOSEST_RESOLUTION_TO_12_MP_FOR_FOUR_TO_THREE_ASPECT_RATION", "FIELD_COLOR_CORRECTION", "FIELD_DISABLE_MANUAL_LENS_POSITION_SUPPORT_CHECK", "FIELD_DISABLE_RE_TRIGGER_AND_CONTINUOUS", "FIELD_EDGE_ENHANCEMENT_MODE", "FIELD_EXPOSURE_DURATION", "FIELD_EXPOSURE_TARGET_BIAS", "FIELD_FOCUS_STRATEGY", "FIELD_FORCE_AGGRESSIVE_AUTO_FOCUS", "FIELD_INITIAL_SINGLE_SHOT_FOCUS_DURATION", "FIELD_MACRO_AUTO_FOCUS_MODE", "FIELD_MANUAL_LENS_POSITION", "FIELD_MAX_EXPOSURE_DURATION", "FIELD_MIN_FRAME_RATE", "FIELD_NOISE_REDUCTION_MODE", "FIELD_NUM_OF_SCAN_PHASE_TRIGGER_CYCLES", "FIELD_OVERWRITE_WITH_HIGHEST_RESOLUTION", "FIELD_PREFERRED_ASPECT_RATION", "FIELD_PREFERRED_FRAME_RATE_RANGE", "FIELD_PREFER_BINNED_FORMAT", "FIELD_REGION_STRATEGY", "FIELD_REPEATED_TRIGGER_INTERVAL", "FIELD_SCAN_PHASE_NO_SRE_TIMEOUT", "FIELD_SCENARIO_A_SMART_AF", "FIELD_SEARCH_PHASE_RE_TRIGGER_INTERVAL", "FIELD_SENSOR_SENSITIVITY", "FIELD_SHARPNESS_STRENGTH", "FIELD_TONE_MAPPING_CURVE", "FIELD_TRIGGER_AF", "FIELD_VIDEO_HDR_MODE", "FIELD_XCOVER_INITIAL_LENS_POSITION", "get", "", "cameraSettings", "Lcom/scandit/datacapture/core/source/CameraSettings;", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Map<String, Object> get(CameraSettings cameraSettings) {
            Intrinsics.checkNotNullParameter(cameraSettings, "cameraSettings");
            return new CameraSettingsPropertiesDefaults(cameraSettings, null).toMap();
        }
    }
}
