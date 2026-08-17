package io.sentry;

import io.sentry.protocol.SentryId;
import io.sentry.util.CollectionUtils;
import io.sentry.util.Objects;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public final class ProfileContext implements JsonUnknown, JsonSerializable {
    public static final String TYPE = "profile";
    private SentryId profilerId;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String PROFILER_ID = "profiler_id";
    }

    public ProfileContext() {
        this(SentryId.EMPTY_ID);
    }

    public ProfileContext(SentryId sentryId) {
        this.profilerId = sentryId;
    }

    public ProfileContext(ProfileContext profileContext) {
        this.profilerId = profileContext.profilerId;
        Map<String, Object> newConcurrentHashMap = CollectionUtils.newConcurrentHashMap(profileContext.unknown);
        if (newConcurrentHashMap != null) {
            this.unknown = newConcurrentHashMap;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ProfileContext) {
            return this.profilerId.equals(((ProfileContext) obj).profilerId);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.profilerId);
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name("profiler_id").value(iLogger, this.profilerId);
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                objectWriter.name(str).value(iLogger, this.unknown.get(str));
            }
        }
        objectWriter.endObject();
    }

    public SentryId getProfilerId() {
        return this.profilerId;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public static final class Deserializer implements JsonDeserializer<ProfileContext> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public ProfileContext deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            objectReader.beginObject();
            ProfileContext profileContext = new ProfileContext();
            ConcurrentHashMap concurrentHashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String nextName = objectReader.nextName();
                nextName.hashCode();
                if (nextName.equals("profiler_id")) {
                    SentryId sentryId = (SentryId) objectReader.nextOrNull(iLogger, new SentryId.Deserializer());
                    if (sentryId != null) {
                        profileContext.profilerId = sentryId;
                    }
                } else {
                    if (concurrentHashMap == null) {
                        concurrentHashMap = new ConcurrentHashMap();
                    }
                    objectReader.nextUnknown(iLogger, concurrentHashMap, nextName);
                }
            }
            profileContext.setUnknown(concurrentHashMap);
            objectReader.endObject();
            return profileContext;
        }
    }
}
