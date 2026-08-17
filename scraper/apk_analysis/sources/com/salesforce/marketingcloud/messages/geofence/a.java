package com.salesforce.marketingcloud.messages.geofence;

import android.location.Location;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.http.d;
import com.salesforce.marketingcloud.internal.i;
import com.salesforce.marketingcloud.internal.n;
import com.salesforce.marketingcloud.location.LatLon;
import com.salesforce.marketingcloud.location.f;
import com.salesforce.marketingcloud.messages.Message;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.messages.c;
import com.salesforce.marketingcloud.storage.h;
import com.salesforce.marketingcloud.storage.j;
import com.salesforce.marketingcloud.util.Crypto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class a implements com.salesforce.marketingcloud.messages.c, com.salesforce.marketingcloud.location.c, d.c {
    static final String k = g.a("GeofenceMessageManager");
    final f d;
    final h e;
    final c.a f;
    final com.salesforce.marketingcloud.http.d g;
    private final n h;
    AtomicBoolean i = new AtomicBoolean(false);
    private c.b j;

    /* renamed from: com.salesforce.marketingcloud.messages.geofence.a$a, reason: collision with other inner class name */
    class C0066a implements MarketingCloudSdk.WhenReadyListener {
        final /* synthetic */ MarketingCloudConfig a;
        final /* synthetic */ String b;
        final /* synthetic */ LatLon c;

        C0066a(MarketingCloudConfig marketingCloudConfig, String str, LatLon latLon) {
            this.a = marketingCloudConfig;
            this.b = str;
            this.c = latLon;
        }

        @Override // com.salesforce.marketingcloud.MarketingCloudSdk.WhenReadyListener
        public void ready(MarketingCloudSdk marketingCloudSdk) {
            a aVar = a.this;
            aVar.g.a(com.salesforce.marketingcloud.http.a.n.a(this.a, aVar.e.c(), com.salesforce.marketingcloud.http.a.a(this.a.applicationId(), this.b, this.c)));
        }
    }

    class b extends i {
        final /* synthetic */ String c;
        final /* synthetic */ int d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, Object[] objArr, String str2, int i) {
            super(str, objArr);
            this.c = str2;
            this.d = i;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            try {
                j o = a.this.e.o();
                Region a = o.a(this.c, a.this.e.b());
                int i = 0;
                if (a == null) {
                    g.c(a.k, "Removing stale geofence from being monitored.", new Object[0]);
                    a.this.d.a(Collections.singletonList(this.c));
                    return;
                }
                int i2 = this.d;
                if (i2 == 1) {
                    a.this.f.b(a);
                    i = 3;
                } else if (i2 == 2) {
                    a.this.f.a(a);
                    i = 4;
                }
                if (i != 0) {
                    List<String> c = o.c(a.id(), i);
                    if (c.isEmpty()) {
                        return;
                    }
                    com.salesforce.marketingcloud.storage.i n = a.this.e.n();
                    Crypto b = a.this.e.b();
                    for (String str : c) {
                        Message a2 = n.a(str, b);
                        if (a2 != null) {
                            a.this.f.a(a, a2);
                        } else {
                            g.a(a.k, "Message with id [%s] not found", str);
                        }
                    }
                }
            } catch (Exception e) {
                g.b(a.k, e, "Geofence (%s - %d) was tripped, but failed to check for associated message", this.c, Integer.valueOf(this.d));
            }
        }
    }

    class c extends i {
        c(String str, Object... objArr) {
            super(str, objArr);
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            List<String> d = a.this.e.o().d(1);
            if (!d.isEmpty()) {
                a.this.d.a(d);
            }
            a.this.e.o().f(1);
        }
    }

    class d extends i {
        final /* synthetic */ GeofenceMessageResponse c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(String str, Object[] objArr, GeofenceMessageResponse geofenceMessageResponse) {
            super(str, objArr);
            this.c = geofenceMessageResponse;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            j o = a.this.e.o();
            List<String> d = o.d(1);
            o.f(1);
            com.salesforce.marketingcloud.storage.i n = a.this.e.n();
            Crypto b = a.this.e.b();
            if (!this.c.fences().isEmpty()) {
                ArrayList arrayList = new ArrayList();
                Iterator<Region> it = this.c.fences().iterator();
                while (true) {
                    boolean z = false;
                    if (!it.hasNext()) {
                        break;
                    }
                    Region next = it.next();
                    try {
                        for (Message message : next.messages()) {
                            com.salesforce.marketingcloud.messages.b.a(message, n, b);
                            n.a(message, b);
                            z = true;
                        }
                        if (z) {
                            if (!d.remove(next.id())) {
                                arrayList.add(next);
                            }
                            o.a(next, b);
                        }
                    } catch (Exception e) {
                        g.b(a.k, e, "Unable to start monitoring geofence region: %s", next.id());
                    }
                }
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    a.this.d.a(a.a((Region) it2.next()));
                }
            }
            if (!d.isEmpty()) {
                a.this.d.a(d);
            }
            a.this.i.set(true);
        }
    }

    class e extends i {
        e(String str, Object... objArr) {
            super(str, objArr);
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            if (a.this.i.get()) {
                g.d(a.k, "Attempt to monitor fences from DB ignored, because they're already monitored.", new Object[0]);
            }
            g.d(a.k, "monitorStoredRegions", new Object[0]);
            try {
                List<Region> a = a.this.e.o().a(1, a.this.e.b());
                if (a.isEmpty()) {
                    return;
                }
                Iterator<Region> it = a.iterator();
                while (it.hasNext()) {
                    a.this.d.a(a.a(it.next()));
                }
            } catch (Exception e) {
                g.b(a.k, e, "Unable to monitor stored geofence regions.", new Object[0]);
            }
        }
    }

    public a(h hVar, f fVar, com.salesforce.marketingcloud.http.d dVar, n nVar, c.a aVar) {
        this.e = hVar;
        this.d = fVar;
        this.g = dVar;
        this.f = aVar;
        this.h = nVar;
        dVar.a(com.salesforce.marketingcloud.http.a.n, this);
    }

    private static int a(int i) {
        if (i < 100) {
            return 100;
        }
        return i;
    }

    @Override // com.salesforce.marketingcloud.messages.c
    public void b() {
        f fVar = this.d;
        if (fVar != null) {
            fVar.b(this);
            if (this.e != null) {
                this.h.b().execute(new c("disable_fence_tracking", new Object[0]));
            }
        }
        this.g.a(com.salesforce.marketingcloud.http.a.n);
        this.i.set(false);
    }

    @Override // com.salesforce.marketingcloud.messages.c
    public void c() {
        this.h.b().execute(new e("monitor_stored_regions", new Object[0]));
    }

    public boolean d() {
        return this.d.a();
    }

    public static void a(h hVar, f fVar, com.salesforce.marketingcloud.http.d dVar, boolean z) {
        List<String> d2 = hVar.o().d(1);
        if (!d2.isEmpty()) {
            fVar.a(d2);
        }
        if (z) {
            hVar.o().f(1);
            com.salesforce.marketingcloud.storage.i n = hVar.n();
            n.e(3);
            n.e(4);
        }
        dVar.a(com.salesforce.marketingcloud.http.a.n);
    }

    static com.salesforce.marketingcloud.location.b a(Region region) {
        return new com.salesforce.marketingcloud.location.b(region.id(), a(region.radius()), region.center().latitude(), region.center().longitude(), 3);
    }

    @Override // com.salesforce.marketingcloud.messages.c
    public void a(LatLon latLon, String str, MarketingCloudConfig marketingCloudConfig, c.b bVar) {
        this.j = bVar;
        try {
            MarketingCloudSdk.requestSdk(new C0066a(marketingCloudConfig, str, latLon));
        } catch (Exception e2) {
            g.b(k, e2, "Failed to update geofence messages", new Object[0]);
        }
    }

    @Override // com.salesforce.marketingcloud.location.c
    public void a(String str, int i, Location location) {
        String str2 = k;
        g.d(str2, "Geofence (%s - %s) was tripped.", str, Integer.valueOf(i));
        if (i == 4) {
            g.d(str2, "Dwell transition ignore for %s", str);
        } else {
            this.h.b().execute(new b("fence_event", new Object[0], str, i));
        }
    }

    @Override // com.salesforce.marketingcloud.location.c
    public void a(int i, String str) {
        g.a(k, "Region error %d - %s", Integer.valueOf(i), str);
    }

    @Override // com.salesforce.marketingcloud.messages.c
    public void a() {
        this.d.a(this);
        this.g.a(com.salesforce.marketingcloud.http.a.n, this);
    }

    @Override // com.salesforce.marketingcloud.http.d.c
    public void a(com.salesforce.marketingcloud.http.b bVar, com.salesforce.marketingcloud.http.e eVar) {
        if (eVar.o()) {
            try {
                a(new GeofenceMessageResponse(new JSONObject(eVar.i())));
                return;
            } catch (Exception e2) {
                g.b(k, e2, "Error parsing response.", new Object[0]);
                return;
            }
        }
        g.c(k, "Request failed: %d - %s", Integer.valueOf(eVar.j()), eVar.m());
    }

    void a(GeofenceMessageResponse geofenceMessageResponse) {
        g.c(k, "Geofence message request contained %d regions", Integer.valueOf(geofenceMessageResponse.fences().size()));
        c.b bVar = this.j;
        if (bVar != null) {
            bVar.a(geofenceMessageResponse);
        }
        this.h.b().execute(new d("fence_response", new Object[0], geofenceMessageResponse));
    }
}
