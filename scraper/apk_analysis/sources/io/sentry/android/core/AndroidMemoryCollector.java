package io.sentry.android.core;

import android.os.Debug;
import io.sentry.IPerformanceSnapshotCollector;
import io.sentry.PerformanceCollectionData;

/* loaded from: classes2.dex */
public class AndroidMemoryCollector implements IPerformanceSnapshotCollector {
    @Override // io.sentry.IPerformanceSnapshotCollector
    public void setup() {
    }

    @Override // io.sentry.IPerformanceSnapshotCollector
    public void collect(PerformanceCollectionData performanceCollectionData) {
        long freeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long nativeHeapSize = Debug.getNativeHeapSize() - Debug.getNativeHeapFreeSize();
        performanceCollectionData.setUsedHeapMemory(Long.valueOf(freeMemory));
        performanceCollectionData.setUsedNativeMemory(Long.valueOf(nativeHeapSize));
    }
}
