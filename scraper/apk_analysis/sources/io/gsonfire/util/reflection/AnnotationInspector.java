package io.gsonfire.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes2.dex */
public abstract class AnnotationInspector<T extends AccessibleObject, M> {
    private final ConcurrentMap<Class, ConcurrentMap<Class<? extends Annotation>, Collection<M>>> cache = new ConcurrentHashMap();

    protected abstract T[] getDeclaredMembers(Class cls);

    protected abstract M map(T t);

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.util.concurrent.ConcurrentMap] */
    public Collection<M> getAnnotatedMembers(Class cls, Class<? extends Annotation> cls2) {
        if (cls != null) {
            Collection<M> fromCache = getFromCache(cls, cls2);
            if (fromCache != null) {
                return fromCache;
            }
            if (getFromCache(cls, cls2) == null) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                for (T t : getDeclaredMembers(cls)) {
                    if (t.isAnnotationPresent(cls2)) {
                        t.setAccessible(true);
                        linkedHashSet.add(map(t));
                    }
                }
                linkedHashSet.addAll(getAnnotatedMembers(cls.getSuperclass(), cls2));
                for (Class<?> cls3 : cls.getInterfaces()) {
                    linkedHashSet.addAll(getAnnotatedMembers(cls3, cls2));
                }
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                ConcurrentMap<Class<? extends Annotation>, Collection<M>> putIfAbsent = this.cache.putIfAbsent(cls, concurrentHashMap);
                if (putIfAbsent != 0) {
                    concurrentHashMap = putIfAbsent;
                }
                concurrentHashMap.put(cls2, linkedHashSet);
                return linkedHashSet;
            }
        }
        return Collections.emptyList();
    }

    private Collection<M> getFromCache(Class cls, Class<? extends Annotation> cls2) {
        Collection<M> collection;
        ConcurrentMap<Class<? extends Annotation>, Collection<M>> concurrentMap = this.cache.get(cls);
        if (concurrentMap == null || (collection = concurrentMap.get(cls2)) == null) {
            return null;
        }
        return collection;
    }
}
