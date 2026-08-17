package javax.ws.rs.core;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.RuntimeType;

/* loaded from: classes4.dex */
public interface Configuration {
    Set<Class<?>> getClasses();

    Map<Class<?>, Integer> getContracts(Class<?> cls);

    Set<Object> getInstances();

    Map<String, Object> getProperties();

    Object getProperty(String str);

    Collection<String> getPropertyNames();

    RuntimeType getRuntimeType();

    boolean isEnabled(Class<? extends Feature> cls);

    boolean isEnabled(Feature feature);

    boolean isRegistered(Class<?> cls);

    boolean isRegistered(Object obj);
}
