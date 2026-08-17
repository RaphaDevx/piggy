package com.scandit.datacapture.core.internal.module.https.trusts;

import android.net.http.X509TrustManagerExtensions;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001J/\u0010\t\u001a\u00020\b2\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0017¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/scandit/datacapture/core/internal/module/https/trusts/WrapperX509TrustManager;", "Lcom/scandit/datacapture/core/internal/module/https/trusts/ExtendedX509TrustManager;", "", "Ljava/security/cert/X509Certificate;", "chain", "", "authType", "host", "", "checkServerTrusted", "([Ljava/security/cert/X509Certificate;Ljava/lang/String;Ljava/lang/String;)V", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public class WrapperX509TrustManager extends ExtendedX509TrustManager {
    private final X509TrustManager a;

    public WrapperX509TrustManager(X509TrustManager trustManager) {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        this.a = trustManager;
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkClientTrusted(X509Certificate[] chain, String authType) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(authType, "authType");
        this.a.checkClientTrusted(chain, authType);
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkServerTrusted(X509Certificate[] chain, String authType) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(authType, "authType");
        this.a.checkServerTrusted(chain, authType);
    }

    @Override // javax.net.ssl.X509TrustManager
    public final X509Certificate[] getAcceptedIssuers() {
        X509Certificate[] acceptedIssuers = this.a.getAcceptedIssuers();
        Intrinsics.checkNotNullExpressionValue(acceptedIssuers, "getAcceptedIssuers(...)");
        return acceptedIssuers;
    }

    @Override // com.scandit.datacapture.core.internal.module.https.trusts.ExtendedX509TrustManager
    public void checkServerTrusted(X509Certificate[] chain, String authType, String host) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(authType, "authType");
        Intrinsics.checkNotNullParameter(host, "host");
        new X509TrustManagerExtensions(this.a).checkServerTrusted(chain, authType, host);
    }
}
