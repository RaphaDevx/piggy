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
import java.time.OffsetDateTime;
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
public class CardsGiftcardTransaction {
    public static final String SERIALIZED_NAME_AMOUNT = "amount";
    public static final String SERIALIZED_NAME_BALANCE_CURRENCY = "balanceCurrency";
    public static final String SERIALIZED_NAME_BALANCE_VALUE = "balanceValue";
    public static final String SERIALIZED_NAME_CURRENCY = "currency";
    public static final String SERIALIZED_NAME_DATE = "date";
    public static final String SERIALIZED_NAME_STORE_NAME = "storeName";
    public static final String SERIALIZED_NAME_TYPE = "type";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName(SERIALIZED_NAME_AMOUNT)
    private Integer amount;

    @SerializedName("balanceCurrency")
    private String balanceCurrency;

    @SerializedName("balanceValue")
    private Integer balanceValue;

    @SerializedName("currency")
    private String currency;

    @SerializedName("date")
    private OffsetDateTime date;

    @SerializedName("storeName")
    private String storeName;

    @SerializedName("type")
    private String type;
    private Map<String, Object> undeclaredProperties;

    public CardsGiftcardTransaction amount(Integer num) {
        this.amount = num;
        return this;
    }

    @Nonnull
    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer num) {
        this.amount = num;
    }

    public CardsGiftcardTransaction balanceValue(Integer num) {
        this.balanceValue = num;
        return this;
    }

    @Nonnull
    public Integer getBalanceValue() {
        return this.balanceValue;
    }

    public void setBalanceValue(Integer num) {
        this.balanceValue = num;
    }

    public CardsGiftcardTransaction balanceCurrency(String str) {
        this.balanceCurrency = str;
        return this;
    }

    @Nonnull
    public String getBalanceCurrency() {
        return this.balanceCurrency;
    }

    public void setBalanceCurrency(String str) {
        this.balanceCurrency = str;
    }

    public CardsGiftcardTransaction currency(String str) {
        this.currency = str;
        return this;
    }

    @Nonnull
    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public CardsGiftcardTransaction date(OffsetDateTime offsetDateTime) {
        this.date = offsetDateTime;
        return this;
    }

    @Nonnull
    public OffsetDateTime getDate() {
        return this.date;
    }

    public void setDate(OffsetDateTime offsetDateTime) {
        this.date = offsetDateTime;
    }

    public CardsGiftcardTransaction storeName(String str) {
        this.storeName = str;
        return this;
    }

    @Nullable
    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String str) {
        this.storeName = str;
    }

    public CardsGiftcardTransaction type(String str) {
        this.type = str;
        return this;
    }

    @Nonnull
    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public CardsGiftcardTransaction putUndeclaredProperty(String str, Object obj) {
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
        CardsGiftcardTransaction cardsGiftcardTransaction = (CardsGiftcardTransaction) obj;
        return Objects.equals(this.amount, cardsGiftcardTransaction.amount) && Objects.equals(this.balanceValue, cardsGiftcardTransaction.balanceValue) && Objects.equals(this.balanceCurrency, cardsGiftcardTransaction.balanceCurrency) && Objects.equals(this.currency, cardsGiftcardTransaction.currency) && Objects.equals(this.date, cardsGiftcardTransaction.date) && Objects.equals(this.storeName, cardsGiftcardTransaction.storeName) && Objects.equals(this.type, cardsGiftcardTransaction.type) && Objects.equals(this.undeclaredProperties, cardsGiftcardTransaction.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.amount, this.balanceValue, this.balanceCurrency, this.currency, this.date, this.storeName, this.type, this.undeclaredProperties);
    }

    public String toString() {
        return "class CardsGiftcardTransaction {\n    amount: " + toIndentedString(this.amount) + "\n    balanceValue: " + toIndentedString(this.balanceValue) + "\n    balanceCurrency: " + toIndentedString(this.balanceCurrency) + "\n    currency: " + toIndentedString(this.currency) + "\n    date: " + toIndentedString(this.date) + "\n    storeName: " + toIndentedString(this.storeName) + "\n    type: " + toIndentedString(this.type) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add(SERIALIZED_NAME_AMOUNT);
        openapiFields.add("balanceValue");
        openapiFields.add("balanceCurrency");
        openapiFields.add("currency");
        openapiFields.add("date");
        openapiFields.add("storeName");
        openapiFields.add("type");
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add(SERIALIZED_NAME_AMOUNT);
        openapiRequiredFields.add("balanceValue");
        openapiRequiredFields.add("balanceCurrency");
        openapiRequiredFields.add("currency");
        openapiRequiredFields.add("date");
        openapiRequiredFields.add("type");
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in CardsGiftcardTransaction is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
        if (!jsonObject.get("balanceCurrency").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `balanceCurrency` to be a primitive type in the JSON string but got `%s`", jsonObject.get("balanceCurrency").toString()));
        }
        if (!jsonObject.get("currency").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `currency` to be a primitive type in the JSON string but got `%s`", jsonObject.get("currency").toString()));
        }
        if (jsonObject.get("storeName") != null && !jsonObject.get("storeName").isJsonNull() && !jsonObject.get("storeName").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `storeName` to be a primitive type in the JSON string but got `%s`", jsonObject.get("storeName").toString()));
        }
        if (!jsonObject.get("type").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `type` to be a primitive type in the JSON string but got `%s`", jsonObject.get("type").toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!CardsGiftcardTransaction.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(CardsGiftcardTransaction.class));
            return (TypeAdapter<T>) new TypeAdapter<CardsGiftcardTransaction>() { // from class: ch.coop.apidia.appGateway.cards.model.CardsGiftcardTransaction.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, CardsGiftcardTransaction cardsGiftcardTransaction) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(cardsGiftcardTransaction).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (cardsGiftcardTransaction.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : cardsGiftcardTransaction.getUndeclaredProperties().entrySet()) {
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
                public CardsGiftcardTransaction read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    CardsGiftcardTransaction.validateJsonObject(asJsonObject);
                    CardsGiftcardTransaction cardsGiftcardTransaction = (CardsGiftcardTransaction) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!CardsGiftcardTransaction.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    cardsGiftcardTransaction.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    cardsGiftcardTransaction.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    cardsGiftcardTransaction.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                cardsGiftcardTransaction.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                cardsGiftcardTransaction.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return cardsGiftcardTransaction;
                }
            }.nullSafe();
        }
    }

    public static CardsGiftcardTransaction fromJson(String str) throws IOException {
        return (CardsGiftcardTransaction) JSON.getGson().fromJson(str, CardsGiftcardTransaction.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
