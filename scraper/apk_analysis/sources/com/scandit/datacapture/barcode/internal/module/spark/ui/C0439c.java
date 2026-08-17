package com.scandit.datacapture.barcode.internal.module.spark.ui;

import com.scandit.datacapture.barcode.internal.module.spark.internal.SparkScanInternal;
import com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSparkScanOverlay;
import com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSparkScanOverlayStyle;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.ui.c, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0439c implements DataCaptureOverlay {
    public final /* synthetic */ C0440d a;

    public C0439c(SparkScanInternal sparkScanInternal, NativeSparkScanOverlayStyle nativeSparkScanOverlayStyle) {
        NativeSparkScanOverlay impl = NativeSparkScanOverlay.create(sparkScanInternal.a(), nativeSparkScanOverlayStyle);
        Intrinsics.checkNotNullExpressionValue(impl, "create(...)");
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new C0440d(impl);
    }

    @Override // com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay, com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlayProxy
    /* renamed from: _dataCaptureOverlayImpl */
    public final NativeDataCaptureOverlay getC() {
        return this.a.b;
    }
}
