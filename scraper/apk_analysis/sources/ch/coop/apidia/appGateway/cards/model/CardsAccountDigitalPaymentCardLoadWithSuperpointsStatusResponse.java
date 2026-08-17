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
public class CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse {
    public static final String SERIALIZED_NAME_BLOCKS = "blocks";
    public static final String SERIALIZED_NAME_CENTS_PER_BLOCK = "centsPerBlock";
    public static final String SERIALIZED_NAME_CODE = "code";
    public static final String SERIALIZED_NAME_CURRENCY = "currency";
    public static final String SERIALIZED_NAME_DIGITAL_PAYMENT_CARD_BALANCE = "digitalPaymentCardBalance";
    public static final String SERIALIZED_NAME_DIGITAL_PAYMENT_CARD_BALANCE_MAX = "digitalPaymentCardBalanceMax";
    public static final String SERIALIZED_NAME_POINTS = "points";
    public static final String SERIALIZED_NAME_POINTS_PER_BLOCK = "pointsPerBlock";
    public static final String SERIALIZED_NAME_STATUS = "status";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName(SERIALIZED_NAME_BLOCKS)
    private CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks blocks;

    @SerializedName(SERIALIZED_NAME_CENTS_PER_BLOCK)
    private Integer centsPerBlock;

    @SerializedName("code")
    private String code;

    @SerializedName("currency")
    private String currency;

    @SerializedName(SERIALIZED_NAME_DIGITAL_PAYMENT_CARD_BALANCE)
    private Integer digitalPaymentCardBalance;

    @SerializedName(SERIALIZED_NAME_DIGITAL_PAYMENT_CARD_BALANCE_MAX)
    private Integer digitalPaymentCardBalanceMax;

    @SerializedName("points")
    private Integer points;

    @SerializedName(SERIALIZED_NAME_POINTS_PER_BLOCK)
    private Integer pointsPerBlock;

    @SerializedName("status")
    private CardsAccountDigitalPaymentCardLoadWithSuperpointsStatus status = CardsAccountDigitalPaymentCardLoadWithSuperpointsStatus.OK;
    private Map<String, Object> undeclaredProperties;

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse blocks(CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks) {
        this.blocks = cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks;
        return this;
    }

    @Nonnull
    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks getBlocks() {
        return this.blocks;
    }

    public void setBlocks(CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks) {
        this.blocks = cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks;
    }

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse centsPerBlock(Integer num) {
        this.centsPerBlock = num;
        return this;
    }

    @Nonnull
    public Integer getCentsPerBlock() {
        return this.centsPerBlock;
    }

