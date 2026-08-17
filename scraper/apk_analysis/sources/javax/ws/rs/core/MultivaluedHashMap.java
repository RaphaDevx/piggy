package javax.ws.rs.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class MultivaluedHashMap<K, V> extends AbstractMultivaluedMap<K, V> implements Serializable {
    private static final long serialVersionUID = -6052320403766368902L;

    public MultivaluedHashMap() {
        super(new HashMap());
    }

    public MultivaluedHashMap(int i) {
        super(new HashMap(i));
    }

    public MultivaluedHashMap(int i, float f) {
        super(new HashMap(i, f));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MultivaluedHashMap(MultivaluedMap<? extends K, ? extends V> multivaluedMap) {
        this();
        putAll((MultivaluedMap) multivaluedMap);
    }

    private <T extends K, U extends V> void putAll(MultivaluedMap<T, U> multivaluedMap) {
        for (Map.Entry<T, U> entry : multivaluedMap.entrySet()) {
            this.store.put(entry.getKey(), new ArrayList((Collection) entry.getValue()));
        }
    }

    public MultivaluedHashMap(Map<? extends K, ? extends V> map) {
        this();
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            putSingle(entry.getKey(), entry.getValue());
        }
    }
}
