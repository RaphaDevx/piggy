package ch.coop.apidia.appGateway.employeeDiscountCard.model;

import ch.coop.apidia.appGateway.employeeDiscountCard.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.samsung.android.sdk.samsungpay.v2.card.Card;
import java.io.IOException;
import java.util.ArrayList;
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
public class EmployeeDiscountCard {
    public static final String SERIALIZED_NAME_CARD_NR = "cardNr";
    public static final String SERIALIZED_NAME_EMPLOYEE_NR = "employeeNr";
    public static final String SERIALIZED_NAME_GIFTCARDS_BALANCE = "giftcardsBalance";
    public static final String SERIALIZED_NAME_LOGO = "logo";
    public static final String SERIALIZED_NAME_REKA_INFO = "rekaInfo";
    public static final String SERIALIZED_NAME_STATUS = "status";
    public static final String SERIALIZED_NAME_WAS_CREATED_FROM_MAIN_CARD = "wasCreatedFromMainCard";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName("cardNr")
    private String cardNr;

    @SerializedName(SERIALIZED_NAME_EMPLOYEE_NR)
    private String employeeNr;

    @SerializedName(SERIALIZED_NAME_REKA_INFO)
    private EmployeeDiscountCardRekaInfo rekaInfo;
    private Map<String, Object> undeclaredProperties;

    @SerializedName(SERIALIZED_NAME_WAS_CREATED_FROM_MAIN_CARD)
    private Boolean wasCreatedFromMainCard;

    @SerializedName(SERIALIZED_NAME_GIFTCARDS_BALANCE)
    private List<EmployeeDiscountCardGiftcardBalance> giftcardsBalance = new ArrayList();

    @SerializedName("logo")
    private List<Image> logo = new ArrayList();

    @SerializedName("status")
    private StatusEnum status = StatusEnum.ACTIVE;

    @JsonAdapter(Adapter.class)
    public enum StatusEnum {
        ACTIVE(Card.ACTIVE),
        INACTIVE("INACTIVE"),
        UNKNOWN_DEFAULT_OPEN_API("unknown_default_open_api");

        private String value;

        StatusEnum(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }

        @Override // java.lang.Enum
        public String toString() {
            return String.valueOf(this.value);
        }

        public static StatusEnum fromValue(String str) {
            for (StatusEnum statusEnum : values()) {
                if (statusEnum.value.equals(str)) {
                    return statusEnum;
                }
            }
            return UNKNOWN_DEFAULT_OPEN_API;
        }

        public static class Adapter extends TypeAdapter<StatusEnum> {
            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter jsonWriter, StatusEnum statusEnum) throws IOException {
                jsonWriter.value(statusEnum.getValue());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.gson.TypeAdapter
            public StatusEnum read(JsonReader jsonReader) throws IOException {
                return StatusEnum.fromValue(jsonReader.nextString());
            }
        }
    }

    public EmployeeDiscountCard cardNr(String str) {
        this.cardNr = str;
        return this;
    }

    @Nonnull
    public String getCardNr() {
        return this.cardNr;
    }

    public void setCardNr(String str) {
        this.cardNr = str;
    }

    public EmployeeDiscountCard employeeNr(String str) {
        this.employeeNr = str;
        return this;
    }

    @Nonnull
    public String getEmployeeNr() {
        return this.employeeNr;
    }

    public void setEmployeeNr(String str) {
        this.employeeNr = str;
    }

    public EmployeeDiscountCard giftcardsBalance(List<EmployeeDiscountCardGiftcardBalance> list) {
        this.giftcardsBalance = list;
        return this;
    }

    public EmployeeDiscountCard addGiftcardsBalanceItem(EmployeeDiscountCardGiftcardBalance employeeDiscountCardGiftcardBalance) {
        if (this.giftcardsBalance == null) {
            this.giftcardsBalance = new ArrayList();
        }
        this.giftcardsBalance.add(employeeDiscountCardGiftcardBalance);
        return this;
    }

    @Nonnull
    public List<EmployeeDiscountCardGiftcardBalance> getGiftcardsBalance() {
        return this.giftcardsBalance;
    }

    public void setGiftcardsBalance(List<EmployeeDiscountCardGiftcardBalance> list) {
        this.giftcardsBalance = list;
    }

    public EmployeeDiscountCard logo(List<Image> list) {
        this.logo = list;
        return this;
    }

    public EmployeeDiscountCard addLogoItem(Image image) {
        if (this.logo == null) {
            this.logo = new ArrayList();
        }
        this.logo.add(image);
        return this;
    }

    @Nonnull
    public List<Image> getLogo() {
        return this.logo;
    }

    public void setLogo(List<Image> list) {
        this.logo = list;
    }

    public EmployeeDiscountCard rekaInfo(EmployeeDiscountCardRekaInfo employeeDiscountCardRekaInfo) {
        this.rekaInfo = employeeDiscountCardRekaInfo;
        return this;
    }

    @Nullable
    public EmployeeDiscountCardRekaInfo getRekaInfo() {
        return this.rekaInfo;
    }

    public void setRekaInfo(EmployeeDiscountCardRekaInfo employeeDiscountCardRekaInfo) {
        this.rekaInfo = employeeDiscountCardRekaInfo;
    }

    public EmployeeDiscountCard status(StatusEnum statusEnum) {
        this.status = statusEnum;
        return this;
    }

    @Nonnull
    public StatusEnum getStatus() {
        return this.status;
    }

    public void setStatus(StatusEnum statusEnum) {
        this.status = statusEnum;
    }

    public EmployeeDiscountCard wasCreatedFromMainCard(Boolean bool) {
        this.wasCreatedFromMainCard = bool;
        return this;
    }

