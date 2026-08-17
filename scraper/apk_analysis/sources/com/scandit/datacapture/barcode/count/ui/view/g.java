package com.scandit.datacapture.barcode.count.ui.view;

import com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayCallback;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class g implements BarcodeCountBasicOverlayCallback {
    public final WeakReference a;

    public g(BarcodeCountView owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayCallback
    public final void cleanUpOverlayViews() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003e, code lost:
    
        r2 = r9.g;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x000a, code lost:
    
        r0 = r0.l;
     */
    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onDataCaptureViewSizeChanged() {
        /*
            r9 = this;
            java.lang.ref.WeakReference r0 = r9.a
            java.lang.Object r0 = r0.get()
            com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView r0 = (com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView) r0
            if (r0 == 0) goto L5b
            java.lang.ref.WeakReference r0 = com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView.access$getMode$p(r0)
            if (r0 == 0) goto L5b
            java.lang.Object r0 = r0.get()
            com.scandit.datacapture.barcode.count.capture.BarcodeCount r0 = (com.scandit.datacapture.barcode.count.capture.BarcodeCount) r0
            if (r0 != 0) goto L19
            goto L5b
        L19:
            com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount r0 = r0.getA()
            com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSession r0 = r0.getSession()
            if (r0 != 0) goto L24
            return
        L24:
            com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountSession r1 = new com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountSession
            com.scandit.datacapture.barcode.count.ui.view.f r2 = new com.scandit.datacapture.barcode.count.ui.view.f
            r2.<init>(r0)
            r1.<init>(r2)
            boolean r0 = r1.c()
            if (r0 != 0) goto L5b
            java.lang.ref.WeakReference r9 = r9.a
            java.lang.Object r9 = r9.get()
            com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView r9 = (com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView) r9
            if (r9 == 0) goto L5b
            com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter r2 = com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView.access$getIndicatorPresenter$p(r9)
            if (r2 == 0) goto L5b
            long r3 = r1.b()
            java.util.ArrayList r5 = r1.i()
            java.util.ArrayList r6 = r1.l()
            java.util.ArrayList r7 = r1.h()
            java.util.ArrayList r8 = r1.g()
            r2.a(r3, r5, r6, r7, r8)
        L5b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.count.ui.view.g.onDataCaptureViewSizeChanged():void");
    }
}
