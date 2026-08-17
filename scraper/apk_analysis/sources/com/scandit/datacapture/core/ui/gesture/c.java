package com.scandit.datacapture.core.ui.gesture;

import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c implements FocusGestureListener {
    public final WeakReference a;

    public c(TapToFocus owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0014, code lost:
    
        r1 = r1.b;
     */
    @Override // com.scandit.datacapture.core.ui.gesture.FocusGestureListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onFocusGesture(com.scandit.datacapture.core.ui.gesture.FocusGesture r2, com.scandit.datacapture.core.common.geometry.PointWithUnit r3) {
        /*
            r1 = this;
            java.lang.String r0 = "focusGesture"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "point"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.core.ui.gesture.TapToFocus r1 = (com.scandit.datacapture.core.ui.gesture.TapToFocus) r1
            if (r1 == 0) goto L2e
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.core.ui.gesture.TapToFocus.access$getListeners$p(r1)
            if (r1 == 0) goto L2e
            java.util.Iterator r1 = r1.iterator()
        L1e:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L2e
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.core.ui.gesture.FocusGestureListener r0 = (com.scandit.datacapture.core.ui.gesture.FocusGestureListener) r0
            r0.onFocusGesture(r2, r3)
            goto L1e
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.ui.gesture.c.onFocusGesture(com.scandit.datacapture.core.ui.gesture.FocusGesture, com.scandit.datacapture.core.common.geometry.PointWithUnit):void");
    }
}
