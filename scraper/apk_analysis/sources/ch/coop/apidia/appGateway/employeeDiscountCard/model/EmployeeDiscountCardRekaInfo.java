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
public class EmployeeDiscountCardRekaInfo {
    public static final String SERIALIZED_NAME_AMOUNT_CURRENCY = "amountCurrency";
    public static final String SERIALIZED_NAME_AMOUNT_EXCHANGED = "amountExchanged";
    public static final String SERIALIZED_NAME_AMOUNT_MAX = "amountMax";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName(SERIALIZED_NAME_AMOUNT_CURRENCY)
    private String amountCurrency;

    @SerializedName(SERIALIZED_NAME_AMOUNT_EXCHANGED)
    private Integer amountExchanged;

    @SerializedName(SERIALIZED_NAME_AMOUNT_MAX)
    private Integer amountMax;
    private Map<String, Object> undeclaredProperties;

    public EmployeeDiscountCardRekaInfo amountCurrency(String str) {
        this.amountCurrency = str;
        return this;
    }

    @Nonnull
    public String getAmountCurrency() {
        return this.amountCurrency;
    }

    public void setAmountCurrency(String str) {
        this.amountCurrency = str;
    }

    public EmployeeDiscountCardRekaInfo amountExchanged(Integer num) {
        this.amountExchanged = num;
        return this;
    }

    @Nonnull
    public Integer getAmountExchanged() {
        return this.amountExchanged;
    }

    public void setAmountExchanged(Integer num) {
        this.amountExchanged = num;
    }

    public EmployeeDiscountCardRekaInfo amountMax(Integer num) {
        this.amountMax = num;
        return this;
    }

    @Nonnull
    public Integer getAmountMax() {
        return this.amountMax;
    }

    public void setAmountMax(Integer num) {
        this.amountMax = num;
    }

    public EmployeeDiscountCardRekaInfo putUndeclaredProperty(String str, Object obj) {
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
        EmployeeDiscountCardRekaInfo employeeDiscountCardRekaInfo = (EmployeeDiscountCardRekaInfo) obj;
        return Objects.equals(this.amountCurrency, employeeDiscountCardRekaInfo.amountCurrency) && Objects.equals(this.amountExchanged, employeeDiscountCardRekaInfo.amountExchanged) && Objects.equals(this.amountMax, employeeDiscountCardRekaInfo.amountMax) && Objects.equals(this.undeclaredProperties, employeeDiscountCardRekaInfo.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.amountCurrency, this.amountExchanged, this.amountMax, this.undeclaredProperties);
    }

    public String toString() {
        return "class EmployeeDiscountCardRekaInfo {\n    amountCurrency: " + toIndentedString(this.amountCurrency) + "\n    amountExchanged: " + toIndentedString(this.amountExchanged) + "\n    amountMax: " + toIndentedString(this.amountMax) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add(SERIALIZED_NAME_AMOUNT_CURRENCY);
        openapiFields.add(SERIALIZED_NAME_AMOUNT_EXCHANGED);
        openapiFields.add(SERIALIZED_NAME_AMOUNT_MAX);
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add(SERIALIZED_NAME_AMOUNT_CURRENCY);
        openapiRequiredFields.add(SERIALIZED_NAME_AMOUNT_EXCHANGED);
        openapiRequiredFields.add(SERIALIZED_NAME_AMOUNT_MAX);
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in EmployeeDiscountCardRekaInfo is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
        if (!jsonObject.get(SERIALIZED_NAME_AMOUNT_CURRENCY).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `amountCurrency` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_AMOUNT_CURRENCY).toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!EmployeeDiscountCardRekaInfo.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(EmployeeDiscountCardRekaInfo.class));
            return (TypeAdapter<T>) new TypeAdapter<EmployeeDiscountCardRekaInfo>() { // from class: ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCardRekaInfo.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, EmployeeDiscountCardRekaInfo employeeDiscountCardRekaInfo) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(employeeDiscountCardRekaInfo).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (employeeDiscountCardRekaInfo.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : employeeDiscountCardRekaInfo.getUndeclaredProperties().entrySet()) {
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
                public EmployeeDiscountCardRekaInfo read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    EmployeeDiscountCardRekaInfo.validateJsonObject(asJsonObject);
                    EmployeeDiscountCardRekaInfo employeeDiscountCardRekaInfo = (EmployeeDiscountCardRekaInfo) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!EmployeeDiscountCardRekaInfo.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    employeeDiscountCardRekaInfo.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    employeeDiscountCardRekaInfo.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    employeeDiscountCardRekaInfo.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                employeeDiscountCardRekaInfo.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                employeeDiscountCardRekaInfo.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return employeeDiscountCardRekaInfo;
                }
            }.nullSafe();
        }
    }

    public static EmployeeDiscountCardRekaInfo fromJson(String str) throws IOException {
        return (EmployeeDiscountCardRekaInfo) JSON.getGson().fromJson(str, EmployeeDiscountCardRekaInfo.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
