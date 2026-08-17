package com.salesforce.marketingcloud.events.predicates;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class e extends f {
    private f[] e;

    public e(f... predicates) {
        Intrinsics.checkNotNullParameter(predicates, "predicates");
        this.e = predicates;
    }

    @Override // com.salesforce.marketingcloud.events.predicates.f
    protected boolean a() {
        for (f fVar : this.e) {
            if (fVar.b()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.salesforce.marketingcloud.events.predicates.f
    protected String c() {
        return "Or";
    }
}
