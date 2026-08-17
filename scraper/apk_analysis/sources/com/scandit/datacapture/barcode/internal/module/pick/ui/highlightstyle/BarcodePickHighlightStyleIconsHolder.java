package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle;

import android.graphics.Bitmap;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;

/* loaded from: classes2.dex */
public interface BarcodePickHighlightStyleIconsHolder extends com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.iconprovider.c {
    public static final /* synthetic */ int a = 0;

    void a(int i, BarcodePickState barcodePickState);

    void a(Bitmap bitmap, BarcodePickState barcodePickState);

    void a(ScanditIcon scanditIcon, BarcodePickState barcodePickState);

    void b(int i, BarcodePickState barcodePickState);

    void b(Bitmap bitmap, BarcodePickState barcodePickState);

    void b(ScanditIcon scanditIcon, BarcodePickState barcodePickState);
}
