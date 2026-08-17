package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Y8 {
    public final String a;
    public final int b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;

    public Y8(int i, String merchantId, String currencyCode, String refno, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(merchantId, "merchantId");
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        Intrinsics.checkNotNullParameter(refno, "refno");
        this.a = merchantId;
        this.b = i;
        this.c = currencyCode;
        this.d = refno;
        this.e = str;
        this.f = str2;
        this.g = str3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Y8)) {
            return false;
        }
        Y8 y8 = (Y8) obj;
        return Intrinsics.areEqual(this.a, y8.a) && this.b == y8.b && Intrinsics.areEqual(this.c, y8.c) && Intrinsics.areEqual(this.d, y8.d) && Intrinsics.areEqual(this.e, y8.e) && Intrinsics.areEqual(this.f, y8.f) && Intrinsics.areEqual(this.g, y8.g);
    }

    public final int hashCode() {
        int hashCode = (this.d.hashCode() + ((this.c.hashCode() + ((Integer.hashCode(this.b) + (this.a.hashCode() * 31)) * 31)) * 31)) * 31;
        String str = this.e;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.f;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.g;
        return hashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PaymentData(merchantId=");
        sb.append(this.a);
        sb.append(", amount=");
        sb.append(this.b);
        sb.append(", currencyCode=");
        sb.append(this.c);
        sb.append(", refno=");
        sb.append(this.d);
        sb.append(", refno2=");
        sb.append(this.e);
        sb.append(", refno3=");
        sb.append(this.f);
        sb.append(", alias=");
        return N7.a(sb, this.g, ')');
    }
}
