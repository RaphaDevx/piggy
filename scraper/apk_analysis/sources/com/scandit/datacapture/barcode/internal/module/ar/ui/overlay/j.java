package com.scandit.datacapture.barcode.internal.module.ar.ui.overlay;

import com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class j implements DataCaptureOverlay {
    public final NativeBarcodeArOverlay a;
    public final NativeDataCaptureOverlay b;

    public j(NativeBarcodeArOverlay _NativeBarcodeArOverlay) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_NativeBarcodeArOverlay, "_NativeBarcodeArOverlay");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeBarcodeArOverlay;
        NativeDataCaptureOverlay asDataCaptureOverlay = _NativeBarcodeArOverlay.asDataCaptureOverlay();
        Intrinsics.checkNotNullExpressionValue(asDataCaptureOverlay, "asDataCaptureOverlay(...)");
        this.b = asDataCaptureOverlay;
    }

    @Override // com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay, com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlayProxy
    public final NativeDataCaptureOverlay _dataCaptureOverlayImpl() {
        return this.b;
    }
}
