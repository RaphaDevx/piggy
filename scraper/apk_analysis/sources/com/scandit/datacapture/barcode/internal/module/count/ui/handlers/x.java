package com.scandit.datacapture.barcode.internal.module.count.ui.handlers;

import android.os.Handler;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountStatusListener;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.AbstractC0379u0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0357j;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0369p;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0371q;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.L;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.X;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class x extends NativeBarcodeCountStatusListener {
    public final WeakReference a;

    public x(J owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    public static final void a(x this$0, ArrayList arrayList) {
        t tVar;
        BarcodeCountView barcodeCountView;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        J j = (J) this$0.a.get();
        if (j == null || (tVar = j.s) == null || (barcodeCountView = (BarcodeCountView) ((com.scandit.datacapture.barcode.count.ui.view.i) tVar).a.get()) == null) {
            return;
        }
        BarcodeCountView.access$onBarcodeStatusesLoading(barcodeCountView);
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountStatusListener
    public final void onAbortStatusMode() {
        BarcodeCountView barcodeCountView;
        J j = (J) this.a.get();
        if (j != null) {
            t tVar = j.s;
            if (tVar != null && (barcodeCountView = (BarcodeCountView) ((com.scandit.datacapture.barcode.count.ui.view.i) tVar).a.get()) != null) {
                barcodeCountView.b();
            }
            j.p.setStatusModeEnabled$scandit_barcode_capture(false);
        }
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountStatusListener
    public final void onBarcodeStatusesLoading(final ArrayList arrayList) {
        Handler handler;
        J j = (J) this.a.get();
        if (j == null || (handler = j.x) == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.handlers.x$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                x.a(x.this, arrayList);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0021, code lost:
    
        r1 = r1.g;
     */
    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountStatusListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onBarcodeStatusesReady(java.util.ArrayList r2) {
        /*
            r1 = this;
            if (r2 == 0) goto L2a
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.J r1 = (com.scandit.datacapture.barcode.internal.module.count.ui.handlers.J) r1
            if (r1 == 0) goto L2a
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.t r1 = r1.s
            if (r1 == 0) goto L2a
            com.scandit.datacapture.barcode.count.ui.view.i r1 = (com.scandit.datacapture.barcode.count.ui.view.i) r1
            java.lang.String r0 = "statusList"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView r1 = (com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView) r1
            if (r1 == 0) goto L2a
            com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter r1 = com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView.access$getIndicatorPresenter$p(r1)
            if (r1 == 0) goto L2a
            r1.a(r2)
        L2a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.count.ui.handlers.x.onBarcodeStatusesReady(java.util.ArrayList):void");
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountStatusListener
    public final void onHideLoading() {
        J j = (J) this.a.get();
        if (j != null) {
            C0371q c0371q = (C0371q) j.g;
            C0357j onFinished = C0357j.a;
            Intrinsics.checkNotNullParameter(onFinished, "onFinished");
            c0371q.g.a(new C0369p(c0371q, true, onFinished));
            L.a((X) j.m, false, "");
            if (j.A) {
                return;
            }
            AbstractC0379u0.b(j.a, true);
        }
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountStatusListener
    public final void onShowLoading() {
        J j = (J) this.a.get();
        if (j != null) {
            C0371q c0371q = (C0371q) j.g;
            C0357j onFinished = C0357j.a;
            Intrinsics.checkNotNullParameter(onFinished, "onFinished");
            c0371q.g.a(new C0369p(c0371q, false, onFinished));
            L.a((X) j.m, true, j.q);
            AbstractC0379u0.b(j.a, false);
        }
    }
}
