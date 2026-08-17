package com.salesforce.marketingcloud.http;

import android.content.SharedPreferences;
import android.os.Build;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.http.b;
import com.salesforce.marketingcloud.location.LatLon;
import com.salesforce.marketingcloud.storage.h;
import com.salesforce.marketingcloud.util.j;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.ws.rs.core.MediaType;
import org.apache.commons.lang3.time.DateUtils;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'l' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:293)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:266)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes2.dex */
public final class a {
    private static final String A;
    private static final long B = 86400000;
    private static final /* synthetic */ a[] C;
    public static final a i = new a("ET_ANALYTICS", 0, "POST", 1, "/device/v1/event/analytic", MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON, "analytics_next_retry_time");
    public static final a j = new a("PI_ANALYTICS", 1, "POST", 2, "{0}", MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON, "piwama_next_retry_time");
    public static final a k;
    public static final a l;
    public static final a m;
    public static final a n;
    public static final a o;
    public static final a p;
    public static final a q;
    public static final a r;
    public static final a s;
    public static final String t = "x-subscriber-token";
    public static final String u = "user-agent";
    public static final String v = "authorization";
    public static final String w = "accept";
    public static final String x = "x-sdk-version";
    public static final String y = "retry-after";
    private static final String z = "Bearer %s";
    public final int b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final long h;

    /* renamed from: com.salesforce.marketingcloud.http.a$a, reason: collision with other inner class name */
    private static class C0056a {
        static final int a = 1;
        static final int b = 2;

        private C0056a() {
        }
    }

    static {
        a aVar = new a("INBOX_MESSAGE", 2, "GET", 1, "/device/v1/{0}/message/?deviceid={1}&wm={2}", MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON, "inbox_next_retry_time");
        k = aVar;
        l = new a("USER_INITIATED_INBOX_MESSAGE", 3, aVar.g, aVar.b, aVar.c, aVar.e, aVar.f, aVar.d, DateUtils.MILLIS_PER_MINUTE);
        m = new a("INBOX_STATUS", 4, "PATCH", 1, "/device/v1/{0}/message", MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON, "inbox_status_next_retry_time");
        n = new a("GEOFENCE_MESSAGE", 5, "GET", 1, "/device/v1/location/{0}/fence/?latitude={1,number,#.########}&longitude={2,number,#.########}&deviceid={3}", MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON, "geofence_next_retry_time");
        o = new a("PROXIMITY_MESSAGES", 6, "GET", 1, "/device/v1/location/{0}/proximity/?latitude={1,number,#.########}&longitude={2,number,#.########}&deviceid={3}", MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON, "proximity_next_retry_time");
        p = new a("REGISTRATION", 7, "POST", 1, "/device/v1/registration", MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON, "registration_next_retry_time", DateUtils.MILLIS_PER_MINUTE);
        q = new a("SYNC", 8, "POST", 1, "/device/v1/{0}/sync/{1}", MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON, "sync_next_retry_time");
        r = new a("DEVICE_STATS", 9, "POST", 1, "/devicestatistics/v1/analytic", MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON, "et_device_stats_retry_after");
        s = new a("EVENTS", 10, "POST", 1, "/devicestatistics/v1/event", MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON, "et_events_retry_after");
        C = a();
        A = String.format(j.a, "MarketingCloudSdk/%s (Android %s; %%s; %s/%s) %%s/%%s", MarketingCloudSdk.getSdkVersionName(), Build.VERSION.RELEASE, Build.MANUFACTURER, Build.MODEL);
    }

    private a(String str, int i2, String str2, int i3, String str3, String str4, String str5, String str6) {
        this(str, i2, str2, i3, str3, str4, str5, str6, 0L);
    }

    private static /* synthetic */ a[] a() {
        return new a[]{i, j, k, l, m, n, o, p, q, r, s};
    }

    public static Object[] b(String str, String str2) {
        return new Object[]{str, str2};
    }

    public static a valueOf(String str) {
        return (a) Enum.valueOf(a.class, str);
    }

    public static a[] values() {
        return (a[]) C.clone();
    }

    long c(SharedPreferences sharedPreferences) {
        return sharedPreferences.getLong(this.d, 0L);
    }

    private a(String str, int i2, String str2, int i3, String str3, String str4, String str5, String str6, long j2) {
        this.g = str2;
        this.b = i3;
        this.c = str3;
        this.e = str4;
        this.f = str5;
        this.d = str6;
        this.h = j2 < 0 ? 0L : j2;
    }

    public static Object[] a(String str, String str2, LatLon latLon) {
        return new Object[]{str, Double.valueOf(latLon.latitude()), Double.valueOf(latLon.longitude()), str2};
    }

    void b(SharedPreferences sharedPreferences) {
        if (this.h > 0) {
            sharedPreferences.edit().putLong(this.d + "_device", this.h + System.currentTimeMillis()).apply();
        }
    }

    public static Object[] a(String str, String str2, String str3) {
        return new Object[]{str, str2, str3};
    }

