package com.scandit.datacapture.tools.internal.module.mapmaker;

import java.util.Map;

/* loaded from: classes2.dex */
public final class M extends AbstractC0613a {
    public final Object a;
    public Object b;
    public final /* synthetic */ N c;

    public M(N n, Object obj, Object obj2) {
        this.c = n;
        this.a = obj;
        this.b = obj2;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return this.a.equals(entry.getKey()) && this.b.equals(entry.getValue());
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.a;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.b;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return this.b.hashCode() ^ this.a.hashCode();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object put = this.c.put(this.a, obj);
        this.b = obj;
        return put;
    }
}
