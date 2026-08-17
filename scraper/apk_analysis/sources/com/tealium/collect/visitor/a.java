package com.tealium.collect.visitor;

import com.tealium.collect.attribute.AttributeGroup;
import com.tealium.collect.attribute.DateAttribute;
import com.tealium.collect.attribute.FlagAttribute;
import com.tealium.collect.attribute.MetricAttribute;
import com.tealium.collect.attribute.PropertyAttribute;
import java.util.Collection;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BaseVisit.java */
/* loaded from: classes2.dex */
public class a {
    private volatile int a;
    private final long b;
    private final AttributeGroup<FlagAttribute> c;
    private final AttributeGroup<MetricAttribute> d;
    private final AttributeGroup<PropertyAttribute> e;
    private final AttributeGroup<DateAttribute> f;

    a() {
        this.b = 0L;
        this.c = new AttributeGroup<>();
        this.d = new AttributeGroup<>();
        this.f = new AttributeGroup<>();
        this.e = new AttributeGroup<>();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.b == aVar.b && this.c.equals(aVar.c) && this.d.equals(aVar.d) && this.e.equals(aVar.e) && this.f.equals(aVar.f);
    }

    public final long getCreationTimestamp() {
        return this.b;
    }

    public AttributeGroup<DateAttribute> getDates() {
        return this.f;
    }

    public AttributeGroup<FlagAttribute> getFlags() {
        return this.c;
    }

    public AttributeGroup<MetricAttribute> getMetrics() {
        return this.d;
    }

    public AttributeGroup<PropertyAttribute> getProperties() {
        return this.e;
    }

    public int hashCode() {
        int i = this.a;
        if (i != 0) {
            return i;
        }
        long j = this.b;
        int hashCode = ((((((((((int) (j ^ (j >>> 32))) + 527) * 31) + this.f.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode();
        this.a = hashCode;
        return hashCode;
    }

    a(long j, Collection<DateAttribute> collection, Collection<FlagAttribute> collection2, Collection<MetricAttribute> collection3, Collection<PropertyAttribute> collection4) {
        this.b = j;
        this.c = new AttributeGroup<>(collection2);
        this.d = new AttributeGroup<>(collection3);
        this.e = new AttributeGroup<>(collection4);
        this.f = new AttributeGroup<>(collection);
    }
}
