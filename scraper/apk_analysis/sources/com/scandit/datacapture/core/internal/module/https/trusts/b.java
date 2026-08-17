package com.scandit.datacapture.core.internal.module.https.trusts;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function1 {
    public final /* synthetic */ X509Certificate[] a;
    public final /* synthetic */ String b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(X509Certificate[] x509CertificateArr, String str) {
        super(1);
        this.a = x509CertificateArr;
        this.b = str;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        X509TrustManager trust = (X509TrustManager) obj;
        Intrinsics.checkNotNullParameter(trust, "trust");
        trust.checkServerTrusted(this.a, this.b);
        return Unit.INSTANCE;
    }
}
