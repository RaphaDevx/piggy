package com.scandit.datacapture.tools.internal.sdk;

import androidx.exifinterface.media.ExifInterface;
import com.scandit.datacapture.tools.internal.module.mapmaker.C0618f;
import com.scandit.datacapture.tools.internal.module.mapmaker.C0619g;
import com.scandit.datacapture.tools.internal.module.mapmaker.C0630s;
import com.scandit.datacapture.tools.internal.module.mapmaker.D;
import com.scandit.datacapture.tools.internal.module.mapmaker.G;
import com.scandit.datacapture.tools.internal.module.mapmaker.N;
import com.scandit.datacapture.tools.internal.module.mapmaker.P;
import com.scandit.datacapture.tools.internal.module.mapmaker.S;
import com.scandit.datacapture.tools.internal.module.mapmaker.t;
import com.scandit.datacapture.tools.internal.module.mapmaker.u;
import com.scandit.datacapture.tools.internal.module.mapmaker.v;
import com.scandit.datacapture.tools.internal.module.mapmaker.y;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JQ\u0010\r\u001a\u00028\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0004\"\b\b\u0001\u0010\u0006*\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00028\u00002\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u000bH\u0016¢\u0006\u0004\b\r\u0010\u000eJC\u0010\u000f\u001a\u00028\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0004\"\b\b\u0001\u0010\u0006*\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000f\u0010\u0010JK\u0010\u0013\u001a\u00020\u0012\"\b\b\u0000\u0010\u0005*\u00020\u0004\"\b\b\u0001\u0010\u0006*\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J9\u0010\u0015\u001a\u00028\u0000\"\b\b\u0000\u0010\u0005*\u00020\u0004\"\b\b\u0001\u0010\u0006*\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\u0011\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u0015\u0010\u0016JG\u0010\u0018\u001a\u00028\u0000\"\b\b\u0000\u0010\u0005*\u00020\u0004\"\b\b\u0001\u0010\u0006*\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\u0011\u001a\u00028\u00012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0016¢\u0006\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/scandit/datacapture/tools/internal/sdk/GuavaMapMakerProxyCache;", "Lcom/scandit/datacapture/tools/internal/sdk/ProxyCache;", "<init>", "()V", "", "K", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Lkotlin/reflect/KClass;", "keyClass", "scope", "key", "Lkotlin/Function0;", "defaultValue", "getOrPut", "(Lkotlin/reflect/KClass;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "require", "(Lkotlin/reflect/KClass;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "value", "", "put", "(Lkotlin/reflect/KClass;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "requireByValue", "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Ljava/lang/Object;", "defaultKey", "getByValueOrPut", "(Lkotlin/reflect/KClass;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class GuavaMapMakerProxyCache implements ProxyCache {
    private final LinkedHashMap a = new LinkedHashMap();
    private final LinkedHashMap b = new LinkedHashMap();

    private final synchronized ConcurrentMap a(KClass kClass, Object obj) {
        ConcurrentMap concurrentMap;
        if (obj == null) {
            LinkedHashMap linkedHashMap = this.a;
            Object obj2 = linkedHashMap.get(kClass);
            if (obj2 == null) {
                obj2 = a();
                linkedHashMap.put(kClass, obj2);
            }
            concurrentMap = (ConcurrentMap) obj2;
        } else {
            WeakHashMap weakHashMap = (WeakHashMap) this.b.get(kClass);
            if (weakHashMap == null) {
                weakHashMap = new WeakHashMap();
                this.b.put(kClass, weakHashMap);
            }
            Object obj3 = weakHashMap.get(obj);
            if (obj3 == null) {
                obj3 = a();
                weakHashMap.put(obj, obj3);
            }
            Intrinsics.checkNotNull(obj3);
            concurrentMap = (ConcurrentMap) obj3;
        }
        return concurrentMap;
    }

    @Override // com.scandit.datacapture.tools.internal.sdk.ProxyCache
    public <K, V> K getByValueOrPut(KClass<K> keyClass, V value, Function0<? extends K> defaultKey) {
        Object obj;
        Intrinsics.checkNotNullParameter(keyClass, "keyClass");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(defaultKey, "defaultKey");
        ConcurrentMap a = a(keyClass, null);
        Iterator<T> it = a.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((Map.Entry) obj).getValue() == value) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) obj;
        K k = entry != null ? (K) entry.getKey() : null;
        if (k == null) {
            k = defaultKey.invoke();
            a.put(k, value);
        }
        Intrinsics.checkNotNull(k, "null cannot be cast to non-null type K of com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache.getByValueOrPut");
        return k;
    }

    @Override // com.scandit.datacapture.tools.internal.sdk.ProxyCache
    public <K, V> V getOrPut(KClass<K> keyClass, Object scope, K key, Function0<? extends V> defaultValue) {
        Object putIfAbsent;
        Intrinsics.checkNotNullParameter(keyClass, "keyClass");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        ConcurrentMap a = a(keyClass, scope);
        V v = (V) a.get(key);
        if (v == null && (putIfAbsent = a.putIfAbsent(key, (v = defaultValue.invoke()))) != null) {
            v = (V) putIfAbsent;
        }
        Intrinsics.checkNotNull(v, "null cannot be cast to non-null type V of com.scandit.datacapture.tools.internal.sdk.GuavaMapMakerProxyCache.getOrPut");
        return v;
    }

    @Override // com.scandit.datacapture.tools.internal.sdk.ProxyCache
    public <K, V> void put(KClass<K> keyClass, Object scope, K key, V value) {
        Intrinsics.checkNotNullParameter(keyClass, "keyClass");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        a(keyClass, scope).put(key, value);
    }

    @Override // com.scandit.datacapture.tools.internal.sdk.ProxyCache
    public <K, V> V require(KClass<K> keyClass, Object scope, K key) {
        Intrinsics.checkNotNullParameter(keyClass, "keyClass");
        Intrinsics.checkNotNullParameter(key, "key");
        V v = (V) a(keyClass, scope).get(key);
        if (v != null) {
            return v;
        }
        throw new IllegalArgumentException("Cache for class " + keyClass + " contains no key " + key);
    }

    @Override // com.scandit.datacapture.tools.internal.sdk.ProxyCache
    public <K, V> K requireByValue(KClass<K> keyClass, V value) {
        Object obj;
        Intrinsics.checkNotNullParameter(keyClass, "keyClass");
        Intrinsics.checkNotNullParameter(value, "value");
        K k = null;
        Iterator<T> it = a(keyClass, null).entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((Map.Entry) obj).getValue(), value)) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry != null) {
            k = (K) entry.getKey();
        }
        if (k != null) {
            return k;
        }
        throw new IllegalArgumentException("Cache for class " + keyClass + " contains no key for value " + value);
    }

    private static N a() {
        N n;
        C0618f c0618f = new C0618f();
        t tVar = u.b;
        u uVar = c0618f.a;
        S.a(uVar == null, "Key strength was already set to %s", (t) uVar);
        c0618f.a = tVar;
        u uVar2 = c0618f.b;
        S.a(uVar2 == null, "Value strength was already set to %s", (t) uVar2);
        c0618f.b = tVar;
        C0619g c0619g = N.j;
        u uVar3 = c0618f.a;
        C0630s c0630s = u.a;
        if (((u) P.a(uVar3, c0630s)) == c0630s && ((u) P.a(c0618f.b, c0630s)) == c0630s) {
            n = new N(c0618f, v.a);
        } else if (((u) P.a(c0618f.a, c0630s)) == c0630s && ((u) P.a(c0618f.b, c0630s)) == tVar) {
            n = new N(c0618f, y.a);
        } else if (((u) P.a(c0618f.a, c0630s)) == tVar && ((u) P.a(c0618f.b, c0630s)) == c0630s) {
            n = new N(c0618f, D.a);
        } else if (((u) P.a(c0618f.a, c0630s)) == tVar && ((u) P.a(c0618f.b, c0630s)) == tVar) {
            n = new N(c0618f, G.a);
        } else {
            throw new AssertionError();
        }
        Intrinsics.checkNotNullExpressionValue(n, "makeMap(...)");
        return n;
    }
}
