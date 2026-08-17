package com.scandit.datacapture.core.internal.module.source;

import android.util.Range;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class Q extends Lambda implements Function1 {
    public final /* synthetic */ NativeCameraApi a;
    public final /* synthetic */ CameraProfile b;
    public final /* synthetic */ Range[] c;
    public final /* synthetic */ NativePreferredFrameRateRange d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Q(NativeCameraApi nativeCameraApi, CameraProfile cameraProfile, Range[] rangeArr, NativePreferredFrameRateRange nativePreferredFrameRateRange) {
        super(1);
        this.a = nativeCameraApi;
        this.b = cameraProfile;
        this.c = rangeArr;
        this.d = nativePreferredFrameRateRange;
    }

    public final Range a(float f) {
        int i;
        Range a;
        NativeCameraApi nativeCameraApi = this.a;
        Intrinsics.checkNotNullParameter(nativeCameraApi, "<this>");
        int[] iArr = P.a;
        int i2 = iArr[nativeCameraApi.ordinal()];
        if (i2 == 1) {
            i = 1000;
        } else {
            if (i2 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            i = 1;
        }
        float f2 = f * i;
        CameraProfile cameraProfile = this.b;
        NativeCameraApi nativeCameraApi2 = this.a;
        Range[] rangeArr = this.c;
        int i3 = iArr[nativeCameraApi2.ordinal()];
        if (i3 == 1) {
            a = cameraProfile.a(rangeArr, f2);
        } else {
            if (i3 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            a = cameraProfile.b(rangeArr, f2);
        }
        if (a != null) {
            return a;
        }
        NativePreferredFrameRateRange nativePreferredFrameRateRange = this.d;
        Range[] rangeArr2 = this.c;
        int i4 = nativePreferredFrameRateRange == null ? -1 : P.b[nativePreferredFrameRateRange.ordinal()];
        return i4 != 1 ? i4 != 2 ? O.b(rangeArr2, f2) : O.b(rangeArr2, f2) : O.c(rangeArr2, f2);
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return a(((Number) obj).floatValue());
    }
}
