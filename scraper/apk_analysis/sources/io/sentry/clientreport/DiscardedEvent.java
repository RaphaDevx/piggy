package io.sentry.clientreport;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.SentryLevel;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class DiscardedEvent implements JsonUnknown, JsonSerializable {
    private final String category;
    private final Long quantity;
    private final String reason;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String CATEGORY = "category";
        public static final String QUANTITY = "quantity";
        public static final String REASON = "reason";
    }

    public DiscardedEvent(String str, String str2, Long l) {
        this.reason = str;
        this.category = str2;
        this.quantity = l;
    }

    public String getReason() {
        return this.reason;
    }

    public String getCategory() {
        return this.category;
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public String toString() {
        return "DiscardedEvent{reason='" + this.reason + "', category='" + this.category + "', quantity=" + this.quantity + '}';
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name(JsonKeys.REASON).value(this.reason);
        objectWriter.name("category").value(this.category);
        objectWriter.name("quantity").value(this.quantity);
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                objectWriter.name(str).value(iLogger, this.unknown.get(str));
            }
        }
        objectWriter.endObject();
    }

    public static final class Deserializer implements JsonDeserializer<DiscardedEvent> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public DiscardedEvent deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            String nextName;
            objectReader.beginObject();
            String str = null;
            String str2 = null;
            Long l = null;
            HashMap hashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                nextName = objectReader.nextName();
                nextName.hashCode();
                switch (nextName) {
                    case "quantity":
                        l = objectReader.nextLongOrNull();
                        break;
                    case "reason":
                        str = objectReader.nextStringOrNull();
                        break;
                    case "category":
                        str2 = objectReader.nextStringOrNull();
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
            if (str == null) {
                throw missingRequiredFieldException(JsonKeys.REASON, iLogger);
            }
            if (str2 == null) {
                throw missingRequiredFieldException("category", iLogger);
            }
            if (l == null) {
                throw missingRequiredFieldException("quantity", iLogger);
            }
            DiscardedEvent discardedEvent = new DiscardedEvent(str, str2, l);
            discardedEvent.setUnknown(hashMap);
            return discardedEvent;
        }

        private Exception missingRequiredFieldException(String str, ILogger iLogger) {
            String str2 = "Missing required field \"" + str + "\"";
            IllegalStateException illegalStateException = new IllegalStateException(str2);
            iLogger.log(SentryLevel.ERROR, str2, illegalStateException);
            return illegalStateException;
        }
    }
}
