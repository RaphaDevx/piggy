package com.scandit.datacapture.barcode.count.ui.view;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlay;
import com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener;
import com.scandit.datacapture.barcode.data.Cluster;
import com.scandit.datacapture.barcode.tracking.internal.module.data.NativeCluster;
import com.scandit.datacapture.core.ui.style.Brush;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c implements BarcodeCountBasicOverlayListener {
    public final WeakReference a;

    public c(BarcodeCountView owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final Brush brushForAcceptedBarcode(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        BarcodeCountViewListener barcodeCountViewListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeCountView barcodeCountView = (BarcodeCountView) this.a.get();
        if (barcodeCountView == null || (barcodeCountViewListener = barcodeCountView.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return null;
        }
        return barcodeCountViewListener.brushForAcceptedBarcode(barcodeCountView, trackedBarcode);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final Brush brushForRecognizedBarcode(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        BarcodeCountViewListener barcodeCountViewListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeCountView barcodeCountView = (BarcodeCountView) this.a.get();
        if (barcodeCountView == null || (barcodeCountViewListener = barcodeCountView.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return null;
        }
        return barcodeCountViewListener.brushForRecognizedBarcode(barcodeCountView, trackedBarcode);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final Brush brushForRecognizedBarcodeNotInList(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        BarcodeCountViewListener barcodeCountViewListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeCountView barcodeCountView = (BarcodeCountView) this.a.get();
        if (barcodeCountView == null || (barcodeCountViewListener = barcodeCountView.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return null;
        }
        return barcodeCountViewListener.brushForRecognizedBarcodeNotInList(barcodeCountView, trackedBarcode);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final Brush brushForRejectedBarcode(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        BarcodeCountViewListener barcodeCountViewListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeCountView barcodeCountView = (BarcodeCountView) this.a.get();
        if (barcodeCountView == null || (barcodeCountViewListener = barcodeCountView.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return null;
        }
        return barcodeCountViewListener.brushForRejectedBarcode(barcodeCountView, trackedBarcode);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final Brush brushForUnrecognizedBarcode(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        return null;
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final void onAcceptedTrackedBarcodeTapped(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        BarcodeCountViewListener barcodeCountViewListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeCountView barcodeCountView = (BarcodeCountView) this.a.get();
        if (barcodeCountView == null || (barcodeCountViewListener = barcodeCountView.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCountViewListener.onAcceptedBarcodeTapped(barcodeCountView, trackedBarcode);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final void onClusterTapped(BarcodeCountBasicOverlay overlay, NativeCluster cluster) {
        BarcodeCountViewListener barcodeCountViewListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(cluster, "cluster");
        BarcodeCountView barcodeCountView = (BarcodeCountView) this.a.get();
        if (barcodeCountView == null || (barcodeCountViewListener = barcodeCountView.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCountViewListener.onClusterTapped(barcodeCountView, new Cluster(cluster));
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final void onFilteredBarcodeTapped(BarcodeCountBasicOverlay overlay, TrackedBarcode filteredBarcode) {
        BarcodeCountViewListener barcodeCountViewListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(filteredBarcode, "filteredBarcode");
        BarcodeCountView barcodeCountView = (BarcodeCountView) this.a.get();
        if (barcodeCountView == null || (barcodeCountViewListener = barcodeCountView.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCountViewListener.onFilteredBarcodeTapped(barcodeCountView, filteredBarcode);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final void onRecognizedBarcodeNotInListTapped(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        BarcodeCountViewListener barcodeCountViewListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeCountView barcodeCountView = (BarcodeCountView) this.a.get();
        if (barcodeCountView == null || (barcodeCountViewListener = barcodeCountView.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCountViewListener.onRecognizedBarcodeNotInListTapped(barcodeCountView, trackedBarcode);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final void onRecognizedBarcodeTapped(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        BarcodeCountViewListener barcodeCountViewListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeCountView barcodeCountView = (BarcodeCountView) this.a.get();
        if (barcodeCountView == null || (barcodeCountViewListener = barcodeCountView.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCountViewListener.onRecognizedBarcodeTapped(barcodeCountView, trackedBarcode);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final void onRejectedTrackedBarcodeTapped(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        BarcodeCountViewListener barcodeCountViewListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeCountView barcodeCountView = (BarcodeCountView) this.a.get();
        if (barcodeCountView == null || (barcodeCountViewListener = barcodeCountView.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCountViewListener.onRejectedBarcodeTapped(barcodeCountView, trackedBarcode);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final void onUnrecognizedBarcodeTapped(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
    }
}
