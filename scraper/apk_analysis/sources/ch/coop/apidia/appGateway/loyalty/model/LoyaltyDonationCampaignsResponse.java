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
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class LoyaltyDonationCampaignsResponse {
    public static final String SERIALIZED_NAME_DONATION_CAMPAIGNS = "donationCampaigns";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName(SERIALIZED_NAME_DONATION_CAMPAIGNS)
    private List<LoyaltyDonationCampaign> donationCampaigns = new ArrayList();
    private Map<String, Object> undeclaredProperties;

    public LoyaltyDonationCampaignsResponse donationCampaigns(List<LoyaltyDonationCampaign> list) {
        this.donationCampaigns = list;
        return this;
    }

    public LoyaltyDonationCampaignsResponse addDonationCampaignsItem(LoyaltyDonationCampaign loyaltyDonationCampaign) {
        if (this.donationCampaigns == null) {
            this.donationCampaigns = new ArrayList();
        }
        this.donationCampaigns.add(loyaltyDonationCampaign);
        return this;
    }

    @Nonnull
    public List<LoyaltyDonationCampaign> getDonationCampaigns() {
        return this.donationCampaigns;
    }

    public void setDonationCampaigns(List<LoyaltyDonationCampaign> list) {
        this.donationCampaigns = list;
    }

    public LoyaltyDonationCampaignsResponse putUndeclaredProperty(String str, Object obj) {
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
        LoyaltyDonationCampaignsResponse loyaltyDonationCampaignsResponse = (LoyaltyDonationCampaignsResponse) obj;
        return Objects.equals(this.donationCampaigns, loyaltyDonationCampaignsResponse.donationCampaigns) && Objects.equals(this.undeclaredProperties, loyaltyDonationCampaignsResponse.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.donationCampaigns, this.undeclaredProperties);
    }

    public String toString() {
        return "class LoyaltyDonationCampaignsResponse {\n    donationCampaigns: " + toIndentedString(this.donationCampaigns) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add(SERIALIZED_NAME_DONATION_CAMPAIGNS);
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add(SERIALIZED_NAME_DONATION_CAMPAIGNS);
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in LoyaltyDonationCampaignsResponse is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
        if (!jsonObject.get(SERIALIZED_NAME_DONATION_CAMPAIGNS).isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `donationCampaigns` to be an array in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_DONATION_CAMPAIGNS).toString()));
        }
        JsonArray asJsonArray = jsonObject.getAsJsonArray(SERIALIZED_NAME_DONATION_CAMPAIGNS);
        for (int i = 0; i < asJsonArray.size(); i++) {
            LoyaltyDonationCampaign.validateJsonObject(asJsonArray.get(i).getAsJsonObject());
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!LoyaltyDonationCampaignsResponse.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(LoyaltyDonationCampaignsResponse.class));
            return (TypeAdapter<T>) new TypeAdapter<LoyaltyDonationCampaignsResponse>() { // from class: ch.coop.apidia.appGateway.loyalty.model.LoyaltyDonationCampaignsResponse.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, LoyaltyDonationCampaignsResponse loyaltyDonationCampaignsResponse) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(loyaltyDonationCampaignsResponse).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (loyaltyDonationCampaignsResponse.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : loyaltyDonationCampaignsResponse.getUndeclaredProperties().entrySet()) {
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
                public LoyaltyDonationCampaignsResponse read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    LoyaltyDonationCampaignsResponse.validateJsonObject(asJsonObject);
                    LoyaltyDonationCampaignsResponse loyaltyDonationCampaignsResponse = (LoyaltyDonationCampaignsResponse) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!LoyaltyDonationCampaignsResponse.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    loyaltyDonationCampaignsResponse.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    loyaltyDonationCampaignsResponse.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    loyaltyDonationCampaignsResponse.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                loyaltyDonationCampaignsResponse.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                loyaltyDonationCampaignsResponse.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return loyaltyDonationCampaignsResponse;
                }
            }.nullSafe();
        }
    }

    public static LoyaltyDonationCampaignsResponse fromJson(String str) throws IOException {
        return (LoyaltyDonationCampaignsResponse) JSON.getGson().fromJson(str, LoyaltyDonationCampaignsResponse.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
