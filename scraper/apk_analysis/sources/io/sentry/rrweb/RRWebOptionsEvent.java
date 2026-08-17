package io.sentry.rrweb;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.SentryOptions;
import io.sentry.SentryReplayOptions;
import io.sentry.protocol.SdkVersion;
import io.sentry.rrweb.RRWebEvent;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public final class RRWebOptionsEvent extends RRWebEvent implements JsonSerializable, JsonUnknown {
    public static final String EVENT_TAG = "options";
    private Map<String, Object> dataUnknown;
    private Map<String, Object> optionsPayload;
    private String tag;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String DATA = "data";
        public static final String PAYLOAD = "payload";
    }

    public RRWebOptionsEvent() {
        super(RRWebEventType.Custom);
        this.optionsPayload = new HashMap();
        this.tag = EVENT_TAG;
    }

    public RRWebOptionsEvent(SentryOptions sentryOptions) {
        this();
        SdkVersion sdkVersion = sentryOptions.getSdkVersion();
        if (sdkVersion != null) {
            this.optionsPayload.put("nativeSdkName", sdkVersion.getName());
            this.optionsPayload.put("nativeSdkVersion", sdkVersion.getVersion());
        }
        SentryReplayOptions sessionReplay = sentryOptions.getSessionReplay();
        this.optionsPayload.put("errorSampleRate", sessionReplay.getOnErrorSampleRate());
        this.optionsPayload.put("sessionSampleRate", sessionReplay.getSessionSampleRate());
        this.optionsPayload.put("maskAllImages", Boolean.valueOf(sessionReplay.getMaskViewClasses().contains(SentryReplayOptions.IMAGE_VIEW_CLASS_NAME)));
        this.optionsPayload.put("maskAllText", Boolean.valueOf(sessionReplay.getMaskViewClasses().contains(SentryReplayOptions.TEXT_VIEW_CLASS_NAME)));
        this.optionsPayload.put("quality", sessionReplay.getQuality().serializedName());
        this.optionsPayload.put("maskedViewClasses", sessionReplay.getMaskViewClasses());
        this.optionsPayload.put("unmaskedViewClasses", sessionReplay.getUnmaskViewClasses());
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public Map<String, Object> getOptionsPayload() {
        return this.optionsPayload;
    }

    public void setOptionsPayload(Map<String, Object> map) {
        this.optionsPayload = map;
    }

    public Map<String, Object> getDataUnknown() {
        return this.dataUnknown;
    }

    public void setDataUnknown(Map<String, Object> map) {
        this.dataUnknown = map;
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
        new RRWebEvent.Serializer().serialize(this, objectWriter, iLogger);
        objectWriter.name("data");
        serializeData(objectWriter, iLogger);
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

    private void serializeData(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name("tag").value(this.tag);
        objectWriter.name("payload");
        serializePayload(objectWriter, iLogger);
        Map<String, Object> map = this.dataUnknown;
        if (map != null) {
            for (String str : map.keySet()) {
                Object obj = this.dataUnknown.get(str);
                objectWriter.name(str);
                objectWriter.value(iLogger, obj);
            }
        }
        objectWriter.endObject();
    }

    private void serializePayload(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        Map<String, Object> map = this.optionsPayload;
        if (map != null) {
            for (String str : map.keySet()) {
                Object obj = this.optionsPayload.get(str);
                objectWriter.name(str);
                objectWriter.value(iLogger, obj);
            }
        }
        objectWriter.endObject();
    }

    public static final class Deserializer implements JsonDeserializer<RRWebOptionsEvent> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public RRWebOptionsEvent deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            objectReader.beginObject();
            RRWebOptionsEvent rRWebOptionsEvent = new RRWebOptionsEvent();
            RRWebEvent.Deserializer deserializer = new RRWebEvent.Deserializer();
            HashMap hashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String nextName = objectReader.nextName();
                nextName.hashCode();
                if (nextName.equals("data")) {
                    deserializeData(rRWebOptionsEvent, objectReader, iLogger);
                } else if (!deserializer.deserializeValue(rRWebOptionsEvent, nextName, objectReader, iLogger)) {
                    if (hashMap == null) {
                        hashMap = new HashMap();
                    }
                    objectReader.nextUnknown(iLogger, hashMap, nextName);
                }
            }
            rRWebOptionsEvent.setUnknown(hashMap);
            objectReader.endObject();
            return rRWebOptionsEvent;
        }

        private void deserializeData(RRWebOptionsEvent rRWebOptionsEvent, ObjectReader objectReader, ILogger iLogger) throws Exception {
            objectReader.beginObject();
            ConcurrentHashMap concurrentHashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String nextName = objectReader.nextName();
                nextName.hashCode();
                if (nextName.equals("payload")) {
                    deserializePayload(rRWebOptionsEvent, objectReader, iLogger);
                } else if (nextName.equals("tag")) {
                    String nextStringOrNull = objectReader.nextStringOrNull();
                    if (nextStringOrNull == null) {
                        nextStringOrNull = "";
                    }
                    rRWebOptionsEvent.tag = nextStringOrNull;
                } else {
                    if (concurrentHashMap == null) {
                        concurrentHashMap = new ConcurrentHashMap();
                    }
                    objectReader.nextUnknown(iLogger, concurrentHashMap, nextName);
                }
            }
            rRWebOptionsEvent.setDataUnknown(concurrentHashMap);
            objectReader.endObject();
        }

        private void deserializePayload(RRWebOptionsEvent rRWebOptionsEvent, ObjectReader objectReader, ILogger iLogger) throws Exception {
            objectReader.beginObject();
            HashMap hashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String nextName = objectReader.nextName();
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                objectReader.nextUnknown(iLogger, hashMap, nextName);
            }
            if (hashMap != null) {
                rRWebOptionsEvent.setOptionsPayload(hashMap);
            }
            objectReader.endObject();
        }
    }
}
