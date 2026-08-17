package ch.coop.apidia.appGateway.cards.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

@JsonAdapter(Adapter.class)
/* loaded from: classes3.dex */
public enum CardsSupercardPayStatusEnum {
    ELIGIBLE("ELIGIBLE"),
    NOT_ELIGIBLE("NOT_ELIGIBLE"),
    DATATRANS_NEEDED("DATATRANS_NEEDED"),
    ONBOARDED("ONBOARDED"),
    UNKNOWN_DEFAULT_OPEN_API("unknown_default_open_api");

    private String value;

    CardsSupercardPayStatusEnum(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return String.valueOf(this.value);
    }

    public static CardsSupercardPayStatusEnum fromValue(String str) {
        for (CardsSupercardPayStatusEnum cardsSupercardPayStatusEnum : values()) {
            if (cardsSupercardPayStatusEnum.value.equals(str)) {
                return cardsSupercardPayStatusEnum;
            }
        }
        return UNKNOWN_DEFAULT_OPEN_API;
    }

    public static class Adapter extends TypeAdapter<CardsSupercardPayStatusEnum> {
        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, CardsSupercardPayStatusEnum cardsSupercardPayStatusEnum) throws IOException {
            jsonWriter.value(cardsSupercardPayStatusEnum.getValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public CardsSupercardPayStatusEnum read(JsonReader jsonReader) throws IOException {
            return CardsSupercardPayStatusEnum.fromValue(jsonReader.nextString());
        }
    }
}
