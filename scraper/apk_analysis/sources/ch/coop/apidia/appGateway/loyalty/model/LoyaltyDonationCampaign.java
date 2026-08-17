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
public class LoyaltyDonationCampaign {
    public static final String SERIALIZED_NAME_CODE = "code";
    public static final String SERIALIZED_NAME_DESCRIPTION = "description";
    public static final String SERIALIZED_NAME_END_DATE = "endDate";
    public static final String SERIALIZED_NAME_IMAGE_BANNER = "imageBanner";
    public static final String SERIALIZED_NAME_IMAGE_HEADER = "imageHeader";
    public static final String SERIALIZED_NAME_INFORMATION_TEXT = "informationText";
    public static final String SERIALIZED_NAME_PARTNER_LOGO = "partnerLogo";
    public static final String SERIALIZED_NAME_PARTNER_LOGOS = "partnerLogos";
    public static final String SERIALIZED_NAME_PARTNER_NAME = "partnerName";
    public static final String SERIALIZED_NAME_POINTS_DONATED = "pointsDonated";
    public static final String SERIALIZED_NAME_PRIORITY = "priority";
    public static final String SERIALIZED_NAME_TITLE = "title";
    public static final String SERIALIZED_NAME_TRACKING_NAME = "trackingName";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName("code")
    private String code;

    @SerializedName("description")
    private String description;

    @SerializedName(SERIALIZED_NAME_END_DATE)
    private OffsetDateTime endDate;

    @SerializedName(SERIALIZED_NAME_IMAGE_BANNER)
    private List<Image> imageBanner;

    @SerializedName(SERIALIZED_NAME_IMAGE_HEADER)
    private List<Image> imageHeader;

    @SerializedName(SERIALIZED_NAME_INFORMATION_TEXT)
    private String informationText;

    @SerializedName(SERIALIZED_NAME_PARTNER_LOGO)
    private List<Image> partnerLogo;

    @SerializedName(SERIALIZED_NAME_PARTNER_LOGOS)
    private List<List<Image>> partnerLogos;

    @SerializedName("partnerName")
    private String partnerName;

    @SerializedName(SERIALIZED_NAME_POINTS_DONATED)
    private Integer pointsDonated;

    @SerializedName("priority")
    private Integer priority;

    @SerializedName("title")
    private String title;

    @SerializedName(SERIALIZED_NAME_TRACKING_NAME)
    private String trackingName;
    private Map<String, Object> undeclaredProperties;

    public LoyaltyDonationCampaign code(String str) {
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

    public LoyaltyDonationCampaign priority(Integer num) {
        this.priority = num;
        return this;
    }

    @Nonnull
    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer num) {
        this.priority = num;
    }

    public LoyaltyDonationCampaign endDate(OffsetDateTime offsetDateTime) {
        this.endDate = offsetDateTime;
        return this;
    }

    @Nonnull
    public OffsetDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(OffsetDateTime offsetDateTime) {
        this.endDate = offsetDateTime;
    }

    public LoyaltyDonationCampaign pointsDonated(Integer num) {
        this.pointsDonated = num;
        return this;
    }

    @Nonnull
    public Integer getPointsDonated() {
        return this.pointsDonated;
    }

    public void setPointsDonated(Integer num) {
        this.pointsDonated = num;
    }

    public LoyaltyDonationCampaign imageBanner(List<Image> list) {
        this.imageBanner = list;
        return this;
    }

    public LoyaltyDonationCampaign addImageBannerItem(Image image) {
        if (this.imageBanner == null) {
            this.imageBanner = new ArrayList();
        }
        this.imageBanner.add(image);
        return this;
    }

    @Nullable
    public List<Image> getImageBanner() {
        return this.imageBanner;
    }

    public void setImageBanner(List<Image> list) {
        this.imageBanner = list;
    }

    public LoyaltyDonationCampaign imageHeader(List<Image> list) {
        this.imageHeader = list;
        return this;
    }

    public LoyaltyDonationCampaign addImageHeaderItem(Image image) {
        if (this.imageHeader == null) {
            this.imageHeader = new ArrayList();
        }
        this.imageHeader.add(image);
        return this;
    }

    @Nullable
    public List<Image> getImageHeader() {
        return this.imageHeader;
    }

    public void setImageHeader(List<Image> list) {
        this.imageHeader = list;
    }

    public LoyaltyDonationCampaign partnerLogo(List<Image> list) {
        this.partnerLogo = list;
        return this;
    }

    public LoyaltyDonationCampaign addPartnerLogoItem(Image image) {
        if (this.partnerLogo == null) {
            this.partnerLogo = new ArrayList();
        }
        this.partnerLogo.add(image);
        return this;
    }

    @Nullable
    public List<Image> getPartnerLogo() {
        return this.partnerLogo;
    }

    public void setPartnerLogo(List<Image> list) {
        this.partnerLogo = list;
    }

    public LoyaltyDonationCampaign partnerLogos(List<List<Image>> list) {
        this.partnerLogos = list;
        return this;
    }

