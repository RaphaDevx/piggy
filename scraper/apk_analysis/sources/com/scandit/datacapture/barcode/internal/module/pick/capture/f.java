package com.scandit.datacapture.barcode.internal.module.pick.capture;

import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class f implements BarcodePickListener {
    public final WeakReference a;

    public f(BarcodePickInternal owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener
    public final void a(BarcodePickInternal mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener
    public final void b(BarcodePickInternal mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0014, code lost:
    
        r1 = r1.e;
     */
    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal r2, java.util.Map r3) {
        /*
            r1 = this;
            java.lang.String r0 = "mode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "requestedData"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal r1 = (com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal) r1
            if (r1 == 0) goto L2e
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal.a(r1)
            if (r1 == 0) goto L2e
            java.util.Iterator r1 = r1.iterator()
        L1e:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L2e
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener r0 = (com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener) r0
            r0.a(r2, r3)
            goto L1e
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.pick.capture.f.a(com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal, java.util.Map):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0019, code lost:
    
        r1 = r1.e;
     */
    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b(com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal r2, com.scandit.datacapture.barcode.internal.module.pick.capture.v r3, com.scandit.datacapture.core.data.FrameData r4) {
        /*
            r1 = this;
            java.lang.String r0 = "mode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "session"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "data"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal r1 = (com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal) r1
            if (r1 == 0) goto L33
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal.a(r1)
            if (r1 == 0) goto L33
            java.util.Iterator r1 = r1.iterator()
        L23:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L33
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener r0 = (com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener) r0
            r0.b(r2, r3, r4)
            goto L23
        L33:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.pick.capture.f.b(com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal, com.scandit.datacapture.barcode.internal.module.pick.capture.v, com.scandit.datacapture.core.data.FrameData):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0014, code lost:
    
        r1 = r1.e;
     */
    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal r2, java.lang.String r3, boolean r4) {
        /*
            r1 = this;
            java.lang.String r0 = "mode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "id"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal r1 = (com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal) r1
            if (r1 == 0) goto L2e
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal.a(r1)
            if (r1 == 0) goto L2e
            java.util.Iterator r1 = r1.iterator()
        L1e:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L2e
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener r0 = (com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener) r0
            r0.a(r2, r3, r4)
            goto L1e
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.pick.capture.f.a(com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal, java.lang.String, boolean):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x000a, code lost:
    
        r1 = r1.e;
     */
    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(com.scandit.datacapture.barcode.batch.data.TrackedObject r2) {
        /*
            r1 = this;
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal r1 = (com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal) r1
            if (r1 == 0) goto L24
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal.a(r1)
            if (r1 == 0) goto L24
            java.util.Iterator r1 = r1.iterator()
        L14:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L24
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener r0 = (com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener) r0
            r0.a(r2)
            goto L14
        L24:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.pick.capture.f.a(com.scandit.datacapture.barcode.batch.data.TrackedObject):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0014, code lost:
    
        r1 = r1.e;
     */
    @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal r2, com.scandit.datacapture.barcode.internal.module.pick.capture.v r3, com.scandit.datacapture.core.data.FrameData r4) {
        /*
            r1 = this;
            java.lang.String r0 = "mode"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "session"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal r1 = (com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal) r1
            if (r1 == 0) goto L2e
            java.util.concurrent.CopyOnWriteArraySet r1 = com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal.a(r1)
            if (r1 == 0) goto L2e
            java.util.Iterator r1 = r1.iterator()
        L1e:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L2e
            java.lang.Object r0 = r1.next()
            com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener r0 = (com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickListener) r0
            r0.a(r2, r3, r4)
            goto L1e
        L2e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.pick.capture.f.a(com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal, com.scandit.datacapture.barcode.internal.module.pick.capture.v, com.scandit.datacapture.core.data.FrameData):void");
    }
}
