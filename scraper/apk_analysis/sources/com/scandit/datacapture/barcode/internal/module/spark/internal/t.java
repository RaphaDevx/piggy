package com.scandit.datacapture.barcode.internal.module.spark.internal;

import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class t {
    public final NativeSparkScan a;
    public final NativeDataCaptureMode b;

    public t(NativeSparkScan _NativeSparkScan) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_NativeSparkScan, "_NativeSparkScan");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeSparkScan;
        NativeDataCaptureMode asDataCaptureMode = _NativeSparkScan.asDataCaptureMode();
        Intrinsics.checkNotNullExpressionValue(asDataCaptureMode, "asDataCaptureMode(...)");
        this.b = asDataCaptureMode;
    }
}
