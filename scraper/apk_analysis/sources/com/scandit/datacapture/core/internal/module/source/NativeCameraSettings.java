package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.source.VideoResolution;

/* loaded from: classes2.dex */
public final class NativeCameraSettings {
    final int api;
    final Size2 arbitraryVideoResolution;
    final boolean chooseClosestResolutionTo12MPForFourThreeAspectRatio;
    final boolean colorCorrection;
    final boolean dynamicResolution;
    final boolean dynamicResolutionLogging;
    final int dynamicResolutionLowFps;
    final int dynamicResolutionMeasuringIntervalMillis;
    final NativeEdgeEnhancement edgeEnhancementMode;
    final boolean enableSensorPixelModeMaximumResolution;
    final float exposureTargetBias;
    final NativeFocusSettings focus;
    final int manualUntilCaptureTimeout;
    final float maxFrameRate;
    final float minFrameRate;
    final NativeNoiseReduction noiseReductionMode;
    final boolean overwriteWithHighestResolution;
    final NativeVideoAspectRatio preferredAspectRatio;
    final NativePreferredFrameRateRange preferredFrameRateRange;
    final VideoResolution preferredResolution;
    final NativeJsonValue properties;
    final boolean quadHd;
    final NativeRegionStrategy regionControlStrategy;
    final NativeShadingMode shadingMode;
    final NativeTonemapCurve toneMappingCurve;
    final float torchLevel;
    final boolean useDepth;
    final boolean usesCameraApi2Features;
    final float zoomFactor;
    final float zoomGestureZoomFactor;

