package com.getcapacitor.plugin.util;

import android.text.TextUtils;
import android.util.Base64;
import com.getcapacitor.Bridge;
import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.JSValue;
import com.getcapacitor.PluginCall;
import com.salesforce.marketingcloud.http.a;
import io.sentry.protocol.Message;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import org.apache.commons.lang3.BooleanUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class HttpRequestHandler {

    @FunctionalInterface
    public interface ProgressEmitter {
        void emit(Integer num, Integer num2);
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v4 com.getcapacitor.plugin.util.HttpRequestHandler$ResponseType, still in use, count: 1, list:
      (r0v4 com.getcapacitor.plugin.util.HttpRequestHandler$ResponseType) from 0x0042: SPUT (r0v4 com.getcapacitor.plugin.util.HttpRequestHandler$ResponseType) (LINE:50) com.getcapacitor.plugin.util.HttpRequestHandler.ResponseType.DEFAULT com.getcapacitor.plugin.util.HttpRequestHandler$ResponseType
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
    	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
    	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:252)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:180)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class ResponseType {
        ARRAY_BUFFER("arraybuffer"),
        BLOB("blob"),
        DOCUMENT("document"),
        JSON("json"),
        TEXT("text");

        static final ResponseType DEFAULT = new ResponseType("text");
        private final String name;

        public static ResponseType valueOf(String str) {
            return (ResponseType) Enum.valueOf(ResponseType.class, str);
        }

        public static ResponseType[] values() {
            return (ResponseType[]) $VALUES.clone();
        }

        static {
        }

        private ResponseType(String str) {
            this.name = str;
        }

        public static ResponseType parse(String str) {
            for (ResponseType responseType : values()) {
                if (responseType.name.equalsIgnoreCase(str)) {
                    return responseType;
                }
            }
            return DEFAULT;
        }
    }

    public static class HttpURLConnectionBuilder {
        public Integer connectTimeout;
        public CapacitorHttpUrlConnection connection;
        public Boolean disableRedirects;
        public JSObject headers;
        public String method;
        public Integer readTimeout;
        public URL url;

        public HttpURLConnectionBuilder setConnectTimeout(Integer num) {
            this.connectTimeout = num;
            return this;
        }

        public HttpURLConnectionBuilder setReadTimeout(Integer num) {
            this.readTimeout = num;
            return this;
        }

        public HttpURLConnectionBuilder setDisableRedirects(Boolean bool) {
            this.disableRedirects = bool;
            return this;
        }

        public HttpURLConnectionBuilder setHeaders(JSObject jSObject) {
            this.headers = jSObject;
            return this;
        }

        public HttpURLConnectionBuilder setMethod(String str) {
            this.method = str;
            return this;
        }

        public HttpURLConnectionBuilder setUrl(URL url) {
            this.url = url;
            return this;
        }

        public HttpURLConnectionBuilder openConnection() throws IOException {
            CapacitorHttpUrlConnection capacitorHttpUrlConnection = new CapacitorHttpUrlConnection((HttpURLConnection) this.url.openConnection());
            this.connection = capacitorHttpUrlConnection;
            capacitorHttpUrlConnection.setAllowUserInteraction(false);
            this.connection.setRequestMethod(this.method);
            Integer num = this.connectTimeout;
            if (num != null) {
                this.connection.setConnectTimeout(num.intValue());
            }
            Integer num2 = this.readTimeout;
            if (num2 != null) {
                this.connection.setReadTimeout(num2.intValue());
            }
            Boolean bool = this.disableRedirects;
            if (bool != null) {
                this.connection.setDisableRedirects(bool.booleanValue());
            }
            this.connection.setRequestHeaders(this.headers);
            return this;
        }

        public HttpURLConnectionBuilder setUrlParams(JSObject jSObject) throws MalformedURLException, URISyntaxException, JSONException {
            return setUrlParams(jSObject, true);
        }

        public HttpURLConnectionBuilder setUrlParams(JSObject jSObject, boolean z) throws URISyntaxException, MalformedURLException {
            String query = this.url.getQuery();
            if (query == null) {
                query = "";
            }
            Iterator<String> keys = jSObject.keys();
            if (!keys.hasNext()) {
                return this;
            }
            StringBuilder sb = new StringBuilder(query);
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    StringBuilder sb2 = new StringBuilder();
                    JSONArray jSONArray = jSObject.getJSONArray(next);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        addUrlParam(sb2, next, jSONArray.getString(i), z);
                        if (i != jSONArray.length() - 1) {
                            sb2.append("&");
                        }
                    }
                    if (sb.length() > 0) {
                        sb.append("&");
                    }
                    sb.append((CharSequence) sb2);
                } catch (JSONException unused) {
                    if (sb.length() > 0) {
                        sb.append("&");
                    }
                    addUrlParam(sb, next, jSObject.getString(next), z);
                }
            }
            String sb3 = sb.toString();
            URI uri = this.url.toURI();
            StringBuilder sb4 = new StringBuilder();
            sb4.append(uri.getScheme());
            sb4.append("://");
            sb4.append(uri.getAuthority());
            sb4.append(uri.getPath());
            sb4.append(sb3.equals("") ? "" : "?" + sb3);
            sb4.append(uri.getFragment() != null ? uri.getFragment() : "");
            this.url = new URL(sb4.toString());
            return this;
        }

        private static void addUrlParam(StringBuilder sb, String str, String str2, boolean z) {
            if (z) {
                try {
                    str = URLEncoder.encode(str, "UTF-8");
                    str2 = URLEncoder.encode(str2, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e.getCause());
                }
            }
            sb.append(str);
            sb.append("=");
            sb.append(str2);
        }

        public CapacitorHttpUrlConnection build() {
            return this.connection;
        }
    }

    public static JSObject buildResponse(CapacitorHttpUrlConnection capacitorHttpUrlConnection) throws IOException, JSONException {
        return buildResponse(capacitorHttpUrlConnection, ResponseType.DEFAULT);
    }

    public static JSObject buildResponse(CapacitorHttpUrlConnection capacitorHttpUrlConnection, ResponseType responseType) throws IOException, JSONException {
        int responseCode = capacitorHttpUrlConnection.getResponseCode();
        JSObject jSObject = new JSObject();
        jSObject.put("status", responseCode);
        jSObject.put("headers", (Object) buildResponseHeaders(capacitorHttpUrlConnection));
        jSObject.put("url", (Object) capacitorHttpUrlConnection.getURL());
        jSObject.put("data", readData(capacitorHttpUrlConnection, responseType));
        if (capacitorHttpUrlConnection.getErrorStream() != null) {
            jSObject.put("error", true);
        }
        return jSObject;
    }

    public static Object readData(ICapacitorHttpUrlConnection iCapacitorHttpUrlConnection, ResponseType responseType) throws IOException, JSONException {
        InputStream errorStream = iCapacitorHttpUrlConnection.getErrorStream();
        String headerField = iCapacitorHttpUrlConnection.getHeaderField(HttpHeaders.CONTENT_TYPE);
        if (errorStream != null) {
            if (isOneOf(headerField, MimeType.APPLICATION_JSON, MimeType.APPLICATION_VND_API_JSON)) {
                return parseJSON(readStreamAsString(errorStream));
            }
            return readStreamAsString(errorStream);
        }
        if (headerField != null && headerField.contains(MimeType.APPLICATION_JSON.getValue())) {
            return parseJSON(readStreamAsString(iCapacitorHttpUrlConnection.getInputStream()));
        }
        InputStream inputStream = iCapacitorHttpUrlConnection.getInputStream();
        int ordinal = responseType.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            return readStreamAsBase64(inputStream);
        }
        if (ordinal == 3) {
            return parseJSON(readStreamAsString(inputStream));
        }
        return readStreamAsString(inputStream);
    }

    public static boolean isOneOf(String str, MimeType... mimeTypeArr) {
        if (str != null) {
            for (MimeType mimeType : mimeTypeArr) {
                if (str.contains(mimeType.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static JSObject buildResponseHeaders(CapacitorHttpUrlConnection capacitorHttpUrlConnection) {
        JSObject jSObject = new JSObject();
        for (Map.Entry<String, List<String>> entry : capacitorHttpUrlConnection.getHeaderFields().entrySet()) {
            jSObject.put(entry.getKey(), TextUtils.join(", ", entry.getValue()));
        }
        return jSObject;
    }

    public static Object parseJSON(String str) throws JSONException {
        new JSONObject();
        try {
            if ("null".equals(str.trim())) {
                return JSONObject.NULL;
            }
            if ("true".equals(str.trim())) {
                return true;
            }
            if (BooleanUtils.FALSE.equals(str.trim())) {
                return false;
            }
            if (str.trim().length() <= 0) {
                return "";
            }
            if (str.trim().matches("^\".*\"$")) {
                return str.trim().substring(1, str.trim().length() - 1);
            }
            if (str.trim().matches("^-?\\d+$")) {
                return Integer.valueOf(Integer.parseInt(str.trim()));
            }
            if (str.trim().matches("^-?\\d+(\\.\\d+)?$")) {
                return Double.valueOf(Double.parseDouble(str.trim()));
            }
            try {
                return new JSObject(str);
            } catch (JSONException unused) {
                return new JSArray(str);
            }
        } catch (JSONException unused2) {
            return str;
        }
    }

    public static String readStreamAsBase64(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    String encodeToString = Base64.encodeToString(byteArray, 0, byteArray.length, 0);
                    byteArrayOutputStream.close();
                    return encodeToString;
                }
            }
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static String readStreamAsString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            StringBuilder sb = new StringBuilder();
            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                sb.append(readLine);
                readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(System.getProperty("line.separator"));
                }
            }
            String sb2 = sb.toString();
            bufferedReader.close();
            return sb2;
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static JSObject request(PluginCall pluginCall, String str, Bridge bridge) throws IOException, URISyntaxException, JSONException {
        String str2 = str;
        String string = pluginCall.getString("url", "");
        JSObject object = pluginCall.getObject("headers", new JSObject());
        JSObject object2 = pluginCall.getObject(Message.JsonKeys.PARAMS, new JSObject());
        Integer num = pluginCall.getInt("connectTimeout");
        Integer num2 = pluginCall.getInt("readTimeout");
        Boolean bool = pluginCall.getBoolean("disableRedirects");
        Boolean bool2 = pluginCall.getBoolean("shouldEncodeUrlParams", true);
        ResponseType parse = ResponseType.parse(pluginCall.getString("responseType"));
        String string2 = pluginCall.getString("dataType");
        if (str2 == null) {
            str2 = pluginCall.getString("method", "GET");
        }
        String upperCase = str2.toUpperCase(Locale.ROOT);
        boolean z = upperCase.equals(HttpMethod.DELETE) || upperCase.equals("PATCH") || upperCase.equals("POST") || upperCase.equals("PUT");
        String string3 = object.getString("x-cap-user-agent");
        if (string3 != null) {
            object.put(HttpHeaders.USER_AGENT, string3);
        }
        object.remove("x-cap-user-agent");
        if (!object.has(HttpHeaders.USER_AGENT) && !object.has(a.u)) {
            object.put(HttpHeaders.USER_AGENT, bridge.getConfig().getOverriddenUserAgentString());
        }
        URL url = new URL(string);
        CapacitorHttpUrlConnection build = new HttpURLConnectionBuilder().setUrl(url).setMethod(upperCase).setHeaders(object).setUrlParams(object2, bool2.booleanValue()).setConnectTimeout(num).setReadTimeout(num2).setDisableRedirects(bool).openConnection().build();
        if (bridge != null && !isDomainExcludedFromSSL(bridge, url).booleanValue()) {
            build.setSSLSocketFactory(bridge);
        }
        if (z) {
            JSValue jSValue = new JSValue(pluginCall, "data");
            if (jSValue.getValue() != null) {
                build.setDoOutput(true);
                build.setRequestBody(pluginCall, jSValue, string2);
            }
        }
        pluginCall.getData().put("activeCapacitorHttpUrlConnection", (Object) build);
        build.connect();
        JSObject buildResponse = buildResponse(build, parse);
        build.disconnect();
        pluginCall.getData().remove("activeCapacitorHttpUrlConnection");
        return buildResponse;
    }

    public static Boolean isDomainExcludedFromSSL(Bridge bridge, URL url) {
        try {
            Class<?> cls = Class.forName("io.ionic.sslpinning.SSLPinning");
            return (Boolean) cls.getDeclaredMethod("isDomainExcluded", Bridge.class, URL.class).invoke(cls.getDeclaredConstructor(null).newInstance(null), bridge, url);
        } catch (Exception unused) {
            return false;
        }
    }
}