    @Nonnull
    public Boolean getWasCreatedFromMainCard() {
        return this.wasCreatedFromMainCard;
    }

    public void setWasCreatedFromMainCard(Boolean bool) {
        this.wasCreatedFromMainCard = bool;
    }

    public EmployeeDiscountCard putUndeclaredProperty(String str, Object obj) {
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
        EmployeeDiscountCard employeeDiscountCard = (EmployeeDiscountCard) obj;
        return Objects.equals(this.cardNr, employeeDiscountCard.cardNr) && Objects.equals(this.employeeNr, employeeDiscountCard.employeeNr) && Objects.equals(this.giftcardsBalance, employeeDiscountCard.giftcardsBalance) && Objects.equals(this.logo, employeeDiscountCard.logo) && Objects.equals(this.rekaInfo, employeeDiscountCard.rekaInfo) && Objects.equals(this.status, employeeDiscountCard.status) && Objects.equals(this.wasCreatedFromMainCard, employeeDiscountCard.wasCreatedFromMainCard) && Objects.equals(this.undeclaredProperties, employeeDiscountCard.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.cardNr, this.employeeNr, this.giftcardsBalance, this.logo, this.rekaInfo, this.status, this.wasCreatedFromMainCard, this.undeclaredProperties);
    }

    public String toString() {
        return "class EmployeeDiscountCard {\n    cardNr: " + toIndentedString(this.cardNr) + "\n    employeeNr: " + toIndentedString(this.employeeNr) + "\n    giftcardsBalance: " + toIndentedString(this.giftcardsBalance) + "\n    logo: " + toIndentedString(this.logo) + "\n    rekaInfo: " + toIndentedString(this.rekaInfo) + "\n    status: " + toIndentedString(this.status) + "\n    wasCreatedFromMainCard: " + toIndentedString(this.wasCreatedFromMainCard) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add("cardNr");
        openapiFields.add(SERIALIZED_NAME_EMPLOYEE_NR);
        openapiFields.add(SERIALIZED_NAME_GIFTCARDS_BALANCE);
        openapiFields.add("logo");
        openapiFields.add(SERIALIZED_NAME_REKA_INFO);
        openapiFields.add("status");
        openapiFields.add(SERIALIZED_NAME_WAS_CREATED_FROM_MAIN_CARD);
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add("cardNr");
        openapiRequiredFields.add(SERIALIZED_NAME_EMPLOYEE_NR);
        openapiRequiredFields.add(SERIALIZED_NAME_GIFTCARDS_BALANCE);
        openapiRequiredFields.add("logo");
        openapiRequiredFields.add("status");
        openapiRequiredFields.add(SERIALIZED_NAME_WAS_CREATED_FROM_MAIN_CARD);
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in EmployeeDiscountCard is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
        if (!jsonObject.get("cardNr").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `cardNr` to be a primitive type in the JSON string but got `%s`", jsonObject.get("cardNr").toString()));
        }
        if (!jsonObject.get(SERIALIZED_NAME_EMPLOYEE_NR).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `employeeNr` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_EMPLOYEE_NR).toString()));
        }
        if (!jsonObject.get(SERIALIZED_NAME_GIFTCARDS_BALANCE).isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `giftcardsBalance` to be an array in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_GIFTCARDS_BALANCE).toString()));
        }
        JsonArray asJsonArray = jsonObject.getAsJsonArray(SERIALIZED_NAME_GIFTCARDS_BALANCE);
        for (int i = 0; i < asJsonArray.size(); i++) {
            EmployeeDiscountCardGiftcardBalance.validateJsonObject(asJsonArray.get(i).getAsJsonObject());
        }
        if (!jsonObject.get("logo").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `logo` to be an array in the JSON string but got `%s`", jsonObject.get("logo").toString()));
        }
        JsonArray asJsonArray2 = jsonObject.getAsJsonArray("logo");
        for (int i2 = 0; i2 < asJsonArray2.size(); i2++) {
            Image.validateJsonObject(asJsonArray2.get(i2).getAsJsonObject());
        }
        if (jsonObject.get(SERIALIZED_NAME_REKA_INFO) != null && !jsonObject.get(SERIALIZED_NAME_REKA_INFO).isJsonNull()) {
            EmployeeDiscountCardRekaInfo.validateJsonObject(jsonObject.getAsJsonObject(SERIALIZED_NAME_REKA_INFO));
        }
        if (!jsonObject.get("status").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `status` to be a primitive type in the JSON string but got `%s`", jsonObject.get("status").toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!EmployeeDiscountCard.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(EmployeeDiscountCard.class));
            return (TypeAdapter<T>) new TypeAdapter<EmployeeDiscountCard>() { // from class: ch.coop.apidia.appGateway.employeeDiscountCard.model.EmployeeDiscountCard.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, EmployeeDiscountCard employeeDiscountCard) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(employeeDiscountCard).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (employeeDiscountCard.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : employeeDiscountCard.getUndeclaredProperties().entrySet()) {
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
                public EmployeeDiscountCard read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    EmployeeDiscountCard.validateJsonObject(asJsonObject);
                    EmployeeDiscountCard employeeDiscountCard = (EmployeeDiscountCard) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!EmployeeDiscountCard.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    employeeDiscountCard.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    employeeDiscountCard.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    employeeDiscountCard.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                employeeDiscountCard.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                employeeDiscountCard.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return employeeDiscountCard;
                }
            }.nullSafe();
        }
    }

    public static EmployeeDiscountCard fromJson(String str) throws IOException {
        return (EmployeeDiscountCard) JSON.getGson().fromJson(str, EmployeeDiscountCard.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
