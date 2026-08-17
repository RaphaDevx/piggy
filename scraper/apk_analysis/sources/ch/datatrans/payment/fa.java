package ch.datatrans.payment;

import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import javax.net.ssl.SSLException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes3.dex */
public final class fa extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ Function1 b;
    public final /* synthetic */ C0149ga c;
    public final /* synthetic */ boolean d;
    public final /* synthetic */ PaymentMethodType e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public fa(Function1 function1, C0149ga c0149ga, boolean z, PaymentMethodType paymentMethodType, Continuation continuation) {
        super(2, continuation);
        this.b = function1;
        this.c = c0149ga;
        this.d = z;
        this.e = paymentMethodType;
    }

    public static final Unit a(C0149ga c0149ga, PaymentMethodType paymentMethodType, Function1 request) {
        c0149ga.getClass();
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        Intrinsics.checkNotNullParameter(request, "request");
        BuildersKt__Builders_commonKt.launch$default(c0149ga.a, null, null, new fa(request, c0149ga, false, paymentMethodType, null), 3, null);
        return Unit.INSTANCE;
    }

    public static final Unit b(C0149ga c0149ga, PaymentMethodType paymentMethodType, Function1 request) {
        c0149ga.getClass();
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        Intrinsics.checkNotNullParameter(request, "request");
        BuildersKt__Builders_commonKt.launch$default(c0149ga.a, null, null, new fa(request, c0149ga, true, paymentMethodType, null), 3, null);
        return Unit.INSTANCE;
    }

    public static final Unit c(C0149ga c0149ga, PaymentMethodType paymentMethodType, Function1 request) {
        c0149ga.getClass();
        Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
        Intrinsics.checkNotNullParameter(request, "request");
        BuildersKt__Builders_commonKt.launch$default(c0149ga.a, null, null, new fa(request, c0149ga, false, paymentMethodType, null), 3, null);
        return Unit.INSTANCE;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new fa(this.b, this.c, this.d, this.e, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((fa) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [androidx.lifecycle.ViewModel, ch.datatrans.payment.f4] */
    /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, ch.datatrans.payment.f4] */
    /* JADX WARN: Type inference failed for: r0v4, types: [androidx.lifecycle.ViewModel, ch.datatrans.payment.f4] */
    /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, ch.datatrans.payment.f4] */
    /* JADX WARN: Type inference failed for: r3v13 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Function1 function1 = this.b;
                this.a = 1;
                Object invoke = function1.invoke(this);
                this = invoke;
                if (invoke == coroutine_suspended) {
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
            if (this.d) {
                final C0149ga c0149ga = this.c;
                ?? r0 = c0149ga.b;
                final PaymentMethodType paymentMethodType = this.e;
                final Function1 function12 = this.b;
                r0.b(new Function0() { // from class: ch.datatrans.payment.fa$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return fa.b(C0149ga.this, paymentMethodType, function12);
                    }
                });
            } else {
                final C0149ga c0149ga2 = this.c;
                ?? r02 = c0149ga2.b;
                final PaymentMethodType paymentMethodType2 = this.e;
                final Function1 function13 = this.b;
                r02.a(new Function0() { // from class: ch.datatrans.payment.fa$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return fa.c(C0149ga.this, paymentMethodType2, function13);
                    }
                });
            }
        } catch (SSLException unused2) {
            final C0149ga c0149ga3 = this.c;
            ?? r03 = c0149ga3.b;
            final PaymentMethodType paymentMethodType3 = this.e;
            final Function1 function14 = this.b;
            r03.c(new Function0() { // from class: ch.datatrans.payment.fa$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return fa.a(C0149ga.this, paymentMethodType3, function14);
                }
            });
        } catch (Exception e) {
            this.c.b.a(e, this.e);
        }
        return Unit.INSTANCE;
    }
}
