package ch.coop.apidia.appGateway.cards.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

@JsonAdapter(Adapter.class)
/* loaded from: classes3.dex */
public enum CardsAccountDigitalPaymentCardLoadWithSuperpointsStatus {
    YEARLY_LIMIT_REACHED("YEARLY_LIMIT_REACHED"),
    DIGITAL_PAYMENT_CARD_LIMIT_REACHED("DIGITAL_PAYMENT_CARD_LIMIT_REACHED"),
    NOT_ENOUGH_POINTS("NOT_ENOUGH_POINTS"),
    DAILY_LIMIT_REACHED("DAILY_LIMIT_REACHED"),
    OK("OK"),
    UNKNOWN_DEFAULT_OPEN_API("unknown_default_open_api");

    private String value;

    CardsAccountDigitalPaymentCardLoadWithSuperpointsStatus(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return String.valueOf(this.value);
    }

    public static CardsAccountDigitalPaymentCardLoadWithSuperpointsStatus fromValue(String str) {
        for (CardsAccountDigitalPaymentCardLoadWithSuperpointsStatus cardsAccountDigitalPaymentCardLoadWithSuperpointsStatus : values()) {
            if (cardsAccountDigitalPaymentCardLoadWithSuperpointsStatus.value.equals(str)) {
                return cardsAccountDigitalPaymentCardLoadWithSuperpointsStatus;
            }
        }
        return UNKNOWN_DEFAULT_OPEN_API;
    }

    public static class Adapter extends TypeAdapter<CardsAccountDigitalPaymentCardLoadWithSuperpointsStatus> {
        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, CardsAccountDigitalPaymentCardLoadWithSuperpointsStatus cardsAccountDigitalPaymentCardLoadWithSuperpointsStatus) throws IOException {
            jsonWriter.value(cardsAccountDigitalPaymentCardLoadWithSuperpointsStatus.getValue());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatus read(JsonReader jsonReader) throws IOException {
            return CardsAccountDigitalPaymentCardLoadWithSuperpointsStatus.fromValue(jsonReader.nextString());
        }
    }
}
