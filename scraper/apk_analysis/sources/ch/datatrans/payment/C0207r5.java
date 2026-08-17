package ch.datatrans.payment;

import ch.datatrans.payment.exception.BackendException;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.core.HttpHeaders;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* renamed from: ch.datatrans.payment.r5, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0207r5 {
    public static C0242vb a;

    public static InputStream a(URL url, String str, String str2, byte[] bArr, Map map, int i, int i2, boolean z, int i3) {
        boolean z2 = (i3 & 32) == 0;
        if ((i3 & 64) != 0) {
            i = 30000;
        }
        if ((i3 & 128) != 0) {
            i2 = 30000;
        }
        if ((i3 & 256) != 0) {
            z = false;
        }
        Intrinsics.checkNotNullParameter(url, "url");
        a();
        URLConnection openConnection = url.openConnection();
        Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type javax.net.ssl.HttpsURLConnection");
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) openConnection;
        C0242vb c0242vb = a;
        if (c0242vb != null) {
            httpsURLConnection.setSSLSocketFactory(c0242vb);
        }
        a();
        httpsURLConnection.setRequestMethod("POST");
        httpsURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, str2);
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                httpsURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
        }
        if (z2) {
            httpsURLConnection.setRequestProperty("Client-Name", "android-sdk");
            httpsURLConnection.setRequestProperty("Client-Version", "3.9.2");
        }
        if (str != null) {
            httpsURLConnection.setRequestProperty(HttpHeaders.AUTHORIZATION, "Bearer ".concat(str));
        }
        httpsURLConnection.setConnectTimeout(i);
        httpsURLConnection.setReadTimeout(i2);
        httpsURLConnection.setDoOutput(true);
        a();
        OutputStream outputStream = httpsURLConnection.getOutputStream();
        a();
        outputStream.write(bArr);
        outputStream.flush();
        a();
        a(httpsURLConnection, z);
        InputStream inputStream = httpsURLConnection.getInputStream();
        Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
        return inputStream;
    }

    public static void b() {
        Intrinsics.checkNotNullParameter("d83eef8d51459181bf95a592e9e01ec71352feeb,1bef45e98c7924811d6669afdfabb3e4dd8a31a0,3dd350a5d6a0adeef34a600a65d321d4f8f8d60f,e4dc3d46441e23141719562430ceedd15941ff1c,0f80611c823161d52f28e78d4638b42ce1c6d9e2,cdb99e0b58cd836c95bfd5811c4b953bd4d152c9,0b64947b5b176c11820fc8cc2bc464891857269b", "pkSHA1Hexes");
        Intrinsics.checkNotNullParameter("d83eef8d51459181bf95a592e9e01ec71352feeb,1bef45e98c7924811d6669afdfabb3e4dd8a31a0,3dd350a5d6a0adeef34a600a65d321d4f8f8d60f,e4dc3d46441e23141719562430ceedd15941ff1c,0f80611c823161d52f28e78d4638b42ce1c6d9e2,cdb99e0b58cd836c95bfd5811c4b953bd4d152c9,0b64947b5b176c11820fc8cc2bc464891857269b", "pkSHA1Hexes");
        List split$default = StringsKt.split$default((CharSequence) "d83eef8d51459181bf95a592e9e01ec71352feeb,1bef45e98c7924811d6669afdfabb3e4dd8a31a0,3dd350a5d6a0adeef34a600a65d321d4f8f8d60f,e4dc3d46441e23141719562430ceedd15941ff1c,0f80611c823161d52f28e78d4638b42ce1c6d9e2,cdb99e0b58cd836c95bfd5811c4b953bd4d152c9,0b64947b5b176c11820fc8cc2bc464891857269b", new String[]{FirebaseConstants.SEPARATOR}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(split$default, 10));
        Iterator it = split$default.iterator();
        while (true) {
            if (!it.hasNext()) {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init((KeyStore) null);
                TrustManager trustManager = trustManagerFactory.getTrustManagers()[0];
                Intrinsics.checkNotNull(trustManager, "null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
                a = new C0242vb(new TrustManager[]{new Lc((X509TrustManager) trustManager, arrayList)});
                return;
            }
            String str = (String) it.next();
            int length = str.length();
            byte[] bArr = new byte[length / 2];
            for (int i = 0; i < length; i += 2) {
                bArr[i / 2] = (byte) (Character.digit(str.charAt(i + 1), 16) + (Character.digit(str.charAt(i), 16) << 4));
            }
            arrayList.add(bArr);
        }
    }

    public static String a(InputStream inputStream) {
        a();
        if (inputStream != null) {
            StringWriter stringWriter = new StringWriter();
            char[] cArr = new char[1024];
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                while (true) {
                    int read = bufferedReader.read(cArr);
                    if (read != -1) {
                        a();
                        stringWriter.write(cArr, 0, read);
                    } else {
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(inputStream, null);
                        return stringWriter.toString();
                    }
                }
            } finally {
            }
        } else {
            return "";
        }
    }

    public static void a(int i, InputStream inputStream) {
        String str;
        String str2;
        try {
            Reader inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            try {
                String readText = TextStreamsKt.readText(bufferedReader);
                CloseableKt.closeFinally(bufferedReader, null);
                JSONObject jSONObject = new JSONObject(readText).getJSONObject("error");
                str = jSONObject.getString("code");
                str2 = jSONObject.getString("message");
            } finally {
            }
        } catch (Exception unused) {
            str = "UNKNOWN";
            str2 = "Unknown reason";
        }
        throw new BackendException(str, str2, i, null, null, 16, null);
    }

    public static void a() {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedIOException("Thread interrupted");
        }
    }

    public static void a(HttpsURLConnection httpsURLConnection, boolean z) {
        int responseCode = httpsURLConnection.getResponseCode();
        if (400 > responseCode || responseCode >= 500) {
            return;
        }
        if (responseCode == 404 || responseCode == 418 || responseCode == 423 || responseCode == 429 || responseCode == 408) {
            throw new D7("Error with response code: " + responseCode);
        }
        if (responseCode != 409) {
            InputStream errorStream = httpsURLConnection.getErrorStream();
            Intrinsics.checkNotNullExpressionValue(errorStream, "getErrorStream(...)");
            a(responseCode, errorStream);
            throw null;
        }
        if (z) {
            InputStream errorStream2 = httpsURLConnection.getErrorStream();
            Intrinsics.checkNotNullExpressionValue(errorStream2, "getErrorStream(...)");
            a(responseCode, errorStream2);
            throw null;
        }
        throw new D7("Error with response code: " + responseCode);
    }
}
