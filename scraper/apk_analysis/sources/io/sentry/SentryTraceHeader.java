package io.sentry;

import io.sentry.exception.InvalidSentryTraceHeaderException;
import io.sentry.protocol.SentryId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public final class SentryTraceHeader {
    private static final Pattern SENTRY_TRACEPARENT_HEADER_REGEX = Pattern.compile("^[ \\t]*([0-9a-f]{32})-([0-9a-f]{16})(-[01])?[ \\t]*$", 2);
    public static final String SENTRY_TRACE_HEADER = "sentry-trace";
    private final Boolean sampled;
    private final SpanId spanId;
    private final SentryId traceId;

    public SentryTraceHeader(SentryId sentryId, SpanId spanId, Boolean bool) {
        this.traceId = sentryId;
        this.spanId = spanId;
        this.sampled = bool;
    }

    public SentryTraceHeader(String str) throws InvalidSentryTraceHeaderException {
        Matcher matcher = SENTRY_TRACEPARENT_HEADER_REGEX.matcher(str);
        if (!matcher.matches()) {
            throw new InvalidSentryTraceHeaderException(str);
        }
        this.traceId = new SentryId(matcher.group(1));
        this.spanId = new SpanId(matcher.group(2));
        String group = matcher.group(3);
        this.sampled = group == null ? null : Boolean.valueOf("1".equals(group.substring(1)));
    }

    public String getName() {
        return SENTRY_TRACE_HEADER;
    }

    public String getValue() {
        Boolean bool = this.sampled;
        if (bool != null) {
            return String.format("%s-%s-%s", this.traceId, this.spanId, bool.booleanValue() ? "1" : "0");
        }
        return String.format("%s-%s", this.traceId, this.spanId);
    }

    public SentryId getTraceId() {
        return this.traceId;
    }

    public SpanId getSpanId() {
        return this.spanId;
    }

    public Boolean isSampled() {
        return this.sampled;
    }
}
