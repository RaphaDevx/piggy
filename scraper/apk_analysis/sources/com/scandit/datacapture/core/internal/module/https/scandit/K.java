package com.scandit.datacapture.core.internal.module.https.scandit;

import com.scandit.datacapture.core.internal.module.https.trusts.ExtendedX509TrustManager;
import javax.net.ssl.SSLContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class K extends Lambda implements Function0 {
    public final /* synthetic */ M a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public K(M m) {
        super(0);
        this.a = m;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
        sSLContext.init(null, (ExtendedX509TrustManager[]) this.a.a.getValue(), null);
        return sSLContext.getSocketFactory();
    }
}
