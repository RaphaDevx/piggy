package com.scandit.datacapture.barcode.internal.module.ar.capture;

import com.scandit.datacapture.barcode.ar.capture.BarcodeAr;
import com.scandit.datacapture.barcode.ar.capture.BarcodeArListener;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements BarcodeArListener {
    public final WeakReference a;

    public b(BarcodeArInternal owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.ar.capture.BarcodeArListener
    public final void onObservationStarted(BarcodeAr barcodeAr) {
        BarcodeArListener.DefaultImpls.onObservationStarted(this, barcodeAr);
    }

    @Override // com.scandit.datacapture.barcode.ar.capture.BarcodeArListener
    public final void onObservationStopped(BarcodeAr barcodeAr) {
        BarcodeArListener.DefaultImpls.onObservationStopped(this, barcodeAr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0019, code lost:
    
        r1 = r1.g;
     */
    @Override // com.scandit.datacapture.barcode.ar.capture.BarcodeArListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onSessionUpdated(com.scandit.datacapture.barcode.ar.capture.BarcodeAr r2, com.scandit.datacapture.barcode.ar.capture.BarcodeArSession r3, com.scandit.datacapture.core.data.FrameData r4) {
        /*
            r1 = this;
            java.lang.String r0 = "barcodeAr"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "session"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "frameData"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.internal.module.ar.capture.BarcodeArInternal r1 = (com.scandit.datacapture.barcode.internal.module.ar.capture.BarcodeArInternal) r1
            if (r1 == 0) goto L33
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.barcode.internal.module.ar.capture.BarcodeArInternal.a(r1)
            if (r1 == 0) goto L33
            java.util.Iterator r1 = r1.iterator()
        L23:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L33
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.barcode.ar.capture.BarcodeArListener r0 = (com.scandit.datacapture.barcode.ar.capture.BarcodeArListener) r0
            r0.onSessionUpdated(r2, r3, r4)
            goto L23
        L33:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.ar.capture.b.onSessionUpdated(com.scandit.datacapture.barcode.ar.capture.BarcodeAr, com.scandit.datacapture.barcode.ar.capture.BarcodeArSession, com.scandit.datacapture.core.data.FrameData):void");
    }
}
