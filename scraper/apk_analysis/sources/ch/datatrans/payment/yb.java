package ch.datatrans.payment;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class yb {
    public final String a;
    public final int b;
    public final String c;

    public yb(String merchantId, int i, String currencyCode) {
        Intrinsics.checkNotNullParameter(merchantId, "merchantId");
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        this.a = merchantId;
        this.b = i;
        this.c = currencyCode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof yb)) {
            return false;
        }
        yb ybVar = (yb) obj;
        return Intrinsics.areEqual(this.a, ybVar.a) && this.b == ybVar.b && Intrinsics.areEqual(this.c, ybVar.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((Integer.hashCode(this.b) + (this.a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ThreeDStatus(merchantId=");
        sb.append(this.a);
        sb.append(", amount=");
        sb.append(this.b);
        sb.append(", currencyCode=");
        return N7.a(sb, this.c, ')');
    }
}
