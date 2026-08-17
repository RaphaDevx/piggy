package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.source.FocusGestureStrategy;
import com.scandit.datacapture.core.source.VideoResolution;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeCameraSettingsDefaults {

    public static final class CppProxy extends NativeCameraSettingsDefaults {
        private final long nativeRef;

        private CppProxy(long j) {
            new AtomicBoolean(false);
            if (j == 0) {
                throw new RuntimeException("nativeRef is zero");
            }
            this.nativeRef = j;
            NativeObjectManager.register(this, j);
        }

        public static native void nativeDestroy(long j);
    }

    public static native int api();

    public static native boolean chooseClosestResolutionTo12MPForFourThreeAspectRatio();

    public static native boolean colorCorrection();

    public static native boolean dynamicResolution();

    public static native boolean dynamicResolutionLogging();

    public static native int dynamicResolutionLowFps();

    public static native int dynamicResolutionMeasuringIntervalMillis();

    public static native NativeEdgeEnhancement edgeEnhancementMode();

    public static native boolean enableSensorPixelModeMaximumResolution();

    public static native float exposureTargetBias();

    public static native FocusGestureStrategy focusGestureStrategy();

    public static native boolean forceAggressiveAutoFocus();

    public static native float lensPosition();

    public static native int manualUntilCaptureTimeout();

    public static native float maxFrameRate();

    public static native float minFrameRate();

    public static native NativeNoiseReduction noiseReductionMode();

    public static native boolean overwriteWithHighestResolution();

    public static native NativeVideoAspectRatio preferredAspectRatio();

    public static native VideoResolution preferredResolution();

    public static native boolean quadHd();

    public static native NativeRegionStrategy regionControlStrategy();

    public static native NativeShadingMode shadingMode();

    public static native boolean shouldPreferSmoothAutoFocus();

    public static native NativeTonemapCurve tonemapCurve();

    public static native float torchLevel();

    public static native boolean useDepth();

    public static native boolean usesCameraApi2Features();

    public static native float zoomFactor();

    public static native float zoomGestureZoomFactor();
}
