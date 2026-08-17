package io.sentry.protocol;

import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.ILogger;
import io.sentry.JsonDeserializer;
import io.sentry.JsonSerializable;
import io.sentry.JsonUnknown;
import io.sentry.ObjectReader;
import io.sentry.ObjectWriter;
import io.sentry.util.CollectionUtils;
import io.sentry.util.Objects;
import io.sentry.vendor.gson.stream.JsonToken;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes2.dex */
public final class Device implements JsonUnknown, JsonSerializable {
    public static final String TYPE = "device";
    private String[] archs;
    private Float batteryLevel;
    private Float batteryTemperature;
    private Date bootTime;
    private String brand;
    private Boolean charging;
    private String chipset;
    private String connectionType;
    private String cpuDescription;
    private Long externalFreeStorage;
    private Long externalStorageSize;
    private String family;
    private Long freeMemory;
    private Long freeStorage;
    private String id;
    private String locale;
    private Boolean lowMemory;
    private String manufacturer;
    private Long memorySize;
    private String model;
    private String modelId;
    private String name;
    private Boolean online;
    private DeviceOrientation orientation;
    private Integer processorCount;
    private Double processorFrequency;
    private Float screenDensity;
    private Integer screenDpi;
    private Integer screenHeightPixels;
    private Integer screenWidthPixels;
    private Boolean simulator;
    private Long storageSize;
    private TimeZone timezone;
    private Map<String, Object> unknown;
    private Long usableMemory;

    public static final class JsonKeys {
        public static final String ARCHS = "archs";
        public static final String BATTERY_LEVEL = "battery_level";
        public static final String BATTERY_TEMPERATURE = "battery_temperature";
        public static final String BOOT_TIME = "boot_time";
        public static final String BRAND = "brand";
        public static final String CHARGING = "charging";
        public static final String CHIPSET = "chipset";
        public static final String CONNECTION_TYPE = "connection_type";
        public static final String CPU_DESCRIPTION = "cpu_description";
        public static final String EXTERNAL_FREE_STORAGE = "external_free_storage";
        public static final String EXTERNAL_STORAGE_SIZE = "external_storage_size";
        public static final String FAMILY = "family";
        public static final String FREE_MEMORY = "free_memory";
        public static final String FREE_STORAGE = "free_storage";
        public static final String ID = "id";
        public static final String LOCALE = "locale";
        public static final String LOW_MEMORY = "low_memory";
        public static final String MANUFACTURER = "manufacturer";
        public static final String MEMORY_SIZE = "memory_size";
        public static final String MODEL = "model";
        public static final String MODEL_ID = "model_id";
        public static final String NAME = "name";
        public static final String ONLINE = "online";
        public static final String ORIENTATION = "orientation";
        public static final String PROCESSOR_COUNT = "processor_count";
        public static final String PROCESSOR_FREQUENCY = "processor_frequency";
        public static final String SCREEN_DENSITY = "screen_density";
        public static final String SCREEN_DPI = "screen_dpi";
        public static final String SCREEN_HEIGHT_PIXELS = "screen_height_pixels";
        public static final String SCREEN_WIDTH_PIXELS = "screen_width_pixels";
        public static final String SIMULATOR = "simulator";
        public static final String STORAGE_SIZE = "storage_size";
        public static final String TIMEZONE = "timezone";
        public static final String USABLE_MEMORY = "usable_memory";
    }

    public Device() {
    }

    Device(Device device) {
        this.name = device.name;
        this.manufacturer = device.manufacturer;
        this.brand = device.brand;
        this.family = device.family;
        this.model = device.model;
        this.modelId = device.modelId;
        this.charging = device.charging;
        this.online = device.online;
        this.orientation = device.orientation;
        this.simulator = device.simulator;
        this.memorySize = device.memorySize;
        this.freeMemory = device.freeMemory;
        this.usableMemory = device.usableMemory;
        this.lowMemory = device.lowMemory;
        this.storageSize = device.storageSize;
        this.freeStorage = device.freeStorage;
        this.externalStorageSize = device.externalStorageSize;
        this.externalFreeStorage = device.externalFreeStorage;
        this.screenWidthPixels = device.screenWidthPixels;
        this.screenHeightPixels = device.screenHeightPixels;
        this.screenDensity = device.screenDensity;
        this.screenDpi = device.screenDpi;
        this.bootTime = device.bootTime;
        this.id = device.id;
        this.connectionType = device.connectionType;
        this.batteryTemperature = device.batteryTemperature;
        this.batteryLevel = device.batteryLevel;
        String[] strArr = device.archs;
        this.archs = strArr != null ? (String[]) strArr.clone() : null;
        this.locale = device.locale;
        TimeZone timeZone = device.timezone;
        this.timezone = timeZone != null ? (TimeZone) timeZone.clone() : null;
        this.processorCount = device.processorCount;
        this.processorFrequency = device.processorFrequency;
        this.cpuDescription = device.cpuDescription;
        this.chipset = device.chipset;
        this.unknown = CollectionUtils.newConcurrentHashMap(device.unknown);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String str) {
        this.manufacturer = str;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String str) {
        this.brand = str;
    }