    public static Object[] a(String str) {
        return new Object[]{str};
    }

    private String b(MarketingCloudConfig marketingCloudConfig) {
        return String.format(j.a, A, Locale.getDefault(), marketingCloudConfig.appPackageName(), marketingCloudConfig.appVersionName());
    }

    public static void a(Map<String, List<String>> map, com.salesforce.marketingcloud.storage.b bVar) {
        List<String> a;
        String str;
        if (map == null || map.isEmpty() || (a = a(map, t)) == null || a.isEmpty() || (str = a.get(0)) == null || str.isEmpty()) {
            return;
        }
        bVar.a(com.salesforce.marketingcloud.storage.b.j, str);
    }

    public static boolean a(h hVar) {
        return hVar.c().b(com.salesforce.marketingcloud.storage.b.j, null) != null;
    }

    private static List<String> a(Map<String, List<String>> map, String str) {
        for (String str2 : map.keySet()) {
            if (str2 != null && str2.equalsIgnoreCase(str)) {
                return map.get(str2);
            }
        }
        return null;
    }

    void a(SharedPreferences sharedPreferences, e eVar) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (eVar.o() && this.h > 0) {
            edit.putLong(this.d + "_device", eVar.k() + this.h);
        }
        List<String> a = a(eVar.l(), y);
        if (a != null && !a.isEmpty()) {
            try {
                long parseLong = Long.parseLong(a.get(0)) * 1000;
                String str = this.d;
                long k2 = eVar.k();
                if (parseLong > 86400000) {
                    parseLong = 86400000;
                }
                edit.putLong(str, k2 + parseLong);
            } catch (Exception e) {
                g.a("MCRequest", e, "Unable to parse Retry-After value.", new Object[0]);
            }
        }
        edit.apply();
    }

    long a(SharedPreferences sharedPreferences) {
        if (this.h <= 0) {
            return 0L;
        }
        return sharedPreferences.getLong(this.d + "_device", 0L);
    }

    private String a(String str, String str2) throws MalformedURLException {
        if (str.endsWith("/")) {
            str = str.substring(0, str.length() - 1);
        }
        return new URL(String.format(j.a, "%s%s", str, str2)).toString();
    }

    public b a(MarketingCloudConfig marketingCloudConfig, com.salesforce.marketingcloud.storage.b bVar, Object[] objArr) {
        return a(marketingCloudConfig, bVar, a(marketingCloudConfig), new MessageFormat(this.c, j.a).format(objArr), null, null);
    }

    public b a(MarketingCloudConfig marketingCloudConfig, com.salesforce.marketingcloud.storage.b bVar, String str) {
        return a(marketingCloudConfig, bVar, a(marketingCloudConfig), this.c, str, null);
    }

    public b a(MarketingCloudConfig marketingCloudConfig, com.salesforce.marketingcloud.storage.b bVar, String str, String str2) {
        String a = a(marketingCloudConfig);
        if (str2 == null) {
            str2 = this.c;
        }
        return a(marketingCloudConfig, bVar, a, str2, str, null);
    }

    public b a(MarketingCloudConfig marketingCloudConfig, com.salesforce.marketingcloud.storage.b bVar, Object[] objArr, String str) {
        return a(marketingCloudConfig, bVar, a(marketingCloudConfig), new MessageFormat(this.c, j.a).format(objArr), str, null);
    }

    public b a(MarketingCloudConfig marketingCloudConfig, com.salesforce.marketingcloud.storage.b bVar, Object[] objArr, String str, Map<String, String> map) {
        return a(marketingCloudConfig, bVar, a(marketingCloudConfig), new MessageFormat(this.c, j.a).format(objArr), str, map);
    }

    private String a(MarketingCloudConfig marketingCloudConfig) {
        return this.b == 1 ? marketingCloudConfig.marketingCloudServerUrl() : marketingCloudConfig.predictiveIntelligenceServerUrl();
    }

    private b a(MarketingCloudConfig marketingCloudConfig, com.salesforce.marketingcloud.storage.b bVar, String str, String str2, String str3, Map<String, String> map) {
        try {
            String a = a(str, str2);
            g.d("MCRequest", "Executing %s request ...", a);
            b.a d = b.b().b(this.g).a(this).a(this.e).d(a);
            if (str3 != null) {
                d.c(str3);
            }
            d.a(u, b(marketingCloudConfig));
            d.a("authorization", String.format(j.a, z, marketingCloudConfig.accessToken()));
            d.a(w, this.f);
            d.a(x, MarketingCloudSdk.getSdkVersionName());
            String b = bVar.b(com.salesforce.marketingcloud.storage.b.j, null);
            if (b != null) {
                d.a(t, b);
            }
            if (map != null && !map.isEmpty()) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    d.a(entry.getKey(), entry.getValue());
                }
            }
            return d.a();
        } catch (Exception e) {
            g.b("MCRequest", e, "Failed to execute request.", new Object[0]);
            return null;
        }
    }
}
