package lib.android.paypal.com.magnessdk;

import android.content.Context;
import android.os.Handler;
import lib.android.paypal.com.magnessdk.g;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class j extends f {
    public static final String i = "RAMP_CONFIG";
    static final int j = 7200;
    public final String d = g.i.CONF_REFRESH_TIME_KEY.toString();
    private Context e;
    private Handler f;
    private MagnesSettings g;
    private JSONObject h;

    public j(MagnesSettings magnesSettings, Handler handler) {
        this.e = magnesSettings.getContext();
        this.g = magnesSettings;
        this.f = handler;
        a(c());
        try {
            e.a(getClass(), 0, a().toString(2));
        } catch (JSONException unused) {
        }
    }

    private JSONObject e() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(g.i.OPEN.toString(), false);
            jSONObject.put(g.i.RAMP_THRESHOLD.toString(), 0);
            jSONObject.put(g.i.MIN_VERSION.toString(), "4.4.0");
            jSONObject.put(g.i.EXCLUDED.toString(), new JSONArray());
            jSONObject.put(g.i.APP_IDS.toString(), new JSONArray());
            jSONObject.put(g.i.APP_SOURCES.toString(), new JSONArray());
        } catch (Exception e) {
            e.a(getClass(), 3, "Failed to create deafult config due to " + e.getLocalizedMessage());
        }
        return jSONObject;
    }

    @Override // lib.android.paypal.com.magnessdk.f
    public JSONObject a() {
        return this.h;
    }

    @Override // lib.android.paypal.com.magnessdk.f
    protected void a(JSONObject jSONObject) {
        this.h = jSONObject;
    }

    @Override // lib.android.paypal.com.magnessdk.f
    protected JSONObject b() {
        e.a(getClass(), 0, "entering getDefaultConfig");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("s", e());
            jSONObject.put(q.r1, e());
            jSONObject.put(q.s1, e());
            jSONObject.put(q.t1, e());
            jSONObject.put(g.i.CONF_REFRESH_TIME_KEY.toString(), j);
        } catch (JSONException e) {
            e.a(getClass(), 3, e);
        }
        return jSONObject;
    }

    @Override // lib.android.paypal.com.magnessdk.f
    protected JSONObject c() {
        try {
            JSONObject a = f.a(i, this.g.getContext());
            if (a == null) {
                new h(g.h.d.RAMP_CONFIG_URL, this.g, this.f, null).c();
                return b();
            }
            if (f.a(a, Long.parseLong(b(this.e, i)), g.c.RAMP)) {
                e.a(getClass(), 0, "Cached config used while fetching.");
                new h(g.h.d.RAMP_CONFIG_URL, this.g, this.f, null).c();
            }
            return a;
        } catch (Exception e) {
            e.a(getClass(), 3, e);
            return b();
        }
    }

    @Override // lib.android.paypal.com.magnessdk.f
    protected String d() {
        return g.h.d.RAMP_CONFIG_URL.toString();
    }
}
