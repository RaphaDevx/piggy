package ch.datatrans.payment;

import ch.datatrans.payment.exception.BackendException;
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

/* renamed from: ch.datatrans.payment.h, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0151h extends SuspendLambda implements Function2 {
    public int a;
    public final /* synthetic */ C0182n b;
    public final /* synthetic */ Nb c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0151h(C0182n c0182n, Nb nb, Continuation continuation) {
        super(2, continuation);
        this.b = c0182n;
        this.c = nb;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new C0151h(this.b, this.c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return new C0151h(this.b, this.c, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v15 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.a;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.b.d.postValue(Boxing.boxBoolean(true));
                C0182n c0182n = this.b;
                Nb nb = this.c;
                this.a = 1;
                Object a = C0182n.a(c0182n, nb, this);
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
            C0182n c0182n2 = this.b;
            Nb nb2 = this.c;
            int i2 = C0182n.f;
            c0182n2.b(nb2);
        } catch (SSLException unused2) {
            C0182n c0182n3 = this.b;
            Nb nb3 = this.c;
            int i3 = C0182n.f;
            c0182n3.c(nb3);
        } catch (Exception e) {
            if ((e instanceof BackendException) && ((BackendException) e).isHttpConflictCancelled$lib_release()) {
                Xa xa = this.b.e;
                Unit unit = Unit.INSTANCE;
                xa.postValue(unit);
                return unit;
            }
            C0182n c0182n4 = this.b;
            Nb nb4 = this.c;
            boolean z = nb4.d != null;
            PaymentMethodType paymentMethodType = nb4.i;
            Intrinsics.checkNotNull(paymentMethodType);
            String str = this.c.n;
            int i4 = C0182n.f;
            c0182n4.a(e, z, paymentMethodType, str);
        }
        return Unit.INSTANCE;
    }
}
