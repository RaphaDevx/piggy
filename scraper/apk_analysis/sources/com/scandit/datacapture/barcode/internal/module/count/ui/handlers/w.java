package com.scandit.datacapture.barcode.internal.module.count.ui.handlers;

import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountGuidanceHandler;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.L;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.O;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.V;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.X;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.o1;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.p1;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.s1;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.v1;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.y1;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class w extends NativeBarcodeCountGuidanceHandler {
    public final WeakReference a;

    public w(J owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountGuidanceHandler
    public final void clear() {
        t tVar;
        BarcodeCountView barcodeCountView;
        J j = (J) this.a.get();
        if (j != null) {
            com.scandit.datacapture.barcode.internal.module.ui.n nVar = ((y1) j.l).b;
            if (nVar != null) {
                nVar.setVisibility(8);
                nVar.d = null;
            }
            L.b((X) j.m, false, "");
            L.a((X) j.m, false, "");
            X x = (X) j.m;
            if (x.a) {
                x.d.a(new V(x, 0.0f));
            }
        }
        J j2 = (J) this.a.get();
        if (j2 == null || (tVar = j2.s) == null || (barcodeCountView = (BarcodeCountView) ((com.scandit.datacapture.barcode.count.ui.view.i) tVar).a.get()) == null) {
            return;
        }
        BarcodeCountView.access$clearIndicatorsOnMainThread(barcodeCountView);
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountGuidanceHandler
    public final void setMoveCloserAndRescan(boolean z, String str) {
        s1 s1Var;
        J j = (J) this.a.get();
        if (j == null || (s1Var = j.l) == null) {
            return;
        }
        o1.b((y1) s1Var, z, str);
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountGuidanceHandler
    public final void setMoveFurtherAndRescan(boolean z, String str) {
        s1 s1Var;
        J j = (J) this.a.get();
        if (j == null || (s1Var = j.l) == null) {
            return;
        }
        o1.a((y1) s1Var, z, str);
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountGuidanceHandler
    public final void setScanning(boolean z, String str) {
        O o;
        J j = (J) this.a.get();
        if (j == null || (o = j.m) == null) {
            return;
        }
        L.b((X) o, z, str);
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountGuidanceHandler
    public final void setScanningProgress(int i) {
        O o;
        J j = (J) this.a.get();
        if (j == null || (o = j.m) == null) {
            return;
        }
        float f = i / 100.0f;
        X x = (X) o;
        if (x.a) {
            x.d.a(new V(x, f));
        }
    }

    @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountGuidanceHandler
    public final void setTapToScan(boolean z, String str) {
        s1 s1Var;
        J j = (J) this.a.get();
        if (j == null || (s1Var = j.l) == null) {
            return;
        }
        p1 onFinished = p1.a;
        y1 y1Var = (y1) s1Var;
        Intrinsics.checkNotNullParameter(onFinished, "onFinished");
        if (y1Var.a) {
            y1Var.c.a(new v1(z, y1Var, str, onFinished));
        }
    }
}
