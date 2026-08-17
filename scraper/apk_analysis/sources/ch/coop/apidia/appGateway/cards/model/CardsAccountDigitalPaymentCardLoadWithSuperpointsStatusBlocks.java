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
public class CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks {
    public static final String SERIALIZED_NAME_DAILY = "daily";
    public static final String SERIALIZED_NAME_NEXT_EXCHANGE_MAX = "nextExchangeMax";
    public static final String SERIALIZED_NAME_YEARLY = "yearly";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName(SERIALIZED_NAME_DAILY)
    private CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod daily;

    @SerializedName(SERIALIZED_NAME_NEXT_EXCHANGE_MAX)
    private Integer nextExchangeMax;
    private Map<String, Object> undeclaredProperties;

    @SerializedName(SERIALIZED_NAME_YEARLY)
    private CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod yearly;

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks nextExchangeMax(Integer num) {
        this.nextExchangeMax = num;
        return this;
    }

    @Nonnull
    public Integer getNextExchangeMax() {
        return this.nextExchangeMax;
    }

    public void setNextExchangeMax(Integer num) {
        this.nextExchangeMax = num;
    }

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks daily(CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod) {
        this.daily = cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod;
        return this;
    }

    @Nonnull
    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod getDaily() {
        return this.daily;
    }

    public void setDaily(CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod) {
        this.daily = cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod;
    }

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks yearly(CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod) {
        this.yearly = cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod;
        return this;
    }

    @Nonnull
    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod getYearly() {
        return this.yearly;
    }

    public void setYearly(CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod) {
        this.yearly = cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod;
    }

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks putUndeclaredProperty(String str, Object obj) {
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
        CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks = (CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks) obj;
        return Objects.equals(this.nextExchangeMax, cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks.nextExchangeMax) && Objects.equals(this.daily, cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks.daily) && Objects.equals(this.yearly, cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks.yearly) && Objects.equals(this.undeclaredProperties, cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.nextExchangeMax, this.daily, this.yearly, this.undeclaredProperties);
    }

    public String toString() {
        return "class CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks {\n    nextExchangeMax: " + toIndentedString(this.nextExchangeMax) + "\n    daily: " + toIndentedString(this.daily) + "\n    yearly: " + toIndentedString(this.yearly) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add(SERIALIZED_NAME_NEXT_EXCHANGE_MAX);
        openapiFields.add(SERIALIZED_NAME_DAILY);
        openapiFields.add(SERIALIZED_NAME_YEARLY);
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add(SERIALIZED_NAME_NEXT_EXCHANGE_MAX);
        openapiRequiredFields.add(SERIALIZED_NAME_DAILY);
        openapiRequiredFields.add(SERIALIZED_NAME_YEARLY);
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
        CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.validateJsonObject(jsonObject.getAsJsonObject(SERIALIZED_NAME_DAILY));
        CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.validateJsonObject(jsonObject.getAsJsonObject(SERIALIZED_NAME_YEARLY));
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks.class));
            return (TypeAdapter<T>) new TypeAdapter<CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks>() { // from class: ch.coop.apidia.appGateway.cards.model.CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks.getUndeclaredProperties().entrySet()) {
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
                public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks.validateJsonObject(asJsonObject);
                    CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks = (CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks;
                }
            }.nullSafe();
        }
    }

    public static CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks fromJson(String str) throws IOException {
        return (CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks) JSON.getGson().fromJson(str, CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
