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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class EmployeeDiscountCardGiftcardsResponse {
    public static final String SERIALIZED_NAME_CARD_NRS = "cardNrs";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName(SERIALIZED_NAME_CARD_NRS)
    private List<String> cardNrs = new ArrayList();
    private Map<String, Object> undeclaredProperties;

    public EmployeeDiscountCardGiftcardsResponse cardNrs(List<String> list) {
        this.cardNrs = list;
        return this;
    }

    public EmployeeDiscountCardGiftcardsResponse addCardNrsItem(String str) {
        if (this.cardNrs == null) {
            this.cardNrs = new ArrayList();
        }
        this.cardNrs.add(str);
        return this;
    }

    @Nonnull
    public List<String> getCardNrs() {
        return this.cardNrs;
    }

    public void setCardNrs(List<String> list) {
        this.cardNrs = list;
    }

    public EmployeeDiscountCardGiftcardsResponse putUndeclaredProperty(String str, Object obj) {
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
        EmployeeDiscountCardGiftcardsResponse employeeDiscountCardGiftcardsResponse = (EmployeeDiscountCardGiftcardsResponse) obj;
        return Objects.equals(this.cardNrs, employeeDiscountCardGiftcardsResponse.cardNrs) && Objects.equals(this.undeclaredProperties, employeeDiscountCardGiftcardsResponse.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.cardNrs, this.undeclaredProperties);
    }

    public String toString() {
        return "class EmployeeDiscountCardGiftcardsResponse {\n    cardNrs: " + toIndentedString(this.cardNrs) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add(SERIALIZED_NAME_CARD_NRS);
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add(SERIALIZED_NAME_CARD_NRS);
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in EmployeeDiscountCardGiftcardsResponse is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
        if (jsonObject.get(SERIALIZED_NAME_CARD_NRS) == null) {
            throw new IllegalArgumentException("Expected the field `linkedContent` to be an array in the JSON string but got `null`");
        }
        if (!jsonObject.get(SERIALIZED_NAME_CARD_NRS).isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `cardNrs` to be an array in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_CARD_NRS).toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!EmployeeDiscountCardGiftcardsResponse.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(EmployeeDiscountCardGiftcardsResponse.class));
            return (TypeAdapter<T>) new TypeAdapter<EmployeeDiscountCardGiftcardsResponse>() { // from class: ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCardGiftcardsResponse.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, EmployeeDiscountCardGiftcardsResponse employeeDiscountCardGiftcardsResponse) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(employeeDiscountCardGiftcardsResponse).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (employeeDiscountCardGiftcardsResponse.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : employeeDiscountCardGiftcardsResponse.getUndeclaredProperties().entrySet()) {
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
                public EmployeeDiscountCardGiftcardsResponse read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    EmployeeDiscountCardGiftcardsResponse.validateJsonObject(asJsonObject);
                    EmployeeDiscountCardGiftcardsResponse employeeDiscountCardGiftcardsResponse = (EmployeeDiscountCardGiftcardsResponse) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!EmployeeDiscountCardGiftcardsResponse.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    employeeDiscountCardGiftcardsResponse.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    employeeDiscountCardGiftcardsResponse.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    employeeDiscountCardGiftcardsResponse.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                employeeDiscountCardGiftcardsResponse.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                employeeDiscountCardGiftcardsResponse.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return employeeDiscountCardGiftcardsResponse;
                }
            }.nullSafe();
        }
    }

    public static EmployeeDiscountCardGiftcardsResponse fromJson(String str) throws IOException {
        return (EmployeeDiscountCardGiftcardsResponse) JSON.getGson().fromJson(str, EmployeeDiscountCardGiftcardsResponse.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
