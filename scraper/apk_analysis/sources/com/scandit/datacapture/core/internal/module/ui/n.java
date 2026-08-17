package com.scandit.datacapture.core.internal.module.ui;

import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class n {
    public final NativeGestureListener a;
    public final ProxyCache b;

    public n(NativeGestureListener _NativeGestureListener) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_NativeGestureListener, "_NativeGestureListener");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _NativeGestureListener;
    }
}
