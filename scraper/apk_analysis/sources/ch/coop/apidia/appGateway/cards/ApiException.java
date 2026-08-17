package ch.coop.apidia.appGateway.cards;

import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class ApiException extends Exception {
    private int code;
    private String responseBody;
    private Map<String, List<String>> responseHeaders;

    public ApiException() {
        this.code = 0;
        this.responseHeaders = null;
        this.responseBody = null;
    }

    public ApiException(Throwable th) {
        super(th);
        this.code = 0;
        this.responseHeaders = null;
        this.responseBody = null;
    }

    public ApiException(String str) {
        super(str);
        this.code = 0;
        this.responseHeaders = null;
        this.responseBody = null;
    }

    public ApiException(String str, Throwable th, int i, Map<String, List<String>> map, String str2) {
        super(str, th);
        this.code = i;
        this.responseHeaders = map;
        this.responseBody = str2;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ApiException(String str, int i, Map<String, List<String>> map, String str2) {
        this(str, null, i, map, str2);
    }

    public ApiException(String str, Throwable th, int i, Map<String, List<String>> map) {
        this(str, th, i, map, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ApiException(int i, Map<String, List<String>> map, String str) {
        this("Response Code: " + i + " Response Body: " + str, null, i, map, str);
    }

    public ApiException(int i, String str) {
        super(str);
        this.responseHeaders = null;
        this.responseBody = null;
        this.code = i;
    }

    public ApiException(int i, String str, Map<String, List<String>> map, String str2) {
        this(i, str);
        this.responseHeaders = map;
        this.responseBody = str2;
    }

    public int getCode() {
        return this.code;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.responseHeaders;
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return String.format("Message: %s%nHTTP response code: %s%nHTTP response body: %s%nHTTP response headers: %s", super.getMessage(), Integer.valueOf(getCode()), getResponseBody(), getResponseHeaders());
    }
}
