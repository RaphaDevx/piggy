package com.salesforce.marketingcloud.sfmcsdk.components.identity;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.analytics.stats.d;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleIdentifier;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: ModuleIdentity.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u0019\u001a\u00020\u001a2\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001bH&J\u0006\u0010\u001c\u001a\u00020\u001aJ\b\u0010\u001d\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR@\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\n2\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR(\u0010\u0010\u001a\u0004\u0018\u00010\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\b\"\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R(\u0010\u0016\u001a\u0004\u0018\u00010\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\b\"\u0004\b\u0018\u0010\u0013¨\u0006\u001e"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/identity/ModuleIdentity;", "", "moduleName", "Lcom/salesforce/marketingcloud/sfmcsdk/modules/ModuleIdentifier;", d.b, "", "(Lcom/salesforce/marketingcloud/sfmcsdk/modules/ModuleIdentifier;Ljava/lang/String;)V", "getApplicationId", "()Ljava/lang/String;", "value", "", "customProperties", "getCustomProperties", "()Ljava/util/Map;", "setCustomProperties", "(Ljava/util/Map;)V", "installationId", "getInstallationId", "setInstallationId", "(Ljava/lang/String;)V", "getModuleName", "()Lcom/salesforce/marketingcloud/sfmcsdk/modules/ModuleIdentifier;", "profileId", "getProfileId", "setProfileId", "customPropertiesToJson", "Lorg/json/JSONObject;", "", "toJson", "toString", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public abstract class ModuleIdentity {
    private final String applicationId;
    private Map<String, Object> customProperties;
    private String installationId;
    private final ModuleIdentifier moduleName;
    private String profileId;

    public abstract JSONObject customPropertiesToJson(Map<String, ? extends Object> customProperties);

    public ModuleIdentity(ModuleIdentifier moduleName, String applicationId) {
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        Intrinsics.checkNotNullParameter(applicationId, "applicationId");
        this.moduleName = moduleName;
        this.applicationId = applicationId;
        this.customProperties = new LinkedHashMap();
    }

    public final ModuleIdentifier getModuleName() {
        return this.moduleName;
    }

    public final String getApplicationId() {
        return this.applicationId;
    }

    public final String getProfileId() {
        return this.profileId;
    }

    public final void setProfileId(String str) {
        if (Intrinsics.areEqual(str, this.profileId)) {
            return;
        }
        this.profileId = str;
    }

    public final Map<String, Object> getCustomProperties() {
        return this.customProperties;
    }

    public final void setCustomProperties(Map<String, Object> value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (Intrinsics.areEqual(value, this.customProperties)) {
            return;
        }
        this.customProperties = value;
    }

    public final String getInstallationId() {
        return this.installationId;
    }

    public final void setInstallationId(String str) {
        if (Intrinsics.areEqual(str, this.installationId)) {
            return;
        }
        this.installationId = str;
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("profileId", this.profileId);
        jSONObject.put(d.b, this.applicationId);
        jSONObject.put("installationId", this.installationId);
        jSONObject.put("customProperties", customPropertiesToJson(this.customProperties));
        return jSONObject;
    }

    public String toString() {
        String jSONObject = toJson().toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
        return jSONObject;
    }
}
