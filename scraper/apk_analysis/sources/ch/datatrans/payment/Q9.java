package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Q9 extends I9 {
    public final Lazy g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Q9(C0196p5 postRequest, PaymentMethodType type, final Jc listener) {
        super(postRequest, type, listener, false);
        Intrinsics.checkNotNullParameter(postRequest, "postRequest");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.g = LazyKt.lazy(new Function0() { // from class: ch.datatrans.payment.Q9$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Q9.a(Q9.this, listener);
            }
        });
    }

    public static final List a(Q9 q9, lg lgVar) {
        return CollectionsKt.plus((Collection<? extends O9>) q9.d.getValue(), new O9(lgVar));
    }

    @Override // ch.datatrans.payment.I9, ch.datatrans.payment.Ae
    public final boolean d() {
        return false;
    }

    @Override // ch.datatrans.payment.I9, ch.datatrans.payment.Ae
    public final List f() {
        return (List) this.g.getValue();
    }

    @Override // ch.datatrans.payment.I9
    /* renamed from: h */
    public final List f() {
        return (List) this.g.getValue();
    }
}
