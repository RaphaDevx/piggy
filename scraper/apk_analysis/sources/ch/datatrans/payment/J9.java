package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class J9 {
    public final String a;
    public final String b;

    public J9(String token, String categories) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(categories, "categories");
        this.a = token;
        this.b = categories;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof J9)) {
            return false;
        }
        J9 j9 = (J9) obj;
        return Intrinsics.areEqual(this.a, j9.a) && Intrinsics.areEqual(this.b, j9.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PaymentParameters(token=");
        sb.append(this.a);
        sb.append(", categories=");
        return N7.a(sb, this.b, ')');
    }
}
