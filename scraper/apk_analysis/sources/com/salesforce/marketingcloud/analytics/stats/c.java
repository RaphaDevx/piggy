package com.salesforce.marketingcloud.analytics.stats;

import androidx.collection.ArrayMap;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.alarms.b;
import com.salesforce.marketingcloud.analytics.l;
import com.salesforce.marketingcloud.config.b;
import com.salesforce.marketingcloud.http.d;
import com.salesforce.marketingcloud.internal.n;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import com.salesforce.marketingcloud.util.Crypto;
import com.salesforce.marketingcloud.util.j;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class c extends com.salesforce.marketingcloud.analytics.i implements d.c, b.InterfaceC0044b {
    static final String k = com.salesforce.marketingcloud.g.a("DeviceStats");
    private static final String l = "nodes";
    private static final String m = "version";
    private static final String n = "event";
    private static final String o = "eventType";
    private static final String p = "items";
    private static final int q = 999;
    private static final int r = 1;
    public final boolean d;
    protected final n e;
    final String f;
    final com.salesforce.marketingcloud.storage.h g;
    final com.salesforce.marketingcloud.http.d h;
    final MarketingCloudConfig i;
    final com.salesforce.marketingcloud.alarms.b j;

    class a extends com.salesforce.marketingcloud.internal.i {
        a(String str, Object... objArr) {
            super(str, objArr);
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            com.salesforce.marketingcloud.storage.c i = c.this.g.i();
            Crypto b = c.this.g.b();
            List<com.salesforce.marketingcloud.analytics.stats.b> j = i.j(b);
            if (!j.isEmpty()) {
                Date date = new Date();
                for (com.salesforce.marketingcloud.analytics.stats.b bVar : j) {
                    try {
                        bVar.a(date);
                        i.a(bVar, b);
                    } catch (Exception e) {
                        com.salesforce.marketingcloud.g.b(c.k, e, "Unable to update sync event analytic [%s]", Integer.valueOf(bVar.d()));
                    }
                }
            }
            com.salesforce.marketingcloud.g.c(c.k, "Handling app close and sending stats.", new Object[0]);
        }
    }

    class b extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ InAppMessage c;
        final /* synthetic */ JSONObject d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, Object[] objArr, InAppMessage inAppMessage, JSONObject jSONObject) {
            super(str, objArr);
            this.c = inAppMessage;
            this.d = jSONObject;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            if (com.salesforce.marketingcloud.config.a.g() == null || com.salesforce.marketingcloud.config.a.g().j()) {
                com.salesforce.marketingcloud.g.c(c.k, "InAppMessage throttled event stat for message id %s", this.c.id());
                Date date = new Date();
                try {
                    c.this.e.b().execute(new com.salesforce.marketingcloud.analytics.stats.a(c.this.g.i(), c.this.g.b(), com.salesforce.marketingcloud.analytics.stats.b.a(com.salesforce.marketingcloud.analytics.stats.b.l, date, com.salesforce.marketingcloud.analytics.stats.d.a(c.this.i.applicationId(), c.this.f, date, this.c.id(), com.salesforce.marketingcloud.internal.c.a(this.c), this.d), true)));
                } catch (JSONException e) {
                    com.salesforce.marketingcloud.g.b(c.k, e, "Failed to record iam throttled event stat.", new Object[0]);
                }
            }
        }
    }

    /* renamed from: com.salesforce.marketingcloud.analytics.stats.c$c, reason: collision with other inner class name */
    class C0048c extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ JSONObject c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0048c(String str, Object[] objArr, JSONObject jSONObject) {
            super(str, objArr);
            this.c = jSONObject;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            if (com.salesforce.marketingcloud.config.a.g() == null || com.salesforce.marketingcloud.config.a.g().j()) {
                try {
                    Date date = new Date();
                    c.this.e.b().execute(new com.salesforce.marketingcloud.analytics.stats.a(c.this.g.i(), c.this.g.b(), com.salesforce.marketingcloud.analytics.stats.b.a(com.salesforce.marketingcloud.analytics.stats.b.l, date, com.salesforce.marketingcloud.analytics.stats.d.a(c.this.i.applicationId(), c.this.f, date, (String) null, (String) null, this.c), true)));
                } catch (Exception e) {
                    com.salesforce.marketingcloud.g.b(c.k, e, "Failed to record syncGateTimeOut Event stat.", new Object[0]);
                }
            }
        }
    }

    class d extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ JSONObject c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(String str, Object[] objArr, JSONObject jSONObject) {
            super(str, objArr);
            this.c = jSONObject;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            if (com.salesforce.marketingcloud.config.a.g() == null || com.salesforce.marketingcloud.config.a.g().j()) {
                try {
                    Date date = new Date();
                    c.this.e.b().execute(new com.salesforce.marketingcloud.analytics.stats.a(c.this.g.i(), c.this.g.b(), com.salesforce.marketingcloud.analytics.stats.b.a(com.salesforce.marketingcloud.analytics.stats.b.l, date, com.salesforce.marketingcloud.analytics.stats.d.a(c.this.i.applicationId(), c.this.f, date, (String) null, (String) null, this.c), true)));
                } catch (JSONException e) {
                    com.salesforce.marketingcloud.g.b(c.k, e, "Failed to record onInvalidConfig Event stat.", new Object[0]);
                }
            }
        }
    }

    class e extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ JSONObject c;
        final /* synthetic */ l.a d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(String str, Object[] objArr, JSONObject jSONObject, l.a aVar) {
            super(str, objArr);
            this.c = jSONObject;
            this.d = aVar;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            try {
                if (com.salesforce.marketingcloud.config.a.g() == null || com.salesforce.marketingcloud.config.a.g().n()) {
                    Date date = new Date();
                    c.this.e.b().execute(new com.salesforce.marketingcloud.analytics.stats.a(c.this.g.i(), c.this.g.b(), com.salesforce.marketingcloud.analytics.stats.b.a(com.salesforce.marketingcloud.analytics.stats.b.m, date, com.salesforce.marketingcloud.analytics.stats.d.a(c.this.i.applicationId(), c.this.f, date, this.c), true)));
                }
            } catch (Exception e) {
                com.salesforce.marketingcloud.g.b(c.k, e, "Failed to record onTelemetryEvent stat. %s", this.d.name());
            }
        }
    }

    class f extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ Event[] c;
        final /* synthetic */ Date d;
        final /* synthetic */ com.salesforce.marketingcloud.analytics.e e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(String str, Object[] objArr, Event[] eventArr, Date date, com.salesforce.marketingcloud.analytics.e eVar) {
            super(str, objArr);
            this.c = eventArr;
            this.d = date;
            this.e = eVar;
        }

        /* JADX WARN: Code restructure failed: missing block: B:74:0x0081, code lost:
        
            if (r5.booleanValue() == false) goto L10;
         */
        @Override // com.salesforce.marketingcloud.internal.i
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        protected void a() {
            /*
                Method dump skipped, instructions count: 318
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.analytics.stats.c.f.a():void");
        }
    }

    class g extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ Map c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(String str, Object[] objArr, Map map) {
            super(str, objArr);
            this.c = map;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            try {
                if (com.salesforce.marketingcloud.config.a.g() != null && com.salesforce.marketingcloud.config.a.g().f() != 0) {
                    if (com.salesforce.marketingcloud.messages.push.a.a((Map<String, String>) this.c)) {
                        Date date = new Date();
                        c cVar = c.this;
                        new com.salesforce.marketingcloud.analytics.stats.a(c.this.g.i(), c.this.g.b(), com.salesforce.marketingcloud.analytics.stats.b.a(com.salesforce.marketingcloud.analytics.stats.b.p, date, com.salesforce.marketingcloud.analytics.stats.d.b(cVar.i.applicationId, cVar.f, date, (String) this.c.get(NotificationMessage.NOTIF_KEY_ID), (String) this.c.get(NotificationMessage.NOTIF_KEY_REQUEST_ID), (String) this.c.get("messageDateUtc"), (String) this.c.get(NotificationMessage.NOTIF_KEY_MESSAGE_TYPE), (String) this.c.get(NotificationMessage.NOTIF_KEY_PB_ID)), true)).a();
                        if (com.salesforce.marketingcloud.config.a.g().f() == 1) {
                            com.salesforce.marketingcloud.alarms.b bVar = c.this.j;
                            a.EnumC0042a enumC0042a = a.EnumC0042a.l;
                            bVar.d(enumC0042a);
                            enumC0042a.a(com.salesforce.marketingcloud.config.a.g().e());
                            c.this.j.b(enumC0042a);
                            return;
                        }
                        return;
                    }
                    return;
                }
                com.salesforce.marketingcloud.g.c(c.k, "onPushReceived with feature disabled do not report delivery receipt", new Object[0]);
            } catch (Exception e) {
                com.salesforce.marketingcloud.g.b(c.k, e, "Failed to record Delivery Receipt event stat", new Object[0]);
            }
        }
    }

    class h extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ com.salesforce.marketingcloud.http.a c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        h(String str, Object[] objArr, com.salesforce.marketingcloud.http.a aVar) {
            super(str, objArr);
            this.c = aVar;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            com.salesforce.marketingcloud.http.a aVar = this.c;
            com.salesforce.marketingcloud.http.a aVar2 = com.salesforce.marketingcloud.http.a.s;
            if ((aVar == aVar2 || aVar == com.salesforce.marketingcloud.http.a.r) && !com.salesforce.marketingcloud.http.a.a(c.this.g)) {
                com.salesforce.marketingcloud.g.c(c.k, "No subscriber token found ignore sendStats request", new Object[0]);
                c.this.j.d(this.c == aVar2 ? a.EnumC0042a.k : a.EnumC0042a.j);
                return;
            }
            com.salesforce.marketingcloud.http.a aVar3 = this.c;
            com.salesforce.marketingcloud.http.a aVar4 = com.salesforce.marketingcloud.http.a.r;
            List<com.salesforce.marketingcloud.analytics.stats.b> k = aVar3 == aVar4 ? c.this.g.i().k(c.this.g.b()) : c.this.g.i().p(c.this.g.b());
            if (k.isEmpty()) {
                com.salesforce.marketingcloud.http.a aVar5 = this.c;
                if (aVar5 == aVar4) {
                    c.this.j.d(a.EnumC0042a.j);
                    return;
                } else {
                    if (aVar5 == aVar2) {
                        c.this.j.d(a.EnumC0042a.k);
                        return;
                    }
                    return;
                }
            }
            com.salesforce.marketingcloud.g.c(c.k, "Preparing payload for device statistics.", new Object[0]);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(com.salesforce.marketingcloud.analytics.stats.d.b, c.this.i.applicationId());
                jSONObject.put("deviceId", c.this.f);
                JSONArray jSONArray = new JSONArray();
                JSONObject jSONObject2 = new JSONObject();
                jSONArray.put(jSONObject2);
                jSONObject.put(c.l, jSONArray);
                jSONObject2.put("version", 1);
                jSONObject2.put("name", "event");
                Integer num = 999;
                String str = null;
                if (this.c == aVar2) {
                    com.salesforce.marketingcloud.config.b a = com.salesforce.marketingcloud.config.a.g() != null ? com.salesforce.marketingcloud.config.a.g().a(c.this.g, b.EnumC0052b.b.name()) : null;
                    if (a != null) {
                        str = a.f();
                        if (a.e() != null) {
                            num = a.e();
                        }
                    }
                }
                for (Map.Entry<String, JSONArray> entry : c.this.a(k, num.intValue()).entrySet()) {
                    jSONObject2.put("items", entry.getValue());
                    com.salesforce.marketingcloud.http.a aVar6 = this.c;
                    c cVar = c.this;
                    com.salesforce.marketingcloud.http.b a2 = aVar6.a(cVar.i, cVar.g.c(), jSONObject.toString(), str);
                    a2.a(entry.getKey());
                    c.this.h.a(a2);
                }
            } catch (Exception e) {
                com.salesforce.marketingcloud.g.b(c.k, e, "Failed to start sync events request.", new Object[0]);
            }
        }
    }

    static /* synthetic */ class i {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[Event.Category.values().length];
            a = iArr;
            try {
                iArr[Event.Category.APPLICATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[Event.Category.ENGAGEMENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[Event.Category.IDENTITY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[Event.Category.SYSTEM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public c(MarketingCloudConfig marketingCloudConfig, String str, boolean z, com.salesforce.marketingcloud.storage.h hVar, com.salesforce.marketingcloud.http.d dVar, com.salesforce.marketingcloud.alarms.b bVar, n nVar) {
        this.i = marketingCloudConfig;
        this.f = str;
        this.d = z;
        this.g = hVar;
        this.h = dVar;
        this.j = bVar;
        this.e = nVar;
        dVar.a(com.salesforce.marketingcloud.http.a.r, this);
        dVar.a(com.salesforce.marketingcloud.http.a.s, this);
        bVar.a(this, a.EnumC0042a.j, a.EnumC0042a.k);
    }

    public static void a(com.salesforce.marketingcloud.storage.h hVar, boolean z) {
        if (z) {
            hVar.i().f();
        }
    }

    @Override // com.salesforce.marketingcloud.analytics.i, com.salesforce.marketingcloud.analytics.f
    public void b(InAppMessage inAppMessage) {
        if (!this.d) {
            com.salesforce.marketingcloud.g.a(k, "Track user is false.  Ignoring onInAppMessageDownloaded event.", new Object[0]);
            return;
        }
        try {
            com.salesforce.marketingcloud.g.c(k, "Creating download event stat for message id %s", inAppMessage.id());
            Date date = new Date();
            this.e.b().execute(new com.salesforce.marketingcloud.analytics.stats.a(this.g.i(), this.g.b(), com.salesforce.marketingcloud.analytics.stats.b.a(101, date, com.salesforce.marketingcloud.analytics.stats.d.b(this.i.applicationId(), this.f, date, inAppMessage.id(), com.salesforce.marketingcloud.internal.c.a(inAppMessage)), true)));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to record analytic event for In App Message Downloaded", new Object[0]);
        }
    }

    @Override // com.salesforce.marketingcloud.analytics.i
    public void a(boolean z) {
        this.h.a(com.salesforce.marketingcloud.http.a.r);
        this.h.a(com.salesforce.marketingcloud.http.a.s);
        com.salesforce.marketingcloud.alarms.b bVar = this.j;
        a.EnumC0042a enumC0042a = a.EnumC0042a.j;
        a.EnumC0042a enumC0042a2 = a.EnumC0042a.k;
        bVar.e(enumC0042a, enumC0042a2);
        if (z) {
            this.j.d(enumC0042a, enumC0042a2, a.EnumC0042a.l);
        }
    }

    @Override // com.salesforce.marketingcloud.http.d.c
    public void a(com.salesforce.marketingcloud.http.b bVar, com.salesforce.marketingcloud.http.e eVar) {
        if (eVar.o()) {
            if (bVar.p() == com.salesforce.marketingcloud.http.a.r) {
                this.j.c(a.EnumC0042a.j);
            } else if (bVar.p() == com.salesforce.marketingcloud.http.a.s) {
                this.j.c(a.EnumC0042a.k);
            }
            if (bVar.q() != null) {
                String[] a2 = com.salesforce.marketingcloud.analytics.c.a(bVar.q());
                com.salesforce.marketingcloud.g.c(k, "Removing events %s from DB", Arrays.toString(a2));
                this.g.i().c(a2);
                return;
            }
            return;
        }
        com.salesforce.marketingcloud.g.c(k, "Request failed: %d - %s", Integer.valueOf(eVar.j()), eVar.m());
        if (bVar.p() == com.salesforce.marketingcloud.http.a.r) {
            this.j.b(a.EnumC0042a.j);
        } else if (bVar.p() == com.salesforce.marketingcloud.http.a.s) {
            this.j.b(a.EnumC0042a.k);
        }
        if (bVar.q() != null) {
            this.g.i().d(com.salesforce.marketingcloud.analytics.c.a(bVar.q()));
        }
    }

    @Override // com.salesforce.marketingcloud.analytics.i, com.salesforce.marketingcloud.analytics.m
    public void b(JSONObject jSONObject) {
        try {
            this.e.b().execute(new C0048c("onSyncGateTimedOutEvent", new Object[0], jSONObject));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to track syncGateTimeOut Event stat.", new Object[0]);
        }
    }

    @Override // com.salesforce.marketingcloud.alarms.b.InterfaceC0044b
    public void a(a.EnumC0042a enumC0042a) {
        if (enumC0042a == a.EnumC0042a.j || enumC0042a == a.EnumC0042a.k) {
            com.salesforce.marketingcloud.g.c(k, "Handling alarm to send stats type [%s]", enumC0042a.name());
            a();
        }
    }

    @Override // com.salesforce.marketingcloud.analytics.i
    public void a(long j) {
        this.e.b().execute(new a("stats_app_close", new Object[0]));
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0050  */
    @Override // com.salesforce.marketingcloud.analytics.i, com.salesforce.marketingcloud.analytics.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.salesforce.marketingcloud.messages.iam.InAppMessage r17, com.salesforce.marketingcloud.messages.iam.j r18) {
        /*
            r16 = this;
            r0 = r16
            boolean r1 = r0.d
            r2 = 0
            if (r1 != 0) goto L11
            java.lang.String r0 = com.salesforce.marketingcloud.analytics.stats.c.k
            java.lang.Object[] r1 = new java.lang.Object[r2]
            java.lang.String r2 = "Track user is false. Ignoring onInAppMessageCompleted event."
            com.salesforce.marketingcloud.g.a(r0, r2, r1)
            return
        L11:
            java.lang.String r1 = com.salesforce.marketingcloud.analytics.stats.c.k     // Catch: java.lang.Exception -> Lb6
            java.lang.String r3 = "Creating display event stat for message id %s"
            java.lang.String r4 = r17.id()     // Catch: java.lang.Exception -> Lb6
            java.lang.Object[] r4 = new java.lang.Object[]{r4}     // Catch: java.lang.Exception -> Lb6
            com.salesforce.marketingcloud.g.c(r1, r3, r4)     // Catch: java.lang.Exception -> Lb6
            com.salesforce.marketingcloud.messages.iam.InAppMessage$Button r1 = r18.i()     // Catch: java.lang.Exception -> Lb6
            java.lang.String r3 = r18.l()     // Catch: java.lang.Exception -> Lb6
            int r4 = r3.hashCode()     // Catch: java.lang.Exception -> Lb6
            r5 = -935167046(0xffffffffc8427bba, float:-199150.9)
            r6 = 1
            if (r4 == r5) goto L42
            r5 = 2117198997(0x7e31e495, float:5.9115055E37)
            if (r4 == r5) goto L38
            goto L4c
        L38:
            java.lang.String r4 = "buttonClicked"
            boolean r3 = r3.equals(r4)     // Catch: java.lang.Exception -> Lb6
            if (r3 == 0) goto L4c
            r3 = r6
            goto L4d
        L42:
            java.lang.String r4 = "autoDismissed"
            boolean r3 = r3.equals(r4)     // Catch: java.lang.Exception -> Lb6
            if (r3 == 0) goto L4c
            r3 = r2
            goto L4d
        L4c:
            r3 = -1
        L4d:
            r4 = 0
            if (r3 == 0) goto L5c
            if (r3 == r6) goto L54
            r6 = 3
            goto L5c
        L54:
            if (r1 == 0) goto L5b
            java.lang.String r1 = r1.id()     // Catch: java.lang.Exception -> Lb6
            r4 = r1
        L5b:
            r6 = 2
        L5c:
            r15 = r4
            r14 = r6
            java.util.Date r1 = new java.util.Date     // Catch: java.lang.Exception -> Lb6
            java.util.Date r3 = r18.k()     // Catch: java.lang.Exception -> Lb6
            long r3 = r3.getTime()     // Catch: java.lang.Exception -> Lb6
            long r5 = r18.j()     // Catch: java.lang.Exception -> Lb6
            long r3 = r3 + r5
            r1.<init>(r3)     // Catch: java.lang.Exception -> Lb6
            com.salesforce.marketingcloud.MarketingCloudConfig r3 = r0.i     // Catch: java.lang.Exception -> Lb6
            java.lang.String r7 = r3.applicationId()     // Catch: java.lang.Exception -> Lb6
            java.lang.String r8 = r0.f     // Catch: java.lang.Exception -> Lb6
            java.lang.String r10 = r17.id()     // Catch: java.lang.Exception -> Lb6
            java.lang.String r11 = com.salesforce.marketingcloud.internal.c.a(r17)     // Catch: java.lang.Exception -> Lb6
            long r3 = r18.j()     // Catch: java.lang.Exception -> Lb6
            double r3 = (double) r3     // Catch: java.lang.Exception -> Lb6
            r5 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r3 = r3 / r5
            double r3 = java.lang.Math.ceil(r3)     // Catch: java.lang.Exception -> Lb6
            long r12 = (long) r3     // Catch: java.lang.Exception -> Lb6
            r9 = r1
            com.salesforce.marketingcloud.analytics.stats.d r3 = com.salesforce.marketingcloud.analytics.stats.d.a(r7, r8, r9, r10, r11, r12, r14, r15)     // Catch: java.lang.Exception -> Lb6
            com.salesforce.marketingcloud.internal.n r4 = r0.e     // Catch: java.lang.Exception -> Lb6
            java.util.concurrent.ExecutorService r4 = r4.b()     // Catch: java.lang.Exception -> Lb6
            com.salesforce.marketingcloud.analytics.stats.a r5 = new com.salesforce.marketingcloud.analytics.stats.a     // Catch: java.lang.Exception -> Lb6
            com.salesforce.marketingcloud.storage.h r6 = r0.g     // Catch: java.lang.Exception -> Lb6
            com.salesforce.marketingcloud.storage.c r6 = r6.i()     // Catch: java.lang.Exception -> Lb6
            com.salesforce.marketingcloud.storage.h r0 = r0.g     // Catch: java.lang.Exception -> Lb6
            com.salesforce.marketingcloud.util.Crypto r0 = r0.b()     // Catch: java.lang.Exception -> Lb6
            r7 = 100
            com.salesforce.marketingcloud.analytics.stats.b r1 = com.salesforce.marketingcloud.analytics.stats.b.a(r7, r1, r3, r2)     // Catch: java.lang.Exception -> Lb6
            r5.<init>(r6, r0, r1)     // Catch: java.lang.Exception -> Lb6
            r4.execute(r5)     // Catch: java.lang.Exception -> Lb6
            goto Lc0
        Lb6:
            r0 = move-exception
            java.lang.String r1 = com.salesforce.marketingcloud.analytics.stats.c.k
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r3 = "Failed to record analytic event for In App Message Displayed"
            com.salesforce.marketingcloud.g.b(r1, r0, r3, r2)
        Lc0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.analytics.stats.c.a(com.salesforce.marketingcloud.messages.iam.InAppMessage, com.salesforce.marketingcloud.messages.iam.j):void");
    }

    @Override // com.salesforce.marketingcloud.analytics.i
    public void b(Map<String, String> map) {
        try {
            this.e.b().execute(new g("onPushReceived", new Object[0], map));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to track Delivery Receipt event stat", new Object[0]);
        }
    }

    @Override // com.salesforce.marketingcloud.analytics.i, com.salesforce.marketingcloud.analytics.j
    public void a(com.salesforce.marketingcloud.push.f fVar, String str) {
        String message = fVar.getMessage();
        if (this.d && message != null) {
            try {
                Date date = new Date();
                this.e.b().execute(new com.salesforce.marketingcloud.analytics.stats.a(this.g.i(), this.g.b(), com.salesforce.marketingcloud.analytics.stats.b.a(com.salesforce.marketingcloud.analytics.stats.b.l, date, com.salesforce.marketingcloud.analytics.stats.d.a(this.i.applicationId(), this.f, date, str, (String) null, fVar.b()), true)));
                return;
            } catch (Exception e2) {
                com.salesforce.marketingcloud.g.b(k, e2, "Failed to record analytic event for Push Notification Error", new Object[0]);
                return;
            }
        }
        com.salesforce.marketingcloud.g.a(k, "Track user is false.  Ignoring PushNotificationError event.", new Object[0]);
    }

    @Override // com.salesforce.marketingcloud.analytics.i, com.salesforce.marketingcloud.analytics.j
    public void a(NotificationMessage notificationMessage, int i2, String str, String str2) {
        if (!this.d) {
            com.salesforce.marketingcloud.g.a(k, "Track user is false.  Ignoring recordNotificationMessageClicked event or message is null.", new Object[0]);
            return;
        }
        if (notificationMessage == null) {
            com.salesforce.marketingcloud.g.a(k, "NotificationMessage is null. Ignoring recordNotificationMessageClicked event.", new Object[0]);
            return;
        }
        try {
            Date date = new Date();
            this.e.b().execute(new com.salesforce.marketingcloud.analytics.stats.a(this.g.i(), this.g.b(), com.salesforce.marketingcloud.analytics.stats.b.a(i2, date, com.salesforce.marketingcloud.analytics.stats.d.a(this.i.applicationId(), this.f, date, notificationMessage.id(), str, notificationMessage.requestId, notificationMessage.propertyBag(), str2), true)));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to record analytic event for recordNotificationMessageClicked", new Object[0]);
        }
    }

    @Override // com.salesforce.marketingcloud.analytics.i, com.salesforce.marketingcloud.analytics.m
    public void a(String str, String str2, String str3, String str4) {
        if (!this.d) {
            com.salesforce.marketingcloud.g.a(k, "Track user is false.  Ignoring onTriggerSuccessEvent event.", new Object[0]);
            return;
        }
        com.salesforce.marketingcloud.g.c(k, "Creating trigger event stat for message id %s", str);
        try {
            Date date = new Date();
            this.e.b().execute(new com.salesforce.marketingcloud.analytics.stats.a(this.g.i(), this.g.b(), com.salesforce.marketingcloud.analytics.stats.b.a(102, date, com.salesforce.marketingcloud.analytics.stats.d.a(this.i.applicationId(), this.f, date, str2, str4, str, str3), true)));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to record device stat for successful trigger event", new Object[0]);
        }
    }

    @Override // com.salesforce.marketingcloud.analytics.i, com.salesforce.marketingcloud.analytics.f
    public void a(String str, String str2, List<String> list) {
        com.salesforce.marketingcloud.g.c(k, "Creating message validation error event stat for message id %s", str);
        try {
            Date date = new Date();
            this.e.b().execute(new com.salesforce.marketingcloud.analytics.stats.a(this.g.i(), this.g.b(), com.salesforce.marketingcloud.analytics.stats.b.a(com.salesforce.marketingcloud.analytics.stats.b.i, date, com.salesforce.marketingcloud.analytics.stats.d.a(this.i.applicationId(), this.f, date, str, str2, list), true)));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to record validation event stat.", new Object[0]);
        }
    }

    @Override // com.salesforce.marketingcloud.analytics.i, com.salesforce.marketingcloud.analytics.f
    public void a(InAppMessage inAppMessage) {
        try {
            com.salesforce.marketingcloud.g.c(k, "InAppMessage displayed event stat for message id %s", inAppMessage.id());
            Date date = new Date();
            this.e.b().execute(new com.salesforce.marketingcloud.analytics.stats.a(this.g.i(), this.g.b(), com.salesforce.marketingcloud.analytics.stats.b.a(104, date, com.salesforce.marketingcloud.analytics.stats.d.a(this.i.applicationId(), this.f, date, inAppMessage.id(), com.salesforce.marketingcloud.internal.c.a(inAppMessage)), true)));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to record iam displayed event stat.", new Object[0]);
        }
    }

    @Override // com.salesforce.marketingcloud.analytics.i, com.salesforce.marketingcloud.analytics.f
    public void a(InAppMessage inAppMessage, JSONObject jSONObject) {
        try {
            this.e.b().execute(new b("onInAppMessageThrottled", new Object[0], inAppMessage, jSONObject));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to track iam throttled event stat.", new Object[0]);
        }
    }

    @Override // com.salesforce.marketingcloud.analytics.i, com.salesforce.marketingcloud.analytics.m
    public void a(JSONObject jSONObject) {
        try {
            this.e.b().execute(new d("onInvalidConfigEvent", new Object[0], jSONObject));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to track onInvalidConfig Event stat.", new Object[0]);
        }
    }

    @Override // com.salesforce.marketingcloud.analytics.i, com.salesforce.marketingcloud.analytics.l
    public void a(l.a aVar, JSONObject jSONObject) {
        try {
            this.e.b().execute(new e("onTelemetryEvent", new Object[0], jSONObject, aVar));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to track onTelemetryEvent stat. %s", aVar.name());
        }
    }

    @Override // com.salesforce.marketingcloud.analytics.i, com.salesforce.marketingcloud.analytics.n
    public void a(com.salesforce.marketingcloud.analytics.e eVar, Event... eventArr) {
        try {
            this.e.b().execute(new f("track_events", new Object[0], eventArr, new Date(), eVar));
        } catch (Exception e2) {
            com.salesforce.marketingcloud.g.b(k, e2, "Failed to record iam displayed event stat.", new Object[0]);
        }
    }

    public void a() {
        a(com.salesforce.marketingcloud.http.a.r);
        a(com.salesforce.marketingcloud.http.a.s);
    }

    void a(com.salesforce.marketingcloud.http.a aVar) {
        this.e.b().execute(new h("send_stats", new Object[0], aVar));
    }

    Map<String, JSONArray> a(List<com.salesforce.marketingcloud.analytics.stats.b> list, int i2) {
        boolean z;
        int size = list.size();
        int ceil = (int) Math.ceil(size / i2);
        ArrayMap arrayMap = new ArrayMap(ceil);
        for (int i3 = 0; i3 < ceil; i3++) {
            StringBuilder sb = new StringBuilder();
            JSONArray jSONArray = new JSONArray();
            int i4 = i3 * i2;
            boolean z2 = true;
            int i5 = i4;
            while (i5 < size && i5 < i4 + i2) {
                com.salesforce.marketingcloud.analytics.stats.b bVar = list.get(i5);
                if (z2) {
                    z = false;
                } else {
                    sb.append(',');
                    z = z2;
                }
                sb.append(bVar.b());
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put(o, bVar.d());
                    if (bVar.d() == 112) {
                        bVar.c().a.put(com.salesforce.marketingcloud.analytics.stats.d.f, j.a(new Date()));
                    }
                    jSONObject.put("event", bVar.c().a);
                    jSONArray.put(jSONObject);
                } catch (JSONException e2) {
                    com.salesforce.marketingcloud.g.b(k, e2, "Unable to add device stats to payload.", new Object[0]);
                }
                i5++;
                z2 = z;
            }
            arrayMap.put(sb.toString(), jSONArray);
        }
        return arrayMap;
    }
}
