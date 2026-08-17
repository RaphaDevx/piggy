package com.salesforce.marketingcloud.events.predicates;

import com.salesforce.marketingcloud.events.g;

/* loaded from: classes2.dex */
public abstract class h<T> extends f {
    private static final String i = com.salesforce.marketingcloud.g.a("ValuePredicate");
    private final Object e;
    private final g.a f;
    private final Object g;
    private final String h;

    h(Object obj, g.a aVar, Object obj2) {
        this.e = obj;
        this.f = aVar;
        this.g = obj2;
        this.h = String.format("%s %s %s", obj, aVar, obj2);
    }

    protected abstract T a(Object obj);

    @Override // com.salesforce.marketingcloud.events.predicates.f
    protected final boolean a() {
        try {
            return a(a(this.e), this.f, a(this.g));
        } catch (Exception e) {
            com.salesforce.marketingcloud.g.b(i, e, "Unable to evaluate predicate.  Returning default value of 'false'", new Object[0]);
            return false;
        }
    }

    protected abstract boolean a(T t, g.a aVar, T t2) throws UnsupportedOperationException;

    @Override // com.salesforce.marketingcloud.events.predicates.f
    protected String c() {
        return this.h;
    }
}
