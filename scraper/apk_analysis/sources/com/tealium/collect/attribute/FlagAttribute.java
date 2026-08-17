package com.tealium.collect.attribute;

import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class FlagAttribute extends BaseAttribute {
    private final boolean b;
    private volatile int c;

    public FlagAttribute(String str, boolean z) {
        super(str);
        this.b = z;
        this.c = 0;
    }

    public boolean equals(Object obj) {
        if (obj == null || !FlagAttribute.class.equals(obj.getClass())) {
            return false;
        }
        FlagAttribute flagAttribute = (FlagAttribute) obj;
        return getId().equals(flagAttribute.getId()) && this.b == flagAttribute.b;
    }

    public boolean getValue() {
        return this.b;
    }

    public int hashCode() {
        int i = this.c;
        if (i != 0) {
            return i;
        }
        int hashCode = ((getId().hashCode() + 527) * 31) + (this.b ? 1 : 0);
        this.c = hashCode;
        return hashCode;
    }

    public String toString() {
        return String.format(Locale.ROOT, "Flag : { id:%s, value:%b }", JSONObject.quote(getId()), Boolean.valueOf(this.b));
    }
}
