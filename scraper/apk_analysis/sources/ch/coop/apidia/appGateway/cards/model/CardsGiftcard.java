package ch.coop.apidia.appGateway.cards.model;

import ch.coop.apidia.appGateway.cards.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
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
public class CardsGiftcard {
    public static final String SERIALIZED_NAME_BALANCE_CURRENCY = "balanceCurrency";
    public static final String SERIALIZED_NAME_BALANCE_MAX = "balanceMax";
    public static final String SERIALIZED_NAME_BALANCE_VALUE = "balanceValue";
    public static final String SERIALIZED_NAME_CARD_NR = "cardNr";
    public static final String SERIALIZED_NAME_CARD_NR_FORMATTED = "cardNrFormatted";
    public static final String SERIALIZED_NAME_CHILD_PROTECTION = "childProtection";
    public static final String SERIALIZED_NAME_EXPIRATION_DATE = "expirationDate";
    public static final String SERIALIZED_NAME_FORMAT_SPECIFIC = "formatSpecific";
    public static final String SERIALIZED_NAME_GROUP_KEY = "groupKey";
    public static final String SERIALIZED_NAME_IS_TRANSFERABLE = "isTransferable";
    public static final String SERIALIZED_NAME_LINKS = "links";
    public static final String SERIALIZED_NAME_LOGO = "logo";
    public static final String SERIALIZED_NAME_NAME = "name";
    public static final String SERIALIZED_NAME_SUPERCARD_PAY_STATUS = "supercardPayStatus";
    public static final String SERIALIZED_NAME_TRANSACTIONS = "transactions";
    public static final String SERIALIZED_NAME_TYPE = "type";
    public static final String SERIALIZED_NAME_VALIDITY_STATUS = "validityStatus";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName("balanceCurrency")
    private String balanceCurrency;

    @SerializedName(SERIALIZED_NAME_BALANCE_MAX)
    private Integer balanceMax;

    @SerializedName("balanceValue")
    private Integer balanceValue;

    @SerializedName("cardNr")
    private String cardNr;

    @SerializedName(SERIALIZED_NAME_CARD_NR_FORMATTED)
    private String cardNrFormatted;

    @SerializedName(SERIALIZED_NAME_CHILD_PROTECTION)
    private Boolean childProtection;

    @SerializedName(SERIALIZED_NAME_EXPIRATION_DATE)
    private OffsetDateTime expirationDate;

    @SerializedName(SERIALIZED_NAME_FORMAT_SPECIFIC)
    private Boolean formatSpecific;

    @SerializedName(SERIALIZED_NAME_GROUP_KEY)
    private String groupKey;

    @SerializedName(SERIALIZED_NAME_IS_TRANSFERABLE)
    private Boolean isTransferable;

    @SerializedName(SERIALIZED_NAME_LINKS)
    private List<CardsGiftcardLink> links = new ArrayList();

    @SerializedName("logo")
    private List<Image> logo;

    @SerializedName("name")
    private String name;

    @SerializedName(SERIALIZED_NAME_SUPERCARD_PAY_STATUS)
    private CardsSupercardPayStatusEnum supercardPayStatus;

    @SerializedName("transactions")
    private List<CardsGiftcardTransaction> transactions;

    @SerializedName("type")
    private CardsGiftcardTypeEnum type;
    private Map<String, Object> undeclaredProperties;

    @SerializedName(SERIALIZED_NAME_VALIDITY_STATUS)
    private CardsGiftcardValidityStatusEnum validityStatus;

