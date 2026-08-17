package com.scandit.datacapture.frameworks.barcode.ar.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.PluginMethod;
import com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArHighlightProvider;
import com.scandit.datacapture.barcode.data.Barcode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HighlightCallbackData.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/data/HighlightCallbackData;", "", "barcode", "Lcom/scandit/datacapture/barcode/data/Barcode;", PluginMethod.RETURN_CALLBACK, "Lcom/scandit/datacapture/barcode/ar/ui/highlight/BarcodeArHighlightProvider$Callback;", "(Lcom/scandit/datacapture/barcode/data/Barcode;Lcom/scandit/datacapture/barcode/ar/ui/highlight/BarcodeArHighlightProvider$Callback;)V", "getBarcode", "()Lcom/scandit/datacapture/barcode/data/Barcode;", "getCallback", "()Lcom/scandit/datacapture/barcode/ar/ui/highlight/BarcodeArHighlightProvider$Callback;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class HighlightCallbackData {
    private final Barcode barcode;
    private final BarcodeArHighlightProvider.Callback callback;

    public HighlightCallbackData(Barcode barcode, BarcodeArHighlightProvider.Callback callback) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.barcode = barcode;
        this.callback = callback;
    }

    public final Barcode getBarcode() {
        return this.barcode;
    }

    public final BarcodeArHighlightProvider.Callback getCallback() {
        return this.callback;
    }
}
