package com.salesforce.marketingcloud.registration;

import ch.coop.apidia.appGateway.loyalty.model.LoyaltyDeviceMediumRequestBody;
import com.salesforce.marketingcloud.internal.o;
import com.salesforce.marketingcloud.storage.db.k;
import com.samsung.android.sdk.samsungpay.v2.InternalConst;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class Registration {
    public final String appId;
    public final String appVersion;
    public final Map<String, String> attributes;
    public final String contactKey;
    public final String deviceId;
    public final boolean dst;
    public final String hwid;
    private int id;
    public final String locale;
    public final boolean locationEnabled;
    public final String platform;
    public final String platformVersion;
    public final boolean proximityEnabled;
    public final boolean pushEnabled;
    public final String sdkVersion;
    public final String signedString;
    public final String systemToken;
    public final Set<String> tags;
    public final int timeZone;
    private final String uuid;

    public Registration(int i, String uuid, String str, String deviceId, String str2, String sdkVersion, String appVersion, boolean z, boolean z2, boolean z3, String platformVersion, boolean z4, int i2, String str3, String platform, String hwid, String appId, String locale, Set<String> tags, Map<String, String> attributes) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(sdkVersion, "sdkVersion");
        Intrinsics.checkNotNullParameter(appVersion, "appVersion");
        Intrinsics.checkNotNullParameter(platformVersion, "platformVersion");
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(hwid, "hwid");
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(locale, "locale");
        Intrinsics.checkNotNullParameter(tags, "tags");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        this.id = i;
        this.uuid = uuid;
        this.signedString = str;
        this.deviceId = deviceId;
        this.systemToken = str2;
        this.sdkVersion = sdkVersion;
        this.appVersion = appVersion;
        this.dst = z;
        this.locationEnabled = z2;
        this.proximityEnabled = z3;
        this.platformVersion = platformVersion;
        this.pushEnabled = z4;
        this.timeZone = i2;
        this.contactKey = str3;
        this.platform = platform;
        this.hwid = hwid;
        this.appId = appId;
        this.locale = locale;
        this.tags = tags;
        this.attributes = attributes;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "appId", imports = {}))
    public final String appId() {
        return this.appId;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = LoyaltyDeviceMediumRequestBody.SERIALIZED_NAME_APP_VERSION, imports = {}))
    public final String appVersion() {
        return this.appVersion;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "attributes", imports = {}))
    public final Map<String, String> attributes() {
        return this.attributes;
    }

    public final int component1$sdk_release() {
        return this.id;
    }

    public final boolean component10() {
        return this.proximityEnabled;
    }

    public final String component11() {
        return this.platformVersion;
    }

    public final boolean component12() {
        return this.pushEnabled;
    }

    public final int component13() {
        return this.timeZone;
    }

    public final String component14() {
        return this.contactKey;
    }

    public final String component15() {
        return this.platform;
    }

    public final String component16() {
        return this.hwid;
    }

    public final String component17() {
        return this.appId;
    }

    public final String component18() {
        return this.locale;
    }

    public final Set<String> component19() {
        return this.tags;
    }

    public final String component2$sdk_release() {
        return this.uuid;
    }

    public final Map<String, String> component20() {
        return this.attributes;
    }

    public final String component3() {
        return this.signedString;
    }

    public final String component4() {
        return this.deviceId;
    }

    public final String component5() {
        return this.systemToken;
    }

    public final String component6() {
        return this.sdkVersion;
    }

    public final String component7() {
        return this.appVersion;
    }

    public final boolean component8() {
        return this.dst;
    }

    public final boolean component9() {
        return this.locationEnabled;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "contactKey", imports = {}))
    public final String contactKey() {
        return this.contactKey;
    }

    public final Registration copy(int i, String uuid, String str, String deviceId, String str2, String sdkVersion, String appVersion, boolean z, boolean z2, boolean z3, String platformVersion, boolean z4, int i2, String str3, String platform, String hwid, String appId, String locale, Set<String> tags, Map<String, String> attributes) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(sdkVersion, "sdkVersion");
        Intrinsics.checkNotNullParameter(appVersion, "appVersion");
        Intrinsics.checkNotNullParameter(platformVersion, "platformVersion");
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(hwid, "hwid");
        Intrinsics.checkNotNullParameter(appId, "appId");
        Intrinsics.checkNotNullParameter(locale, "locale");
        Intrinsics.checkNotNullParameter(tags, "tags");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        return new Registration(i, uuid, str, deviceId, str2, sdkVersion, appVersion, z, z2, z3, platformVersion, z4, i2, str3, platform, hwid, appId, locale, tags, attributes);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "deviceId", imports = {}))
    public final String deviceId() {
        return this.deviceId;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = k.a.f, imports = {}))
    public final boolean dst() {
        return this.dst;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Registration)) {
            return false;
        }
        Registration registration = (Registration) obj;
        return this.id == registration.id && Intrinsics.areEqual(this.uuid, registration.uuid) && Intrinsics.areEqual(this.signedString, registration.signedString) && Intrinsics.areEqual(this.deviceId, registration.deviceId) && Intrinsics.areEqual(this.systemToken, registration.systemToken) && Intrinsics.areEqual(this.sdkVersion, registration.sdkVersion) && Intrinsics.areEqual(this.appVersion, registration.appVersion) && this.dst == registration.dst && this.locationEnabled == registration.locationEnabled && this.proximityEnabled == registration.proximityEnabled && Intrinsics.areEqual(this.platformVersion, registration.platformVersion) && this.pushEnabled == registration.pushEnabled && this.timeZone == registration.timeZone && Intrinsics.areEqual(this.contactKey, registration.contactKey) && Intrinsics.areEqual(this.platform, registration.platform) && Intrinsics.areEqual(this.hwid, registration.hwid) && Intrinsics.areEqual(this.appId, registration.appId) && Intrinsics.areEqual(this.locale, registration.locale) && Intrinsics.areEqual(this.tags, registration.tags) && Intrinsics.areEqual(this.attributes, registration.attributes);
    }

    public final int getId$sdk_release() {
        return this.id;
    }

    public final String getUuid$sdk_release() {
        return this.uuid;
    }

    public int hashCode() {
        int hashCode = ((Integer.hashCode(this.id) * 31) + this.uuid.hashCode()) * 31;
        String str = this.signedString;
        int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.deviceId.hashCode()) * 31;
        String str2 = this.systemToken;
        int hashCode3 = (((((((((((((((((hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.sdkVersion.hashCode()) * 31) + this.appVersion.hashCode()) * 31) + Boolean.hashCode(this.dst)) * 31) + Boolean.hashCode(this.locationEnabled)) * 31) + Boolean.hashCode(this.proximityEnabled)) * 31) + this.platformVersion.hashCode()) * 31) + Boolean.hashCode(this.pushEnabled)) * 31) + Integer.hashCode(this.timeZone)) * 31;
        String str3 = this.contactKey;
        return ((((((((((((hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.platform.hashCode()) * 31) + this.hwid.hashCode()) * 31) + this.appId.hashCode()) * 31) + this.locale.hashCode()) * 31) + this.tags.hashCode()) * 31) + this.attributes.hashCode();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = k.a.m, imports = {}))
    public final String hwid() {
        return this.hwid;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "locale", imports = {}))
    public final String locale() {
        return this.locale;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "locationEnabled", imports = {}))
    public final boolean locationEnabled() {
        return this.locationEnabled;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "platform", imports = {}))
    public final String platform() {
        return this.platform;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "platformVersion", imports = {}))
    public final String platformVersion() {
        return this.platformVersion;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "proximityEnabled", imports = {}))
    public final boolean proximityEnabled() {
        return this.proximityEnabled;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "pushEnabled", imports = {}))
    public final boolean pushEnabled() {
        return this.pushEnabled;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = InternalConst.EXTRA_SDK_VERSION, imports = {}))
    public final String sdkVersion() {
        return this.sdkVersion;
    }

    public final void setId$sdk_release(int i) {
        this.id = i;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "signedString", imports = {}))
    public final String signedString() {
        return this.signedString;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "systemToken", imports = {}))
    public final String systemToken() {
        return this.systemToken;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "tags", imports = {}))
    public final Set<String> tags() {
        return this.tags;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "timeZone", imports = {}))
    public final int timeZone() {
        return this.timeZone;
    }

    public final JSONObject toJson$sdk_release() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("uuid", this.uuid);
        jSONObject.put("signedString", this.signedString);
        jSONObject.put("deviceID", this.deviceId);
        String str = this.systemToken;
        if (str != null) {
            jSONObject.put("device_Token", str);
        }
        jSONObject.put("sdk_Version", this.sdkVersion);
        jSONObject.put("app_Version", this.appVersion);
        jSONObject.put("dST", this.dst);
        jSONObject.put("location_Enabled", this.locationEnabled);
        jSONObject.put("proximity_Enabled", this.proximityEnabled);
        jSONObject.put("platform_Version", this.platformVersion);
        jSONObject.put("push_Enabled", this.pushEnabled);
        jSONObject.put("timeZone", String.valueOf(this.timeZone));
        String str2 = this.contactKey;
        if (str2 != null) {
            jSONObject.put("subscriberKey", str2);
        }
        jSONObject.put("platform", this.platform);
        jSONObject.put(k.a.m, this.hwid);
        jSONObject.put(com.salesforce.marketingcloud.analytics.b.v, this.appId);
        jSONObject.put("locale", this.locale);
        jSONObject.put("tags", new JSONArray((Collection) new TreeSet(this.tags)));
        jSONObject.put("attributes", o.a(MapsKt.toSortedMap(this.attributes)));
        return jSONObject;
    }

    public String toString() {
        return "Registration(id=" + this.id + ", uuid=" + this.uuid + ", signedString=" + this.signedString + ", deviceId=" + this.deviceId + ", systemToken=" + this.systemToken + ", sdkVersion=" + this.sdkVersion + ", appVersion=" + this.appVersion + ", dst=" + this.dst + ", locationEnabled=" + this.locationEnabled + ", proximityEnabled=" + this.proximityEnabled + ", platformVersion=" + this.platformVersion + ", pushEnabled=" + this.pushEnabled + ", timeZone=" + this.timeZone + ", contactKey=" + this.contactKey + ", platform=" + this.platform + ", hwid=" + this.hwid + ", appId=" + this.appId + ", locale=" + this.locale + ", tags=" + this.tags + ", attributes=" + this.attributes + ")";
    }

    public /* synthetic */ Registration(int i, String str, String str2, String str3, String str4, String str5, String str6, boolean z, boolean z2, boolean z3, String str7, boolean z4, int i2, String str8, String str9, String str10, String str11, String str12, Set set, Map map, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, str, str2, str3, (i3 & 16) != 0 ? null : str4, str5, str6, z, z2, z3, str7, z4, i2, (i3 & 8192) != 0 ? null : str8, str9, str10, str11, str12, set, map);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Registration(org.json.JSONObject r28) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 490
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.registration.Registration.<init>(org.json.JSONObject):void");
    }
}
