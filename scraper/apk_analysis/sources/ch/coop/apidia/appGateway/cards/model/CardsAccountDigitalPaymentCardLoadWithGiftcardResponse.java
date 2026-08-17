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
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class CardsAccountDigitalPaymentCardLoadWithGiftcardResponse {
    public static final String SERIALIZED_NAME_IS_LIMITED_TRANSFER = "isLimitedTransfer";
    public static final String SERIALIZED_NAME_SOURCE_CARD_AMOUNT = "sourceCardAmount";
    public static final String SERIALIZED_NAME_TARGET_CARD_AMOUNT = "targetCardAmount";
    public static final String SERIALIZED_NAME_TARGET_CARD_BALANCE_MAX = "targetCardBalanceMax";
    public static final String SERIALIZED_NAME_TRANSFER_AMOUNT = "transferAmount";
    public static final String SERIALIZED_NAME_TRANSFER_CURRENCY = "transferCurrency";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName(SERIALIZED_NAME_IS_LIMITED_TRANSFER)
    private Boolean isLimitedTransfer;

    @SerializedName(SERIALIZED_NAME_SOURCE_CARD_AMOUNT)
    private Integer sourceCardAmount;

    @SerializedName(SERIALIZED_NAME_TARGET_CARD_AMOUNT)
    private Integer targetCardAmount;

    @SerializedName(SERIALIZED_NAME_TARGET_CARD_BALANCE_MAX)
    private Integer targetCardBalanceMax;

    @SerializedName(SERIALIZED_NAME_TRANSFER_AMOUNT)
    private Integer transferAmount;

    @SerializedName(SERIALIZED_NAME_TRANSFER_CURRENCY)
    private String transferCurrency;
    private Map<String, Object> undeclaredProperties;

    public CardsAccountDigitalPaymentCardLoadWithGiftcardResponse isLimitedTransfer(Boolean bool) {
        this.isLimitedTransfer = bool;
        return this;
    }

    @Nonnull
    public Boolean getIsLimitedTransfer() {
        return this.isLimitedTransfer;
    }

    public void setIsLimitedTransfer(Boolean bool) {
        this.isLimitedTransfer = bool;
    }

    public CardsAccountDigitalPaymentCardLoadWithGiftcardResponse sourceCardAmount(Integer num) {
        this.sourceCardAmount = num;
        return this;
    }

    @Nonnull
    public Integer getSourceCardAmount() {
        return this.sourceCardAmount;
    }

    public void setSourceCardAmount(Integer num) {
        this.sourceCardAmount = num;
    }

    public CardsAccountDigitalPaymentCardLoadWithGiftcardResponse targetCardAmount(Integer num) {
        this.targetCardAmount = num;
        return this;
    }

    @Nonnull
    public Integer getTargetCardAmount() {
        return this.targetCardAmount;
    }

    public void setTargetCardAmount(Integer num) {
        this.targetCardAmount = num;
    }

    public CardsAccountDigitalPaymentCardLoadWithGiftcardResponse targetCardBalanceMax(Integer num) {
        this.targetCardBalanceMax = num;
        return this;
    }

    @Nonnull
    public Integer getTargetCardBalanceMax() {
        return this.targetCardBalanceMax;
    }

    public void setTargetCardBalanceMax(Integer num) {
        this.targetCardBalanceMax = num;
    }

    public CardsAccountDigitalPaymentCardLoadWithGiftcardResponse transferAmount(Integer num) {
        this.transferAmount = num;
        return this;
    }

    @Nonnull
    public Integer getTransferAmount() {
        return this.transferAmount;
    }

    public void setTransferAmount(Integer num) {
        this.transferAmount = num;
    }

    public CardsAccountDigitalPaymentCardLoadWithGiftcardResponse transferCurrency(String str) {
        this.transferCurrency = str;
        return this;
    }

    @Nonnull
    public String getTransferCurrency() {
        return this.transferCurrency;
    }

    public void setTransferCurrency(String str) {
        this.transferCurrency = str;
    }

    public CardsAccountDigitalPaymentCardLoadWithGiftcardResponse putUndeclaredProperty(String str, Object obj) {
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
        CardsAccountDigitalPaymentCardLoadWithGiftcardResponse cardsAccountDigitalPaymentCardLoadWithGiftcardResponse = (CardsAccountDigitalPaymentCardLoadWithGiftcardResponse) obj;
        return Objects.equals(this.isLimitedTransfer, cardsAccountDigitalPaymentCardLoadWithGiftcardResponse.isLimitedTransfer) && Objects.equals(this.sourceCardAmount, cardsAccountDigitalPaymentCardLoadWithGiftcardResponse.sourceCardAmount) && Objects.equals(this.targetCardAmount, cardsAccountDigitalPaymentCardLoadWithGiftcardResponse.targetCardAmount) && Objects.equals(this.targetCardBalanceMax, cardsAccountDigitalPaymentCardLoadWithGiftcardResponse.targetCardBalanceMax) && Objects.equals(this.transferAmount, cardsAccountDigitalPaymentCardLoadWithGiftcardResponse.transferAmount) && Objects.equals(this.transferCurrency, cardsAccountDigitalPaymentCardLoadWithGiftcardResponse.transferCurrency) && Objects.equals(this.undeclaredProperties, cardsAccountDigitalPaymentCardLoadWithGiftcardResponse.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.isLimitedTransfer, this.sourceCardAmount, this.targetCardAmount, this.targetCardBalanceMax, this.transferAmount, this.transferCurrency, this.undeclaredProperties);
    }

    public String toString() {
        return "class CardsAccountDigitalPaymentCardLoadWithGiftcardResponse {\n    isLimitedTransfer: " + toIndentedString(this.isLimitedTransfer) + "\n    sourceCardAmount: " + toIndentedString(this.sourceCardAmount) + "\n    targetCardAmount: " + toIndentedString(this.targetCardAmount) + "\n    targetCardBalanceMax: " + toIndentedString(this.targetCardBalanceMax) + "\n    transferAmount: " + toIndentedString(this.transferAmount) + "\n    transferCurrency: " + toIndentedString(this.transferCurrency) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add(SERIALIZED_NAME_IS_LIMITED_TRANSFER);
        openapiFields.add(SERIALIZED_NAME_SOURCE_CARD_AMOUNT);
        openapiFields.add(SERIALIZED_NAME_TARGET_CARD_AMOUNT);
        openapiFields.add(SERIALIZED_NAME_TARGET_CARD_BALANCE_MAX);
        openapiFields.add(SERIALIZED_NAME_TRANSFER_AMOUNT);
        openapiFields.add(SERIALIZED_NAME_TRANSFER_CURRENCY);
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add(SERIALIZED_NAME_IS_LIMITED_TRANSFER);
        openapiRequiredFields.add(SERIALIZED_NAME_SOURCE_CARD_AMOUNT);
        openapiRequiredFields.add(SERIALIZED_NAME_TARGET_CARD_AMOUNT);
        openapiRequiredFields.add(SERIALIZED_NAME_TARGET_CARD_BALANCE_MAX);
        openapiRequiredFields.add(SERIALIZED_NAME_TRANSFER_AMOUNT);
        openapiRequiredFields.add(SERIALIZED_NAME_TRANSFER_CURRENCY);
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in CardsAccountDigitalPaymentCardLoadWithGiftcardResponse is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
        if (!jsonObject.get(SERIALIZED_NAME_TRANSFER_CURRENCY).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `transferCurrency` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_TRANSFER_CURRENCY).toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!CardsAccountDigitalPaymentCardLoadWithGiftcardResponse.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(CardsAccountDigitalPaymentCardLoadWithGiftcardResponse.class));
            return (TypeAdapter<T>) new TypeAdapter<CardsAccountDigitalPaymentCardLoadWithGiftcardResponse>() { // from class: ch.coop.apidia.appGateway.cards.model.CardsAccountDigitalPaymentCardLoadWithGiftcardResponse.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, CardsAccountDigitalPaymentCardLoadWithGiftcardResponse cardsAccountDigitalPaymentCardLoadWithGiftcardResponse) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(cardsAccountDigitalPaymentCardLoadWithGiftcardResponse).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (cardsAccountDigitalPaymentCardLoadWithGiftcardResponse.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : cardsAccountDigitalPaymentCardLoadWithGiftcardResponse.getUndeclaredProperties().entrySet()) {
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
                public CardsAccountDigitalPaymentCardLoadWithGiftcardResponse read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    CardsAccountDigitalPaymentCardLoadWithGiftcardResponse.validateJsonObject(asJsonObject);
                    CardsAccountDigitalPaymentCardLoadWithGiftcardResponse cardsAccountDigitalPaymentCardLoadWithGiftcardResponse = (CardsAccountDigitalPaymentCardLoadWithGiftcardResponse) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!CardsAccountDigitalPaymentCardLoadWithGiftcardResponse.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    cardsAccountDigitalPaymentCardLoadWithGiftcardResponse.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    cardsAccountDigitalPaymentCardLoadWithGiftcardResponse.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    cardsAccountDigitalPaymentCardLoadWithGiftcardResponse.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                cardsAccountDigitalPaymentCardLoadWithGiftcardResponse.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                cardsAccountDigitalPaymentCardLoadWithGiftcardResponse.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return cardsAccountDigitalPaymentCardLoadWithGiftcardResponse;
                }
            }.nullSafe();
        }
    }

    public static CardsAccountDigitalPaymentCardLoadWithGiftcardResponse fromJson(String str) throws IOException {
        return (CardsAccountDigitalPaymentCardLoadWithGiftcardResponse) JSON.getGson().fromJson(str, CardsAccountDigitalPaymentCardLoadWithGiftcardResponse.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
