package com.tealium.internal.k;

import android.net.Uri;
import com.tealium.remotecommands.RemoteCommand;
import io.sentry.SentryLogEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Locale;
import kotlin.text.Typography;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: HttpRemoteCommand.java */
/* loaded from: classes2.dex */
class b extends RemoteCommand {

    /* compiled from: HttpRemoteCommand.java */
    class a implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ RemoteCommand.Response c;

        a(b bVar, String str, String str2, RemoteCommand.Response response) {
            this.a = str;
            this.b = str2;
            this.c = response;
        }

        @Override // java.lang.Runnable
        public void run() {
            HttpURLConnection httpURLConnection;
            BufferedReader bufferedReader;
            BufferedReader bufferedReader2 = null;
            try {
                try {
                    try {
                        httpURLConnection = (HttpURLConnection) new URL(this.b).openConnection();
                        b.b(this.c, httpURLConnection);
                        httpURLConnection.setRequestMethod(this.a);
                        httpURLConnection.setDoInput(true);
                        if ("POST".equals(this.a) || "PUT".equals(this.a)) {
                            httpURLConnection.setDoOutput(true);
                            OutputStream outputStream = httpURLConnection.getOutputStream();
                            outputStream.write(b.b(this.c.getRequestPayload()));
                            outputStream.flush();
                            outputStream.close();
                        }
                        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    } catch (Exception unused) {
                        return;
                    }
                } catch (IOException | JSONException e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                    sb.append('\n');
                }
                this.c.setStatus(httpURLConnection.getResponseCode()).setBody(sb.toString()).send();
                bufferedReader.close();
            } catch (IOException | JSONException e2) {
                e = e2;
                bufferedReader2 = bufferedReader;
                this.c.setStatus(555).setBody(RemoteCommand.Response.stringify(e)).send();
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Exception unused2) {
                    }
                }
                throw th;
            }
        }
    }

    public b() {
        super("_http", "Perform a native HTTP operation");
    }

    private String b(String str, JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("authenticate");
        if (optJSONObject == null) {
            return str;
        }
        String optString = optJSONObject.optString("username");
        String optString2 = optJSONObject.optString("password");
        if (optString == null || optString2 == null) {
            return str;
        }
        String str2 = "http://";
        if (!str.startsWith("http://")) {
            str2 = "https://";
            if (!str.startsWith("https://")) {
                throw new JSONException("Unsupported URL protocol.");
            }
        }
        try {
            return String.format(Locale.ROOT, "%s%s:%s@%s", str2, URLEncoder.encode(optString, "UTF-8"), URLEncoder.encode(optString2, "UTF-8"), str.substring(str2.length()));
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    @Override // com.tealium.remotecommands.RemoteCommand
    protected void onInvoke(RemoteCommand.Response response) throws Exception {
        String optString = response.getRequestPayload().optString("url", null);
        String optString2 = response.getRequestPayload().optString("method", null);
        if (optString == null || optString2 == null) {
            response.setStatus(RemoteCommand.Response.STATUS_BAD_REQUEST).setBody(String.format(Locale.ROOT, "Missing required keys \"%s\" or \"%s\".", "method", "url")).send();
        } else {
            a(response, a(b(optString, response.getRequestPayload()), response.getRequestPayload()), optString2.toUpperCase(Locale.ROOT));
        }
    }

    private String a(String str, JSONObject jSONObject) throws JSONException {
        char c;
        JSONObject optJSONObject = jSONObject.optJSONObject("parameters");
        if (optJSONObject == null) {
            return str;
        }
        Iterator<String> keys = optJSONObject.keys();
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                boolean hasNext = keys.hasNext();
                c = Typography.amp;
                if (!hasNext) {
                    break;
                }
                String next = keys.next();
                String obj = optJSONObject.get(next).toString();
                if (sb.length() > 0) {
                    sb.append(Typography.amp);
                }
                sb.append(URLEncoder.encode(next, "UTF-8"));
                sb.append('=');
                sb.append(URLEncoder.encode(obj, "UTF-8"));
            } catch (UnsupportedEncodingException unused) {
                return str;
            }
        }
        if (str.indexOf(63) <= 0) {
            c = '?';
        }
        sb.insert(0, c);
        return sb.insert(0, str).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] b(JSONObject jSONObject) throws JSONException, UnsupportedEncodingException {
        Charset forName = Charset.forName("UTF-8");
        Object opt = jSONObject.opt(SentryLogEvent.JsonKeys.BODY);
        if (opt instanceof JSONObject) {
            JSONObject jSONObject2 = (JSONObject) opt;
            Iterator<String> keys = jSONObject2.keys();
            Uri.Builder builder = new Uri.Builder();
            while (keys.hasNext()) {
                String next = keys.next();
                builder.appendQueryParameter(next, jSONObject2.optString(next, ""));
            }
            return builder.build().getEncodedQuery().getBytes(forName);
        }
        if (opt instanceof String) {
            return URLEncoder.encode((String) opt, "UTF-8").getBytes(forName);
        }
        if (opt != null) {
            return URLEncoder.encode(opt.toString(), "UTF-8").getBytes(forName);
        }
        return new byte[0];
    }

    private void a(RemoteCommand.Response response, String str, String str2) {
        new Thread(new a(this, str2, str, response)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(RemoteCommand.Response response, HttpURLConnection httpURLConnection) {
        JSONObject optJSONObject = response.getRequestPayload().optJSONObject("headers");
        if (optJSONObject == null) {
            return;
        }
        Iterator<String> keys = optJSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            httpURLConnection.setRequestProperty(next, optJSONObject.optString(next, ""));
        }
    }
}
