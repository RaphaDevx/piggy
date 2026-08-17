package io.sentry;

import io.sentry.util.AutoClosableReentrantLock;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
/* loaded from: classes2.dex */
public final class SentrySpanStorage {
    private static volatile SentrySpanStorage INSTANCE;
    private static final AutoClosableReentrantLock staticLock = new AutoClosableReentrantLock();
    private final Map<String, ISpan> spans = new ConcurrentHashMap();

    public static SentrySpanStorage getInstance() {
        if (INSTANCE == null) {
            ISentryLifecycleToken acquire = staticLock.acquire();
            try {
                if (INSTANCE == null) {
                    INSTANCE = new SentrySpanStorage();
                }
                if (acquire != null) {
                    acquire.close();
                }
            } catch (Throwable th) {
                if (acquire != null) {
                    try {
                        acquire.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        return INSTANCE;
    }

    private SentrySpanStorage() {
    }

    public void store(String str, ISpan iSpan) {
        this.spans.put(str, iSpan);
    }

    public ISpan get(String str) {
        return this.spans.get(str);
    }

    public ISpan removeAndGet(String str) {
        return this.spans.remove(str);
    }
}
