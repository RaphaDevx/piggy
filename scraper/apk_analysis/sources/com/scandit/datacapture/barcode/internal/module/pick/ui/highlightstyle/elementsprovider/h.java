package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider;

import android.view.View;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickStatusIconStyle;

/* loaded from: classes2.dex */
public final class h implements i {
    public final View a;
    public final BarcodePickStatusIconStyle b;

    public h(View view, BarcodePickStatusIconStyle barcodePickStatusIconStyle) {
        this.a = view;
        this.b = barcodePickStatusIconStyle;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.i
    public final BarcodePickStatusIconStyle a() {
        return this.b;
    }
}
