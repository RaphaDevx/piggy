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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class LoyaltySupercardCodeResponse {
    public static final String SERIALIZED_NAME_DATAMATRIX = "datamatrix";
    public static final String SERIALIZED_NAME_DATAMATRIX_IMAGE = "datamatrixImage";
    public static final String SERIALIZED_NAME_HASH = "hash";
    public static final String SERIALIZED_NAME_VERSION = "version";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName(SERIALIZED_NAME_DATAMATRIX)
    private LoyaltySupercardDatamatrixData datamatrix;

    @SerializedName(SERIALIZED_NAME_DATAMATRIX_IMAGE)
    private String datamatrixImage;

    @SerializedName("hash")
    private String hash;
    private Map<String, Object> undeclaredProperties;

    @SerializedName("version")
    private Integer version;

    public LoyaltySupercardCodeResponse datamatrix(LoyaltySupercardDatamatrixData loyaltySupercardDatamatrixData) {
        this.datamatrix = loyaltySupercardDatamatrixData;
        return this;
    }

    @Nullable
    public LoyaltySupercardDatamatrixData getDatamatrix() {
        return this.datamatrix;
    }

    public void setDatamatrix(LoyaltySupercardDatamatrixData loyaltySupercardDatamatrixData) {
        this.datamatrix = loyaltySupercardDatamatrixData;
    }

    public LoyaltySupercardCodeResponse datamatrixImage(String str) {
        this.datamatrixImage = str;
        return this;
    }

    @Nullable
    public String getDatamatrixImage() {
        return this.datamatrixImage;
    }

    public void setDatamatrixImage(String str) {
        this.datamatrixImage = str;
    }

    public LoyaltySupercardCodeResponse hash(String str) {
        this.hash = str;
        return this;
    }

    @Nullable
    public String getHash() {
        return this.hash;
    }

    public void setHash(String str) {
        this.hash = str;
    }

    public LoyaltySupercardCodeResponse version(Integer num) {
        this.version = num;
        return this;
    }

    @Nullable
    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer num) {
        this.version = num;
    }

    public LoyaltySupercardCodeResponse putUndeclaredProperty(String str, Object obj) {
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
        LoyaltySupercardCodeResponse loyaltySupercardCodeResponse = (LoyaltySupercardCodeResponse) obj;
        return Objects.equals(this.datamatrix, loyaltySupercardCodeResponse.datamatrix) && Objects.equals(this.datamatrixImage, loyaltySupercardCodeResponse.datamatrixImage) && Objects.equals(this.hash, loyaltySupercardCodeResponse.hash) && Objects.equals(this.version, loyaltySupercardCodeResponse.version) && Objects.equals(this.undeclaredProperties, loyaltySupercardCodeResponse.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.datamatrix, this.datamatrixImage, this.hash, this.version, this.undeclaredProperties);
    }

    public String toString() {
        return "class LoyaltySupercardCodeResponse {\n    datamatrix: " + toIndentedString(this.datamatrix) + "\n    datamatrixImage: " + toIndentedString(this.datamatrixImage) + "\n    hash: " + toIndentedString(this.hash) + "\n    version: " + toIndentedString(this.version) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add(SERIALIZED_NAME_DATAMATRIX);
        openapiFields.add(SERIALIZED_NAME_DATAMATRIX_IMAGE);
        openapiFields.add("hash");
        openapiFields.add("version");
        openapiRequiredFields = new HashSet<>();
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in LoyaltySupercardCodeResponse is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        if (jsonObject.get(SERIALIZED_NAME_DATAMATRIX) != null && !jsonObject.get(SERIALIZED_NAME_DATAMATRIX).isJsonNull()) {
            LoyaltySupercardDatamatrixData.validateJsonObject(jsonObject.getAsJsonObject(SERIALIZED_NAME_DATAMATRIX));
        }
        if (jsonObject.get(SERIALIZED_NAME_DATAMATRIX_IMAGE) != null && !jsonObject.get(SERIALIZED_NAME_DATAMATRIX_IMAGE).isJsonNull() && !jsonObject.get(SERIALIZED_NAME_DATAMATRIX_IMAGE).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `datamatrixImage` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_DATAMATRIX_IMAGE).toString()));
        }
        if (jsonObject.get("hash") != null && !jsonObject.get("hash").isJsonNull() && !jsonObject.get("hash").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `hash` to be a primitive type in the JSON string but got `%s`", jsonObject.get("hash").toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!LoyaltySupercardCodeResponse.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(LoyaltySupercardCodeResponse.class));
            return (TypeAdapter<T>) new TypeAdapter<LoyaltySupercardCodeResponse>() { // from class: ch.coop.apidia.appGateway.loyalty.model.LoyaltySupercardCodeResponse.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, LoyaltySupercardCodeResponse loyaltySupercardCodeResponse) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(loyaltySupercardCodeResponse).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (loyaltySupercardCodeResponse.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : loyaltySupercardCodeResponse.getUndeclaredProperties().entrySet()) {
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
                public LoyaltySupercardCodeResponse read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    LoyaltySupercardCodeResponse.validateJsonObject(asJsonObject);
                    LoyaltySupercardCodeResponse loyaltySupercardCodeResponse = (LoyaltySupercardCodeResponse) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!LoyaltySupercardCodeResponse.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    loyaltySupercardCodeResponse.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    loyaltySupercardCodeResponse.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    loyaltySupercardCodeResponse.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                loyaltySupercardCodeResponse.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                loyaltySupercardCodeResponse.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return loyaltySupercardCodeResponse;
                }
            }.nullSafe();
        }
    }

    public static LoyaltySupercardCodeResponse fromJson(String str) throws IOException {
        return (LoyaltySupercardCodeResponse) JSON.getGson().fromJson(str, LoyaltySupercardCodeResponse.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