    public LoyaltyDonationCampaign addPartnerLogosItem(List<Image> list) {
        if (this.partnerLogos == null) {
            this.partnerLogos = new ArrayList();
        }
        this.partnerLogos.add(list);
        return this;
    }

    @Nullable
    public List<List<Image>> getPartnerLogos() {
        return this.partnerLogos;
    }

    public void setPartnerLogos(List<List<Image>> list) {
        this.partnerLogos = list;
    }

    public LoyaltyDonationCampaign partnerName(String str) {
        this.partnerName = str;
        return this;
    }

    @Nullable
    public String getPartnerName() {
        return this.partnerName;
    }

    public void setPartnerName(String str) {
        this.partnerName = str;
    }

    public LoyaltyDonationCampaign title(String str) {
        this.title = str;
        return this;
    }

    @Nonnull
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public LoyaltyDonationCampaign description(String str) {
        this.description = str;
        return this;
    }

    @Nullable
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public LoyaltyDonationCampaign informationText(String str) {
        this.informationText = str;
        return this;
    }

    @Nullable
    public String getInformationText() {
        return this.informationText;
    }

    public void setInformationText(String str) {
        this.informationText = str;
    }

    public LoyaltyDonationCampaign trackingName(String str) {
        this.trackingName = str;
        return this;
    }

    @Nonnull
    public String getTrackingName() {
        return this.trackingName;
    }

    public void setTrackingName(String str) {
        this.trackingName = str;
    }

    public LoyaltyDonationCampaign putUndeclaredProperty(String str, Object obj) {
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
        LoyaltyDonationCampaign loyaltyDonationCampaign = (LoyaltyDonationCampaign) obj;
        return Objects.equals(this.code, loyaltyDonationCampaign.code) && Objects.equals(this.priority, loyaltyDonationCampaign.priority) && Objects.equals(this.endDate, loyaltyDonationCampaign.endDate) && Objects.equals(this.pointsDonated, loyaltyDonationCampaign.pointsDonated) && Objects.equals(this.imageBanner, loyaltyDonationCampaign.imageBanner) && Objects.equals(this.imageHeader, loyaltyDonationCampaign.imageHeader) && Objects.equals(this.partnerLogo, loyaltyDonationCampaign.partnerLogo) && Objects.equals(this.partnerLogos, loyaltyDonationCampaign.partnerLogos) && Objects.equals(this.partnerName, loyaltyDonationCampaign.partnerName) && Objects.equals(this.title, loyaltyDonationCampaign.title) && Objects.equals(this.description, loyaltyDonationCampaign.description) && Objects.equals(this.informationText, loyaltyDonationCampaign.informationText) && Objects.equals(this.trackingName, loyaltyDonationCampaign.trackingName) && Objects.equals(this.undeclaredProperties, loyaltyDonationCampaign.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.code, this.priority, this.endDate, this.pointsDonated, this.imageBanner, this.imageHeader, this.partnerLogo, this.partnerLogos, this.partnerName, this.title, this.description, this.informationText, this.trackingName, this.undeclaredProperties);
    }

