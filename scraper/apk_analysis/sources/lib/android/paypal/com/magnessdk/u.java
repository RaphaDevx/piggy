package lib.android.paypal.com.magnessdk;

import android.content.Context;
import android.os.Handler;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lib.android.paypal.com.magnessdk.g;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class u extends q {
    private static u G1;
    private JSONArray A1;
    private Handler B1;
    private MagnesSettings C1;
    private x D1;
    private x E1;
    private x F1;
    private String x1 = "";
    private JSONObject y1;
    private k z1;

    class a implements Runnable {
        final /* synthetic */ JSONObject a;

        a(JSONObject jSONObject) {
            this.a = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (u.this.D1 != null && this.a.optBoolean(g.l.AC.toString(), false)) {
                u.this.A1.put(u.this.D1.f());
            }
            if (u.this.E1 != null && this.a.optBoolean(g.l.GY.toString(), false)) {
                u.this.A1.put(u.this.E1.f());
            }
            if (u.this.F1 != null && this.a.optBoolean(g.l.MG.toString(), false)) {
                u.this.A1.put(u.this.F1.f());
            }
            u.this.c();
        }
    }

    u() {
    }

    static synchronized u b() {
        u uVar;
        synchronized (u.class) {
            if (G1 == null) {
                G1 = new u();
            }
            uVar = G1;
        }
        return uVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        try {
            boolean a2 = q.a("s");
            JSONObject a3 = a2 ? q.a(this.x1, this.A1, "s") : q.b(this.x1, this.A1, "s");
            if (a3 != null) {
                new i(g.h.d.PRODUCTION_JSON_URL, a3, a2, this.C1, this.B1).c();
            }
        } catch (Exception e) {
            e.a((Class<?>) u.class, 3, e);
        }
    }

    @Override // lib.android.paypal.com.magnessdk.q
    JSONObject a() {
        return null;
    }

    JSONObject a(MagnesSettings magnesSettings, String str, JSONObject jSONObject) {
        this.x1 = str;
        this.y1 = jSONObject;
        a(96, magnesSettings);
        a(97, magnesSettings);
        a(102, magnesSettings);
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        newSingleThreadScheduledExecutor.schedule(new a(jSONObject), this.z1.j(), TimeUnit.SECONDS);
        newSingleThreadScheduledExecutor.shutdown();
        return null;
    }

    @Override // lib.android.paypal.com.magnessdk.q
    JSONObject a(MagnesSettings magnesSettings, j jVar, k kVar) {
        return null;
    }

    @Override // lib.android.paypal.com.magnessdk.q
    void a(int i, MagnesSettings magnesSettings) {
        x xVar;
        try {
            Context context = magnesSettings.getContext();
            if (i != 96) {
                if (i != 97) {
                    if (i != 102 || !this.z1.a(i)) {
                        return;
                    }
                    this.F1 = new x(context, this.B1, 2);
                    if (!this.y1.optBoolean(g.l.MG.toString(), false)) {
                        return;
                    } else {
                        xVar = this.F1;
                    }
                } else {
                    if (!this.z1.a(i)) {
                        return;
                    }
                    this.E1 = new x(context, this.B1, 4);
                    if (!this.y1.optBoolean(g.l.GY.toString(), false)) {
                        return;
                    } else {
                        xVar = this.E1;
                    }
                }
            } else {
                if (!this.z1.a(i)) {
                    return;
                }
                this.D1 = new x(context, this.B1, 1);
                if (!this.y1.optBoolean(g.l.AC.toString(), false)) {
                    return;
                } else {
                    xVar = this.D1;
                }
            }
            xVar.c();
        } catch (Exception e) {
            e.a((Class<?>) u.class, 3, e);
        }
    }

    void a(k kVar, Handler handler, MagnesSettings magnesSettings) {
        this.B1 = handler;
        this.z1 = kVar;
        this.C1 = magnesSettings;
        this.A1 = new JSONArray();
    }
}
