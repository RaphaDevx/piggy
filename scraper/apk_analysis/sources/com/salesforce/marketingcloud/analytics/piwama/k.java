package com.salesforce.marketingcloud.analytics.piwama;

import android.text.TextUtils;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import io.sentry.TraceContext;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
class k extends j {
    k(MarketingCloudConfig marketingCloudConfig, com.salesforce.marketingcloud.storage.h hVar) {
        super(marketingCloudConfig, hVar);
    }

    @Override // com.salesforce.marketingcloud.analytics.piwama.j
    JSONObject a(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("app_name", this.b.appPackageName());
            jSONObject3.put("app_id", this.b.applicationId());
            String b = this.a.c().b(com.salesforce.marketingcloud.storage.b.g, null);
            if (!TextUtils.isEmpty(b)) {
                jSONObject.put(TraceContext.JsonKeys.USER_ID, b);
            }
            jSONObject3.put("user_info", jSONObject);
            jSONObject2.put("payload", jSONObject3);
            return jSONObject2;
        } catch (JSONException e) {
            com.salesforce.marketingcloud.g.b(i.k, e, "Failed to construct PiWama payload JSON Object.", new Object[0]);
            return new JSONObject();
        }
    }

    @Override // com.salesforce.marketingcloud.analytics.piwama.j
    Object[] b() {
        return new Object[]{"?session_id=" + this.a.c().b(com.salesforce.marketingcloud.storage.b.f, "")};
    }
}
