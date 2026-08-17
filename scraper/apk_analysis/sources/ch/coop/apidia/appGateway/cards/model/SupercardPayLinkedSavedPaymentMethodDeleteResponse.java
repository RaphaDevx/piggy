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
public class SupercardPayLinkedSavedPaymentMethodDeleteResponse {
    public static final String SERIALIZED_NAME_DELETED_SUPERCARD_PAY_SAVED_PAYMENT_METHOD_ID = "deletedSupercardPaySavedPaymentMethodId";
    public static final String SERIALIZED_NAME_SUCCESS = "success";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName("deletedSupercardPaySavedPaymentMethodId")
    private String deletedSupercardPaySavedPaymentMethodId;

    @SerializedName("success")
    private Boolean success;
    private Map<String, Object> undeclaredProperties;

    public SupercardPayLinkedSavedPaymentMethodDeleteResponse success(Boolean bool) {
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

    public SupercardPayLinkedSavedPaymentMethodDeleteResponse deletedSupercardPaySavedPaymentMethodId(String str) {
        this.deletedSupercardPaySavedPaymentMethodId = str;
        return this;
    }

    @Nullable
    public String getDeletedSupercardPaySavedPaymentMethodId() {
        return this.deletedSupercardPaySavedPaymentMethodId;
    }

    public void setDeletedSupercardPaySavedPaymentMethodId(String str) {
        this.deletedSupercardPaySavedPaymentMethodId = str;
    }

    public SupercardPayLinkedSavedPaymentMethodDeleteResponse putUndeclaredProperty(String str, Object obj) {
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
        SupercardPayLinkedSavedPaymentMethodDeleteResponse supercardPayLinkedSavedPaymentMethodDeleteResponse = (SupercardPayLinkedSavedPaymentMethodDeleteResponse) obj;
        return Objects.equals(this.success, supercardPayLinkedSavedPaymentMethodDeleteResponse.success) && Objects.equals(this.deletedSupercardPaySavedPaymentMethodId, supercardPayLinkedSavedPaymentMethodDeleteResponse.deletedSupercardPaySavedPaymentMethodId) && Objects.equals(this.undeclaredProperties, supercardPayLinkedSavedPaymentMethodDeleteResponse.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.success, this.deletedSupercardPaySavedPaymentMethodId, this.undeclaredProperties);
    }

    public String toString() {
        return "class SupercardPayLinkedSavedPaymentMethodDeleteResponse {\n    success: " + toIndentedString(this.success) + "\n    deletedSupercardPaySavedPaymentMethodId: " + toIndentedString(this.deletedSupercardPaySavedPaymentMethodId) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        openapiFields.add("deletedSupercardPaySavedPaymentMethodId");
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add("success");
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in SupercardPayLinkedSavedPaymentMethodDeleteResponse is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
        if (jsonObject.get("deletedSupercardPaySavedPaymentMethodId") != null && !jsonObject.get("deletedSupercardPaySavedPaymentMethodId").isJsonNull() && !jsonObject.get("deletedSupercardPaySavedPaymentMethodId").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `deletedSupercardPaySavedPaymentMethodId` to be a primitive type in the JSON string but got `%s`", jsonObject.get("deletedSupercardPaySavedPaymentMethodId").toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!SupercardPayLinkedSavedPaymentMethodDeleteResponse.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(SupercardPayLinkedSavedPaymentMethodDeleteResponse.class));
            return (TypeAdapter<T>) new TypeAdapter<SupercardPayLinkedSavedPaymentMethodDeleteResponse>() { // from class: ch.coop.apidia.appGateway.cards.model.SupercardPayLinkedSavedPaymentMethodDeleteResponse.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, SupercardPayLinkedSavedPaymentMethodDeleteResponse supercardPayLinkedSavedPaymentMethodDeleteResponse) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(supercardPayLinkedSavedPaymentMethodDeleteResponse).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (supercardPayLinkedSavedPaymentMethodDeleteResponse.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : supercardPayLinkedSavedPaymentMethodDeleteResponse.getUndeclaredProperties().entrySet()) {
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
                public SupercardPayLinkedSavedPaymentMethodDeleteResponse read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    SupercardPayLinkedSavedPaymentMethodDeleteResponse.validateJsonObject(asJsonObject);
                    SupercardPayLinkedSavedPaymentMethodDeleteResponse supercardPayLinkedSavedPaymentMethodDeleteResponse = (SupercardPayLinkedSavedPaymentMethodDeleteResponse) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!SupercardPayLinkedSavedPaymentMethodDeleteResponse.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    supercardPayLinkedSavedPaymentMethodDeleteResponse.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    supercardPayLinkedSavedPaymentMethodDeleteResponse.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    supercardPayLinkedSavedPaymentMethodDeleteResponse.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                supercardPayLinkedSavedPaymentMethodDeleteResponse.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                supercardPayLinkedSavedPaymentMethodDeleteResponse.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return supercardPayLinkedSavedPaymentMethodDeleteResponse;
                }
            }.nullSafe();
        }
    }

    public static SupercardPayLinkedSavedPaymentMethodDeleteResponse fromJson(String str) throws IOException {
        return (SupercardPayLinkedSavedPaymentMethodDeleteResponse) JSON.getGson().fromJson(str, SupercardPayLinkedSavedPaymentMethodDeleteResponse.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
