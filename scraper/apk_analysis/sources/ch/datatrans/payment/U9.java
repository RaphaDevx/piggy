package ch.datatrans.payment;

import androidx.lifecycle.ViewModel;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class U9 {
    public final C0149ga a;
    public final ViewModel b;
    public final Y8 c;
    public final Q5 d;
    public final PaymentMethodType e;

    /* JADX WARN: Multi-variable type inference failed */
    public U9(C0149ga retryRequestHandler, S9 callback, Y8 paymentData, Q5 options, PaymentMethodType paymentMethodType) {
        Intrinsics.checkNotNullParameter(retryRequestHandler, "retryRequestHandler");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(paymentData, "paymentData");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        this.a = retryRequestHandler;
        this.b = (ViewModel) callback;
        this.c = paymentData;
        this.d = options;
        this.e = paymentMethodType;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public U9(C0149ga retryRequestHandler, S9 callback, W8 payment, Q5 options, PaymentMethodType paymentMethodType, String str) {
        this(retryRequestHandler, callback, Z8.a(payment, str), options, paymentMethodType);
        Intrinsics.checkNotNullParameter(retryRequestHandler, "retryRequestHandler");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(payment, "payment");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
    }
}
