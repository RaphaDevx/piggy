package com.salesforce.marketingcloud;

import com.salesforce.marketingcloud.registration.RegistrationManager;
import com.salesforce.marketingcloud.sfmcsdk.components.identity.ModuleIdentity;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleIdentifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class i extends ModuleIdentity {
    public static final a b = new a(null);
    private static ModuleIdentity c;
    private final RegistrationManager a;

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final ModuleIdentity a(String applicationId, RegistrationManager registrationManager) {
            Intrinsics.checkNotNullParameter(applicationId, "applicationId");
            Intrinsics.checkNotNullParameter(registrationManager, "registrationManager");
            if (i.c == null) {
                i.c = new i(applicationId, registrationManager, null);
            }
            return i.c;
        }

        private a() {
        }
    }

    public /* synthetic */ i(String str, RegistrationManager registrationManager, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, registrationManager);
    }

    @JvmStatic
    public static final ModuleIdentity a(String str, RegistrationManager registrationManager) {
        return b.a(str, registrationManager);
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.components.identity.ModuleIdentity
    public JSONObject customPropertiesToJson(Map<String, ? extends Object> customProperties) {
        Intrinsics.checkNotNullParameter(customProperties, "customProperties");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("deviceId", customProperties.get("deviceId"));
            Object obj = customProperties.get("attributes");
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.Any?, kotlin.Any?>");
            jSONObject.put("attributes", new JSONObject(TypeIntrinsics.asMutableMap(obj)));
            jSONObject.put("tags", new JSONArray((Collection) this.a.getTags()));
            return jSONObject;
        } catch (Exception e) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("error", e.getMessage());
            return jSONObject2;
        }
    }

    private i(String str, RegistrationManager registrationManager) {
        super(ModuleIdentifier.PUSH, str);
        this.a = registrationManager;
        if (registrationManager != null) {
            setProfileId(registrationManager.getContactKey());
            setInstallationId(registrationManager.getDeviceId());
            HashMap hashMap = new HashMap();
            hashMap.put("deviceId", registrationManager.getDeviceId());
            hashMap.put("attributes", registrationManager.getAttributes());
            hashMap.put("tags", registrationManager.getTags());
            setCustomProperties(hashMap);
        }
    }
}
