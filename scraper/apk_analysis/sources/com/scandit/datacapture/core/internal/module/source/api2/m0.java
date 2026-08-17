package com.scandit.datacapture.core.internal.module.source.api2;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.RggbChannelVector;
import android.os.Build;
import android.util.Log;
import android.util.Range;
import android.util.Rational;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Rect;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile;
import com.scandit.datacapture.core.internal.module.serialization.NativeEnumDeserializer;
import com.scandit.datacapture.core.internal.module.source.NativeCameraApi;
import com.scandit.datacapture.core.internal.module.source.NativeCameraDelegateSettings;
import com.scandit.datacapture.core.internal.module.source.NativeEdgeEnhancement;
import com.scandit.datacapture.core.internal.module.source.NativeFocusStrategy;
import com.scandit.datacapture.core.internal.module.source.NativeMacroAfMode;
import com.scandit.datacapture.core.internal.module.source.NativeNoiseReduction;
import com.scandit.datacapture.core.internal.module.source.NativeShadingMode;
import com.scandit.datacapture.core.internal.module.source.NativeTonemapCurve;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.internal.sdk.source.api2.CameraCharacteristicsWrapper;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.source.TorchState;
import java.util.Map;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.RangesKt;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

/* loaded from: classes2.dex */
public final class m0 {
    public final N a;
    public final W b;
    public final f0 c;
    public final B0 d;
    public NativeCameraDelegateSettings e;
    public Boolean f;
    public boolean g;
    public boolean h;
    public Integer i;
    public boolean j;
    public int k;
    public final com.scandit.datacapture.core.internal.module.utils.a l;

    public m0(O cameraInfo) {
        Z cameraTorchController = new Z(cameraInfo);
        Intrinsics.checkNotNullParameter(cameraInfo, "cameraInfo");
        Intrinsics.checkNotNullParameter(cameraTorchController, "cameraTorchController");
        this.a = cameraInfo;
        this.b = cameraTorchController;
        this.c = new f0();
        this.d = new B0();
        this.l = new com.scandit.datacapture.core.internal.module.utils.a();
        CameraSettings cameraSettings = cameraInfo.c;
        Object property = cameraSettings != null ? cameraSettings.getProperty("xcoverInitialLensPosition") : null;
        Integer num = property instanceof Integer ? (Integer) property : null;
        int intValue = num != null ? num.intValue() : 0;
        if (intValue > 0) {
            a(AbstractC0555a.a, Integer.valueOf(intValue));
        }
    }

    public final void a(Boolean bool) {
        this.f = bool;
        CaptureRequest.Key FLASH_MODE = CaptureRequest.FLASH_MODE;
        Intrinsics.checkNotNullExpressionValue(FLASH_MODE, "FLASH_MODE");
        a(FLASH_MODE, Integer.valueOf(bool.booleanValue() ? 2 : 0));
    }

    public final void b() {
        CaptureRequest.Key key = CaptureRequest.CONTROL_AF_TRIGGER;
        e0.a(key, "CONTROL_AF_TRIGGER", 0, this, key);
    }

    public final void c() {
        CaptureRequest.Key CONTROL_AE_EXPOSURE_COMPENSATION = CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION;
        Intrinsics.checkNotNullExpressionValue(CONTROL_AE_EXPOSURE_COMPENSATION, "CONTROL_AE_EXPOSURE_COMPENSATION");
        a(CONTROL_AE_EXPOSURE_COMPENSATION, (Object) null);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof m0) {
            m0 m0Var = (m0) obj;
            if (Intrinsics.areEqual(m0Var.l, this.l) && Intrinsics.areEqual(m0Var.f, this.f) && m0Var.g == this.g && m0Var.j == this.j && m0Var.h == this.h && Intrinsics.areEqual(m0Var.i, this.i)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return com.scandit.datacapture.core.internal.module.utils.g.a(com.scandit.datacapture.core.internal.module.utils.g.a(com.scandit.datacapture.core.internal.module.utils.g.a(com.scandit.datacapture.core.internal.module.utils.g.a(com.scandit.datacapture.core.internal.module.utils.g.a(com.scandit.datacapture.core.internal.module.utils.g.a(0, this.l), this.f), Boolean.valueOf(this.g)), Boolean.valueOf(this.j)), Boolean.valueOf(this.h)), this.i);
    }

    public final void c(Rect rect) {
        CaptureRequest.Key CONTROL_AF_REGIONS = CaptureRequest.CONTROL_AF_REGIONS;
        Intrinsics.checkNotNullExpressionValue(CONTROL_AF_REGIONS, "CONTROL_AF_REGIONS");
        CameraCharacteristicsWrapper cameraCharacteristicsWrapper = ((O) this.a).d;
        CameraCharacteristics.Key CONTROL_MAX_REGIONS_AF = CameraCharacteristics.CONTROL_MAX_REGIONS_AF;
        Intrinsics.checkNotNullExpressionValue(CONTROL_MAX_REGIONS_AF, "CONTROL_MAX_REGIONS_AF");
        Integer num = (Integer) cameraCharacteristicsWrapper.get(CONTROL_MAX_REGIONS_AF);
        a(CONTROL_AF_REGIONS, a(rect, num != null ? num.intValue() : 0));
        CaptureRequest.Key key = CaptureRequest.CONTROL_AF_MODE;
        e0.a(key, "CONTROL_AF_MODE", 1, this, key);
        a(rect);
        CaptureRequest.Key key2 = CaptureRequest.CONTROL_AF_TRIGGER;
        e0.a(key2, "CONTROL_AF_TRIGGER", 1, this, key2);
    }

    public final void a(CaptureRequest.Key key, Object obj) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (obj == null) {
            this.l.remove(key);
        } else {
            this.l.put(key, obj);
        }
    }

