package com.scandit.datacapture.tools.internal.sdk;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0006\"\u0017\u0010\u0005\u001a\u00020\u00008\u0006¢\u0006\f\n\u0004\b\u0001\u0010\u0002\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, d2 = {"Lcom/scandit/datacapture/tools/internal/sdk/GuavaMapMakerProxyCache;", "a", "Lcom/scandit/datacapture/tools/internal/sdk/GuavaMapMakerProxyCache;", "getGlobalProxyCache", "()Lcom/scandit/datacapture/tools/internal/sdk/GuavaMapMakerProxyCache;", "GlobalProxyCache", "scandit-capture-core"}, k = 2, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class ProxyCacheKt {
    private static final GuavaMapMakerProxyCache a = new GuavaMapMakerProxyCache();

    public static final GuavaMapMakerProxyCache getGlobalProxyCache() {
        return a;
    }
}
