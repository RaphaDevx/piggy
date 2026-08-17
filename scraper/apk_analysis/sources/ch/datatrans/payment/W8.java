package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class W8 {
    public final int a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;

    public W8(int i, String currencyCode, String merchantId, String refno, String str, String str2, String localizedPriceDescription) {
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        Intrinsics.checkNotNullParameter(merchantId, "merchantId");
        Intrinsics.checkNotNullParameter(refno, "refno");
        Intrinsics.checkNotNullParameter(localizedPriceDescription, "localizedPriceDescription");
        this.a = i;
        this.b = currencyCode;
        this.c = merchantId;
        this.d = refno;
        this.e = str;
        this.f = str2;
        this.g = localizedPriceDescription;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof W8)) {
            return false;
        }
        W8 w8 = (W8) obj;
        return this.a == w8.a && Intrinsics.areEqual(this.b, w8.b) && Intrinsics.areEqual(this.c, w8.c) && Intrinsics.areEqual(this.d, w8.d) && Intrinsics.areEqual(this.e, w8.e) && Intrinsics.areEqual(this.f, w8.f) && Intrinsics.areEqual(this.g, w8.g);
    }

    public final int hashCode() {
        int hashCode = (this.d.hashCode() + ((this.c.hashCode() + ((this.b.hashCode() + (Integer.hashCode(this.a) * 31)) * 31)) * 31)) * 31;
        String str = this.e;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.f;
        return this.g.hashCode() + ((hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Payment(amount=");
        sb.append(this.a);
        sb.append(", currencyCode=");
        sb.append(this.b);
        sb.append(", merchantId=");
        sb.append(this.c);
        sb.append(", refno=");
        sb.append(this.d);
        sb.append(", refno2=");
        sb.append(this.e);
        sb.append(", refno3=");
        sb.append(this.f);
        sb.append(", localizedPriceDescription=");
        return N7.a(sb, this.g, ')');
    }
}
