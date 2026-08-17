package com.salesforce.marketingcloud.analytics.etanalytics;

import android.text.TextUtils;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.alarms.b;
import com.salesforce.marketingcloud.analytics.AnalyticsManager;
import com.salesforce.marketingcloud.http.d;
import com.salesforce.marketingcloud.http.e;
import com.salesforce.marketingcloud.internal.i;
import com.salesforce.marketingcloud.internal.n;
import com.salesforce.marketingcloud.storage.h;
import com.salesforce.marketingcloud.util.g;
import com.salesforce.marketingcloud.util.j;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class c implements d.c, b.InterfaceC0044b {
    final MarketingCloudConfig d;
    final String e;
    final h f;
    final com.salesforce.marketingcloud.http.d g;
    final com.salesforce.marketingcloud.alarms.b h;
    private final n i;

    class a extends i {
        a(String str, Object... objArr) {
            super(str, objArr);
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            List<com.salesforce.marketingcloud.analytics.b> c = c.this.f.h().c(c.this.f.b());
            if (c.isEmpty()) {
                c.this.h.d(a.EnumC0042a.d);
                return;
            }
            com.salesforce.marketingcloud.http.a aVar = com.salesforce.marketingcloud.http.a.i;
            c cVar = c.this;
            MarketingCloudConfig marketingCloudConfig = cVar.d;
            com.salesforce.marketingcloud.storage.b c2 = cVar.f.c();
            c cVar2 = c.this;
            com.salesforce.marketingcloud.http.b a = aVar.a(marketingCloudConfig, c2, cVar2.a(cVar2.d.applicationId(), c.this.e, c).toString());
            a.a(com.salesforce.marketingcloud.analytics.c.a(c));
            c.this.g.a(a);
        }
    }

    public c(MarketingCloudConfig marketingCloudConfig, String str, h hVar, com.salesforce.marketingcloud.http.d dVar, com.salesforce.marketingcloud.alarms.b bVar, n nVar) {
        this.d = (MarketingCloudConfig) g.a(marketingCloudConfig, "Config is null");
        this.e = (String) g.a(str, "DeviceId is null");
        this.f = (h) g.a(hVar, "MCStorage is null");
        this.g = (com.salesforce.marketingcloud.http.d) g.a(dVar, "RequestManager is null");
        this.h = (com.salesforce.marketingcloud.alarms.b) g.a(bVar, "AlarmScheduler is null");
        this.i = nVar;
        dVar.a(com.salesforce.marketingcloud.http.a.i, this);
        bVar.a(this, a.EnumC0042a.d);
    }

    @Override // com.salesforce.marketingcloud.http.d.c
    public void a(com.salesforce.marketingcloud.http.b bVar, e eVar) {
        if (!eVar.o()) {
            com.salesforce.marketingcloud.g.c(AnalyticsManager.TAG, "Request failed: %d - %s", Integer.valueOf(eVar.j()), eVar.m());
            this.h.b(a.EnumC0042a.d);
        } else {
            this.h.c(a.EnumC0042a.d);
            if (bVar.q() != null) {
                this.i.b().execute(new com.salesforce.marketingcloud.analytics.d(this.f.h(), com.salesforce.marketingcloud.analytics.c.a(bVar.q())));
            }
        }
    }

    public void b() {
        this.g.a(com.salesforce.marketingcloud.http.a.i);
        com.salesforce.marketingcloud.alarms.b bVar = this.h;
        a.EnumC0042a enumC0042a = a.EnumC0042a.d;
        bVar.d(enumC0042a);
        this.h.e(enumC0042a);
    }

    @Override // com.salesforce.marketingcloud.alarms.b.InterfaceC0044b
    public void a(a.EnumC0042a enumC0042a) {
        if (enumC0042a == a.EnumC0042a.d) {
            a();
        }
    }

    public void a() {
        this.i.b().execute(new a("send_analytics", new Object[0]));
    }

    JSONArray a(String str, String str2, List<com.salesforce.marketingcloud.analytics.b> list) {
        JSONArray jSONArray = new JSONArray();
        for (com.salesforce.marketingcloud.analytics.b bVar : list) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(com.salesforce.marketingcloud.analytics.b.v, str);
                jSONObject.put("deviceId", str2);
                jSONObject.put(com.salesforce.marketingcloud.analytics.b.x, j.a(bVar.b()));
                jSONObject.put("value", bVar.g());
                jSONObject.put(com.salesforce.marketingcloud.analytics.b.z, new JSONArray((Collection) Collections.singletonList(Integer.valueOf(bVar.a()))));
                jSONObject.put(com.salesforce.marketingcloud.analytics.b.A, new JSONArray((Collection) bVar.i()));
                String c = bVar.c();
                if (!TextUtils.isEmpty(c)) {
                    JSONObject jSONObject2 = new JSONObject(c);
                    String optString = jSONObject2.optString("uuid");
                    if (!TextUtils.isEmpty(optString)) {
                        jSONObject.put("uuid", optString);
                    }
                    String optString2 = jSONObject2.optString("requestId");
                    if (!TextUtils.isEmpty(optString2)) {
                        jSONObject.put("requestId", optString2);
                    }
                    JSONObject jSONObject3 = jSONObject2.optJSONObject(com.salesforce.marketingcloud.analytics.b.u) != null ? jSONObject2.getJSONObject(com.salesforce.marketingcloud.analytics.b.u) : new JSONObject();
                    jSONObject3.put("platform", "Android");
                    jSONObject.put(com.salesforce.marketingcloud.analytics.b.u, jSONObject3);
                }
                jSONArray.put(jSONObject);
            } catch (Exception e) {
                com.salesforce.marketingcloud.g.b(AnalyticsManager.TAG, e, "Failed to update EtAnalyticItem or convert it to JSON for transmission.", new Object[0]);
            }
        }
        return jSONArray;
    }
}
