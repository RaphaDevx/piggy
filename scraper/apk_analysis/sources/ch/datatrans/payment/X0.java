package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class X0 {
    public final String a;
    public final String b;
    public final Long c;
    public final String d;
    public final String e;

    public X0(String dccId, String str, Long l, String str2, String str3) {
        Intrinsics.checkNotNullParameter(dccId, "dccId");
        this.a = dccId;
        this.b = str;
        this.c = l;
        this.d = str2;
        this.e = str3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof X0)) {
            return false;
        }
        X0 x0 = (X0) obj;
        return Intrinsics.areEqual(this.a, x0.a) && Intrinsics.areEqual(this.b, x0.b) && Intrinsics.areEqual(this.c, x0.c) && Intrinsics.areEqual(this.d, x0.d) && Intrinsics.areEqual(this.e, x0.e);
    }

    public final int hashCode() {
        int hashCode = this.a.hashCode() * 31;
        String str = this.b;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Long l = this.c;
        int hashCode3 = (hashCode2 + (l == null ? 0 : l.hashCode())) * 31;
        String str2 = this.d;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.e;
        return hashCode4 + (str3 != null ? str3.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CreditCardDCCResult(dccId=");
        sb.append(this.a);
        sb.append(", dccCurrency=");
        sb.append(this.b);
        sb.append(", dccAmount=");
        sb.append(this.c);
        sb.append(", dccRate=");
        sb.append(this.d);
        sb.append(", dccMarkupPercentage=");
        return N7.a(sb, this.e, ')');
    }
}
