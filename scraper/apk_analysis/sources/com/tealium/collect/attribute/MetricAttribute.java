package com.tealium.collect.attribute;

import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MetricAttribute extends BaseAttribute {
    private final double b;
    private volatile int c;

    public MetricAttribute(String str, double d) {
        super(str);
        this.b = d;
    }

    public boolean equals(Object obj) {
        if (obj == null || !MetricAttribute.class.equals(obj.getClass())) {
            return false;
        }
        MetricAttribute metricAttribute = (MetricAttribute) obj;
        return getId().equals(metricAttribute.getId()) && this.b == metricAttribute.b;
    }

    public double getValue() {
        return this.b;
    }

    public int hashCode() {
        int i = this.c;
        if (i != 0) {
            return i;
        }
        long doubleToLongBits = Double.doubleToLongBits(this.b);
        int hashCode = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + ((getId().hashCode() + 527) * 31);
        this.c = hashCode;
        return hashCode;
    }

    public String toString() {
        return String.format(Locale.ROOT, "Metric : { id:%s, value:%f }", JSONObject.quote(getId()), Double.valueOf(this.b));
    }
}
