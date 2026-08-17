package com.scandit.datacapture.barcode.internal.module.count.ui.popover;

import android.content.Context;
import androidx.core.view.PointerIconCompat;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayColorScheme;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountNotInListActionSettings;
import com.scandit.datacapture.barcode.internal.module.ui.popover.LinearPopoverButtonViewSettings;
import java.util.Map;
import kotlin.Lazy;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;

/* loaded from: classes2.dex */
public final class f {
    public static final Map a(Context context, BarcodeCountNotInListActionSettings barcodeCountNotInListActionSettings, NativeBarcodeCountBasicOverlayColorScheme nativeBarcodeCountBasicOverlayColorScheme, g gVar) {
        int i;
        Lazy lazy = h.f;
        Pair pair = TuplesKt.to(new LinearPopoverButtonViewSettings(0, context.getDrawable(R.drawable.sc_count_popup_reject), barcodeCountNotInListActionSettings.getRejectButtonText(), barcodeCountNotInListActionSettings.getRejectButtonContentDescription(), 0.0f, 0, null, false, false, PointerIconCompat.TYPE_TEXT), new c(gVar));
        int i2 = b.a[nativeBarcodeCountBasicOverlayColorScheme.ordinal()];
        if (i2 == 1) {
            i = R.drawable.sc_count_popup_accept;
        } else {
            if (i2 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            i = R.drawable.sc_count_popup_accept_alt;
        }
        return MapsKt.mapOf(pair, TuplesKt.to(new LinearPopoverButtonViewSettings(1, context.getDrawable(i), barcodeCountNotInListActionSettings.getAcceptButtonText(), barcodeCountNotInListActionSettings.getAcceptButtonContentDescription(), 0.0f, 0, null, false, false, PointerIconCompat.TYPE_TEXT), new d(gVar)), TuplesKt.to(new LinearPopoverButtonViewSettings(2, context.getDrawable(R.drawable.sc_count_popup_cancel), barcodeCountNotInListActionSettings.getCancelButtonText(), barcodeCountNotInListActionSettings.getCancelButtonContentDescription(), 0.0f, 0, null, false, false, PointerIconCompat.TYPE_TEXT), new e(gVar)));
    }
}
