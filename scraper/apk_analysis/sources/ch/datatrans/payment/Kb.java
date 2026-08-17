package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Kb {
    public final C0149ga a;
    public final D4 b;
    public final W8 c;
    public final Q5 d;
    public final PaymentMethodType e;

    /* JADX WARN: Multi-variable type inference failed */
    public Kb(C0149ga retryRequestHandler, Ib callback, W8 payment, Q5 options, PaymentMethodType paymentMethodType) {
        Intrinsics.checkNotNullParameter(retryRequestHandler, "retryRequestHandler");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(payment, "payment");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        this.a = retryRequestHandler;
        this.b = (D4) callback;
        this.c = payment;
        this.d = options;
        this.e = paymentMethodType;
    }
}
