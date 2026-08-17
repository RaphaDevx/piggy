package io.sentry.protocol.profiling;

import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.protocol.SentryStackFrame;
import io.sentry.protocol.profiling.SentrySample;
import io.sentry.protocol.profiling.SentryThreadMetadata;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public final class SentryProfile implements JsonUnknown, JsonSerializable {
    private Map<String, Object> unknown;
    private List<SentrySample> samples = new ArrayList();
    private List<List<Integer>> stacks = new ArrayList();
    private List<SentryStackFrame> frames = new ArrayList();
    private Map<String, SentryThreadMetadata> threadMetadata = new HashMap();

    public static final class JsonKeys {
        public static final String FRAMES = "frames";
        public static final String SAMPLES = "samples";
        public static final String STACKS = "stacks";
        public static final String THREAD_METADATA = "thread_metadata";
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name(JsonKeys.SAMPLES).value(iLogger, this.samples);
        objectWriter.name(JsonKeys.STACKS).value(iLogger, this.stacks);
        objectWriter.name("frames").value(iLogger, this.frames);
        objectWriter.name(JsonKeys.THREAD_METADATA).value(iLogger, this.threadMetadata);
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                objectWriter.name(str).value(iLogger, this.unknown.get(str));
            }
        }
        objectWriter.endObject();
    }

    public List<SentrySample> getSamples() {
        return this.samples;
    }

    public void setSamples(List<SentrySample> list) {
        this.samples = list;
    }

    public List<List<Integer>> getStacks() {
        return this.stacks;
    }

    public void setStacks(List<List<Integer>> list) {
        this.stacks = list;
    }

    public List<SentryStackFrame> getFrames() {
        return this.frames;
    }

    public void setFrames(List<SentryStackFrame> list) {
        this.frames = list;
    }

    public Map<String, SentryThreadMetadata> getThreadMetadata() {
        return this.threadMetadata;
    }

    public void setThreadMetadata(Map<String, SentryThreadMetadata> map) {
        this.threadMetadata = map;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public static final class Deserializer implements JsonDeserializer<SentryProfile> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public SentryProfile deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            String nextName;
            objectReader.beginObject();
            SentryProfile sentryProfile = new SentryProfile();
            ConcurrentHashMap concurrentHashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                nextName = objectReader.nextName();
                nextName.hashCode();
                switch (nextName) {
                    case "frames":
                        List nextListOrNull = objectReader.nextListOrNull(iLogger, new SentryStackFrame.Deserializer());
                        if (nextListOrNull == null) {
                            break;
                        } else {
                            sentryProfile.frames = nextListOrNull;
                            break;
                        }
                    case "stacks":
                        List list = (List) objectReader.nextOrNull(iLogger, new NestedIntegerListDeserializer());
                        if (list == null) {
                            break;
                        } else {
                            sentryProfile.stacks = list;
                            break;
                        }
                    case "samples":
                        List nextListOrNull2 = objectReader.nextListOrNull(iLogger, new SentrySample.Deserializer());
                        if (nextListOrNull2 == null) {
                            break;
                        } else {
                            sentryProfile.samples = nextListOrNull2;
                            break;
                        }
                    case "thread_metadata":
                        Map nextMapOrNull = objectReader.nextMapOrNull(iLogger, new SentryThreadMetadata.Deserializer());
                        if (nextMapOrNull == null) {
                            break;
                        } else {
                            sentryProfile.threadMetadata = nextMapOrNull;
                            break;
                        }
                    default:
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        objectReader.nextUnknown(iLogger, concurrentHashMap, nextName);
                        break;
                }
            }
            sentryProfile.setUnknown(concurrentHashMap);
            objectReader.endObject();
            return sentryProfile;
        }
    }

    private static final class NestedIntegerListDeserializer implements JsonDeserializer<List<List<Integer>>> {
        private NestedIntegerListDeserializer() {
        }

        @Override // io.sentry.JsonDeserializer
        public List<List<Integer>> deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            ArrayList arrayList = new ArrayList();
            objectReader.beginArray();
            while (objectReader.hasNext()) {
                ArrayList arrayList2 = new ArrayList();
                objectReader.beginArray();
                while (objectReader.hasNext()) {
                    arrayList2.add(Integer.valueOf(objectReader.nextInt()));
                }
                objectReader.endArray();
                arrayList.add(arrayList2);
            }
            objectReader.endArray();
            return arrayList;
        }
    }
}
