package io.sentry.protocol.profiling;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class SentrySample implements JsonUnknown, JsonSerializable {
    private int stackId;
    private String threadId;
    private double timestamp;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String STACK_ID = "stack_id";
        public static final String THREAD_ID = "thread_id";
        public static final String TIMESTAMP = "timestamp";
    }

    public double getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(double d) {
        this.timestamp = d;
    }

    public int getStackId() {
        return this.stackId;
    }

    public void setStackId(int i) {
        this.stackId = i;
    }

    public String getThreadId() {
        return this.threadId;
    }

    public void setThreadId(String str) {
        this.threadId = str;
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name("timestamp").value(iLogger, doubleToBigDecimal(Double.valueOf(this.timestamp)));
        objectWriter.name(JsonKeys.STACK_ID).value(iLogger, Integer.valueOf(this.stackId));
        if (this.threadId != null) {
            objectWriter.name("thread_id").value(iLogger, this.threadId);
        }
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                objectWriter.name(str).value(iLogger, this.unknown.get(str));
            }
        }
        objectWriter.endObject();
    }

    private BigDecimal doubleToBigDecimal(Double d) {
        return BigDecimal.valueOf(d.doubleValue()).setScale(6, RoundingMode.DOWN);
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public static final class Deserializer implements JsonDeserializer<SentrySample> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public SentrySample deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            String nextName;
            objectReader.beginObject();
            SentrySample sentrySample = new SentrySample();
            HashMap hashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                nextName = objectReader.nextName();
                nextName.hashCode();
                switch (nextName) {
                    case "thread_id":
                        sentrySample.threadId = objectReader.nextStringOrNull();
                        break;
                    case "timestamp":
                        sentrySample.timestamp = objectReader.nextDouble();
                        break;
                    case "stack_id":
                        sentrySample.stackId = objectReader.nextInt();
                        break;
                    default:
                        if (hashMap == null) {
                            hashMap = new HashMap();
                        }
                        objectReader.nextUnknown(iLogger, hashMap, nextName);
                        break;
                }
            }
            sentrySample.setUnknown(hashMap);
            objectReader.endObject();
            return sentrySample;
        }
    }
}
