package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.CardExpiryDate;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class O2 implements Serializable {
    public final PaymentMethodType a;
    public final CardExpiryDate b;
    public final String c;
    public final String d;

    public O2(PaymentMethodType paymentMethodType, CardExpiryDate cardExpiryDate, String number, String cvv) {
        Intrinsics.checkNotNullParameter(number, "number");
        Intrinsics.checkNotNullParameter(cvv, "cvv");
        this.a = paymentMethodType;
        this.b = cardExpiryDate;
        this.c = number;
        this.d = cvv;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof O2)) {
            return false;
        }
        O2 o2 = (O2) obj;
        return this.a == o2.a && Intrinsics.areEqual(this.b, o2.b) && Intrinsics.areEqual(this.c, o2.c) && Intrinsics.areEqual(this.d, o2.d);
    }

    public final int hashCode() {
        PaymentMethodType paymentMethodType = this.a;
        int hashCode = (paymentMethodType == null ? 0 : paymentMethodType.hashCode()) * 31;
        CardExpiryDate cardExpiryDate = this.b;
        return this.d.hashCode() + ((this.c.hashCode() + ((hashCode + (cardExpiryDate != null ? cardExpiryDate.hashCode() : 0)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CreditCardModel(paymentMethodType=");
        sb.append(this.a);
        sb.append(", expiryDate=");
        sb.append(this.b);
        sb.append(", number=");
        sb.append(this.c);
        sb.append(", cvv=");
        return N7.a(sb, this.d, ')');
    }
}
