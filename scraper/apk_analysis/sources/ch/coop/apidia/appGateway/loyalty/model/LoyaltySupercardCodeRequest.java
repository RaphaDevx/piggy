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
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class LoyaltySupercardCodeRequest {
    public static final String SERIALIZED_NAME_FORMAT = "format";
    public static final String SERIALIZED_NAME_HASH = "hash";
    public static final String SERIALIZED_NAME_UUID = "uuid";
    public static final String SERIALIZED_NAME_VERSION = "version";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName("format")
    private String format = "default";

    @SerializedName("hash")
    private String hash;
    private Map<String, Object> undeclaredProperties;

    @SerializedName("uuid")
    private String uuid;

    @SerializedName("version")
    private Integer version;

    public LoyaltySupercardCodeRequest format(String str) {
        this.format = str;
        return this;
    }

    @Nullable
    public String getFormat() {
        return this.format;
    }

    public void setFormat(String str) {
        this.format = str;
    }

    public LoyaltySupercardCodeRequest hash(String str) {
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

    public LoyaltySupercardCodeRequest uuid(String str) {
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

    public LoyaltySupercardCodeRequest version(Integer num) {
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

    public LoyaltySupercardCodeRequest putUndeclaredProperty(String str, Object obj) {
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
        LoyaltySupercardCodeRequest loyaltySupercardCodeRequest = (LoyaltySupercardCodeRequest) obj;
        return Objects.equals(this.format, loyaltySupercardCodeRequest.format) && Objects.equals(this.hash, loyaltySupercardCodeRequest.hash) && Objects.equals(this.uuid, loyaltySupercardCodeRequest.uuid) && Objects.equals(this.version, loyaltySupercardCodeRequest.version) && Objects.equals(this.undeclaredProperties, loyaltySupercardCodeRequest.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.format, this.hash, this.uuid, this.version, this.undeclaredProperties);
    }

    public String toString() {
        return "class LoyaltySupercardCodeRequest {\n    format: " + toIndentedString(this.format) + "\n    hash: " + toIndentedString(this.hash) + "\n    uuid: " + toIndentedString(this.uuid) + "\n    version: " + toIndentedString(this.version) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add("format");
        openapiFields.add("hash");
        openapiFields.add("uuid");
        openapiFields.add("version");
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add("uuid");
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in LoyaltySupercardCodeRequest is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
        if (jsonObject.get("format") != null && !jsonObject.get("format").isJsonNull() && !jsonObject.get("format").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `format` to be a primitive type in the JSON string but got `%s`", jsonObject.get("format").toString()));
        }
        if (jsonObject.get("hash") != null && !jsonObject.get("hash").isJsonNull() && !jsonObject.get("hash").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `hash` to be a primitive type in the JSON string but got `%s`", jsonObject.get("hash").toString()));
        }
        if (!jsonObject.get("uuid").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `uuid` to be a primitive type in the JSON string but got `%s`", jsonObject.get("uuid").toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!LoyaltySupercardCodeRequest.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(LoyaltySupercardCodeRequest.class));
            return (TypeAdapter<T>) new TypeAdapter<LoyaltySupercardCodeRequest>() { // from class: ch.coop.apidia.appGateway.loyalty.model.LoyaltySupercardCodeRequest.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, LoyaltySupercardCodeRequest loyaltySupercardCodeRequest) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(loyaltySupercardCodeRequest).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (loyaltySupercardCodeRequest.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : loyaltySupercardCodeRequest.getUndeclaredProperties().entrySet()) {
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
                public LoyaltySupercardCodeRequest read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    LoyaltySupercardCodeRequest.validateJsonObject(asJsonObject);
                    LoyaltySupercardCodeRequest loyaltySupercardCodeRequest = (LoyaltySupercardCodeRequest) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!LoyaltySupercardCodeRequest.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    loyaltySupercardCodeRequest.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    loyaltySupercardCodeRequest.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    loyaltySupercardCodeRequest.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                loyaltySupercardCodeRequest.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                loyaltySupercardCodeRequest.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return loyaltySupercardCodeRequest;
                }
            }.nullSafe();
        }
    }

    public static LoyaltySupercardCodeRequest fromJson(String str) throws IOException {
        return (LoyaltySupercardCodeRequest) JSON.getGson().fromJson(str, LoyaltySupercardCodeRequest.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
