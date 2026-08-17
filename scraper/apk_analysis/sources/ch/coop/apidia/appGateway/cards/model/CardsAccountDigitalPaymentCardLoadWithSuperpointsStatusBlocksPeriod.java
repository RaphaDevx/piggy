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
public class CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod {
    public static final String SERIALIZED_NAME_MAX = "max";
    public static final String SERIALIZED_NAME_PREVIOUS = "previous";
    public static final String SERIALIZED_NAME_REMAINING = "remaining";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName(SERIALIZED_NAME_MAX)
    private Integer max;

    @SerializedName(SERIALIZED_NAME_PREVIOUS)
    private Integer previous;

    @SerializedName(SERIALIZED_NAME_REMAINING)
    private Integer remaining;
    private Map<String, Object> undeclaredProperties;

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod max(Integer num) {
        this.max = num;
        return this;
    }

    @Nonnull
    public Integer getMax() {
        return this.max;
    }

    public void setMax(Integer num) {
        this.max = num;
    }

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod previous(Integer num) {
        this.previous = num;
        return this;
    }

    @Nonnull
    public Integer getPrevious() {
        return this.previous;
    }

    public void setPrevious(Integer num) {
        this.previous = num;
    }

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod remaining(Integer num) {
        this.remaining = num;
        return this;
    }

    @Nonnull
    public Integer getRemaining() {
        return this.remaining;
    }

    public void setRemaining(Integer num) {
        this.remaining = num;
    }

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod putUndeclaredProperty(String str, Object obj) {
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
        CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod = (CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod) obj;
        return Objects.equals(this.max, cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.max) && Objects.equals(this.previous, cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.previous) && Objects.equals(this.remaining, cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.remaining) && Objects.equals(this.undeclaredProperties, cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.max, this.previous, this.remaining, this.undeclaredProperties);
    }

    public String toString() {
        return "class CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod {\n    max: " + toIndentedString(this.max) + "\n    previous: " + toIndentedString(this.previous) + "\n    remaining: " + toIndentedString(this.remaining) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add(SERIALIZED_NAME_MAX);
        openapiFields.add(SERIALIZED_NAME_PREVIOUS);
        openapiFields.add(SERIALIZED_NAME_REMAINING);
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add(SERIALIZED_NAME_MAX);
        openapiRequiredFields.add(SERIALIZED_NAME_PREVIOUS);
        openapiRequiredFields.add(SERIALIZED_NAME_REMAINING);
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.class));
            return (TypeAdapter<T>) new TypeAdapter<CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod>() { // from class: ch.coop.apidia.appGateway.cards.model.CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.getUndeclaredProperties().entrySet()) {
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
                public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.validateJsonObject(asJsonObject);
                    CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod = (CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod;
                }
            }.nullSafe();
        }
    }

    public static CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod fromJson(String str) throws IOException {
        return (CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod) JSON.getGson().fromJson(str, CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocksPeriod.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
