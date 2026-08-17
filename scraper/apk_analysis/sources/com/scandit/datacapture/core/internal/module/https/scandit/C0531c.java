package com.scandit.datacapture.core.internal.module.https.scandit;

import com.scandit.datacapture.core.internal.module.https.NativeHttpsMethod;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.https.scandit.c, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0531c implements InterfaceC0529a {
    public final String a;
    public final NativeHttpsMethod b;
    public final Map c;
    public final N d;
    public final InterfaceC0534f e;

    public C0531c(String url, NativeHttpsMethod method, HashMap headers, O sslSocketHandler) {
        C0535g hostValidator = new C0535g();
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(sslSocketHandler, "sslSocketHandler");
        Intrinsics.checkNotNullParameter(hostValidator, "hostValidator");
        this.a = url;
        this.b = method;
        this.c = headers;
        this.d = sslSocketHandler;
        this.e = hostValidator;
    }
}
