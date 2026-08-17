package com.scandit.datacapture.core.source;

import com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameData;
import com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameDataPool;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class h extends NativeCameraFrameDataPool {
    public final WeakReference a;

    public h(SequenceFrameSource owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.source.NativeCameraFrameDataPool
    public final void returnToPool(NativeCameraFrameData frameData) {
        Intrinsics.checkNotNullParameter(frameData, "frameData");
        SequenceFrameSource sequenceFrameSource = (SequenceFrameSource) this.a.get();
        if (sequenceFrameSource != null) {
            SequenceFrameSource.access$returnToPool(sequenceFrameSource, frameData);
        }
    }
}
