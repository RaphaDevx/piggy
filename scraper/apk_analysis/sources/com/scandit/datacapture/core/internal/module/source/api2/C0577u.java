package com.scandit.datacapture.core.internal.module.source.api2;

import com.scandit.datacapture.core.internal.sdk.data.NativeCameraCaptureParameterKey;
import com.scandit.datacapture.core.internal.sdk.data.NativeCameraCaptureParameters;
import com.scandit.datacapture.core.internal.sdk.source.api2.CameraApi2Info;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.core.internal.module.source.api2.u, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0577u extends Lambda implements Function1 {
    public final /* synthetic */ C0578v a;
    public final /* synthetic */ F b;
    public final /* synthetic */ CameraApi2Info c;
    public final /* synthetic */ Long d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0577u(C0578v c0578v, F f, CameraApi2Info cameraApi2Info, Long l) {
        super(1);
        this.a = c0578v;
        this.b = f;
        this.c = cameraApi2Info;
        this.d = l;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        NativeCameraCaptureParameters updateFrameData = (NativeCameraCaptureParameters) obj;
        Intrinsics.checkNotNullParameter(updateFrameData, "$this$updateFrameData");
        C0578v.a(this.a, updateFrameData, this.b, this.c);
        CameraApi2Info cameraInfo = this.c;
        Intrinsics.checkNotNullParameter(updateFrameData, "<this>");
        Intrinsics.checkNotNullParameter(cameraInfo, "cameraInfo");
        NativeCameraCaptureParameterKey nativeCameraCaptureParameterKey = NativeCameraCaptureParameterKey.POSITION;
        int facing = cameraInfo.getFacing();
        updateFrameData.insertInt64(nativeCameraCaptureParameterKey, facing != 0 ? facing != 1 ? -1L : 1L : 2L);
        updateFrameData.insertInt64(NativeCameraCaptureParameterKey.LENS_FOCUS_CALIBRATION, cameraInfo.getFocusCalibration());
        Long l = this.d;
        Intrinsics.checkNotNullParameter(updateFrameData, "<this>");
        updateFrameData.insertInt64(NativeCameraCaptureParameterKey.DELAY_BETWEEN_YUV_AND_PREVIEW, l != null ? l.longValue() : 0L);
        return Unit.INSTANCE;
    }
}
