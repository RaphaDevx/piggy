package com.scandit.datacapture.core.internal.module.device.profiles.camera;

import android.hardware.Camera;
import android.util.Range;
import com.scandit.datacapture.core.internal.module.source.NativeCameraApi;
import com.scandit.datacapture.core.internal.module.source.NativeFocusStrategy;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.RegexOption;

/* loaded from: classes2.dex */
public abstract class e implements CameraProfile {
    public final f a = new f(false, true, true, 0.0f, false, false, -1);
    public final a b = new a("continuous-picture", "continuous-video", "auto");

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public void a(Camera.Parameters camParams) {
        Intrinsics.checkNotNullParameter(camParams, "camParams");
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public int b() {
        return Integer.MAX_VALUE;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public Range b(Range[] frameRateRanges, float f) {
        Intrinsics.checkNotNullParameter(frameRateRanges, "frameRateRanges");
        return null;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public boolean c() {
        return false;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public boolean d() {
        return false;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public NativeFocusStrategy e() {
        return b.a();
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public boolean g() {
        return false;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public boolean h() {
        return false;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public boolean i() {
        return b.c();
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public NativeCameraApi j() {
        return b.b();
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public Range a(Range[] frameRateRanges, float f) {
        Intrinsics.checkNotNullParameter(frameRateRanges, "frameRateRanges");
        return null;
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public final boolean a(String model) {
        Intrinsics.checkNotNullParameter(model, "model");
        return new Regex(f(), RegexOption.IGNORE_CASE).matches(model);
    }

    @Override // com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile
    public f a() {
        return this.a;
    }

    public static void a(Camera.Parameters camParams, float f) {
        Intrinsics.checkNotNullParameter(camParams, "camParams");
        float exposureCompensationStep = camParams.getExposureCompensationStep();
        camParams.setExposureCompensation(Math.min(camParams.getMaxExposureCompensation(), Math.max(camParams.getMinExposureCompensation(), Math.round(f / exposureCompensationStep))));
    }
}
