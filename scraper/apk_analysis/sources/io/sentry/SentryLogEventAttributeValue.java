package io.sentry;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class SentryLogEventAttributeValue implements JsonUnknown, JsonSerializable {
    private String type;
    private Map<String, Object> unknown;
    private Object value;

    public static final class JsonKeys {
        public static final String TYPE = "type";
        public static final String VALUE = "value";
    }

    public SentryLogEventAttributeValue(String str, Object obj) {
        this.type = str;
        if (obj != null && str.equals(TypedValues.Custom.S_STRING)) {
            this.value = obj.toString();
        } else {
            this.value = obj;
        }
    }

    public SentryLogEventAttributeValue(SentryAttributeType sentryAttributeType, Object obj) {
        this(sentryAttributeType.apiName(), obj);
    }

    public String getType() {
        return this.type;
    }

    public Object getValue() {
        return this.value;
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name("type").value(iLogger, this.type);
        objectWriter.name("value").value(iLogger, this.value);
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

    public static final class Deserializer implements JsonDeserializer<SentryLogEventAttributeValue> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public SentryLogEventAttributeValue deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            objectReader.beginObject();
            String str = null;
            Object obj = null;
            HashMap hashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String nextName = objectReader.nextName();
                nextName.hashCode();
                if (nextName.equals("type")) {
                    str = objectReader.nextStringOrNull();
                } else if (nextName.equals("value")) {
                    obj = objectReader.nextObjectOrNull();
                } else {
                    if (hashMap == null) {
                        hashMap = new HashMap();
                    }
                    objectReader.nextUnknown(iLogger, hashMap, nextName);
                }
            }
            objectReader.endObject();
            if (str == null) {
                IllegalStateException illegalStateException = new IllegalStateException("Missing required field \"type\"");
                iLogger.log(SentryLevel.ERROR, "Missing required field \"type\"", illegalStateException);
                throw illegalStateException;
            }
            SentryLogEventAttributeValue sentryLogEventAttributeValue = new SentryLogEventAttributeValue(str, obj);
            sentryLogEventAttributeValue.setUnknown(hashMap);
            return sentryLogEventAttributeValue;
        }
    }
}
