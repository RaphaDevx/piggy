package com.scandit.datacapture.core.internal.module.https.scandit;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.core.HttpHeaders;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.core.internal.module.https.scandit.h, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0536h extends Lambda implements Function0 {
    public final /* synthetic */ C0537i a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0536h(C0537i c0537i) {
        super(0);
        this.a = c0537i;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        C0531c c0531c = (C0531c) this.a.a;
        c0531c.getClass();
        try {
            URL url = new URL(c0531c.a);
            URLConnection openConnection = url.openConnection();
            Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type javax.net.ssl.HttpsURLConnection");
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) openConnection;
            ((O) c0531c.d).a(httpsURLConnection);
            for (Map.Entry entry : c0531c.c.entrySet()) {
                httpsURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
            httpsURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, "ScanditInternal");
            int i = AbstractC0530b.a[c0531c.b.ordinal()];
            if (i == 1) {
                httpsURLConnection.setRequestMethod("GET");
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(false);
            } else if (i == 2) {
                httpsURLConnection.setRequestMethod("POST");
                httpsURLConnection.setDoInput(true);
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setChunkedStreamingMode(0);
            }
            InterfaceC0534f interfaceC0534f = c0531c.e;
            URL url2 = httpsURLConnection.getURL();
            Intrinsics.checkNotNullExpressionValue(url2, "getURL(...)");
            ((C0535g) interfaceC0534f).a(url, url2);
            return httpsURLConnection;
        } catch (IOException e) {
            throw new C0543o(e);
        } catch (ClassCastException e2) {
            throw new C0550w(e2);
        } catch (MalformedURLException e3) {
            throw new C0551x(e3);
        } catch (Exception e4) {
            throw new C0553z(e4);
        }
    }
}
