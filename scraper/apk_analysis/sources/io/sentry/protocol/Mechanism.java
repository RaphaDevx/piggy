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
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class Mechanism implements JsonUnknown, JsonSerializable {
    private Map<String, Object> data;
    private String description;
    private Boolean exceptionGroup;
    private Integer exceptionId;
    private Boolean handled;
    private String helpLink;
    private Map<String, Object> meta;
    private Integer parentId;
    private Boolean synthetic;
    private final transient Thread thread;
    private String type;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String DATA = "data";
        public static final String DESCRIPTION = "description";
        public static final String EXCEPTION_ID = "exception_id";
        public static final String HANDLED = "handled";
        public static final String HELP_LINK = "help_link";
        public static final String IS_EXCEPTION_GROUP = "is_exception_group";
        public static final String META = "meta";
        public static final String PARENT_ID = "parent_id";
        public static final String SYNTHETIC = "synthetic";
        public static final String TYPE = "type";
    }

    public Mechanism() {
        this(null);
    }

    public Mechanism(Thread thread) {
        this.thread = thread;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getHelpLink() {
        return this.helpLink;
    }

    public void setHelpLink(String str) {
        this.helpLink = str;
    }

    public Boolean isHandled() {
        return this.handled;
    }

    public void setHandled(Boolean bool) {
        this.handled = bool;
    }

    public Map<String, Object> getMeta() {
        return this.meta;
    }

    public void setMeta(Map<String, Object> map) {
        this.meta = CollectionUtils.newHashMap(map);
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public void setData(Map<String, Object> map) {
        this.data = CollectionUtils.newHashMap(map);
    }

    Thread getThread() {
        return this.thread;
    }

    public Boolean getSynthetic() {
        return this.synthetic;
    }

    public void setSynthetic(Boolean bool) {
        this.synthetic = bool;
    }

    public Integer getExceptionId() {
        return this.exceptionId;
    }

    public void setExceptionId(Integer num) {
        this.exceptionId = num;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer num) {
        this.parentId = num;
    }

    public Boolean isExceptionGroup() {
        return this.exceptionGroup;
    }

    public void setExceptionGroup(Boolean bool) {
        this.exceptionGroup = bool;
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
        if (this.type != null) {
            objectWriter.name("type").value(this.type);
        }
        if (this.description != null) {
            objectWriter.name("description").value(this.description);
        }
        if (this.helpLink != null) {
            objectWriter.name(JsonKeys.HELP_LINK).value(this.helpLink);
        }
        if (this.handled != null) {
            objectWriter.name(JsonKeys.HANDLED).value(this.handled);
        }
        if (this.meta != null) {
            objectWriter.name(JsonKeys.META).value(iLogger, this.meta);
        }
        if (this.data != null) {
            objectWriter.name("data").value(iLogger, this.data);
        }
        if (this.synthetic != null) {
            objectWriter.name(JsonKeys.SYNTHETIC).value(this.synthetic);
        }
        if (this.exceptionId != null) {
            objectWriter.name(JsonKeys.EXCEPTION_ID).value(iLogger, this.exceptionId);
        }
        if (this.parentId != null) {
            objectWriter.name(JsonKeys.PARENT_ID).value(iLogger, this.parentId);
        }
        if (this.exceptionGroup != null) {
            objectWriter.name(JsonKeys.IS_EXCEPTION_GROUP).value(this.exceptionGroup);
        }
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                objectWriter.name(str).value(iLogger, this.unknown.get(str));
            }
        }
        objectWriter.endObject();
    }

    public static final class Deserializer implements JsonDeserializer<Mechanism> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public Mechanism deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            String nextName;
            Mechanism mechanism = new Mechanism();
            objectReader.beginObject();
            HashMap hashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                nextName = objectReader.nextName();
                nextName.hashCode();
                switch (nextName) {
                    case "description":
                        mechanism.description = objectReader.nextStringOrNull();
                        break;
                    case "exception_id":
                        mechanism.exceptionId = objectReader.nextIntegerOrNull();
                        break;
                    case "data":
                        mechanism.data = CollectionUtils.newConcurrentHashMap((Map) objectReader.nextObjectOrNull());
                        break;
                    case "meta":
                        mechanism.meta = CollectionUtils.newConcurrentHashMap((Map) objectReader.nextObjectOrNull());
                        break;
                    case "type":
                        mechanism.type = objectReader.nextStringOrNull();
                        break;
                    case "handled":
                        mechanism.handled = objectReader.nextBooleanOrNull();
                        break;
                    case "synthetic":
                        mechanism.synthetic = objectReader.nextBooleanOrNull();
                        break;
                    case "is_exception_group":
                        mechanism.exceptionGroup = objectReader.nextBooleanOrNull();
                        break;
                    case "help_link":
                        mechanism.helpLink = objectReader.nextStringOrNull();
                        break;
                    case "parent_id":
                        mechanism.parentId = objectReader.nextIntegerOrNull();
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
            mechanism.setUnknown(hashMap);
            return mechanism;
        }
    }
}
