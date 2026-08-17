package com.byteowls.capacitor.filesharer;

import com.getcapacitor.JSObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public abstract class ConfigUtils {
    public static String getParamString(JSObject jSObject, String str) {
        return (String) getParam(String.class, jSObject, str);
    }

    public static <T> T getParam(Class<T> cls, JSObject jSObject, String str) {
        return (T) getParam(cls, jSObject, str, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x008f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0090 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T> T getParam(java.lang.Class<T> r1, com.getcapacitor.JSObject r2, java.lang.String r3, T r4) {
        /*
            java.lang.String r0 = getDeepestKey(r3)
            if (r0 == 0) goto L91
            com.getcapacitor.JSObject r2 = getDeepestObject(r2, r3)     // Catch: java.lang.Exception -> L91
            boolean r3 = r2.has(r0)     // Catch: java.lang.Exception -> L91
            if (r3 == 0) goto L8c
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            boolean r3 = r1.isAssignableFrom(r3)     // Catch: java.lang.Exception -> L91
            if (r3 == 0) goto L1e
            java.lang.String r1 = r2.getString(r0)     // Catch: java.lang.Exception -> L91
            goto L8d
        L1e:
            java.lang.Class<java.lang.Boolean> r3 = java.lang.Boolean.class
            boolean r3 = r1.isAssignableFrom(r3)     // Catch: java.lang.Exception -> L91
            if (r3 == 0) goto L2f
            boolean r1 = r2.optBoolean(r0)     // Catch: java.lang.Exception -> L91
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch: java.lang.Exception -> L91
            goto L8d
        L2f:
            java.lang.Class<java.lang.Double> r3 = java.lang.Double.class
            boolean r3 = r1.isAssignableFrom(r3)     // Catch: java.lang.Exception -> L91
            if (r3 == 0) goto L40
            double r1 = r2.getDouble(r0)     // Catch: java.lang.Exception -> L91
            java.lang.Double r1 = java.lang.Double.valueOf(r1)     // Catch: java.lang.Exception -> L91
            goto L8d
        L40:
            java.lang.Class<java.lang.Integer> r3 = java.lang.Integer.class
            boolean r3 = r1.isAssignableFrom(r3)     // Catch: java.lang.Exception -> L91
            if (r3 == 0) goto L51
            int r1 = r2.getInt(r0)     // Catch: java.lang.Exception -> L91
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Exception -> L91
            goto L8d
        L51:
            java.lang.Class<java.lang.Long> r3 = java.lang.Long.class
            boolean r3 = r1.isAssignableFrom(r3)     // Catch: java.lang.Exception -> L91
            if (r3 == 0) goto L62
            long r1 = r2.getLong(r0)     // Catch: java.lang.Exception -> L91
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch: java.lang.Exception -> L91
            goto L8d
        L62:
            java.lang.Class<java.lang.Float> r3 = java.lang.Float.class
            boolean r3 = r1.isAssignableFrom(r3)     // Catch: java.lang.Exception -> L91
            if (r3 == 0) goto L7b
            double r1 = r2.getDouble(r0)     // Catch: java.lang.Exception -> L91
            java.lang.Double r1 = java.lang.Double.valueOf(r1)     // Catch: java.lang.Exception -> L91
            float r1 = r1.floatValue()     // Catch: java.lang.Exception -> L91
            java.lang.Float r1 = java.lang.Float.valueOf(r1)     // Catch: java.lang.Exception -> L91
            goto L8d
        L7b:
            java.lang.Class<java.lang.Integer> r3 = java.lang.Integer.class
            boolean r1 = r1.isAssignableFrom(r3)     // Catch: java.lang.Exception -> L91
            if (r1 == 0) goto L8c
            int r1 = r2.getInt(r0)     // Catch: java.lang.Exception -> L91
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Exception -> L91
            goto L8d
        L8c:
            r1 = 0
        L8d:
            if (r1 != 0) goto L90
            return r4
        L90:
            return r1
        L91:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byteowls.capacitor.filesharer.ConfigUtils.getParam(java.lang.Class, com.getcapacitor.JSObject, java.lang.String, java.lang.Object):java.lang.Object");
    }

    public static Map<String, String> getParamMap(JSObject jSObject, String str) {
        HashMap hashMap = new HashMap();
        String deepestKey = getDeepestKey(str);
        if (deepestKey != null) {
            try {
                JSONObject jSONObject = getDeepestObject(jSObject, str).getJSONObject(deepestKey);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (next != null && next.trim().length() > 0) {
                        try {
                            hashMap.put(next, jSONObject.getString(next));
                        } catch (JSONException unused) {
                        }
                    }
                }
            } catch (Exception unused2) {
            }
        }
        return hashMap;
    }

    public static String getDeepestKey(String str) {
        String[] split = str.split("\\.");
        if (split.length > 0) {
            return split[split.length - 1];
        }
        return null;
    }

    public static JSObject getDeepestObject(JSObject jSObject, String str) {
        String[] split = str.split("\\.");
        for (int i = 0; i < split.length - 1; i++) {
            jSObject = jSObject.getJSObject(split[i]);
        }
        return jSObject;
    }

    public static <T> T getOverwrittenAndroidParam(Class<T> cls, JSObject jSObject, String str) {
        T t = (T) getParam(cls, jSObject, str);
        T t2 = (T) getParam(cls, jSObject, "android." + str);
        return t2 != null ? t2 : t;
    }

    public static Map<String, String> getOverwrittenAndroidParamMap(JSObject jSObject, String str) {
        Map<String, String> paramMap = getParamMap(jSObject, str);
        Map<String, String> paramMap2 = getParamMap(jSObject, "android." + str);
        HashMap hashMap = new HashMap(paramMap);
        hashMap.putAll(paramMap2);
        return hashMap;
    }

    public static String getRandomString(int i) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] cArr2 = new char[i];
        Random random = new Random();
        for (int i2 = 0; i2 < i; i2++) {
            cArr2[i2] = cArr[random.nextInt(62)];
        }
        return new String(cArr2);
    }

    public static String trimToNull(String str) {
        if (str == null || str.trim().length() != 0) {
            return str;
        }
        return null;
    }
}
