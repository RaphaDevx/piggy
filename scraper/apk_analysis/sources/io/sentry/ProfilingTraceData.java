package io.sentry;

import io.sentry.ProfilingTransactionData;
import io.sentry.profilemeasurements.ProfileMeasurement;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes2.dex */
public final class ProfilingTraceData implements JsonUnknown, JsonSerializable {
    private static final String DEFAULT_ENVIRONMENT = "production";
    public static final String TRUNCATION_REASON_BACKGROUNDED = "backgrounded";
    public static final String TRUNCATION_REASON_NORMAL = "normal";
    public static final String TRUNCATION_REASON_TIMEOUT = "timeout";
    private int androidApiLevel;
    private String buildId;
    private String cpuArchitecture;
    private List<Integer> deviceCpuFrequencies;
    private final Callable<List<Integer>> deviceCpuFrequenciesReader;
    private boolean deviceIsEmulator;
    private String deviceLocale;
    private String deviceManufacturer;
    private String deviceModel;
    private String deviceOsBuildNumber;
    private String deviceOsName;
    private String deviceOsVersion;
    private String devicePhysicalMemoryBytes;
    private String durationNs;
    private String environment;
    private final Map<String, ProfileMeasurement> measurementsMap;
    private String platform;
    private String profileId;
    private String release;
    private String sampledProfile;
    private Date timestamp;
    private final File traceFile;
    private String traceId;
    private String transactionId;
    private String transactionName;
    private List<ProfilingTransactionData> transactions;
    private String truncationReason;
    private Map<String, Object> unknown;
    private String versionCode;

    public static final class JsonKeys {
        public static final String ANDROID_API_LEVEL = "android_api_level";
        public static final String ARCHITECTURE = "architecture";
        public static final String BUILD_ID = "build_id";
        public static final String DEVICE_CPU_FREQUENCIES = "device_cpu_frequencies";
        public static final String DEVICE_IS_EMULATOR = "device_is_emulator";
        public static final String DEVICE_LOCALE = "device_locale";
        public static final String DEVICE_MANUFACTURER = "device_manufacturer";
        public static final String DEVICE_MODEL = "device_model";
        public static final String DEVICE_OS_BUILD_NUMBER = "device_os_build_number";
        public static final String DEVICE_OS_NAME = "device_os_name";
        public static final String DEVICE_OS_VERSION = "device_os_version";
        public static final String DEVICE_PHYSICAL_MEMORY_BYTES = "device_physical_memory_bytes";
        public static final String DURATION_NS = "duration_ns";
        public static final String ENVIRONMENT = "environment";
        public static final String MEASUREMENTS = "measurements";
        public static final String PLATFORM = "platform";
        public static final String PROFILE_ID = "profile_id";
        public static final String RELEASE = "version_name";
        public static final String SAMPLED_PROFILE = "sampled_profile";
        public static final String TIMESTAMP = "timestamp";
        public static final String TRACE_ID = "trace_id";
        public static final String TRANSACTION_ID = "transaction_id";
        public static final String TRANSACTION_LIST = "transactions";
        public static final String TRANSACTION_NAME = "transaction_name";
        public static final String TRUNCATION_REASON = "truncation_reason";
        public static final String VERSION_CODE = "version_code";
    }

    private ProfilingTraceData() {
        this(new File("dummy"), NoOpTransaction.getInstance());
    }

