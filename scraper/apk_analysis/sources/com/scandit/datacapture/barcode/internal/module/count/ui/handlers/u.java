package com.scandit.datacapture.barcode.internal.module.count.ui.handlers;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.count.capture.BarcodeCount;
import com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountListener;
import com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountSession;
import com.scandit.datacapture.core.data.FrameData;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class u implements InternalBarcodeCountListener {
    public final WeakReference a;

    public u(J owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountListener
    public final void a(InternalBarcodeCountSession session, TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountListener
    public final void b(BarcodeCount mode, InternalBarcodeCountSession session, FrameData data) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        J j = (J) this.a.get();
        if (j != null) {
            j.B = false;
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountListener
    public final void onObservationStarted(BarcodeCount mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountListener
    public final void onObservationStopped(BarcodeCount mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0086, code lost:
    
        r0 = r7.g;
     */
    @Override // com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(com.scandit.datacapture.barcode.count.capture.BarcodeCount r8, com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountSession r9, com.scandit.datacapture.core.data.FrameData r10) {
        /*
            r7 = this;
            java.lang.String r0 = "mode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r8 = "session"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r8)
            java.lang.String r0 = "data"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.ref.WeakReference r7 = r7.a
            java.lang.Object r7 = r7.get()
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.J r7 = (com.scandit.datacapture.barcode.internal.module.count.ui.handlers.J) r7
            if (r7 == 0) goto La3
            boolean r10 = r9.c()
            if (r10 != 0) goto La3
            com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.m0 r10 = r7.f
            int r0 = r9.k()
            r1 = 1
            if (r0 <= 0) goto L2a
            r0 = r1
            goto L2b
        L2a:
            r0 = 0
        L2b:
            com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.t0 r10 = (com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0377t0) r10
            r10.c = r0
            com.scandit.datacapture.barcode.internal.module.ui.b r10 = r10.e
            if (r10 == 0) goto L3b
            com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.p0 r2 = new com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.p0
            r2.<init>(r0)
            com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt.runOnMainThread(r10, r2)
        L3b:
            boolean r10 = r7.u
            if (r10 != 0) goto L57
            com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.A r10 = r7.b
            com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.z r0 = com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0388z.a
            com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.F r10 = (com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.F) r10
            r10.getClass()
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r8)
            java.lang.String r2 = "onFinished"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            int r2 = r9.k()
            r10.a(r2, r0)
        L57:
            com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewSettings r10 = r7.y
            boolean r10 = r10.x()
            if (r10 == 0) goto L73
            boolean r10 = r7.B
            if (r10 != 0) goto L73
            com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.S0 r10 = r7.i
            java.util.Map r0 = r9.d()
            boolean r0 = r0.isEmpty()
            r0 = r0 ^ r1
            com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.Z0 r10 = (com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.Z0) r10
            com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.P0.b(r10, r0)
        L73:
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.t r7 = r7.s
            if (r7 == 0) goto La3
            com.scandit.datacapture.barcode.count.ui.view.i r7 = (com.scandit.datacapture.barcode.count.ui.view.i) r7
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r8)
            java.lang.ref.WeakReference r7 = r7.a
            java.lang.Object r7 = r7.get()
            com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView r7 = (com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView) r7
            if (r7 == 0) goto La3
            com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter r0 = com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView.access$getIndicatorPresenter$p(r7)
            if (r0 == 0) goto La3
            long r1 = r9.b()
            java.util.ArrayList r3 = r9.i()
            java.util.ArrayList r4 = r9.l()
            java.util.ArrayList r5 = r9.h()
            java.util.ArrayList r6 = r9.g()
            r0.a(r1, r3, r4, r5, r6)
        La3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.count.ui.handlers.u.a(com.scandit.datacapture.barcode.count.capture.BarcodeCount, com.scandit.datacapture.barcode.internal.module.count.capture.InternalBarcodeCountSession, com.scandit.datacapture.core.data.FrameData):void");
    }
}
