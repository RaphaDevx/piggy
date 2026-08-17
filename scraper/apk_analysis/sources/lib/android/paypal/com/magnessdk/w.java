package lib.android.paypal.com.magnessdk;

import android.os.Handler;
import android.view.MotionEvent;
import java.util.Timer;
import java.util.TimerTask;
import lib.android.paypal.com.magnessdk.g;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
class w extends q {
    private static w K1;
    private j G1;
    private MagnesSettings H1;
    private Handler I1;
    private JSONObject x1 = new JSONObject();
    private JSONArray y1 = new JSONArray();
    private JSONArray z1 = new JSONArray();
    private boolean A1 = false;
    private int B1 = 0;
    private boolean C1 = false;
    private boolean D1 = true;
    private int E1 = 0;
    private String F1 = "";
    private Timer J1 = new Timer();

    class a extends TimerTask {
        a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            w.this.C1 = true;
            w.this.d();
            w.this.c();
        }
    }

    class b {
        float a;
        float b;
        float c;
        float d;
        int e;
        long f;
        long g;

        b(MotionEvent motionEvent, long j, long j2) {
            this.a = motionEvent.getPressure();
            this.b = motionEvent.getSize();
            this.c = motionEvent.getRawX();
            this.d = motionEvent.getRawY();
            this.e = motionEvent.getPointerCount();
            this.f = j;
            this.g = j2;
        }
    }

    w() {
    }

    private String a(float f) {
        return String.valueOf(Math.round(f * r0.b()) / g.n.MAXIMUM_PRECISION.b());
    }

    private JSONArray a(JSONArray jSONArray) throws JSONException {
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            jSONArray2.put(jSONArray.getJSONObject(i));
        }
        return jSONArray2;
    }

    private JSONObject a(b bVar) {
        if (bVar == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            if (bVar.a != 0.0f) {
                jSONObject.put(g.n.FORCE.toString(), a(bVar.a));
            }
            if (bVar.b != 0.0f) {
                jSONObject.put(g.n.RADIUS.toString(), a(bVar.b));
            }
            jSONObject.put(g.n.TOUCH_LOCATION_X.toString(), String.valueOf(Math.round(bVar.c)));
            jSONObject.put(g.n.TOUCH_LOCATION_Y.toString(), String.valueOf(Math.round(bVar.d)));
            if (bVar.e > 1) {
                jSONObject.put(g.n.TOUCH_COUNT.toString(), String.valueOf(bVar.e));
            }
            return jSONObject;
        } catch (JSONException e) {
            e.a(getClass(), 3, e);
            return null;
        }
    }

    private void b() throws JSONException {
        if (this.z1.length() >= g.n.MAXIMUM_TOUCH_COUNT.c()) {
            c();
            return;
        }
        JSONArray jSONArray = this.y1;
        if (jSONArray != null && jSONArray.length() > 0) {
            this.x1.put(g.n.TOUCH_EVENT.toString(), a(this.y1));
            this.z1.put(this.x1);
        }
        this.y1 = new JSONArray();
        this.x1 = new JSONObject();
    }

    private void b(String str) {
        f();
        this.F1 = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x006f A[Catch: JSONException -> 0x0080, TryCatch #0 {JSONException -> 0x0080, blocks: (B:2:0x0000, B:4:0x000c, B:5:0x001d, B:7:0x0027, B:8:0x0031, B:9:0x0053, B:10:0x0056, B:12:0x006f, B:13:0x0072, B:15:0x007c, B:20:0x0036, B:22:0x0042), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x007c A[Catch: JSONException -> 0x0080, TRY_LEAVE, TryCatch #0 {JSONException -> 0x0080, blocks: (B:2:0x0000, B:4:0x000c, B:5:0x001d, B:7:0x0027, B:8:0x0031, B:9:0x0053, B:10:0x0056, B:12:0x006f, B:13:0x0072, B:15:0x007c, B:20:0x0036, B:22:0x0042), top: B:1:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(lib.android.paypal.com.magnessdk.w.b r6) {
        /*
            r5 = this;
            long r0 = r6.f     // Catch: org.json.JSONException -> L80
            lib.android.paypal.com.magnessdk.g$n r2 = lib.android.paypal.com.magnessdk.g.n.UNINITIALIZED_TIME     // Catch: org.json.JSONException -> L80
            long r3 = r2.d()     // Catch: org.json.JSONException -> L80
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 == 0) goto L1d
            org.json.JSONObject r0 = r5.x1     // Catch: org.json.JSONException -> L80
            lib.android.paypal.com.magnessdk.g$n r1 = lib.android.paypal.com.magnessdk.g.n.START_TIME     // Catch: org.json.JSONException -> L80
            java.lang.String r1 = r1.toString()     // Catch: org.json.JSONException -> L80
            long r3 = r6.f     // Catch: org.json.JSONException -> L80
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch: org.json.JSONException -> L80
            r0.put(r1, r3)     // Catch: org.json.JSONException -> L80
        L1d:
            long r0 = r6.g     // Catch: org.json.JSONException -> L80
            long r3 = r2.d()     // Catch: org.json.JSONException -> L80
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 == 0) goto L36
            org.json.JSONObject r0 = r5.x1     // Catch: org.json.JSONException -> L80
            lib.android.paypal.com.magnessdk.g$n r1 = lib.android.paypal.com.magnessdk.g.n.END_TIME     // Catch: org.json.JSONException -> L80
            java.lang.String r1 = r1.toString()     // Catch: org.json.JSONException -> L80
            long r3 = r6.g     // Catch: org.json.JSONException -> L80
        L31:
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch: org.json.JSONException -> L80
            goto L53
        L36:
            int r0 = r5.E1     // Catch: org.json.JSONException -> L80
            lib.android.paypal.com.magnessdk.g$n r1 = lib.android.paypal.com.magnessdk.g.n.MAXIMUM_EVENT_COUNT     // Catch: org.json.JSONException -> L80
            int r1 = r1.c()     // Catch: org.json.JSONException -> L80
            int r1 = r1 + (-1)
            if (r0 < r1) goto L56
            long r0 = java.lang.System.currentTimeMillis()     // Catch: org.json.JSONException -> L80
            r6.g = r0     // Catch: org.json.JSONException -> L80
            org.json.JSONObject r0 = r5.x1     // Catch: org.json.JSONException -> L80
            lib.android.paypal.com.magnessdk.g$n r1 = lib.android.paypal.com.magnessdk.g.n.END_TIME     // Catch: org.json.JSONException -> L80
            java.lang.String r1 = r1.toString()     // Catch: org.json.JSONException -> L80
            long r3 = r6.g     // Catch: org.json.JSONException -> L80
            goto L31
        L53:
            r0.put(r1, r3)     // Catch: org.json.JSONException -> L80
        L56:
            org.json.JSONArray r0 = r5.y1     // Catch: org.json.JSONException -> L80
            org.json.JSONObject r1 = r5.a(r6)     // Catch: org.json.JSONException -> L80
            r0.put(r1)     // Catch: org.json.JSONException -> L80
            int r0 = r5.E1     // Catch: org.json.JSONException -> L80
            int r0 = r0 + 1
            r5.E1 = r0     // Catch: org.json.JSONException -> L80
            long r0 = r6.g     // Catch: org.json.JSONException -> L80
            long r2 = r2.d()     // Catch: org.json.JSONException -> L80
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 == 0) goto L72
            r5.b()     // Catch: org.json.JSONException -> L80
        L72:
            int r6 = r5.E1     // Catch: org.json.JSONException -> L80
            lib.android.paypal.com.magnessdk.g$n r0 = lib.android.paypal.com.magnessdk.g.n.MAXIMUM_EVENT_COUNT     // Catch: org.json.JSONException -> L80
            int r0 = r0.c()     // Catch: org.json.JSONException -> L80
            if (r6 < r0) goto L89
            r5.c()     // Catch: org.json.JSONException -> L80
            goto L89
        L80:
            r6 = move-exception
            java.lang.Class r5 = r5.getClass()
            r0 = 3
            lib.android.paypal.com.magnessdk.e.a(r5, r0, r6)
        L89:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: lib.android.paypal.com.magnessdk.w.b(lib.android.paypal.com.magnessdk.w$b):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.D1 = false;
        if (this.A1) {
            return;
        }
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        try {
            JSONObject jSONObject = this.x1;
            g.n nVar = g.n.END_TIME;
            String optString = jSONObject.optString(nVar.toString());
            if (optString == null || optString.isEmpty()) {
                this.x1.put(nVar.toString(), String.valueOf(System.currentTimeMillis()));
            }
            b();
        } catch (JSONException e) {
            e.a(getClass(), 3, e);
        }
    }

    static synchronized w e() {
        w wVar;
        synchronized (w.class) {
            if (K1 == null) {
                K1 = new w();
            }
            wVar = K1;
        }
        return wVar;
    }

    private void f() {
        if (this.C1) {
            this.J1.cancel();
        }
        this.C1 = false;
        this.E1 = 0;
        this.B1 = 0;
        this.D1 = true;
        if (!this.A1) {
            g();
        }
        this.y1 = new JSONArray();
        this.z1 = new JSONArray();
        this.A1 = false;
    }

    private void g() {
        this.A1 = true;
        JSONArray jSONArray = this.z1;
        if (jSONArray == null || jSONArray.length() == 0) {
            return;
        }
        try {
            if (this.z1.length() > 0) {
                JSONArray a2 = a(this.z1);
                this.z1 = new JSONArray();
                boolean a3 = q.a(this.G1, q.t1);
                JSONObject a4 = a3 ? q.a(this.F1, a2, q.t1) : q.b(this.F1, a2, q.t1);
                if (a4 != null) {
                    new i(g.h.d.PRODUCTION_JSON_URL, a4, a3, this.H1, this.I1).c();
                }
            }
        } catch (Exception e) {
            e.a(getClass(), 3, e);
        }
    }

    private void h() {
        this.J1.schedule(new a(), g.n.MAXIMUM_TIME_ALLOWED.d());
    }

    @Override // lib.android.paypal.com.magnessdk.q
    JSONObject a() {
        return null;
    }

    @Override // lib.android.paypal.com.magnessdk.q
    JSONObject a(MagnesSettings magnesSettings, j jVar, k kVar) {
        return null;
    }

    @Override // lib.android.paypal.com.magnessdk.q
    void a(int i, MagnesSettings magnesSettings) {
    }

    void a(MotionEvent motionEvent, String str) {
        j jVar;
        if (!this.D1 || str == null || str.isEmpty() || motionEvent == null || (jVar = this.G1) == null || !a(jVar, this.H1.getMagnesSource(), q.k1, q.t1, this.H1.getContext())) {
            return;
        }
        if (!this.F1.equals(str)) {
            b(str);
        }
        g.n nVar = g.n.UNINITIALIZED_TIME;
        long d = nVar.d();
        long d2 = nVar.d();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            d = System.currentTimeMillis();
            int i = this.B1 + 1;
            this.B1 = i;
            if (i == 1) {
                h();
            }
        } else if (actionMasked == 1) {
            d2 = System.currentTimeMillis();
        } else if (actionMasked != 2) {
            return;
        }
        b(new b(motionEvent, d, d2));
    }

    void a(j jVar, MagnesSettings magnesSettings, Handler handler) {
        this.G1 = jVar;
        this.H1 = magnesSettings;
        this.I1 = handler;
    }
}
