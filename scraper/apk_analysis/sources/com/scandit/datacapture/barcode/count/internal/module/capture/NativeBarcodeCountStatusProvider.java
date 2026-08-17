package com.scandit.datacapture.barcode.count.internal.module.capture;

import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedBarcode;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeCountStatusProvider {
    public abstract void onStatusRequested(ArrayList<NativeTrackedBarcode> arrayList, NativeBarcodeCountStatusProviderCallback nativeBarcodeCountStatusProviderCallback);
}
