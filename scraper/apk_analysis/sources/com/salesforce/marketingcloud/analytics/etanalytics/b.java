package com.salesforce.marketingcloud.analytics.etanalytics;

import com.salesforce.marketingcloud.analytics.AnalyticsManager;
import com.salesforce.marketingcloud.analytics.i;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.internal.n;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.messages.inbox.InboxMessage;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.storage.h;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class b extends i {
    private static final int f = 0;
    final h d;
    private final n e;

    class a extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ h c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, Object[] objArr, h hVar) {
            super(str, objArr);
            this.c = hVar;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            this.c.h().a(0);
        }
    }

    /* renamed from: com.salesforce.marketingcloud.analytics.etanalytics.b$b, reason: collision with other inner class name */
    class C0047b extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ long c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0047b(String str, Object[] objArr, long j) {
            super(str, objArr);
            this.c = j;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            if (b.this.d.h().c(0)) {
                return;
            }
            try {
                b.this.d.h().a(com.salesforce.marketingcloud.analytics.b.a(new Date(this.c), 0, 4), b.this.d.b());
            } catch (Exception e) {
                g.b(AnalyticsManager.TAG, e, "Failed to create our EtAnalyticItem for TimeInApp.", new Object[0]);
            }
        }
    }

    class c extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ long c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(String str, Object[] objArr, long j) {
            super(str, objArr);
            this.c = j;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            try {
                List<com.salesforce.marketingcloud.analytics.b> g = b.this.d.h().g(b.this.d.b());
                if (g.isEmpty()) {
                    return;
                }
                for (com.salesforce.marketingcloud.analytics.b bVar : g) {
                    int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(this.c - bVar.b().getTime());
                    if (seconds > 0) {
                        bVar.b(seconds);
                        bVar.a(true);
                        b.this.d.h().b(bVar, b.this.d.b());
                    }
                }
            } catch (Exception e) {
                g.b(AnalyticsManager.TAG, e, "Failed to update our EtAnalytic TimeInApp.", new Object[0]);
            }
        }
    }

    class d extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ long c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(String str, Object[] objArr, long j) {
            super(str, objArr);
            this.c = j;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            try {
                List<com.salesforce.marketingcloud.analytics.b> h = b.this.d.h().h(b.this.d.b());
                if (h.isEmpty()) {
                    return;
                }
                for (com.salesforce.marketingcloud.analytics.b bVar : h) {
                    int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(this.c - bVar.b().getTime());
                    if (seconds > 0) {
                        bVar.b(seconds);
                        bVar.a(true);
                        b.this.d.h().b(bVar, b.this.d.b());
                    }
                }
            } catch (Exception e) {
                g.b(AnalyticsManager.TAG, e, "Failed to update local storage for stopTimeInAllRegions.", new Object[0]);
            }
        }
    }

    class e extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ Region c;
        final /* synthetic */ Date d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(String str, Object[] objArr, Region region, Date date) {
            super(str, objArr);
            this.c = region;
            this.d = date;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            try {
                List<com.salesforce.marketingcloud.analytics.b> b = b.this.d.h().b(this.c, b.this.d.b());
                if (b.isEmpty()) {
                    return;
                }
                for (com.salesforce.marketingcloud.analytics.b bVar : b) {
                    int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(this.d.getTime() - bVar.b().getTime());
                    if (seconds > 0) {
                        bVar.b(seconds);
                        bVar.a(true);
                        b.this.d.h().b(bVar, b.this.d.b());
                    }
                }
            } catch (Exception e) {
                g.b(AnalyticsManager.TAG, e, "Failed to record EtAnalyticItem for stopTimeInRegion.", new Object[0]);
            }
        }
    }

    public b(h hVar, n nVar) {
        this.d = hVar;
        this.e = nVar;
    }

    public static void a(h hVar, n nVar, boolean z) {
        if (z) {
            a(nVar, hVar);
        }
    }

    @Override // com.salesforce.marketingcloud.analytics.i
    public void b(long j) {
        this.e.b().execute(new C0047b("start_app_counter", new Object[0], j));
    }

    @Override // com.salesforce.marketingcloud.analytics.i
    public void c(long j) {
        this.e.b().execute(new d("end_region_counter", new Object[0], j));
    }

    @Override // com.salesforce.marketingcloud.analytics.i, com.salesforce.marketingcloud.analytics.AnalyticsManager
    public void trackInboxOpenEvent(InboxMessage inboxMessage) {
        if (inboxMessage == null) {
            g.e(AnalyticsManager.TAG, "InboxMessage was null. Call to trackInboxOpenEvent() ignored.", new Object[0]);
        } else {
            this.e.b().execute(new com.salesforce.marketingcloud.analytics.a(this.d.h(), this.d.b(), com.salesforce.marketingcloud.analytics.b.a(new Date(), 0, 15, Collections.singletonList(inboxMessage.id()), com.salesforce.marketingcloud.internal.d.b(inboxMessage), true)));
        }
    }

    private static void a(n nVar, h hVar) {
        nVar.b().execute(new a("delete_analytics", new Object[0], hVar));
    }

    @Override // com.salesforce.marketingcloud.analytics.i, com.salesforce.marketingcloud.analytics.k
    public void b(Region region) {
        Date date = new Date();
        b(region, date);
        if (region.regionType() == 3) {
            return;
        }
        this.e.b().execute(new com.salesforce.marketingcloud.analytics.a(this.d.h(), this.d.b(), com.salesforce.marketingcloud.analytics.b.a(date, 0, 7, (List<String>) Collections.singletonList(region.id()), true)));
    }

    @Override // com.salesforce.marketingcloud.analytics.i
    public void a(boolean z) {
        if (z) {
            a(this.e, this.d);
        }
    }

    @Override // com.salesforce.marketingcloud.analytics.i
    public void a(long j) {
        this.e.b().execute(new c("end_app_counter", new Object[0], j));
    }

    @Override // com.salesforce.marketingcloud.analytics.i, com.salesforce.marketingcloud.analytics.j
    public void a(NotificationMessage notificationMessage) {
        a(System.currentTimeMillis());
        this.e.b().execute(new com.salesforce.marketingcloud.analytics.a(this.d.h(), this.d.b(), com.salesforce.marketingcloud.analytics.b.a(new Date(), 0, 5, notificationMessage, false)));
    }

    @Override // com.salesforce.marketingcloud.analytics.i, com.salesforce.marketingcloud.analytics.k
    public void a(Region region) {
        Date date = new Date();
        a(region, date);
        this.e.b().execute(new com.salesforce.marketingcloud.analytics.a(this.d.h(), this.d.b(), com.salesforce.marketingcloud.analytics.b.a(date, 0, region.regionType() == 1 ? 6 : 12, (List<String>) Collections.singletonList(region.id()), true)));
    }

    private void b(Region region, Date date) {
        this.e.b().execute(new e("end_region_counter", new Object[0], region, date));
    }

    private void a(Region region, Date date) {
        this.e.b().execute(new com.salesforce.marketingcloud.analytics.a(this.d.h(), this.d.b(), com.salesforce.marketingcloud.analytics.b.a(date, 0, region.regionType() == 1 ? 11 : 13, (List<String>) Collections.singletonList(region.id()), false)));
    }
}
