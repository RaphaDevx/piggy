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
public class EmployeeDiscountCardGiftcardBalance {
    public static final String SERIALIZED_NAME_BALANCE_CURRENCY = "balanceCurrency";
    public static final String SERIALIZED_NAME_BALANCE_VALUE = "balanceValue";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName("balanceCurrency")
    private String balanceCurrency;

    @SerializedName("balanceValue")
    private Integer balanceValue;
    private Map<String, Object> undeclaredProperties;

    public EmployeeDiscountCardGiftcardBalance balanceCurrency(String str) {
        this.balanceCurrency = str;
        return this;
    }

    @Nonnull
    public String getBalanceCurrency() {
        return this.balanceCurrency;
    }

    public void setBalanceCurrency(String str) {
        this.balanceCurrency = str;
    }

    public EmployeeDiscountCardGiftcardBalance balanceValue(Integer num) {
        this.balanceValue = num;
        return this;
    }

    @Nonnull
    public Integer getBalanceValue() {
        return this.balanceValue;
    }

    public void setBalanceValue(Integer num) {
        this.balanceValue = num;
    }

    public EmployeeDiscountCardGiftcardBalance putUndeclaredProperty(String str, Object obj) {
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
        EmployeeDiscountCardGiftcardBalance employeeDiscountCardGiftcardBalance = (EmployeeDiscountCardGiftcardBalance) obj;
        return Objects.equals(this.balanceCurrency, employeeDiscountCardGiftcardBalance.balanceCurrency) && Objects.equals(this.balanceValue, employeeDiscountCardGiftcardBalance.balanceValue) && Objects.equals(this.undeclaredProperties, employeeDiscountCardGiftcardBalance.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.balanceCurrency, this.balanceValue, this.undeclaredProperties);
    }

    public String toString() {
        return "class EmployeeDiscountCardGiftcardBalance {\n    balanceCurrency: " + toIndentedString(this.balanceCurrency) + "\n    balanceValue: " + toIndentedString(this.balanceValue) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add("balanceCurrency");
        openapiFields.add("balanceValue");
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add("balanceCurrency");
        openapiRequiredFields.add("balanceValue");
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in EmployeeDiscountCardGiftcardBalance is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
        if (!jsonObject.get("balanceCurrency").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `balanceCurrency` to be a primitive type in the JSON string but got `%s`", jsonObject.get("balanceCurrency").toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!EmployeeDiscountCardGiftcardBalance.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(EmployeeDiscountCardGiftcardBalance.class));
            return (TypeAdapter<T>) new TypeAdapter<EmployeeDiscountCardGiftcardBalance>() { // from class: ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCardGiftcardBalance.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, EmployeeDiscountCardGiftcardBalance employeeDiscountCardGiftcardBalance) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(employeeDiscountCardGiftcardBalance).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (employeeDiscountCardGiftcardBalance.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : employeeDiscountCardGiftcardBalance.getUndeclaredProperties().entrySet()) {
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
                public EmployeeDiscountCardGiftcardBalance read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    EmployeeDiscountCardGiftcardBalance.validateJsonObject(asJsonObject);
                    EmployeeDiscountCardGiftcardBalance employeeDiscountCardGiftcardBalance = (EmployeeDiscountCardGiftcardBalance) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!EmployeeDiscountCardGiftcardBalance.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    employeeDiscountCardGiftcardBalance.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    employeeDiscountCardGiftcardBalance.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    employeeDiscountCardGiftcardBalance.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                employeeDiscountCardGiftcardBalance.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                employeeDiscountCardGiftcardBalance.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return employeeDiscountCardGiftcardBalance;
                }
            }.nullSafe();
        }
    }

    public static EmployeeDiscountCardGiftcardBalance fromJson(String str) throws IOException {
        return (EmployeeDiscountCardGiftcardBalance) JSON.getGson().fromJson(str, EmployeeDiscountCardGiftcardBalance.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
