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
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes3.dex */
public class LoyaltyDeviceMediumRequestBody {
    public static final String SERIALIZED_NAME_APP_ID = "appId";
    public static final String SERIALIZED_NAME_APP_LANGUAGE = "appLanguage";
    public static final String SERIALIZED_NAME_APP_NAME = "appName";
    public static final String SERIALIZED_NAME_APP_PUSH_ID = "appPushId";
    public static final String SERIALIZED_NAME_APP_VERSION = "appVersion";
    public static final String SERIALIZED_NAME_DEVICE_LANGUAGE = "deviceLanguage";
    public static final String SERIALIZED_NAME_DEVICE_MODEL = "deviceModel";
    public static final String SERIALIZED_NAME_DEVICE_NAME = "deviceName";
    public static final String SERIALIZED_NAME_MEDIUM_NAME = "mediumName";
    public static final String SERIALIZED_NAME_OS_NAME = "osName";
    public static final String SERIALIZED_NAME_OS_VERSION = "osVersion";
    public static final String SERIALIZED_NAME_UUID = "uuid";
    public static HashSet<String> openapiFields;
    public static HashSet<String> openapiRequiredFields;

    @SerializedName("appId")
    private String appId;

    @SerializedName(SERIALIZED_NAME_APP_LANGUAGE)
    private String appLanguage;

    @SerializedName(SERIALIZED_NAME_APP_NAME)
    private String appName;

    @SerializedName(SERIALIZED_NAME_APP_PUSH_ID)
    private String appPushId;

    @SerializedName(SERIALIZED_NAME_APP_VERSION)
    private String appVersion;

    @SerializedName(SERIALIZED_NAME_DEVICE_LANGUAGE)
    private String deviceLanguage;

    @SerializedName(SERIALIZED_NAME_DEVICE_MODEL)
    private String deviceModel;

    @SerializedName(SERIALIZED_NAME_DEVICE_NAME)
    private String deviceName;

    @SerializedName(SERIALIZED_NAME_MEDIUM_NAME)
    private String mediumName;

    @SerializedName(SERIALIZED_NAME_OS_NAME)
    private LoyaltyDeviceOsNameEnum osName;

    @SerializedName(SERIALIZED_NAME_OS_VERSION)
    private String osVersion;
    private Map<String, Object> undeclaredProperties;

    @SerializedName("uuid")
    private String uuid;

    public LoyaltyDeviceMediumRequestBody appId(String str) {
        this.appId = str;
        return this;
    }

    @Nonnull
    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public LoyaltyDeviceMediumRequestBody appLanguage(String str) {
        this.appLanguage = str;
        return this;
    }

    @Nonnull
    public String getAppLanguage() {
        return this.appLanguage;
    }

    public void setAppLanguage(String str) {
        this.appLanguage = str;
    }

    public LoyaltyDeviceMediumRequestBody appName(String str) {
        this.appName = str;
        return this;
    }

    @Nonnull
    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public LoyaltyDeviceMediumRequestBody appPushId(String str) {
        this.appPushId = str;
        return this;
    }

    @Nullable
    public String getAppPushId() {
        return this.appPushId;
    }

    public void setAppPushId(String str) {
        this.appPushId = str;
    }

    public LoyaltyDeviceMediumRequestBody appVersion(String str) {
        this.appVersion = str;
        return this;
    }

    @Nonnull
    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public LoyaltyDeviceMediumRequestBody deviceLanguage(String str) {
        this.deviceLanguage = str;
        return this;
    }

    @Nonnull
    public String getDeviceLanguage() {
        return this.deviceLanguage;
    }

    public void setDeviceLanguage(String str) {
        this.deviceLanguage = str;
    }

    public LoyaltyDeviceMediumRequestBody deviceModel(String str) {
        this.deviceModel = str;
        return this;
    }

    @Nullable
    public String getDeviceModel() {
        return this.deviceModel;
    }

    public void setDeviceModel(String str) {
        this.deviceModel = str;
    }

