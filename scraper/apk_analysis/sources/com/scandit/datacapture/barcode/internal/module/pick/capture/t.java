package com.scandit.datacapture.barcode.internal.module.pick.capture;

import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class t {
    public final NativeBarcodePick a;
    public final ProxyCache b;
    public final NativeDataCaptureMode c;

    public t(NativeBarcodePick _NativeBarcodePick) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_NativeBarcodePick, "_NativeBarcodePick");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeBarcodePick;
        this.b = proxyCache;
        NativeDataCaptureMode asDataCaptureMode = _NativeBarcodePick.asDataCaptureMode();
        Intrinsics.checkNotNullExpressionValue(asDataCaptureMode, "asDataCaptureMode(...)");
        this.c = asDataCaptureMode;
    }
}
