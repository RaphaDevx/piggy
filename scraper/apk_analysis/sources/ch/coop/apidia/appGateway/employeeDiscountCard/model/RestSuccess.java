package ch.coop.apidia.appGateway.employeeDiscountCard.model;

import ch.coop.apidia.appGateway.employeeDiscountCard.JSON;
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
public class RestSuccess {
    public static final String SERIALIZED_NAME_SUCCESS = "success";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName("success")
    private Boolean success;
    private Map<String, Object> undeclaredProperties;

    public RestSuccess success(Boolean bool) {
        this.success = bool;
        return this;
    }

    @Nonnull
    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean bool) {
        this.success = bool;
    }

    public RestSuccess putUndeclaredProperty(String str, Object obj) {
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
        RestSuccess restSuccess = (RestSuccess) obj;
        return Objects.equals(this.success, restSuccess.success) && Objects.equals(this.undeclaredProperties, restSuccess.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.success, this.undeclaredProperties);
    }

    public String toString() {
        return "class RestSuccess {\n    success: " + toIndentedString(this.success) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add("success");
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add("success");
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in RestSuccess is not found in the empty JSON string", openapiRequiredFields.toString()));
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
            if (!RestSuccess.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(RestSuccess.class));
            return (TypeAdapter<T>) new TypeAdapter<RestSuccess>() { // from class: ch.coop.apidia.appGateway.employeeDiscountCard.model.RestSuccess.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, RestSuccess restSuccess) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(restSuccess).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (restSuccess.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : restSuccess.getUndeclaredProperties().entrySet()) {
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
                public RestSuccess read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    RestSuccess.validateJsonObject(asJsonObject);
                    RestSuccess restSuccess = (RestSuccess) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!RestSuccess.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    restSuccess.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    restSuccess.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    restSuccess.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                restSuccess.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                restSuccess.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return restSuccess;
                }
            }.nullSafe();
        }
    }

    public static RestSuccess fromJson(String str) throws IOException {
        return (RestSuccess) JSON.getGson().fromJson(str, RestSuccess.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
