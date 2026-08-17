package com.salesforce.marketingcloud.config;

import android.content.SharedPreferences;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.analytics.m;
import com.salesforce.marketingcloud.config.b;
import com.salesforce.marketingcloud.extensions.PushExtensionsKt;
import com.salesforce.marketingcloud.k;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class a extends com.salesforce.marketingcloud.f implements k.f {
    private static final String A = "maxDisplay";
    private static final String B = "timeBetweenDisplaySec";
    private static final String C = "invalidConfigurationKey";
    private static final String D = "invalidConfigurationValue";
    private static final String E = "event";
    private static final String F = "activeEvents";
    private static final String G = "enableEngagementEvents";
    private static final String H = "enableSystemEvents";
    private static final String I = "enableAppEvents";
    private static final String J = "enableIdentityEvents";
    private static final String K = "enableDebugInfo";
    private static final String L = "enableTelemetryInfo";
    private static final String M = "endpoints";
    private static final String N = "deliveryReceipt";
    private static final String O = "deliveryReceiptStatus";
    private static final String P = "gateDeliveryReceiptProcessingMs";
    private static final String Q = "dataTypes";
    private static final int R = 999;
    private static final String S = "version";
    private static a T = null;
    public static final C0051a d = new C0051a(null);
    public static final String e = "correlationIds";
    public static final String f = "gateEventProcessingMs";
    public static final int g = 0;
    public static final String h = "eventName";
    public static final String i = "endpoint";
    public static final String j = "path";
    public static final String k = "maxBatchSize";
    public static final int l = 0;
    public static final int m = 1;
    public static final int n = 10000;
    private static final EnumSet<k.e> o;
    private static final Object p;
    private static final String q = "~!ConfigComponent";
    private static final int r = 1;
    private static final boolean s = true;
    private static final boolean t = false;
    private static final boolean u = false;
    private static final boolean v = false;
    private static final boolean w = false;
    private static final boolean x = false;
    private static final String y = "items";
    private static final String z = "inApp";
    private final com.salesforce.marketingcloud.k U;
    private final com.salesforce.marketingcloud.storage.h V;
    private final m W;
    private Map<String, com.salesforce.marketingcloud.config.b> X;
    private Boolean Y;
    private Boolean Z;
    private Boolean a0;
    private Boolean b0;
    private Boolean c0;
    private Boolean d0;
    private Map<String, String> e0;
    private Integer f0;
    private Integer g0;

    /* renamed from: com.salesforce.marketingcloud.config.a$a, reason: collision with other inner class name */
    public static final class C0051a {
        public /* synthetic */ C0051a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void b() {
        }

        public final a a() {
            return a.T;
        }

        public final Object c() {
            return a.p;
        }

        public final EnumSet<k.e> d() {
            return a.o;
        }

        private C0051a() {
        }

        public final void a(a aVar) {
            a.T = aVar;
        }
    }

    static final class b extends Lambda implements Function0<String> {
        public static final b b = new b();

        b() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Unable to generate complete SDK state output for component.";
        }
    }

    static final class c extends Lambda implements Function0<String> {
        public static final c b = new c();

        c() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Failed to parse [New Events Config] sync data";
        }
    }

    static final class d extends Lambda implements Function0<String> {
        public static final d b = new d();

        d() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Failed to parse [Endpoint Config] sync data.";
        }
    }

    static final class e extends Lambda implements Function0<String> {
        public static final e b = new e();

        e() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Failed to parse [Event Config] sync data";
        }
    }

    static final class f extends Lambda implements Function0<String> {
        public static final f b = new f();

        f() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Failed to parse [InApp Config] sync data";
        }
    }

    static final class g extends Lambda implements Function0<String> {
        final /* synthetic */ String b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(String str) {
            super(0);
            this.b = str;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Unknown endpoint '" + this.b + "' in config.";
        }
    }

    static final class h extends Lambda implements Function0<String> {
        public static final h b = new h();

        h() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Failed to parse endpoint from sync response.";
        }
    }

    static final class i extends Lambda implements Function0<String> {
        public static final i b = new i();

        i() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Unable to handle sync payload due to version mismatch";
        }
    }

    static final class j extends Lambda implements Function0<String> {
        public static final j b = new j();

        j() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Could not process [AppConfig Node] from Sync.";
        }
    }

    static final class k extends Lambda implements Function0<String> {
        final /* synthetic */ String b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        k(String str) {
            super(0);
            this.b = str;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return "Failed to log analytics for InvalidConfig [" + this.b + "]";
        }
    }

    static {
        EnumSet<k.e> of = EnumSet.of(k.e.appConfig);
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        o = of;
        p = new Object();
    }

    public a(com.salesforce.marketingcloud.k syncRouteComponent, com.salesforce.marketingcloud.storage.h storage, m triggerAnalytics) {
        Intrinsics.checkNotNullParameter(syncRouteComponent, "syncRouteComponent");
        Intrinsics.checkNotNullParameter(storage, "storage");
        Intrinsics.checkNotNullParameter(triggerAnalytics, "triggerAnalytics");
        this.U = syncRouteComponent;
        this.V = storage;
        this.W = triggerAnalytics;
        T = this;
    }

    public static final void b(a aVar) {
        d.a(aVar);
    }

    public static final a g() {
        return d.a();
    }

    private final Map<String, String> h() {
        return PushExtensionsKt.toMap(new JSONArray(this.V.e().getString(F, new JSONArray().toString())));
    }

    @Override // com.salesforce.marketingcloud.d
    public String componentName() {
        return "ConfigComponent";
    }

    @Override // com.salesforce.marketingcloud.d
    public JSONObject componentState() {
        JSONObject jSONObject = new JSONObject();
        synchronized (p) {
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(G, this.V.e().getBoolean(G, true));
                jSONObject2.put(H, this.V.e().getBoolean(H, false));
                jSONObject2.put(I, this.V.e().getBoolean(I, false));
                jSONObject2.put(J, this.V.e().getBoolean(J, false));
                jSONObject2.put(L, this.V.e().getBoolean(L, false));
                jSONObject2.put(K, this.V.e().getBoolean(K, false));
                Map<String, String> map = this.e0;
                if (map == null) {
                    map = MapsKt.emptyMap();
                }
                JSONArray jSONArray = new JSONArray();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put(h, entry.getKey());
                    String value = entry.getValue();
                    if (value != null) {
                        jSONObject3.put(e, value);
                    }
                    jSONArray.put(jSONObject3);
                }
                Unit unit = Unit.INSTANCE;
                jSONObject2.put(F, jSONArray);
                jSONObject.put("event", jSONObject2);
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put(f, this.V.e().getInt(f, 0));
                jSONObject4.put(A, this.V.e().getInt(A, Integer.MAX_VALUE));
                jSONObject4.put(B, this.V.e().getInt(B, 0));
                jSONObject.put(z, jSONObject4);
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put(O, this.V.e().getInt(O, 0));
                jSONObject5.put(P, this.V.e().getInt(P, n));
                jSONObject.put(N, jSONObject5);
                Map<String, com.salesforce.marketingcloud.config.b> map2 = this.X;
                if (map2 == null) {
                    map2 = MapsKt.emptyMap();
                }
                jSONObject.put(M, PushExtensionsKt.toJSONArray(map2));
            } catch (Exception unused) {
                com.salesforce.marketingcloud.g.e(com.salesforce.marketingcloud.g.a, q, null, b.b, 2, null);
            }
            Unit unit2 = Unit.INSTANCE;
        }
        return jSONObject;
    }

    public final Map<String, String> d() {
        return this.e0;
    }

    public final int e() {
        int i2;
        synchronized (p) {
            Integer num = this.g0;
            if (num != null) {
                i2 = num.intValue();
            } else {
                int i3 = this.V.e().getInt(P, n);
                this.g0 = Integer.valueOf(i3);
                i2 = i3;
            }
        }
        return i2;
    }

    public final int f() {
        int i2;
        synchronized (p) {
            Integer num = this.f0;
            if (num != null) {
                i2 = num.intValue();
            } else {
                int i3 = this.V.e().getInt(O, 0);
                this.f0 = Integer.valueOf(i3);
                i2 = i3;
            }
        }
        return i2;
    }

    public final boolean i() {
        boolean z2;
        synchronized (p) {
            Boolean bool = this.a0;
            if (bool != null) {
                z2 = bool.booleanValue();
            } else {
                boolean z3 = this.V.e().getBoolean(I, false);
                this.a0 = Boolean.valueOf(z3);
                z2 = z3;
            }
        }
        return z2;
    }

    public final boolean j() {
        boolean z2;
        synchronized (p) {
            Boolean bool = this.d0;
            if (bool != null) {
                z2 = bool.booleanValue();
            } else {
                boolean z3 = this.V.e().getBoolean(K, false);
                this.d0 = Boolean.valueOf(z3);
                z2 = z3;
            }
        }
        return z2;
    }

    public final boolean k() {
        boolean z2;
        synchronized (p) {
            Boolean bool = this.Y;
            if (bool != null) {
                z2 = bool.booleanValue();
            } else {
                boolean z3 = this.V.e().getBoolean(G, true);
                this.Y = Boolean.valueOf(z3);
                z2 = z3;
            }
        }
        return z2;
    }

    public final boolean l() {
        boolean z2;
        synchronized (p) {
            Boolean bool = this.b0;
            if (bool != null) {
                z2 = bool.booleanValue();
            } else {
                boolean z3 = this.V.e().getBoolean(J, false);
                this.b0 = Boolean.valueOf(z3);
                z2 = z3;
            }
        }
        return z2;
    }

    public final boolean m() {
        boolean z2;
        synchronized (p) {
            Boolean bool = this.Z;
            if (bool != null) {
                z2 = bool.booleanValue();
            } else {
                boolean z3 = this.V.e().getBoolean(H, false);
                this.Z = Boolean.valueOf(z3);
                z2 = z3;
            }
        }
        return z2;
    }

    public final boolean n() {
        boolean z2;
        synchronized (p) {
            Boolean bool = this.c0;
            if (bool != null) {
                z2 = bool.booleanValue();
            } else {
                boolean z3 = this.V.e().getBoolean(L, false);
                this.c0 = Boolean.valueOf(z3);
                z2 = z3;
            }
        }
        return z2;
    }

    @Override // com.salesforce.marketingcloud.k.f
    public void onSyncReceived(k.e node, JSONObject data) {
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(data, "data");
        if (o.contains(node)) {
            if (data.optInt("version") != 1) {
                com.salesforce.marketingcloud.g.b(com.salesforce.marketingcloud.g.a, q, null, i.b, 2, null);
                return;
            }
            try {
                if (node == k.e.appConfig) {
                    d(data);
                }
            } catch (Throwable th) {
                com.salesforce.marketingcloud.g.a.b(q, th, j.b);
            }
        }
    }

    @Override // com.salesforce.marketingcloud.f, com.salesforce.marketingcloud.d
    public void tearDown(boolean z2) {
        this.U.a(o, (k.f) null);
        T = null;
    }

    private final void c(JSONObject jSONObject) {
        synchronized (p) {
            try {
                int optInt = jSONObject.optInt(f, 0);
                int optInt2 = jSONObject.optInt(A, Integer.MAX_VALUE);
                int optInt3 = jSONObject.optInt(B, 0);
                SharedPreferences.Editor edit = this.V.e().edit();
                Intrinsics.checkNotNullExpressionValue(edit, "edit(...)");
                if (optInt >= 0) {
                    edit.putInt(com.salesforce.marketingcloud.events.c.r, optInt);
                }
                if (optInt2 >= 0) {
                    edit.putInt(com.salesforce.marketingcloud.events.c.s, optInt2);
                }
                if (optInt3 >= 0) {
                    edit.putInt(com.salesforce.marketingcloud.events.c.t, optInt3);
                }
                edit.apply();
                if (optInt < 0) {
                    a(f, String.valueOf(optInt));
                }
                if (optInt2 < 0) {
                    a(A, String.valueOf(optInt2));
                }
                if (optInt3 < 0) {
                    a(B, String.valueOf(optInt3));
                }
            } catch (Exception e2) {
                com.salesforce.marketingcloud.g.a.b(q, e2, f.b);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void d(JSONObject data) {
        Intrinsics.checkNotNullParameter(data, "data");
        JSONObject optJSONObject = data.optJSONObject("items");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        JSONObject optJSONObject2 = optJSONObject.optJSONObject("event");
        if (optJSONObject2 == null) {
            optJSONObject2 = new JSONObject();
        } else {
            Intrinsics.checkNotNull(optJSONObject2);
        }
        b(optJSONObject2);
        JSONObject optJSONObject3 = optJSONObject.optJSONObject(z);
        if (optJSONObject3 == null) {
            optJSONObject3 = new JSONObject();
        } else {
            Intrinsics.checkNotNull(optJSONObject3);
        }
        c(optJSONObject3);
        JSONArray optJSONArray = optJSONObject.optJSONArray(M);
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        } else {
            Intrinsics.checkNotNull(optJSONArray);
        }
        a(optJSONArray);
        JSONObject optJSONObject4 = optJSONObject.optJSONObject(N);
        if (optJSONObject4 == null) {
            optJSONObject4 = new JSONObject();
        } else {
            Intrinsics.checkNotNull(optJSONObject4);
        }
        a(optJSONObject4);
    }

    @Override // com.salesforce.marketingcloud.f
    protected void a(InitializationStatus.a statusBuilder) {
        Intrinsics.checkNotNullParameter(statusBuilder, "statusBuilder");
        this.U.a(o, this);
    }

    public final boolean b(String eventName) {
        boolean containsKey;
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        synchronized (p) {
            Map<String, String> map = this.e0;
            if (map != null) {
                String lowerCase = eventName.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                containsKey = map.containsKey(lowerCase);
            } else {
                Map<String, String> h2 = h();
                this.e0 = h2;
                String lowerCase2 = eventName.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                containsKey = h2.containsKey(lowerCase2);
            }
        }
        return containsKey;
    }

    public final com.salesforce.marketingcloud.config.b a(com.salesforce.marketingcloud.storage.h hVar, String str) {
        com.salesforce.marketingcloud.config.b bVar;
        if (hVar == null || str == null || str.length() == 0) {
            return null;
        }
        synchronized (p) {
            Map<String, com.salesforce.marketingcloud.config.b> map = this.X;
            if (map == null || (bVar = map.get(str)) == null) {
                Map<String, com.salesforce.marketingcloud.config.b> b2 = b(new JSONArray(hVar.e().getString(M, new JSONArray().toString())));
                this.X = b2;
                bVar = b2.get(str);
            }
        }
        return bVar;
    }

    private final Map<String, com.salesforce.marketingcloud.config.b> b(JSONArray jSONArray) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (jSONArray.length() != 0) {
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                try {
                    Object obj = jSONArray.get(i2);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                    JSONObject jSONObject = (JSONObject) obj;
                    JSONArray optJSONArray = jSONObject.optJSONArray(Q);
                    if (optJSONArray != null) {
                        int length2 = optJSONArray.length();
                        for (int i3 = 0; i3 < length2; i3++) {
                            Object obj2 = optJSONArray.get(i3);
                            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.String");
                            String str = (String) obj2;
                            if (Intrinsics.areEqual(str, "EVENTS")) {
                                b.a aVar = com.salesforce.marketingcloud.config.b.d;
                                String stringOrNull = PushExtensionsKt.getStringOrNull(jSONObject, j);
                                Integer intOrNull = PushExtensionsKt.getIntOrNull(jSONObject, k);
                                int i4 = 999;
                                if (intOrNull != null) {
                                    if (intOrNull.intValue() > 999) {
                                        intOrNull = null;
                                    }
                                    if (intOrNull != null) {
                                        i4 = intOrNull.intValue();
                                    }
                                }
                                linkedHashMap.put(str, aVar.a(str, stringOrNull, Integer.valueOf(i4)));
                            } else {
                                com.salesforce.marketingcloud.g.e(com.salesforce.marketingcloud.g.a, q, null, new g(str), 2, null);
                            }
                        }
                    }
                } catch (Exception e2) {
                    com.salesforce.marketingcloud.g.a.e(q, e2, h.b);
                }
            }
        }
        return linkedHashMap;
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x001d, code lost:
    
        if (r1 == null) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String a(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r0 = "eventName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.Object r0 = com.salesforce.marketingcloud.config.a.p
            monitor-enter(r0)
            java.util.Map<java.lang.String, java.lang.String> r1 = r4.e0     // Catch: java.lang.Throwable -> L39
            if (r1 == 0) goto L1f
            java.util.Locale r2 = java.util.Locale.ROOT     // Catch: java.lang.Throwable -> L39
            java.lang.String r2 = r5.toLowerCase(r2)     // Catch: java.lang.Throwable -> L39
            java.lang.String r3 = "toLowerCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch: java.lang.Throwable -> L39
            java.lang.Object r1 = r1.get(r2)     // Catch: java.lang.Throwable -> L39
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Throwable -> L39
            if (r1 != 0) goto L37
        L1f:
            java.util.Map r1 = r4.h()     // Catch: java.lang.Throwable -> L39
            r4.e0 = r1     // Catch: java.lang.Throwable -> L39
            java.util.Locale r4 = java.util.Locale.ROOT     // Catch: java.lang.Throwable -> L39
            java.lang.String r4 = r5.toLowerCase(r4)     // Catch: java.lang.Throwable -> L39
            java.lang.String r5 = "toLowerCase(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)     // Catch: java.lang.Throwable -> L39
            java.lang.Object r4 = r1.get(r4)     // Catch: java.lang.Throwable -> L39
            r1 = r4
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Throwable -> L39
        L37:
            monitor-exit(r0)
            return r1
        L39:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.config.a.a(java.lang.String):java.lang.String");
    }

    private final void a(JSONObject jSONObject) {
        synchronized (p) {
            try {
                SharedPreferences.Editor edit = this.V.e().edit();
                Integer valueOf = Integer.valueOf(jSONObject.optInt(O, 0));
                if (valueOf.intValue() < 0) {
                    valueOf = null;
                }
                Integer valueOf2 = Integer.valueOf(valueOf != null ? valueOf.intValue() : 0);
                edit.putInt(O, valueOf2.intValue());
                this.f0 = valueOf2;
                int i2 = n;
                Integer valueOf3 = Integer.valueOf(jSONObject.optInt(P, n));
                Integer num = valueOf3.intValue() > 10000 ? valueOf3 : null;
                if (num != null) {
                    i2 = num.intValue();
                }
                Integer valueOf4 = Integer.valueOf(i2);
                edit.putInt(P, valueOf4.intValue());
                this.g0 = valueOf4;
                edit.apply();
            } catch (Exception e2) {
                com.salesforce.marketingcloud.g.a.b(q, e2, c.b);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void b(JSONObject jSONObject) {
        synchronized (p) {
            try {
                SharedPreferences.Editor edit = this.V.e().edit();
                Intrinsics.checkNotNullExpressionValue(edit, "edit(...)");
                boolean optBoolean = jSONObject.optBoolean(G, true);
                Boolean valueOf = Boolean.valueOf(optBoolean);
                edit.putBoolean(G, optBoolean);
                this.Y = valueOf;
                boolean optBoolean2 = jSONObject.optBoolean(H, false);
                Boolean valueOf2 = Boolean.valueOf(optBoolean2);
                edit.putBoolean(H, optBoolean2);
                this.Z = valueOf2;
                boolean optBoolean3 = jSONObject.optBoolean(I, false);
                Boolean valueOf3 = Boolean.valueOf(optBoolean3);
                edit.putBoolean(I, optBoolean3);
                this.a0 = valueOf3;
                boolean optBoolean4 = jSONObject.optBoolean(J, false);
                Boolean valueOf4 = Boolean.valueOf(optBoolean4);
                edit.putBoolean(J, optBoolean4);
                this.b0 = valueOf4;
                boolean optBoolean5 = jSONObject.optBoolean(K, false);
                Boolean valueOf5 = Boolean.valueOf(optBoolean5);
                edit.putBoolean(K, optBoolean5);
                this.d0 = valueOf5;
                boolean optBoolean6 = jSONObject.optBoolean(L, false);
                Boolean valueOf6 = Boolean.valueOf(optBoolean6);
                edit.putBoolean(L, optBoolean6);
                this.c0 = valueOf6;
                JSONArray optJSONArray = jSONObject.optJSONArray(F);
                if (optJSONArray == null) {
                    optJSONArray = new JSONArray();
                } else {
                    Intrinsics.checkNotNull(optJSONArray);
                }
                this.e0 = PushExtensionsKt.toMap(optJSONArray);
                edit.putString(F, optJSONArray.toString());
                edit.apply();
            } catch (Exception e2) {
                com.salesforce.marketingcloud.g.a.b(q, e2, e.b);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void a(JSONArray jSONArray) {
        synchronized (p) {
            try {
                this.X = b(jSONArray);
                this.V.e().edit().putString(M, jSONArray.toString()).apply();
            } catch (Exception e2) {
                com.salesforce.marketingcloud.g.a.b(q, e2, d.b);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void a(String str, String str2) {
        try {
            if (j()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(C, str);
                jSONObject.put(D, str2);
                this.W.a(jSONObject);
            }
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.a.b(q, e2, new k(str));
        }
    }
}