    public void setCentsPerBlock(Integer num) {
        this.centsPerBlock = num;
    }

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse code(String str) {
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

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse currency(String str) {
        this.currency = str;
        return this;
    }

    @Nonnull
    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse digitalPaymentCardBalanceMax(Integer num) {
        this.digitalPaymentCardBalanceMax = num;
        return this;
    }

    @Nonnull
    public Integer getDigitalPaymentCardBalanceMax() {
        return this.digitalPaymentCardBalanceMax;
    }

    public void setDigitalPaymentCardBalanceMax(Integer num) {
        this.digitalPaymentCardBalanceMax = num;
    }

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse digitalPaymentCardBalance(Integer num) {
        this.digitalPaymentCardBalance = num;
        return this;
    }

    @Nonnull
    public Integer getDigitalPaymentCardBalance() {
        return this.digitalPaymentCardBalance;
    }

    public void setDigitalPaymentCardBalance(Integer num) {
        this.digitalPaymentCardBalance = num;
    }

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse points(Integer num) {
        this.points = num;
        return this;
    }

    @Nonnull
    public Integer getPoints() {
        return this.points;
    }

    public void setPoints(Integer num) {
        this.points = num;
    }

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse pointsPerBlock(Integer num) {
        this.pointsPerBlock = num;
        return this;
    }

    @Nonnull
    public Integer getPointsPerBlock() {
        return this.pointsPerBlock;
    }

    public void setPointsPerBlock(Integer num) {
        this.pointsPerBlock = num;
    }

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse status(CardsAccountDigitalPaymentCardLoadWithSuperpointsStatus cardsAccountDigitalPaymentCardLoadWithSuperpointsStatus) {
        this.status = cardsAccountDigitalPaymentCardLoadWithSuperpointsStatus;
        return this;
    }

    @Nonnull
    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatus getStatus() {
        return this.status;
    }

    public void setStatus(CardsAccountDigitalPaymentCardLoadWithSuperpointsStatus cardsAccountDigitalPaymentCardLoadWithSuperpointsStatus) {
        this.status = cardsAccountDigitalPaymentCardLoadWithSuperpointsStatus;
    }

    public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse putUndeclaredProperty(String str, Object obj) {
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
        CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse = (CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse) obj;
        return Objects.equals(this.blocks, cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.blocks) && Objects.equals(this.centsPerBlock, cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.centsPerBlock) && Objects.equals(this.code, cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.code) && Objects.equals(this.currency, cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.currency) && Objects.equals(this.digitalPaymentCardBalanceMax, cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.digitalPaymentCardBalanceMax) && Objects.equals(this.digitalPaymentCardBalance, cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.digitalPaymentCardBalance) && Objects.equals(this.points, cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.points) && Objects.equals(this.pointsPerBlock, cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.pointsPerBlock) && Objects.equals(this.status, cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.status) && Objects.equals(this.undeclaredProperties, cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.blocks, this.centsPerBlock, this.code, this.currency, this.digitalPaymentCardBalanceMax, this.digitalPaymentCardBalance, this.points, this.pointsPerBlock, this.status, this.undeclaredProperties);
    }

    public String toString() {
        return "class CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse {\n    blocks: " + toIndentedString(this.blocks) + "\n    centsPerBlock: " + toIndentedString(this.centsPerBlock) + "\n    code: " + toIndentedString(this.code) + "\n    currency: " + toIndentedString(this.currency) + "\n    digitalPaymentCardBalanceMax: " + toIndentedString(this.digitalPaymentCardBalanceMax) + "\n    digitalPaymentCardBalance: " + toIndentedString(this.digitalPaymentCardBalance) + "\n    points: " + toIndentedString(this.points) + "\n    pointsPerBlock: " + toIndentedString(this.pointsPerBlock) + "\n    status: " + toIndentedString(this.status) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add(SERIALIZED_NAME_BLOCKS);
        openapiFields.add(SERIALIZED_NAME_CENTS_PER_BLOCK);
        openapiFields.add("code");
        openapiFields.add("currency");
        openapiFields.add(SERIALIZED_NAME_DIGITAL_PAYMENT_CARD_BALANCE_MAX);
        openapiFields.add(SERIALIZED_NAME_DIGITAL_PAYMENT_CARD_BALANCE);
        openapiFields.add("points");
        openapiFields.add(SERIALIZED_NAME_POINTS_PER_BLOCK);
        openapiFields.add("status");
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add(SERIALIZED_NAME_BLOCKS);
        openapiRequiredFields.add(SERIALIZED_NAME_CENTS_PER_BLOCK);
        openapiRequiredFields.add("code");
        openapiRequiredFields.add("currency");
        openapiRequiredFields.add(SERIALIZED_NAME_DIGITAL_PAYMENT_CARD_BALANCE_MAX);
        openapiRequiredFields.add(SERIALIZED_NAME_DIGITAL_PAYMENT_CARD_BALANCE);
        openapiRequiredFields.add("points");
        openapiRequiredFields.add(SERIALIZED_NAME_POINTS_PER_BLOCK);
        openapiRequiredFields.add("status");
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
        CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusBlocks.validateJsonObject(jsonObject.getAsJsonObject(SERIALIZED_NAME_BLOCKS));
        if (!jsonObject.get("code").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `code` to be a primitive type in the JSON string but got `%s`", jsonObject.get("code").toString()));
        }
        if (!jsonObject.get("currency").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `currency` to be a primitive type in the JSON string but got `%s`", jsonObject.get("currency").toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.class));
            return (TypeAdapter<T>) new TypeAdapter<CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse>() { // from class: ch.coop.apidia.appGateway.cards.model.CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.getUndeclaredProperties().entrySet()) {
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
                public CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.validateJsonObject(asJsonObject);
                    CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse = (CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return cardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse;
                }
            }.nullSafe();
        }
    }

    public static CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse fromJson(String str) throws IOException {
        return (CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse) JSON.getGson().fromJson(str, CardsAccountDigitalPaymentCardLoadWithSuperpointsStatusResponse.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
