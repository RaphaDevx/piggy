package io.sentry;

import io.sentry.SentryLogEventAttributeValue;
import io.sentry.SentryLogLevel;
import io.sentry.protocol.SentryId;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class SentryLogEvent implements JsonUnknown, JsonSerializable {
    private Map<String, SentryLogEventAttributeValue> attributes;
    private String body;
    private SentryLogLevel level;
    private Integer severityNumber;
    private Double timestamp;
    private SentryId traceId;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String ATTRIBUTES = "attributes";
        public static final String BODY = "body";
        public static final String LEVEL = "level";
        public static final String SEVERITY_NUMBER = "severity_number";
        public static final String TIMESTAMP = "timestamp";
        public static final String TRACE_ID = "trace_id";
    }

    public SentryLogEvent(SentryId sentryId, SentryDate sentryDate, String str, SentryLogLevel sentryLogLevel) {
        this(sentryId, Double.valueOf(DateUtils.nanosToSeconds(sentryDate.nanoTimestamp())), str, sentryLogLevel);
    }

    public SentryLogEvent(SentryId sentryId, Double d, String str, SentryLogLevel sentryLogLevel) {
        this.traceId = sentryId;
        this.timestamp = d;
        this.body = str;
        this.level = sentryLogLevel;
    }

    public Double getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Double d) {
        this.timestamp = d;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String str) {
        this.body = str;
    }

    public SentryLogLevel getLevel() {
        return this.level;
    }

    public void setLevel(SentryLogLevel sentryLogLevel) {
        this.level = sentryLogLevel;
    }

    public Map<String, SentryLogEventAttributeValue> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<String, SentryLogEventAttributeValue> map) {
        this.attributes = map;
    }

    public void setAttribute(String str, SentryLogEventAttributeValue sentryLogEventAttributeValue) {
        if (str == null) {
            return;
        }
        if (this.attributes == null) {
            this.attributes = new HashMap();
        }
        this.attributes.put(str, sentryLogEventAttributeValue);
    }

    public Integer getSeverityNumber() {
        return this.severityNumber;
    }

    public void setSeverityNumber(Integer num) {
        this.severityNumber = num;
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name("timestamp").value(iLogger, DateUtils.doubleToBigDecimal(this.timestamp));
        objectWriter.name("trace_id").value(iLogger, this.traceId);
        objectWriter.name(JsonKeys.BODY).value(this.body);
        objectWriter.name("level").value(iLogger, this.level);
        if (this.severityNumber != null) {
            objectWriter.name(JsonKeys.SEVERITY_NUMBER).value(iLogger, this.severityNumber);
        }
        if (this.attributes != null) {
            objectWriter.name("attributes").value(iLogger, this.attributes);
        }
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                objectWriter.name(str).value(iLogger, this.unknown.get(str));
            }
        }
        objectWriter.endObject();
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public static final class Deserializer implements JsonDeserializer<SentryLogEvent> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public SentryLogEvent deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            String nextName;
            objectReader.beginObject();
            SentryId sentryId = null;
            Double d = null;
            String str = null;
            HashMap hashMap = null;
            SentryLogLevel sentryLogLevel = null;
            Map<String, SentryLogEventAttributeValue> map = null;
            Integer num = null;
            while (objectReader.peek() == JsonToken.NAME) {
                nextName = objectReader.nextName();
                nextName.hashCode();
                switch (nextName) {
                    case "severity_number":
                        num = objectReader.nextIntegerOrNull();
                        break;
                    case "body":
                        str = objectReader.nextStringOrNull();
                        break;
                    case "timestamp":
                        d = objectReader.nextDoubleOrNull();
                        break;
                    case "level":
                        sentryLogLevel = (SentryLogLevel) objectReader.nextOrNull(iLogger, new SentryLogLevel.Deserializer());
                        break;
                    case "attributes":
                        map = objectReader.nextMapOrNull(iLogger, new SentryLogEventAttributeValue.Deserializer());
                        break;
                    case "trace_id":
                        sentryId = (SentryId) objectReader.nextOrNull(iLogger, new SentryId.Deserializer());
                        break;
                    default:
                        if (hashMap == null) {
                            hashMap = new HashMap();
                        }
                        objectReader.nextUnknown(iLogger, hashMap, nextName);
                        break;
                }
            }
            objectReader.endObject();
            if (sentryId == null) {
                IllegalStateException illegalStateException = new IllegalStateException("Missing required field \"trace_id\"");
                iLogger.log(SentryLevel.ERROR, "Missing required field \"trace_id\"", illegalStateException);
                throw illegalStateException;
            }
            if (d == null) {
                IllegalStateException illegalStateException2 = new IllegalStateException("Missing required field \"timestamp\"");
                iLogger.log(SentryLevel.ERROR, "Missing required field \"timestamp\"", illegalStateException2);
                throw illegalStateException2;
            }
            if (str == null) {
                IllegalStateException illegalStateException3 = new IllegalStateException("Missing required field \"body\"");
                iLogger.log(SentryLevel.ERROR, "Missing required field \"body\"", illegalStateException3);
                throw illegalStateException3;
            }
            if (sentryLogLevel == null) {
                IllegalStateException illegalStateException4 = new IllegalStateException("Missing required field \"level\"");
                iLogger.log(SentryLevel.ERROR, "Missing required field \"level\"", illegalStateException4);
                throw illegalStateException4;
            }
            SentryLogEvent sentryLogEvent = new SentryLogEvent(sentryId, d, str, sentryLogLevel);
            sentryLogEvent.setAttributes(map);
            sentryLogEvent.setSeverityNumber(num);
            sentryLogEvent.setUnknown(hashMap);
            return sentryLogEvent;
        }
    }
}
