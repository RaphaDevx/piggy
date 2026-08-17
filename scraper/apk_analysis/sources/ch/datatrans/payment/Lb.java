package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Lb {
    public final String a;
    public final String b;
    public final String c;

    public Lb(String transactionId, String token, String str) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(token, "token");
        this.a = transactionId;
        this.b = token;
        this.c = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Lb)) {
            return false;
        }
        Lb lb = (Lb) obj;
        return Intrinsics.areEqual(this.a, lb.a) && Intrinsics.areEqual(this.b, lb.b) && Intrinsics.areEqual(this.c, lb.c);
    }

    public final int hashCode() {
        int hashCode = (this.b.hashCode() + (this.a.hashCode() * 31)) * 31;
        String str = this.c;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TokenResult(transactionId=");
        sb.append(this.a);
        sb.append(", token=");
        sb.append(this.b);
        sb.append(", paymentMethodCategories=");
        return N7.a(sb, this.c, ')');
    }
}
