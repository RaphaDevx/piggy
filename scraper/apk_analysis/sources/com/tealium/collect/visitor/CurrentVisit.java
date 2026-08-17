package com.tealium.collect.visitor;

import com.tealium.collect.attribute.DateAttribute;
import com.tealium.collect.attribute.FlagAttribute;
import com.tealium.collect.attribute.MetricAttribute;
import com.tealium.collect.attribute.PropertyAttribute;
import java.util.Collection;

/* loaded from: classes2.dex */
public final class CurrentVisit extends a {
    private volatile int g;
    private final long h;
    private final int i;

    public CurrentVisit() {
        super(0L, null, null, null, null);
        this.h = 0L;
        this.i = 0;
    }

    @Override // com.tealium.collect.visitor.a
    public boolean equals(Object obj) {
        if (!super.equals(obj) || !(obj instanceof CurrentVisit)) {
            return false;
        }
        CurrentVisit currentVisit = (CurrentVisit) obj;
        return this.h == currentVisit.h && this.i == currentVisit.i && super.equals(currentVisit);
    }

    public long getLastEventTimestamp() {
        return this.h;
    }

    public int getTotalEventCount() {
        return this.i;
    }

    @Override // com.tealium.collect.visitor.a
    public int hashCode() {
        int i = this.g;
        if (i != 0) {
            return i;
        }
        int hashCode = (super.hashCode() + 527) * 31;
        long j = this.h;
        int i2 = ((hashCode + ((int) (j ^ (j >>> 32)))) * 31) + this.i;
        this.g = i2;
        return i2;
    }

    public String toString() {
        return toString(null);
    }

    public String toString(String str) {
        String str2;
        if (str == null) {
            str = "";
        }
        if (str.length() == 0) {
            str2 = "    ";
        } else {
            str2 = str + str;
        }
        String property = System.getProperty("line.separator");
        return str + "CurrentVisit : {" + property + str2 + "creation_ts : " + getCreationTimestamp() + property + str2 + "last_event : " + this.h + property + str2 + "total_event_count : " + this.i + property + str2 + "dates : " + getDates().toString(str2) + property + str2 + "flags : " + getFlags().toString(str2) + property + str2 + "metrics : " + getMetrics().toString(str2) + property + str2 + "properties : " + getProperties().toString(str2) + property + str + "}";
    }

    public CurrentVisit(long j, Collection<DateAttribute> collection, Collection<FlagAttribute> collection2, Collection<MetricAttribute> collection3, Collection<PropertyAttribute> collection4, long j2, int i) {
        super(j, collection, collection2, collection3, collection4);
        this.h = j2;
        this.i = i;
    }
}
