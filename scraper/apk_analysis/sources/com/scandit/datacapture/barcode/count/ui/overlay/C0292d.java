package com.scandit.datacapture.barcode.count.ui.overlay;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener;
import com.scandit.datacapture.barcode.tracking.internal.module.data.NativeCluster;
import com.scandit.datacapture.core.internal.sdk.extensions.BrushExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.ui.style.NativeBrush;
import com.scandit.datacapture.core.ui.style.Brush;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.count.ui.overlay.d, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0292d implements BarcodeCountBasicOverlayListener, BarcodeCountBasicOverlayUiListener {
    public final WeakReference a;

    public C0292d(BarcodeCountBasicOverlay owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final Brush brushForAcceptedBarcode(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        Brush brushForAcceptedBarcode;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeCountBasicOverlay barcodeCountBasicOverlay = (BarcodeCountBasicOverlay) this.a.get();
        if (barcodeCountBasicOverlay != null) {
            BarcodeCountBasicOverlayListener barcodeCountBasicOverlayListener = barcodeCountBasicOverlay.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String();
            if (barcodeCountBasicOverlayListener != null && (brushForAcceptedBarcode = barcodeCountBasicOverlayListener.brushForAcceptedBarcode(overlay, trackedBarcode)) != null) {
                return brushForAcceptedBarcode;
            }
            NativeBrush acceptedBrush = overlay.getA().getAcceptedBrush();
            if (acceptedBrush != null) {
                Intrinsics.checkNotNull(acceptedBrush);
                return BrushExtensionsKt.of(Brush.INSTANCE, acceptedBrush);
            }
        }
        return null;
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final Brush brushForRecognizedBarcode(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        Brush brushForRecognizedBarcode;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeCountBasicOverlay barcodeCountBasicOverlay = (BarcodeCountBasicOverlay) this.a.get();
        if (barcodeCountBasicOverlay != null) {
            BarcodeCountBasicOverlayListener barcodeCountBasicOverlayListener = barcodeCountBasicOverlay.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String();
            if (barcodeCountBasicOverlayListener != null && (brushForRecognizedBarcode = barcodeCountBasicOverlayListener.brushForRecognizedBarcode(overlay, trackedBarcode)) != null) {
                return brushForRecognizedBarcode;
            }
            NativeBrush scannedBrush = overlay.getA().getScannedBrush();
            if (scannedBrush != null) {
                Intrinsics.checkNotNull(scannedBrush);
                return BrushExtensionsKt.of(Brush.INSTANCE, scannedBrush);
            }
        }
        return null;
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final Brush brushForRecognizedBarcodeNotInList(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        Brush brushForRecognizedBarcodeNotInList;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeCountBasicOverlay barcodeCountBasicOverlay = (BarcodeCountBasicOverlay) this.a.get();
        if (barcodeCountBasicOverlay != null) {
            BarcodeCountBasicOverlayListener barcodeCountBasicOverlayListener = barcodeCountBasicOverlay.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String();
            if (barcodeCountBasicOverlayListener != null && (brushForRecognizedBarcodeNotInList = barcodeCountBasicOverlayListener.brushForRecognizedBarcodeNotInList(overlay, trackedBarcode)) != null) {
                return brushForRecognizedBarcodeNotInList;
            }
            NativeBrush notInListBrush = overlay.getA().getNotInListBrush();
            if (notInListBrush != null) {
                Intrinsics.checkNotNull(notInListBrush);
                return BrushExtensionsKt.of(Brush.INSTANCE, notInListBrush);
            }
        }
        return null;
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final Brush brushForRejectedBarcode(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        Brush brushForRejectedBarcode;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeCountBasicOverlay barcodeCountBasicOverlay = (BarcodeCountBasicOverlay) this.a.get();
        if (barcodeCountBasicOverlay != null) {
            BarcodeCountBasicOverlayListener barcodeCountBasicOverlayListener = barcodeCountBasicOverlay.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String();
            if (barcodeCountBasicOverlayListener != null && (brushForRejectedBarcode = barcodeCountBasicOverlayListener.brushForRejectedBarcode(overlay, trackedBarcode)) != null) {
                return brushForRejectedBarcode;
            }
            NativeBrush rejectedBrush = overlay.getA().getRejectedBrush();
            if (rejectedBrush != null) {
                Intrinsics.checkNotNull(rejectedBrush);
                return BrushExtensionsKt.of(Brush.INSTANCE, rejectedBrush);
            }
        }
        return null;
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final Brush brushForUnrecognizedBarcode(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        Brush brushForUnrecognizedBarcode;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeCountBasicOverlay barcodeCountBasicOverlay = (BarcodeCountBasicOverlay) this.a.get();
        if (barcodeCountBasicOverlay != null) {
            BarcodeCountBasicOverlayListener barcodeCountBasicOverlayListener = barcodeCountBasicOverlay.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String();
            if (barcodeCountBasicOverlayListener != null && (brushForUnrecognizedBarcode = barcodeCountBasicOverlayListener.brushForUnrecognizedBarcode(overlay, trackedBarcode)) != null) {
                return brushForUnrecognizedBarcode;
            }
            NativeBrush unscannedBrush = overlay.getA().getUnscannedBrush();
            if (unscannedBrush != null) {
                Intrinsics.checkNotNull(unscannedBrush);
                return BrushExtensionsKt.of(Brush.INSTANCE, unscannedBrush);
            }
        }
        return null;
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final void onAcceptedTrackedBarcodeTapped(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        BarcodeCountBasicOverlayListener barcodeCountBasicOverlayListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeCountBasicOverlay barcodeCountBasicOverlay = (BarcodeCountBasicOverlay) this.a.get();
        if (barcodeCountBasicOverlay == null || (barcodeCountBasicOverlayListener = barcodeCountBasicOverlay.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCountBasicOverlayListener.onAcceptedTrackedBarcodeTapped(overlay, trackedBarcode);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final void onClusterTapped(BarcodeCountBasicOverlay barcodeCountBasicOverlay, NativeCluster nativeCluster) {
        BarcodeCountBasicOverlayListener.DefaultImpls.onClusterTapped(this, barcodeCountBasicOverlay, nativeCluster);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayUiListener
    public final void onExitButtonTapped(BarcodeCountBasicOverlay overlay) {
        BarcodeCountBasicOverlayUiListener uiListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        BarcodeCountBasicOverlay barcodeCountBasicOverlay = (BarcodeCountBasicOverlay) this.a.get();
        if (barcodeCountBasicOverlay == null || (uiListener = barcodeCountBasicOverlay.getUiListener()) == null) {
            return;
        }
        uiListener.onExitButtonTapped(barcodeCountBasicOverlay);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final void onFilteredBarcodeTapped(BarcodeCountBasicOverlay overlay, TrackedBarcode filteredBarcode) {
        BarcodeCountBasicOverlayListener barcodeCountBasicOverlayListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(filteredBarcode, "filteredBarcode");
        BarcodeCountBasicOverlay barcodeCountBasicOverlay = (BarcodeCountBasicOverlay) this.a.get();
        if (barcodeCountBasicOverlay == null || (barcodeCountBasicOverlayListener = barcodeCountBasicOverlay.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCountBasicOverlayListener.onFilteredBarcodeTapped(overlay, filteredBarcode);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayUiListener
    public final void onListButtonTapped(BarcodeCountBasicOverlay overlay) {
        BarcodeCountBasicOverlayUiListener uiListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        BarcodeCountBasicOverlay barcodeCountBasicOverlay = (BarcodeCountBasicOverlay) this.a.get();
        if (barcodeCountBasicOverlay == null || (uiListener = barcodeCountBasicOverlay.getUiListener()) == null) {
            return;
        }
        uiListener.onListButtonTapped(barcodeCountBasicOverlay);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final void onRecognizedBarcodeNotInListTapped(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        BarcodeCountBasicOverlayListener barcodeCountBasicOverlayListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeCountBasicOverlay barcodeCountBasicOverlay = (BarcodeCountBasicOverlay) this.a.get();
        if (barcodeCountBasicOverlay == null || (barcodeCountBasicOverlayListener = barcodeCountBasicOverlay.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCountBasicOverlayListener.onRecognizedBarcodeNotInListTapped(overlay, trackedBarcode);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final void onRecognizedBarcodeTapped(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        BarcodeCountBasicOverlayListener barcodeCountBasicOverlayListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeCountBasicOverlay barcodeCountBasicOverlay = (BarcodeCountBasicOverlay) this.a.get();
        if (barcodeCountBasicOverlay == null || (barcodeCountBasicOverlayListener = barcodeCountBasicOverlay.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCountBasicOverlayListener.onRecognizedBarcodeTapped(overlay, trackedBarcode);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final void onRejectedTrackedBarcodeTapped(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        BarcodeCountBasicOverlayListener barcodeCountBasicOverlayListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeCountBasicOverlay barcodeCountBasicOverlay = (BarcodeCountBasicOverlay) this.a.get();
        if (barcodeCountBasicOverlay == null || (barcodeCountBasicOverlayListener = barcodeCountBasicOverlay.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCountBasicOverlayListener.onRejectedTrackedBarcodeTapped(overlay, trackedBarcode);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlayListener
    public final void onUnrecognizedBarcodeTapped(BarcodeCountBasicOverlay overlay, TrackedBarcode trackedBarcode) {
        BarcodeCountBasicOverlayListener barcodeCountBasicOverlayListener;
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        BarcodeCountBasicOverlay barcodeCountBasicOverlay = (BarcodeCountBasicOverlay) this.a.get();
        if (barcodeCountBasicOverlay == null || (barcodeCountBasicOverlayListener = barcodeCountBasicOverlay.getCom.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs.LISTENER java.lang.String()) == null) {
            return;
        }
        barcodeCountBasicOverlayListener.onUnrecognizedBarcodeTapped(overlay, trackedBarcode);
    }
}
