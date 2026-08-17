package ch.coop.apidia.appGateway.loyalty.model;

import ch.coop.apidia.appGateway.loyalty.JSON;
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
public class LoyaltySupercard {
    public static final String SERIALIZED_NAME_LOGO = "logo";
    public static final String SERIALIZED_NAME_POINTS = "points";
    public static final String SERIALIZED_NAME_SUPERCARD_NUMBER = "supercardNumber";
    public static final String SERIALIZED_NAME_TRANSACTIONS = "transactions";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName("logo")
    private List<Image> logo = new ArrayList();

    @SerializedName("points")
    private Integer points;

    @SerializedName(SERIALIZED_NAME_SUPERCARD_NUMBER)
    private String supercardNumber;

    @SerializedName("transactions")
    private List<LoyaltySupercardTransaction> transactions;
    private Map<String, Object> undeclaredProperties;

    public LoyaltySupercard logo(List<Image> list) {
        this.logo = list;
        return this;
    }

    public LoyaltySupercard addLogoItem(Image image) {
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

    public LoyaltySupercard supercardNumber(String str) {
        this.supercardNumber = str;
        return this;
    }

    @Nullable
    public String getSupercardNumber() {
        return this.supercardNumber;
    }

    public void setSupercardNumber(String str) {
        this.supercardNumber = str;
    }

    public LoyaltySupercard points(Integer num) {
        this.points = num;
        return this;
    }

    @Nullable
    public Integer getPoints() {
        return this.points;
    }

    public void setPoints(Integer num) {
        this.points = num;
    }

    public LoyaltySupercard transactions(List<LoyaltySupercardTransaction> list) {
        this.transactions = list;
        return this;
    }

    public LoyaltySupercard addTransactionsItem(LoyaltySupercardTransaction loyaltySupercardTransaction) {
        if (this.transactions == null) {
            this.transactions = new ArrayList();
        }
        this.transactions.add(loyaltySupercardTransaction);
        return this;
    }

    @Nullable
    public List<LoyaltySupercardTransaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(List<LoyaltySupercardTransaction> list) {
        this.transactions = list;
    }

    public LoyaltySupercard putUndeclaredProperty(String str, Object obj) {
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
        LoyaltySupercard loyaltySupercard = (LoyaltySupercard) obj;
        return Objects.equals(this.logo, loyaltySupercard.logo) && Objects.equals(this.supercardNumber, loyaltySupercard.supercardNumber) && Objects.equals(this.points, loyaltySupercard.points) && Objects.equals(this.transactions, loyaltySupercard.transactions) && Objects.equals(this.undeclaredProperties, loyaltySupercard.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.logo, this.supercardNumber, this.points, this.transactions, this.undeclaredProperties);
    }

    public String toString() {
        return "class LoyaltySupercard {\n    logo: " + toIndentedString(this.logo) + "\n    supercardNumber: " + toIndentedString(this.supercardNumber) + "\n    points: " + toIndentedString(this.points) + "\n    transactions: " + toIndentedString(this.transactions) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add("logo");
        openapiFields.add(SERIALIZED_NAME_SUPERCARD_NUMBER);
        openapiFields.add("points");
        openapiFields.add("transactions");
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add("logo");
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        JsonArray asJsonArray;
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in LoyaltySupercard is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
        if (!jsonObject.get("logo").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `logo` to be an array in the JSON string but got `%s`", jsonObject.get("logo").toString()));
        }
        JsonArray asJsonArray2 = jsonObject.getAsJsonArray("logo");
        for (int i = 0; i < asJsonArray2.size(); i++) {
            Image.validateJsonObject(asJsonArray2.get(i).getAsJsonObject());
        }
        if (jsonObject.get(SERIALIZED_NAME_SUPERCARD_NUMBER) != null && !jsonObject.get(SERIALIZED_NAME_SUPERCARD_NUMBER).isJsonNull() && !jsonObject.get(SERIALIZED_NAME_SUPERCARD_NUMBER).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `supercardNumber` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_SUPERCARD_NUMBER).toString()));
        }
        if (jsonObject.get("transactions") == null || jsonObject.get("transactions").isJsonNull() || (asJsonArray = jsonObject.getAsJsonArray("transactions")) == null) {
            return;
        }
        if (!jsonObject.get("transactions").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `transactions` to be an array in the JSON string but got `%s`", jsonObject.get("transactions").toString()));
        }
        for (int i2 = 0; i2 < asJsonArray.size(); i2++) {
            LoyaltySupercardTransaction.validateJsonObject(asJsonArray.get(i2).getAsJsonObject());
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!LoyaltySupercard.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(LoyaltySupercard.class));
            return (TypeAdapter<T>) new TypeAdapter<LoyaltySupercard>() { // from class: ch.coop.apidia.appGateway.loyalty.model.LoyaltySupercard.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, LoyaltySupercard loyaltySupercard) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(loyaltySupercard).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (loyaltySupercard.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : loyaltySupercard.getUndeclaredProperties().entrySet()) {
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
                public LoyaltySupercard read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    LoyaltySupercard.validateJsonObject(asJsonObject);
                    LoyaltySupercard loyaltySupercard = (LoyaltySupercard) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!LoyaltySupercard.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    loyaltySupercard.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    loyaltySupercard.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    loyaltySupercard.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                loyaltySupercard.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                loyaltySupercard.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return loyaltySupercard;
                }
            }.nullSafe();
        }
    }

    public static LoyaltySupercard fromJson(String str) throws IOException {
        return (LoyaltySupercard) JSON.getGson().fromJson(str, LoyaltySupercard.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
