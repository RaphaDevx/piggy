package com.salesforce.marketingcloud.http;

import android.os.Bundle;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.http.e;
import com.salesforce.marketingcloud.internal.o;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import kotlin.Unit;
import kotlin.annotation.AnnotationRetention;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* loaded from: classes2.dex */
public final class b {
    public static final C0057b i = new C0057b(null);
    private static final String j = g.a("Request");
    public static final String k = "GET";
    public static final String l = "POST";
    public static final String m = "PATCH";
    public static final int n = -100;
    private static final int o = 30000;
    private final String a;
    private final String b;
    private final int c;
    private final String d;
    private final String e;
    private final List<String> f;
    private final com.salesforce.marketingcloud.http.a g;
    private String h;

    public static final class a {
        private String a;
        private String b;
        private String d;
        private String e;
        private com.salesforce.marketingcloud.http.a f;
        private List<String> h;
        private int c = b.o;
        private Map<String, String> g = new LinkedHashMap();

        public final a a(String contentType) {
            Intrinsics.checkNotNullParameter(contentType, "contentType");
            this.e = contentType;
            return this;
        }

        public final a b(String method) {
            Intrinsics.checkNotNullParameter(method, "method");
            this.a = method;
            return this;
        }

        public final a c(String requestBody) {
            Intrinsics.checkNotNullParameter(requestBody, "requestBody");
            this.d = requestBody;
            return this;
        }

        public final a d(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            this.b = url;
            return this;
        }

        public final a a(com.salesforce.marketingcloud.http.a requestId) {
            Intrinsics.checkNotNullParameter(requestId, "requestId");
            this.f = requestId;
            return this;
        }

        public final a a(int i) {
            this.c = i;
            return this;
        }

        public final void a(List<String> headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            this.h = headers;
        }

