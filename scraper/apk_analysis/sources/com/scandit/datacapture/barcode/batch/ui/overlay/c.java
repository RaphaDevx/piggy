package com.scandit.datacapture.barcode.batch.ui.overlay;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.core.ui.style.Brush;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c implements BarcodeBatchBasicOverlayListener {
    public final WeakReference a;

    public c(BarcodeBatchBasicOverlay owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlayListener
    public final Brush brushForTrackedBarcode(BarcodeBatchBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        BarcodeBatchBasicOverlayListener barcodeBatchBasicOverlayListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeBatchBasicOverlay barcodeBatchBasicOverlay = (BarcodeBatchBasicOverlay) this.a.get();
        if (barcodeBatchBasicOverlay != null && (barcodeBatchBasicOverlayListener = barcodeBatchBasicOverlay.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) != null) {
            return barcodeBatchBasicOverlayListener.brushForTrackedBarcode(overlay, trackedBarcode);
        }
        BarcodeBatchBasicOverlay barcodeBatchBasicOverlay2 = (BarcodeBatchBasicOverlay) this.a.get();
        if (barcodeBatchBasicOverlay2 != null) {
            return barcodeBatchBasicOverlay2.getBrush();
        }
        return null;
    }

    @Override // com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchBasicOverlayListener
    public final void onTrackedBarcodeTapped(BarcodeBatchBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        BarcodeBatchBasicOverlayListener barcodeBatchBasicOverlayListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeBatchBasicOverlay barcodeBatchBasicOverlay = (BarcodeBatchBasicOverlay) this.a.get();
        if (barcodeBatchBasicOverlay == null || (barcodeBatchBasicOverlayListener = barcodeBatchBasicOverlay.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeBatchBasicOverlayListener.onTrackedBarcodeTapped(overlay, trackedBarcode);
    }
}
