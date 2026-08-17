package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile;
import com.scandit.datacapture.core.internal.sdk.source.CameraFactory;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.CameraApiUtilsKt;
import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.source.CameraSettings;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class J implements CameraFactory {
    public static final F d = new F();
    public final CameraProfile a;
    public final Function1 b;
    public final D c;

    public J(CameraProfile cameraProfile) {
        C0605y cameraDelegateFactory = new C0605y();
        Intrinsics.checkNotNullParameter(cameraProfile, "cameraProfile");
        Intrinsics.checkNotNullParameter(cameraDelegateFactory, "cameraDelegateFactory");
        this.a = cameraProfile;
        this.b = cameraDelegateFactory;
        this.c = new D();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0090  */
    @Override // com.scandit.datacapture.core.internal.sdk.source.CameraFactory
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized com.scandit.datacapture.core.source.Camera getCamera(com.scandit.datacapture.core.source.CameraPosition r10, com.scandit.datacapture.core.source.CameraSettings r11) {
        /*
            r9 = this;
            monitor-enter(r9)
            java.lang.String r0 = "position"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)     // Catch: java.lang.Throwable -> L9e
            if (r11 == 0) goto Ld
            com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile r0 = r9.a     // Catch: java.lang.Throwable -> L9e
            r11.addDefaultPropertiesFromProfile$scandit_capture_core(r0)     // Catch: java.lang.Throwable -> L9e
        Ld:
            int[] r0 = com.scandit.datacapture.core.internal.module.source.G.a     // Catch: java.lang.Throwable -> L9e
            int r1 = r10.ordinal()     // Catch: java.lang.Throwable -> L9e
            r0 = r0[r1]     // Catch: java.lang.Throwable -> L9e
            r1 = 1
            r2 = 0
            if (r0 == r1) goto L26
            r1 = 2
            if (r0 == r1) goto L26
            r10 = 3
            if (r0 != r10) goto L20
            goto L4e
        L20:
            kotlin.NoWhenBranchMatchedException r10 = new kotlin.NoWhenBranchMatchedException     // Catch: java.lang.Throwable -> L9e
            r10.<init>()     // Catch: java.lang.Throwable -> L9e
            throw r10     // Catch: java.lang.Throwable -> L9e
        L26:
            com.scandit.datacapture.core.internal.module.source.D r0 = r9.c     // Catch: java.lang.Throwable -> L9e
            r0.getClass()     // Catch: java.lang.Throwable -> L9e
            java.lang.String r1 = "position"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r1)     // Catch: java.lang.Throwable -> L9e
            java.util.LinkedHashMap r0 = r0.a     // Catch: java.lang.Throwable -> L9e
            com.scandit.datacapture.core.internal.module.source.B r1 = com.scandit.datacapture.core.internal.module.source.D.a(r10)     // Catch: java.lang.Throwable -> L9e
            java.lang.Object r0 = r0.get(r1)     // Catch: java.lang.Throwable -> L9e
            com.scandit.datacapture.core.source.Camera r0 = (com.scandit.datacapture.core.source.Camera) r0     // Catch: java.lang.Throwable -> L9e
            com.scandit.datacapture.core.internal.module.source.NativeCameraApi r1 = r9.getNativeCameraApi(r11)     // Catch: java.lang.Throwable -> L9e
            if (r0 == 0) goto L48
            com.scandit.datacapture.core.internal.module.source.NativeCameraApi r3 = r0.getApi$scandit_capture_core()     // Catch: java.lang.Throwable -> L9e
            if (r3 == r1) goto L78
        L48:
            com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate r1 = r9.getCameraDelegate(r10, r1, r11)     // Catch: java.lang.Throwable -> L9e
            if (r1 != 0) goto L50
        L4e:
            r10 = r2
            goto L8e
        L50:
            if (r0 != 0) goto L71
            com.scandit.datacapture.core.internal.module.source.D r0 = r9.c     // Catch: java.lang.Throwable -> L9e
            com.scandit.datacapture.core.source.Camera$Companion r3 = com.scandit.datacapture.core.source.Camera.INSTANCE     // Catch: java.lang.Throwable -> L9e
            com.scandit.datacapture.core.source.Camera r1 = r3.create$scandit_capture_core(r1)     // Catch: java.lang.Throwable -> L9e
            r0.getClass()     // Catch: java.lang.Throwable -> L9e
            java.lang.String r3 = "position"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r3)     // Catch: java.lang.Throwable -> L9e
            java.lang.String r3 = "camera"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r3)     // Catch: java.lang.Throwable -> L9e
            java.util.LinkedHashMap r0 = r0.a     // Catch: java.lang.Throwable -> L9e
            com.scandit.datacapture.core.internal.module.source.B r3 = com.scandit.datacapture.core.internal.module.source.D.a(r10)     // Catch: java.lang.Throwable -> L9e
            r0.put(r3, r1)     // Catch: java.lang.Throwable -> L9e
            goto L78
        L71:
            com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera r0 = r0.getA()     // Catch: java.lang.Throwable -> L9e
            r0.setDelegateAsync(r1)     // Catch: java.lang.Throwable -> L9e
        L78:
            com.scandit.datacapture.core.internal.module.source.D r0 = r9.c     // Catch: java.lang.Throwable -> L9e
            r0.getClass()     // Catch: java.lang.Throwable -> L9e
            java.lang.String r1 = "position"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r1)     // Catch: java.lang.Throwable -> L9e
            java.util.LinkedHashMap r0 = r0.a     // Catch: java.lang.Throwable -> L9e
            com.scandit.datacapture.core.internal.module.source.B r10 = com.scandit.datacapture.core.internal.module.source.D.a(r10)     // Catch: java.lang.Throwable -> L9e
            java.lang.Object r10 = r0.get(r10)     // Catch: java.lang.Throwable -> L9e
            com.scandit.datacapture.core.source.Camera r10 = (com.scandit.datacapture.core.source.Camera) r10     // Catch: java.lang.Throwable -> L9e
        L8e:
            if (r10 == 0) goto L9c
            if (r11 == 0) goto L9b
            r7 = 4
            r8 = 0
            r6 = 0
            r3 = r10
            r4 = r11
            r5 = r9
            com.scandit.datacapture.core.source.Camera.applySettingsInternal$scandit_capture_core$default(r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L9e
        L9b:
            r2 = r10
        L9c:
            monitor-exit(r9)
            return r2
        L9e:
            r10 = move-exception
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L9e
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.module.source.J.getCamera(com.scandit.datacapture.core.source.CameraPosition, com.scandit.datacapture.core.source.CameraSettings):com.scandit.datacapture.core.source.Camera");
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.CameraFactory
    public final NativeCameraDelegate getCameraDelegate(CameraPosition position, NativeCameraApi api, CameraSettings cameraSettings) {
        Intrinsics.checkNotNullParameter(position, "position");
        Intrinsics.checkNotNullParameter(api, "api");
        H h = new H(this, position);
        I i = new I(this, position);
        InterfaceC0604x interfaceC0604x = (InterfaceC0604x) this.b.invoke(api);
        if (interfaceC0604x != null) {
            return interfaceC0604x.a(position, cameraSettings, h, i);
        }
        return null;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.CameraFactory
    public final Camera getDefaultCamera(CameraSettings cameraSettings) {
        Camera camera = getCamera(CameraPosition.WORLD_FACING, cameraSettings);
        return camera == null ? getCamera(CameraPosition.USER_FACING, cameraSettings) : camera;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.CameraFactory
    public final NativeCameraApi getNativeCameraApi(CameraSettings cameraSettings) {
        if (cameraSettings != null) {
            NativeCameraApi cameraApi = CameraApiUtilsKt.getCameraApi(cameraSettings);
            if (cameraApi != null) {
                return cameraApi;
            }
            if (cameraSettings.isUsingApi2Features$scandit_capture_core() && this.a.i()) {
                return NativeCameraApi.CAMERA2;
            }
        }
        return this.a.j();
    }
}
