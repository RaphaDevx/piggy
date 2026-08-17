package com.scandit.datacapture.core.internal.module.https.trusts;

/* loaded from: classes2.dex */
public final class d extends WrapperX509TrustManager {
    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public d() {
        /*
            r4 = this;
            java.lang.String r0 = "Unexpected default trust managers: "
            java.lang.String r1 = javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm()     // Catch: java.security.GeneralSecurityException -> L47
            javax.net.ssl.TrustManagerFactory r1 = javax.net.ssl.TrustManagerFactory.getInstance(r1)     // Catch: java.security.GeneralSecurityException -> L47
            r2 = 0
            r1.init(r2)     // Catch: java.security.GeneralSecurityException -> L47
            javax.net.ssl.TrustManager[] r1 = r1.getTrustManagers()     // Catch: java.security.GeneralSecurityException -> L47
            int r2 = r1.length     // Catch: java.security.GeneralSecurityException -> L47
            r3 = 1
            if (r2 != r3) goto L2d
            r2 = 0
            r2 = r1[r2]     // Catch: java.security.GeneralSecurityException -> L47
            boolean r3 = r2 instanceof javax.net.ssl.X509TrustManager     // Catch: java.security.GeneralSecurityException -> L47
            if (r3 == 0) goto L2d
            java.lang.String r0 = "null cannot be cast to non-null type javax.net.ssl.X509TrustManager"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r0)     // Catch: java.security.GeneralSecurityException -> L47
            javax.net.ssl.X509TrustManager r2 = (javax.net.ssl.X509TrustManager) r2     // Catch: java.security.GeneralSecurityException -> L47
            com.scandit.datacapture.core.internal.module.https.trusts.WrapperX509TrustManager r0 = new com.scandit.datacapture.core.internal.module.https.trusts.WrapperX509TrustManager     // Catch: java.security.GeneralSecurityException -> L47
            r0.<init>(r2)     // Catch: java.security.GeneralSecurityException -> L47
            r4.<init>(r0)
            return
        L2d:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.security.GeneralSecurityException -> L47
            r4.<init>(r0)     // Catch: java.security.GeneralSecurityException -> L47
            java.lang.String r0 = java.util.Arrays.toString(r1)     // Catch: java.security.GeneralSecurityException -> L47
            r4.append(r0)     // Catch: java.security.GeneralSecurityException -> L47
            java.lang.String r4 = r4.toString()     // Catch: java.security.GeneralSecurityException -> L47
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: java.security.GeneralSecurityException -> L47
            java.lang.String r4 = r4.toString()     // Catch: java.security.GeneralSecurityException -> L47
            r0.<init>(r4)     // Catch: java.security.GeneralSecurityException -> L47
            throw r0     // Catch: java.security.GeneralSecurityException -> L47
        L47:
            r4 = move-exception
            java.lang.AssertionError r0 = new java.lang.AssertionError
            java.lang.String r1 = "No System TLS"
            r0.<init>(r1, r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.module.https.trusts.d.<init>():void");
    }
}
