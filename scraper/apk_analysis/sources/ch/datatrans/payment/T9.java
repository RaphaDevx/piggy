package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* loaded from: classes3.dex */
public final class T9 extends SuspendLambda implements Function1 {
    public int a;
    public final /* synthetic */ U9 b;
    public final /* synthetic */ Object c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public T9(U9 u9, Object obj, Continuation continuation) {
        super(1, continuation);
        this.b = u9;
        this.c = obj;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Continuation continuation) {
        return new T9(this.b, this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return new T9(this.b, this.c, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, ch.datatrans.payment.S9] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            C7 a = r4.a();
            U9 u9 = this.b;
            Y8 y8 = u9.c;
            PaymentMethodType paymentMethodType = u9.e;
            Q5 q5 = u9.d;
            Object obj2 = this.c;
            this.a = 1;
            obj = a.a(y8, paymentMethodType, q5, obj2, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        V9 v9 = (V9) obj;
        U9 u92 = this.b;
        u92.b.a(v9.b, v9.a, u92.e);
        return Unit.INSTANCE;
    }
}
