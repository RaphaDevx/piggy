package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import ch.datatrans.payment.paymentmethods.SavedPaymentMethod;
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
public final class Ja extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ Ka b;
    public Object c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Ja(Ka ka, Continuation continuation) {
        super(2, continuation);
        this.b = ka;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new Ja(this.b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new Ja(this.b, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                W8 w8 = this.b.a.k;
                Intrinsics.checkNotNull(w8);
                Intrinsics.checkNotNullParameter(w8, "<this>");
                C0142fe c0142fe = new C0142fe(w8.a, w8.b, w8.c, w8.d, w8.e, w8.f);
                C7 a = r4.a();
                String str = this.b.h;
                if (str == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("walletToken");
                    str = null;
                }
                C0130de c0130de = new C0130de(str, null, null);
                PaymentMethodType paymentMethodType = PaymentMethodType.SAMSUNG_PAY;
                this.c = SpillingKt.nullOutSpilledVariable(c0142fe);
                this.a = 1;
                obj = a.a(c0142fe, c0130de, paymentMethodType, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            Ka ka = this.b;
            String str2 = ((C0137ee) obj).a;
            int i2 = Ka.i;
            ka.a.a(new SavedPaymentMethod(PaymentMethodType.SAMSUNG_PAY, ""));
            ka.a.n = str2;
            ka.c.postValue(Unit.INSTANCE);
        } catch (D7 unused) {
            Ka ka2 = this.b;
            int i3 = Ka.i;
            ka2.a();
        } catch (SSLException unused2) {
            Ka ka3 = this.b;
            int i4 = Ka.i;
            ka3.b();
        } catch (Exception e) {
            Ka ka4 = this.b;
            int i5 = Ka.i;
            ka4.a(e);
        }
        return Unit.INSTANCE;
    }
}
