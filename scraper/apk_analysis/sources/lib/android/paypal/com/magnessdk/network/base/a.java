package lib.android.paypal.com.magnessdk.network.base;

import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLException;

/* loaded from: classes4.dex */
class a implements MagnesNetworking {
    private byte[] b;
    private Uri d;
    private String c = null;
    private final c a = a();
    private Map<String, String> e = new HashMap();

    public c a() throws SSLException {
        return c.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00d5  */
    @Override // lib.android.paypal.com.magnessdk.network.base.MagnesNetworking
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int execute(byte[] r9) {
        /*
            r8 = this;
            r9 = 0
            java.net.URL r0 = new java.net.URL     // Catch: java.lang.Throwable -> Lb0 java.lang.Exception -> Lb4
            android.net.Uri r1 = r8.d     // Catch: java.lang.Throwable -> Lb0 java.lang.Exception -> Lb4
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lb0 java.lang.Exception -> Lb4
            r0.<init>(r1)     // Catch: java.lang.Throwable -> Lb0 java.lang.Exception -> Lb4
            java.net.URLConnection r0 = r0.openConnection()     // Catch: java.lang.Throwable -> Lb0 java.lang.Exception -> Lb4
            javax.net.ssl.HttpsURLConnection r0 = (javax.net.ssl.HttpsURLConnection) r0     // Catch: java.lang.Throwable -> Lb0 java.lang.Exception -> Lb4
            lib.android.paypal.com.magnessdk.g$h$a r1 = lib.android.paypal.com.magnessdk.g.h.a.HTTP_READ_TIMEOUT     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            int r1 = r1.b()     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            r0.setReadTimeout(r1)     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            lib.android.paypal.com.magnessdk.g$h$a r1 = lib.android.paypal.com.magnessdk.g.h.a.HTTP_CONNECT_TIMEOUT     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            int r1 = r1.b()     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            r0.setConnectTimeout(r1)     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            lib.android.paypal.com.magnessdk.g$h$b r1 = lib.android.paypal.com.magnessdk.g.h.b.GET     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            r0.setRequestMethod(r1)     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            java.util.Map<java.lang.String, java.lang.String> r1 = r8.e     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            java.util.Set r1 = r1.entrySet()     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
        L37:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            if (r2 == 0) goto L57
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            java.lang.Object r3 = r2.getKey()     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            java.lang.Object r2 = r2.getValue()     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            r0.setRequestProperty(r3, r2)     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            goto L37
        L57:
            int r1 = r0.getResponseCode()     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            java.lang.String r2 = "correlation-id"
            java.lang.String r2 = r0.getHeaderField(r2)     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            r8.c = r2     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            r2 = 200(0xc8, float:2.8E-43)
            r3 = 0
            if (r1 != r2) goto L94
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            java.io.InputStream r4 = r0.getInputStream()     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            r2.<init>(r4)     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            lib.android.paypal.com.magnessdk.g$h$a r4 = lib.android.paypal.com.magnessdk.g.h.a.READ_BYTE     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L92
            int r4 = r4.b()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L92
            byte[] r4 = new byte[r4]     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L92
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L92
            r5.<init>()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L92
        L7e:
            int r6 = r2.read(r4)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L92
            r7 = -1
            if (r6 == r7) goto L89
            r5.write(r4, r3, r6)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L92
            goto L7e
        L89:
            byte[] r3 = r5.toByteArray()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L92
            r8.b = r3     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L92
            goto L99
        L90:
            r1 = move-exception
            goto Ld9
        L92:
            r1 = move-exception
            goto Lb7
        L94:
            byte[] r2 = new byte[r3]     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            r8.b = r2     // Catch: java.lang.Throwable -> Lab java.lang.Exception -> Lae
            r2 = r9
        L99:
            java.lang.Class r3 = r8.getClass()
            lib.android.paypal.com.magnessdk.o.a(r3, r2)
            java.lang.Class r8 = r8.getClass()
            lib.android.paypal.com.magnessdk.o.a(r8, r9)
            r0.disconnect()
            return r1
        Lab:
            r1 = move-exception
            r2 = r9
            goto Ld9
        Lae:
            r1 = move-exception
            goto Lb6
        Lb0:
            r1 = move-exception
            r0 = r9
            r2 = r0
            goto Ld9
        Lb4:
            r1 = move-exception
            r0 = r9
        Lb6:
            r2 = r9
        Lb7:
            java.lang.Class r3 = r8.getClass()     // Catch: java.lang.Throwable -> L90
            r4 = 3
            lib.android.paypal.com.magnessdk.e.a(r3, r4, r1)     // Catch: java.lang.Throwable -> L90
            lib.android.paypal.com.magnessdk.g$h$c r1 = lib.android.paypal.com.magnessdk.g.h.c.HTTP_STATUS_FAILED     // Catch: java.lang.Throwable -> L90
            int r1 = r1.b()     // Catch: java.lang.Throwable -> L90
            java.lang.Class r3 = r8.getClass()
            lib.android.paypal.com.magnessdk.o.a(r3, r2)
            java.lang.Class r8 = r8.getClass()
            lib.android.paypal.com.magnessdk.o.a(r8, r9)
            if (r0 == 0) goto Ld8
            r0.disconnect()
        Ld8:
            return r1
        Ld9:
            java.lang.Class r3 = r8.getClass()
            lib.android.paypal.com.magnessdk.o.a(r3, r2)
            java.lang.Class r8 = r8.getClass()
            lib.android.paypal.com.magnessdk.o.a(r8, r9)
            if (r0 == 0) goto Lec
            r0.disconnect()
        Lec:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: lib.android.paypal.com.magnessdk.network.base.a.execute(byte[]):int");
    }

    @Override // lib.android.paypal.com.magnessdk.network.base.MagnesNetworking
    public String getPayPalDebugId() {
        return this.c;
    }

    @Override // lib.android.paypal.com.magnessdk.network.base.MagnesNetworking
    public byte[] getResponseContent() {
        return this.b;
    }

    @Override // lib.android.paypal.com.magnessdk.network.base.MagnesNetworking
    public void setHeader(Map<String, String> map) {
        this.e = map;
    }

    @Override // lib.android.paypal.com.magnessdk.network.base.MagnesNetworking
    public void setUri(Uri uri) {
        this.d = uri;
    }
}
