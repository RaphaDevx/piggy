package com.scandit.datacapture.barcode.internal.module.pick.capture;

import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class w {
    public final NativeBarcodePickSession a;

    public w(NativeBarcodePickSession _NativeBarcodePickSession) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_NativeBarcodePickSession, "_NativeBarcodePickSession");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeBarcodePickSession;
    }
}
