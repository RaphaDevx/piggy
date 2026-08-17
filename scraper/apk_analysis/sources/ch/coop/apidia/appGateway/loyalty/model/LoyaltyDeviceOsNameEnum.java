package ch.coop.apidia.appGateway.loyalty.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

@JsonAdapter(Adapter.class)
/* loaded from: classes3.dex */
public enum LoyaltyDeviceOsNameEnum {
    ANDROID("ANDROID"),
    IOS("IOS"),
    WATCHOS("WATCHOS"),
    MAC("MAC"),
    WINDOWS("WINDOWS"),
    ANDROID_WIDGET("ANDROID-WIDGET"),
    IOS_WIDGET("IOS-WIDGET"),
    UNKNOWN_DEFAULT_OPEN_API("unknown_default_open_api");

    private String value;

    LoyaltyDeviceOsNameEnum(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return String.valueOf(this.value);
    }

    public static LoyaltyDeviceOsNameEnum fromValue(String str) {
        for (LoyaltyDeviceOsNameEnum loyaltyDeviceOsNameEnum : values()) {
            if (loyaltyDeviceOsNameEnum.value.equals(str)) {
                return loyaltyDeviceOsNameEnum;
            }
        }
        return UNKNOWN_DEFAULT_OPEN_API;
    }

    public static class Adapter extends TypeAdapter<LoyaltyDeviceOsNameEnum> {
        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, LoyaltyDeviceOsNameEnum loyaltyDeviceOsNameEnum) throws IOException {
            jsonWriter.value(loyaltyDeviceOsNameEnum.getValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public LoyaltyDeviceOsNameEnum read(JsonReader jsonReader) throws IOException {
            return LoyaltyDeviceOsNameEnum.fromValue(jsonReader.nextString());
        }
    }
}
