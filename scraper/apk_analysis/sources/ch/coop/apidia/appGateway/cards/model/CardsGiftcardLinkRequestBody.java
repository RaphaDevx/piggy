package ch.coop.apidia.appGateway.cards.model;

import ch.coop.apidia.appGateway.cards.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class CardsGiftcardLinkRequestBody {
    public static final String SERIALIZED_NAME_CARD_NR = "cardNr";
    public static final String SERIALIZED_NAME_PIN = "pin";
    public static final String SERIALIZED_NAME_TRANSACTION_DAYS = "transactionDays";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName("cardNr")
    private String cardNr;

    @SerializedName("pin")
    private String pin;

    @SerializedName("transactionDays")
    private Integer transactionDays = 0;
    private Map<String, Object> undeclaredProperties;

    public CardsGiftcardLinkRequestBody cardNr(String str) {
        this.cardNr = str;
        return this;
    }

    @Nonnull
    public String getCardNr() {
        return this.cardNr;
    }

    public void setCardNr(String str) {
        this.cardNr = str;
    }

    public CardsGiftcardLinkRequestBody pin(String str) {
        this.pin = str;
        return this;
    }

    @Nonnull
    public String getPin() {
        return this.pin;
    }

    public void setPin(String str) {
        this.pin = str;
    }

    public CardsGiftcardLinkRequestBody transactionDays(Integer num) {
        this.transactionDays = num;
        return this;
    }

    @Nullable
    public Integer getTransactionDays() {
        return this.transactionDays;
    }

    public void setTransactionDays(Integer num) {
        this.transactionDays = num;
    }

    public CardsGiftcardLinkRequestBody putUndeclaredProperty(String str, Object obj) {
        if (this.undeclaredProperties == null) {
            this.undeclaredProperties = new HashMap();
        }
        this.undeclaredProperties.put(str, obj);
        return this;
    }

    public Map<String, Object> getUndeclaredProperties() {
        return this.undeclaredProperties;
    }

    public Object getUndeclaredProperty(String str) {
        Map<String, Object> map = this.undeclaredProperties;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CardsGiftcardLinkRequestBody cardsGiftcardLinkRequestBody = (CardsGiftcardLinkRequestBody) obj;
        return Objects.equals(this.cardNr, cardsGiftcardLinkRequestBody.cardNr) && Objects.equals(this.pin, cardsGiftcardLinkRequestBody.pin) && Objects.equals(this.transactionDays, cardsGiftcardLinkRequestBody.transactionDays) && Objects.equals(this.undeclaredProperties, cardsGiftcardLinkRequestBody.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.cardNr, this.pin, this.transactionDays, this.undeclaredProperties);
    }

    public String toString() {
        return "class CardsGiftcardLinkRequestBody {\n    cardNr: " + toIndentedString(this.cardNr) + "\n    pin: " + toIndentedString(this.pin) + "\n    transactionDays: " + toIndentedString(this.transactionDays) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
    }

    private String toIndentedString(Object obj) {
        if (obj == null) {
            return "null";
        }
        return obj.toString().replace(StringUtils.LF, "\n    ");
    }

    static {
        HashSet<String> hashSet = new HashSet<>();
        openapiFields = hashSet;
        hashSet.add("cardNr");
        openapiFields.add("pin");
        openapiFields.add("transactionDays");
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add("cardNr");
        openapiRequiredFields.add("pin");
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in CardsGiftcardLinkRequestBody is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
        if (!jsonObject.get("cardNr").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `cardNr` to be a primitive type in the JSON string but got `%s`", jsonObject.get("cardNr").toString()));
        }
        if (!jsonObject.get("pin").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `pin` to be a primitive type in the JSON string but got `%s`", jsonObject.get("pin").toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!CardsGiftcardLinkRequestBody.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(CardsGiftcardLinkRequestBody.class));
            return (TypeAdapter<T>) new TypeAdapter<CardsGiftcardLinkRequestBody>() { // from class: ch.coop.apidia.appGateway.cards.model.CardsGiftcardLinkRequestBody.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, CardsGiftcardLinkRequestBody cardsGiftcardLinkRequestBody) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(cardsGiftcardLinkRequestBody).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (cardsGiftcardLinkRequestBody.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : cardsGiftcardLinkRequestBody.getUndeclaredProperties().entrySet()) {
                            if (entry.getValue() instanceof String) {
                                asJsonObject.addProperty(entry.getKey(), (String) entry.getValue());
                            } else if (entry.getValue() instanceof Number) {
                                asJsonObject.addProperty(entry.getKey(), (Number) entry.getValue());
                            } else if (entry.getValue() instanceof Boolean) {
                                asJsonObject.addProperty(entry.getKey(), (Boolean) entry.getValue());
                            } else if (entry.getValue() instanceof Character) {
                                asJsonObject.addProperty(entry.getKey(), (Character) entry.getValue());
                            } else {
                                asJsonObject.add(entry.getKey(), gson.toJsonTree(entry.getValue()).getAsJsonObject());
                            }
                        }
                    }
                    adapter.write(jsonWriter, asJsonObject);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.google.gson.TypeAdapter
                public CardsGiftcardLinkRequestBody read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    CardsGiftcardLinkRequestBody.validateJsonObject(asJsonObject);
                    CardsGiftcardLinkRequestBody cardsGiftcardLinkRequestBody = (CardsGiftcardLinkRequestBody) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!CardsGiftcardLinkRequestBody.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    cardsGiftcardLinkRequestBody.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    cardsGiftcardLinkRequestBody.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    cardsGiftcardLinkRequestBody.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                cardsGiftcardLinkRequestBody.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                cardsGiftcardLinkRequestBody.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return cardsGiftcardLinkRequestBody;
                }
            }.nullSafe();
        }
    }

    public static CardsGiftcardLinkRequestBody fromJson(String str) throws IOException {
        return (CardsGiftcardLinkRequestBody) JSON.getGson().fromJson(str, CardsGiftcardLinkRequestBody.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
