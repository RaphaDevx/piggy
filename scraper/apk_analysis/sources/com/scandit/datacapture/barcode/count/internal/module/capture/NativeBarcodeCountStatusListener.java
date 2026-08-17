package com.scandit.datacapture.barcode.count.internal.module.capture;

import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedBarcode;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeCountStatusListener {
    public abstract void onAbortStatusMode();

    public abstract void onBarcodeStatusesLoading(ArrayList<NativeTrackedBarcode> arrayList);

    public abstract void onBarcodeStatusesReady(ArrayList<NativeBarcodeCountStatusItem> arrayList);

    public abstract void onHideLoading();

    public abstract void onShowLoading();
}
