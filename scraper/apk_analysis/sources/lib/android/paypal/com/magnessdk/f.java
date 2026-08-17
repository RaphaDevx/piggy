package lib.android.paypal.com.magnessdk;

import android.content.Context;
import java.io.File;
import lib.android.paypal.com.magnessdk.g;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public abstract class f {
    static final int a = 1000;
    private static final String b = "_TIME";
    private static final String c = "_DATA";

    protected static String a(Context context, String str) {
        e.a((Class<?>) f.class, 0, "Loading loadCachedConfigData");
        return d.b(new File(context.getFilesDir(), str + c));
    }

    protected static JSONObject a(String str, Context context) {
        String a2;
        e.a((Class<?>) f.class, 0, "entering getCachedConfig");
        try {
            a2 = a(context, str);
        } catch (Exception e) {
            e.a((Class<?>) f.class, 3, e);
        }
        if (a2.isEmpty()) {
            e.a((Class<?>) f.class, 0, "leaving getCachedConfig,cached config loaded empty");
            return null;
        }
        e.a((Class<?>) f.class, 0, "leaving getCachedConfig,cached config loadsuccessfully");
        return new JSONObject(a2);
    }

    public static void a(Context context, String str, String str2) {
        e.a((Class<?>) f.class, 0, "entering saveConfigData");
        File file = new File(context.getFilesDir(), str2 + c);
        File file2 = new File(context.getFilesDir(), str2 + b);
        d.a(file, str);
        d.a(file2, String.valueOf(System.currentTimeMillis()));
    }

    static boolean a(JSONObject jSONObject, long j, g.c cVar) {
        return System.currentTimeMillis() > j + (jSONObject.optLong(cVar == g.c.RAMP ? g.i.CONF_REFRESH_TIME_KEY.toString() : cVar == g.c.REMOTE ? g.j.CONF_REFRESH_TIME_KEY.toString() : "", 0L) * 1000);
    }

    static boolean c(Context context, String str) {
        e.a((Class<?>) f.class, 0, "entering deleteCachedConfigDataFromDisk");
        File file = new File(context.getFilesDir(), str + c);
        File filesDir = context.getFilesDir();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(b);
        return d.a(file) && d.a(new File(filesDir, sb.toString()));
    }

    abstract JSONObject a();

    protected abstract void a(JSONObject jSONObject);

    protected String b(Context context, String str) {
        e.a(getClass(), 0, "Loading loadCachedConfigTime");
        return d.b(new File(context.getFilesDir(), str + b));
    }

    protected abstract JSONObject b();

    protected abstract JSONObject c();

    protected abstract String d();
}
