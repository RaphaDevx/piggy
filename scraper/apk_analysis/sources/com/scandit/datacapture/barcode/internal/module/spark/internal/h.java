package com.scandit.datacapture.barcode.internal.module.spark.internal;

import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class h implements j {
    public final WeakReference a;

    public h(SparkScanInternal owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0019, code lost:
    
        r1 = r1.c;
     */
    @Override // com.scandit.datacapture.barcode.internal.module.spark.internal.j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onBarcodeScanned(com.scandit.datacapture.barcode.internal.module.spark.internal.SparkScanInternal r2, com.scandit.datacapture.barcode.spark.capture.SparkScanSession r3, com.scandit.datacapture.core.data.FrameData r4) {
        /*
            r1 = this;
            java.lang.String r0 = "sparkScan"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "session"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "data"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.internal.module.spark.internal.SparkScanInternal r1 = (com.scandit.datacapture.barcode.internal.module.spark.internal.SparkScanInternal) r1
            if (r1 == 0) goto L33
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.barcode.internal.module.spark.internal.SparkScanInternal.a(r1)
            if (r1 == 0) goto L33
            java.util.Iterator r1 = r1.iterator()
        L23:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L33
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.barcode.internal.module.spark.internal.j r0 = (com.scandit.datacapture.barcode.internal.module.spark.internal.j) r0
            r0.onBarcodeScanned(r2, r3, r4)
            goto L23
        L33:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.spark.internal.h.onBarcodeScanned(com.scandit.datacapture.barcode.internal.module.spark.internal.SparkScanInternal, com.scandit.datacapture.barcode.spark.capture.SparkScanSession, com.scandit.datacapture.core.data.FrameData):void");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.internal.j
    public final void onObservationStarted(SparkScanInternal sparkScan) {
        Intrinsics.checkNotNullParameter(sparkScan, "sparkScan");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.internal.j
    public final void onObservationStopped(SparkScanInternal sparkScan) {
        Intrinsics.checkNotNullParameter(sparkScan, "sparkScan");
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0019, code lost:
    
        r1 = r1.c;
     */
    @Override // com.scandit.datacapture.barcode.internal.module.spark.internal.j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onSessionUpdated(com.scandit.datacapture.barcode.internal.module.spark.internal.SparkScanInternal r2, com.scandit.datacapture.barcode.spark.capture.SparkScanSession r3, com.scandit.datacapture.core.data.FrameData r4) {
        /*
            r1 = this;
            java.lang.String r0 = "sparkScan"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "session"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "data"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.internal.module.spark.internal.SparkScanInternal r1 = (com.scandit.datacapture.barcode.internal.module.spark.internal.SparkScanInternal) r1
            if (r1 == 0) goto L33
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.barcode.internal.module.spark.internal.SparkScanInternal.a(r1)
            if (r1 == 0) goto L33
            java.util.Iterator r1 = r1.iterator()
        L23:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L33
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.barcode.internal.module.spark.internal.j r0 = (com.scandit.datacapture.barcode.internal.module.spark.internal.j) r0
            r0.onSessionUpdated(r2, r3, r4)
            goto L23
        L33:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.spark.internal.h.onSessionUpdated(com.scandit.datacapture.barcode.internal.module.spark.internal.SparkScanInternal, com.scandit.datacapture.barcode.spark.capture.SparkScanSession, com.scandit.datacapture.core.data.FrameData):void");
    }
}