    public String getFamily() {
        return this.family;
    }

    public void setFamily(String str) {
        this.family = str;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public String getModelId() {
        return this.modelId;
    }

    public void setModelId(String str) {
        this.modelId = str;
    }

    public Float getBatteryLevel() {
        return this.batteryLevel;
    }

    public void setBatteryLevel(Float f) {
        this.batteryLevel = f;
    }

    public Boolean isCharging() {
        return this.charging;
    }

    public void setCharging(Boolean bool) {
        this.charging = bool;
    }

    public Boolean isOnline() {
        return this.online;
    }

    public void setOnline(Boolean bool) {
        this.online = bool;
    }

    public DeviceOrientation getOrientation() {
        return this.orientation;
    }

    public void setOrientation(DeviceOrientation deviceOrientation) {
        this.orientation = deviceOrientation;
    }

    public Boolean isSimulator() {
        return this.simulator;
    }

    public void setSimulator(Boolean bool) {
        this.simulator = bool;
    }

    public Long getMemorySize() {
        return this.memorySize;
    }

    public void setMemorySize(Long l) {
        this.memorySize = l;
    }

    public Long getFreeMemory() {
        return this.freeMemory;
    }

    public void setFreeMemory(Long l) {
        this.freeMemory = l;
    }

    public Long getUsableMemory() {
        return this.usableMemory;
    }

    public void setUsableMemory(Long l) {
        this.usableMemory = l;
    }

    public Boolean isLowMemory() {
        return this.lowMemory;
    }

    public void setLowMemory(Boolean bool) {
        this.lowMemory = bool;
    }

    public Long getStorageSize() {
        return this.storageSize;
    }

    public void setStorageSize(Long l) {
        this.storageSize = l;
    }

    public Long getFreeStorage() {
        return this.freeStorage;
    }

    public void setFreeStorage(Long l) {
        this.freeStorage = l;
    }

    public Long getExternalStorageSize() {
        return this.externalStorageSize;
    }

    public void setExternalStorageSize(Long l) {
        this.externalStorageSize = l;
    }

    public Long getExternalFreeStorage() {
        return this.externalFreeStorage;
    }

    public void setExternalFreeStorage(Long l) {
        this.externalFreeStorage = l;
    }

    public Float getScreenDensity() {
        return this.screenDensity;
    }

    public void setScreenDensity(Float f) {
        this.screenDensity = f;
    }

    public Integer getScreenDpi() {
        return this.screenDpi;
    }

    public void setScreenDpi(Integer num) {
        this.screenDpi = num;
    }

    public Date getBootTime() {
        Date date = this.bootTime;
        if (date != null) {
            return (Date) date.clone();
        }
        return null;
    }

    public void setBootTime(Date date) {
        this.bootTime = date;
    }

    public TimeZone getTimezone() {
        return this.timezone;
    }

    public void setTimezone(TimeZone timeZone) {
        this.timezone = timeZone;
    }

    public String[] getArchs() {
        return this.archs;
    }

    public void setArchs(String[] strArr) {
        this.archs = strArr;
    }

    public Integer getScreenWidthPixels() {
        return this.screenWidthPixels;
    }

    public void setScreenWidthPixels(Integer num) {
        this.screenWidthPixels = num;
    }

    public Integer getScreenHeightPixels() {
        return this.screenHeightPixels;
    }

    public void setScreenHeightPixels(Integer num) {
        this.screenHeightPixels = num;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getConnectionType() {
        return this.connectionType;
    }

    public void setConnectionType(String str) {
        this.connectionType = str;
    }

    public Float getBatteryTemperature() {
        return this.batteryTemperature;
    }

    public void setBatteryTemperature(Float f) {
        this.batteryTemperature = f;
    }

    public Integer getProcessorCount() {
        return this.processorCount;
    }

    public void setProcessorCount(Integer num) {
        this.processorCount = num;
    }

    public Double getProcessorFrequency() {
        return this.processorFrequency;
    }

    public void setProcessorFrequency(Double d) {
        this.processorFrequency = d;
    }

    public String getCpuDescription() {
        return this.cpuDescription;
    }

    public void setCpuDescription(String str) {
        this.cpuDescription = str;
    }

    public String getChipset() {
        return this.chipset;
    }

    public void setChipset(String str) {
        this.chipset = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Device device = (Device) obj;
        return Objects.equals(this.name, device.name) && Objects.equals(this.manufacturer, device.manufacturer) && Objects.equals(this.brand, device.brand) && Objects.equals(this.family, device.family) && Objects.equals(this.model, device.model) && Objects.equals(this.modelId, device.modelId) && Arrays.equals(this.archs, device.archs) && Objects.equals(this.batteryLevel, device.batteryLevel) && Objects.equals(this.charging, device.charging) && Objects.equals(this.online, device.online) && this.orientation == device.orientation && Objects.equals(this.simulator, device.simulator) && Objects.equals(this.memorySize, device.memorySize) && Objects.equals(this.freeMemory, device.freeMemory) && Objects.equals(this.usableMemory, device.usableMemory) && Objects.equals(this.lowMemory, device.lowMemory) && Objects.equals(this.storageSize, device.storageSize) && Objects.equals(this.freeStorage, device.freeStorage) && Objects.equals(this.externalStorageSize, device.externalStorageSize) && Objects.equals(this.externalFreeStorage, device.externalFreeStorage) && Objects.equals(this.screenWidthPixels, device.screenWidthPixels) && Objects.equals(this.screenHeightPixels, device.screenHeightPixels) && Objects.equals(this.screenDensity, device.screenDensity) && Objects.equals(this.screenDpi, device.screenDpi) && Objects.equals(this.bootTime, device.bootTime) && Objects.equals(this.id, device.id) && Objects.equals(this.locale, device.locale) && Objects.equals(this.connectionType, device.connectionType) && Objects.equals(this.batteryTemperature, device.batteryTemperature) && Objects.equals(this.processorCount, device.processorCount) && Objects.equals(this.processorFrequency, device.processorFrequency) && Objects.equals(this.cpuDescription, device.cpuDescription) && Objects.equals(this.chipset, device.chipset);
    }

    public int hashCode() {
        return (Objects.hash(this.name, this.manufacturer, this.brand, this.family, this.model, this.modelId, this.batteryLevel, this.charging, this.online, this.orientation, this.simulator, this.memorySize, this.freeMemory, this.usableMemory, this.lowMemory, this.storageSize, this.freeStorage, this.externalStorageSize, this.externalFreeStorage, this.screenWidthPixels, this.screenHeightPixels, this.screenDensity, this.screenDpi, this.bootTime, this.timezone, this.id, this.locale, this.connectionType, this.batteryTemperature, this.processorCount, this.processorFrequency, this.cpuDescription, this.chipset) * 31) + Arrays.hashCode(this.archs);
    }

    public enum DeviceOrientation implements JsonSerializable {
        PORTRAIT,
        LANDSCAPE;

        @Override // io.sentry.JsonSerializable
        public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
            objectWriter.value(toString().toLowerCase(Locale.ROOT));
        }

        public static final class Deserializer implements JsonDeserializer<DeviceOrientation> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.sentry.JsonDeserializer
            public DeviceOrientation deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
                return DeviceOrientation.valueOf(objectReader.nextString().toUpperCase(Locale.ROOT));
            }
        }
    }

    @Override // io.sentry.JsonSerializable
    public void serialize(ObjectWriter objectWriter, ILogger iLogger) throws IOException {
        objectWriter.beginObject();
        if (this.name != null) {
            objectWriter.name("name").value(this.name);
        }
        if (this.manufacturer != null) {
            objectWriter.name(JsonKeys.MANUFACTURER).value(this.manufacturer);
        }
        if (this.brand != null) {
            objectWriter.name(JsonKeys.BRAND).value(this.brand);
        }
        if (this.family != null) {
            objectWriter.name(JsonKeys.FAMILY).value(this.family);
        }
        if (this.model != null) {
            objectWriter.name(JsonKeys.MODEL).value(this.model);
        }
        if (this.modelId != null) {
            objectWriter.name(JsonKeys.MODEL_ID).value(this.modelId);
        }
        if (this.archs != null) {
            objectWriter.name(JsonKeys.ARCHS).value(iLogger, this.archs);
        }
        if (this.batteryLevel != null) {
            objectWriter.name(JsonKeys.BATTERY_LEVEL).value(this.batteryLevel);
        }
        if (this.charging != null) {
            objectWriter.name(JsonKeys.CHARGING).value(this.charging);
        }
        if (this.online != null) {
            objectWriter.name("online").value(this.online);
        }
        if (this.orientation != null) {
            objectWriter.name("orientation").value(iLogger, this.orientation);
        }
        if (this.simulator != null) {
            objectWriter.name(JsonKeys.SIMULATOR).value(this.simulator);
        }
        if (this.memorySize != null) {
            objectWriter.name("memory_size").value(this.memorySize);
        }
        if (this.freeMemory != null) {
            objectWriter.name(JsonKeys.FREE_MEMORY).value(this.freeMemory);
        }
        if (this.usableMemory != null) {
            objectWriter.name(JsonKeys.USABLE_MEMORY).value(this.usableMemory);
        }
        if (this.lowMemory != null) {
            objectWriter.name(JsonKeys.LOW_MEMORY).value(this.lowMemory);
        }
        if (this.storageSize != null) {
            objectWriter.name(JsonKeys.STORAGE_SIZE).value(this.storageSize);
        }
        if (this.freeStorage != null) {
            objectWriter.name(JsonKeys.FREE_STORAGE).value(this.freeStorage);
        }
        if (this.externalStorageSize != null) {
            objectWriter.name(JsonKeys.EXTERNAL_STORAGE_SIZE).value(this.externalStorageSize);
        }
        if (this.externalFreeStorage != null) {
            objectWriter.name(JsonKeys.EXTERNAL_FREE_STORAGE).value(this.externalFreeStorage);
        }
        if (this.screenWidthPixels != null) {
            objectWriter.name(JsonKeys.SCREEN_WIDTH_PIXELS).value(this.screenWidthPixels);
        }
        if (this.screenHeightPixels != null) {
            objectWriter.name(JsonKeys.SCREEN_HEIGHT_PIXELS).value(this.screenHeightPixels);
        }
        if (this.screenDensity != null) {
            objectWriter.name(JsonKeys.SCREEN_DENSITY).value(this.screenDensity);
        }
        if (this.screenDpi != null) {
            objectWriter.name(JsonKeys.SCREEN_DPI).value(this.screenDpi);
        }
        if (this.bootTime != null) {
            objectWriter.name(JsonKeys.BOOT_TIME).value(iLogger, this.bootTime);
        }
        if (this.timezone != null) {
            objectWriter.name("timezone").value(iLogger, this.timezone);
        }
        if (this.id != null) {
            objectWriter.name("id").value(this.id);
        }
        if (this.connectionType != null) {
            objectWriter.name("connection_type").value(this.connectionType);
        }
        if (this.batteryTemperature != null) {
            objectWriter.name(JsonKeys.BATTERY_TEMPERATURE).value(this.batteryTemperature);
        }
        if (this.locale != null) {
            objectWriter.name("locale").value(this.locale);
        }
        if (this.processorCount != null) {
            objectWriter.name(JsonKeys.PROCESSOR_COUNT).value(this.processorCount);
        }
        if (this.processorFrequency != null) {
            objectWriter.name(JsonKeys.PROCESSOR_FREQUENCY).value(this.processorFrequency);
        }
        if (this.cpuDescription != null) {
            objectWriter.name(JsonKeys.CPU_DESCRIPTION).value(this.cpuDescription);
        }
        if (this.chipset != null) {
            objectWriter.name(JsonKeys.CHIPSET).value(this.chipset);
        }
        Map<String, Object> map = this.unknown;
        if (map != null) {
            for (String str : map.keySet()) {
                objectWriter.name(str).value(iLogger, this.unknown.get(str));
            }
        }
        objectWriter.endObject();
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    @Override // io.sentry.JsonUnknown
    public Map<String, Object> getUnknown() {
        return this.unknown;
    }

    @Override // io.sentry.JsonUnknown
    public void setUnknown(Map<String, Object> map) {
        this.unknown = map;
    }

    public static final class Deserializer implements JsonDeserializer<Device> {
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // io.sentry.JsonDeserializer
        public Device deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception {
            objectReader.beginObject();
            Device device = new Device();
            ConcurrentHashMap concurrentHashMap = null;
            while (objectReader.peek() == JsonToken.NAME) {
                String nextName = objectReader.nextName();
                nextName.hashCode();
                char c = 65535;
                switch (nextName.hashCode()) {
                    case -2076227591:
                        if (nextName.equals("timezone")) {
                            c = 0;
                            break;
                        }
                        break;
                    case -2012489734:
                        if (nextName.equals(JsonKeys.BOOT_TIME)) {
                            c = 1;
                            break;
                        }
                        break;
                    case -1981332476:
                        if (nextName.equals(JsonKeys.SIMULATOR)) {
                            c = 2;
                            break;
                        }
                        break;
                    case -1969347631:
                        if (nextName.equals(JsonKeys.MANUFACTURER)) {
                            c = 3;
                            break;
                        }
                        break;
                    case -1608004830:
                        if (nextName.equals(JsonKeys.PROCESSOR_COUNT)) {
                            c = 4;
                            break;
                        }
                        break;
                    case -1439500848:
                        if (nextName.equals("orientation")) {
                            c = 5;
                            break;
                        }
                        break;
                    case -1410521534:
                        if (nextName.equals(JsonKeys.BATTERY_TEMPERATURE)) {
                            c = 6;
                            break;
                        }
                        break;
                    case -1281860764:
                        if (nextName.equals(JsonKeys.FAMILY)) {
                            c = 7;
                            break;
                        }
                        break;
                    case -1097462182:
                        if (nextName.equals("locale")) {
                            c = '\b';
                            break;
                        }
                        break;
                    case -1012222381:
                        if (nextName.equals("online")) {
                            c = '\t';
                            break;
                        }
                        break;
                    case -877252910:
                        if (nextName.equals(JsonKeys.BATTERY_LEVEL)) {
                            c = '\n';
                            break;
                        }
                        break;
                    case -619038223:
                        if (nextName.equals(JsonKeys.MODEL_ID)) {
                            c = 11;
                            break;
                        }
                        break;
                    case -568274923:
                        if (nextName.equals(JsonKeys.SCREEN_DENSITY)) {
                            c = '\f';
                            break;
                        }
                        break;
                    case -417046774:
                        if (nextName.equals(JsonKeys.SCREEN_DPI)) {
                            c = CharUtils.CR;
                            break;
                        }
                        break;
                    case -136523212:
                        if (nextName.equals(JsonKeys.FREE_MEMORY)) {
                            c = 14;
                            break;
                        }
                        break;
                    case 3355:
                        if (nextName.equals("id")) {
                            c = 15;
                            break;
                        }
                        break;
                    case 3373707:
                        if (nextName.equals("name")) {
                            c = 16;
                            break;
                        }
                        break;
                    case 59142220:
                        if (nextName.equals(JsonKeys.LOW_MEMORY)) {
                            c = 17;
                            break;
                        }
                        break;
                    case 93076189:
                        if (nextName.equals(JsonKeys.ARCHS)) {
                            c = 18;
                            break;
                        }
                        break;
                    case 93997959:
                        if (nextName.equals(JsonKeys.BRAND)) {
                            c = 19;
                            break;
                        }
                        break;
                    case 104069929:
                        if (nextName.equals(JsonKeys.MODEL)) {
                            c = 20;
                            break;
                        }
                        break;
                    case 115746789:
                        if (nextName.equals(JsonKeys.CPU_DESCRIPTION)) {
                            c = 21;
                            break;
                        }
                        break;
                    case 244497903:
                        if (nextName.equals(JsonKeys.PROCESSOR_FREQUENCY)) {
                            c = 22;
                            break;
                        }
                        break;
                    case 731866107:
                        if (nextName.equals("connection_type")) {
                            c = 23;
                            break;
                        }
                        break;
                    case 746402966:
                        if (nextName.equals(JsonKeys.CHIPSET)) {
                            c = 24;
                            break;
                        }
                        break;
                    case 817830969:
                        if (nextName.equals(JsonKeys.SCREEN_WIDTH_PIXELS)) {
                            c = 25;
                            break;
                        }
                        break;
                    case 823882553:
                        if (nextName.equals(JsonKeys.EXTERNAL_STORAGE_SIZE)) {
                            c = 26;
                            break;
                        }
                        break;
                    case 897428293:
                        if (nextName.equals(JsonKeys.STORAGE_SIZE)) {
                            c = 27;
                            break;
                        }
                        break;
                    case 1331465768:
                        if (nextName.equals(JsonKeys.USABLE_MEMORY)) {
                            c = 28;
                            break;
                        }
                        break;
                    case 1418777727:
                        if (nextName.equals("memory_size")) {
                            c = 29;
                            break;
                        }
                        break;
                    case 1436115569:
                        if (nextName.equals(JsonKeys.CHARGING)) {
                            c = 30;
                            break;
                        }
                        break;
                    case 1450613660:
                        if (nextName.equals(JsonKeys.EXTERNAL_FREE_STORAGE)) {
                            c = 31;
                            break;
                        }
                        break;
                    case 1524159400:
                        if (nextName.equals(JsonKeys.FREE_STORAGE)) {
                            c = ' ';
                            break;
                        }
                        break;
                    case 1556284978:
                        if (nextName.equals(JsonKeys.SCREEN_HEIGHT_PIXELS)) {
                            c = '!';
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        device.timezone = objectReader.nextTimeZoneOrNull(iLogger);
                        break;
                    case 1:
                        if (objectReader.peek() != JsonToken.STRING) {
                            break;
                        } else {
                            device.bootTime = objectReader.nextDateOrNull(iLogger);
                            break;
                        }
                    case 2:
                        device.simulator = objectReader.nextBooleanOrNull();
                        break;
                    case 3:
                        device.manufacturer = objectReader.nextStringOrNull();
                        break;
                    case 4:
                        device.processorCount = objectReader.nextIntegerOrNull();
                        break;
                    case 5:
                        device.orientation = (DeviceOrientation) objectReader.nextOrNull(iLogger, new DeviceOrientation.Deserializer());
                        break;
                    case 6:
                        device.batteryTemperature = objectReader.nextFloatOrNull();
                        break;
                    case 7:
                        device.family = objectReader.nextStringOrNull();
                        break;
                    case '\b':
                        device.locale = objectReader.nextStringOrNull();
                        break;
                    case '\t':
                        device.online = objectReader.nextBooleanOrNull();
                        break;
                    case '\n':
                        device.batteryLevel = objectReader.nextFloatOrNull();
                        break;
                    case 11:
                        device.modelId = objectReader.nextStringOrNull();
                        break;
                    case '\f':
                        device.screenDensity = objectReader.nextFloatOrNull();
                        break;
                    case '\r':
                        device.screenDpi = objectReader.nextIntegerOrNull();
                        break;
                    case 14:
                        device.freeMemory = objectReader.nextLongOrNull();
                        break;
                    case 15:
                        device.id = objectReader.nextStringOrNull();
                        break;
                    case 16:
                        device.name = objectReader.nextStringOrNull();
                        break;
                    case 17:
                        device.lowMemory = objectReader.nextBooleanOrNull();
                        break;
                    case 18:
                        List list = (List) objectReader.nextObjectOrNull();
                        if (list == null) {
                            break;
                        } else {
                            String[] strArr = new String[list.size()];
                            list.toArray(strArr);
                            device.archs = strArr;
                            break;
                        }
                    case 19:
                        device.brand = objectReader.nextStringOrNull();
                        break;
                    case 20:
                        device.model = objectReader.nextStringOrNull();
                        break;
                    case 21:
                        device.cpuDescription = objectReader.nextStringOrNull();
                        break;
                    case 22:
                        device.processorFrequency = objectReader.nextDoubleOrNull();
                        break;
                    case 23:
                        device.connectionType = objectReader.nextStringOrNull();
                        break;
                    case 24:
                        device.chipset = objectReader.nextStringOrNull();
                        break;
                    case 25:
                        device.screenWidthPixels = objectReader.nextIntegerOrNull();
                        break;
                    case 26:
                        device.externalStorageSize = objectReader.nextLongOrNull();
                        break;
                    case 27:
                        device.storageSize = objectReader.nextLongOrNull();
                        break;
                    case 28:
                        device.usableMemory = objectReader.nextLongOrNull();
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                        device.memorySize = objectReader.nextLongOrNull();
                        break;
                    case 30:
                        device.charging = objectReader.nextBooleanOrNull();
                        break;
                    case 31:
                        device.externalFreeStorage = objectReader.nextLongOrNull();
                        break;
                    case ' ':
                        device.freeStorage = objectReader.nextLongOrNull();
                        break;
                    case '!':
                        device.screenHeightPixels = objectReader.nextIntegerOrNull();
                        break;
                    default:
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        objectReader.nextUnknown(iLogger, concurrentHashMap, nextName);
                        break;
                }
            }
            device.setUnknown(concurrentHashMap);
            objectReader.endObject();
            return device;
        }
    }
}
