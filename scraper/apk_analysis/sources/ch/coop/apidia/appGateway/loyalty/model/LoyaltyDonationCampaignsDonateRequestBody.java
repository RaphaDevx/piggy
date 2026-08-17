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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class LoyaltyDonationCampaignsDonateRequestBody {
    public static final String SERIALIZED_NAME_CODE = "code";
    public static final String SERIALIZED_NAME_POINTS = "points";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName("code")
    private String code;

    @SerializedName("points")
    private Integer points;
    private Map<String, Object> undeclaredProperties;

    public LoyaltyDonationCampaignsDonateRequestBody code(String str) {
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

    public LoyaltyDonationCampaignsDonateRequestBody points(Integer num) {
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

    public LoyaltyDonationCampaignsDonateRequestBody putUndeclaredProperty(String str, Object obj) {
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
        LoyaltyDonationCampaignsDonateRequestBody loyaltyDonationCampaignsDonateRequestBody = (LoyaltyDonationCampaignsDonateRequestBody) obj;
        return Objects.equals(this.code, loyaltyDonationCampaignsDonateRequestBody.code) && Objects.equals(this.points, loyaltyDonationCampaignsDonateRequestBody.points) && Objects.equals(this.undeclaredProperties, loyaltyDonationCampaignsDonateRequestBody.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.code, this.points, this.undeclaredProperties);
    }

    public String toString() {
        return "class LoyaltyDonationCampaignsDonateRequestBody {\n    code: " + toIndentedString(this.code) + "\n    points: " + toIndentedString(this.points) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        openapiFields.add("points");
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add("code");
        openapiRequiredFields.add("points");
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in LoyaltyDonationCampaignsDonateRequestBody is not found in the empty JSON string", openapiRequiredFields.toString()));
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
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!LoyaltyDonationCampaignsDonateRequestBody.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(LoyaltyDonationCampaignsDonateRequestBody.class));
            return (TypeAdapter<T>) new TypeAdapter<LoyaltyDonationCampaignsDonateRequestBody>() { // from class: ch.coop.apidia.appGateway.loyalty.model.LoyaltyDonationCampaignsDonateRequestBody.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, LoyaltyDonationCampaignsDonateRequestBody loyaltyDonationCampaignsDonateRequestBody) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(loyaltyDonationCampaignsDonateRequestBody).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (loyaltyDonationCampaignsDonateRequestBody.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : loyaltyDonationCampaignsDonateRequestBody.getUndeclaredProperties().entrySet()) {
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
                public LoyaltyDonationCampaignsDonateRequestBody read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    LoyaltyDonationCampaignsDonateRequestBody.validateJsonObject(asJsonObject);
                    LoyaltyDonationCampaignsDonateRequestBody loyaltyDonationCampaignsDonateRequestBody = (LoyaltyDonationCampaignsDonateRequestBody) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!LoyaltyDonationCampaignsDonateRequestBody.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    loyaltyDonationCampaignsDonateRequestBody.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    loyaltyDonationCampaignsDonateRequestBody.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    loyaltyDonationCampaignsDonateRequestBody.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                loyaltyDonationCampaignsDonateRequestBody.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                loyaltyDonationCampaignsDonateRequestBody.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return loyaltyDonationCampaignsDonateRequestBody;
                }
            }.nullSafe();
        }
    }

    public static LoyaltyDonationCampaignsDonateRequestBody fromJson(String str) throws IOException {
        return (LoyaltyDonationCampaignsDonateRequestBody) JSON.getGson().fromJson(str, LoyaltyDonationCampaignsDonateRequestBody.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
