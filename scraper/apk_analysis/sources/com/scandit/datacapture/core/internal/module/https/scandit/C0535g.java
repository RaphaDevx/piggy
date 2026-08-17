package com.scandit.datacapture.core.internal.module.https.scandit;

import java.net.URL;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.https.scandit.g, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0535g implements InterfaceC0534f {
    public final void a(URL originalUrl, URL connectionUrl) {
        Intrinsics.checkNotNullParameter(originalUrl, "originalUrl");
        Intrinsics.checkNotNullParameter(connectionUrl, "connectionUrl");
        String host = originalUrl.getHost();
        String host2 = connectionUrl.getHost();
        if (Intrinsics.areEqual(originalUrl.getHost(), connectionUrl.getHost())) {
            return;
        }
        Intrinsics.checkNotNull(host);
        Intrinsics.checkNotNull(host2);
        throw new C0552y(host, host2);
    }
}
