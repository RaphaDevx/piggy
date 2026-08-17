package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.t0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0219t0 {
    public final String a;
    public final List b;
    public final boolean c;
    public final List d;

    public C0219t0(String str, List enabledPaymentMethods, List preSelectedPaymentMethods, boolean z) {
        Intrinsics.checkNotNullParameter(enabledPaymentMethods, "enabledPaymentMethods");
        Intrinsics.checkNotNullParameter(preSelectedPaymentMethods, "preSelectedPaymentMethods");
        this.a = str;
        this.b = enabledPaymentMethods;
        this.d = preSelectedPaymentMethods;
        this.c = z;
    }

    public final boolean a(PaymentMethodType paymentMethodType) {
        return paymentMethodType != null && this.b.contains(paymentMethodType.getIdentifier()) && this.c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0219t0)) {
            return false;
        }
        C0219t0 c0219t0 = (C0219t0) obj;
        return Intrinsics.areEqual(this.a, c0219t0.a) && Intrinsics.areEqual(this.b, c0219t0.b) && Intrinsics.areEqual(this.d, c0219t0.d) && this.c == c0219t0.c;
    }

    public final int hashCode() {
        String str = this.a;
        return Boolean.hashCode(this.c) + ((this.d.hashCode() + ((this.b.hashCode() + ((str == null ? 0 : str.hashCode()) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "CreditCardDCCData(merchantId=" + this.a + ", enabledPaymentMethods=" + this.b + ", preSelectedPaymentMethods=" + this.d + ", hasDCC=" + this.c + ')';
    }
}
