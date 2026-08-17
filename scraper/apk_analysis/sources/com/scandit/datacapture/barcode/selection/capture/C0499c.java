package com.scandit.datacapture.barcode.selection.capture;

import com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionListener;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.selection.capture.c, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0499c implements BarcodeSelectionListener {
    public final WeakReference a;

    public C0499c(BarcodeSelection owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionListener
    public final void onObservationStarted(BarcodeSelection barcodeSelection) {
        BarcodeSelectionListener.DefaultImpls.onObservationStarted(this, barcodeSelection);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionListener
    public final void onObservationStopped(BarcodeSelection barcodeSelection) {
        BarcodeSelectionListener.DefaultImpls.onObservationStopped(this, barcodeSelection);
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0014, code lost:
    
        r1 = r1.e;
     */
    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onSelectionUpdated(com.scandit.datacapture.barcode.selection.capture.BarcodeSelection r2, com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSession r3, com.scandit.datacapture.core.data.FrameData r4) {
        /*
            r1 = this;
            java.lang.String r0 = "barcodeSelection"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "session"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.selection.capture.BarcodeSelection r1 = (com.scandit.datacapture.barcode.selection.capture.BarcodeSelection) r1
            if (r1 == 0) goto L2e
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.barcode.selection.capture.BarcodeSelection.access$getListeners$p(r1)
            if (r1 == 0) goto L2e
            java.util.Iterator r1 = r1.iterator()
        L1e:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L2e
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionListener r0 = (com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionListener) r0
            r0.onSelectionUpdated(r2, r3, r4)
            goto L1e
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.selection.capture.C0499c.onSelectionUpdated(com.scandit.datacapture.barcode.selection.capture.BarcodeSelection, com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSession, com.scandit.datacapture.core.data.FrameData):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0014, code lost:
    
        r1 = r1.e;
     */
    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onSessionUpdated(com.scandit.datacapture.barcode.selection.capture.BarcodeSelection r2, com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSession r3, com.scandit.datacapture.core.data.FrameData r4) {
        /*
            r1 = this;
            java.lang.String r0 = "barcodeSelection"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "session"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.selection.capture.BarcodeSelection r1 = (com.scandit.datacapture.barcode.selection.capture.BarcodeSelection) r1
            if (r1 == 0) goto L2e
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.barcode.selection.capture.BarcodeSelection.access$getListeners$p(r1)
            if (r1 == 0) goto L2e
            java.util.Iterator r1 = r1.iterator()
        L1e:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L2e
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionListener r0 = (com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionListener) r0
            r0.onSessionUpdated(r2, r3, r4)
            goto L1e
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.selection.capture.C0499c.onSessionUpdated(com.scandit.datacapture.barcode.selection.capture.BarcodeSelection, com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSession, com.scandit.datacapture.core.data.FrameData):void");
    }
}
