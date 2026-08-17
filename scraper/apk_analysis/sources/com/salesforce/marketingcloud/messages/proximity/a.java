package com.salesforce.marketingcloud.messages.proximity;

import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.http.d;
import com.salesforce.marketingcloud.internal.i;
import com.salesforce.marketingcloud.internal.l;
import com.salesforce.marketingcloud.internal.n;
import com.salesforce.marketingcloud.location.LatLon;
import com.salesforce.marketingcloud.messages.Message;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.messages.c;
import com.salesforce.marketingcloud.proximity.e;
import com.salesforce.marketingcloud.storage.h;
import com.salesforce.marketingcloud.storage.j;
import com.salesforce.marketingcloud.util.Crypto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class a implements com.salesforce.marketingcloud.messages.c, e.a, d.c {
    static final String j = g.a("ProximityMessageManager");
    final h d;
    final com.salesforce.marketingcloud.proximity.e e;
    final c.a f;
    final com.salesforce.marketingcloud.http.d g;
    private final n h;
    private c.b i;

    /* renamed from: com.salesforce.marketingcloud.messages.proximity.a$a, reason: collision with other inner class name */
    class C0070a implements MarketingCloudSdk.WhenReadyListener {
        final /* synthetic */ MarketingCloudConfig a;
        final /* synthetic */ String b;
        final /* synthetic */ LatLon c;

        C0070a(MarketingCloudConfig marketingCloudConfig, String str, LatLon latLon) {
            this.a = marketingCloudConfig;
            this.b = str;
            this.c = latLon;
        }

        @Override // com.salesforce.marketingcloud.MarketingCloudSdk.WhenReadyListener
        public void ready(MarketingCloudSdk marketingCloudSdk) {
            a aVar = a.this;
            aVar.g.a(com.salesforce.marketingcloud.http.a.o.a(this.a, aVar.d.c(), com.salesforce.marketingcloud.http.a.a(this.a.applicationId(), this.b, this.c)));
        }
    }

    class b extends i {
        b(String str, Object... objArr) {
            super(str, objArr);
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            a.this.d.o().f(3);
        }
    }

    class c extends i {
        final /* synthetic */ com.salesforce.marketingcloud.proximity.c c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(String str, Object[] objArr, com.salesforce.marketingcloud.proximity.c cVar) {
            super(str, objArr);
            this.c = cVar;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            try {
                j o = a.this.d.o();
                Region a = o.a(this.c.n(), a.this.d.b());
                if (a == null) {
                    g.a(a.j, "BeaconRegion [%s] did not have matching Region in storage.", this.c);
                    return;
                }
                if (l.a(a)) {
                    g.a(a.j, "Ignoring entry event.  Already inside Region [%s]", a);
                    return;
                }
                g.d(a.j, "Region [%s] was entered.  Will attempt to show associated message.", a.id());
                l.a(a, true);
                o.a(a.id(), true);
                a.this.f.b(a);
                List<String> c = o.c(a.id(), 5);
                if (c.isEmpty()) {
                    return;
                }
                com.salesforce.marketingcloud.storage.i n = a.this.d.n();
                Crypto b = a.this.d.b();
                for (String str : c) {
                    Message a2 = n.a(str, b);
                    if (a2 != null) {
                        a.this.f.a(a, a2);
                    } else {
                        g.a(a.j, "Message with id [%s] not found", str);
                    }
                }
            } catch (Exception e) {
                g.b(a.j, e, "Proximity region (%s) was entered, but failed to check for associated message", this.c.n());
            }
        }
    }

    class d implements Runnable {
        final /* synthetic */ com.salesforce.marketingcloud.proximity.c b;

        d(com.salesforce.marketingcloud.proximity.c cVar) {
            this.b = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            j o = a.this.d.o();
            Region a = o.a(this.b.n(), a.this.d.b());
            if (a == null) {
                g.a(a.j, "BeaconRegion [%s] did not have matching Region in storage.", this.b);
            } else {
                if (!l.a(a)) {
                    g.a(a.j, "Ignoring exit event.  Was not inside BeaconRegion [%s]", this.b);
                    return;
                }
                l.a(a, false);
                a.this.f.a(a);
                o.a(a.id(), false);
            }
        }
    }

    class e extends i {
        final /* synthetic */ ProximityMessageResponse c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(String str, Object[] objArr, ProximityMessageResponse proximityMessageResponse) {
            super(str, objArr);
            this.c = proximityMessageResponse;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            Crypto b = a.this.d.b();
            j o = a.this.d.o();
            List<Region> a = o.a(3, a.this.d.b());
            if (!a.isEmpty()) {
                Collections.sort(a);
            }
            o.f(3);
            com.salesforce.marketingcloud.storage.i n = a.this.d.n();
            if (!this.c.beacons().isEmpty()) {
                ArrayList arrayList = new ArrayList();
                for (Region region : this.c.beacons()) {
                    try {
                        boolean z = false;
                        for (Message message : region.messages()) {
                            com.salesforce.marketingcloud.messages.b.a(message, n, b);
                            n.a(message, b);
                            z = true;
                        }
                        if (z) {
                            int binarySearch = Collections.binarySearch(a, region);
                            if (binarySearch >= 0) {
                                l.a(region, l.a(a.remove(binarySearch)));
                            }
                            o.a(region, b);
                            arrayList.add(new com.salesforce.marketingcloud.proximity.c(region));
                        }
                    } catch (Exception e) {
                        g.b(a.j, e, "Unable to start monitoring proximity region: %s", region.id());
                    }
                }
                g.a(a.j, "Monitoring beacons from request [%s]", arrayList);
                a.this.e.a(arrayList);
            }
            if (a.isEmpty()) {
                return;
            }
            ArrayList arrayList2 = new ArrayList(a.size());
            Iterator<Region> it = a.iterator();
            while (it.hasNext()) {
                arrayList2.add(new com.salesforce.marketingcloud.proximity.c(it.next()));
            }
            g.a(a.j, "Unmonitoring beacons [%s]", arrayList2);
            a.this.e.b(arrayList2);
        }
    }

    public a(h hVar, com.salesforce.marketingcloud.proximity.e eVar, com.salesforce.marketingcloud.http.d dVar, n nVar, c.a aVar) {
        this.d = hVar;
        this.e = eVar;
        this.g = dVar;
        this.h = nVar;
        this.f = aVar;
        dVar.a(com.salesforce.marketingcloud.http.a.o, this);
    }

    public static void a(h hVar, com.salesforce.marketingcloud.proximity.e eVar, com.salesforce.marketingcloud.http.d dVar, boolean z) {
        eVar.c();
        if (z) {
            hVar.o().f(3);
            hVar.n().e(5);
        }
        dVar.a(com.salesforce.marketingcloud.http.a.o);
    }

    @Override // com.salesforce.marketingcloud.messages.c
    public void b() {
        this.e.c();
        this.e.b(this);
        this.g.a(com.salesforce.marketingcloud.http.a.o);
        this.h.b().execute(new b("disable_beacon_tracking", new Object[0]));
    }

    @Override // com.salesforce.marketingcloud.messages.c
    public void c() {
        g.c(j, "monitorStoredRegions", new Object[0]);
        try {
            List<Region> a = this.d.o().a(3, this.d.b());
            if (a.isEmpty()) {
                return;
            }
            ArrayList arrayList = new ArrayList(a.size());
            Iterator<Region> it = a.iterator();
            while (it.hasNext()) {
                arrayList.add(new com.salesforce.marketingcloud.proximity.c(it.next()));
            }
            g.a(j, "Monitoring beacons [%s]", arrayList);
            this.e.a(arrayList);
        } catch (Exception unused) {
            g.b(j, "Unable to monitor stored proximity regions.", new Object[0]);
        }
    }

    public boolean d() {
        return this.e.b();
    }

    @Override // com.salesforce.marketingcloud.proximity.e.a
    public void b(com.salesforce.marketingcloud.proximity.c cVar) {
        g.d(j, "Proximity region (%s) entered.", cVar.n());
        this.h.b().execute(new c("", new Object[0], cVar));
    }

    @Override // com.salesforce.marketingcloud.messages.c
    public void a(LatLon latLon, String str, MarketingCloudConfig marketingCloudConfig, c.b bVar) {
        this.i = bVar;
        try {
            MarketingCloudSdk.requestSdk(new C0070a(marketingCloudConfig, str, latLon));
        } catch (Exception e2) {
            g.b(j, e2, "Failed to update proximity messages", new Object[0]);
        }
    }

    @Override // com.salesforce.marketingcloud.messages.c
    public void a() {
        this.e.a(this);
        this.g.a(com.salesforce.marketingcloud.http.a.o, this);
    }

    @Override // com.salesforce.marketingcloud.proximity.e.a
    public void a(com.salesforce.marketingcloud.proximity.c cVar) {
        g.d(j, "Proximity region (%s) exited.", cVar.n());
        this.h.b().execute(new d(cVar));
    }

    @Override // com.salesforce.marketingcloud.http.d.c
    public void a(com.salesforce.marketingcloud.http.b bVar, com.salesforce.marketingcloud.http.e eVar) {
        if (eVar.o()) {
            try {
                a(new ProximityMessageResponse(new JSONObject(eVar.i())));
                return;
            } catch (Exception e2) {
                g.b(j, e2, "Error parsing response.", new Object[0]);
                return;
            }
        }
        g.c(j, "Request failed: %d - %s", Integer.valueOf(eVar.j()), eVar.m());
    }

    void a(ProximityMessageResponse proximityMessageResponse) {
        g.c(j, "Proximity message request contained %d regions", Integer.valueOf(proximityMessageResponse.beacons().size()));
        c.b bVar = this.i;
        if (bVar != null) {
            bVar.a(proximityMessageResponse);
        }
        this.h.b().execute(new e("beacon_response", new Object[0], proximityMessageResponse));
    }
}
