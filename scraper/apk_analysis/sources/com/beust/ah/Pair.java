package com.beust.ah;

import java.util.AbstractMap;

/* compiled from: A.java */
/* loaded from: classes3.dex */
class Pair<T, V> {
    AbstractMap.SimpleEntry<T, V> entry;

    public Pair(T t, V v) {
        this.entry = new AbstractMap.SimpleEntry<>(t, v);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Pair) {
            return this.entry.equals(((Pair) obj).entry);
        }
        return false;
    }

    public int hashCode() {
        return this.entry.hashCode();
    }
}
