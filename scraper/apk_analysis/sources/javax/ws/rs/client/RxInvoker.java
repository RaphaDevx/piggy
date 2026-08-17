package javax.ws.rs.client;

import javax.ws.rs.core.GenericType;

/* loaded from: classes3.dex */
public interface RxInvoker<T> {
    T delete();

    <R> T delete(Class<R> cls);

    <R> T delete(GenericType<R> genericType);

    T get();

    <R> T get(Class<R> cls);

    <R> T get(GenericType<R> genericType);

    T head();

    T method(String str);

    <R> T method(String str, Class<R> cls);

    T method(String str, Entity<?> entity);

    <R> T method(String str, Entity<?> entity, Class<R> cls);

    <R> T method(String str, Entity<?> entity, GenericType<R> genericType);

    <R> T method(String str, GenericType<R> genericType);

    T options();

    <R> T options(Class<R> cls);

    <R> T options(GenericType<R> genericType);

    T post(Entity<?> entity);

    <R> T post(Entity<?> entity, Class<R> cls);

    <R> T post(Entity<?> entity, GenericType<R> genericType);

    T put(Entity<?> entity);

    <R> T put(Entity<?> entity, Class<R> cls);

    <R> T put(Entity<?> entity, GenericType<R> genericType);

    T trace();

    <R> T trace(Class<R> cls);

    <R> T trace(GenericType<R> genericType);
}
