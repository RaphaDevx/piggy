package io.sentry;

import java.util.List;

/* loaded from: classes2.dex */
public final class NoOpCompositePerformanceCollector implements CompositePerformanceCollector {
    private static final NoOpCompositePerformanceCollector instance = new NoOpCompositePerformanceCollector();

    @Override // io.sentry.CompositePerformanceCollector
    public void close() {
    }

    @Override // io.sentry.CompositePerformanceCollector
    public void onSpanFinished(ISpan iSpan) {
    }

    @Override // io.sentry.CompositePerformanceCollector
    public void onSpanStarted(ISpan iSpan) {
    }

    @Override // io.sentry.CompositePerformanceCollector
    public void start(ITransaction iTransaction) {
    }

    @Override // io.sentry.CompositePerformanceCollector
    public void start(String str) {
    }

    @Override // io.sentry.CompositePerformanceCollector
    public List<PerformanceCollectionData> stop(ITransaction iTransaction) {
        return null;
    }

    @Override // io.sentry.CompositePerformanceCollector
    public List<PerformanceCollectionData> stop(String str) {
        return null;
    }

    public static NoOpCompositePerformanceCollector getInstance() {
        return instance;
    }

    private NoOpCompositePerformanceCollector() {
    }
}
