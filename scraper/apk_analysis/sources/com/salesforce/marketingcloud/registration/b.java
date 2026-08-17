package com.salesforce.marketingcloud.registration;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* loaded from: classes2.dex */
class b implements Map<String, String> {
    private final HashMap<String, String> b;
    private final TreeMap<String, String> c;

    b() {
        this.b = new HashMap<>();
        this.c = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    @Override // java.util.Map
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String get(Object obj) {
        String str = this.c.get(obj);
        if (str != null) {
            return this.b.get(str);
        }
        return null;
    }

    @Override // java.util.Map
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public String remove(Object obj) {
        return this.b.remove(this.c.remove(obj));
    }

    @Override // java.util.Map
    public void clear() {
        this.b.clear();
        this.c.clear();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.c.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.b.containsValue(obj);
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, String>> entrySet() {
        return this.b.entrySet();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.b.isEmpty();
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return this.b.keySet();
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        for (Map.Entry<? extends String, ? extends String> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public int size() {
        return this.b.size();
    }

    @Override // java.util.Map
    public Collection<String> values() {
        return this.b.values();
    }

    b(Map<String, String> map) {
        this();
        if (map != null) {
            this.b.putAll(map);
            for (String str : this.b.keySet()) {
                this.c.put(str, str);
            }
        }
    }

    @Override // java.util.Map
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public String put(String str, String str2) {
        if (str == null) {
            return null;
        }
        String str3 = this.c.get(str);
        String remove = str3 != null ? this.b.remove(str3) : null;
        this.c.put(str, str);
        this.b.put(str, str2);
        return remove;
    }
}
