package com.scandit.datacapture.barcode.internal.module.ar.capture;

import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c {
    public final NativeBarcodeAr a;
    public final NativeDataCaptureMode b;

    public c(NativeBarcodeAr _NativeBarcodeAr) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_NativeBarcodeAr, "_NativeBarcodeAr");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeBarcodeAr;
        NativeDataCaptureMode asDataCaptureMode = _NativeBarcodeAr.asDataCaptureMode();
        Intrinsics.checkNotNullExpressionValue(asDataCaptureMode, "asDataCaptureMode(...)");
        this.b = asDataCaptureMode;
    }
}
