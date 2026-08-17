package com.scandit.datacapture.barcode.internal.module.count.ui.handlers;

import android.util.Size;
import com.scandit.datacapture.barcode.count.capture.BarcodeCount;
import com.scandit.datacapture.barcode.count.feedback.BarcodeCountFeedback;
import com.scandit.datacapture.barcode.count.ui.BarcodeCountViewInternalUiListener;
import com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewLayerManager;
import com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewSettings;
import com.scandit.datacapture.barcode.internal.module.count.ui.mode.BarcodeCountInternalMode;
import com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.BarcodeCountToolbarView;

/* loaded from: classes2.dex */
public interface BarcodeCountUiPresenter {
    void a();

    void a(int i);

    void a(int i, int i2);

    void a(Size size, Size size2);

    void a(BarcodeCount barcodeCount);

    void a(BarcodeCountFeedback barcodeCountFeedback);

    void a(BarcodeCountViewInternalUiListener barcodeCountViewInternalUiListener);

    void a(com.scandit.datacapture.barcode.count.ui.view.i iVar);

    void a(BarcodeCountViewLayerManager barcodeCountViewLayerManager, BarcodeCount barcodeCount);

    void a(BarcodeCountViewSettings barcodeCountViewSettings);

    void a(BarcodeCountInternalMode barcodeCountInternalMode);

    void a(boolean z);

    void a(boolean z, boolean z2);

    void b(BarcodeCountViewSettings barcodeCountViewSettings);

    void b(boolean z);

    boolean b();

    BarcodeCountViewInternalUiListener c();

    void c(boolean z);

    void d();

    void d(boolean z);

    void e(boolean z);

    boolean e();

    void f();

    BarcodeCountToolbarView g();

    void h();

    void i();

    int j();

    void k();

    void l();
}
