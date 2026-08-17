package ch.coop.apidia.appGateway.employeeDiscountCard.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

@JsonAdapter(Adapter.class)
/* loaded from: classes3.dex */
public enum ImageFormatEnum {
    APP_DETAIL("APP_DETAIL"),
    APP_LIST("APP_LIST"),
    DEFAULT("DEFAULT"),
    LOGO_SINGLE_PNG("LOGO_SINGLE_PNG"),
    LOGO_SINGLE_SVG("LOGO_SINGLE_SVG"),
    LOGO_SINGLE_CONTRAST_SVG("LOGO_SINGLE_CONTRAST_SVG"),
    LOGO_COMBINED_SVG("LOGO_COMBINED_SVG"),
    LOGO_BADGE_SVG("LOGO_BADGE_SVG"),
    LOGO_BADGE_PNG("LOGO_BADGE_PNG"),
    PHONE_3X("PHONE_3X"),
    PHONE_4X("PHONE_4X"),
    UNKNOWN_DEFAULT_OPEN_API("unknown_default_open_api");

    private String value;

    ImageFormatEnum(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return String.valueOf(this.value);
    }

    public static ImageFormatEnum fromValue(String str) {
        for (ImageFormatEnum imageFormatEnum : values()) {
            if (imageFormatEnum.value.equals(str)) {
                return imageFormatEnum;
            }
        }
        return UNKNOWN_DEFAULT_OPEN_API;
    }

    public static class Adapter extends TypeAdapter<ImageFormatEnum> {
        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, ImageFormatEnum imageFormatEnum) throws IOException {
            jsonWriter.value(imageFormatEnum.getValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public ImageFormatEnum read(JsonReader jsonReader) throws IOException {
            return ImageFormatEnum.fromValue(jsonReader.nextString());
        }
    }
}