    public CardsGiftcard balanceCurrency(String str) {
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

    public CardsGiftcard balanceMax(Integer num) {
        this.balanceMax = num;
        return this;
    }

    @Nonnull
    public Integer getBalanceMax() {
        return this.balanceMax;
    }

    public void setBalanceMax(Integer num) {
        this.balanceMax = num;
    }

    public CardsGiftcard balanceValue(Integer num) {
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

    public CardsGiftcard cardNr(String str) {
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

    public CardsGiftcard cardNrFormatted(String str) {
        this.cardNrFormatted = str;
        return this;
    }

    @Nonnull
    public String getCardNrFormatted() {
        return this.cardNrFormatted;
    }

    public void setCardNrFormatted(String str) {
        this.cardNrFormatted = str;
    }

    public CardsGiftcard childProtection(Boolean bool) {
        this.childProtection = bool;
        return this;
    }

    @Nonnull
    public Boolean getChildProtection() {
        return this.childProtection;
    }

    public void setChildProtection(Boolean bool) {
        this.childProtection = bool;
    }

    public CardsGiftcard expirationDate(OffsetDateTime offsetDateTime) {
        this.expirationDate = offsetDateTime;
        return this;
    }

    @Nullable
    public OffsetDateTime getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(OffsetDateTime offsetDateTime) {
        this.expirationDate = offsetDateTime;
    }

    public CardsGiftcard formatSpecific(Boolean bool) {
        this.formatSpecific = bool;
        return this;
    }

    @Nonnull
    public Boolean getFormatSpecific() {
        return this.formatSpecific;
    }

    public void setFormatSpecific(Boolean bool) {
        this.formatSpecific = bool;
    }

    public CardsGiftcard groupKey(String str) {
        this.groupKey = str;
        return this;
    }

    @Nonnull
    public String getGroupKey() {
        return this.groupKey;
    }

    public void setGroupKey(String str) {
        this.groupKey = str;
    }

    public CardsGiftcard isTransferable(Boolean bool) {
        this.isTransferable = bool;
        return this;
    }

    @Nonnull
    public Boolean getIsTransferable() {
        return this.isTransferable;
    }

    public void setIsTransferable(Boolean bool) {
        this.isTransferable = bool;
    }

    public CardsGiftcard links(List<CardsGiftcardLink> list) {
        this.links = list;
        return this;
    }

    public CardsGiftcard addLinksItem(CardsGiftcardLink cardsGiftcardLink) {
        if (this.links == null) {
            this.links = new ArrayList();
        }
        this.links.add(cardsGiftcardLink);
        return this;
    }

    @Nonnull
    public List<CardsGiftcardLink> getLinks() {
        return this.links;
    }

    public void setLinks(List<CardsGiftcardLink> list) {
        this.links = list;
    }

    public CardsGiftcard logo(List<Image> list) {
        this.logo = list;
        return this;
    }

    public CardsGiftcard addLogoItem(Image image) {
        if (this.logo == null) {
            this.logo = new ArrayList();
        }
        this.logo.add(image);
        return this;
    }

    @Nullable
    public List<Image> getLogo() {
        return this.logo;
    }

    public void setLogo(List<Image> list) {
        this.logo = list;
    }

    public CardsGiftcard name(String str) {
        this.name = str;
        return this;
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public CardsGiftcard supercardPayStatus(CardsSupercardPayStatusEnum cardsSupercardPayStatusEnum) {
        this.supercardPayStatus = cardsSupercardPayStatusEnum;
        return this;
    }

    @Nonnull
    public CardsSupercardPayStatusEnum getSupercardPayStatus() {
        return this.supercardPayStatus;
    }

    public void setSupercardPayStatus(CardsSupercardPayStatusEnum cardsSupercardPayStatusEnum) {
        this.supercardPayStatus = cardsSupercardPayStatusEnum;
    }

    public CardsGiftcard transactions(List<CardsGiftcardTransaction> list) {
        this.transactions = list;
        return this;
    }

    public CardsGiftcard addTransactionsItem(CardsGiftcardTransaction cardsGiftcardTransaction) {
        if (this.transactions == null) {
            this.transactions = new ArrayList();
        }
        this.transactions.add(cardsGiftcardTransaction);
        return this;
    }

    @Nullable
    public List<CardsGiftcardTransaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(List<CardsGiftcardTransaction> list) {
        this.transactions = list;
    }

    public CardsGiftcard type(CardsGiftcardTypeEnum cardsGiftcardTypeEnum) {
        this.type = cardsGiftcardTypeEnum;
        return this;
    }

    @Nonnull
    public CardsGiftcardTypeEnum getType() {
        return this.type;
    }

    public void setType(CardsGiftcardTypeEnum cardsGiftcardTypeEnum) {
        this.type = cardsGiftcardTypeEnum;
    }

    public CardsGiftcard validityStatus(CardsGiftcardValidityStatusEnum cardsGiftcardValidityStatusEnum) {
        this.validityStatus = cardsGiftcardValidityStatusEnum;
        return this;
    }

    @Nonnull
    public CardsGiftcardValidityStatusEnum getValidityStatus() {
        return this.validityStatus;
    }

    public void setValidityStatus(CardsGiftcardValidityStatusEnum cardsGiftcardValidityStatusEnum) {
        this.validityStatus = cardsGiftcardValidityStatusEnum;
    }

    public CardsGiftcard putUndeclaredProperty(String str, Object obj) {
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
        CardsGiftcard cardsGiftcard = (CardsGiftcard) obj;
        return Objects.equals(this.balanceCurrency, cardsGiftcard.balanceCurrency) && Objects.equals(this.balanceMax, cardsGiftcard.balanceMax) && Objects.equals(this.balanceValue, cardsGiftcard.balanceValue) && Objects.equals(this.cardNr, cardsGiftcard.cardNr) && Objects.equals(this.cardNrFormatted, cardsGiftcard.cardNrFormatted) && Objects.equals(this.childProtection, cardsGiftcard.childProtection) && Objects.equals(this.expirationDate, cardsGiftcard.expirationDate) && Objects.equals(this.formatSpecific, cardsGiftcard.formatSpecific) && Objects.equals(this.groupKey, cardsGiftcard.groupKey) && Objects.equals(this.isTransferable, cardsGiftcard.isTransferable) && Objects.equals(this.links, cardsGiftcard.links) && Objects.equals(this.logo, cardsGiftcard.logo) && Objects.equals(this.name, cardsGiftcard.name) && Objects.equals(this.supercardPayStatus, cardsGiftcard.supercardPayStatus) && Objects.equals(this.transactions, cardsGiftcard.transactions) && Objects.equals(this.type, cardsGiftcard.type) && Objects.equals(this.validityStatus, cardsGiftcard.validityStatus) && Objects.equals(this.undeclaredProperties, cardsGiftcard.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.balanceCurrency, this.balanceMax, this.balanceValue, this.cardNr, this.cardNrFormatted, this.childProtection, this.expirationDate, this.formatSpecific, this.groupKey, this.isTransferable, this.links, this.logo, this.name, this.supercardPayStatus, this.transactions, this.type, this.validityStatus, this.undeclaredProperties);
    }

    public String toString() {
        return "class CardsGiftcard {\n    balanceCurrency: " + toIndentedString(this.balanceCurrency) + "\n    balanceMax: " + toIndentedString(this.balanceMax) + "\n    balanceValue: " + toIndentedString(this.balanceValue) + "\n    cardNr: " + toIndentedString(this.cardNr) + "\n    cardNrFormatted: " + toIndentedString(this.cardNrFormatted) + "\n    childProtection: " + toIndentedString(this.childProtection) + "\n    expirationDate: " + toIndentedString(this.expirationDate) + "\n    formatSpecific: " + toIndentedString(this.formatSpecific) + "\n    groupKey: " + toIndentedString(this.groupKey) + "\n    isTransferable: " + toIndentedString(this.isTransferable) + "\n    links: " + toIndentedString(this.links) + "\n    logo: " + toIndentedString(this.logo) + "\n    name: " + toIndentedString(this.name) + "\n    supercardPayStatus: " + toIndentedString(this.supercardPayStatus) + "\n    transactions: " + toIndentedString(this.transactions) + "\n    type: " + toIndentedString(this.type) + "\n    validityStatus: " + toIndentedString(this.validityStatus) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        openapiFields.add(SERIALIZED_NAME_BALANCE_MAX);
        openapiFields.add("balanceValue");
        openapiFields.add("cardNr");
        openapiFields.add(SERIALIZED_NAME_CARD_NR_FORMATTED);
        openapiFields.add(SERIALIZED_NAME_CHILD_PROTECTION);
        openapiFields.add(SERIALIZED_NAME_EXPIRATION_DATE);
        openapiFields.add(SERIALIZED_NAME_FORMAT_SPECIFIC);
        openapiFields.add(SERIALIZED_NAME_GROUP_KEY);
        openapiFields.add(SERIALIZED_NAME_IS_TRANSFERABLE);
        openapiFields.add(SERIALIZED_NAME_LINKS);
        openapiFields.add("logo");
        openapiFields.add("name");
        openapiFields.add(SERIALIZED_NAME_SUPERCARD_PAY_STATUS);
        openapiFields.add("transactions");
        openapiFields.add("type");
        openapiFields.add(SERIALIZED_NAME_VALIDITY_STATUS);
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add("balanceCurrency");
        openapiRequiredFields.add(SERIALIZED_NAME_BALANCE_MAX);
        openapiRequiredFields.add("balanceValue");
        openapiRequiredFields.add("cardNr");
        openapiRequiredFields.add(SERIALIZED_NAME_CARD_NR_FORMATTED);
        openapiRequiredFields.add(SERIALIZED_NAME_CHILD_PROTECTION);
        openapiRequiredFields.add(SERIALIZED_NAME_FORMAT_SPECIFIC);
        openapiRequiredFields.add(SERIALIZED_NAME_GROUP_KEY);
        openapiRequiredFields.add(SERIALIZED_NAME_IS_TRANSFERABLE);
        openapiRequiredFields.add(SERIALIZED_NAME_LINKS);
        openapiRequiredFields.add("name");
        openapiRequiredFields.add(SERIALIZED_NAME_SUPERCARD_PAY_STATUS);
        openapiRequiredFields.add("type");
        openapiRequiredFields.add(SERIALIZED_NAME_VALIDITY_STATUS);
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        JsonArray asJsonArray;
        JsonArray asJsonArray2;
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in CardsGiftcard is not found in the empty JSON string", openapiRequiredFields.toString()));
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
        if (!jsonObject.get("cardNr").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `cardNr` to be a primitive type in the JSON string but got `%s`", jsonObject.get("cardNr").toString()));
        }
        if (!jsonObject.get(SERIALIZED_NAME_CARD_NR_FORMATTED).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `cardNrFormatted` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_CARD_NR_FORMATTED).toString()));
        }
        if (!jsonObject.get(SERIALIZED_NAME_GROUP_KEY).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `groupKey` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_GROUP_KEY).toString()));
        }
        if (!jsonObject.get(SERIALIZED_NAME_LINKS).isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `links` to be an array in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_LINKS).toString()));
        }
        JsonArray asJsonArray3 = jsonObject.getAsJsonArray(SERIALIZED_NAME_LINKS);
        for (int i = 0; i < asJsonArray3.size(); i++) {
            CardsGiftcardLink.validateJsonObject(asJsonArray3.get(i).getAsJsonObject());
        }
        if (jsonObject.get("logo") != null && !jsonObject.get("logo").isJsonNull() && (asJsonArray2 = jsonObject.getAsJsonArray("logo")) != null) {
            if (!jsonObject.get("logo").isJsonArray()) {
                throw new IllegalArgumentException(String.format("Expected the field `logo` to be an array in the JSON string but got `%s`", jsonObject.get("logo").toString()));
            }
            for (int i2 = 0; i2 < asJsonArray2.size(); i2++) {
                Image.validateJsonObject(asJsonArray2.get(i2).getAsJsonObject());
            }
        }
        if (!jsonObject.get("name").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `name` to be a primitive type in the JSON string but got `%s`", jsonObject.get("name").toString()));
        }
        if (jsonObject.get("transactions") == null || jsonObject.get("transactions").isJsonNull() || (asJsonArray = jsonObject.getAsJsonArray("transactions")) == null) {
            return;
        }
        if (!jsonObject.get("transactions").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `transactions` to be an array in the JSON string but got `%s`", jsonObject.get("transactions").toString()));
        }
        for (int i3 = 0; i3 < asJsonArray.size(); i3++) {
            CardsGiftcardTransaction.validateJsonObject(asJsonArray.get(i3).getAsJsonObject());
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!CardsGiftcard.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(CardsGiftcard.class));
            return (TypeAdapter<T>) new TypeAdapter<CardsGiftcard>() { // from class: ch.coop.apidia.appGateway.cards.model.CardsGiftcard.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, CardsGiftcard cardsGiftcard) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(cardsGiftcard).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (cardsGiftcard.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : cardsGiftcard.getUndeclaredProperties().entrySet()) {
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
                public CardsGiftcard read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    CardsGiftcard.validateJsonObject(asJsonObject);
                    CardsGiftcard cardsGiftcard = (CardsGiftcard) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!CardsGiftcard.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    cardsGiftcard.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    cardsGiftcard.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    cardsGiftcard.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                cardsGiftcard.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                cardsGiftcard.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return cardsGiftcard;
                }
            }.nullSafe();
        }
    }

    public static CardsGiftcard fromJson(String str) throws IOException {
        return (CardsGiftcard) JSON.getGson().fromJson(str, CardsGiftcard.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
