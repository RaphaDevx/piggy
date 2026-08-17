package com.scandit.datacapture.barcode.count.ui.overlay;

import com.scandit.datacapture.core.ui.DataCaptureViewListener;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.count.ui.overlay.a, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0289a implements DataCaptureViewListener {
    public final WeakReference a;

    public C0289a(BarcodeCountBasicOverlay owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewListener
    public final void onSizeChanged(int i, int i2, int i3) {
        BarcodeCountBasicOverlayCallback barcodeCountBasicOverlayCallback;
        BarcodeCountBasicOverlay barcodeCountBasicOverlay = (BarcodeCountBasicOverlay) this.a.get();
        if (barcodeCountBasicOverlay == null || (barcodeCountBasicOverlayCallback = barcodeCountBasicOverlay.getCom.getcapacitor.PluginMethod.RETURN_CALLBACK java.lang.String()) == null) {
            return;
        }
        barcodeCountBasicOverlayCallback.onDataCaptureViewSizeChanged();
    }
}
