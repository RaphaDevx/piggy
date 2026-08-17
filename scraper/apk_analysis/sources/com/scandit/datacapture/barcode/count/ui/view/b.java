package com.scandit.datacapture.barcode.count.ui.view;

import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedBarcode;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListStatus;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountViewHandler;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b extends NativeBarcodeCountViewHandler {
    public final WeakReference a;

    public b(BarcodeCountView owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x000a, code lost:
    
        r0 = r0.e;
     */
    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountViewHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void clear() {
        /*
            r0 = this;
            java.lang.ref.WeakReference r0 = r0.a
            java.lang.Object r0 = r0.get()
            com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView r0 = (com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView) r0
            if (r0 == 0) goto L13
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter r0 = com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView.access$getUiHandler$p(r0)
            if (r0 == 0) goto L13
            r0.f()
        L13:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.count.ui.view.b.clear():void");
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountViewHandler
    public final void hideClassificationPopupForNotInListBarcode(NativeTrackedBarcode nativeTrackedBarcode, NativeBarcodeCountNotInListStatus nativeBarcodeCountNotInListStatus) {
        BarcodeCountView barcodeCountView = (BarcodeCountView) this.a.get();
        if (barcodeCountView != null) {
            BarcodeCountView.access$hideActionPopup(barcodeCountView, nativeTrackedBarcode);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x000a, code lost:
    
        r0 = r0.e;
     */
    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountViewHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setEnableProgressBar(boolean r1) {
        /*
            r0 = this;
            java.lang.ref.WeakReference r0 = r0.a
            java.lang.Object r0 = r0.get()
            com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView r0 = (com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView) r0
            if (r0 == 0) goto L13
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter r0 = com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView.access$getUiHandler$p(r0)
            if (r0 == 0) goto L13
            r0.d(r1)
        L13:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.count.ui.view.b.setEnableProgressBar(boolean):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x000a, code lost:
    
        r0 = r0.e;
     */
    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountViewHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setEnableShutterAnimation(boolean r1) {
        /*
            r0 = this;
            java.lang.ref.WeakReference r0 = r0.a
            java.lang.Object r0 = r0.get()
            com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView r0 = (com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView) r0
            if (r0 == 0) goto L13
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter r0 = com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView.access$getUiHandler$p(r0)
            if (r0 == 0) goto L13
            r0.e(r1)
        L13:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.count.ui.view.b.setEnableShutterAnimation(boolean):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x000a, code lost:
    
        r0 = r0.e;
     */
    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountViewHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setProgressBarState(int r1, int r2) {
        /*
            r0 = this;
            java.lang.ref.WeakReference r0 = r0.a
            java.lang.Object r0 = r0.get()
            com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView r0 = (com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView) r0
            if (r0 == 0) goto L13
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter r0 = com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView.access$getUiHandler$p(r0)
            if (r0 == 0) goto L13
            r0.a(r1, r2)
        L13:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.count.ui.view.b.setProgressBarState(int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:3:0x000a, code lost:
    
        r0 = r0.e;
     */
    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountViewHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void setShutterDimmed(boolean r1) {
        /*
            r0 = this;
            java.lang.ref.WeakReference r0 = r0.a
            java.lang.Object r0 = r0.get()
            com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView r0 = (com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView) r0
            if (r0 == 0) goto L15
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter r0 = com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView.access$getUiHandler$p(r0)
            if (r0 == 0) goto L15
            r1 = r1 ^ 1
            r0.c(r1)
        L15:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.count.ui.view.b.setShutterDimmed(boolean):void");
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountViewHandler
    public final void showClassificationPopupForNotInListBarcode(NativeTrackedBarcode nativeTrackedBarcode, NativeBarcodeCountNotInListStatus nativeBarcodeCountNotInListStatus) {
        BarcodeCountView barcodeCountView = (BarcodeCountView) this.a.get();
        if (barcodeCountView != null) {
            BarcodeCountView.access$showActionPopup(barcodeCountView, nativeTrackedBarcode, nativeBarcodeCountNotInListStatus);
        }
    }
}
