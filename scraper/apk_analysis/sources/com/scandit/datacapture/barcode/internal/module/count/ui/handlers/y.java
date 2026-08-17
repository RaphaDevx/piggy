package com.scandit.datacapture.barcode.internal.module.count.ui.handlers;

import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayColorScheme;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.J0;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class y implements com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.b {
    public final WeakReference a;

    public y(J owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.b
    public final void a(boolean z) {
        t tVar;
        BarcodeCountView barcodeCountView;
        J j = (J) this.a.get();
        if (j == null || (tVar = j.s) == null || (barcodeCountView = (BarcodeCountView) ((com.scandit.datacapture.barcode.count.ui.view.i) tVar).a.get()) == null) {
            return;
        }
        BarcodeCountView.access$updateModeFeedback(barcodeCountView);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.b
    public final void b(boolean z) {
        t tVar;
        J j = (J) this.a.get();
        if (j != null) {
            NativeBarcodeCountBasicOverlayColorScheme colorScheme = z ? NativeBarcodeCountBasicOverlayColorScheme.ACCESSIBLE : NativeBarcodeCountBasicOverlayColorScheme.DEFAULT;
            j.p.setColorScheme$scandit_barcode_capture(colorScheme);
            J j2 = (J) this.a.get();
            if (j2 == null || (tVar = j2.s) == null) {
                return;
            }
            Intrinsics.checkNotNullParameter(colorScheme, "colorScheme");
            BarcodeCountView barcodeCountView = (BarcodeCountView) ((com.scandit.datacapture.barcode.count.ui.view.i) tVar).a.get();
            if (barcodeCountView != null) {
                BarcodeCountView.access$onToolbarColorSchemeChanged(barcodeCountView, colorScheme);
            }
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.b
    public final void c(boolean z) {
        J j = (J) this.a.get();
        if (j != null) {
            J0 j0 = (J0) j.a;
            j0.k = true;
            j0.d = z;
            j0.a();
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.b
    public final void d(boolean z) {
        t tVar;
        BarcodeCountView barcodeCountView;
        J j = (J) this.a.get();
        if (j == null || (tVar = j.s) == null || (barcodeCountView = (BarcodeCountView) ((com.scandit.datacapture.barcode.count.ui.view.i) tVar).a.get()) == null) {
            return;
        }
        BarcodeCountView.access$updateModeFeedback(barcodeCountView);
    }
}
