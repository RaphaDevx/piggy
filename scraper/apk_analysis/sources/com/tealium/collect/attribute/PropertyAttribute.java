package com.tealium.collect.attribute;

import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class PropertyAttribute extends BaseAttribute {
    private final String b;
    private volatile int c;

    public PropertyAttribute(String str, String str2) {
        super(str);
        this.b = str2;
    }

    public boolean equals(Object obj) {
        if (obj == null || !PropertyAttribute.class.equals(obj.getClass())) {
            return false;
        }
        PropertyAttribute propertyAttribute = (PropertyAttribute) obj;
        if (!getId().equals(propertyAttribute.getId())) {
            return false;
        }
        String str = this.b;
        boolean z = str == null;
        String str2 = propertyAttribute.b;
        boolean z2 = str2 == null;
        if (z && z2) {
            return true;
        }
        if (z ^ z2) {
            return false;
        }
        return str.equals(str2);
    }

    public String getValue() {
        return this.b;
    }

    public int hashCode() {
        int i = this.c;
        if (i == 0) {
            i = getId().hashCode() + 527;
            String str = this.b;
            if (str != null) {
                i = (i * 31) + str.hashCode();
            }
            this.c = i;
        }
        return i;
    }

    public String toString() {
        return String.format(Locale.ROOT, "Property : { id:%s, value:%s }", JSONObject.quote(getId()), JSONObject.quote(this.b));
    }
}
