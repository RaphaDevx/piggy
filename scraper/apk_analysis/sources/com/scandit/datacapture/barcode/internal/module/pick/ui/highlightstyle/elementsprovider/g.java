package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider;

import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickBrush;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickIcon;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickStatusIconStyle;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class g implements i {
    public final BarcodePickIcon a;
    public final BarcodePickBrush b;
    public final BarcodePickStatusIconStyle c;

    public g(BarcodePickIcon icon, BarcodePickBrush brush, BarcodePickStatusIconStyle barcodePickStatusIconStyle) {
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(brush, "brush");
        this.a = icon;
        this.b = brush;
        this.c = barcodePickStatusIconStyle;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.i
    public final BarcodePickStatusIconStyle a() {
        return this.c;
    }
}
