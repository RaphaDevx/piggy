package com.salesforce.marketingcloud.analytics;

import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public interface f {
    void a(InAppMessage inAppMessage);

    void a(InAppMessage inAppMessage, com.salesforce.marketingcloud.messages.iam.j jVar);

    void a(InAppMessage inAppMessage, JSONObject jSONObject);

    void a(String str, String str2, List<String> list);

    void b(InAppMessage inAppMessage);
}
