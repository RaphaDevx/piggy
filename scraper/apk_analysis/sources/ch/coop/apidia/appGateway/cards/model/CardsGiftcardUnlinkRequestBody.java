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
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class CardsGiftcardUnlinkRequestBody {
    public static final String SERIALIZED_NAME_CARD_NR = "cardNr";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName("cardNr")
    private String cardNr;
    private Map<String, Object> undeclaredProperties;

    public CardsGiftcardUnlinkRequestBody cardNr(String str) {
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

    public CardsGiftcardUnlinkRequestBody putUndeclaredProperty(String str, Object obj) {
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
        CardsGiftcardUnlinkRequestBody cardsGiftcardUnlinkRequestBody = (CardsGiftcardUnlinkRequestBody) obj;
        return Objects.equals(this.cardNr, cardsGiftcardUnlinkRequestBody.cardNr) && Objects.equals(this.undeclaredProperties, cardsGiftcardUnlinkRequestBody.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.cardNr, this.undeclaredProperties);
    }

    public String toString() {
        return "class CardsGiftcardUnlinkRequestBody {\n    cardNr: " + toIndentedString(this.cardNr) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add("cardNr");
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in CardsGiftcardUnlinkRequestBody is not found in the empty JSON string", openapiRequiredFields.toString()));
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
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!CardsGiftcardUnlinkRequestBody.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(CardsGiftcardUnlinkRequestBody.class));
            return (TypeAdapter<T>) new TypeAdapter<CardsGiftcardUnlinkRequestBody>() { // from class: ch.coop.apidia.appGateway.cards.model.CardsGiftcardUnlinkRequestBody.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, CardsGiftcardUnlinkRequestBody cardsGiftcardUnlinkRequestBody) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(cardsGiftcardUnlinkRequestBody).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (cardsGiftcardUnlinkRequestBody.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : cardsGiftcardUnlinkRequestBody.getUndeclaredProperties().entrySet()) {
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
                public CardsGiftcardUnlinkRequestBody read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    CardsGiftcardUnlinkRequestBody.validateJsonObject(asJsonObject);
                    CardsGiftcardUnlinkRequestBody cardsGiftcardUnlinkRequestBody = (CardsGiftcardUnlinkRequestBody) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!CardsGiftcardUnlinkRequestBody.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    cardsGiftcardUnlinkRequestBody.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    cardsGiftcardUnlinkRequestBody.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    cardsGiftcardUnlinkRequestBody.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                cardsGiftcardUnlinkRequestBody.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                cardsGiftcardUnlinkRequestBody.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return cardsGiftcardUnlinkRequestBody;
                }
            }.nullSafe();
        }
    }

    public static CardsGiftcardUnlinkRequestBody fromJson(String str) throws IOException {
        return (CardsGiftcardUnlinkRequestBody) JSON.getGson().fromJson(str, CardsGiftcardUnlinkRequestBody.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
