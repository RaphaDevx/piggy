package ch.coop.apidia.appGateway.loyalty;

import ch.coop.apidia.appGateway.loyalty.auth.ApiKeyAuth;
import ch.coop.apidia.appGateway.loyalty.auth.Authentication;
import ch.coop.apidia.appGateway.loyalty.auth.HttpBasicAuth;
import ch.coop.apidia.appGateway.loyalty.auth.HttpBearerAuth;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import net.openid.appauth.AuthorizationResponse;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class ApiClient {
    private Map<String, Authentication> authentications;
    private String basePath;
    private DateFormat dateFormat;
    private int dateLength;
    private DateFormat datetimeFormat;
    private boolean debugging;
    private Map<String, String> defaultCookieMap;
    private Map<String, String> defaultHeaderMap;
    private OkHttpClient httpClient;
    private JSON json;
    private KeyManager[] keyManagers;
    private boolean lenientDatetimeFormat;
    private HttpLoggingInterceptor loggingInterceptor;
    protected Integer serverIndex;
    protected Map<String, String> serverVariables;
    protected List<ServerConfiguration> servers;
    private InputStream sslCaCert;
    private String tempFolderPath;
    private boolean verifyingSsl;

    public ApiClient() {
        this.basePath = "https://entw-api.supercard.ch/loyalty-programs/api/v1";
        this.servers = new ArrayList(Arrays.asList(new ServerConfiguration("https://{environment}/loyalty-programs/api/{version}", "App Gateway BDS Loyalty", new HashMap<String, ServerVariable>() { // from class: ch.coop.apidia.appGateway.loyalty.ApiClient.1
            {
                put("environment", new ServerVariable("Environment", "entw-api.supercard.ch", new HashSet(Arrays.asList("entw-api.supercard.ch", "test-api.supercard.ch", "api.supercard.ch", "bds-agwloyalty-develop.coop-appgateway-staging1.sz-gc.coop.ch", "bds-agwloyalty-gkesz-test.coop-appgateway-staging1.sz-gc.coop.ch"))));
                put("version", new ServerVariable("API version", "v1", new HashSet(Arrays.asList("v1"))));
            }
        })));
        this.serverIndex = 0;
        this.serverVariables = null;
        this.debugging = false;
        this.defaultHeaderMap = new HashMap();
        this.defaultCookieMap = new HashMap();
        this.tempFolderPath = null;
        init();
        initHttpClient();
        this.authentications.put("openId", new HttpBearerAuth(AuthorizationResponse.TOKEN_TYPE_BEARER));
        this.authentications = Collections.unmodifiableMap(this.authentications);
    }

    public ApiClient(OkHttpClient okHttpClient) {
        this.basePath = "https://entw-api.supercard.ch/loyalty-programs/api/v1";
        this.servers = new ArrayList(Arrays.asList(new ServerConfiguration("https://{environment}/loyalty-programs/api/{version}", "App Gateway BDS Loyalty", new HashMap<String, ServerVariable>() { // from class: ch.coop.apidia.appGateway.loyalty.ApiClient.1
            {
                put("environment", new ServerVariable("Environment", "entw-api.supercard.ch", new HashSet(Arrays.asList("entw-api.supercard.ch", "test-api.supercard.ch", "api.supercard.ch", "bds-agwloyalty-develop.coop-appgateway-staging1.sz-gc.coop.ch", "bds-agwloyalty-gkesz-test.coop-appgateway-staging1.sz-gc.coop.ch"))));
                put("version", new ServerVariable("API version", "v1", new HashSet(Arrays.asList("v1"))));
            }
        })));
        this.serverIndex = 0;
        this.serverVariables = null;
        this.debugging = false;
        this.defaultHeaderMap = new HashMap();
        this.defaultCookieMap = new HashMap();
        this.tempFolderPath = null;
        init();
        this.httpClient = okHttpClient;
        this.authentications.put("openId", new HttpBearerAuth(AuthorizationResponse.TOKEN_TYPE_BEARER));
        this.authentications = Collections.unmodifiableMap(this.authentications);
    }

    private void initHttpClient() {
        initHttpClient(Collections.emptyList());
    }

    private void initHttpClient(List<Interceptor> list) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addNetworkInterceptor(getProgressInterceptor());
        Iterator<Interceptor> it = list.iterator();
        while (it.hasNext()) {
            builder.addInterceptor(it.next());
        }
        this.httpClient = builder.build();
    }

    private void init() {
        this.verifyingSsl = true;
        this.json = new JSON();
        setUserAgent("OpenAPI-Generator/1.5.8/java");
        this.authentications = new HashMap();
    }

    public String getBasePath() {
        return this.basePath;
    }

    public ApiClient setBasePath(String str) {
        this.basePath = str;
        this.serverIndex = null;
        return this;
    }

    public List<ServerConfiguration> getServers() {
        return this.servers;
    }

    public ApiClient setServers(List<ServerConfiguration> list) {
        this.servers = list;
        return this;
    }

    public Integer getServerIndex() {
        return this.serverIndex;
    }

    public ApiClient setServerIndex(Integer num) {
        this.serverIndex = num;
        return this;
    }

    public Map<String, String> getServerVariables() {
        return this.serverVariables;
    }

    public ApiClient setServerVariables(Map<String, String> map) {
        this.serverVariables = map;
        return this;
    }

    public OkHttpClient getHttpClient() {
        return this.httpClient;
    }

    public ApiClient setHttpClient(OkHttpClient okHttpClient) {
        this.httpClient = (OkHttpClient) Objects.requireNonNull(okHttpClient, "HttpClient must not be null!");
        return this;
    }

    public JSON getJSON() {
        return this.json;
    }

    public ApiClient setJSON(JSON json) {
        this.json = json;
        return this;
    }

    public boolean isVerifyingSsl() {
        return this.verifyingSsl;
    }

    public ApiClient setVerifyingSsl(boolean z) {
        this.verifyingSsl = z;
        applySslSettings();
        return this;
    }

    public InputStream getSslCaCert() {
        return this.sslCaCert;
    }

    public ApiClient setSslCaCert(InputStream inputStream) {
        this.sslCaCert = inputStream;
        applySslSettings();
        return this;
    }

    public KeyManager[] getKeyManagers() {
        return this.keyManagers;
    }

    public ApiClient setKeyManagers(KeyManager[] keyManagerArr) {
        this.keyManagers = keyManagerArr;
        applySslSettings();
        return this;
    }

    public DateFormat getDateFormat() {
        return this.dateFormat;
    }

    public ApiClient setDateFormat(DateFormat dateFormat) {
        JSON.setDateFormat(dateFormat);
        return this;
    }

    public ApiClient setSqlDateFormat(DateFormat dateFormat) {
        JSON.setSqlDateFormat(dateFormat);
        return this;
    }

    public ApiClient setOffsetDateTimeFormat(DateTimeFormatter dateTimeFormatter) {
        JSON.setOffsetDateTimeFormat(dateTimeFormatter);
        return this;
    }

    public ApiClient setLocalDateFormat(DateTimeFormatter dateTimeFormatter) {
        JSON.setLocalDateFormat(dateTimeFormatter);
        return this;
    }

    public ApiClient setLenientOnJson(boolean z) {
        JSON.setLenientOnJson(z);
        return this;
    }

    public Map<String, Authentication> getAuthentications() {
        return this.authentications;
    }

    public Authentication getAuthentication(String str) {
        return this.authentications.get(str);
    }

    public void setBearerToken(String str) {
        for (Authentication authentication : this.authentications.values()) {
            if (authentication instanceof HttpBearerAuth) {
                ((HttpBearerAuth) authentication).setBearerToken(str);
                return;
            }
        }
        throw new RuntimeException("No Bearer authentication configured!");
    }

    public void setUsername(String str) {
        for (Authentication authentication : this.authentications.values()) {
            if (authentication instanceof HttpBasicAuth) {
                ((HttpBasicAuth) authentication).setUsername(str);
                return;
            }
        }
        throw new RuntimeException("No HTTP basic authentication configured!");
    }

    public void setPassword(String str) {
        for (Authentication authentication : this.authentications.values()) {
            if (authentication instanceof HttpBasicAuth) {
                ((HttpBasicAuth) authentication).setPassword(str);
                return;
            }
        }
        throw new RuntimeException("No HTTP basic authentication configured!");
    }

    public void setApiKey(String str) {
        for (Authentication authentication : this.authentications.values()) {
            if (authentication instanceof ApiKeyAuth) {
                ((ApiKeyAuth) authentication).setApiKey(str);
                return;
            }
        }
        throw new RuntimeException("No API key authentication configured!");
    }

    public void setApiKeyPrefix(String str) {
        for (Authentication authentication : this.authentications.values()) {
            if (authentication instanceof ApiKeyAuth) {
                ((ApiKeyAuth) authentication).setApiKeyPrefix(str);
                return;
            }
        }
        throw new RuntimeException("No API key authentication configured!");
    }

    public void setAccessToken(String str) {
        throw new RuntimeException("No OAuth2 authentication configured!");
    }

    public void setAWS4Configuration(String str, String str2, String str3, String str4) {
        throw new RuntimeException("No AWS4 authentication configured!");
    }

    public ApiClient setUserAgent(String str) {
        addDefaultHeader(HttpHeaders.USER_AGENT, str);
        return this;
    }

    public ApiClient addDefaultHeader(String str, String str2) {
        this.defaultHeaderMap.put(str, str2);
        return this;
    }

    public ApiClient addDefaultCookie(String str, String str2) {
        this.defaultCookieMap.put(str, str2);
        return this;
    }

    public boolean isDebugging() {
        return this.debugging;
    }

    public ApiClient setDebugging(boolean z) {
        if (z != this.debugging) {
            if (z) {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                this.loggingInterceptor = httpLoggingInterceptor;
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                this.httpClient = this.httpClient.newBuilder().addInterceptor(this.loggingInterceptor).build();
            } else {
                OkHttpClient.Builder newBuilder = this.httpClient.newBuilder();
                newBuilder.interceptors().remove(this.loggingInterceptor);
                this.httpClient = newBuilder.build();
                this.loggingInterceptor = null;
            }
        }
        this.debugging = z;
        return this;
    }

    public String getTempFolderPath() {
        return this.tempFolderPath;
    }

    public ApiClient setTempFolderPath(String str) {
        this.tempFolderPath = str;
        return this;
    }

    public int getConnectTimeout() {
        return this.httpClient.connectTimeoutMillis();
    }

    public ApiClient setConnectTimeout(int i) {
        this.httpClient = this.httpClient.newBuilder().connectTimeout(i, TimeUnit.MILLISECONDS).build();
        return this;
    }

    public int getReadTimeout() {
        return this.httpClient.readTimeoutMillis();
    }

    public ApiClient setReadTimeout(int i) {
        this.httpClient = this.httpClient.newBuilder().readTimeout(i, TimeUnit.MILLISECONDS).build();
        return this;
    }

    public int getWriteTimeout() {
        return this.httpClient.writeTimeoutMillis();
    }

    public ApiClient setWriteTimeout(int i) {
        this.httpClient = this.httpClient.newBuilder().writeTimeout(i, TimeUnit.MILLISECONDS).build();
        return this;
    }

    public String parameterToString(Object obj) {
        if (obj == null) {
            return "";
        }
        if ((obj instanceof Date) || (obj instanceof OffsetDateTime) || (obj instanceof LocalDate)) {
            String serialize = JSON.serialize(obj);
            return serialize.substring(1, serialize.length() - 1);
        }
        if (obj instanceof Collection) {
            StringBuilder sb = new StringBuilder();
            for (Object obj2 : (Collection) obj) {
                if (sb.length() > 0) {
                    sb.append(FirebaseConstants.SEPARATOR);
                }
                sb.append(obj2);
            }
            return sb.toString();
        }
        return String.valueOf(obj);
    }

    public List<Pair> parameterToPair(String str, Object obj) {
        ArrayList arrayList = new ArrayList();
        if (str != null && !str.isEmpty() && obj != null && !(obj instanceof Collection)) {
            arrayList.add(new Pair(str, parameterToString(obj)));
        }
        return arrayList;
    }

    public List<Pair> parameterToPairs(String str, String str2, Collection collection) {
        String str3;
        ArrayList arrayList = new ArrayList();
        if (str2 != null && !str2.isEmpty() && collection != null && !collection.isEmpty()) {
            if ("multi".equals(str)) {
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    arrayList.add(new Pair(str2, escapeString(parameterToString(it.next()))));
                }
                return arrayList;
            }
            if ("ssv".equals(str)) {
                str3 = escapeString(StringUtils.SPACE);
            } else if ("tsv".equals(str)) {
                str3 = escapeString("\t");
            } else if (!"pipes".equals(str)) {
                str3 = FirebaseConstants.SEPARATOR;
            } else {
                str3 = escapeString("|");
            }
            StringBuilder sb = new StringBuilder();
            for (Object obj : collection) {
                sb.append(str3);
                sb.append(escapeString(parameterToString(obj)));
            }
            arrayList.add(new Pair(str2, sb.substring(str3.length())));
        }
        return arrayList;
    }

    public String collectionPathParameterToString(String str, Collection collection) {
        String str2;
        if ("multi".equals(str)) {
            return parameterToString(collection);
        }
        if ("ssv".equals(str)) {
            str2 = StringUtils.SPACE;
        } else if ("tsv".equals(str)) {
            str2 = "\t";
        } else if (!"pipes".equals(str)) {
            str2 = FirebaseConstants.SEPARATOR;
        } else {
            str2 = "|";
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj : collection) {
            sb.append(str2);
            sb.append(parameterToString(obj));
        }
        return sb.substring(str2.length());
    }

    public String sanitizeFilename(String str) {
        return str.replaceAll(".*[/\\\\]", "");
    }

    public boolean isJsonMime(String str) {
        return str != null && (str.matches("(?i)^(application/json|[^;/ \t]+/[^;/ \t]+[+]json)[ \t]*(;.*)?$") || str.equals(MediaType.WILDCARD));
    }

    public String selectHeaderAccept(String[] strArr) {
        if (strArr.length == 0) {
            return null;
        }
        for (String str : strArr) {
            if (isJsonMime(str)) {
                return str;
            }
        }
        return StringUtil.join(strArr, FirebaseConstants.SEPARATOR);
    }

    public String selectHeaderContentType(String[] strArr) {
        if (strArr.length == 0) {
            return null;
        }
        if (strArr[0].equals(MediaType.WILDCARD)) {
            return MediaType.APPLICATION_JSON;
        }
        for (String str : strArr) {
            if (isJsonMime(str)) {
                return str;
            }
        }
        return strArr[0];
    }

    public String escapeString(String str) {
        try {
            return URLEncoder.encode(str, "utf8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T deserialize(Response response, Type type) throws ApiException {
        if (response == null || type == null) {
            return null;
        }
        if ("byte[]".equals(type.toString())) {
            try {
                return (T) response.body().bytes();
            } catch (IOException e) {
                throw new ApiException(e);
            }
        }
        if (type.equals(File.class)) {
            return (T) downloadFileFromResponse(response);
        }
        try {
            Object obj = response.body() != null ? (T) response.body().string() : (T) null;
            if (obj == null || "".equals(obj)) {
                return null;
            }
            String str = response.headers().get(HttpHeaders.CONTENT_TYPE);
            if (str == null) {
                str = MediaType.APPLICATION_JSON;
            }
            if (isJsonMime(str)) {
                return (T) JSON.deserialize((String) obj, type);
            }
            if (type.equals(String.class)) {
                return (T) obj;
            }
            throw new ApiException("Content type \"" + str + "\" is not supported for type: " + type, response.code(), response.headers().toMultimap(), (String) obj);
        } catch (IOException e2) {
            throw new ApiException(e2);
        }
    }

    public RequestBody serialize(Object obj, String str) throws ApiException {
        if (obj instanceof byte[]) {
            return RequestBody.create((byte[]) obj, okhttp3.MediaType.parse(str));
        }
        if (obj instanceof File) {
            return RequestBody.create((File) obj, okhttp3.MediaType.parse(str));
        }
        if ("text/plain".equals(str) && (obj instanceof String)) {
            return RequestBody.create((String) obj, okhttp3.MediaType.parse(str));
        }
        if (isJsonMime(str)) {
            return RequestBody.create(obj != null ? JSON.serialize(obj) : null, okhttp3.MediaType.parse(str));
        }
        if (obj instanceof String) {
            return RequestBody.create((String) obj, okhttp3.MediaType.parse(str));
        }
        throw new ApiException("Content type \"" + str + "\" is not supported");
    }

    public File downloadFileFromResponse(Response response) throws ApiException {
        try {
            File prepareDownloadFile = prepareDownloadFile(response);
            BufferedSink buffer = Okio.buffer(Okio.sink(prepareDownloadFile));
            buffer.writeAll(response.body().getSource());
            buffer.close();
            return prepareDownloadFile;
        } catch (IOException e) {
            throw new ApiException(e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.io.File prepareDownloadFile(okhttp3.Response r7) throws java.io.IOException {
        /*
            r6 = this;
            java.lang.String r0 = "Content-Disposition"
            java.lang.String r7 = r7.header(r0)
            java.lang.String r0 = ""
            r1 = 0
            if (r7 == 0) goto L2b
            boolean r2 = r0.equals(r7)
            if (r2 != 0) goto L2b
            java.lang.String r2 = "filename=['\"]?([^'\"\\s]+)['\"]?"
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2)
            java.util.regex.Matcher r7 = r2.matcher(r7)
            boolean r2 = r7.find()
            if (r2 == 0) goto L2b
            r2 = 1
            java.lang.String r7 = r7.group(r2)
            java.lang.String r7 = r6.sanitizeFilename(r7)
            goto L2c
        L2b:
            r7 = r1
        L2c:
            java.lang.String r2 = "download-"
            r3 = 0
            if (r7 != 0) goto L32
            goto L70
        L32:
            java.lang.String r0 = "."
            int r0 = r7.lastIndexOf(r0)
            r4 = -1
            java.lang.String r5 = "-"
            if (r0 != r4) goto L4e
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r7)
            r0.append(r5)
            java.lang.String r7 = r0.toString()
            r0 = r1
            goto L67
        L4e:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = r7.substring(r3, r0)
            r1.append(r4)
            r1.append(r5)
            java.lang.String r1 = r1.toString()
            java.lang.String r7 = r7.substring(r0)
            r0 = r7
            r7 = r1
        L67:
            int r1 = r7.length()
            r4 = 3
            if (r1 >= r4) goto L6f
            goto L70
        L6f:
            r2 = r7
        L70:
            java.lang.String r6 = r6.tempFolderPath
            if (r6 != 0) goto L7f
            java.nio.file.attribute.FileAttribute[] r6 = new java.nio.file.attribute.FileAttribute[r3]
            java.nio.file.Path r6 = java.nio.file.Files.createTempFile(r2, r0, r6)
            java.io.File r6 = r6.toFile()
            return r6
        L7f:
            java.lang.String[] r7 = new java.lang.String[r3]
            java.nio.file.Path r6 = java.nio.file.Paths.get(r6, r7)
            java.nio.file.attribute.FileAttribute[] r7 = new java.nio.file.attribute.FileAttribute[r3]
            java.nio.file.Path r6 = java.nio.file.Files.createTempFile(r6, r2, r0, r7)
            java.io.File r6 = r6.toFile()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.coop.apidia.appGateway.loyalty.ApiClient.prepareDownloadFile(okhttp3.Response):java.io.File");
    }

    public <T> ApiResponse<T> execute(Call call) throws ApiException {
        return execute(call, null);
    }

    public <T> ApiResponse<T> execute(Call call, Type type) throws ApiException {
        try {
            Response execute = call.execute();
            return new ApiResponse<>(execute.code(), execute.headers().toMultimap(), handleResponse(execute, type));
        } catch (IOException e) {
            throw new ApiException(e);
        }
    }

    public <T> void executeAsync(Call call, ApiCallback<T> apiCallback) {
        executeAsync(call, null, apiCallback);
    }

    public <T> void executeAsync(Call call, final Type type, final ApiCallback<T> apiCallback) {
        call.enqueue(new Callback() { // from class: ch.coop.apidia.appGateway.loyalty.ApiClient.2
            @Override // okhttp3.Callback
            public void onFailure(Call call2, IOException iOException) {
                apiCallback.onFailure(new ApiException(iOException), 0, null);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call2, Response response) throws IOException {
                try {
                    apiCallback.onSuccess(ApiClient.this.handleResponse(response, type), response.code(), response.headers().toMultimap());
                } catch (ApiException e) {
                    apiCallback.onFailure(e, response.code(), response.headers().toMultimap());
                } catch (Exception e2) {
                    apiCallback.onFailure(new ApiException(e2), response.code(), response.headers().toMultimap());
                }
            }
        });
    }

    public <T> T handleResponse(Response response, Type type) throws ApiException {
        String str = null;
        if (response.isSuccessful()) {
            if (type == null || response.code() == 204) {
                if (response.body() != null) {
                    try {
                        response.body().close();
                    } catch (Exception e) {
                        throw new ApiException(response.message(), e, response.code(), response.headers().toMultimap());
                    }
                }
                return null;
            }
            return (T) deserialize(response, type);
        }
        if (response.body() != null) {
            try {
                str = response.body().string();
            } catch (IOException e2) {
                throw new ApiException(response.message(), e2, response.code(), response.headers().toMultimap());
            }
        }
        throw new ApiException(response.message(), response.code(), response.headers().toMultimap(), str);
    }

    public Call buildCall(String str, String str2, String str3, List<Pair> list, List<Pair> list2, Object obj, Map<String, String> map, Map<String, String> map2, Map<String, Object> map3, String[] strArr, ApiCallback apiCallback) throws ApiException {
        return this.httpClient.newCall(buildRequest(str, str2, str3, list, list2, obj, map, map2, map3, strArr, apiCallback));
    }

    public Request buildRequest(String str, String str2, String str3, List<Pair> list, List<Pair> list2, Object obj, Map<String, String> map, Map<String, String> map2, Map<String, Object> map3, String[] strArr, ApiCallback apiCallback) throws ApiException {
        ArrayList arrayList = new ArrayList(list);
        arrayList.addAll(list2);
        String buildUrl = buildUrl(str, str2, list, list2);
        String str4 = map.get(HttpHeaders.CONTENT_TYPE);
        RequestBody requestBody = null;
        requestBody = null;
        if (HttpMethod.permitsRequestBody(str3)) {
            if ("application/x-www-form-urlencoded".equals(str4)) {
                requestBody = buildRequestBodyFormEncoding(map3);
            } else if ("multipart/form-data".equals(str4)) {
                requestBody = buildRequestBodyMultipart(map3);
            } else if (obj == null) {
                if (!javax.ws.rs.HttpMethod.DELETE.equals(str3)) {
                    requestBody = RequestBody.create("", str4 != null ? okhttp3.MediaType.parse(str4) : null);
                }
            } else {
                requestBody = serialize(obj, str4);
            }
        }
        RequestBody requestBody2 = requestBody;
        updateParamsForAuth(strArr, arrayList, map, map2, requestBodyToString(requestBody2), str3, URI.create(buildUrl));
        Request.Builder url = new Request.Builder().url(buildUrl);
        processHeaderParams(map, url);
        processCookieParams(map2, url);
        url.tag(apiCallback);
        if (apiCallback != null && requestBody2 != null) {
            return url.method(str3, new ProgressRequestBody(requestBody2, apiCallback)).build();
        }
        return url.method(str3, requestBody2).build();
    }

    public String buildUrl(String str, String str2, List<Pair> list, List<Pair> list2) {
        String str3;
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
            sb.append(str2);
        } else {
            Integer num = this.serverIndex;
            if (num != null) {
                if (num.intValue() < 0 || this.serverIndex.intValue() >= this.servers.size()) {
                    throw new ArrayIndexOutOfBoundsException(String.format("Invalid index %d when selecting the host settings. Must be less than %d", this.serverIndex, Integer.valueOf(this.servers.size())));
                }
                str3 = this.servers.get(this.serverIndex.intValue()).URL(this.serverVariables);
            } else {
                str3 = this.basePath;
            }
            sb.append(str3);
            sb.append(str2);
        }
        if (list != null && !list.isEmpty()) {
            String str4 = str2.contains("?") ? "&" : "?";
            for (Pair pair : list) {
                if (pair.getValue() != null) {
                    if (str4 != null) {
                        sb.append(str4);
                        str4 = null;
                    } else {
                        sb.append("&");
                    }
                    String parameterToString = parameterToString(pair.getValue());
                    sb.append(escapeString(pair.getName()));
                    sb.append("=");
                    sb.append(escapeString(parameterToString));
                }
            }
        }
        if (list2 != null && !list2.isEmpty()) {
            String str5 = sb.toString().contains("?") ? "&" : "?";
            for (Pair pair2 : list2) {
                if (pair2.getValue() != null) {
                    if (str5 != null) {
                        sb.append(str5);
                        str5 = null;
                    } else {
                        sb.append("&");
                    }
                    String parameterToString2 = parameterToString(pair2.getValue());
                    sb.append(escapeString(pair2.getName()));
                    sb.append("=");
                    sb.append(parameterToString2);
                }
            }
        }
        return sb.toString();
    }

    public void processHeaderParams(Map<String, String> map, Request.Builder builder) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.header(entry.getKey(), parameterToString(entry.getValue()));
        }
        for (Map.Entry<String, String> entry2 : this.defaultHeaderMap.entrySet()) {
            if (!map.containsKey(entry2.getKey())) {
                builder.header(entry2.getKey(), parameterToString(entry2.getValue()));
            }
        }
    }

    public void processCookieParams(Map<String, String> map, Request.Builder builder) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.addHeader("Cookie", String.format("%s=%s", entry.getKey(), entry.getValue()));
        }
        for (Map.Entry<String, String> entry2 : this.defaultCookieMap.entrySet()) {
            if (!map.containsKey(entry2.getKey())) {
                builder.addHeader("Cookie", String.format("%s=%s", entry2.getKey(), entry2.getValue()));
            }
        }
    }

    public void updateParamsForAuth(String[] strArr, List<Pair> list, Map<String, String> map, Map<String, String> map2, String str, String str2, URI uri) throws ApiException {
        for (String str3 : strArr) {
            Authentication authentication = this.authentications.get(str3);
            if (authentication == null) {
                throw new RuntimeException("Authentication undefined: " + str3);
            }
            authentication.applyToParams(list, map, map2, str, str2, uri);
        }
    }

    public RequestBody buildRequestBodyFormEncoding(Map<String, Object> map) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            builder.add(entry.getKey(), parameterToString(entry.getValue()));
        }
        return builder.build();
    }

    public RequestBody buildRequestBodyMultipart(Map<String, Object> map) {
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof File) {
                addPartToMultiPartBuilder(type, entry.getKey(), (File) entry.getValue());
            } else if (entry.getValue() instanceof List) {
                for (Object obj : (List) entry.getValue()) {
                    if (obj instanceof File) {
                        addPartToMultiPartBuilder(type, entry.getKey(), (File) obj);
                    } else {
                        addPartToMultiPartBuilder(type, entry.getKey(), entry.getValue());
                    }
                }
            } else {
                addPartToMultiPartBuilder(type, entry.getKey(), entry.getValue());
            }
        }
        return type.build();
    }

    public String guessContentTypeFromFile(File file) {
        String guessContentTypeFromName = URLConnection.guessContentTypeFromName(file.getName());
        return guessContentTypeFromName == null ? MediaType.APPLICATION_OCTET_STREAM : guessContentTypeFromName;
    }

    private void addPartToMultiPartBuilder(MultipartBody.Builder builder, String str, File file) {
        builder.addPart(Headers.of(HttpHeaders.CONTENT_DISPOSITION, "form-data; name=\"" + str + "\"; filename=\"" + file.getName() + "\""), RequestBody.create(file, okhttp3.MediaType.parse(guessContentTypeFromFile(file))));
    }

    private void addPartToMultiPartBuilder(MultipartBody.Builder builder, String str, Object obj) {
        RequestBody create;
        if (obj instanceof String) {
            create = RequestBody.create((String) obj, okhttp3.MediaType.parse("text/plain"));
        } else {
            create = RequestBody.create(obj != null ? JSON.serialize(obj) : null, okhttp3.MediaType.parse(MediaType.APPLICATION_JSON));
        }
        builder.addPart(Headers.of(HttpHeaders.CONTENT_DISPOSITION, "form-data; name=\"" + str + "\""), create);
    }

    private Interceptor getProgressInterceptor() {
        return new Interceptor() { // from class: ch.coop.apidia.appGateway.loyalty.ApiClient.3
            @Override // okhttp3.Interceptor
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request();
                Response proceed = chain.proceed(request);
                if (!(request.tag() instanceof ApiCallback)) {
                    return proceed;
                }
                return proceed.newBuilder().body(new ProgressResponseBody(proceed.body(), (ApiCallback) request.tag())).build();
            }
        };
    }

    private void applySslSettings() {
        TrustManager[] trustManagers;
        HostnameVerifier hostnameVerifier;
        try {
            if (!this.verifyingSsl) {
                trustManagers = new TrustManager[]{new X509TrustManager() { // from class: ch.coop.apidia.appGateway.loyalty.ApiClient.4
                    @Override // javax.net.ssl.X509TrustManager
                    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                    }

                    @Override // javax.net.ssl.X509TrustManager
                    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                    }

                    @Override // javax.net.ssl.X509TrustManager
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                }};
                hostnameVerifier = new HostnameVerifier() { // from class: ch.coop.apidia.appGateway.loyalty.ApiClient.5
                    @Override // javax.net.ssl.HostnameVerifier
                    public boolean verify(String str, SSLSession sSLSession) {
                        return true;
                    }
                };
            } else {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                if (this.sslCaCert == null) {
                    trustManagerFactory.init((KeyStore) null);
                } else {
                    Collection<? extends Certificate> generateCertificates = CertificateFactory.getInstance("X.509").generateCertificates(this.sslCaCert);
                    if (generateCertificates.isEmpty()) {
                        throw new IllegalArgumentException("expected non-empty set of trusted certificates");
                    }
                    KeyStore newEmptyKeyStore = newEmptyKeyStore(null);
                    Iterator<? extends Certificate> it = generateCertificates.iterator();
                    int i = 0;
                    while (it.hasNext()) {
                        newEmptyKeyStore.setCertificateEntry("ca" + i, it.next());
                        i++;
                    }
                    trustManagerFactory.init(newEmptyKeyStore);
                }
                trustManagers = trustManagerFactory.getTrustManagers();
                hostnameVerifier = OkHostnameVerifier.INSTANCE;
            }
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(this.keyManagers, trustManagers, new SecureRandom());
            this.httpClient = this.httpClient.newBuilder().sslSocketFactory(sSLContext.getSocketFactory(), (X509TrustManager) trustManagers[0]).hostnameVerifier(hostnameVerifier).build();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    private KeyStore newEmptyKeyStore(char[] cArr) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, cArr);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    private String requestBodyToString(RequestBody requestBody) throws ApiException {
        if (requestBody != null) {
            try {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                return buffer.readUtf8();
            } catch (IOException e) {
                throw new ApiException(e);
            }
        }
        return "";
    }
}
