package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile;
import com.scandit.datacapture.core.logger.CameraInfoProviderEvent;
import com.scandit.datacapture.core.logger.SdcLogger;
import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.source.CameraSettings;
import java.util.Iterator;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.source.p, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0597p implements InterfaceC0604x {
    public final CameraProfile a;
    public final InterfaceC0601u b;

    public C0597p() {
        CameraProfile cameraProfile = (CameraProfile) com.scandit.datacapture.core.internal.module.device.profiles.camera.d.a.getValue();
        d0 cameraInfoProvider = d0.a;
        Intrinsics.checkNotNullParameter(cameraProfile, "cameraProfile");
        Intrinsics.checkNotNullParameter(cameraInfoProvider, "cameraInfoProvider");
        this.a = cameraProfile;
        this.b = cameraInfoProvider;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.InterfaceC0604x
    public final NativeCameraDelegate a(CameraPosition position, CameraSettings cameraSettings, H frameCallback, I priorityCameraSwitchStateCallback) {
        Object obj;
        Intrinsics.checkNotNullParameter(position, "position");
        Intrinsics.checkNotNullParameter(frameCallback, "frameCallback");
        Intrinsics.checkNotNullParameter(priorityCameraSwitchStateCallback, "priorityCameraSwitchStateCallback");
        CameraPosition cameraPosition = CameraPosition.UNSPECIFIED;
        if (position == cameraPosition) {
            throw new IllegalStateException("Check failed.".toString());
        }
        int i = AbstractC0596o.a[position.ordinal()];
        int i2 = 1;
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                throw new AssertionError("Unsupported CameraPosition " + cameraPosition);
            }
            i2 = 0;
        }
        Iterator it = ((d0) this.b).a().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((c0) ((InterfaceC0600t) obj)).c == i2) {
                break;
            }
        }
        InterfaceC0600t interfaceC0600t = (InterfaceC0600t) obj;
        if (interfaceC0600t == null) {
            SdcLogger.INSTANCE.get().onEvent$scandit_capture_core(new CameraInfoProviderEvent(1, 0, "No suitable CameraInfo found with facing " + i2, null, 10, null));
            Unit unit = Unit.INSTANCE;
        }
        if (interfaceC0600t == null) {
            return null;
        }
        return new C0595n(interfaceC0600t, this.a, frameCallback);
    }
}