    public ProfilingTraceData(File file, ITransaction iTransaction) {
        this(file, DateUtils.getCurrentDateTime(), new ArrayList(), iTransaction.getName(), iTransaction.getEventId().toString(), iTransaction.getSpanContext().getTraceId().toString(), "0", 0, "", new Callable() { // from class: io.sentry.ProfilingTraceData$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ProfilingTraceData.lambda$new$0();
            }
        }, null, null, null, null, null, null, null, null, TRUNCATION_REASON_NORMAL, new HashMap());
    }

    static /* synthetic */ List lambda$new$0() throws Exception {
        return new ArrayList();
    }

    public ProfilingTraceData(File file, Date date, List<ProfilingTransactionData> list, String str, String str2, String str3, String str4, int i, String str5, Callable<List<Integer>> callable, String str6, String str7, String str8, Boolean bool, String str9, String str10, String str11, String str12, String str13, Map<String, ProfileMeasurement> map) {
        this.deviceCpuFrequencies = new ArrayList();
        this.sampledProfile = null;
        this.traceFile = file;
        this.timestamp = date;
        this.cpuArchitecture = str5;
        this.deviceCpuFrequenciesReader = callable;
        this.androidApiLevel = i;
        this.deviceLocale = Locale.getDefault().toString();
        this.deviceManufacturer = str6 != null ? str6 : "";
        this.deviceModel = str7 != null ? str7 : "";
        this.deviceOsVersion = str8 != null ? str8 : "";
        this.deviceIsEmulator = bool != null ? bool.booleanValue() : false;
        this.devicePhysicalMemoryBytes = str9 != null ? str9 : "0";
        this.deviceOsBuildNumber = "";
        this.deviceOsName = ProfileChunk.PLATFORM_ANDROID;
        this.platform = ProfileChunk.PLATFORM_ANDROID;
        this.buildId = str10 != null ? str10 : "";
        this.transactions = list;
        this.transactionName = str.isEmpty() ? "unknown" : str;
        this.durationNs = str4;
        this.versionCode = "";
        this.release = str11 != null ? str11 : "";
        this.transactionId = str2;
        this.traceId = str3;
        this.profileId = SentryUUID.generateSentryId();
        this.environment = str12 != null ? str12 : "production";
        this.truncationReason = str13;
        if (!isTruncationReasonValid()) {
            this.truncationReason = TRUNCATION_REASON_NORMAL;
        }
        this.measurementsMap = map;
    }

    private boolean isTruncationReasonValid() {
        return this.truncationReason.equals(TRUNCATION_REASON_NORMAL) || this.truncationReason.equals("timeout") || this.truncationReason.equals(TRUNCATION_REASON_BACKGROUNDED);
    }

    public File getTraceFile() {
        return this.traceFile;
    }

    public int getAndroidApiLevel() {
        return this.androidApiLevel;
    }

    public String getCpuArchitecture() {
        return this.cpuArchitecture;
    }

    public String getDeviceLocale() {
        return this.deviceLocale;
    }

    public String getDeviceManufacturer() {
        return this.deviceManufacturer;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public String getDeviceOsBuildNumber() {
        return this.deviceOsBuildNumber;
    }

    public String getDeviceOsName() {
        return this.deviceOsName;
    }

    public String getDeviceOsVersion() {
        return this.deviceOsVersion;
    }

    public boolean isDeviceIsEmulator() {
        return this.deviceIsEmulator;
    }

    public String getPlatform() {
        return this.platform;
    }

    public String getBuildId() {
        return this.buildId;
    }

    public String getTransactionName() {
        return this.transactionName;
    }

    public String getRelease() {
        return this.release;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public List<ProfilingTransactionData> getTransactions() {
        return this.transactions;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public String getProfileId() {
        return this.profileId;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public String getSampledProfile() {
        return this.sampledProfile;
    }

    public String getDurationNs() {
        return this.durationNs;
    }

    public List<Integer> getDeviceCpuFrequencies() {
        return this.deviceCpuFrequencies;
    }

    public String getDevicePhysicalMemoryBytes() {
        return this.devicePhysicalMemoryBytes;
    }

    public String getTruncationReason() {
        return this.truncationReason;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public Map<String, ProfileMeasurement> getMeasurementsMap() {
        return this.measurementsMap;
    }

    public void setAndroidApiLevel(int i) {
        this.androidApiLevel = i;
    }

    public void setCpuArchitecture(String str) {
        this.cpuArchitecture = str;
    }

    public void setDeviceLocale(String str) {
        this.deviceLocale = str;
    }

    public void setDeviceManufacturer(String str) {
        this.deviceManufacturer = str;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public void setDeviceOsBuildNumber(String str) {
        this.deviceOsBuildNumber = str;
    }

    public void setDeviceOsVersion(String str) {
        this.deviceOsVersion = str;
    }

    public void setDeviceIsEmulator(boolean z) {
        this.deviceIsEmulator = z;
    }

    public void setDeviceCpuFrequencies(List<Integer> list) {
        this.deviceCpuFrequencies = list;
    }

    public void setDevicePhysicalMemoryBytes(String str) {
        this.devicePhysicalMemoryBytes = str;
    }

    public void setTimestamp(Date date) {
        this.timestamp = date;
    }

    public void setTruncationReason(String str) {
        this.truncationReason = str;
    }

    public void setTransactions(List<ProfilingTransactionData> list) {
        this.transactions = list;
    }

    public void setBuildId(String str) {
        this.buildId = str;
    }

    public void setTransactionName(String str) {
        this.transactionName = str;
    }

    public void setDurationNs(String str) {
        this.durationNs = str;
    }

    public void setRelease(String str) {
        this.release = str;
    }

    public void setTransactionId(String str) {
        this.transactionId = str;
    }

    public void setTraceId(String str) {
        this.traceId = str;
    }

    public void setProfileId(String str) {
        this.profileId = str;
    }

    public void setEnvironment(String str) {
        this.environment = str;
    }

    public void setSampledProfile(String str) {
        this.sampledProfile = str;
    }

    public void readDeviceCpuFrequencies() {
        try {
            this.deviceCpuFrequencies = this.deviceCpuFrequenciesReader.call();
        } catch (Throwable unused) {
        }
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        objectWriter.name(JsonKeys.ANDROID_API_LEVEL).value(iLogger, Integer.valueOf(this.androidApiLevel));
        objectWriter.name(JsonKeys.DEVICE_LOCALE).value(iLogger, this.deviceLocale);
        objectWriter.name(JsonKeys.DEVICE_MANUFACTURER).value(this.deviceManufacturer);
        objectWriter.name(JsonKeys.DEVICE_MODEL).value(this.deviceModel);
        objectWriter.name(JsonKeys.DEVICE_OS_BUILD_NUMBER).value(this.deviceOsBuildNumber);
        objectWriter.name(JsonKeys.DEVICE_OS_NAME).value(this.deviceOsName);
        objectWriter.name("device_os_version").value(this.deviceOsVersion);
        objectWriter.name(JsonKeys.DEVICE_IS_EMULATOR).value(this.deviceIsEmulator);
        objectWriter.name(JsonKeys.ARCHITECTURE).value(iLogger, this.cpuArchitecture);
        objectWriter.name(JsonKeys.DEVICE_CPU_FREQUENCIES).value(iLogger, this.deviceCpuFrequencies);
        objectWriter.name(JsonKeys.DEVICE_PHYSICAL_MEMORY_BYTES).value(this.devicePhysicalMemoryBytes);
        objectWriter.name("platform").value(this.platform);
        objectWriter.name(JsonKeys.BUILD_ID).value(this.buildId);
        objectWriter.name(JsonKeys.TRANSACTION_NAME).value(this.transactionName);
        objectWriter.name(JsonKeys.DURATION_NS).value(this.durationNs);
        objectWriter.name(JsonKeys.RELEASE).value(this.release);
        objectWriter.name(JsonKeys.VERSION_CODE).value(this.versionCode);
        if (!this.transactions.isEmpty()) {
            objectWriter.name("transactions").value(iLogger, this.transactions);
        }
        objectWriter.name("transaction_id").value(this.transactionId);
        objectWriter.name("trace_id").value(this.traceId);
        objectWriter.name(JsonKeys.PROFILE_ID).value(this.profileId);
        objectWriter.name("environment").value(this.environment);
        objectWriter.name(JsonKeys.TRUNCATION_REASON).value(this.truncationReason);
        if (this.sampledProfile != null) {
            objectWriter.name("sampled_profile").value(this.sampledProfile);
        }
        String indent = objectWriter.getIndent();
        objectWriter.setIndent("");
        objectWriter.name("measurements").value(iLogger, this.measurementsMap);
        objectWriter.setIndent(indent);
        objectWriter.name("timestamp").value(iLogger, this.timestamp);
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

    public static final class Deserializer implements JsonDeserializer<ProfilingTraceData> {
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.sentry.JsonDeserializer
        public ProfilingTraceData deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            objectReader.beginObject();
            ConcurrentHashMap concurrentHashMap = null;
            ProfilingTraceData profilingTraceData = new ProfilingTraceData();
            while (objectReader.peek() == JsonToken.NAME) {
                String nextName = objectReader.nextName();
                nextName.hashCode();
                char c = 65535;
                switch (nextName.hashCode()) {
                    case -2133529830:
                        if (nextName.equals(JsonKeys.DEVICE_MANUFACTURER)) {
                            c = 0;
                            break;
                        }
                        break;
                    case -1981468849:
                        if (nextName.equals(JsonKeys.ANDROID_API_LEVEL)) {
                            c = 1;
                            break;
                        }
                        break;
                    case -1430655860:
                        if (nextName.equals(JsonKeys.BUILD_ID)) {
                            c = 2;
                            break;
                        }
                        break;
                    case -1172160413:
                        if (nextName.equals(JsonKeys.DEVICE_LOCALE)) {
                            c = 3;
                            break;
                        }
                        break;
                    case -1102636175:
                        if (nextName.equals(JsonKeys.PROFILE_ID)) {
                            c = 4;
                            break;
                        }
                        break;
                    case -716656436:
                        if (nextName.equals(JsonKeys.DEVICE_OS_BUILD_NUMBER)) {
                            c = 5;
                            break;
                        }
                        break;
                    case -591076352:
                        if (nextName.equals(JsonKeys.DEVICE_MODEL)) {
                            c = 6;
                            break;
                        }
                        break;
                    case -512511455:
                        if (nextName.equals(JsonKeys.DEVICE_IS_EMULATOR)) {
                            c = 7;
                            break;
                        }
                        break;
                    case -478065584:
                        if (nextName.equals(JsonKeys.DURATION_NS)) {
                            c = '\b';
                            break;
                        }
                        break;
                    case -362243017:
                        if (nextName.equals("measurements")) {
                            c = '\t';
                            break;
                        }
                        break;
                    case -332426004:
                        if (nextName.equals(JsonKeys.DEVICE_PHYSICAL_MEMORY_BYTES)) {
                            c = '\n';
                            break;
                        }
                        break;
                    case -212264198:
                        if (nextName.equals(JsonKeys.DEVICE_CPU_FREQUENCIES)) {
                            c = 11;
                            break;
                        }
                        break;
                    case -102985484:
                        if (nextName.equals(JsonKeys.VERSION_CODE)) {
                            c = '\f';
                            break;
                        }
                        break;
                    case -102670958:
                        if (nextName.equals(JsonKeys.RELEASE)) {
                            c = CharUtils.CR;
                            break;
                        }
                        break;
                    case -85904877:
                        if (nextName.equals("environment")) {
                            c = 14;
                            break;
                        }
                        break;
                    case 55126294:
                        if (nextName.equals("timestamp")) {
                            c = 15;
                            break;
                        }
                        break;
                    case 508853068:
                        if (nextName.equals(JsonKeys.TRANSACTION_NAME)) {
                            c = 16;
                            break;
                        }
                        break;
                    case 796476189:
                        if (nextName.equals(JsonKeys.DEVICE_OS_NAME)) {
                            c = 17;
                            break;
                        }
                        break;
                    case 839674195:
                        if (nextName.equals(JsonKeys.ARCHITECTURE)) {
                            c = 18;
                            break;
                        }
                        break;
                    case 1010584092:
                        if (nextName.equals("transaction_id")) {
                            c = 19;
                            break;
                        }
                        break;
                    case 1052553990:
                        if (nextName.equals("device_os_version")) {
                            c = 20;
                            break;
                        }
                        break;
                    case 1163928186:
                        if (nextName.equals(JsonKeys.TRUNCATION_REASON)) {
                            c = 21;
                            break;
                        }
                        break;
                    case 1270300245:
                        if (nextName.equals("trace_id")) {
                            c = 22;
                            break;
                        }
                        break;
                    case 1874684019:
                        if (nextName.equals("platform")) {
                            c = 23;
                            break;
                        }
                        break;
                    case 1953158756:
                        if (nextName.equals("sampled_profile")) {
                            c = 24;
                            break;
                        }
                        break;
                    case 1954122069:
                        if (nextName.equals("transactions")) {
                            c = 25;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        String nextStringOrNull = objectReader.nextStringOrNull();
                        if (nextStringOrNull == null) {
                            break;
                        } else {
                            profilingTraceData.deviceManufacturer = nextStringOrNull;
                            break;
                        }
                    case 1:
                        Integer nextIntegerOrNull = objectReader.nextIntegerOrNull();
                        if (nextIntegerOrNull == null) {
                            break;
                        } else {
                            profilingTraceData.androidApiLevel = nextIntegerOrNull.intValue();
                            break;
                        }
                    case 2:
                        String nextStringOrNull2 = objectReader.nextStringOrNull();
                        if (nextStringOrNull2 == null) {
                            break;
                        } else {
                            profilingTraceData.buildId = nextStringOrNull2;
                            break;
                        }
                    case 3:
                        String nextStringOrNull3 = objectReader.nextStringOrNull();
                        if (nextStringOrNull3 == null) {
                            break;
                        } else {
                            profilingTraceData.deviceLocale = nextStringOrNull3;
                            break;
                        }
                    case 4:
                        String nextStringOrNull4 = objectReader.nextStringOrNull();
                        if (nextStringOrNull4 == null) {
                            break;
                        } else {
                            profilingTraceData.profileId = nextStringOrNull4;
                            break;
                        }
                    case 5:
                        String nextStringOrNull5 = objectReader.nextStringOrNull();
                        if (nextStringOrNull5 == null) {
                            break;
                        } else {
                            profilingTraceData.deviceOsBuildNumber = nextStringOrNull5;
                            break;
                        }
                    case 6:
                        String nextStringOrNull6 = objectReader.nextStringOrNull();
                        if (nextStringOrNull6 == null) {
                            break;
                        } else {
                            profilingTraceData.deviceModel = nextStringOrNull6;
                            break;
                        }
                    case 7:
                        Boolean nextBooleanOrNull = objectReader.nextBooleanOrNull();
                        if (nextBooleanOrNull == null) {
                            break;
                        } else {
                            profilingTraceData.deviceIsEmulator = nextBooleanOrNull.booleanValue();
                            break;
                        }
                    case '\b':
                        String nextStringOrNull7 = objectReader.nextStringOrNull();
                        if (nextStringOrNull7 == null) {
                            break;
                        } else {
                            profilingTraceData.durationNs = nextStringOrNull7;
                            break;
                        }
                    case '\t':
                        Map nextMapOrNull = objectReader.nextMapOrNull(iLogger, new ProfileMeasurement.Deserializer());
                        if (nextMapOrNull == null) {
                            break;
                        } else {
                            profilingTraceData.measurementsMap.putAll(nextMapOrNull);
                            break;
                        }
                    case '\n':
                        String nextStringOrNull8 = objectReader.nextStringOrNull();
                        if (nextStringOrNull8 == null) {
                            break;
                        } else {
                            profilingTraceData.devicePhysicalMemoryBytes = nextStringOrNull8;
                            break;
                        }
                    case 11:
                        List list = (List) objectReader.nextObjectOrNull();
                        if (list == null) {
                            break;
                        } else {
                            profilingTraceData.deviceCpuFrequencies = list;
                            break;
                        }
                    case '\f':
                        String nextStringOrNull9 = objectReader.nextStringOrNull();
                        if (nextStringOrNull9 == null) {
                            break;
                        } else {
                            profilingTraceData.versionCode = nextStringOrNull9;
                            break;
                        }
                    case '\r':
                        String nextStringOrNull10 = objectReader.nextStringOrNull();
                        if (nextStringOrNull10 == null) {
                            break;
                        } else {
                            profilingTraceData.release = nextStringOrNull10;
                            break;
                        }
                    case 14:
                        String nextStringOrNull11 = objectReader.nextStringOrNull();
                        if (nextStringOrNull11 == null) {
                            break;
                        } else {
                            profilingTraceData.environment = nextStringOrNull11;
                            break;
                        }
                    case 15:
                        Date nextDateOrNull = objectReader.nextDateOrNull(iLogger);
                        if (nextDateOrNull == null) {
                            break;
                        } else {
                            profilingTraceData.timestamp = nextDateOrNull;
                            break;
                        }
                    case 16:
                        String nextStringOrNull12 = objectReader.nextStringOrNull();
                        if (nextStringOrNull12 == null) {
                            break;
                        } else {
                            profilingTraceData.transactionName = nextStringOrNull12;
                            break;
                        }
                    case 17:
                        String nextStringOrNull13 = objectReader.nextStringOrNull();
                        if (nextStringOrNull13 == null) {
                            break;
                        } else {
                            profilingTraceData.deviceOsName = nextStringOrNull13;
                            break;
                        }
                    case 18:
                        String nextStringOrNull14 = objectReader.nextStringOrNull();
                        if (nextStringOrNull14 == null) {
                            break;
                        } else {
                            profilingTraceData.cpuArchitecture = nextStringOrNull14;
                            break;
                        }
                    case 19:
                        String nextStringOrNull15 = objectReader.nextStringOrNull();
                        if (nextStringOrNull15 == null) {
                            break;
                        } else {
                            profilingTraceData.transactionId = nextStringOrNull15;
                            break;
                        }
                    case 20:
                        String nextStringOrNull16 = objectReader.nextStringOrNull();
                        if (nextStringOrNull16 == null) {
                            break;
                        } else {
                            profilingTraceData.deviceOsVersion = nextStringOrNull16;
                            break;
                        }
                    case 21:
                        String nextStringOrNull17 = objectReader.nextStringOrNull();
                        if (nextStringOrNull17 == null) {
                            break;
                        } else {
                            profilingTraceData.truncationReason = nextStringOrNull17;
                            break;
                        }
                    case 22:
                        String nextStringOrNull18 = objectReader.nextStringOrNull();
                        if (nextStringOrNull18 == null) {
                            break;
                        } else {
                            profilingTraceData.traceId = nextStringOrNull18;
                            break;
                        }
                    case 23:
                        String nextStringOrNull19 = objectReader.nextStringOrNull();
                        if (nextStringOrNull19 == null) {
                            break;
                        } else {
                            profilingTraceData.platform = nextStringOrNull19;
                            break;
                        }
                    case 24:
                        String nextStringOrNull20 = objectReader.nextStringOrNull();
                        if (nextStringOrNull20 == null) {
                            break;
                        } else {
                            profilingTraceData.sampledProfile = nextStringOrNull20;
                            break;
                        }
                    case 25:
                        List nextListOrNull = objectReader.nextListOrNull(iLogger, new ProfilingTransactionData.Deserializer());
                        if (nextListOrNull == null) {
                            break;
                        } else {
                            profilingTraceData.transactions.addAll(nextListOrNull);
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
            profilingTraceData.setUnknown(concurrentHashMap);
            objectReader.endObject();
            return profilingTraceData;
        }
    }
}
