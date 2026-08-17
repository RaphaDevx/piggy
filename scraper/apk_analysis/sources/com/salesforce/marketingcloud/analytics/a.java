package com.salesforce.marketingcloud.analytics;

import com.salesforce.marketingcloud.util.Crypto;

/* loaded from: classes2.dex */
public class a extends com.salesforce.marketingcloud.internal.i {
    private final com.salesforce.marketingcloud.storage.a c;
    private final Crypto d;
    private final b e;

    public a(com.salesforce.marketingcloud.storage.a aVar, Crypto crypto, b bVar) {
        super("add_analytic", new Object[0]);
        this.c = aVar;
        this.d = crypto;
        this.e = bVar;
    }

    @Override // com.salesforce.marketingcloud.internal.i
    protected void a() {
        try {
            this.c.a(this.e, this.d);
        } catch (Exception e) {
            com.salesforce.marketingcloud.g.b(AnalyticsManager.TAG, e, "Unable to record analytic [%d].", Integer.valueOf(this.e.a()));
        }
    }
}
