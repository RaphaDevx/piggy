package com.scandit.datacapture.barcode.selection.ui.overlay;

import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionBrushProvider;
import com.scandit.datacapture.core.ui.style.Brush;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class d implements BarcodeSelectionBrushProvider {
    public final WeakReference a;

    public d(BarcodeSelectionBasicOverlay owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionBrushProvider
    public final Brush brushForBarcode(Barcode barcode) {
        BarcodeSelectionBrushProvider trackedBarcodeBrushProvider;
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        BarcodeSelectionBasicOverlay barcodeSelectionBasicOverlay = (BarcodeSelectionBasicOverlay) this.a.get();
        if (barcodeSelectionBasicOverlay == null || (trackedBarcodeBrushProvider = barcodeSelectionBasicOverlay.getTrackedBarcodeBrushProvider()) == null) {
            return null;
        }
        return trackedBarcodeBrushProvider.brushForBarcode(barcode);
    }
}
