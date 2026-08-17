package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Y0 {
    public final C0219t0 a;
    public final Function2 b;
    public final Function2 c;
    public final PaymentMethodType d;
    public final Q3 e;
    public final String f;
    public final String g;

    public Y0(C0219t0 dccData, Function2 errorAction, Function2 successAction, PaymentMethodType paymentMethodType, Q3 dccType, String str, String str2) {
        Intrinsics.checkNotNullParameter(dccData, "dccData");
        Intrinsics.checkNotNullParameter(errorAction, "errorAction");
        Intrinsics.checkNotNullParameter(successAction, "successAction");
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        Intrinsics.checkNotNullParameter(dccType, "dccType");
        this.a = dccData;
        this.b = errorAction;
        this.c = successAction;
        this.d = paymentMethodType;
        this.e = dccType;
        this.f = str;
        this.g = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Y0)) {
            return false;
        }
        Y0 y0 = (Y0) obj;
        return Intrinsics.areEqual(this.a, y0.a) && Intrinsics.areEqual(this.b, y0.b) && Intrinsics.areEqual(this.c, y0.c) && this.d == y0.d && this.e == y0.e && Intrinsics.areEqual(this.f, y0.f) && Intrinsics.areEqual(this.g, y0.g);
    }

    public final int hashCode() {
        int hashCode = (this.e.hashCode() + ((this.d.hashCode() + ((this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31)) * 31)) * 31)) * 31;
        String str = this.f;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.g;
        return hashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CreditCardDCCServiceConfig(dccData=");
        sb.append(this.a);
        sb.append(", errorAction=");
        sb.append(this.b);
        sb.append(", successAction=");
        sb.append(this.c);
        sb.append(", paymentMethodType=");
        sb.append(this.d);
        sb.append(", dccType=");
        sb.append(this.e);
        sb.append(", alias=");
        sb.append(this.f);
        sb.append(", number=");
        return N7.a(sb, this.g, ')');
    }
}
