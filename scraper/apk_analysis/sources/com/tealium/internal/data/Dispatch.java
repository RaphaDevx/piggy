package com.tealium.internal.data;

import android.text.TextUtils;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/* loaded from: classes2.dex */
public final class Dispatch {
    private final long a;
    private Map<String, String> b;
    private Map<String, String[]> c;
    private Map<String, JSONObject> d;
    private String e;
    private String f;

    public Dispatch() {
        this.a = System.currentTimeMillis();
    }

    private void a() {
        this.e = null;
        this.f = null;
    }

    private Map<String, String[]> b() {
        if (this.c == null) {
            this.c = new HashMap();
        }
        return this.c;
    }

    private Map<String, JSONObject> c() {
        if (this.d == null) {
            this.d = new HashMap();
        }
        return this.d;
    }

    private Map<String, String> d() {
        if (this.b == null) {
            this.b = new HashMap();
        }
        return this.b;
    }

    public boolean containsKey(String str) {
        Map<String, String[]> map;
        Map<String, JSONObject> map2;
        Map<String, String> map3 = this.b;
        return (map3 != null && map3.containsKey(str)) || ((map = this.c) != null && map.containsKey(str)) || ((map2 = this.d) != null && map2.containsKey(str));
    }

    public Object get(String str) {
        JSONObject jSONObject;
        String str2;
        Map<String, String> map = this.b;
        if (map != null && (str2 = map.get(str)) != null) {
            return str2;
        }
        Map<String, JSONObject> map2 = this.d;
        if (map2 != null && (jSONObject = map2.get(str)) != null) {
            return jSONObject;
        }
        Map<String, String[]> map3 = this.c;
        if (map3 == null) {
            return null;
        }
        return map3.get(str);
    }

    public String[] getArray(String str) {
        Map<String, String[]> map = this.c;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public long getCreationTime() {
        return this.a;
    }

    public JSONObject getJsonObject(String str) {
        Map<String, JSONObject> map = this.d;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public String getString(String str) {
        Map<String, String> map = this.b;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public String[] keys() {
        Map<String, String> map = this.b;
        Set<String> keySet = map == null ? null : map.keySet();
        Map<String, String[]> map2 = this.c;
        Set<String> keySet2 = map2 == null ? null : map2.keySet();
        Map<String, JSONObject> map3 = this.d;
        Set<String> keySet3 = map3 != null ? map3.keySet() : null;
        int i = 0;
        String[] strArr = new String[(keySet == null ? 0 : keySet.size()) + (keySet2 == null ? 0 : keySet2.size()) + (keySet3 == null ? 0 : keySet3.size())];
        if (keySet != null) {
            Iterator<String> it = keySet.iterator();
            while (it.hasNext()) {
                strArr[i] = it.next();
                i++;
            }
        }
        if (keySet2 != null) {
            Iterator<String> it2 = keySet2.iterator();
            while (it2.hasNext()) {
                strArr[i] = it2.next();
                i++;
            }
        }
        if (keySet3 != null) {
            Iterator<String> it3 = keySet3.iterator();
            while (it3.hasNext()) {
                strArr[i] = it3.next();
                i++;
            }
        }
        return strArr;
    }

    public void put(String str, String str2) {
        Map<String, String> map;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        }
        if (str2 == null && (map = this.b) != null) {
            map.remove(str);
            a();
        } else if (str2 != null) {
            d().put(str, str2);
            a();
        }
    }

    public void putAll(Map<String, ?> map) {
        if (map == null) {
            return;
        }
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            if (!TextUtils.isEmpty(entry.getKey()) && entry.getValue() != null) {
                String[] b = b(entry.getValue());
                if (b != null) {
                    b().put(entry.getKey(), b);
                } else {
                    JSONObject c = c(entry.getValue());
                    if (c != null) {
                        c().put(entry.getKey(), c);
                    } else {
                        d().put(entry.getKey(), entry.getValue().toString());
                    }
                }
            }
        }
        a();
    }

    public void putAllIfAbsent(Map<String, ?> map) {
        if (map == null) {
            return;
        }
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            if (!TextUtils.isEmpty(entry.getKey()) && entry.getValue() != null) {
                String[] b = b(entry.getValue());
                if (b != null) {
                    putIfAbsent(entry.getKey(), b);
                } else {
                    JSONObject c = c(entry.getValue());
                    if (c != null) {
                        c().put(entry.getKey(), c);
                    } else {
                        putIfAbsent(entry.getKey(), entry.getValue().toString());
                    }
                }
            }
        }
        a();
    }

