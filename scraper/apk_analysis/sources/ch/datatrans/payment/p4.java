package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class p4 {
    public final String a;
    public final String b;

    public p4(String json, String etag) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(etag, "etag");
        this.a = json;
        this.b = etag;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p4)) {
            return false;
        }
        p4 p4Var = (p4) obj;
        return Intrinsics.areEqual(this.a, p4Var.a) && Intrinsics.areEqual(this.b, p4Var.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ETagJsonResult(json=");
        sb.append(this.a);
        sb.append(", etag=");
        return N7.a(sb, this.b, ')');
    }
}