        public final a a(String key, String value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            this.g.put(key, StringsKt.trim((CharSequence) value).toString());
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x004a  */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0054  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x007b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final com.salesforce.marketingcloud.http.b a() {
            /*
                r9 = this;
                java.util.List<java.lang.String> r0 = r9.h
                if (r0 != 0) goto L45
                java.util.Map<java.lang.String, java.lang.String> r0 = r9.g
                boolean r0 = r0.isEmpty()
                if (r0 != 0) goto L41
                java.util.Map<java.lang.String, java.lang.String> r0 = r9.g
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                java.util.Set r0 = r0.entrySet()
                java.util.Iterator r0 = r0.iterator()
            L1b:
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L3f
                java.lang.Object r2 = r0.next()
                java.util.Map$Entry r2 = (java.util.Map.Entry) r2
                java.lang.Object r3 = r2.getKey()
                java.lang.String r3 = (java.lang.String) r3
                java.lang.Object r2 = r2.getValue()
                java.lang.String r2 = (java.lang.String) r2
                java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
                java.util.List r2 = kotlin.collections.CollectionsKt.plus(r3, r2)
                kotlin.collections.CollectionsKt.addAll(r1, r2)
                goto L1b
            L3f:
                r7 = r1
                goto L46
            L41:
                java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
            L45:
                r7 = r0
            L46:
                java.lang.String r3 = r9.d
                if (r3 != 0) goto L4e
                java.lang.String r0 = ""
                r9.e = r0
            L4e:
                java.lang.String r2 = r9.a
                java.lang.String r0 = "Required value was null."
                if (r2 == 0) goto L7b
                java.lang.String r6 = r9.b
                if (r6 == 0) goto L75
                int r4 = r9.c
                java.lang.String r5 = r9.e
                if (r5 == 0) goto L6f
                com.salesforce.marketingcloud.http.a r8 = r9.f
                if (r8 == 0) goto L69
                com.salesforce.marketingcloud.http.b r9 = new com.salesforce.marketingcloud.http.b
                r1 = r9
                r1.<init>(r2, r3, r4, r5, r6, r7, r8)
                return r9
            L69:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                r9.<init>(r0)
                throw r9
            L6f:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                r9.<init>(r0)
                throw r9
            L75:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                r9.<init>(r0)
                throw r9
            L7b:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                r9.<init>(r0)
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.http.b.a.a():com.salesforce.marketingcloud.http.b");
        }
    }

    /* renamed from: com.salesforce.marketingcloud.http.b$b, reason: collision with other inner class name */
    public static final class C0057b {
        public /* synthetic */ C0057b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final a a() {
            return new a();
        }

        public final String b() {
            return b.j;
        }

        private C0057b() {
        }

        @JvmStatic
        public final b a(Bundle data) {
            Intrinsics.checkNotNullParameter(data, "data");
            a a = b.i.a();
            String string = data.getString("method");
            if (string != null) {
                Intrinsics.checkNotNull(string);
                a.b(string);
            }
            String string2 = data.getString("requestBody");
            if (string2 != null) {
                Intrinsics.checkNotNull(string2);
                a.c(string2);
            }
            a.a(data.getInt("connectionTimeout"));
            String string3 = data.getString("contentType");
            if (string3 != null) {
                Intrinsics.checkNotNull(string3);
                a.a(string3);
            }
            String string4 = data.getString("url");
            if (string4 != null) {
                Intrinsics.checkNotNull(string4);
                a.d(string4);
            }
            ArrayList<String> stringArrayList = data.getStringArrayList("headers");
            if (stringArrayList != null) {
                Intrinsics.checkNotNull(stringArrayList);
                a.a(stringArrayList);
            }
            a.a(com.salesforce.marketingcloud.http.a.values()[data.getInt("mcRequestId", 0)]);
            b a2 = a.a();
            a2.a(data.getString("tag"));
            return a2;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    public @interface c {
    }

    static final class d extends Lambda implements Function0<String> {
        public static final d b = new d();

        d() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Unable to complete request";
        }
    }

    public b(String method, String str, int i2, String contentType, String url, List<String> headers, com.salesforce.marketingcloud.http.a requestId) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        this.a = method;
        this.b = str;
        this.c = i2;
        this.d = contentType;
        this.e = url;
        this.f = headers;
        this.g = requestId;
    }

    @JvmStatic
    public static final a b() {
        return i.a();
    }

    public final b a(String method, String str, int i2, String contentType, String url, List<String> headers, com.salesforce.marketingcloud.http.a requestId) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        return new b(method, str, i2, contentType, url, headers, requestId);
    }

    public final String c() {
        return this.a;
    }

    public final String d() {
        return this.b;
    }

    public final int e() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return Intrinsics.areEqual(this.a, bVar.a) && Intrinsics.areEqual(this.b, bVar.b) && this.c == bVar.c && Intrinsics.areEqual(this.d, bVar.d) && Intrinsics.areEqual(this.e, bVar.e) && Intrinsics.areEqual(this.f, bVar.f) && this.g == bVar.g;
    }

    public final String f() {
        return this.d;
    }

    public final String g() {
        return this.e;
    }

    public final List<String> h() {
        return this.f;
    }

