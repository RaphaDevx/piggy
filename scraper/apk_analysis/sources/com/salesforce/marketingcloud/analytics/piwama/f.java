package com.salesforce.marketingcloud.analytics.piwama;

import com.salesforce.marketingcloud.analytics.PiCart;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class f implements c {
    private final PiCart a;
    private final Date b;

    public f(PiCart piCart, Date timestamp) {
        Intrinsics.checkNotNullParameter(piCart, "piCart");
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        this.a = piCart;
        this.b = timestamp;
    }

    @Override // com.salesforce.marketingcloud.analytics.piwama.c
    public String a() {
        return "";
    }

    @Override // com.salesforce.marketingcloud.analytics.piwama.c
    public int b() {
        return com.salesforce.marketingcloud.analytics.b.q;
    }

    @Override // com.salesforce.marketingcloud.analytics.piwama.c
    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        a(jSONObject);
        if (this.a.cartItems.isEmpty()) {
            jSONObject.put("clear_cart", true);
        } else {
            jSONObject.put("cart", this.a.toJson$sdk_release());
        }
        return jSONObject;
    }

    @Override // com.salesforce.marketingcloud.analytics.piwama.c
    public String d() {
        return "track_cart";
    }

    @Override // com.salesforce.marketingcloud.analytics.piwama.c
    public Date e() {
        return this.b;
    }
}
