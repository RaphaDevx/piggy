package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.SentryOptions;
import io.sentry.protocol.DebugImage;
import io.sentry.protocol.SdkInfo;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public final class DebugMeta implements JsonUnknown, JsonSerializable {
    private List<DebugImage> images;
    private SdkInfo sdkInfo;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String IMAGES = "images";
        public static final String SDK_INFO = "sdk_info";
    }

    public List<DebugImage> getImages() {
        return this.images;
    }

    public void setImages(List<DebugImage> list) {
        this.images = list != null ? new ArrayList(list) : null;
    }

    public SdkInfo getSdkInfo() {
        return this.sdkInfo;
    }

    public void setSdkInfo(SdkInfo sdkInfo) {
        this.sdkInfo = sdkInfo;
    }

    public static DebugMeta buildDebugMeta(DebugMeta debugMeta, SentryOptions sentryOptions) {
        ArrayList arrayList = new ArrayList();
        if (sentryOptions.getProguardUuid() != null) {
            DebugImage debugImage = new DebugImage();
            debugImage.setType(DebugImage.PROGUARD);
            debugImage.setUuid(sentryOptions.getProguardUuid());
            arrayList.add(debugImage);
        }
        for (String str : sentryOptions.getBundleIds()) {
            DebugImage debugImage2 = new DebugImage();
            debugImage2.setType(DebugImage.JVM);
            debugImage2.setDebugId(str);
            arrayList.add(debugImage2);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (debugMeta == null) {
            debugMeta = new DebugMeta();
        }
        if (debugMeta.getImages() == null) {
            debugMeta.setImages(arrayList);
        } else {
            debugMeta.getImages().addAll(arrayList);
        }
        return debugMeta;
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
        if (this.sdkInfo != null) {
            objectWriter.name(JsonKeys.SDK_INFO).value(iLogger, this.sdkInfo);
        }
        if (this.images != null) {
            objectWriter.name(JsonKeys.IMAGES).value(iLogger, this.images);
        }
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                objectWriter.name(str).value(iLogger, this.unknown.get(str));
            }
        }
        objectWriter.endObject();
    }

    public static final class Deserializer implements JsonDeserializer<DebugMeta> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public DebugMeta deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            DebugMeta debugMeta = new DebugMeta();
            objectReader.beginObject();
            HashMap hashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String nextName = objectReader.nextName();
                nextName.hashCode();
                if (nextName.equals(JsonKeys.IMAGES)) {
                    debugMeta.images = objectReader.nextListOrNull(iLogger, new DebugImage.Deserializer());
                } else if (nextName.equals(JsonKeys.SDK_INFO)) {
                    debugMeta.sdkInfo = (SdkInfo) objectReader.nextOrNull(iLogger, new SdkInfo.Deserializer());
                } else {
                    if (hashMap == null) {
                        hashMap = new HashMap();
                    }
                    objectReader.nextUnknown(iLogger, hashMap, nextName);
                }
            }
            objectReader.endObject();
            debugMeta.setUnknown(hashMap);
            return debugMeta;
        }
    }
}
