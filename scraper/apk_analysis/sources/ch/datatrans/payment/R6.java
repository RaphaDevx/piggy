package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.AuthorizationInitRequestConfig;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* loaded from: classes3.dex */
public final class R6 extends SuspendLambda implements Function2 {
    public final /* synthetic */ C0118c7 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public R6(C0118c7 c0118c7, Continuation continuation) {
        super(2, continuation);
        this.a = c0118c7;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new R6(this.a, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new R6(this.a, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        k7 k7Var = (k7) this.a.c.getValue();
        U9 u9 = k7Var.h;
        PaymentMethodType paymentMethodType = PaymentMethodType.MOBILE_PAY;
        String str = k7Var.e().h.a;
        Intrinsics.checkNotNull(str);
        AuthorizationInitRequestConfig authorizationInitRequestConfig = new AuthorizationInitRequestConfig(paymentMethodType.generateAppCallbackUrl$lib_release(str));
        C0149ga c0149ga = u9.a;
        PaymentMethodType paymentMethodType2 = u9.e;
        T9 request = new T9(u9, authorizationInitRequestConfig, null);
        c0149ga.getClass();
        Intrinsics.checkNotNullParameter(paymentMethodType2, "paymentMethodType");
        Intrinsics.checkNotNullParameter(request, "request");
        BuildersKt__Builders_commonKt.launch$default(c0149ga.a, null, null, new fa(request, c0149ga, false, paymentMethodType2, null), 3, null);
        return Unit.INSTANCE;
    }
}
