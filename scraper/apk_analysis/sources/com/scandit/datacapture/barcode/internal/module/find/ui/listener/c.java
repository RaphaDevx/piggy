package com.scandit.datacapture.barcode.internal.module.find.ui.listener;

import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedBarcode;
import com.scandit.datacapture.barcode.find.capture.BarcodeFind;
import com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener;
import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindSession;
import com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewPresenter;
import com.scandit.datacapture.core.data.FrameData;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.DataCaptureViewSizeListener;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashSet;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c implements InternalBarcodeFindListener, DataCaptureViewSizeListener {
    public final BarcodeFind a;
    public final WeakReference b;

    public c(BarcodeFind mode, BarcodeFindViewPresenter presenter) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(presenter, "presenter");
        this.a = mode;
        this.b = new WeakReference(presenter);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void a(BarcodeFind mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void b(BarcodeFind mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void c(HashSet barcodes) {
        Intrinsics.checkNotNullParameter(barcodes, "barcodes");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void onSearchStarted() {
        BarcodeFindViewPresenter barcodeFindViewPresenter = (BarcodeFindViewPresenter) this.b.get();
        if (barcodeFindViewPresenter != null) {
            barcodeFindViewPresenter.onSearchStarted();
        }
    }

    @Override // com.scandit.datacapture.core.ui.DataCaptureViewSizeListener
    public final void onSizeChanged(DataCaptureView view, int i, int i2, int i3) {
        BarcodeFindViewPresenter barcodeFindViewPresenter;
        Intrinsics.checkNotNullParameter(view, "view");
        NativeBarcodeFindSession session = this.a.getA().getSession();
        if (session == null || (barcodeFindViewPresenter = (BarcodeFindViewPresenter) this.b.get()) == null) {
            return;
        }
        Collection<NativeTrackedBarcode> values = session.getFoundTrackedBarcodes().values();
        Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
        Collection<NativeTrackedBarcode> values2 = session.getTrackedBarcodes().values();
        Intrinsics.checkNotNullExpressionValue(values2, "<get-values>(...)");
        barcodeFindViewPresenter.a(values, values2, new b(this, session));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void a(BarcodeFind mode, NativeBarcodeFindSession session, FrameData data) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(data, "data");
        BarcodeFindViewPresenter barcodeFindViewPresenter = (BarcodeFindViewPresenter) this.b.get();
        if (barcodeFindViewPresenter != null) {
            Collection<NativeTrackedBarcode> values = session.getFoundTrackedBarcodes().values();
            Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
            Collection<NativeTrackedBarcode> values2 = session.getTrackedBarcodes().values();
            Intrinsics.checkNotNullExpressionValue(values2, "<get-values>(...)");
            barcodeFindViewPresenter.a(values, values2, new a(mode, session));
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void b(HashSet barcodes) {
        Intrinsics.checkNotNullParameter(barcodes, "barcodes");
        BarcodeFindViewPresenter barcodeFindViewPresenter = (BarcodeFindViewPresenter) this.b.get();
        if (barcodeFindViewPresenter != null) {
            barcodeFindViewPresenter.a(barcodes);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.capture.InternalBarcodeFindListener
    public final void a(HashSet barcodes) {
        Intrinsics.checkNotNullParameter(barcodes, "barcodes");
        BarcodeFindViewPresenter barcodeFindViewPresenter = (BarcodeFindViewPresenter) this.b.get();
        if (barcodeFindViewPresenter != null) {
            barcodeFindViewPresenter.c();
        }
    }
}
