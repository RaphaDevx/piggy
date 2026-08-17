package com.scandit.datacapture.core.source;

import com.scandit.datacapture.core.internal.module.source.T;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class d implements T {
    public final WeakReference a;

    public d(Camera owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x000f, code lost:
    
        r1 = r1.d;
     */
    @Override // com.scandit.datacapture.core.internal.module.source.T
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(com.scandit.datacapture.core.capture.DataCaptureContext r2) {
        /*
            r1 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.core.source.Camera r1 = (com.scandit.datacapture.core.source.Camera) r1
            if (r1 == 0) goto L29
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.core.source.Camera.access$getInternalListeners$p(r1)
            if (r1 == 0) goto L29
            java.util.Iterator r1 = r1.iterator()
        L19:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L29
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.core.internal.module.source.T r0 = (com.scandit.datacapture.core.internal.module.source.T) r0
            r0.a(r2)
            goto L19
        L29:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.source.d.a(com.scandit.datacapture.core.capture.DataCaptureContext):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x000f, code lost:
    
        r1 = r1.d;
     */
    @Override // com.scandit.datacapture.core.internal.module.source.T
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(com.scandit.datacapture.core.capture.DataCaptureContext r2) {
        /*
            r1 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.core.source.Camera r1 = (com.scandit.datacapture.core.source.Camera) r1
            if (r1 == 0) goto L29
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.core.source.Camera.access$getInternalListeners$p(r1)
            if (r1 == 0) goto L29
            java.util.Iterator r1 = r1.iterator()
        L19:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L29
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.core.internal.module.source.T r0 = (com.scandit.datacapture.core.internal.module.source.T) r0
            r0.b(r2)
            goto L19
        L29:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.source.d.b(com.scandit.datacapture.core.capture.DataCaptureContext):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0038, code lost:
    
        r2 = r2.e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0014, code lost:
    
        r0 = r0.d;
     */
    @Override // com.scandit.datacapture.core.internal.module.source.T
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onFrameOutput(com.scandit.datacapture.core.source.FrameSource r3, com.scandit.datacapture.core.data.FrameData r4) {
        /*
            r2 = this;
            java.lang.String r0 = "frameSource"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "frame"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.ref.WeakReference r0 = r2.a
            java.lang.Object r0 = r0.get()
            com.scandit.datacapture.core.source.Camera r0 = (com.scandit.datacapture.core.source.Camera) r0
            if (r0 == 0) goto L2e
            java.util.concurrent.CopyOnWriteArraySet r0 = com.scandit.datacapture.core.source.Camera.access$getInternalListeners$p(r0)
            if (r0 == 0) goto L2e
            java.util.Iterator r0 = r0.iterator()
        L1e:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L2e
            java.lang.Object r1 = r0.next()
            com.scandit.datacapture.core.internal.module.source.T r1 = (com.scandit.datacapture.core.internal.module.source.T) r1
            r1.onFrameOutput(r3, r4)
            goto L1e
        L2e:
            java.lang.ref.WeakReference r2 = r2.a
            java.lang.Object r2 = r2.get()
            com.scandit.datacapture.core.source.Camera r2 = (com.scandit.datacapture.core.source.Camera) r2
            if (r2 == 0) goto L52
            java.util.concurrent.CopyOnWriteArraySet r2 = com.scandit.datacapture.core.source.Camera.access$getListeners$p(r2)
            if (r2 == 0) goto L52
            java.util.Iterator r2 = r2.iterator()
        L42:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L52
            java.lang.Object r0 = r2.next()
            com.scandit.datacapture.core.source.FrameSourceListener r0 = (com.scandit.datacapture.core.source.FrameSourceListener) r0
            r0.onFrameOutput(r3, r4)
            goto L42
        L52:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.source.d.onFrameOutput(com.scandit.datacapture.core.source.FrameSource, com.scandit.datacapture.core.data.FrameData):void");
    }

    @Override // com.scandit.datacapture.core.internal.module.source.T
    public final void onObservationStarted(FrameSource frameSource) {
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
    }

    @Override // com.scandit.datacapture.core.internal.module.source.T
    public final void onObservationStopped(FrameSource frameSource) {
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0038, code lost:
    
        r2 = r2.e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0014, code lost:
    
        r0 = r0.d;
     */
    @Override // com.scandit.datacapture.core.internal.module.source.T
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onStateChanged(com.scandit.datacapture.core.source.FrameSource r3, com.scandit.datacapture.core.source.FrameSourceState r4) {
        /*
            r2 = this;
            java.lang.String r0 = "frameSource"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "newState"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.ref.WeakReference r0 = r2.a
            java.lang.Object r0 = r0.get()
            com.scandit.datacapture.core.source.Camera r0 = (com.scandit.datacapture.core.source.Camera) r0
            if (r0 == 0) goto L2e
            java.util.concurrent.CopyOnWriteArraySet r0 = com.scandit.datacapture.core.source.Camera.access$getInternalListeners$p(r0)
            if (r0 == 0) goto L2e
            java.util.Iterator r0 = r0.iterator()
        L1e:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L2e
            java.lang.Object r1 = r0.next()
            com.scandit.datacapture.core.internal.module.source.T r1 = (com.scandit.datacapture.core.internal.module.source.T) r1
            r1.onStateChanged(r3, r4)
            goto L1e
        L2e:
            java.lang.ref.WeakReference r2 = r2.a
            java.lang.Object r2 = r2.get()
            com.scandit.datacapture.core.source.Camera r2 = (com.scandit.datacapture.core.source.Camera) r2
            if (r2 == 0) goto L52
            java.util.concurrent.CopyOnWriteArraySet r2 = com.scandit.datacapture.core.source.Camera.access$getListeners$p(r2)
            if (r2 == 0) goto L52
            java.util.Iterator r2 = r2.iterator()
        L42:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L52
            java.lang.Object r0 = r2.next()
            com.scandit.datacapture.core.source.FrameSourceListener r0 = (com.scandit.datacapture.core.source.FrameSourceListener) r0
            r0.onStateChanged(r3, r4)
            goto L42
        L52:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.source.d.onStateChanged(com.scandit.datacapture.core.source.FrameSource, com.scandit.datacapture.core.source.FrameSourceState):void");
    }
}
