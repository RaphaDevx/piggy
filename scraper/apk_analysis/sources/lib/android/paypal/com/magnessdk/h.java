package lib.android.paypal.com.magnessdk;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.HttpHeaders;
import lib.android.paypal.com.magnessdk.g;
import lib.android.paypal.com.magnessdk.network.base.MagnesNetworking;
import lib.android.paypal.com.magnessdk.network.base.MagnesNetworkingFactoryImpl;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class h extends l {
    private g.h.d b;
    private Map<String, String> c = new HashMap();
    private Handler d;
    private MagnesNetworkingFactoryImpl e;
    private MagnesSettings f;
    private JSONObject g;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[g.h.d.values().length];
            a = iArr;
            try {
                iArr[g.h.d.RAMP_CONFIG_URL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[g.h.d.REMOTE_CONFIG_URL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public h(g.h.d dVar, MagnesSettings magnesSettings, Handler handler, JSONObject jSONObject) {
        this.b = dVar;
        this.f = magnesSettings;
        this.d = handler;
        this.e = magnesSettings.getMagnesNetworkingFactoryImpl() == null ? new MagnesNetworkingFactoryImpl() : magnesSettings.getMagnesNetworkingFactoryImpl();
        this.g = jSONObject;
    }

    private void a(int i, String str) {
        e.a(getClass(), 0, "MagesGetRequest for " + this.b.toString() + " returned status code " + i + ", and responseString: " + str);
    }

    private void a(String str) throws JSONException {
        int i = a.a[this.b.ordinal()];
        if (i == 1) {
            f.a(this.f.getContext(), str, j.i);
            return;
        }
        if (i != 2) {
            return;
        }
        JSONObject jSONObject = new JSONObject(str);
        f.a(this.f.getContext(), jSONObject.toString(), k.k);
        k.b(jSONObject);
        if (jSONObject.optJSONArray(g.d.NOT_COLLECTIBLE_LIST.toString()) != null) {
            k.a(true);
        }
    }

    private String e() {
        if (this.g == null) {
            return null;
        }
        return g.h.d.PRODUCTION_BEACON_URL.toString() + "?p=" + this.g.optString(g.b.a) + "&i=" + this.g.optString(g.e.IP_ADDRS.toString()) + "&t=" + String.valueOf(System.currentTimeMillis() / 1000) + "&a=" + this.f.getMagnesSource();
    }

    private String f() {
        if (this.b == g.h.d.PRODUCTION_BEACON_URL) {
            if (this.g == null) {
                return null;
            }
            String e = e();
            if (e != null && e.length() > 0) {
                return e;
            }
        }
        return this.b.toString();
    }

    private void g() {
        JSONObject jSONObject = this.g;
        if (jSONObject == null) {
            return;
        }
        this.c.put(HttpHeaders.USER_AGENT, String.format("%s/%s/%s/Android", jSONObject.optString(g.d.APP_ID.toString()), this.g.optString(g.d.APP_VERSION.toString()), this.g.optString(g.d.APP_GUID.toString())));
        this.c.put(HttpHeaders.ACCEPT_LANGUAGE, "en-us");
    }

    @Override // lib.android.paypal.com.magnessdk.l
    public void a() {
        if (this.b == g.h.d.PRODUCTION_BEACON_URL) {
            g();
        }
    }

    @Override // lib.android.paypal.com.magnessdk.l
    public void c() {
        if (this.f.isEnableNetworkOnCallerThread()) {
            d();
        } else {
            b();
        }
    }

    @Override // lib.android.paypal.com.magnessdk.l
    public void d() {
        Message obtain;
        Handler handler;
        a();
        try {
            MagnesNetworking createHttpClient = this.e.createHttpClient(g.h.b.GET);
            String f = f();
            if (f == null) {
                return;
            }
            createHttpClient.setUri(Uri.parse(f));
            Map<String, String> map = this.c;
            if (map != null && !map.isEmpty()) {
                createHttpClient.setHeader(this.c);
            }
            Handler handler2 = this.d;
            if (handler2 != null) {
                handler2.sendMessage(Message.obtain(handler2, g.h.c.GET_REQUEST_STARTED.b(), "Magnes Request Started for URL: " + f));
            }
            int execute = createHttpClient.execute(null);
            String str = new String(createHttpClient.getResponseContent(), "UTF-8");
            a(execute, str);
            if (execute == g.h.c.HTTP_STATUS_200.b()) {
                a(str);
                handler = this.d;
                if (handler == null) {
                    return;
                } else {
                    obtain = Message.obtain(handler, g.h.c.GET_REQUEST_SUCCEEDED.b(), str);
                }
            } else {
                Handler handler3 = this.d;
                if (handler3 == null) {
                    return;
                }
                obtain = Message.obtain(handler3, g.h.c.GET_REQUEST_ERROR.b(), execute + " : " + str);
                handler = handler3;
            }
            handler.sendMessage(obtain);
        } catch (Exception e) {
            Handler handler4 = this.d;
            if (handler4 != null) {
                handler4.sendMessage(Message.obtain(handler4, g.h.c.GET_REQUEST_ERROR.b(), e));
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.d == null) {
            return;
        }
        d();
    }
}
