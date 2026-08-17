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
public class RestError {
    public static final String SERIALIZED_NAME_ADDITIONAL_PROPERTIES = "additionalProperties";
    public static final String SERIALIZED_NAME_ERROR_TYPE = "errorType";
    public static final String SERIALIZED_NAME_MESSAGE = "message";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName("additionalProperties")
    private Map<String, Object> additionalProperties = new HashMap();

    @SerializedName("errorType")
    private String errorType;

    @SerializedName("message")
    private String message;
    private Map<String, Object> undeclaredProperties;

    public RestError errorType(String str) {
        this.errorType = str;
        return this;
    }

    @Nonnull
    public String getErrorType() {
        return this.errorType;
    }

    public void setErrorType(String str) {
        this.errorType = str;
    }

    public RestError message(String str) {
        this.message = str;
        return this;
    }

    @Nonnull
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public RestError additionalProperties(Map<String, Object> map) {
        this.additionalProperties = map;
        return this;
    }

    public RestError putAdditionalPropertiesItem(String str, Object obj) {
        if (this.additionalProperties == null) {
            this.additionalProperties = new HashMap();
        }
        this.additionalProperties.put(str, obj);
        return this;
    }

    @Nullable
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> map) {
        this.additionalProperties = map;
    }

    public RestError putUndeclaredProperty(String str, Object obj) {
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
        RestError restError = (RestError) obj;
        return Objects.equals(this.errorType, restError.errorType) && Objects.equals(this.message, restError.message) && Objects.equals(this.additionalProperties, restError.additionalProperties) && Objects.equals(this.undeclaredProperties, restError.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.errorType, this.message, this.additionalProperties, this.undeclaredProperties);
    }

    public String toString() {
        return "class RestError {\n    errorType: " + toIndentedString(this.errorType) + "\n    message: " + toIndentedString(this.message) + "\n    additionalProperties: " + toIndentedString(this.additionalProperties) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add("errorType");
        openapiFields.add("message");
        openapiFields.add("additionalProperties");
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add("errorType");
        openapiRequiredFields.add("message");
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in RestError is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
        if (!jsonObject.get("errorType").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `errorType` to be a primitive type in the JSON string but got `%s`", jsonObject.get("errorType").toString()));
        }
        if (!jsonObject.get("message").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `message` to be a primitive type in the JSON string but got `%s`", jsonObject.get("message").toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!RestError.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(RestError.class));
            return (TypeAdapter<T>) new TypeAdapter<RestError>() { // from class: ch.coop.apidia.appGateway.cards.model.RestError.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, RestError restError) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(restError).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (restError.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : restError.getUndeclaredProperties().entrySet()) {
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
                public RestError read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    RestError.validateJsonObject(asJsonObject);
                    RestError restError = (RestError) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!RestError.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    restError.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    restError.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    restError.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                restError.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                restError.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return restError;
                }
            }.nullSafe();
        }
    }

    public static RestError fromJson(String str) throws IOException {
        return (RestError) JSON.getGson().fromJson(str, RestError.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
