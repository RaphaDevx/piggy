package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class H {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final int f;
    public final String g;

    public H(int i, String transactionId, String merchantId, String refno, String str, String str2, String currency) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        Intrinsics.checkNotNullParameter(merchantId, "merchantId");
        Intrinsics.checkNotNullParameter(refno, "refno");
        Intrinsics.checkNotNullParameter(currency, "currency");
        this.a = transactionId;
        this.b = merchantId;
        this.c = refno;
        this.d = str;
        this.e = str2;
        this.f = i;
        this.g = currency;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof H)) {
            return false;
        }
        H h = (H) obj;
        return Intrinsics.areEqual(this.a, h.a) && Intrinsics.areEqual(this.b, h.b) && Intrinsics.areEqual(this.c, h.c) && Intrinsics.areEqual(this.d, h.d) && Intrinsics.areEqual(this.e, h.e) && this.f == h.f && Intrinsics.areEqual(this.g, h.g);
    }

    public final int hashCode() {
        int hashCode = (this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31)) * 31;
        String str = this.d;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.e;
        int hashCode3 = str2 != null ? str2.hashCode() : 0;
        return this.g.hashCode() + ((Integer.hashCode(this.f) + ((hashCode2 + hashCode3) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CancelServiceRequest(transactionId=");
        sb.append(this.a);
        sb.append(", merchantId=");
        sb.append(this.b);
        sb.append(", refno=");
        sb.append(this.c);
        sb.append(", refno2=");
        sb.append(this.d);
        sb.append(", refno3=");
        sb.append(this.e);
        sb.append(", amount=");
        sb.append(this.f);
        sb.append(", currency=");
        return N7.a(sb, this.g, ')');
    }
}
