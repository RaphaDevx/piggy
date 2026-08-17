package lib.android.paypal.com.magnessdk;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.os.Build;
import android.util.Base64;
import com.tealium.library.DataSources;
import io.sentry.ProfilingTraceData;
import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import lib.android.paypal.com.magnessdk.g;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public final class o {

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[Environment.values().length];
            a = iArr;
            try {
                iArr[Environment.SANDBOX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private o() {
    }

    static <T> T a(Object obj, Class<T> cls) {
        if (obj == null || !cls.isAssignableFrom(obj.getClass())) {
            return null;
        }
        return cls.cast(obj);
    }

    public static String a(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
    }

    static String a(String str) throws UnsupportedEncodingException {
        return new String(Base64.decode(str, 2), "UTF-8");
    }

    public static String a(Environment environment) {
        return (a.a[environment.ordinal()] != 1 ? g.h.d.AUDIT_JSON_URL : g.h.d.SANDBOX_AUDIT_JSON_URL).toString();
    }

    static String a(boolean z) {
        return z ? UUID.randomUUID().toString() : UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String a(Boolean[] boolArr) {
        String str;
        if (boolArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Boolean bool : boolArr) {
            if (bool.booleanValue()) {
                str = "1";
            } else if (!bool.booleanValue()) {
                str = "0";
            }
            sb.append(str);
        }
        return sb.toString();
    }

    static List<Integer> a(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add((Integer) jSONArray.get(i));
            }
        }
        return arrayList;
    }

    private static Map<String, String> a(Map<String, String> map, Context context) throws Exception {
        String str = Build.VERSION.RELEASE;
        String str2 = Build.MODEL;
        String packageName = context.getPackageName();
        String a2 = a(context);
        if (!b((Object) lib.android.paypal.com.magnessdk.a.o)) {
            map.put("comp_version", lib.android.paypal.com.magnessdk.a.o);
        }
        if (!b((Object) str)) {
            map.put(DataSources.Key.OS_VERSION, str);
        }
        if (!b((Object) "Android")) {
            map.put("os_type", "Android");
        }
        if (!b((Object) str2)) {
            map.put(ProfilingTraceData.JsonKeys.DEVICE_MODEL, str2);
        }
        if (!b((Object) packageName)) {
            map.put("app_id", packageName);
        }
        if (!b((Object) a2)) {
            map.put("app_version", a2);
        }
        return map;
    }

    static JSONArray a(Object obj) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(obj);
        return jSONArray;
    }

    public static JSONObject a(Sensor sensor) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(g.d.e.NAME.toString(), sensor.getName());
        jSONObject.put(g.d.e.VENDOR.toString(), sensor.getVendor());
        jSONObject.put(g.d.e.POWER.toString(), String.format("%.8f", Float.valueOf(sensor.getPower())));
        jSONObject.put(g.d.e.VERSION.toString(), String.valueOf(sensor.getVersion()));
        jSONObject.put(g.d.e.RESOLUTION.toString(), String.format("%.8f", Float.valueOf(sensor.getResolution())));
        jSONObject.put(g.d.e.MAX_RANGE.toString(), String.format("%.8f", Float.valueOf(sensor.getMaximumRange())));
        jSONObject.put(g.d.e.FIFO_MAX_EVENT_COUNT.toString(), String.valueOf(sensor.getFifoMaxEventCount()));
        return jSONObject;
    }

    static JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) {
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (!jSONObject.has(next)) {
                try {
                    jSONObject.put(next, jSONObject2.opt(next));
                } catch (JSONException e) {
                    e.a((Class<?>) o.class, 3, e);
                }
            }
        }
        return jSONObject;
    }

    public static void a(Class<?> cls, Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.a(cls.getClass(), 3, e);
            }
        }
    }

    static boolean a(List<String> list, String str) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    static List<String> b(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(String.valueOf(jSONArray.get(i)));
            }
        }
        return arrayList;
    }

    public static Map<String, String> b(Context context) throws Exception {
        if (context == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        a(hashMap, context);
        return hashMap;
    }

    static boolean b(Object obj) {
        if (obj == null) {
            return true;
        }
        return obj instanceof String ? ((String) obj).isEmpty() : obj instanceof Long ? ((Long) obj).longValue() == 0 : !(obj instanceof Integer) || ((Integer) obj).intValue() == 0;
    }

    static boolean b(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("^[a-zA-Z0-9-]*$");
    }

    public static Map<String, String> c(Context context) throws Exception {
        if (context == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("X-PAYPAL-RESPONSE-DATA-FORMAT", "NV");
        hashMap.put("X-PAYPAL-REQUEST-DATA-FORMAT", "NV");
        hashMap.put("X-PAYPAL-SERVICE-VERSION", "1.0.0");
        hashMap.put(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        a(hashMap, context);
        return hashMap;
    }

    static boolean c(String str) {
        return str != null && !str.isEmpty() && b(str) && str.length() <= 36 && str.length() >= 30;
    }

    static String d(String str) {
        return str.replaceAll(".debug", "").replaceAll(".release", "");
    }
}
