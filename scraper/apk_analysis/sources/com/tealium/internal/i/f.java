package com.tealium.internal.i;

import com.tealium.collect.attribute.AttributeGroup;
import com.tealium.collect.attribute.MetricAttribute;
import com.tealium.collect.listeners.MetricUpdateListener;
import java.util.Iterator;

/* compiled from: MetricUpdateMessenger.java */
/* loaded from: classes2.dex */
final class f extends a<MetricUpdateListener, MetricAttribute> {
    public f(AttributeGroup<MetricAttribute> attributeGroup, AttributeGroup<MetricAttribute> attributeGroup2) {
        super(MetricUpdateListener.class, attributeGroup, attributeGroup2);
    }

    @Override // com.tealium.internal.j.n
    public void a(MetricUpdateListener metricUpdateListener) {
        if (e() != null) {
            Iterator<MetricAttribute> it = e().iterator();
            while (it.hasNext()) {
                metricUpdateListener.onMetricUpdate(it.next(), null);
            }
        }
        if (c() != null) {
            for (MetricAttribute metricAttribute : c()) {
                metricUpdateListener.onMetricUpdate(d().get(metricAttribute.getId()), metricAttribute);
            }
        }
        if (b() != null) {
            Iterator<MetricAttribute> it2 = b().iterator();
            while (it2.hasNext()) {
                metricUpdateListener.onMetricUpdate(null, it2.next());
            }
        }
    }
}
