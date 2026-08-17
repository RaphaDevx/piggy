package com.scandit.datacapture.barcode.internal.module.spark.ui;

import com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSparkScanOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.ui.d, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0440d implements DataCaptureOverlay {
    public final NativeSparkScanOverlay a;
    public final NativeDataCaptureOverlay b;

    public C0440d(NativeSparkScanOverlay _NativeSparkScanOverlay) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_NativeSparkScanOverlay, "_NativeSparkScanOverlay");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeSparkScanOverlay;
        NativeDataCaptureOverlay asDataCaptureOverlay = _NativeSparkScanOverlay.asDataCaptureOverlay();
        Intrinsics.checkNotNullExpressionValue(asDataCaptureOverlay, "asDataCaptureOverlay(...)");
        this.b = asDataCaptureOverlay;
    }

    @Override // com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay, com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlayProxy
    /* renamed from: _dataCaptureOverlayImpl */
    public final NativeDataCaptureOverlay getC() {
        return this.b;
    }
}
