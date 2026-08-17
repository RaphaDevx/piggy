package ch.coop.apidia.appGateway.cards;

import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class ApiResponse<T> {
    private final T data;
    private final Map<String, List<String>> headers;
    private final int statusCode;

    public ApiResponse(int i, Map<String, List<String>> map) {
        this(i, map, null);
    }

    public ApiResponse(int i, Map<String, List<String>> map, T t) {
        this.statusCode = i;
        this.headers = map;
        this.data = t;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public Map<String, List<String>> getHeaders() {
        return this.headers;
    }

    public T getData() {
        return this.data;
    }
}
