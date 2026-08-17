package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import javax.net.ssl.SSLException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class pd extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ C0224td b;
    public final /* synthetic */ Nb c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public pd(C0224td c0224td, Nb nb, Continuation continuation) {
        super(2, continuation);
        this.b = c0224td;
        this.c = nb;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new pd(this.b, this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new pd(this.b, this.c, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v13 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.b.e.postValue(Boxing.boxBoolean(true));
                C0224td c0224td = this.b;
                Nb nb = this.c;
                this.a = 1;
                Object a = C0224td.a(c0224td, nb, this);
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
        } catch (D7 unused) {
            C0224td c0224td2 = this.b;
            Nb nb2 = this.c;
            int i2 = C0224td.f;
            c0224td2.a(nb2);
        } catch (SSLException unused2) {
            C0224td c0224td3 = this.b;
            Nb nb3 = this.c;
            int i3 = C0224td.f;
            c0224td3.b(nb3);
        } catch (Exception e) {
            C0224td c0224td4 = this.b;
            PaymentMethodType paymentMethodType = this.c.i;
            Intrinsics.checkNotNull(paymentMethodType);
            String str = this.c.n;
            int i4 = C0224td.f;
            c0224td4.a(e, paymentMethodType, str);
        }
        return Unit.INSTANCE;
    }
}
