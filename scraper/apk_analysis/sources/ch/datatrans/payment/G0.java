package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class G0 extends AbstractC0138f {
    public final Y8 l;
    public final SavedPaymentMethod m;
    public final X9 n;
    public final Map o;
    public final PaymentMethodType p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public G0(String str, Y8 payment, SavedPaymentMethod savedPaymentMethod, X9 reqType, LinkedHashMap merchantProperties, PaymentMethodType paymentMethodType) {
        super(str);
        Intrinsics.checkNotNullParameter(payment, "payment");
        Intrinsics.checkNotNullParameter(reqType, "reqType");
        Intrinsics.checkNotNullParameter(merchantProperties, "merchantProperties");
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        this.l = payment;
        this.m = savedPaymentMethod;
        this.n = reqType;
        this.o = merchantProperties;
        this.p = paymentMethodType;
    }
}
