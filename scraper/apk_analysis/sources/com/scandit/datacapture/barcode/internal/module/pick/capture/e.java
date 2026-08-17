package com.scandit.datacapture.barcode.internal.module.pick.capture;

import com.scandit.datacapture.barcode.pick.capture.BarcodePick;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningListener;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class e implements BarcodePickScanningListener {
    public final WeakReference a;

    public e(BarcodePickInternal owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningListener
    public final void onObservationStarted(BarcodePick barcodePick) {
        BarcodePickScanningListener.DefaultImpls.onObservationStarted(this, barcodePick);
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningListener
    public final void onObservationStopped(BarcodePick barcodePick) {
        BarcodePickScanningListener.DefaultImpls.onObservationStopped(this, barcodePick);
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0014, code lost:
    
        r1 = r1.g;
     */
    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onScanningSessionCompleted(com.scandit.datacapture.barcode.pick.capture.BarcodePick r2, com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningSession r3) {
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
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal.c(r1)
            if (r1 == 0) goto L2e
            java.util.Iterator r1 = r1.iterator()
        L1e:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L2e
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningListener r0 = (com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningListener) r0
            r0.onScanningSessionCompleted(r2, r3)
            goto L1e
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.pick.capture.e.onScanningSessionCompleted(com.scandit.datacapture.barcode.pick.capture.BarcodePick, com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningSession):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0014, code lost:
    
        r1 = r1.g;
     */
    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onScanningSessionUpdated(com.scandit.datacapture.barcode.pick.capture.BarcodePick r2, com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningSession r3) {
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
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal.c(r1)
            if (r1 == 0) goto L2e
            java.util.Iterator r1 = r1.iterator()
        L1e:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L2e
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningListener r0 = (com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningListener) r0
            r0.onScanningSessionUpdated(r2, r3)
            goto L1e
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.pick.capture.e.onScanningSessionUpdated(com.scandit.datacapture.barcode.pick.capture.BarcodePick, com.scandit.datacapture.barcode.pick.capture.BarcodePickScanningSession):void");
    }
}
