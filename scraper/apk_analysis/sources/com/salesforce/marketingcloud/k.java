package com.salesforce.marketingcloud;

import android.os.Bundle;
import androidx.collection.ArrayMap;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.alarms.b;
import com.salesforce.marketingcloud.analytics.l;
import com.salesforce.marketingcloud.b;
import com.salesforce.marketingcloud.http.d;
import com.salesforce.marketingcloud.internal.n;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class k implements com.salesforce.marketingcloud.e, com.salesforce.marketingcloud.behaviors.b, d.c, b.InterfaceC0044b {
    public static final String n = "_sync";
    public static final String o = "_nodes";
    private static final String p = g.a("SyncRouteComponent");
    private static final int q = 202;
    protected final MarketingCloudConfig d;
    protected final com.salesforce.marketingcloud.http.d e;
    protected final com.salesforce.marketingcloud.storage.h f;
    protected final String g;
    private final n h;
    private final com.salesforce.marketingcloud.behaviors.c i;
    private final com.salesforce.marketingcloud.alarms.b j;
    private final l k;
    protected Map<e, f> l = new ArrayMap(e.values().length);
    private boolean m;

    class a extends com.salesforce.marketingcloud.internal.i {
        a(String str, Object... objArr) {
            super(str, objArr);
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            MarketingCloudSdk.requestSdk(k.this.b());
        }
    }

    class b implements MarketingCloudSdk.WhenReadyListener {
        b() {
        }

        @Override // com.salesforce.marketingcloud.MarketingCloudSdk.WhenReadyListener
        public void ready(MarketingCloudSdk marketingCloudSdk) {
            k kVar = k.this;
            kVar.e.a(com.salesforce.marketingcloud.http.a.q.a(kVar.d, kVar.f.c(), com.salesforce.marketingcloud.http.a.b(k.this.d.applicationId(), k.this.g), "{}"));
        }
    }

    class c extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ e c;
        final /* synthetic */ JSONObject d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(String str, Object[] objArr, e eVar, JSONObject jSONObject) {
            super(str, objArr);
            this.c = eVar;
            this.d = jSONObject;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            f fVar = k.this.l.get(this.c);
            if (fVar != null) {
                fVar.onSyncReceived(this.c, this.d);
            }
        }
    }

    static /* synthetic */ class d {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[com.salesforce.marketingcloud.behaviors.a.values().length];
            a = iArr;
            try {
                iArr[com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_PUSH_RECEIVED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public enum e {
        blocked,
        inAppMessages,
        triggers,
        pushFeaturesInUse,
        appConfig
    }

    public interface f {
        void onSyncReceived(e eVar, JSONObject jSONObject);
    }

    k(String str, MarketingCloudConfig marketingCloudConfig, com.salesforce.marketingcloud.storage.h hVar, com.salesforce.marketingcloud.http.d dVar, com.salesforce.marketingcloud.behaviors.c cVar, com.salesforce.marketingcloud.alarms.b bVar, n nVar, l lVar) {
        this.g = str;
        this.d = marketingCloudConfig;
        this.f = hVar;
        this.i = cVar;
        this.e = dVar;
        this.j = bVar;
        this.h = nVar;
        this.k = lVar;
    }

    public static boolean a(Map<String, String> map) {
        return map.containsKey(n) || map.containsKey(o);
    }

    private boolean c() {
        return !this.m;
    }

    MarketingCloudSdk.WhenReadyListener b() {
        return new b();
    }

    @Override // com.salesforce.marketingcloud.d
    public String componentName() {
        return "SyncRoute";
    }

    @Override // com.salesforce.marketingcloud.d
    public JSONObject componentState() {
        return null;
    }

    @Override // com.salesforce.marketingcloud.e
    public void controlChannelInit(int i) {
        if (com.salesforce.marketingcloud.b.a(i, b.c.RTBF.b)) {
            this.i.a(this);
            this.e.a(com.salesforce.marketingcloud.http.a.q);
            com.salesforce.marketingcloud.alarms.b bVar = this.j;
            a.EnumC0042a enumC0042a = a.EnumC0042a.h;
            bVar.e(enumC0042a);
            this.j.d(enumC0042a);
            this.m = true;
        }
    }

    @Override // com.salesforce.marketingcloud.e
    public void init(InitializationStatus.a aVar, int i) {
        if (com.salesforce.marketingcloud.b.a(i, b.c.RTBF.b)) {
            this.m = true;
            return;
        }
        this.e.a(com.salesforce.marketingcloud.http.a.q, this);
        this.i.a(this, EnumSet.of(com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_PUSH_RECEIVED));
        this.j.a(this, a.EnumC0042a.h);
    }

    @Override // com.salesforce.marketingcloud.behaviors.b
    public void onBehavior(com.salesforce.marketingcloud.behaviors.a aVar, Bundle bundle) {
        int i = d.a[aVar.ordinal()];
        if (i == 1) {
            a();
            return;
        }
        if (i != 2) {
            return;
        }
        if (bundle.containsKey(n)) {
            a();
        } else if (bundle.containsKey(o)) {
            a(bundle.getString(o));
        }
    }

    @Override // com.salesforce.marketingcloud.d
    public void tearDown(boolean z) {
        this.i.a(this);
        this.e.a(com.salesforce.marketingcloud.http.a.q);
        com.salesforce.marketingcloud.alarms.b bVar = this.j;
        a.EnumC0042a enumC0042a = a.EnumC0042a.h;
        bVar.e(enumC0042a);
        if (z) {
            this.j.d(enumC0042a);
        }
    }

    public void a(e eVar, f fVar) {
        f fVar2 = this.l.get(eVar);
        if (fVar2 == null || fVar == null || fVar2 == fVar) {
            this.l.put(eVar, fVar);
        } else {
            g.e(p, "Node %s already assigned to listener %s.  %s was not added for the Node.", eVar, fVar2, fVar);
        }
    }

    public void a(EnumSet<e> enumSet, f fVar) {
        Iterator<E> it = enumSet.iterator();
        while (it.hasNext()) {
            a((e) it.next(), fVar);
        }
    }

    @Override // com.salesforce.marketingcloud.alarms.b.InterfaceC0044b
    public void a(a.EnumC0042a enumC0042a) {
        if (enumC0042a == a.EnumC0042a.h) {
            a();
        }
    }

    private void a(String str) {
        if (str != null) {
            try {
                a(new JSONArray(str));
            } catch (Exception e2) {
                g.b(p, e2, "Failed to parse sync push message", new Object[0]);
            }
        }
    }

    private void a() {
        if (c()) {
            this.h.b().execute(new a("attempt_sync_route_request", new Object[0]));
        }
    }

    @Override // com.salesforce.marketingcloud.http.d.c
    public void a(com.salesforce.marketingcloud.http.b bVar, com.salesforce.marketingcloud.http.e eVar) {
        if (eVar.o()) {
            this.j.d(a.EnumC0042a.h);
            com.salesforce.marketingcloud.http.a.a(eVar.l(), this.f.c());
            a(eVar.p());
            try {
                JSONArray jSONArray = new JSONObject(eVar.i()).getJSONArray("nodes");
                if (jSONArray != null) {
                    a(jSONArray, eVar.j());
                    return;
                }
                return;
            } catch (Exception e2) {
                g.b(p, e2, "Failed to parse /sync route response", new Object[0]);
                return;
            }
        }
        this.j.b(a.EnumC0042a.h);
        g.b(p, "Sync route request failed with message: %s", eVar.m());
    }

    private void a(long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            l.a aVar = l.a.SYNC_API;
            jSONObject.put(aVar.b(), j);
            if (com.salesforce.marketingcloud.config.a.g() == null || !com.salesforce.marketingcloud.config.a.g().n()) {
                return;
            }
            this.k.a(aVar, jSONObject);
        } catch (JSONException e2) {
            g.b(p, e2, "Failed to log TelemetryEvent for Sync Route", new Object[0]);
        }
    }

    private void a(JSONArray jSONArray) throws JSONException {
        a(jSONArray, 202);
    }

    private void a(JSONArray jSONArray, int i) throws JSONException {
        String str;
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i2);
            try {
                str = jSONObject.optString("name");
                try {
                    e valueOf = e.valueOf(str);
                    if (i != 202 || valueOf == e.appConfig || valueOf == e.blocked) {
                        this.h.a().execute(new c(str + "-sync_node_process", new Object[0], valueOf, jSONObject));
                    }
                } catch (Exception unused) {
                    g.a(p, "Failed to process node %s sync route", str);
                }
            } catch (Exception unused2) {
                str = null;
            }
        }
    }
}
