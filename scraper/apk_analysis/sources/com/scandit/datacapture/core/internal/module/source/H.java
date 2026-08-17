package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera;
import com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameData;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.CameraPosition;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class H extends Lambda implements Function1 {
    public final /* synthetic */ J a;
    public final /* synthetic */ CameraPosition b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H(J j, CameraPosition cameraPosition) {
        super(1);
        this.a = j;
        this.b = cameraPosition;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        NativeAndroidCamera a;
        NativeCameraFrameData frameData = (NativeCameraFrameData) obj;
        Intrinsics.checkNotNullParameter(frameData, "frameData");
        D d = this.a.c;
        CameraPosition position = this.b;
        d.getClass();
        Intrinsics.checkNotNullParameter(position, "position");
        Camera camera = (Camera) d.a.get(D.a(position));
        if (camera != null && (a = camera.getA()) != null) {
            a.onFrameOutputAndroid(frameData);
        }
        return Unit.INSTANCE;
    }
}
