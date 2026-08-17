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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class CardsGiftcardLinkRequestBodyAllOf {
    public static final String SERIALIZED_NAME_TRANSACTION_DAYS = "transactionDays";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName("transactionDays")
    private Integer transactionDays = 0;
    private Map<String, Object> undeclaredProperties;

    public CardsGiftcardLinkRequestBodyAllOf transactionDays(Integer num) {
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

    public CardsGiftcardLinkRequestBodyAllOf putUndeclaredProperty(String str, Object obj) {
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
        CardsGiftcardLinkRequestBodyAllOf cardsGiftcardLinkRequestBodyAllOf = (CardsGiftcardLinkRequestBodyAllOf) obj;
        return Objects.equals(this.transactionDays, cardsGiftcardLinkRequestBodyAllOf.transactionDays) && Objects.equals(this.undeclaredProperties, cardsGiftcardLinkRequestBodyAllOf.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.transactionDays, this.undeclaredProperties);
    }

    public String toString() {
        return "class CardsGiftcardLinkRequestBodyAllOf {\n    transactionDays: " + toIndentedString(this.transactionDays) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add("transactionDays");
        openapiRequiredFields = new HashSet<>();
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in CardsGiftcardLinkRequestBodyAllOf is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!CardsGiftcardLinkRequestBodyAllOf.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(CardsGiftcardLinkRequestBodyAllOf.class));
            return (TypeAdapter<T>) new TypeAdapter<CardsGiftcardLinkRequestBodyAllOf>() { // from class: ch.coop.apidia.appGateway.cards.model.CardsGiftcardLinkRequestBodyAllOf.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, CardsGiftcardLinkRequestBodyAllOf cardsGiftcardLinkRequestBodyAllOf) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(cardsGiftcardLinkRequestBodyAllOf).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (cardsGiftcardLinkRequestBodyAllOf.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : cardsGiftcardLinkRequestBodyAllOf.getUndeclaredProperties().entrySet()) {
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
                public CardsGiftcardLinkRequestBodyAllOf read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    CardsGiftcardLinkRequestBodyAllOf.validateJsonObject(asJsonObject);
                    CardsGiftcardLinkRequestBodyAllOf cardsGiftcardLinkRequestBodyAllOf = (CardsGiftcardLinkRequestBodyAllOf) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!CardsGiftcardLinkRequestBodyAllOf.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    cardsGiftcardLinkRequestBodyAllOf.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    cardsGiftcardLinkRequestBodyAllOf.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    cardsGiftcardLinkRequestBodyAllOf.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                cardsGiftcardLinkRequestBodyAllOf.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                cardsGiftcardLinkRequestBodyAllOf.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return cardsGiftcardLinkRequestBodyAllOf;
                }
            }.nullSafe();
        }
    }

    public static CardsGiftcardLinkRequestBodyAllOf fromJson(String str) throws IOException {
        return (CardsGiftcardLinkRequestBodyAllOf) JSON.getGson().fromJson(str, CardsGiftcardLinkRequestBodyAllOf.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
