package lib.android.paypal.com.magnessdk.network.base;

import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLException;

/* loaded from: classes4.dex */
class b implements MagnesNetworking {
    private byte[] b;
    private Uri d;
    private String c = null;
    private final c a = a();
    private Map<String, String> e = new HashMap();

    private c a() throws SSLException {
        return c.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x010c  */
    @Override // lib.android.paypal.com.magnessdk.network.base.MagnesNetworking
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int execute(byte[] r9) {
        /*
            Method dump skipped, instructions count: 272
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: lib.android.paypal.com.magnessdk.network.base.b.execute(byte[]):int");
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
