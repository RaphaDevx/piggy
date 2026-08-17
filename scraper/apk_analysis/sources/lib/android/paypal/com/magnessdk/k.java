package lib.android.paypal.com.magnessdk;

import android.content.Context;
import android.os.Handler;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import lib.android.paypal.com.magnessdk.g;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class k extends f {
    private static final String j = "5.0";
    public static final String k = "REMOTE_CONFIG";
    private static final String l = "QW5kcm9pZE1hZ25lcw==";
    private static final int m = 5;
    private static final int n = 86400;
    private static final int o = 128;
    private static BitSet p = null;
    private static boolean q = false;
    private static JSONArray r;
    public final String d = "conf_refresh_time_interval";
    private Context e;
    private MagnesSettings f;
    private JSONObject g;
    private Handler h;
    private boolean i;

    public k(MagnesSettings magnesSettings, Handler handler) {
        this.i = false;
        this.f = magnesSettings;
        this.e = magnesSettings.getContext();
        this.h = handler;
        this.i = magnesSettings.isDisableRemoteConfig();
        a(c());
        try {
            e.a(getClass(), 0, this.g.toString(2));
        } catch (JSONException e) {
            e.a(getClass(), 3, e);
        }
    }

    public static void a(boolean z) {
        q = z;
    }

    private boolean a(String str, String str2) {
        e.a(getClass(), 0, "entering shouldUseCachedConfiguration");
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        e.a(getClass(), 0, "Comparing Cached version is " + str + " default version is " + str2);
        int i = 0;
        while (i < split.length && i < split2.length && split[i].equals(split2[i])) {
            i++;
        }
        return Integer.valueOf(Integer.signum((i >= split.length || i >= split2.length) ? split.length - split2.length : Integer.valueOf(split[i]).compareTo(Integer.valueOf(split2[i])))).intValue() >= 0;
    }

    public static void b(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray(g.j.NOT_COLLECTABLE.toString());
        if (optJSONArray != null) {
            r = optJSONArray;
        }
        BitSet bitSet = new BitSet(128);
        p = bitSet;
        bitSet.set(0, 128, true);
        for (int i = 0; optJSONArray != null && i < optJSONArray.length(); i++) {
            try {
                p.set(optJSONArray.getInt(i), false);
            } catch (JSONException e) {
                e.a((Class<?>) k.class, 3, e);
            }
        }
    }

    @Override // lib.android.paypal.com.magnessdk.f
    JSONObject a() {
        return this.g;
    }

    @Override // lib.android.paypal.com.magnessdk.f
    protected void a(JSONObject jSONObject) {
        b(jSONObject);
        this.g = jSONObject;
    }

    public boolean a(int i) {
        return p.get(i);
    }

    @Override // lib.android.paypal.com.magnessdk.f
    protected JSONObject b() {
        e.a(getClass(), 0, "entering getDefaultRemoteConfig");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(g.j.CONF_VERSION.toString(), j);
            jSONObject.put(g.j.CONF_REFRESH_TIME_KEY.toString(), n);
            jSONObject.put(g.j.CONF_ENDPOINT_URL.toString(), g.h.d.DEVICE_INFO_URL.toString());
        } catch (JSONException e) {
            e.a(getClass(), 3, e);
        }
        return jSONObject;
    }

    @Override // lib.android.paypal.com.magnessdk.f
    protected JSONObject c() {
        try {
            JSONObject a = f.a(k, this.e);
            if (a == null) {
                new h(g.h.d.REMOTE_CONFIG_URL, this.f, this.h, null).c();
            } else {
                if (a(a.optString(g.e.CONF_VERSION.toString(), ""), j)) {
                    boolean a2 = f.a(a, Long.parseLong(b(this.e, k)), g.c.REMOTE);
                    if (!this.i && a2) {
                        new h(g.h.d.REMOTE_CONFIG_URL, this.f, this.h, null).c();
                    }
                    e.a(getClass(), 0, "Using cached currentConfig due to isRemoteConfigDisabled : " + this.i + " or isConfigExpired : " + a2);
                    return a;
                }
                f.c(this.e, k);
            }
        } catch (Exception e) {
            e.a(getClass(), 3, e);
        }
        return b();
    }

    @Override // lib.android.paypal.com.magnessdk.f
    protected String d() {
        return g.h.d.REMOTE_CONFIG_URL.toString();
    }

    public List<String> e() throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = this.g.optJSONArray(g.j.ANDROID_APPS_TO_CHECK.toString());
        for (int i = 0; optJSONArray != null && i < optJSONArray.length(); i++) {
            arrayList.add(optJSONArray.getString(i));
        }
        return arrayList;
    }

    public String f() {
        return this.g.optString(g.j.CONF_VERSION.toString());
    }

    public String g() {
        return this.g.optString(g.j.CONF_ENDPOINT_URL.toString(), g.h.d.DEVICE_INFO_URL.toString());
    }

    public String h() {
        return this.g.optString(g.j.MG_ID.toString(), l);
    }

    public JSONArray i() {
        return r;
    }

    public int j() {
        return this.g.optInt(g.j.SENSOR_COLLECT_TIME.toString(), 5);
    }

    public boolean k() {
        return q;
    }
}
