package com.salesforce.marketingcloud.analytics;

import com.salesforce.marketingcloud.messages.push.PushMessageManager;
import com.salesforce.marketingcloud.sfmcsdk.components.identity.Identity;
import com.salesforce.marketingcloud.storage.db.k;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class e {
    private final com.salesforce.marketingcloud.registration.f a;
    private final PushMessageManager b;
    private final boolean c;
    private final Identity d;

    public e(com.salesforce.marketingcloud.registration.f registrationMeta, PushMessageManager pushMessageManager, boolean z, Identity identity) {
        Intrinsics.checkNotNullParameter(registrationMeta, "registrationMeta");
        this.a = registrationMeta;
        this.b = pushMessageManager;
        this.c = z;
        this.d = identity;
    }

    private final com.salesforce.marketingcloud.registration.f a() {
        return this.a;
    }

    private final PushMessageManager b() {
        return this.b;
    }

    private final boolean c() {
        return this.c;
    }

    private final Identity d() {
        return this.d;
    }

    public final JSONObject e() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("deviceID", this.a.f());
        jSONObject.put(b.v, this.a.d());
        jSONObject.put(k.a.m, this.a.g());
        jSONObject.put("platform", this.a.h());
        jSONObject.put("platform_Version", this.a.i());
        jSONObject.put("sdk_Version", this.a.j());
        jSONObject.put("app_Version", this.a.e());
        jSONObject.put("locale", Locale.getDefault().toString());
        jSONObject.put("timeZone", com.salesforce.marketingcloud.util.j.b());
        jSONObject.put("location_Enabled", this.c);
        PushMessageManager pushMessageManager = this.b;
        if (pushMessageManager != null) {
            jSONObject.put("backgroundRefreshEnabled", pushMessageManager.isPushEnabled());
            jSONObject.put("push_Enabled", pushMessageManager.isPushEnabled());
        }
        Identity identity = this.d;
        if (identity != null) {
            jSONObject.put("identity", identity.toJson());
        }
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return Intrinsics.areEqual(this.a, eVar.a) && Intrinsics.areEqual(this.b, eVar.b) && this.c == eVar.c && Intrinsics.areEqual(this.d, eVar.d);
    }

    public int hashCode() {
        int hashCode = this.a.hashCode() * 31;
        PushMessageManager pushMessageManager = this.b;
        int hashCode2 = (((hashCode + (pushMessageManager == null ? 0 : pushMessageManager.hashCode())) * 31) + Boolean.hashCode(this.c)) * 31;
        Identity identity = this.d;
        return hashCode2 + (identity != null ? identity.hashCode() : 0);
    }

    public String toString() {
        return "EventMetaData(registrationMeta=" + this.a + ", pushMessageManager=" + this.b + ", locationEnabled=" + this.c + ", identity=" + this.d + ")";
    }

    public final e a(com.salesforce.marketingcloud.registration.f registrationMeta, PushMessageManager pushMessageManager, boolean z, Identity identity) {
        Intrinsics.checkNotNullParameter(registrationMeta, "registrationMeta");
        return new e(registrationMeta, pushMessageManager, z, identity);
    }

    public static /* synthetic */ e a(e eVar, com.salesforce.marketingcloud.registration.f fVar, PushMessageManager pushMessageManager, boolean z, Identity identity, int i, Object obj) {
        if ((i & 1) != 0) {
            fVar = eVar.a;
        }
        if ((i & 2) != 0) {
            pushMessageManager = eVar.b;
        }
        if ((i & 4) != 0) {
            z = eVar.c;
        }
        if ((i & 8) != 0) {
            identity = eVar.d;
        }
        return eVar.a(fVar, pushMessageManager, z, identity);
    }
}
