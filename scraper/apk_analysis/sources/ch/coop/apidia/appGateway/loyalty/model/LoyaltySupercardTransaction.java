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
import java.time.OffsetDateTime;
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
public class LoyaltySupercardTransaction {
    public static final String SERIALIZED_NAME_CODE = "code";
    public static final String SERIALIZED_NAME_DATE = "date";
    public static final String SERIALIZED_NAME_LABEL = "label";
    public static final String SERIALIZED_NAME_POINTS = "points";
    public static final String SERIALIZED_NAME_STORE_NAME = "storeName";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName("code")
    private String code;

    @SerializedName("date")
    private OffsetDateTime date;

    @SerializedName("label")
    private String label;

    @SerializedName("points")
    private String points;

    @SerializedName("storeName")
    private String storeName;
    private Map<String, Object> undeclaredProperties;

    public LoyaltySupercardTransaction code(String str) {
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

    public LoyaltySupercardTransaction date(OffsetDateTime offsetDateTime) {
        this.date = offsetDateTime;
        return this;
    }

    @Nonnull
    public OffsetDateTime getDate() {
        return this.date;
    }

    public void setDate(OffsetDateTime offsetDateTime) {
        this.date = offsetDateTime;
    }

    public LoyaltySupercardTransaction label(String str) {
        this.label = str;
        return this;
    }

    @Nonnull
    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public LoyaltySupercardTransaction points(String str) {
        this.points = str;
        return this;
    }

    @Nonnull
    public String getPoints() {
        return this.points;
    }

    public void setPoints(String str) {
        this.points = str;
    }

    public LoyaltySupercardTransaction storeName(String str) {
        this.storeName = str;
        return this;
    }

    @Nullable
    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String str) {
        this.storeName = str;
    }

    public LoyaltySupercardTransaction putUndeclaredProperty(String str, Object obj) {
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
        LoyaltySupercardTransaction loyaltySupercardTransaction = (LoyaltySupercardTransaction) obj;
        return Objects.equals(this.code, loyaltySupercardTransaction.code) && Objects.equals(this.date, loyaltySupercardTransaction.date) && Objects.equals(this.label, loyaltySupercardTransaction.label) && Objects.equals(this.points, loyaltySupercardTransaction.points) && Objects.equals(this.storeName, loyaltySupercardTransaction.storeName) && Objects.equals(this.undeclaredProperties, loyaltySupercardTransaction.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.code, this.date, this.label, this.points, this.storeName, this.undeclaredProperties);
    }

    public String toString() {
        return "class LoyaltySupercardTransaction {\n    code: " + toIndentedString(this.code) + "\n    date: " + toIndentedString(this.date) + "\n    label: " + toIndentedString(this.label) + "\n    points: " + toIndentedString(this.points) + "\n    storeName: " + toIndentedString(this.storeName) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        openapiFields.add("date");
        openapiFields.add("label");
        openapiFields.add("points");
        openapiFields.add("storeName");
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add("code");
        openapiRequiredFields.add("date");
        openapiRequiredFields.add("label");
        openapiRequiredFields.add("points");
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in LoyaltySupercardTransaction is not found in the empty JSON string", openapiRequiredFields.toString()));
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
        if (!jsonObject.get("label").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `label` to be a primitive type in the JSON string but got `%s`", jsonObject.get("label").toString()));
        }
        if (!jsonObject.get("points").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `points` to be a primitive type in the JSON string but got `%s`", jsonObject.get("points").toString()));
        }
        if (jsonObject.get("storeName") != null && !jsonObject.get("storeName").isJsonNull() && !jsonObject.get("storeName").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `storeName` to be a primitive type in the JSON string but got `%s`", jsonObject.get("storeName").toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!LoyaltySupercardTransaction.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(LoyaltySupercardTransaction.class));
            return (TypeAdapter<T>) new TypeAdapter<LoyaltySupercardTransaction>() { // from class: ch.coop.apidia.appGateway.loyalty.model.LoyaltySupercardTransaction.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, LoyaltySupercardTransaction loyaltySupercardTransaction) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(loyaltySupercardTransaction).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (loyaltySupercardTransaction.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : loyaltySupercardTransaction.getUndeclaredProperties().entrySet()) {
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
                public LoyaltySupercardTransaction read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    LoyaltySupercardTransaction.validateJsonObject(asJsonObject);
                    LoyaltySupercardTransaction loyaltySupercardTransaction = (LoyaltySupercardTransaction) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!LoyaltySupercardTransaction.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    loyaltySupercardTransaction.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    loyaltySupercardTransaction.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    loyaltySupercardTransaction.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                loyaltySupercardTransaction.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                loyaltySupercardTransaction.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return loyaltySupercardTransaction;
                }
            }.nullSafe();
        }
    }

    public static LoyaltySupercardTransaction fromJson(String str) throws IOException {
        return (LoyaltySupercardTransaction) JSON.getGson().fromJson(str, LoyaltySupercardTransaction.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
