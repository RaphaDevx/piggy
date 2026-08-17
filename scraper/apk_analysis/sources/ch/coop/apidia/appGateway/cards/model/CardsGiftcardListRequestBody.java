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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class CardsGiftcardListRequestBody {
    public static final String SERIALIZED_NAME_CARDS = "cards";
    public static final String SERIALIZED_NAME_CARD_NR_FILTER = "cardNrFilter";
    public static final String SERIALIZED_NAME_CARD_TYPES = "cardTypes";
    public static final String SERIALIZED_NAME_EMPLOYEE_DISCOUNT_CARD = "employeeDiscountCard";
    public static final String SERIALIZED_NAME_TRANSACTION_DAYS = "transactionDays";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName(SERIALIZED_NAME_CARD_TYPES)
    private List<CardsGiftcardTypeEnum> cardTypes;

    @SerializedName("cards")
    private List<CardsGiftcardCredentials> cards;

    @SerializedName(SERIALIZED_NAME_EMPLOYEE_DISCOUNT_CARD)
    private CardsGiftcardListRequestBodyEmployeeDiscountCard employeeDiscountCard;
    private Map<String, Object> undeclaredProperties;

    @SerializedName("transactionDays")
    private Integer transactionDays = 0;

    @SerializedName(SERIALIZED_NAME_CARD_NR_FILTER)
    private List<String> cardNrFilter = null;

    public CardsGiftcardListRequestBody cards(List<CardsGiftcardCredentials> list) {
        this.cards = list;
        return this;
    }

    public CardsGiftcardListRequestBody addCardsItem(CardsGiftcardCredentials cardsGiftcardCredentials) {
        if (this.cards == null) {
            this.cards = new ArrayList();
        }
        this.cards.add(cardsGiftcardCredentials);
        return this;
    }

    @Nullable
    public List<CardsGiftcardCredentials> getCards() {
        return this.cards;
    }

    public void setCards(List<CardsGiftcardCredentials> list) {
        this.cards = list;
    }

    public CardsGiftcardListRequestBody employeeDiscountCard(CardsGiftcardListRequestBodyEmployeeDiscountCard cardsGiftcardListRequestBodyEmployeeDiscountCard) {
        this.employeeDiscountCard = cardsGiftcardListRequestBodyEmployeeDiscountCard;
        return this;
    }

    @Nullable
    public CardsGiftcardListRequestBodyEmployeeDiscountCard getEmployeeDiscountCard() {
        return this.employeeDiscountCard;
    }

    public void setEmployeeDiscountCard(CardsGiftcardListRequestBodyEmployeeDiscountCard cardsGiftcardListRequestBodyEmployeeDiscountCard) {
        this.employeeDiscountCard = cardsGiftcardListRequestBodyEmployeeDiscountCard;
    }

    public CardsGiftcardListRequestBody transactionDays(Integer num) {
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

    public CardsGiftcardListRequestBody cardTypes(List<CardsGiftcardTypeEnum> list) {
        this.cardTypes = list;
        return this;
    }

    public CardsGiftcardListRequestBody addCardTypesItem(CardsGiftcardTypeEnum cardsGiftcardTypeEnum) {
        if (this.cardTypes == null) {
            this.cardTypes = new ArrayList();
        }
        this.cardTypes.add(cardsGiftcardTypeEnum);
        return this;
    }

    @Nullable
    public List<CardsGiftcardTypeEnum> getCardTypes() {
        return this.cardTypes;
    }

    public void setCardTypes(List<CardsGiftcardTypeEnum> list) {
        this.cardTypes = list;
    }

    public CardsGiftcardListRequestBody cardNrFilter(List<String> list) {
        this.cardNrFilter = list;
        return this;
    }

    public CardsGiftcardListRequestBody addCardNrFilterItem(String str) {
        if (this.cardNrFilter == null) {
            this.cardNrFilter = null;
        }
        this.cardNrFilter.add(str);
        return this;
    }

    @Nullable
    public List<String> getCardNrFilter() {
        return this.cardNrFilter;
    }

    public void setCardNrFilter(List<String> list) {
        this.cardNrFilter = list;
    }

    public CardsGiftcardListRequestBody putUndeclaredProperty(String str, Object obj) {
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
        CardsGiftcardListRequestBody cardsGiftcardListRequestBody = (CardsGiftcardListRequestBody) obj;
        return Objects.equals(this.cards, cardsGiftcardListRequestBody.cards) && Objects.equals(this.employeeDiscountCard, cardsGiftcardListRequestBody.employeeDiscountCard) && Objects.equals(this.transactionDays, cardsGiftcardListRequestBody.transactionDays) && Objects.equals(this.cardTypes, cardsGiftcardListRequestBody.cardTypes) && Objects.equals(this.cardNrFilter, cardsGiftcardListRequestBody.cardNrFilter) && Objects.equals(this.undeclaredProperties, cardsGiftcardListRequestBody.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.cards, this.employeeDiscountCard, this.transactionDays, this.cardTypes, this.cardNrFilter, this.undeclaredProperties);
    }

    public String toString() {
        return "class CardsGiftcardListRequestBody {\n    cards: " + toIndentedString(this.cards) + "\n    employeeDiscountCard: " + toIndentedString(this.employeeDiscountCard) + "\n    transactionDays: " + toIndentedString(this.transactionDays) + "\n    cardTypes: " + toIndentedString(this.cardTypes) + "\n    cardNrFilter: " + toIndentedString(this.cardNrFilter) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        openapiFields.add(SERIALIZED_NAME_EMPLOYEE_DISCOUNT_CARD);
        openapiFields.add("transactionDays");
        openapiFields.add(SERIALIZED_NAME_CARD_TYPES);
        openapiFields.add(SERIALIZED_NAME_CARD_NR_FILTER);
        openapiRequiredFields = new HashSet<>();
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        JsonArray asJsonArray;
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in CardsGiftcardListRequestBody is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        if (jsonObject.get("cards") != null && !jsonObject.get("cards").isJsonNull() && (asJsonArray = jsonObject.getAsJsonArray("cards")) != null) {
            if (!jsonObject.get("cards").isJsonArray()) {
                throw new IllegalArgumentException(String.format("Expected the field `cards` to be an array in the JSON string but got `%s`", jsonObject.get("cards").toString()));
            }
            for (int i = 0; i < asJsonArray.size(); i++) {
                CardsGiftcardCredentials.validateJsonObject(asJsonArray.get(i).getAsJsonObject());
            }
        }
        if (jsonObject.get(SERIALIZED_NAME_EMPLOYEE_DISCOUNT_CARD) != null && !jsonObject.get(SERIALIZED_NAME_EMPLOYEE_DISCOUNT_CARD).isJsonNull()) {
            CardsGiftcardListRequestBodyEmployeeDiscountCard.validateJsonObject(jsonObject.getAsJsonObject(SERIALIZED_NAME_EMPLOYEE_DISCOUNT_CARD));
        }
        if (jsonObject.get(SERIALIZED_NAME_CARD_TYPES) != null && !jsonObject.get(SERIALIZED_NAME_CARD_TYPES).isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `cardTypes` to be an array in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_CARD_TYPES).toString()));
        }
        if (jsonObject.get(SERIALIZED_NAME_CARD_NR_FILTER) != null && !jsonObject.get(SERIALIZED_NAME_CARD_NR_FILTER).isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `cardNrFilter` to be an array in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_CARD_NR_FILTER).toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!CardsGiftcardListRequestBody.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(CardsGiftcardListRequestBody.class));
            return (TypeAdapter<T>) new TypeAdapter<CardsGiftcardListRequestBody>() { // from class: ch.coop.apidia.appGateway.cards.model.CardsGiftcardListRequestBody.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, CardsGiftcardListRequestBody cardsGiftcardListRequestBody) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(cardsGiftcardListRequestBody).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (cardsGiftcardListRequestBody.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : cardsGiftcardListRequestBody.getUndeclaredProperties().entrySet()) {
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
                public CardsGiftcardListRequestBody read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    CardsGiftcardListRequestBody.validateJsonObject(asJsonObject);
                    CardsGiftcardListRequestBody cardsGiftcardListRequestBody = (CardsGiftcardListRequestBody) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!CardsGiftcardListRequestBody.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    cardsGiftcardListRequestBody.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    cardsGiftcardListRequestBody.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    cardsGiftcardListRequestBody.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                cardsGiftcardListRequestBody.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                cardsGiftcardListRequestBody.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return cardsGiftcardListRequestBody;
                }
            }.nullSafe();
        }
    }

    public static CardsGiftcardListRequestBody fromJson(String str) throws IOException {
        return (CardsGiftcardListRequestBody) JSON.getGson().fromJson(str, CardsGiftcardListRequestBody.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
