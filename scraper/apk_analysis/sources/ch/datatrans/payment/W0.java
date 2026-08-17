package ch.datatrans.payment;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class W0 implements Serializable {
    public String a;
    public String b;
    public long c;
    public String d;
    public String e;
    public a1 f;
    public boolean g;
    public boolean h;

    public W0() {
        a1 dccStatus = a1.c;
        Intrinsics.checkNotNullParameter("", "dccId");
        Intrinsics.checkNotNullParameter("", "dccCurrency");
        Intrinsics.checkNotNullParameter("", "dccRate");
        Intrinsics.checkNotNullParameter("", "dccMarkupPercentage");
        Intrinsics.checkNotNullParameter(dccStatus, "dccStatus");
        this.a = "";
        this.b = "";
        this.c = -1L;
        this.d = "";
        this.e = "";
        this.f = dccStatus;
        this.g = false;
        this.h = false;
    }

    public final void a(X0 result) {
        Long l;
        Intrinsics.checkNotNullParameter(result, "result");
        String str = result.b;
        if (str == null || (l = result.c) == null || result.d == null || result.e == null) {
            this.f = a1.c;
            return;
        }
        this.a = result.a;
        this.b = str;
        this.c = l.longValue();
        this.d = result.d;
        this.e = result.e;
        this.f = a1.b;
        this.g = true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof W0)) {
            return false;
        }
        W0 w0 = (W0) obj;
        return Intrinsics.areEqual(this.a, w0.a) && Intrinsics.areEqual(this.b, w0.b) && this.c == w0.c && Intrinsics.areEqual(this.d, w0.d) && Intrinsics.areEqual(this.e, w0.e) && this.f == w0.f && this.g == w0.g && this.h == w0.h;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.h) + ((Boolean.hashCode(this.g) + ((this.f.hashCode() + ((this.e.hashCode() + ((this.d.hashCode() + ((Long.hashCode(this.c) + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "CreditCardDCCModel(dccId=" + this.a + ", dccCurrency=" + this.b + ", dccAmount=" + this.c + ", dccRate=" + this.d + ", dccMarkupPercentage=" + this.e + ", dccStatus=" + this.f + ", isNewRequest=" + this.g + ", isDCCPreSelected=" + this.h + ')';
    }
}
