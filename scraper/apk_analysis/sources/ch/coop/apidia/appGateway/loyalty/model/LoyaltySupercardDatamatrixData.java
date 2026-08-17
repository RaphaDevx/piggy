package ch.coop.apidia.appGateway.loyalty.model;

import ch.coop.apidia.appGateway.loyalty.JSON;
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
public class LoyaltySupercardDatamatrixData {
    public static final String SERIALIZED_NAME_APP_ID = "appId";
    public static final String SERIALIZED_NAME_APP_ID_AI = "appIdAi";
    public static final String SERIALIZED_NAME_GUID = "guid";
    public static final String SERIALIZED_NAME_GUID_AI = "guidAi";
    public static final String SERIALIZED_NAME_SUPERCARD = "supercard";
    public static final String SERIALIZED_NAME_SUPERCARD_AI = "supercardAi";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName("appId")
    private String appId;

    @SerializedName(SERIALIZED_NAME_APP_ID_AI)
    private String appIdAi;

    @SerializedName(SERIALIZED_NAME_GUID)
    private String guid;

    @SerializedName(SERIALIZED_NAME_GUID_AI)
    private String guidAi;

    @SerializedName(SERIALIZED_NAME_SUPERCARD)
    private String supercard;

    @SerializedName(SERIALIZED_NAME_SUPERCARD_AI)
    private String supercardAi;
    private Map<String, Object> undeclaredProperties;

    public LoyaltySupercardDatamatrixData appId(String str) {
        this.appId = str;
        return this;
    }

    @Nonnull
    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public LoyaltySupercardDatamatrixData appIdAi(String str) {
        this.appIdAi = str;
        return this;
    }

    @Nonnull
    public String getAppIdAi() {
        return this.appIdAi;
    }

    public void setAppIdAi(String str) {
        this.appIdAi = str;
    }

    public LoyaltySupercardDatamatrixData guid(String str) {
        this.guid = str;
        return this;
    }

    @Nonnull
    public String getGuid() {
        return this.guid;
    }

    public void setGuid(String str) {
        this.guid = str;
    }

    public LoyaltySupercardDatamatrixData guidAi(String str) {
        this.guidAi = str;
        return this;
    }

    @Nonnull
    public String getGuidAi() {
        return this.guidAi;
    }

    public void setGuidAi(String str) {
        this.guidAi = str;
    }

    public LoyaltySupercardDatamatrixData supercard(String str) {
        this.supercard = str;
        return this;
    }

    @Nonnull
    public String getSupercard() {
        return this.supercard;
    }

    public void setSupercard(String str) {
        this.supercard = str;
    }

    public LoyaltySupercardDatamatrixData supercardAi(String str) {
        this.supercardAi = str;
        return this;
    }

    @Nonnull
    public String getSupercardAi() {
        return this.supercardAi;
    }

    public void setSupercardAi(String str) {
        this.supercardAi = str;
    }

    public LoyaltySupercardDatamatrixData putUndeclaredProperty(String str, Object obj) {
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
        LoyaltySupercardDatamatrixData loyaltySupercardDatamatrixData = (LoyaltySupercardDatamatrixData) obj;
        return Objects.equals(this.appId, loyaltySupercardDatamatrixData.appId) && Objects.equals(this.appIdAi, loyaltySupercardDatamatrixData.appIdAi) && Objects.equals(this.guid, loyaltySupercardDatamatrixData.guid) && Objects.equals(this.guidAi, loyaltySupercardDatamatrixData.guidAi) && Objects.equals(this.supercard, loyaltySupercardDatamatrixData.supercard) && Objects.equals(this.supercardAi, loyaltySupercardDatamatrixData.supercardAi) && Objects.equals(this.undeclaredProperties, loyaltySupercardDatamatrixData.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.appId, this.appIdAi, this.guid, this.guidAi, this.supercard, this.supercardAi, this.undeclaredProperties);
    }

    public String toString() {
        return "class LoyaltySupercardDatamatrixData {\n    appId: " + toIndentedString(this.appId) + "\n    appIdAi: " + toIndentedString(this.appIdAi) + "\n    guid: " + toIndentedString(this.guid) + "\n    guidAi: " + toIndentedString(this.guidAi) + "\n    supercard: " + toIndentedString(this.supercard) + "\n    supercardAi: " + toIndentedString(this.supercardAi) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add("appId");
        openapiFields.add(SERIALIZED_NAME_APP_ID_AI);
        openapiFields.add(SERIALIZED_NAME_GUID);
        openapiFields.add(SERIALIZED_NAME_GUID_AI);
        openapiFields.add(SERIALIZED_NAME_SUPERCARD);
        openapiFields.add(SERIALIZED_NAME_SUPERCARD_AI);
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add("appId");
        openapiRequiredFields.add(SERIALIZED_NAME_APP_ID_AI);
        openapiRequiredFields.add(SERIALIZED_NAME_GUID);
        openapiRequiredFields.add(SERIALIZED_NAME_GUID_AI);
        openapiRequiredFields.add(SERIALIZED_NAME_SUPERCARD);
        openapiRequiredFields.add(SERIALIZED_NAME_SUPERCARD_AI);
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in LoyaltySupercardDatamatrixData is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
        if (!jsonObject.get("appId").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `appId` to be a primitive type in the JSON string but got `%s`", jsonObject.get("appId").toString()));
        }
        if (!jsonObject.get(SERIALIZED_NAME_APP_ID_AI).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `appIdAi` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_APP_ID_AI).toString()));
        }
        if (!jsonObject.get(SERIALIZED_NAME_GUID).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `guid` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_GUID).toString()));
        }
        if (!jsonObject.get(SERIALIZED_NAME_GUID_AI).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `guidAi` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_GUID_AI).toString()));
        }
        if (!jsonObject.get(SERIALIZED_NAME_SUPERCARD).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `supercard` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_SUPERCARD).toString()));
        }
        if (!jsonObject.get(SERIALIZED_NAME_SUPERCARD_AI).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `supercardAi` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_SUPERCARD_AI).toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!LoyaltySupercardDatamatrixData.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(LoyaltySupercardDatamatrixData.class));
            return (TypeAdapter<T>) new TypeAdapter<LoyaltySupercardDatamatrixData>() { // from class: ch.coop.apidia.appGateway.loyalty.model.LoyaltySupercardDatamatrixData.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, LoyaltySupercardDatamatrixData loyaltySupercardDatamatrixData) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(loyaltySupercardDatamatrixData).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (loyaltySupercardDatamatrixData.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : loyaltySupercardDatamatrixData.getUndeclaredProperties().entrySet()) {
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
                public LoyaltySupercardDatamatrixData read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    LoyaltySupercardDatamatrixData.validateJsonObject(asJsonObject);
                    LoyaltySupercardDatamatrixData loyaltySupercardDatamatrixData = (LoyaltySupercardDatamatrixData) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!LoyaltySupercardDatamatrixData.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    loyaltySupercardDatamatrixData.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    loyaltySupercardDatamatrixData.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    loyaltySupercardDatamatrixData.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                loyaltySupercardDatamatrixData.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                loyaltySupercardDatamatrixData.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return loyaltySupercardDatamatrixData;
                }
            }.nullSafe();
        }
    }

    public static LoyaltySupercardDatamatrixData fromJson(String str) throws IOException {
        return (LoyaltySupercardDatamatrixData) JSON.getGson().fromJson(str, LoyaltySupercardDatamatrixData.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
