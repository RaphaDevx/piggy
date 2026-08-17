package com.scandit.datacapture.barcode.internal.module.count.ui.handlers;

import android.os.Handler;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class v implements com.scandit.datacapture.barcode.internal.module.count.ui.cluster.r {
    public final WeakReference a;

    public v(J owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    public final void a(final int i) {
        Handler handler;
        J j = (J) this.a.get();
        if (j == null || (handler = j.x) == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.handlers.v$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                v.a(v.this, i);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x001f, code lost:
    
        r1 = r1.g;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void a(com.scandit.datacapture.barcode.internal.module.count.ui.handlers.v r1, int r2) {
        /*
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.J r1 = (com.scandit.datacapture.barcode.internal.module.count.ui.handlers.J) r1
            if (r1 == 0) goto L28
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.t r1 = r1.s
            if (r1 == 0) goto L28
            com.scandit.datacapture.barcode.count.ui.view.i r1 = (com.scandit.datacapture.barcode.count.ui.view.i) r1
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView r1 = (com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView) r1
            if (r1 == 0) goto L28
            com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter r1 = com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView.access$getIndicatorPresenter$p(r1)
            if (r1 == 0) goto L28
            r1.a(r2)
        L28:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.count.ui.handlers.v.a(com.scandit.datacapture.barcode.internal.module.count.ui.handlers.v, int):void");
    }
}
