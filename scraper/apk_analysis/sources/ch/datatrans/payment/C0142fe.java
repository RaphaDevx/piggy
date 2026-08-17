package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.fe, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0142fe {
    public final int a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;

    public C0142fe(int i, String currencyCode, String merchantId, String refno, String str, String str2) {
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        Intrinsics.checkNotNullParameter(merchantId, "merchantId");
        Intrinsics.checkNotNullParameter(refno, "refno");
        this.a = i;
        this.b = currencyCode;
        this.c = merchantId;
        this.d = refno;
        this.e = str;
        this.f = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0142fe)) {
            return false;
        }
        C0142fe c0142fe = (C0142fe) obj;
        return this.a == c0142fe.a && Intrinsics.areEqual(this.b, c0142fe.b) && Intrinsics.areEqual(this.c, c0142fe.c) && Intrinsics.areEqual(this.d, c0142fe.d) && Intrinsics.areEqual(this.e, c0142fe.e) && Intrinsics.areEqual(this.f, c0142fe.f);
    }

    public final int hashCode() {
        int hashCode = (this.d.hashCode() + ((this.c.hashCode() + ((this.b.hashCode() + (Integer.hashCode(this.a) * 31)) * 31)) * 31)) * 31;
        String str = this.e;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.f;
        return hashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("WalletServiceTransactionInfo(amount=");
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
        return N7.a(sb, this.f, ')');
    }
}
