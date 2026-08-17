package io.sentry.rrweb;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.rrweb.RRWebEvent;
import io.sentry.util.Objects;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public final class RRWebMetaEvent extends RRWebEvent implements JsonUnknown, JsonSerializable {
    private Map<String, Object> dataUnknown;
    private int height;
    private String href;
    private Map<String, Object> unknown;
    private int width;

    public static final class JsonKeys {
        public static final String DATA = "data";
        public static final String HEIGHT = "height";
        public static final String HREF = "href";
        public static final String WIDTH = "width";
    }

    public RRWebMetaEvent() {
        super(RRWebEventType.Meta);
        this.href = "";
    }

    public String getHref() {
        return this.href;
    }

    public void setHref(String str) {
        this.href = str;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public Map<String, Object> getDataUnknown() {
        return this.dataUnknown;
    }

    public void setDataUnknown(Map<String, Object> map) {
        this.dataUnknown = map;
    }

    @Override // io.sentry.rrweb.RRWebEvent
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        RRWebMetaEvent rRWebMetaEvent = (RRWebMetaEvent) obj;
        return this.height == rRWebMetaEvent.height && this.width == rRWebMetaEvent.width && Objects.equals(this.href, rRWebMetaEvent.href);
    }

    @Override // io.sentry.rrweb.RRWebEvent
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.href, Integer.valueOf(this.height), Integer.valueOf(this.width));
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        new RRWebEvent.Serializer().serialize(this, objectWriter, iLogger);
        objectWriter.name("data");
        serializeData(objectWriter, iLogger);
        objectWriter.endObject();
    }

    private void serializeData(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name(JsonKeys.HREF).value(this.href);
        objectWriter.name("height").value(this.height);
        objectWriter.name("width").value(this.width);
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                Object obj = this.unknown.get(str);
                objectWriter.name(str);
                objectWriter.value(iLogger, obj);
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

    public static final class Deserializer implements JsonDeserializer<RRWebMetaEvent> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public RRWebMetaEvent deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            objectReader.beginObject();
            RRWebMetaEvent rRWebMetaEvent = new RRWebMetaEvent();
            RRWebEvent.Deserializer deserializer = new RRWebEvent.Deserializer();
            HashMap hashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String nextName = objectReader.nextName();
                nextName.hashCode();
                if (nextName.equals("data")) {
                    deserializeData(rRWebMetaEvent, objectReader, iLogger);
                } else if (!deserializer.deserializeValue(rRWebMetaEvent, nextName, objectReader, iLogger)) {
                    if (hashMap == null) {
                        hashMap = new HashMap();
                    }
                    objectReader.nextUnknown(iLogger, hashMap, nextName);
                }
            }
            rRWebMetaEvent.setUnknown(hashMap);
            objectReader.endObject();
            return rRWebMetaEvent;
        }

        private void deserializeData(RRWebMetaEvent rRWebMetaEvent, ObjectReader objectReader, ILogger iLogger) throws Exception {
            String nextName;
            objectReader.beginObject();
            ConcurrentHashMap concurrentHashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                nextName = objectReader.nextName();
                nextName.hashCode();
                switch (nextName) {
                    case "height":
                        Integer nextIntegerOrNull = objectReader.nextIntegerOrNull();
                        rRWebMetaEvent.height = nextIntegerOrNull != null ? nextIntegerOrNull.intValue() : 0;
                        break;
                    case "href":
                        String nextStringOrNull = objectReader.nextStringOrNull();
                        if (nextStringOrNull == null) {
                            nextStringOrNull = "";
                        }
                        rRWebMetaEvent.href = nextStringOrNull;
                        break;
                    case "width":
                        Integer nextIntegerOrNull2 = objectReader.nextIntegerOrNull();
                        rRWebMetaEvent.width = nextIntegerOrNull2 != null ? nextIntegerOrNull2.intValue() : 0;
                        break;
                    default:
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        objectReader.nextUnknown(iLogger, concurrentHashMap, nextName);
                        break;
                }
            }
            rRWebMetaEvent.setDataUnknown(concurrentHashMap);
            objectReader.endObject();
        }
    }
}
