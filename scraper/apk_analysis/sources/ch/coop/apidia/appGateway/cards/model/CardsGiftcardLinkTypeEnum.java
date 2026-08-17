package ch.coop.apidia.appGateway.cards.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

@JsonAdapter(Adapter.class)
/* loaded from: classes3.dex */
public enum CardsGiftcardLinkTypeEnum {
    ACCOUNT("ACCOUNT"),
    EMPLOYEE_DISCOUNT_CARD("EMPLOYEE_DISCOUNT_CARD"),
    UNKNOWN_DEFAULT_OPEN_API("unknown_default_open_api");

    private String value;

    CardsGiftcardLinkTypeEnum(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return String.valueOf(this.value);
    }

    public static CardsGiftcardLinkTypeEnum fromValue(String str) {
        for (CardsGiftcardLinkTypeEnum cardsGiftcardLinkTypeEnum : values()) {
            if (cardsGiftcardLinkTypeEnum.value.equals(str)) {
                return cardsGiftcardLinkTypeEnum;
            }
        }
        return UNKNOWN_DEFAULT_OPEN_API;
    }

    public static class Adapter extends TypeAdapter<CardsGiftcardLinkTypeEnum> {
        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, CardsGiftcardLinkTypeEnum cardsGiftcardLinkTypeEnum) throws IOException {
            jsonWriter.value(cardsGiftcardLinkTypeEnum.getValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public CardsGiftcardLinkTypeEnum read(JsonReader jsonReader) throws IOException {
            return CardsGiftcardLinkTypeEnum.fromValue(jsonReader.nextString());
        }
    }
}
