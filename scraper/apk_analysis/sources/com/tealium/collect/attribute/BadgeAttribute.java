package com.tealium.collect.attribute;

import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class BadgeAttribute extends BaseAttribute {
    private volatile int b;

    public BadgeAttribute(String str) {
        super(str);
        this.b = 0;
    }

    public boolean equals(Object obj) {
        if (obj != null && BadgeAttribute.class.equals(obj.getClass())) {
            return getId().equals(((BadgeAttribute) obj).getId());
        }
        return false;
    }

    public int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int hashCode = getId().hashCode() + 527;
        this.b = hashCode;
        return hashCode;
    }

    public String toString() {
        return String.format(Locale.ROOT, "Badge : { id:%s }", JSONObject.quote(getId()));
    }
}
