package io.sentry;

import io.sentry.util.AutoClosableReentrantLock;
import io.sentry.util.Objects;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public final class DefaultCompositePerformanceCollector implements CompositePerformanceCollector {
    private static final long TRANSACTION_COLLECTION_INTERVAL_MILLIS = 100;
    private static final long TRANSACTION_COLLECTION_TIMEOUT_MILLIS = 30000;
    private final boolean hasNoCollectors;
    private final SentryOptions options;
    private final AutoClosableReentrantLock timerLock = new AutoClosableReentrantLock();
    private volatile Timer timer = null;
    private final Map<String, List<PerformanceCollectionData>> performanceDataMap = new ConcurrentHashMap();
    private final AtomicBoolean isStarted = new AtomicBoolean(false);
    private long lastCollectionTimestamp = 0;
    private final List<IPerformanceSnapshotCollector> snapshotCollectors = new ArrayList();
    private final List<IPerformanceContinuousCollector> continuousCollectors = new ArrayList();

    public DefaultCompositePerformanceCollector(SentryOptions sentryOptions) {
        boolean z = false;
        this.options = (SentryOptions) Objects.requireNonNull(sentryOptions, "The options object is required.");
        for (IPerformanceCollector iPerformanceCollector : sentryOptions.getPerformanceCollectors()) {
            if (iPerformanceCollector instanceof IPerformanceSnapshotCollector) {
                this.snapshotCollectors.add((IPerformanceSnapshotCollector) iPerformanceCollector);
            }
            if (iPerformanceCollector instanceof IPerformanceContinuousCollector) {
                this.continuousCollectors.add((IPerformanceContinuousCollector) iPerformanceCollector);
            }
        }
        if (this.snapshotCollectors.isEmpty() && this.continuousCollectors.isEmpty()) {
            z = true;
        }
        this.hasNoCollectors = z;
    }

    @Override // io.sentry.CompositePerformanceCollector
    public void start(final ITransaction iTransaction) {
        if (this.hasNoCollectors) {
            this.options.getLogger().log(SentryLevel.INFO, "No collector found. Performance stats will not be captured during transactions.", new Object[0]);
            return;
        }
        Iterator<IPerformanceContinuousCollector> it = this.continuousCollectors.iterator();
        while (it.hasNext()) {
            it.next().onSpanStarted(iTransaction);
        }
        if (!this.performanceDataMap.containsKey(iTransaction.getEventId().toString())) {
            this.performanceDataMap.put(iTransaction.getEventId().toString(), new ArrayList());
            try {
                this.options.getExecutorService().schedule(new Runnable() { // from class: io.sentry.DefaultCompositePerformanceCollector$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        DefaultCompositePerformanceCollector.this.m3804lambda$start$0$iosentryDefaultCompositePerformanceCollector(iTransaction);
                    }
                }, 30000L);
            } catch (RejectedExecutionException e) {
                this.options.getLogger().log(SentryLevel.ERROR, "Failed to call the executor. Performance collector will not be automatically finished. Did you call Sentry.close()?", e);
            }
        }
        start(iTransaction.getEventId().toString());
    }

    @Override // io.sentry.CompositePerformanceCollector
    public void start(String str) {
        if (this.hasNoCollectors) {
            this.options.getLogger().log(SentryLevel.INFO, "No collector found. Performance stats will not be captured during transactions.", new Object[0]);
            return;
        }
        if (!this.performanceDataMap.containsKey(str)) {
            this.performanceDataMap.put(str, new ArrayList());
        }
        if (this.isStarted.getAndSet(true)) {
            return;
        }
        ISentryLifecycleToken acquire = this.timerLock.acquire();
        try {
            if (this.timer == null) {
                this.timer = new Timer(true);
            }
            this.timer.schedule(new TimerTask() { // from class: io.sentry.DefaultCompositePerformanceCollector.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    Iterator it = DefaultCompositePerformanceCollector.this.snapshotCollectors.iterator();
                    while (it.hasNext()) {
                        ((IPerformanceSnapshotCollector) it.next()).setup();
                    }
                }
            }, 0L);
            this.timer.scheduleAtFixedRate(new TimerTask() { // from class: io.sentry.DefaultCompositePerformanceCollector.2
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - DefaultCompositePerformanceCollector.this.lastCollectionTimestamp <= 10) {
                        return;
                    }
                    DefaultCompositePerformanceCollector.this.lastCollectionTimestamp = currentTimeMillis;
                    PerformanceCollectionData performanceCollectionData = new PerformanceCollectionData(new SentryNanotimeDate().nanoTimestamp());
                    Iterator it = DefaultCompositePerformanceCollector.this.snapshotCollectors.iterator();
                    while (it.hasNext()) {
                        ((IPerformanceSnapshotCollector) it.next()).collect(performanceCollectionData);
                    }
                    Iterator it2 = DefaultCompositePerformanceCollector.this.performanceDataMap.values().iterator();
                    while (it2.hasNext()) {
                        ((List) it2.next()).add(performanceCollectionData);
                    }
                }
            }, 100L, 100L);
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

    @Override // io.sentry.CompositePerformanceCollector
    public void onSpanStarted(ISpan iSpan) {
        Iterator<IPerformanceContinuousCollector> it = this.continuousCollectors.iterator();
        while (it.hasNext()) {
            it.next().onSpanStarted(iSpan);
        }
    }

    @Override // io.sentry.CompositePerformanceCollector
    public void onSpanFinished(ISpan iSpan) {
        Iterator<IPerformanceContinuousCollector> it = this.continuousCollectors.iterator();
        while (it.hasNext()) {
            it.next().onSpanFinished(iSpan);
        }
    }

    @Override // io.sentry.CompositePerformanceCollector
    /* renamed from: stop, reason: merged with bridge method [inline-methods] */
    public List<PerformanceCollectionData> m3804lambda$start$0$iosentryDefaultCompositePerformanceCollector(ITransaction iTransaction) {
        this.options.getLogger().log(SentryLevel.DEBUG, "stop collecting performance info for transactions %s (%s)", iTransaction.getName(), iTransaction.getSpanContext().getTraceId().toString());
        Iterator<IPerformanceContinuousCollector> it = this.continuousCollectors.iterator();
        while (it.hasNext()) {
            it.next().onSpanFinished(iTransaction);
        }
        return stop(iTransaction.getEventId().toString());
    }

    @Override // io.sentry.CompositePerformanceCollector
    public List<PerformanceCollectionData> stop(String str) {
        List<PerformanceCollectionData> remove = this.performanceDataMap.remove(str);
        if (this.performanceDataMap.isEmpty()) {
            close();
        }
        return remove;
    }

    @Override // io.sentry.CompositePerformanceCollector
    public void close() {
        this.options.getLogger().log(SentryLevel.DEBUG, "stop collecting all performance info for transactions", new Object[0]);
        this.performanceDataMap.clear();
        Iterator<IPerformanceContinuousCollector> it = this.continuousCollectors.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
        if (this.isStarted.getAndSet(false)) {
            ISentryLifecycleToken acquire = this.timerLock.acquire();
            try {
                if (this.timer != null) {
                    this.timer.cancel();
                    this.timer = null;
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
    }
}