    public NativeCameraSettings(VideoResolution videoResolution, float f, float f2, float f3, float f4, NativeFocusSettings nativeFocusSettings, int i, boolean z, boolean z2, float f5, boolean z3, NativeTonemapCurve nativeTonemapCurve, NativeNoiseReduction nativeNoiseReduction, NativeShadingMode nativeShadingMode, NativeEdgeEnhancement nativeEdgeEnhancement, NativeRegionStrategy nativeRegionStrategy, float f6, NativeVideoAspectRatio nativeVideoAspectRatio, Size2 size2, boolean z4, boolean z5, boolean z6, int i2, int i3, boolean z7, boolean z8, NativeJsonValue nativeJsonValue, NativePreferredFrameRateRange nativePreferredFrameRateRange, int i4, boolean z9) {
        this.preferredResolution = videoResolution;
        this.minFrameRate = f;
        this.maxFrameRate = f2;
        this.zoomFactor = f3;
        this.zoomGestureZoomFactor = f4;
        this.focus = nativeFocusSettings;
        this.api = i;
        this.usesCameraApi2Features = z;
        this.overwriteWithHighestResolution = z2;
        this.exposureTargetBias = f5;
        this.colorCorrection = z3;
        this.toneMappingCurve = nativeTonemapCurve;
        this.noiseReductionMode = nativeNoiseReduction;
        this.shadingMode = nativeShadingMode;
        this.edgeEnhancementMode = nativeEdgeEnhancement;
        this.regionControlStrategy = nativeRegionStrategy;
        this.torchLevel = f6;
        this.preferredAspectRatio = nativeVideoAspectRatio;
        this.arbitraryVideoResolution = size2;
        this.chooseClosestResolutionTo12MPForFourThreeAspectRatio = z4;
        this.quadHd = z5;
        this.dynamicResolution = z6;
        this.dynamicResolutionLowFps = i2;
        this.dynamicResolutionMeasuringIntervalMillis = i3;
        this.dynamicResolutionLogging = z7;
        this.enableSensorPixelModeMaximumResolution = z8;
        this.properties = nativeJsonValue;
        this.preferredFrameRateRange = nativePreferredFrameRateRange;
        this.manualUntilCaptureTimeout = i4;
        this.useDepth = z9;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NativeCameraSettings)) {
            return false;
        }
        NativeCameraSettings nativeCameraSettings = (NativeCameraSettings) obj;
        if (this.preferredResolution != nativeCameraSettings.preferredResolution || this.minFrameRate != nativeCameraSettings.minFrameRate || this.maxFrameRate != nativeCameraSettings.maxFrameRate || this.zoomFactor != nativeCameraSettings.zoomFactor || this.zoomGestureZoomFactor != nativeCameraSettings.zoomGestureZoomFactor || !this.focus.equals(nativeCameraSettings.focus) || this.api != nativeCameraSettings.api || this.usesCameraApi2Features != nativeCameraSettings.usesCameraApi2Features || this.overwriteWithHighestResolution != nativeCameraSettings.overwriteWithHighestResolution || this.exposureTargetBias != nativeCameraSettings.exposureTargetBias || this.colorCorrection != nativeCameraSettings.colorCorrection || this.toneMappingCurve != nativeCameraSettings.toneMappingCurve || this.noiseReductionMode != nativeCameraSettings.noiseReductionMode || this.shadingMode != nativeCameraSettings.shadingMode || this.edgeEnhancementMode != nativeCameraSettings.edgeEnhancementMode || this.regionControlStrategy != nativeCameraSettings.regionControlStrategy || this.torchLevel != nativeCameraSettings.torchLevel || this.preferredAspectRatio != nativeCameraSettings.preferredAspectRatio) {
            return false;
        }
        Size2 size2 = this.arbitraryVideoResolution;
        if (((size2 != null || nativeCameraSettings.arbitraryVideoResolution != null) && (size2 == null || !size2.equals(nativeCameraSettings.arbitraryVideoResolution))) || this.chooseClosestResolutionTo12MPForFourThreeAspectRatio != nativeCameraSettings.chooseClosestResolutionTo12MPForFourThreeAspectRatio || this.quadHd != nativeCameraSettings.quadHd || this.dynamicResolution != nativeCameraSettings.dynamicResolution || this.dynamicResolutionLowFps != nativeCameraSettings.dynamicResolutionLowFps || this.dynamicResolutionMeasuringIntervalMillis != nativeCameraSettings.dynamicResolutionMeasuringIntervalMillis || this.dynamicResolutionLogging != nativeCameraSettings.dynamicResolutionLogging || this.enableSensorPixelModeMaximumResolution != nativeCameraSettings.enableSensorPixelModeMaximumResolution) {
            return false;
        }
        NativeJsonValue nativeJsonValue = this.properties;
        if (!(nativeJsonValue == null && nativeCameraSettings.properties == null) && (nativeJsonValue == null || !nativeJsonValue.equals(nativeCameraSettings.properties))) {
            return false;
        }
        NativePreferredFrameRateRange nativePreferredFrameRateRange = this.preferredFrameRateRange;
        return ((nativePreferredFrameRateRange == null && nativeCameraSettings.preferredFrameRateRange == null) || (nativePreferredFrameRateRange != null && nativePreferredFrameRateRange.equals(nativeCameraSettings.preferredFrameRateRange))) && this.manualUntilCaptureTimeout == nativeCameraSettings.manualUntilCaptureTimeout && this.useDepth == nativeCameraSettings.useDepth;
    }

    public int getApi() {
        return this.api;
    }

    public Size2 getArbitraryVideoResolution() {
        return this.arbitraryVideoResolution;
    }

    public boolean getChooseClosestResolutionTo12MPForFourThreeAspectRatio() {
        return this.chooseClosestResolutionTo12MPForFourThreeAspectRatio;
    }

    public boolean getColorCorrection() {
        return this.colorCorrection;
    }

    public boolean getDynamicResolution() {
        return this.dynamicResolution;
    }

    public boolean getDynamicResolutionLogging() {
        return this.dynamicResolutionLogging;
    }

    public int getDynamicResolutionLowFps() {
        return this.dynamicResolutionLowFps;
    }

    public int getDynamicResolutionMeasuringIntervalMillis() {
        return this.dynamicResolutionMeasuringIntervalMillis;
    }

    public NativeEdgeEnhancement getEdgeEnhancementMode() {
        return this.edgeEnhancementMode;
    }

    public boolean getEnableSensorPixelModeMaximumResolution() {
        return this.enableSensorPixelModeMaximumResolution;
    }

    public float getExposureTargetBias() {
        return this.exposureTargetBias;
    }

    public NativeFocusSettings getFocus() {
        return this.focus;
    }

    public int getManualUntilCaptureTimeout() {
        return this.manualUntilCaptureTimeout;
    }

    public float getMaxFrameRate() {
        return this.maxFrameRate;
    }

    public float getMinFrameRate() {
        return this.minFrameRate;
    }

    public NativeNoiseReduction getNoiseReductionMode() {
        return this.noiseReductionMode;
    }

    public boolean getOverwriteWithHighestResolution() {
        return this.overwriteWithHighestResolution;
    }

    public NativeVideoAspectRatio getPreferredAspectRatio() {
        return this.preferredAspectRatio;
    }

    public NativePreferredFrameRateRange getPreferredFrameRateRange() {
        return this.preferredFrameRateRange;
    }

    public VideoResolution getPreferredResolution() {
        return this.preferredResolution;
    }

    public NativeJsonValue getProperties() {
        return this.properties;
    }

    public boolean getQuadHd() {
        return this.quadHd;
    }

    public NativeRegionStrategy getRegionControlStrategy() {
        return this.regionControlStrategy;
    }

    public NativeShadingMode getShadingMode() {
        return this.shadingMode;
    }

    public NativeTonemapCurve getToneMappingCurve() {
        return this.toneMappingCurve;
    }

    public float getTorchLevel() {
        return this.torchLevel;
    }

    public boolean getUseDepth() {
        return this.useDepth;
    }

    public boolean getUsesCameraApi2Features() {
        return this.usesCameraApi2Features;
    }

    public float getZoomFactor() {
        return this.zoomFactor;
    }

    public float getZoomGestureZoomFactor() {
        return this.zoomGestureZoomFactor;
    }

    public int hashCode() {
        int hashCode = (this.preferredAspectRatio.hashCode() + ((Float.floatToIntBits(this.torchLevel) + ((this.regionControlStrategy.hashCode() + ((this.edgeEnhancementMode.hashCode() + ((this.shadingMode.hashCode() + ((this.noiseReductionMode.hashCode() + ((this.toneMappingCurve.hashCode() + ((((Float.floatToIntBits(this.exposureTargetBias) + ((((((((this.focus.hashCode() + ((Float.floatToIntBits(this.zoomGestureZoomFactor) + ((Float.floatToIntBits(this.zoomFactor) + ((Float.floatToIntBits(this.maxFrameRate) + ((Float.floatToIntBits(this.minFrameRate) + ((this.preferredResolution.hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + this.api) * 31) + (this.usesCameraApi2Features ? 1 : 0)) * 31) + (this.overwriteWithHighestResolution ? 1 : 0)) * 31)) * 31) + (this.colorCorrection ? 1 : 0)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        Size2 size2 = this.arbitraryVideoResolution;
        int hashCode2 = (((((((((((((((hashCode + (size2 == null ? 0 : size2.hashCode())) * 31) + (this.chooseClosestResolutionTo12MPForFourThreeAspectRatio ? 1 : 0)) * 31) + (this.quadHd ? 1 : 0)) * 31) + (this.dynamicResolution ? 1 : 0)) * 31) + this.dynamicResolutionLowFps) * 31) + this.dynamicResolutionMeasuringIntervalMillis) * 31) + (this.dynamicResolutionLogging ? 1 : 0)) * 31) + (this.enableSensorPixelModeMaximumResolution ? 1 : 0)) * 31;
        NativeJsonValue nativeJsonValue = this.properties;
        int hashCode3 = (hashCode2 + (nativeJsonValue == null ? 0 : nativeJsonValue.hashCode())) * 31;
        NativePreferredFrameRateRange nativePreferredFrameRateRange = this.preferredFrameRateRange;
        return ((((hashCode3 + (nativePreferredFrameRateRange != null ? nativePreferredFrameRateRange.hashCode() : 0)) * 31) + this.manualUntilCaptureTimeout) * 31) + (this.useDepth ? 1 : 0);
    }

    public String toString() {
        return "NativeCameraSettings{preferredResolution=" + this.preferredResolution + ",minFrameRate=" + this.minFrameRate + ",maxFrameRate=" + this.maxFrameRate + ",zoomFactor=" + this.zoomFactor + ",zoomGestureZoomFactor=" + this.zoomGestureZoomFactor + ",focus=" + this.focus + ",api=" + this.api + ",usesCameraApi2Features=" + this.usesCameraApi2Features + ",overwriteWithHighestResolution=" + this.overwriteWithHighestResolution + ",exposureTargetBias=" + this.exposureTargetBias + ",colorCorrection=" + this.colorCorrection + ",toneMappingCurve=" + this.toneMappingCurve + ",noiseReductionMode=" + this.noiseReductionMode + ",shadingMode=" + this.shadingMode + ",edgeEnhancementMode=" + this.edgeEnhancementMode + ",regionControlStrategy=" + this.regionControlStrategy + ",torchLevel=" + this.torchLevel + ",preferredAspectRatio=" + this.preferredAspectRatio + ",arbitraryVideoResolution=" + this.arbitraryVideoResolution + ",chooseClosestResolutionTo12MPForFourThreeAspectRatio=" + this.chooseClosestResolutionTo12MPForFourThreeAspectRatio + ",quadHd=" + this.quadHd + ",dynamicResolution=" + this.dynamicResolution + ",dynamicResolutionLowFps=" + this.dynamicResolutionLowFps + ",dynamicResolutionMeasuringIntervalMillis=" + this.dynamicResolutionMeasuringIntervalMillis + ",dynamicResolutionLogging=" + this.dynamicResolutionLogging + ",enableSensorPixelModeMaximumResolution=" + this.enableSensorPixelModeMaximumResolution + ",properties=" + this.properties + ",preferredFrameRateRange=" + this.preferredFrameRateRange + ",manualUntilCaptureTimeout=" + this.manualUntilCaptureTimeout + ",useDepth=" + this.useDepth + "}";
    }
}
