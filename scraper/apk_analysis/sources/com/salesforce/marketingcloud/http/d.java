package com.salesforce.marketingcloud.http;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import com.google.android.gms.security.ProviderInstaller;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.MCService;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.internal.i;
import com.salesforce.marketingcloud.internal.n;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class d extends com.salesforce.marketingcloud.f {
    public static final String j = "com.salesforce.marketingcloud.http.RESPONSE";
    public static final String k = "http_response";
    public static final String l = "http_request";
    static final String m = g.a("RequestManager");
    private static final int n = 10;
    private final Context f;
    private final SharedPreferences g;
    private n h;
    private BroadcastReceiver i;
    private final Map<String, String> e = new a();
    final Map<com.salesforce.marketingcloud.http.a, c> d = new ArrayMap();

    class a extends LinkedHashMap<String, String> {
        a() {
        }

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<String, String> entry) {
            return size() > 10;
        }
    }

    class b extends i {
        final /* synthetic */ c c;
        final /* synthetic */ com.salesforce.marketingcloud.http.b d;
        final /* synthetic */ e e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, Object[] objArr, c cVar, com.salesforce.marketingcloud.http.b bVar, e eVar) {
            super(str, objArr);
            this.c = cVar;
            this.d = bVar;
            this.e = eVar;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            this.c.a(this.d, this.e);
        }
    }

    public interface c {
        void a(com.salesforce.marketingcloud.http.b bVar, e eVar);
    }

    /* renamed from: com.salesforce.marketingcloud.http.d$d, reason: collision with other inner class name */
    class C0058d extends BroadcastReceiver {
        C0058d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                g.d(d.m, "Received null intent", new Object[0]);
                return;
            }
            String action = intent.getAction();
            if (action == null) {
                g.d(d.m, "Received null action", new Object[0]);
                return;
            }
            if (!action.equals(d.j)) {
                g.a(d.m, "Received unknown action: %s", action);
                return;
            }
            Bundle bundleExtra = intent.getBundleExtra(d.l);
            com.salesforce.marketingcloud.http.b a = bundleExtra != null ? com.salesforce.marketingcloud.http.b.a(bundleExtra) : null;
            e eVar = (e) intent.getParcelableExtra(d.k);
            if (a == null || eVar == null) {
                g.d(d.m, "Received null request/response", new Object[0]);
            } else {
                d.this.a(a, eVar);
            }
        }
    }

    public d(Context context, SharedPreferences sharedPreferences, n nVar) {
        this.f = (Context) com.salesforce.marketingcloud.util.g.a(context, "Context is null");
        this.g = (SharedPreferences) com.salesforce.marketingcloud.util.g.a(sharedPreferences, "SharedPreferences is null");
        this.h = nVar;
    }

    @Override // com.salesforce.marketingcloud.f
    protected final void a(InitializationStatus.a aVar) {
        try {
            a();
        } catch (Exception e) {
            aVar.e(true);
            aVar.a("Failed to install providers: " + e.getMessage());
        }
        this.i = new C0058d();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(j);
        ContextCompat.registerReceiver(this.f, this.i, intentFilter, 4);
    }

    @Override // com.salesforce.marketingcloud.d
    public final String componentName() {
        return "RequestManager";
    }

    @Override // com.salesforce.marketingcloud.d
    public final JSONObject componentState() {
        return new JSONObject(this.e);
    }

    @Override // com.salesforce.marketingcloud.f, com.salesforce.marketingcloud.d
    public final void tearDown(boolean z) {
        BroadcastReceiver broadcastReceiver;
        synchronized (this.d) {
            this.d.clear();
        }
        Context context = this.f;
        if (context == null || (broadcastReceiver = this.i) == null) {
            return;
        }
        context.unregisterReceiver(broadcastReceiver);
    }

    private void a() throws Exception {
        ProviderInstaller.installIfNeeded(this.f);
    }

    public void a(com.salesforce.marketingcloud.http.a aVar, c cVar) {
        synchronized (this.d) {
            if (this.d.put(aVar, cVar) != null) {
                g.a(m, "%s replaces previous listener for $s requests", cVar.getClass().getName(), aVar.name());
            }
        }
    }

    public void a(com.salesforce.marketingcloud.http.a aVar) {
        synchronized (this.d) {
            this.d.remove(aVar);
        }
    }

    public synchronized void a(com.salesforce.marketingcloud.http.b bVar) {
        com.salesforce.marketingcloud.util.g.a(bVar, "request is null");
        try {
            a();
        } catch (Exception unused) {
            g.e(m, "Failed to verify SSL providers via Google Play Services.", new Object[0]);
        }
        long currentTimeMillis = System.currentTimeMillis();
        long c2 = bVar.p().c(this.g);
        long a2 = bVar.p().a(this.g);
        if (currentTimeMillis > c2 && currentTimeMillis > a2) {
            bVar.p().b(this.g);
            MCService.a(this.f, bVar);
        } else {
            a(bVar, e.a("Too Many Requests", 429));
        }
    }

    void a(com.salesforce.marketingcloud.http.b bVar, e eVar) {
        com.salesforce.marketingcloud.http.a p = bVar.p();
        g.d(m, "%s request took %dms with code: %d", p.name(), Long.valueOf(eVar.p()), Integer.valueOf(eVar.j()));
        p.a(this.g, eVar);
        try {
            this.e.put(bVar.r(), String.format(Locale.ENGLISH, "%s - %d", eVar.m(), Integer.valueOf(eVar.j())));
        } catch (Exception e) {
            g.b(m, e, "Failed to record response.", new Object[0]);
        }
        synchronized (this.d) {
            c cVar = this.d.get(p);
            if (cVar != null) {
                try {
                    this.h.a().execute(new b("onResponse", new Object[0], cVar, bVar, eVar));
                } catch (Exception e2) {
                    g.b(m, e2, "Failed to deliver response.", new Object[0]);
                }
            } else {
                g.e(m, "Request %s complete, but no listener was present to handle response %d.", bVar.r(), Integer.valueOf(eVar.j()));
            }
        }
    }
}
