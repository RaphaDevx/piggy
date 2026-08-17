package io.sentry;

import io.sentry.util.SentryRandom;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public final class SentryAppStartProfilingOptions implements JsonUnknown, JsonSerializable {
    boolean continuousProfileSampled;
    boolean isContinuousProfilingEnabled;
    boolean isEnableAppStartProfiling;
    boolean isProfilingEnabled;
    boolean isStartProfilerOnAppStart;
    ProfileLifecycle profileLifecycle;
    Double profileSampleRate;
    boolean profileSampled;
    String profilingTracesDirPath;
    int profilingTracesHz;
    Double traceSampleRate;
    boolean traceSampled;
    private Map<String, Object> unknown;

    public static final class JsonKeys {
        public static final String CONTINUOUS_PROFILE_SAMPLED = "continuous_profile_sampled";
        public static final String IS_CONTINUOUS_PROFILING_ENABLED = "is_continuous_profiling_enabled";
        public static final String IS_ENABLE_APP_START_PROFILING = "is_enable_app_start_profiling";
        public static final String IS_PROFILING_ENABLED = "is_profiling_enabled";
        public static final String IS_START_PROFILER_ON_APP_START = "is_start_profiler_on_app_start";
        public static final String PROFILE_LIFECYCLE = "profile_lifecycle";
        public static final String PROFILE_SAMPLED = "profile_sampled";
        public static final String PROFILE_SAMPLE_RATE = "profile_sample_rate";
        public static final String PROFILING_TRACES_DIR_PATH = "profiling_traces_dir_path";
        public static final String PROFILING_TRACES_HZ = "profiling_traces_hz";
        public static final String TRACE_SAMPLED = "trace_sampled";
        public static final String TRACE_SAMPLE_RATE = "trace_sample_rate";
    }

    public SentryAppStartProfilingOptions() {
        this.traceSampled = false;
        this.traceSampleRate = null;
        this.profileSampled = false;
        this.profileSampleRate = null;
        this.continuousProfileSampled = false;
        this.profilingTracesDirPath = null;
        this.isProfilingEnabled = false;
        this.isContinuousProfilingEnabled = false;
        this.profileLifecycle = ProfileLifecycle.MANUAL;
        this.profilingTracesHz = 0;
        this.isEnableAppStartProfiling = true;
        this.isStartProfilerOnAppStart = false;
    }

    SentryAppStartProfilingOptions(SentryOptions sentryOptions, TracesSamplingDecision tracesSamplingDecision) {
        this.traceSampled = tracesSamplingDecision.getSampled().booleanValue();
        this.traceSampleRate = tracesSamplingDecision.getSampleRate();
        this.profileSampled = tracesSamplingDecision.getProfileSampled().booleanValue();
        this.profileSampleRate = tracesSamplingDecision.getProfileSampleRate();
        this.continuousProfileSampled = sentryOptions.getInternalTracesSampler().sampleSessionProfile(SentryRandom.current().nextDouble());
        this.profilingTracesDirPath = sentryOptions.getProfilingTracesDirPath();
        this.isProfilingEnabled = sentryOptions.isProfilingEnabled();
        this.isContinuousProfilingEnabled = sentryOptions.isContinuousProfilingEnabled();
        this.profileLifecycle = sentryOptions.getProfileLifecycle();
        this.profilingTracesHz = sentryOptions.getProfilingTracesHz();
        this.isEnableAppStartProfiling = sentryOptions.isEnableAppStartProfiling();
        this.isStartProfilerOnAppStart = sentryOptions.isStartProfilerOnAppStart();
    }

    public void setProfileSampled(boolean z) {
        this.profileSampled = z;
    }

    public boolean isProfileSampled() {
        return this.profileSampled;
    }

    public void setContinuousProfileSampled(boolean z) {
        this.continuousProfileSampled = z;
    }

    public boolean isContinuousProfileSampled() {
        return this.continuousProfileSampled;
    }

    public void setProfileLifecycle(ProfileLifecycle profileLifecycle) {
        this.profileLifecycle = profileLifecycle;
    }

    public ProfileLifecycle getProfileLifecycle() {
        return this.profileLifecycle;
    }

    public void setProfileSampleRate(Double d) {
        this.profileSampleRate = d;
    }

    public Double getProfileSampleRate() {
        return this.profileSampleRate;
    }

    public void setTraceSampled(boolean z) {
        this.traceSampled = z;
    }

    public boolean isTraceSampled() {
        return this.traceSampled;
    }

    public void setTraceSampleRate(Double d) {
        this.traceSampleRate = d;
    }

    public Double getTraceSampleRate() {
        return this.traceSampleRate;
    }

    public void setProfilingTracesDirPath(String str) {
        this.profilingTracesDirPath = str;
    }

    public String getProfilingTracesDirPath() {
        return this.profilingTracesDirPath;
    }

    public void setProfilingEnabled(boolean z) {
        this.isProfilingEnabled = z;
    }

    public boolean isProfilingEnabled() {
        return this.isProfilingEnabled;
    }

    public void setContinuousProfilingEnabled(boolean z) {
        this.isContinuousProfilingEnabled = z;
    }

    public boolean isContinuousProfilingEnabled() {
        return this.isContinuousProfilingEnabled;
    }

    public void setProfilingTracesHz(int i) {
        this.profilingTracesHz = i;
    }

    public int getProfilingTracesHz() {
        return this.profilingTracesHz;
    }

    public void setEnableAppStartProfiling(boolean z) {
        this.isEnableAppStartProfiling = z;
    }

    public boolean isEnableAppStartProfiling() {
        return this.isEnableAppStartProfiling;
    }

    public void setStartProfilerOnAppStart(boolean z) {
        this.isStartProfilerOnAppStart = z;
    }

    public boolean isStartProfilerOnAppStart() {
        return this.isStartProfilerOnAppStart;
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name(JsonKeys.PROFILE_SAMPLED).value(iLogger, Boolean.valueOf(this.profileSampled));
        objectWriter.name(JsonKeys.PROFILE_SAMPLE_RATE).value(iLogger, this.profileSampleRate);
        objectWriter.name(JsonKeys.CONTINUOUS_PROFILE_SAMPLED).value(iLogger, Boolean.valueOf(this.continuousProfileSampled));
        objectWriter.name(JsonKeys.TRACE_SAMPLED).value(iLogger, Boolean.valueOf(this.traceSampled));
        objectWriter.name(JsonKeys.TRACE_SAMPLE_RATE).value(iLogger, this.traceSampleRate);
        objectWriter.name(JsonKeys.PROFILING_TRACES_DIR_PATH).value(iLogger, this.profilingTracesDirPath);
        objectWriter.name(JsonKeys.IS_PROFILING_ENABLED).value(iLogger, Boolean.valueOf(this.isProfilingEnabled));
        objectWriter.name(JsonKeys.IS_CONTINUOUS_PROFILING_ENABLED).value(iLogger, Boolean.valueOf(this.isContinuousProfilingEnabled));
        objectWriter.name(JsonKeys.PROFILE_LIFECYCLE).value(iLogger, this.profileLifecycle.name());
        objectWriter.name(JsonKeys.PROFILING_TRACES_HZ).value(iLogger, Integer.valueOf(this.profilingTracesHz));
        objectWriter.name(JsonKeys.IS_ENABLE_APP_START_PROFILING).value(iLogger, Boolean.valueOf(this.isEnableAppStartProfiling));
        objectWriter.name(JsonKeys.IS_START_PROFILER_ON_APP_START).value(iLogger, Boolean.valueOf(this.isStartProfilerOnAppStart));
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

    public static final class Deserializer implements JsonDeserializer<SentryAppStartProfilingOptions> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.sentry.JsonDeserializer
        public SentryAppStartProfilingOptions deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            String nextName;
            objectReader.beginObject();
            SentryAppStartProfilingOptions sentryAppStartProfilingOptions = new SentryAppStartProfilingOptions();
            ConcurrentHashMap concurrentHashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                nextName = objectReader.nextName();
                nextName.hashCode();
                switch (nextName) {
                    case "is_enable_app_start_profiling":
                        Boolean nextBooleanOrNull = objectReader.nextBooleanOrNull();
                        if (nextBooleanOrNull == null) {
                            break;
                        } else {
                            sentryAppStartProfilingOptions.isEnableAppStartProfiling = nextBooleanOrNull.booleanValue();
                            break;
                        }
                    case "trace_sampled":
                        Boolean nextBooleanOrNull2 = objectReader.nextBooleanOrNull();
                        if (nextBooleanOrNull2 == null) {
                            break;
                        } else {
                            sentryAppStartProfilingOptions.traceSampled = nextBooleanOrNull2.booleanValue();
                            break;
                        }
                    case "profiling_traces_dir_path":
                        String nextStringOrNull = objectReader.nextStringOrNull();
                        if (nextStringOrNull == null) {
                            break;
                        } else {
                            sentryAppStartProfilingOptions.profilingTracesDirPath = nextStringOrNull;
                            break;
                        }
                    case "is_continuous_profiling_enabled":
                        Boolean nextBooleanOrNull3 = objectReader.nextBooleanOrNull();
                        if (nextBooleanOrNull3 == null) {
                            break;
                        } else {
                            sentryAppStartProfilingOptions.isContinuousProfilingEnabled = nextBooleanOrNull3.booleanValue();
                            break;
                        }
                    case "is_profiling_enabled":
                        Boolean nextBooleanOrNull4 = objectReader.nextBooleanOrNull();
                        if (nextBooleanOrNull4 == null) {
                            break;
                        } else {
                            sentryAppStartProfilingOptions.isProfilingEnabled = nextBooleanOrNull4.booleanValue();
                            break;
                        }
                    case "is_start_profiler_on_app_start":
                        Boolean nextBooleanOrNull5 = objectReader.nextBooleanOrNull();
                        if (nextBooleanOrNull5 == null) {
                            break;
                        } else {
                            sentryAppStartProfilingOptions.isStartProfilerOnAppStart = nextBooleanOrNull5.booleanValue();
                            break;
                        }
                    case "profile_sampled":
                        Boolean nextBooleanOrNull6 = objectReader.nextBooleanOrNull();
                        if (nextBooleanOrNull6 == null) {
                            break;
                        } else {
                            sentryAppStartProfilingOptions.profileSampled = nextBooleanOrNull6.booleanValue();
                            break;
                        }
                    case "profile_lifecycle":
                        String nextStringOrNull2 = objectReader.nextStringOrNull();
                        if (nextStringOrNull2 == null) {
                            break;
                        } else {
                            try {
                                sentryAppStartProfilingOptions.profileLifecycle = ProfileLifecycle.valueOf(nextStringOrNull2);
                                break;
                            } catch (IllegalArgumentException unused) {
                                iLogger.log(SentryLevel.ERROR, "Error when deserializing ProfileLifecycle: " + nextStringOrNull2, new Object[0]);
                                break;
                            }
                        }
                    case "continuous_profile_sampled":
                        Boolean nextBooleanOrNull7 = objectReader.nextBooleanOrNull();
                        if (nextBooleanOrNull7 == null) {
                            break;
                        } else {
                            sentryAppStartProfilingOptions.continuousProfileSampled = nextBooleanOrNull7.booleanValue();
                            break;
                        }
                    case "profiling_traces_hz":
                        Integer nextIntegerOrNull = objectReader.nextIntegerOrNull();
                        if (nextIntegerOrNull == null) {
                            break;
                        } else {
                            sentryAppStartProfilingOptions.profilingTracesHz = nextIntegerOrNull.intValue();
                            break;
                        }
                    case "trace_sample_rate":
                        Double nextDoubleOrNull = objectReader.nextDoubleOrNull();
                        if (nextDoubleOrNull == null) {
                            break;
                        } else {
                            sentryAppStartProfilingOptions.traceSampleRate = nextDoubleOrNull;
                            break;
                        }
                    case "profile_sample_rate":
                        Double nextDoubleOrNull2 = objectReader.nextDoubleOrNull();
                        if (nextDoubleOrNull2 == null) {
                            break;
                        } else {
                            sentryAppStartProfilingOptions.profileSampleRate = nextDoubleOrNull2;
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
            sentryAppStartProfilingOptions.setUnknown(concurrentHashMap);
            objectReader.endObject();
            return sentryAppStartProfilingOptions;
        }
    }
}
