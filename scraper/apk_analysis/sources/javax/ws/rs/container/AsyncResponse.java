package javax.ws.rs.container;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public interface AsyncResponse {
    public static final long NO_TIMEOUT = 0;

    boolean cancel();

    boolean cancel(int i);

    boolean cancel(Date date);

    boolean isCancelled();

    boolean isDone();

    boolean isSuspended();

    Collection<Class<?>> register(Class<?> cls);

    Collection<Class<?>> register(Object obj);

    Map<Class<?>, Collection<Class<?>>> register(Class<?> cls, Class<?>... clsArr);

    Map<Class<?>, Collection<Class<?>>> register(Object obj, Object... objArr);

    boolean resume(Object obj);

    boolean resume(Throwable th);

    boolean setTimeout(long j, TimeUnit timeUnit);

    void setTimeoutHandler(TimeoutHandler timeoutHandler);
}
