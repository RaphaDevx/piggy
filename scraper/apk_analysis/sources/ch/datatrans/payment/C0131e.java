package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.e, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0131e {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;

    public C0131e(String merchantId, String currencyCode, String refno, String str, String str2) {
        Intrinsics.checkNotNullParameter(merchantId, "merchantId");
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        Intrinsics.checkNotNullParameter(refno, "refno");
        this.a = merchantId;
        this.b = currencyCode;
        this.c = refno;
        this.d = str;
        this.e = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0131e)) {
            return false;
        }
        C0131e c0131e = (C0131e) obj;
        return Intrinsics.areEqual(this.a, c0131e.a) && Intrinsics.areEqual(this.b, c0131e.b) && Intrinsics.areEqual(this.c, c0131e.c) && Intrinsics.areEqual(this.d, c0131e.d) && Intrinsics.areEqual(this.e, c0131e.e);
    }

    public final int hashCode() {
        int hashCode = (this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31)) * 31;
        String str = this.d;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.e;
        return hashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AliasRequest(merchantId=");
        sb.append(this.a);
        sb.append(", currencyCode=");
        sb.append(this.b);
        sb.append(", refno=");
        sb.append(this.c);
        sb.append(", refno2=");
        sb.append(this.d);
        sb.append(", refno3=");
        return N7.a(sb, this.e, ')');
    }
}
