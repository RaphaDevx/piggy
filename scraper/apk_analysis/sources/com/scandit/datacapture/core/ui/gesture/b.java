package com.scandit.datacapture.core.ui.gesture;

import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements ZoomGestureListener {
    public final WeakReference a;

    public b(SwipeToZoom owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x000f, code lost:
    
        r1 = r1.b;
     */
    @Override // com.scandit.datacapture.core.ui.gesture.ZoomGestureListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onZoomInGesture(com.scandit.datacapture.core.ui.gesture.ZoomGesture r2) {
        /*
            r1 = this;
            java.lang.String r0 = "zoomGesture"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.core.ui.gesture.SwipeToZoom r1 = (com.scandit.datacapture.core.ui.gesture.SwipeToZoom) r1
            if (r1 == 0) goto L29
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.core.ui.gesture.SwipeToZoom.access$getListeners$p(r1)
            if (r1 == 0) goto L29
            java.util.Iterator r1 = r1.iterator()
        L19:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L29
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.core.ui.gesture.ZoomGestureListener r0 = (com.scandit.datacapture.core.ui.gesture.ZoomGestureListener) r0
            r0.onZoomInGesture(r2)
            goto L19
        L29:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.ui.gesture.b.onZoomInGesture(com.scandit.datacapture.core.ui.gesture.ZoomGesture):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x000f, code lost:
    
        r1 = r1.b;
     */
    @Override // com.scandit.datacapture.core.ui.gesture.ZoomGestureListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onZoomOutGesture(com.scandit.datacapture.core.ui.gesture.ZoomGesture r2) {
        /*
            r1 = this;
            java.lang.String r0 = "zoomGesture"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.core.ui.gesture.SwipeToZoom r1 = (com.scandit.datacapture.core.ui.gesture.SwipeToZoom) r1
            if (r1 == 0) goto L29
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.core.ui.gesture.SwipeToZoom.access$getListeners$p(r1)
            if (r1 == 0) goto L29
            java.util.Iterator r1 = r1.iterator()
        L19:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L29
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.core.ui.gesture.ZoomGestureListener r0 = (com.scandit.datacapture.core.ui.gesture.ZoomGestureListener) r0
            r0.onZoomOutGesture(r2)
            goto L19
        L29:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.ui.gesture.b.onZoomOutGesture(com.scandit.datacapture.core.ui.gesture.ZoomGesture):void");
    }
}