    public final void a(NativeCameraDelegateSettings delegateSettings) {
        int i;
        float zoomFactor;
        CaptureRequest.Key CONTROL_ZOOM_RATIO;
        CaptureRequest.Key key;
        Intrinsics.checkNotNullParameter(delegateSettings, "delegateSettings");
        this.e = delegateSettings;
        N cameraInfo = this.a;
        NativeEdgeEnhancement edgeEnhancementMode = delegateSettings.getEdgeEnhancementMode();
        Intrinsics.checkNotNullExpressionValue(edgeEnhancementMode, "getEdgeEnhancementMode(...)");
        int i2 = l0.b[edgeEnhancementMode.ordinal()];
        if (i2 == 1) {
            i = 0;
        } else if (i2 == 2) {
            i = 1;
        } else {
            if (i2 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            i = 2;
        }
        CaptureRequest.Key key2 = CaptureRequest.EDGE_MODE;
        e0.a(key2, "EDGE_MODE", i, this, key2);
        f0 f0Var = this.c;
        f0Var.getClass();
        Intrinsics.checkNotNullParameter(this, "requestSettings");
        CaptureRequest.Key key3 = CaptureRequest.CONTROL_AWB_MODE;
        e0.a(key3, "CONTROL_AWB_MODE", 1, this, key3);
        f0Var.b = false;
        if (delegateSettings.getDisablePostProcessing()) {
            e0.a(key2, "EDGE_MODE", 0, this, key2);
            CaptureRequest.Key key4 = CaptureRequest.NOISE_REDUCTION_MODE;
            e0.a(key4, "NOISE_REDUCTION_MODE", 0, this, key4);
            try {
                this.i = 0;
            } catch (Exception unused) {
            }
        }
        if (delegateSettings.getEnableSensorPixelModeMaximumResolution() && Build.VERSION.SDK_INT >= 31) {
            key = CaptureRequest.SENSOR_PIXEL_MODE;
            e0.a(key, "SENSOR_PIXEL_MODE", 1, this, key);
        }
        O o = (O) cameraInfo;
        CameraProfile cameraProfile = o.a;
        int sharpnessStrength = delegateSettings.getSharpnessStrength();
        if (sharpnessStrength < 0) {
            sharpnessStrength = cameraProfile.a().g;
        }
        if (sharpnessStrength >= 0) {
            try {
                this.i = Integer.valueOf(sharpnessStrength);
            } catch (Exception unused2) {
            }
        }
        NativeNoiseReduction noiseReductionMode = delegateSettings.getNoiseReductionMode();
        Intrinsics.checkNotNullExpressionValue(noiseReductionMode, "getNoiseReductionMode(...)");
        int i3 = l0.c[noiseReductionMode.ordinal()];
        if (i3 == 1) {
            CaptureRequest.Key key5 = CaptureRequest.NOISE_REDUCTION_MODE;
            e0.a(key5, "NOISE_REDUCTION_MODE", 0, this, key5);
        } else if (i3 == 2) {
            CaptureRequest.Key key6 = CaptureRequest.NOISE_REDUCTION_MODE;
            e0.a(key6, "NOISE_REDUCTION_MODE", 1, this, key6);
        } else if (i3 == 3) {
            CaptureRequest.Key key7 = CaptureRequest.NOISE_REDUCTION_MODE;
            e0.a(key7, "NOISE_REDUCTION_MODE", 2, this, key7);
        }
        NativeShadingMode shadingMode = delegateSettings.getShadingMode();
        Intrinsics.checkNotNullExpressionValue(shadingMode, "getShadingMode(...)");
        int i4 = l0.d[shadingMode.ordinal()];
        if (i4 == 1) {
            CaptureRequest.Key key8 = CaptureRequest.SHADING_MODE;
            e0.a(key8, "SHADING_MODE", 0, this, key8);
        } else if (i4 == 2) {
            CaptureRequest.Key key9 = CaptureRequest.SHADING_MODE;
            e0.a(key9, "SHADING_MODE", 1, this, key9);
        } else if (i4 == 3) {
            CaptureRequest.Key key10 = CaptureRequest.SHADING_MODE;
            e0.a(key10, "SHADING_MODE", 2, this, key10);
        }
        int sensorSensitivity = delegateSettings.getSensorSensitivity();
        CaptureRequest.Key SENSOR_SENSITIVITY = CaptureRequest.SENSOR_SENSITIVITY;
        Intrinsics.checkNotNullExpressionValue(SENSOR_SENSITIVITY, "SENSOR_SENSITIVITY");
        a(SENSOR_SENSITIVITY, sensorSensitivity > 0 ? Integer.valueOf(sensorSensitivity) : null);
        CameraProfile cameraProfile2 = o.a;
        NativeMacroAfMode macroAutofocusMode = delegateSettings.getMacroAutofocusMode();
        Intrinsics.checkNotNullExpressionValue(macroAutofocusMode, "getMacroAutofocusMode(...)");
        if (macroAutofocusMode == NativeMacroAfMode.MACRO ? cameraProfile2.d() : false) {
            NativeMacroAfMode macroAutofocusMode2 = delegateSettings.getMacroAutofocusMode();
            Intrinsics.checkNotNullExpressionValue(macroAutofocusMode2, "getMacroAutofocusMode(...)");
            float f = o.f() - 1.5f;
            int i5 = l0.e[macroAutofocusMode2.ordinal()];
            if (i5 == 1) {
                CaptureRequest.Key key11 = CaptureRequest.CONTROL_AF_MODE;
                e0.a(key11, "CONTROL_AF_MODE", 2, this, key11);
                CaptureRequest.Key key12 = CaptureRequest.CONTROL_AF_TRIGGER;
                e0.a(key12, "CONTROL_AF_TRIGGER", 1, this, key12);
            } else if (i5 != 2) {
                String message = "Macro Autofocus Mode is not supported: " + macroAutofocusMode2;
                Intrinsics.checkNotNullParameter(message, "message");
                Log.e("sdc-core", message);
            } else {
                CaptureRequest.Key key13 = CaptureRequest.CONTROL_AF_MODE;
                e0.a(key13, "CONTROL_AF_MODE", 0, this, key13);
                CaptureRequest.Key LENS_FOCUS_DISTANCE = CaptureRequest.LENS_FOCUS_DISTANCE;
                Intrinsics.checkNotNullExpressionValue(LENS_FOCUS_DISTANCE, "LENS_FOCUS_DISTANCE");
                a(LENS_FOCUS_DISTANCE, Float.valueOf(f));
            }
        }
        NativeJsonValue properties = delegateSettings.getProperties();
        if (properties != null) {
            if (properties.contains("multiFrameNoiseReduction")) {
                try {
                    a(new CaptureRequest.Key("org.codeaurora.qcamera3.sessionParameters.enableMFNR", int[].class), new int[]{properties.getBoolForKey("multiFrameNoiseReduction")});
                } catch (Exception unused3) {
                    com.scandit.datacapture.core.internal.module.utils.i.a("could not set ".concat("org.codeaurora.qcamera3.sessionParameters.enableMFNR"));
                }
            }
            if (properties.contains("contrastLevel")) {
                try {
                    a(new CaptureRequest.Key("org.codeaurora.qcamera3.contrast.level", int[].class), new int[]{properties.getIntForKey("contrastLevel")});
                } catch (Exception unused4) {
                    com.scandit.datacapture.core.internal.module.utils.i.a("could not set ".concat("org.codeaurora.qcamera3.contrast.level"));
                }
            }
        }
        Intrinsics.checkNotNullParameter(delegateSettings, "delegateSettings");
        Intrinsics.checkNotNullParameter(cameraInfo, "cameraInfo");
        NativeJsonValue properties2 = delegateSettings.getProperties();
        if (properties2 != null ? properties2.getBoolForKeyOrDefault("ultraWideModeEnabled", false) : false) {
            zoomFactor = delegateSettings.getZoomFactor() * ((Number) o.b().getStart()).floatValue();
        } else {
            zoomFactor = delegateSettings.getZoomFactor();
        }
        float floatValue = ((Number) RangesKt.coerceIn(Float.valueOf(zoomFactor), (ClosedRange<Float>) o.b())).floatValue();
        if (Build.VERSION.SDK_INT >= 30) {
            CONTROL_ZOOM_RATIO = CaptureRequest.CONTROL_ZOOM_RATIO;
            Intrinsics.checkNotNullExpressionValue(CONTROL_ZOOM_RATIO, "CONTROL_ZOOM_RATIO");
            a(CONTROL_ZOOM_RATIO, Float.valueOf(zoomFactor));
        } else {
            CameraCharacteristicsWrapper cameraCharacteristicsWrapper = o.d;
            CameraCharacteristics.Key SENSOR_INFO_ACTIVE_ARRAY_SIZE = CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE;
            Intrinsics.checkNotNullExpressionValue(SENSOR_INFO_ACTIVE_ARRAY_SIZE, "SENSOR_INFO_ACTIVE_ARRAY_SIZE");
            android.graphics.Rect rect = (android.graphics.Rect) cameraCharacteristicsWrapper.get(SENSOR_INFO_ACTIVE_ARRAY_SIZE);
            if (rect == null) {
                rect = new android.graphics.Rect();
            }
            int width = (int) (rect.width() / floatValue);
            int height = (int) (rect.height() / floatValue);
            int width2 = (rect.width() - width) / 2;
            int height2 = (rect.height() - height) / 2;
            if (o.a.a().f && rect.left - width2 < 16 && rect.top - height2 < 16 && rect.width() - width < 32 && rect.height() - height < 32) {
                width = rect.width() - 32;
                height = rect.height() - 32;
                width2 = rect.left + 16;
                height2 = rect.top + 16;
            }
            android.graphics.Rect rect2 = new android.graphics.Rect(width2, height2, width + width2, height + height2);
            CaptureRequest.Key SCALER_CROP_REGION = CaptureRequest.SCALER_CROP_REGION;
            Intrinsics.checkNotNullExpressionValue(SCALER_CROP_REGION, "SCALER_CROP_REGION");
            a(SCALER_CROP_REGION, rect2);
        }
        TorchState torchState = delegateSettings.getTorchState();
        Intrinsics.checkNotNullExpressionValue(torchState, "getTorchState(...)");
        int i6 = l0.f[torchState.ordinal()];
        if (i6 == 1) {
            this.g = false;
            a(Boolean.FALSE);
        } else if (i6 == 2) {
            this.g = false;
            a(Boolean.TRUE);
        } else if (i6 == 3) {
            this.g = true;
            if (this.f == null) {
                a(Boolean.FALSE);
            }
        }
        boolean colorCorrection = delegateSettings.getColorCorrection();
        this.j = colorCorrection;
        this.c.a(this, colorCorrection);
        NativeTonemapCurve toneMappingCurve = delegateSettings.getToneMappingCurve();
        Intrinsics.checkNotNullExpressionValue(toneMappingCurve, "getToneMappingCurve(...)");
        this.d.a(this, toneMappingCurve);
        a(delegateSettings, (O) this.a, this.h);
    }

    public final void b(Rect rect) {
        CaptureRequest.Key CONTROL_AF_REGIONS = CaptureRequest.CONTROL_AF_REGIONS;
        Intrinsics.checkNotNullExpressionValue(CONTROL_AF_REGIONS, "CONTROL_AF_REGIONS");
        CameraCharacteristicsWrapper cameraCharacteristicsWrapper = ((O) this.a).d;
        CameraCharacteristics.Key CONTROL_MAX_REGIONS_AF = CameraCharacteristics.CONTROL_MAX_REGIONS_AF;
        Intrinsics.checkNotNullExpressionValue(CONTROL_MAX_REGIONS_AF, "CONTROL_MAX_REGIONS_AF");
        Integer num = (Integer) cameraCharacteristicsWrapper.get(CONTROL_MAX_REGIONS_AF);
        a(CONTROL_AF_REGIONS, a(rect, num != null ? num.intValue() : 0));
        CaptureRequest.Key key = CaptureRequest.CONTROL_AF_MODE;
        e0.a(key, "CONTROL_AF_MODE", 4, this, key);
        a(rect);
        CaptureRequest.Key CONTROL_AF_TRIGGER = CaptureRequest.CONTROL_AF_TRIGGER;
        Intrinsics.checkNotNullExpressionValue(CONTROL_AF_TRIGGER, "CONTROL_AF_TRIGGER");
        a(CONTROL_AF_TRIGGER, (Object) null);
    }

    public final void a() {
        CaptureRequest.Key key = CaptureRequest.CONTROL_AF_MODE;
        e0.a(key, "CONTROL_AF_MODE", 1, this, key);
        CaptureRequest.Key key2 = CaptureRequest.CONTROL_AF_TRIGGER;
        e0.a(key2, "CONTROL_AF_TRIGGER", 2, this, key2);
    }

    public final void a(float f) {
        CaptureRequest.Key key = CaptureRequest.CONTROL_AF_MODE;
        e0.a(key, "CONTROL_AF_MODE", 0, this, key);
        CaptureRequest.Key LENS_FOCUS_DISTANCE = CaptureRequest.LENS_FOCUS_DISTANCE;
        Intrinsics.checkNotNullExpressionValue(LENS_FOCUS_DISTANCE, "LENS_FOCUS_DISTANCE");
        a(LENS_FOCUS_DISTANCE, Float.valueOf(((O) this.a).e() + ((1.0f - f) * (((O) this.a).f() - ((O) this.a).e()))));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(G result) {
        long j;
        NativeFocusStrategy nativeFocusStrategy;
        m0 m0Var = this;
        Intrinsics.checkNotNullParameter(result, "captureResult");
        f0 f0Var = m0Var.c;
        f0Var.getClass();
        Intrinsics.checkNotNullParameter(result, "result");
        f0Var.a = (RggbChannelVector) result.a.get(CaptureResult.COLOR_CORRECTION_GAINS);
        boolean z = m0Var.j;
        m0Var.j = z;
        m0Var.c.a(m0Var, z);
        if (m0Var.g) {
            W w = m0Var.b;
            NativeCameraDelegateSettings nativeCameraDelegateSettings = m0Var.e;
            if (nativeCameraDelegateSettings != null) {
                Intrinsics.checkNotNullParameter(nativeCameraDelegateSettings, "<this>");
                NativeJsonValue properties = nativeCameraDelegateSettings.getProperties();
                String json = properties != null ? properties.getOptionalStringForKeyOrDefault("focusStrategy", null) : null;
                if (json != null) {
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        Intrinsics.checkNotNullParameter(json, "json");
                        NativeFocusStrategy focusStrategyFromJsonString = NativeEnumDeserializer.focusStrategyFromJsonString(json);
                        Intrinsics.checkNotNullExpressionValue(focusStrategyFromJsonString, "focusStrategyFromJsonString(...)");
                        nativeFocusStrategy = Result.m3945constructorimpl(focusStrategyFromJsonString);
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        nativeFocusStrategy = Result.m3945constructorimpl(ResultKt.createFailure(th));
                    }
                    r6 = Result.m3951isFailureimpl(nativeFocusStrategy) ? null : nativeFocusStrategy;
                }
            }
            int i = r6 == null ? -1 : l0.a[r6.ordinal()];
            boolean z2 = true;
            boolean z3 = i == 1 || i == 2;
            Z z4 = (Z) w;
            z4.getClass();
            Intrinsics.checkNotNullParameter(result, "result");
            float longValue = (((Long) result.a.get(CaptureResult.SENSOR_EXPOSURE_TIME)) != null ? r3.longValue() : 0L) * 1.0E-9f;
            Integer num = (Integer) result.a.get(CaptureResult.SENSOR_SENSITIVITY);
            float intValue = num != null ? num.intValue() : 0;
            Float f = (Float) result.a.get(CaptureResult.LENS_FOCUS_DISTANCE);
            float floatValue = f != null ? f.floatValue() : 0.0f;
            float e = ((O) z4.b).e();
            float coerceIn = 1.0f - RangesKt.coerceIn((floatValue - e) / (((O) z4.b).f() - e), 0.0f, 1.0f);
            float f2 = z4.e;
            int i2 = z4.c;
            float f3 = i2 - 1;
            float f4 = (f2 * f3) + longValue;
            float f5 = i2;
            float f6 = f4 / f5;
            z4.e = f6;
            float f7 = ((z4.f * f3) + intValue) / f5;
            z4.f = f7;
            float f8 = 1.0f / (f6 * f7);
            Duration.Companion companion3 = Duration.INSTANCE;
            long duration = DurationKt.toDuration(((Number) z4.d.invoke()).longValue(), DurationUnit.MILLISECONDS);
            if (coerceIn < 0.8f) {
                if (!z4.j) {
                    z4.j = true;
                    z4.k = duration;
                }
            } else {
                z4.j = false;
            }
            if (z3) {
                W.a.getClass();
                j = V.c;
            } else {
                W.a.getClass();
                j = V.b;
            }
            int ordinal = z4.g.ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal != 2) {
                        if (ordinal == 3) {
                            if (f8 > 0.12f) {
                                z4.g = Y.a;
                                com.scandit.datacapture.core.internal.module.utils.i.a("CAM2_AT FD:" + coerceIn + ", EST:" + f8 + " DELAY_TO_ON -> OFF");
                            } else if (Duration.m5289compareToLRDsOJo(duration, z4.i) > 0) {
                                z4.g = Y.b;
                                com.scandit.datacapture.core.internal.module.utils.i.a("CAM2_AT FD:" + coerceIn + ", EST:" + f8 + " DELAY_TO_ON -> ON");
                                m0Var = this;
                                m0Var.a(Boolean.valueOf(z2));
                            }
                        }
                    } else if (f8 < 0.65f) {
                        z4.g = Y.b;
                        com.scandit.datacapture.core.internal.module.utils.i.a("CAM2_AT FD:" + coerceIn + ", EST:" + f8 + " DELAY_TO_OFF -> ON");
                    } else {
                        if (coerceIn < 0.8f) {
                            long m5318minusLRDsOJo = Duration.m5318minusLRDsOJo(duration, z4.k);
                            W.a.getClass();
                            if (Duration.m5289compareToLRDsOJo(m5318minusLRDsOJo, V.e) > 0) {
                                z4.h = Duration.m5319plusLRDsOJo(duration, j);
                            }
                        }
                        if (Duration.m5289compareToLRDsOJo(duration, z4.h) > 0) {
                            z4.g = Y.a;
                            com.scandit.datacapture.core.internal.module.utils.i.a("CAM2_AT FD:" + coerceIn + ", EST:" + f8 + " DELAY_TO_OFF -> OFF");
                        }
                    }
                } else if (f8 > 0.65f) {
                    z4.h = Duration.m5319plusLRDsOJo(duration, j);
                    z4.g = Y.c;
                    com.scandit.datacapture.core.internal.module.utils.i.a("CAM2_AT FD:" + coerceIn + ", EST:" + f8 + " ON -> DELAY_TO_OFF");
                }
                z2 = true;
                m0Var = this;
                m0Var.a(Boolean.valueOf(z2));
            } else if (f8 < 0.12f) {
                W.a.getClass();
                z4.i = Duration.m5319plusLRDsOJo(duration, V.d);
                z4.g = Y.d;
                com.scandit.datacapture.core.internal.module.utils.i.a("CAM2_AT FD:" + coerceIn + ", EST:" + f8 + " OFF -> DELAY_TO_ON");
            }
            z2 = false;
            m0Var = this;
            m0Var.a(Boolean.valueOf(z2));
        }
        NativeCameraDelegateSettings nativeCameraDelegateSettings2 = m0Var.e;
        if (nativeCameraDelegateSettings2 != null) {
            N n = m0Var.a;
            float exposureTargetBias = nativeCameraDelegateSettings2.getExposureTargetBias();
            O o = (O) n;
            CameraCharacteristicsWrapper cameraCharacteristicsWrapper = o.d;
            CameraCharacteristics.Key CONTROL_AE_COMPENSATION_RANGE = CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE;
            Intrinsics.checkNotNullExpressionValue(CONTROL_AE_COMPENSATION_RANGE, "CONTROL_AE_COMPENSATION_RANGE");
            Range range = (Range) cameraCharacteristicsWrapper.get(CONTROL_AE_COMPENSATION_RANGE);
            if (range != null) {
                CameraCharacteristicsWrapper cameraCharacteristicsWrapper2 = o.d;
                CameraCharacteristics.Key CONTROL_AE_COMPENSATION_STEP = CameraCharacteristics.CONTROL_AE_COMPENSATION_STEP;
                Intrinsics.checkNotNullExpressionValue(CONTROL_AE_COMPENSATION_STEP, "CONTROL_AE_COMPENSATION_STEP");
                Rational rational = (Rational) cameraCharacteristicsWrapper2.get(CONTROL_AE_COMPENSATION_STEP);
                if (rational != null) {
                    Integer num2 = (Integer) range.getLower();
                    Integer num3 = (Integer) range.getUpper();
                    if ((num2 == null || num2.intValue() != 0 || num3 == null || num3.intValue() != 0) && !rational.isZero() && rational.isFinite()) {
                        int roundToInt = MathKt.roundToInt(exposureTargetBias / rational.floatValue());
                        Intrinsics.checkNotNull(num2);
                        int intValue2 = num2.intValue();
                        Intrinsics.checkNotNull(num3);
                        int coerceIn2 = RangesKt.coerceIn(roundToInt, intValue2, num3.intValue());
                        CaptureRequest.Key key = CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION;
                        e0.a(key, "CONTROL_AE_EXPOSURE_COMPENSATION", coerceIn2, m0Var, key);
                        return;
                    }
                    CaptureRequest.Key key2 = CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION;
                    e0.a(key2, "CONTROL_AE_EXPOSURE_COMPENSATION", 0, m0Var, key2);
                    return;
                }
                throw new IllegalArgumentException("Exposure compensation step should be non-null on all devices.".toString());
            }
            throw new IllegalArgumentException("Exposure compensation range should be non-null on all devices.".toString());
        }
    }

    public final void a(NativeCameraDelegateSettings nativeCameraDelegateSettings, O o, boolean z) {
        CaptureRequest.Key CONTROL_MODE = CaptureRequest.CONTROL_MODE;
        Intrinsics.checkNotNullExpressionValue(CONTROL_MODE, "CONTROL_MODE");
        a(CONTROL_MODE, (Object) null);
        CaptureRequest.Key CONTROL_AE_MODE = CaptureRequest.CONTROL_AE_MODE;
        Intrinsics.checkNotNullExpressionValue(CONTROL_AE_MODE, "CONTROL_AE_MODE");
        a(CONTROL_AE_MODE, (Object) null);
        CaptureRequest.Key SENSOR_EXPOSURE_TIME = CaptureRequest.SENSOR_EXPOSURE_TIME;
        Intrinsics.checkNotNullExpressionValue(SENSOR_EXPOSURE_TIME, "SENSOR_EXPOSURE_TIME");
        a(SENSOR_EXPOSURE_TIME, (Object) null);
        CaptureRequest.Key SENSOR_FRAME_DURATION = CaptureRequest.SENSOR_FRAME_DURATION;
        Intrinsics.checkNotNullExpressionValue(SENSOR_FRAME_DURATION, "SENSOR_FRAME_DURATION");
        a(SENSOR_FRAME_DURATION, (Object) null);
        CaptureRequest.Key CONTROL_AE_TARGET_FPS_RANGE = CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE;
        Intrinsics.checkNotNullExpressionValue(CONTROL_AE_TARGET_FPS_RANGE, "CONTROL_AE_TARGET_FPS_RANGE");
        a(CONTROL_AE_TARGET_FPS_RANGE, (Object) null);
        if (nativeCameraDelegateSettings.getExposureDuration() <= 0 && nativeCameraDelegateSettings.getFrameDuration() <= 0) {
            if (nativeCameraDelegateSettings.getMinFrameRate() > 0.0f) {
                Range a = com.scandit.datacapture.core.internal.module.source.S.a(o.a, nativeCameraDelegateSettings.getMaxFrameRate(), o.d.getAutoExposureAvailableFrameRateRanges(), z, NativeCameraApi.CAMERA2, nativeCameraDelegateSettings.getPreferredFrameRateRange());
                if (a != null) {
                    Intrinsics.checkNotNullExpressionValue(CONTROL_AE_TARGET_FPS_RANGE, "CONTROL_AE_TARGET_FPS_RANGE");
                    a(CONTROL_AE_TARGET_FPS_RANGE, a);
                    return;
                }
                return;
            }
            return;
        }
        long exposureDuration = nativeCameraDelegateSettings.getExposureDuration();
        long frameDuration = nativeCameraDelegateSettings.getFrameDuration();
        e0.a(CONTROL_MODE, "CONTROL_MODE", 1, this, CONTROL_MODE);
        e0.a(CONTROL_AE_MODE, "CONTROL_AE_MODE", 0, this, CONTROL_AE_MODE);
        if (exposureDuration > 0) {
            Intrinsics.checkNotNullExpressionValue(SENSOR_EXPOSURE_TIME, "SENSOR_EXPOSURE_TIME");
            a(SENSOR_EXPOSURE_TIME, Long.valueOf(exposureDuration));
        }
        if (frameDuration > 0) {
            Intrinsics.checkNotNullExpressionValue(SENSOR_FRAME_DURATION, "SENSOR_FRAME_DURATION");
            a(SENSOR_FRAME_DURATION, Long.valueOf(frameDuration));
        }
    }

    public final void a(Rect rect) {
        NativeCameraDelegateSettings nativeCameraDelegateSettings = this.e;
        if ((nativeCameraDelegateSettings != null ? nativeCameraDelegateSettings.getExposureDuration() : 0L) <= 0) {
            NativeCameraDelegateSettings nativeCameraDelegateSettings2 = this.e;
            if ((nativeCameraDelegateSettings2 != null ? nativeCameraDelegateSettings2.getFrameDuration() : 0L) <= 0) {
                CaptureRequest.Key key = CaptureRequest.CONTROL_AE_MODE;
                e0.a(key, "CONTROL_AE_MODE", 1, this, key);
                CaptureRequest.Key CONTROL_AE_REGIONS = CaptureRequest.CONTROL_AE_REGIONS;
                Intrinsics.checkNotNullExpressionValue(CONTROL_AE_REGIONS, "CONTROL_AE_REGIONS");
                CameraCharacteristicsWrapper cameraCharacteristicsWrapper = ((O) this.a).d;
                CameraCharacteristics.Key CONTROL_MAX_REGIONS_AE = CameraCharacteristics.CONTROL_MAX_REGIONS_AE;
                Intrinsics.checkNotNullExpressionValue(CONTROL_MAX_REGIONS_AE, "CONTROL_MAX_REGIONS_AE");
                Integer num = (Integer) cameraCharacteristicsWrapper.get(CONTROL_MAX_REGIONS_AE);
                a(CONTROL_AE_REGIONS, a(rect, num != null ? num.intValue() : 0));
            }
        }
    }

    public final CaptureRequest a(CaptureRequest.Builder requestBuilder) {
        Intrinsics.checkNotNullParameter(requestBuilder, "requestBuilder");
        Integer num = this.i;
        if (num != null) {
            a(new CaptureRequest.Key("org.codeaurora.qcamera3.sharpness.strength", int[].class), new int[]{num.intValue()});
        }
        for (Map.Entry entry : this.l.entrySet()) {
            requestBuilder.set((CaptureRequest.Key) entry.getKey(), entry.getValue());
        }
        this.k = this.l.hashCode();
        CaptureRequest build = requestBuilder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    public final MeteringRectangle[] a(Rect rect, int i) {
        if (rect == null || i == 0) {
            return null;
        }
        CameraCharacteristicsWrapper cameraCharacteristicsWrapper = ((O) this.a).d;
        CameraCharacteristics.Key SENSOR_INFO_ACTIVE_ARRAY_SIZE = CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE;
        Intrinsics.checkNotNullExpressionValue(SENSOR_INFO_ACTIVE_ARRAY_SIZE, "SENSOR_INFO_ACTIVE_ARRAY_SIZE");
        android.graphics.Rect rect2 = (android.graphics.Rect) cameraCharacteristicsWrapper.get(SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        if (rect2 == null) {
            rect2 = new android.graphics.Rect();
        }
        NativeCameraDelegateSettings nativeCameraDelegateSettings = this.e;
        if (nativeCameraDelegateSettings != null && nativeCameraDelegateSettings.getZoomAffectsMeteringArea()) {
            CaptureRequest.Key key = CaptureRequest.SCALER_CROP_REGION;
            Intrinsics.checkNotNullExpressionValue(key, "SCALER_CROP_REGION");
            Intrinsics.checkNotNullParameter(key, "key");
            Object obj = this.l.get(key);
            android.graphics.Rect rect3 = (android.graphics.Rect) (obj != null ? obj : null);
            if (rect3 == null) {
                rect3 = rect2;
            }
            float width = rect3.width() / rect2.width();
            return new MeteringRectangle[]{a(new Rect(new Point(((rect.getOrigin().getX() - 0.5f) * width) + 0.5f, ((rect.getOrigin().getY() - 0.5f) * width) + 0.5f), new Size2(rect.getSize().getWidth() * width, rect.getSize().getHeight() * width)), rect2)};
        }
        return new MeteringRectangle[]{a(rect, rect2)};
    }

    public static MeteringRectangle a(Rect rect, android.graphics.Rect rect2) {
        int i;
        int i2;
        int width = rect2.width();
        int height = rect2.height();
        float f = width;
        float x = rect.getOrigin().getX() * f;
        float f2 = height;
        float y = rect.getOrigin().getY() * f2;
        float width2 = rect.getSize().getWidth() * f;
        float height2 = rect.getSize().getHeight() * f2;
        int i3 = (int) x;
        int i4 = (int) y;
        int i5 = (int) width2;
        int i6 = (int) height2;
        if (i3 == 0) {
            i5--;
            i = 1;
        } else {
            i = i3;
        }
        if (i4 == 0) {
            i6--;
            i2 = 1;
        } else {
            i2 = i4;
        }
        if (i5 + i == rect2.width()) {
            i5--;
        }
        int i7 = i5;
        if (i6 + i2 == rect2.height()) {
            i6--;
        }
        return new MeteringRectangle(i, i2, i7, i6, 1000);
    }
}
