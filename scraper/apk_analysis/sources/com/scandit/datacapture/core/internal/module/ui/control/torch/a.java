package com.scandit.datacapture.core.internal.module.ui.control.torch;

import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.core.source.FrameSourceListener;
import com.scandit.datacapture.core.source.FrameSourceState;
import com.scandit.datacapture.core.ui.control.k;
import java.util.Set;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements FrameSourceListener {
    public final Set a;
    public final Function1 b;

    public a(Set wantedStates, k action) {
        Intrinsics.checkNotNullParameter(wantedStates, "wantedStates");
        Intrinsics.checkNotNullParameter(action, "action");
        this.a = wantedStates;
        this.b = action;
    }

    @Override // com.scandit.datacapture.core.source.FrameSourceListener
    public final void onFrameOutput(FrameSource frameSource, FrameData frameData) {
        FrameSourceListener.DefaultImpls.onFrameOutput(this, frameSource, frameData);
    }

    @Override // com.scandit.datacapture.core.source.FrameSourceListener
    public final void onObservationStarted(FrameSource frameSource) {
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
        if (this.a.contains(frameSource.getCurrentState())) {
            this.b.invoke(frameSource);
            frameSource.removeListener(this);
        }
    }

    @Override // com.scandit.datacapture.core.source.FrameSourceListener
    public final void onObservationStopped(FrameSource frameSource) {
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
        frameSource.removeListener(this);
    }

    @Override // com.scandit.datacapture.core.source.FrameSourceListener
    public final void onStateChanged(FrameSource frameSource, FrameSourceState newState) {
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
        Intrinsics.checkNotNullParameter(newState, "newState");
        if (this.a.contains(frameSource.getCurrentState())) {
            this.b.invoke(frameSource);
            frameSource.removeListener(this);
        }
    }
}
