package io.sentry;

/* loaded from: classes2.dex */
public final class SentryAttribute {
    private final String name;
    private final SentryAttributeType type;
    private final Object value;

    private SentryAttribute(String str, SentryAttributeType sentryAttributeType, Object obj) {
        this.name = str;
        this.type = sentryAttributeType;
        this.value = obj;
    }

    public String getName() {
        return this.name;
    }

    public SentryAttributeType getType() {
        return this.type;
    }

    public Object getValue() {
        return this.value;
    }

    public static SentryAttribute named(String str, Object obj) {
        return new SentryAttribute(str, null, obj);
    }

    public static SentryAttribute booleanAttribute(String str, Boolean bool) {
        return new SentryAttribute(str, SentryAttributeType.BOOLEAN, bool);
    }

    public static SentryAttribute integerAttribute(String str, Integer num) {
        return new SentryAttribute(str, SentryAttributeType.INTEGER, num);
    }

    public static SentryAttribute doubleAttribute(String str, Double d) {
        return new SentryAttribute(str, SentryAttributeType.DOUBLE, d);
    }

    public static SentryAttribute stringAttribute(String str, String str2) {
        return new SentryAttribute(str, SentryAttributeType.STRING, str2);
    }
}
