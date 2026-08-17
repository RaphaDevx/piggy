package com.scandit.datacapture.barcode.internal.module.spark.smartscan.ui;

import com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSmartScanSelectionOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements DataCaptureOverlay {
    public final NativeSmartScanSelectionOverlay a;
    public final NativeDataCaptureOverlay b;

    public b(NativeSmartScanSelectionOverlay _NativeSmartScanSelectionOverlay) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_NativeSmartScanSelectionOverlay, "_NativeSmartScanSelectionOverlay");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeSmartScanSelectionOverlay;
        NativeDataCaptureOverlay asDataCaptureOverlay = _NativeSmartScanSelectionOverlay.asDataCaptureOverlay();
        Intrinsics.checkNotNullExpressionValue(asDataCaptureOverlay, "asDataCaptureOverlay(...)");
        this.b = asDataCaptureOverlay;
    }

    @Override // com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay, com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlayProxy
    public final NativeDataCaptureOverlay _dataCaptureOverlayImpl() {
        return this.b;
    }
}
