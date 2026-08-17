package ch.coop.apidia.appGateway.loyalty.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.salesforce.marketingcloud.push.g;
import java.io.IOException;

@JsonAdapter(Adapter.class)
/* loaded from: classes3.dex */
public enum Language {
    DE("de"),
    FR("fr"),
    IT(g.h),
    EN("en"),
    UNKNOWN_DEFAULT_OPEN_API("unknown_default_open_api");

    private String value;

    Language(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return String.valueOf(this.value);
    }

    public static Language fromValue(String str) {
        for (Language language : values()) {
            if (language.value.equals(str)) {
                return language;
            }
        }
        return UNKNOWN_DEFAULT_OPEN_API;
    }

    public static class Adapter extends TypeAdapter<Language> {
        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Language language) throws IOException {
            jsonWriter.value(language.getValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Language read(JsonReader jsonReader) throws IOException {
            return Language.fromValue(jsonReader.nextString());
        }
    }
}
