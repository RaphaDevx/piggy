package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayColorScheme;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayStyle;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.ui.style.Brush;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class z {
    public final int a;
    public final int b;
    public final List c;
    public final Quadrilateral d;
    public final Brush e;
    public final float f;
    public final NativeBarcodeCountBasicOverlayStyle g;
    public final NativeBarcodeCountBasicOverlayColorScheme h;
    public final String i;
    public final W j;

    public z(int i, int i2, ArrayList trackedBarcodeIdentifiers, Quadrilateral mappedLocation, Brush brush, float f, NativeBarcodeCountBasicOverlayStyle style, NativeBarcodeCountBasicOverlayColorScheme colorScheme, String data, W status) {
        Intrinsics.checkNotNullParameter(trackedBarcodeIdentifiers, "trackedBarcodeIdentifiers");
        Intrinsics.checkNotNullParameter(mappedLocation, "mappedLocation");
        Intrinsics.checkNotNullParameter(brush, "brush");
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(colorScheme, "colorScheme");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(status, "status");
        this.a = i;
        this.b = i2;
        this.c = trackedBarcodeIdentifiers;
        this.d = mappedLocation;
        this.e = brush;
        this.f = f;
        this.g = style;
        this.h = colorScheme;
        this.i = data;
        this.j = status;
    }
}
