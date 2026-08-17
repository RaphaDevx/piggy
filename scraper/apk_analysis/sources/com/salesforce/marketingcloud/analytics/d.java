package com.salesforce.marketingcloud.analytics;

/* loaded from: classes2.dex */
public class d extends com.salesforce.marketingcloud.internal.i {
    private final com.salesforce.marketingcloud.storage.a c;
    private final String[] d;

    public d(com.salesforce.marketingcloud.storage.a aVar, String[] strArr) {
        super("delete_analytics", new Object[0]);
        this.c = aVar;
        this.d = strArr;
    }

    @Override // com.salesforce.marketingcloud.internal.i
    protected void a() {
        this.c.a(this.d);
    }
}
