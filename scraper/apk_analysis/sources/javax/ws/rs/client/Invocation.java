package javax.ws.rs.client;

import java.util.Locale;
import java.util.concurrent.Future;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/* loaded from: classes3.dex */
public interface Invocation {

    public interface Builder extends SyncInvoker {
        Builder accept(String... strArr);

        Builder accept(MediaType... mediaTypeArr);

        Builder acceptEncoding(String... strArr);

        Builder acceptLanguage(String... strArr);

        Builder acceptLanguage(Locale... localeArr);

        AsyncInvoker async();

        Invocation build(String str);

        Invocation build(String str, Entity<?> entity);

        Invocation buildDelete();

        Invocation buildGet();

        Invocation buildPost(Entity<?> entity);

        Invocation buildPut(Entity<?> entity);

        Builder cacheControl(CacheControl cacheControl);

        Builder cookie(String str, String str2);

        Builder cookie(Cookie cookie);

        Builder header(String str, Object obj);

        Builder headers(MultivaluedMap<String, Object> multivaluedMap);

        Builder property(String str, Object obj);

        CompletionStageRxInvoker rx();

        <T extends RxInvoker> T rx(Class<T> cls);
    }

    <T> T invoke(Class<T> cls);

    <T> T invoke(GenericType<T> genericType);

    Response invoke();

    Invocation property(String str, Object obj);

    Future<Response> submit();

    <T> Future<T> submit(Class<T> cls);

    <T> Future<T> submit(InvocationCallback<T> invocationCallback);

    <T> Future<T> submit(GenericType<T> genericType);
}
