package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import android.graphics.Color;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayStyle;
import com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountBrushHandler;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.ui.style.Brush;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.p, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0313p extends Lambda implements Function3 {
    public final /* synthetic */ C0316t a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0313p(C0316t c0316t) {
        super(3);
        this.a = c0316t;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean z;
        TrackedBarcode trackedBarcode = (TrackedBarcode) obj;
        TrackedBarcode barcode = (TrackedBarcode) obj2;
        Intrinsics.checkNotNullParameter((Point) obj3, "<anonymous parameter 2>");
        boolean z2 = false;
        if (barcode != null) {
            C0316t c0316t = this.a;
            if (trackedBarcode != null && trackedBarcode.getGlobalId$scandit_barcode_capture() == barcode.getGlobalId$scandit_barcode_capture()) {
                W status = c0316t.a(barcode, c0316t.d(barcode));
                if (AbstractC0303f.a[status.ordinal()] == 1) {
                    BarcodeCountBrushHandler barcodeCountBrushHandler = c0316t.e;
                    W w = W.b;
                    Brush a = barcodeCountBrushHandler.a();
                    z = !(a != null && Color.alpha(a.getFillColor()) == 0);
                } else if (((L) c0316t.r.get(Integer.valueOf(barcode.getGlobalId$scandit_barcode_capture()))) != null) {
                    NativeBarcodeCountBasicOverlayStyle style = c0316t.d;
                    C0306i dotBrushProvider = new C0306i(c0316t.e);
                    Intrinsics.checkNotNullParameter(style, "style");
                    Intrinsics.checkNotNullParameter(barcode, "barcode");
                    Intrinsics.checkNotNullParameter(status, "status");
                    Intrinsics.checkNotNullParameter(dotBrushProvider, "dotBrushProvider");
                    z = AbstractC0301d.a(barcode, style, status, dotBrushProvider);
                } else {
                    z = false;
                }
                if (z) {
                    z2 = true;
                }
            }
        }
        return Boolean.valueOf(z2);
    }
}
