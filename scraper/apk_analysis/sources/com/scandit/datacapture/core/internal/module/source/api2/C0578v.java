package com.scandit.datacapture.core.internal.module.source.api2;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import com.scandit.datacapture.core.internal.module.source.DirectByteBufferCache;
import com.scandit.datacapture.core.internal.sdk.data.NativeCameraCaptureParameterKey;
import com.scandit.datacapture.core.internal.sdk.data.NativeCameraCaptureParameters;
import com.scandit.datacapture.core.internal.sdk.source.api2.CameraApi2Info;

/* renamed from: com.scandit.datacapture.core.internal.module.source.api2.v, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0578v {
    public final com.scandit.datacapture.core.internal.module.source.K a;
    public final C0576t b = new C0576t(this);
    public final DirectByteBufferCache c = new DirectByteBufferCache();

    public C0578v(int i) {
        this.a = new com.scandit.datacapture.core.internal.module.source.K(i);
    }

    public static final void a(C0578v c0578v, NativeCameraCaptureParameters nativeCameraCaptureParameters, F f, CameraApi2Info cameraApi2Info) {
        com.scandit.datacapture.core.internal.module.source.M m;
        c0578v.getClass();
        if (f != null) {
            G g = (G) f;
            Float valueOf = ((Long) g.a.get(CaptureResult.SENSOR_EXPOSURE_TIME)) != null ? Float.valueOf((float) (r7.longValue() * 1.0E-9d)) : null;
            if (valueOf != null) {
                nativeCameraCaptureParameters.insertFloat(NativeCameraCaptureParameterKey.EXPOSURE_TIME, valueOf.floatValue());
            }
            Float f2 = (Float) g.a.get(CaptureResult.LENS_APERTURE);
            if (f2 != null) {
                nativeCameraCaptureParameters.insertFloat(NativeCameraCaptureParameterKey.F_NUMBER, f2.floatValue());
            }
            TotalCaptureResult totalCaptureResult = g.a;
            Integer num = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_STATE);
            boolean z = true;
            if (num == null) {
                m = com.scandit.datacapture.core.internal.module.source.M.a;
            } else {
                int intValue = num.intValue();
                if (intValue != 0) {
                    m = intValue != 3 ? intValue != 4 ? intValue != 5 ? com.scandit.datacapture.core.internal.module.source.M.a : com.scandit.datacapture.core.internal.module.source.M.d : com.scandit.datacapture.core.internal.module.source.M.c : com.scandit.datacapture.core.internal.module.source.M.b;
                } else {
                    Integer num2 = (Integer) totalCaptureResult.getRequest().get(CaptureRequest.CONTROL_AF_MODE);
                    Integer num3 = (Integer) totalCaptureResult.get(CaptureResult.LENS_STATE);
                    Float f3 = (Float) totalCaptureResult.get(CaptureResult.LENS_FOCUS_DISTANCE);
                    Float f4 = (Float) totalCaptureResult.getRequest().get(CaptureRequest.LENS_FOCUS_DISTANCE);
                    m = (num2 == null || num3 == null || f3 == null || f4 == null) ? com.scandit.datacapture.core.internal.module.source.M.f : num2.intValue() != 0 ? com.scandit.datacapture.core.internal.module.source.M.a : num3.intValue() == 1 ? com.scandit.datacapture.core.internal.module.source.M.b : Math.abs(f4.floatValue() - f3.floatValue()) < 0.05f ? com.scandit.datacapture.core.internal.module.source.M.e : com.scandit.datacapture.core.internal.module.source.M.f;
                }
            }
            Float f5 = m == com.scandit.datacapture.core.internal.module.source.M.e ? (Float) g.a.getRequest().get(CaptureRequest.LENS_FOCUS_DISTANCE) : (Float) g.a.get(CaptureResult.LENS_FOCUS_DISTANCE);
            if (f5 != null) {
                nativeCameraCaptureParameters.insertFloat(NativeCameraCaptureParameterKey.LENS_FOCUSING_DISTANCE, f5.floatValue());
            }
            float[] b = g.b();
            if (b != null) {
                nativeCameraCaptureParameters.insertFloat(NativeCameraCaptureParameterKey.FOCAL_LENGTH_IN_PIXELS_X, b[0]);
                nativeCameraCaptureParameters.insertFloat(NativeCameraCaptureParameterKey.FOCAL_LENGTH_IN_PIXELS_Y, b[1]);
                nativeCameraCaptureParameters.insertFloat(NativeCameraCaptureParameterKey.PRINCIPAL_POINT_IN_PIXELS_X, b[2]);
                nativeCameraCaptureParameters.insertFloat(NativeCameraCaptureParameterKey.PRINCIPAL_POINT_IN_PIXELS_Y, b[3]);
                nativeCameraCaptureParameters.insertFloat(NativeCameraCaptureParameterKey.AXIS_SKEW, b[4]);
            }
            if (((Integer) g.a.get(CaptureResult.SENSOR_SENSITIVITY)) != null) {
                nativeCameraCaptureParameters.insertFloat(NativeCameraCaptureParameterKey.ISO, r7.intValue());
            }
            if (((Integer) g.a.get(CaptureResult.CONTROL_AE_EXPOSURE_COMPENSATION)) != null) {
                nativeCameraCaptureParameters.insertFloat(NativeCameraCaptureParameterKey.EXPOSURE_BIAS_VALUE, cameraApi2Info.getCompensationStep().floatValue() * r7.intValue());
            }
            Integer num4 = (Integer) g.a.get(CaptureResult.FLASH_STATE);
            if (num4 != null) {
                int intValue2 = num4.intValue();
                if (intValue2 != 4 && intValue2 != 3) {
                    z = false;
                }
                nativeCameraCaptureParameters.insertBool(NativeCameraCaptureParameterKey.FLASH_ENABLED, z);
            }
        }
    }
}
