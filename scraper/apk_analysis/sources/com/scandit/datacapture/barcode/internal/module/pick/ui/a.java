package com.scandit.datacapture.barcode.internal.module.pick.ui;

import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements DataCaptureOverlay {
    public final NativeBarcodePickBasicOverlay a;
    public final ProxyCache b;
    public final NativeDataCaptureOverlay c;

    public a(NativeBarcodePickBasicOverlay _NativeBarcodePickBasicOverlay) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_NativeBarcodePickBasicOverlay, "_NativeBarcodePickBasicOverlay");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeBarcodePickBasicOverlay;
        this.b = proxyCache;
        NativeDataCaptureOverlay asDataCaptureOverlay = _NativeBarcodePickBasicOverlay.asDataCaptureOverlay();
        Intrinsics.checkNotNullExpressionValue(asDataCaptureOverlay, "asDataCaptureOverlay(...)");
        this.c = asDataCaptureOverlay;
    }

    @Override // com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay, com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlayProxy
    /* renamed from: _dataCaptureOverlayImpl */
    public final NativeDataCaptureOverlay getC() {
        return this.c;
    }
}
