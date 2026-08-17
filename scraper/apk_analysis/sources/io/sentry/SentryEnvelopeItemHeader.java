package io.sentry;

import io.sentry.SentryItemType;
import io.sentry.util.Objects;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
public final class SentryEnvelopeItemHeader implements JsonSerializable, JsonUnknown {
    private final String attachmentType;
    private final String contentType;
    private final String fileName;
    private final Callable<Integer> getLength;
    private final Integer itemCount;
    private final int length;
    private final String platform;
    private final SentryItemType type;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String ATTACHMENT_TYPE = "attachment_type";
        public static final String CONTENT_TYPE = "content_type";
        public static final String FILENAME = "filename";
        public static final String ITEM_COUNT = "item_count";
        public static final String LENGTH = "length";
        public static final String PLATFORM = "platform";
        public static final String TYPE = "type";
    }

    public SentryItemType getType() {
        return this.type;
    }

    public int getLength() {
        Callable<Integer> callable = this.getLength;
        if (callable != null) {
            try {
                return callable.call().intValue();
            } catch (Throwable unused) {
                return -1;
            }
        }
        return this.length;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getPlatform() {
        return this.platform;
    }

    public SentryEnvelopeItemHeader(SentryItemType sentryItemType, int i, String str, String str2, String str3, String str4, Integer num) {
        this.type = (SentryItemType) Objects.requireNonNull(sentryItemType, "type is required");
        this.contentType = str;
        this.length = i;
        this.fileName = str2;
        this.getLength = null;
        this.attachmentType = str3;
        this.platform = str4;
        this.itemCount = num;
    }

    SentryEnvelopeItemHeader(SentryItemType sentryItemType, Callable<Integer> callable, String str, String str2, String str3) {
        this(sentryItemType, callable, str, str2, str3, (String) null, (Integer) null);
    }

    SentryEnvelopeItemHeader(SentryItemType sentryItemType, Callable<Integer> callable, String str, String str2, String str3, String str4, Integer num) {
        this.type = (SentryItemType) Objects.requireNonNull(sentryItemType, "type is required");
        this.contentType = str;
        this.length = -1;
        this.fileName = str2;
        this.getLength = callable;
        this.attachmentType = str3;
        this.platform = str4;
        this.itemCount = num;
    }

    SentryEnvelopeItemHeader(SentryItemType sentryItemType, Callable<Integer> callable, String str, String str2) {
        this(sentryItemType, callable, str, str2, null);
    }

    public String getAttachmentType() {
        return this.attachmentType;
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        if (this.contentType != null) {
            objectWriter.name("content_type").value(this.contentType);
        }
        if (this.fileName != null) {
            objectWriter.name("filename").value(this.fileName);
        }
        objectWriter.name("type").value(iLogger, this.type);
        if (this.attachmentType != null) {
            objectWriter.name(JsonKeys.ATTACHMENT_TYPE).value(this.attachmentType);
        }
        if (this.platform != null) {
            objectWriter.name("platform").value(this.platform);
        }
        if (this.itemCount != null) {
            objectWriter.name(JsonKeys.ITEM_COUNT).value(this.itemCount);
        }
        objectWriter.name(JsonKeys.LENGTH).value(getLength());
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

    public static final class Deserializer implements JsonDeserializer<SentryEnvelopeItemHeader> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public SentryEnvelopeItemHeader deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            String nextName;
            objectReader.beginObject();
            HashMap hashMap = null;
            SentryItemType sentryItemType = null;
            String str = null;
            String str2 = null;
            String str3 = null;
            String str4 = null;
            Integer num = null;
            int i = 0;
            while (objectReader.peek() == JsonToken.NAME) {
                nextName = objectReader.nextName();
                nextName.hashCode();
                switch (nextName) {
                    case "item_count":
                        num = objectReader.nextIntegerOrNull();
                        break;
                    case "length":
                        i = objectReader.nextInt();
                        break;
                    case "filename":
                        str2 = objectReader.nextStringOrNull();
                        break;
                    case "attachment_type":
                        str3 = objectReader.nextStringOrNull();
                        break;
                    case "type":
                        sentryItemType = (SentryItemType) objectReader.nextOrNull(iLogger, new SentryItemType.Deserializer());
                        break;
                    case "content_type":
                        str = objectReader.nextStringOrNull();
                        break;
                    case "platform":
                        str4 = objectReader.nextStringOrNull();
                        break;
                    default:
                        if (hashMap == null) {
                            hashMap = new HashMap();
                        }
                        objectReader.nextUnknown(iLogger, hashMap, nextName);
                        break;
                }
            }
            if (sentryItemType == null) {
                throw missingRequiredFieldException("type", iLogger);
            }
            SentryEnvelopeItemHeader sentryEnvelopeItemHeader = new SentryEnvelopeItemHeader(sentryItemType, i, str, str2, str3, str4, num);
            sentryEnvelopeItemHeader.setUnknown(hashMap);
            objectReader.endObject();
            return sentryEnvelopeItemHeader;
        }

        private Exception missingRequiredFieldException(String str, ILogger iLogger) {
            String str2 = "Missing required field \"" + str + "\"";
            IllegalStateException illegalStateException = new IllegalStateException(str2);
            iLogger.log(SentryLevel.ERROR, str2, illegalStateException);
            return illegalStateException;
        }
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }
}
