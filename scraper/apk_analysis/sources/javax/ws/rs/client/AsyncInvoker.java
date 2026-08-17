package javax.ws.rs.client;

import java.util.concurrent.Future;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/* loaded from: classes3.dex */
public interface AsyncInvoker {
    Future<Response> delete();

    <T> Future<T> delete(Class<T> cls);

    <T> Future<T> delete(InvocationCallback<T> invocationCallback);

    <T> Future<T> delete(GenericType<T> genericType);

    Future<Response> get();

    <T> Future<T> get(Class<T> cls);

    <T> Future<T> get(InvocationCallback<T> invocationCallback);

    <T> Future<T> get(GenericType<T> genericType);

    Future<Response> head();

    Future<Response> head(InvocationCallback<Response> invocationCallback);

    Future<Response> method(String str);

    <T> Future<T> method(String str, Class<T> cls);

    Future<Response> method(String str, Entity<?> entity);

    <T> Future<T> method(String str, Entity<?> entity, Class<T> cls);

    <T> Future<T> method(String str, Entity<?> entity, InvocationCallback<T> invocationCallback);

    <T> Future<T> method(String str, Entity<?> entity, GenericType<T> genericType);

    <T> Future<T> method(String str, InvocationCallback<T> invocationCallback);

    <T> Future<T> method(String str, GenericType<T> genericType);

    Future<Response> options();

    <T> Future<T> options(Class<T> cls);

    <T> Future<T> options(InvocationCallback<T> invocationCallback);

    <T> Future<T> options(GenericType<T> genericType);

    Future<Response> post(Entity<?> entity);

    <T> Future<T> post(Entity<?> entity, Class<T> cls);

    <T> Future<T> post(Entity<?> entity, InvocationCallback<T> invocationCallback);

    <T> Future<T> post(Entity<?> entity, GenericType<T> genericType);

    Future<Response> put(Entity<?> entity);

    <T> Future<T> put(Entity<?> entity, Class<T> cls);

    <T> Future<T> put(Entity<?> entity, InvocationCallback<T> invocationCallback);

    <T> Future<T> put(Entity<?> entity, GenericType<T> genericType);

    Future<Response> trace();

    <T> Future<T> trace(Class<T> cls);

    <T> Future<T> trace(InvocationCallback<T> invocationCallback);

    <T> Future<T> trace(GenericType<T> genericType);
}