    public int hashCode() {
        int hashCode = this.a.hashCode() * 31;
        String str = this.b;
        return ((((((((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.c)) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode();
    }

    public final com.salesforce.marketingcloud.http.a i() {
        return this.g;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final e j() {
        e a2;
        HttpsURLConnection httpsURLConnection;
        long currentTimeMillis = System.currentTimeMillis();
        HttpsURLConnection httpsURLConnection2 = null;
        HttpsURLConnection httpsURLConnection3 = null;
        try {
            try {
                URLConnection openConnection = new URL(this.e).openConnection();
                Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type javax.net.ssl.HttpsURLConnection");
                httpsURLConnection = (HttpsURLConnection) openConnection;
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            httpsURLConnection.setRequestMethod(this.a);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setUseCaches(false);
            httpsURLConnection.setAllowUserInteraction(false);
            httpsURLConnection.setConnectTimeout(this.c);
            IntProgression step = RangesKt.step(RangesKt.until(0, this.f.size()), 2);
            int first = step.getFirst();
            int last = step.getLast();
            int step2 = step.getStep();
            if ((step2 > 0 && first <= last) || (step2 < 0 && last <= first)) {
                while (true) {
                    httpsURLConnection.setRequestProperty(this.f.get(first), this.f.get(first + 1));
                    if (first == last) {
                        break;
                    }
                    first += step2;
                }
            }
            String str = this.b;
            if (str != null) {
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setRequestProperty("content-type", this.d);
                OutputStream outputStream = httpsURLConnection.getOutputStream();
                try {
                    byte[] bytes = str.getBytes(o.b());
                    Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                    outputStream.write(bytes);
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(outputStream, null);
                } finally {
                }
            }
            e.a a3 = e.h.a();
            a3.a(httpsURLConnection.getResponseCode());
            a3.b(httpsURLConnection.getResponseMessage());
            a3.a(httpsURLConnection.getHeaderFields());
            try {
                String a4 = a(httpsURLConnection.getInputStream());
                if (a4 != null) {
                    a3.a(a4);
                }
            } catch (IOException unused) {
                String a5 = a(httpsURLConnection.getErrorStream());
                if (a5 != null) {
                    a3.a(a5);
                }
            }
            a3.b(currentTimeMillis);
            a3.a(System.currentTimeMillis());
            a2 = a3.a();
            httpsURLConnection.disconnect();
            httpsURLConnection2 = a3;
        } catch (Exception e2) {
            e = e2;
            httpsURLConnection3 = httpsURLConnection;
            g.a.b(j, e, d.b);
            a2 = e.h.a("ERROR", -100);
            httpsURLConnection2 = httpsURLConnection3;
            if (httpsURLConnection3 != null) {
                httpsURLConnection3.disconnect();
                httpsURLConnection2 = httpsURLConnection3;
            }
            return a2;
        } catch (Throwable th2) {
            th = th2;
            httpsURLConnection2 = httpsURLConnection;
            if (httpsURLConnection2 != null) {
                httpsURLConnection2.disconnect();
            }
            throw th;
        }
        return a2;
    }

    public final int k() {
        return this.c;
    }

    public final String l() {
        return this.d;
    }

    public final List<String> m() {
        return this.f;
    }

    public final String n() {
        return this.a;
    }

    public final String o() {
        return this.b;
    }

    public final com.salesforce.marketingcloud.http.a p() {
        return this.g;
    }

    public final String q() {
        return this.h;
    }

    public final String r() {
        return this.e;
    }

    public final Bundle s() {
        Bundle bundle = new Bundle();
        bundle.putString("method", this.a);
        bundle.putString("requestBody", this.b);
        bundle.putInt("connectionTimeout", this.c);
        bundle.putString("contentType", this.d);
        bundle.putString("url", this.e);
        List<String> list = this.f;
        bundle.putStringArrayList("headers", list instanceof ArrayList ? (ArrayList) list : new ArrayList<>(this.f));
        bundle.putInt("mcRequestId", this.g.ordinal());
        bundle.putString("tag", this.h);
        return bundle;
    }

    public String toString() {
        return "Request(method=" + this.a + ", requestBody=" + this.b + ", connectionTimeout=" + this.c + ", contentType=" + this.d + ", url=" + this.e + ", headers=" + this.f + ", requestId=" + this.g + ")";
    }

    public static /* synthetic */ b a(b bVar, String str, String str2, int i2, String str3, String str4, List list, com.salesforce.marketingcloud.http.a aVar, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = bVar.a;
        }
        if ((i3 & 2) != 0) {
            str2 = bVar.b;
        }
        String str5 = str2;
        if ((i3 & 4) != 0) {
            i2 = bVar.c;
        }
        int i4 = i2;
        if ((i3 & 8) != 0) {
            str3 = bVar.d;
        }
        String str6 = str3;
        if ((i3 & 16) != 0) {
            str4 = bVar.e;
        }
        String str7 = str4;
        if ((i3 & 32) != 0) {
            list = bVar.f;
        }
        List list2 = list;
        if ((i3 & 64) != 0) {
            aVar = bVar.g;
        }
        return bVar.a(str, str5, i4, str6, str7, list2, aVar);
    }

    @JvmStatic
    public static final b a(Bundle bundle) {
        return i.a(bundle);
    }

    public final void a(String str) {
        this.h = str;
    }

    private final String a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, o.b()));
        try {
            StringBuilder sb = new StringBuilder();
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                sb.append(readLine);
                sb.append('\n');
            }
            String sb2 = sb.toString();
            CloseableKt.closeFinally(bufferedReader, null);
            return sb2;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(bufferedReader, th);
                throw th2;
            }
        }
    }
}
