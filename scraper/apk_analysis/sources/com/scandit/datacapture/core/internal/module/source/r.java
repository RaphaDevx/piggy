package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameData;
import com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameDataPool;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class r extends NativeCameraFrameDataPool {
    public final WeakReference a;

    public r(C0599s parent) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        this.a = new WeakReference(parent);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameDataPool
    public final void returnToPool(NativeCameraFrameData cameraFrameData) {
        Intrinsics.checkNotNullParameter(cameraFrameData, "frameData");
        C0599s c0599s = (C0599s) this.a.get();
        if (c0599s != null) {
            Intrinsics.checkNotNullParameter(cameraFrameData, "cameraFrame");
            byte[] takeBuffer = cameraFrameData.takeBuffer();
            HandlerC0598q handlerC0598q = c0599s.g;
            handlerC0598q.sendMessage(handlerC0598q.obtainMessage(0, takeBuffer));
            K k = c0599s.h;
            k.getClass();
            Intrinsics.checkNotNullParameter(cameraFrameData, "cameraFrameData");
            if (k.b.size() < k.a) {
                k.b.push(cameraFrameData);
            }
        }
    }
}
