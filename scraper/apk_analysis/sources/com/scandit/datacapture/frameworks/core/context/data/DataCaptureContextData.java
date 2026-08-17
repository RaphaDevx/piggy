package com.scandit.datacapture.frameworks.core.context.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.coop.apidia.appGateway.loyalty.model.LoyaltyDeviceMediumRequestBody;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import io.sentry.protocol.Request;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: DataCaptureContextData.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\b\u0018\u0000 #2\u00020\u0001:\u0001#BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\nHÆ\u0003Jg\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\nHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u001f\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006$"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/context/data/DataCaptureContextData;", "", "licenseKey", "", LoyaltyDeviceMediumRequestBody.SERIALIZED_NAME_DEVICE_NAME, "framework", "frameworkVersion", "externalId", "frameSource", "settings", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getDeviceName", "()Ljava/lang/String;", "getExternalId", "getFrameSource", "getFramework", "getFrameworkVersion", "getLicenseKey", "getSettings", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", Request.JsonKeys.OTHER, "hashCode", "", "toString", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class DataCaptureContextData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String deviceName;
    private final String externalId;
    private final String frameSource;
    private final String framework;
    private final String frameworkVersion;
    private final String licenseKey;
    private final Map<String, Object> settings;

    public static /* synthetic */ DataCaptureContextData copy$default(DataCaptureContextData dataCaptureContextData, String str, String str2, String str3, String str4, String str5, String str6, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = dataCaptureContextData.licenseKey;
        }
        if ((i & 2) != 0) {
            str2 = dataCaptureContextData.deviceName;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = dataCaptureContextData.framework;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = dataCaptureContextData.frameworkVersion;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = dataCaptureContextData.externalId;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = dataCaptureContextData.frameSource;
        }
        String str11 = str6;
        if ((i & 64) != 0) {
            map = dataCaptureContextData.settings;
        }
        return dataCaptureContextData.copy(str, str7, str8, str9, str10, str11, map);
    }

    /* renamed from: component1, reason: from getter */
    public final String getLicenseKey() {
        return this.licenseKey;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDeviceName() {
        return this.deviceName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getFramework() {
        return this.framework;
    }

    /* renamed from: component4, reason: from getter */
    public final String getFrameworkVersion() {
        return this.frameworkVersion;
    }

    /* renamed from: component5, reason: from getter */
    public final String getExternalId() {
        return this.externalId;
    }

    /* renamed from: component6, reason: from getter */
    public final String getFrameSource() {
        return this.frameSource;
    }

    public final Map<String, Object> component7() {
        return this.settings;
    }

    public final DataCaptureContextData copy(String licenseKey, String deviceName, String framework, String frameworkVersion, String externalId, String frameSource, Map<String, ? extends Object> settings) {
        Intrinsics.checkNotNullParameter(licenseKey, "licenseKey");
        return new DataCaptureContextData(licenseKey, deviceName, framework, frameworkVersion, externalId, frameSource, settings);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DataCaptureContextData)) {
            return false;
        }
        DataCaptureContextData dataCaptureContextData = (DataCaptureContextData) other;
        return Intrinsics.areEqual(this.licenseKey, dataCaptureContextData.licenseKey) && Intrinsics.areEqual(this.deviceName, dataCaptureContextData.deviceName) && Intrinsics.areEqual(this.framework, dataCaptureContextData.framework) && Intrinsics.areEqual(this.frameworkVersion, dataCaptureContextData.frameworkVersion) && Intrinsics.areEqual(this.externalId, dataCaptureContextData.externalId) && Intrinsics.areEqual(this.frameSource, dataCaptureContextData.frameSource) && Intrinsics.areEqual(this.settings, dataCaptureContextData.settings);
    }

    public int hashCode() {
        int hashCode = this.licenseKey.hashCode() * 31;
        String str = this.deviceName;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.framework;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.frameworkVersion;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.externalId;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.frameSource;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        Map<String, Object> map = this.settings;
        return hashCode6 + (map != null ? map.hashCode() : 0);
    }

    public String toString() {
        return "DataCaptureContextData(licenseKey=" + this.licenseKey + ", deviceName=" + this.deviceName + ", framework=" + this.framework + ", frameworkVersion=" + this.frameworkVersion + ", externalId=" + this.externalId + ", frameSource=" + this.frameSource + ", settings=" + this.settings + ')';
    }

    public DataCaptureContextData(String licenseKey, String str, String str2, String str3, String str4, String str5, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(licenseKey, "licenseKey");
        this.licenseKey = licenseKey;
        this.deviceName = str;
        this.framework = str2;
        this.frameworkVersion = str3;
        this.externalId = str4;
        this.frameSource = str5;
        this.settings = map;
    }

    public final String getLicenseKey() {
        return this.licenseKey;
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    public final String getFramework() {
        return this.framework;
    }

    public final String getFrameworkVersion() {
        return this.frameworkVersion;
    }

    public final String getExternalId() {
        return this.externalId;
    }

    public final String getFrameSource() {
        return this.frameSource;
    }

    public final Map<String, Object> getSettings() {
        return this.settings;
    }

    /* compiled from: DataCaptureContextData.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/context/data/DataCaptureContextData$Companion;", "", "()V", "from", "Lcom/scandit/datacapture/frameworks/core/context/data/DataCaptureContextData;", "json", "", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DataCaptureContextData from(String json) {
            Map<String, Object> map;
            Intrinsics.checkNotNullParameter(json, "json");
            JSONObject jSONObject = new JSONObject(json);
            String string = jSONObject.getString("licenseKey");
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String orNull = ExtentionsKt.getOrNull(jSONObject, LoyaltyDeviceMediumRequestBody.SERIALIZED_NAME_DEVICE_NAME);
            String orNull2 = ExtentionsKt.getOrNull(jSONObject, "framework");
            String orNull3 = ExtentionsKt.getOrNull(jSONObject, "frameworkVersion");
            String orNull4 = ExtentionsKt.getOrNull(jSONObject, "externalId");
            JSONObject optJSONObject = jSONObject.optJSONObject("frameSource");
            String jSONObject2 = optJSONObject != null ? optJSONObject.toString() : null;
            JSONObject optJSONObject2 = jSONObject.optJSONObject("settings");
            if (optJSONObject2 != null) {
                Intrinsics.checkNotNull(optJSONObject2);
                map = DataCaptureContextDataKt.toMap(optJSONObject2);
            } else {
                map = null;
            }
            return new DataCaptureContextData(string, orNull, orNull2, orNull3, orNull4, jSONObject2, map);
        }
    }
}
