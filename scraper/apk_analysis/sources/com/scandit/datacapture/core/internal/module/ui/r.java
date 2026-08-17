package com.scandit.datacapture.core.internal.module.ui;

import com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCache;
import com.scandit.datacapture.tools.internal.sdk.ProxyCacheKt;
import java.util.EnumSet;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* loaded from: classes2.dex */
public final class r extends NativeGestureRecognizer {
    public final GestureRecognizer a;
    public final ProxyCache b;

    public r(GestureRecognizer _GestureRecognizer) {
        GuavaMapMakerProxyCache proxyCache = ProxyCacheKt.getGlobalProxyCache();
        Intrinsics.checkNotNullParameter(_GestureRecognizer, "_GestureRecognizer");
        Intrinsics.checkNotNullParameter(proxyCache, "proxyCache");
        this.a = _GestureRecognizer;
        this.b = proxyCache;
    }

    @Override // com.scandit.datacapture.core.internal.module.ui.NativeGestureRecognizer
    public final void setGestureListener(NativeGestureListener listener, EnumSet gestures) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(gestures, "gestures");
        this.a.a((GestureListener) this.b.getOrPut(Reflection.getOrCreateKotlinClass(NativeGestureListener.class), null, listener, new q(listener)), gestures);
    }
}
