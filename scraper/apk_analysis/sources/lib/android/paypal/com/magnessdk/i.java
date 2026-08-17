package lib.android.paypal.com.magnessdk;

import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import lib.android.paypal.com.magnessdk.g;
import lib.android.paypal.com.magnessdk.network.base.MagnesNetworking;
import lib.android.paypal.com.magnessdk.network.base.MagnesNetworkingFactoryImpl;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class i extends l {
    private static final String i = "MagnesPostRequest";
    private g.h.d b;
    private Map<String, String> c = new HashMap();
    private Handler d;
    private MagnesNetworkingFactoryImpl e;
    private MagnesSettings f;
    private JSONObject g;
    private boolean h;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[g.h.d.values().length];
            a = iArr;
            try {
                iArr[g.h.d.DEVICE_INFO_URL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[g.h.d.SANDBOX_DEVICE_INFO_URL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[g.h.d.PRODUCTION_JSON_URL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[g.h.d.AUDIT_JSON_URL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public i(g.h.d dVar, JSONObject jSONObject, boolean z, MagnesSettings magnesSettings, Handler handler) {
        this.b = dVar;
        this.g = jSONObject;
        this.h = z;
        this.d = handler;
        this.f = magnesSettings;
        this.e = magnesSettings.getMagnesNetworkingFactoryImpl() == null ? new MagnesNetworkingFactoryImpl() : magnesSettings.getMagnesNetworkingFactoryImpl();
    }

    private String a(JSONObject jSONObject) {
        return String.format(Locale.US, "Dyson/%S (%S %S)", jSONObject.optString(g.d.COMP_VERSION.toString()), jSONObject.optString(g.d.OS_TYPE.toString()), Build.VERSION.RELEASE);
    }

    private void a(int i2, String str, String str2) {
        e.a(getClass(), 0, "MagnesPostRequest for " + str2 + " returned status code " + i2 + ", and responseString: " + str);
    }

    private String e() throws Exception {
        if (this.g == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("appGuid", this.g.optString(g.d.APP_GUID.toString()));
        hashMap.put("libraryVersion", a(this.g));
        hashMap.put(PaymentManager.EXTRA_KEY_ADDITIONAL_DATA, this.g.toString());
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Map.Entry entry : hashMap.entrySet()) {
            if (z) {
                z = false;
            } else {
                sb.append("&");
            }
            sb.append(URLEncoder.encode((String) entry.getKey(), "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
        }
        e.a(getClass(), 0, "Encoded Device info payload : " + sb.toString());
        return sb.toString();
    }

    private String f() throws Exception {
        if (this.g == null) {
            return null;
        }
        int i2 = a.a[this.b.ordinal()];
        if (i2 != 1 && i2 != 2) {
            return this.g.toString();
        }
        String e = e();
        if (e == null) {
            return null;
        }
        return e;
    }

    private String g() {
        if (this.f == null || this.d == null) {
            return null;
        }
        int i2 = a.a[this.b.ordinal()];
        if (i2 == 1 || i2 == 2) {
            return this.f.getEnvironment() == Environment.LIVE ? MagnesSDK.getInstance().a.g() : g.h.d.SANDBOX_DEVICE_INFO_URL.toString();
        }
        if (i2 != 3 && i2 != 4) {
            return this.b.toString();
        }
        Environment environment = this.f.getEnvironment();
        Environment environment2 = Environment.LIVE;
        boolean z = this.h;
        return (environment == environment2 ? z ? g.h.d.AUDIT_JSON_URL : g.h.d.PRODUCTION_JSON_URL : z ? g.h.d.SANDBOX_AUDIT_JSON_URL : g.h.d.SANDBOX_PROD_JSON_URL).toString();
    }

    @Override // lib.android.paypal.com.magnessdk.l
    public void a() {
        Map<String, String> c;
        if (this.f == null) {
            return;
        }
        try {
            int i2 = a.a[this.b.ordinal()];
            if (i2 == 1 || i2 == 2) {
                c = o.c(this.f.getContext());
                if (c == null) {
                    return;
                }
            } else {
                c = o.b(this.f.getContext());
                if (c == null) {
                    return;
                }
            }
            this.c = c;
        } catch (Exception e) {
            e.a((Class<?>) o.class, 3, e);
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
        Handler handler;
        Message obtain;
        a();
        try {
            MagnesNetworking createHttpClient = this.e.createHttpClient(g.h.b.POST);
            String g = g();
            String f = f();
            if (g != null && f != null) {
                createHttpClient.setUri(Uri.parse(g));
                createHttpClient.setHeader(this.c);
                Handler handler2 = this.d;
                handler2.sendMessage(Message.obtain(handler2, g.h.c.POST_REQUEST_STARTED.b(), g));
                int execute = createHttpClient.execute(f.getBytes("UTF-8"));
                String str = new String(createHttpClient.getResponseContent(), "UTF-8");
                Log.d(i, "MagnesPostRequest returned PayPal-Debug-Id: " + createHttpClient.getPayPalDebugId());
                a(execute, str, g);
                if (execute == g.h.c.HTTP_STATUS_200.b()) {
                    handler = this.d;
                    if (handler == null) {
                        return;
                    } else {
                        obtain = Message.obtain(handler, g.h.c.POST_REQUEST_SUCCEEDED.b(), str);
                    }
                } else {
                    handler = this.d;
                    if (handler == null) {
                        return;
                    } else {
                        obtain = Message.obtain(handler, g.h.c.POST_REQUEST_ERROR.b(), Integer.valueOf(execute));
                    }
                }
                handler.sendMessage(obtain);
            }
        } catch (Exception e) {
            e.a(getClass(), 3, e);
            Handler handler3 = this.d;
            if (handler3 != null) {
                handler3.sendMessage(Message.obtain(handler3, g.h.c.POST_REQUEST_ERROR.b(), e));
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
