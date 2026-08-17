package javax.ws.rs.core;

import java.util.Map;
import javax.ws.rs.core.Configurable;

/* loaded from: classes4.dex */
public interface Configurable<C extends Configurable> {
    Configuration getConfiguration();

    C property(String str, Object obj);

    C register(Class<?> cls);

    C register(Class<?> cls, int i);

    C register(Class<?> cls, Map<Class<?>, Integer> map);

    C register(Class<?> cls, Class<?>... clsArr);

    C register(Object obj);

    C register(Object obj, int i);

    C register(Object obj, Map<Class<?>, Integer> map);

    C register(Object obj, Class<?>... clsArr);
}
