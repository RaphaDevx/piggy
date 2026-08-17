package io.sentry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public final class SentryAttributes {
    private final Map<String, SentryAttribute> attributes;

    private SentryAttributes(Map<String, SentryAttribute> map) {
        this.attributes = map;
    }

    public void add(SentryAttribute sentryAttribute) {
        if (sentryAttribute == null) {
            return;
        }
        this.attributes.put(sentryAttribute.getName(), sentryAttribute);
    }

    public Map<String, SentryAttribute> getAttributes() {
        return this.attributes;
    }

    public static SentryAttributes of(SentryAttribute... sentryAttributeArr) {
        if (sentryAttributeArr == null) {
            return new SentryAttributes(new ConcurrentHashMap());
        }
        SentryAttributes sentryAttributes = new SentryAttributes(new ConcurrentHashMap(sentryAttributeArr.length));
        for (SentryAttribute sentryAttribute : sentryAttributeArr) {
            sentryAttributes.add(sentryAttribute);
        }
        return sentryAttributes;
    }

    public static SentryAttributes fromMap(Map<String, Object> map) {
        if (map == null) {
            return new SentryAttributes(new ConcurrentHashMap());
        }
        SentryAttributes sentryAttributes = new SentryAttributes(new ConcurrentHashMap(map.size()));
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key != null) {
                sentryAttributes.add(SentryAttribute.named(key, entry.getValue()));
            }
        }
        return sentryAttributes;
    }
}
