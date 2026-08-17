package ch.coop.apidia.appGateway.cards.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.samsung.android.sdk.samsungpay.v2.card.Card;
import java.io.IOException;

@JsonAdapter(Adapter.class)
/* loaded from: classes3.dex */
public enum CardsGiftcardValidityStatusEnum {
    VALID("VALID"),
    EXPIRES_SOON("EXPIRES-SOON"),
    EXPIRED(Card.EXPIRED),
    UNKNOWN_DEFAULT_OPEN_API("unknown_default_open_api");

    private String value;

    CardsGiftcardValidityStatusEnum(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return String.valueOf(this.value);
    }

    public static CardsGiftcardValidityStatusEnum fromValue(String str) {
        for (CardsGiftcardValidityStatusEnum cardsGiftcardValidityStatusEnum : values()) {
            if (cardsGiftcardValidityStatusEnum.value.equals(str)) {
                return cardsGiftcardValidityStatusEnum;
            }
        }
        return UNKNOWN_DEFAULT_OPEN_API;
    }

    public static class Adapter extends TypeAdapter<CardsGiftcardValidityStatusEnum> {
        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, CardsGiftcardValidityStatusEnum cardsGiftcardValidityStatusEnum) throws IOException {
            jsonWriter.value(cardsGiftcardValidityStatusEnum.getValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public CardsGiftcardValidityStatusEnum read(JsonReader jsonReader) throws IOException {
            return CardsGiftcardValidityStatusEnum.fromValue(jsonReader.nextString());
        }
    }
}
