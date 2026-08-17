package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import android.graphics.Color;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayColorScheme;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayStyle;
import com.scandit.datacapture.core.ui.style.Brush;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.d, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public abstract class AbstractC0301d {
    public static final int a = Color.parseColor("#28D380");
    public static final int b = Color.parseColor("#FA4446");

    public static EnumC0298a a(NativeBarcodeCountBasicOverlayStyle nativeBarcodeCountBasicOverlayStyle, W w) {
        int ordinal = w.ordinal();
        if (ordinal == 1 || ordinal == 2) {
            return EnumC0298a.c;
        }
        int i = AbstractC0299b.a[nativeBarcodeCountBasicOverlayStyle.ordinal()];
        if (i == 1) {
            return EnumC0298a.a;
        }
        if (i == 2) {
            return EnumC0298a.b;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static Integer a(W w, NativeBarcodeCountBasicOverlayColorScheme nativeBarcodeCountBasicOverlayColorScheme) {
        int i;
        int i2;
        int i3;
        int ordinal = w.ordinal();
        if (ordinal == 0) {
            int i4 = AbstractC0299b.b[nativeBarcodeCountBasicOverlayColorScheme.ordinal()];
            if (i4 == 1) {
                i = R.drawable.sc_ic_barcode_scanned;
            } else {
                if (i4 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                i = R.drawable.sc_ic_barcode_scanned_alt;
            }
            return Integer.valueOf(i);
        }
        if (ordinal == 1) {
            return Integer.valueOf(R.drawable.sc_ic_barcode_accepted);
        }
        if (ordinal == 2) {
            return Integer.valueOf(R.drawable.sc_ic_barcode_rejected);
        }
        if (ordinal == 3) {
            int i5 = AbstractC0299b.b[nativeBarcodeCountBasicOverlayColorScheme.ordinal()];
            if (i5 == 1) {
                i2 = R.drawable.sc_ic_barcode_not_in_list;
            } else {
                if (i5 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                i2 = R.drawable.sc_ic_barcode_not_in_list_alt;
            }
            return Integer.valueOf(i2);
        }
        if (ordinal != 4) {
            if (ordinal == 5) {
                return null;
            }
            throw new NoWhenBranchMatchedException();
        }
        int i6 = AbstractC0299b.b[nativeBarcodeCountBasicOverlayColorScheme.ordinal()];
        if (i6 == 1) {
            i3 = R.drawable.sc_ic_barcode_unscanned;
        } else {
            if (i6 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            i3 = R.drawable.sc_ic_barcode_unscanned_alt;
        }
        return Integer.valueOf(i3);
    }

    public static boolean a(TrackedBarcode barcode, NativeBarcodeCountBasicOverlayStyle style, W status, Function2 dotBrushProvider) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(dotBrushProvider, "dotBrushProvider");
        EnumC0298a a2 = a(style, status);
        Brush brush = (Brush) dotBrushProvider.invoke(barcode, status);
        return a2 == EnumC0298a.b || !(brush == null || Color.alpha(brush.getFillColor()) == 0);
    }
}
