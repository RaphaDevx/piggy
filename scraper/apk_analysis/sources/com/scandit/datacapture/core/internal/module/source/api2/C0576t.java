package com.scandit.datacapture.core.internal.module.source.api2;

import com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameData;
import com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameDataPool;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.source.api2.t, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0576t extends NativeCameraFrameDataPool {
    public final WeakReference a;

    public C0576t(C0578v parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        this.a = new WeakReference(parent);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameDataPool
    public final void returnToPool(NativeCameraFrameData cameraFrameData) {
        Intrinsics.checkNotNullParameter(cameraFrameData, "frameData");
        C0578v c0578v = (C0578v) this.a.get();
        if (c0578v != null) {
            com.scandit.datacapture.core.internal.module.source.K k = c0578v.a;
            k.getClass();
            Intrinsics.checkNotNullParameter(cameraFrameData, "cameraFrameData");
            if (k.b.size() < k.a) {
                k.b.push(cameraFrameData);
            }
        }
    }
}
