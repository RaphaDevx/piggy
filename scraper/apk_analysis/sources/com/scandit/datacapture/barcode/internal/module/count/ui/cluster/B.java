package com.scandit.datacapture.barcode.internal.module.count.ui.cluster;

import android.content.Context;
import com.scandit.datacapture.barcode.count.capture.BarcodeCount;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeClusterLiveEditor;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayColorScheme;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayStyle;
import com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlay;
import com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewLayerManager;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import com.scandit.datacapture.core.ui.style.Brush;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class B implements s {
    public final BarcodeCount a;
    public final BarcodeCountBasicOverlay b;
    public final BarcodeCountViewLayerManager c;
    public final Function1 d;
    public NativeBarcodeClusterLiveEditor e;
    public final com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f f;
    public final com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f g;
    public final com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f h;
    public r i;
    public boolean j;

    public B(Context context, BarcodeCount mode, BarcodeCountBasicOverlay overlay, com.scandit.datacapture.barcode.internal.module.count.ui.g layerManager, com.scandit.datacapture.barcode.internal.module.count.ui.c mapper) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(layerManager, "layerManager");
        Intrinsics.checkNotNullParameter(mapper, "mapper");
        this.a = mode;
        this.b = overlay;
        this.c = layerManager;
        this.d = mapper;
        this.f = new com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f(new E(), new v(context));
        this.g = new com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f(new E(), new A(context, this));
        this.h = new com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f(new E(), new u(context));
        this.j = true;
    }

    public final Quadrilateral a(Quadrilateral quadrilateral, float f) {
        Intrinsics.checkNotNullParameter(quadrilateral, "quadrilateral");
        Quadrilateral quadrilateral2 = (Quadrilateral) this.d.invoke(quadrilateral);
        return QuadrilateralUtilsKt.grow(quadrilateral2, ((f - 1.0f) * Float.min(QuadrilateralUtilsKt.getHeight(quadrilateral2), QuadrilateralUtilsKt.getWidth(quadrilateral2))) / 2.0f);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void b() {
        this.g.a(this.c.a());
        this.f.a(this.c.a());
        ((p) this.f.b()).g = new w(this);
        this.h.a(this.c.d());
        ((j) this.h.b()).g = new x(this);
    }

    public final boolean c() {
        int i = t.a[this.a.getClusteringMode$scandit_barcode_capture().ordinal()];
        if (i != 1 && i != 2) {
            if (i != 3 && i != 4) {
                throw new NoWhenBranchMatchedException();
            }
            if (this.j) {
                return true;
            }
        }
        return false;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void a() {
        com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f fVar = this.g;
        fVar.a.a(fVar.b(), fVar.a());
        q qVar = (q) this.g.b();
        boolean c = c();
        qVar.h = c;
        if (!c) {
            qVar.d.clear();
            qVar.c = null;
        }
        qVar.invalidate();
        com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f fVar2 = this.f;
        fVar2.a.a(fVar2.b(), fVar2.a());
        p pVar = (p) this.f.b();
        pVar.f = c();
        pVar.invalidate();
        com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f fVar3 = this.h;
        fVar3.a.a(fVar3.b(), fVar3.a());
        j jVar = (j) this.h.b();
        jVar.f = c();
        jVar.invalidate();
    }

    public final void a(NativeBarcodeCountBasicOverlayStyle style, NativeBarcodeCountBasicOverlayColorScheme colorScheme) {
        int a;
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(colorScheme, "colorScheme");
        q qVar = (q) this.g.b();
        int i = AbstractC0323a.a;
        BarcodeCountBasicOverlay brushDelegate = this.b;
        Intrinsics.checkNotNullParameter(brushDelegate, "brushDelegate");
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(colorScheme, "colorScheme");
        if (colorScheme != NativeBarcodeCountBasicOverlayColorScheme.ACCESSIBLE && style != NativeBarcodeCountBasicOverlayStyle.DOT) {
            a = AbstractC0323a.a;
        } else {
            Brush recognizedBrush = brushDelegate.getRecognizedBrush();
            a = recognizedBrush != null ? com.scandit.datacapture.barcode.internal.module.extensions.a.a(recognizedBrush.getFillColor(), 77) : 0;
        }
        qVar.a.setColor(a);
    }
}
