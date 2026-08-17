package com.scandit.datacapture.barcode.count.ui.view;

import com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlay;
import com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayUiListener;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class d implements BarcodeCountBasicOverlayUiListener {
    public final BarcodeCountViewUiListener a;
    public final WeakReference b;

    public d(BarcodeCountView owner, BarcodeCountViewUiListener barcodeCountViewUiListener) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = barcodeCountViewUiListener;
        this.b = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayUiListener
    public final void onExitButtonTapped(BarcodeCountBasicOverlay overlay) {
        BarcodeCountViewUiListener barcodeCountViewUiListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        BarcodeCountView barcodeCountView = (BarcodeCountView) this.b.get();
        if (barcodeCountView == null || (barcodeCountViewUiListener = this.a) == null) {
            return;
        }
        barcodeCountViewUiListener.onExitButtonTapped(barcodeCountView);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayUiListener
    public final void onListButtonTapped(BarcodeCountBasicOverlay overlay) {
        BarcodeCountViewUiListener barcodeCountViewUiListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        BarcodeCountView barcodeCountView = (BarcodeCountView) this.b.get();
        if (barcodeCountView == null || (barcodeCountViewUiListener = this.a) == null) {
            return;
        }
        barcodeCountViewUiListener.onListButtonTapped(barcodeCountView);
    }
}
