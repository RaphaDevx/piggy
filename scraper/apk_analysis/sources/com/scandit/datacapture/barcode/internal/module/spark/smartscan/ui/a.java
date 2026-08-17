package com.scandit.datacapture.barcode.internal.module.spark.smartscan.ui;

import com.scandit.datacapture.barcode.internal.module.spark.internal.SparkScanInternal;
import com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSmartScanSelectionOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements DataCaptureOverlay {
    public final /* synthetic */ b a;

    public a(SparkScanInternal sparkScanInternal) {
        NativeSmartScanSelectionOverlay impl = NativeSmartScanSelectionOverlay.create(sparkScanInternal.a());
        Intrinsics.checkNotNullExpressionValue(impl, "create(...)");
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new b(impl);
    }

    @Override // com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay, com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlayProxy
    /* renamed from: _dataCaptureOverlayImpl */
    public final NativeDataCaptureOverlay getC() {
        return this.a.b;
    }
}
