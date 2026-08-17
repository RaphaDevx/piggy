package com.google.android.gms.common.data;

import io.sentry.android.replay.RootViewsSpy$delegatingViewList$1;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
/* loaded from: classes3.dex */
public final class FreezableUtils {
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(ArrayList<E> arrayList) {
        RootViewsSpy$delegatingViewList$1 rootViewsSpy$delegatingViewList$1 = (ArrayList<T>) new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            rootViewsSpy$delegatingViewList$1.add((RootViewsSpy$delegatingViewList$1) arrayList.get(i).freeze());
        }
        return rootViewsSpy$delegatingViewList$1;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(Iterable<E> iterable) {
        RootViewsSpy$delegatingViewList$1 rootViewsSpy$delegatingViewList$1 = (ArrayList<T>) new ArrayList();
        Iterator<E> it = iterable.iterator();
        while (it.hasNext()) {
            rootViewsSpy$delegatingViewList$1.add((RootViewsSpy$delegatingViewList$1) it.next().freeze());
        }
        return rootViewsSpy$delegatingViewList$1;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freeze(E[] eArr) {
        RootViewsSpy$delegatingViewList$1 rootViewsSpy$delegatingViewList$1 = (ArrayList<T>) new ArrayList(eArr.length);
        for (E e : eArr) {
            rootViewsSpy$delegatingViewList$1.add((RootViewsSpy$delegatingViewList$1) e.freeze());
        }
        return rootViewsSpy$delegatingViewList$1;
    }
}
