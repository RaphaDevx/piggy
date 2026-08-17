package com.salesforce.marketingcloud.analytics.piwama;

import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class b implements c {
    private final Date a;

    public b(Date timestamp) {
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        this.a = timestamp;
    }

    @Override // com.salesforce.marketingcloud.analytics.piwama.c
    public String a() {
        return "app_close";
    }

    @Override // com.salesforce.marketingcloud.analytics.piwama.c
    public int b() {
        return 0;
    }

    @Override // com.salesforce.marketingcloud.analytics.piwama.c
    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        a(jSONObject);
        return jSONObject;
    }

    @Override // com.salesforce.marketingcloud.analytics.piwama.c
    public String d() {
        return "track_event";
    }

    @Override // com.salesforce.marketingcloud.analytics.piwama.c
    public Date e() {
        return this.a;
    }
}
