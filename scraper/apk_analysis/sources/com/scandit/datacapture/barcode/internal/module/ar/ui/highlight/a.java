package com.scandit.datacapture.barcode.internal.module.ar.ui.highlight;

import android.content.Context;
import com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArHighlightProvider;
import com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArRectangleHighlight;
import com.scandit.datacapture.barcode.data.Barcode;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements BarcodeArHighlightProvider {
    @Override // com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArHighlightProvider
    public final void highlightForBarcode(Context context, Barcode barcode, BarcodeArHighlightProvider.Callback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.onData(new BarcodeArRectangleHighlight(context, barcode));
    }
}