    public void putIfAbsent(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        }
        if (str2 == null) {
            return;
        }
        Map<String, String> map = this.b;
        if (map == null) {
            d().put(str, str2);
            a();
        } else {
            if (map.containsKey(str)) {
                return;
            }
            this.b.put(str, str2);
            a();
        }
    }

    public void remove(String str) {
        removeString(str);
        removeJsonObject(str);
        removeArray(str);
    }

    public void removeArray(String str) {
        Map<String, String[]> map = this.c;
        if (map == null || !map.containsKey(str)) {
            return;
        }
        this.c.remove(str);
    }

    public void removeJsonObject(String str) {
        Map<String, JSONObject> map = this.d;
        if (map == null || !map.containsKey(str)) {
            return;
        }
        this.d.remove(str);
    }

    public void removeString(String str) {
        Map<String, String> map = this.b;
        if (map == null || !map.containsKey(str)) {
            return;
        }
        this.b.remove(str);
    }

    public int size() {
        Map<String, String> map = this.b;
        int size = map == null ? 0 : map.size();
        Map<String, String[]> map2 = this.c;
        int size2 = size + (map2 == null ? 0 : map2.size());
        Map<String, JSONObject> map3 = this.d;
        return size2 + (map3 != null ? map3.size() : 0);
    }

    public JSONObject toJsonObject() {
        JSONObject jSONObject = this.b == null ? new JSONObject() : new JSONObject(this.b);
        Map<String, String[]> map = this.c;
        if (map != null) {
            try {
                for (Map.Entry<String, String[]> entry : map.entrySet()) {
                    jSONObject.put(entry.getKey(), a(entry.getValue()));
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        Map<String, JSONObject> map2 = this.d;
        if (map2 != null) {
            try {
                for (Map.Entry<String, JSONObject> entry2 : map2.entrySet()) {
                    jSONObject.put(entry2.getKey(), entry2.getValue());
                }
            } catch (JSONException e2) {
                throw new RuntimeException(e2);
            }
        }
        return jSONObject;
    }

    public String toJsonString() {
        String str = this.e;
        if (str != null) {
            return str;
        }
        try {
            JSONStringer jSONStringer = new JSONStringer();
            jSONStringer.object();
            Map<String, String> map = this.b;
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    a(jSONStringer, entry.getKey(), entry.getValue());
                }
            }
            Map<String, String[]> map2 = this.c;
            if (map2 != null) {
                for (Map.Entry<String, String[]> entry2 : map2.entrySet()) {
                    a(jSONStringer, entry2.getKey(), entry2.getValue());
                }
            }
            Map<String, JSONObject> map3 = this.d;
            if (map3 != null) {
                for (Map.Entry<String, JSONObject> entry3 : map3.entrySet()) {
                    a(jSONStringer, entry3.getKey(), entry3.getValue());
                }
            }
            jSONStringer.endObject();
            String jSONStringer2 = jSONStringer.toString();
            this.e = jSONStringer2;
            return jSONStringer2;
        } catch (JSONException unused) {
            return null;
        }
    }

    public String toSortedJsonString() {
        String str = this.f;
        if (str != null) {
            return str;
        }
        TreeMap treeMap = this.b == null ? new TreeMap() : new TreeMap(this.b);
        Map<String, String[]> map = this.c;
        if (map != null) {
            treeMap.putAll(map);
        }
        Map<String, JSONObject> map2 = this.d;
        if (map2 != null) {
            treeMap.putAll(map2);
        }
        String a = a((Map<String, ?>) treeMap);
        this.f = a;
        this.e = a;
        return a;
    }

    public String toString() {
        return toSortedJsonString();
    }

    public Dispatch(long j) {
        this.a = j;
    }

    private String a(Map<String, ?> map) {
        try {
            JSONStringer jSONStringer = new JSONStringer();
            jSONStringer.object();
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                if (entry.getValue() instanceof String) {
                    a(jSONStringer, entry.getKey(), (String) entry.getValue());
                } else if (entry.getValue() instanceof String[]) {
                    a(jSONStringer, entry.getKey(), (String[]) entry.getValue());
                } else if (entry.getValue() instanceof JSONObject) {
                    a(jSONStringer, entry.getKey(), (JSONObject) entry.getValue());
                }
            }
            jSONStringer.endObject();
            return jSONStringer.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    public Dispatch(long j, JSONObject jSONObject) {
        this.a = j;
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object opt = jSONObject.opt(next);
                if (!TextUtils.isEmpty(next) && opt != null && !JSONObject.NULL.equals(opt)) {
                    String[] b = b(opt);
                    if (b != null) {
                        b().put(next, b);
                    } else {
                        JSONObject c = c(opt);
                        if (c != null) {
                            c().put(next, c);
                        } else {
                            d().put(next, opt.toString());
                        }
                    }
                }
            }
        }
    }

    private String[] b(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj.getClass().isArray()) {
            return a(obj);
        }
        if (obj instanceof Collection) {
            return a(((Collection) obj).toArray());
        }
        if (obj instanceof JSONArray) {
            return a((JSONArray) obj);
        }
        return null;
    }

    private JSONObject c(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        if (obj instanceof Map) {
            return b((Map<String, ?>) obj);
        }
        return null;
    }

    private JSONObject b(Map<String, ?> map) {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key != null) {
                try {
                    jSONObject.put(key, entry.getValue());
                } catch (JSONException unused) {
                    jSONObject = null;
                }
            } else {
                throw new NullPointerException("key == null");
            }
        }
        return jSONObject;
    }

    public void put(String str, String[] strArr) {
        Map<String, String[]> map;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        }
        if (strArr == null && (map = this.c) != null) {
            map.remove(str);
            a();
        } else if (strArr != null) {
            b().put(str, a((Object) strArr));
            a();
        }
    }

    private void a(JSONStringer jSONStringer, String str, Object obj) throws JSONException {
        jSONStringer.key(str);
        jSONStringer.value(obj);
    }

    private void a(JSONStringer jSONStringer, String str, String[] strArr) throws JSONException {
        jSONStringer.key(str);
        jSONStringer.array();
        for (String str2 : strArr) {
            jSONStringer.value(str2);
        }
        jSONStringer.endArray();
    }

    public void putIfAbsent(String str, String[] strArr) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        }
        if (strArr == null) {
            return;
        }
        Map<String, String[]> map = this.c;
        if (map == null) {
            b().put(str, a((Object) strArr));
            a();
        } else {
            if (map.containsKey(str)) {
                return;
            }
            this.c.put(str, a((Object) strArr));
            a();
        }
    }

    private JSONArray a(String[] strArr) throws JSONException {
        return new JSONArray(strArr);
    }

    public Dispatch(Map<String, ?> map) {
        this.a = System.currentTimeMillis();
        if (map != null) {
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                if (!TextUtils.isEmpty(entry.getKey()) && entry.getValue() != null) {
                    String[] b = b(entry.getValue());
                    if (b != null) {
                        b().put(entry.getKey(), b);
                    } else {
                        JSONObject c = c(entry.getValue());
                        if (c != null) {
                            c().put(entry.getKey(), c);
                        } else {
                            d().put(entry.getKey(), entry.getValue().toString());
                        }
                    }
                }
            }
        }
    }

    private String[] a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        int i = 0;
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            if (jSONArray.opt(i2) != null) {
                i++;
            }
        }
        String[] strArr = new String[i];
        int i3 = 0;
        for (int i4 = 0; i4 < jSONArray.length(); i4++) {
            Object opt = jSONArray.opt(i4);
            if (opt != null) {
                strArr[i3] = opt.toString();
                i3++;
            }
        }
        return strArr;
    }

    public void put(String str, JSONObject jSONObject) {
        Map<String, JSONObject> map;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        }
        if (jSONObject == null && (map = this.d) != null) {
            map.remove(str);
            a();
        } else if (jSONObject != null) {
            c().put(str, jSONObject);
            a();
        }
    }

    public void putIfAbsent(String str, JSONObject jSONObject) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        }
        if (jSONObject == null) {
            return;
        }
        Map<String, JSONObject> map = this.d;
        if (map == null) {
            c().put(str, jSONObject);
            a();
        } else {
            if (map.containsKey(str)) {
                return;
            }
            this.d.put(str, jSONObject);
            a();
        }
    }

    private String[] a(Object obj) {
        if (obj == null) {
            return null;
        }
        int i = 0;
        for (int i2 = 0; i2 < Array.getLength(obj); i2++) {
            if (Array.get(obj, i2) != null) {
                i++;
            }
        }
        String[] strArr = new String[i];
        int i3 = 0;
        for (int i4 = 0; i4 < Array.getLength(obj); i4++) {
            Object obj2 = Array.get(obj, i4);
            if (obj2 != null) {
                strArr[i3] = obj2.toString();
                i3++;
            }
        }
        return strArr;
    }
}
