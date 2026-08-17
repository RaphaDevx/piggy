package com.tealium.collect.attribute;

import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class DateAttribute extends BaseAttribute {
    private final long b;
    private volatile int c;

    public DateAttribute(String str, long j) {
        super(str);
        this.b = j;
    }

    public boolean equals(Object obj) {
        if (obj == null || !DateAttribute.class.equals(obj.getClass())) {
            return false;
        }
        DateAttribute dateAttribute = (DateAttribute) obj;
        return getId().equals(dateAttribute.getId()) && this.b == dateAttribute.b;
    }

    public long getTime() {
        return this.b;
    }

    public int hashCode() {
        int i = this.c;
        if (i != 0) {
            return i;
        }
        int hashCode = (getId().hashCode() + 527) * 31;
        long j = this.b;
        int i2 = hashCode + ((int) (j ^ (j >>> 32)));
        this.c = i2;
        return i2;
    }

    public String toString() {
        return String.format(Locale.ROOT, "Date : { id:%s, timestamp_ms:%d }", JSONObject.quote(getId()), Long.valueOf(this.b));
    }
}
