package ch.coop.apidia.appGateway.cards.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

@JsonAdapter(Adapter.class)
/* loaded from: classes3.dex */
public enum CardsGiftcardTypeEnum {
    DIGITAL_PAYMENT_CARD("DIGITAL_PAYMENT_CARD"),
    EMPLOYEE_GIFTCARD("EMPLOYEE_GIFTCARD"),
    GIFTCARD("GIFTCARD"),
    UNKNOWN_DEFAULT_OPEN_API("unknown_default_open_api");

    private String value;

    CardsGiftcardTypeEnum(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return String.valueOf(this.value);
    }

    public static CardsGiftcardTypeEnum fromValue(String str) {
        for (CardsGiftcardTypeEnum cardsGiftcardTypeEnum : values()) {
            if (cardsGiftcardTypeEnum.value.equals(str)) {
                return cardsGiftcardTypeEnum;
            }
        }
        return UNKNOWN_DEFAULT_OPEN_API;
    }

    public static class Adapter extends TypeAdapter<CardsGiftcardTypeEnum> {
        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, CardsGiftcardTypeEnum cardsGiftcardTypeEnum) throws IOException {
            jsonWriter.value(cardsGiftcardTypeEnum.getValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public CardsGiftcardTypeEnum read(JsonReader jsonReader) throws IOException {
            return CardsGiftcardTypeEnum.fromValue(jsonReader.nextString());
        }
    }
}
