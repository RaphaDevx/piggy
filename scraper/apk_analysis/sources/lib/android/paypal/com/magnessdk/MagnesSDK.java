package lib.android.paypal.com.magnessdk;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.MotionEvent;
import android.widget.EditText;
import java.util.HashMap;
import lib.android.paypal.com.magnessdk.MagnesSettings;
import lib.android.paypal.com.magnessdk.g;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public final class MagnesSDK {
    private static final int j = 32;
    private static MagnesSDK k;
    public k a;
    MagnesSettings b;
    private JSONObject c;
    private Handler d;
    private HandlerThread e;
    private s f;
    private j g;
    private v h = v.b();
    private w i = w.e();

    private MagnesSDK() {
    }

    private MagnesResult a(Context context, String str, HashMap<String, String> hashMap, boolean z) throws InvalidInputException {
        String str2;
        StringBuilder sb = new StringBuilder("COLLECT method called with paypalClientMetaDataId : ");
        sb.append(str);
        sb.append(" , Is pass in additionalData null? : ");
        sb.append(Boolean.toString(hashMap == null));
        e.a((Class<?>) MagnesSDK.class, 0, sb.toString());
        if (str != null && str.length() > 32) {
            throw new InvalidInputException(g.b.c.CMID_EXCEPTION_MESSAGE.toString());
        }
        if (this.b == null) {
            e.a((Class<?>) MagnesSDK.class, 2, "No MagnesSettings specified, using platform default.");
            MagnesSettings build = new MagnesSettings.Builder(context).build();
            this.b = build;
            setUp(build);
        }
        if (this.a.k()) {
            e.a((Class<?>) MagnesSDK.class, 0, "nc presents, collecting coreData.");
            s sVar = new s();
            this.f = sVar;
            this.c = sVar.a(this.b, this.g, this.a);
            k.a(false);
        }
        JSONObject a = this.f.a(new t(z).a(this.b, this.g, this.a, this.f.b(), str, hashMap, this.d));
        try {
            e.a((Class<?>) MagnesSDK.class, 0, "Device Info JSONObject : " + a.toString(2));
            str2 = a.getString(g.b.a);
        } catch (JSONException e) {
            e.a((Class<?>) MagnesSDK.class, 3, e);
            str2 = null;
        }
        return new MagnesResult().setDeviceInfo(a).setPaypalClientMetaDataId(str2);
    }

    private void a(Context context, JSONObject jSONObject) {
        new i(g.h.d.DEVICE_INFO_URL, jSONObject, false, this.b, this.d).c();
        if (c()) {
            new h(g.h.d.PRODUCTION_BEACON_URL, this.b, this.d, jSONObject).c();
        }
    }

    private void b() {
        if (this.e == null) {
            HandlerThread handlerThread = new HandlerThread("MagnesHandlerThread");
            this.e = handlerThread;
            handlerThread.start();
            this.d = n.a(this.e.getLooper(), this);
        }
    }

    private boolean c() {
        return !this.b.isDisableBeacon() && this.b.getEnvironment() == Environment.LIVE;
    }

    public static synchronized MagnesSDK getInstance() {
        MagnesSDK magnesSDK;
        synchronized (MagnesSDK.class) {
            if (k == null) {
                k = new MagnesSDK();
            }
            magnesSDK = k;
        }
        return magnesSDK;
    }

    j a() {
        if (this.g == null) {
            this.g = new j(this.b, this.d);
        }
        return this.g;
    }

    public MagnesResult collect(Context context) {
        try {
            return a(context, null, null, false);
        } catch (InvalidInputException unused) {
            return null;
        }
    }

    public MagnesResult collect(Context context, String str, HashMap<String, String> hashMap) throws InvalidInputException {
        return a(context, str, hashMap, false);
    }

    public MagnesResult collectAndSubmit(Context context) {
        try {
            return collectAndSubmit(context, null, null);
        } catch (InvalidInputException unused) {
            return null;
        }
    }

    public MagnesResult collectAndSubmit(Context context, String str, HashMap<String, String> hashMap) throws InvalidInputException {
        StringBuilder sb = new StringBuilder("SUBMIT method called with paypalClientMetaDataId : ");
        sb.append(str);
        sb.append(" , Is pass in additionalData null? : ");
        sb.append(Boolean.toString(hashMap == null));
        e.a((Class<?>) MagnesSDK.class, 0, sb.toString());
        if (str != null && str.length() > 32) {
            throw new InvalidInputException(g.b.c.CMID_EXCEPTION_MESSAGE.toString());
        }
        MagnesResult a = a(context, str, hashMap, true);
        a(context, a.getDeviceInfo());
        return a;
    }

    public void collectTelemetryData(Context context, EditText editText, String str, String str2, boolean z) {
        if (this.b == null) {
            e.a((Class<?>) MagnesSDK.class, 2, "No MagnesSettings specified, using platform default.");
            MagnesSettings build = new MagnesSettings.Builder(context).build();
            this.b = build;
            setUp(build);
        }
        v.b().a(editText, str, str2, context, z);
    }

    public void collectTouchData(MotionEvent motionEvent, Context context, String str) {
        if (this.b == null) {
            e.a((Class<?>) MagnesSDK.class, 2, "No MagnesSettings specified, using platform default.");
            MagnesSettings build = new MagnesSettings.Builder(context).build();
            this.b = build;
            setUp(build);
        }
        w.e().a(motionEvent, str);
    }

    public void setTelemetryFocusChanged(Context context, EditText editText, String str, String str2, boolean z) {
        if (this.b == null) {
            e.a((Class<?>) MagnesSDK.class, 2, "No MagnesSettings specified, using platform default.");
            MagnesSettings build = new MagnesSettings.Builder(context).build();
            this.b = build;
            setUp(build);
        }
        v.b().b(str, str2, z);
    }

    public MagnesSettings setUp(MagnesSettings magnesSettings) {
        this.b = magnesSettings;
        b();
        this.a = new k(magnesSettings, this.d);
        j jVar = new j(magnesSettings, this.d);
        this.g = jVar;
        this.h.a(jVar, this.b, this.d);
        this.i.a(this.g, this.b, this.d);
        if (this.f == null) {
            s sVar = new s();
            this.f = sVar;
            this.c = sVar.a(magnesSettings, this.g, this.a);
        }
        return magnesSettings;
    }
}
