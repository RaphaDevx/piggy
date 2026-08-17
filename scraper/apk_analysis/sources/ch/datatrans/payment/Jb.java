package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* loaded from: classes3.dex */
public final class Jb extends SuspendLambda implements Function1 {
    public int a;
    public final /* synthetic */ Kb b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Jb(Kb kb, Continuation continuation) {
        super(1, continuation);
        this.b = kb;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Continuation continuation) {
        return new Jb(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return new Jb(this.b, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r5v2, types: [ch.datatrans.payment.D4, ch.datatrans.payment.Ib] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            C7 a = r4.a();
            Kb kb = this.b;
            W8 w8 = kb.c;
            PaymentMethodType paymentMethodType = kb.e;
            Q5 q5 = kb.d;
            this.a = 1;
            obj = a.a(w8, paymentMethodType, q5, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Lb lb = (Lb) obj;
        this.b.b.a(lb.b, lb.a);
        return Unit.INSTANCE;
    }
}
