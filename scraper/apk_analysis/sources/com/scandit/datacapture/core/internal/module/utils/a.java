package com.scandit.datacapture.core.internal.module.utils;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a extends ConcurrentHashMap {
    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof a) && hashCode() == ((a) obj).hashCode());
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        Set<Map.Entry> entrySet = super.entrySet();
        Intrinsics.checkNotNullExpressionValue(entrySet, "<get-entries>(...)");
        int i = 0;
        for (Map.Entry entry : entrySet) {
            Intrinsics.checkNotNull(entry);
            Object key = entry.getKey();
            Object value = entry.getValue();
            i = (((i * 31) + (key != null ? key.hashCode() : 0)) * 31) + (value instanceof Object[] ? ArraysKt.contentDeepHashCode((Object[]) value) : value instanceof int[] ? Arrays.hashCode((int[]) value) : value != null ? value.hashCode() : 0);
        }
        return i;
    }
}
