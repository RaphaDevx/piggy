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
public class CardsGiftcardLink {
    public static final String SERIALIZED_NAME_REFERENCE_CODE = "referenceCode";
    public static final String SERIALIZED_NAME_TYPE = "type";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName(SERIALIZED_NAME_REFERENCE_CODE)
    private String referenceCode;

    @SerializedName("type")
    private CardsGiftcardLinkTypeEnum type;
    private Map<String, Object> undeclaredProperties;

    public CardsGiftcardLink type(CardsGiftcardLinkTypeEnum cardsGiftcardLinkTypeEnum) {
        this.type = cardsGiftcardLinkTypeEnum;
        return this;
    }

    @Nonnull
    public CardsGiftcardLinkTypeEnum getType() {
        return this.type;
    }

    public void setType(CardsGiftcardLinkTypeEnum cardsGiftcardLinkTypeEnum) {
        this.type = cardsGiftcardLinkTypeEnum;
    }

    public CardsGiftcardLink referenceCode(String str) {
        this.referenceCode = str;
        return this;
    }

    @Nullable
    public String getReferenceCode() {
        return this.referenceCode;
    }

    public void setReferenceCode(String str) {
        this.referenceCode = str;
    }

    public CardsGiftcardLink putUndeclaredProperty(String str, Object obj) {
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
        CardsGiftcardLink cardsGiftcardLink = (CardsGiftcardLink) obj;
        return Objects.equals(this.type, cardsGiftcardLink.type) && Objects.equals(this.referenceCode, cardsGiftcardLink.referenceCode) && Objects.equals(this.undeclaredProperties, cardsGiftcardLink.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.type, this.referenceCode, this.undeclaredProperties);
    }

    public String toString() {
        return "class CardsGiftcardLink {\n    type: " + toIndentedString(this.type) + "\n    referenceCode: " + toIndentedString(this.referenceCode) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add("type");
        openapiFields.add(SERIALIZED_NAME_REFERENCE_CODE);
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add("type");
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in CardsGiftcardLink is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
        if (jsonObject.get(SERIALIZED_NAME_REFERENCE_CODE) != null && !jsonObject.get(SERIALIZED_NAME_REFERENCE_CODE).isJsonNull() && !jsonObject.get(SERIALIZED_NAME_REFERENCE_CODE).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `referenceCode` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_REFERENCE_CODE).toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!CardsGiftcardLink.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(CardsGiftcardLink.class));
            return (TypeAdapter<T>) new TypeAdapter<CardsGiftcardLink>() { // from class: ch.coop.apidia.appGateway.cards.model.CardsGiftcardLink.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, CardsGiftcardLink cardsGiftcardLink) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(cardsGiftcardLink).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (cardsGiftcardLink.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : cardsGiftcardLink.getUndeclaredProperties().entrySet()) {
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
                public CardsGiftcardLink read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    CardsGiftcardLink.validateJsonObject(asJsonObject);
                    CardsGiftcardLink cardsGiftcardLink = (CardsGiftcardLink) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!CardsGiftcardLink.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    cardsGiftcardLink.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    cardsGiftcardLink.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    cardsGiftcardLink.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                cardsGiftcardLink.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                cardsGiftcardLink.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return cardsGiftcardLink;
                }
            }.nullSafe();
        }
    }

    public static CardsGiftcardLink fromJson(String str) throws IOException {
        return (CardsGiftcardLink) JSON.getGson().fromJson(str, CardsGiftcardLink.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
