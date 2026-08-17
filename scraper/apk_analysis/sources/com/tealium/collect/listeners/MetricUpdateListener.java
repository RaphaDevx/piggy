package com.tealium.collect.listeners;

import com.tealium.collect.attribute.MetricAttribute;

/* loaded from: classes2.dex */
public interface MetricUpdateListener extends CollectUpdateListener {
    void onMetricUpdate(MetricAttribute metricAttribute, MetricAttribute metricAttribute2);
}
