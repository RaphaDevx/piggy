package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
import java.util.LinkedHashMap;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.jd, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0166jd extends SuspendLambda implements Function1 {
    public int a;
    public final /* synthetic */ C0176ld b;
    public Object c;
    public Object d;
    public Object e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0166jd(C0176ld c0176ld, Continuation continuation) {
        super(1, continuation);
        this.b = c0176ld;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Continuation continuation) {
        return new C0166jd(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return new C0166jd(this.b, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            C0131e c0131e = this.b.e().d;
            Intrinsics.checkNotNull(c0131e);
            String str = this.b.e().n;
            Intrinsics.checkNotNull(str);
            LinkedHashMap linkedHashMap = this.b.e().h.k;
            C7 a = r4.a();
            this.c = SpillingKt.nullOutSpilledVariable(c0131e);
            this.d = SpillingKt.nullOutSpilledVariable(str);
            this.e = SpillingKt.nullOutSpilledVariable(linkedHashMap);
            this.a = 1;
            obj = a.a(c0131e, str, linkedHashMap, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        this.b.e().a(new SavedPaymentMethod(PaymentMethodType.TWINT, ((C0249wb) obj).a));
        Xa xa = this.b.b;
        Unit unit = Unit.INSTANCE;
        xa.postValue(unit);
        return unit;
    }
}
