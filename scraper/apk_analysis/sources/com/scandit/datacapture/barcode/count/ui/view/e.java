package com.scandit.datacapture.barcode.count.ui.view;

import com.scandit.datacapture.barcode.count.capture.BarcodeCountFeedbackChangeListener;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class e implements BarcodeCountFeedbackChangeListener {
    public final WeakReference a;

    public e(BarcodeCountView owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x000f, code lost:
    
        r1 = r1.e;
     */
    @Override // com.scandit.datacapture.barcode.count.capture.BarcodeCountFeedbackChangeListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void feedbackChanged(com.scandit.datacapture.barcode.count.feedback.BarcodeCountFeedback r2) {
        /*
            r1 = this;
            java.lang.String r0 = "feedback"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView r1 = (com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView) r1
            if (r1 == 0) goto L18
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter r1 = com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView.access$getUiHandler$p(r1)
            if (r1 == 0) goto L18
            r1.a(r2)
        L18:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.count.ui.view.e.feedbackChanged(com.scandit.datacapture.barcode.count.feedback.BarcodeCountFeedback):void");
    }
}