    public LoyaltyDeviceMediumRequestBody deviceName(String str) {
        this.deviceName = str;
        return this;
    }

    @Nullable
    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public LoyaltyDeviceMediumRequestBody mediumName(String str) {
        this.mediumName = str;
        return this;
    }

    @Nonnull
    public String getMediumName() {
        return this.mediumName;
    }

    public void setMediumName(String str) {
        this.mediumName = str;
    }

    public LoyaltyDeviceMediumRequestBody osName(LoyaltyDeviceOsNameEnum loyaltyDeviceOsNameEnum) {
        this.osName = loyaltyDeviceOsNameEnum;
        return this;
    }

    @Nonnull
    public LoyaltyDeviceOsNameEnum getOsName() {
        return this.osName;
    }

    public void setOsName(LoyaltyDeviceOsNameEnum loyaltyDeviceOsNameEnum) {
        this.osName = loyaltyDeviceOsNameEnum;
    }

    public LoyaltyDeviceMediumRequestBody osVersion(String str) {
        this.osVersion = str;
        return this;
    }

    @Nonnull
    public String getOsVersion() {
        return this.osVersion;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public LoyaltyDeviceMediumRequestBody uuid(String str) {
        this.uuid = str;
        return this;
    }

    @Nonnull
    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public LoyaltyDeviceMediumRequestBody putUndeclaredProperty(String str, Object obj) {
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
        LoyaltyDeviceMediumRequestBody loyaltyDeviceMediumRequestBody = (LoyaltyDeviceMediumRequestBody) obj;
        return Objects.equals(this.appId, loyaltyDeviceMediumRequestBody.appId) && Objects.equals(this.appLanguage, loyaltyDeviceMediumRequestBody.appLanguage) && Objects.equals(this.appName, loyaltyDeviceMediumRequestBody.appName) && Objects.equals(this.appPushId, loyaltyDeviceMediumRequestBody.appPushId) && Objects.equals(this.appVersion, loyaltyDeviceMediumRequestBody.appVersion) && Objects.equals(this.deviceLanguage, loyaltyDeviceMediumRequestBody.deviceLanguage) && Objects.equals(this.deviceModel, loyaltyDeviceMediumRequestBody.deviceModel) && Objects.equals(this.deviceName, loyaltyDeviceMediumRequestBody.deviceName) && Objects.equals(this.mediumName, loyaltyDeviceMediumRequestBody.mediumName) && Objects.equals(this.osName, loyaltyDeviceMediumRequestBody.osName) && Objects.equals(this.osVersion, loyaltyDeviceMediumRequestBody.osVersion) && Objects.equals(this.uuid, loyaltyDeviceMediumRequestBody.uuid) && Objects.equals(this.undeclaredProperties, loyaltyDeviceMediumRequestBody.undeclaredProperties);
    }

    public int hashCode() {
        return Objects.hash(this.appId, this.appLanguage, this.appName, this.appPushId, this.appVersion, this.deviceLanguage, this.deviceModel, this.deviceName, this.mediumName, this.osName, this.osVersion, this.uuid, this.undeclaredProperties);
    }

    public String toString() {
        return "class LoyaltyDeviceMediumRequestBody {\n    appId: " + toIndentedString(this.appId) + "\n    appLanguage: " + toIndentedString(this.appLanguage) + "\n    appName: " + toIndentedString(this.appName) + "\n    appPushId: " + toIndentedString(this.appPushId) + "\n    appVersion: " + toIndentedString(this.appVersion) + "\n    deviceLanguage: " + toIndentedString(this.deviceLanguage) + "\n    deviceModel: " + toIndentedString(this.deviceModel) + "\n    deviceName: " + toIndentedString(this.deviceName) + "\n    mediumName: " + toIndentedString(this.mediumName) + "\n    osName: " + toIndentedString(this.osName) + "\n    osVersion: " + toIndentedString(this.osVersion) + "\n    uuid: " + toIndentedString(this.uuid) + "\n    undeclaredProperties: " + toIndentedString(this.undeclaredProperties) + "\n}";
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
        hashSet.add("appId");
        openapiFields.add(SERIALIZED_NAME_APP_LANGUAGE);
        openapiFields.add(SERIALIZED_NAME_APP_NAME);
        openapiFields.add(SERIALIZED_NAME_APP_PUSH_ID);
        openapiFields.add(SERIALIZED_NAME_APP_VERSION);
        openapiFields.add(SERIALIZED_NAME_DEVICE_LANGUAGE);
        openapiFields.add(SERIALIZED_NAME_DEVICE_MODEL);
        openapiFields.add(SERIALIZED_NAME_DEVICE_NAME);
        openapiFields.add(SERIALIZED_NAME_MEDIUM_NAME);
        openapiFields.add(SERIALIZED_NAME_OS_NAME);
        openapiFields.add(SERIALIZED_NAME_OS_VERSION);
        openapiFields.add("uuid");
        HashSet<String> hashSet2 = new HashSet<>();
        openapiRequiredFields = hashSet2;
        hashSet2.add("appId");
        openapiRequiredFields.add(SERIALIZED_NAME_APP_LANGUAGE);
        openapiRequiredFields.add(SERIALIZED_NAME_APP_NAME);
        openapiRequiredFields.add(SERIALIZED_NAME_APP_VERSION);
        openapiRequiredFields.add(SERIALIZED_NAME_DEVICE_LANGUAGE);
        openapiRequiredFields.add(SERIALIZED_NAME_MEDIUM_NAME);
        openapiRequiredFields.add(SERIALIZED_NAME_OS_NAME);
        openapiRequiredFields.add(SERIALIZED_NAME_OS_VERSION);
        openapiRequiredFields.add("uuid");
    }

    public static void validateJsonObject(JsonObject jsonObject) throws IOException {
        if (jsonObject == null && !openapiRequiredFields.isEmpty()) {
            throw new IllegalArgumentException(String.format("The required field(s) %s in LoyaltyDeviceMediumRequestBody is not found in the empty JSON string", openapiRequiredFields.toString()));
        }
        Iterator<String> it = openapiRequiredFields.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (jsonObject.get(next) == null) {
                throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", next, jsonObject.toString()));
            }
        }
        if (!jsonObject.get("appId").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `appId` to be a primitive type in the JSON string but got `%s`", jsonObject.get("appId").toString()));
        }
        if (!jsonObject.get(SERIALIZED_NAME_APP_LANGUAGE).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `appLanguage` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_APP_LANGUAGE).toString()));
        }
        if (!jsonObject.get(SERIALIZED_NAME_APP_NAME).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `appName` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_APP_NAME).toString()));
        }
        if (jsonObject.get(SERIALIZED_NAME_APP_PUSH_ID) != null && !jsonObject.get(SERIALIZED_NAME_APP_PUSH_ID).isJsonNull() && !jsonObject.get(SERIALIZED_NAME_APP_PUSH_ID).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `appPushId` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_APP_PUSH_ID).toString()));
        }
        if (!jsonObject.get(SERIALIZED_NAME_APP_VERSION).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `appVersion` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_APP_VERSION).toString()));
        }
        if (!jsonObject.get(SERIALIZED_NAME_DEVICE_LANGUAGE).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `deviceLanguage` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_DEVICE_LANGUAGE).toString()));
        }
        if (jsonObject.get(SERIALIZED_NAME_DEVICE_MODEL) != null && !jsonObject.get(SERIALIZED_NAME_DEVICE_MODEL).isJsonNull() && !jsonObject.get(SERIALIZED_NAME_DEVICE_MODEL).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `deviceModel` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_DEVICE_MODEL).toString()));
        }
        if (jsonObject.get(SERIALIZED_NAME_DEVICE_NAME) != null && !jsonObject.get(SERIALIZED_NAME_DEVICE_NAME).isJsonNull() && !jsonObject.get(SERIALIZED_NAME_DEVICE_NAME).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `deviceName` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_DEVICE_NAME).toString()));
        }
        if (!jsonObject.get(SERIALIZED_NAME_MEDIUM_NAME).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `mediumName` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_MEDIUM_NAME).toString()));
        }
        if (!jsonObject.get(SERIALIZED_NAME_OS_VERSION).isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `osVersion` to be a primitive type in the JSON string but got `%s`", jsonObject.get(SERIALIZED_NAME_OS_VERSION).toString()));
        }
        if (!jsonObject.get("uuid").isJsonPrimitive()) {
            throw new IllegalArgumentException(String.format("Expected the field `uuid` to be a primitive type in the JSON string but got `%s`", jsonObject.get("uuid").toString()));
        }
    }

    public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(final Gson gson, TypeToken<T> typeToken) {
            if (!LoyaltyDeviceMediumRequestBody.class.isAssignableFrom(typeToken.getRawType())) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(JsonElement.class);
            final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, TypeToken.get(LoyaltyDeviceMediumRequestBody.class));
            return (TypeAdapter<T>) new TypeAdapter<LoyaltyDeviceMediumRequestBody>() { // from class: ch.coop.apidia.appGateway.loyalty.model.LoyaltyDeviceMediumRequestBody.CustomTypeAdapterFactory.1
                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter jsonWriter, LoyaltyDeviceMediumRequestBody loyaltyDeviceMediumRequestBody) throws IOException {
                    JsonObject asJsonObject = delegateAdapter.toJsonTree(loyaltyDeviceMediumRequestBody).getAsJsonObject();
                    asJsonObject.remove("undeclaredProperties");
                    if (loyaltyDeviceMediumRequestBody.getUndeclaredProperties() != null) {
                        for (Map.Entry<String, Object> entry : loyaltyDeviceMediumRequestBody.getUndeclaredProperties().entrySet()) {
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
                public LoyaltyDeviceMediumRequestBody read(JsonReader jsonReader) throws IOException {
                    JsonObject asJsonObject = ((JsonElement) adapter.read(jsonReader)).getAsJsonObject();
                    LoyaltyDeviceMediumRequestBody.validateJsonObject(asJsonObject);
                    LoyaltyDeviceMediumRequestBody loyaltyDeviceMediumRequestBody = (LoyaltyDeviceMediumRequestBody) delegateAdapter.fromJsonTree(asJsonObject);
                    for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
                        if (!LoyaltyDeviceMediumRequestBody.openapiFields.contains(entry.getKey())) {
                            if (entry.getValue().isJsonPrimitive()) {
                                if (entry.getValue().getAsJsonPrimitive().isString()) {
                                    loyaltyDeviceMediumRequestBody.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsString());
                                } else if (entry.getValue().getAsJsonPrimitive().isNumber()) {
                                    loyaltyDeviceMediumRequestBody.putUndeclaredProperty(entry.getKey(), entry.getValue().getAsNumber());
                                } else if (entry.getValue().getAsJsonPrimitive().isBoolean()) {
                                    loyaltyDeviceMediumRequestBody.putUndeclaredProperty(entry.getKey(), Boolean.valueOf(entry.getValue().getAsBoolean()));
                                } else {
                                    throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                                }
                            } else if (entry.getValue().isJsonArray()) {
                                loyaltyDeviceMediumRequestBody.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                            } else {
                                loyaltyDeviceMediumRequestBody.putUndeclaredProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                            }
                        }
                    }
                    return loyaltyDeviceMediumRequestBody;
                }
            }.nullSafe();
        }
    }

    public static LoyaltyDeviceMediumRequestBody fromJson(String str) throws IOException {
        return (LoyaltyDeviceMediumRequestBody) JSON.getGson().fromJson(str, LoyaltyDeviceMediumRequestBody.class);
    }

    public String toJson() {
        return JSON.getGson().toJson(this);
    }
}
