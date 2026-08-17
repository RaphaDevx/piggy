package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes3.dex */
public final class T0 extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ V0 b;
    public final /* synthetic */ C0219t0 c;
    public final /* synthetic */ PaymentMethodType d;
    public final /* synthetic */ String e;
    public final /* synthetic */ String f;
    public final /* synthetic */ Q3 g;
    public final /* synthetic */ Function2 h;
    public final /* synthetic */ Function2 i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public T0(V0 v0, C0219t0 c0219t0, PaymentMethodType paymentMethodType, String str, String str2, Q3 q3, Function2 function2, Function2 function22, Continuation continuation) {
        super(2, continuation);
        this.b = v0;
        this.c = c0219t0;
        this.d = paymentMethodType;
        this.e = str;
        this.f = str2;
        this.g = q3;
        this.h = function2;
        this.i = function22;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new T0(this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((T0) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v8 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                V0 v0 = this.b;
                C0219t0 c0219t0 = this.c;
                PaymentMethodType paymentMethodType = this.d;
                String str = this.e;
                String str2 = this.f;
                Q3 q3 = this.g;
                Function2 function2 = this.h;
                this.a = 1;
                Object a = V0.a(v0, c0219t0, paymentMethodType, str, str2, q3, function2, this);
                this = a;
                if (a == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this = this;
            }
        } catch (Exception e) {
            this.i.invoke(e, this.g);
        }
        return Unit.INSTANCE;
    }
}
