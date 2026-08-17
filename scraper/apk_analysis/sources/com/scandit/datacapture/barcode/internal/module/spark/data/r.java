package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.core.source.FrameSourceListener;
import com.scandit.datacapture.core.source.FrameSourceState;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class r implements FrameSourceListener {
    public final /* synthetic */ Function0 a;

    public r(J j) {
        this.a = j;
    }

    @Override // com.scandit.datacapture.core.source.FrameSourceListener
    public final void onFrameOutput(FrameSource frameSource, FrameData frame) {
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
        Intrinsics.checkNotNullParameter(frame, "frame");
        if (frameSource.getCurrentState() == FrameSourceState.ON) {
            frameSource.removeListener(this);
            this.a.invoke();
        }
    }

    @Override // com.scandit.datacapture.core.source.FrameSourceListener
    public final void onObservationStarted(FrameSource frameSource) {
        FrameSourceListener.DefaultImpls.onObservationStarted(this, frameSource);
    }

    @Override // com.scandit.datacapture.core.source.FrameSourceListener
    public final void onObservationStopped(FrameSource frameSource) {
        FrameSourceListener.DefaultImpls.onObservationStopped(this, frameSource);
    }

    @Override // com.scandit.datacapture.core.source.FrameSourceListener
    public final void onStateChanged(FrameSource frameSource, FrameSourceState frameSourceState) {
        FrameSourceListener.DefaultImpls.onStateChanged(this, frameSource, frameSourceState);
    }
}
