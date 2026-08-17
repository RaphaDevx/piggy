package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.CardExpiryDate;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.ee, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0137ee {
    public final String a;
    public final Boolean b;
    public final CardExpiryDate c;
    public final PaymentMethodType d;
    public final String e;
    public final String f;
    public final String g;

    public C0137ee(String transactionId, Boolean bool, CardExpiryDate cardExpiryDate, PaymentMethodType paymentMethodType, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(transactionId, "transactionId");
        this.a = transactionId;
        this.b = bool;
        this.c = cardExpiryDate;
        this.d = paymentMethodType;
        this.e = str;
        this.f = str2;
        this.g = str3;
    }
}
