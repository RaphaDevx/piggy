package com.salesforce.marketingcloud.sfmcsdk.components.http;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.config.a;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: Request.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 !2\u00020\u0001:\u0003 !\"BS\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000eJ\r\u0010\u001d\u001a\u00020\u001eH\u0000¢\u0006\u0002\b\u001fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014¨\u0006#"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/http/Request;", "", "method", "", "requestBody", "connectionTimeout", "", "url", "headers", "", "name", "rateLimit", "", "tag", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/util/List;Ljava/lang/String;JLjava/lang/String;)V", "getConnectionTimeout", "()I", "getHeaders", "()Ljava/util/List;", "getMethod", "()Ljava/lang/String;", "getName", "getRateLimit", "()J", "getRequestBody", "getTag", "setTag", "(Ljava/lang/String;)V", "getUrl", "toBuilder", "Lcom/salesforce/marketingcloud/sfmcsdk/components/http/Request$Builder;", "toBuilder$sfmcsdk_release", "Builder", "Companion", "Method", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class Request {
    private static final int DEFAULT_CONNECTION_TIMEOUT = 30000;
    public static final String GET = "GET";
    public static final String PATCH = "PATCH";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final int RESPONSE_REQUEST_FAILED = -100;
    private final int connectionTimeout;
    private final List<String> headers;
    private final String method;
    private final String name;
    private final long rateLimit;
    private final String requestBody;
    private String tag;
    private final String url;

    /* compiled from: Request.kt */
    @Target({ElementType.PARAMETER, ElementType.TYPE_USE})
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/http/Request$Method;", "", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @kotlin.annotation.Target(allowedTargets = {AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.TYPE})
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    public @interface Method {
    }

    public Request(String method, String str, int i, String url, List<String> headers, String name, long j, String str2) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(name, "name");
        this.method = method;
        this.requestBody = str;
        this.connectionTimeout = i;
        this.url = url;
        this.headers = headers;
        this.name = name;
        this.rateLimit = j;
        this.tag = str2;
    }

    public /* synthetic */ Request(String str, String str2, int i, String str3, List list, String str4, long j, String str5, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, str3, list, str4, j, (i2 & 128) != 0 ? null : str5);
    }

    public final String getMethod() {
        return this.method;
    }

    public final String getRequestBody() {
        return this.requestBody;
    }

    public final int getConnectionTimeout() {
        return this.connectionTimeout;
    }

    public final String getUrl() {
        return this.url;
    }

    public final List<String> getHeaders() {
        return this.headers;
    }

    public final String getName() {
        return this.name;
    }

    public final long getRateLimit() {
        return this.rateLimit;
    }

    public final String getTag() {
        return this.tag;
    }

    public final void setTag(String str) {
        this.tag = str;
    }

    public final Builder toBuilder$sfmcsdk_release() {
        Builder headers = new Builder().method(this.method).url(this.url).connectionTimeout(this.connectionTimeout).name(this.name).headers(this.headers);
        String str = this.requestBody;
        if (str != null) {
            headers.requestBody(str);
        }
        return headers;
    }

    /* compiled from: Request.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007J\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u0014\u0010\u0005\u001a\u00020\u00002\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0007J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0007J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u00020\u00002\b\u0010\u000e\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\u000f\u001a\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007J\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0007J\u0016\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/http/Request$Builder;", "", "()V", "connectionTimeout", "", "headers", "", "", "headersMap", "", "method", "name", "rateLimit", "", "requestBody", "tag", "url", "addOrReplaceHeader", "key", "value", "build", "Lcom/salesforce/marketingcloud/sfmcsdk/components/http/Request;", "seconds", "baseUrl", a.j, "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Builder {
        private List<String> headers;
        private String method;
        private String name;
        private long rateLimit;
        private String requestBody;
        private String tag;
        private String url;
        private int connectionTimeout = Request.DEFAULT_CONNECTION_TIMEOUT;
        private Map<String, String> headersMap = new LinkedHashMap();

        public final Builder method(String method) {
            Intrinsics.checkNotNullParameter(method, "method");
            this.method = method;
            return this;
        }

        public final Builder requestBody(String requestBody) {
            this.requestBody = requestBody;
            return this;
        }

        public final Builder url(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            this.url = url;
            return this;
        }

        public final Builder url(String baseUrl, String path) {
            Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
            Intrinsics.checkNotNullParameter(path, "path");
            if (StringsKt.endsWith$default(baseUrl, "/", false, 2, (Object) null)) {
                baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
                Intrinsics.checkNotNullExpressionValue(baseUrl, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            this.url = new URL(baseUrl + path).toString();
            return this;
        }

        public final Builder name(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.name = name;
            return this;
        }

        public final Builder rateLimit(long seconds) {
            this.rateLimit = TimeUnit.SECONDS.toMillis(seconds);
            return this;
        }

        public final Builder connectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        public final Builder headers(List<String> headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            this.headers = headers;
            return this;
        }

        public final Builder addOrReplaceHeader(String key, String value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            this.headersMap.put(key, StringsKt.trim((CharSequence) value).toString());
            return this;
        }

        public final Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public final Request build() {
            ArrayList arrayList;
            List<String> list = this.headers;
            if (list == null || (arrayList = CollectionsKt.toMutableList((Collection) list)) == null) {
                arrayList = new ArrayList();
            }
            for (Map.Entry<String, String> entry : this.headersMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (arrayList != null) {
                    arrayList.add(key);
                }
                if (arrayList != null) {
                    arrayList.add(value);
                }
            }
            String str = this.method;
            if (str == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            String str2 = this.url;
            if (str2 == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            int i = this.connectionTimeout;
            String str3 = this.requestBody;
            List list2 = arrayList != null ? CollectionsKt.toList(arrayList) : null;
            if (list2 == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            String str4 = this.name;
            if (str4 != null) {
                return new Request(str, str3, i, str2, list2, str4, this.rateLimit, this.tag);
            }
            throw new IllegalStateException("Required value was null.".toString());
        }
    }
}
