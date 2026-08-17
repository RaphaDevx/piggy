package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.SentryLevel;
import io.sentry.protocol.SentryId;
import io.sentry.util.CollectionUtils;
import io.sentry.util.Objects;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class Feedback implements JsonUnknown, JsonSerializable {
    public static final String TYPE = "feedback";
    private SentryId associatedEventId;
    private String contactEmail;
    private String message;
    private String name;
    private SentryId replayId;
    private Map<String, Object> unknown;
    private String url;

    public static final class JsonKeys {
        public static final String ASSOCIATED_EVENT_ID = "associated_event_id";
        public static final String CONTACT_EMAIL = "contact_email";
        public static final String MESSAGE = "message";
        public static final String NAME = "name";
        public static final String REPLAY_ID = "replay_id";
        public static final String URL = "url";
    }

    public Feedback(String str) {
        setMessage(str);
    }

    public Feedback(Feedback feedback) {
        this.message = feedback.message;
        this.contactEmail = feedback.contactEmail;
        this.name = feedback.name;
        this.associatedEventId = feedback.associatedEventId;
        this.replayId = feedback.replayId;
        this.url = feedback.url;
        this.unknown = CollectionUtils.newConcurrentHashMap(feedback.unknown);
    }

    public String getContactEmail() {
        return this.contactEmail;
    }

    public void setContactEmail(String str) {
        this.contactEmail = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public SentryId getAssociatedEventId() {
        return this.associatedEventId;
    }

    public void setAssociatedEventId(SentryId sentryId) {
        this.associatedEventId = sentryId;
    }

    public SentryId getReplayId() {
        return this.replayId;
    }

    public void setReplayId(SentryId sentryId) {
        this.replayId = sentryId;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        if (str.length() > 4096) {
            this.message = str.substring(0, 4096);
        } else {
            this.message = str;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Feedback)) {
            return false;
        }
        Feedback feedback = (Feedback) obj;
        return Objects.equals(this.message, feedback.message) && Objects.equals(this.contactEmail, feedback.contactEmail) && Objects.equals(this.name, feedback.name) && Objects.equals(this.associatedEventId, feedback.associatedEventId) && Objects.equals(this.replayId, feedback.replayId) && Objects.equals(this.url, feedback.url) && Objects.equals(this.unknown, feedback.unknown);
    }

    public String toString() {
        return "Feedback{message='" + this.message + "', contactEmail='" + this.contactEmail + "', name='" + this.name + "', associatedEventId=" + this.associatedEventId + ", replayId=" + this.replayId + ", url='" + this.url + "', unknown=" + this.unknown + '}';
    }

    public int hashCode() {
        return Objects.hash(this.message, this.contactEmail, this.name, this.associatedEventId, this.replayId, this.url, this.unknown);
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
        objectWriter.name("message").value(this.message);
        if (this.contactEmail != null) {
            objectWriter.name(JsonKeys.CONTACT_EMAIL).value(this.contactEmail);
        }
        if (this.name != null) {
            objectWriter.name("name").value(this.name);
        }
        if (this.associatedEventId != null) {
            objectWriter.name(JsonKeys.ASSOCIATED_EVENT_ID);
            this.associatedEventId.serialize(objectWriter, iLogger);
        }
        if (this.replayId != null) {
            objectWriter.name("replay_id");
            this.replayId.serialize(objectWriter, iLogger);
        }
        if (this.url != null) {
            objectWriter.name("url").value(this.url);
        }
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                objectWriter.name(str).value(iLogger, this.unknown.get(str));
            }
        }
        objectWriter.endObject();
    }

    public static final class Deserializer implements JsonDeserializer<Feedback> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public Feedback deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            String nextName;
            objectReader.beginObject();
            String str = null;
            String str2 = null;
            String str3 = null;
            SentryId sentryId = null;
            SentryId sentryId2 = null;
            String str4 = null;
            HashMap hashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                nextName = objectReader.nextName();
                nextName.hashCode();
                switch (nextName) {
                    case "associated_event_id":
                        sentryId = new SentryId.Deserializer().deserialize(objectReader, iLogger);
                        break;
                    case "replay_id":
                        sentryId2 = new SentryId.Deserializer().deserialize(objectReader, iLogger);
                        break;
                    case "url":
                        str4 = objectReader.nextStringOrNull();
                        break;
                    case "name":
                        str3 = objectReader.nextStringOrNull();
                        break;
                    case "contact_email":
                        str2 = objectReader.nextStringOrNull();
                        break;
                    case "message":
                        str = objectReader.nextStringOrNull();
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
                IllegalStateException illegalStateException = new IllegalStateException("Missing required field \"message\"");
                iLogger.log(SentryLevel.ERROR, "Missing required field \"message\"", illegalStateException);
                throw illegalStateException;
            }
            Feedback feedback = new Feedback(str);
            feedback.contactEmail = str2;
            feedback.name = str3;
            feedback.associatedEventId = sentryId;
            feedback.replayId = sentryId2;
            feedback.url = str4;
            feedback.unknown = hashMap;
            return feedback;
        }
    }
}
