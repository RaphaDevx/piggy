package com.fasterxml.jackson.databind.util.internal;

/* loaded from: classes3.dex */
interface EvictionListener<K, V> {
    void onEviction(K k, V v);
}
