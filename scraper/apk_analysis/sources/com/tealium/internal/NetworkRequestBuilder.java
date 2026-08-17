package com.tealium.internal;

import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class NetworkRequestBuilder {
    public static final String METHOD_GET = "GET";
    public static final String METHOD_HEAD = "HEAD";
    public static final String METHOD_POST = "POST";
    private final String a;
    private final String b;
    private Map<String, String> c;
    private HttpResponseListener d;
    private String e;
    private JSONObject f;
    private boolean g;

    public interface HttpResponseListener {
        void onHttpError(String str, Throwable th);

        void onHttpResponse(String str, String str2, int i, Map<String, List<String>> map, byte[] bArr);
    }

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(NetworkRequestBuilder.this.a).openConnection();
                httpURLConnection.setRequestMethod(NetworkRequestBuilder.this.b);
                httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
                if (NetworkRequestBuilder.this.g) {
                    httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_ENCODING, "gzip");
                }
                if (NetworkRequestBuilder.this.c != null) {
                    for (Map.Entry entry : NetworkRequestBuilder.this.c.entrySet()) {
                        httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                boolean equals = "HEAD".equals(NetworkRequestBuilder.this.b);
                httpURLConnection.setDoInput(!equals);
                if (NetworkRequestBuilder.this.f != null) {
                    httpURLConnection.setDoOutput(true);
                    DataOutputStream dataOutputStream = NetworkRequestBuilder.this.g ? new DataOutputStream(new GZIPOutputStream(httpURLConnection.getOutputStream())) : new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(NetworkRequestBuilder.this.f.toString().getBytes(StandardCharsets.UTF_8));
                    dataOutputStream.flush();
                    dataOutputStream.close();
                }
                byte[] b = !equals ? NetworkRequestBuilder.b(httpURLConnection.getInputStream()) : null;
                if (NetworkRequestBuilder.this.d == null) {
                    return;
                }
                NetworkRequestBuilder.this.d.onHttpResponse(NetworkRequestBuilder.this.a, NetworkRequestBuilder.this.b, httpURLConnection.getResponseCode(), httpURLConnection.getHeaderFields(), b);
            } catch (Throwable th) {
                if (NetworkRequestBuilder.this.d != null) {
                    NetworkRequestBuilder.this.d.onHttpError(NetworkRequestBuilder.this.a, th);
                }
            }
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HttpURLConnection httpURLConnection;
            Throwable th;
            byte[] b;
            try {
                httpURLConnection = (HttpURLConnection) new URL(NetworkRequestBuilder.this.a).openConnection();
                try {
                    httpURLConnection.setRequestMethod(NetworkRequestBuilder.this.b);
                    httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "*");
                    if (NetworkRequestBuilder.this.c != null) {
                        for (Map.Entry entry : NetworkRequestBuilder.this.c.entrySet()) {
                            httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                        }
                    }
                    boolean equals = "HEAD".equals(NetworkRequestBuilder.this.b);
                    httpURLConnection.setDoInput(!equals);
                    if (NetworkRequestBuilder.this.e != null) {
                        httpURLConnection.setDoOutput(true);
                        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                        dataOutputStream.write(NetworkRequestBuilder.this.e.getBytes("UTF-8"));
                        dataOutputStream.close();
                    } else {
                        httpURLConnection.setDoOutput(false);
                    }
                    httpURLConnection.connect();
                    b = equals ? null : NetworkRequestBuilder.b(httpURLConnection.getInputStream());
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        if (NetworkRequestBuilder.this.d != null) {
                            NetworkRequestBuilder.this.d.onHttpError(NetworkRequestBuilder.this.a, th);
                        }
                        if (httpURLConnection == null) {
                            return;
                        }
                        httpURLConnection.disconnect();
                    } finally {
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                    }
                }
            } catch (Throwable th3) {
                httpURLConnection = null;
                th = th3;
            }
            if (NetworkRequestBuilder.this.d == null) {
                if (httpURLConnection != null) {
                    return;
                } else {
                    return;
                }
            }
            NetworkRequestBuilder.this.d.onHttpResponse(NetworkRequestBuilder.this.a, NetworkRequestBuilder.this.b, httpURLConnection.getResponseCode(), httpURLConnection.getHeaderFields(), b);
            if (httpURLConnection == null) {
                return;
            }
            httpURLConnection.disconnect();
        }
    }

    private NetworkRequestBuilder(String str, String str2) {
        this.a = str;
        if (!TextUtils.isEmpty(str)) {
            this.b = str2;
            if (!TextUtils.isEmpty(str2)) {
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    public static NetworkRequestBuilder createGetRequest(String str) {
        return new NetworkRequestBuilder(str, "GET");
    }

    public static NetworkRequestBuilder createHeadRequest(String str) {
        return new NetworkRequestBuilder(str, "HEAD");
    }

    public static NetworkRequestBuilder createPostRequest(String str) {
        return new NetworkRequestBuilder(str, "POST");
    }

    public NetworkRequestBuilder addHeader(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException();
        }
        if (this.c == null) {
            this.c = new HashMap();
        }
        this.c.put(str, str2);
        return this;
    }

    public Runnable createPostRunnable() {
        return new a();
    }

    public Runnable createRunnable() {
        return new b();
    }

    public NetworkRequestBuilder setJsonBody(JSONObject jSONObject) {
        this.f = jSONObject;
        return this;
    }

    public NetworkRequestBuilder setListener(HttpResponseListener httpResponseListener) {
        this.d = httpResponseListener;
        return this;
    }

    public NetworkRequestBuilder setShouldGzip(boolean z) {
        this.g = z;
        return this;
    }

    public NetworkRequestBuilder setStringBody(String str) {
        this.e = str;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] b(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                inputStream.close();
                return byteArray;
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }
}
