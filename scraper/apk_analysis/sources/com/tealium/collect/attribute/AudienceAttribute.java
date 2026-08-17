package com.tealium.collect.attribute;

import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class AudienceAttribute extends BaseAttribute {
    private final String b;
    private volatile int c;

    public AudienceAttribute(String str, String str2) {
        super(str);
        if (str2 == null || str2.length() == 0) {
            throw new IllegalArgumentException("name must be provided.");
        }
        this.b = str2;
    }

    public boolean equals(Object obj) {
        if (obj == null || !AudienceAttribute.class.equals(obj.getClass())) {
            return false;
        }
        AudienceAttribute audienceAttribute = (AudienceAttribute) obj;
        return getId().equals(audienceAttribute.getId()) && this.b.equals(audienceAttribute.b);
    }

    public String getName() {
        return this.b;
    }

    public int hashCode() {
        int i = this.c;
        if (i != 0) {
            return i;
        }
        int hashCode = ((getId().hashCode() + 527) * 31) + this.b.hashCode();
        this.c = hashCode;
        return hashCode;
    }

    public String toString() {
        return String.format(Locale.ROOT, "Audience : { id:%s, name:%s }", JSONObject.quote(getId()), JSONObject.quote(this.b));
    }
}
