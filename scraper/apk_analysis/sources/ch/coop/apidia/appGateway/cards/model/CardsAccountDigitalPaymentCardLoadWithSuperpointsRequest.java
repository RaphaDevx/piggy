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
public class CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest {
    public static final String SERIALIZED_NAME_CODE = "code";
    public static final String SERIALIZED_NAME_NR_BLOCKS = "nrBlocks";
    public static final String SERIALIZED_NAME_UUID = "uuid";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName("code")
    private String code;

    @SerializedName(SERIALIZED_NAME_NR_BLOCKS)
    private Integer nrBlocks;
    private Map<String, Object> undeclaredProperties;

    @SerializedName("uuid")
    private String uuid;

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest code(String str) {
        this.code = str;
        return this;
    }

    @Nonnull
    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest nrBlocks(Integer num) {
        this.nrBlocks = num;
        return this;
    }

    @Nonnull
    public Integer getNrBlocks() {
        return this.nrBlocks;
    }

    public void setNrBlocks(Integer num) {
        this.nrBlocks = num;
    }

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest uuid(String str) {
        this.uuid = str;
        return this;
    }

    @Nonnull
    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest putUndeclaredProperty(String str, Object obj) {
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
        CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest = (CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest) obj;
        return Objects.equals(this.code, cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest.code) && Objects.equals(this.nrBlocks, cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest.nrBlocks) && Objects.equals(this.uuid, cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest.uuid) && Objects.equals(this.undeclaredProperties, cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.code, this.nrBlocks, this.uuid, this.undeclaredProperties);
    }

    public String toString() {
        return "class CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest {\n    code: " + toIndentedString(this.code) + "\n    nrBlocks: " + toIndentedString(this.nrBlocks) + "\n    uuid: " + toIndentedString(this.uuid) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add("code");
        openapiFields.add(SERIALIZED_NAME_NR_BLOCKS);
        openapiFields.add("uuid");
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add("code");
        openapiRequiredFields.add(SERIALIZED_NAME_NR_BLOCKS);
        openapiRequiredFields.add("uuid");
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
        if (!jsonObject.get("code").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `code` to be a primitive type in the JSON string but got `%s`", jsonObject.get("code").toString()));
        }
        if (!jsonObject.get("uuid").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `uuid` to be a primitive type in the JSON string but got `%s`", jsonObject.get("uuid").toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest.class));
            return (TypeAdapter<T>) new TypeAdapter<CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest>() { // from class: ch.coop.apidia.appGateway.cards.model.CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest.getUndeclaredProperties().entrySet()) {
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
                public CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest.validateJsonObject(asJsonObject);
                    CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest = (CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return cardsAccountDigitalPaymentCardLoadWithSuperpointsRequest;
                }
            }.nullSafe();
        }
    }

    public static CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest fromJson(String str) throws IOException {
        return (CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest) JSON.getGson().fromJson(str, CardsAccountDigitalPaymentCardLoadWithSuperpointsRequest.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
