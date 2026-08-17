package com.scandit.datacapture.barcode.internal.module.pick.capture;

import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class d implements com.scandit.datacapture.barcode.pick.capture.BarcodePickListener {
    public final WeakReference a;

    public d(BarcodePickInternal owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x000f, code lost:
    
        r1 = r1.h;
     */
    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onObservationStarted(com.scandit.datacapture.barcode.pick.capture.BarcodePick r2) {
        /*
            r1 = this;
            java.lang.String r0 = "barcodePick"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal r1 = (com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal) r1
            if (r1 == 0) goto L29
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal.b(r1)
            if (r1 == 0) goto L29
            java.util.Iterator r1 = r1.iterator()
        L19:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L29
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.barcode.pick.capture.BarcodePickListener r0 = (com.scandit.datacapture.barcode.pick.capture.BarcodePickListener) r0
            r0.onObservationStarted(r2)
            goto L19
        L29:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.pick.capture.d.onObservationStarted(com.scandit.datacapture.barcode.pick.capture.BarcodePick):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x000f, code lost:
    
        r1 = r1.h;
     */
    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onObservationStopped(com.scandit.datacapture.barcode.pick.capture.BarcodePick r2) {
        /*
            r1 = this;
            java.lang.String r0 = "barcodePick"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal r1 = (com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal) r1
            if (r1 == 0) goto L29
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal.b(r1)
            if (r1 == 0) goto L29
            java.util.Iterator r1 = r1.iterator()
        L19:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L29
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.barcode.pick.capture.BarcodePickListener r0 = (com.scandit.datacapture.barcode.pick.capture.BarcodePickListener) r0
            r0.onObservationStopped(r2)
            goto L19
        L29:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.pick.capture.d.onObservationStopped(com.scandit.datacapture.barcode.pick.capture.BarcodePick):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0014, code lost:
    
        r1 = r1.h;
     */
    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onSessionUpdated(com.scandit.datacapture.barcode.pick.capture.BarcodePick r2, com.scandit.datacapture.barcode.pick.capture.BarcodePickSession r3) {
        /*
            r1 = this;
            java.lang.String r0 = "barcodePick"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "session"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal r1 = (com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal) r1
            if (r1 == 0) goto L2e
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal.b(r1)
            if (r1 == 0) goto L2e
            java.util.Iterator r1 = r1.iterator()
        L1e:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L2e
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.barcode.pick.capture.BarcodePickListener r0 = (com.scandit.datacapture.barcode.pick.capture.BarcodePickListener) r0
            r0.onSessionUpdated(r2, r3)
            goto L1e
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.pick.capture.d.onSessionUpdated(com.scandit.datacapture.barcode.pick.capture.BarcodePick, com.scandit.datacapture.barcode.pick.capture.BarcodePickSession):void");
    }
}
