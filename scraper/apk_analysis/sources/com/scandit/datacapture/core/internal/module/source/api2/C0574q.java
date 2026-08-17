package com.scandit.datacapture.core.internal.module.source.api2;

import android.hardware.camera2.CameraManager;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile;
import com.scandit.datacapture.core.internal.module.source.InterfaceC0604x;
import com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate;
import com.scandit.datacapture.core.internal.sdk.source.api2.CameraApi2;
import com.scandit.datacapture.core.internal.sdk.source.api2.CameraApi2Info;
import com.scandit.datacapture.core.internal.sdk.source.api2.CameraApi2InfoProvider;
import com.scandit.datacapture.core.logger.CameraInfoProviderEvent;
import com.scandit.datacapture.core.logger.SdcLogger;
import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.source.CameraSettings;
import java.util.Iterator;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.source.api2.q, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0574q implements InterfaceC0604x {
    public final CameraManager a;
    public final CameraProfile b;
    public final CameraApi2InfoProvider c;

    public C0574q(CameraManager cameraManager) {
        CameraProfile cameraProfile = (CameraProfile) com.scandit.datacapture.core.internal.module.device.profiles.camera.d.a.getValue();
        CameraApi2InfoProvider cameraInfoProvider = CameraApi2.INSTANCE.infoProvider(cameraManager);
        Intrinsics.checkNotNullParameter(cameraManager, "cameraManager");
        Intrinsics.checkNotNullParameter(cameraProfile, "cameraProfile");
        Intrinsics.checkNotNullParameter(cameraInfoProvider, "cameraInfoProvider");
        this.a = cameraManager;
        this.b = cameraProfile;
        this.c = cameraInfoProvider;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.InterfaceC0604x
    public final NativeCameraDelegate a(CameraPosition position, CameraSettings cameraSettings, com.scandit.datacapture.core.internal.module.source.H frameCallback, com.scandit.datacapture.core.internal.module.source.I priorityCameraSwitchStateCallback) {
        CameraApi2Info cameraApi2Info;
        CameraApi2Info cameraApi2Info2;
        CameraApi2Info cameraApi2Info3;
        Intrinsics.checkNotNullParameter(position, "position");
        Intrinsics.checkNotNullParameter(frameCallback, "frameCallback");
        Intrinsics.checkNotNullParameter(priorityCameraSwitchStateCallback, "priorityCameraSwitchStateCallback");
        CameraPosition cameraPosition = CameraPosition.UNSPECIFIED;
        if (position == cameraPosition) {
            throw new IllegalStateException("Check failed.".toString());
        }
        Object property = cameraSettings != null ? cameraSettings.getProperty("cameraId") : null;
        String str = property instanceof String ? (String) property : null;
        if (str != null) {
            Iterable<CameraApi2Info> cameraInfos = this.c.cameraInfos();
            Iterator<CameraApi2Info> it = cameraInfos.iterator();
            while (true) {
                if (!it.hasNext()) {
                    cameraApi2Info3 = null;
                    break;
                }
                cameraApi2Info3 = it.next();
                if (Intrinsics.areEqual(cameraApi2Info3.getId(), str)) {
                    break;
                }
            }
            cameraApi2Info2 = cameraApi2Info3;
            if (cameraApi2Info2 == null) {
                SdcLogger.INSTANCE.get().onEvent$scandit_capture_core(new CameraInfoProviderEvent(2, 0, "No suitable CameraInfo found for id ".concat(str), CollectionsKt.joinToString$default(cameraInfos, ";", null, null, 0, null, null, 62, null), 2, null));
                Unit unit = Unit.INSTANCE;
            }
        } else {
            Intrinsics.checkNotNullParameter(position, "<this>");
            int i = r.a[position.ordinal()];
            int i2 = 1;
            if (i == 1) {
                i2 = 0;
            } else if (i != 2) {
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                throw new AssertionError("Unsupported CameraPosition " + cameraPosition);
            }
            Iterable<CameraApi2Info> cameraInfos2 = this.c.cameraInfos();
            Iterator<CameraApi2Info> it2 = cameraInfos2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    cameraApi2Info = null;
                    break;
                }
                cameraApi2Info = it2.next();
                if (cameraApi2Info.getFacing() == i2) {
                    break;
                }
            }
            cameraApi2Info2 = cameraApi2Info;
            if (cameraApi2Info2 == null) {
                SdcLogger.INSTANCE.get().onEvent$scandit_capture_core(new CameraInfoProviderEvent(2, 0, "No suitable CameraInfo found with facing " + i2, CollectionsKt.joinToString$default(cameraInfos2, ";", null, null, 0, null, null, 62, null), 2, null));
                Unit unit2 = Unit.INSTANCE;
            }
        }
        if (cameraApi2Info2 == null) {
            return null;
        }
        U u = new U(this.a);
        D d = new D();
        O o = new O(this.b, cameraApi2Info2, cameraSettings);
        return new C0573p(o, frameCallback, priorityCameraSwitchStateCallback, u, new z0(), d, new o0(), new h0(o), C0557b.a);
    }
}
