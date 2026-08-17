package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;

/* renamed from: ch.datatrans.payment.u5, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0230u5 implements Ae {
    public final C0185n5 a;
    public final Be b;
    public final Lazy c;
    public final int d;
    public final String e;

    public C0230u5(C0185n5 c0185n5, final String str, final PaymentMethodType paymentMethodType, Be be) {
        this.a = c0185n5;
        this.b = be;
        this.c = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.u5$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return C0230u5.a(PaymentMethodType.this, str, this);
            }
        });
        this.d = paymentMethodType.getViewPortWidth();
        this.e = paymentMethodType.getViewPortAdjustment();
    }

    public static final List a(PaymentMethodType paymentMethodType, String str, C0230u5 c0230u5) {
        return CollectionsKt.listOf(new C0214s5(paymentMethodType.generateAppCallbackUrl$lib_release(str), c0230u5.b));
    }

    @Override // ch.datatrans.payment.Ae
    public final int b() {
        return this.d;
    }

    @Override // ch.datatrans.payment.Ae
    public final AbstractC0200q5 c() {
        return this.a;
    }

    @Override // ch.datatrans.payment.Ae
    public final boolean d() {
        return false;
    }

    @Override // ch.datatrans.payment.Ae
    public final String e() {
        return this.a.a;
    }

    @Override // ch.datatrans.payment.Ae
    public final List f() {
        return (List) this.c.getValue();
    }

    @Override // ch.datatrans.payment.Ae
    public final Be g() {
        return this.b;
    }

    @Override // ch.datatrans.payment.Ae
    public final String a() {
        return this.e;
    }
}
