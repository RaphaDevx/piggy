package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.filtered;

import com.scandit.datacapture.barcode.filter.ui.overlay.BarcodeFilterHighlightSettings;
import com.scandit.datacapture.core.common.geometry.Size2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class f {
    public final Size2 a;
    public final BarcodeFilterHighlightSettings b;

    public f(Size2 minSize, BarcodeFilterHighlightSettings barcodeFilterHighlightSettings) {
        Intrinsics.checkNotNullParameter(minSize, "minSize");
        this.a = minSize;
        this.b = barcodeFilterHighlightSettings;
    }
}
