package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.internal.sdk.source.NativeSingleBitmapFrameDataCollection;
import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class SingleBitmapFrameDataCollection {
    private final NativeSingleBitmapFrameDataCollection a;

    public SingleBitmapFrameDataCollection(NativeSingleBitmapFrameDataCollection _NativeSingleBitmapFrameDataCollection) {
        this.a = _NativeSingleBitmapFrameDataCollection;
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_NativeSingleBitmapFrameDataCollection, "_NativeSingleBitmapFrameDataCollection");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
    }

    public final NativeSingleBitmapFrameDataCollection a() {
        return this.a;
    }
}
