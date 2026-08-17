package ch.datatrans.payment;

import android.util.Log;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import javax.net.ssl.X509TrustManager;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Lc implements X509TrustManager {
    public final /* synthetic */ X509TrustManager a;
    public final /* synthetic */ ArrayList b;

    public Lc(X509TrustManager x509TrustManager, ArrayList arrayList) {
        this.a = x509TrustManager;
        this.b = arrayList;
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkClientTrusted(X509Certificate[] chain, String authType) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(authType, "authType");
        this.a.checkClientTrusted(chain, authType);
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkServerTrusted(X509Certificate[] chain, String authType) {
        byte[] bArr;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(authType, "authType");
        this.a.checkServerTrusted(chain, authType);
        boolean z = false;
        for (X509Certificate x509Certificate : chain) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
                byte[] encoded = x509Certificate.getPublicKey().getEncoded();
                int i = encoded.length > 512 ? 526 : 270;
                if (encoded.length >= i) {
                    messageDigest.update(encoded, encoded.length - i, i);
                } else {
                    messageDigest.update(encoded);
                }
                bArr = messageDigest.digest();
            } catch (Exception e) {
                Log.e("DTPL", "TrustManagersFactory" + e.getLocalizedMessage());
                bArr = null;
            }
            if (bArr != null) {
                Mc.a(bArr);
            }
            ArrayList arrayList = this.b;
            int size = arrayList.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                Object obj = arrayList.get(i2);
                i2++;
                if (Arrays.equals((byte[]) obj, bArr)) {
                    z = true;
                    break;
                }
            }
            if (z) {
                break;
            }
        }
        if (!z) {
            throw new CertificateException("Untrusted certificate chain.");
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public final X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
