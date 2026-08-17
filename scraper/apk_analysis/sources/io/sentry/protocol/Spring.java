package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.util.CollectionUtils;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public final class Spring implements JsonUnknown, JsonSerializable {
    public static final String TYPE = "spring";
    private String[] activeProfiles;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String ACTIVE_PROFILES = "active_profiles";
    }

    public Spring() {
    }

    public Spring(Spring spring) {
        this.activeProfiles = spring.activeProfiles;
        this.unknown = CollectionUtils.newConcurrentHashMap(spring.unknown);
    }

    public String[] getActiveProfiles() {
        return this.activeProfiles;
    }

    public void setActiveProfiles(String[] strArr) {
        this.activeProfiles = strArr;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.activeProfiles, ((Spring) obj).activeProfiles);
    }

    public int hashCode() {
        return Arrays.hashCode(this.activeProfiles);
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        if (this.activeProfiles != null) {
            objectWriter.name(JsonKeys.ACTIVE_PROFILES).value(iLogger, this.activeProfiles);
        }
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

    public static final class Deserializer implements JsonDeserializer<Spring> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public Spring deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            objectReader.beginObject();
            Spring spring = new Spring();
            ConcurrentHashMap concurrentHashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String nextName = objectReader.nextName();
                nextName.hashCode();
                if (nextName.equals(JsonKeys.ACTIVE_PROFILES)) {
                    List list = (List) objectReader.nextObjectOrNull();
                    if (list != null) {
                        String[] strArr = new String[list.size()];
                        list.toArray(strArr);
                        spring.activeProfiles = strArr;
                    }
                } else {
                    if (concurrentHashMap == null) {
                        concurrentHashMap = new ConcurrentHashMap();
                    }
                    objectReader.nextUnknown(iLogger, concurrentHashMap, nextName);
                }
            }
            spring.setUnknown(concurrentHashMap);
            objectReader.endObject();
            return spring;
        }
    }
}
