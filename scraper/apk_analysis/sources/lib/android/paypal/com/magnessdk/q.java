package lib.android.paypal.com.magnessdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.StatFs;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import lib.android.paypal.com.magnessdk.g;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public abstract class q {
    static final int A = 16;
    static final int A0 = 69;
    static final int B = 17;
    static final int B0 = 70;
    static final int C = 18;
    static final int C0 = 71;
    static final int D = 19;
    static final int D0 = 72;
    static final int E = 20;
    static final int E0 = 73;
    static final int F = 21;
    static final int F0 = 74;
    static final int G = 22;
    static final int G0 = 75;
    static final int H = 23;
    static final int H0 = 76;
    static final int I = 25;
    static final int I0 = 77;
    static final int J = 26;
    static final int J0 = 78;
    static final int K = 27;
    static final int K0 = 79;
    static final int L = 28;
    static final int L0 = 80;
    static final int M = 29;
    static final int M0 = 81;
    static final int N = 30;
    static final int N0 = 82;
    static final int O = 31;
    static final int O0 = 83;
    static final int P = 32;
    static final int P0 = 84;
    static final int Q = 33;
    static final int Q0 = 85;
    static final int R = 34;
    static final int R0 = 86;
    static final int S = 35;
    static final int S0 = 87;
    static final int T = 36;
    static final int T0 = 88;
    static final int U = 37;
    static final int U0 = 89;
    static final int V = 38;
    static final int V0 = 90;
    static final int W = 39;
    static final int W0 = 91;
    static final int X = 40;
    static final int X0 = 92;
    static final int Y = 41;
    static final int Y0 = 93;
    static final int Z = 42;
    static final int Z0 = 94;
    static final int a = -400;
    static final int a0 = 43;
    static final int a1 = 95;
    static final int b = -401;
    static final int b0 = 44;
    static final int b1 = 96;
    public static final int c = -402;
    static final int c0 = 45;
    static final int c1 = 97;
    public static final int d = -403;
    static final int d0 = 46;
    static final int d1 = 98;
    public static final int e = -405;
    static final int e0 = 47;
    static final int e1 = 99;
    static final int f = 12345;
    static final int f0 = 48;
    static final int f1 = 100;
    static final double g = 12345.0d;
    static final int g0 = 49;
    static final int g1 = 101;
    static final long h = 12345;
    static final int h0 = 50;
    static final int h1 = 102;
    static final float i = 12345.0f;
    static final int i0 = 51;
    static final int i1 = 103;
    static final String j = "default";
    static final int j0 = 52;
    static boolean j1 = false;
    static final int k = 0;
    static final int k0 = 53;
    static String k1 = null;
    static final int l = 1;
    static final int l0 = 54;
    static final int l1 = 600;
    static final int m = 2;
    static final int m0 = 55;
    static final int m1 = 601;
    static final int n = 3;
    static final int n0 = 56;
    static final int n1 = 1;
    static final int o = 4;
    static final int o0 = 57;
    static final int o1 = 2;
    static final int p = 5;
    static final int p0 = 58;
    static final int p1 = 3;
    static final int q = 6;
    static final int q0 = 59;
    public static final String q1 = "s";
    static final int r = 7;
    static final int r0 = 60;
    public static final String r1 = "hw";
    static final int s = 8;
    static final int s0 = 61;
    public static final String s1 = "ts";
    static final int t = 9;
    static final int t0 = 62;
    public static final String t1 = "td";
    static final int u = 10;
    static final int u0 = 63;
    static final int u1 = 100;
    static final int v = 11;
    static final int v0 = 64;
    static final int v1 = 10000;
    static final int w = 12;
    static final int w0 = 65;
    static final int w1 = 1000000;
    static final int x = 13;
    static final int x0 = 66;
    static final int y = 14;
    static final int y0 = 67;
    static final int z = 15;
    static final int z0 = 68;

    @Retention(RetentionPolicy.SOURCE)
    @interface a {
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface b {
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface c {
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface d {
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface e {
    }

    static JSONObject a(String str, JSONArray jSONArray, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(g.a.FEATURE.toString(), str2);
        jSONObject.put(g.a.PAYLOAD.toString(), jSONArray);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(g.b.a, str);
        jSONObject2.put(g.a.AUDIT_KEY.toString(), o.a(jSONObject));
        return jSONObject2;
    }

    private JSONObject a(HashMap<String, String> hashMap) {
        try {
            return new JSONObject("{\"id\":" + hashMap.get("id") + ",\"created_at\":" + hashMap.get("created_at") + "}");
        } catch (JSONException unused) {
            return null;
        }
    }

    static JSONObject a(JSONObject jSONObject, Object obj, String str) throws JSONException {
        JSONArray jSONArray = jSONObject.has(str) ? jSONObject.getJSONArray(str) : new JSONArray();
        jSONArray.put(obj);
        jSONObject.put(str, jSONArray);
        return jSONObject;
    }

    static JSONObject a(JSONObject jSONObject, JSONArray jSONArray, String str) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(g.a.FEATURE.toString(), str);
        jSONObject2.put(g.a.PAYLOAD.toString(), jSONArray);
        g.a aVar = g.a.AUDIT_KEY;
        if (!jSONObject.has(aVar.toString())) {
            return null;
        }
        JSONArray jSONArray2 = jSONObject.getJSONArray(aVar.toString());
        jSONArray2.put(jSONObject2);
        jSONObject.put(aVar.toString(), jSONArray2);
        return jSONObject;
    }

    private boolean a(int i2, String str, String str2) {
        if (str == null || str.equalsIgnoreCase("")) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        int abs = Math.abs(i2);
        int abs2 = Math.abs(lowerCase.hashCode());
        if (abs2 <= 0) {
            return false;
        }
        str2.hashCode();
        str2.hashCode();
        switch (str2) {
            case "s":
                break;
            case "hw":
                abs2 /= 100;
                break;
            case "td":
                abs2 /= 1000000;
                break;
            case "ts":
                abs2 /= 10000;
                break;
            default:
                return false;
        }
        int i3 = abs2 % 100;
        if (i3 < abs) {
            j1 = true;
        }
        return i3 < abs;
    }

    public static boolean a(String str) {
        return a(MagnesSDK.getInstance().a(), str);
    }

    public static boolean a(j jVar, String str) {
        JSONObject optJSONObject = jVar.a().optJSONObject(str);
        return optJSONObject != null && optJSONObject.optInt(g.i.RAMP_THRESHOLD.toString(), 0) < 0;
    }

    static JSONObject b(String str, JSONArray jSONArray, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(g.b.a, str);
        jSONObject.put(str2, jSONArray);
        return jSONObject;
    }

    long a(int i2) {
        String str;
        long blockSize;
        int blockCount;
        File file = new File("/storage");
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.exists()) {
                        try {
                            if (android.os.Environment.isExternalStorageRemovable(file2)) {
                                str = file2.getAbsolutePath();
                                break;
                            }
                            continue;
                        } catch (Exception e2) {
                            lib.android.paypal.com.magnessdk.e.a(this.getClass(), 3, e2);
                        }
                    }
                }
            }
            str = "";
            if (!str.isEmpty()) {
                File file3 = new File(str);
                if (file3.exists()) {
                    StatFs statFs = new StatFs(file3.getPath());
                    if (i2 == 600) {
                        blockSize = statFs.getBlockSize();
                        blockCount = statFs.getAvailableBlocks();
                    } else if (i2 == 601) {
                        blockSize = statFs.getBlockSize();
                        blockCount = statFs.getBlockCount();
                    }
                    return blockSize * blockCount;
                }
            }
        }
        return h;
    }

    Object a(Object obj) {
        boolean z2 = obj instanceof Integer;
        Integer valueOf = Integer.valueOf(a);
        return z2 ? ((Integer) obj).intValue() == f ? valueOf : obj : obj instanceof Double ? ((Double) obj).doubleValue() == g ? valueOf : obj : obj instanceof Long ? ((Long) obj).longValue() == h ? valueOf : obj : obj instanceof Float ? ((Float) obj).floatValue() == i ? valueOf : obj : obj instanceof String ? obj.equals(j) ? "-400" : obj : valueOf;
    }

    String a(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageName();
    }

    abstract JSONObject a();

    abstract JSONObject a(MagnesSettings magnesSettings, j jVar, k kVar);

    JSONObject a(JSONObject jSONObject) {
        JSONObject a2 = a();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                String next = keys.next();
                Object opt = a2.opt(next);
                if (opt == null || !(opt instanceof JSONObject)) {
                    opt = jSONObject.get(next);
                } else {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                    Iterator<String> keys2 = jSONObject2.keys();
                    while (keys2.hasNext()) {
                        String next2 = keys2.next();
                        ((JSONObject) opt).put(next2, jSONObject2.get(next2));
                    }
                }
                a2.put(next, opt);
            } catch (JSONException e2) {
                lib.android.paypal.com.magnessdk.e.a(getClass(), 3, e2);
            }
        }
        return a2;
    }

    abstract void a(int i2, MagnesSettings magnesSettings);

    boolean a(Context context, String str) {
        try {
            return context.checkCallingOrSelfPermission(str) == 0;
        } catch (Exception e2) {
            lib.android.paypal.com.magnessdk.e.a(this.getClass(), 3, e2);
            return false;
        }
    }

    boolean a(String str, String str2) {
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int i2 = 0;
        while (true) {
            if (i2 >= split.length && i2 >= split2.length) {
                return false;
            }
            if (i2 >= split.length || i2 >= split2.length) {
                if (i2 < split.length) {
                    if (Integer.parseInt(split[i2]) != 0) {
                        return true;
                    }
                } else if (i2 < split2.length && Integer.parseInt(split2[i2]) != 0) {
                    return false;
                }
            } else {
                if (Integer.parseInt(split[i2]) < Integer.parseInt(split2[i2])) {
                    return false;
                }
                if (Integer.parseInt(split[i2]) > Integer.parseInt(split2[i2])) {
                    return true;
                }
            }
            i2++;
        }
    }

    boolean a(j jVar, int i2, String str, String str2, Context context) {
        try {
            JSONObject optJSONObject = jVar.a().optJSONObject(str2);
            if (optJSONObject != null && !str.equalsIgnoreCase("")) {
                String string = optJSONObject.getString(g.i.MIN_VERSION.toString());
                String d2 = o.d(lib.android.paypal.com.magnessdk.a.o);
                if (!string.equalsIgnoreCase("") && a(d2, string)) {
                    if (optJSONObject.getBoolean(g.i.OPEN.toString())) {
                        return a(optJSONObject, i2, context);
                    }
                    if (i2 != MagnesSource.PAYPAL.getVersion() && i2 != MagnesSource.VENMO.getVersion()) {
                        return false;
                    }
                    return a(optJSONObject.optInt(g.i.RAMP_THRESHOLD.toString(), 0), str, str2);
                }
            }
            return false;
        } catch (Exception e2) {
            lib.android.paypal.com.magnessdk.e.a(getClass(), 3, e2);
            return false;
        }
    }

    boolean a(JSONObject jSONObject, int i2, Context context) {
        try {
            String a2 = a(context);
            String d2 = o.d(lib.android.paypal.com.magnessdk.a.o);
            List<String> b2 = o.b(jSONObject.getJSONArray(g.i.EXCLUDED.toString()));
            if (b2 == null) {
                return false;
            }
            if (!o.a(b2, d2)) {
                return true;
            }
            List<String> b3 = o.b(jSONObject.getJSONArray(g.i.APP_IDS.toString()));
            List<Integer> a3 = o.a(jSONObject.getJSONArray(g.i.APP_SOURCES.toString()));
            if (a3 != null && a3.contains(Integer.valueOf(i2))) {
                return false;
            }
            if (b3 != null) {
                return !o.a(b3, a2);
            }
            return true;
        } catch (Exception e2) {
            lib.android.paypal.com.magnessdk.e.a(this.getClass(), 3, e2);
            return false;
        }
    }

    JSONObject b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(lib.android.paypal.com.magnessdk.c.d, 0);
        k1 = sharedPreferences.getString(lib.android.paypal.com.magnessdk.c.d, "");
        long j2 = sharedPreferences.getLong(lib.android.paypal.com.magnessdk.c.e, 0L);
        if (k1.equals("") && j2 == 0) {
            k1 = o.a(true);
            j2 = System.currentTimeMillis();
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(lib.android.paypal.com.magnessdk.c.d, k1);
            edit.putLong(lib.android.paypal.com.magnessdk.c.e, j2);
            edit.apply();
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", k1);
        hashMap.put("created_at", j2 + "");
        return a(hashMap);
    }
}
