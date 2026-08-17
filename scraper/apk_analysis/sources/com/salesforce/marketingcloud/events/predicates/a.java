package com.salesforce.marketingcloud.events.predicates;

/* loaded from: classes2.dex */
public class a extends f {
    private final f[] e;

    public a(f... fVarArr) {
        this.e = fVarArr;
    }

    @Override // com.salesforce.marketingcloud.events.predicates.f
    protected boolean a() {
        for (f fVar : this.e) {
            if (!fVar.b()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.salesforce.marketingcloud.events.predicates.f
    protected String c() {
        return "And";
    }
}
