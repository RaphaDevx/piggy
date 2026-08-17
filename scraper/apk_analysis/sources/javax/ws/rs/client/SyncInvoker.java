package javax.ws.rs.client;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/* loaded from: classes3.dex */
public interface SyncInvoker {
    <T> T delete(Class<T> cls);

    <T> T delete(GenericType<T> genericType);

    Response delete();

    <T> T get(Class<T> cls);

    <T> T get(GenericType<T> genericType);

    Response get();

    Response head();

    <T> T method(String str, Class<T> cls);

    <T> T method(String str, Entity<?> entity, Class<T> cls);

    <T> T method(String str, Entity<?> entity, GenericType<T> genericType);

    <T> T method(String str, GenericType<T> genericType);

    Response method(String str);

    Response method(String str, Entity<?> entity);

    <T> T options(Class<T> cls);

    <T> T options(GenericType<T> genericType);

    Response options();

    <T> T post(Entity<?> entity, Class<T> cls);

    <T> T post(Entity<?> entity, GenericType<T> genericType);

    Response post(Entity<?> entity);

    <T> T put(Entity<?> entity, Class<T> cls);

    <T> T put(Entity<?> entity, GenericType<T> genericType);

    Response put(Entity<?> entity);

    <T> T trace(Class<T> cls);

    <T> T trace(GenericType<T> genericType);

    Response trace();
}
