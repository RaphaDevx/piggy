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

/* renamed from: ch.datatrans.payment.s6, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0215s6 extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ C0221t6 b;
    public Object c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0215s6(C0221t6 c0221t6, Continuation continuation) {
        super(2, continuation);
        this.b = c0221t6;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new C0215s6(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new C0215s6(this.b, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Lb lb;
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                W8 w8 = this.b.e().k;
                Intrinsics.checkNotNull(w8);
                C7 a = r4.a();
                PaymentMethodType paymentMethodType = PaymentMethodType.KLARNA;
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
            lb = (Lb) obj;
            str = lb.c;
        } catch (D7 unused) {
            this.b.a(new C0208r6(this.b));
        } catch (SSLException unused2) {
            this.b.c(new C0201q6(this.b));
        } catch (Exception e) {
            this.b.a(e, PaymentMethodType.KLARNA);
        }
        if (str != null && str.length() != 0) {
            this.b.e().n = lb.a;
            Xa xa = this.b.a;
            String str2 = lb.b;
            String str3 = lb.c;
            Intrinsics.checkNotNull(str3);
            xa.postValue(new J9(str2, str3));
            return Unit.INSTANCE;
        }
        C0221t6 c0221t6 = this.b;
        int i2 = C0221t6.l;
        c0221t6.f();
        return Unit.INSTANCE;
    }
}