    public String toString() {
        return "class LoyaltyDonationCampaign {\n    code: " + toIndentedString(this.code) + "\n    priority: " + toIndentedString(this.priority) + "\n    endDate: " + toIndentedString(this.endDate) + "\n    pointsDonated: " + toIndentedString(this.pointsDonated) + "\n    imageBanner: " + toIndentedString(this.imageBanner) + "\n    imageHeader: " + toIndentedString(this.imageHeader) + "\n    partnerLogo: " + toIndentedString(this.partnerLogo) + "\n    partnerLogos: " + toIndentedString(this.partnerLogos) + "\n    partnerName: " + toIndentedString(this.partnerName) + "\n    title: " + toIndentedString(this.title) + "\n    description: " + toIndentedString(this.description) + "\n    informationText: " + toIndentedString(this.informationText) + "\n    trackingName: " + toIndentedString(this.trackingName) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        openapiFields.add("priority");
        openapiFields.add(SERIALIZED_NAME_END_DATE);
        openapiFields.add(SERIALIZED_NAME_POINTS_DONATED);
        openapiFields.add(SERIALIZED_NAME_IMAGE_BANNER);
        openapiFields.add(SERIALIZED_NAME_IMAGE_HEADER);
        openapiFields.add(SERIALIZED_NAME_PARTNER_LOGO);
        openapiFields.add(SERIALIZED_NAME_PARTNER_LOGOS);
        openapiFields.add("partnerName");
        openapiFields.add("title");
        openapiFields.add("description");
        openapiFields.add(SERIALIZED_NAME_INFORMATION_TEXT);
        openapiFields.add(SERIALIZED_NAME_TRACKING_NAME);
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add("code");
        openapiRequiredFields.add("priority");
        openapiRequiredFields.add(SERIALIZED_NAME_END_DATE);
        openapiRequiredFields.add(SERIALIZED_NAME_POINTS_DONATED);
        openapiRequiredFields.add("title");
        openapiRequiredFields.add(SERIALIZED_NAME_TRACKING_NAME);
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        JsonArray asJsonArray;
        JsonArray asJsonArray2;
        JsonArray asJsonArray3;
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in LoyaltyDonationCampaign is not found in the empty JSON string", openapiRequiredFields.toString()));
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
        if (jsonObject.get(SERIALIZED_NAME_IMAGE_BANNER) != null && !jsonObject.get(SERIALIZED_NAME_IMAGE_BANNER).isJsonNull() && (asJsonArray3 = jsonObject.getAsJsonArray(SERIALIZED_NAME_IMAGE_BANNER)) != null) {
            if (!jsonObject.get(SERIALIZED_NAME_IMAGE_BANNER).isJsonArray()) {
                throw new IllegalArgumentException(String.format("Expected the field `imageBanner` to be an array in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_IMAGE_BANNER).toString()));
            }
            for (int i = 0; i < asJsonArray3.size(); i++) {
                Image.validateJsonObject(asJsonArray3.get(i).getAsJsonObject());
            }
        }
        if (jsonObject.get(SERIALIZED_NAME_IMAGE_HEADER) != null && !jsonObject.get(SERIALIZED_NAME_IMAGE_HEADER).isJsonNull() && (asJsonArray2 = jsonObject.getAsJsonArray(SERIALIZED_NAME_IMAGE_HEADER)) != null) {
            if (!jsonObject.get(SERIALIZED_NAME_IMAGE_HEADER).isJsonArray()) {
                throw new IllegalArgumentException(String.format("Expected the field `imageHeader` to be an array in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_IMAGE_HEADER).toString()));
            }
            for (int i2 = 0; i2 < asJsonArray2.size(); i2++) {
                Image.validateJsonObject(asJsonArray2.get(i2).getAsJsonObject());
            }
        }
        if (jsonObject.get(SERIALIZED_NAME_PARTNER_LOGO) != null && !jsonObject.get(SERIALIZED_NAME_PARTNER_LOGO).isJsonNull() && (asJsonArray = jsonObject.getAsJsonArray(SERIALIZED_NAME_PARTNER_LOGO)) != null) {
            if (!jsonObject.get(SERIALIZED_NAME_PARTNER_LOGO).isJsonArray()) {
                throw new IllegalArgumentException(String.format("Expected the field `partnerLogo` to be an array in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_PARTNER_LOGO).toString()));
            }
            for (int i3 = 0; i3 < asJsonArray.size(); i3++) {
                Image.validateJsonObject(asJsonArray.get(i3).getAsJsonObject());
            }
        }
        if (jsonObject.get(SERIALIZED_NAME_PARTNER_LOGOS) != null && !jsonObject.get(SERIALIZED_NAME_PARTNER_LOGOS).isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `partnerLogos` to be an array in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_PARTNER_LOGOS).toString()));
        }
        if (jsonObject.get("partnerName") != null && !jsonObject.get("partnerName").isJsonNull() && !jsonObject.get("partnerName").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `partnerName` to be a primitive type in the JSON string but got `%s`", jsonObject.get("partnerName").toString()));
        }
        if (!jsonObject.get("title").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `title` to be a primitive type in the JSON string but got `%s`", jsonObject.get("title").toString()));
        }
        if (jsonObject.get("description") != null && !jsonObject.get("description").isJsonNull() && !jsonObject.get("description").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `description` to be a primitive type in the JSON string but got `%s`", jsonObject.get("description").toString()));
        }
        if (jsonObject.get(SERIALIZED_NAME_INFORMATION_TEXT) != null && !jsonObject.get(SERIALIZED_NAME_INFORMATION_TEXT).isJsonNull() && !jsonObject.get(SERIALIZED_NAME_INFORMATION_TEXT).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `informationText` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_INFORMATION_TEXT).toString()));
        }
        if (!jsonObject.get(SERIALIZED_NAME_TRACKING_NAME).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `trackingName` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_TRACKING_NAME).toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!LoyaltyDonationCampaign.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(LoyaltyDonationCampaign.class));
            return (TypeAdapter<T>) new TypeAdapter<LoyaltyDonationCampaign>() { // from class: ch.coop.apidia.appGateway.loyalty.model.LoyaltyDonationCampaign.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, LoyaltyDonationCampaign loyaltyDonationCampaign) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(loyaltyDonationCampaign).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (loyaltyDonationCampaign.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : loyaltyDonationCampaign.getUndeclaredProperties().entrySet()) {
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
                public LoyaltyDonationCampaign read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    LoyaltyDonationCampaign.validateJsonObject(asJsonObject);
                    LoyaltyDonationCampaign loyaltyDonationCampaign = (LoyaltyDonationCampaign) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!LoyaltyDonationCampaign.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    loyaltyDonationCampaign.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    loyaltyDonationCampaign.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    loyaltyDonationCampaign.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                loyaltyDonationCampaign.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                loyaltyDonationCampaign.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return loyaltyDonationCampaign;
                }
            }.nullSafe();
        }
    }

    public static LoyaltyDonationCampaign fromJson(String str) throws IOException {
        return (LoyaltyDonationCampaign) JSON.getGson().fromJson(str, LoyaltyDonationCampaign.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
