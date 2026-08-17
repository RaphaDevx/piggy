package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.source.TorchState;

/* loaded from: classes2.dex */
public final class NativeCameraDelegateSettings {
    final boolean colorCorrection;
    final boolean disablePostProcessing;
    final NativeEdgeEnhancement edgeEnhancementMode;
    final boolean enableSensorPixelModeMaximumResolution;
    final long exposureDuration;
    final float exposureTargetBias;
    final long frameDuration;
    final Size2 frameResolution;
    final NativeMacroAfMode macroAutofocusMode;
    final float maxFrameRate;
    final float minFrameRate;
    final int minPreviewShortSide;
    final NativeNoiseReduction noiseReductionMode;
    final NativePreferredFrameRateRange preferredFrameRateRange;
    final NativeJsonValue properties;
    final boolean sceneChangeDetection;
    final int sensorSensitivity;
    final NativeShadingMode shadingMode;
    final int sharpnessStrength;
    final float stageOneStandbyDuration;
    final NativeTonemapCurve toneMappingCurve;
    final TorchState torchState;
    final boolean zoomAffectsMeteringArea;
    final float zoomFactor;

    public NativeCameraDelegateSettings(Size2 size2, float f, float f2, float f3, float f4, NativeEdgeEnhancement nativeEdgeEnhancement, boolean z, TorchState torchState, NativeTonemapCurve nativeTonemapCurve, NativeNoiseReduction nativeNoiseReduction, NativeShadingMode nativeShadingMode, boolean z2, NativeMacroAfMode nativeMacroAfMode, boolean z3, long j, long j2, boolean z4, int i, int i2, float f5, int i3, boolean z5, NativeJsonValue nativeJsonValue, NativePreferredFrameRateRange nativePreferredFrameRateRange) {
        this.frameResolution = size2;
        this.minFrameRate = f;
        this.maxFrameRate = f2;
        this.zoomFactor = f3;
        this.exposureTargetBias = f4;
        this.edgeEnhancementMode = nativeEdgeEnhancement;
        this.colorCorrection = z;
        this.torchState = torchState;
        this.toneMappingCurve = nativeTonemapCurve;
        this.noiseReductionMode = nativeNoiseReduction;
        this.shadingMode = nativeShadingMode;
        this.zoomAffectsMeteringArea = z2;
        this.macroAutofocusMode = nativeMacroAfMode;
        this.sceneChangeDetection = z3;
        this.exposureDuration = j;
        this.frameDuration = j2;
        this.disablePostProcessing = z4;
        this.sharpnessStrength = i;
        this.sensorSensitivity = i2;
        this.stageOneStandbyDuration = f5;
        this.minPreviewShortSide = i3;
        this.enableSensorPixelModeMaximumResolution = z5;
        this.properties = nativeJsonValue;
        this.preferredFrameRateRange = nativePreferredFrameRateRange;
    }

    public boolean getColorCorrection() {
        return this.colorCorrection;
    }

    public boolean getDisablePostProcessing() {
        return this.disablePostProcessing;
    }

    public NativeEdgeEnhancement getEdgeEnhancementMode() {
        return this.edgeEnhancementMode;
    }

    public boolean getEnableSensorPixelModeMaximumResolution() {
        return this.enableSensorPixelModeMaximumResolution;
    }

    public long getExposureDuration() {
        return this.exposureDuration;
    }

    public float getExposureTargetBias() {
        return this.exposureTargetBias;
    }

    public long getFrameDuration() {
        return this.frameDuration;
    }

    public Size2 getFrameResolution() {
        return this.frameResolution;
    }

    public NativeMacroAfMode getMacroAutofocusMode() {
        return this.macroAutofocusMode;
    }

    public float getMaxFrameRate() {
        return this.maxFrameRate;
    }

    public float getMinFrameRate() {
        return this.minFrameRate;
    }

    public int getMinPreviewShortSide() {
        return this.minPreviewShortSide;
    }

    public NativeNoiseReduction getNoiseReductionMode() {
        return this.noiseReductionMode;
    }

    public NativePreferredFrameRateRange getPreferredFrameRateRange() {
        return this.preferredFrameRateRange;
    }

    public NativeJsonValue getProperties() {
        return this.properties;
    }

    public boolean getSceneChangeDetection() {
        return this.sceneChangeDetection;
    }

    public int getSensorSensitivity() {
        return this.sensorSensitivity;
    }

    public NativeShadingMode getShadingMode() {
        return this.shadingMode;
    }

    public int getSharpnessStrength() {
        return this.sharpnessStrength;
    }

    public float getStageOneStandbyDuration() {
        return this.stageOneStandbyDuration;
    }

    public NativeTonemapCurve getToneMappingCurve() {
        return this.toneMappingCurve;
    }

    public TorchState getTorchState() {
        return this.torchState;
    }

    public boolean getZoomAffectsMeteringArea() {
        return this.zoomAffectsMeteringArea;
    }

    public float getZoomFactor() {
        return this.zoomFactor;
    }

    public String toString() {
        return "NativeCameraDelegateSettings{frameResolution=" + this.frameResolution + ",minFrameRate=" + this.minFrameRate + ",maxFrameRate=" + this.maxFrameRate + ",zoomFactor=" + this.zoomFactor + ",exposureTargetBias=" + this.exposureTargetBias + ",edgeEnhancementMode=" + this.edgeEnhancementMode + ",colorCorrection=" + this.colorCorrection + ",torchState=" + this.torchState + ",toneMappingCurve=" + this.toneMappingCurve + ",noiseReductionMode=" + this.noiseReductionMode + ",shadingMode=" + this.shadingMode + ",zoomAffectsMeteringArea=" + this.zoomAffectsMeteringArea + ",macroAutofocusMode=" + this.macroAutofocusMode + ",sceneChangeDetection=" + this.sceneChangeDetection + ",exposureDuration=" + this.exposureDuration + ",frameDuration=" + this.frameDuration + ",disablePostProcessing=" + this.disablePostProcessing + ",sharpnessStrength=" + this.sharpnessStrength + ",sensorSensitivity=" + this.sensorSensitivity + ",stageOneStandbyDuration=" + this.stageOneStandbyDuration + ",minPreviewShortSide=" + this.minPreviewShortSide + ",enableSensorPixelModeMaximumResolution=" + this.enableSensorPixelModeMaximumResolution + ",properties=" + this.properties + ",preferredFrameRateRange=" + this.preferredFrameRateRange + "}";
    }
}
