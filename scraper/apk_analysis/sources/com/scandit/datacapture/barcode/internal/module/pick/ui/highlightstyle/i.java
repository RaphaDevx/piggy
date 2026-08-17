package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle;

import android.graphics.Bitmap;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class i {
    public static final BarcodePickIcon a(int i, BarcodePickIcon barcodePickIcon) {
        Intrinsics.checkNotNullParameter(g.a, "<this>");
        Intrinsics.checkNotNullParameter(barcodePickIcon, "default");
        return i == 0 ? barcodePickIcon : new o(i);
    }

    public static final BarcodePickIcon a(Bitmap bitmap, BarcodePickIcon barcodePickIcon) {
        Intrinsics.checkNotNullParameter(g.a, "<this>");
        Intrinsics.checkNotNullParameter(barcodePickIcon, "default");
        return bitmap == null ? barcodePickIcon : new j(bitmap);
    }

    public static final BarcodePickIcon a(ScanditIcon scanditIcon, BarcodePickIcon barcodePickIcon) {
        Intrinsics.checkNotNullParameter(g.a, "<this>");
        Intrinsics.checkNotNullParameter(barcodePickIcon, "default");
        return scanditIcon == null ? barcodePickIcon : new n(scanditIcon);
    }
}
