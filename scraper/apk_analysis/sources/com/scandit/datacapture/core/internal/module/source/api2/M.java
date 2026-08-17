package com.scandit.datacapture.core.internal.module.source.api2;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Range;
import android.util.Rational;
import android.util.Size;
import android.util.SizeF;
import android.view.SurfaceHolder;
import com.scandit.datacapture.core.internal.sdk.source.api2.CameraCharacteristicsWrapper;
import java.util.List;
import java.util.Set;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class M implements CameraCharacteristicsWrapper {
    public static final Size[] b = {new Size(1920, 1080), new Size(1280, 720)};
    public final CameraCharacteristics a;

    public M(CameraCharacteristics cameraCharacteristics) {
        Intrinsics.checkNotNullParameter(cameraCharacteristics, "cameraCharacteristics");
        this.a = cameraCharacteristics;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraCharacteristicsWrapper
    public final Object get(CameraCharacteristics.Key key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.a.get(key);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraCharacteristicsWrapper
    public final Range[] getAutoExposureAvailableFrameRateRanges() {
        CameraCharacteristics.Key key = CameraCharacteristics.CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES;
        Intrinsics.checkNotNullExpressionValue(key, "CONTROL_AE_AVAILABLE_TARGET_FPS_RANGES");
        Intrinsics.checkNotNullParameter(key, "key");
        Range[] rangeArr = (Range[]) this.a.get(key);
        return rangeArr == null ? new Range[0] : rangeArr;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraCharacteristicsWrapper
    public final List getAvailableCaptureRequestKeys() {
        List<CaptureRequest.Key<?>> availableCaptureRequestKeys = this.a.getAvailableCaptureRequestKeys();
        Intrinsics.checkNotNullExpressionValue(availableCaptureRequestKeys, "getAvailableCaptureRequestKeys(...)");
        return availableCaptureRequestKeys;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraCharacteristicsWrapper
    public final Range getAvailableExposureTimeRange() {
        CameraCharacteristics.Key key = CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE;
        Intrinsics.checkNotNullExpressionValue(key, "SENSOR_INFO_EXPOSURE_TIME_RANGE");
        Intrinsics.checkNotNullParameter(key, "key");
        Range range = (Range) this.a.get(key);
        return range == null ? new Range(0L, 0L) : range;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraCharacteristicsWrapper
    public final Size[] getAvailableHighResYuvResolutions() {
        Object m3945constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            CameraCharacteristics.Key key = CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP;
            Intrinsics.checkNotNullExpressionValue(key, "SCALER_STREAM_CONFIGURATION_MAP");
            Intrinsics.checkNotNullParameter(key, "key");
            StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) this.a.get(key);
            m3945constructorimpl = Result.m3945constructorimpl(streamConfigurationMap != null ? streamConfigurationMap.getHighResolutionOutputSizes(35) : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m3945constructorimpl = Result.m3945constructorimpl(ResultKt.createFailure(th));
        }
        Size[] sizeArr = (Size[]) (Result.m3951isFailureimpl(m3945constructorimpl) ? null : m3945constructorimpl);
        return sizeArr == null ? new Size[0] : sizeArr;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraCharacteristicsWrapper
    public final long getAvailableMaxFrameDuration() {
        CameraCharacteristics.Key key = CameraCharacteristics.SENSOR_INFO_MAX_FRAME_DURATION;
        Intrinsics.checkNotNullExpressionValue(key, "SENSOR_INFO_MAX_FRAME_DURATION");
        Intrinsics.checkNotNullParameter(key, "key");
        Long l = (Long) this.a.get(key);
        if (l != null) {
            return l.longValue();
        }
        return 0L;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraCharacteristicsWrapper
    public List getAvailablePreviewResolution() {
        Object m3945constructorimpl;
        Size[] outputSizes;
        try {
            Result.Companion companion = Result.INSTANCE;
            CameraCharacteristics.Key key = CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP;
            Intrinsics.checkNotNullExpressionValue(key, "SCALER_STREAM_CONFIGURATION_MAP");
            Intrinsics.checkNotNullParameter(key, "key");
            StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) this.a.get(key);
            m3945constructorimpl = Result.m3945constructorimpl((streamConfigurationMap == null || (outputSizes = streamConfigurationMap.getOutputSizes(SurfaceHolder.class)) == null) ? null : ArraysKt.sortedWith(outputSizes, new L()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m3945constructorimpl = Result.m3945constructorimpl(ResultKt.createFailure(th));
        }
        List list = (List) (Result.m3951isFailureimpl(m3945constructorimpl) ? null : m3945constructorimpl);
        return list == null ? ArraysKt.toList(b) : list;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraCharacteristicsWrapper
    public Size[] getAvailableYuvResolutions() {
        Object m3945constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            CameraCharacteristics.Key key = CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP;
            Intrinsics.checkNotNullExpressionValue(key, "SCALER_STREAM_CONFIGURATION_MAP");
            Intrinsics.checkNotNullParameter(key, "key");
            StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) this.a.get(key);
            m3945constructorimpl = Result.m3945constructorimpl(streamConfigurationMap != null ? streamConfigurationMap.getOutputSizes(35) : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m3945constructorimpl = Result.m3945constructorimpl(ResultKt.createFailure(th));
        }
        Size[] sizeArr = (Size[]) (Result.m3951isFailureimpl(m3945constructorimpl) ? null : m3945constructorimpl);
        return sizeArr == null ? b : sizeArr;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraCharacteristicsWrapper
    public final double getCalculatedFieldOfView() {
        float[] fArr = (float[]) this.a.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS);
        if (fArr == null) {
            return 0.0d;
        }
        for (float f : fArr) {
            if (((SizeF) this.a.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE)) != null) {
                return Math.atan(r4.getWidth() / (2 * f));
            }
        }
        return 0.0d;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraCharacteristicsWrapper
    public final Rational getControlAeCompensationStep() {
        CameraCharacteristics.Key key = CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP;
        Intrinsics.checkNotNullExpressionValue(key, "CONTROL_AE_COMPENSATION_STEP");
        Intrinsics.checkNotNullParameter(key, "key");
        Rational rational = (Rational) this.a.get(key);
        if (rational != null) {
            return rational;
        }
        throw new IllegalArgumentException("Exposure compensation step should be non-null on all devices.".toString());
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraCharacteristicsWrapper
    public final Integer getFocusCalibration() {
        CameraCharacteristics.Key key = CameraCharacteristics.LENS_INFO_FOCUS_DISTANCE_CALIBRATION;
        Intrinsics.checkNotNullExpressionValue(key, "LENS_INFO_FOCUS_DISTANCE_CALIBRATION");
        Intrinsics.checkNotNullParameter(key, "key");
        return (Integer) this.a.get(key);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraCharacteristicsWrapper
    public final int getLensFacing() {
        CameraCharacteristics.Key key = CameraCharacteristics.LENS_FACING;
        Intrinsics.checkNotNullExpressionValue(key, "LENS_FACING");
        Intrinsics.checkNotNullParameter(key, "key");
        Integer num = (Integer) this.a.get(key);
        if (num != null) {
            return num.intValue();
        }
        return 1;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraCharacteristicsWrapper
    public final long getOutputMinFrameDuration(Size size) {
        Intrinsics.checkNotNullParameter(size, "size");
        CameraCharacteristics.Key key = CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP;
        Intrinsics.checkNotNullExpressionValue(key, "SCALER_STREAM_CONFIGURATION_MAP");
        Intrinsics.checkNotNullParameter(key, "key");
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) this.a.get(key);
        if (streamConfigurationMap != null) {
            return streamConfigurationMap.getOutputMinFrameDuration(35, size);
        }
        return 0L;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraCharacteristicsWrapper
    public final Range getSensorSensitivityRange() {
        Range range = (Range) this.a.get(CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE);
        return range == null ? new Range(0, 0) : range;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraCharacteristicsWrapper
    public final Integer getSupportedHardwareLevel() {
        CameraCharacteristics.Key key = CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL;
        Intrinsics.checkNotNullExpressionValue(key, "INFO_SUPPORTED_HARDWARE_LEVEL");
        Intrinsics.checkNotNullParameter(key, "key");
        return (Integer) this.a.get(key);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.api2.CameraCharacteristicsWrapper
    public final Set getAvailablePhysicalCameraIds() {
        Set<String> physicalCameraIds = this.a.getPhysicalCameraIds();
        Intrinsics.checkNotNull(physicalCameraIds);
        return physicalCameraIds;
    }
}
