package com.scandit.datacapture.barcode.internal.module.common;

import com.scandit.datacapture.barcode.data.BarcodeDataTransformer;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a extends NativeBarcodeDataTransformer {
    public final WeakReference a;

    public a(BarcodeDataTransformer transformer) {
        Intrinsics.checkNotNullParameter(transformer, "transformer");
        this.a = new WeakReference(transformer);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.common.NativeBarcodeDataTransformer
    public final String transformBarcodeData(String transformBarcodeData) {
        Intrinsics.checkNotNullParameter(transformBarcodeData, "transformBarcodeData");
        BarcodeDataTransformer barcodeDataTransformer = (BarcodeDataTransformer) this.a.get();
        if (barcodeDataTransformer != null) {
            return barcodeDataTransformer.transformBarcodeData(transformBarcodeData);
        }
        return null;
    }
}
