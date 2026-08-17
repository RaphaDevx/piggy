package com.scandit.datacapture.barcode.count.internal.module.capture;

import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedBarcode;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeCountViewHandler {
    public abstract void clear();

    public abstract void hideClassificationPopupForNotInListBarcode(NativeTrackedBarcode nativeTrackedBarcode, NativeBarcodeCountNotInListStatus nativeBarcodeCountNotInListStatus);

    public abstract void setEnableProgressBar(boolean z);

    public abstract void setEnableShutterAnimation(boolean z);

    public abstract void setProgressBarState(int i, int i2);

    public abstract void setShutterDimmed(boolean z);

    public abstract void showClassificationPopupForNotInListBarcode(NativeTrackedBarcode nativeTrackedBarcode, NativeBarcodeCountNotInListStatus nativeBarcodeCountNotInListStatus);
}
