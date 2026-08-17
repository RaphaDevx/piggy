package io.sentry;

import io.sentry.SpanId;
import io.sentry.SpanStatus;
import io.sentry.protocol.SentryId;
import io.sentry.util.CollectionUtils;
import io.sentry.util.Objects;
import io.sentry.util.thread.IThreadChecker;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public class SpanContext implements JsonUnknown, JsonSerializable {
    public static final String DEFAULT_ORIGIN = "manual";
    public static final String TYPE = "trace";
    protected Baggage baggage;
    protected Map<String, Object> data;
    protected String description;
    private Instrumenter instrumenter;
    protected String op;
    protected String origin;
    private SpanId parentSpanId;
    private SentryId profilerId;
    private transient TracesSamplingDecision samplingDecision;
    private final SpanId spanId;
    protected SpanStatus status;
    protected Map<String, String> tags;
    private final SentryId traceId;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String DATA = "data";
        public static final String DESCRIPTION = "description";
        public static final String OP = "op";
        public static final String ORIGIN = "origin";
        public static final String PARENT_SPAN_ID = "parent_span_id";
        public static final String SPAN_ID = "span_id";
        public static final String STATUS = "status";
        public static final String TAGS = "tags";
        public static final String TRACE_ID = "trace_id";
    }

    public SpanContext(String str, TracesSamplingDecision tracesSamplingDecision) {
        this(new SentryId(), new SpanId(), str, null, tracesSamplingDecision);
    }

    public SpanContext(String str) {
        this(new SentryId(), new SpanId(), str, null, null);
    }

    public SpanContext(SentryId sentryId, SpanId spanId, String str, SpanId spanId2, TracesSamplingDecision tracesSamplingDecision) {
        this(sentryId, spanId, spanId2, str, null, tracesSamplingDecision, null, DEFAULT_ORIGIN);
    }

    public SpanContext(SentryId sentryId, SpanId spanId, SpanId spanId2, String str, String str2, TracesSamplingDecision tracesSamplingDecision, SpanStatus spanStatus, String str3) {
        this.tags = new ConcurrentHashMap();
        this.origin = DEFAULT_ORIGIN;
        this.data = new ConcurrentHashMap();
        this.instrumenter = Instrumenter.SENTRY;
        this.profilerId = SentryId.EMPTY_ID;
        this.traceId = (SentryId) Objects.requireNonNull(sentryId, "traceId is required");
        this.spanId = (SpanId) Objects.requireNonNull(spanId, "spanId is required");
        this.op = (String) Objects.requireNonNull(str, "operation is required");
        this.parentSpanId = spanId2;
        this.description = str2;
        this.status = spanStatus;
        this.origin = str3;
        setSamplingDecision(tracesSamplingDecision);
        IThreadChecker threadChecker = ScopesAdapter.getInstance().getOptions().getThreadChecker();
        this.data.put(SpanDataConvention.THREAD_ID, String.valueOf(threadChecker.currentThreadSystemId()));
        this.data.put(SpanDataConvention.THREAD_NAME, threadChecker.getCurrentThreadName());
    }

    public SpanContext(SpanContext spanContext) {
        this.tags = new ConcurrentHashMap();
        this.origin = DEFAULT_ORIGIN;
        this.data = new ConcurrentHashMap();
        this.instrumenter = Instrumenter.SENTRY;
        this.profilerId = SentryId.EMPTY_ID;
        this.traceId = spanContext.traceId;
        this.spanId = spanContext.spanId;
        this.parentSpanId = spanContext.parentSpanId;
        setSamplingDecision(spanContext.samplingDecision);
        this.op = spanContext.op;
        this.description = spanContext.description;
        this.status = spanContext.status;
        Map<String, String> newConcurrentHashMap = CollectionUtils.newConcurrentHashMap(spanContext.tags);
        if (newConcurrentHashMap != null) {
            this.tags = newConcurrentHashMap;
        }
        Map<String, Object> newConcurrentHashMap2 = CollectionUtils.newConcurrentHashMap(spanContext.unknown);
        if (newConcurrentHashMap2 != null) {
            this.unknown = newConcurrentHashMap2;
        }
        this.baggage = spanContext.baggage;
        Map<String, Object> newConcurrentHashMap3 = CollectionUtils.newConcurrentHashMap(spanContext.data);
        if (newConcurrentHashMap3 != null) {
            this.data = newConcurrentHashMap3;
        }
    }

    public void setOperation(String str) {
        this.op = (String) Objects.requireNonNull(str, "operation is required");
    }

    public void setTag(String str, String str2) {
        if (str == null) {
            return;
        }
        if (str2 == null) {
            this.tags.remove(str);
        } else {
            this.tags.put(str, str2);
        }
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setStatus(SpanStatus spanStatus) {
        this.status = spanStatus;
    }

    public SentryId getTraceId() {
        return this.traceId;
    }

    public SpanId getSpanId() {
        return this.spanId;
    }

    public SpanId getParentSpanId() {
        return this.parentSpanId;
    }

    public String getOperation() {
        return this.op;
    }

    public String getDescription() {
        return this.description;
    }

    public SpanStatus getStatus() {
        return this.status;
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    public TracesSamplingDecision getSamplingDecision() {
        return this.samplingDecision;
    }

    public Boolean getSampled() {
        TracesSamplingDecision tracesSamplingDecision = this.samplingDecision;
        if (tracesSamplingDecision == null) {
            return null;
        }
        return tracesSamplingDecision.getSampled();
    }

    public Boolean getProfileSampled() {
        TracesSamplingDecision tracesSamplingDecision = this.samplingDecision;
        if (tracesSamplingDecision == null) {
            return null;
        }
        return tracesSamplingDecision.getProfileSampled();
    }

    public void setSampled(Boolean bool) {
        if (bool == null) {
            setSamplingDecision(null);
        } else {
            setSamplingDecision(new TracesSamplingDecision(bool));
        }
    }

    public void setSampled(Boolean bool, Boolean bool2) {
        if (bool == null) {
            setSamplingDecision(null);
        } else if (bool2 == null) {
            setSamplingDecision(new TracesSamplingDecision(bool));
        } else {
            setSamplingDecision(new TracesSamplingDecision(bool, null, bool2, null));
        }
    }

    public void setSamplingDecision(TracesSamplingDecision tracesSamplingDecision) {
        this.samplingDecision = tracesSamplingDecision;
        Baggage baggage = this.baggage;
        if (baggage != null) {
            baggage.setValuesFromSamplingDecision(tracesSamplingDecision);
        }
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String str) {
        this.origin = str;
    }

    public Instrumenter getInstrumenter() {
        return this.instrumenter;
    }

    public void setInstrumenter(Instrumenter instrumenter) {
        this.instrumenter = instrumenter;
    }

    public Baggage getBaggage() {
        return this.baggage;
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public void setData(String str, Object obj) {
        if (str == null) {
            return;
        }
        if (obj == null) {
            this.data.remove(str);
        } else {
            this.data.put(str, obj);
        }
    }

    public SpanContext copyForChild(String str, SpanId spanId, SpanId spanId2) {
        SentryId sentryId = this.traceId;
        if (spanId2 == null) {
            spanId2 = new SpanId();
        }
        return new SpanContext(sentryId, spanId2, spanId, str, null, this.samplingDecision, null, DEFAULT_ORIGIN);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SpanContext)) {
            return false;
        }
        SpanContext spanContext = (SpanContext) obj;
        return this.traceId.equals(spanContext.traceId) && this.spanId.equals(spanContext.spanId) && Objects.equals(this.parentSpanId, spanContext.parentSpanId) && this.op.equals(spanContext.op) && Objects.equals(this.description, spanContext.description) && getStatus() == spanContext.getStatus();
    }

    public int hashCode() {
        return Objects.hash(this.traceId, this.spanId, this.parentSpanId, this.op, this.description, getStatus());
    }

    public SentryId getProfilerId() {
        return this.profilerId;
    }

    public void setProfilerId(SentryId sentryId) {
        this.profilerId = sentryId;
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name("trace_id");
        this.traceId.serialize(objectWriter, iLogger);
        objectWriter.name("span_id");
        this.spanId.serialize(objectWriter, iLogger);
        if (this.parentSpanId != null) {
            objectWriter.name("parent_span_id");
            this.parentSpanId.serialize(objectWriter, iLogger);
        }
        objectWriter.name("op").value(this.op);
        if (this.description != null) {
            objectWriter.name("description").value(this.description);
        }
        if (getStatus() != null) {
            objectWriter.name("status").value(iLogger, getStatus());
        }
        if (this.origin != null) {
            objectWriter.name("origin").value(iLogger, this.origin);
        }
        if (!this.tags.isEmpty()) {
            objectWriter.name("tags").value(iLogger, this.tags);
        }
        if (!this.data.isEmpty()) {
            objectWriter.name("data").value(iLogger, this.data);
        }
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                objectWriter.name(str).value(iLogger, this.unknown.get(str));
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

    public static final class Deserializer implements JsonDeserializer<SpanContext> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public SpanContext deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            String nextName;
            objectReader.beginObject();
            String str = null;
            SentryId sentryId = null;
            SpanId spanId = null;
            SpanId spanId2 = null;
            ConcurrentHashMap concurrentHashMap = null;
            String str2 = null;
            SpanStatus spanStatus = null;
            String str3 = null;
            Map<String, String> map = null;
            Map<String, Object> map2 = null;
            while (objectReader.peek() == JsonToken.NAME) {
                nextName = objectReader.nextName();
                nextName.hashCode();
                switch (nextName) {
                    case "span_id":
                        spanId = new SpanId.Deserializer().deserialize(objectReader, iLogger);
                        break;
                    case "parent_span_id":
                        spanId2 = (SpanId) objectReader.nextOrNull(iLogger, new SpanId.Deserializer());
                        break;
                    case "description":
                        str2 = objectReader.nextString();
                        break;
                    case "origin":
                        str3 = objectReader.nextString();
                        break;
                    case "status":
                        spanStatus = (SpanStatus) objectReader.nextOrNull(iLogger, new SpanStatus.Deserializer());
                        break;
                    case "op":
                        str = objectReader.nextString();
                        break;
                    case "data":
                        map2 = (Map) objectReader.nextObjectOrNull();
                        break;
                    case "tags":
                        map = CollectionUtils.newConcurrentHashMap((Map) objectReader.nextObjectOrNull());
                        break;
                    case "trace_id":
                        sentryId = new SentryId.Deserializer().deserialize(objectReader, iLogger);
                        break;
                    default:
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        objectReader.nextUnknown(iLogger, concurrentHashMap, nextName);
                        break;
                }
            }
            if (sentryId == null) {
                IllegalStateException illegalStateException = new IllegalStateException("Missing required field \"trace_id\"");
                iLogger.log(SentryLevel.ERROR, "Missing required field \"trace_id\"", illegalStateException);
                throw illegalStateException;
            }
            if (spanId == null) {
                IllegalStateException illegalStateException2 = new IllegalStateException("Missing required field \"span_id\"");
                iLogger.log(SentryLevel.ERROR, "Missing required field \"span_id\"", illegalStateException2);
                throw illegalStateException2;
            }
            if (str == null) {
                str = "";
            }
            SpanContext spanContext = new SpanContext(sentryId, spanId, str, spanId2, null);
            spanContext.setDescription(str2);
            spanContext.setStatus(spanStatus);
            spanContext.setOrigin(str3);
            if (map != null) {
                spanContext.tags = map;
            }
            if (map2 != null) {
                spanContext.data = map2;
            }
            spanContext.setUnknown(concurrentHashMap);
            objectReader.endObject();
            return spanContext;
        }
    }
}
