package ch.coop.apidia.appGateway.cards.model;

import ch.coop.apidia.appGateway.cards.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class CardsGiftcardResponse {
    public static final String SERIALIZED_NAME_CARDS = "cards";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName("cards")
    private List<CardsGiftcard> cards = new ArrayList();
    private Map<String, Object> undeclaredProperties;

    public CardsGiftcardResponse cards(List<CardsGiftcard> list) {
        this.cards = list;
        return this;
    }

    public CardsGiftcardResponse addCardsItem(CardsGiftcard cardsGiftcard) {
        if (this.cards == null) {
            this.cards = new ArrayList();
        }
        this.cards.add(cardsGiftcard);
        return this;
    }

    @Nonnull
    public List<CardsGiftcard> getCards() {
        return this.cards;
    }

    public void setCards(List<CardsGiftcard> list) {
        this.cards = list;
    }

    public CardsGiftcardResponse putUndeclaredProperty(String str, Object obj) {
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
        CardsGiftcardResponse cardsGiftcardResponse = (CardsGiftcardResponse) obj;
        return Objects.equals(this.cards, cardsGiftcardResponse.cards) && Objects.equals(this.undeclaredProperties, cardsGiftcardResponse.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.cards, this.undeclaredProperties);
    }

    public String toString() {
        return "class CardsGiftcardResponse {\n    cards: " + toIndentedString(this.cards) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add("cards");
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add("cards");
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in CardsGiftcardResponse is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
        if (!jsonObject.get("cards").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `cards` to be an array in the JSON string but got `%s`", jsonObject.get("cards").toString()));
        }
        JsonArray asJsonArray = jsonObject.getAsJsonArray("cards");
        for (int i = 0; i < asJsonArray.size(); i++) {
            CardsGiftcard.validateJsonObject(asJsonArray.get(i).getAsJsonObject());
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!CardsGiftcardResponse.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(CardsGiftcardResponse.class));
            return (TypeAdapter<T>) new TypeAdapter<CardsGiftcardResponse>() { // from class: ch.coop.apidia.appGateway.cards.model.CardsGiftcardResponse.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, CardsGiftcardResponse cardsGiftcardResponse) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(cardsGiftcardResponse).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (cardsGiftcardResponse.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : cardsGiftcardResponse.getUndeclaredProperties().entrySet()) {
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
                public CardsGiftcardResponse read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    CardsGiftcardResponse.validateJsonObject(asJsonObject);
                    CardsGiftcardResponse cardsGiftcardResponse = (CardsGiftcardResponse) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!CardsGiftcardResponse.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    cardsGiftcardResponse.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    cardsGiftcardResponse.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    cardsGiftcardResponse.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                cardsGiftcardResponse.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                cardsGiftcardResponse.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return cardsGiftcardResponse;
                }
            }.nullSafe();
        }
    }

    public static CardsGiftcardResponse fromJson(String str) throws IOException {
        return (CardsGiftcardResponse) JSON.getGson().fromJson(str, CardsGiftcardResponse.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
