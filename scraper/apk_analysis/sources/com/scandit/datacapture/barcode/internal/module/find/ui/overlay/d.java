package com.scandit.datacapture.barcode.internal.module.find.ui.overlay;

import com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindBasicOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class d implements c {
    public final NativeBarcodeFindBasicOverlay a;
    public final NativeDataCaptureOverlay b;

    public d(NativeBarcodeFindBasicOverlay _NativeBarcodeFindBasicOverlay) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_NativeBarcodeFindBasicOverlay, "_NativeBarcodeFindBasicOverlay");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeBarcodeFindBasicOverlay;
        NativeDataCaptureOverlay asDataCaptureOverlay = _NativeBarcodeFindBasicOverlay.asDataCaptureOverlay();
        Intrinsics.checkNotNullExpressionValue(asDataCaptureOverlay, "asDataCaptureOverlay(...)");
        this.b = asDataCaptureOverlay;
    }

    @Override // com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay, com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlayProxy
    public final NativeDataCaptureOverlay _dataCaptureOverlayImpl() {
        return this.b;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.c
    public final void a(String str) {
        throw null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.c
    public final void b(String str) {
        throw null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.c
    public final void c(String str) {
        throw null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.c
    public final void d(String str) {
        throw null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.c
    public final void e(String str) {
        throw null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.c
    public final void f(String str) {
        throw null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.c
    public final void g(String str) {
        throw null;
    }
}
