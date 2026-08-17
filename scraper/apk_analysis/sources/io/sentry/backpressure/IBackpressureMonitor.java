package io.sentry.backpressure;

/* loaded from: classes2.dex */
public interface IBackpressureMonitor {
    void close();

    int getDownsampleFactor();

    void start();
}
