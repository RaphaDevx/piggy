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
public class Rest500 {
    public static final String SERIALIZED_NAME_ERROR_TYPE = "errorType";
    public static final String SERIALIZED_NAME_MESSAGE = "message";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName("errorType")
    private String errorType;

    @SerializedName("message")
    private String message;
    private Map<String, Object> undeclaredProperties;

    public Rest500 errorType(String str) {
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

    public Rest500 message(String str) {
        this.message = str;
        return this;
    }

    @Nullable
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public Rest500 putUndeclaredProperty(String str, Object obj) {
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
        Rest500 rest500 = (Rest500) obj;
        return Objects.equals(this.errorType, rest500.errorType) && Objects.equals(this.message, rest500.message) && Objects.equals(this.undeclaredProperties, rest500.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.errorType, this.message, this.undeclaredProperties);
    }

    public String toString() {
        return "class Rest500 {\n    errorType: " + toIndentedString(this.errorType) + "\n    message: " + toIndentedString(this.message) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add("errorType");
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in Rest500 is not found in the empty JSON string", openapiRequiredFields.toString()));
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
        if (jsonObject.get("message") != null && !jsonObject.get("message").isJsonNull() && !jsonObject.get("message").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `message` to be a primitive type in the JSON string but got `%s`", jsonObject.get("message").toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!Rest500.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(Rest500.class));
            return (TypeAdapter<T>) new TypeAdapter<Rest500>() { // from class: ch.coop.apidia.appGateway.loyalty.model.Rest500.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, Rest500 rest500) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(rest500).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (rest500.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : rest500.getUndeclaredProperties().entrySet()) {
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
                public Rest500 read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    Rest500.validateJsonObject(asJsonObject);
                    Rest500 rest500 = (Rest500) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!Rest500.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    rest500.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    rest500.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    rest500.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                rest500.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                rest500.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return rest500;
                }
            }.nullSafe();
        }
    }

    public static Rest500 fromJson(String str) throws IOException {
        return (Rest500) JSON.getGson().fromJson(str, Rest500.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
