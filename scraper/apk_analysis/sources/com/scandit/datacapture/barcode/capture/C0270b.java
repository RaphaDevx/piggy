package com.scandit.datacapture.barcode.capture;

import com.scandit.datacapture.barcode.capture.BarcodeCaptureListener;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.capture.b, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0270b implements BarcodeCaptureListener {
    public final WeakReference a;

    public C0270b(BarcodeCapture owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0019, code lost:
    
        r1 = r1.f;
     */
    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onBarcodeScanned(com.scandit.datacapture.barcode.capture.BarcodeCapture r2, com.scandit.datacapture.barcode.capture.BarcodeCaptureSession r3, com.scandit.datacapture.core.data.FrameData r4) {
        /*
            r1 = this;
            java.lang.String r0 = "barcodeCapture"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "session"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "data"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.capture.BarcodeCapture r1 = (com.scandit.datacapture.barcode.capture.BarcodeCapture) r1
            if (r1 == 0) goto L33
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.barcode.capture.BarcodeCapture.access$getListeners$p(r1)
            if (r1 == 0) goto L33
            java.util.Iterator r1 = r1.iterator()
        L23:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L33
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.barcode.capture.BarcodeCaptureListener r0 = (com.scandit.datacapture.barcode.capture.BarcodeCaptureListener) r0
            r0.onBarcodeScanned(r2, r3, r4)
            goto L23
        L33:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.capture.C0270b.onBarcodeScanned(com.scandit.datacapture.barcode.capture.BarcodeCapture, com.scandit.datacapture.barcode.capture.BarcodeCaptureSession, com.scandit.datacapture.core.data.FrameData):void");
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureListener
    public final void onObservationStarted(BarcodeCapture barcodeCapture) {
        BarcodeCaptureListener.DefaultImpls.onObservationStarted(this, barcodeCapture);
    }

    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureListener
    public final void onObservationStopped(BarcodeCapture barcodeCapture) {
        BarcodeCaptureListener.DefaultImpls.onObservationStopped(this, barcodeCapture);
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0019, code lost:
    
        r1 = r1.f;
     */
    @Override // com.scandit.datacapture.barcode.capture.BarcodeCaptureListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onSessionUpdated(com.scandit.datacapture.barcode.capture.BarcodeCapture r2, com.scandit.datacapture.barcode.capture.BarcodeCaptureSession r3, com.scandit.datacapture.core.data.FrameData r4) {
        /*
            r1 = this;
            java.lang.String r0 = "barcodeCapture"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "session"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "data"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.capture.BarcodeCapture r1 = (com.scandit.datacapture.barcode.capture.BarcodeCapture) r1
            if (r1 == 0) goto L33
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.barcode.capture.BarcodeCapture.access$getListeners$p(r1)
            if (r1 == 0) goto L33
            java.util.Iterator r1 = r1.iterator()
        L23:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L33
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.barcode.capture.BarcodeCaptureListener r0 = (com.scandit.datacapture.barcode.capture.BarcodeCaptureListener) r0
            r0.onSessionUpdated(r2, r3, r4)
            goto L23
        L33:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.capture.C0270b.onSessionUpdated(com.scandit.datacapture.barcode.capture.BarcodeCapture, com.scandit.datacapture.barcode.capture.BarcodeCaptureSession, com.scandit.datacapture.core.data.FrameData):void");
    }
}
