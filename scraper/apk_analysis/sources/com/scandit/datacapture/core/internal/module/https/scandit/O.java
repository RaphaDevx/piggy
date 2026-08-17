package com.scandit.datacapture.core.internal.module.https.scandit;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class O implements N {
    public final J a;

    public O() {
        J sslSocketFactoryCreator = (J) Q.a.getValue();
        Intrinsics.checkNotNullParameter(sslSocketFactoryCreator, "sslSocketFactoryCreator");
        this.a = sslSocketFactoryCreator;
    }

    public final void a(HttpsURLConnection connection) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        Object value = ((M) this.a).b.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        connection.setSSLSocketFactory((SSLSocketFactory) value);
    }
}
