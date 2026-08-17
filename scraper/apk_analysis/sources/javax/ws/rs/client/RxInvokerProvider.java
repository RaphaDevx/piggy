package javax.ws.rs.client;

import java.util.concurrent.ExecutorService;
import javax.ws.rs.client.RxInvoker;

/* loaded from: classes3.dex */
public interface RxInvokerProvider<T extends RxInvoker> {
    T getRxInvoker(SyncInvoker syncInvoker, ExecutorService executorService);

    boolean isProviderFor(Class<?> cls);
}
