package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayColorScheme;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayStyle;
import com.scandit.datacapture.core.ui.style.Brush;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.g, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0304g {
    public final /* synthetic */ C0316t a;

    public C0304g(C0316t c0316t) {
        this.a = c0316t;
    }

    public final Brush a(TrackedBarcode trackedBarcode) {
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        W a = this.a.a(trackedBarcode, N.a);
        C0316t c0316t = this.a;
        if (c0316t.d == NativeBarcodeCountBasicOverlayStyle.DOT || c0316t.o == NativeBarcodeCountBasicOverlayColorScheme.ACCESSIBLE) {
            return c0316t.e.a(trackedBarcode, a);
        }
        int ordinal = a.ordinal();
        return ordinal != 0 ? ordinal != 3 ? this.a.e.a(trackedBarcode, a) : new Brush(-375738, 0, 0.0f) : new Brush(-13712946, 0, 0.0f);
    }
}
