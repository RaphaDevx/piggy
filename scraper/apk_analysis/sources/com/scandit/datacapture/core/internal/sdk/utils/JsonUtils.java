package com.scandit.datacapture.core.internal.sdk.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\u001a\u0017\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\u0006\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"", "json", "", "objectFromJson", "(Ljava/lang/String;)Ljava/lang/Object;", "obj", "jsonFromObject", "(Ljava/lang/Object;)Ljava/lang/String;", "scandit-capture-core"}, k = 2, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class JsonUtils {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v17, types: [org.json.JSONArray] */
    /* JADX WARN: Type inference failed for: r0v18, types: [org.json.JSONArray] */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v20, types: [org.json.JSONObject] */
    private static final Object a(Object obj) {
        Object jSONArray;
        if (obj == null) {
            Object NULL = JSONObject.NULL;
            Intrinsics.checkNotNullExpressionValue(NULL, "NULL");
            return NULL;
        }
        if (obj instanceof Map) {
            jSONArray = new JSONObject();
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (key == null) {
                    throw new JSONException("Maps with null keys cannot be serialized to json");
                }
                if (!(key instanceof String)) {
                    throw new JSONException("Maps with keys of type " + key.getClass().getSimpleName() + " cannot be serialized to json");
                }
                jSONArray.put((String) key, a(value));
            }
        } else if (obj instanceof Collection) {
            jSONArray = new JSONArray();
            Iterator it = ((Iterable) obj).iterator();
            while (it.hasNext()) {
                jSONArray.put(a(it.next()));
            }
        } else {
            if (!(obj instanceof Object[])) {
                if (obj instanceof String ? true : obj instanceof Boolean ? true : obj instanceof Integer ? true : obj instanceof Long) {
                    return obj;
                }
                if (obj instanceof Double) {
                    double doubleValue = ((Number) obj).doubleValue();
                    if (Double.isInfinite(doubleValue) || Double.isNaN(doubleValue)) {
                        throw new JSONException("JSON prohibits infinities or NaNs");
                    }
                    return Double.valueOf(doubleValue);
                }
                if (!(obj instanceof Float)) {
                    throw new JSONException(obj.getClass().getSimpleName().concat(" cannot be serialized to json"));
                }
                float floatValue = ((Number) obj).floatValue();
                if (Float.isInfinite(floatValue) || Float.isNaN(floatValue)) {
                    throw new JSONException("JSON prohibits infinities or NaNs");
                }
                return Double.valueOf(floatValue);
            }
            jSONArray = new JSONArray();
            for (Object obj2 : (Object[]) obj) {
                jSONArray.put(a(obj2));
            }
        }
        return jSONArray;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17, types: [java.util.LinkedHashMap, java.util.Map] */
    private static final Object b(Object obj) {
        Object obj2;
        if (Intrinsics.areEqual(obj, JSONObject.NULL)) {
            return null;
        }
        if (obj instanceof JSONObject) {
            obj2 = new LinkedHashMap();
            JSONObject jSONObject = (JSONObject) obj;
            Iterator<String> keys = jSONObject.keys();
            Intrinsics.checkNotNullExpressionValue(keys, "keys(...)");
            while (keys.hasNext()) {
                String next = keys.next();
                Intrinsics.checkNotNull(next);
                Object obj3 = jSONObject.get(next);
                Intrinsics.checkNotNullExpressionValue(obj3, "get(...)");
                obj2.put(next, b(obj3));
            }
        } else {
            if (!(obj instanceof JSONArray)) {
                if (obj instanceof String ? true : obj instanceof Boolean ? true : obj instanceof Integer ? true : obj instanceof Double) {
                    return obj;
                }
                if (obj instanceof Float) {
                    return Double.valueOf(((Number) obj).floatValue());
                }
                throw new JSONException(obj.getClass().getSimpleName().concat(" is not a json type"));
            }
            JSONArray jSONArray = (JSONArray) obj;
            obj2 = new Object[jSONArray.length()];
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                Object obj4 = jSONArray.get(i);
                Intrinsics.checkNotNullExpressionValue(obj4, "get(...)");
                obj2[i] = b(obj4);
            }
        }
        return obj2;
    }

    public static final String jsonFromObject(Object obj) throws JSONException {
        if (obj == null) {
            return JSONObject.NULL.toString();
        }
        if (obj instanceof Map) {
            Object a = a(obj);
            Intrinsics.checkNotNull(a, "null cannot be cast to non-null type org.json.JSONObject");
            String jSONObject = ((JSONObject) a).toString(0);
            Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
            return jSONObject;
        }
        if (obj instanceof Collection) {
            Object a2 = a(obj);
            Intrinsics.checkNotNull(a2, "null cannot be cast to non-null type org.json.JSONArray");
            String jSONArray = ((JSONArray) a2).toString(0);
            Intrinsics.checkNotNullExpressionValue(jSONArray, "toString(...)");
            return jSONArray;
        }
        if (obj instanceof Object[]) {
            Object a3 = a(obj);
            Intrinsics.checkNotNull(a3, "null cannot be cast to non-null type org.json.JSONArray");
            String jSONArray2 = ((JSONArray) a3).toString(0);
            Intrinsics.checkNotNullExpressionValue(jSONArray2, "toString(...)");
            return jSONArray2;
        }
        if (obj instanceof String ? true : obj instanceof Boolean ? true : obj instanceof Integer) {
            return obj.toString();
        }
        if (obj instanceof Double) {
            Number number = (Number) obj;
            if (Double.isInfinite(number.doubleValue()) || Double.isNaN(number.doubleValue())) {
                throw new JSONException("JSON prohibits infinities or NaNs");
            }
            return String.valueOf(number.doubleValue());
        }
        if (!(obj instanceof Float)) {
            throw new JSONException(obj.getClass().getSimpleName().concat(" cannot be serialized to json"));
        }
        Number number2 = (Number) obj;
        if (Float.isInfinite(number2.floatValue()) || Float.isNaN(number2.floatValue())) {
            throw new JSONException("JSON prohibits infinities or NaNs");
        }
        return String.valueOf(number2.floatValue());
    }

    public static final Object objectFromJson(String json) throws JSONException {
        Intrinsics.checkNotNullParameter(json, "json");
        try {
            Object b = b(new JSONArray("[" + json + ']'));
            Intrinsics.checkNotNull(b, "null cannot be cast to non-null type kotlin.Array<*>");
            return ((Object[]) b)[0];
        } catch (Exception unused) {
            throw new JSONException("JSON Parsing failed due to unknown reason");
        }
    }
}
