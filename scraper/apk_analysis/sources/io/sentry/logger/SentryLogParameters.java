package io.sentry.logger;

import io.sentry.SentryAttributes;
import io.sentry.SentryDate;
import io.sentry.SpanContext;

/* loaded from: classes2.dex */
public final class SentryLogParameters {
    private SentryAttributes attributes;
    private String origin = SpanContext.DEFAULT_ORIGIN;
    private SentryDate timestamp;

    public SentryDate getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(SentryDate sentryDate) {
        this.timestamp = sentryDate;
    }

    public SentryAttributes getAttributes() {
        return this.attributes;
    }

    public void setAttributes(SentryAttributes sentryAttributes) {
        this.attributes = sentryAttributes;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String str) {
        this.origin = str;
    }

    public static SentryLogParameters create(SentryDate sentryDate, SentryAttributes sentryAttributes) {
        SentryLogParameters sentryLogParameters = new SentryLogParameters();
        sentryLogParameters.setTimestamp(sentryDate);
        sentryLogParameters.setAttributes(sentryAttributes);
        return sentryLogParameters;
    }

    public static SentryLogParameters create(SentryAttributes sentryAttributes) {
        return create(null, sentryAttributes);
    }
}
