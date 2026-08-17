package com.scandit.datacapture.core.source;

import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.internal.module.source.T;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class g implements T {
    public final WeakReference a;

    public g(SequenceFrameSource owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.core.internal.module.source.T
    public final void a(DataCaptureContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // com.scandit.datacapture.core.internal.module.source.T
    public final void b(DataCaptureContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0014, code lost:
    
        r1 = r1.h;
     */
    @Override // com.scandit.datacapture.core.internal.module.source.T
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onFrameOutput(com.scandit.datacapture.core.source.FrameSource r2, com.scandit.datacapture.core.data.FrameData r3) {
        /*
            r1 = this;
            java.lang.String r0 = "frameSource"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "frame"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.core.source.SequenceFrameSource r1 = (com.scandit.datacapture.core.source.SequenceFrameSource) r1
            if (r1 == 0) goto L2e
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.core.source.SequenceFrameSource.access$getListeners$p(r1)
            if (r1 == 0) goto L2e
            java.util.Iterator r1 = r1.iterator()
        L1e:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L2e
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.core.source.FrameSourceListener r0 = (com.scandit.datacapture.core.source.FrameSourceListener) r0
            r0.onFrameOutput(r2, r3)
            goto L1e
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.source.g.onFrameOutput(com.scandit.datacapture.core.source.FrameSource, com.scandit.datacapture.core.data.FrameData):void");
    }

    @Override // com.scandit.datacapture.core.internal.module.source.T
    public final void onObservationStarted(FrameSource frameSource) {
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
    }

    @Override // com.scandit.datacapture.core.internal.module.source.T
    public final void onObservationStopped(FrameSource frameSource) {
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0014, code lost:
    
        r1 = r1.h;
     */
    @Override // com.scandit.datacapture.core.internal.module.source.T
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onStateChanged(com.scandit.datacapture.core.source.FrameSource r2, com.scandit.datacapture.core.source.FrameSourceState r3) {
        /*
            r1 = this;
            java.lang.String r0 = "frameSource"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "newState"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.core.source.SequenceFrameSource r1 = (com.scandit.datacapture.core.source.SequenceFrameSource) r1
            if (r1 == 0) goto L2e
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.core.source.SequenceFrameSource.access$getListeners$p(r1)
            if (r1 == 0) goto L2e
            java.util.Iterator r1 = r1.iterator()
        L1e:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L2e
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.core.source.FrameSourceListener r0 = (com.scandit.datacapture.core.source.FrameSourceListener) r0
            r0.onStateChanged(r2, r3)
            goto L1e
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.source.g.onStateChanged(com.scandit.datacapture.core.source.FrameSource, com.scandit.datacapture.core.source.FrameSourceState):void");
    }
}
