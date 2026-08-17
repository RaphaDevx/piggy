package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import javax.net.ssl.SSLException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class R8 extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ S8 b;
    public Object c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public R8(S8 s8, Continuation continuation) {
        super(2, continuation);
        this.b = s8;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new R8(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new R8(this.b, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        W8 w8;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                C0131e aliasRequest = this.b.e().d;
                if (aliasRequest != null) {
                    Intrinsics.checkNotNullParameter(aliasRequest, "aliasRequest");
                    w8 = new W8(0, aliasRequest.b, aliasRequest.a, aliasRequest.c, aliasRequest.d, aliasRequest.e, "");
                } else {
                    w8 = this.b.e().k;
                    Intrinsics.checkNotNull(w8);
                }
                C7 a = r4.a();
                PaymentMethodType paymentMethodType = PaymentMethodType.PAY_PAL;
                Q5 q5 = this.b.e().h;
                this.c = SpillingKt.nullOutSpilledVariable(w8);
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
            this.b.e().n = lb.a;
            S8 s8 = this.b;
            s8.h.postValue(lb.b);
            this.b.a.postValue(null);
        } catch (D7 unused) {
            this.b.a(new Q8(this.b));
        } catch (SSLException unused2) {
            this.b.c(new P8(this.b));
        } catch (Exception e) {
            this.b.a(e, PaymentMethodType.PAY_PAL);
        }
        return Unit.INSTANCE;
    }
}
