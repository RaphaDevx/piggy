package com.scandit.datacapture.barcode.count.ui.view;

import com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureList;
import com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureListListener;
import com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureListSession;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements BarcodeCountCaptureListListener {
    public final WeakReference a;

    public a(BarcodeCountView owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x000a, code lost:
    
        r0 = r0.l;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
    
        if (r0._shouldDisableModeWhenCaptureListCompleted() == true) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a() {
        /*
            r5 = this;
            java.lang.ref.WeakReference r0 = r5.a
            java.lang.Object r0 = r0.get()
            com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView r0 = (com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView) r0
            if (r0 == 0) goto L20
            java.lang.ref.WeakReference r0 = com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView.access$getMode$p(r0)
            if (r0 == 0) goto L20
            java.lang.Object r0 = r0.get()
            com.scandit.datacapture.barcode.count.capture.BarcodeCount r0 = (com.scandit.datacapture.barcode.count.capture.BarcodeCount) r0
            if (r0 == 0) goto L20
            boolean r0 = r0._shouldDisableModeWhenCaptureListCompleted()
            r1 = 1
            if (r0 != r1) goto L20
            goto L21
        L20:
            r1 = 0
        L21:
            java.lang.ref.WeakReference r5 = r5.a
            java.lang.Object r5 = r5.get()
            com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView r5 = (com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView) r5
            if (r5 == 0) goto L39
            android.os.Handler r0 = com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView.access$getMainHandler$p(r5)
            com.scandit.datacapture.barcode.count.ui.view.a$$ExternalSyntheticLambda0 r2 = new com.scandit.datacapture.barcode.count.ui.view.a$$ExternalSyntheticLambda0
            r2.<init>()
            r3 = 300(0x12c, double:1.48E-321)
            r0.postDelayed(r2, r3)
        L39:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.count.ui.view.a.a():void");
    }

    @Override // com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureListListener
    public final void onCaptureListCompleted(BarcodeCountCaptureList list, BarcodeCountCaptureListSession session) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(session, "session");
        a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x0017, code lost:
    
        r1 = r1.g;
     */
    @Override // com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureListListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onCaptureListSessionUpdated(com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureList r2, com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureListSession r3) {
        /*
            r1 = this;
            java.lang.String r0 = "list"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "session"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            r1.onObservationStarted(r2)
            java.lang.ref.WeakReference r1 = r1.a
            java.lang.Object r1 = r1.get()
            com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView r1 = (com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView) r1
            if (r1 == 0) goto L20
            com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter r1 = com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView.access$getIndicatorPresenter$p(r1)
            if (r1 == 0) goto L20
            r1.a(r3)
        L20:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.count.ui.view.a.onCaptureListSessionUpdated(com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureList, com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureListSession):void");
    }

    @Override // com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureListListener
    public final void onObservationStarted(BarcodeCountCaptureList list) {
        boolean z;
        Intrinsics.checkNotNullParameter(list, "list");
        BarcodeCountView barcodeCountView = (BarcodeCountView) this.a.get();
        if (barcodeCountView != null) {
            z = barcodeCountView.o;
            if (z) {
                return;
            }
            BarcodeCountView.access$setListUiEnabled(barcodeCountView, true, list.getTargetBarcodesQuantity$scandit_barcode_capture());
        }
    }

    @Override // com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureListListener
    public final void onObservationStopped(BarcodeCountCaptureList list) {
        boolean z;
        Intrinsics.checkNotNullParameter(list, "list");
        BarcodeCountView barcodeCountView = (BarcodeCountView) this.a.get();
        if (barcodeCountView != null) {
            z = barcodeCountView.o;
            if (z) {
                BarcodeCountView.access$setListUiEnabled(barcodeCountView, false, list.getTargetBarcodesQuantity$scandit_barcode_capture());
            }
        }
    }

    public static final void a(BarcodeCountView owner, boolean z) {
        Intrinsics.checkNotNullParameter(owner, "$owner");
        BarcodeCountViewListener barcodeCountViewListener = owner.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String();
        if (barcodeCountViewListener != null) {
            barcodeCountViewListener.onCaptureListCompleted(owner);
        }
        if (z) {
            owner.disableScanningAndResetBarcodeIndicators$scandit_barcode_capture();
        }
    }
}
