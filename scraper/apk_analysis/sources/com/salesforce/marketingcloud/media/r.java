package com.salesforce.marketingcloud.media;

import android.graphics.Bitmap;
import com.salesforce.marketingcloud.media.o;
import com.salesforce.marketingcloud.media.t;
import com.salesforce.marketingcloud.media.v;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import javax.net.ssl.HttpsURLConnection;

/* loaded from: classes2.dex */
public class r extends v {
    private static final String b = com.salesforce.marketingcloud.g.a("NetworkRequestHandler");
    private final s a;

    public r(s sVar) {
        this.a = sVar;
    }

    @Override // com.salesforce.marketingcloud.media.v
    public boolean a(t tVar) {
        try {
            String lowerCase = tVar.a.getScheme().toLowerCase(Locale.ENGLISH);
            return "http".equalsIgnoreCase(lowerCase) || "https".equalsIgnoreCase(lowerCase);
        } catch (Exception e) {
            com.salesforce.marketingcloud.g.a(b, e, "Unable to get scheme from request.", new Object[0]);
            return false;
        }
    }

    @Override // com.salesforce.marketingcloud.media.v
    public void a(o oVar, t tVar, v.a aVar) throws IOException {
        HttpsURLConnection httpsURLConnection;
        String uri = tVar.a.toString();
        Bitmap a = a(uri, tVar);
        if (a != null) {
            aVar.a(new v.b(a, o.b.DISK));
            return;
        }
        com.salesforce.marketingcloud.g.a("IMAGE", "Starting network request for image", new Object[0]);
        HttpURLConnection.setFollowRedirects(true);
        HttpsURLConnection httpsURLConnection2 = null;
        try {
            try {
                httpsURLConnection = (HttpsURLConnection) new URL(uri).openConnection();
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            httpsURLConnection.setUseCaches(false);
            httpsURLConnection.setConnectTimeout(30000);
            httpsURLConnection.setRequestMethod("GET");
            InputStream inputStream = httpsURLConnection.getInputStream();
            if (t.b.c(tVar.d)) {
                byte[] a2 = com.salesforce.marketingcloud.util.e.a(inputStream);
                com.salesforce.marketingcloud.util.e.a((Closeable) inputStream);
                this.a.a(uri, new ByteArrayInputStream(a2));
                inputStream = new ByteArrayInputStream(a2);
            }
            Bitmap a3 = v.a(inputStream, tVar);
            com.salesforce.marketingcloud.util.e.a((Closeable) inputStream);
            aVar.a(new v.b(a3, o.b.NETWORK));
            httpsURLConnection.disconnect();
        } catch (Exception e2) {
            e = e2;
            httpsURLConnection2 = httpsURLConnection;
            com.salesforce.marketingcloud.g.b("IMAGE", e, "Image network error for URL: %s", uri);
            aVar.a(e);
            if (httpsURLConnection2 != null) {
                httpsURLConnection2.disconnect();
            }
        } catch (Throwable th2) {
            th = th2;
            httpsURLConnection2 = httpsURLConnection;
            if (httpsURLConnection2 != null) {
                httpsURLConnection2.disconnect();
            }
            throw th;
        }
    }

    private Bitmap a(String str, t tVar) throws IOException {
        InputStream a = this.a.a(str);
        Bitmap bitmap = null;
        if (a == null) {
            return null;
        }
        try {
            bitmap = v.a(a, tVar);
            com.salesforce.marketingcloud.util.e.a((Closeable) a);
            return bitmap;
        } catch (Exception e) {
            com.salesforce.marketingcloud.g.a(b, e, "Failed to decode cache into Bitmap.", new Object[0]);
            return bitmap;
        }
    }
}
