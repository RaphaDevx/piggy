package javax.ws.rs.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public abstract class AbstractMultivaluedMap<K, V> implements MultivaluedMap<K, V> {
    protected final Map<K, List<V>> store;

    protected void addFirstNull(List<V> list) {
    }

    protected void addNull(List<V> list) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
        return put((AbstractMultivaluedMap<K, V>) obj, (List) obj2);
    }

    public AbstractMultivaluedMap(Map<K, List<V>> map) {
        if (map == null) {
            throw new NullPointerException("Underlying store must not be 'null'.");
        }
        this.store = map;
    }

    @Override // javax.ws.rs.core.MultivaluedMap
    public final void putSingle(K k, V v) {
        List<V> values = getValues(k);
        values.clear();
        if (v != null) {
            values.add(v);
        } else {
            addNull(values);
        }
    }

    @Override // javax.ws.rs.core.MultivaluedMap
    public final void add(K k, V v) {
        List<V> values = getValues(k);
        if (v != null) {
            values.add(v);
        } else {
            addNull(values);
        }
    }

    @Override // javax.ws.rs.core.MultivaluedMap
    public final void addAll(K k, V... vArr) {
        if (vArr == null) {
            throw new NullPointerException("Supplied array of values must not be null.");
        }
        if (vArr.length == 0) {
            return;
        }
        List<V> values = getValues(k);
        for (V v : vArr) {
            if (v != null) {
                values.add(v);
            } else {
                addNull(values);
            }
        }
    }

    @Override // javax.ws.rs.core.MultivaluedMap
    public final void addAll(K k, List<V> list) {
        if (list == null) {
            throw new NullPointerException("Supplied list of values must not be null.");
        }
        if (list.isEmpty()) {
            return;
        }
        List<V> values = getValues(k);
        for (V v : list) {
            if (v != null) {
                values.add(v);
            } else {
                addNull(values);
            }
        }
    }

    @Override // javax.ws.rs.core.MultivaluedMap
    public final V getFirst(K k) {
        List<V> list = this.store.get(k);
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    @Override // javax.ws.rs.core.MultivaluedMap
    public final void addFirst(K k, V v) {
        List<V> values = getValues(k);
        if (v != null) {
            values.add(0, v);
        } else {
            addFirstNull(values);
        }
    }

    protected final List<V> getValues(K k) {
        List<V> list = this.store.get(k);
        if (list != null) {
            return list;
        }
        LinkedList linkedList = new LinkedList();
        this.store.put(k, linkedList);
        return linkedList;
    }

    public String toString() {
        return this.store.toString();
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.store.hashCode();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.store.equals(obj);
    }

    @Override // java.util.Map
    public Collection<List<V>> values() {
        return this.store.values();
    }

    @Override // java.util.Map
    public int size() {
        return this.store.size();
    }

    @Override // java.util.Map
    public List<V> remove(Object obj) {
        return this.store.remove(obj);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends List<V>> map) {
        this.store.putAll(map);
    }

    public List<V> put(K k, List<V> list) {
        return this.store.put(k, list);
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return this.store.keySet();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.store.isEmpty();
    }

    @Override // java.util.Map
    public List<V> get(Object obj) {
        return this.store.get(obj);
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, List<V>>> entrySet() {
        return this.store.entrySet();
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.store.containsValue(obj);
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.store.containsKey(obj);
    }

    @Override // java.util.Map
    public void clear() {
        this.store.clear();
    }

    @Override // javax.ws.rs.core.MultivaluedMap
    public boolean equalsIgnoreValueOrder(MultivaluedMap<K, V> multivaluedMap) {
        if (this == multivaluedMap) {
            return true;
        }
        if (!keySet().equals(multivaluedMap.keySet())) {
            return false;
        }
        for (Map.Entry<K, List<V>> entry : entrySet()) {
            List list = (List) multivaluedMap.get(entry.getKey());
            if (entry.getValue().size() != list.size()) {
                return false;
            }
            Iterator<V> it = entry.getValue().iterator();
            while (it.hasNext()) {
                if (!list.contains(it.next())) {
                    return false;
                }
            }
        }
        return true;
    }
}
